package com.models.itasset.master;

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
@Table(name = "tb_mstr_budget", uniqueConstraints = {
@UniqueConstraint(columnNames = "id")})
public class TB_MASTER_BUDGET {
	private int id;
	private String budget_head;
	private String budget_code;
	private String created_by;
	 @DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date created_date;
	private String modified_by;
	 @DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date modified_date;
	
	   @Id
	      @GeneratedValue(strategy = IDENTITY)
	      @Column(name = "id", unique = true, nullable = false)

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBudget_head() {
		return budget_head;
	}
	public void setBudget_head(String budget_head) {
		this.budget_head = budget_head;
	}
	public String getBudget_code() {
		return budget_code;
	}
	public void setBudget_code(String budget_code) {
		this.budget_code = budget_code;
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

	
}
