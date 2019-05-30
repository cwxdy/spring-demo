package com.example.demo.serviceImp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.base.ServiceException;
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
        User user = (User) subject.getPrincipal();
        return GeneralResponseDto.addSuccess(user);
    }


    /**
     * 保存用户
     * @return
     */
    public void doSaveUser(User user) {
        //修改
        if(user.getId()!=null){
            userDao.update(user);
        //新增
        }else{
            if(userDao.selectOne(new QueryWrapper<User>().eq("username",user.getUsername()))!=null){
                throw new ServiceException("用户名已存在");
            }
            user.setPassword(md5Hex(user.getPassword()));
            userDao.insert(user);
        }
    }


    /**
     * 分页查询
     * @return
     */
    public Object findAllUser(String username, String phone, String realname, String status,String email, int pageNo, int pageSize) {
        Page<User> page = new Page<>(pageNo,pageSize);
        return userDao.findUsersByPage(page,username,phone,realname,status,email);
    }

    /**
     * 删除
     * @return
     */
    public void doDelete(int id) {
        userDao.deleteById(id);
    }

}
