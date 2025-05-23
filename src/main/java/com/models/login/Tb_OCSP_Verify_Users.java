package com.models.login;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_ocsp_verify_users", uniqueConstraints = {
@UniqueConstraint(columnNames = "id")})
public class Tb_OCSP_Verify_Users {
	private int id;
	private int userid;
	private String army_no;
	private String dashboard_role;
	private Date created_on;
	private String ocsp_status;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getArmy_no() {
		return army_no;
	}
	public void setArmy_no(String army_no) {
		this.army_no = army_no;
	}
	public String getDashboard_role() {
		return dashboard_role;
	}
	public void setDashboard_role(String dashboard_role) {
		this.dashboard_role = dashboard_role;
	}
	public Date getCreated_on() {
		return created_on;
	}
	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}

	public String getOcsp_status() {
		return ocsp_status;
	}

	public void setOcsp_status(String ocsp_status) {
		this.ocsp_status = ocsp_status;
	}
	
	
}
