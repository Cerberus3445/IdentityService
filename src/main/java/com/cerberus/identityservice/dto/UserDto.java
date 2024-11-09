package com.cerberus.identityservice.dto;

import com.cerberus.identityservice.domain.user.Role;
import com.cerberus.identityservice.security.JwtResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private Role role;

    private JwtResponse jwtResponse;
}
