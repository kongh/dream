package com.coder.dream.introduce.service.sm;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.coder.dream.base.service.BaseService;
import com.coder.dream.introduce.entity.sm.Param;
import com.coder.dream.introduce.repository.sm.ParamDao;

@Service
@Transactional
public class ParamService extends BaseService<Param, ParamDao>{
}
