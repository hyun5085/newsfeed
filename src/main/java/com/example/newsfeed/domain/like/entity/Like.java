package com.example.newsfeed.domain.like.entity;

import com.example.newsfeed.domain.comment.entity.Comment;
import com.example.newsfeed.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "likes",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "Like_UNIQUE",
                        columnNames = {"comment_id", "user_id"}
                )})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id", nullable = false)
    private Comment comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Like(Comment comment, User user) {
        this.comment = comment;
        this.user = user;
    }

}
