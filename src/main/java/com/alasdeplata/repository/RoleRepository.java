package com.alasdeplata.repository;

import java.util.List;

import com.alasdeplata.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alasdeplata.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findRoleEntitiesByRoleEnumIn(List<RoleEnum> roleNames);
}
