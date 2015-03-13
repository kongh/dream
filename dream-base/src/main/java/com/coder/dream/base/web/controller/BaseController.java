
package com.coder.dream.base.web.controller;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.coder.dream.base.entity.BaseEntity;
import com.coder.dream.base.repository.BaseDao;
import com.coder.dream.base.service.BaseService;

/**
 * 基本控制器
 * 
 * @author konghang
 *
 * @param <T>
 * @param <S>
 * @param <D>
 */
public abstract class BaseController<T extends BaseEntity,S extends BaseService<T,D>,D extends BaseDao<T,String>> {

	@Autowired
	protected S service;
	
	/**
	 * 模块页
	 * 
	 * @param params
	 * @param model
	 * @return
	 */
	@RequestMapping
	public ModelAndView index(@RequestParam Map<String,String> params,Model model){
		return new ModelAndView();
	}
	
	/**
	 * 是否新增实体
	 * 
	 * @param entity
	 * @return
	 */
	protected boolean isAdd (T entity) {
		return StringUtils.isBlank(entity.getId());
	}
}

