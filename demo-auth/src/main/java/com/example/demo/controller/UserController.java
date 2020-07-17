package com.example.demo.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.example.demo.dao.UserDao;
import com.example.demo.dto.OauthDto;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.util.wrapper.Json;
import com.example.demo.util.wrapper.JsonBuilder;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserDao userDao;

//    @LoadBalanced
    @Resource
    private RestTemplate restTemplate;

    @Resource
    private UserService userService;

    @RequestMapping("/hello")
    public Integer hello1() {
        return 1122;
    }

    //    @PostMapping
    @DeleteMapping
    @RequestMapping("/get")
    public Json hello() {
        Wrapper wp = new EntityWrapper<User>();
        Page<User> sss = new Page<>(1, 10);
//        wp.gt("age", 20);
//        wp.eq(name != null,"SLI");
        List<User> users = userDao.selectPage(sss, wp);


        return JsonBuilder.ok("haha");
    }

    @PostMapping
    @RequestMapping("/insert")
    public Json hello2(User user) {
        System.out.println(user);
        userService.creatUser(user);
        return JsonBuilder.build(200, "角色创建成功");
    }

    @RequestMapping("/redirect")
//    @ResponseBody
    public String redirectUrl(@RequestParam("code") String code) {
        System.out.println("获取成功");
        System.out.println(code);
        String clientId = "cafbac3150ce1951d339";
        String clientSecret = "4778c8a8c733e931ebc597f968693b5bd21a3860";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        ResponseEntity<OauthDto> responseEntity = restTemplate.postForEntity("https://github.com/login/oauth/access_token?client_id=" + clientId + "&client_secret=" + clientSecret + "&code=" + code, request, OauthDto.class);
        OauthDto result = responseEntity.getBody();
        System.out.println(result);
//        HttpHeaders headers1= new HttpHeaders();
//        headers1.add("Authorization","token " + result.getAccess_token());
//        List<MediaType> ls = new ArrayList<>();
//        ls.add(MediaType.APPLICATION_JSON_UTF8);
//        headers1.setAccept(ls);
//        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<MultiValueMap<String, String>>(map, headers1);
//        System.out.println(httpEntity);
//        HttpEntity<String> responseEntity1 =  restTemplate.exchange("https://api.github.com/user", HttpMethod.GET,
//                httpEntity, String.class);
//        String result1 = responseEntity1.getBody();
//        System.out.println(result1);
//        return JsonBuilder.build(200, result1);
        return "hello";
    }

    @ResponseBody
    @RequestMapping(value = "/zzz", method = RequestMethod.GET)
    public String hello(HttpServletResponse response, HttpSession session) {
        session.setAttribute("hello","123123123");
        Cookie cookie=new Cookie("sessionId","CookieTestInfo");
        response.addCookie(cookie);
        return "hello workflow123";
//            return restTemplate.getForObject("http://DEMO-GATEWAY/gateway/hello", String.class);
    }
}