package com.sli.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

//@EnableScheduling //开启定时器功能
//@Component
public class MessageSender {
    @Resource(name = "stringRedisTemplate")
    private StringRedisTemplate stringRedisTemplate;

    @Scheduled(fixedRate = 2000) //间隔2s 通过StringRedisTemplate对象向redis消息队列cat频道发布消息
    public void sendCatMessage(){
        stringRedisTemplate.convertAndSend("cat","猫");
    }

    @Scheduled(fixedRate = 1000) //间隔1s 通过StringRedisTemplate对象向redis消息队列fish频道发布消息
    public void sendFishMessage(){
        stringRedisTemplate.convertAndSend("fish","鱼");
    }

}
