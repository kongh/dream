package com.coder.dream.introduce.entity.sm;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.coder.dream.base.entity.BaseEntity;

@Entity
@Table(name="t_sm_user",uniqueConstraints={
							@UniqueConstraint(columnNames = {"account"}),
							@UniqueConstraint(columnNames={"phone"}),
							@UniqueConstraint(columnNames={"email"})
					  })
public class User extends BaseEntity{

	private static final long serialVersionUID = -6388878476759084985L;

	private String account;//用户账户,系统内部标识，唯一
	
	private String password;//密码
	
	private String name;//用户名称，姓名
	
	private String phone;//手机,唯一
	
	private String email;//电子邮箱,唯一

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
