package com.example.newsfeed.user.repository;

import com.example.newsfeed.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // 해당 부분 추가
// 사용중인 이메일 확인
    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

}

