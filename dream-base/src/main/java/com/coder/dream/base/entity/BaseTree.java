package com.coder.dream.base.entity;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

@MappedSuperclass
public class BaseTree extends BaseEntity{
	
	private static final long serialVersionUID = 5759499629824541886L;

	protected String parentId;

	@Transient
	protected Boolean leaf;

	@Transient
	protected Boolean expanded;
	
	@Transient
	protected Object data;
	
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Boolean getLeaf() {
		return leaf;
	}

	public void setLeaf(Boolean leaf) {
		this.leaf = leaf;
	}

	public Boolean getExpanded() {
		return expanded;
	}

	public void setExpanded(Boolean expanded) {
		this.expanded = expanded;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	} 
}
