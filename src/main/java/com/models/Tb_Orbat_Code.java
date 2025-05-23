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
@Table(name = "tb_orbat_code", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id") })
public class Tb_Orbat_Code {
	
	private int id;
	private String code_type;
	private String code_value;
	private String code;
	private String status_record;
	private String created_by;	
	private Date created_on;
	private String modified_by;	
	private Date modified_on;
	private String approved_rejected_by;
	private Date approved_rejected_on;
	private int type_mapping;
	private int version_on;
	private String softdelete;
	private String remarks; 
	private String type_loc;
	private String mod_desc;
	private String nrs_code;
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	public String getCode_type() {
		return code_type;
	}
	public void setCode_type(String code_type) {
		this.code_type = code_type;
	}
	
	
	public String getCode_value() {
		return code_value;
	}
	public void setCode_value(String code_value) {
		this.code_value = code_value;
	}
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
	public String getStatus_record() {
		return status_record;
	}
	public void setStatus_record(String status_record) {
		this.status_record = status_record;
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
	
	
	public String getApproved_rejected_by() {
		return approved_rejected_by;
	}
	public void setApproved_rejected_by(String approved_rejected_by) {
		this.approved_rejected_by = approved_rejected_by;
	}
	
	
	public Date getApproved_rejected_on() {
		return approved_rejected_on;
	}
	public void setApproved_rejected_on(Date approved_rejected_on) {
		this.approved_rejected_on = approved_rejected_on;
	}
	
	
	public int getType_mapping() {
		return type_mapping;
	}
	public void setType_mapping(int type_mapping) {
		this.type_mapping = type_mapping;
	}
	
	
	public int getVersion_on() {
		return version_on;
	}
	public void setVersion_on(int version_on) {
		this.version_on = version_on;
	}
	
	public String getSoftdelete() {
		return softdelete;
	}
	public void setSoftdelete(String softdelete) {
		this.softdelete = softdelete;
	}
	
	
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
	public String getType_loc() {
		return type_loc;
	}
	public void setType_loc(String type_loc) {
		this.type_loc = type_loc;
	}
	
	public String getMod_desc() {
		return mod_desc;
	}
	public void setMod_desc(String mod_desc) {
		this.mod_desc = mod_desc;
	}
	
	
	public String getNrs_code() {
		return nrs_code;
	}
	public void setNrs_code(String nrs_code) {
		this.nrs_code = nrs_code;
	}
	
	
	
	

}
