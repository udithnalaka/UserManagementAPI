package com.ud.user.entity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * User entity.
 * 
 * @author udith
 *
 */
public class User {

	private int id;

	@NotNull
	@Size(min = 1, max = 50)
	private String name;

	private int age;

	private String sex;
	
	private String status;
	
	@NotNull
	@Email
	private String email;
	
	public User() {}

	public User(int id, String name, int age, String sex, String status, String email) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.status = status;
		this.email = email;
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
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [id=" + id + 
				", name=" + name + 
				", age=" + age + 
				", sex=" + sex + 
				", status=" + status + 
				", email="	+ email + "]";
	}

}
