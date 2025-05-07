package com.example.demo.ctrl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.MemberRequestDTO;
import com.example.demo.domain.PostRequestDTO;
import com.example.demo.domain.PostResponseDTO;
import com.example.demo.domain.UserRequestDTO;
import com.example.demo.service.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
// 로그인하고나서 사용할 API를 작성하는 컨트롤러로 설정할 것인지 체크가 필요하다.
@RequestMapping("/user") // API 경로를 지정합니다. (예: /api/user)

public class UserCtrl {
    @Autowired
    private UserService userService;
    

    @PostMapping("/userCreate")
    public ResponseEntity<Void> createUser(@RequestBody MemberRequestDTO params) {
        //TODO: process POST request
        System.out.println("debug >> createUser(ctrl) endpoint hit");
        userService.createUserService(params); // UserService를 호출하여 사용자 생성 요청을 처리한다.
        
        return ResponseEntity.noContent().build(); // ResponseEntity를 반환한다.
    }
    
    @PostMapping("/{id}/post")
    // @PathVariable("id")
    public ResponseEntity<PostResponseDTO> createPost(@PathVariable("id") String userId, @RequestBody PostRequestDTO params) { // 이렇게 쓸때 pathvariable을 사용하는 것이 좋다. -> @PathVariable(name = "id")
        //TODO: process POST request
        System.out.println("debug >> createPost(ctrl) endpoint hit");
        PostResponseDTO response =  userService.createPostService(userId, params); // UserService를 호출하여 사용자 생성 요청을 처리한다.
        
        return ResponseEntity.ok().body(response); // ResponseEntity를 반환한다.
    }
    @PostMapping("/{id}/list")
    public ResponseEntity<List<PostResponseDTO>> getUserPosts(@PathVariable(name = "id") String userId) { // 이렇게 쓸때 pathvariable을 사용하는 것이 좋다.
        //TODO: process POST request
        System.out.println("debug >> getUserPosts(ctrl) endpoint hit");
        // Member를 토대로 해당 사용자의 post를 가져오는 것.
        List<PostResponseDTO> lst =  userService.getUserPostService(userId); // UserService를 호출하여 사용자 생성 요청을 처리한다.
        
        return ResponseEntity.ok().body(lst); // ResponseEntity를 반환한다.
    }
    
    // @PostMapping("/user")
    // public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userRequestDTO) {
    //     UserResponseDTO userResponseDTO = userService.createUser(userRequestDTO);
    //     return new ResponseEntity<>(userResponseDTO, HttpStatus.CREATED);
    //
}
