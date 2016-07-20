package com.gusi.demo.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hazelcast_person")
public class Person implements Serializable {
	private static final long serialVersionUID = 8821657922113203891L;
	private long id;
	private String name;
	private int age;
	private boolean isMan;

	public Person() {
		super();
	}

	public Person(long id, String name, int age, boolean isMan) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.isMan = isMan;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "age")
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Column(name = "isMan")
	public boolean getIsMan() {
		return isMan;
	}

	public void setIsMan(boolean isMan) {
		this.isMan = isMan;
	}

	@Override
	public String toString() {
		return "Entity [id=" + id + ", name=" + name + ", age=" + age + ", isMan=" + isMan + "]";
	}

}
