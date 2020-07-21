package com.example.demo.controller;

import com.example.demo.dto.OauthDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/oauth2")
public class OAuth2Controller {

    private static final String clientId = "cafbac3150ce1951d339";
    private static final String clientSecret = "4778c8a8c733e931ebc597f968693b5bd21a3860";

    @Resource
    private RestTemplate restTemplate;

    @RequestMapping("/redirect")
//    @ResponseBody
    public String redirectUrl(@RequestParam("code") String code, Model model) {
        System.out.println("获取成功");
        System.out.println(code);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        ResponseEntity<OauthDto> responseEntity = restTemplate.postForEntity("https://github.com/login/oauth/access_token?client_id=" + clientId + "&client_secret=" + clientSecret + "&code=" + code, request, OauthDto.class);
        OauthDto result = responseEntity.getBody();
        System.out.println(result);
        model.addAttribute("access_token", result.getAccess_token());
        return "success";
    }

    @ResponseBody
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String hello(HttpServletResponse response, HttpSession session) {
        String str = new String();
        session.setAttribute("hello", "123123123");
        Cookie cookie = new Cookie("sessionId", "CookieTestInfo");
        response.addCookie(cookie);
        return "hello workflow123";
//            return restTemplate.getForObject("http://DEMO-GATEWAY/gateway/hello", String.class);
    }
}
