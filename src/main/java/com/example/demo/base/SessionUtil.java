package com.example.demo.base;

import com.example.demo.config.Consants;
import com.example.demo.config.redis.RedisUtil;
import com.example.demo.entity.User;
import com.example.demo.utils.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: ChangYu
 * @Date: 3/19/2019 3:13 PM
 * @Version 1.0
 */
@Service
public class SessionUtil {

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 获取当前用户信息
     * @param request
     * @return
     */
    public User getOnlineUser(HttpServletRequest request){

        return JSONUtil.toBean(redisUtil.get(request.getHeader("token")),User.class);

    }
}
