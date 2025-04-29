package com.example.demo.filter;

// import io.jsonwebtoken.io.IOException;
import java.io.IOException;
import java.security.Key;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
// @Order(1) // 필터의 순서를 지정합니다. 숫자가 낮을수록 우선순위가 높습니다.
public class JwtFilter implements Filter {
    private final Key key = Keys.hmacShaKeyFor("siat-very-very-important-secret-key".getBytes()); // JWT 서명 키를 설정합니다.

    // JWT 필터 구현
    // JWT를 검증하고, 유효한 경우 요청을 처리하도록 설정합니다.
    // JWT가 유효하지 않은 경우, 요청을 거부합니다.
    // JWT 필터는 Spring Security와 함께 사용됩니다.
    // Spring Security에서 제공하는 FilterChainProxy를 사용하여 필터를 등록합니다.
    // FilterChainProxy는 여러 개의 필터를 체인 형태로 연결하여 요청을 처리합니다.
    // FilterChainProxy는 요청을 처리하는 과정에서 각 필터를 순차적으로 호출합니다.
    // 각 필터는 요청을 처리한 후, 다음 필터를 호출합니다.
    // 마지막 필터가 요청을 처리한 후, 응답을 반환합니다.
    // 필터는 요청을 가로채고, 요청을 처리하는 과정에서 필요한 작업을 수행합니다.

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // JWT 검증 로직을 구현합니다.
        // JWT가 유효한 경우, 다음 필터를 호출합니다.
        // JWT가 유효하지 않은 경우, 요청을 거부합니다.

        System.out.println("debug >> JwtFilter doFilter() method called");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;


        String path = req.getRequestURI(); // 요청 URI를 가져옵니다.
        System.out.println("debug >> JwtFilter path : " + path);


        // 패스가 /swagger-ui 또는 /v3/api-docs인 경우, JWT 검증을 건너뜁니다.
        if (isPassPath(path)) { // 패스가  또는 인 경우, JWT 검증을 건너뜁니다.
            System.out.println("debug >> 인증이 필요없는 경로입니다. 패스 : " + path);
            System.out.println("debug >> JwtFilter isPassPath() method called" + path);
            chain.doFilter(req, res); // 다음 필터를 호출합니다.
            return; // 요청을 종료합니다.
            
        }

        
        // Token 검증 로직을 구현합니다.
        // req.getHeader("Authorization"); // Authorization 헤더를 가져옵니다.
        String authHeader = req.getHeader("Authorization"); // Authorization 헤더에서 JWT를 가져옵니다.
        System.out.println("debug >> JwtFilter authHeader : " + authHeader);

        // 필요없는 정보를 체크해야한다.

        // auth Header가 null이거나, Bearer로 시작하지 않는 경우, 요청을 거부합니다.
        // auth Header에 담아서 jwt를 보내야하기 때문에! 또는 Bearer
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            System.out.println("debug >> JwtFilter authHeader is null or does not start with Bearer");
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 Unauthorized 응답을 반환합니다.
            return; // 요청을 종료합니다.
            // res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized"); // 401 Unauthorized 응답을 반환합니다.
            // return; // 요청을 종료합니다.
        }
        
        // 404 에러
        // 403 에러
        String token = authHeader.substring(7); // Bearer 다음의 JWT를 가져옵니다.
        System.out.println("debug >> JwtFilter token : " + token);
        try {
            // jwt(서명 + 유효기간 체크)
            Jwts.parserBuilder()
                .setSigningKey(key) // JWT 서명 키를 설정합니다.
                .build()
                .parseClaimsJws(token); // JWT를 파싱합니다. Bearer 다음의 JWT를 가져옵니다.

                System.out.println("debug >> 검증성공 -> 컨트롤러로 이동"); // JWT가 유효한 경우, 다음 필터를 호출합니다.
            
                // String token = authHeader.substring(7); // Bearer 다음의 JWT를 가져옵니다.
            // System.out.println("debug >> JwtFilter token : " + token);
            // JWT 검증 로직을 구현합니다.
            // JwtProvider jwtProvider = new JwtProvider(); // JwtProvider를 생성합니다.
            // jwtProvider.validateToken(token); // JWT를 검증합니다.
            // jwtProvider.getUserEmail(token); // JWT에서 사용자 이메일을 가져옵니다.
        } catch (Exception e) {
            System.out.println("debug >> 토큰 문제가 생김(403)");
            res.setStatus(res.SC_FORBIDDEN); // 401 Unauthorized 응답을 반환합니다.
            res.getWriter().write("Invalid or expired token"); // 응답 본문에 메시지를 추가합니다.
            return; // 요청을 종료합니다.
        }


        
        // chain.doFilter(req, res); // 다음 필터를 호출합니다.
    }
    public boolean isPassPath(String path) {
        // 패스가  또는 인 경우, JWT 검증을 건너뜁니다.
        // return path.equals("/auth/login") || path.equals("/auth/register");
        return  path.startsWith("/swagger-ui") || 
                path.startsWith("/v3/api-docs") || 
                path.startsWith("/swagger-resources") || 
                path.startsWith("/swagger-ui") || 
                path.startsWith("/h2-console") ||    
                path.startsWith("/auth"); 
            // auth/login 또는 auth/register인 경우, JWT 검증을 건너뜁니다.
    }
    
}
