package com.example.demo.aop;

import com.example.demo.base.BaseController;
import com.example.demo.config.mybatis.DBTypeEnum;
import com.example.demo.config.mybatis.DbContextHolder;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Author: ChangYu
 * @Date: 3/28/2019 9:46 AM
 * @Version 1.0
 */
@Aspect
@Order(-1)
@Component
public class DataSourceSwitchAspect {
    public static Logger logger = LoggerFactory.getLogger(DataSourceSwitchAspect.class);

    @Pointcut("execution(* com.example.demo.dao.db1..*.*(..))")
    private void db1Aspect() {
    }

    @Pointcut("execution(* com.example.demo.dao.db2..*.*(..))")
    private void db2Aspect() {
    }

    @Before("db1Aspect()")
    public void db1() {
        logger.info("切换到db1 数据源...");
        DbContextHolder.setDbType(DBTypeEnum.db1);
    }

    @Before("db2Aspect()")
    public void db2() {
        logger.info("切换到db2 数据源...");
        DbContextHolder.setDbType(DBTypeEnum.db2);
    }

}
