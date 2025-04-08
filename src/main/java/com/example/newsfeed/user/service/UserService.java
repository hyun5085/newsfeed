package com.example.newsfeed.user.service;

import com.example.newsfeed.exception.CustomException;
import com.example.newsfeed.exception.ErrorCode;
import com.example.newsfeed.user.dto.response.SignUpResponseDto;
import com.example.newsfeed.user.dto.response.UserResponseDto;
import com.example.newsfeed.user.entity.User;
import com.example.newsfeed.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public SignUpResponseDto signUp(String username, String password, String email, LocalDate birthday, String hobby) {
        User user = new User(username, password, email, birthday, hobby);
        User saveUser = userRepository.save(user);
        return new SignUpResponseDto(saveUser.getId(), saveUser.getUsername(), saveUser.getEmail(), saveUser.getBirthday(), saveUser.getHobby());
    }

    @Transactional
    public UserResponseDto updateUser(Long id, String username, String email, String password, LocalDate birthday, String hobby, Long userId) {
        User findUser = userRepository.findById(id).orElseThrow(() ->
                new CustomException(ErrorCode.USER_NOT_FOUND));
        if (!findUser.getId().equals(userId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "작성자 정보가 일치하지 않습니다.");
        }

        if (!findUser.getPassword().equals(password)) {
            throw new CustomException(ErrorCode.INVALID_PASSWORD);
        }
        findUser.updateUser(username, email, password, birthday, hobby);
        return new UserResponseDto(findUser);
    }

    @Transactional
    public UserResponseDto updatePassword(Long id, String oldPassword, String newPassword, Long userId) {
        User findUser = userRepository.findById(id).orElseThrow(() ->
                new CustomException(ErrorCode.USER_NOT_FOUND));
//        if (!findUser.getId().equals(userId)) {
//            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "작성자 정보가 일치하지 않습니다.");
//        }
        if (!findUser.getPassword().equals(oldPassword)){
            throw new CustomException(ErrorCode.INVALID_PASSWORD);
        }
        findUser.updatePassword(newPassword);
        return new UserResponseDto(findUser);
    }

    public void delete(Long id, String password, Long userId) {
        User findUser = userRepository.findById(id).orElseThrow(() ->
                new CustomException(ErrorCode.USER_NOT_FOUND));
//        if (!findUser.getId().equals(userId)) {
//            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "작성자 정보가 일치하지 않습니다.");
//        }
        if (!findUser.getPassword().equals(password)) {
            throw new CustomException(ErrorCode.INVALID_PASSWORD);
        }
        userRepository.delete(findUser);
    }
}
