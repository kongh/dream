package com.coder.dream.introduce.web.controller;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import com.coder.dream.base.utils.NullUtil;
import com.coder.dream.introduce.service.sm.UserService;

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
	public String auth(@RequestParam Map<String,String> params,RedirectAttributesModelMap modelMap){
		String account = params.get("account");
		String password = params.get("password");
		
		//校验
		if(NullUtil.isNull(account) || StringUtils.isBlank(account)){
			modelMap.addFlashAttribute("errorMsg", "账户不能为空！");
			return "redirect:/login";
		}else if(NullUtil.isNull(password) || StringUtils.isBlank(password)){
			modelMap.addFlashAttribute("account", account);
			modelMap.addFlashAttribute("errorMsg", "密码不能为空！");
			return "redirect:/login";
		}
		
		//鉴权
		boolean passed = userService.auth(account, password);
		if(!passed){
			modelMap.addFlashAttribute("account", account);
			modelMap.addFlashAttribute("errorMsg", "用户名或密码错误！");
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
