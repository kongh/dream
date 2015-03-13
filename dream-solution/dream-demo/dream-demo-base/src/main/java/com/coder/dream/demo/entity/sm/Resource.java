package com.coder.dream.demo.entity.sm;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.coder.dream.base.entity.BaseTree;

@Entity
@Table(name="t_sm_resource")
public class Resource extends BaseTree{

	private static final long serialVersionUID = -7208675703811458597L;

	private String type;
	
	private String name;
	
	private String code;
	
	private String url;
	
	private String iconCls;
	
	private String comments;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
