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
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
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
	@RequestMapping(value = "/loginout",method = RequestMethod.GET)
	public GeneralResponseDto loginout() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return GeneralResponseDto.addSuccess();
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
	@RequiresRoles(value = {"admin"},logical = Logical.OR)
	@RequestMapping(value = "/findUsers",method = RequestMethod.GET)
	public GeneralResponseDto findUsers(@RequestParam(value = "pageNum") int  pageNum,
										@RequestParam(value = "pageSize") int  pageSize) {

		return GeneralResponseDto.addSuccess(userService.findAllUser(pageNum,pageSize));
	}
}