package com.example.newsfeed.domain.auth.repository;

import com.example.newsfeed.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<User, Long> {

    // 유저 이메일로 검색하는 메서드
    // 요청 데이터 형식: userEmail (String)
    // 반환 데이터 형식: Optional<User> (해당 이메일을 가진 소비자가 있을 경우 해당 객체, 없으면 Optional.empty())
    Optional<User> findUserByEmail(String email);

}
