package com.enigmacamp.enigma_loan_app.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RedisTokenBlacklistService {
    private final StringRedisTemplate template;

    public void blackListToken(String token, Long expirationTime) {
        template.opsForValue().set(token, "blacklisted", expirationTime, TimeUnit.MILLISECONDS);
    }

    public Boolean isTokenBlacklisted(String token) {
        return Boolean.TRUE.equals(template.hasKey(token));
    }
}
