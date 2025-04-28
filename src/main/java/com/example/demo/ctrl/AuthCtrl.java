package com.example.demo.ctrl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
// @RequestMapping("/api/v1/auth")
public class AuthCtrl {

    // @GetMapping("/login")
    //@RequestMapping("/login")
    public ResponseEntity<Void> login() {
        System.out.println("debug >> Login(ctrl) endpoint hit");
        return null;
    }
    
    
}
