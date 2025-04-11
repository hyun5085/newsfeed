package com.example.newsfeed.domain.board.entity;

//import com.example.newsfeed.users.entity.User;
import com.example.newsfeed.common.entity.BaseEntity;
import com.example.newsfeed.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name="boards")
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "longtext")
    private String contents;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Board(String contents) {
        this.contents = contents;
    }

    public Board() {

    }

    public void setUser(User user) {
        this.user = user;
    }

    public void updateBoard(Long id, String contents) {
        this.id = id;
        this.contents = contents;
    }
}
