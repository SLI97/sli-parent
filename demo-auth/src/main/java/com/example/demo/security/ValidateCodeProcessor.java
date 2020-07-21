package com.example.demo.security;

import org.springframework.web.context.request.ServletWebRequest;

public interface ValidateCodeProcessor {

    /**
     * 创建校验码
     *
     * @param request the request
     *
     * @throws Exception the exception
     */
    void create(ServletWebRequest request) throws Exception;

    /**
     * 校验验证码(验证后删除)
     *
     * @param servletWebRequest the servlet web request
     */
    void validate(ServletWebRequest servletWebRequest);

    /**
     * 校验验证码(验证后不删除)
     *
     * @param servletWebRequest the servlet web request
     */
    void check(ServletWebRequest servletWebRequest);

}
