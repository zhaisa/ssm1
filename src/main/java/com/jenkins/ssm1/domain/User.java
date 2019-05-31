package com.jenkins.ssm1.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 用户信息的实体类
 * @author Nicky
 */

@Table(name="user")
public class User implements Serializable{

	/** 用户Id**/
	private int id;

	/** 用户名**/
	private String username;

	/** 用户密码**/
	private String password;

	/** 手机号**/
	private String phone;

	/** 性别**/
	private String sex;

	/** 邮件**/
	private String email;

	/** 备注**/
	private String mark;

	/** 用户级别**/
	private String rank;

	/** 最后一次时间**/
	private Date lastLogin;

	/** 登录ip**/
	private String loginIp;

	/** 图片路径**/
	private String imageUrl;

	/** 注册时间**/
	private Date regTime;

	/** 账号是否被锁定**/
	private Boolean locked = Boolean.FALSE;

	/** 权限**/
	private String rights;

	private Set<Role> roles;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}


	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}


    @JSONField(format ="yyyy-MM-dd HH:mm:ss")
	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}


	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}


	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}


    @JSONField(format ="yyyy-MM-dd HH:mm:ss")
	public Date getRegTime() {
		return regTime;
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}

	public Boolean getLocked() {
		return locked;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

	public String getRights() {
		return rights;
	}

	public void setRights(String rights) {
		this.rights = rights;
	}

	//修改cascade策略为级联关系
	@ManyToMany(targetEntity = Role.class, cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinTable(name = "sys_user_role", joinColumns = @JoinColumn(name = "userId", referencedColumnName = "id") , inverseJoinColumns = @JoinColumn(name = "roleId", referencedColumnName = "roleId") )
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}



}
