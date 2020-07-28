package com.sli.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.annotation.Resource;

//@Configuration
public class TokenStoreConfig {

    /**
     * 使用Redis做缓存
     */
//    @Configuration
//    @ConditionalOnProperty(prefix = "eastcom.core.oauth2", name = "tokenStore", havingValue = "redis")
//    public static class RedisConfig {
//
//        @Resource
//        private RedisConnectionFactory redisConnectionFactory;
//
//        @Bean
//        public TokenStore redisTokenStore() {
//            RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
////            tokenStore.setPrefix(CacheConstants.OAUTH_ACCESS);
//            return tokenStore;
//        }
//
//    }
    /**
     * 使用jwt时的配置，默认生效
     */
//    @Configuration
//    @ConditionalOnProperty(prefix = "eastcom.core.oauth2", name = "tokenStore", havingValue = "jwt", matchIfMissing = true)
    public static class JwtConfig {

//        @Bean
        public TokenStore jwtTokenStore() {
            return new JwtTokenStore(jwtAccessTokenConverter());
        }

//        @Bean
        public JwtAccessTokenConverter jwtAccessTokenConverter() {
            JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
            converter.setSigningKey("sli");
            return converter;
        }

    }
}
