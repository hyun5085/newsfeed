package com.example.newsfeed.follow.repository;

import com.example.newsfeed.follow.entitiy.Follow;
import com.example.newsfeed.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {
}
