package com.example.demo.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONObject;

import java.util.Date;

import com.example.demo.base.BaseController;
import com.example.demo.dto.GeneralResponseDto;
import com.example.demo.entity.User;
import com.example.demo.serviceImp.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.format.annotation.DateTimeFormat;
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
	@RequestMapping(value = "/login",method = RequestMethod.POST)
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
	@RequiresRoles("admin")
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	public GeneralResponseDto save(@RequestBody User user) {
		userService.doSaveUser(user);
		return GeneralResponseDto.addSuccess();
	}

	@ApiOperation(value="删除用户")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "body", name = "user", value = "user", required = true)
	})
	@RequiresRoles("admin")
	@RequestMapping(value = "/delete",method = RequestMethod.POST)
	public GeneralResponseDto delete(@RequestBody User user) {
		userService.doDelete(user);
		return GeneralResponseDto.addSuccess();

	}


	@ApiOperation(value="查询")
	@RequiresRoles("admin")
	@RequestMapping(value = "/findUsers",method = RequestMethod.GET)
	public GeneralResponseDto findUsers(@RequestBody(required = false) JSONObject json) {
		return GeneralResponseDto.addSuccess(userService.findAllUser(json));
	}

	@RequestMapping(value = "test",method = RequestMethod.POST)
	public GeneralResponseDto test(@RequestParam(value = "startDate")Date startDate){
		Date s=DateUtil.parse(DateUtil.formatDate(startDate));
		return GeneralResponseDto.addSuccess(s);
	}
}