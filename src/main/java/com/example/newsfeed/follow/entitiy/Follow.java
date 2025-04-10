package com.example.newsfeed.follow.entitiy;

import com.example.newsfeed.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "follows")
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "follower_email")
    private User follower;

    @ManyToOne
    @JoinColumn(name = "following_email")
    private User followed;

    public Follow() {
    }

    public Follow(User follower, User followed) {
        this.follower = follower;
        this.followed = followed;
    }

}
