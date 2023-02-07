package com.kaspi.backend.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
public class SignUpRequestDto {
    private String id;
    private String password;
    private String gender;
    private String age;
}
