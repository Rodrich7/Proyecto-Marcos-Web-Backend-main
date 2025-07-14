package com.alasdeplata.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alasdeplata.models.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
    // Custom query methods can be defined here if needed

}
