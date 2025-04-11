package com.example.newsfeed.domain.comment.service;

import com.example.newsfeed.domain.comment.dto.request.CommentUpdateRequestDto;
import com.example.newsfeed.domain.comment.dto.request.CreateCommentRequestDto;
import com.example.newsfeed.domain.comment.dto.response.CommentResponseDto;
import com.example.newsfeed.domain.comment.dto.response.CreateCommentResponseDto;
import com.example.newsfeed.domain.board.entity.Board;
import com.example.newsfeed.domain.comment.entity.Comment;
import com.example.newsfeed.domain.board.repository.BoardRepository;
import com.example.newsfeed.domain.comment.repository.CommentRepository;
import com.example.newsfeed.common.exception.CustomException;
import com.example.newsfeed.common.exception.ErrorCode;
import com.example.newsfeed.domain.user.entity.User;
import com.example.newsfeed.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;


    /**
     * boardId의 게시물에 댓글 저장
     *
     * @param boardId
     * @param requestDto
     * @return
     */
    @Transactional
    public CreateCommentResponseDto save(Long userId, Long boardId, CreateCommentRequestDto requestDto) {
        User user = userRepository.findByIdOrElseThrow(userId);

        Board board = boardRepository.findByIdOrElseThrow(boardId);
        Comment comment = new Comment(user, board, requestDto.getContents());
        commentRepository.save(comment);

        //comment 엔티티를 dto 내부에서 CreateCommentResponseDto로 변환
        return CreateCommentResponseDto.from(comment);
    }

    /**
     * boardId의 게시물에 작성된 댓글 전체 조회
     * 수정일 기준 최신날짜를 기준으로 내림차순
     *
     * @param boardId
     * @return List<CommentResponseDto>
     */
    @Transactional(readOnly = true)
    public List<CommentResponseDto> findCommentsByBoardId(Long boardId) {
        Board board = boardRepository.findByIdOrElseThrow(boardId);

        List<Comment> commentList = commentRepository.findByBoardIdOrderByUpdatedAtDesc(board.getId());

        if (commentList.isEmpty()) {
            throw new CustomException(ErrorCode.COMMENT_NOT_FOUND);
        }
        // dto 내부에서 CommentResponseDto 변환
        return commentList.stream().map(CommentResponseDto::from).toList();

    }

    /**
     * 댓글 조회 페이지네이션
     * 수정일 기준 최신날짜를 기준으로 내림차순
     * page : 페이지 번호, size : resource 수, 댓글 10개
     *
     * @param boardId
     * @param page
     * @return
     */

    @Transactional(readOnly = true)
    public Page<CommentResponseDto> findCommentsPaged(Long boardId, Integer page) {
        Board board = boardRepository.findByIdOrElseThrow(boardId);

        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "updatedAt"));
        Page<Comment> commentPage = commentRepository.findAllByBoard_Id(board.getId(), pageable);

        return commentPage.map(CommentResponseDto::from);
    }

    /**
     * 게시글의 댓글 단건 조회
     *
     * @param id
     * @return CommentResponseDto
     */
    @Transactional(readOnly = true)
    public CommentResponseDto findById(Long id) {
        Comment comment = commentRepository.findByIdOrElseThrow(id);
        // comment 엔티티를 dto 내부에서 CommentResponseDto 변환
        return CommentResponseDto.from(comment);
    }

    /**
     * 댓글 수정
     * 댓글 작성자만 수정 가능
     *
     * @param id
     * @param requestDto
     * @return
     */
    @Transactional
    public CommentResponseDto updateComments(Long id, Long userId, CommentUpdateRequestDto requestDto) {

        Comment findComment = commentRepository.findByIdOrElseThrow(id);
        // 댓글의 작성자와 요청받은 식별자 값이 일치하지 않을 경우
        if (!findComment.getUser().getId().equals(userId)) {
            throw new CustomException(ErrorCode.COMMENT_UPDATE_UNAUTHORIZED);
        }

        findComment.update(requestDto.getContents());
        // comment 엔티티를 dto 내부에서 CommentResponseDto 변환
        return CommentResponseDto.from(findComment);
    }

    /**
     * 댓글 삭제
     * 댓글 작성자, 게시글 작성자만 삭제 가능
     *
     * @param id
     * @return
     */
    @Transactional
    public void deleteComment(Long id, Long userId) {
        Comment comment = commentRepository.findByIdOrElseThrow(id);

        if (!comment.getUser().getId().equals(userId) && !comment.getBoard().getUser().getId().equals(userId)) {
            throw new CustomException(ErrorCode.COMMENT_DELETE_UNAUTHORIZED);
        }
        commentRepository.delete(comment);
    }
}

