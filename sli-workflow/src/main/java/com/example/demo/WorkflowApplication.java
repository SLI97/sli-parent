package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@EnableDiscoveryClient
@SpringBootApplication
public class WorkflowApplication{
    public static void main(String[] args) {
        SpringApplication.run(WorkflowApplication.class, args);
    }

    @RestController
    class HelloController {

        @RequestMapping(value = "/hello", method = RequestMethod.GET)
        public String hello(HttpServletResponse response, HttpSession session) {
            session.setAttribute("hello","123123123");
            Cookie cookie=new Cookie("sessionId","CookieTestInfo");
            response.addCookie(cookie);
            return "hello workflow123";
//            return restTemplate.getForObject("http://DEMO-GATEWAY/gateway/hello", String.class);
        }
    }

}
