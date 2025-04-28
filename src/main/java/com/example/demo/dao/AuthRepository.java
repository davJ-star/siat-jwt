package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.entity.UserEntity;

@Repository
// mybatis는 mapper interface로 진행된다. -> @Mapper  그리고 .xml로 mapper를 작성한다.
public interface AuthRepository extends JpaRepository<UserEntity, Long> {
    // JpaRepository를 상속받아 CRUD 메소드를 자동으로 생성합니다. // JpaRepository<User, Long>에서 User는 엔티티 클래스, Long은 ID 타입입니다.
    // CRUD 메소드 정의
    // 예: User findById(Long id);
    // 예: List<User> findAll();
    // 예: void save(User user);
    // 예: void deleteById(Long id);
}
