package com.stu.helloserver;

import com.stu.helloserver.common.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                // 拦截所有请求，排除登录、注册接口
                .addPathPatterns("/**")
                .excludePathPatterns("/api/user/register", "/api/user/login", "/error");
    }
}