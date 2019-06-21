package com.jenkins.ssm1.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @description 权限操作的Vo类
 * @author Nicky
 * @date 2017年3月6日
 */
@Table(name="syspermission")
@Entity
public class Permission implements Serializable {

	private int id;
	private String pdesc;
	private String name;
	private static final long serialVersionUID = 1L;

	private Menu menu;

	private Set<Operation> operations = new HashSet<Operation>();

	public Permission() {
		super();
	}

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(length=100)
	public String getPdesc() {
		return this.pdesc;
	}

	public void setPdesc(String pdesc) {
		this.pdesc = pdesc;
	}

	@Column(length=100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	//cascade=CascadeType.REFRESH改为ALL
	@OneToOne(targetEntity=Menu.class,cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="menuId",referencedColumnName="menuId")
	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	@ManyToMany(targetEntity=Operation.class,cascade=CascadeType.MERGE,fetch=FetchType.EAGER)
	@JoinTable(name="sys_permission_operation",joinColumns=@JoinColumn(name="permissionId",referencedColumnName="id"),inverseJoinColumns=@JoinColumn(name="operationId",referencedColumnName="id"))
	public Set<Operation> getOperations() {
		return operations;
	}

	public void setOperations(Set<Operation> operations) {
		this.operations = operations;
	}
}
