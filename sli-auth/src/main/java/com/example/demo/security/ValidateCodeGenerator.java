package com.example.demo.security;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 校验码生成器
 *
 */
public interface ValidateCodeGenerator {

    /**
     * 生成校验码
     *
     * @param request the request
     *
     * @return validate code
     */
    ValidateCode generate(ServletWebRequest request);

}
