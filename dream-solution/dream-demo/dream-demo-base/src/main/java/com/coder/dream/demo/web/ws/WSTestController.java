package com.coder.dream.demo.web.ws;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.coder.dream.base.web.vo.ResultMap;
import com.coder.dream.demo.service.ws.IsmpEngineServiceProxy;
import com.coder.dream.ws.Response;
import com.coder.dream.ws.ServiceConsumeNotifyReq;

@RestController
@RequestMapping(value="/ws/test")
public class WSTestController {

	@Autowired
	private IsmpEngineServiceProxy proxy;
	
	@RequestMapping
	public ModelAndView index(@RequestParam Map<String,String> params){
		return new ModelAndView();
	}
	
	@RequestMapping(value="/notify")
	public ResultMap notify(String linkID,String productID){
		ServiceConsumeNotifyReq req = new ServiceConsumeNotifyReq();
		req.setLinkID(linkID);
		req.setProductID(productID);
		Response response = proxy.getIsmpEngine().notifyServiceConsume(req);
		
		ResultMap resultMap = new ResultMap();
		resultMap.success(response);
		return resultMap;
	}
}
