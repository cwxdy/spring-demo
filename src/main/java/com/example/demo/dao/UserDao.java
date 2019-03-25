package com.example.demo.dao;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @Author: ChangYu
 * @Version 1.0
 */
@Mapper
public interface UserDao {
    List<User> findByExample(User user);
    void updateEntity(User user);
    void insertEntity(User user);
    @Select("SELECT * FROM user where username=#{username}")
    User findByUsername(String username);
}
