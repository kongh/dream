package com.coder.dream.introduce.service.sm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.coder.dream.base.repository.support.SpecificationBuildUtil;
import com.coder.dream.base.service.BaseService;
import com.coder.dream.base.utils.EncryptUtil;
import com.coder.dream.base.utils.NullUtil;
import com.coder.dream.base.web.session.SessionManager;
import com.coder.dream.base.web.vo.FilterMap;
import com.coder.dream.introduce.entity.sm.User;
import com.coder.dream.introduce.entity.sm.UserRole;
import com.coder.dream.introduce.repository.sm.ResourceDao;
import com.coder.dream.introduce.repository.sm.RoleResourceDao;
import com.coder.dream.introduce.repository.sm.UserDao;
import com.coder.dream.introduce.repository.sm.UserRoleDao;

@Service
@Transactional
public class UserService extends BaseService<User, UserDao>{

	@Autowired
	private UserRoleDao userRoleDao;
	
	@Autowired
	private RoleResourceDao roleResourceDao;
	
	@Autowired
	private ResourceDao resourceDao;
	
	/**
	 * 用户认证
	 * 
	 * @param account
	 * @param password
	 * @return
	 */
	public boolean auth(String account,String password){
		//通过账户获取用户
		FilterMap filterMap = new FilterMap();
		filterMap.eq("account", account);
		Specification<User> specification = SpecificationBuildUtil.buildSpecification(filterMap);
		User authUser = dao.findOne(specification);
		if(NullUtil.isNull(authUser)){
			return false;
		}
		
		//比对密码
		String encodePassword = null;
		try {
			encodePassword = EncryptUtil.encodeMD5String(password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(StringUtils.isEmpty(encodePassword)){
			//加密失败，返回
			return false;
		}
		if(!StringUtils.equals(authUser.getPassword(),encodePassword)){
			//密码不匹配，返回
			return false;
		}
		SessionManager.setActiveUser(authUser);//用户鉴权信息
		SessionManager.setActiveResource(loadAuthResource(authUser.getId()));//资源鉴权信息
		return true;
	}
	
	/**
	 * 加载认证资源(url)
	 * 
	 * @param userId
	 * @return
	 */
	public Collection<String> loadAuthResource(String userId){
		List<String> roleIds = userRoleDao.findRoleIdsByUserId(userId);
		if(CollectionUtils.isEmpty(roleIds)){
			return new ArrayList<String>(0);
		}
		List<String> resourceIds = roleResourceDao.findResourceIdsByRoleIds(roleIds);
		if(CollectionUtils.isEmpty(resourceIds)){
			return new ArrayList<String>(0);
		}
		List<String> urls = resourceDao.findResourceUrlsByResourceIds(resourceIds);
		return urls;
	}
	
	@Override
	public void delete(String id) {
		super.delete(id);
		FilterMap filterMap = new FilterMap();
		filterMap.eq("userId", id);
		Specification<UserRole> specification = SpecificationBuildUtil.buildSpecification(filterMap);
		List<UserRole> userRoles = userRoleDao.findAll(specification);
		userRoleDao.deleteInBatch(userRoles);
	}
	
	@Override
	public void delete(String[] ids) {
		if(ids == null || ids.length == 0){
			return ;
		}
		List<User> deleting = dao.findAll(Arrays.asList(ids));
		for (User user : deleting) {
			delete(user.getId());
		}
	}
}
