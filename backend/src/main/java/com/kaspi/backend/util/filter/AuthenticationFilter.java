package com.kaspi.backend.util.filter;

import com.kaspi.backend.dao.UserDao;
import com.kaspi.backend.domain.User;
import com.kaspi.backend.service.UserService;
import com.kaspi.backend.util.exception.NotAuthException;
import com.kaspi.backend.util.response.code.ErrorCode;
import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;

@RequiredArgsConstructor
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession(false);

        if (session == null) {
            throw new NotAuthException(ErrorCode.AUTH_ERROR);
        }

        chain.doFilter(request, response);
    }


}
