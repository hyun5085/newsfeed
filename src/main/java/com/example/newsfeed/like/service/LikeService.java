package com.example.newsfeed.like.service;

import com.example.newsfeed.comment.entity.Comment;
import com.example.newsfeed.comment.repository.CommentRepository;
import com.example.newsfeed.like.dto.LikeResponseDto;
import com.example.newsfeed.like.repository.LikeRepository;
import com.example.newsfeed.user.entity.User;
import com.example.newsfeed.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {

    private LikeRepository likeRepository;
    private UserRepository userRepository;
    private CommentRepository commentRepository;

    public LikeResponseDto CreateLike(Long commentId, Long userId) {
//        User user = userRepository.findByIdOrElseThrow(userId);
//        Comment comment = commentRepository.findByIdOrElseThrow(commentId);
//
//        if(comment.getUser().getId().equals(user.getId())){
//            throw new IllegalArgumentException("본인의 댓글에 좋아요를 남길 수 없습니다.");
//        }
//
                    return null;
  }
}
