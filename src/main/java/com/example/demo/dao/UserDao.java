package com.example.demo.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;
import java.util.List;

/**
 * @Author: ChangYu
 * @Version 1.0
 */
@Mapper
public interface UserDao extends BaseMapper<User> {
    List<User> findByExample(Pagination page, User user);
    void updateEntity(User user);
    void insertEntity(User user);
    @Select("SELECT * FROM user where username=#{username}")
    User findByUsername(String username);

}
