package com.models.itasset.master;
import static javax.persistence.GenerationType.IDENTITY;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;





@Entity
@Table(name = "tb_orbat_line_dte", uniqueConstraints = {
@UniqueConstraint(columnNames = "id")})
public class TB_ORBAT_LINE_DTE {
	
	private int id;
	private String arm_code;
	private String arm_desc;
	private String line_dte_name;
	private String line_dte_sus;
	
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
	public String getLine_dte_name() {
		return line_dte_name;
	}
	public void setLine_dte_name(String line_dte_name) {
		this.line_dte_name = line_dte_name;
	}
	public String getLine_dte_sus() {
		return line_dte_sus;
	}
	public void setLine_dte_sus(String line_dte_sus) {
		this.line_dte_sus = line_dte_sus;
	}
	
	

}
