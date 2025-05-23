package com.dao.Assets;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.Session;

import com.models.itasset.assets.Assets_Main;
import com.persistance.util.HibernateUtil;

public class interUnitTransf_DAOImpl implements interUnitTransf_DAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<Map<String, Object>> GetMachine_noDataComp(String machine_no)
	{
	
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();			
	Connection conn = null;
	String q="";
	
	try{	
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			q="select am.id,ma.assets_name,am.s_state,am.model_number,am.machine_number,am.mac_address,am.ip_address,pm.processor_type,rm.ram,hm.hdd,om.os,ofm.office,fm.os_firmware,dem.dply_env from tb_asset_main am\r\n" + 
					"left join  tb_mstr_assets ma on  ma.id=am.asset_type and ma.category=1\r\n" + 
					"left join  tb_mstr_processor_type pm on  pm.id=am.processor_type\r\n" + 
					"left join  tb_mstr_ram rm on  rm.id=am.ram_capi\r\n" + 
					"left join  tb_mstr_hdd hm on  hm.id=am.hdd_capi\r\n" + 
					"left join  tb_mstr_os om on  om.id=am.operating_system\r\n" + 
					"left join  tb_mstr_office ofm on  ofm.id=am.office\r\n" + 
					"left join  tb_mstr_os_firmware fm on  fm.id=am.os_patch\r\n"+
//					"inner join  tb_mstr_make mm on  mm.id=am.make_name\r\n"+ 
					"left join  tb_mstr_dply_env dem on  dem.id=am.dply_envt  "
					+ "inner join tb_orbat_unt_dtl orb on orb.sus_no = am.sus_no and status_sus_no='Active' where am.machine_number = ?";
			stmt=conn.prepareStatement(q);
			stmt.setString(1, machine_no);
	
	      ResultSet rs = stmt.executeQuery();   

	      ResultSetMetaData metaData = rs.getMetaData();
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
	   }catch (SQLException e) {
			e.printStackTrace();
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
	
	public List<Map<String, Object>> GetMachine_noDataPerif(String machine_no)
	{
	
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();			
	Connection conn = null;
	String q="";
	
	try{	
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			q="select ap.id,pt.assets_name,ap.s_state,th.type_of_hw,ap.year_of_proc,\r\n"
					+ "ap.year_of_manufacturing,ap.b_cost,ap.make_name,ap.machine_no,ap.model_no,ap.remarks from it_asset_peripherals ap\r\n"
					+ "left join tb_mstr_assets pt on pt.id=ap.assets_type  and pt.category=2\r\n"
					+ "left join tb_mstr_type_of_hw th on th.id=ap.type_of_hw\r\n"
					+ "inner join tb_orbat_unt_dtl orb on orb.sus_no = ap.sus_no and status_sus_no='Active' where ap.machine_no = ?";
			stmt=conn.prepareStatement(q);
			stmt.setString(1, machine_no);
	
	      ResultSet rs = stmt.executeQuery();   

	      ResultSetMetaData metaData = rs.getMetaData();
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
	   }catch (SQLException e) {
			e.printStackTrace();
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
	
	public List<Map<String, Object>> GetAssetTransfDataComp(String roleType,String from_sus_no, String to_sus_no,String machine_no, String Status)
	{
	
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();			
	Connection conn = null;
	String q="";
	String qry="";
	
	try{	
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			if (Status !=null && !Status.equals("")) {
				qry+= " and status=?";
			}
			if (from_sus_no !=null && !from_sus_no.equals("")) {
				qry+= " and from_sus=?";
			}
			if (to_sus_no !=null && !to_sus_no.equals("")) {
				qry+= " and to_sus=?";
			}
			if (machine_no !=null && !machine_no.equals("")) {
				qry+= " and machine_no=?";
			}
			
			q="SELECT * FROM it_asset_inter_unit_trnsf_comp where id!=0 "+ qry +"\r\n"
					+ " ORDER BY id ASC ";
			stmt=conn.prepareStatement(q);
			
			int j=0;
			if (Status !=null && !Status.equals("")) {
				j++;
				stmt.setInt(j, Integer.parseInt(Status));
			}
			if (from_sus_no !=null && !from_sus_no.equals("")) {
				j++;
				stmt.setString(j, (from_sus_no));
			}
			if (to_sus_no !=null && !to_sus_no.equals("")) {
				j++;
				stmt.setString(j, (to_sus_no));
			}
			if (machine_no !=null && !machine_no.equals("")) {
				j++;
				stmt.setString(j, (machine_no));
			}
	
	      ResultSet rs = stmt.executeQuery();   

	      ResultSetMetaData metaData = rs.getMetaData();
	      int columnCount = metaData.getColumnCount();
	      
	      while (rs.next()) {
	    	  Map<String, Object> columns = new LinkedHashMap<String, Object>();
	    	  for (int i = 1; i <= columnCount; i++) {
	    		  columns.put(metaData.getColumnLabel(i), rs.getObject(i));
	    	  }

				String f1 = "";



				if (roleType.equals("APP") && Status.equals("0"))

				{



					String Approved = "onclick=\"  if (confirm('Are You Sure you want to Approve ?') ){Approved("

							+ rs.getString("id") + " ,'" + rs.getString("machine_id") + "' ,'"+ rs.getString("to_sus") + "'  )}else{ return false;}\"";


					f1 = "<i class='action_icons action_approve' " + Approved + " title='Approve Data' ></i>";

					String reject = "onclick=\"  if (confirm('Are You Sure you want to Reject ?') ){Reject( "

							+ rs.getString("id") + "  )}else{ return false;}\"";

					f1 += "<i class='action_icons action_reject' " + reject + " title='Reject Data' ></i>";

					/*String reject = "onclick=\"  if (confirm('Are You Sure you want to Reject this Letter  ?') ){addRemarkModel('Reject', '"+rs.getString("id")+"'"
							+ ",'" + rs.getString("comm_id") + "','" + rs.getString("unit_sus_no") + "' )}else{ return false;}\"";					
					f1 = "<i class='action_icons action_reject' " + reject + " title='Reject Data'></i>";*/

				}
				columns.put("action", f1);
	    	  
	    	  list.add(columns);
 	        }
	      
	      rs.close();
	      stmt.close();
	      conn.close();
	   }catch (SQLException e) {
			e.printStackTrace();
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
	
	
	public List<Map<String, Object>> GetAssetTransfDataPerif(String roleType, String from_sus_no,String to_sus_no,String machine_no, String Status)
	{
	
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();			
	Connection conn = null;
	String q="";
	String qry="";
	
	try{	
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			if (Status !=null && !Status.equals("")) {
				qry+= " and status=?";
			}
			if (from_sus_no !=null && !from_sus_no.equals("")) {
				qry+= " and from_sus=?";
			}
			if (to_sus_no !=null && !to_sus_no.equals("")) {
				qry+= " and to_sus=?";
			}
			if (machine_no !=null && !machine_no.equals("")) {
				qry+= " and machine_no=?";
			}
			
			q="SELECT * FROM it_asset_inter_unit_trnsf_perif where id!=0"+ qry +"\r\n"
					+ " ORDER BY id ASC ";
			stmt=conn.prepareStatement(q);
			
			int j=0;
			if (Status !=null && !Status.equals("")) {
				j++;
				stmt.setInt(1, Integer.parseInt(Status));
			}
			if (from_sus_no !=null && !from_sus_no.equals("")) {
				j++;
				stmt.setString(j, (from_sus_no));
			}
			if (to_sus_no !=null && !to_sus_no.equals("")) {
				j++;
				stmt.setString(j, (to_sus_no));
			}
			if (machine_no !=null && !machine_no.equals("")) {
				j++;
				stmt.setString(j, (machine_no));
			}
	
	      ResultSet rs = stmt.executeQuery();   

	      ResultSetMetaData metaData = rs.getMetaData();
	      int columnCount = metaData.getColumnCount();
	      
	      while (rs.next()) {
	    	  Map<String, Object> columns = new LinkedHashMap<String, Object>();
	    	  for (int i = 1; i <= columnCount; i++) {
	    		  columns.put(metaData.getColumnLabel(i), rs.getObject(i));
	    	  }

				String f1 = "";



				if (roleType.equals("APP") && Status.equals("0"))



				{



					String Approved = "onclick=\"  if (confirm('Are You Sure you want to Approve ?') ){Approved("

							+ rs.getString("id") + " ,'" + rs.getString("machine_id") + "' ,'"+ rs.getString("to_sus") + "' )}else{ return false;}\"";



					f1 = "<i class='action_icons action_approve' " + Approved + " title='Approve Data' ></i>";

					String reject = "onclick=\"  if (confirm('Are You Sure you want to Reject ?') ){Reject( "

							+ rs.getString("id") + "  )}else{ return false;}\"";

					f1 += "<i class='action_icons action_reject' " + reject + " title='Reject Data' ></i>";

					/*String reject = "onclick=\"  if (confirm('Are You Sure you want to Reject this Letter  ?') ){addRemarkModel('Reject', '"+rs.getString("id")+"'"
							+ ",'" + rs.getString("comm_id") + "','" + rs.getString("unit_sus_no") + "' )}else{ return false;}\"";					
					f1 = "<i class='action_icons action_reject' " + reject + " title='Reject Data'></i>";*/

				}
				columns.put("action", f1);
	    	  
	    	  list.add(columns);
 	        }
	      
	      rs.close();
	      stmt.close();
	      conn.close();
	   }catch (SQLException e) {
			e.printStackTrace();
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
	
	
	public List<Map<String, Object>> getmachine_no_CompListToTransf(String sus_no)
	{
	
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();			
	Connection conn = null;
	String q="";
	
	try{	
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			q=" select distinct p.id, p.machine_number from tb_asset_main p  \r\n"
					+ "	 where  sus_no=? and status ='1' \r\n"
					+ "	order by p.machine_number";
			stmt=conn.prepareStatement(q);
			stmt.setString(1, sus_no);
	
	      ResultSet rs = stmt.executeQuery();   

	      ResultSetMetaData metaData = rs.getMetaData();
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
	   }catch (SQLException e) {
			e.printStackTrace();
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
	
	
	
	public List<Map<String, Object>> getmachine_no_perifListToTransf(String sus_no)
	{
	
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();			
	Connection conn = null;
	String q="";
	
	try{	
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			q="select distinct p.id, p.machine_no as machine_number from it_asset_peripherals p \r\n"
					+ "	where sus_no =? and status = '1' \r\n"
					+ "	order by p.machine_no";
			stmt=conn.prepareStatement(q);
			stmt.setString(1, sus_no);
	
	      ResultSet rs = stmt.executeQuery();   

	      ResultSetMetaData metaData = rs.getMetaData();
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
	   }catch (SQLException e) {
			e.printStackTrace();
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