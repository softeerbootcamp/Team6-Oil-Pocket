package com.kaspi.backend.util.filter;

import com.kaspi.backend.util.exception.AuthorizationException;
import com.kaspi.backend.util.response.code.ErrorCode;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthorizationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession(false);

        if (session == null) {
            throw new AuthorizationException(ErrorCode.AUTH_ERROR);
        }

        chain.doFilter(request, response);
    }


}
