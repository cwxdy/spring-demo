package com.example.demo;

import com.example.demo.aop.Demo;

/**
 * @Author: ChangYu
 * @Date: 5/22/2019 3:46 PM
 * @Version 1.0
 */
public class Test {
    public static void main(String args[]){
        System.out.println(Demo.ALIPAY.getIndex());
        System.out.println(Demo.ALIPAY.getName());
    }
}
