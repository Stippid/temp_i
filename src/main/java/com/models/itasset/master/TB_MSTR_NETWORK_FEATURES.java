package com.models.itasset.master;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tb_mstr_network_features", uniqueConstraints = {
@UniqueConstraint(columnNames = "id")})

public class TB_MSTR_NETWORK_FEATURES {
	
	private int id;
	private String network_features;
	private String created_by;
	 @DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date created_dt;
	private String modified_by;
	 @DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date modified_dt;
	 
	 @Id
     @GeneratedValue(strategy = IDENTITY)
     @Column(name = "id", unique = true, nullable = false)
	 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNetwork_features() {
		return network_features;
	}
	public void setNetwork_features(String network_features) {
		this.network_features = network_features;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public Date getCreated_dt() {
		return created_dt;
	}
	public void setCreated_dt(Date created_dt) {
		this.created_dt = created_dt;
	}
	public String getModified_by() {
		return modified_by;
	}
	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}
	public Date getModified_dt() {
		return modified_dt;
	}
	public void setModified_dt(Date modified_dt) {
		this.modified_dt = modified_dt;
	}
}
