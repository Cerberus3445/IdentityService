package com.cerberus.identityservice.service.impl;

import com.cerberus.identityservice.domain.user.UserCredential;
import com.cerberus.identityservice.repository.UserCredentialRepository;
import com.cerberus.identityservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserCredentialRepository userCredentialRepository;

    @Override
    public UserCredential findByEmail(String email) {
        return this.userCredentialRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
