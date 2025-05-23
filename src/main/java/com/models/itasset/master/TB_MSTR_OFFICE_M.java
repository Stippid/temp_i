package com.models.itasset.master;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_mstr_office", uniqueConstraints = {
@UniqueConstraint(columnNames = "id"),})

public class TB_MSTR_OFFICE_M {

      private int id;
      private String office;
      private String created_by;
  @DateTimeFormat(pattern = "dd/MM/yyyy")
      private Date created_date;
      private String modified_by;
  @DateTimeFormat(pattern = "dd/MM/yyyy")
      private Date modified_date;
  
  	private int status=1;



      @Id
      @GeneratedValue(strategy = IDENTITY)
      @Column(name = "id", unique = true, nullable = false)


      public int getId() {
           return id;
      }
      public void setId(int id) {
	  this.id = id;
      }
      public String getOffice() {
           return office;
      }
      public void setOffice(String office) {
	  this.office = office;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
