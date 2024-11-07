package com.cerberus.identityservice.service;

public interface JwtService {

    void validateToken(String token);

    String generateToken(String email);

}
