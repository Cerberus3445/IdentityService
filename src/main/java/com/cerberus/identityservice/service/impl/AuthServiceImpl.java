package com.cerberus.identityservice.service.impl;

import com.cerberus.identityservice.model.UserCredential;
import com.cerberus.identityservice.repository.UserCredentialRepository;
import com.cerberus.identityservice.service.AuthService;
import com.cerberus.identityservice.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserCredentialRepository repository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String saveUser(UserCredential credential) {
        credential.setPassword(this.passwordEncoder.encode(credential.getPassword()));
        this.repository.save(credential);
        return "user added to the system";
    }

    @Override
    public String generateToken(String email) {
        return this.jwtService.generateToken(email);
    }

    @Override
    public void validateToken(String token) {
        this.jwtService.validateToken(token);
    }
}
