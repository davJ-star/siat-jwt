package com.example.demo.domain.dto;

import com.example.demo.domain.entity.JpaSampleEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class SampleResponseDTO {
    private String userId;

    private String passwd;

    private String name;

    private Integer point;

    private String refreshToken;

     public SampleResponseDTO(JpaSampleEntity entity) {
        this.userId = entity.getUserId();
        this.passwd = entity.getPasswd();
        this.name = entity.getName();
        this.point = entity.getPoint();
        this.refreshToken = entity.getRefreshToken();
     }

}
