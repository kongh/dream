package com.coder.dream.demo.entity.sm;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.coder.dream.base.entity.BaseEntity;

@Entity
@Table(name="t_sm_role",uniqueConstraints={
			@UniqueConstraint(columnNames={"name"}),
			@UniqueConstraint(columnNames={"code"})
		})
public class Role extends BaseEntity{

	private static final long serialVersionUID = -156217808082882196L;

	private String name;//名称
	
	private String code;//编码
	
	private String comments;//备注

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

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
}
