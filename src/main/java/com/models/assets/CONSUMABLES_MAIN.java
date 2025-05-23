package com.models.assets;

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
@Table(name = "tb_consumable_main", uniqueConstraints = { @UniqueConstraint(columnNames = "id") })
public class CONSUMABLES_MAIN {

	private int id;
	private int assets_type = 0;
	private int type_of_hw = 0;
	private String proc_cost;
	private String model_no;
	private String machine_no;
	private String type;
	private String remarks;
	private int assetcount;
	private String created_by;
	private Date created_date;
	private String modified_by;
	private Date modified_date;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date warranty;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date proc_date;
	private String b_head;
	private String b_code;
	private String b_cost;
	private int model_name = 0;
	private int make_name = 0;
	private int status = 0;
	
	private String year_of_proc;
	private String warrantycheck;
	private String brand_other;
	private String model_other;
	private String peri_hw_other;
	private String model_type_other;
	private String connectivity_other;
	private String u_file;
	private String section;
	private String crv_no;
	private String gem_no;
	@Column(name = "maintain_agency")
	private String maintainagency;
	private String username;
	private String request_status;
	private String redgno;
	
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public int getAssetcount() {
		return assetcount;
	}
	public void setAssetcount(int assetcount) {
		this.assetcount = assetcount;
	}
	public int getAssets_type() {
		return assets_type;
	}
	public void setAssets_type(int assets_type) {
		this.assets_type = assets_type;
	}
	public int getType_of_hw() {
		return type_of_hw;
	}
	public void setType_of_hw(int type_of_hw) {
		this.type_of_hw = type_of_hw;
	}
	public String getProc_cost() {
		return proc_cost;
	}
	public void setProc_cost(String proc_cost) {
		this.proc_cost = proc_cost;
	}
	public String getModel_no() {
		return model_no;
	}
	public void setModel_no(String model_no) {
		this.model_no = model_no;
	}
	public String getMachine_no() {
		return machine_no;
	}
	public void setMachine_no(String machine_no) {
		this.machine_no = machine_no;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	public Date getWarranty() {
		return warranty;
	}
	public void setWarranty(Date warranty) {
		this.warranty = warranty;
	}
	public Date getProc_date() {
		return proc_date;
	}
	public void setProc_date(Date proc_date) {
		this.proc_date = proc_date;
	}
	public String getB_head() {
		return b_head;
	}
	public void setB_head(String b_head) {
		this.b_head = b_head;
	}
	public String getB_code() {
		return b_code;
	}
	public void setB_code(String b_code) {
		this.b_code = b_code;
	}
	public String getB_cost() {
		return b_cost;
	}
	public void setB_cost(String b_cost) {
		this.b_cost = b_cost;
	}
	
	public int getModel_name() {
		return model_name;
	}
	public void setModel_name(int model_name) {
		this.model_name = model_name;
	}
	public int getMake_name() {
		return make_name;
	}
	public void setMake_name(int make_name) {
		this.make_name = make_name;
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getYear_of_proc() {
		return year_of_proc;
	}
	public void setYear_of_proc(String year_of_proc) {
		this.year_of_proc = year_of_proc;
	}
	
	public String getWarrantycheck() {
		return warrantycheck;
	}
	public void setWarrantycheck(String warrantycheck) {
		this.warrantycheck = warrantycheck;
	}
	
	public String getBrand_other() {
		return brand_other;
	}
	public void setBrand_other(String brand_other) {
		this.brand_other = brand_other;
	}
	public String getModel_other() {
		return model_other;
	}
	public void setModel_other(String model_other) {
		this.model_other = model_other;
	}
	public String getPeri_hw_other() {
		return peri_hw_other;
	}
	public void setPeri_hw_other(String peri_hw_other) {
		this.peri_hw_other = peri_hw_other;
	}
	public String getModel_type_other() {
		return model_type_other;
	}
	public void setModel_type_other(String model_type_other) {
		this.model_type_other = model_type_other;
	}
	public String getConnectivity_other() {
		return connectivity_other;
	}
	public void setConnectivity_other(String connectivity_other) {
		this.connectivity_other = connectivity_other;
	}
	public String getU_file() {
		return u_file;
	}
	public void setU_file(String u_file) {
		this.u_file = u_file;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public String getCrv_no() {
		return crv_no;
	}
	public void setCrv_no(String crv_no) {
		this.crv_no = crv_no;
	}
	public String getGem_no() {
		return gem_no;
	}
	public void setGem_no(String gem_no) {
		this.gem_no = gem_no;
	}
	public String getMaintainagency() {
		return maintainagency;
	}
	public void setMaintainagency(String maintainagency) {
		this.maintainagency = maintainagency;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getRequest_status() {
		return request_status;
	}

	public void setRequest_status(String request_status) {
		this.request_status = request_status;
	}

	public String getRedgno() {
		return redgno;
	}

	public void setRedgno(String redgno) {
		this.redgno = redgno;
	}

	

}
