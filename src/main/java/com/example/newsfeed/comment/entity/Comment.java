package com.example.newsfeed.comment.entity;

import com.example.newsfeed.boards.entity.Board;
import com.example.newsfeed.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "comments")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 생성자의 엑세스 레벨 지정
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contents;

    // User 테이블과 연결
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // Board 테이블과 연결
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

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
