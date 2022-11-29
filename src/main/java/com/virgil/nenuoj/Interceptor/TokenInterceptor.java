package com.virgil.nenuoj.Interceptor;

import com.virgil.nenuoj.utils.JWTUtil;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle( HttpServletRequest request,
                              @NotNull HttpServletResponse response,
                              @NotNull Object handler) {
        String token = request.getHeader("Authorization");
        int result = JWTUtil.verify(token);
        if(result == 1) {
            response.setStatus(403);
            return false;
        }
        else {
            token = JWTUtil.refreshToken(token);
            response.setHeader("Authorization", token);
        }
        return true;
    }
}
