package com.bridgelabz.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name="userinfo")
@Component
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="userid")
	private long userid;
	
	@Column(name="username")
	private String name;
	
	@Column(name="userpassword")
	private String password;
	
	@Column(name="userphone")
	private long phone;
	
	@Column(name="useremail")
	private String email;
	
	@Column(name="isverified",columnDefinition = "boolean Default false", nullable=false)
	private boolean isVerified;
	
	@Column(name="datetime")
	private LocalDateTime dateTime;
	
//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinColumn(name = "userId")
//	private List<Note> noteList;
	
	

	public User() {
	}

	public User(long userid, String name, String password, long phone, String email, boolean isVerified,
			LocalDateTime dateTime) {
		super();
		this.userid = userid;
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.email = email;
		this.isVerified = isVerified;
		this.dateTime = dateTime;
	}

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isVerified() {
		return isVerified;
	}

	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	
	

	@Override
	public String toString() {
		return "User [userid=" + userid + ", name=" + name + ", password=" + password + ", phone=" + phone + ", email="
				+ email + ", isVerified=" + isVerified + ", dateTime=" + dateTime + "]";
	}
	
	
}
