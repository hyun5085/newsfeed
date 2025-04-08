package com.example.newsfeed.board.service;

import com.example.newsfeed.board.dto.BoardRequestDto;
import com.example.newsfeed.board.dto.BoardResponseDto;
import com.example.newsfeed.board.entity.Board;
import com.example.newsfeed.board.repository.BoardRepository;
import com.example.newsfeed.exception.CustomException;
import com.example.newsfeed.exception.ErrorCode;
import com.example.newsfeed.user.entity.User;
import com.example.newsfeed.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public BoardResponseDto save(BoardRequestDto dto) {
        User user = userRepository.findById(dto.getUserid()).orElseThrow(
                () -> new CustomException(ErrorCode.USER_NOT_FOUND)
        );
        Board board = new Board(dto.getContents());
        board.setUser(user);

        Board saveboard = boardRepository.save(board);

        return new BoardResponseDto(saveboard.getId(),
                saveboard.getUser().getId(),
                saveboard.getContents(),
                saveboard.getCreatedAt(),
                saveboard.getUpdatedAt());
    }
}
