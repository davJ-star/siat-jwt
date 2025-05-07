package com.example.demo.service;

import java.util.Optional;

// import org.h2.engine.User;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dao.AuthRepository;
import com.example.demo.dao.MemberRepository;
import com.example.demo.dao.PostRepository;
import com.example.demo.domain.MemberRequestDTO;
import com.example.demo.domain.PostRequestDTO;
import com.example.demo.domain.UserRequestDTO;
import com.example.demo.domain.UserResponseDTO;
import com.example.demo.domain.entity.MemberEntity;
import com.example.demo.domain.entity.PostEntity;
import com.example.demo.domain.entity.UserEntity;
import com.example.demo.util.JwtProvider;

@Service
public class UserService {
    // autowired로 주입받아야한다.
    @Autowired
    private MemberRepository memberRepository; // @Autowired를 사용하여 AuthRepository를 주입받는다.
    // @Autowired
    // private JwtProvider jwtProvider; // @Autowired를 사용하여 JwtProvider를 주입받는다.
    @Autowired
    private PostRepository postRepository ; // @Autowired를 사용하여 AuthRepository를 주입받는다.
    // @Autowired
    // private JwtProvider jwtProvider; // @Autowired를 사용하여 JwtProvider를 주입받는다.

    public void createUser(MemberRequestDTO params) {
        System.out.println("debug >> createUser(service) hit");
        System.out.println("debug >> createUser(service) params : " + params.toString());
        
        // **db에 존재하면, 갱신, 없으면, 생성한다.**
        // 일단 dto에 담아서 넘겨주고, entity로 변환해서 저장하는 방식이 있어야한다. 이를 repository에 대해서 처리해야한다. 
        MemberEntity entity = MemberEntity.builder()
                                .email(params.getEmail()) // email
                                .passwd(params.getPwd()) // password
                                .build(); // UserEntity를 생성한다.     

        // 지금 검증 절차가 없다.
        memberRepository.save(entity); // UserEntity를 저장한다.

        
    }
    public UserResponseDTO createPost(PostRequestDTO params) {
        System.out.println("debug >> createUser(service) hit");
        System.out.println("debug >> createUser(service) params : " + params.toString());
        
        // **db에 존재하면, 갱신, 없으면, 생성한다.**
        // 일단 dto에 담아서 넘겨주고, entity로 변환해서 저장하는 방식이 있어야한다. 이를 repository에 대해서 처리해야한다. 
        PostEntity entity = PostEntity.builder()
                                .title(params.getTitle()) // email
                                .content(params.getContent()) // password
                                
                                .build(); // UserEntity를 생성한다.     

        // 지금 검증 절차가 없다.
        postRepository.save(entity); // UserEntity를 저장한다.

        // 나머지 
        return null;
    }
}
