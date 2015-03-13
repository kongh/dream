package com.coder.dream.introduce.service.sm;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.coder.dream.base.service.BaseService;
import com.coder.dream.introduce.entity.sm.Role;
import com.coder.dream.introduce.repository.sm.RoleDao;

@Service
@Transactional
public class RoleService extends BaseService<Role, RoleDao>{

}
