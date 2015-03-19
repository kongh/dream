package com.coder.dream.introduce.web.title;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coder.dream.base.web.title.TitleLoader;
import com.coder.dream.introduce.service.sm.ParamService;

@Component("titleLoader")
public class DefaultTitleLoader implements TitleLoader{

	@Autowired
	private ParamService paramService;
	
	@Override
	public String getTitle() {
		String systemTitle = paramService.getSystemTitle();
		return systemTitle == null ? "请设置标题" : systemTitle;
	}

}
