package com.coder.dream.base.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy(value=false)
public class SpringContextHolder implements ApplicationContextAware{

	private static ApplicationContext applicationContext; 
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringContextHolder.applicationContext = applicationContext;
	}

	public static ApplicationContext getApplicationContext() {  
		return applicationContext;  
	} 
	
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {  
		return (T) applicationContext.getBean(name);  
	}
	
	@SuppressWarnings("unchecked")  
	public static <T> T getBean(Class<T> clazz) {  
		return (T) applicationContext.getBeansOfType(clazz);  
	} 
}
