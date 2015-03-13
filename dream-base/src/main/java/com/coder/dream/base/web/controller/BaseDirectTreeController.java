package com.coder.dream.base.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.coder.dream.base.entity.BaseTree;
import com.coder.dream.base.repository.BaseTreeDao;
import com.coder.dream.base.service.BaseTreeService;
import com.coder.dream.base.web.vo.ResultMap;

/**
 * 基本直接树形控制器
 * 
 * @author konghang
 *
 * @param <T>
 * @param <S>
 * @param <D>
 */
public abstract class BaseDirectTreeController<T extends BaseTree,S extends BaseTreeService<T,D>,D extends BaseTreeDao<T,String>> extends BaseDirectController<T,S,D> {

	/**
	 * 模块页
	 * 
	 * @param params
	 * @param model
	 */
	@Override
	public ModelAndView index(Map<String, String> params, Model model) {
		T root = service.getRoot();
		model.addAttribute("root", root);
		return super.index(params, model);
	}
	
	/**
	 * 孩子节点
	 * 
	 * @param params
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/children")
	public ModelAndView children(@RequestParam Map<String,String> params,String id, Model model) {
		List<T> children = doChildren(params, id);
		model.addAttribute("children", children);
		return new ModelAndView();
	}
	
	/**
	 * 树形选择列表
	 * 
	 * @param params
	 * @param model
	 */
	@Override
	public ModelAndView select(@RequestParam Map<String,String> params,Model model){
		List<T> resources = new ArrayList<T>();
		service.treeList(service.getRoot(), resources);
		doAfterSelect(params, model, resources);
		model.addAttribute("select", resources);
		return new ModelAndView();
	}
	
	/**
	 * 子节点
	 * 
	 * @param params
	 * @param id
	 * @return
	 */
	protected List<T> doChildren(Map<String,String> params,String id){
		return service.children(id);
	}
	
	/**
	 * 删除树节点
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/deleteNode")
	@ResponseBody
	public ResultMap deleteNode(String[] ids){
		ResultMap resultMap = new ResultMap();
		try {
			service.deleteNode(ids);
			resultMap.success();
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.failure(e.getMessage());
		}
		return resultMap;
	}
}
