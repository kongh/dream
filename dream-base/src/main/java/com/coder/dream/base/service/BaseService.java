package com.coder.dream.base.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import com.coder.dream.base.entity.BaseEntity;
import com.coder.dream.base.repository.BaseDao;
import com.coder.dream.base.repository.support.SortBuildUtil;
import com.coder.dream.base.repository.support.SpecificationBuildUtil;
import com.coder.dream.base.utils.EntityInfoWriter;
import com.coder.dream.base.web.vo.FilterMap;
import com.coder.dream.base.web.vo.OrderMap;
import com.mysql.jdbc.StringUtils;

public class BaseService<T extends BaseEntity,D extends BaseDao<T,String>> {
	
	@Autowired
	protected D dao;
	
	/**
	 * 保存(新增/更新)
	 * 
	 * @param entity
	 * @return
	 */
	public T save(T entity){
		EntityInfoWriter.write(entity);
		return dao.save(entity);
	}
	
	/**
	 * 根据id获取实体
	 * 
	 * @param id
	 * @return
	 */
	public T get(String id){
		return dao.findOne(id);
	}
	
	/**
	 * 分页
	 * 
	 * @param filterMap
	 * @param orderMap
	 * @param pageIndex
	 * @param pageLimit
	 * @return
	 */
	public Page<T> page(FilterMap filterMap,OrderMap orderMap,int pageIndex,int pageLimit){
		Specification<T> specification = SpecificationBuildUtil.buildSpecification(filterMap);
		Sort sort = SortBuildUtil.buildSort(orderMap);
		PageRequest request = new PageRequest(pageIndex, pageLimit,sort);
		return dao.findAll(specification, request);
	}
	
	/**
	 * 列表
	 * 
	 * @param filterMap
	 * @param orderMap
	 * @return
	 */
	public List<T> list(FilterMap filterMap,OrderMap orderMap){
		Specification<T> specification = SpecificationBuildUtil.buildSpecification(filterMap);
		Sort sort = SortBuildUtil.buildSort(orderMap);
		return dao.findAll(specification,sort);
	}
	
	/**
	 * 删除
	 * 
	 * @param ids
	 */
	public void delete(String[] ids){
		if(ids == null || ids.length == 0){
			return ;
		}
		List<T> deleting = dao.findAll(Arrays.asList(ids));
		dao.delete(deleting);
	}
	
	/**
	 * 删除
	 * 
	 * @param id
	 */
	public void delete(String id){
		if(StringUtils.isNullOrEmpty(id)){
			return ;
		}
		dao.delete(id);
	}
	
	/**
	 * 数量
	 * 
	 * @param filterMap
	 * @return
	 */
	public long count(FilterMap filterMap){
		Specification<T> specification = SpecificationBuildUtil.buildSpecification(filterMap);
		return dao.count(specification);
	}
	
	/**
	 * 查找一个
	 * 
	 * @param filterMap
	 * @return
	 */
	public T findOne(FilterMap filterMap){
		Specification<T> specification = SpecificationBuildUtil.buildSpecification(filterMap);
		return dao.findOne(specification);
	}
}
