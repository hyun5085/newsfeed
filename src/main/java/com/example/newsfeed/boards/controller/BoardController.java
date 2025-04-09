package com.example.newsfeed.boards.controller;

import com.example.newsfeed.boards.dto.BoardResponseDto;
import com.example.newsfeed.boards.dto.CreateBoardRequestDto;
import com.example.newsfeed.boards.dto.DetailBoardResponseDto;
import com.example.newsfeed.boards.dto.UpdateBoardRequestDto;
import com.example.newsfeed.boards.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

//    @PostMapping("/boards")
//    public ResponseEntity<BoardResponseDto> createBoard(@RequestBody CreateBoardRequestDto createBoardRequestDto) {
//
//        BoardResponseDto boardResponseDto = boardService.create(createBoardRequestDto.getContents(), createBoardRequestDto.getUsername());
//
//        return new ResponseEntity<>(boardResponseDto, HttpStatus.CREATED);
//    }

//    @PostMapping("/boards/like")
//    public ResponseEntity<BoardLikeResponseDto> createLike(@RequestBody CreateBoardLikeRequestDto createBoardLikeRequestDto) {
//
//    }

    @GetMapping("/boards")
    public ResponseEntity<List<BoardResponseDto>> findAll() {

        List<BoardResponseDto> boardResponseDto = boardService.findAll();

        return new ResponseEntity<>(boardResponseDto, HttpStatus.OK);
    }


//    @GetMapping("/boards/{id}")
//    public ResponseEntity<DetailBoardResponseDto> findById(@PathVariable Long id) {
//        DetailBoardResponseDto detailBoardResponseDto = boardService.findById(id);
//
//        return new ResponseEntity<>(detailBoardResponseDto, HttpStatus.OK);
//    }


    @PatchMapping("/boards/{id}")
    public ResponseEntity<Void> updateBoard(@PathVariable Long id, @RequestBody UpdateBoardRequestDto updateBoardRequestDto) {

        boardService.updateBoard(id, updateBoardRequestDto.getContents());

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("/boards/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable Long id) {

        boardService.deleteBoard(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
