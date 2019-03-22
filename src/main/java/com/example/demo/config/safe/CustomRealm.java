package com.example.demo.config.safe;

import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONUtil;
import com.example.demo.base.SessionUtil;
import com.example.demo.config.redis.RedisUtil;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import com.example.demo.utils.core.util.HashUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Author: ChangYu
 * @Date: 3/22/2019 9:47 AM
 * @Version 1.0
 */
public class CustomRealm extends AuthorizingRealm {
    @Autowired
    private UserDao userDao;
    @Autowired
    private SessionUtil sessionUtil;
    @Autowired
    private RedisUtil redisUtil;
    @Value("${spring.session.timeout}")
    private long timeout;

    /**
     * 获取身份验证信息
     * Shiro中，最终是通过 Realm 来获取应用程序中的用户、角色及权限信息的。
     *
     * @param authenticationToken 用户身份信息 token
     * @return 返回封装了用户信息的 AuthenticationInfo 实例
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        // 从数据库获取对应用户名密码的用户
        User user = userDao.findByUsername(token.getUsername());
        if (null == user) {
            throw new AccountException("用户名不正确");
        } else if (!user.getPassword().equals(new String((char[])token.getCredentials()))) {
            throw new AccountException("密码不正确");
        }
        HttpServletRequest request=sessionUtil.getCurrentHttpServletRequest();
        HttpSession session=request.getSession();
        redisUtil.expireKey(session.getId(), JSONUtil.toJsonStr(user),timeout, TimeUnit.MINUTES);
        return new SimpleAuthenticationInfo(token.getPrincipal(), user.getPassword(), getName());
    }

    /**
     * 获取授权信息
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获得该用户角色
        String role = userDao.findByUsername(username).getRole();
        Set<String> set = new HashSet<>();
        //需要将 role 封装到 Set 作为 info.setRoles() 的参数
        set.add(role);
        //设置该用户拥有的角色
        info.setRoles(set);
        return info;
    }
}
