package com.coder.dream.demo.service.sm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.coder.dream.base.service.BaseService;
import com.coder.dream.base.web.vo.FilterMap;
import com.coder.dream.base.web.vo.OrderMap;
import com.coder.dream.demo.entity.sm.RoleResource;
import com.coder.dream.demo.repository.sm.RoleResourceDao;

@Service
@Transactional
public class RoleResourceService extends BaseService<RoleResource, RoleResourceDao>{

	/**
	 * 保存角色资源
	 * 
	 * @param roleId
	 * @param resourceIds
	 */
	@SuppressWarnings("unchecked")
	public void saveRoleResource(String roleId,String[] resourceIds){
		List<String> resourceIdList = new ArrayList<String>(resourceIds.length);
		CollectionUtils.addAll(resourceIdList, resourceIds);
		List<String> remainResourceIdList = dao.findResourceIdsByRoleId(roleId);
		Collection<String> bothHadList = CollectionUtils.retainAll(remainResourceIdList, resourceIdList);//并集
		remainResourceIdList.removeAll(bothHadList);
		resourceIdList.removeAll(bothHadList);
		
		if(CollectionUtils.isNotEmpty(remainResourceIdList)){
			FilterMap filterMap = new FilterMap();
			filterMap.eq("roleId", roleId);
			filterMap.in("resourceId", remainResourceIdList);
			OrderMap orderMap = new OrderMap();
			List<RoleResource> entities = list(filterMap, orderMap);
			dao.delete(entities);
		}
		
		for (String resourceId : resourceIdList) {
			RoleResource roleResource = new RoleResource();
			roleResource.setRoleId(roleId);
			roleResource.setResourceId(resourceId);
			dao.save(roleResource);
		}
	}
	
	public List<String> findResourceIdsByRoleId(String roleId){
		return dao.findResourceIdsByRoleId(roleId);
	}
}
