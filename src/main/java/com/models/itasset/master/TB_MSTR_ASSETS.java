package com.models.itasset.master;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_mstr_assets", uniqueConstraints = {
@UniqueConstraint(columnNames = "id")})
public class TB_MSTR_ASSETS {
	private int id;
	private int category;
	private String assets_name;
	private String created_by;
	private Date created_dt;
	private String modifed_by;
	private Date modified_dt;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public String getAssets_name() {
		return assets_name;
	}
	public void setAssets_name(String assets_name) {
		this.assets_name = assets_name;
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
	public String getModifed_by() {
		return modifed_by;
	}
	public void setModifed_by(String modifed_by) {
		this.modifed_by = modifed_by;
	}
	public Date getModified_dt() {
		return modified_dt;
	}
	public void setModified_dt(Date modified_dt) {
		this.modified_dt = modified_dt;
	}

}
