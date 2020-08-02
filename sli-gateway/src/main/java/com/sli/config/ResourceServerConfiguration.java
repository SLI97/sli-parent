//package com.sli.config;
//
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.util.AntPathMatcher;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import java.util.ArrayList;
//import java.util.List;
//
//@Configuration
//@EnableResourceServer
//public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
//
//    @Resource
//    private OAuth2WebSecurityExpressionHandler gatewaySecurityExpressionHandler;
//    @Resource
//    private GatewayAccessDeniedHandler gatewayAccessDeniedHandler;
//
//    private AntPathMatcher antPathMatcher = new AntPathMatcher();
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        // 允许使用iframe 嵌套，避免swagger-ui 不被加载的问题
//        http.headers().frameOptions().disable();
//
//        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http
//                .authorizeRequests();
//        registry.antMatchers("/uaa/auth/**").permitAll();
//        registry.antMatchers("/uaa/user/**").permitAll();
//        registry.antMatchers("/uaa/oauth/**").permitAll();
//        registry.antMatchers("/uaa/**").permitAll();
//        registry.antMatchers("/**").permitAll();
////        registry.antMatchers("/hello").permitAll();
////        registry.anyRequest()
////                .access("@permissionService.hasPermission(authentication,request)");
//    }
//
//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources) {
//        resources.expressionHandler(gatewaySecurityExpressionHandler);
//        resources.accessDeniedHandler(gatewayAccessDeniedHandler);
//    }
//
//    @Bean
//    public OAuth2WebSecurityExpressionHandler oAuth2WebSecurityExpressionHandler(ApplicationContext applicationContext) {
//        OAuth2WebSecurityExpressionHandler expressionHandler = new OAuth2WebSecurityExpressionHandler();
//        expressionHandler.setApplicationContext(applicationContext);
//        return expressionHandler;
//    }
//}
