package com.example.newsfeed.cookiesession.service;

import com.example.newsfeed.user.entity.User;

public interface LoginService {

    User login(String email, String password);

    // 이메일로 사용자 조회하는 메서드 추가
    User getUserByEmail(String email);

}
