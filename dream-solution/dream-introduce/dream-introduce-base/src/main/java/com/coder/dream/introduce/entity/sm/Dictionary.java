package com.coder.dream.introduce.entity.sm;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.coder.dream.base.entity.BaseEntity;

@Entity
@Table(name="t_sm_dictionary",uniqueConstraints={
							  @UniqueConstraint(columnNames={"code"})
							 })
public class Dictionary extends BaseEntity{

	private static final long serialVersionUID = -6540157444240477794L;

	private String name;
	
	private String code;
	
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

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
}
