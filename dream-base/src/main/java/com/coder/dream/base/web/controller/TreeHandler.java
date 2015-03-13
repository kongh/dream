package com.coder.dream.base.web.controller;

import java.util.List;

import com.coder.dream.base.entity.BaseTree;

/**
 * 树处理器
 * 
 * 
 * @author konghang
 *
 */
public interface TreeHandler<T extends BaseTree> {
	
	/**
	 * 获取根
	 * 
	 * @return
	 */
	public T getRoot();
	
	/**
	 * 获取树节点
	 * 
	 * @param id
	 * @return
	 */
	public T getNode(String id);
	
	/**
	 * 获取孩子节点
	 * 
	 * @param id
	 * @return
	 */
	public List<T> children(String id);
	
	/**
	 * 获取子树
	 * 
	 * @param id
	 * @return
	 */
	public List<T> subTree(String id);
	
	/**
	 * 获取树
	 * 
	 * @return
	 */
	public List<T> tree();
}
