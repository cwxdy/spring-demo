package com.example.demo.dao;

import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;

/**
 * @Author: ChangYu
 * @Version 1.0
 */
@Mapper
public interface UserDao extends BaseMapper<User> {
    @Select("SELECT * FROM t_user where username=#{username}")
    User findByUsername(String username);

    @Select("SELECT r.role,r.permission_name FROM t_user u LEFT JOIN t_role r on u.role_id=r.id where u.id=#{id}")
    Map<String,String> findRoleByUserId(Long id);
}
