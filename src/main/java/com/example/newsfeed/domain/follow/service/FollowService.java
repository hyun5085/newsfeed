package com.example.newsfeed.domain.follow.service;

import com.example.newsfeed.common.exception.CustomException;
import com.example.newsfeed.common.exception.ErrorCode;
import com.example.newsfeed.domain.follow.dto.request.FollowRequestDto;
import com.example.newsfeed.domain.follow.dto.response.FollowResponseDto;
import com.example.newsfeed.domain.follow.dto.response.UnfollowResponseDto;
import com.example.newsfeed.domain.follow.entity.Follow;
import com.example.newsfeed.domain.follow.repository.FollowRepository;
import com.example.newsfeed.domain.user.entity.User;
import com.example.newsfeed.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final UserRepository userRepository;
    private final FollowRepository followRepository;

    @Transactional
    public FollowResponseDto follow(Long userId, FollowRequestDto requestDto) {

        User follower = userRepository.findByIdOrElseThrow(userId);

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

        User follower = userRepository.findByIdOrElseThrow(userId);

        User followed = userRepository.findById(requestDto.getFollowedId())
                .orElseThrow(() -> new CustomException(ErrorCode.FOLLOW_NOT_FOUND));

        if (follower.getId().equals(followed.getId())) {
            throw new CustomException(ErrorCode.FOLLOW_LOGIN_USER);
        }

        // 언팔로우 예외처리 넣음

        Follow findById = followRepository.findByIdOrElseThrow(userId);

        followRepository.delete(findById);

        return new UnfollowResponseDto("언팔로우 되었습니다.");
    }

}
