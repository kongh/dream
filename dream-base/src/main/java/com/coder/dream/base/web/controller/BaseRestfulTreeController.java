package com.coder.dream.base.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coder.dream.base.entity.BaseTree;
import com.coder.dream.base.repository.BaseTreeDao;
import com.coder.dream.base.service.BaseTreeService;
import com.coder.dream.base.web.vo.ResultMap;

public abstract class BaseRestfulTreeController<T extends BaseTree,S extends BaseTreeService<T,D>,D extends BaseTreeDao<T,String>> extends BaseRestfulController<T,S,D> {
	
	@RequestMapping(value="/root")
	@ResponseBody
	public ResultMap getRoot(@RequestParam Map<String,String> params) {
		ResultMap resultMap = new ResultMap();
		try{
			T root = service.getRoot();
			resultMap.success(root);
		}catch(Exception e){
			e.printStackTrace();
			resultMap.failure(e.getMessage());
		}
		return resultMap;
	}
	
	@RequestMapping(value="/node")
	@ResponseBody
	public ResultMap getNode(@RequestParam Map<String,String> params,String id) {
		ResultMap resultMap = new ResultMap();
		if(StringUtils.isEmpty(id)){
			resultMap.failure("id为空!");
			return resultMap;
		}
		
		try{
			T node = service.getNode(id);
			resultMap.success(node);
		}catch(Exception e){
			e.printStackTrace();
			resultMap.failure(e.getMessage());
		}
		
		return resultMap;
	}

	@RequestMapping(value="/children")
	@ResponseBody
	public ResultMap children(@RequestParam Map<String,String> params,String node) {
		ResultMap resultMap = new ResultMap();
		if(!doBeforeChildren(params, node,resultMap)){
			return resultMap;
		}
		try{
			List<T> children = doChildren(params, node);
			doAfterChildren(params, children);
			resultMap.success(children);
		}catch(Exception e){
			e.printStackTrace();
			resultMap.failure(e.getMessage());
		}
		
		return resultMap;
	}
	
	protected boolean doBeforeChildren(Map<String,String> params,String id,ResultMap resultMap){
		if(StringUtils.isEmpty(id)){
			resultMap.failure("id不能为空!");
			return false;
		}
		return true;
	}
	
	protected List<T> doChildren(Map<String,String> params,String id){
		return service.children(id);
	}
	
	protected void doAfterChildren(@RequestParam Map<String,String> params,List<T> children){
	}
	
	@RequestMapping(value="/checkedChildren")
	@ResponseBody
	public ResultMap checkedChildren(@RequestParam Map<String,String> params,String node){
		ResultMap resultMap = new ResultMap();
		if(!doBeforeCheckedChildren(params, node,resultMap)){
			return resultMap;
		}
		try{
			List<T> children = doCheckedChildren(params, node);
			doAfterCheckedChildren(params, children);
			resultMap.success(children);
		}catch(Exception e){
			e.printStackTrace();
			resultMap.failure(e.getMessage());
		}
		return resultMap;
	}

	protected boolean doBeforeCheckedChildren(Map<String, String> params,String id, ResultMap resultMap) {
		if(StringUtils.isEmpty(id)){
			resultMap.failure("id不能为空!");
			return false;
		}
		return true;
	}
	
	protected List<T> doCheckedChildren(Map<String, String> params, String id) {
		return service.children(id);
	}

	protected void doAfterCheckedChildren(@RequestParam Map<String,String> params,List<T> children){
	}
	
	@RequestMapping(value="/subTree")
	@ResponseBody
	public ResultMap subTree(@RequestParam Map<String,String> params,String id) {
		return null;
	}

	@RequestMapping(value="/tree")
	@ResponseBody
	public ResultMap tree(@RequestParam Map<String,String> params) {
		return null;
	}
	
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
