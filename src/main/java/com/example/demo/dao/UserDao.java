package com.example.demo.dao;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;

/**
 * @Author: ChangYu
 * @Version 1.0
 */
@Mapper
public interface UserDao extends BaseMapper<User> {
    @Select("SELECT * FROM user where username=#{username}")
    User findByUsername(String username);

}
