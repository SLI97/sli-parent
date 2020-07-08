package com.example.demo;

import com.example.demo.service.impl.Cat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MapTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private TestService maoService;

    @Test
    public void sayMap() {
        this.maoService.sendMap();

        System.out.println(applicationContext.getBean(Cat.class));
        System.out.println(applicationContext.getBean(TestService.class));
        System.out.println(applicationContext.containsBean("Cat"));
    }

    @Test
    public void sayList() {
        this.maoService.sendList();
    }
}
