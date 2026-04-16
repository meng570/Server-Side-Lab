package com.stu.helloserver.common;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 简单登录校验：从请求头/ session 中获取用户信息，这里做基础实现
        Object user = request.getSession().getAttribute("loginUser");
        if (user == null) {
            // 未登录，返回JSON错误信息
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("{\"code\":" + ResultCode.UNAUTHORIZED.getCode() + ",\"msg\":\"" + ResultCode.UNAUTHORIZED.getMsg() + "\"}");
            return false;
        }
        return true;
    }
}