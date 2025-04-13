package com.example.newsfeed.domain.board.controller;

import com.example.newsfeed.domain.board.dto.request.CreateBoardRequestDto;
import com.example.newsfeed.domain.board.dto.request.UpdateBoardRequestDto;
import com.example.newsfeed.domain.board.dto.response.DetailBoardResponseDto;
import com.example.newsfeed.domain.board.dto.response.FeedResponseDto;
import com.example.newsfeed.domain.board.service.BoardService;
import com.example.newsfeed.common.util.JwtUtil;
import com.example.newsfeed.common.exception.CustomException;
import com.example.newsfeed.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<DetailBoardResponseDto> createBoard(@RequestBody CreateBoardRequestDto createBoardRequestDto,
                                                              @RequestHeader("Authorization") String authorizationHeader
    ) {
        // Authorization 헤더에서 JWT 토큰을 추출 (Bearer 방식)
        String token = authorizationHeader.substring(7); // "Bearer " 제거

        // 토큰 검증
        if (!jwtUtil.validateToken(token)) {
            throw new CustomException(ErrorCode.UNAUTHORIZED);
        }
        Long userId = jwtUtil.extractUserId(token); // JWT에서 사용자 ID 추출

        DetailBoardResponseDto detailBoardResponseDto = boardService.create(createBoardRequestDto.getContents(), userId);

        return new ResponseEntity<>(detailBoardResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<FeedResponseDto>> findAll() {

        List<FeedResponseDto> feedResponseDto = boardService.findAll();

        return new ResponseEntity<>(feedResponseDto, HttpStatus.OK);
    }

    @GetMapping("/limit/{pagination}")
    public ResponseEntity<List<FeedResponseDto>> findBoardAll(@PathVariable int pagination
    ) {
        int limit = 10 * pagination;

        List<FeedResponseDto> feedResponseDto = boardService.findBoardAll(limit);

        return new ResponseEntity<>(feedResponseDto, HttpStatus.OK);
    }

    @GetMapping("/follower/{pagination}")
    public ResponseEntity<List<FeedResponseDto>> findBoardFollowerAll(@PathVariable int pagination, @RequestHeader("Authorization") String authorizationHeader
    ) {

        // Authorization 헤더에서 JWT 토큰을 추출 (Bearer 방식)
        String token = authorizationHeader.substring(7); // "Bearer " 제거

        // 토큰 검증
        if (!jwtUtil.validateToken(token)) {
            throw new CustomException(ErrorCode.UNAUTHORIZED);
        }
        Long userId = jwtUtil.extractUserId(token); // JWT에서 사용자 ID 추출

        int limit = 10 * pagination;

        List<FeedResponseDto> feedResponseDto = boardService.findBoardFollowerAll(userId, limit);

        return new ResponseEntity<>(feedResponseDto, HttpStatus.OK);
    }



    @GetMapping("/{id}")
    public ResponseEntity<DetailBoardResponseDto> findById(@PathVariable Long id) {
        DetailBoardResponseDto detailBoardResponseDto = boardService.findById(id);

        return new ResponseEntity<>(detailBoardResponseDto, HttpStatus.OK);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<DetailBoardResponseDto> updateBoard(@PathVariable Long id, @RequestBody UpdateBoardRequestDto updateBoardRequestDto,
                                            @RequestHeader("Authorization") String authorizationHeader
    ) {
        // Authorization 헤더에서 JWT 토큰을 추출 (Bearer 방식)
        String token = authorizationHeader.substring(7); // "Bearer " 제거

        // 토큰 검증
        if (!jwtUtil.validateToken(token)) {
            throw new CustomException(ErrorCode.UNAUTHORIZED);
        }

        Long userId = jwtUtil.extractUserId(token); // JWT에서 사용자 ID 추출

        DetailBoardResponseDto detailBoardResponseDto = boardService.updateBoard(id, updateBoardRequestDto.getContents(), userId);

        return new ResponseEntity<>(detailBoardResponseDto, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable Long id,
                                            @RequestHeader("Authorization") String authorizationHeader) {

        // Authorization 헤더에서 JWT 토큰을 추출 (Bearer 방식)
        String token = authorizationHeader.substring(7); // "Bearer " 제거

        // 토큰 검증
        if (!jwtUtil.validateToken(token)) {
            throw new CustomException(ErrorCode.UNAUTHORIZED);

        }

        Long userId = jwtUtil.extractUserId(token); // JWT에서 사용자 ID 추출

        boardService.deleteBoard(id, userId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
