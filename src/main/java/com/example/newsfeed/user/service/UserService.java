package com.example.newsfeed.user.service;

import com.example.newsfeed.exception.CustomException;
import com.example.newsfeed.exception.ErrorCode;
import com.example.newsfeed.user.dto.request.DeleteUserRequestDto;
import com.example.newsfeed.user.dto.request.SignUpRequestDto;
import com.example.newsfeed.user.dto.request.UpdatePasswordRequestDto;
import com.example.newsfeed.user.dto.request.UpdateUserRequestDto;
import com.example.newsfeed.user.dto.response.SignUpResponseDto;
import com.example.newsfeed.user.dto.response.UserResponseDto;
import com.example.newsfeed.user.entity.RetiredEmail;
import com.example.newsfeed.user.entity.User;
import com.example.newsfeed.user.repository.RetiredEmailRepository;
import com.example.newsfeed.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RetiredEmailRepository retiredEmailRepository;

    public SignUpResponseDto signUp(SignUpRequestDto requestDto) {

        // 🔒 이미 탈퇴한 이메일인지 확인
        if (retiredEmailRepository.existsByEmail(requestDto.getEmail())) {
            throw new CustomException(ErrorCode.EMAIL_ALREADY_RETIRED);
        }

        // 🔒 기존 회원인지 확인
        if (userRepository.existsByEmail(requestDto.getEmail())) {
            throw new CustomException(ErrorCode.EMAIL_DUPLICATED);
        }

        User user = new User(requestDto);
        User saveUser = userRepository.save(user);
        return SignUpResponseDto.from(saveUser);
    }

    public UserResponseDto findById(Long id) {
        User findUser = findUserById(id);
        return new UserResponseDto(findUser);
    }

    @Transactional
    public UserResponseDto updateUser(Long id, UpdateUserRequestDto requestDto, Long loginUserId) {
        User findUser = findUserById(loginUserId);
        findUser.updateUser(id, requestDto);
        return new UserResponseDto(findUser);
    }

    @Transactional
    public UserResponseDto updatePassword(Long id, UpdatePasswordRequestDto requestDto, Long loginUserId) {
        User findUser = findUserById(loginUserId);
        findUser.updatePassword(id, requestDto);
        return new UserResponseDto(findUser);
    }

    public void delete(Long id, DeleteUserRequestDto requestDto, Long loginUserId) {
        User findUser = findUserById(loginUserId);
        // ✅ 탈퇴 이메일 저장
        RetiredEmail retiredEmail = new RetiredEmail(findUser.getEmail());
        retiredEmailRepository.save(retiredEmail);
        findUser.validatePassword(id, requestDto.getPassword());
        userRepository.delete(findUser);
    }

    private User findUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }
}
