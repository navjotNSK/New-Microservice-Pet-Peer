package com.pet.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Pet_Table")
public class Pet implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Pet_Id")
	private long id;

	@Column(name = "Pet_Name")
	private String name;
	
	@Column(name = "Pet_Age")
	private int age;

	@Column(name = "Pet_Place")
	private String place;

	@Column(name = "Pet_Owner_Id")
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
