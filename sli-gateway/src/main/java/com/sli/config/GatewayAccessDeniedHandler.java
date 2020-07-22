package com.sli.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
public class GatewayAccessDeniedHandler implements AccessDeniedHandler {

    @Resource
    private ObjectMapper objectMapper;

    /**
     * 授权拒绝处理，使用Json包装
     *
     * @param request request
     * @param response response
     * @param authException authException
     * @throws IOException IOException
     * @throws ServletException ServletException
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException authException) throws IOException, ServletException {
//        response.setCharacterEncoding(GlobalConstant.UTF8);
//        response.setContentType(GlobalConstant.CONTENT_TYPE);
//        Json result = JsonBuilder.build(ErrorCodeEnum.SY1000401.code(), "没有访问权限，禁止访问");
        System.out.println("有访问权限，禁止访问!!");
        response.setStatus(HttpStatus.SC_FORBIDDEN);
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials","true");
        PrintWriter printWriter = response.getWriter();
        printWriter.append(objectMapper.writeValueAsString("123"));
    }

}