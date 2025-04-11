package com.example.newsfeed.user.repository;

import com.example.newsfeed.exception.CustomException;
import com.example.newsfeed.exception.ErrorCode;
import com.example.newsfeed.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 회원 Repository
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 사용중인 이메일 확인
     *
     * @param email the email
     * @return the boolean
     */
    boolean existsByEmail(String email);

    /**
     * 이메일 검증
     *
     * @param email the email
     * @return the optional
     */
    Optional<User> findByEmail(String email);

    /**
     * 유저 식별 번호 확인
     *
     * @param id the id
     * @return the user
     */
    default User findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }
}

