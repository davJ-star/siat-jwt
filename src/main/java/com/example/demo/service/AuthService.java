package com.example.demo.service;

import org.h2.engine.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.AuthRepository;
import com.example.demo.domain.entity.UserRequestDTO;

@Service
public class AuthService {
    // autowired로 주입받아야한다.
    @Autowired
    private AuthRepository authRepository; // @Autowired를 사용하여 AuthRepository를 주입받는다.


    public void loginService(UserRequestDTO params) {
        System.out.println("debug >> Login(service) hit");
        System.out.println("debug >> Login(service) params : " + params.toString());

        // UserEntity user = authRepository.findById(params.getId()).orElse(null); // ID로 User를 찾는다.
    }
}
