package com.sli.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sli.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDao extends BaseMapper<User> {
    @Select("select * from m_user where id = #{id}")
    User findUserById(int id);
}
