package com.sli.config;

import com.sli.exception.CustomWebResponseExceptionTranslator;
import com.sli.common.security.service.RedisClientDetailsService;
import com.sli.common.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * OAuth2 认证服务配置
 *
 * @author sli
 */
@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private DataSource dataSource;

    @Autowired
    @Qualifier("redisTokenStore")
    private TokenStore tokenStore;

    @Autowired
    @Qualifier("jwtTokenConverter")
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired
    @Qualifier("jwtTokenEnhancer")
    private TokenEnhancer tokenEnhancer;

    /**
     * 配置客户端详情
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService());
    }

    /**
     * 声明 ClientDetails实现
     */
    public ClientDetailsService clientDetailsService() {
//        return new JdbcClientDetailsService(dataSource);
        return new RedisClientDetailsService(dataSource);
    }


    /**
     * 定义授权和令牌端点以及令牌服务
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                // 请求方式
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
                // 用户账号密码认证
                .userDetailsService(userDetailsService)
                // 指定token存储位置
                .tokenStore(tokenStore)
                // 自定义生成令牌
//                .tokenEnhancer(tokenEnhancer)
                //使用加密算法加密token
//                .accessTokenConverter(jwtAccessTokenConverter)
                // 指定认证管理器
                .authenticationManager(authenticationManager)
                // 是否重复使用 refresh_token
                .reuseRefreshTokens(false)
                // 自定义异常处理
                .exceptionTranslator(new CustomWebResponseExceptionTranslator());

        //这里有坑，一定要用这种方式，才能同时使用增强和JWT
        if(jwtAccessTokenConverter != null && tokenEnhancer != null){
            TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
            List<TokenEnhancer> enhancers = new ArrayList<>();
            enhancers.add(tokenEnhancer);
            enhancers.add(jwtAccessTokenConverter);
            tokenEnhancerChain.setTokenEnhancers(enhancers);
            endpoints.tokenEnhancer(tokenEnhancerChain).accessTokenConverter(jwtAccessTokenConverter);
        }
    }

    /**
     * 配置令牌端点(Token Endpoint)的安全约束
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        oauthServer
                // 开启/oauth/token_key验证端口无权限访问
                .tokenKeyAccess("permitAll()")
                // 开启/oauth/check_token验证端口认证权限访问
                .checkTokenAccess("isAuthenticated()")
                .allowFormAuthenticationForClients();
    }
}
