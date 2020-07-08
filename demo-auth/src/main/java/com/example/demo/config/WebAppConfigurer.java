package com.example.demo.config;

import com.example.demo.util.interceptor.CrosIntercetpor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

//@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {

//    @Resource
//    private CrosIntercetpor crosIntercetpor;

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        // 可添加多个
//        registry.addInterceptor(crosIntercetpor).addPathPatterns("/**");
////        registry.addInterceptor(new CrosIntercetpor()).addPathPatterns("/**");
//    }

}