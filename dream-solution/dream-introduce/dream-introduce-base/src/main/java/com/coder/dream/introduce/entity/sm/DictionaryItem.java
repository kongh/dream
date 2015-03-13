package com.coder.dream.introduce.entity.sm;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.coder.dream.base.entity.BaseTree;

@Entity
@Table(name="t_sm_dictionary_item",uniqueConstraints={
							  @UniqueConstraint(columnNames={"dictionaryCode","value"})
							 })
public class DictionaryItem extends BaseTree{

	private static final long serialVersionUID = -327694360739395788L;

	private String dictionaryCode;
	
	private String name;
	
	private String value;

	private String comments;
	
	public String getDictionaryCode() {
		return dictionaryCode;
	}

	public void setDictionaryCode(String dictionaryCode) {
		this.dictionaryCode = dictionaryCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
}
