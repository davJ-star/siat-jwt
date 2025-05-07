package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
import com.example.demo.domain.PostResponseDTO;
import com.example.demo.domain.UserRequestDTO;
import com.example.demo.domain.UserResponseDTO;
import com.example.demo.domain.entity.MemberEntity;
import com.example.demo.domain.entity.PostEntity;

import oracle.jdbc.proxy.annotation.Post;


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

    public void createUserService(MemberRequestDTO params) {
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
    public PostResponseDTO createPostService(String id, PostRequestDTO params) {
        System.out.println("debug >> createUser(service) hit");
        System.out.println("debug >> createUser(service) params : " + params.toString());
        

        Optional<MemberEntity> op = memberRepository.findById(id); // id로 User를 찾는다.
        PostEntity post = PostEntity.builder()
        .title(params.getTitle()) // email
        .content(params.getContent()) // password
        .build(); // UserEntity를 생성한다.     


        if (op.isPresent()) { // User가 존재하면 -> refresh token이 있다.
            // postRepository.save(post);
            op.get().addPost(post); // User를 가져온다.
            //postRepository.save(op.get());
            memberRepository.save(op.get());
            
            return PostResponseDTO.builder()
                        .title(post.getTitle()) // email
                        .content(post.getContent()) // password
                        .build(); // UserResponseDTO를 생성한다.
           
        } else {
            // 없으면, 에러처리
            return null;
            
        }

        // **db에 존재하면, 갱신, 없으면, 생성한다.**
        // 일단 dto에 담아서 넘겨주고, entity로 변환해서 저장하는 방식이 있어야한다. 이를 repository에 대해서 처리해야한다. 
        
        // 지금 검증 절차가 없다.
        
        // 나머지 부분은 생략한다.
    }
    public List<PostResponseDTO> getUserPostService(String id) {
        System.out.println("debug >> createUser(service) hit");
        //System.out.println("debug >> createUser(service) params : " + params.toString());
        

        Optional<MemberEntity> op = memberRepository.findById(id); // id로 User를 찾는다.
        // MemberEntity member = memberRepository.findById(id)
        //                             .orElseThrow(() -> new RuntimeException("not found")); // User를 가져온다.
        
        // PostEntity post = PostEntity.builder()
        // //.title(params.getTitle()) // email
        // //.content(params.getContent()) // password
        // .build(); // UserEntity를 생성한다.     

        List<PostResponseDTO> postList = null;
        // if (op.isPresent()) { // User가 존재하면 -> refresh token이 있다.
        //     // postRepository.save(post);
            

            
            
        //     List<PostEntity> posts = op.get().getPosts(); // User를 가져온다.
        //     return posts.stream()
        //         .map(post -> PostResponseDTO.builder()
        //                 .title(post.getTitle()) // email
        //                 .content(post.getContent()) // password
        //                 .build()) // UserResponseDTO를 생성한다.
        //         .collect(Collectors.toList());
            
        // } else {
        //     // 없으면, 에러처리
        //     return null;
            
        // }

        if (op.isPresent()) { // User가 존재하면 -> refresh token이 있다.
            // postRepository.save(post);
            return op.get().getPosts().stream()
                        .map(PostResponseDTO::new) // UserResponseDTO를 생성한다.
                        .toList(); // User를 가져온다.
            
        } 
        return postList;



        // **db에 존재하면, 갱신, 없으면, 생성한다.**
        // 일단 dto에 담아서 넘겨주고, entity로 변환해서 저장하는 방식이 있어야한다. 이를 repository에 대해서 처리해야한다. 
        
        // 지금 검증 절차가 없다.
        
        // 나머지 부분은 생략한다.
    }
}
