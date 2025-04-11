package com.example.newsfeed.follow.service;

import com.example.newsfeed.exception.CustomException;
import com.example.newsfeed.exception.ErrorCode;
import com.example.newsfeed.follow.dto.FollowRequestDto;
import com.example.newsfeed.follow.dto.FollowResponseDto;
import com.example.newsfeed.follow.dto.UnfollowResponseDto;
import com.example.newsfeed.follow.entitiy.Follow;
import com.example.newsfeed.follow.repository.FollowRepository;
import com.example.newsfeed.user.entity.User;
import com.example.newsfeed.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final UserRepository userRepository;
    private final FollowRepository followRepository;

    @Transactional
    public FollowResponseDto follow(Long userId, FollowRequestDto requestDto) {

        User follower = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        User followed = userRepository.findById(requestDto.getFollowedId())
                .orElseThrow(() -> new CustomException(ErrorCode.FOLLOW_NOT_FOUND));

        if (follower.getId().equals(followed.getId())) {
            throw new CustomException(ErrorCode.FOLLOW_LOGIN_USER);
        }

        if (followRepository.findByFollowerAndFollowed(follower, followed).isPresent()) {
            throw new CustomException(ErrorCode.FOLLOW_ALREADY_FOLLOW);
        }

        Follow follow = new Follow(follower, followed);

        followRepository.save(follow);

        return new FollowResponseDto(follow.getId(), follower.getEmail(), followed.getEmail(), "팔로우 되었습니다.");
    }

    @Transactional
    public UnfollowResponseDto unfollow(Long userId, FollowRequestDto requestDto) {

        Follow findById = followRepository.findById(userId).orElseThrow();

        followRepository.delete(findById);

        return new UnfollowResponseDto("언팔로우 되었습니다.");
    }

}
