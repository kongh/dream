package com.coder.dream.demo.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.coder.dream.base.web.session.SessionManager;
import com.coder.dream.demo.entity.sm.Resource;
import com.coder.dream.demo.entity.sm.User;
import com.coder.dream.demo.service.sm.ResourceService;

@Controller
@RequestMapping(value="/frame")
public class FrameController {
	
	@Autowired
	private ResourceService resourceService;
	
	@RequestMapping(value="/main")
	public ModelAndView main(Map<String,String> params,Model model){
		Object activeResourceObj = SessionManager.getActiveUser();
		User activeUser = (User) activeResourceObj;
		List<Resource> adminMenus = resourceService.loadAuthAdminMenus(activeUser.getId());
		model.addAttribute("adminMenus", adminMenus);
		return new ModelAndView();
	}
	
	@RequestMapping(value="/left")
	public ModelAndView left(Map<String,String> params,Model model){
		return new ModelAndView();
	}
}
