package com.coder.dream.introduce.web.controller.sm;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coder.dream.base.web.controller.BaseDirectController;
import com.coder.dream.introduce.entity.sm.Param;
import com.coder.dream.introduce.repository.sm.ParamDao;
import com.coder.dream.introduce.service.sm.ParamService;

@RestController
@RequestMapping(value="/sm/param")
public class ParamController extends BaseDirectController<Param, ParamService, ParamDao>{	
}
