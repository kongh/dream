package com.coder.dream.introduce.web.menu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coder.dream.base.web.menu.MenuResourceLoader;
import com.coder.dream.base.web.session.SessionManager;
import com.coder.dream.introduce.entity.sm.Resource;
import com.coder.dream.introduce.entity.sm.User;
import com.coder.dream.introduce.service.sm.ResourceService;

@Component(value="menuResourceLoader")
public class IntroduceMenuResourceLoader implements MenuResourceLoader{

	@Autowired
	private ResourceService resourceService;

	@Override
	public Map<String, Object> load() {
		Object activeResourceObj = SessionManager.getActiveUser();
		User activeUser = (User) activeResourceObj;
		List<Resource> adminMenus = resourceService.loadAuthAdminMenus(activeUser.getId());
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("adminMenus", adminMenus);
		return map;
	}
}
