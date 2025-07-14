package com.alasdeplata.dto.permissions;

import jakarta.validation.constraints.NotBlank;

public record PermissionRequest(
        @NotBlank String name) {

}
