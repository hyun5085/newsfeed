package com.example.newsfeed.follow.service;

import com.example.newsfeed.follow.dto.FollowRequestDto;
import com.example.newsfeed.follow.dto.FollowResponseDto;
import com.example.newsfeed.follow.entitiy.Follow;
import com.example.newsfeed.follow.repository.FollowRepository;
import com.example.newsfeed.user.entity.User;
import com.example.newsfeed.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final UserRepository userRepository;
    private final FollowRepository followRepository;

    @Transactional
    public FollowResponseDto followUser(FollowRequestDto requestDto) {

        Long followerId = requestDto.getFollowerId();
        Long followingId = requestDto.getFollowingId();

        User findFollower = userRepository.findByIdOrElseThrow(followerId);
        User findFollowing = userRepository.findByIdOrElseThrow(followingId);

        followRepository.findByFollowerAndFollowing(follower, following);


    }






//         2
//
//  public FollowResponseDto follow(User email) {
//
//        User findEmail = userRepository.findUserByUserEmailOrElseThrow(email.getEmail());
//
//        Follow follow = new Follow(follower, following);
//        follow.setUser(findEmail);
//
//        followRepository.save(follow);
//
//        return new FollowResponseDto(follow);



//        1
//        private final FollowRepository followRepository;
//        private final UserRepository userRepository;
//
//        userRepository.findUserByUserId(userId);
//
//        Follow follow = new Follow(follower, following);
//        follow.
//
//  }
}
