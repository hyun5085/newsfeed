package com.example.newsfeed.like.repository;


import com.example.newsfeed.like.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like,Long> {

}
