package com.example.newsfeed.domain.like.repository;


import com.example.newsfeed.common.exception.CustomException;
import com.example.newsfeed.common.exception.ErrorCode;
import com.example.newsfeed.domain.comment.entity.Comment;
import com.example.newsfeed.domain.like.entity.Like;
import com.example.newsfeed.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    // 해당 유저가 좋아요를 눌렀는지 상태 여부
    boolean existsByCommentAndUser(Comment comment, User user);

    // 좋아요 총 개수
    long countLikeByComment(Comment comment);

    // 좋아요 누른 유저 정보 리스트 조회
    @Query("select l from Like l join fetch l.user where l.comment = :comment ")
    List<Like> findAllByCommentWithUser(@Param("comment") Comment comment);

    // 좋아요를 찾지 못했을때 예외, 있다면 반환
    default Like findByCommentAndUserOrElseThrow(Comment comment, User user) {
        return findByCommentAndUser(comment, user).orElseThrow(
                () -> new CustomException(ErrorCode.LIKE_NOT_FOUND)
        );
    }
    // 좋아요 조회 메서드
    Optional<Like> findByCommentAndUser(Comment comment, User user);

}
