package com.example.demo.service;

import com.baomidou.mybatisplus.service.IService;
import com.example.demo.entity.User;
import org.springframework.stereotype.Service;

//public interface UserService extends IService<User> {
public interface UserService{
    public void creatUser(User user);

}
