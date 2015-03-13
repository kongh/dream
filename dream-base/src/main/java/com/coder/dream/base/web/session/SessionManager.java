package com.coder.dream.base.web.session;

import javax.servlet.http.HttpSession;

import com.coder.dream.base.constant.BaseConstant;
import com.coder.dream.base.utils.NullUtil;

public class SessionManager {

	private static ThreadLocal<HttpSession> local = new ThreadLocal<HttpSession>();

	public static HttpSession getHttpSession() {
		if (NullUtil.isNull(local)) {
			return null;
		} else {
			if (NullUtil.isNull(local.get())) {
				return null;
			} else {
				return local.get();
			}
		}
	}

	public static void setHttpSession(HttpSession httpSession) {
		if (NullUtil.isNotNull(getHttpSession())) {
			local.remove();
		}
		local.set(httpSession);
	}

	private static Object getAttribute(String key) {
		if (NullUtil.isNotNull(getHttpSession())) {
			return getHttpSession().getAttribute(key);
		} else {
			return null;
		}
	}

	private static void setAttribute(String key, Object value) {
		if (NullUtil.isNotNull(getHttpSession())) {
			getHttpSession().setAttribute(key, value);
		}
	}

	private static void removeAttribute(String key) {
		if (NullUtil.isNotNull(getHttpSession())) {
			getHttpSession().removeAttribute(key);
		}
	}

	public static Object getLoginCaptcha() {
		return getAttribute(BaseConstant.LOGINCAPTCHA);
	}

	public static void setLoginCaptcha(Object value) {
		setAttribute(BaseConstant.LOGINCAPTCHA, value);
	}

	public static void removeLoginCaptcha() {
		removeAttribute(BaseConstant.LOGINCAPTCHA);
	}

	public static Object getActiveUser() {
		return getAttribute(BaseConstant.ACTIVEUSER);
	}

	public static void setActiveUser(Object value) {
		setAttribute(BaseConstant.ACTIVEUSER, value);
	}

	public static void removeActiveUser() {
		removeAttribute(BaseConstant.ACTIVEUSER);
	}

	public static Object getActiveResource() {
		return getAttribute(BaseConstant.ACTIVERESOURCE);
	}

	public static void setActiveResource(Object value) {
		setAttribute(BaseConstant.ACTIVERESOURCE, value);
	}

	public static void removeActiveResource() {
		removeAttribute(BaseConstant.ACTIVERESOURCE);
	}

}
