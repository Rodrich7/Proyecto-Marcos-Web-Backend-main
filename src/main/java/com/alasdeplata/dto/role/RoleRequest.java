package com.alasdeplata.dto.role;

import java.util.Set;

import com.alasdeplata.enums.RoleEnum;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RoleRequest(
                @NotNull RoleEnum roleEnum,
                @NotNull @NotEmpty Set<Long> permissionIds) {

}
