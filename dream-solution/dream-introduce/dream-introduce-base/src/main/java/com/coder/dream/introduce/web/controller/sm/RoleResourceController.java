package com.coder.dream.introduce.web.controller.sm;

import java.util.Map;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coder.dream.base.web.controller.BaseDirectController;
import com.coder.dream.base.web.vo.ResultMap;
import com.coder.dream.introduce.entity.sm.RoleResource;
import com.coder.dream.introduce.repository.sm.RoleResourceDao;
import com.coder.dream.introduce.service.sm.RoleResourceService;

@RestController
@RequestMapping(value="/sm/roleResource")
public class RoleResourceController extends BaseDirectController<RoleResource, RoleResourceService, RoleResourceDao>{


	@RequestMapping(value="/relative")
	public ResultMap relative(@RequestParam Map<String,String> params,String roleId,String[] resourceIds){
		if(resourceIds == null) resourceIds = new String[]{};
		ResultMap resultMap = new ResultMap();
		if(StringUtils.isEmpty(roleId)){
			resultMap.failure("角色ID不能为空!");
			return resultMap;
		}
		
		try{
			service.saveRoleResource(roleId, resourceIds);
			resultMap.success();
		}catch(Exception e){
			e.printStackTrace();
			resultMap.failure(e.getMessage());
		}
		return resultMap;
	}
}
