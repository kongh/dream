package com.coder.dream.introduce.web.controller.sm;

import java.util.Map;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coder.dream.base.web.controller.BaseDirectController;
import com.coder.dream.base.web.vo.ResultMap;
import com.coder.dream.introduce.entity.sm.UserRole;
import com.coder.dream.introduce.repository.sm.UserRoleDao;
import com.coder.dream.introduce.service.sm.UserRoleService;

@RestController
@RequestMapping(value="/sm/userRole")
public class UserRoleController extends BaseDirectController<UserRole, UserRoleService, UserRoleDao>{

	@RequestMapping(value="/relative")
	public ResultMap relative(@RequestParam Map<String,String> params,String userId,String[] roleIds){
		if(roleIds == null) roleIds = new String[]{};
		ResultMap resultMap = new ResultMap();
		if(StringUtils.isEmpty(userId)){
			resultMap.failure("用户ID不能为空!");
			return resultMap;
		}
		
		try{
			service.saveUserRole(userId, roleIds);
			resultMap.success();
		}catch(Exception e){
			e.printStackTrace();
			resultMap.failure(e.getMessage());
		}
		return resultMap;
	}
}
