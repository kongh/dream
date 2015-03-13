package com.coder.dream.introduce.entity.sm;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.coder.dream.base.entity.BaseEntity;

@Entity
@Table(name="t_role_resource",uniqueConstraints={
									@UniqueConstraint(columnNames={"roleId","resourceId"})
							  })
public class RoleResource extends BaseEntity{

	private static final long serialVersionUID = 1043011463533976576L;

	private String roleId;//角色Id
	
	private String resourceId;//资源Id

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
}
