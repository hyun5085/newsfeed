package com.example.newsfeed.follow.entitiy;

import com.example.newsfeed.exception.CustomException;
import com.example.newsfeed.exception.ErrorCode;
import com.example.newsfeed.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "follows", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"follower_id", "followed_id"})
})
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower_id", nullable = false)
    private User follower;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "followed_id", nullable = false)
    private User followed;

    protected Follow() {}

    public Follow(User follower, User followed) {
        if (follower == followed) {
            throw new CustomException(ErrorCode.FOLLOW_LOGIN_USER);
        }

        this.follower = follower;
        this.followed = followed;
    }

    public Follow(User followed) {
        this.followed = followed;
    }
}
