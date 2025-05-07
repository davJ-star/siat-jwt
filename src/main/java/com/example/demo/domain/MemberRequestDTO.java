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


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MemberRequestDTO {
    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
    //@Column(name = "id") // column name을 자동으로 생성한다. -> @Column(name = "user_id")로 변경 가능하다.
    private Long id; // ID

    //@Column(name = "name") // column name을 자동으로 생성한다. -> @Column(name = "user_name")로 변경 가능하다.
    private String email; // email

    //@Column(name = "password") // column name을 자동으로 생성한다. -> @Column(name = "user_password")로 변경 가능하다.
    private String pwd; // password

    //@Column(name = "access_token") // column name을 자동으로 생성한다. -> @Column(name = "user_token")로 변경 가능하다.
    private String accessToken; // token

    //@Column(name = "refresh_token") // column name을 자동으로 생성한다. -> @Column(name = "user_token")로 변경 가능하다.
    private String refreshToken; // token

    //private List<PostResponseDTO> posts; // member가 쓴 글을 나타냅니다.
    // private List<PostEntity> posts; // member가 쓴 글을 나타냅니다.
    
}
