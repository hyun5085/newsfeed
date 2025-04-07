package com.example.newsfeed.user.service;

import com.example.newsfeed.user.dto.SignUpResponseDto;
import com.example.newsfeed.user.dto.UserResponseDto;
import com.example.newsfeed.user.entity.User;
import com.example.newsfeed.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public SignUpResponseDto signUp(String username, String password, String email) {
        User user = new User(username, password, email);
        User saveUser = userRepository.save(user);
        return new SignUpResponseDto(saveUser.getId(), saveUser.getUsername(), saveUser.getEmail());
    }

    @Transactional
    public UserResponseDto updateUser(Long id, String username, String email, String password) {
        User findUser = userRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Does not exist id : " + id));
        if (!findUser.getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }
        findUser.updateUser(username, email, password);
        return new UserResponseDto(findUser);
    }

    public UserResponseDto updatePassword(Long id, String oldPassword, String newPassword) {
        User findUser = userRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Does not exist id : " + id));

        if (!findUser.getPassword().equals(oldPassword)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }
        findUser.updatePassword(newPassword);
        return new UserResponseDto(findUser);
    }

    public void delete(Long id, String password) {
        User findUser = userRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Does not exist id : " + id));
        if (!findUser.getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }
        userRepository.delete(findUser);
    }
}
