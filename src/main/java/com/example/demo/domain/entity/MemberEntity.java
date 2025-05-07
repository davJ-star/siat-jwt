package com.example.demo.domain.entity;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.domain.PostResponseDTO;

//import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
//import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



/*
 * 데이터베이스 테이블과 java 객체간의 매핑
 * 이를 도와주는 것이 ORM(Object Relational Mapping)이다.
 * JPA는 ORM의 구현체이다.
 * @Entity는 JPA에서 제공하는 어노테이션으로, 해당 클래스가 데이터베이스 테이블과 매핑된다는 것을 나타낸다.
 * @Entity 어노테이션이 붙은 클래스는 JPA가 관리하는 엔티티 클래스가 된다.
 */
@Entity
@Table(name = "siat_member_tbl") // 테이블 이름을 지정합니다. 기본적으로 클래스 이름을 소문자로 변환하여 사용합니다.
@Builder // Builder 패턴을 사용하여 객체를 생성할 수 있도록 합니다.
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MemberEntity {
    @Id // 기본 키를 나타냅니다.
    //@GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 증가 전략을 사용합니다.
    @Column(name = "email") // email를 저장할 컬럼 이름을 지정합니다.
    private String email; // 이메일


    private String passwd; // 비밀번호

    @Column(name = "refresh_token") // 리프레시 토큰을 저장할 컬럼 이름을 지정합니다.
    private String refreshToken; // 리프레시 토큰

    // member가 쓴 글이필요하다. post_id를 추가한다.
    // @OneToMany(mappedBy = "postEntity") // postEntity 기준으로 매핑합니다.
    // // mappedBy는 연관관계의 주인이 아닌 쪽에서 사용합니다.
    // private Long postId; // 글 ID 

    // OneToMany는 member가 쓴 글을 나타냅니다.또는 그냥 PostEntity로 해도 된다.
    //@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true) // cascade = CascadeType.ALL // cascade = CascadeType.ALL은 연관된 엔티티를 함께 저장, 삭제하는 옵션입니다.
    @OneToMany // (cascade = CascadeType.ALL, orphanRemoval = true) // cascade = CascadeType.ALL // cascade = CascadeType.ALL은 연관된 엔티티를 함께 저장, 삭제하는 옵션입니다.
    @JoinColumn(name = "member_id") // member_id를 외래 키로 사용합니다.
    // @JoinColumn은 연관관계의 주인 쪽에서 사용합니다.
    private List<PostEntity> posts = new ArrayList<>(); // member가 쓴 글을 나타냅니다. 
    //private List<PostResponseDTO> posts = new ArrayList<>(); // member가 쓴 글을 나타냅니다. 
}
/*
 * @Id // 기본 키를 나타냅니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 증가 전략을 사용합니다.
    private Long id; // ID

    private String email; // 이메일

    private String pwd; // 비밀번호

    private String refreshToken; // 리프레시 토큰

    // Getters and Setters (생략 가능, Lombok 사용 시 @Getter, @Setter 어노테이션으로 대체 가능)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
 * 
 */