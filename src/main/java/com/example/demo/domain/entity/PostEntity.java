package com.example.demo.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity // JPA에서 제공하는 어노테이션으로, 해당 클래스가 데이터베이스 테이블과 매핑된다는 것을 나타냅니다.
@Table(name = "siat_post_tbl") // 테이블 이름을 지정합니다. 기본적으로 클래스 이름을 소문자로 변환하여 사용합니다.
@Getter // Lombok을 사용하여 Getter 메소드를 자동으로 생성합니다.
@Setter // Lombok을 사용하여 Setter 메소드를 자동으로 생성합니다. (필요에 따라 주석 처리할 수 있습니다.)
@Builder // Builder 패턴을 사용하여 객체를 생성할 수 있도록 합니다.
@AllArgsConstructor // 모든 필드를 매개변수로 받는 생성자를 자동으로 생성합니다.
@NoArgsConstructor // 기본 생성자를 자동으로 생성합니다.
@ToString // Lombok을 사용하여 toString 메소드를 자동으로 생성합니다. (필요에 따라 주석 처리할 수 있습니다.)
public class PostEntity {
    @Id // 기본 키를 나타냅니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 증가 전략을 사용합니다.
    private Long id; // ID

    private String title; // 제목
    private String content; // 내용
}
