package com.example.demo.serviceImp;
import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONUtil;
import com.example.demo.base.SessionUtil;
import com.example.demo.config.Consants;
import com.example.demo.config.redis.RedisUtil;
import com.example.demo.dao.UserDao;
import com.example.demo.dto.GeneralResponseDto;
import com.example.demo.entity.User;
import com.example.demo.utils.core.util.HashUtil;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Author: ChangYu
 * @Version 1.0
 */
@Service
public class UserService {

    @Autowired
    private  UserDao userDao;
    @Autowired
    private RedisUtil redisUtil;
    @Value("${spring.session.timeout}")
    private long timeout;

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    public GeneralResponseDto login(HttpServletRequest request,String username, String password){
        User user=userDao.findByUsername(username);
        if(user==null){
            throw new RuntimeException("用户不存在");
        }
        if(!String.valueOf(HashUtil.fnvHash(password)).equals(user.getPassword())){
                throw new RuntimeException("密码错误");
        }
        String token= IdUtil.randomUUID();
        redisUtil.expireKey(token, JSONUtil.toJsonStr(user),timeout, TimeUnit.MINUTES);
        request.setAttribute(Consants.CURRENT_USER_TOKEN,token);
        return GeneralResponseDto.addSuccess(token);
    }

    /**
     * 注册
     * @param username
     * @param password
     * @return
     */
    public GeneralResponseDto regist(String username, String password) {
        if(userDao.findByUsername(username)!=null){
            throw new RuntimeException("用户名已存在");
        }
        User user=new User();
        user.setPassword(String.valueOf(HashUtil.mixHash(password)));
        user.setUsername(username);
        userDao.save(user);
        return GeneralResponseDto.addSuccess(null);
    }
}
