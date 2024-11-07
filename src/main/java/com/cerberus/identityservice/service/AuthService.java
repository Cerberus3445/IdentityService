package com.cerberus.identityservice.service;

import com.cerberus.identityservice.domain.user.UserCredential;

public interface AuthService {

    String saveUser(UserCredential credential);

    String generateToken(String email);

    void validateToken(String token);
}