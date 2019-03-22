package com.example.demo.controller;

import com.example.demo.base.BaseController;
import com.example.demo.dto.GeneralResponseDto;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ChangYu
 * @Date: 3/22/2019 4:02 PM
 * @Version 1.0
 */
@Api(description = "MQ")
@RestController
@RequestMapping("/mq")
public class MqController extends BaseController {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @RequestMapping(value = "/sendMessage",method = RequestMethod.GET)
    public GeneralResponseDto sendMessage(@RequestParam(value = "topic") String topic,
                                          @RequestParam(value = "message")String message){
        jmsMessagingTemplate.convertAndSend(topic,message);
        return GeneralResponseDto.addSuccess();
    }
}
