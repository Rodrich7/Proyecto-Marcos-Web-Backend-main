package com.alasdeplata.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alasdeplata.dto.user.UserRequest;
import com.alasdeplata.dto.user.UserResponse;
import com.alasdeplata.services.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAll() {
        List<UserResponse> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable() Long id) {
        try {
            Optional<UserResponse> user = userService.getUserById(id);
            return user.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@Valid @RequestBody UserRequest user) {
        if (user.termsAccepted() == null) {
            throw new IllegalArgumentException("Debes aceptar los términos y condiciones.");
        }
        user = new UserRequest(
                user.firstName(),
                user.lastName(),
                user.name(),
                user.email().toLowerCase(),
                user.username().toLowerCase(), // 👈 evitar conflicto por mayúsculas
                user.password(),
                user.termsAccepted(),
                user.newsletterSubscribed(),
                user.phone(),
                user.roleIds()
        );
        UserResponse savedItem = userService.createUser(user);
        return new ResponseEntity<>(savedItem, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserResponse> update(@PathVariable() Long id, @RequestBody UserRequest item) {
        UserResponse updatedItem = userService.updateUser(id, item);
        return new ResponseEntity<>(updatedItem, HttpStatus.OK);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
