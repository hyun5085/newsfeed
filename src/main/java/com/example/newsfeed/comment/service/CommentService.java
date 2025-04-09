package com.example.newsfeed.comment.service;

import com.example.newsfeed.comment.dto.request.CommentUpdateRequestDto;
import com.example.newsfeed.comment.dto.request.CreateCommentRequestDto;
import com.example.newsfeed.comment.dto.response.CommentResponseDto;
import com.example.newsfeed.comment.dto.response.CreateCommentResponseDto;
import com.example.newsfeed.boards.entity.Board;
import com.example.newsfeed.comment.entity.Comment;
import com.example.newsfeed.boards.repository.BoardRepository;
import com.example.newsfeed.comment.repository.CommentRepository;
import com.example.newsfeed.exception.CustomException;
import com.example.newsfeed.exception.ErrorCode;
import com.example.newsfeed.user.entity.User;
import com.example.newsfeed.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
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
        User user = userRepository.findById(userId).orElseThrow(
                () -> new CustomException(ErrorCode.USER_NOT_FOUND)
        );

        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new CustomException(ErrorCode.BOARD_NOT_FOUND)
        );
        Comment comment = new Comment(user, board, requestDto.getContents());
        commentRepository.save(comment);

        return new CreateCommentResponseDto(
                comment.getId(),
                board.getId(),
                user.getId(),
                user.getUsername(),
                comment.getContents(),
                comment.getCreatedAt()
        );
    }

    /**
     * boardId의 게시물에 작성된 댓글 전체 조회
     * 최신순으로 정렬
     *
     * @param boardId
     * @return List<CommentResponseDto>
     */
    @Transactional(readOnly = true)
    public List<CommentResponseDto> findCommentsByBoardId(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new CustomException(ErrorCode.BOARD_NOT_FOUND)
        );
        List<Comment> commentList = commentRepository.findByBoardIdOrderByUpdatedAtDesc(board.getId());
        if (commentList.isEmpty()) {
            throw new CustomException(ErrorCode.COMMENT_NOT_FOUND);
        }

        return commentList.stream().map(comment ->
                new CommentResponseDto(
                        comment.getId(),
                        comment.getBoard().getId(),
                        comment.getUser().getId(),
                        comment.getUser().getUsername(),
                        comment.getContents(),
                        comment.getCreatedAt(),
                        comment.getUpdatedAt()
                )).toList();

    }

    /**
     * boardId에 해당하는 게시글의 댓글 단건 조회
     *
     * @param boardId
     * @param id
     * @return CommentResponseDto
     */
    @Transactional(readOnly = true)
    public CommentResponseDto findByBoardId(Long boardId, Long id) {
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new CustomException(ErrorCode.BOARD_NOT_FOUND)
        );
        Comment comment = commentRepository.findByBoardId(board.getId());
        return new CommentResponseDto(
                comment.getId(),
                comment.getBoard().getId(),
                comment.getUser().getId(),
                comment.getUser().getUsername(),
                comment.getContents(),
                comment.getCreatedAt(),
                comment.getUpdatedAt()
        );
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


        Comment findComment = commentRepository.findById(id).orElseThrow(
                () -> new CustomException(ErrorCode.COMMENT_NOT_FOUND)
        );
        if (!findComment.getUser().getId().equals(userId)) {
            throw new CustomException(ErrorCode.COMMENT_UPDATE_UNAUTHORIZED);
        }
        findComment.update(requestDto.getContents());
        return new CommentResponseDto(
                findComment.getId(),
                findComment.getBoard().getId(),
                findComment.getUser().getId(),
                findComment.getUser().getUsername(),
                findComment.getContents(),
                findComment.getCreatedAt(),
                findComment.getUpdatedAt()
        );
    }

    /**
     * 댓글 삭제
     * 댓글 작성자, 게시글 작성자만 삭제 가능
     *
     * @param id
     * @return
     */
    @Transactional
    public String deleteComment(Long id, Long userId) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new CustomException(ErrorCode.COMMENT_NOT_FOUND)
        );

        if (!comment.getUser().getId().equals(userId) || !comment.getBoard().getUser().getId().equals(userId)) {
            throw new CustomException(ErrorCode.COMMENT_DELETE_UNAUTHORIZED);
        }
        commentRepository.delete(comment);
        return "삭제되었습니다";
    }

//    public Page<CommentResponseDto> findCommentsPaged(Long boardId, int page, int size) {
//        Board board = boardRepository.findById(boardId).orElseThrow(
//                () -> new CustomException(ErrorCode.BOARD_NOT_FOUND));
//        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "updatedAt"));
//        Page<Comment> page = commentRepository.findAllByBoard_Id(board.getId(), pageable);
//
//        Page<CommentResponseDto> dtoPage = page.map(c -> new CommentResponseDto(
//                c.getId(),
//                c.getBoard().getId(),
//                c.getUser().getId(),
//                c.getUser().getUsername(),
//                c.getContents(),
//                c.getCreatedAt(),
//                c.getUpdatedAt()
//        ));
//        return dtoPage;
//    }
}

