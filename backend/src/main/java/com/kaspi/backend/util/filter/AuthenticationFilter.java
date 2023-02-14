package com.kaspi.backend.util.filter;

import com.kaspi.backend.util.exception.AuthenticationException;
import com.kaspi.backend.util.response.code.ErrorCode;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import javax.servlet.http.HttpSession;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession(false);
        checkSession((HttpServletRequest) request, session);
        chain.doFilter(request, response);
    }

    private void checkSession(HttpServletRequest request, HttpSession session) {
        // POST로 클라이언트가 요청시 우선 OPTIONS메서드로 보내기에 해당 method는 예외적용 X
        if (!request.getMethod().equals("OPTIONS") && session == null) {
            throw new AuthenticationException(ErrorCode.AUTH_ERROR);
        }
    }


}
