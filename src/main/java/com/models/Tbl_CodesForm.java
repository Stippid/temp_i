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
@Table(name = "tb_orbat_codesform", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id")})
public class Tbl_CodesForm {

	private int id;
	private String sus_no;
	private String level_in_hierarchy;
	private String formation_code;
	private String status_record;
	private String parent_code;
	private String create_by;
	private Date created_on;
	private String modified_by;
	private Date modified_on;
	private String approved_rejected_by;
	private Date approved_rejected_on;
	private String filler_1;
	private String filler_2;
	private String filler_3;
	private int version_on;
	private String softdelete;
	private String unit_identifier;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSus_no() {
		return sus_no;
	}

	public void setSus_no(String sus_no) {
		this.sus_no = sus_no;
	}

	public String getLevel_in_hierarchy() {
		return level_in_hierarchy;
	}

	public void setLevel_in_hierarchy(String level_in_hierarchy) {
		this.level_in_hierarchy = level_in_hierarchy;
	}

	public String getFormation_code() {
		return formation_code;
	}

	public void setFormation_code(String formation_code) {
		this.formation_code = formation_code;
	}

	public String getStatus_record() {
		return status_record;
	}

	public void setStatus_record(String status_record) {
		this.status_record = status_record;
	}

	public String getParent_code() {
		return parent_code;
	}

	public void setParent_code(String parent_code) {
		this.parent_code = parent_code;
	}

	public String getCreate_by() {
		return create_by;
	}

	public void setCreate_by(String create_by) {
		this.create_by = create_by;
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

	public String getFiller_1() {
		return filler_1;
	}

	public void setFiller_1(String filler_1) {
		this.filler_1 = filler_1;
	}

	public String getFiller_2() {
		return filler_2;
	}

	public void setFiller_2(String filler_2) {
		this.filler_2 = filler_2;
	}

	public String getFiller_3() {
		return filler_3;
	}

	public void setFiller_3(String filler_3) {
		this.filler_3 = filler_3;
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

	public String getUnit_identifier() {
		return unit_identifier;
	}

	public void setUnit_identifier(String unit_identifier) {
		this.unit_identifier = unit_identifier;
	}
}
