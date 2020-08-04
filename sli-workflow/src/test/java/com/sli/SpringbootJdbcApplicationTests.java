package com.sli;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sli.entity.MyTest;
import com.sli.entity.User;
import com.sli.mapper.UserMapper;
//import com.sli.service.TestService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootJdbcApplicationTests {
//    @Autowired
//    private TestDao testDao;

//    @Test
//    public void insert() {
//        MyTest myTest = new MyTest("fulin", 18, true);
//        testDao.insert(myTest);
//    }
//
//    @Test
//    public void selectOne() {
//        System.out.println(testDao.selectOne(1L));
//    }
//
//
//    @Test
//    public void update() {
//        MyTest myTest = new MyTest(1L, "fulin", 88, true);
//        testDao.update(myTest);
//    }
//
//    @Test
//    public void delete() {
//        testDao.delete(13L);
//    }

//    @Autowired
//    private TestService testService;
//
//
//    @Test
//    public void add() throws Exception {
//        MyTest t = new MyTest();
//        t.setAge(11);
//        t.setInhere(false);
//        t.setName("DesrCat");
//        testService.add(t);
//    }
//
//    @Test
//    public void update() throws Exception {
//        MyTest t = new MyTest();
//        t.setAge(180);
//        t.setInhere(false);
//        t.setName("昴先生111");
//        t.setId(1L);
//        testService.update(t);
//    }
//
//    @Test
//    public void select() throws Exception {
//        List<MyTest> select = testService.select();
//        Assert.assertTrue(select.size() > 0);
//        System.out.println(Collections.unmodifiableCollection(select));
//    }
//
//    @Test
//    public void del() throws Exception {
//        testService.del(11L);
//    }
//
//    @Autowired
//    private RedisTemplate<String, Serializable> redisTemplate;
//
////    @Autowired
////    private StringRedisTemplate stringRedisTemplate;
//
//    @Test
//    public void setTest() {
//        redisTemplate.opsForValue().set("one","1");
////        stringRedisTemplate.opsForValue().set("two", "2");
//        MyTest t = new MyTest();
//        t.setAge(180);
//        t.setInhere(false);
//        t.setName("昴先生111");
//        t.setId(1L);
//        redisTemplate.opsForValue().set("user:1", t);
//
////        ExecutorService executorService = Executors.newFixedThreadPool(1000);
////        IntStream.range(0, 1000).forEach(i -> {
////            executorService.execute(() -> stringRedisTemplate.opsForValue().increment("num", 1));
////        });
//    }
//
//    @Test
//    public void getTest() {
//        Object one = redisTemplate.opsForValue().get("one");
//        Assert.assertEquals("1", one);
//
////        String two = stringRedisTemplate.opsForValue().get("two");
////        Assert.assertEquals("2", two);
//
//        MyTest user = (MyTest) redisTemplate.opsForValue().get("user:1");
//        Assert.assertEquals("昴先生111", user.getName());
//    }
//
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
    }

    @Test
    public void testInsert(){
        User user = new User();
        user.setName("东方不败12321");
        user.setEmail("dfbb@163.com");
        user.setAge(20);
        userMapper.insert(user);
        //mybatisplus会自动把当前插入对象在数据库中的id写回到该实体中
        System.out.println(user.getId());
    }

    @Test
    public void testDelete(){
        QueryWrapper qw = new QueryWrapper<User>();
        qw.eq("id",1);
        qw.eq("name","更新测试");
        System.out.println(userMapper.delete(qw));
    }

    @Test
    public void testUpdate(){
        List<MyTest> list = Arrays.asList(
                new MyTest(1, "fulin", "男"),
                new MyTest(2, "dog", "女"),
                new MyTest(3, "mao", "男"),
                new MyTest(4, "cat", "女")
        );

        List<MyTest> data = list;
        //old
        List<MyTest> temp = new ArrayList<>();
        for (MyTest user : data) {
            if ("男".equals(user.getSex())) {
                temp.add(user);
            }
        }
        System.out.println(temp);
        //new
        List<MyTest> collect = data
                .stream()
                .filter(user -> "男".equals(user.getSex()))
                .collect(Collectors.toList());
        System.out.println(collect);
    }
}