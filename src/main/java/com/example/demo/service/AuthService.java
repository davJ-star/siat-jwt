package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class AuthService {
    // autowired로 주입받아야한다.
    
    public void loginService() {
        System.out.println("debug >> Login(service) hit");
    }
}
