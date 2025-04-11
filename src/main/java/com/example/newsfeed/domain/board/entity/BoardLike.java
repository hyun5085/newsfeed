package com.example.newsfeed.domain.board.entity;

import com.example.newsfeed.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "board_like")
public class BoardLike extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

}
