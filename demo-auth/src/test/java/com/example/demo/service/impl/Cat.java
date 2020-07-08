package com.example.demo.service.impl;

import com.example.demo.service.MaoService;
import org.springframework.stereotype.Component;

@Component("Cat")
public class Cat implements MaoService {

    @Override
    public void say() {
        System.out.println("Cat!~");
    }

}
