package com.sli.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("formAuthenticationFailureHandler")
public class FormAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Resource
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {
        System.out.println("登陆失败!");
        System.out.println(exception);

        String tipMessage = exception.getMessage();
        //判断登录出错类型是否为密码错误
        if (exception instanceof BadCredentialsException) {
            tipMessage = "密码错误";
        }else if (exception instanceof InternalAuthenticationServiceException){
            tipMessage = "该账号不存在";
        }
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setContentType("application/json;charset=UTF-8");
//        response.getWriter().write(objectMapper
//                .writeValueAsString(JsonBuilder.error(tipMessage)));
    }
}
