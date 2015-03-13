
package com.coder.dream.base.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
public class IdEntity implements Serializable{

	private static final long serialVersionUID = -303017835754883405L;
	
	@Id
	@GenericGenerator(name="systemUUID",strategy="uuid")
	@GeneratedValue(generator="systemUUID")
	protected String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}

