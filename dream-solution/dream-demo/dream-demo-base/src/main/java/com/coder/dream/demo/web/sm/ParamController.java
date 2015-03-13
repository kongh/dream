package com.coder.dream.demo.web.sm;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coder.dream.base.web.controller.BaseRestfulController;
import com.coder.dream.demo.entity.sm.Param;
import com.coder.dream.demo.repository.sm.ParamDao;
import com.coder.dream.demo.service.sm.ParamService;

@RestController
@RequestMapping(value="/sm/param")
public class ParamController extends BaseRestfulController<Param, ParamService, ParamDao>{	
	
}
