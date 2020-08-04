package com.sli.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 监听发送的消息
 */
public class FishListener implements MessageListener {

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public void onMessage(Message message, byte[] bytes) {
        System.out.println("我是Fish监听" + message.toString());
    }
}
