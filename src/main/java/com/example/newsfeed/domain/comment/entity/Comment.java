package com.example.newsfeed.domain.comment.entity;

import com.example.newsfeed.common.entity.BaseEntity;
import com.example.newsfeed.domain.board.entity.Board;
import com.example.newsfeed.domain.user.entity.User;
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

    @Column(nullable = false)
    private String contents;

    // 댓글 작성자 (User)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // 댓글이 달린 게시글 (Board)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    // 댓글 생성자
    public Comment(User user, Board board, String contents) {
        this.user = user;
        this.board = board;
        this.contents = contents;
    }

    // 댓글 내용 수정
    public void update(String contents) {
        this.contents = contents;
    }
}
