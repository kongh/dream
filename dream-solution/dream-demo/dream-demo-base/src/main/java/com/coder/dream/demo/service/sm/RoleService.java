package com.coder.dream.demo.service.sm;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.coder.dream.base.service.BaseService;
import com.coder.dream.demo.entity.sm.Role;
import com.coder.dream.demo.repository.sm.RoleDao;

@Service
@Transactional
public class RoleService extends BaseService<Role, RoleDao>{

}
