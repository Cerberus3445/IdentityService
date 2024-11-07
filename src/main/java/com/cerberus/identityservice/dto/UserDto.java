package com.cerberus.identityservice.dto;

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

    private int id;

    private String name;

    private String email;

    private String password;

    private String role;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String environment;

    private JwtResponse jwtResponse;
}
