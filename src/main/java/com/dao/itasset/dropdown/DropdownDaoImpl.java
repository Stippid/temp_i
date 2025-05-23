package com.dao.itasset.dropdown;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;



public class DropdownDaoImpl  implements DropdownDao {
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	public List<Map<String, Object>> ComputingAssetListNew(){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String qry = "";
		
		
		try {
			conn = dataSource.getConnection();

			qry = "select distinct id,assets_name,category from tb_mstr_assets  where category='1'";	
		PreparedStatement stmt = conn.prepareStatement(qry);
		
		ResultSet rs = stmt.executeQuery();
		ResultSetMetaData  metaData = rs.getMetaData();
          int columnCount = metaData.getColumnCount();
          while (rs.next()) {
              Map<String, Object> columns = new LinkedHashMap<String, Object>();                        
              for (int i = 1; i <= columnCount; i++) {
                  columns.put(metaData.getColumnLabel(i), rs.getObject(i));
              }
              list.add(columns);
          }
          rs.close();
		stmt.close();
		conn.close();
	} catch (SQLException e) {
		e.getMessage();
	} finally {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
	}
		
	return list;
}
	
	
	public List<Map<String, Object>> ComputingMakeListNew(){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String qry = "";
		
		
		try {
			conn = dataSource.getConnection();

			qry = "select id,make_name from tb_mstr_make";	
		PreparedStatement stmt = conn.prepareStatement(qry);
		
		ResultSet rs = stmt.executeQuery();
		ResultSetMetaData  metaData = rs.getMetaData();
          int columnCount = metaData.getColumnCount();
          while (rs.next()) {
              Map<String, Object> columns = new LinkedHashMap<String, Object>();                        
              for (int i = 1; i <= columnCount; i++) {
                  columns.put(metaData.getColumnLabel(i), rs.getObject(i));
              }
              list.add(columns);
          }
          rs.close();
		stmt.close();
		conn.close();
	} catch (SQLException e) {
		e.getMessage();
	} finally {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
	}

	return list;
}
	
	
	public List<Map<String, Object>> ComputingModelListNew(){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String qry = "";
		
		
		try {
			conn = dataSource.getConnection();

			qry = "select id,model_name from tb_mstr_model";
		PreparedStatement stmt = conn.prepareStatement(qry);
		
		ResultSet rs = stmt.executeQuery();
		ResultSetMetaData  metaData = rs.getMetaData();
          int columnCount = metaData.getColumnCount();
          while (rs.next()) {
              Map<String, Object> columns = new LinkedHashMap<String, Object>();                        
              for (int i = 1; i <= columnCount; i++) {
                  columns.put(metaData.getColumnLabel(i), rs.getObject(i));
              }
              list.add(columns);
          }
          rs.close();
		stmt.close();
		conn.close();
	} catch (SQLException e) {
		e.getMessage();
	} finally {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
	}

	return list;
}
	

	public List<Map<String, Object>> processor_typeListNew(){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String qry = "";
		
		
		try {
			conn = dataSource.getConnection();

			qry = "select distinct id,processor_type from tb_mstr_processor_type";	
		PreparedStatement stmt = conn.prepareStatement(qry);
		
		ResultSet rs = stmt.executeQuery();
		ResultSetMetaData  metaData = rs.getMetaData();
          int columnCount = metaData.getColumnCount();
          while (rs.next()) {
              Map<String, Object> columns = new LinkedHashMap<String, Object>();                        
              for (int i = 1; i <= columnCount; i++) {
                  columns.put(metaData.getColumnLabel(i), rs.getObject(i));
              }
              list.add(columns);
          }
          rs.close();
		stmt.close();
		conn.close();
	} catch (SQLException e) {
		e.getMessage();
	} finally {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
	}

	return list;
}
	
	
	public List<Map<String, Object>> ramListNew(){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String qry = "";
		
		
		try {
			conn = dataSource.getConnection();

			qry = "select id,ram from tb_mstr_ram";	
		PreparedStatement stmt = conn.prepareStatement(qry);
		
		ResultSet rs = stmt.executeQuery();
		ResultSetMetaData  metaData = rs.getMetaData();
          int columnCount = metaData.getColumnCount();
          while (rs.next()) {
              Map<String, Object> columns = new LinkedHashMap<String, Object>();                        
              for (int i = 1; i <= columnCount; i++) {
                  columns.put(metaData.getColumnLabel(i), rs.getObject(i));
              }
              list.add(columns);
          }
          rs.close();
		stmt.close();
		conn.close();
	} catch (SQLException e) {
		e.getMessage();
	} finally {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
	}

	return list;
}
	public List<Map<String, Object>> hddListNew(){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String qry = "";
		
		
		try {
			conn = dataSource.getConnection();

			qry = "select id,hdd from tb_mstr_hdd";	
		PreparedStatement stmt = conn.prepareStatement(qry);
		
		ResultSet rs = stmt.executeQuery();
		ResultSetMetaData  metaData = rs.getMetaData();
          int columnCount = metaData.getColumnCount();
          while (rs.next()) {
              Map<String, Object> columns = new LinkedHashMap<String, Object>();                        
              for (int i = 1; i <= columnCount; i++) {
                  columns.put(metaData.getColumnLabel(i), rs.getObject(i));
              }
              list.add(columns);
          }
          rs.close();
		stmt.close();
		conn.close();
	} catch (SQLException e) {
		e.getMessage();
	} finally {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
	}
	
	return list;
}
	
	
	public List<Map<String, Object>> OperatingsystemListNew(){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String qry = "";
		
		
		try {
			conn = dataSource.getConnection();

			qry = "select id,os from tb_mstr_os";	
		PreparedStatement stmt = conn.prepareStatement(qry);
		
		ResultSet rs = stmt.executeQuery();
		ResultSetMetaData  metaData = rs.getMetaData();
          int columnCount = metaData.getColumnCount();
          while (rs.next()) {
              Map<String, Object> columns = new LinkedHashMap<String, Object>();                        
              for (int i = 1; i <= columnCount; i++) {
                  columns.put(metaData.getColumnLabel(i), rs.getObject(i));
              }
              list.add(columns);
          }
          rs.close();
		stmt.close();
		conn.close();
	} catch (SQLException e) {
		e.getMessage();
	} finally {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
	}

	return list;
}
	
	public List<Map<String, Object>> OfficeListNew(){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String qry = "";
		
		
		try {
			conn = dataSource.getConnection();

			qry = "select id,office from tb_mstr_office";
		PreparedStatement stmt = conn.prepareStatement(qry);
		
		ResultSet rs = stmt.executeQuery();
		ResultSetMetaData  metaData = rs.getMetaData();
          int columnCount = metaData.getColumnCount();
          while (rs.next()) {
              Map<String, Object> columns = new LinkedHashMap<String, Object>();                        
              for (int i = 1; i <= columnCount; i++) {
                  columns.put(metaData.getColumnLabel(i), rs.getObject(i));
              }
              list.add(columns);
          }
          rs.close();
		stmt.close();
		conn.close();
	} catch (SQLException e) {
		e.getMessage();
	} finally {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
	}

	return list;
}
	
	
	public List<Map<String, Object>> AntiVirusListNew(){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String qry = "";
		
		
		try {
			conn = dataSource.getConnection();

			qry = "select id,antivirus from tb_mstr_antivirus";	
		PreparedStatement stmt = conn.prepareStatement(qry);
		
		ResultSet rs = stmt.executeQuery();
		ResultSetMetaData  metaData = rs.getMetaData();
          int columnCount = metaData.getColumnCount();
          while (rs.next()) {
              Map<String, Object> columns = new LinkedHashMap<String, Object>();                        
              for (int i = 1; i <= columnCount; i++) {
                  columns.put(metaData.getColumnLabel(i), rs.getObject(i));
              }
              list.add(columns);
          }
          rs.close();
		stmt.close();
		conn.close();
	} catch (SQLException e) {
		e.getMessage();
	} finally {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
	}

	return list;
}
	public List<Map<String, Object>> OSFirmwareListNew(){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String qry = "";
		
		
		try {
			conn = dataSource.getConnection();

			qry = "select id,os_firmware from tb_mstr_os_firmware";	
		PreparedStatement stmt = conn.prepareStatement(qry);
		
		ResultSet rs = stmt.executeQuery();
		ResultSetMetaData  metaData = rs.getMetaData();
          int columnCount = metaData.getColumnCount();
          while (rs.next()) {
              Map<String, Object> columns = new LinkedHashMap<String, Object>();                        
              for (int i = 1; i <= columnCount; i++) {
                  columns.put(metaData.getColumnLabel(i), rs.getObject(i));
              }
              list.add(columns);
          }
          rs.close();
		stmt.close();
		conn.close();
	} catch (SQLException e) {
		e.getMessage();
	} finally {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
	}

	return list;
}
	
	public List<Map<String, Object>> DplyenvListNew(){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String qry = "";
		
		
		try {
			conn = dataSource.getConnection();

			qry = "select id,dply_env from tb_mstr_dply_env";
		PreparedStatement stmt = conn.prepareStatement(qry);
		
		ResultSet rs = stmt.executeQuery();
		ResultSetMetaData  metaData = rs.getMetaData();
          int columnCount = metaData.getColumnCount();
          while (rs.next()) {
              Map<String, Object> columns = new LinkedHashMap<String, Object>();                        
              for (int i = 1; i <= columnCount; i++) {
                  columns.put(metaData.getColumnLabel(i), rs.getObject(i));
              }
              list.add(columns);
          }
          rs.close();
		stmt.close();
		conn.close();
	} catch (SQLException e) {
		e.getMessage();
	} finally {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
	}

	return list;
}
	
	
	public List<Map<String, Object>> BudgetListNew(){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String qry = "";
		
		
		try {
			conn = dataSource.getConnection();

			qry = "select id,budget_head,budget_code from tb_mstr_budget";	
		PreparedStatement stmt = conn.prepareStatement(qry);
		
		ResultSet rs = stmt.executeQuery();
		ResultSetMetaData  metaData = rs.getMetaData();
          int columnCount = metaData.getColumnCount();
          while (rs.next()) {
              Map<String, Object> columns = new LinkedHashMap<String, Object>();                        
              for (int i = 1; i <= columnCount; i++) {
                  columns.put(metaData.getColumnLabel(i), rs.getObject(i));
              }
              list.add(columns);
          }
          rs.close();
		stmt.close();
		conn.close();
	} catch (SQLException e) {
		e.getMessage();
	} finally {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
	}
		
	return list;
}
	/////start peripheral_type
	public List<Map<String, Object>> peripheral_typeListNew(){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String qry = "";
		
		
		try {
			conn = dataSource.getConnection();

			qry = "select distinct id,assets_name,category from tb_mstr_assets  where category='2'";
			
		PreparedStatement stmt = conn.prepareStatement(qry);
		
		ResultSet rs = stmt.executeQuery();
		ResultSetMetaData  metaData = rs.getMetaData();
          int columnCount = metaData.getColumnCount();
          while (rs.next()) {
              Map<String, Object> columns = new LinkedHashMap<String, Object>();                        
              for (int i = 1; i <= columnCount; i++) {
                  columns.put(metaData.getColumnLabel(i), rs.getObject(i));
              }
              list.add(columns);
          }
          rs.close();
		stmt.close();
		conn.close();
	} catch (SQLException e) {
		e.getMessage();
	} finally {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
	}
		
		
	return list;
}
	public List<Map<String, Object>> hwListNew(){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String qry = "";
		
		
		try {
			conn = dataSource.getConnection();

			qry = "select distinct id,type_of_hw from tb_mstr_type_of_hw";
			
		PreparedStatement stmt = conn.prepareStatement(qry);
		
		ResultSet rs = stmt.executeQuery();
		ResultSetMetaData  metaData = rs.getMetaData();
          int columnCount = metaData.getColumnCount();
          while (rs.next()) {
              Map<String, Object> columns = new LinkedHashMap<String, Object>();                        
              for (int i = 1; i <= columnCount; i++) {
                  columns.put(metaData.getColumnLabel(i), rs.getObject(i));
              }
              list.add(columns);
          }
          rs.close();
		stmt.close();
		conn.close();
	} catch (SQLException e) {
		e.getMessage();
	} finally {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
	}
		
		
	return list;
}
	public List<Map<String, Object>> typeListNew(){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String qry = "";
		
		
		try {
			conn = dataSource.getConnection();

			qry = "select distinct id,type from tb_mstr_type";
			
		PreparedStatement stmt = conn.prepareStatement(qry);
		
		ResultSet rs = stmt.executeQuery();
		ResultSetMetaData  metaData = rs.getMetaData();
          int columnCount = metaData.getColumnCount();
          while (rs.next()) {
              Map<String, Object> columns = new LinkedHashMap<String, Object>();                        
              for (int i = 1; i <= columnCount; i++) {
                  columns.put(metaData.getColumnLabel(i), rs.getObject(i));
              }
              list.add(columns);
          }
          rs.close();
		stmt.close();
		conn.close();
	} catch (SQLException e) {
		e.getMessage();
	} finally {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
	}
		
		
	return list;
}
	public List<Map<String, Object>> ups_capacityNew(){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String qry = "";
		
		
		try {
			conn = dataSource.getConnection();

			qry = "select distinct id,ups_capacity from tb_mstr_ups_capacity";
			
		PreparedStatement stmt = conn.prepareStatement(qry);
		
		ResultSet rs = stmt.executeQuery();
		ResultSetMetaData  metaData = rs.getMetaData();
          int columnCount = metaData.getColumnCount();
          while (rs.next()) {
              Map<String, Object> columns = new LinkedHashMap<String, Object>();                        
              for (int i = 1; i <= columnCount; i++) {
                  columns.put(metaData.getColumnLabel(i), rs.getObject(i));
              }
              list.add(columns);
          }
          rs.close();
		stmt.close();
		conn.close();
	} catch (SQLException e) {
		e.getMessage();
	} finally {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
	}
		
		
	return list;
}
	public List<Map<String, Object>> paper_sizeNew(){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String qry = "";
		
		
		try {
			conn = dataSource.getConnection();

			qry = "select codevalue,label from t_domain_value where domainid='PAPER_SIZE'";
			
		PreparedStatement stmt = conn.prepareStatement(qry);
		
		ResultSet rs = stmt.executeQuery();
		ResultSetMetaData  metaData = rs.getMetaData();
          int columnCount = metaData.getColumnCount();
          while (rs.next()) {
              Map<String, Object> columns = new LinkedHashMap<String, Object>();                        
              for (int i = 1; i <= columnCount; i++) {
                  columns.put(metaData.getColumnLabel(i), rs.getObject(i));
              }
              list.add(columns);
          }
          rs.close();
		stmt.close();
		conn.close();
	} catch (SQLException e) {
		e.getMessage();
	} finally {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
	}
		
		
	return list;
}
	public List<Map<String, Object>> paper_connetivity(){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String qry = "";
		
		
		try {
			conn = dataSource.getConnection();

			qry = "select codevalue,label from t_domain_value where domainid='CONNECTIVITY_TYPE'";
			
		PreparedStatement stmt = conn.prepareStatement(qry);
		
		ResultSet rs = stmt.executeQuery();
		ResultSetMetaData  metaData = rs.getMetaData();
          int columnCount = metaData.getColumnCount();
          while (rs.next()) {
              Map<String, Object> columns = new LinkedHashMap<String, Object>();                        
              for (int i = 1; i <= columnCount; i++) {
                  columns.put(metaData.getColumnLabel(i), rs.getObject(i));
              }
              list.add(columns);
          }
          rs.close();
		stmt.close();
		conn.close();
	} catch (SQLException e) {
		e.getMessage();
	} finally {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
	}
		
		
	return list;
}
	public List<Map<String, Object>> hardware_interfaceNew(){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String qry = "";
		
		
		try {
			conn = dataSource.getConnection();

			qry = "select distinct id,hardware_interface from tb_mstr_hardware_interface";
			
		PreparedStatement stmt = conn.prepareStatement(qry);
		
		ResultSet rs = stmt.executeQuery();
		ResultSetMetaData  metaData = rs.getMetaData();
          int columnCount = metaData.getColumnCount();
          while (rs.next()) {
              Map<String, Object> columns = new LinkedHashMap<String, Object>();                        
              for (int i = 1; i <= columnCount; i++) {
                  columns.put(metaData.getColumnLabel(i), rs.getObject(i));
              }
              list.add(columns);
          }
          rs.close();
		stmt.close();
		conn.close();
	} catch (SQLException e) {
		e.getMessage();
	} finally {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
	}
		
		
	return list;
}
	public List<Map<String, Object>> type_connetivity(){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String qry = "";
		
		
		try {
			conn = dataSource.getConnection();
			
			qry = "select codevalue,label from t_domain_value where domainid='DISPLAY_CONNECTOR'";
			
		PreparedStatement stmt = conn.prepareStatement(qry);
		
		ResultSet rs = stmt.executeQuery();
		ResultSetMetaData  metaData = rs.getMetaData();
          int columnCount = metaData.getColumnCount();
          while (rs.next()) {
              Map<String, Object> columns = new LinkedHashMap<String, Object>();                        
              for (int i = 1; i <= columnCount; i++) {
                  columns.put(metaData.getColumnLabel(i), rs.getObject(i));
              }
              list.add(columns);
          }
          rs.close();
		stmt.close();
		conn.close();
	} catch (SQLException e) {
		e.getMessage();
	} finally {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
	}
		
		
	return list;
}
	public List<Map<String, Object>> getnetwork_features(){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String qry = "";
		
		
		try {
			conn = dataSource.getConnection();
			
			qry = "select id,network_features from tb_mstr_network_features";
			
		PreparedStatement stmt = conn.prepareStatement(qry);
		
		ResultSet rs = stmt.executeQuery();
		ResultSetMetaData  metaData = rs.getMetaData();
          int columnCount = metaData.getColumnCount();
          while (rs.next()) {
              Map<String, Object> columns = new LinkedHashMap<String, Object>();                        
              for (int i = 1; i <= columnCount; i++) {
                  columns.put(metaData.getColumnLabel(i), rs.getObject(i));
              }
              list.add(columns);
          }
          rs.close();
		stmt.close();
		conn.close();
	} catch (SQLException e) {
		e.getMessage();
	} finally {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
	}
		
		
	return list;
}
	public List<Map<String, Object>> getethernet_portnew(){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String qry = "";
		
		
		try {
			conn = dataSource.getConnection();
			
			qry = "select id,ethernet_port from  tb_mstr_ethernet_port";
			
		PreparedStatement stmt = conn.prepareStatement(qry);
		
		ResultSet rs = stmt.executeQuery();
		ResultSetMetaData  metaData = rs.getMetaData();
          int columnCount = metaData.getColumnCount();
          while (rs.next()) {
              Map<String, Object> columns = new LinkedHashMap<String, Object>();                        
              for (int i = 1; i <= columnCount; i++) {
                  columns.put(metaData.getColumnLabel(i), rs.getObject(i));
              }
              list.add(columns);
          }
          rs.close();
		stmt.close();
		conn.close();
	} catch (SQLException e) {
		e.getMessage();
	} finally {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
	}
		
		
	return list;
}
	public List<Map<String, Object>> getmanagement_layernew(){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String qry = "";
		
		
		try {
			conn = dataSource.getConnection();
			
			qry = "select id,management_layer from  tb_mstr_management_layer";
			
		PreparedStatement stmt = conn.prepareStatement(qry);
		
		ResultSet rs = stmt.executeQuery();
		ResultSetMetaData  metaData = rs.getMetaData();
          int columnCount = metaData.getColumnCount();
          while (rs.next()) {
              Map<String, Object> columns = new LinkedHashMap<String, Object>();                        
              for (int i = 1; i <= columnCount; i++) {
                  columns.put(metaData.getColumnLabel(i), rs.getObject(i));
              }
              list.add(columns);
          }
          rs.close();
		stmt.close();
		conn.close();
	} catch (SQLException e) {
		e.getMessage();
	} finally {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
			}
		}
	}
		
		
	return list;
}
}