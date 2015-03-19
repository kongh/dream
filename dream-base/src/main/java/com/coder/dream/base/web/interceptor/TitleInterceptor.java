package com.coder.dream.base.web.interceptor;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.coder.dream.base.utils.SpringContextHolder;
import com.coder.dream.base.web.title.TitleLoader;

/**
 * 处理系统title
 * 
 * @author konghang
 *
 */
public class TitleInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		ServletContext servletContext = request.getSession().getServletContext();
		Object sysTitleObj = servletContext.getAttribute("sysTitle");
		if(sysTitleObj == null){
			TitleLoader titleLoader = SpringContextHolder.getBean("titleLoader");
			String title = titleLoader.getTitle();
			servletContext.setAttribute("sysTitle", title);
		}
		return true;
	}
	
}
