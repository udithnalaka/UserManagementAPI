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

	private String name;

	@Size(min = 1, max = 50)
	private int age;

	private String sex;

	public User(int id, String name, int age, String sex) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.sex = sex;
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

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age + ", sex=" + sex + "]";
	}

}
