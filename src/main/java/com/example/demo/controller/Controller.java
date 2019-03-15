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
public class Controller {
	@Autowired
	private DoActionImp doActionImp;
	/*
	 * @PathVariable可以直接接受链接参数
	 */
	@RequestMapping(value = "/hi/{name}/{dep}")
	public GeneralResponse get1(@PathVariable String name, @PathVariable String dep) {
		System.out.println(name + "|" + dep);
		GeneralResponse httpResp=doActionImp.doAction_1(name, dep);
		return httpResp;
	}
	/*
	 * @RequestBody可以接受消息体内容
	 */
	@RequestMapping(value = "/postByJson",method=RequestMethod.POST)
	public String post2(@RequestBody String requestData) {
		JSONObject json = JSONObject.parseObject(requestData);
		System.out.println(json.getString("name"));
		System.out.println(json.getString("dep"));
		return requestData.toString();
	}

}