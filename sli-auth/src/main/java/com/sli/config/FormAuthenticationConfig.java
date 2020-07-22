package com.sli.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class FormAuthenticationConfig {

    @Autowired
    private AuthenticationSuccessHandler formAuthenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler formAuthenticationFailureHandler;

    /**
     * 表单登录配置.
     *
     * @param http the http
     * @throws Exception the exception
     */
    void configure(HttpSecurity http) throws Exception {
        http.formLogin().permitAll()
//                .loginPage("/auth/require")
                .loginProcessingUrl("/auth/login")
                .successHandler(formAuthenticationSuccessHandler)
                .failureHandler(formAuthenticationFailureHandler);
    }

}
