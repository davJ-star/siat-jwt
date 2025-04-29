package com.example.demo.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.UserResponseDTO;
import com.example.demo.domain.entity.UserRequestDTO;
import com.example.demo.service.AuthService;


@RestController
// @RequestMapping("/")
@RequestMapping("/auth")


@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600) // CORS 설정을 위한 어노테이션이다. -> localhost:3000에서만 접근 가능하다. // //@CrossOrigin(origins = "*", maxAge = 3600) // CORS 설정을 위한 어노테이션이다. -> 모든 출처에서 접근 가능하다.
public class AuthCtrl {
    @Autowired
    private AuthService authService; // @Autowired를 사용하여 AuthService를 주입받는다.
    
    // @GetMapping("/login")


    // 유효성 검증해주면 좋을 것 같은데, 어떻게 해야할까? -> @Valid 어노테이션을 사용하면 된다.


    /*
     * 
     */

    
    // @RequestMapping("/user")
    @PostMapping("/login") // @RequestMapping("/login")과 @PostMapping("/login")은 같은 의미이다.
    // 현재 endpoint는 /api/v1/auth/login이다.
    public ResponseEntity<UserResponseDTO> login(@RequestBody UserRequestDTO params) { // @RequestBody 어노테이션을 사용하여 JSON 데이터를 DTO로 변환한다.
        System.out.println("debug >> Login(ctrl) endpoint hit");
        System.out.println("debug >> Login(ctrl) params : " + params.toString());
        UserResponseDTO response = authService.loginService(params); // service를 호출한다.
        return ResponseEntity.ok()
                                .header("Authorization", "Bearer "+response.getAccessToken()) // JWT Access Token을 헤더에 추가한다.  
                                .header("Refresh-token", response.getRefreshToken()) // JWT Refresh Token을 헤더에 추가한다.
                                .body(response); // ResponseEntity를 반환한다.
    }
    
       
}
