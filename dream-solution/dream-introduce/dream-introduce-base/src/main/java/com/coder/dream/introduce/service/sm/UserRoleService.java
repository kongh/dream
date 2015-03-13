package com.coder.dream.introduce.service.sm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.coder.dream.base.service.BaseService;
import com.coder.dream.base.web.vo.FilterMap;
import com.coder.dream.base.web.vo.OrderMap;
import com.coder.dream.introduce.entity.sm.UserRole;
import com.coder.dream.introduce.repository.sm.UserRoleDao;

@Service
@Transactional
public class UserRoleService extends BaseService<UserRole, UserRoleDao>{
	/**
	 * 保存角色资源
	 * 
	 * @param roleId
	 * @param resourceIds
	 */
	@SuppressWarnings("unchecked")
	public void saveUserRole(String userId,String[] roleIds){
		List<String> roleIdList = new ArrayList<String>(roleIds.length);
		CollectionUtils.addAll(roleIdList, roleIds);
		List<String> remainRoleIdList = dao.findRoleIdsByUserId(userId);
		Collection<String> bothHadList = CollectionUtils.retainAll(remainRoleIdList, roleIdList);//并集
		remainRoleIdList.removeAll(bothHadList);
		roleIdList.removeAll(bothHadList);
		
		if(CollectionUtils.isNotEmpty(remainRoleIdList)){
			FilterMap filterMap = new FilterMap();
			filterMap.eq("userId", userId);
			filterMap.in("roleId", remainRoleIdList);
			OrderMap orderMap = new OrderMap();
			List<UserRole> entities = list(filterMap, orderMap);
			dao.delete(entities);
		}
		
		for (String roleId : roleIdList) {
			UserRole userRole = new UserRole();
			userRole.setUserId(userId);
			userRole.setRoleId(roleId);
			dao.save(userRole);
		}		
	}
	
	/**
	 * 查找角色Id集合
	 * 
	 * @param userId
	 * @return
	 */
	public List<String> findRoleIdsByUserId(String userId){
		return dao.findRoleIdsByUserId(userId);
	}
}
