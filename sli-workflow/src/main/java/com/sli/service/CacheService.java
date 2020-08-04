package com.sli.service;

//import com.sli.entity.MyTest;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.stereotype.Service;
//
//import java.util.HashMap;
//import java.util.Map;


//@Service
//public class CacheService {
//
//    public static final Map<Integer, MyTest> users = new HashMap<>();
//
//    static {
//        users.put(1, new MyTest("我是快乐鱼"));
//        users.put(2, new MyTest( "我是忧郁猫"));
//        users.put(3, new MyTest( "我是昴先生"));
//    }
//
//    @Cacheable(cacheNames = "user",key = "targetClass + methodName +#p0")
//    public MyTest getUser(int id) {
//        System.out.println("缓存中没有，从map中获取");
//        MyTest user = users.get(id);
//        return user;
//    }
//}
