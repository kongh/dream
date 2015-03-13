package com.coder.dream.introduce.service.sm;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coder.dream.base.service.BaseTreeService;
import com.coder.dream.base.web.vo.FilterMap;
import com.coder.dream.introduce.entity.sm.Resource;
import com.coder.dream.introduce.repository.sm.ResourceDao;
import com.coder.dream.introduce.repository.sm.RoleResourceDao;
import com.coder.dream.introduce.repository.sm.UserRoleDao;

@Service
@Transactional
public class ResourceService extends BaseTreeService<Resource, ResourceDao>{

	@Autowired
	private UserRoleDao userRoleDao;
	
	@Autowired
	private RoleResourceDao roleResourceDao;
	
	@Override
	protected Resource createRoot() {
		Resource resource = new Resource();
		resource.setName("资源根节点");
		resource.setCode("root");
		return resource;
	}
	
	/**
	 * 加载认证后台管理菜单（树结构）
	 * 
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Resource> loadAuthAdminMenus(String userId){
		List<String> roleIds = userRoleDao.findRoleIdsByUserId(userId);
		if(CollectionUtils.isEmpty(roleIds)){
			return new ArrayList<Resource>(0);
		}
		List<String> resourceIds = roleResourceDao.findResourceIdsByRoleIds(roleIds);
		if(CollectionUtils.isEmpty(resourceIds)){
			return new ArrayList<Resource>(0);
		}
		Resource root = getRoot();
		FilterMap filterMap = new FilterMap();
		filterMap.eq("type", "adminMenu");
		filterMap.in("id", resourceIds);
		tree(root, filterMap);
		Object data = root.getData();
		return data == null ? new ArrayList<Resource>(0) : (List<Resource>) data;
	}
}
