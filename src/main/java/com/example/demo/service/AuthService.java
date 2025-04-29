package com.example.demo.service;

// import org.h2.engine.User;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dao.AuthRepository;
import com.example.demo.domain.UserResponseDTO;
import com.example.demo.domain.entity.UserEntity;
import com.example.demo.domain.entity.UserRequestDTO;
import com.example.demo.util.JwtProvider;

@Service
public class AuthService {
    // autowired로 주입받아야한다.
    @Autowired
    private AuthRepository repository; // @Autowired를 사용하여 AuthRepository를 주입받는다.
    @Autowired
    private JwtProvider jwtProvider; // @Autowired를 사용하여 JwtProvider를 주입받는다.


    public UserResponseDTO loginService(UserRequestDTO params) {
        System.out.println("debug >> Login(service) hit");
        System.out.println("debug >> Login(service) params : " + params.toString());
        String accToken = jwtProvider.generateAccToken(params.getEmail()); // JWT Access Token 생성
        String reToken = jwtProvider.generateReToken(params.getEmail()); // JWT Refresh Token 생성


        // jpa 방식으로 하려면 entity를 사용해야한다.
        // UserEntity user = authRepository.findById(params.getId()).orElse(null); // ID로 User를 찾는다.
        UserEntity  entity = UserEntity.builder() // UserEntity를 생성한다.
                .email(params.getEmail()) // email
                .pwd(params.getPwd()) // password
                .token(accToken) // token
                .build(); // UserEntity를 생성한다.
        
        repository.save(entity); // UserEntity를 저장한다.


        // UserResponseDTO를 생성해서 반환한다. -> 지금은 그냥 반환한다.
        UserResponseDTO response = UserResponseDTO.builder()
                // .email(params.getEmail()) // email
                .accessToken(accToken) // access token
                .refreshToken(reToken) // refresh token
                .build(); // UserResponseDTO를 생성한다.
        return response; // response를 반환한다.
    }
}
