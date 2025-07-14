package com.alasdeplata.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.alasdeplata.dto.permissions.PermissionRequest;
import com.alasdeplata.dto.permissions.PermissionResponse;
import com.alasdeplata.mapper.PermissionMapper;
import com.alasdeplata.models.Permission;
import com.alasdeplata.repository.PermissionRepository;
import com.alasdeplata.services.PermissionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;
    private final PermissionMapper permissionMapper;

    @Override
    public List<PermissionResponse> getAllPermissions() {
        return permissionRepository.findAll()
                .stream()
                .map(permissionMapper::toResponse)
                .toList();
    }

    @Override
    public Optional<PermissionResponse> getPermissionById(Long id) {
        return permissionRepository.findById(id)
                .map(permissionMapper::toResponse);
    }

    @Override
    public PermissionResponse createPermission(PermissionRequest permissionRequest) {
        return permissionMapper.toResponse(
                permissionRepository.save(permissionMapper.toEntity(permissionRequest)));
    }

    @Override
    public PermissionResponse updatePermission(Long id, PermissionRequest permissionResponse) {
        Permission permission = permissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Permission not found"));
        permission.setName(permissionResponse.name());
        return permissionMapper.toResponse(permissionRepository.save(permission));

    }

    @Override
    public void deletePermission(Long id) {
        Permission permission = permissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Permission not found"));
        permissionRepository.delete(permission);
    }

}
