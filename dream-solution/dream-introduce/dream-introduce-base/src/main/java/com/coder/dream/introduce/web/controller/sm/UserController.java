package com.coder.dream.introduce.web.controller.sm;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coder.dream.base.utils.EncryptUtil;
import com.coder.dream.base.web.controller.BaseDirectController;
import com.coder.dream.base.web.vo.ResultMap;
import com.coder.dream.introduce.entity.sm.User;
import com.coder.dream.introduce.repository.sm.UserDao;
import com.coder.dream.introduce.service.sm.UserService;

@RestController
@RequestMapping(value="/sm/user")
public class UserController extends BaseDirectController<User, UserService, UserDao>{
	
	@Override
	public ResultMap create(User entity) {
		ResultMap resultMap = new ResultMap();
		boolean success = false;
		try {
			entity.setPassword(entity.getAccount());
			String encodePassword = EncryptUtil.encodeMD5String(entity.getPassword());
			entity.setPassword(encodePassword);
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(!success){
			resultMap.failure("对密码进行MD5加密编码失败!");
			return resultMap;
		}
		return super.create(entity);
	}
}
