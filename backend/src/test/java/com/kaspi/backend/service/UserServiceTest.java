package com.kaspi.backend.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.kaspi.backend.dao.UserDao;
import com.kaspi.backend.domain.User;
import com.kaspi.backend.dto.SignUpRequestDto;
import com.kaspi.backend.dto.UserUpdateReqDto;
import com.kaspi.backend.enums.Age;
import com.kaspi.backend.enums.Gender;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserDao userDao;

    @Mock
    private HttpSessionService httpSessionService ;

    @InjectMocks
    private UserService userService;


    @Test
    @DisplayName("유저 정상 생성 테스트")
    void makeUser() {
        //given
        SignUpRequestDto signUpRequestDto = SignUpRequestDto.builder().id("user1").password("password").gender("M")
                .age("20대")
                .build();
        User expectedUser = User.builder().userNo(1L).id("user1").password("password").age(Age.TWENTY)
                .gender(Gender.MALE)
                .build();
        when(userDao.save(any(User.class)))
                .thenReturn(expectedUser);
        //when
        User actualUser = userService.makeUser(signUpRequestDto);
        //then
        assertEquals(expectedUser, actualUser);
    }


    @Test
    @DisplayName("유저 비정상 생성 테스트 에러 생성")
    void makeNoTValidUser() {
        //given
        SignUpRequestDto signUpRequestDto = SignUpRequestDto.builder().id("user1").password("password").gender("Male")
                .age("20대")
                .build();
        assertThrows(IllegalArgumentException.class, () -> {
            userService.makeUser(signUpRequestDto);
        });
    }


    @Test
    @DisplayName("update user service 테스트")
    void updateUser() {
        //given
        User user = User.builder().id("test").password("password").gender(Gender.MALE).age(Age.TWENTY).build();
        UserUpdateReqDto dto = UserUpdateReqDto.builder().age(Age.FORTY).age(Age.FIFTY).build();
        when(httpSessionService.getUserFromSession()).thenReturn(user);
        //when
        userService.updateUser(dto);
        //then
        verify(httpSessionService, times(1)).getUserFromSession();
        assertEquals(user.getGender(),dto.getGender() );
        assertEquals(user.getAge(), dto.getAge());
    }
}