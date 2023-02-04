package com.kaspi.backend.util.cookie;


import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

@Component
public class CookieFactory {



    public ResponseCookie makeResponseCookie(String key,String value,int validSecond) {
        return ResponseCookie.from(key, value)
                .httpOnly(true)
                .secure(true)
                .path("/")
                .maxAge(validSecond)
                .build();
    }


}
