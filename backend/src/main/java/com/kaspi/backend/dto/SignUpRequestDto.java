package com.kaspi.backend.dto;

public class SignUpRequestDto {
    private String id;
    private String password;
    private String gender;
    private String age;

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getGender() {
        return gender;
    }

    public String getAge() {
        return age;
    }
}
