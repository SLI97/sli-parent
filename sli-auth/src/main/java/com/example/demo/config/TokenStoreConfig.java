package com.example.demo.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.annotation.Resource;

@Configuration
public class TokenStoreConfig {
    /**
     * 使用jwt时的配置，默认生效
     */
    @Configuration
//    @ConditionalOnProperty(prefix = "eastcom.security.oauth2", name = "tokenStore", havingValue = "jwt", matchIfMissing = true)
    public static class JwtConfig {

//        @Resource
//        private EastcomProperties eastcomProperties;

        /**
         * Jwt token store token store.
         *
         * @return the token store
         */
        @Bean
        public TokenStore jwtTokenStore() {
            return new JwtTokenStore(jwtAccessTokenConverter());
        }

        /**
         * Jwt access token converter jwt access token converter.
         *
         * @return the jwt access token converter
         */
        @Bean
        public JwtAccessTokenConverter jwtAccessTokenConverter() {
            JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//            converter.setSigningKey(eastcomProperties.getSecurity().getOauth2().getJwtSigningKey());
            converter.setSigningKey("demo-parent");
            return converter;
        }

        /**
         * jwt 生成token 定制化处理
         *
         * @return TokenEnhancer
         */
        @Bean
        @ConditionalOnBean(TokenEnhancer.class)
        public TokenEnhancer jwtTokenEnhancer() {
            return new TokenJwtEnhancer();
        }

    }
}
