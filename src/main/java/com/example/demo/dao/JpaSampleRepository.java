// src/main/java/com/example/demo/dao/JpaSampleRepository.java
package com.example.demo.dao;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.dto.SampleResponseDTO;
import com.example.demo.domain.entity.JpaSampleEntity;

@Repository
public interface JpaSampleRepository extends JpaRepository<JpaSampleEntity, String> {
    Optional<JpaSampleEntity> findByUserIdAndPasswd(String userId, String passwd);

    // DTO 매핑: 반드시 별도 public DTO 클래스 경로 지정
    @Query("SELECT new com.example.demo.domain.dto.SampleResponseDTO(E.userId, E.passwd, E.name) FROM JpaSampleEntity E")
    List<SampleResponseDTO> findByAll();

    @Modifying
    @Query("UPDATE JpaSampleEntity U SET U.passwd = :passwd, U.name = :name WHERE U.userId = :userId")
    void updateRow(@Param("userId") String userId, @Param("passwd") String passwd, @Param("name") String name);
}
