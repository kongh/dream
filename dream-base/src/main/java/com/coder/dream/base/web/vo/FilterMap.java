package com.coder.dream.base.web.vo;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.coder.dream.base.constant.BaseConstant;

public class FilterMap extends LinkedHashMap<String, Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 等于
	 * 
	 * @param propertyName
	 * @param value
	 */
	public void eq(String propertyName, Object value) {
		this.put(propertyName + BaseConstant.SPLIT + BaseConstant.EQ, value);
	}

	/**
	 * 不等于
	 * 
	 * @param propertyName
	 * @param value
	 */
	public void ne(String propertyName, Object value) {
		this.put(propertyName + BaseConstant.SPLIT + BaseConstant.NE, value);
	}

	/**
	 * 大于
	 * 
	 * @param propertyName
	 * @param value
	 */
	public void gt(String propertyName, Integer value) {
		this.put(propertyName + BaseConstant.SPLIT + BaseConstant.GT, value);
	}

	/**
	 * 大于
	 * 
	 * @param propertyName
	 * @param value
	 */
	public void gt(String propertyName, Date value) {
		this.put(propertyName + BaseConstant.SPLIT + BaseConstant.GTDATE, value);
	}

	/**
	 * 大于等于
	 * 
	 * @param propertyName
	 * @param value
	 */
	public void ge(String propertyName, Integer value) {
		this.put(propertyName + BaseConstant.SPLIT + BaseConstant.GE, value);
	}

	/**
	 * 大于等于
	 * 
	 * @param propertyName
	 * @param value
	 */
	public void ge(String propertyName, Date value) {
		this.put(propertyName + BaseConstant.SPLIT + BaseConstant.GEDATE, value);
	}

	/**
	 * 小于
	 * 
	 * @param propertyName
	 * @param value
	 */
	public void lt(String propertyName, Integer value) {
		this.put(propertyName + BaseConstant.SPLIT + BaseConstant.LT, value);
	}

	/**
	 * 小于
	 * 
	 * @param propertyName
	 * @param value
	 */
	public void lt(String propertyName, Date value) {
		this.put(propertyName + BaseConstant.SPLIT + BaseConstant.LTDATE, value);
	}

	/**
	 * 小于等于
	 * 
	 * @param propertyName
	 * @param value
	 */
	public void le(String propertyName, Integer value) {
		this.put(propertyName + BaseConstant.SPLIT + BaseConstant.LE, value);
	}

	/**
	 * 小于等于
	 * 
	 * @param propertyName
	 * @param value
	 */
	public void le(String propertyName, Date value) {
		this.put(propertyName + BaseConstant.SPLIT + BaseConstant.LEDATE, value);
	}

	/**
	 * 相似
	 * 
	 * @param propertyName
	 * @param value
	 */
	public void like(String propertyName, String value) {
		this.put(propertyName + BaseConstant.SPLIT + BaseConstant.LIKE, value);
	}

	/**
	 * 不相似
	 * 
	 * @param propertyName
	 * @param value
	 */
	public void notLike(String propertyName, String value) {
		this.put(propertyName + BaseConstant.SPLIT + BaseConstant.NOTLIKE, value);
	}

	/**
	 * 包含
	 * 
	 * @param propertyName
	 * @param value
	 */
	@SuppressWarnings("rawtypes")
	public void in(String propertyName, Collection value) {
		this.put(propertyName + BaseConstant.SPLIT + BaseConstant.IN, value);
	}

	/**
	 * 不包含
	 * 
	 * @param propertyName
	 * @param value
	 */
	@SuppressWarnings("rawtypes")
	public void notIn(String propertyName, Collection value) {
		this.put(propertyName + BaseConstant.SPLIT + BaseConstant.NOTIN, value);
	}

	/**
	 * 之间
	 * 
	 * @param propertyName
	 * @param lo
	 * @param go
	 */
	public void between(String propertyName, Integer lo, Integer go) {
		Map<String, Integer> between = new HashMap<String, Integer>();
		between.put(BaseConstant.LO, lo);
		between.put(BaseConstant.GO, go);
		this.put(propertyName + BaseConstant.SPLIT + BaseConstant.BETWEEN, between);
	}

	/**
	 * 之间
	 * 
	 * @param propertyName
	 * @param lo
	 * @param go
	 */
	public void between(String propertyName, Date lo, Date go) {
		Map<String, Date> between = new HashMap<String, Date>();
		between.put(BaseConstant.LO, lo);
		between.put(BaseConstant.GO, go);
		this.put(propertyName + BaseConstant.SPLIT + BaseConstant.BETWEENDATE, between);
	}

	/**
	 * 空
	 * 
	 * @param propertyName
	 */
	public void isNull(String propertyName) {
		this.put(propertyName + BaseConstant.SPLIT + BaseConstant.ISNULL, null);
	}

	/**
	 * 非空
	 * 
	 * @param propertyName
	 */
	public void isNotNull(String propertyName) {
		this.put(propertyName + BaseConstant.SPLIT + BaseConstant.ISNOTNULL, null);
	}

	/**
	 * 空
	 * 
	 * @param propertyName
	 */
	public void isEmpty(String propertyName) {
		this.put(propertyName + BaseConstant.SPLIT + BaseConstant.ISEMPTY, null);
	}

	/**
	 * 非空
	 * 
	 * @param propertyName
	 */
	public void isNotEmpty(String propertyName) {
		this.put(propertyName + BaseConstant.SPLIT + BaseConstant.ISNOTEMPTY, null);
	}

	/**
	 * 或者
	 * 
	 * @param filterMap
	 */
	public void or(FilterMap filterMap) {
		this.put(BaseConstant.SPLIT + BaseConstant.OR, filterMap);
	}

}
