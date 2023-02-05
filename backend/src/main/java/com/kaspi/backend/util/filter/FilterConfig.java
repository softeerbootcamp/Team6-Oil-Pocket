package com.kaspi.backend.util.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<AuthenticationFilter> authenticationFilter() {
        FilterRegistrationBean<AuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthenticationFilter());
        registrationBean.addUrlPatterns();
        registrationBean.addUrlPatterns("/api/v2/*");
        return registrationBean;
    }

}
