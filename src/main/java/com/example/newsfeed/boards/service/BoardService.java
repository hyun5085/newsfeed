package com.example.newsfeed.boards.service;

import com.example.newsfeed.boards.dto.BoardResponseDto;
import com.example.newsfeed.boards.dto.DetailBoardResponseDto;
import com.example.newsfeed.boards.entity.Board;
import com.example.newsfeed.boards.repository.BoardRepository;
//import com.example.newsfeed.users.entity.User;
//import com.example.newsfeed.users.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
//    private final UserRepository userRepository;
//
//    public BoardResponseDto create(String contents, String username) {
//
//        User findUser = userRepository.findUserByUsernameOrElseThrow(username);
//
//        Board board = new Board(contents);
//        board.setUser(findUser);
//
//        Board createBoard = boardRepository.save(board);
//
//        return new BoardResponseDto(createBoard.getId(), createBoard.getContents(), createBoard.getCreatedAt(), createBoard.getUpdatedAt());
//    }

    public List<BoardResponseDto> findAll() {
        return boardRepository.findAll().stream().map(BoardResponseDto::toDto).toList();
    }

//    public DetailBoardResponseDto findById(Long id) {
//        Board findBoard = boardRepository.findByIdOrElseThrow(id);
//        User writer = findBoard.getUser();
//
//        return new DetailBoardResponseDto(findBoard.getId(), findBoard.getContents(), writer.getUsername(), findBoard.getCreatedAt(), findBoard.getUpdatedAt());
//    }

    @Transactional
    public void updateBoard(Long id, String contents) {

        Board findBoard = boardRepository.findByIdOrElseThrow(id);

        findBoard.updateBoard(id, contents);

    }

    public void deleteBoard(Long id) {
        Board findBoard = boardRepository.findByIdOrElseThrow(id);
        boardRepository.delete(findBoard);
    }

}
