package com.example.newsfeed.follow.repository;

import com.example.newsfeed.follow.entitiy.Follow;
import com.example.newsfeed.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {

   Optional<Follow> findByFollowerAndFollowing(User follower, User following);

//    List<Follow> findByFollower(User follower);
//
//    List<Follow> findByFollowing(User following);

}
