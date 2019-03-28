package com.example.demo.controller;

import cn.hutool.json.JSONObject;
import com.example.demo.base.BaseController;
import com.example.demo.dto.GeneralResponseDto;
import com.example.demo.entity.User;
import com.example.demo.serviceImp.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: ChangYu
 * @Version 1.0
 */
@Api(description = "用户相关接口")
@RestController
@RequestMapping(value = "/api/user")
public class LoginController extends BaseController {

	@Autowired
	private UserService userService;


	@ApiOperation(value="登录")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "username", value = "username", required = true),
			@ApiImplicitParam(paramType = "query", name = "password", value = "password", required = true)
	})
	@RequestMapping(value = "/login",method = RequestMethod.GET)
	public GeneralResponseDto login(@RequestParam(value = "username") String username,
									@RequestParam(value = "password") String password) {
		return userService.login(username,password);
	}

	@ApiOperation(value="注销")
	@RequestMapping(value = "/logout",method = RequestMethod.GET)
	public GeneralResponseDto logout() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return GeneralResponseDto.addSuccess();
	}


	@ApiOperation(value="保存用户")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "body", name = "user", value = "user", required = true)
	})
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	public GeneralResponseDto save(@RequestBody User user) {
		return userService.doSaveUser(user);
	}


	@ApiOperation(value="查询")
	@RequestMapping(value = "/findUsers",method = RequestMethod.POST)
	public GeneralResponseDto findUsers(@RequestBody(required = false) JSONObject json) {

		return GeneralResponseDto.addSuccess(userService.findAllUser(json));
	}
}