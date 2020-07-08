package com.example.demo.config;

import com.example.demo.util.RequestUtils;
import com.example.demo.util.wrapper.JsonBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("formAuthenticationSuccessHandler")
public class FormAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Resource
    private ObjectMapper objectMapper;

//    @Resource
//    private AuthorizationServerTokenServices authorizationServerTokenServices;

    private static final String BEARER_TOKEN_TYPE = "Basic ";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response, Authentication authentication)
            throws IOException {
        System.out.println("登陆成功!");
        System.out.println(authentication);

        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith(BEARER_TOKEN_TYPE)) {
            try {
                throw new Exception("请求头中无client信息");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

//        String[] tokens = RequestUtils.extractAndDecodeHeader(header);

        String token = "123456haha";

        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "content-type, Authorization, currentUserToken");
        response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
        response.addHeader("Access-Control-Request-Headers",
                "Origin, X-Requested-With, content-Type, Accept, Authorization, currentUserToken");
        response.getWriter()
                .write((objectMapper.writeValueAsString(JsonBuilder.ok(token))));
    }
}
