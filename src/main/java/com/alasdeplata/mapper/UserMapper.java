package com.alasdeplata.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.alasdeplata.dto.user.UserRequest;
import com.alasdeplata.dto.user.UserResponse;
import com.alasdeplata.models.Role;
import com.alasdeplata.models.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "roles", source = "roles", qualifiedByName = "rolesToStrings")
    @Mapping(target = "isEnabled", source = "enabled")
    UserResponse toResponse(UserEntity userEntity);

    @Mapping(target = "id", ignore = true)
    UserEntity toEntity(UserRequest data);

    @Named("rolesToStrings")
    default Set<String> rolesToStrings(Set<Role> roles) {
        return roles.stream()
                .map(role -> role.getRoleEnum().name())
                .collect(Collectors.toSet());
    }

}
