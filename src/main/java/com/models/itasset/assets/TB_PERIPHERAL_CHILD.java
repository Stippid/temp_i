package com.models.itasset.assets;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tb_peripheral_child", uniqueConstraints = {
@UniqueConstraint(columnNames = "id")})
public class TB_PERIPHERAL_CHILD {
	
	private int id;
	private int p_id;
	private int status;

	private Date warranty;
	private Date created_dt;
	private Date modified_date;
	private String created_by;
	private String modified_by;
	
	private int service_state=0;
	private int unservice_state=0;
	private String warrantycheck;
	
	
	
	private String ip_address;
	private String is_networked;
	private String connectivity_type;
	
	private String hw_interface;
	private int ethernet_port=0;
	private int management_layer=0;
	private String network_features;
	
	//** AHM BISAG **//
	
		private Date unsv_from_dt;
		private Date unsv_to_dt;
		
		//** END AHM BISAG **//
	
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getWarranty() {
		return warranty;
	}
	public void setWarranty(Date warranty) {
		this.warranty = warranty;
	}
	public Date getCreated_dt() {
		return created_dt;
	}
	public void setCreated_dt(Date created_dt) {
		this.created_dt = created_dt;
	}
	public Date getModified_date() {
		return modified_date;
	}
	public void setModified_date(Date modified_date) {
		this.modified_date = modified_date;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public String getModified_by() {
		return modified_by;
	}
	public void setModified_by(String modified_by) {
		this.modified_by = modified_by;
	}
	public int getUnservice_state() {
		return unservice_state;
	}
	public void setUnservice_state(int unservice_state) {
		this.unservice_state = unservice_state;
	}
	public int getService_state() {
		return service_state;
	}
	public void setService_state(int service_state) {
		this.service_state = service_state;
	}
	public String getWarrantycheck() {
		return warrantycheck;
	}
	public void setWarrantycheck(String warrantycheck) {
		this.warrantycheck = warrantycheck;
	}
	//** AHM BISAG **//
	
		public Date getUnsv_from_dt() {
			return unsv_from_dt;
		}
		public void setUnsv_from_dt(Date unsv_from_dt) {
			this.unsv_from_dt = unsv_from_dt;
		}
		public Date getUnsv_to_dt() {
			return unsv_to_dt;
		}
		public void setUnsv_to_dt(Date unsv_to_dt) {
			this.unsv_to_dt = unsv_to_dt;
		}
		public String getIp_address() {
			return ip_address;
		}
		public void setIp_address(String ip_address) {
			this.ip_address = ip_address;
		}
		public String getIs_networked() {
			return is_networked;
		}
		public String getHw_interface() {
			return hw_interface;
		}
		public void setIs_networked(String is_networked) {
			this.is_networked = is_networked;
		}
		public String getConnectivity_type() {
			return connectivity_type;
		}
		public void setConnectivity_type(String connectivity_type) {
			this.connectivity_type = connectivity_type;
		}
		public int getEthernet_port() {
			return ethernet_port;
		}
		public void setEthernet_port(int ethernet_port) {
			this.ethernet_port = ethernet_port;
		}
		public int getManagement_layer() {
			return management_layer;
		}
		public void setManagement_layer(int management_layer) {
			this.management_layer = management_layer;
		}
		public String getNetwork_features() {
			return network_features;
		}
		public void setNetwork_features(String network_features) {
			this.network_features = network_features;
		}
		public void setHw_interface(String hw_interface) {
			this.hw_interface = hw_interface;
		}
		
		
		
		
		
		//** END AHM BISAG **//
	
}
