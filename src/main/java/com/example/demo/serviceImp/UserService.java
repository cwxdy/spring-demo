package com.example.demo.serviceImp;
import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONUtil;
import com.example.demo.config.redis.RedisUtil;
import com.example.demo.dao.UserDao;
import com.example.demo.dto.GeneralResponseDto;
import com.example.demo.entity.User;
import com.example.demo.utils.core.util.HashUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
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
        return GeneralResponseDto.addSuccess(token);
    }


    /**
     * 注册
     * @return
     */
    public GeneralResponseDto regist(User user) {
        if(userDao.findByUsername(user.getUsername())!=null){
            throw new RuntimeException("用户名已存在");
        }
        user.setPassword(String.valueOf(HashUtil.mixHash(user.getPassword())));
        userDao.save(user);
        return GeneralResponseDto.addSuccess(null);
    }

    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    public Page<User> findAllUser(int pageNum, int pageSize) {
        Sort sort = new Sort(Sort.Direction.DESC,"id");
        Pageable pageable = new PageRequest(pageNum-1,pageSize,sort);
        return userDao.findAll(pageable);
    }
}
