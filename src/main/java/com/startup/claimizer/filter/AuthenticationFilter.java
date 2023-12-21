package com.startup.claimizer.filter;


import com.auth0.jwt.exceptions.TokenExpiredException;
import com.startup.claimizer.dto.UserSessionData;
import com.startup.claimizer.util.JwtUtil;
import com.waleedreda.core.common.AppResponseUtil;
import com.waleedreda.core.common.ErrorCode;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

@Getter
@Setter
@Component
public class AuthenticationFilter implements Filter {

    private static final ArrayList<String> EXCLUDED_URLS = new ArrayList<>(Arrays.asList("/auth/login", "/user/register"));

    @Autowired
    UserSessionData userSessionData;

    JwtUtil jwtUtil;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();
        if (shouldIncluded(requestURI)) {
            try {
                String authorizationToken = httpRequest.getHeader("Authorization").substring(7);
                JwtUtil.validateToken(authorizationToken);
                userSessionData = JwtUtil.extractUserData(authorizationToken);
                chain.doFilter(httpRequest, response);
            } catch (TokenExpiredException e) {
                String errorResponse = AppResponseUtil.buildFailedResponse(ErrorCode.GENERAL, e.getMessage()).toJsonString();
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(errorResponse);
                return;
            }
        } else {
            chain.doFilter(httpRequest, response);
        }
    }

    private boolean shouldIncluded(String requestURI) {
        return !EXCLUDED_URLS.contains(requestURI);
    }

}
