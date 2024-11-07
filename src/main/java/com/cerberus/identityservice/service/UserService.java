package com.cerberus.identityservice.service;

import com.cerberus.identityservice.domain.user.UserCredential;

public interface UserService {

    UserCredential findByEmail(String email);
}
