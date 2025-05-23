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
@Table(name = "it_asset_inter_unit_trnsf_perif", uniqueConstraints = {
@UniqueConstraint(columnNames = "id")})

public class It_Asset_Inter_Unit_Trnsf_Perif {
	private int id;
	private String from_sus;
	private String to_sus;
	private String machine_no;
	private int machine_id;
	private int status=0;
	private String created_by;
	private Date created_dt;
	private String modified_by;
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
	public String getFrom_sus() {
		return from_sus;
	}
	public void setFrom_sus(String from_sus) {
		this.from_sus = from_sus;
	}
	public String getTo_sus() {
		return to_sus;
	}
	public void setTo_sus(String to_sus) {
		this.to_sus = to_sus;
	}
	public String getMachine_no() {
		return machine_no;
	}
	public void setMachine_no(String machine_no) {
		this.machine_no = machine_no;
	}
	public int getMachine_id() {
		return machine_id;
	}
	public void setMachine_id(int machine_id) {
		this.machine_id = machine_id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
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
