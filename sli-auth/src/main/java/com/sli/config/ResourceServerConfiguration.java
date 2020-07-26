package com.sli.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.expression.OAuth2WebSecurityExpressionHandler;

@Configuration
@EnableResourceServer
@Order(101)
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

//    @Autowired
//    private OAuth2WebSecurityExpressionHandler pcSecurityExpressionHandler;

    @Autowired
    private FormAuthenticationConfig formAuthenticationConfig;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();

        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http
                .authorizeRequests();
        http.authorizeRequests()
                .antMatchers("/**").permitAll()
//                .antMatchers("/user/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable();
//        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http
//                .authorizeRequests();
////        registry.antMatchers("/auth/**").permitAll();
//        registry.antMatchers("/user/**").permitAll();
//        registry.anyRequest().access("@permissionService.hasPermission(authentication,request)");

        formAuthenticationConfig.configure(http);
    }

//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources) {
//        resources.expressionHandler(pcSecurityExpressionHandler);
//    }
}
