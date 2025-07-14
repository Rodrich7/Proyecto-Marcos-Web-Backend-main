package com.alasdeplata.dto.auth;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AuthCreateUserRequest(
                @NotBlank String username,
                @NotBlank String password,
                @NotBlank String firstName,
                @NotBlank String lastName,
                @NotBlank String email,
                @NotNull Boolean termsAccepted,
                Boolean newsletterSubscribed,
                @Valid AuthCreateRoleRequest roleRequest) {

}
