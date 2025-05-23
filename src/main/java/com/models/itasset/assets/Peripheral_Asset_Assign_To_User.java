package com.models.itasset.assets;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_peripheral_assign_asset_user", uniqueConstraints = {
@UniqueConstraint(columnNames = "id")})
public class Peripheral_Asset_Assign_To_User {
	@Id
	@GeneratedValue
	private int id;
	private String section;
	private String assets_type;
	private String type_of_hw;
	private String make_name;
	private String model_name;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	private String assignuser;
	private String assetcount;
	private String assignstatus;
	
	public String getAssignstatus() {
		return assignstatus;
	}
	public void setAssignstatus(String assignstatus) {
		this.assignstatus = assignstatus;
	}
	
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
	public String getAssets_type() {
		return assets_type;
	}
	public void setAssets_type(String assets_type) {
		this.assets_type = assets_type;
	}
	public String getType_of_hw() {
		return type_of_hw;
	}
	public void setType_of_hw(String type_of_hw) {
		this.type_of_hw = type_of_hw;
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
	
	
	

}
