package com.it.app.dto.response;

import com.it.app.dto.RoleDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserResponseDto {

    private Long id;

    private String name;

    private Set<RoleDto> roles;

}
