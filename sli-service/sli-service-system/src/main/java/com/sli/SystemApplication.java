package com.sli;

import com.sli.common.security.annotation.EnableCustomConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 系统模块
 *
 * @author sli
 */
@MapperScan("com.sli.mapper")
@EnableFeignClients
@SpringCloudApplication
@EnableCustomConfig
public class SystemApplication
{
    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class, args);
    }
}
