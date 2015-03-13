package com.coder.dream.base.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.coder.dream.base.entity.BaseEntity;
import com.coder.dream.base.repository.BaseDao;
import com.coder.dream.base.service.BaseService;
import com.coder.dream.base.web.vo.DataTablePageParams;
import com.coder.dream.base.web.vo.FilterMap;
import com.coder.dream.base.web.vo.OrderMap;
import com.coder.dream.base.web.vo.ResultMap;

/**
 * 基本直接控制器-[适配轻量级的UI Frame，例如：jquery 和 bootstrap]
 * 
 * @author konghang
 *
 * @param <T>
 * @param <S>
 * @param <D>
 */
public abstract class BaseDirectController <T extends BaseEntity,S extends BaseService<T,D>,D extends BaseDao<T,String>> extends BaseController<T, S, D>{

	/**
	 * 创建实体
	 * 
	 * @param entity
	 * @return
	 */
	@RequestMapping(value="/create")
	@ResponseBody
	public ResultMap create(T entity){
		return doSave(entity);
	}
	
	/**
	 * 更新实体
	 * 
	 * @param entity
	 * @return
	 */
	@RequestMapping(value="/update")
	@ResponseBody
	public ResultMap update(T entity){
		return doSave(entity);
	}
	
	/**
	 * 保存或者更新
	 * 
	 * @param entity
	 * @return
	 */
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
	
	/**
	 * 获取
	 * 
	 * @param id
	 * @return
	 */
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
	
	/**
	 * 分页结果
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "/page")
	@ResponseBody
	public ResultMap page(@RequestParam Map<String, String> params) {
		ResultMap resultMap = new ResultMap();
		try{
			FilterMap filterMap = new FilterMap();
			OrderMap orderMap = new OrderMap();
			doBeforePage(params, filterMap, orderMap);
			
			DataTablePageParams instance = DataTablePageParams.newInstance(params);
			Page<T> page = service.page(filterMap, orderMap, instance.getIndexOfCurrentPage(), instance.getTotalOfPage());
			
			doAfterPage(params, resultMap, page);
		}catch(Exception e){
			e.printStackTrace();
			resultMap.failure(e.getMessage());
		}
		return resultMap;
	}
	
	/**
	 * 分页前处理
	 * 
	 * @param params
	 * @param filterMap
	 * @param orderMap
	 */
	protected void doBeforePage(Map<String, String> params,FilterMap filterMap ,OrderMap orderMap){
	}
	
	/**
	 * 分页后处理
	 * 
	 * @param params
	 * @param resultMap
	 * @param page
	 */
	protected void doAfterPage(Map<String, String> params,ResultMap resultMap,Page<T> page){
		resultMap.success(page.getContent(),page.getTotalElements());
		resultMap.put(DataTablePageParams.sEcho_KEY, params.get(DataTablePageParams.sEcho_KEY));
		resultMap.put(DataTablePageParams.iTotalRecords_KEY,page.getTotalElements());
		resultMap.put(DataTablePageParams.iTotalDisplayRecords_KEY, page.getTotalElements());
	}
	
	/**
	 * 删除
	 * 
	 * @param ids
	 * @return
	 */
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
	
	/**
	 * 列表
	 * 
	 * @param params
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list")
	public ResultMap list(@RequestParam Map<String,String> params,Model model){
		ResultMap resultMap = new ResultMap();
		try{
			FilterMap filterMap = new FilterMap();
			OrderMap orderMap = new OrderMap();
			doBeforeList(params, filterMap, orderMap);
			List<T> list = service.list(filterMap, orderMap);
			doAfterList(params, resultMap, list);
		}catch(Exception e){
			
		}
		return resultMap;
	} 
	
	/**
	 * 列表结果处理前
	 * 
	 * @param params
	 * @param filterMap
	 * @param orderMap
	 */
	protected void doBeforeList(Map<String, String> params, FilterMap filterMap,OrderMap orderMap) {
		
	}

	/**
	 * 列表结果处理后
	 * 
	 * @param params
	 * @param resultMap
	 * @param list
	 */
	protected void doAfterList(Map<String, String> params,ResultMap resultMap,List<T> list){
		resultMap.success(list);
	}
	
	/**
	 * 选择页面
	 * 
	 * @param params
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/select")
	public ModelAndView select(@RequestParam Map<String,String> params,Model model){
		FilterMap filterMap = new FilterMap();
		OrderMap orderMap = new OrderMap();
		doBeforeSelect(params, filterMap, orderMap);
		
		List<T> select = service.list(filterMap, orderMap);
		
		doAfterSelect(params, model, select);
		model.addAttribute("select", select);
		return new ModelAndView();
	}
	
	/**
	 * 选择页面处理前
	 * 
	 * @param params
	 * @param filterMap
	 * @param orderMap
	 */
	protected void doBeforeSelect(Map<String, String> params,FilterMap filterMap ,OrderMap orderMap){
		
	}
	
	/**
	 * 选择页面处理后
	 * 
	 * @param params
	 * @param model
	 * @param select
	 */
	protected void doAfterSelect(Map<String, String> params,Model model,List<T> select) {
	}
}
