package com.example.demo.service.impl;

import com.example.demo.service.MaoService;
import org.springframework.stereotype.Component;

@Component
public class Fish implements MaoService {

    @Override
    public void say() {
        System.out.println("Fish!~");
    }

}
