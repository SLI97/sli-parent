package com.sli.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 监听发送的消息
 */
//@Component
public class CatListener implements MessageListener {

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public void onMessage(Message message, byte[] bytes) {
        System.out.println("我是Cat监听" + message.toString());
    }
}
