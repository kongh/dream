package com.coder.dream.demo.web.sm;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coder.dream.base.web.controller.BaseRestfulTreeController;
import com.coder.dream.base.web.session.SessionManager;
import com.coder.dream.base.web.vo.ResultMap;
import com.coder.dream.demo.entity.sm.Resource;
import com.coder.dream.demo.entity.sm.User;
import com.coder.dream.demo.repository.sm.ResourceDao;
import com.coder.dream.demo.service.sm.ResourceService;
import com.coder.dream.demo.service.sm.RoleResourceService;

@RestController
@RequestMapping(value="/sm/resource")
public class ResourceController extends BaseRestfulTreeController<Resource, ResourceService, ResourceDao>{

	@Autowired
	private RoleResourceService roleResourceService;
	
	@Override
	protected void doAfterCheckedChildren(Map<String, String> params,List<Resource> children) {
		doCheckedResouces(params, children);
	}
	
	private void doCheckedResouces(Map<String, String> params,List<Resource> children){
		if(CollectionUtils.isEmpty(children)){
			return ;
		}
		
		String roleId = params.get("roleId");
		if(StringUtils.isNotEmpty(roleId)){
			List<String> resourceIds = roleResourceService.findResourceIdsByRoleId(roleId);
			for (Resource resource : children) {
				resource.setChecked(resourceIds.contains(resource.getId()));
			}
		}
	}
	
	/**
	 * 菜单
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value="/menus")
	public ResultMap menus(@RequestParam Map<String,String> params){
		ResultMap resultMap = new ResultMap();
		try{
			Object activeResourceObj = SessionManager.getActiveUser();
			User activeUser = (User) activeResourceObj;
			List<Resource> adminMenus = service.loadAuthAdminMenus(activeUser.getId());
			resultMap.success(adminMenus);
		}catch(Exception e){
			e.printStackTrace();
			resultMap.failure(e.getMessage());
		}
		return resultMap;
	}
}
