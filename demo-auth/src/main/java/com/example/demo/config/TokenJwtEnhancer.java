package com.example.demo.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

public class TokenJwtEnhancer implements TokenEnhancer {

//    private EastcomProperties eastcomProperties;

//    public TokenJwtEnhancer(EastcomProperties eastcomProperties) {
//        this.eastcomProperties = eastcomProperties;
//    }

    /**
     * Enhance o auth 2 access token.
     *
     * @param accessToken the access token
     * @param oAuth2Authentication the o auth 2 authentication
     *
     * @return the o auth 2 access token
     */
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken,
                                     OAuth2Authentication oAuth2Authentication) {
        Map<String, Object> additionalInfo = new HashMap<>(8);
        additionalInfo.put("timestamp", System.currentTimeMillis());
        additionalInfo.put("license", "SLI97");
        Authentication authentication = oAuth2Authentication.getUserAuthentication();
        if (authentication != null
                && authentication.getPrincipal() instanceof UserDetails) {
            Object principal = authentication.getPrincipal();
            additionalInfo.put("username", ((UserDetails) principal).getUsername());
        }

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);

        return accessToken;
    }

}