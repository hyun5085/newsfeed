package com.example.newsfeed.boards.controller;

import com.example.newsfeed.boards.dto.BoardResponseDto;
import com.example.newsfeed.boards.dto.CreateBoardRequestDto;
import com.example.newsfeed.boards.dto.DetailBoardResponseDto;
import com.example.newsfeed.boards.dto.UpdateBoardRequestDto;
import com.example.newsfeed.boards.entity.Board;
import com.example.newsfeed.boards.service.BoardService;
import com.example.newsfeed.common.Const;
import com.example.newsfeed.cookiesession.dto.LoginResponseDto;
import com.example.newsfeed.cookiesession.util.JwtUtil;
import com.example.newsfeed.exception.CustomException;
import com.example.newsfeed.exception.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final JwtUtil jwtUtil;

    @PostMapping("/boards")
    public ResponseEntity<BoardResponseDto> createBoard(@RequestBody CreateBoardRequestDto createBoardRequestDto,
                                                        @RequestHeader("Authorization") String authorizationHeader
//                                                        HttpSession session
    ) {

        // Authorization 헤더에서 JWT 토큰을 추출 (Bearer 방식)
        String token = authorizationHeader.substring(7); // "Bearer " 제거

        // 토큰 검증
        if (!jwtUtil.validateToken(token)) {
            throw new CustomException(ErrorCode.UNAUTHORIZED);
        }


//        LoginResponseDto loginResponseDto = (LoginResponseDto) session.getAttribute(Const.LOGIN_USER);

//        if(loginResponseDto == null) {
//            throw new CustomException(ErrorCode.UNAUTHORIZED);
//        }
//
//        if(loginResponseDto.getId().equals(createBoardRequestDto.getUserId())) {
//            throw new CustomException(ErrorCode.BOARD_CREATE_UNAUTHORIZED);
//        }
        Long userId = jwtUtil.extractUserId(token); // JWT에서 사용자 ID 추출

        BoardResponseDto boardResponseDto = boardService.create(createBoardRequestDto.getContents(), userId);

        return new ResponseEntity<>(boardResponseDto, HttpStatus.CREATED);
    }

//    @PostMapping("/boards/like")
//    public ResponseEntity<BoardLikeResponseDto> createLike(@RequestBody CreateBoardLikeRequestDto createBoardLikeRequestDto) {
//
//    }

    @GetMapping("/boards")
    public ResponseEntity<List<BoardResponseDto>> findAll() {

        List<BoardResponseDto> boardResponseDto = boardService.findAll();

        return new ResponseEntity<>(boardResponseDto, HttpStatus.OK);
    }

//    @GetMapping("/boards/pagination")
//    public ResponseEntity<List<BoardResponseDto>> findTopAll(@RequestParam int page, @RequestParam int size) {
//
//        List<BoardResponseDto> boardResponseDto = boardService.findTopAll(page, size);
//
//        return new ResponseEntity<>(boardResponseDto, HttpStatus.OK);
//    }



    @GetMapping("/boards/{id}")
    public ResponseEntity<DetailBoardResponseDto> findById(@PathVariable Long id) {
        DetailBoardResponseDto detailBoardResponseDto = boardService.findById(id);

        return new ResponseEntity<>(detailBoardResponseDto, HttpStatus.OK);
    }


    @PatchMapping("/boards/{id}")
    public ResponseEntity<Void> updateBoard(@PathVariable Long id, @RequestBody UpdateBoardRequestDto updateBoardRequestDto,
                                            @RequestHeader("Authorization") String authorizationHeader
    ) {
        // Authorization 헤더에서 JWT 토큰을 추출 (Bearer 방식)
        String token = authorizationHeader.substring(7); // "Bearer " 제거

        // 토큰 검증
        if (!jwtUtil.validateToken(token)) {
            throw new CustomException(ErrorCode.UNAUTHORIZED);
        }

        Long userId = jwtUtil.extractUserId(token); // JWT에서 사용자 ID 추출


//        LoginResponseDto loginResponseDto = (LoginResponseDto) session.getAttribute(Const.LOGIN_USER);

//        if(loginResponseDto == null) {
//            throw new CustomException(ErrorCode.UNAUTHORIZED);
//        }

        boardService.updateBoard(id, updateBoardRequestDto.getContents(), userId);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("/boards/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable Long id,
                                            @RequestHeader("Authorization") String authorizationHeader) {

        // Authorization 헤더에서 JWT 토큰을 추출 (Bearer 방식)
        String token = authorizationHeader.substring(7); // "Bearer " 제거

        // 토큰 검증
        if (!jwtUtil.validateToken(token)) {
            throw new CustomException(ErrorCode.UNAUTHORIZED);

        }

        Long userId = jwtUtil.extractUserId(token); // JWT에서 사용자 ID 추출
//        LoginResponseDto loginResponseDto = (LoginResponseDto) session.getAttribute(Const.LOGIN_USER);

//        if(loginResponseDto == null) {
//            throw new CustomException(ErrorCode.UNAUTHORIZED);
//        }

        boardService.deleteBoard(id, userId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
