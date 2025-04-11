package com.example.newsfeed.domain.like.service;

import com.example.newsfeed.domain.comment.entity.Comment;
import com.example.newsfeed.domain.comment.repository.CommentRepository;
import com.example.newsfeed.domain.like.dto.LikeResponseDto;
import com.example.newsfeed.domain.like.entity.Like;
import com.example.newsfeed.domain.like.repository.LikeRepository;
import com.example.newsfeed.domain.user.entity.User;
import com.example.newsfeed.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public LikeResponseDto createLike(Long commentId, Long userId) {
        User user = userRepository.findByIdOrElseThrow(userId);
        Comment comment = commentRepository.findByIdOrElseThrow(commentId);

        if (comment.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("본인의 댓글에 좋아요를 남길 수 없습니다.");
        }

        if (likeRepository.existsByCommentAndUser(comment, user)) {
            throw new IllegalStateException("이미 좋아요를 눌렀습니다");
        }

        Like like = new Like(comment, user);
        Like savelike = likeRepository.save(like);
        return LikeResponseDto.from(savelike);
    }
}
