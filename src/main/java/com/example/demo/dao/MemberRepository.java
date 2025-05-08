package com.example.demo.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.entity.MemberEntity;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, String> {
    // MemberEntity를 가지고 전체 post를 가져올 순 없다.
    // public Optional<MemberEntity> findById(String id); // ID로 User를 찾는 메소드
    
}
