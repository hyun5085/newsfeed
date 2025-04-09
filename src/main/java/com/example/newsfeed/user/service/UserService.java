package com.example.newsfeed.user.service;

import com.example.newsfeed.exception.CustomException;
import com.example.newsfeed.exception.ErrorCode;
import com.example.newsfeed.user.dto.request.DeleteUserRequestDto;
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

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RetiredEmailRepository retiredEmailRepository;

    public SignUpResponseDto signUp(String username, String password, String email, LocalDate birthday, String hobby) {

        // 🔒 이미 탈퇴한 이메일인지 확인
        if (retiredEmailRepository.existsByEmail(email)) {
            throw new CustomException(ErrorCode.EMAIL_ALREADY_RETIRED);
        }

        // 🔒 기존 회원인지 확인
        if (userRepository.existsByEmail(email)) {
            throw new CustomException(ErrorCode.EMAIL_DUPLICATED);
        }

        User user = new User(username, password, email, birthday, hobby);
        User saveUser = userRepository.save(user);
        return new SignUpResponseDto(saveUser.getId(), saveUser.getUsername(), saveUser.getEmail(), saveUser.getBirthday(), saveUser.getHobby());
    }

    public UserResponseDto findById(Long id) {
        User findUser = userRepository.findById(id).orElseThrow(() ->
                new CustomException(ErrorCode.USER_NOT_FOUND));
        return new UserResponseDto(findUser);
    }

    @Transactional
    public UserResponseDto updateUser(UpdateUserRequestDto requestDto, Long loginUserId) {
        User findUser = userRepository.findById(loginUserId).orElseThrow(() ->
                new CustomException(ErrorCode.USER_NOT_FOUND));
        if (!findUser.getPassword().equals(requestDto.getPassword())) {
            throw new CustomException(ErrorCode.INVALID_PASSWORD);
        }
        findUser.updateUser(requestDto.getUsername(), requestDto.getEmail(), requestDto.getPassword(), requestDto.getBirthday(), requestDto.getHobby());
        return new UserResponseDto(findUser);
    }

    @Transactional
    public UserResponseDto updatePassword(UpdatePasswordRequestDto requestDto, Long loginUserId) {
        User findUser = userRepository.findById(loginUserId).orElseThrow(() ->
                new CustomException(ErrorCode.USER_NOT_FOUND));
        if (!findUser.getPassword().equals(requestDto.getOldPassword())){
            throw new CustomException(ErrorCode.INVALID_PASSWORD);
        }
        findUser.updatePassword(requestDto.getNewPassword());
        return new UserResponseDto(findUser);
    }

    public void delete(DeleteUserRequestDto requestDto, Long loginUserId) {
        User findUser = userRepository.findById(loginUserId).orElseThrow(() ->
                new CustomException(ErrorCode.USER_NOT_FOUND));
        if (!findUser.getPassword().equals(requestDto.getPassword())) {
            throw new CustomException(ErrorCode.INVALID_PASSWORD);
        }

        // ✅ 탈퇴 이메일 저장
        RetiredEmail retiredEmail = new RetiredEmail(findUser.getEmail());
        retiredEmailRepository.save(retiredEmail);

        userRepository.delete(findUser);
    }

}
