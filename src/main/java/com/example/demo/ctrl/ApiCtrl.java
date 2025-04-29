package com.example.demo.ctrl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1")
// @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")//@CrossOrigin(origins = "http://localhost:3000") // CORS 설정을 위한 어노테이션이다. -> localhost:3000에서만 접근 가능하다.
public class ApiCtrl {
    @GetMapping("/hello") // @RequestMapping("/hello")과 @PostMapping("/hello")은 같은 의미이다.
    // 현재 endpoint는 /api/v1/hello이다.
    public String hello() {
        //TODO: process POST request
        System.out.println("debug >> Hello(ctrl) endpoint hit");
        return "success"; // ResponseEntity를 반환한다.
    }
    
}
