package com.coder.dream.demo.service.ws;

import org.springframework.stereotype.Component;

import com.coder.dream.ws.IsmpEngine;
import com.coder.dream.ws.IsmpEngineService;

@Component
public class IsmpEngineServiceProxy {

	public IsmpEngine getIsmpEngine(){
		IsmpEngineService ismpEngineService = new IsmpEngineService();
		IsmpEngine ismpEngine = ismpEngineService.getIsmpEnginePort();
		return ismpEngine;
	}
	
}
