package com.alasdeplata.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.alasdeplata.enums.RoleEnum;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.alasdeplata.common.JwtUtils;
import com.alasdeplata.dto.auth.AuthCreateUserRequest;
import com.alasdeplata.dto.auth.AuthLoginRequest;
import com.alasdeplata.dto.auth.AuthResponse;
import com.alasdeplata.models.Role;
import com.alasdeplata.models.UserEntity;
import com.alasdeplata.repository.RoleRepository;
import com.alasdeplata.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import static com.alasdeplata.enums.RoleEnum.USER;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

        private final PasswordEncoder passwordEncoder;
        private final JwtUtils jwtUtils;
        private final UserRepository userRepository;
        private final RoleRepository roleRepository;

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                UserEntity user = userRepository.findUserEntityByUsername(username)
                        .orElseThrow(() -> new UsernameNotFoundException("El usuario " + username + " no existe."));

                if (!user.isEnabled())
                        throw new DisabledException("Token no válido");

                List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

                user.getRoles().forEach(role -> {
                        authorityList.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleEnum().name()));
                        role.getPermissions().forEach(permission ->
                                authorityList.add(new SimpleGrantedAuthority(permission.getName()))
                        );
                });

                return new User(
                        user.getUsername(),
                        user.getPassword(),
                        user.isEnabled(),
                        user.isAccountNoExpired(),
                        user.isCredentialNoExpired(),
                        user.isAccountNoLocked(),
                        authorityList
                );
        }

        public AuthResponse loginUser(AuthLoginRequest authLoginRequest) {
                String username = authLoginRequest.username();
                String password = authLoginRequest.password();

                Authentication authentication = this.authenticate(username, password);
                SecurityContextHolder.getContext().setAuthentication(authentication);

                String accessToken = jwtUtils.createToken(authentication);
                return new AuthResponse(username, "User logged successfuly", accessToken, true);
        }

        public Authentication authenticate(String username, String password) {
                UserDetails userDetails = this.loadUserByUsername(username);

                if (userDetails == null) {
                        throw new BadCredentialsException("Invalid username or password");
                }

                if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                        throw new BadCredentialsException("Invalid password");
                }

                return new UsernamePasswordAuthenticationToken(username, userDetails.getPassword(),
                        userDetails.getAuthorities());
        }

        public AuthResponse createUser(AuthCreateUserRequest authCreateUserRequest) {
                String username = authCreateUserRequest.username();
                String email = authCreateUserRequest.email();
                String password = authCreateUserRequest.password();

                if (userRepository.findUserEntityByUsername(username).isPresent())
                        throw new IllegalArgumentException("El nombre de usuario ya existe.");

                if (userRepository.findByEmail(email).isPresent())
                        throw new IllegalArgumentException("El correo electrónico ya está en uso.");

                Set<Role> roleEntitiesSet = roleRepository.findRoleEntitiesByRoleEnumIn(List.of(USER))
                        .stream()
                        .collect(Collectors.toSet());

                if (roleEntitiesSet.isEmpty()) {
                        throw new IllegalArgumentException("Los roles especificados no existen.");
                }

                UserEntity user = UserEntity.builder()
                        .username(username)
                        .password(passwordEncoder.encode(password))
                        .firstName(authCreateUserRequest.firstName())
                        .lastName(authCreateUserRequest.lastName())
                        .email(authCreateUserRequest.email())
                        .termsAccepted(authCreateUserRequest.termsAccepted())
                        .newsletterSubscribed(authCreateUserRequest.newsletterSubscribed() != null
                                ? authCreateUserRequest.newsletterSubscribed()
                                : false)
                        .roles(roleEntitiesSet)
                        .isEnabled(true)
                        .accountNoLocked(true)
                        .accountNoExpired(true)
                        .credentialNoExpired(true)
                        .build();

                UserEntity userCreated = userRepository.save(user);

                ArrayList<SimpleGrantedAuthority> authorityList = new ArrayList<>();

                userCreated.getRoles().forEach(role -> {
                        authorityList.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleEnum().name()));
                        role.getPermissions().forEach(permission ->
                                authorityList.add(new SimpleGrantedAuthority(permission.getName()))
                        );
                });

                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        userCreated.getUsername(),
                        userCreated.getPassword(),
                        authorityList
                );

                String accessToken = jwtUtils.createToken(authentication);
                return new AuthResponse(userCreated.getUsername(), "User created successfuly", accessToken, true);
        }
}
