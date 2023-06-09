package com.pet.model;

import java.io.Serializable;

/*@Entity
@Table(name = "Peer_Table")*/
public class Peer implements Serializable{

	private static final long serialVersionUID = 1L;

	/*
	 * @Id
	 * 
	 * @GeneratedValue(strategy = GenerationType.AUTO)
	 * 
	 * @Column(name = "Peer_Id")
	 */
	private long id;

	/* @Column(name = "User_Name") */
	private String userName;

	/* @Column(name = "Password") */
	private String password;

	public Peer() {
		super();
	}

	public Peer(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public Peer(long id, String userName, String password) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Peer [id=" + id + ", userName=" + userName + ", password=" + password + "]";
	}
	
}
