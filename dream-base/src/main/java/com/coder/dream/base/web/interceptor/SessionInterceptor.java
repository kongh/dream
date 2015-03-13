package com.coder.dream.base.web.interceptor;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.UrlPathHelper;

import com.coder.dream.base.web.session.SessionManager;

public class SessionInterceptor extends HandlerInterceptorAdapter{

	private UrlPathHelper urlPathHelper = new UrlPathHelper();
	
	private List<String> excludes;
	
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		String url = urlPathHelper.getLookupPathForRequest(request);
		if(!isExcludes(url)){
			SessionManager.setHttpSession(request.getSession());
		}
		return true;
	}

	public List<String> getExcludes() {
		return excludes;
	}

	public void setExcludes(List<String> excludes) {
		this.excludes = excludes;
	}
	
	/**
	 * 是否排除<填充session>
	 * 
	 * @return
	 */
	public boolean isExcludes(String url){
		if(CollectionUtils.isEmpty(excludes)){
			return false;
		}
		
		boolean exclude = false;
		for (String excludeUrl : excludes) {
			Pattern p = Pattern.compile(excludeUrl);
			Matcher m = p.matcher(url);
			if (m.find()) {
				exclude = true;
				break;
			}
		}
		return exclude;
	}
}
