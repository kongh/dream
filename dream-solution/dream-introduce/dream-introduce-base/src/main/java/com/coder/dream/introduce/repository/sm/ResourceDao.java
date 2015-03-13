package com.coder.dream.introduce.repository.sm;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.coder.dream.base.repository.BaseTreeDao;
import com.coder.dream.introduce.entity.sm.Resource;

public interface ResourceDao extends BaseTreeDao<Resource, String>{

	/**
	 * 通过资源ID集合查找资源路径集合
	 * 
	 * @param resourceIds
	 * @return
	 */
	@Query(value="select distinct url from Resource where 1 = 1 and id in ?1 ")
	public List<String> findResourceUrlsByResourceIds(List<String> resourceIds);
}
