package com.example.newsfeed.domain.auth.service;

import com.example.newsfeed.domain.user.entity.User;

public interface LoginService {

    User login(String email, String password);

    // 이메일로 사용자 조회하는 메서드 추가
    User getUserByEmail(String email);

}
