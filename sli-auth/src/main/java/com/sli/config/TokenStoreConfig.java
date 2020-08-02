package com.sli.config;

import com.sli.common.core.constant.CacheConstants;
import com.sli.common.core.constant.SecurityConstants;
import com.sli.common.security.domain.LoginUser;
//import com.sli.util.serialization.FastjsonRedisTokenStoreSerializationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class TokenStoreConfig {

    /**
     * 使用Redis做缓存
     */
    @Configuration
//    @ConditionalOnProperty(prefix = "eastcom.core.oauth2", name = "tokenStore", havingValue = "redis")
    public static class RedisConfig {
        //
        @Resource
        private RedisConnectionFactory redisConnectionFactory;

        /**
         * 基于 Redis 实现，令牌保存到缓存
         */
        @Bean
        public TokenStore redisTokenStore() {
            RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
            tokenStore.setPrefix(CacheConstants.OAUTH_ACCESS);
//            tokenStore.setSerializationStrategy(new FastjsonRedisTokenStoreSerializationStrategy());
            return tokenStore;
        }
//
    }

    /**
     * 使用jwt时的配置，默认生效
     */
    @Configuration
//    @ConditionalOnProperty(prefix = "eastcom.core.oauth2", name = "tokenStore", havingValue = "jwt", matchIfMissing = true)
    public static class JwtConfig {

//        @Bean()
//        public TokenStore myJwtTokenStore() {
//            return new JwtTokenStore(jwtAccessTokenConverter());
//        }

        @Bean
        public JwtAccessTokenConverter jwtTokenConverter() {
            JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
            converter.setSigningKey("sli");
            return converter;
        }

        /**
         * 自定义生成令牌
         */
        @Bean()
        public TokenEnhancer jwtTokenEnhancer() {
            //返回一个实现了TokenEnhancer接口的匿名内部类对象,lambda表达式写法
            return (OAuth2AccessToken accessToken, OAuth2Authentication authentication) -> {
                if (authentication.getUserAuthentication() != null) {
                    Map<String, Object> additionalInformation = new LinkedHashMap<>();
                    LoginUser user = (LoginUser) authentication.getUserAuthentication().getPrincipal();
                    additionalInformation.put("timestamp", System.currentTimeMillis());
                    additionalInformation.put("license", "SLI97");
                    additionalInformation.put(SecurityConstants.DETAILS_USER_ID, user.getUserId());
                    additionalInformation.put(SecurityConstants.DETAILS_USERNAME, user.getUsername());
                    ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);
                }
                return accessToken;
            };
        }

    }
}
