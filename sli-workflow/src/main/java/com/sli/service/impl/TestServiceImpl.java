//package com.sli.service.impl;
//
//import com.sli.dao.TestDao;
//import com.sli.entity.MyTest;
//import com.sli.service.TestService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class TestServiceImpl implements TestService {
//    @Autowired
//    private TestDao testDao;
//
//
//    @Override
//    public void add(MyTest tset) throws Exception {
//        testDao.save(tset);
//    }
//
//    @Override
//    public void update(MyTest myTest) throws Exception {
//        testDao.saveAndFlush(myTest);
//    }
//
//    @Override
//    public void del(Long id) throws Exception{
//        testDao.deleteById(id);
//    }
//
//    @Override
//    public List<MyTest> select() throws Exception {
//        return testDao.findAll();
//    }
//}