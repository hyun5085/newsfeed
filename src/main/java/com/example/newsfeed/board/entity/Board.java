package com.example.newsfeed.board.entity;

import com.example.newsfeed.user.entity.BaseEntity;
import com.example.newsfeed.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "boards")
@Getter
public class Board extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contents;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    public Board(){}

    public Board(String contents){
        this.contents = contents;
    }

    public void setUser(User user){
        this.user = user;
    }
}
