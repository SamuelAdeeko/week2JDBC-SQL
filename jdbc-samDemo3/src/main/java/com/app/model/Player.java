package com.app.model;

import java.sql.Date;

public class Player {
	
	private int id;
	private String name;
	private int team_id;
	private int age;
	private String gender;
	private long contact;
	private Date dob;
	
	public Player() {
		
	}
	public Player(int id, String name, int team_id, int age, String gender, long contact, Date dob) {
		super();
		this.id = id;
		this.name = name;
		this.team_id = team_id;
		this.age = age;
		this.gender = gender;
		this.contact = contact;
		this.dob = dob;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTeam_id() {
		return team_id;
	}
	public void setTeam_id(int team_id) {
		this.team_id = team_id;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public long getContact() {
		return contact;
	}
	public void setContact(long contact) {
		this.contact = contact;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	@Override
	public String toString() {
		return "Player [id=" + id + ", name=" + name + ", team_id=" + team_id + ", age=" + age + ", gender=" + gender
				+ ", contact=" + contact + ", dob=" + dob + "]";
	}

}
