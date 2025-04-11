package com.example.newsfeed.domain.user.service;

import com.example.newsfeed.common.exception.CustomException;
import com.example.newsfeed.common.exception.ErrorCode;
import com.example.newsfeed.domain.user.dto.request.DeleteUserRequestDto;
import com.example.newsfeed.domain.user.dto.request.SignUpRequestDto;
import com.example.newsfeed.domain.user.dto.request.UpdatePasswordRequestDto;
import com.example.newsfeed.domain.user.dto.request.UpdateUserRequestDto;
import com.example.newsfeed.domain.user.dto.response.SignUpResponseDto;
import com.example.newsfeed.domain.user.dto.response.UserResponseDto;
import com.example.newsfeed.domain.user.entity.RetiredEmail;
import com.example.newsfeed.domain.user.entity.User;
import com.example.newsfeed.domain.user.repository.RetiredEmailRepository;
import com.example.newsfeed.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


/**
 * 회원 서비스
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RetiredEmailRepository retiredEmailRepository;

    /**
     * 회원가입
     *
     * @param requestDto the request dto
     * @return the signup response dto
     */
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

    /**
     * 회원 식별번호 조회
     *
     * @param id the id
     * @return the user response dto
     */
    public UserResponseDto findById(Long id) {
        User findUser = userRepository.findByIdOrElseThrow(id);
        return new UserResponseDto(findUser, "팔로우 되었습니다.");
    }

    /**
     * 회원 정보 변경
     *
     * @param id          the id
     * @param requestDto  the request dto
     * @param loginUserId the login user id
     * @return the user response dto
     */
    @Transactional
    public UserResponseDto updateUser(Long id, UpdateUserRequestDto requestDto, Long loginUserId) {
        User findUser = userRepository.findByIdOrElseThrow(loginUserId);
        findUser.updateUser(id, requestDto);
        return new UserResponseDto(findUser, "팔로우 되었습니다.");
    }

    /**
     * 비밀번호 변경
     *
     * @param id          the id
     * @param requestDto  the request dto
     * @param loginUserId the login user id
     * @return the user response dto
     */
    @Transactional
    public UserResponseDto updatePassword(Long id, UpdatePasswordRequestDto requestDto, Long loginUserId) {
        User findUser = userRepository.findByIdOrElseThrow(loginUserId);
        findUser.updatePassword(id, requestDto);
        return new UserResponseDto(findUser, "Password updated successfully");
    }

    /**
     * 삭제
     *
     * @param id          the id
     * @param requestDto  the request dto
     * @param loginUserId the login user id
     */
    public void delete(Long id, DeleteUserRequestDto requestDto, Long loginUserId) {
        User findUser = userRepository.findByIdOrElseThrow(loginUserId);
        // ✅ 탈퇴 이메일 저장
        RetiredEmail retiredEmail = new RetiredEmail(findUser.getEmail());
        retiredEmailRepository.save(retiredEmail);
        findUser.validatePassword(id, requestDto.getPassword());
        userRepository.delete(findUser);
    }
}
