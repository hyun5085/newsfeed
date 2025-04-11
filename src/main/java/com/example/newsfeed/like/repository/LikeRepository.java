package com.example.newsfeed.like.repository;


import com.example.newsfeed.comment.entity.Comment;
import com.example.newsfeed.like.entity.Like;
import com.example.newsfeed.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like,Long> {

    boolean existsByCommentAndUser(Comment comment, User user);
}
