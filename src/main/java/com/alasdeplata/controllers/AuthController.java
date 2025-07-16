    package com.alasdeplata.controllers;

    import com.alasdeplata.dto.user.UserResponse;
    import com.alasdeplata.models.UserEntity;
    import com.alasdeplata.repository.UserRepository;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.security.core.context.SecurityContextHolder;
    import org.springframework.security.core.userdetails.UsernameNotFoundException;
    import org.springframework.web.bind.annotation.PostMapping;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.RequestBody;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RestController;

    import org.springframework.security.core.Authentication;

    import com.alasdeplata.dto.auth.AuthCreateUserRequest;
    import com.alasdeplata.dto.auth.AuthLoginRequest;
    import com.alasdeplata.dto.auth.AuthResponse;
    import com.alasdeplata.services.impl.UserDetailServiceImpl;

    import jakarta.validation.Valid;
    import lombok.RequiredArgsConstructor;

    import java.util.Set;
    import java.util.stream.Collectors;

    @RestController
    @RequestMapping("/api/v1/auth")
    @RequiredArgsConstructor
    public class AuthController {

        private final UserDetailServiceImpl userDetailsService;
        private final UserRepository userRepository;

        @PostMapping("/sign-up")
        public ResponseEntity<AuthResponse> register(@RequestBody @Valid AuthCreateUserRequest authCreateUser) {
            return new ResponseEntity<>(this.userDetailsService.createUser(authCreateUser), HttpStatus.CREATED);
        }

        @PostMapping("/log-in")
        public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthLoginRequest userRequest) {
            return new ResponseEntity<>(this.userDetailsService.loginUser(userRequest), HttpStatus.OK);
        }

        @GetMapping("/me")
        public ResponseEntity<UserResponse> getCurrentUser() {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String username = auth.getName();

            UserEntity user = userRepository.findUserEntityByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

            Set<String> roles = user.getRoles()
                    .stream()
                    .map(role -> role.getRoleEnum().name())
                    .collect(Collectors.toSet());

            UserResponse response = new UserResponse(
                    user.getId(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getEmail(),
                    user.getUsername(),
                    user.getPhone(),
                    user.getTermsAccepted(),
                    user.getNewsletterSubscribed(),
                    user.getTermsAcceptedAt(),
                    user.getCreatedAt(),
                    user.isEnabled(),
                    user.isAccountNoExpired(),
                    user.isAccountNoLocked(),
                    user.isCredentialNoExpired(),
                    roles
            );

            return ResponseEntity.ok(response);
        }


    }
