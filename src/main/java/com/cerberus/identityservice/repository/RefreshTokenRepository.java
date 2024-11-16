package com.cerberus.identityservice.repository;

import com.cerberus.identityservice.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Integer> {

    Optional<RefreshToken> findByToken(String token);
}