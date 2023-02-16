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
import com.kaspi.backend.util.exception.ParameterException;
import com.kaspi.backend.util.exception.SqlNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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
        assertThrows(ParameterException.class, () -> {
            userService.makeUser(signUpRequestDto);
        });
    }


    @Test
    @DisplayName("update user service 테스트")
    void updateUser() {
        //given
        User user = User.builder().id("test").password("password").gender(Gender.MALE).age(Age.TWENTY).build();
        UserUpdateReqDto dto = UserUpdateReqDto.builder().gender(Gender.FEMALE.getInitial()).age(Age.FIFTY.getAgeBound()).build();
        when(httpSessionService.getUserFromSession()).thenReturn(user);
        //when
        userService.updateUser(dto);
        //then
        verify(httpSessionService, times(1)).getUserFromSession();
        assertEquals(user.getGender(),Gender.getGender(dto.getGender()).get() );
        assertEquals(user.getAge(), Age.getAge(dto.getAge()).get());
    }

    @Test
    @DisplayName("회원 삭제")
    void deleteUser() {
        //given
        User existUser = User.builder().build();
        when(httpSessionService.getUserFromSession()).thenReturn(existUser);
        //when
        userService.deleteUser();
        //then
        verify(userDao, Mockito.times(1)).delete(existUser);
        verify(httpSessionService, Mockito.times(1)).deleteSession();
    }
}