package com.example.demo.service;

import java.util.Optional;

// import org.h2.engine.User;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dao.AuthRepository;
import com.example.demo.domain.UserRequestDTO;
import com.example.demo.domain.UserResponseDTO;
import com.example.demo.domain.entity.UserEntity;
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


        // // jpa 방식으로 하려면 entity를 사용해야한다.
        // // UserEntity user = authRepository.findById(params.getId()).orElse(null); // ID로 User를 찾는다.
        // UserEntity  entity = UserEntity.builder() // UserEntity를 생성한다.
        //         .email(params.getEmail()) // email
        //         .pwd(params.getPwd()) // password
        //         .refreshToken(reToken) // token
        //         .build(); // UserEntity를 생성한다.
        
        // repository.save(entity); // UserEntity를 저장한다.

        // **db에 존재하면, 갱신, 없으면, 생성한다.**

        // UserEntity entity = repository.findByEmail(params.getEmail()).orElse(null); // email로 User를 찾는다.
        Optional<UserEntity> op = repository.findByEmail(params.getEmail()); // email로 User를 찾는다.
        if (op.isPresent()) { // User가 존재하면 -> refresh token이 있다.
            UserEntity result = op.get(); // User를 가져온다.
            System.out.println("debug >> Login(service) result : " + result.toString());

            // 실제로 header에 token을 넘겨줘야한다. 
            UserResponseDTO response = UserResponseDTO.builder()
                .email(result.getEmail()) // email
                .pwd(result.getPwd()) // password
                .accessToken(accToken) // access token
                .refreshToken(result.getRefreshToken()) // refresh token
                .build(); // UserResponseDTO를 생성한다.
            return response; // response를 반환한다.


            // result.setRefreshToken(reToken); // refresh token을 갱신한다.
            // repository.save(result); // UserEntity를 저장한다.
        } else { // User가 존재하지 않으면 처음 로그인한다.
            UserEntity entity = UserEntity.builder() // UserEntity를 생성한다.
                                .email(params.getEmail()) // email
                                .pwd(params.getPwd()) // password
                                .refreshToken(reToken) // token
                                .build(); // UserEntity를 생성한다.
            repository.save(entity); // UserEntity를 저장한다.
            
            // 근데 이렇게 하면, 확실하게 repository에 저장이 안된 경우에 갑자기 반환해버리면 어떻게 하지?
            // UserResponseDTO를 생성해서 반환한다. -> 지금은 그냥 반환한다.
            UserResponseDTO response = UserResponseDTO.builder()
                    .email(params.getEmail()) // email
                    .pwd(params.getPwd()) // password
                    .accessToken(accToken) // access token
                    .refreshToken(reToken) // refresh token
                    .build(); // UserResponseDTO를 생성한다.
    
            return response; // response를 반환한다.
        }

    }
    
    // ctrl에서 header에 있는 token을 가져와서 subString으로 Bearer를 제외한 token을 가져온다.(= refresh token임.)
    // service에서 token을 검증하고(1), 유효한 경우 refresh token을 갱신하고, 새로운 JWT Access Token을 생성한다.(2)
    // (1) token을 검증하는 방법은, op.isPresent()으로 체크 -> 만약 유효하지 않으면, 예외가 발생한다.
    // (2) 새로운 JWT Access Token을 생성하는 방법은, jwtProvider에서 generateAccToken(email)으로 생성한다.
    public String renewToken(String token) throws Exception {
        System.out.println("debug >> renewToken(service) hit");

        // db에 있는 refresh token과 비교해야한다. -> /renew 요청시 header에 refresh token을 넘겨줘야한다. 해당 토큰이랑 db에 있는 토큰을 비교해야한다.
        Optional<UserEntity> op = repository.findByRefreshToken(token); // token으로 User를 찾는다.
        if (op.isPresent()) {
            // jpa에서 token을 찾는 query에서 userEntity가 존재한다.
            UserEntity result = op.get(); // User를 가져온다. refresh token 존재한다. -> db에 있는 refresh token과 비교해야한다.
            System.out.println("debug >> renewToken(service) result : " + result.toString());
            // 해당 Refresh Token으로 Access Token을 갱신한다.
            String email = jwtProvider.renewToken(result.getRefreshToken()); // 
            String newToken = jwtProvider.generateAccToken(email); // JWT Access Token을 생성한다.
            System.out.println("debug >> renewToken(service) newToken : " + newToken);
            return newToken; // JWT Access Token을 반환한다.

        } else{
            // token이 존재하지 않으면, 유효하지 않은 토큰이다.
            throw new Exception(); // Token이 유효하지 않다.
        }
        
        // String email = jwtProvider.renewToken(token); // JWT Refresh Token을 갱신한다.
        // System.out.println("debug >> renewToken(service) email : " + email);
        // return jwtProvider.generateAccToken(email); // JWT Access Token을 생성한다.
    }
}
