package com.coder.dream.demo.entity.sm;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.coder.dream.base.entity.BaseEntity;

@Entity
@Table(name="t_user_role",uniqueConstraints={
								@UniqueConstraint(columnNames={"userId","roleId"})
						  })
public class UserRole extends BaseEntity{

	private static final long serialVersionUID = -8664942752404130910L;

	private String userId;
	
	private String roleId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
}
