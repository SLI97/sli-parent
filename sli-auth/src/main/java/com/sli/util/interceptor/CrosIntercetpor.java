package com.sli.util.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

public class CrosIntercetpor implements HandlerInterceptor {
    protected Logger log = LoggerFactory.getLogger(getClass());

//    @Value("${system.jwt.token.max-age}")
//    private long tokenMaxAge;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
//        System.out.println("---------------");
//        Enumeration<?> enum1 = request.getHeaderNames();
//        while (enum1.hasMoreElements()) {
//            String key = (String) enum1.nextElement();
//            String value = request.getHeader(key);
//            System.out.println(key + "\t" + value);
//        }
//        System.out.println("***************");


        //前段发起一个请求，后端收到两个请求，一个方法为： OPTIONS     第二个真实的请求
//        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
//            System.out.println("haha");
//            return true;
//        }

////		//头部
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "content-type, Authorization, currentUserToken");
        response.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
        response.addHeader("Access-Control-Request-Headers",
                "Origin, X-Requested-With, content-Type, Accept, Authorization, currentUserToken");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }
}