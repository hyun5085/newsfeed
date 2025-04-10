package com.example.newsfeed.boards.service;

import com.example.newsfeed.boards.dto.BoardResponseDto;
import com.example.newsfeed.boards.dto.DetailBoardResponseDto;
import com.example.newsfeed.boards.entity.Board;
import com.example.newsfeed.boards.repository.BoardRepository;
//import com.example.newsfeed.users.entity.User;
//import com.example.newsfeed.users.repository.UserRepository;
import com.example.newsfeed.exception.CustomException;
import com.example.newsfeed.exception.ErrorCode;
import com.example.newsfeed.user.entity.User;
import com.example.newsfeed.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public BoardResponseDto create(String contents, Long userId) {

        User findUser = userRepository.findById(userId).orElseThrow();

        Board board = new Board(contents);
        board.setUser(findUser);

        Board createBoard = boardRepository.save(board);

        return new BoardResponseDto(createBoard.getId(), createBoard.getContents());
    }

    public List<BoardResponseDto> findAll() {
        return boardRepository.findAll().stream().map(BoardResponseDto::toDto).toList();
    }

    public DetailBoardResponseDto findById(Long id) {
        Board findBoard = boardRepository.findByIdOrElseThrow(id);
        User writer = findBoard.getUser();

        return new DetailBoardResponseDto(findBoard.getId(), findBoard.getContents(), writer.getUsername(), findBoard.getCreatedAt(), findBoard.getUpdatedAt());
    }

    @Transactional
    public void updateBoard(Long id, String contents, Long userId) {

        Board findBoard = boardRepository.findByIdOrElseThrow(id);

        if(!findBoard.getUser().getId().equals(userId)) {
            throw new CustomException(ErrorCode.BOARD_UPDATE_UNAUTHORIZED);
        }

        findBoard.updateBoard(id, contents);

    }

    public void deleteBoard(Long id, Long userId) {
        Board findBoard = boardRepository.findByIdOrElseThrow(id);

        if(!findBoard.getUser().getId().equals(userId)) {
            throw new CustomException(ErrorCode.BOARD_DELETE_UNAUTHORIZED);
        }

        boardRepository.delete(findBoard);
    }

}
