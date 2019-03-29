package com.example.demo.config.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.example.demo.entity.User;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author: ChangYu
 * @Date: 3/28/2019 2:22 PM
 * @Version 1.0
 */
@Component
public class AutoTableField implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        Object createDate = this.getFieldValByName("createDate",metaObject);
        Object createBy = this.getFieldValByName("createBy",metaObject);
        if(null == createDate){
            this.setFieldValByName("createDate", new Date(),metaObject);
        }
        User user =(User)SecurityUtils.getSubject().getPrincipal();
        if(null==createBy){
            this.setFieldValByName("createBy",user.getUsername(),metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Object modifyDate = this.getFieldValByName("updateDate",metaObject);
        Object updateBy = this.getFieldValByName("updateBy",metaObject);
        if(null == modifyDate){
            this.setFieldValByName("updateDate", new Date(),metaObject);
        }
        User user =(User)SecurityUtils.getSubject().getPrincipal();
        if(null==updateBy){
            this.setFieldValByName("updateBy",user.getUsername(),metaObject);
        }
    }
}
