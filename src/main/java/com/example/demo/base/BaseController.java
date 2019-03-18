package com.example.demo.base;

import com.example.demo.dto.GeneralResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
/**
 * @Author: ChangYu
 * @Version 1.0
 */
@RestController
public class BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    public static final String BUSSINESS_ERROR = "数据交互异常";

    /**
     * 所有异常统一格式返回
     * @param except
     * @param request
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public GeneralResponseDto runtimeExceptionHandler(RuntimeException except, HttpServletRequest request) {
        logger.error(except.getMessage());
        return new GeneralResponseDto(-1, BUSSINESS_ERROR, except.getMessage());
    }
}
