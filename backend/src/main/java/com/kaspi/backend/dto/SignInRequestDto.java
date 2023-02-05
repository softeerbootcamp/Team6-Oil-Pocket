package com.kaspi.backend.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignInRequestDto {
    private String id;
    private String password;
}
