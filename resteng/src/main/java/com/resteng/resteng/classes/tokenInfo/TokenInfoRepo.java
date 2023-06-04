package com.resteng.resteng.classes.tokenInfo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenInfoRepo extends JpaRepository<TokenInfo, Long> {

    Optional<TokenInfo> findByRefreshToken(String refreshToken);

}