package com.coder.dream.base.web.vo;

import java.util.LinkedHashMap;

import com.coder.dream.base.constant.BaseConstant;

public class OrderMap extends LinkedHashMap<String, Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 顺序
	 * 
	 * @param key
	 */
	public void asc(String key) {
		this.put(key + BaseConstant.SPLIT + BaseConstant.ASC, null);
	}

	/**
	 * 倒序
	 * 
	 * @param key
	 */
	public void desc(String key) {
		this.put(key + BaseConstant.SPLIT + BaseConstant.DESC, null);
	}

}
