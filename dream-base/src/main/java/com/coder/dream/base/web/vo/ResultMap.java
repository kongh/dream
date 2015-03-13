package com.coder.dream.base.web.vo;

import java.util.LinkedHashMap;
import java.util.Map;

public class ResultMap extends LinkedHashMap<String, Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String SUCCESS = "success";

	private static final String DATA = "data";

	private static final String MSG = "msg";

	private static final String TOTAL = "total";

//	private static final String METADATA = "metaData";

	/**
	 * 获取是否成功
	 * 
	 * @return
	 */
	public Boolean getSuccess() {
		return (Boolean) this.get(SUCCESS);
	}

	private void setSuccess(Boolean success) {
		this.put(SUCCESS, success);
	}

	/**
	 * 获取返回数据
	 * 
	 * @return
	 */
	public Object getData() {
		return this.get(DATA);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getDataMap() {
		return (Map<String, Object>) this.get(DATA);
	}

	private void setData(Object data) {
		this.put(DATA, data);
	}

	/**
	 * 获取返回消息
	 * 
	 * @return
	 */
	public Object getMsg() {
		return this.get(MSG);
	}

	private void setMsg(Object msg) {
		this.put(MSG, msg);
	}

	/**
	 * 获取总记录数
	 * 
	 * @return
	 */
	public Long getTotal() {
		return (Long) this.get(TOTAL);
	}

	private void setTotal(Long total) {
		this.put(TOTAL, total);
	}

	private void setMetaData(Object metaData) {
//		this.put(METADATA, metaData);
	}

	/**
	 * 返回结果成功，返回数据为null
	 */
	public void success() {
		this.setSuccess(true);
		this.setData(null);
		Map<String, String> metaData = new LinkedHashMap<String, String>();
		metaData.put("successProperty", SUCCESS);
		metaData.put("rootProperty", DATA);
		this.setMetaData(metaData);
	}

	/**
	 * 返回结果成功，返回数据为data
	 * 
	 * @param data
	 */
	public void success(Object data) {
		this.setSuccess(true);
		this.setData(data);
		Map<String, String> metaData = new LinkedHashMap<String, String>();
		metaData.put("successProperty", SUCCESS);
		metaData.put("rootProperty", DATA);
		this.setMetaData(metaData);
	}

	/**
	 * 返回结果成功，返回数据为page
	 * 
	 * @param data
	 * @param total
	 */
	public void success(Object data, Long total) {
		this.setSuccess(true);
		this.setData(data);
		this.setTotal(total);
		Map<String, String> metaData = new LinkedHashMap<String, String>();
		metaData.put("successProperty", SUCCESS);
		metaData.put("rootProperty", DATA);
		metaData.put("totalProperty", TOTAL);
		this.setMetaData(metaData);
	}

	/**
	 * 返回结果失败，返回消息为null
	 */
	public void failure() {
		this.setSuccess(false);
		this.setMsg(null);
		Map<String, String> metaData = new LinkedHashMap<String, String>();
		metaData.put("successProperty", SUCCESS);
		metaData.put("messageProperty", MSG);
		this.setMetaData(metaData);
	}

	/**
	 * 返回结果失败，返回消息为msg
	 * 
	 * @param msg
	 */
	public void failure(Object msg) {
		this.setSuccess(false);
		this.setMsg(msg);
		Map<String, String> metaData = new LinkedHashMap<String, String>();
		metaData.put("successProperty", SUCCESS);
		metaData.put("messageProperty", MSG);
		this.setMetaData(metaData);
	}

}
