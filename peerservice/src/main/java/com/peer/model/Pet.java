package com.peer.model;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class Pet implements Serializable{

	private static final long serialVersionUID = 1L;
	private long id;
	private String name;
	private int age;
	private String place;
	private int owner;

	public Pet() {
		super();
	}

	public Pet(String name, int age, String place) {
		super();
		this.name = name;
		this.age = age;
		this.place = place;
	}
	
	public Pet(long id, String name, int age, String place) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.place = place;
	}
	
	public Pet(long id, String name, int age, String place, int owner) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.place = place;
		this.owner = owner;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public int getOwner() {
		return owner;
	}

	public void setOwner(int owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		return "Pet [id=" + id + ", name=" + name + ", age=" + age + ", place=" + place + ", owner=" + owner + "]";
	}
}
