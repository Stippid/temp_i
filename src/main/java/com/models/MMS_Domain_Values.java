package com.models;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "mms_domain_values", uniqueConstraints = {
		@UniqueConstraint(columnNames = "id") })


public class MMS_Domain_Values {

	private int id ;
	private String domainid ;
	private String  codevalue ;
	private String label ;
	private String  lastupdatedby ;
	private String  lastupdateddatetime ;
	private String  createdby ;
	private String  createddatetime ;
	private String versionno ;
	private String module ;
	private String label_s ;
	private String disp_order ;
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDomainid() {
		return domainid;
	}
	public void setDomainid(String domainid) {
		this.domainid = domainid;
	}
	public String getCodevalue() {
		return codevalue;
	}
	public void setCodevalue(String codevalue) {
		this.codevalue = codevalue;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getLastupdatedby() {
		return lastupdatedby;
	}
	public void setLastupdatedby(String lastupdatedby) {
		this.lastupdatedby = lastupdatedby;
	}
	public String getLastupdateddatetime() {
		return lastupdateddatetime;
	}
	public void setLastupdateddatetime(String lastupdateddatetime) {
		this.lastupdateddatetime = lastupdateddatetime;
	}
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	public String getCreateddatetime() {
		return createddatetime;
	}
	public void setCreateddatetime(String createddatetime) {
		this.createddatetime = createddatetime;
	}
	public String getVersionno() {
		return versionno;
	}
	public void setVersionno(String versionno) {
		this.versionno = versionno;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getLabel_s() {
		return label_s;
	}
	public void setLabel_s(String label_s) {
		this.label_s = label_s;
	}
	public String getDisp_order() {
		return disp_order;
	}
	public void setDisp_order(String disp_order) {
		this.disp_order = disp_order;
	}
	
	
	
}
