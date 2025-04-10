package com.example.newsfeed.boards.repository;

import com.example.newsfeed.boards.dto.BoardResponseDto;
import com.example.newsfeed.boards.entity.Board;
import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.awt.print.Pageable;
import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

//    @Query(value = "select contents from boards as board where user_id = ? order by updated_at desc limit 0 offset ?")
//    List<BoardResponseDto> findBoardAll(Long userId, int limit);

    default Board findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, id + " 번의 게시글을 찾을 수 없습니다."));
    }

}
