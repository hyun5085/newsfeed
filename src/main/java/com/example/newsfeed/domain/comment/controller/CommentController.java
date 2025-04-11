package com.example.newsfeed.domain.comment.controller;

import com.example.newsfeed.domain.comment.dto.request.CommentUpdateRequestDto;
import com.example.newsfeed.domain.comment.dto.request.CreateCommentRequestDto;
import com.example.newsfeed.domain.comment.dto.response.CommentResponseDto;
import com.example.newsfeed.domain.comment.dto.response.CreateCommentResponseDto;
import com.example.newsfeed.domain.comment.service.CommentService;
import com.example.newsfeed.common.util.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class CommentController {

    private final CommentService commentService;
    private final JwtUtil jwtUtil;

    /**
     * 댓글 생성
     *
     * @param requestDto
     * @parameter boardId
     */
    @PostMapping("/boards/{boardId}/comments")
    public ResponseEntity<CreateCommentResponseDto> createComment(
            @RequestHeader("Authorization") String authorizationHeader,
            @PathVariable Long boardId,
            @Valid @RequestBody CreateCommentRequestDto requestDto) {
        String token = authorizationHeader.substring(7);
        log.info("생성 url : /boards/{boardId}/comments");
        Long userId = jwtUtil.extractUserId(token);

        CreateCommentResponseDto saveComment = commentService.save(userId, boardId, requestDto);
        return ResponseEntity.ok(saveComment);
    }

    /**
     * 댓글 전체 조회, 페이지네이션 조회
     * ex )
     * /boards/{boardId}/comments 일 경우 전체 조회
     * /boards/{boardId}/comments?page=2 로 들어올 경우 페이지네이션 처리
     * @param boardId
     * @param page : 페이지네이션으로 조회
     * @return
     */
    @GetMapping("/boards/{boardId}/comments")
    public ResponseEntity<?> findCommentsByBoardId(
            @PathVariable Long boardId,
            @RequestParam(required = false) Integer page
    ) {
        if (page == null) {
            // 전체 리스트 조회
            List<CommentResponseDto> commentList = commentService.findCommentsByBoardId(boardId);
            return ResponseEntity.ok(commentList);
        } else {
            // 페이지네이션 조회
            Page<CommentResponseDto> commentPage = commentService.findCommentsPaged(boardId, page);
            return ResponseEntity.ok(commentPage);
        }
    }

    /**
     * 댓글 단건 조회
     *
     * @param id
     */
    @GetMapping("/comments/{id}")
    public ResponseEntity<CommentResponseDto> findCommentById(
            @PathVariable Long id
    ) {
        log.info("단건 조회");
        CommentResponseDto findComment = commentService.findById(id);
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
            @RequestHeader("Authorization") String authorizationHeader,
            @PathVariable Long id,
            @Valid @RequestBody CommentUpdateRequestDto requestDto
    ) {
        String token = authorizationHeader.substring(7);
        Long userId = jwtUtil.extractUserId(token);

        log.info("댓글 수정");

        CommentResponseDto updateComment = commentService.updateComments(id, userId, requestDto);
        return ResponseEntity.ok(updateComment);
    }

    /**
     * 댓글 삭제 api
     * 게시글 작성자, 댓글 작성자만 삭제 가능
     * 완료, 게시글 작성자 기준 되는지 확인 필요
     *
     * @param id
     * @return
     */
    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Void> deleteComment(
            @RequestHeader("Authorization") String authorizationHeader,
            @PathVariable Long id
    ) {
        log.info("댓글 삭제");
        String token = authorizationHeader.substring(7);
        Long userId = jwtUtil.extractUserId(token);

        commentService.deleteComment(id, userId);
        return ResponseEntity.ok().build();
    }
}
