package com.example.demo.service.imp;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
//public class UserServiceImp extends ServiceImpl<UserDao, User> implements UserService {
public class UserServiceImp implements UserService {
    @Resource
    private UserDao userDao;

    public void creatUser(User user){
        System.out.println(userDao.insert(user));
    }
}
