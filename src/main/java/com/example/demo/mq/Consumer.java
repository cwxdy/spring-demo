package com.example.demo.mq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;


/**
 * @Author: ChangYu
 * @Date: 3/22/2019 3:39 PM
 * @Version 1.0
 */
@Component
public class Consumer {
    /**
     * 消费者示例
     * @param message
     */
    @JmsListener(destination = "message")
    public void readMap(String message) {
        System.out.println(message);
    }

}
