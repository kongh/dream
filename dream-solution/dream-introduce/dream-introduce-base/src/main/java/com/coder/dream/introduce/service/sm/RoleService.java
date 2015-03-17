package com.coder.dream.introduce.service.sm;

import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.coder.dream.base.repository.support.SpecificationBuildUtil;
import com.coder.dream.base.service.BaseService;
import com.coder.dream.base.web.vo.FilterMap;
import com.coder.dream.introduce.entity.sm.Role;
import com.coder.dream.introduce.entity.sm.RoleResource;
import com.coder.dream.introduce.entity.sm.UserRole;
import com.coder.dream.introduce.repository.sm.RoleDao;
import com.coder.dream.introduce.repository.sm.RoleResourceDao;
import com.coder.dream.introduce.repository.sm.UserRoleDao;

@Service
@Transactional
public class RoleService extends BaseService<Role, RoleDao>{

	@Autowired
	private UserRoleDao userRoleDao;
	
	@Autowired
	private RoleResourceDao roleResourceDao;
	
	@Override
	public void delete(String id) {
		super.delete(id);
		FilterMap filterMap = new FilterMap();
		filterMap.eq("roleId", id);
		Specification<UserRole> specification = SpecificationBuildUtil.buildSpecification(filterMap);
		List<UserRole> userRoles = userRoleDao.findAll(specification);
		userRoleDao.deleteInBatch(userRoles);
		Specification<RoleResource> spec = SpecificationBuildUtil.buildSpecification(filterMap);
		List<RoleResource> roleResources = roleResourceDao.findAll(spec);
		roleResourceDao.deleteInBatch(roleResources);
	}
	
	@Override
	public void delete(String[] ids) {
		if(ids == null || ids.length == 0){
			return ;
		}
		List<Role> deleting = dao.findAll(Arrays.asList(ids));
		for (Role role : deleting) {
			delete(role.getId());
		}
	}
}
