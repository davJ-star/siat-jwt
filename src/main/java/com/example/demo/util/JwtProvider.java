package com.example.demo.util;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;

// import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtProvider {
    // private final Key key = Keys.hmacShaKeyFor("siat-very-very-important-secret-key".getBytes(StandardCharsets.UTF_8));  // 서명 알고리즘 설정; // JWT 서명에 사용할 키

    @Value("${jwt.secret}") // application.properties에 설정된 jwt.secret.key 값을 주입받습니다.
    private String secretKey; // JWT 서명에 사용할 키
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)); // JWT 서명에 사용할 키를 가져옵니다.
    }

    public String generateAccToken(String email) {
        // JWT Access Token 생성 로직
        

        // setSubject, setIssuedAt, setExpiration, signWith 등을 사용하여 JWT를 생성합니다.
        return Jwts.builder()
                .setSubject(email)  // JWT의 subject 설정 (사용자 이메일)
                .setIssuedAt(new Date()) // JWT 발급 시간 설정
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 2)) // JWT 만료 시간 설정 (1시간 후) 1초 * 1000 = 1ms, 1분 * 60 = 1s, 1시간 * 60 = 1m
                .signWith(getSigningKey()) // 서명 알고리즘 설정
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
                .signWith(getSigningKey()) // 서명 알고리즘 설정
                .compact();
    }

    public String renewToken(String token) {
        System.out.println("debug >> JwtProvider renewToken() method called");
        // JWT Refresh Token 갱신 로직
        // JWT를 파싱하여 만료 시간을 갱신합니다.
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey()) // JWT 서명에 사용할 키를 가져옵니다.
                .build()
                // 토큰을 한번더 검증한다.
                .parseClaimsJws(token) // JWT를 파싱합니다.
                .getBody() // JWT의 본문을 가져옵니다.
                .getSubject(); // JWT의 subject를 가져옵니다.
    }
}
