package com.example.demo.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity // table name을 자동으로 생성한다. -> @Table(name = "user")로 변경 가능하다.
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
    @Column(name = "id") // column name을 자동으로 생성한다. -> @Column(name = "user_id")로 변경 가능하다.
    private Long id; // ID

    @Column(name = "name") // column name을 자동으로 생성한다. -> @Column(name = "user_name")로 변경 가능하다.
    private String email; // email

    @Column(name = "password") // column name을 자동으로 생성한다. -> @Column(name = "user_password")로 변경 가능하다.
    private String pwd; // password

    @Column(name = "token") // column name을 자동으로 생성한다. -> @Column(name = "user_token")로 변경 가능하다.
    private String token; // token


}
