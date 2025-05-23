package com.models.assets;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_consumable_child", uniqueConstraints = {
@UniqueConstraint(columnNames = "id")})
public class CONSUMABLES_CHILD {
	private int id;
	private int p_id;
	private int status;

	private Date warranty;
	private Date created_dt;
	private Date modified_date;
	private String created_by;
	private String modified_by;
	
	private int service_state=0;
	private int unservice_state=0;
	private String warrantycheck;

	private Date unsv_from_dt;
	private Date unsv_to_dt;
	private int issue_quantity;
	private String request_status;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getWarranty() {
		return warranty;
	}
	public void setWarranty(Date warranty) {
		this.warranty = warranty;
	}
	public Date getCreated_dt() {
		return created_dt;
	}
	public void setCreated_dt(Date created_dt) {
		this.created_dt = created_dt;
	}
	public Date getModified_date() {
		return modified_date;
	}
	public void setModified_date(Date modified_date) {
		this.modified_date = modified_date;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public String getModified_by() {
		return modified_by;
	}
	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}
	public int getUnservice_state() {
		return unservice_state;
	}
	public void setUnservice_state(int unservice_state) {
		this.unservice_state = unservice_state;
	}
	public int getService_state() {
		return service_state;
	}
	public void setService_state(int service_state) {
		this.service_state = service_state;
	}
	public String getWarrantycheck() {
		return warrantycheck;
	}
	public void setWarrantycheck(String warrantycheck) {
		this.warrantycheck = warrantycheck;
	}
	
	public Date getUnsv_from_dt() {
		return unsv_from_dt;
	}
	public void setUnsv_from_dt(Date unsv_from_dt) {
		this.unsv_from_dt = unsv_from_dt;
	}
	public Date getUnsv_to_dt() {
		return unsv_to_dt;
	}
	public void setUnsv_to_dt(Date unsv_to_dt) {
		this.unsv_to_dt = unsv_to_dt;
	}
	public int getIssue_quantity() {
		return issue_quantity;
	}
	public void setIssue_quantity(int issue_quantity) {
		this.issue_quantity = issue_quantity;
	}
	public String getRequest_status() {
		return request_status;
	}
	public void setRequest_status(String request_status) {
		this.request_status = request_status;
	}
	
	

}
