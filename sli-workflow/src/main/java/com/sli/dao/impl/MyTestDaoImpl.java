//package com.sli.dao.impl;
//
//import com.sli.dao.MyTestDao;
//import com.sli.entity.MyTest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public class MyTestDaoImpl implements MyTestDao {
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//    @Override
//    public MyTest selectOne(Long id) {
//        String sql = "select id,name,age,inhere from Test where id=? ";
//        return jdbcTemplate.queryForObject(sql, (resultSet, i) -> {
//            MyTest myTest = new MyTest();
//            myTest.setId(resultSet.getLong("id"));
//            myTest.setName(resultSet.getString("name"));
//            myTest.setAge(resultSet.getInt("age"));
//            myTest.setInhere(resultSet.getBoolean("inhere"));
//            return myTest;
//        }, id);
//    }
//
//    @Override
//    public int insert(MyTest myTest) {
//        String sql = "insert into Test (name,age,inhere) values(?,?,?)";
//        return jdbcTemplate.update(sql, myTest.getName(), myTest.getAge(), myTest.getInhere());
//    }
//
//    @Override
//    public int delete(Long id) {
//        String sql = "delete from Test where id=?";
//        return jdbcTemplate.update(sql, id);
//    }
//
//    @Override
//    public int update(MyTest myTest) {
//        String sql = "update Test set name=?,age=?,inhere=? where id=?";
//        return jdbcTemplate.update(sql, myTest.getName(), myTest.getAge(), myTest.getInhere(), myTest.getId());
//    }
//}