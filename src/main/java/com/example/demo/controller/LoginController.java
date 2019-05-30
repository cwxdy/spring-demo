package com.example.demo.controller;

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
	@RequiresRoles("admin")
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	public GeneralResponseDto save(@RequestBody User user) {
		userService.doSaveUser(user);
		return GeneralResponseDto.addSuccess();
	}

	@ApiOperation(value="删除用户")
	@RequiresRoles("admin")
	@RequestMapping(value = "/delete",method = RequestMethod.POST)
	public GeneralResponseDto delete(int id) {
		userService.doDelete(id);
		return GeneralResponseDto.addSuccess();

	}


	@ApiOperation(value="查询")
	@RequiresRoles("admin")
	@RequestMapping(value = "/findUsers",method = RequestMethod.GET)
	public GeneralResponseDto findUsers(@RequestParam(value = "username",required = false)String username,
										@RequestParam(value = "phone",required = false)String phone,
										@RequestParam(value = "realname",required = false)String realname,
										@RequestParam(value = "status",required = false)String status,
										@RequestParam(value = "email",required = false)String email,
										@RequestParam(value = "pageNo",required = false,defaultValue = "1")int pageNo,
										@RequestParam(value = "pageSize",required = false,defaultValue = "999")int pageSize
										) {
		return GeneralResponseDto.addSuccess(userService.findAllUser(username,phone,realname,status,email,pageNo,pageSize));
	}
}