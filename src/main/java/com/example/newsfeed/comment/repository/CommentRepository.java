package com.example.newsfeed.comment.repository;

import com.example.newsfeed.comment.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment>  findByBoardIdOrderByUpdatedAtDesc(Long boardId);

   // Comment findByBoardId(Long id);

    Page<Comment> findAllByBoard_Id(Long id, Pageable pageable);


}
