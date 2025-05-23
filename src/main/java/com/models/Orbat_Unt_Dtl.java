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
@Table(name = "tb_orbat_unt_dtl", uniqueConstraints = {
@UniqueConstraint(columnNames = "id"),})
public class Orbat_Unt_Dtl {
	
	  private int id;
	  private String sus_no;
	  private String form_code_control;
	  private String form_code_admin;
	  private String form_code_operation;
	  private String letter_no;
	  private String unit_name;
	  private String status_sus_no;
	  private String convert_status;
	  private String address;
	  private String remarks;
	  private String code;
	  private String code_type;
	  private String modification;
	  private String type_force;
	  private String ct_part_i_ii;
	  private String entity;
	  private String entity_contd;
	  private int version_no;
	  private String softdelete;
	  private String parent_sus_no;
	  private String sus_no_for_comb_disint;
	  private String unit_cat;
	  private int sus_version;
	  private String type_of_location;
	  private String state;
	  private String district;
	  private String arm_code;
	  private String unit_army_hq;
	  private String creation_by;
	  private String modified_by;
	  @DateTimeFormat(pattern = "yyyy-MM-dd")
	  private Date creation_on;
	  @DateTimeFormat(pattern = "yyyy-MM-dd")
	  private Date modified_on;
	  private String letter_id;
	  private String is_unit_pending;
	  private String nrs_code;
	  private String condition;
	  @DateTimeFormat(pattern = "yyyy-MM-dd")
	  private Date comm_depart_date;
	  @DateTimeFormat(pattern = "yyyy-MM-dd")
	  private Date compltn_arrv_date;
	  private String old_unit_status;
	  
	  private String level_c;
	  private String level_d;
	  
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
  	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getSus_no() {
		return sus_no;
	}
	public void setSus_no(String sus_no) {
		this.sus_no = sus_no;
	}
	
	public String getForm_code_control() {
		return form_code_control;
	}
	public void setForm_code_control(String form_code_control) {
		this.form_code_control = form_code_control;
	}
	
	public String getForm_code_admin() {
		return form_code_admin;
	}
	public void setForm_code_admin(String form_code_admin) {
		this.form_code_admin = form_code_admin;
	}
	
	public String getForm_code_operation() {
		return form_code_operation;
	}
	public void setForm_code_operation(String form_code_operation) {
		this.form_code_operation = form_code_operation;
	}
	
	public String getLetter_no() {
		return letter_no;
	}
	public void setLetter_no(String letter_no) {
		this.letter_no = letter_no;
	}
	
	public String getUnit_name() {
		return unit_name;
	}
	public void setUnit_name(String unit_name) {
		this.unit_name = unit_name;
	}
	
	public String getStatus_sus_no() {
		return status_sus_no;
	}
	public void setStatus_sus_no(String status_sus_no) {
		this.status_sus_no = status_sus_no;
	}
	
	/*public String getSpecial_equip_held() {
		return special_equip_held;
	}
	public void setSpecial_equip_held(String special_equip_held) {
		this.special_equip_held = special_equip_held;
	}*/
	
	public String getConvert_status() {
		return convert_status;
	}
	public void setConvert_status(String convert_status) {
		this.convert_status = convert_status;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getCode_type() {
		return code_type;
		
	}
	public void setCode_type(String code_type) {
		this.code_type = code_type;
	}
	
	public String getModification() {
		return modification;
	}
	public void setModification(String modification) {
		this.modification = modification;
	}
	
	public String getType_force() {
		return type_force;
	}
	public void setType_force(String type_force) {
		this.type_force = type_force;
	}
	
	public String getCt_part_i_ii() {
		return ct_part_i_ii;
	}
	public void setCt_part_i_ii(String ct_part_i_ii) {
		this.ct_part_i_ii = ct_part_i_ii;
	}
	
	/*public String getWe_pe_no() {
		return we_pe_no;
	}
	public void setWe_pe_no(String we_pe_no) {
		this.we_pe_no = we_pe_no;
	}*/
	
	public String getEntity() {
		return entity;
	}
	public void setEntity(String entity) {
		this.entity = entity;
	}
	
	public String getEntity_contd() {
		return entity_contd;
	}
	public void setEntity_contd(String entity_contd) {
		this.entity_contd = entity_contd;
	}
	
	/*public String getWet_pet_no() {
		return wet_pet_no;
	}
	public void setWet_pet_no(String wet_pet_no) {
		this.wet_pet_no = wet_pet_no;
	}*/
	
	/*public String getCif_role() {
		return cif_role;
	}
	public void setCif_role(String cif_role) {
		this.cif_role = cif_role;
	}*/
	
	public int getVersion_no() {
		return version_no;
	}
	public void setVersion_no(int version_no) {
		this.version_no = version_no;
	}
	
	public String getSoftdelete() {
		return softdelete;
	}
	public void setSoftdelete(String softdelete) {
		this.softdelete = softdelete;
	}
	
	/*public String getWe_created_by() {
		return we_created_by;
	}
	public void setWe_created_by(String we_created_by) {
		this.we_created_by = we_created_by;
	}*/
	
	/*public String getWe_created_on() {
		return we_created_on;
	}
	public void setWe_created_on(String we_created_on) {
		this.we_created_on = we_created_on;
	}*/
	
	/*public String getWe_modified_by() {
		return we_modified_by;
	}
	public void setWe_modified_by(String we_modified_by) {
		this.we_modified_by = we_modified_by;
	}*/
	
	/*public String getWe_modified_on() {
		return we_modified_on;
	}
	public void setWe_modified_on(String we_modified_on) {
		this.we_modified_on = we_modified_on;
	}*/
	
	/*public String getRevocation_date() {
		return revocation_date;
	}
	public void setRevocation_date(String revocation_date) {
		this.revocation_date = revocation_date;
	}
	
	public String getMve_with_wthout_equip_vehs() {
		return mve_with_wthout_equip_vehs;
	}
	public void setMve_with_wthout_equip_vehs(String mve_with_wthout_equip_vehs) {
		this.mve_with_wthout_equip_vehs = mve_with_wthout_equip_vehs;
	}
	
	public String getWe_approved_by() {
		return we_approved_by;
	}
	public void setWe_approved_by(String we_approved_by) {
		this.we_approved_by = we_approved_by;
	}
	
	public String getWe_approved_on() {
		return we_approved_on;
	}
	public void setWe_approved_on(String we_approved_on) {
		this.we_approved_on = we_approved_on;
	}
	
	public String getWet_created_by() {
		return wet_created_by;
	}
	public void setWet_created_by(String wet_created_by) {
		this.wet_created_by = wet_created_by;
	}
	
	public String getWet_created_on() {
		return wet_created_on;
	}
	public void setWet_created_on(String wet_created_on) {
		this.wet_created_on = wet_created_on;
	}
	
	public String getWet_modified_by() {
		return wet_modified_by;
	}
	public void setWet_modified_by(String wet_modified_by) {
		this.wet_modified_by = wet_modified_by;
	}
	
	public String getWet_modified_on() {
		return wet_modified_on;
	}
	public void setWet_modified_on(String wet_modified_on) {
		this.wet_modified_on = wet_modified_on;
	}
	
	public String getWet_approved_by() {
		return wet_approved_by;
	}
	public void setWet_approved_by(String wet_approved_by) {
		this.wet_approved_by = wet_approved_by;
	}
	
	public String getWet_approved_on() {
		return wet_approved_on;
	}
	public void setWet_approved_on(String wet_approved_on) {
		this.wet_approved_on = wet_approved_on;
	}
	
	public String getSp_eqp_cif_created_on() {
		return sp_eqp_cif_created_on;
	}
	public void setSp_eqp_cif_created_on(String sp_eqp_cif_created_on) {
		this.sp_eqp_cif_created_on = sp_eqp_cif_created_on;
	}
	
	public String getSp_eqp_cif_modified_by() {
		return sp_eqp_cif_modified_by;
	}
	public void setSp_eqp_cif_modified_by(String sp_eqp_cif_modified_by) {
		this.sp_eqp_cif_modified_by = sp_eqp_cif_modified_by;
	}
	
	public String getSp_eqp_cif_modified_on() {
		return sp_eqp_cif_modified_on;
	}
	public void setSp_eqp_cif_modified_on(String sp_eqp_cif_modified_on) {
		this.sp_eqp_cif_modified_on = sp_eqp_cif_modified_on;
	}
	
	public String getSp_eqp_cif_approve_by() {
		return sp_eqp_cif_approve_by;
	}
	public void setSp_eqp_cif_approve_by(String sp_eqp_cif_approve_by) {
		this.sp_eqp_cif_approve_by = sp_eqp_cif_approve_by;
	}
	
	public String getSp_eqp_cif_approved_on() {
		return sp_eqp_cif_approved_on;
	}
	public void setSp_eqp_cif_approved_on(String sp_eqp_cif_approved_on) {
		this.sp_eqp_cif_approved_on = sp_eqp_cif_approved_on;
	}
	
	public String getLink_we_status() {
		return link_we_status;
	}
	public void setLink_we_status(String link_we_status) {
		this.link_we_status = link_we_status;
	}
	
	public String getLink_wet_status() {
		return link_wet_status;
	}
	public void setLink_wet_status(String link_wet_status) {
		this.link_wet_status = link_wet_status;
	}
	
	public String getSp_eqp_cif_status() {
		return sp_eqp_cif_status;
	}
	public void setSp_eqp_cif_status(String sp_eqp_cif_status) {
		this.sp_eqp_cif_status = sp_eqp_cif_status;
	}
	
	public String getSp_eqp_cif_created_by() {
		return sp_eqp_cif_created_by;
	}
	public void setSp_eqp_cif_created_by(String sp_eqp_cif_created_by) {
		this.sp_eqp_cif_created_by = sp_eqp_cif_created_by;
	}
	*/
	public String getParent_sus_no() {
		return parent_sus_no;
	}
	public void setParent_sus_no(String parent_sus_no) {
		this.parent_sus_no = parent_sus_no;
	}
	
	/*public String getPymnt_bookd_debt() {
		return pymnt_bookd_debt;
	}
	public void setPymnt_bookd_debt(String pymnt_bookd_debt) {
		this.pymnt_bookd_debt = pymnt_bookd_debt;
	}*/
	
	public String getSus_no_for_comb_disint() {
		return sus_no_for_comb_disint;
	}
	public void setSus_no_for_comb_disint(String sus_no_for_comb_disint) {
		this.sus_no_for_comb_disint = sus_no_for_comb_disint;
	}
	
	public String getUnit_cat() {
		return unit_cat;
	}
	public void setUnit_cat(String unit_cat) {
		this.unit_cat = unit_cat;
	}
	
	public int getSus_version() {
		return sus_version;
	}
	public void setSus_version(int sus_version) {
		this.sus_version = sus_version;
	}
	
	public String getType_of_location() {
		return type_of_location;
	}
	public void setType_of_location(String type_of_location) {
		this.type_of_location = type_of_location;
	}
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	
	public String getArm_code() {
		return arm_code;
	}
	public void setArm_code(String arm_code) {
		this.arm_code = arm_code;
	}
	
	public String getUnit_army_hq() {
		return unit_army_hq;
	}
	public void setUnit_army_hq(String unit_army_hq) {
		this.unit_army_hq = unit_army_hq;
	}
	
	public String getCreation_by() {
		return creation_by;
	}
	public void setCreation_by(String creation_by) {
		this.creation_by = creation_by;
	}
	
	public String getModified_by() {
		return modified_by;
	}
	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}
	public Date getCreation_on() {
		return creation_on;
	}
	public void setCreation_on(Date creation_on) {
		this.creation_on = creation_on;
	}
	public Date getModified_on() {
		return modified_on;
	}
	public void setModified_on(Date modified_on) {
		this.modified_on = modified_on;
	}
	public String getLetter_id() {
		return letter_id;
	}
	public void setLetter_id(String letter_id) {
		this.letter_id = letter_id;
	}
	
/*	public String getUe_modification() {
		return ue_modification;
	}
	public void setUe_modification(String ue_modification) {
		this.ue_modification = ue_modification;
	}
	*/
	public String getIs_unit_pending() {
		return is_unit_pending;
	}
	public void setIs_unit_pending(String is_unit_pending) {
		this.is_unit_pending = is_unit_pending;
	}
	
/*	public String getRemarks_wepelink() {
		return remarks_wepelink;
	}
	public void setRemarks_wepelink(String remarks_wepelink) {
		this.remarks_wepelink = remarks_wepelink;
	}*/
	
	public String getNrs_code() {
		return nrs_code;
	}
	public void setNrs_code(String nrs_code) {
		this.nrs_code = nrs_code;
	}
	
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	
	public Date getComm_depart_date() {
		return comm_depart_date;
	}
	public void setComm_depart_date(Date comm_depart_date) {
		this.comm_depart_date = comm_depart_date;
	}
	
	public Date getCompltn_arrv_date() {
		return compltn_arrv_date;
	}
	public void setCompltn_arrv_date(Date compltn_arrv_date) {
		this.compltn_arrv_date = compltn_arrv_date;
	}
	
	public String getOld_unit_status() {
		return old_unit_status;
	}
	public void setOld_unit_status(String old_unit_status) {
		this.old_unit_status = old_unit_status;
	}
	public String getLevel_c() {
		return level_c;
	}
	public void setLevel_c(String level_c) {
		this.level_c = level_c;
	}
	public String getLevel_d() {
		return level_d;
	}
	public void setLevel_d(String level_d) {
		this.level_d = level_d;
	}
}
