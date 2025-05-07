package com.example.demo.domain;

import java.util.List;

import com.example.demo.domain.entity.PostEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// import jakarta.persistence.Column;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
// mybatis에서 활용한 dto과 다른 점은 일단 거기서는 todo만 신경썼지만, 여기서는 post 자체 초점.
public class PostRequestDTO {
    private String title; // 제목
    private String content; // 내용

    // //@Id
    // //@GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
    // //@Column(name = "id") // column name을 자동으로 생성한다. -> @Column(name = "user_id")로 변경 가능하다.
    // private Long id; // ID

    // //@Column(name = "name") // column name을 자동으로 생성한다. -> @Column(name = "user_name")로 변경 가능하다.
    // private String email; // email

    // //@Column(name = "password") // column name을 자동으로 생성한다. -> @Column(name = "user_password")로 변경 가능하다.
    // private String pwd; // password

    // //@Column(name = "access_token") // column name을 자동으로 생성한다. -> @Column(name = "user_token")로 변경 가능하다.
    // private String accessToken; // token

    // //@Column(name = "refresh_token") // column name을 자동으로 생성한다. -> @Column(name = "user_token")로 변경 가능하다.
    // private String refreshToken; // token

    // //private List<PostResponseDTO> posts; // member가 쓴 글을 나타냅니다.
    // private List<PostEntity> posts; // member가 쓴 글을 나타냅니다.
    
}
