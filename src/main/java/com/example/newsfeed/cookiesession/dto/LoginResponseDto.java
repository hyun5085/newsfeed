package com.example.newsfeed.cookiesession.dto;

import lombok.Getter;

@Getter
public class LoginResponseDto {

    private String userName;   // 유저 이름
    private String userEmail;  // 작성자 Email

//    public LoginResponseDto(Consummer consummer) {
//        this.userName = user.getUserName();
//        this.userEmail = user.getUserEmail();
//    }

    public LoginResponseDto(String userName, String userEmail) {
        this.userName = userName;
        this.userEmail = userEmail;
    }

}
