package com.example.demo.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.entity.JpaSampleEntity;
import com.example.demo.domain.entity.PostEntity;

@Repository
public interface JpaSampleRepository extends JpaRepository<JpaSampleEntity, String> {
    // public Optional<PostEntity> findById(Long id); // ID로 Post를 찾는 메소드
}
