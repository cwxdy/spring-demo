package com.example.demo.serviceImp;
import com.example.demo.config.redis.RedisUtil;
import com.example.demo.dao.UserDao;
import com.example.demo.dto.GeneralResponseDto;
import com.example.demo.entity.User;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: ChangYu
 * @Version 1.0
 */
@Service
public class UserService {

    @Autowired
    private  UserDao userDao;

    public User login(String username,String password){
        User user=userDao.findByUsername(username);
        if(user==null){
            throw new RuntimeException("用户不存在");
        }else{
            // TODO: 3/19/2019  
        }
        return user;
    }


}
