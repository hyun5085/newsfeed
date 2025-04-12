package com.example.newsfeed.domain.like.service;

import com.example.newsfeed.common.exception.CustomException;
import com.example.newsfeed.common.exception.ErrorCode;
import com.example.newsfeed.domain.comment.entity.Comment;
import com.example.newsfeed.domain.comment.repository.CommentRepository;
import com.example.newsfeed.domain.like.dto.LikeResponseDto;
import com.example.newsfeed.domain.like.dto.LikeUpdateResponseDto;
import com.example.newsfeed.domain.like.dto.LikedUserDto;
import com.example.newsfeed.domain.like.entity.Like;
import com.example.newsfeed.domain.like.repository.LikeRepository;
import com.example.newsfeed.domain.user.entity.User;
import com.example.newsfeed.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public LikeUpdateResponseDto createLike(Long commentId, Long userId) {
        User user = userRepository.findByIdOrElseThrow(userId);
        Comment comment = commentRepository.findByIdOrElseThrow(commentId);

        if (comment.getUser().getId().equals(user.getId())) {
            throw new CustomException(ErrorCode.CANNOT_LIKE_OWN_COMMENT);
        }

        if (likeRepository.existsByCommentAndUser(comment, user)) {
            throw new CustomException(ErrorCode.ALREADY_LIKED);
        }

        Like like = new Like(comment, user);
        Like savedlike = likeRepository.save(like);
        return LikeUpdateResponseDto.from(savedlike);
    }

    public LikeResponseDto getLikeInfo(Long commentId, Long userId) {
        User user = userRepository.findByIdOrElseThrow(userId);
        Comment comment = commentRepository.findByIdOrElseThrow(commentId);

        long totalLikes = likeRepository.countLikeByComment(comment);
        boolean likedByCurrentUser = likeRepository.existsByCommentAndUser(comment, user);

        List<Like> likes = likeRepository.findAllByCommentWithUser(comment);

        List<LikedUserDto> likedUsers = likes.stream().map(LikedUserDto::from).toList();
        return LikeResponseDto.of(comment,
                totalLikes,
                likedByCurrentUser,
                likedUsers);
    }

    public void deleteLike(Long commentId, Long userId) {
        User user = userRepository.findByIdOrElseThrow(userId);
        Comment comment = commentRepository.findByIdOrElseThrow(commentId);

        Like like = likeRepository.findByCommentAndUserOrElseThrow(comment, user);
        likeRepository.delete(like);

    }
}
