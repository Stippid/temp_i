package com.models;


import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
@Table(name = "tbl_user", uniqueConstraints = {
		@UniqueConstraint(columnNames = "u_id")})
public class UserRegistration {

	private int id;
	private String userName;
	private String password;
	private String ConfirmPassword;
	private String emailId;
	private String department;
	private int enabled;
	private int accountNonExpired;
	private int accountNonLocked;
	private int credentialsNonExpired;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "u_id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id; 
	}
	
	@Column(name = "userName", nullable = false)
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Column(name = "password", nullable = false)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column(name = "confirm_password", nullable = false)
	public String getConfirmPassword() {
		return ConfirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		ConfirmPassword = confirmPassword;
	}
	
	@Column(name = "email", nullable = false)
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	@Column(name = "department", nullable = false)
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	@Column(name = "enabled", nullable = false, length = 4)
	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	
	@Column(name = "accountNonExpired", nullable = false, length = 4)
	public int getAccountNonExpired() {
		return accountNonExpired;
	}
	public void setAccountNonExpired(int accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}
	
	@Column(name = "accountNonLocked", nullable = false, length = 4)
	public int getAccountNonLocked() {
		return accountNonLocked;
	}
	public void setAccountNonLocked(int accountNonLocked){
		this.accountNonLocked = accountNonLocked;
	}
	
	@Column(name = "credentialsNonExpired", nullable = false, length = 4)
	public int getCredentialsNonExpired() {
		return credentialsNonExpired;
	}
	public void setCredentialsNonExpired(int credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}
}
