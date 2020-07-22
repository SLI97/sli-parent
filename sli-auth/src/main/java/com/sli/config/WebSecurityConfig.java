package com.sli.config;

import com.sli.service.imp.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


/**
 * Security 安全认证相关配置
 * Oauth2依赖于Security 默认情况下WebSecurityConfig执行比ResourceServerConfig优先
 *
 * @author sli
 */
@Order(1)
@Configuration
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter {

    @Autowired
    private FormAuthenticationConfig formAuthenticationConfig;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    @ConditionalOnMissingBean(PasswordEncoder.class)
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
//                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();
        http.authorizeRequests()
                .antMatchers("/oauth/**").permitAll()
                .antMatchers("/oauth2/**").permitAll()
                .anyRequest().authenticated().and().csrf().disable();;
//                .anyRequest()
//                .access("@permissionService.hasPermission(authentication,request)");
//        formAuthenticationConfig.configure(http);
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS);
    }

//    private AntPathMatcher antPathMatcher = new AntPathMatcher();

//    @Component("permissionService")
//    public class PermissionServiceImpl {
//
//        public boolean hasPermission(Authentication authentication,
//                                     HttpServletRequest request) {
//            if (HttpMethod.OPTIONS.name().equalsIgnoreCase(request.getMethod())) {
//                return true;
//            }
////            List<String> currentRoleList = SecurityUtils.getCurrentRoles();
////            Authentication authentication = SecurityContextHolder.getContext()
////                    .getAuthentication();
//            String requestURI = request.getRequestURI();
//
//            System.out.println(requestURI);
//
//            List<String> urls = new ArrayList<>();
////            urls.add("/uaa/auth/login");
//
////            if (ObjectUtils.isEmpty(currentRoleList)) {
////                System.out.println("权限列表为空：username={}");
////                return false;
////            }
//
//            for (String url : urls) {
//                if (antPathMatcher.match(url, requestURI)) {
//                    System.out.println("有着路径！");
//                    return true;
//                }
//            }
//            return false;
//        }
//    }
}
