package com.example.demo.config.mybatis;

/**
 * @Author: ChangYu
 * @Date: 3/28/2019 9:41 AM
 * @Version 1.0
 */
public enum DBTypeEnum {
    db1("db1"), db2("db2");
    private String value;

    DBTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
