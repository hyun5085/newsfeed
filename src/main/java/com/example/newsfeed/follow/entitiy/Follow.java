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
    @JoinColumn(name = "follower_id")
    private User follower;

    @ManyToOne
    @JoinColumn(name = "following_id")
    private User following;

    public Follow() {
    }

    public Follow(User follower, User following) {
        this.follower = follower;
        this.following = following;
    }
}
