package com.example.demo.controller;
import com.example.demo.base.BaseController;
import com.example.demo.base.SessionUtil;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import com.example.demo.serviceImp.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.GeneralResponseDto;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author: ChangYu
 * @Version 1.0
 */
@Api(tags = {"用户相关接口"})
@RestController
@RequestMapping(value = "/api/")
public class LoginController extends BaseController {

	@Autowired
	private UserService userService;


	@ApiOperation(value="登录")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "username", value = "username", required = true),
			@ApiImplicitParam(paramType = "query", name = "password", value = "password", required = true)
	})
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public GeneralResponseDto login(@RequestParam(value = "username") String username,
									@RequestParam(value = "password") String password, HttpServletRequest request) {
		logger.info("username:"+username);
		return userService.login(request,username,password);
	}


	@ApiOperation(value="注册")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "body", name = "User", value = "User", required = true)
	})
	@RequestMapping(value = "/regist",method = RequestMethod.POST)
	public GeneralResponseDto regist(@RequestBody User user) {
		return userService.regist(user);
	}

	@ApiOperation(value="查询")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "pageNum", value = "pageNum", required = true),
			@ApiImplicitParam(paramType = "query", name = "pageSize", value = "pageSize", required = true)
	})
	@RequestMapping(value = "/findUsers",method = RequestMethod.POST)
	public GeneralResponseDto findUsers(@RequestParam(value = "pageNum") int  pageNum,
										@RequestParam(value = "pageSize") int  pageSize) {

		return GeneralResponseDto.addSuccess(userService.findAllUser(pageNum,pageSize));
	}
}