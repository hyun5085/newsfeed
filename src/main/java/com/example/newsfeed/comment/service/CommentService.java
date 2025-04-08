package com.example.newsfeed.comment.service;

import com.example.newsfeed.comment.dto.CommentUpdateRequestDto;
import com.example.newsfeed.comment.dto.CreateCommentRequestDto;
import com.example.newsfeed.comment.dto.CommentResponseDto;
import com.example.newsfeed.comment.dto.CreateCommentResponseDto;
import com.example.newsfeed.comment.entity.Board;
import com.example.newsfeed.comment.entity.Comment;
import com.example.newsfeed.comment.repository.BoardRepository;
import com.example.newsfeed.comment.repository.CommentRepository;
import com.example.newsfeed.comment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;


    /**
     * boardId의 게시물에 댓글 저장
     * @param boardId
     * @param requestDto
     * @return
     */
    @Transactional
    public CreateCommentResponseDto save(Long boardId, CreateCommentRequestDto requestDto) {

        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 게시글이 존재하지 않습니다.")
        );
        Comment comment = new Comment(board, requestDto.getContents());
        commentRepository.save(comment);

        return new CreateCommentResponseDto(
                comment.getId(),
                comment.getBoard().getId(),
                comment.getUser().getId(),
                comment.getUser().getUsername(),
                comment.getContents(),
                comment.getCreatedAt()
        );
    }

    /**
     * boardId의 게시물에 작성된 댓글 전체 조회
     * 최신순으로 정렬
     * @param boardId
     * @return List<CommentResponseDto>
     */
    @Transactional(readOnly = true)
    public List<CommentResponseDto> findCommentsByBoardId(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 게시글이 존재하지 않습니다.")
        );

        List<Comment> commentList = commentRepository.findByBoardIdOrderByUpdatedAtDesc(board.getId());
        if (commentList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "댓글이 존재하지 않습니다.");
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
     * @param boardId
     * @param id
     * @return CommentResponseDto
     */
    @Transactional(readOnly = true)
    public CommentResponseDto findByBoardId(Long boardId, Long id) {
        Board board = boardRepository.findById(boardId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "게시물을 찾을 수 없습니다.")
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
     * @param id
     * @param requestDto
     * @return
     */
    @Transactional
    public CommentResponseDto updateComments(Long id, CommentUpdateRequestDto requestDto) {
        Comment findComment = commentRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "댓글을 찾을 수 없습니다.")
        );
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
     * @param id
     * @return
     */
    @Transactional
    public String deleteComment(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("댓글을 찾을 수 업습니다.")
        );
        commentRepository.delete(comment);
        return "삭제되었습니다";
    }

}
