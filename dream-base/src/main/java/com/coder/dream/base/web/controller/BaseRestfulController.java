package com.coder.dream.base.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coder.dream.base.entity.BaseEntity;
import com.coder.dream.base.repository.BaseDao;
import com.coder.dream.base.service.BaseService;
import com.coder.dream.base.web.vo.FilterMap;
import com.coder.dream.base.web.vo.OrderMap;
import com.coder.dream.base.web.vo.ResultMap;

public abstract class BaseRestfulController <T extends BaseEntity,S extends BaseService<T,D>,D extends BaseDao<T,String>> extends BaseController<T, S, D>{

	@RequestMapping(value="/create")
	@ResponseBody
	public ResultMap create(T entity){
		return doSave(entity);
	}
	
	@RequestMapping(value="/update")
	@ResponseBody
	public ResultMap update(T entity){
		return doSave(entity);
	}
	
	protected ResultMap doSave(T entity) {
		ResultMap resultMap = new ResultMap();
		try{
			T saved = service.save(entity);
			resultMap.success(saved);
		}catch(Exception e){
			e.printStackTrace();
			resultMap.failure(e.getMessage());
		}
		return resultMap;
	}
	
	@RequestMapping(value="/get")
	@ResponseBody
	public ResultMap get(String id){
		ResultMap resultMap = new ResultMap();
		try{
			T entity = service.get(id);
			if(entity != null){
				resultMap.success(entity);
			}else{
				resultMap.failure();
			}
		}catch(Exception e){
			e.printStackTrace();
			resultMap.failure(e.getMessage());
		}
		return resultMap;
	}
	
	@RequestMapping(value = "/page")
	@ResponseBody
	public ResultMap page(@RequestParam Map<String, String> params, int start, int limit) {
		ResultMap resultMap = new ResultMap();
		try{
			FilterMap filterMap = new FilterMap();
			OrderMap orderMap = new OrderMap();
			Page<T> page = service.page(filterMap, orderMap, start, limit);
			resultMap.success(page.getContent(),page.getTotalElements());
		}catch(Exception e){
			e.printStackTrace();
			resultMap.failure(e.getMessage());
		}
		return resultMap;
	}
	
	@RequestMapping(value="/list")
	@ResponseBody
	public ResultMap list(@RequestParam Map<String,String> params){
		ResultMap resultMap = new ResultMap();
		try{
			FilterMap filterMap = new FilterMap();
			OrderMap orderMap = new OrderMap();
			List<T> list = service.list(filterMap, orderMap);
			resultMap.success(list);
		}catch(Exception e){
			e.printStackTrace();
			resultMap.failure(e.getMessage());
		}
		return resultMap;
	}
	
	@RequestMapping(value="/checkedList")
	@ResponseBody
	public ResultMap checkedList(@RequestParam Map<String,String> params){
		ResultMap resultMap = new ResultMap();
		try{
			FilterMap filterMap = new FilterMap();
			OrderMap orderMap = new OrderMap();
			List<T> list = service.list(filterMap, orderMap);
			doAfterCheckedList(list,params);
			resultMap.success(list);
		}catch(Exception e){
			e.printStackTrace();
			resultMap.failure(e.getMessage());
		}
		return resultMap;
	}
	
	protected void doAfterCheckedList(List<T> list,Map<String,String> params) {
	}
	
	@RequestMapping(value="/delete")
	@ResponseBody
	public ResultMap delete(String[] ids){
		ResultMap resultMap = new ResultMap();
		try {
			service.delete(ids);
			resultMap.success();
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.failure(e.getMessage());
		}
		return resultMap;
	}
}
