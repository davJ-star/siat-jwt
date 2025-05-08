package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// import org.h2.engine.User;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.demo.ctrl.ApiCtrl;
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

    private final ApiCtrl apiCtrl;
    // autowired로 주입받아야한다.
    @Autowired
    private MemberRepository memberRepository; // @Autowired를 사용하여 AuthRepository를 주입받는다.
    // @Autowired
    // private JwtProvider jwtProvider; // @Autowired를 사용하여 JwtProvider를 주입받는다.
    @Autowired
    private PostRepository postRepository ;

    UserService(ApiCtrl apiCtrl) {
        this.apiCtrl = apiCtrl;
    } // @Autowired를 사용하여 AuthRepository를 주입받는다.
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

 // 근데 이걸 굳이 해야될까? 밖에서 체크해서 바궈버리고, 아래에서 체크해버리면 되지 않나?    
    @Transactional
    public void updateUserPostService(String email, PostRequestDTO params) {
        System.out.println("debug >> updateUserPost(service) hit");
        //System.out.println("debug >> createUser(service) params : " + params.toString());
        
        /*
            update user set title = ?, content = ? where email = ?; 정확한 컬럼명은 아니지만 대략 이렇게 진행해야한다.
        */
        // memberRepository.findById(email).ifPresentOrElse(member -> {
        //     // member.setTitle(params.getTitle()); // email
        //     // member.setContent(params.getContent()); // password
        //     // memberRepository.save(member); // UserEntity를 저장한다.
        //     List<PostEntity> posts = member.getPosts(); // User를 가져온다.
        //     for (PostEntity post : posts) {
        //         if (post.getId() == email) { // id로 User를 찾는다.
        //             post.setTitle(params.getTitle()); // email
        //             post.setContent(params.getContent()); // password
        //             postRepository.save(post); // UserEntity를 저장한다.
        //         }
        //     }
        // }, () -> {
        //     System.out.println("debug >> updateUserPost(service) not found");
        // });
       
        // Optional<MemberEntity> op = memberRepository.findById(email); // id로 User를 찾는다.
        /*
         * Optional<MemberEntity> op = memberRepository.findById(email); // id로 User를 찾는다.
         * if (op.isPresent()) { // User가 존재하면 -> refresh token이 있다.
         * // postRepository.save(post);
         * // op.get().addPost(post); // User를 가져온다.
         * // postRepository.save(op.get());
         * // memberRepository.save(op.get());
         * // return PostResponseDTO.builder()
         * }
         */

        //  // 이렇게 쓰면 Optional을 사용하지 않아도 된다.
        // memberRepository.findById(email)
        //         .orElseThrow(() -> new RuntimeException("not found")) // User를 가져온다.
        //         // 멤버에 있는 posts를 가져온다.
        //         // post id로 post를 가져온다.
        //         .getPosts().stream()
        //         .filter(post -> post.getId() == params.getId()) // id로 User를 찾는다.
        //         .findFirst() // 첫번째 요소를 가져온다.
        //         .ifPresentOrElse(post -> {
        //             post.setTitle(params.getTitle()); // email
        //             post.setContent(params.getContent()); // password
        //             postRepository.save(post); // UserEntity를 저장한다.
        //         }, () -> {
        //             System.out.println("debug >> updateUserPost(service) not found");
        //         });
        //         ;
       
        MemberEntity member = memberRepository.findById(email)
                .orElseThrow(() -> new RuntimeException("not found")) // User를 가져온다.
                ;
        // PostEntity post = postRepository.findById(params.getId())
        //         .orElseThrow(() -> new RuntimeException("not found")) // User를 가져온다.
        //         ;
        
        
        // 멤버로부터 post id를 가져와야한다.
        
        // 전체를 보여주면, 굳이 필요없다. -> postRepository에서 가져오면 끝남.

        // 멤버 관련한 post를 가져야하기 때문에 member_id를 알고 post_id를 알아야한다. 이 둘을 받고(직접적으로 받진 않더라도) 
        System.out.println("debug >> updateUserPost(service) member : " + member.toString());
        // member에 있는 posts중에서 id로 post를 찾는다.
        // List<PostEntity> posts = member.getPosts().stream()
        //         .filter(post -> post.getId() == params.getId()) // id로 User를 찾는다

        // 일단 member에서 findPost를 사용해서 post를 찾는것을 구현한다.
        Optional<PostEntity> post = member.findPost(params.getId()); // id로 User를 찾는다.


        System.out.println("debug >> updateUserPost(service) post : " + post.toString());
        post.get().updatePost(params);   // postRepository.save(post); // UserEntity를 저장한다.

        // 바꿔버리면 자동으로 업데이트되나?
    }

    public void deletePostService(String email, Long postId) {
        System.out.println("debug >> deletePost(service) hit");

        // 우선 멤버를 호출한다.
        // memberEntity를 가져온다.
        MemberEntity member = memberRepository.findById(email)
            .orElseThrow(() -> new RuntimeException("not found"))
        ;

        // 해당 하는 memberEntity를 가져 왔다. 여기서 해당하는 postId를 찾는다. 
        Optional<PostEntity> post = member.findPost(postId); // 리스트에 있는 것을 가져온 것이다.
        // 그러면 어떻게 설정하면 되는가?
        // 일단 저장하면 될텐데, 일단 멤버를 토대로 다시 저장하는게 중요하다.
        // 
        member.deletePost(post.get());
        memberRepository.save(member);

        /*
         *  if (op.isPresent()) { // User가 존재하면 -> refresh token이 있다.
            // postRepository.save(post);
            return op.get().getPosts().stream()
                        .map(PostResponseDTO::new) // UserResponseDTO를 생성한다.
                        .toList(); // User를 가져온다.
            
        } 
         * 
         */
        
    }
}
