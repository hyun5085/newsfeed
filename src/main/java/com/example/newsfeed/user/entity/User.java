package com.example.newsfeed.user.entity;

import com.example.newsfeed.user.security.PasswordConverter;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    @Convert(converter = PasswordConverter.class)
    private String password;

    @Column
    private LocalDate birthday;

    @Column
    private String hobby;

    public User() {

    }

    public User(String username, String password, String email, LocalDate birthday, String hobby) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.birthday = birthday;
        this.hobby = hobby;
    }

    public void updateUser(String username, String email, String password, LocalDate birthday, String hobby) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.hobby = hobby;
    }

    public void updatePassword(String password) {
        this.password = password;
    }
}
