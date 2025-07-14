package com.alasdeplata.mapper;

import com.alasdeplata.dto.role.RoleRequest;
import com.alasdeplata.dto.role.RoleResponse;
import com.alasdeplata.models.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleResponse toResponse(Role role);

    Role toEntity(RoleRequest data);
}
