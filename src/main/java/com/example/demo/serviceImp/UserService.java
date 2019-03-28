package com.example.demo.serviceImp;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.example.demo.config.redis.RedisUtil;
import com.example.demo.dao.UserDao;
import com.example.demo.dto.GeneralResponseDto;
import com.example.demo.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

import static cn.hutool.crypto.digest.DigestUtil.md5Hex;

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
    public GeneralResponseDto login(String username, String password){
        // 从SecurityUtils里边创建一个 subject
         Subject subject = SecurityUtils.getSubject();
         // 在认证提交前准备 token（令牌）
         UsernamePasswordToken token = new UsernamePasswordToken(username, md5Hex(password));
        // 执行认证登陆
         subject.login(token);
        // 根据权限，指定返回数据
         User user = userDao.findByUsername(username);
        String role=user.getRole();
        return GeneralResponseDto.addSuccess(null,role);
    }


    /**
     * 保存用户
     * @return
     */
    public GeneralResponseDto doSaveUser(User user) {
        user.setPassword(md5Hex(user.getPassword()));
        if(user.getId()!=null){
            userDao.updateById(user);
        }else{
            if(userDao.selectOne(user)!=null){
                throw new RuntimeException("用户名已存在");
            }
            userDao.insert(user);
        }
        return GeneralResponseDto.addSuccess();
    }


    /**
     * 分页查询
     * @return
     */
    public Page<User> findAllUser(JSONObject json) {
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        int pageNo=json.getInt("pageNo",1);
        int pageSize=json.getInt("pageSize",99999);
        Page<User> page = new Page<>(pageNo,pageSize);
        User user= JSONUtil.toBean(json,User.class);
        /**
         * 简单分页查询可以直接用通用方法
         */
        EntityWrapper<User> wrapper=new EntityWrapper<>();
        if(!StringUtils.isEmpty(user.getUsername())){
            wrapper.like("username",user.getUsername());
        }
        if(!StringUtils.isEmpty(user.getRealname())){
            wrapper.like("realname",user.getRealname());
        }
        if(!StringUtils.isEmpty(user.getPhone())){
            wrapper.like("phone",user.getPhone());
        }
        List<User> userDomains = userDao.selectPage(page,wrapper);
        page.setRecords(userDomains);
        return page;
    }
}
