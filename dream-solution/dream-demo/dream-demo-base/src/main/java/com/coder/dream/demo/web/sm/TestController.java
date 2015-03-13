package com.coder.dream.demo.web.sm;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/sm/test")
public class TestController {

	@RequestMapping
	public ModelAndView index(@RequestParam Map<String,String> params){
		return new ModelAndView();
	}
	
}
