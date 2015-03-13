package com.coder.dream.introduce.web.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coder.dream.base.web.session.SessionManager;
import com.coder.dream.base.web.vo.ResultMap;

@Controller
@RequestMapping(value="/logout")
public class LogoutController {

	@RequestMapping
	@ResponseBody
	public ResultMap unauth(@RequestParam Map<String,String> params){
		ResultMap resultMap = new ResultMap();
		try{
			SessionManager.setActiveUser(null);
			SessionManager.setActiveResource(null);
			resultMap.success();
		}catch(Exception e){
			e.printStackTrace();
			resultMap.failure("退出失败");
		}
		return resultMap;
	}
}
