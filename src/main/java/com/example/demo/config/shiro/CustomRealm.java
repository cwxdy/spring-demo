package com.example.demo.config.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.dao.RoleDao;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import cn.hutool.core.map.MapUtil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author: ChangYu
 * @Date: 3/22/2019 9:47 AM
 * @Version 1.0
 */
public class CustomRealm extends AuthorizingRealm {
    @Autowired
    private UserDao userDao;
    @Value("${spring.session.timeout}")
    private long timeout;

    /**
     * 获取身份验证信息 Shiro中，最终是通过 Realm 来获取应用程序中的用户、角色及权限信息的。
     *
     * @param authenticationToken 用户身份信息 token
     * @return 返回封装了用户信息的 AuthenticationInfo 实例
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        // 从数据库获取对应用户名密码的用户
        User user = userDao.findByUsername(token.getUsername());
        if (null == user) {
            throw new AccountException("用户名不正确");
        } else if (!user.getPassword().equals(new String((char[]) token.getCredentials()))) {
            throw new AccountException("密码不正确");
        }
        return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
    }

    /**
     * 获取授权信息
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获得该用户角色及权限
        Map<String,String> role =userDao.findRoleByUserId(user.getId());
        info.addRole(MapUtil.getStr(role, "role"));
        info.setStringPermissions((new HashSet<>(Arrays.asList(MapUtil.getStr(role, "permission_name").split(",")))));
        return info;
    }
}
