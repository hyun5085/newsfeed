package com.example.newsfeed.domain.like.repository;


import com.example.newsfeed.domain.comment.entity.Comment;
import com.example.newsfeed.domain.like.entity.Like;
import com.example.newsfeed.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like,Long> {

    boolean existsByCommentAndUser(Comment comment, User user);
}
