package com.kaspi.backend.dto;

import com.kaspi.backend.enums.Age;
import com.kaspi.backend.enums.Gender;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserUpdateReqDto {
    private String gender;
    private String age;

}
