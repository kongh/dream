package com.coder.dream.introduce.repository.sm;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.coder.dream.base.repository.BaseDao;
import com.coder.dream.introduce.entity.sm.UserRole;

public interface UserRoleDao extends BaseDao<UserRole, String>{

	/**
	 * 查找某用户的角色Id集合
	 * 
	 * @param userId
	 * @return
	 */
	@Query(value="select roleId from UserRole where 1 = 1 and userId = ?1")
	public List<String> findRoleIdsByUserId(String userId);
	
}
