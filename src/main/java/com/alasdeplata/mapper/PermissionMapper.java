package com.alasdeplata.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.alasdeplata.dto.permissions.PermissionRequest;
import com.alasdeplata.dto.permissions.PermissionResponse;
import com.alasdeplata.models.Permission;

@Mapper(componentModel = "spring")
public interface PermissionMapper {

    PermissionResponse toResponse(Permission permission);

    Permission toEntity(PermissionRequest data);
}
