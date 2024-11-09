package com.cerberus.identityservice.controller;

import com.cerberus.identityservice.domain.token.RefreshToken;
import com.cerberus.identityservice.domain.user.UserCredential;
import com.cerberus.identityservice.dto.UserDto;
import com.cerberus.identityservice.mapper.EntityDtoMapper;
import com.cerberus.identityservice.security.AuthRequest;
import com.cerberus.identityservice.security.JwtResponse;
import com.cerberus.identityservice.security.RefreshTokenRequest;
import com.cerberus.identityservice.service.AuthService;
import com.cerberus.identityservice.service.JwtService;
import com.cerberus.identityservice.service.RefreshTokenService;
import com.cerberus.identityservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private EntityDtoMapper mapper;

    @Autowired
    private AuthenticationManager authenticationManager; //токен получают только те, кто есть в бд

    @PostMapping("/register")
    public String addNewUser(@RequestBody UserDto userDto) {

        UserCredential user = this.mapper.toEntity(userDto);

        return this.authService.saveUser(user);
    }

    @PostMapping("/login")
    public UserDto login(@RequestBody AuthRequest authRequest) {
        Authentication authenticate = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        if (authenticate.isAuthenticated()) {
            UserCredential userCredential = this.userService.findByEmail(authRequest.getEmail());

            JwtResponse jwtResponse = JwtResponse
                    .builder()
                    .accessToken(this.authService.generateToken(authRequest.getEmail()))
                    .refreshToken(this.refreshTokenService.createRefreshToken(authRequest.getEmail()).getToken())
                    .build();

            return UserDto.builder()
                    .id(userCredential.getId())
                    .firstName(userCredential.getFirstName())
                    .lastName(userCredential.getLastName())
                    .email(userCredential.getEmail())
                    .role(userCredential.getRole())
                    .password(userCredential.getPassword())
                    .jwtResponse(jwtResponse)
                    .build();
        } else {
            throw new RuntimeException("invalid access");
        }
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        this.authService.validateToken(token);
        return "Token is valid";
    }

    @PostMapping("/refreshToken")
    public JwtResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest){
        return this.refreshTokenService.findByToken(refreshTokenRequest.getToken())
                .map(this.refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUserCredential)
                .map(userCredential -> {
                    String accessToken = this.jwtService.generateToken(userCredential.getEmail());
                    return JwtResponse.builder()
                            .accessToken(accessToken)
                            .refreshToken(refreshTokenRequest.getToken())
                            .build();
                }).orElseThrow(() -> new RuntimeException("Token not found"));
    }
}
