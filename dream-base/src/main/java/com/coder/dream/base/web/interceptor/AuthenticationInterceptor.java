package com.coder.dream.base.web.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.UrlPathHelper;

import com.coder.dream.base.utils.NullUtil;
import com.coder.dream.base.web.session.SessionManager;
import com.coder.dream.base.web.util.AntPathRequestMatcher;

public class AuthenticationInterceptor extends HandlerInterceptorAdapter{

	private UrlPathHelper urlPathHelper = new UrlPathHelper();
	
	//全局排除认证资源
	private List<String> excludes;
	
	//全局认证资源
	private List<String> includes;
	
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		String url = urlPathHelper.getLookupPathForRequest(request);
		if(!isAuthResourceOfExcludes(request)){
			Object activeUserObj = SessionManager.getActiveUser();
			//用户鉴权
			if(NullUtil.isNull(activeUserObj)){
				doAfterAuthFailed(request, response, url,HttpServletResponse.SC_UNAUTHORIZED);
				return false;
			}else{
				//资源鉴权
				if(!isAuthResource(request)){
					doAfterAuthFailed(request, response, url,HttpServletResponse.SC_FORBIDDEN);
					return false;
				}
			}
		}
		return true;
	}
	
	protected boolean isAuthResourceOfExcludes(HttpServletRequest request){
		return matchedResource(request, excludes);
	}
	
	protected boolean isAuthResourceOfIncludes(HttpServletRequest request){
		return matchedResource(request, includes);
	}
	
	protected boolean isAuthResource(HttpServletRequest request){
		return isAuthResourceOfIncludes(request) || isAuthResourceOfActiveResources(request);
	}
	
	@SuppressWarnings("unchecked")
	protected boolean isAuthResourceOfActiveResources(HttpServletRequest request){
		Object activeResourceObj = SessionManager.getActiveResource();
 		List<String> activeResources = (List<String>) activeResourceObj;
		return matchedResource(request, activeResources);
	}
	
	protected boolean matchedResource(HttpServletRequest request,List<String> urlResources){
		if(CollectionUtils.isEmpty(urlResources)){
			return false;
		}
		
		boolean matched = false;
		for (String resource : urlResources) {
			AntPathRequestMatcher matcher = new AntPathRequestMatcher(resource);
			if(matcher.matches(request)){
				matched = true;
			}
		}
		return matched;
	}
	
	protected void doAfterAuthFailed(HttpServletRequest request,HttpServletResponse response,String url,int status) throws Exception{
		// 获取herder
		String header = request.getHeader("x-requested-with");
		
		if (NullUtil.isNotNull(header) && header.equals("XMLHttpRequest")) {
			if (url.contains("mobile")) {
				response.setContentType("application/json;charset=UTF-8");
				response.getWriter().print("{\"success\":false,\"errorMessage\":\""+status+"\",\"data\":{}}");
			} else {
				response.setContentType("text/html;charset=UTF-8");
				if(HttpServletResponse.SC_UNAUTHORIZED == status){
					response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "unauthorized");
				}else{
					response.sendError(HttpServletResponse.SC_FORBIDDEN, "forbidden");
				}
			}
		} else {
			String path = request.getContextPath();
			response.sendRedirect(path + "/login");
		}

	}

	public List<String> getExcludes() {
		return excludes;
	}

	public void setExcludes(List<String> excludes) {
		this.excludes = excludes;
	}
	
	public List<String> getIncludes() {
		return includes;
	}

	public void setIncludes(List<String> includes) {
		this.includes = includes;
	}
}
