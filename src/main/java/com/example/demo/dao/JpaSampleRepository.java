package com.example.demo.dao;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.entity.JpaSampleEntity;
import com.example.demo.domain.entity.PostEntity;
import java.util.List;

// JPQL - update, delete
@Repository
public interface JpaSampleRepository extends JpaRepository<JpaSampleEntity, String> {
    // public Optional<PostEntity> findById(Long id); // ID로 Post를 찾는 메소드
    public Optional<JpaSampleEntity> findByUserIdAndPasswd(String userId, String passWd);
    
    @Modifying
    //@Query("UPDATE SIAT_JPA_SAMPLE_TBL SET user_passwd = ? , user_name = ? WHERE user_id = ?")
    // 테이블 별칭을 활용해야하고 ?가 아니라 :별칭이 필요하다.

    // 엔티티 기존으로 체크하고 필드명이랑 일치시켜한다. 
    // 또한 일단 map에서 key값과 requestBody의 key값이 동일해야한다.
    @Query( "UPDATE JpaSampleEntity U "  
            + "SET U.passwd = :passwd, U.name = :name " 
            + "WHERE U.userId = :userId"
            ) // userId
    public void updateRow(  @Param("userId") String userId, // userId
                            @Param("passwd") String passwd,
                            @Param("name") String name);
}
