package com.cerberus.identityservice.service;

import com.cerberus.identityservice.model.UserCredential;

public interface UserService {

    UserCredential findByEmail(String email);
}
