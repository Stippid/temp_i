package com.models;

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
@Table(name = "mms_tb_mlccs_new_req", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})

public class MMS_TB_MLCCS_NEW_REQ {
	
	private int id;
	private String req_agency;
	private String auth_lett_no;
	private String auth_date; 
	private String prf_code;
	private String prf_group;
	private String cat_part_no;
	private String nomen;
	private String brief_desc;
	private String au;
	private String item_status;
	private String item_category;
	private String upload_file_name;
	private String spl_remarks;
	private String remarks;
	private String data_cr_by;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date data_cr_date =new Date();;
	private String data_upd_by;
	private Date data_upd_date;
	private String data_app_by;
	private Date data_app_date;
	private String op_status;
	private String alot_census_no;
	private String reject_remarks;
	private String reject_letter_no;
	private int roleid;
	

	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getReq_agency() {
		return req_agency;
	}
	public void setReq_agency(String req_agency) {
		this.req_agency = req_agency;
	}
	public String getAuth_lett_no() {
		return auth_lett_no;
	}
	public void setAuth_lett_no(String auth_lett_no) {
		this.auth_lett_no = auth_lett_no;
	}
	public String getAuth_date() {
		return auth_date;
	}
	public void setAuth_date(String auth_date) {
		this.auth_date = auth_date;
	}
	public String getPrf_code() {
		return prf_code;
	}
	public void setPrf_code(String prf_code) {
		this.prf_code = prf_code;
	}
	public String getPrf_group() {
		return prf_group;
	}
	public void setPrf_group(String prf_group) {
		this.prf_group = prf_group;
	}
	public String getCat_part_no() {
		return cat_part_no;
	}
	public void setCat_part_no(String cat_part_no) {
		this.cat_part_no = cat_part_no;
	}
	public String getNomen() {
		return nomen;
	}
	public void setNomen(String nomen) {
		this.nomen = nomen;
	}
	public String getBrief_desc() {
		return brief_desc;
	}
	public void setBrief_desc(String brief_desc) {
		this.brief_desc = brief_desc;
	}
	public String getAu() {
		return au;
	}
	public void setAu(String au) {
		this.au = au;
	}
	public String getItem_status() {
		return item_status;
	}
	public void setItem_status(String item_status) {
		this.item_status = item_status;
	}
	public String getItem_category() {
		return item_category;
	}
	public void setItem_category(String item_category) {
		this.item_category = item_category;
	}
	public String getUpload_file_name() {
		return upload_file_name;
	}
	public void setUpload_file_name(String upload_file_name) {
		this.upload_file_name = upload_file_name;
	}
	public String getSpl_remarks() {
		return spl_remarks;
	}
	public void setSpl_remarks(String spl_remarks) {
		this.spl_remarks = spl_remarks;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getData_cr_by() {
		return data_cr_by;
	}
	public void setData_cr_by(String data_cr_by) {
		this.data_cr_by = data_cr_by;
	}
	public Date getData_cr_date() {
		return data_cr_date;
	}
	public void setData_cr_date(Date data_cr_date) {
		this.data_cr_date = data_cr_date;
	}
	public String getData_upd_by() {
		return data_upd_by;
	}
	public void setData_upd_by(String data_upd_by) {
		this.data_upd_by = data_upd_by;
	}
	public Date getData_upd_date() {
		return data_upd_date;
	}
	public void setData_upd_date(Date data_upd_date) {
		this.data_upd_date = data_upd_date;
	}
	public String getData_app_by() {
		return data_app_by;
	}
	public void setData_app_by(String data_app_by) {
		this.data_app_by = data_app_by;
	}
	public Date getData_app_date() {
		return data_app_date;
	}
	public void setData_app_date(Date data_app_date) {
		this.data_app_date = data_app_date;
	}
	public String getOp_status() {
		return op_status;
	}
	public void setOp_status(String op_status) {
		this.op_status = op_status;
	}
	public String getAlot_census_no() {
		return alot_census_no;
	}
	public void setAlot_census_no(String alot_census_no) {
		this.alot_census_no = alot_census_no;
	}
	public int getRoleid() {
		return roleid;
	}
	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}
	public String getReject_remarks() {
		return reject_remarks;
	}
	public void setReject_remarks(String reject_remarks) {
		this.reject_remarks = reject_remarks;
	}
	public String getReject_letter_no() {
		return reject_letter_no;
	}
	public void setReject_letter_no(String reject_letter_no) {
		this.reject_letter_no = reject_letter_no;
	}

}
