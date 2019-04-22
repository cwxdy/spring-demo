package com.example.demo.base;
import com.example.demo.dto.GeneralResponseDto;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

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
    @ExceptionHandler(Exception.class)
    public GeneralResponseDto runtimeExceptionHandler(Exception except) {
        except.printStackTrace();
        if(except instanceof UnauthorizedException){
            return GeneralResponseDto.addError(-1, "权限不足");
        }else{
            return GeneralResponseDto.addError(-1, except.getMessage());
        }
    }
    /**
     * 这里只是示例，将来shiro配置那里配置页面地址
     * @return
     */
    @RequestMapping(value = "/notLogin", method = RequestMethod.GET)
    public void notLogin() { throw new RuntimeException("请先登录"); }

}
