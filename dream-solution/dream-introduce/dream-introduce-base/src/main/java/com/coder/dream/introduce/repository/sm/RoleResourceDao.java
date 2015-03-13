package com.coder.dream.introduce.repository.sm;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.coder.dream.base.repository.BaseDao;
import com.coder.dream.introduce.entity.sm.RoleResource;

public interface RoleResourceDao extends BaseDao<RoleResource, String>{
	
	/**
	 * 查找某角色的资源Id集合
	 * 
	 * @param roleId
	 * @return
	 */
	@Query(value="select resourceId from RoleResource where 1 = 1 and roleId = ?1")
	public List<String> findResourceIdsByRoleId(String roleId);
	
	/**
	 * 查找某角色的资源Id集合
	 * 
	 * @param roleId
	 * @return
	 */
	@Query(value="select distinct resourceId from RoleResource where 1 = 1 and roleId in ?1")
	public List<String> findResourceIdsByRoleIds(List<String> roleIds);
}
