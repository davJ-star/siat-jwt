package com.example.demo.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 모든 경로에 대해 CORS 설정을 적용합니다.
                .allowedOrigins("http://localhost:3000") // 허용할 출처를 지정합니다. (예: React 앱의 주소)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 허용할 HTTP 메소드를 지정합니다.
                .allowedHeaders("*") // 모든 헤더를 허용합니다.
                .allowCredentials(true) // 자격 증명(쿠키 등)을 허용합니다.
                .exposedHeaders("Authorization", "Refresh-Token"); // 클라이언트가 접근할 수 있는 헤더를 지정합니다.
    }
}
