package com.models.itasset.assets;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_assign_asset_user", uniqueConstraints = {
@UniqueConstraint(columnNames = "id")})
public class Assign_Asset_to_user {
	@Id
	@GeneratedValue
	private int id;
	private String section;
	private String asset_type;
	private String make_name;
	private String model_name;
	private String processor_type;
	private String ram_capi;
	private String hdd_capi;
	private String ssdcheck;
	private String ssd_capi;
	private String operating_system;
	private String assignuser;
	private String assetcount;
	private String assignstatus;
	private String created_by;
	private Date created_date;
	private Integer comm_id;
	private String modified_by;
	private Date modified_date;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public String getAsset_type() {
		return asset_type;
	}
	public void setAsset_type(String asset_type) {
		this.asset_type = asset_type;
	}
	public String getMake_name() {
		return make_name;
	}
	public void setMake_name(String make_name) {
		this.make_name = make_name;
	}
	public String getModel_name() {
		return model_name;
	}
	public void setModel_name(String model_name) {
		this.model_name = model_name;
	}
	public String getProcessor_type() {
		return processor_type;
	}
	public void setProcessor_type(String processor_type) {
		this.processor_type = processor_type;
	}
	public String getRam_capi() {
		return ram_capi;
	}
	public void setRam_capi(String ram_capi) {
		this.ram_capi = ram_capi;
	}
	public String getHdd_capi() {
		return hdd_capi;
	}
	public void setHdd_capi(String hdd_capi) {
		this.hdd_capi = hdd_capi;
	}
	public String getSsdcheck() {
		return ssdcheck;
	}
	public void setSsdcheck(String ssdcheck) {
		this.ssdcheck = ssdcheck;
	}

	public String getSsd_capi() {
		return ssd_capi;
	}
	public void setSsd_capi(String ssd_capi) {
		this.ssd_capi = ssd_capi;
	}
	public String getOperating_system() {
		return operating_system;
	}
	public void setOperating_system(String operating_system) {
		this.operating_system = operating_system;
	}
	public String getAssignuser() {
		return assignuser;
	}
	public void setAssignuser(String assignuser) {
		this.assignuser = assignuser;
	}
	public String getAssetcount() {
		return assetcount;
	}
	public void setAssetcount(String assetcount) {
		this.assetcount = assetcount;
	}
	public String getAssignstatus() {
		return assignstatus;
	}
	public void setAssignstatus(String assignstatus) {
		this.assignstatus = assignstatus;
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
	public Integer getComm_id() {
		return comm_id;
	}
	public void setComm_id(Integer comm_id) {
		this.comm_id = comm_id;
	}
	
	
	
	


}
