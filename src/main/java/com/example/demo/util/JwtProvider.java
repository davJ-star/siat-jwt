package com.example.demo.util;

import java.security.Key;
import java.util.Date;

// import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtProvider {
    private Key key = io.jsonwebtoken.security.Keys.secretKeyFor(SignatureAlgorithm.HS256); // 서명 알고리즘 설정; // JWT 서명에 사용할 키

    public String generateAccToken(String email) {
        // JWT Access Token 생성 로직
        

        // setSubject, setIssuedAt, setExpiration, signWith 등을 사용하여 JWT를 생성합니다.
        return Jwts.builder()
                .setSubject(email)  // JWT의 subject 설정 (사용자 이메일)
                .setIssuedAt(new Date()) // JWT 발급 시간 설정
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // JWT 만료 시간 설정 (1시간 후) 1초 * 1000 = 1ms, 1분 * 60 = 1s, 1시간 * 60 = 1m
                .signWith(key) // 서명 알고리즘 설정
                .compact();

                // .setId("id") // JWT의 고유 ID 설정
                // .setSubject("subject") // JWT의 subject 설정
                // .setIssuedAt(new Date()) // JWT 발급 시간 설정
                // .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // JWT 만료 시간 설정 (1시간 후)
                // .signWith(null, io.jsonwebtoken.SignatureAlgorithm.ES256) // 서명 알고리즘 설정
                // .setIssuer("issuer") // JWT 발급자 설정
                // .setAudience("audience") // JWT 수신자 설정
                // .compact();
        // return Jwts.builder()
        //         .setId("id") // JWT의 고유 ID 설정
        //         .setSubject("subject") // JWT의 subject 설정
        //         .setIssuedAt(new Date()) // JWT 발급 시간 설정
        //         .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // JWT 만료 시간 설정 (1시간 후)
        //         .signWith(null, io.jsonwebtoken.SignatureAlgorithm.ES256) // 서명 알고리즘 설정
        //         .setIssuer("issuer") // JWT 발급자 설정
        //         .setAudience("audience") // JWT 수신자 설정
        //         .compact();
    }
    public String generateReToken(String email) {
        // JWT Refresh Token 생성 로직
        return Jwts.builder()
                .setSubject(email)  // JWT의 subject 설정 (사용자 이메일)
                .setIssuedAt(new Date()) // JWT 발급 시간 설정
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // JWT 만료 시간 설정 (1시간 후) 1초 * 1000 = 1ms, 1분 * 60 = 1s, 1시간 * 60 = 1m
                .signWith(key) // 서명 알고리즘 설정
                .compact();
    }
}
