package com.example.newsfeed.domain.user.repository;

import com.example.newsfeed.domain.user.entity.RetiredEmail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 이메일 조회 Repository
 */
public interface RetiredEmailRepository extends JpaRepository<RetiredEmail, Long> {
    boolean existsByEmail(String email);
}
