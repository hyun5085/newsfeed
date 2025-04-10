package com.example.newsfeed.follow.service;

import com.example.newsfeed.follow.dto.FollowRequestDto;
import com.example.newsfeed.follow.dto.FollowResponseDto;
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
    public FollowResponseDto follow(FollowRequestDto requestDto) {

        User follower = userRepository.findByEmail(requestDto.getFollowerEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Does not exist email "));
        User followed = userRepository.findByEmail(requestDto.getFollowedEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Does not exist email "));

        Follow follow = new Follow(follower, followed);

        followRepository.save(follow);

        return new FollowResponseDto("팔로우 되었습니다.",follower.getEmail(), followed.getEmail());
    }

    @Transactional
    public FollowResponseDto unfollow(FollowRequestDto requestDto) {

        User follower = userRepository.findByEmail(requestDto.getFollowerEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Does not exist email "));
        User followed = userRepository.findByEmail(requestDto.getFollowedEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Does not exist email "));

        Follow follow = new Follow(follower, followed);

        followRepository.delete(follow);

        return new FollowResponseDto("언팔로우 되었습니다.");
    }

}
