package com.coder.dream.demo.web;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/main")
public class MainController {

	@RequestMapping
	public ModelAndView main(@RequestParam Map<String,String> params,Model model){
		return new ModelAndView();
	}
	
	@RequestMapping(value="/header")
	public ModelAndView header(@RequestParam Map<String,String> params,Model model){
		return new ModelAndView("header");
	}
}
