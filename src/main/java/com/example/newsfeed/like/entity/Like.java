package com.example.newsfeed.like.entity;

import com.example.newsfeed.comment.entity.Comment;
import com.example.newsfeed.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "likes",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "Like_UNIQUE",
                        columnNames = {"comment_id", "user_id"}
                )})
@Getter
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Like() {
    }

    public Like(Comment comment, User user) {
        this.comment = comment;
        this.user = user;
    }

}
