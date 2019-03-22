package com.example.demo.base;

import com.example.demo.config.redis.RedisUtil;
import com.example.demo.entity.User;
import com.example.demo.utils.json.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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
     * @return
     */
    public User getCurrentUser(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes())
                .getRequest();
        String sessionId=request.getRequestedSessionId();
        return JSONUtil.toBean(redisUtil.get(sessionId),User.class);

    }

    /**
     * 获取当前请求
     * @return
     */
    public HttpServletRequest getCurrentHttpServletRequest(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes())
                .getRequest();
       return request;
    }
}
