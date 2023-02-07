
package com.kaspi.backend.util.cookie;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

import java.net.MalformedURLException;

/**
 * 쿠키 samesite 설정 none
 * 추후 배포시 삭제 예정
 */
@Configuration
public class CookieSerialize {
    @Bean
    public CookieSerializer cookieSerializer() throws MalformedURLException {
        DefaultCookieSerializer serializer = new DefaultCookieSerializer();
        serializer.setDomainName("localhost:5173");
        return serializer;
    }
}