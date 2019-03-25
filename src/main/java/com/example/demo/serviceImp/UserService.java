package com.example.demo.serviceImp;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.demo.config.redis.RedisUtil;
import com.example.demo.dao.UserDao;
import com.example.demo.dto.GeneralResponseDto;
import com.example.demo.entity.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
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
            userDao.updateEntity(user);
        }else{
            if(userDao.findByUsername(user.getUsername())!=null){
                throw new RuntimeException("用户名已存在");
            }
            userDao.insertEntity(user);
        }
        return GeneralResponseDto.addSuccess();
    }


    /**
     * 分页查询
     * @return
     */
    public PageInfo<User> findAllUser(JSONObject json) {
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        int pageNo=json.getInt("pageNo",1);
        int pageSize=json.getInt("pageSize",99999);
        PageHelper.startPage(pageNo-1, pageSize);
        User user= JSONUtil.toBean(json,User.class);
        List<User> userDomains = userDao.findByExample(user);
        PageInfo result = new PageInfo(userDomains);
        return result;
    }
}
