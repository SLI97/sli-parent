package com.sli.service.impl;

import com.sli.service.MaoService;
import org.springframework.stereotype.Component;

@Component
public class Fish implements MaoService {

    @Override
    public void say() {
        System.out.println("Fish!~");
    }

}
