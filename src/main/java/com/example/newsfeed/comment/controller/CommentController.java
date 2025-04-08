package com.example.newsfeed.comment.controller;

import com.example.newsfeed.comment.dto.CommentUpdateRequestDto;
import com.example.newsfeed.comment.dto.CreateCommentRequestDto;
import com.example.newsfeed.comment.dto.CommentResponseDto;
import com.example.newsfeed.comment.dto.CreateCommentResponseDto;
import com.example.newsfeed.comment.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class CommentController {

    private final CommentService commentService;

    // 댓글 생성 Api
    // path variable : boardId
    // requestBody : contents

    /**
     * 댓글 생성
     *
     * @param requestDto
     * @parameter boardId
     */
    @PostMapping("/board/{boardId}/comments")
    public ResponseEntity<CreateCommentResponseDto> createComment(
            @PathVariable Long boardId,
            @Valid @RequestBody CreateCommentRequestDto requestDto) {
        log.info("Comment 생성");
        CreateCommentResponseDto saveComment = commentService.save(boardId, requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveComment);
    }

    /**
     * 댓글 전체 조회
     *
     * @param boardId
     */
    @GetMapping("/boards/{boardId}/comments")
    public ResponseEntity<List<CommentResponseDto>> findCommentsByBoardId(
            @PathVariable Long boardId
    ) {
        List<CommentResponseDto> commentList = commentService.findCommentsByBoardId(boardId);
        return ResponseEntity.ok(commentList);
    }

    /**
     * 댓글 단건 조회
     *
     * @param boardId
     * @param id
     */
    @GetMapping("/boards/{boardId}/comments/{id}")
    public ResponseEntity<CommentResponseDto> findCommentById(
            @PathVariable Long boardId,
            @PathVariable Long id
    ) {
        CommentResponseDto findComment = commentService.findByBoardId(boardId, id);
        return ResponseEntity.ok(findComment);
    }

    /**
     * 댓글 수정 api
     * 작성자만 수정 가능
     *
     * @param id
     * @param requestDto
     * @return
     */
    @PutMapping("/comments/{id}")
    public ResponseEntity<CommentResponseDto> updateComment(
            @PathVariable Long id,
            @Valid @RequestBody CommentUpdateRequestDto requestDto
    ) {
        CommentResponseDto updateComment = commentService.updateComments(id, requestDto);
        return ResponseEntity.ok(updateComment);
    }

    /**
     * 댓글 삭제 api
     * 게시글 작성자, 댓글 작성자만 삭제 가능
     *
     * @param id
     * @return
     */
    @DeleteMapping("/comments/{id}")
    public ResponseEntity<String> deleteComment(
            @PathVariable Long id
    ) {
        String msg = commentService.deleteComment(id);
        return ResponseEntity.ok(msg);
    }
}
