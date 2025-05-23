package com.model.WorkOrder;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_it_computing_wo_p", uniqueConstraints = {
@UniqueConstraint(columnNames = "id")})
public class COMPUTING_WORK_ORDER_P {
	private int id;
	private String wo_no;
	private Date wo_dt;
	private Date dt_eqpt_reqd_fwd_wksp;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private int wk_generated_status;
	private String wksp;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getWo_no() {
		return wo_no;
	}
	public void setWo_no(String wo_no) {
		this.wo_no = wo_no;
	}
	public Date getWo_dt() {
		return wo_dt;
	}
	public void setWo_dt(Date wo_dt) {
		this.wo_dt = wo_dt;
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
	public Date getDt_eqpt_reqd_fwd_wksp() {
		return dt_eqpt_reqd_fwd_wksp;
	}
	public void setDt_eqpt_reqd_fwd_wksp(Date dt_eqpt_reqd_fwd_wksp) {
		this.dt_eqpt_reqd_fwd_wksp = dt_eqpt_reqd_fwd_wksp;
	}
	public int getWk_generated_status() {
		return wk_generated_status;
	}
	public void setWk_generated_status(int wk_generated_status) {
		this.wk_generated_status = wk_generated_status;
	}
	public String getWksp() {
		return wksp;
	}
	public void setWksp(String wksp) {
		this.wksp = wksp;
	}
	

	
}
