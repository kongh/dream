package com.coder.dream.base.web.tag;

import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang3.StringUtils;

import com.coder.dream.base.web.session.SessionManager;
import com.coder.dream.base.web.util.AntPathRequestMatcher;

/**
 * 资源认证标签
 * 
 * @author konghang
 *
 */
public class ResourceAuthTag extends TagSupport{

	private String resource;
	
	private static final long serialVersionUID = 5353863833491658954L;

	@SuppressWarnings("unchecked")
	@Override
	public int doStartTag() throws JspException {
		Object activeResourceObj = SessionManager.getActiveResource();
		if(activeResourceObj == null || StringUtils.isBlank(resource)) return SKIP_BODY;
		
		boolean matches = false;
		List<String> activeResources = (List<String>) activeResourceObj;
		for (String activeResource : activeResources) {
			AntPathRequestMatcher matcher = new AntPathRequestMatcher(activeResource);
			if(matcher.matches(resource)){
				matches = true;
				break;
			}
		}
		if(matches){
			return EVAL_BODY_INCLUDE;
		}else {
			return SKIP_BODY;
		}
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}
}
