package com.example.demo.base;

import com.example.demo.dto.GeneralResponseDto;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @Author: ChangYu
 * @Date: 4/22/2019 10:09 AM
 * @Version 1.0
 */
@ControllerAdvice
public class BaseExceptionHandler {
    /**
     * 所有异常统一格式返回
     * @param except
     * @return
     */
    @ExceptionHandler(Exception.class)
    public GeneralResponseDto runtimeExceptionHandler(Exception except) {
        except.printStackTrace();
        if(except instanceof UnauthorizedException){
            return GeneralResponseDto.addError(-1, "权限不足");
        }else{
            return GeneralResponseDto.addError(-1, except.getMessage());
        }
    }
}
