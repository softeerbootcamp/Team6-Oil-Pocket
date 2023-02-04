package com.kaspi.backend.util.filter;

import com.kaspi.backend.dao.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.session.SessionRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FilterConfig {

    private final UserDao userDao;
    private final SessionRepository sessionRepository;

    @Bean
    public FilterRegistrationBean<AuthenticationFilter> authenticationFilter() {
        FilterRegistrationBean<AuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthenticationFilter(userDao, sessionRepository));
        registrationBean.addUrlPatterns("/api/v1/user");
        return registrationBean;
    }

}
