package com.example.demo.controller;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.base.BaseController;
import com.example.demo.serviceImp.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.GeneralResponseDto;
/**
 * @Author: ChangYu
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/api/")
public class LoginController extends BaseController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public GeneralResponseDto login(@RequestParam(value = "username") String username,
								   @RequestParam(value = "password") String password) {

		return GeneralResponseDto.addError("测试");
	}
}