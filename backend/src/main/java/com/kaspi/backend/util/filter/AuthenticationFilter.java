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
import lombok.RequiredArgsConstructor;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;

@RequiredArgsConstructor
public class AuthenticationFilter implements Filter {
    private final UserDao userDao;
    private final SessionRepository sessionRepository;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String sessionId = extractSessionIdFromCookie(httpRequest);

        Session session = sessionRepository.findById(sessionId);

        if (sessionId == null || session.isExpired()) {
            throw new NotAuthException(ErrorCode.AUTH_ERROR);
        }

        User user = userDao.findByUserNo(session.getAttribute(UserService.SESSION_COOKIE_KEY)).get();
        chain.doFilter(request, response);
    }

    private String extractSessionIdFromCookie(HttpServletRequest httpRequest) {
        String sessionId = null;
        Cookie[] cookies = httpRequest.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(UserService.SESSION_COOKIE_KEY)) {
                    sessionId = cookie.getValue();
                    break;
                }
            }
        }
        return sessionId;
    }


}
