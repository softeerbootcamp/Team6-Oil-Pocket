package com.kaspi.backend.dto;

import lombok.Getter;

@Getter
public class SignUpRequestDto {
    private String id;
    private String password;
    private String gender;
    private String age;
}
