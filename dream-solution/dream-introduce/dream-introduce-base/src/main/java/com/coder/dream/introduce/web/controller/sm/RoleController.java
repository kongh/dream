package com.coder.dream.introduce.web.controller.sm;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coder.dream.base.web.controller.BaseDirectController;
import com.coder.dream.introduce.entity.sm.Role;
import com.coder.dream.introduce.repository.sm.RoleDao;
import com.coder.dream.introduce.service.sm.RoleService;
import com.coder.dream.introduce.service.sm.UserRoleService;

@RestController
@RequestMapping(value="/sm/role")
public class RoleController extends BaseDirectController<Role, RoleService,RoleDao> {

	@Autowired
	private UserRoleService userRoleService;
	
	@Override
	protected void doAfterSelect(Map<String, String> params,Model model ,List<Role> select) {
		if(CollectionUtils.isEmpty(select)){
			return ;
		}
		
		String userId = params.get("userId");
		if(StringUtils.isNotEmpty(userId)){
			List<String> roleIds = userRoleService.findRoleIdsByUserId(userId);
			for (Role role : select) {
				role.setChecked(roleIds.contains(role.getId()));
			}
		}
	}
}
