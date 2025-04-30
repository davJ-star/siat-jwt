package com.example.demo.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.entity.UserEntity;

@Repository
// mybatis는 mapper interface로 진행된다. -> @Mapper  그리고 .xml로 mapper를 작성한다.
public interface AuthRepository extends JpaRepository<UserEntity, Long> {
    // JpaRepository를 상속받아 CRUD 메소드를 자동으로 생성합니다. // JpaRepository<User, Long>에서 User는 엔티티 클래스, Long은 ID 타입입니다.
    // CRUD 메소드 정의
    Optional<UserEntity> findById(Long id); // ID로 User를 찾는 메소드
    Optional<UserEntity> findByEmail(String email); // email로 User를 찾는 메소드

    // CRUD 메소드 정의
    // 예: User findById(Long id);
    // 예: List<User> findAll();
    // 예: void save(User user);
    // 예: void deleteById(Long id);
    // 예: void delete(User user);
    // 예: void update(User user);
    // 예: User findByEmail(String email);
    // 예: User findByEmailAndPassword(String email, String password);
    // 예: User findByEmailAndPasswordAndToken(String email, String password, String token);
    // 예: User findByEmailAndToken(String email, String token);
    // 예: User findByToken(String token);
    // 예: User findByEmailAndPasswordAndRefreshToken(String email, String password, String refreshToken);
    // 예: User findByEmailAndRefreshToken(String email, String refreshToken);
    // 예: User findByRefreshToken(String refreshToken);
    // 예: User findByEmailAndPasswordAndAccessToken(String email, String password, String accessToken);
    // 예: User findByEmailAndAccessToken(String email, String accessToken);
    // 예: User findByAccessToken(String accessToken);
    // 예: User findByEmailAndPasswordAndAccessTokenAndRefreshToken(String email, String password, String accessToken, String refreshToken);
    // 예: User findByEmailAndAccessTokenAndRefreshToken(String email, String accessToken, String refreshToken);
    // 예: User findByAccessTokenAndRefreshToken(String accessToken, String refreshToken);
    // 예: User findByEmailAndPasswordAndAccessTokenAndRefreshTokenAndId(String email, String password, String accessToken, String refreshToken, Long id);
    // 예: User findByEmailAndAccessTokenAndRefreshTokenAndId(String email, String accessToken, String refreshToken, Long id);
    // 예: User findByAccessTokenAndRefreshTokenAndId(String accessToken, String refreshToken, Long id);
    // 예: User findByEmailAndPasswordAndAccessTokenAndRefreshTokenAndIdAndName(String email, String password, String accessToken, String refreshToken, Long id, String name);
    // 예: User findByEmailAndAccessTokenAndRefreshTokenAndIdAndName(String email, String accessToken, String refreshToken, Long id, String name);
    // 예: User findByAccessTokenAndRefreshTokenAndIdAndName(String accessToken, String refreshToken, Long id, String name);
}
