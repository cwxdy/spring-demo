package com.example.demo.base;

import cn.hutool.core.io.FileUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.example.demo.dto.GeneralResponseDto;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

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
        logger.info(except.getMessage());
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
    public void notLogin() { throw new ServiceException("请先登录"); }

    /**
     * excel模板下载
     * @param response
     * @param filename
     * @throws IOException
     */
    @ApiOperation(value="模板下载",produces="application/octet-stream")
    @RequestMapping(value = "/downloadTemplate", method = RequestMethod.GET)
    public void downloadTempate(HttpServletResponse response, @RequestParam(value ="filename") String filename) throws IOException {
        String path=Consants.TEMPATE_PATH_PREFIX+String.format("%s.xlsx",filename);
        boolean exist = FileUtil.exist(path);
        if(!exist){
            throw new ServiceException("文件模板不存在");
        }
        ExcelWriter writer= ExcelUtil.getWriter(path);
        response.setContentType("application/octet-stream;charset=utf-8");
        response.setHeader("Content-Disposition", String.format("attachment;filename=%s.xlsx", filename));
        OutputStream out = response.getOutputStream();
        writer.flush(out);
        writer.close();
        out.close();
    }

}
