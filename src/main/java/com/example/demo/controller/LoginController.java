package com.example.demo.controller;
import com.example.demo.base.BaseController;
import com.example.demo.serviceImp.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.GeneralResponseDto;

import javax.servlet.http.HttpServletRequest;

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
		return userService.login(request,username,password);
	}


	@ApiOperation(value="注册")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "username", value = "username", required = true),
			@ApiImplicitParam(paramType = "query", name = "password", value = "password", required = true)
	})
	@RequestMapping(value = "/regist",method = RequestMethod.POST)
	public GeneralResponseDto regist(@RequestParam(value = "username") String username,
									@RequestParam(value = "password") String password) {
		return userService.regist(username,password);
	}

	@ApiOperation(value="测试")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "username", value = "username", required = true),
			@ApiImplicitParam(paramType = "query", name = "password", value = "password", required = true)
	})
	@RequestMapping(value = "/testToken",method = RequestMethod.POST)
	public GeneralResponseDto testToken(@RequestParam(value = "username") String username,
										@RequestParam(value = "password") String password) {
		System.out.println("username:"+username+";password:"+password);
		return GeneralResponseDto.addSuccess(null);
	}
}