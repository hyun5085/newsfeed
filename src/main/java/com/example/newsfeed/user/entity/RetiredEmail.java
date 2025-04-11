package com.example.newsfeed.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 이메일 조회 테이블
 */
@Entity
@Getter
@Table(name = "retired_email")
public class RetiredEmail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    public RetiredEmail() {

    }

    // ✅ email만 받는 생성자 추가
    public RetiredEmail(String email) {
        this.email = email;
    }

}
