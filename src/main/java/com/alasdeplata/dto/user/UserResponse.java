package com.alasdeplata.dto.user;

import java.time.LocalDateTime;
import java.util.Set;

public record UserResponse(
                Long id,
                String firstName,
                String lastName,
                String email,
                String username,
                String phone,
                Boolean termsAccepted,
                Boolean newsletterSubscribed,
                LocalDateTime termsAcceptedAt,
                LocalDateTime createdAt,
                boolean isEnabled,
                boolean accountNoExpired,
                boolean accountNoLocked,
                boolean credentialNoExpired,
                Set<String> roles) {

}
