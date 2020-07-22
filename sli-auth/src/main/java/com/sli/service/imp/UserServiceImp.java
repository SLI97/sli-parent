package com.sli.service.imp;

import com.sli.dao.UserDao;
import com.sli.entity.User;
import com.sli.service.UserService;
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
