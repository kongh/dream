package com.coder.dream.introduce.web.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.coder.dream.base.web.menu.MenuResourceLoader;
import com.coder.dream.base.web.session.SessionManager;
import com.coder.dream.introduce.entity.sm.User;

@Controller
@RequestMapping(value="/main")
public class MainController {

	@Resource
	private MenuResourceLoader menuResourceLoader;
	
	@RequestMapping
	public ModelAndView main(@RequestParam Map<String,String> params,Model model){
		loadAuthUserInfo(model);
		loadAdminMenus(model);
		return new ModelAndView();
	}
	
	/**
	 * 加载认证用户信息
	 * 
	 * @param model
	 */
	protected void loadAuthUserInfo(Model model){
		Object activeUser = SessionManager.getActiveUser();
		User user = (User) activeUser;
		model.addAttribute("user", user);
	}
	
	/**
	 * 加载菜单资源
	 * 
	 * @param model
	 */
	protected void loadAdminMenus(Model model){
		Map<String, Object> map = menuResourceLoader.load();
		model.addAllAttributes(map);
	}
}
