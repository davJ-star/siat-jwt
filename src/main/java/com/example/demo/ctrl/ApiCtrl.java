package com.example.demo.ctrl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.UserResponseDTO;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*") // CORS 설정을 위한 어노테이션이다. -> localhost:3000에서만 접근 가능하다.
public class ApiCtrl {
    @PostMapping("/hello") // @RequestMapping("/hello")과 @PostMapping("/hello")은 같은 의미이다.
    // 현재 endpoint는 /api/v1/hello이다.
    public String hello(@RequestBody String params) {
        //TODO: process POST request
        System.out.println("debug >> Hello(ctrl) endpoint hit");
        System.out.println("debug >> Hello(ctrl) params : " + params.toString());
        return "Hello, " + params.toString(); // ResponseEntity를 반환한다.
    }
    
}
