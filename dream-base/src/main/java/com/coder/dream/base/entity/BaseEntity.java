
package com.coder.dream.base.entity;

import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

@MappedSuperclass
public class BaseEntity extends IdEntity{

	private static final long serialVersionUID = 4311388340319773704L;

	/**创建信息**/
	
	/**
	 * 创建用户Id
	 */
	protected String  createUserId;
	
	/**
	 * 创建用户名称
	 */
	@Transient
	protected String createUserName;
	
	/**
	 * 创建日期
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date createDate;
	
	/**更新信息**/
	/**
	 * 更新用户Id
	 */
	protected String  updateUserId;
	
	/**
	 * 更新用户名称
	 */
	@Transient
	protected String updateUserName;
	
	/**
	 * 更新日期
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	protected Date updateDate;
	
	/**其他信息**/
	/**
	 * 排序号
	 */
	protected Integer sort;

	/**
	 * 状态
	 */
	protected String status;
	
	/**
	 * 行号（虚拟字段）
	 */
	@Transient
	protected Integer rowNum;
	
	/**
	 * 是否选中（虚拟字段）
	 */
	@Transient
	protected Boolean checked;
	
	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}

	public String getUpdateUserName() {
		return updateUserName;
	}

	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public Integer getRowNum() {
		return rowNum;
	}

	public void setRowNum(Integer rowNum) {
		this.rowNum = rowNum;
	}
}

