package com.example.demo.base;
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
     * 这里只是示例，将来shiro配置那里配置页面地址
     * @return
     */
    @RequestMapping(value = "/notLogin", method = RequestMethod.GET)
    public void notLogin() { throw new RuntimeException("请先登录"); }

}
