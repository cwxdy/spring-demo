package com.example.demo.config.mybatis;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
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
            /**
             * 设置实体属性setter进去的值，优先级要高于自动填充的值。
             * 如果实体没有设置该属性，就给默认值，防止entity的setter值被覆盖。
             */
            this.setFieldValByName("createDate", new Date(),metaObject);
        }
        JSONObject currentUser= (JSONObject) JSONUtil.parse(SecurityUtils.getSubject().getPrincipal());
        if(null==createBy){
            this.setFieldValByName("createBy",currentUser.getStr("username"),metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        System.out.println("xxxxxxx");
        Object modifyDate = this.getFieldValByName("updateDate",metaObject);
        Object updateBy = this.getFieldValByName("updateBy",metaObject);
        if(null == modifyDate){
            this.setFieldValByName("updateDate", new Date(),metaObject);
        }
        JSONObject currentUser= (JSONObject) JSONUtil.parse(SecurityUtils.getSubject().getPrincipal());
        if(null==updateBy){
            this.setFieldValByName("updateBy",currentUser.getStr("username"),metaObject);
        }
    }
}
