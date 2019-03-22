package com.example.demo.serviceImp;
import cn.hutool.core.util.HashUtil;
import com.example.demo.base.SessionUtil;
import com.example.demo.config.redis.RedisUtil;
import com.example.demo.dao.UserDao;
import com.example.demo.dto.GeneralResponseDto;
import com.example.demo.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    @Autowired
    private SessionUtil sessionUtil;

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    public GeneralResponseDto login(String username, String password){
        // 从SecurityUtils里边创建一个 subject
         Subject subject = SecurityUtils.getSubject();
         // 在认证提交前准备 token（令牌）
         UsernamePasswordToken token = new UsernamePasswordToken(username, String.valueOf(HashUtil.mixHash(password)));
        // 执行认证登陆
         subject.login(token);
        // 根据权限，指定返回数据
         User user = userDao.findByUsername(username);
        String role=user.getRole();

        return GeneralResponseDto.addSuccess(null,role);
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
        return GeneralResponseDto.addSuccess(null,null);
    }

    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    public Page<User> findAllUser(int pageNum, int pageSize) {
        User user=sessionUtil.getCurrentUser();
        System.out.println(user.toString());
        Sort sort = new Sort(Sort.Direction.DESC,"id");
        Pageable pageable = new PageRequest(pageNum-1,pageSize,sort);
        return userDao.findAll(pageable);
    }
}
