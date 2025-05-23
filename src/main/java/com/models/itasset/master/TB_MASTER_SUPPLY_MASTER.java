package com.models.itasset.master;

import java.util.Date;


import org.springframework.format.annotation.DateTimeFormat;
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
@Table(name = "tb_mstr_supply_master", uniqueConstraints = {
@UniqueConstraint(columnNames = "id"),})
public class TB_MASTER_SUPPLY_MASTER {

	 private int id;
     private String supplier;
     private String contact_number;
     private String created_by;
 @DateTimeFormat(pattern = "dd/MM/yyyy")
     private Date created_date;
     private String modified_by;
 @DateTimeFormat(pattern = "dd/MM/yyyy")
     private Date modified_date;
 
 @Id
 @GeneratedValue(strategy = IDENTITY)
 @Column(name = "id", unique = true, nullable = false)
 
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getSupplier() {
	return supplier;
}
public void setSupplier(String supplier) {
	this.supplier = supplier;
}
public String getContact_number() {
	return contact_number;
}
public void setContact_number(String contact_number) {
	this.contact_number = contact_number;
}
public String getCreated_by() {
	return created_by;
}
public void setCreated_by(String created_by) {
	this.created_by = created_by;
}
public Date getCreated_date() {
	return created_date;
}
public void setCreated_date(Date created_date) {
	this.created_date = created_date;
}
public String getModified_by() {
	return modified_by;
}
public void setModified_by(String modified_by) {
	this.modified_by = modified_by;
}
public Date getModified_date() {
	return modified_date;
}
public void setModified_date(Date modified_date) {
	this.modified_date = modified_date;
}

 
	
}
