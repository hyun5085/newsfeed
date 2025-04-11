package com.example.newsfeed.boards.repository;

import com.example.newsfeed.boards.dto.BoardResponseDto;
import com.example.newsfeed.boards.entity.Board;
import com.example.newsfeed.exception.CustomException;
import com.example.newsfeed.exception.ErrorCode;
import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.awt.print.Pageable;
import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query(value = "select id, contents, user_id, created_at, updated_at from newsfeed.boards as board order by created_at desc limit 0, ?", nativeQuery = true)
    List<Board> findBoardAll(int limit);

    @Query(value = "select b.id, b.contents, b.user_id, b.created_at, b.updated_at from newsfeed.boards as b left join newsfeed.follows as f on b.user_id = f.followed_id order by created_at desc limit 0, ?", nativeQuery = true)
    List<Board> findBoardFollowerAll(int limit);

    default Board findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new CustomException(ErrorCode.BOARD_NOT_FOUND));
    }

}
