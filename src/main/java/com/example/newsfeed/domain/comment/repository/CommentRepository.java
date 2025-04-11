package com.example.newsfeed.domain.comment.repository;

import com.example.newsfeed.domain.comment.entity.Comment;
import com.example.newsfeed.common.exception.CustomException;
import com.example.newsfeed.common.exception.ErrorCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    // 게시글의 댓글 전체 조회 (최신순)
    List<Comment> findByBoardIdOrderByUpdatedAtDesc(Long boardId);

    // 게시글의 댓글 페이지네이션 조회
    Page<Comment> findAllByBoard_Id(Long id, Pageable pageable);

    // 커스텀 예외 처리 포함한 단건 조회
    default Comment findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(
                () -> new CustomException(ErrorCode.COMMENT_NOT_FOUND)
        );
    }
}
