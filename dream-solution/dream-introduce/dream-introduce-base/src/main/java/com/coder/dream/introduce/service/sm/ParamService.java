package com.coder.dream.introduce.service.sm;

import javax.transaction.Transactional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.coder.dream.base.repository.support.SpecificationBuildUtil;
import com.coder.dream.base.service.BaseService;
import com.coder.dream.base.web.vo.FilterMap;
import com.coder.dream.introduce.entity.sm.Param;
import com.coder.dream.introduce.repository.sm.ParamDao;

@Service
@Transactional
public class ParamService extends BaseService<Param, ParamDao>{

	public String getSystemTitle(){
		FilterMap filterMap = new FilterMap();
		filterMap.eq("code", "sys-title");
		Specification<Param> specification = SpecificationBuildUtil.buildSpecification(filterMap);
		Param param = dao.findOne(specification);
		return param == null ? null : param.getValue();
	}
}
