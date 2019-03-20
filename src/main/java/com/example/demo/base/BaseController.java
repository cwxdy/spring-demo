package com.example.demo.base;

import com.example.demo.dto.GeneralResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
/**
 * @Author: ChangYu
 * @Version 1.0
 */
@RestController
public class BaseController {
    public static Logger logger = LoggerFactory.getLogger(BaseController.class);
    /**
     * 所有异常统一格式返回
     * @param except
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public GeneralResponseDto runtimeExceptionHandler(RuntimeException except) {
        except.printStackTrace();
        return new GeneralResponseDto(-1, except.getMessage(), null);
    }
}
