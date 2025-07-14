package com.alasdeplata.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.alasdeplata.dto.user.UserRequest;
import com.alasdeplata.dto.user.UserResponse;
import com.alasdeplata.mapper.UserMapper;
import com.alasdeplata.models.Role;
import com.alasdeplata.models.UserEntity;
import com.alasdeplata.repository.RoleRepository;
import com.alasdeplata.repository.UserRepository;
import com.alasdeplata.services.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toResponse)
                .toList();
    }

    @Override
    public Optional<UserResponse> getUserById(Long id) {
        Optional<UserResponse> userFound = userRepository.findById(id).map(userMapper::toResponse);
        return userFound.isPresent() ? userFound : Optional.empty();

    }

    @Override
    public UserResponse createUser(UserRequest userRequest) {

        Optional<UserEntity> userFound = userRepository.findByEmail(userRequest.email());

        if (userFound.isPresent())
            throw new IllegalArgumentException("El correo ya existe");

        Set<Role> roles = userRequest.roleIds().stream()
                .map(roleId -> roleRepository.findById(roleId)
                        .orElseThrow(() -> new IllegalArgumentException("Rol no encontrado")))
                .collect(Collectors.toSet());

        UserEntity userEntity = userMapper.toEntity(userRequest);
        userEntity.setRoles(roles);

        UserEntity savedUser = userRepository.save(userEntity);

        return userMapper.toResponse(savedUser);
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest userRequest) {
        UserEntity user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setFirstName(userRequest.firstName());
            user.setLastName(userRequest.lastName());
            user.setEmail(userRequest.email());
            user.setPassword(userRequest.password());
            return userMapper.toResponse(userRepository.save(user));
        }
        return null;
    }

    @Override
    public void deleteUser(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        user.setEnabled(false);
        userRepository.save(user);
    }

}
