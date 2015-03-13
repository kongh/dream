package com.coder.dream.base.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.coder.dream.base.entity.BaseTree;
import com.coder.dream.base.repository.BaseTreeDao;
import com.coder.dream.base.repository.support.SpecificationBuildUtil;
import com.coder.dream.base.utils.NullUtil;
import com.coder.dream.base.web.vo.FilterMap;

public abstract class BaseTreeService<T extends BaseTree,D extends BaseTreeDao<T,String>> extends BaseService<T,D>{

	@Override
	public T save(T entity) {
		T saved= super.save(entity);
		caculateLogicProperties(saved);
		return saved;
	}
	/**
	 * 获取根节点
	 * 
	 * @return
	 */
	public T getRoot(){
		FilterMap filterMap = new FilterMap();
		FilterMap rootMap = new FilterMap();
		rootMap.isNull("parentId");
		rootMap.isEmpty("parentId");
		filterMap.or(rootMap);
		Specification<T> specification = SpecificationBuildUtil.buildSpecification(filterMap);
		
		T root = dao.findOne(specification);
		if(root == null){
			root = createRoot();
			dao.save(root);
		}
		root.setLeaf(false);
		return root;
	}
	
	/**
	 * 创建根节点
	 * 
	 * @return
	 */
	protected abstract T createRoot();
	
	/**
	 * 孩子节点
	 * 
	 * @param parentId
	 * @return
	 */
	public List<T> children(String parentId){
		FilterMap filterMap = new FilterMap();
		return children(parentId, filterMap);
	}
	
	/**
	 * 孩子节点
	 * 
	 * @param parentId
	 * @param filterMap
	 * @return
	 */
	public List<T> children(String parentId,FilterMap filterMap){
		filterMap.eq("parentId",parentId);
		Specification<T> specification = SpecificationBuildUtil.buildSpecification(filterMap);
		List<T> children = dao.findAll(specification);
		for (T node : children) {
			caculateLogicProperties(node,filterMap);
		}
		return children;
	}
	
	/**
	 * 计算业务逻辑
	 * 
	 * @param node
	 */
	protected void caculateLogicProperties(T node){
		node.setLeaf(isLeaf(node));
		node.setExpanded(false);
	}
	
	/**
	 * 计算业务逻辑
	 * 
	 * @param node
	 */
	protected void caculateLogicProperties(T node,FilterMap filterMap){
		node.setLeaf(isLeaf(node,filterMap));
		node.setExpanded(false);
	}
	
	/**
	 * 是否叶子节点
	 * 
	 * @param node
	 * @return
	 */
	public boolean isLeaf(T node){
		FilterMap filterMap = new FilterMap();
		return isLeaf(node, filterMap);
	}
	
	/**
	 * 是否叶子节点
	 * 
	 * @param node
	 * @return
	 */
	public boolean isLeaf(T node,FilterMap filterMap){
		filterMap.eq("parentId",node.getId());
		Specification<T> specification = SpecificationBuildUtil.buildSpecification(filterMap);
		return dao.count(specification) <= 0;
	}
	
	/**
	 * 删除节点，包含其子节点
	 * 
	 * @param ids
	 */
	public void deleteNode(String[] ids){
		if(ids == null || ids.length == 0){
			return ;
		}
		
		List<T> deleting = dao.findAll(Arrays.asList(ids));
		deleteNode(deleting);
	}
	
	/**
	 * 删除节点，包含其子节点
	 * 
	 * @param nodes
	 */
	public void deleteNode(List<T> nodes){
		if(nodes == null || nodes.isEmpty()){
			return ;
		}
		
		for (T node : nodes) {
			List<T> children = children(node.getId());
			if(!children.isEmpty()){
				deleteNode(children);
			}
			
			if(StringUtils.isEmpty(node.getParentId())){//根节点不删除
				continue;
			}
			
			delete(node.getId());
		}
	}
	
	/**
	 * 获取节点
	 * 
	 * @param id
	 * @return
	 */
	public T getNode(String id){
		T node = dao.findOne(id);
		caculateLogicProperties(node);
		return node;
	}
	
	/**
	 * 获取节点
	 * 
	 * @param id
	 * @return
	 */
	public T getNode(String id,FilterMap filterMap){
		T node = dao.findOne(id);
		caculateLogicProperties(node,filterMap);
		return node;
	}
	
	/**
	 * 查找节点
	 * 
	 * @param filterMap
	 * @return
	 */
	public T findNode(FilterMap filterMap){
		Specification<T> specification = SpecificationBuildUtil.buildSpecification(filterMap);
		T one = dao.findOne(specification);
		if(NullUtil.isNotNull(one)){
			caculateLogicProperties(one);
		}
		return one;
	}
	
	/**
	 * 获取树集合
	 * 
	 * @param node
	 * @return
	 */
	public T tree(T node){
		if(!node.getLeaf()){
			List<T> children = children(node.getId());
			for (T child : children) {
				tree(child);
			}
			node.setData(children);
		}
		return node;
	}
	
	/**
	 * 获取树集合
	 * 
	 * @param nodeId
	 * @return
	 */
	public T tree(String nodeId){
		T node = getNode(nodeId);
		return tree(node);
	}
	
	/**
	 * 获取树集合
	 * 
	 * @param node
	 * @return
	 */
	public T tree(T node,FilterMap filterMap){
		if(!node.getLeaf()){
			List<T> children = children(node.getId(),filterMap);
			for (T child : children) {
				tree(child,filterMap);
			}
			node.setData(children);
		}
		return node;
	}
	
	/**
	 * 获取树集合
	 * 
	 * @param nodeId
	 * @return
	 */
	public T tree(String nodeId,FilterMap filterMap){
		T node = getNode(nodeId,filterMap);
		return tree(node);
	}
	
	public void treeList(T node,List<T> nodes) {
		nodes.add(node);
		if(!node.getLeaf()){
			List<T> children = children(node.getId());
			for (T child : children) {
				treeList(child,nodes);
			}
		}
	}
} 
