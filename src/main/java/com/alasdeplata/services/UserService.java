package com.alasdeplata.services;

import java.util.List;
import java.util.Optional;

import com.alasdeplata.dto.user.UserRequest;
import com.alasdeplata.dto.user.UserResponse;

public interface UserService {
    List<UserResponse> getAllUsers();

    Optional<UserResponse> getUserById(Long id);

    UserResponse createUser(UserRequest userRequest);

    UserResponse updateUser(Long id, UserRequest userRequest);

    void deleteUser(Long id);
}
