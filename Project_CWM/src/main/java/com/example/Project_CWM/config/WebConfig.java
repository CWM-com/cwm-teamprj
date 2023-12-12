package com.example.Project_CWM.config;

import com.example.Project_CWM.interceptor.SessionCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SessionCheckInterceptor())
                .order(1).addPathPatterns("/mypage") // 포트 뒤에 / 가 붙는 경우 다 막게함
                .excludePathPatterns("/")
                .excludePathPatterns("/login"); // 해당 등록한건 가능하게
    }
}