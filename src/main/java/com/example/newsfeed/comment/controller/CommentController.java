package com.example.newsfeed.comment.controller;

import com.example.newsfeed.comment.dto.request.CommentUpdateRequestDto;
import com.example.newsfeed.comment.dto.request.CreateCommentRequestDto;
import com.example.newsfeed.comment.dto.response.CommentResponseDto;
import com.example.newsfeed.comment.dto.response.CreateCommentResponseDto;
import com.example.newsfeed.comment.service.CommentService;
import com.example.newsfeed.cookiesession.dto.LoginResponseDto;
import com.example.newsfeed.exception.CustomException;
import com.example.newsfeed.exception.ErrorCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class CommentController {

    private final CommentService commentService;

    /**
     * 댓글 생성*
     * 완료
     *
     * @param requestDto
     * @parameter boardId
     */
    @PostMapping("/boards/{boardId}/comments")
    public ResponseEntity<CreateCommentResponseDto> createComment(
            @SessionAttribute(name = "LOGIN_USER", required = false) LoginResponseDto loginUser,
            @PathVariable Long boardId,
            @Valid @RequestBody CreateCommentRequestDto requestDto) {

        if (loginUser == null || loginUser.getId() == null) {
            throw new CustomException(ErrorCode.UNAUTHORIZED);
        }
        log.info("Comment 생성");
        Long userId = loginUser.getId();
        CreateCommentResponseDto saveComment = commentService.save(userId, boardId, requestDto);
        return ResponseEntity.ok(saveComment);
    }

    /**
     * 댓글 전체 조회
     * 완료
     *
     * @param boardId
     */
    @GetMapping("/boards/{boardId}/comments")
    public ResponseEntity<List<CommentResponseDto>> findCommentsByBoardId(
            @PathVariable Long boardId
    ) {

        //  Page<CommentResponseDto> commentPage = commentService.findCommentsPaged(boardId);
        List<CommentResponseDto> commentList = commentService.findCommentsByBoardId(boardId);
        return ResponseEntity.ok(commentList);
    }
//
//    @GetMapping("/boards/{boardId}/comments/page")
//    public ResponseEntity<Page<CommentResponseDto>> findCommentsByBoardId(
//            @PathVariable Long boardId,
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size
//    ) {
//
//        Page<CommentResponseDto> commentPage = commentService.findCommentsPaged(boardId,page,size);
//        return ResponseEntity.ok(commentPage);
//    }

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
     * 완료
     *
     * @param id
     * @param requestDto
     * @return
     */
    @PutMapping("/comments/{id}")
    public ResponseEntity<CommentResponseDto> updateComment(
            @SessionAttribute(name = "LOGIN_USER", required = false) LoginResponseDto loginUser,
            @PathVariable Long id,
            @Valid @RequestBody CommentUpdateRequestDto requestDto
    ) {
        if (loginUser == null || loginUser.getId() == null) {
            throw new CustomException(ErrorCode.UNAUTHORIZED);
        }
        Long userId = loginUser.getId();
        CommentResponseDto updateComment = commentService.updateComments(id, userId, requestDto);
        return ResponseEntity.ok(updateComment);
    }

    /**
     * 댓글 삭제 api
     * 게시글 작성자, 댓글 작성자만 삭제 가능
     *  완료, 게시글 작성자 기준 되는지 확인 필요
     * @param id
     * @return
     */
    @DeleteMapping("/comments/{id}")
    public ResponseEntity<String> deleteComment(
            @SessionAttribute(name = "LOGIN_USER", required = false) LoginResponseDto loginUser,
            @PathVariable Long id
    ) {
        if (loginUser == null || loginUser.getId() == null) {
            throw new CustomException(ErrorCode.UNAUTHORIZED);
        }
        Long userId = loginUser.getId();
        String msg = commentService.deleteComment(id, userId);
        return ResponseEntity.ok(msg);
    }
}
