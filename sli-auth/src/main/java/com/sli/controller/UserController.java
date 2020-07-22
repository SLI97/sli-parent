package com.sli.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.sli.dao.UserDao;
import com.sli.entity.User;
import com.sli.service.UserService;
import com.sli.util.wrapper.Json;
import com.sli.util.wrapper.JsonBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.*;

@RestController
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
}