
package com.example.demo.domain.entity;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
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


@Entity
@Table(name = "siat_jpa_sample_tbl")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicUpdate
public class JpaSampleEntity {

    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", length = 50) // , nullable = false
    private String userId;

    @Column(name = "user_passwd", length = 50, nullable = false)
    private String passwd;

    @Column(name = "user_name", length = 50, nullable = false)
    private String name;
    
    @Column(name = "user_token")
    private String refreshToken;
    

}
