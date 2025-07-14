package com.alasdeplata.dto.role;

import java.util.Set;

import com.alasdeplata.enums.RoleEnum;

public record RoleResponse(
        Long id,
        RoleEnum roleEnum,
        Set<Long> permissionIds) {

}
