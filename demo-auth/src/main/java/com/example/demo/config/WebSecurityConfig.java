package com.example.demo.config;

import com.example.demo.service.imp.UserDetailsServiceImpl;
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
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.cors.reactive.CorsUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Configuration
@Order(1)
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter {

    @Autowired
    private FormAuthenticationConfig formAuthenticationConfig;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.cors().and().csrf().disable();
        http.csrf().disable();
        http.headers().frameOptions().disable();
//        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http
//                .authorizeRequests();
//        registry.antMatchers("/uaa/auth/**").c();
//        registry.antMatchers("/uaa/user/**").permitAll();
//        registry.anyRequest().access("@permissionService.hasPermission(authentication,request)");
        http.authorizeRequests()
                .antMatchers("/oauth/**").permitAll()
                .antMatchers("/user/**").permitAll()
//                .antMatchers("/**").permitAll() //让所有人可以访问首页
//                .antMatchers("/oauth/**").permitAll() //让所有人可以访问首页
//                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .anyRequest().authenticated();
//                .anyRequest()
//                .access("@permissionService.hasPermission(authentication,request)");
        //定制请求的授权规则
//        formAuthenticationConfig.configure(http);
//        http.formLogin();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
//                .passwordEncoder(passwordEncoder());

        //内存用户验证
//        auth.inMemoryAuthentication()
//                .withUser("wang").password("123456").roles("VIP1","VIP2").and()
//                .withUser("yun").password("123456").roles("VIP3");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    @ConditionalOnMissingBean(PasswordEncoder.class)
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    HttpFirewall httpFirewall() {
//        StrictHttpFirewall firewall = new StrictHttpFirewall();
//        firewall.setAllowSemicolon(true);
//        return firewall;
//    }

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Component("permissionService")
    public class PermissionServiceImpl {

        public boolean hasPermission(Authentication authentication,
                                     HttpServletRequest request) {
            if (HttpMethod.OPTIONS.name().equalsIgnoreCase(request.getMethod())) {
                return true;
            }
//            List<String> currentRoleList = SecurityUtils.getCurrentRoles();
//            Authentication authentication = SecurityContextHolder.getContext()
//                    .getAuthentication();
            String requestURI = request.getRequestURI();

            System.out.println(requestURI);

            List<String> urls = new ArrayList<>();
//            urls.add("/uaa/auth/login");

//            if (ObjectUtils.isEmpty(currentRoleList)) {
//                System.out.println("权限列表为空：username={}");
//                return false;
//            }

            for (String url : urls) {
                if (antPathMatcher.match(url, requestURI)) {
                    System.out.println("有着路径！");
                    return true;
                }
            }
            return false;
        }
    }
}
