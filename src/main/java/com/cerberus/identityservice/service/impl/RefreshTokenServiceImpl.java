package com.cerberus.identityservice.service.impl;

import com.cerberus.identityservice.domain.token.RefreshToken;
import com.cerberus.identityservice.repository.RefreshTokenRepository;
import com.cerberus.identityservice.repository.UserCredentialRepository;
import com.cerberus.identityservice.service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private UserCredentialRepository userCredentialRepository;

    @Override
    public RefreshToken createRefreshToken(String email) {
        RefreshToken refreshToken = RefreshToken.builder()
                .userCredential(this.userCredentialRepository.findByEmail(email).get())
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusSeconds(600000))
                .build();
        return this.refreshTokenRepository.save(refreshToken);
    }

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return this.refreshTokenRepository.findByToken(token);
    }

    @Override
    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            this.refreshTokenRepository.delete(token);
            throw new RuntimeException(token.getToken() + " Refresh token was expired. Please make a new signin request");
        }
        return token;
    }
}
