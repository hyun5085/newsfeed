package com.example.newsfeed.domain.board.service;

import com.example.newsfeed.domain.board.dto.response.DetailBoardResponseDto;
import com.example.newsfeed.domain.board.dto.response.FeedResponseDto;
import com.example.newsfeed.domain.board.entity.Board;
import com.example.newsfeed.domain.board.repository.BoardRepository;
//import com.example.newsfeed.users.entity.User;
//import com.example.newsfeed.users.repository.UserRepository;
import com.example.newsfeed.common.exception.CustomException;
import com.example.newsfeed.common.exception.ErrorCode;
import com.example.newsfeed.domain.user.entity.User;
import com.example.newsfeed.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public DetailBoardResponseDto create(String contents, Long userId) {

        User findUser = userRepository.findByIdOrElseThrow(userId);


        Board board = new Board(contents);
        board.setUser(findUser);

        Board createBoard = boardRepository.save(board);

        return new DetailBoardResponseDto(createBoard.getId(), createBoard.getContents(), createBoard.getUser().getId(), createBoard.getUser().getUsername(), createBoard.getCreatedAt(), createBoard.getUpdatedAt());
    }

    public List<FeedResponseDto> findAll() {
        return boardRepository.findAll().stream().map(FeedResponseDto::toDto).toList();
    }

    public List<FeedResponseDto> findBoardAll(int limit) {

        return boardRepository.findBoardAll(limit).stream().map(FeedResponseDto::toDto).toList();
    }

    public List<FeedResponseDto> findBoardFollowerAll(int limit) {

        return boardRepository.findBoardFollowerAll(limit).stream().map(FeedResponseDto::toDto).toList();
    }

    public DetailBoardResponseDto findById(Long id) {
        Board findBoard = boardRepository.findByIdOrElseThrow(id);
        User writer = findBoard.getUser();

        return new DetailBoardResponseDto(findBoard.getId(), findBoard.getContents(), writer.getId(), writer.getUsername(), findBoard.getCreatedAt(), findBoard.getUpdatedAt());
    }

    @Transactional
    public DetailBoardResponseDto updateBoard(Long id, String contents, Long userId) {

        Board findBoard = boardRepository.findByIdOrElseThrow(id);
        User findUser = userRepository.findById(userId).orElseThrow();

        if(!findBoard.getUser().getId().equals(userId)) {
            throw new CustomException(ErrorCode.BOARD_UPDATE_UNAUTHORIZED);
        }

        findBoard.updateBoard(id, contents);

        return new DetailBoardResponseDto(id, contents, findUser.getId(), findBoard.getUser().getUsername(), findBoard.getCreatedAt(), findBoard.getUpdatedAt());
    }

    public void deleteBoard(Long id, Long userId) {
        Board findBoard = boardRepository.findByIdOrElseThrow(id);

        if(!findBoard.getUser().getId().equals(userId)) {
            throw new CustomException(ErrorCode.BOARD_DELETE_UNAUTHORIZED);
        }

        boardRepository.delete(findBoard);
    }

}
