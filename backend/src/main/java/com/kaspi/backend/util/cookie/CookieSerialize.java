
package com.kaspi.backend.util.cookie;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class CookieSerialize {
    @Bean
    public CookieSerializer cookieSerializer() throws MalformedURLException {
        log.info("cookie fileter");
        DefaultCookieSerializer serializer = new DefaultCookieSerializer();
        serializer.setSameSite("None");
        serializer.setUseSecureCookie(true);
        serializer.setDomainName("https://moon-gd.github.io/");
        return serializer;
    }
}