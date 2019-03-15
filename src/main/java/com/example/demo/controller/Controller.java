package com.example.demo.controller;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.serviceImp.DoActionImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.GeneralResponse;

@RestController
@RequestMapping(value = "/api")
public class Controller {

	@Autowired
	private DoActionImp doActionImp;

	@RequestMapping(value = "/hi/{name}/{dep}",method = RequestMethod.GET)
	public GeneralResponse get1(@PathVariable String name, @PathVariable String dep) {
		System.out.println(name + "|" + dep);
		return doActionImp.doAction_1(name, dep);
	}


	@RequestMapping(value = "/postByJson",method=RequestMethod.POST)
	public GeneralResponse post2(@RequestBody JSONObject json) {
		System.out.println(json.getString("name"));
		System.out.println(json.getString("dep"));
		return GeneralResponse.addSuccess(json);
	}

}