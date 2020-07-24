package com.sli;

import com.sli.common.core.utils.IdUtils;
import com.sli.common.core.utils.RedisUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class GatewayApplicationTest {

    @Autowired
    private RedisUtils redisUtils;

    @Test
    public void randomUUID() {
        String aa = IdUtils.simpleUUID();
        System.out.println(aa);
        redisUtils.setCacheObject("haha22222",aa);
    }
}
