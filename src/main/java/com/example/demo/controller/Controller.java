package com.example.demo.controller;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.base.BaseController;
import com.example.demo.serviceImp.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.GeneralResponseDto;

@RestController
@RequestMapping(value = "/api")
public class Controller extends BaseController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/hi/{name}/{dep}",method = RequestMethod.GET)
	public GeneralResponseDto get1(@PathVariable String name, @PathVariable String dep) {
		if("changyu".equals(name)){
			throw new RuntimeException("测试报错");
		}
		System.out.println(name + "|" + dep);
		return userService.doAction_1(name, dep);
	}
}