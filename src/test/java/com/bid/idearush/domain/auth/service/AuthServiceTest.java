package com.bid.idearush.domain.auth.service;

import com.bid.idearush.domain.auth.model.request.SignupRequest;
import com.bid.idearush.domain.user.model.entity.Users;
import com.bid.idearush.domain.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @InjectMocks
    AuthService authService;

    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @Nested
    @DisplayName("회원가입 테스트")
    class Signup {

        SignupRequest signupRequest =
                new SignupRequest("a123", "aaaa", "1234");

        @Test
        @DisplayName("회원가입 성공 테스트")
        void signupSuccessTest() {
            given(userRepository.findByUserAccountId(signupRequest.userAccountId()))
                    .willReturn(Optional.empty());
            given(userRepository.findByNickname(signupRequest.nickname()))
                    .willReturn(Optional.empty());

            authService.signup(signupRequest);

            verify(userRepository, times(1)).save(any(Users.class));
        }

        @Test
        @DisplayName("유저 아이디가 중복되어 회원가입에 실패하는 경우 테스트")
        void UserAccountIdDupSignupFailTest() {
            given(userRepository.findByUserAccountId(signupRequest.userAccountId()))
                    .willReturn(Optional.of(Users.builder().build()));

            IllegalStateException ex = assertThrows(IllegalStateException.class, ()->
                    authService.signup(signupRequest));

            assertEquals("유저 아이디가 중복됩니다.", ex.getMessage());
        }

        @Test
        @DisplayName("닉네임이 중복되어 회원가입에 실패하는 경우 테스트")
        void NicknameDupSignupFailTest() {
            given(userRepository.findByNickname(signupRequest.nickname()))
                    .willReturn(Optional.of(Users.builder().build()));

            IllegalStateException ex = assertThrows(IllegalStateException.class, ()->
                    authService.signup(signupRequest));

            assertEquals("닉네임이 중복됩니다.", ex.getMessage());
        }

    }

}