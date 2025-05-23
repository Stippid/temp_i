package com.models.itasset.Workorder;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_it_asset_work_order", uniqueConstraints = {
@UniqueConstraint(columnNames = "id")})
public class TB_IT_ASSET_WORK_ORDER {
	private int id;
	private String wo_no;
	private Date wo_dt;
	private String machine_number;
	private int wk_generate_status;
	private int category;
	private String defect_obs;
	private Date dt_eqpt_reqd_fwd_wksp;
	private String wksp_sus_no;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private int wk_generated_status;
	private int p_id;
	private String wksp_unit_name;
	
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
	public String getMachine_number() {
		return machine_number;
	}
	public void setMachine_number(String machine_number) {
		this.machine_number = machine_number;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public String getDefect_obs() {
		return defect_obs;
	}
	public void setDefect_obs(String defect_obs) {
		this.defect_obs = defect_obs;
	}
	public Date getDt_eqpt_reqd_fwd_wksp() {
		return dt_eqpt_reqd_fwd_wksp;
	}
	public void setDt_eqpt_reqd_fwd_wksp(Date dt_eqpt_reqd_fwd_wksp) {
		this.dt_eqpt_reqd_fwd_wksp = dt_eqpt_reqd_fwd_wksp;
	}
	public String getWksp_sus_no() {
		return wksp_sus_no;
	}
	public void setWksp_sus_no(String wksp_sus_no) {
		this.wksp_sus_no = wksp_sus_no;
	}
	public int getWk_generate_status() {
		return wk_generate_status;
	}
	public void setWk_generate_status(int wk_generate_status) {
		this.wk_generate_status = wk_generate_status;
	}
	public int getWk_generated_status() {
		return wk_generated_status;
	}
	public void setWk_generated_status(int wk_generated_status) {
		this.wk_generated_status = wk_generated_status;
	}
	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
	public String getWksp_unit_name() {
		return wksp_unit_name;
	}
	public void setWksp_unit_name(String wksp_unit_name) {
		this.wksp_unit_name = wksp_unit_name;
	}
	
	
}
