package com.ud.user.entity;

import javax.validation.constraints.Size;

/**
 * User entity.
 * 
 * @author udith
 *
 */
public class User {

	private int id;

	@Size(min = 1, max = 50)
	private String name;

	private int age;

	private String sex;
	
	private String status;
	
	public User() {}

	public User(int id, String name, int age, String sex, String status) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public String getSex() {
		return sex;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age + ", sex=" + sex + ", status=" + status + "]";
	}

}
