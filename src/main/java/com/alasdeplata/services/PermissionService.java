package com.alasdeplata.services;

import java.util.List;
import java.util.Optional;

import com.alasdeplata.dto.permissions.PermissionRequest;
import com.alasdeplata.dto.permissions.PermissionResponse;

public interface PermissionService {
    List<PermissionResponse> getAllPermissions();
    Optional<PermissionResponse> getPermissionById(Long id);

    PermissionResponse createPermission(PermissionRequest permissionResponse);

    PermissionResponse updatePermission(Long id, PermissionRequest permissionResponse);

    void deletePermission(Long id);
}
