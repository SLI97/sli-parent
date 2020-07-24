//package com.sli.config;
//
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.core.config.annotation.web.builders.HttpSecurity;
//import org.springframework.core.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
//import org.springframework.core.core.Authentication;
//import org.springframework.core.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.core.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.core.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
//import org.springframework.core.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;
//import org.springframework.stereotype.Component;
//import org.springframework.util.AntPathMatcher;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import java.util.ArrayList;
//import java.util.List;
//
////@Configuration
////@EnableResourceServer
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
//        registry.anyRequest()
//                .access("@permissionService.hasPermission(authentication,request)");
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
//
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
//}
