package com.sli.service.impl;

import com.sli.service.MaoService;
import org.springframework.stereotype.Component;

@Component("Cat")
public class Cat implements MaoService {

    @Override
    public void say() {
        System.out.println("Cat!~");
    }

}
