package com.example.newsfeed.domain.board.repository;

import com.example.newsfeed.domain.board.entity.Board;
import com.example.newsfeed.common.exception.CustomException;
import com.example.newsfeed.common.exception.ErrorCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query(value = "select id, contents, user_id, created_at, updated_at from newsfeed.boards as board order by created_at desc limit 0, ?", nativeQuery = true)
    List<Board> findBoardAll(int limit);

    @Query(value = "SELECT b.id, b.contents, b.user_id, b.created_at, b.updated_at FROM boards b JOIN follows f ON b.user_id = f.followed_id WHERE f.follower_id = ? ORDER BY b.created_at DESC LIMIT 0, ?", nativeQuery = true)
    List<Board> findBoardFollowerAll(Long userId, int limit);

    default Board findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new CustomException(ErrorCode.BOARD_NOT_FOUND));
    }

}
