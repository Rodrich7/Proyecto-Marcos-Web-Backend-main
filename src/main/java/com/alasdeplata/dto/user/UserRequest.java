package com.alasdeplata.dto.user;

import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRequest(
                @NotBlank String firstName,
                @NotBlank String lastName,
                @NotBlank String name,
                @Email @NotBlank String email,
                @NotBlank String username,
                @NotBlank @Size(min = 5) String password,
                @NotNull Boolean termsAccepted,
                Boolean newsletterSubscribed,
                String phone,
                @NotNull Set<Long> roleIds) {

}
