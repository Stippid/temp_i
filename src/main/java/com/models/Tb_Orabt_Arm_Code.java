package com.models;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_orbat_arm_code", uniqueConstraints = { @UniqueConstraint(columnNames = "id") })
public class Tb_Orabt_Arm_Code {
	
	private int id;
	private String arm_code;
	private String arm_desc;
	private String created_by;	
	private Date created_on;
	private String modified_by;	
	private Date modified_on;
	private String status;
	private int version_no;
	private int arm_type_flag;
	private String remarkes;
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	} 
	
	public String getArm_code() {
		return arm_code;
	}
	public void setArm_code(String arm_code) {
		this.arm_code = arm_code;
	}
	public String getArm_desc() {
		return arm_desc;
	}
	public void setArm_desc(String arm_desc) {
		this.arm_desc = arm_desc;
	}
	
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	
	public Date getCreated_on() {
		return created_on;
	}
	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}
	
	
	public String getModified_by() {
		return modified_by;
	}
	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}
	
	public Date getModified_on() {
		return modified_on;
	}
	public void setModified_on(Date modified_on) {
		this.modified_on = modified_on;
	}
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	public int getVersion_no() {
		return version_no;
	}
	public void setVersion_no(int version_no) {
		this.version_no = version_no;
	}
	public int getArm_type_flag() {
		return arm_type_flag;
	}
	public void setArm_type_flag(int arm_type_flag) {
		this.arm_type_flag = arm_type_flag;
	}
	
	
	public String getRemarkes() {
		return remarkes;
	}
	public void setRemarkes(String remarkes) {
		this.remarkes = remarkes;
	}
	
	

}
