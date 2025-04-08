package com.example.newsfeed.cookiesession.service;

import com.example.newsfeed.user.entity.User;

public interface LoginService {

    User login(String email, String password);

}
