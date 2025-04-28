package com.example.demo.util;

import org.springframework.stereotype.Component;

@Component
public class JwtProvider {
    
    private String generateAccToken() {
        // JWT Access Token 생성 로직
        return "access token";
    }
    private String generateReToken() {
        // JWT Refresh Token 생성 로직
        return "refresh token";
    }
}
