package com.coder.dream.demo.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.coder.dream.demo.service.sm.UserService;

@Controller
@RequestMapping(value="/login")
public class LoginController {

	@Autowired
	private UserService userService;
	
	/**
	 * 登陆页面
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping
	public ModelAndView index(@RequestParam Map<String,String> params){
		return new ModelAndView();
	}
	
	/**
	 * 认证
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping("/auth")
	public String auth(@RequestParam Map<String,String> params){
		String account = params.get("account");
		String password = params.get("password");
		boolean passed = userService.auth(account, password);
		if(!passed){
			return "redirect:/login";
		}
		return "redirect:/main";
	}
	
	/**
	 * 解除认证
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value="/unauth")
	public String unauth(@RequestParam Map<String,String> params){
		return "redirect:/login";
	}
}
