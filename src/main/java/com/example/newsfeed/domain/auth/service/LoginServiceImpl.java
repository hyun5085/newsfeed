package com.example.newsfeed.domain.auth.service;

import com.example.newsfeed.domain.auth.repository.AuthRepository;
import com.example.newsfeed.common.exception.CustomException;
import com.example.newsfeed.common.exception.ErrorCode;
import com.example.newsfeed.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final AuthRepository authRepository;

    // 로그인 기능 추가
    // 주어진 이메일과 비밀번호로 소비자 로그인 처리
    // 요청 데이터 형식: String (이메일, 비밀번호)
    // 반환 데이터 형식: User (로그인된 소비자 객체)
    public User login(String email, String password) {

        // 이메일로 소비자 조회 (이메일이 없으면 예외 처리)
        User user = authRepository.findUserByEmail(email)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        // 비밀번호가 일치하는지 확인
        if (!user.getPassword().equals(password)) {

            // 비밀번호가 일치하지 않으면 예외 처리
            throw new CustomException(ErrorCode.INVALID_PASSWORD);
        }

        // 로그인 성공 시 유저 객체 반환
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        return authRepository.findUserByEmail(email)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }

}
