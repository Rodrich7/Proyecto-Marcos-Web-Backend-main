package com.alasdeplata.dto.auth;

import java.util.List;

import jakarta.validation.constraints.Size;

public record AuthCreateRoleRequest(
                @Size(max = 3, message = "The user cannot have more than 3 roles") List<String> roleListName) {

}
