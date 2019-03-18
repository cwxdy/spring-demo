package com.example.demo.serviceImp;


import com.example.demo.dao.UserDao;
import com.example.demo.dto.GeneralResponseDto;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @Author: ChangYu
 * @Version 1.0
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public GeneralResponseDto doAction_1(String name,String dept){
        List<User> users=userDao.findAll();
        return GeneralResponseDto.addSuccess(users);
    }
}
