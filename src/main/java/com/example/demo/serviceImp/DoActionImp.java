package com.example.demo.serviceImp;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.demo.entity.GeneralResponse;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class DoActionImp  {

	public GeneralResponse doAction_1(String arg1, String arg2){
		// TODO Auto-generated method stub
		
		System.out.println(arg1);
		Map<String,String> map=new HashMap<String,String>(){{
			put("name",arg1);
			put("dept",arg2);
		}};
		return  GeneralResponse.addSuccess(map);
	}

}
