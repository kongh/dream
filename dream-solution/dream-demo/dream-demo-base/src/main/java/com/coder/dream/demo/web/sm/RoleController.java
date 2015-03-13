package com.coder.dream.demo.web.sm;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coder.dream.base.web.controller.BaseRestfulController;
import com.coder.dream.demo.entity.sm.Role;
import com.coder.dream.demo.repository.sm.RoleDao;
import com.coder.dream.demo.service.sm.RoleService;
import com.coder.dream.demo.service.sm.UserRoleService;

@RestController
@RequestMapping(value="/sm/role")
public class RoleController extends BaseRestfulController<Role, RoleService,RoleDao> {

	@Autowired
	private UserRoleService userRoleService;
	
	@Override
	protected void doAfterCheckedList(List<Role> list,Map<String,String> params) {
		doCheckedRoles(list, params);
	}
	
	private void doCheckedRoles(List<Role> list,Map<String,String> params){
		if(CollectionUtils.isEmpty(list)){
			return ;
		}
		
		String userId = params.get("userId");
		if(StringUtils.isNotEmpty(userId)){
			List<String> roleIds = userRoleService.findRoleIdsByUserId(userId);
			for (Role entity : list) {
				entity.setChecked(roleIds.contains(entity.getId()));
			}
		}
	}
}
