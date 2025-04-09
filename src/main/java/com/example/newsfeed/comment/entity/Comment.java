package com.example.newsfeed.comment.entity;

import com.example.newsfeed.boards.entity.Board;
import com.example.newsfeed.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;


@Entity
@Table(name = "comments")
@Getter
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contents;

    // User 테이블과 연결
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Board 테이블과 연결
    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    public Comment() {
    }

    public Comment(User user, Board board, String contents) {
        this.user = user;
        this.board = board;
        this.contents = contents;
    }

    // 댓글 수정
    public void update(String contents) {
        this.contents = contents;
    }
}
