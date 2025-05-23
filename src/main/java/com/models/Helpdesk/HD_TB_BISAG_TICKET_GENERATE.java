package com.models.Helpdesk;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.format.annotation.DateTimeFormat;



import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Table(name = "ticket_generate", uniqueConstraints = {
@UniqueConstraint(columnNames = "id"),})

public class HD_TB_BISAG_TICKET_GENERATE {
	
	 private int id ;
	 private int userid ;
	 private String  username ;
	 private String  section ;
	 //private String  unit_name ;
	 private String  help_topic ;	
	 private int module ;
	 private int sub_module ;
	 private int screen_name ;
	 private String  screen_shot ;
	 private String  description;
	 private String  issue_summary;
	 private String ticket_status ;
	 private String assigned_to;
	 private String remarks;
	 private String it_tec_remarks;
	 private String assignuser;
	
	 private String it_tech_approve_status;

	@Column(name = "ticket", unique = true, nullable = false)
	 private int ticket ;
	 @DateTimeFormat (pattern = "yyyy-MM-dd")
	private Date created_on ;
	@DateTimeFormat (pattern = "yyyy-MM-dd")
	private Date assigned_dt ;
	public Date getAssigned_dt() {
		return assigned_dt;
	}
	public void setAssigned_dt(Date assigned_dt) {
		this.assigned_dt = assigned_dt;
	}
	public Date getCompleted_dt() {
		return completed_dt;
	}
	public void setCompleted_dt(Date completed_dt) {
		this.completed_dt = completed_dt;
	}
	@DateTimeFormat (pattern = "yyyy-MM-dd")
	private Date completed_dt ;
	private String created_by ;
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
//	public String getSus_no() {
//		return sus_no;
//	}
//	public void setSus_no(String sus_no) {
//		this.sus_no = sus_no;
//	}
	public String getHelp_topic() {
		return help_topic;
	}
	public void setHelp_topic(String help_topic) {
		this.help_topic = help_topic;
	}
	public int getModule() {
		return module;
	}
	public void setModule(int module) {
		this.module = module;
	}
	public int getSub_module() {
		return sub_module;
	}
	public void setSub_module(int sub_module) {
		this.sub_module = sub_module;
	}
	public int getScreen_name() {
		return screen_name;
	}
	public void setScreen_name(int screen_name) {
		this.screen_name = screen_name;
	}
	public String getScreen_shot() {
		return screen_shot;
	}
	public void setScreen_shot(String screen_shot) {
		this.screen_shot = screen_shot;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIssue_summary() {
		return issue_summary;
	}
	public void setIssue_summary(String issue_summary) {
		this.issue_summary = issue_summary;
	}
	public String getTicket_status() {
		return ticket_status;
	}
	public void setTicket_status(String ticket_status) {
		this.ticket_status = ticket_status;
	}
	public String getAssigned_to() {
		return assigned_to;
	}
	public void setAssigned_to(String assigned_to) {
		this.assigned_to = assigned_to;
	}
	public int getTicket() {
		return ticket;
	}
	public void setTicket(int ticket) {
		this.ticket = ticket;
	}
	public Date getCreated_on() {
		return created_on;
	}
	public void setCreated_on(Date created_on) {
		this.created_on = created_on;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	 public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getAssignuser() {
		return assignuser;
	}
	public void setAssignuser(String assignuser) {
		this.assignuser = assignuser;
	}

	public String getIt_tech_approve_status() {
		return it_tech_approve_status;
	}
	public void setIt_tech_approve_status(String it_tech_approve_status) {
		this.it_tech_approve_status = it_tech_approve_status;
	}
	public String getIt_tec_remarks() {
		return it_tec_remarks;
	}
	public void setIt_tec_remarks(String it_tec_remarks) {
		this.it_tec_remarks = it_tec_remarks;
	}
	

	
	
}
