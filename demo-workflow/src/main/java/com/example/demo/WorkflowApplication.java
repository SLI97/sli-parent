package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@EnableDiscoveryClient
@SpringBootApplication
public class WorkflowApplication{
    public static void main(String[] args) {
        SpringApplication.run(WorkflowApplication.class, args);
    }

    @RestController
    class HelloController {

        @RequestMapping(value = "/hello", method = RequestMethod.GET)
        public String hello() {
            return "hello workflow";
//            return restTemplate.getForObject("http://DEMO-GATEWAY/gateway/hello", String.class);
        }
    }
}
