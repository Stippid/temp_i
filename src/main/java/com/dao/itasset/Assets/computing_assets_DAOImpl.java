package com.dao.itasset.Assets;
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

public class computing_assets_DAOImpl implements computing_assets_DAO{
	
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	
	
	//BISAG
	public List<Map<String, Object>> Search_Computing_Assets(int startPage,int pageLength,String Search,String orderColunm,String orderType,String status,
			String assets_type,String model_number,String machine_number,String ram_capi,String hdd_capi,String operating_system,String from_date,String to_date,
			String s_state,String unit_sus_no, String unit_name, String section ,HttpSession session)
	{
	
    	String SearchValue = GenerateQueryWhereClause_SQL4(Search,assets_type,model_number,machine_number,ram_capi,hdd_capi,operating_system,from_date,to_date,status,
    			s_state,unit_sus_no,  unit_name,section,session);
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();			
	Connection conn = null;
	String q="";
	
	try{	
		String pageL = "";
        
        if(pageLength == -1){
			pageL = "ALL";
		}else {
			pageL = String.valueOf(pageLength);
		}
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			q="select am.id,ma.assets_name,am.s_state,am.model_number,am.machine_number,am.mac_address,am.ip_address,pm.processor_type,rm.ram,hm.hdd,om.os,ofm.office,fm.os_firmware,dem.dply_env ,sec.section ,am.created_by from tb_asset_main am\r\n" + 
					"inner join  tb_mstr_section sec  on sec.id:: text = am.section \r\n"+
					"left join  tb_mstr_assets ma on  ma.id=am.asset_type and ma.category=1\r\n" + 
					"left join  tb_mstr_processor_type pm on  pm.id=am.processor_type\r\n" + 
					"left join  tb_mstr_ram rm on  rm.id=am.ram_capi\r\n" + 
					"left join  tb_mstr_hdd hm on  hm.id=am.hdd_capi\r\n" + 
					"left join  tb_mstr_os om on  om.id=am.operating_system\r\n" + 
					"left join  tb_mstr_office ofm on  ofm.id=am.office\r\n" + 
					"left join  tb_mstr_os_firmware fm on  fm.id=am.os_patch\r\n"+
				
//					"inner join  tb_mstr_make mm on  mm.id=am.make_name\r\n"+ 
					"left join  tb_mstr_dply_env dem on  dem.id=am.dply_envt  "
					+ "left join tb_orbat_unt_dtl orb on orb.sus_no = am.sus_no and status_sus_no='Active' where am.id!=0 "+SearchValue+
					" ORDER BY "+orderColunm+" "+orderType +" limit " +pageL+" OFFSET "+startPage ;
			stmt=conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQL4(stmt,Search,assets_type,model_number,machine_number,ram_capi,hdd_capi,operating_system,from_date,to_date,status,s_state,unit_sus_no,section,  unit_name,session);

	      ResultSet rs = stmt.executeQuery();   

	      ResultSetMetaData metaData = rs.getMetaData();
	      int columnCount = metaData.getColumnCount();
	      
	      while (rs.next()) {
	    	  Map<String, Object> columns = new LinkedHashMap<String, Object>();
	    	  for (int i = 1; i <= columnCount; i++) {
	    		  columns.put(metaData.getColumnLabel(i), rs.getObject(i));
	    		  
	    		 
	    	  }
	    	  String Checkbox="<input class='nrCheckBox' type='checkbox' id='" + rs.getObject(1)//13
				+ "' name='cbox' onchange='checkbox_count(this," + rs.getObject(1) + ");' />";
  		  
  		  String CheckboxId="<input  type='hidden' id='id" + rs.getObject(1) + "' name='id" + rs.getObject(1)//14
				+ "' value='" + rs.getObject(1) + "'   />";
  		  
  		  String chekboxaction="";
  		  
  		chekboxaction+=Checkbox;
	      chekboxaction+=CheckboxId;
	      
	  	columns.put("chekboxaction", chekboxaction);
  		  
  		  
  		  
				
				String f1 = "";				
				String f2 = "";
				String f3 = "";
				String f4 = "";
				
				
			
					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update Details ?') ){editData("
							+ rs.getInt("id") + ")}else{ return false;}\"";
					f1 = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";

					String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Information ?') ){deleteData("
							+ rs.getInt("id") + ")}else{ return false;}\"";
					f2 = "<i class='action_icons action_delete' " + Delete1 + " title='Delete Data'></i>";
					
					
					String Archive = "onclick=\"  if (confirm('Are You Sure You Want to Archive This Information ?') ){ArchiveData("
							+ rs.getInt("id") + ")}else{ return false;}\"";
					f4 = "<i class='action_icons action_archive' " + Archive + " title='Archive Data'></i>";
					
					
					

					String View = "onclick=\"  if (confirm('Are You Sure You Want to View This Information ?') ){viewData("
												+ rs.getInt("id") + ")}else{ return false;}\"";
										
										f3 = "<i class='fa fa-eye' " + View + " title='View Data'></i>";
	
				
			String action="";
										
			 if (!status.equals("1")) {
				 columns.put("action",f1);//15
				 columns.put("action",f2);//16
				 action+=f2;
				 action+=f1;
				
				}
				

			 
			 columns.put("action",f3);//17
			 action+=f3;
			 columns.put("action",f4);
			 action+=f4;
			 columns.put("action", action);
			
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
	
	
	 public long getAppComputingassetCountList1(String Search,String orderColunm,String orderType,String status,String assets_type,String model_number,
				String machine_number,String ram_capi,String hdd_capi,String operating_system,String from_date,String to_date,String s_state,String unit_sus_no, String unit_name,  String section ,HttpSession sessionUserId)throws SQLException {
	    	String SearchValue = GenerateQueryWhereClause_SQL4(Search,assets_type,model_number,machine_number,ram_capi,hdd_capi,operating_system,from_date,to_date,status,s_state, unit_sus_no,  unit_name,section,sessionUserId);
			int total = 0;
			String q = null;
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				q ="select \r\n" + 
						" count(app.*)\r\n" + 
						" from  ( select am.id,ma.assets_name,am.s_state,am.model_number,am.machine_number,am.mac_address,am.ip_address,pm.processor_type,rm.ram,hm.hdd,om.os,ofm.office,fm.os_firmware,dem.dply_env ,sec.section ,am.created_by from tb_asset_main am \r\n" + 
						"inner join  tb_mstr_section sec  on sec.id:: text = am.section \r\n"+
						"					left join  tb_mstr_assets ma on  ma.id=am.asset_type and ma.category=1 \r\n" + 
						"					left join  tb_mstr_processor_type pm on  pm.id=am.processor_type \r\n" + 
						"					left join  tb_mstr_ram rm on  rm.id=am.ram_capi \r\n" + 
						"					left join  tb_mstr_hdd hm on  hm.id=am.hdd_capi \r\n" + 
						"					left join  tb_mstr_os om on  om.id=am.operating_system \r\n" + 
						"					left join  tb_mstr_office ofm on  ofm.id=am.office\r\n" + 
						"					left join  tb_mstr_os_firmware fm on  fm.id=am.os_patch \r\n" + 
//						"					inner join  tb_mstr_make mm on  mm.id=am.make_name\r\n"+ 
						"					left join  tb_mstr_dply_env dem on  dem.id=am.dply_envt  "
						+ " left join tb_orbat_unt_dtl orb on orb.sus_no = am.sus_no and status_sus_no='Active' "
						+ "where am.id!=0  "  + SearchValue +" ) app " ;

				PreparedStatement stmt = conn.prepareStatement(q);
				
				stmt = setQueryWhereClause_SQL4(stmt,Search,assets_type,model_number,machine_number,ram_capi,hdd_capi,operating_system,from_date,to_date,status,s_state,unit_sus_no,  unit_name,section,sessionUserId);
				
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {  
					total = rs.getInt(1);
				}
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
					}
				}
			}
			return (long) total;
		}
	
	
	 
	 //BISAG
	 public String GenerateQueryWhereClause_SQL4(String Search,String assets_type,String model_number,String machine_number,String ram_capi,String hdd_capi,String operating_system,String from_date,String to_date, String status,String s_state,String unit_sus_no, String unit_name,String section,HttpSession sessionUserId) {
			String SearchValue ="";
			
			if (!Search.equals("") &&  Search != null  &&  !Search.equals(null)  && Search != " ") { // for Input Filter
				SearchValue = " and ( ";
				SearchValue += "cast(ma.assets_name as character varying) like ? or upper(am.model_number)  like ? or upper(am.machine_number)  like ? or upper(am.mac_address)  like ? or upper(am.ip_address)  like ? or upper(pm.processor_type)  like ?"
						+ " or upper(ofm.office)  like ? or upper(am.created_by ) like ? or upper(sec.section) like ? or upper(dem.dply_env)  like ?)";
						
				
			}
	      
			
			
			if(!status.equals(""))
			{
				SearchValue += "  and cast(am.status as character varying) = ? ";
			}
			
			if(!assets_type.equals("0")) {
				SearchValue += "  and cast(am.asset_type as character varying) = ? ";
			}	
			if(!model_number.equals("")) {
				SearchValue += "  and upper(am.model_number) like ?";
			}
			if(!machine_number.equals("")) {
				SearchValue += "  and upper(am.machine_number) like ?";
			}
			if(!ram_capi.equals("0")) {
				SearchValue += "  and cast(am.ram_capi as character varying) = ?";
			}
			if(!hdd_capi.equals("0")) {
				SearchValue += "  and cast(am.hdd_capi as character varying) = ?";
			}
			if(!operating_system.equals("0")) {
				SearchValue += "  and cast(am.operating_system as character varying) = ?";
			}
			if(!from_date.equals("") && !to_date.equals(""))
			{
				SearchValue +=" and am.created_date BETWEEN to_date(?,'DD/MM/YYYY') and  to_date(?,'DD/MM/YYYY')";
			}
			if(!s_state.equals("0")) {
				SearchValue += "  and cast(am.s_state as character varying) = ? ";
			}
			String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
			if (roleAccess.equals("Unit") ) {
				SearchValue += "  and am.sus_no = ? ";
			}
			if( !unit_sus_no.equals("")) {
				SearchValue += " and  orb.sus_no = ?";
				
			}
//			if( !unit_name.equals("")) {
//				SearchValue += " and  orb.unit_name = ? ";
//				
//			}
			if(!section.equals("") &&!section.equals("0"))
			{
				SearchValue += " and  am.section= ? ";
				
			}
			return SearchValue;
		}
	 public PreparedStatement setQueryWhereClause_SQL4(PreparedStatement stmt,String Search,String assets_type,String model_number,String machine_number,String ram_capi,String hdd_capi,String operating_system,String from_date,String to_date,
			 String status,String s_state,String unit_sus_no, String unit_name,String section,HttpSession sessionUserId) {
			
			int flag = 0;
			try {
				if (!Search.equals("") &&  Search != null  &&  !Search.equals(null)  && Search != " ") {
					flag += 1;
					stmt.setString(flag, "%" + Search.toUpperCase() + "%");
					flag += 1;
					stmt.setString(flag, "%" + Search.toUpperCase() + "%");
					flag += 1;
					stmt.setString(flag, "%" + Search.toUpperCase() + "%");
					flag += 1;
					stmt.setString(flag, "%" + Search.toUpperCase() + "%");
					flag += 1;
					stmt.setString(flag, "%" + Search.toUpperCase() + "%");
					flag += 1;
					stmt.setString(flag, "%" + Search.toUpperCase() + "%");		
					flag += 1;
					stmt.setString(flag, "%" + Search.toUpperCase() + "%");	
					flag += 1;
					stmt.setString(flag, "%" + Search.toUpperCase() + "%");	
					flag += 1;
					stmt.setString(flag, "%" + Search.toUpperCase() + "%");	
					flag += 1;
					stmt.setString(flag, "%" + Search.toUpperCase() + "%");
//					flag += 1;
//					stmt.setString(flag, "%" + Search.toUpperCase() + "%");
//					flag += 1;
//					stmt.setString(flag, "%" + Search.toUpperCase() + "%");
					
				}

				
					if (!status.equals("")) {
						flag += 1;
						stmt.setString(flag, status);
					}
					if (!assets_type.equals("0")) {
						flag += 1;
						stmt.setString(flag, assets_type);
					}
					if (!model_number.equals("")) {
						flag += 1;
						stmt.setString(flag, model_number.toUpperCase() +"%");
						
					}
					if (!machine_number.equals("")) {
						flag += 1;
						stmt.setString(flag, machine_number.toUpperCase() +"%");
					}
					if (!ram_capi.equals("0")) {
						flag += 1;
						stmt.setString(flag, ram_capi);
					}  
					if (!hdd_capi.equals("0")) {
						flag += 1;
						stmt.setString(flag, hdd_capi);   
						
					}
					if (!operating_system.equals("0")) {
						flag += 1;
						stmt.setString(flag, operating_system);   
						
					}
					if (!from_date.equals("") && !to_date.equals("")) {
						flag += 1;
						stmt.setString(flag, from_date);
						flag += 1;
						stmt.setString(flag, to_date);
						
					}

					if (!s_state.equals("0")) {
						flag += 1;
						stmt.setString(flag, s_state);   
						
					}
					String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
					String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
					if (roleAccess.equals("Unit") ) {
						flag += 1;
						stmt.setString(flag, roleSusNo);   
					}
				
					
					if(!unit_sus_no.equals("")) {
						flag += 1;
						
						stmt.setString(flag, unit_sus_no.toUpperCase());
						
					}
					
					
//					if(!unit_name.equals("")) {
//						flag += 1;
//						stmt.setString(flag, unit_name.toUpperCase());
//					}
					if(!section.equals("") &&!section.equals("0"))
					{
						flag += 1;
						stmt.setString(flag, section);
						
					}
					
			}catch (Exception e) {
				e.printStackTrace();
			}
			return stmt;
		}
	
	
	public String approve_AssetsData(String a,String user_sus,String status,String username) {
		String[] id_list = a.split(":");
		///BISAG V2 240822///
		String event_status= status;
		Connection conn = null;
		Integer out = 0;
		String q = "";
	

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
	
			
				for (int i = 0; i < id_list.length; i++) {
					int id = Integer.parseInt(id_list[i]);
	
					 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
					Assets_Main assetupd =(Assets_Main)sessionHQL.get(Assets_Main.class, id);
//					stmt = conn.prepareStatement("select assets_name from tb_mstr_make where id=? and status='1'");
					
					stmt = conn.prepareStatement("update tb_asset_main set status=? where id=?");
					
					
					if(event_status == "1" || event_status.equals("1"))
					{
						
						if(assetupd.getS_state() == "1" || assetupd.getS_state().equals("1"))
						{
							status="1";
						}
						
						if(assetupd.getS_state() == "2" || assetupd.getS_state().equals("2"))
						{
							if(assetupd.getUnserviceable_state() == 1)
							{
								status="4";
							}
							if(assetupd.getUnserviceable_state() == 3 || assetupd.getUnserviceable_state() == 2)
							{
								status="1";
							}
						}
					
					}
					if(event_status == "3" || event_status.equals("3"))
					{
						status="3";
					}
					///BISAG V2 240822 end///
					stmt.setInt(1, Integer.parseInt(status));
					stmt.setInt(2, id);
					out = stmt.executeUpdate();
				
					PreparedStatement stmt2=null;
					
					stmt2 = conn.prepareStatement("update tb_assets_child set status=? where p_id=?");
					stmt2.setInt(1, Integer.parseInt(status));
					stmt2.setInt(2, id);
					out = stmt2.executeUpdate();
					
					PreparedStatement stmt1=null;
					
					q="select id,warranty,unserviceable_state,s_state  from tb_asset_main  \r\n" + 
							" where id =? ";
					stmt1 = conn.prepareStatement(q);
					stmt1.setInt(1, id);
					ResultSet rs = stmt1.executeQuery();   
					
					rs.next();
					
					}

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}

		if (out > 0) {
			if(status.equals("1")) {
			return "Approved Successfully";
			}
			else if(status.equals("3")) {
				return "Rejected Successfully";
				}
			else
				return "UnSuccessfully";
		} else {
			if(status.equals("1")) {
				return "Approved not Successfully";
				}
			else if(status.equals("3")) {
				return "Rejected not Successfully";
				}
			else
			return "UnSuccessfully";
		}
	}
	
	
	
	// ** BISAG AHM**//
	
	public List<Map<String, Object>> getAppComputingassetList(int startPage,int pageLength,String Search,String orderColunm,String orderType,String status,String assets_type,
			String model_number,String machine_number,String ram_capi,String hdd_capi,String operating_system,String unit_sus_no,String from_date,String to_date,String section ,HttpSession sessionUserId) throws SQLException
    {                
		String roleType = sessionUserId.getAttribute("roleType").toString();
    	
    	String SearchValue = GenerateQueryWhereClause_SQL(Search,assets_type,model_number,machine_number,ram_capi,hdd_capi,operating_system,unit_sus_no,from_date,to_date,status,section,sessionUserId);
    	String SearchValue1 = GenerateQueryWhereClause_SQL1(Search,status);
            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            Connection conn = null;
            try{          
                    conn = dataSource.getConnection();     
                    PreparedStatement stmt =null;
                    String qry="";                
                
                    String pageL = "";
                    
                    if(pageLength == -1){
            			pageL = "ALL";
            		}else {
            			pageL = String.valueOf(pageLength);
            		}
                
                    
                    if(status.equals("0")) {
                   	 qry="select distinct am.id,ma.assets_name,am.model_number,am.machine_number,am.mac_address,am.ip_address,pm.processor_type,rm.ram,hm.hdd,om.os,ofm.office,fm.os_firmware,dem.dply_env,\r\n" + 
                   	 		" coalesce(ltrim(TO_CHAR( am.warranty  ,'DD-MON-YYYY'),'0'),'')   as warranty,\r\n" + 
                   	 		" td1.label   as service_state,\r\n" + 
                   	 		" td3.label   as unservice_state  ,\r\n" + 
                   	 		" td1.codevalue  as ser,ch_am.unsv_from_dt,ch_am.unsv_to_dt,case when ch_am.status=0 then ch_am.id else 0 end as ch_id, \r\n" + 
                   	 		" td3.codevalue   as unsv,sec.section  from tb_asset_main am \r\n" + 
                   	 		"inner join tb_assets_child ch_am on am.id = ch_am.p_id  \r\n" + 
                   	 		"inner join tb_mstr_section sec on sec.id::text= am.section   \r\n" + 
                   	 		"left join  tb_mstr_assets ma on  ma.id=am.asset_type and ma.category=1\r\n" + 
                   	 		"left join  tb_mstr_processor_type pm on  pm.id=am.processor_type\r\n" + 
                   	 		"left join  tb_mstr_ram rm on  rm.id=am.ram_capi\r\n" + 
                   	 		"left join  tb_mstr_hdd hm on  hm.id=am.hdd_capi\r\n" + 
                   	 		"left join  tb_mstr_os om on  om.id=am.operating_system\r\n" + 
                   	 		"left join  tb_mstr_office ofm on  ofm.id=am.office\r\n" + 
                   	 		"left join  tb_mstr_os_firmware fm on  fm.id=am.os_patch\r\n" + 
                   	 		"left join  tb_mstr_dply_env dem on  dem.id=am.dply_envt \r\n" + 
                   	 		"left join t_domain_value td on  td.codevalue:: character varying = am.s_state:: character varying and td.domainid='SERVICE_CAT'\r\n" + 
                   	 		"left join t_domain_value td1 on  td1.codevalue:: character varying = ch_am.service_state:: character varying and td1.domainid='SERVICE_CAT'  \r\n" + 
                   	 		"left join t_domain_value td2 on  td2.codevalue:: character varying = am.unserviceable_state:: character varying and td2.domainid='UNSERVICEABLE_STATE' \r\n" + 
                   	 		"left join t_domain_value td3 on  td3.codevalue:: character varying = ch_am.unservice_state:: character varying and td3.domainid='UNSERVICEABLE_STATE' \r\n" + 
                   	 		"where am.id!=0 and am.status='1' and ch_am.status = (case when (select count(*) from tb_assets_child ch where ch.p_id=am.id)=1 then 1 else 0 end) " + SearchValue+
                                "group by  am.id,ma.assets_name,am.model_number,am.machine_number,am.mac_address,am.ip_address,pm.processor_type,rm.ram,hm.hdd,om.os,ofm.office,fm.os_firmware,dem.dply_env,\r\n" + 
                                "ch_am.warranty,ch_am.service_state,ch_am.unsv_from_dt,td1.label,td3.label,td1.codevalue,td3.codevalue,ch_am.id ,sec.section "+
                                " ORDER BY "+orderColunm+" "+orderType +" limit " +pageL+" OFFSET "+startPage ;
                    stmt=conn.prepareStatement(qry);
        			
      				 
        			stmt = setQueryWhereClause_SQL(stmt,Search,assets_type,model_number,machine_number,ram_capi,hdd_capi,operating_system,unit_sus_no,from_date,to_date,status,section,sessionUserId);
        			
                   }
                   else {
                   	 qry="select distinct am.id,ma.assets_name,am.model_number,am.machine_number,am.mac_address,am.ip_address,pm.processor_type,rm.ram,hm.hdd,om.os,ofm.office,fm.os_firmware,dem.dply_env,\r\n" + 
                                " coalesce(ltrim(TO_CHAR( am.warranty  ,'DD-MON-YYYY'),'0'),'')   as warranty,\r\n" + 
                                " td1.label   as service_state,\r\n" + 
                                " td3.label   as unservice_state  ,\r\n" + 
                                " td1.codevalue  as ser,ch_am.unsv_from_dt,ch_am.unsv_to_dt, \r\n" + 
                                " td3.codevalue   as unsv,ch_am.id as ch_id ,sec.section " + 
                                " from tb_asset_main am \r\n" + 
                                "inner join tb_assets_child ch_am on am.id = ch_am.p_id \r\n" +
                        		"inner join tb_mstr_section sec on sec.id :: text = am.section   \r\n" + 
                                "                                        left join  tb_mstr_assets ma on  ma.id=am.asset_type and ma.category=1\r\n" + 
                                "                                        left join  tb_mstr_processor_type pm on  pm.id=am.processor_type\r\n" + 
                                "                                        left join  tb_mstr_ram rm on  rm.id=am.ram_capi\r\n" + 
                                "                                        left join  tb_mstr_hdd hm on  hm.id=am.hdd_capi\r\n" + 
                                "                                        left join  tb_mstr_os om on  om.id=am.operating_system\r\n" + 
                                "                                        left join  tb_mstr_office ofm on  ofm.id=am.office\r\n" + 
                                "                                        left join  tb_mstr_os_firmware fm on  fm.id=am.os_patch\r\n" + 
                                "                                        left join  tb_mstr_dply_env dem on  dem.id=am.dply_envt \r\n" + 
                                "                                        left join t_domain_value td on  td.codevalue:: character varying = am.s_state:: character varying and td.domainid='SERVICE_CAT'\r\n" + 
                                "                                        left join t_domain_value td1 on  td1.codevalue:: character varying = ch_am.service_state:: character varying and td1.domainid='SERVICE_CAT'  \r\n" + 
                                "                                        left join t_domain_value td2 on  td2.codevalue:: character varying = am.unserviceable_state:: character varying and td2.domainid='UNSERVICEABLE_STATE' \r\n" + 
                                "                                        left join t_domain_value td3 on  td3.codevalue:: character varying = ch_am.unservice_state:: character varying and td3.domainid='UNSERVICEABLE_STATE' \r\n" + 
                                "                                        where am.id!=0 and am.status='1' \r\n" +SearchValue1+" " +SearchValue+
                                "group by  am.id,ma.assets_name,am.model_number,am.machine_number,am.mac_address,am.ip_address,pm.processor_type,rm.ram,hm.hdd,om.os,ofm.office,fm.os_firmware,dem.dply_env,\r\n" + 
                                "ch_am.warranty,ch_am.id,ch_am.service_state,ch_am.unsv_from_dt,td1.label,td3.label,td1.codevalue,td3.codevalue,sec.section "+
                                " ORDER BY "+orderColunm+" "+orderType +" limit " +pageL+" OFFSET "+startPage ;
                   	 
                   	stmt=conn.prepareStatement(qry);
        			stmt = setQueryWhereClause_SQL(stmt,Search,assets_type,model_number,machine_number,ram_capi,hdd_capi,operating_system,unit_sus_no,from_date,to_date,status,section,sessionUserId);
        			stmt = setQueryWhereClause_SQL1(stmt,Search,status);
        		
                   	
                   }
			 
		    ResultSet rs = null ;
		    try {
				rs = stmt.executeQuery();      
				ResultSetMetaData metaData = rs.getMetaData();
	
				int columnCount = metaData.getColumnCount();
				while (rs.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();			
					for (int i = 1; i <= columnCount; i++) {
						columns.put(metaData.getColumnLabel(i), rs.getObject(i));

						
					}
					 String updateButton="";
						
					
					 String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update Details ?') ){editData("+ rs.getInt("id") + ","+ rs.getInt("ch_id") + ")}else{ return false;}\"";
					 updateButton = "<i class='action_icons action_update' "+Update+" title='Edit Data'></i>";
					
						
						String Approve = "onclick=\"  if (confirm('Are You Sure You Want to Approve This Information ?') ){Approve("
								+ rs.getInt("id") + ")}else{ return false;}\"";
						
						String ApproveButton = "<i class='action_icons action_approve' " + Approve + " title='Approve Data'></i>";
		
						
					     String Reject = "onclick=\"  if (confirm('Are You Sure You Want to Reject This Information ?') ){Reject("
									+ rs.getInt("id") + ")}else{ return false;}\"";
						 String RejectButton  = "<i class='action_icons action_reject' " + Reject + " title='Reject Data'></i>";

					
					
					 String f = "";
					 
					 List<Map<String, Object>> ls = getComputingChildStatus( rs.getInt("id"));
					 String ch_status ="1";
					 if(ls.size() > 0 )
					 {
						  ch_status = String.valueOf(ls.get(0).get("status")) ;
					 }
					 
					
					 
					if (roleType.equals("DEO") && (status == "0"  || status.equals("0") || status == "3"  || status.equals("3"))) {
					
						 f += updateButton;
					}
					
					if (roleType.equals("DEO") && (status == "1"  || status.equals("1"))) {
						
						 f += updateButton;
					}
					
					if (roleType.equals("APP") && ((status == "0"  || status.equals("0")) && (ch_status == "0"  || ch_status.equals("0"))) ) {
						
						f += updateButton;
					
						 f += ApproveButton;
	                      f += RejectButton;
					}
					if (roleType.equals("APP") && ((status == "0"  || status.equals("0")) && (ch_status == "1"  || ch_status.equals("1"))) ) {
						
						f += updateButton;
					
						
					}
					
					if (roleType.equals("APP") && (status == "1"  || status.equals("1")) ) {
						
						f += updateButton;
					
						 
					}
					
					if (roleType.equals("APP") && (status == "3"  || status.equals("3")) ) {
						
						f += updateButton;
					
						 
					}
					columns.put("action", f);
					
					
					list.add(columns);
				}
				rs.close();
		    }catch (Exception e) {
				// TODO: handle exception
			}
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
	public long getAppComputingassetCountList(String Search,String orderColunm,String orderType,String status,String assets_type,String model_number,
			String machine_number,String ram_capi,String hdd_capi,String operating_system,String unit_sus_no,String from_date,String to_date,String section,HttpSession sessionUserId)throws SQLException {
    	String SearchValue = GenerateQueryWhereClause_SQL(Search,assets_type,model_number,machine_number,ram_capi,hdd_capi,operating_system,unit_sus_no,from_date,
    			to_date,status,section,sessionUserId);
    	String SearchValue1 = GenerateQueryWhereClause_SQL1(Search,status);
		int total = 0;
		String q = null;
		
		 PreparedStatement stmt =null;
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();

			
			 if(status.equals("0")) {
				 q="select"+
                 			" count(app.*)\r\n"
                 			+ "from(select am.id,ma.assets_name,am.model_number,am.machine_number,am.mac_address,am.ip_address,pm.processor_type,rm.ram,hm.hdd,om.os,ofm.office,fm.os_firmware,dem.dply_env,\r\n" + 
               	 		" coalesce(ltrim(TO_CHAR( am.warranty  ,'DD-MON-YYYY'),'0'),'')   as warranty,\r\n" + 
               	 		" td1.label   as service_state,\r\n" + 
               	 		" td3.label   as unservice_state  ,\r\n" + 
               	 		" td1.codevalue  as ser,ch_am.unsv_from_dt,ch_am.unsv_to_dt,case when ch_am.status=0 then ch_am.id else 0 end as ch_id, \r\n" + 
               	 		" td3.codevalue   as unsv ,sec.section  from tb_asset_main am \r\n" + 
               	 		"inner join tb_assets_child ch_am on am.id = ch_am.p_id  \r\n" + 
               			"inner join tb_mstr_section sec on sec.id:: text = am.section   \r\n" + 
               	 		"left join  tb_mstr_assets ma on  ma.id=am.asset_type and ma.category=1\r\n" + 
               	 		"left join  tb_mstr_processor_type pm on  pm.id=am.processor_type\r\n" + 
               	 		"left join  tb_mstr_ram rm on  rm.id=am.ram_capi\r\n" + 
               	 		"left join  tb_mstr_hdd hm on  hm.id=am.hdd_capi\r\n" + 
               	 		"left join  tb_mstr_os om on  om.id=am.operating_system\r\n" + 
               	 		"left join  tb_mstr_office ofm on  ofm.id=am.office\r\n" + 
               	 		"left join  tb_mstr_os_firmware fm on  fm.id=am.os_patch\r\n" + 
               	 		"left join  tb_mstr_dply_env dem on  dem.id=am.dply_envt \r\n" + 
               	 		"left join t_domain_value td on  td.codevalue:: character varying = am.s_state:: character varying and td.domainid='SERVICE_CAT'\r\n" + 
               	 		"left join t_domain_value td1 on  td1.codevalue:: character varying = ch_am.service_state:: character varying and td1.domainid='SERVICE_CAT'  \r\n" + 
               	 		"left join t_domain_value td2 on  td2.codevalue:: character varying = am.unserviceable_state:: character varying and td2.domainid='UNSERVICEABLE_STATE' \r\n" + 
               	 		"left join t_domain_value td3 on  td3.codevalue:: character varying = ch_am.unservice_state:: character varying and td3.domainid='UNSERVICEABLE_STATE' \r\n" + 
               	 		"where am.id!=0 and am.status='1' and ch_am.status = (case when (select count(*) from tb_assets_child ch where ch.p_id=am.id)=1 then 1 else 0 end) " + SearchValue+
                            "group by  am.id,ma.assets_name,am.model_number,am.machine_number,am.mac_address,am.ip_address,pm.processor_type,rm.ram,hm.hdd,om.os,ofm.office,fm.os_firmware,dem.dply_env,\r\n" + 
                            "ch_am.warranty,ch_am.service_state,ch_am.unsv_from_dt,td1.label,td3.label,td1.codevalue,td3.codevalue,ch_am.id,sec.section  ) app";
                           
                stmt=conn.prepareStatement(q);
    			
  				 
    			stmt = setQueryWhereClause_SQL(stmt,Search,assets_type,model_number,machine_number,ram_capi,hdd_capi,operating_system,unit_sus_no,from_date,to_date,status,section,sessionUserId);
    		
               }
			
			  else {
                   	 q="select"+
                  			" count(app.*)\r\n"
                 			+ "from(select distinct am.id,ma.assets_name,am.model_number,am.machine_number,am.mac_address,am.ip_address,pm.processor_type,rm.ram,hm.hdd,om.os,ofm.office,fm.os_firmware,dem.dply_env,\r\n" + 
                                " coalesce(ltrim(TO_CHAR( am.warranty  ,'DD-MON-YYYY'),'0'),'')   as warranty,\r\n" + 
                                " td1.label   as service_state,\r\n" + 
                                " td3.label   as unservice_state  ,\r\n" + 
                                " td1.codevalue  as ser,ch_am.unsv_from_dt,ch_am.unsv_to_dt, \r\n" + 
                                " td3.codevalue   as unsv,ch_am.id as ch_id ,sec.section " + 
                                " from tb_asset_main am \r\n" + 
                                "inner join tb_assets_child ch_am on am.id = ch_am.p_id \r\n" +
                        		"inner join tb_mstr_section sec on sec.id :: text = am.section   \r\n" + 
                                "                                        left join  tb_mstr_assets ma on  ma.id=am.asset_type and ma.category=1\r\n" + 
                                "                                        left join  tb_mstr_processor_type pm on  pm.id=am.processor_type\r\n" + 
                                "                                        left join  tb_mstr_ram rm on  rm.id=am.ram_capi\r\n" + 
                                "                                        left join  tb_mstr_hdd hm on  hm.id=am.hdd_capi\r\n" + 
                                "                                        left join  tb_mstr_os om on  om.id=am.operating_system\r\n" + 
                                "                                        left join  tb_mstr_office ofm on  ofm.id=am.office\r\n" + 
                                "                                        left join  tb_mstr_os_firmware fm on  fm.id=am.os_patch\r\n" + 
                                "                                        left join  tb_mstr_dply_env dem on  dem.id=am.dply_envt \r\n" + 
                                "                                        left join t_domain_value td on  td.codevalue:: character varying = am.s_state:: character varying and td.domainid='SERVICE_CAT'\r\n" + 
                                "                                        left join t_domain_value td1 on  td1.codevalue:: character varying = ch_am.service_state:: character varying and td1.domainid='SERVICE_CAT'  \r\n" + 
                                "                                        left join t_domain_value td2 on  td2.codevalue:: character varying = am.unserviceable_state:: character varying and td2.domainid='UNSERVICEABLE_STATE' \r\n" + 
                                "                                        left join t_domain_value td3 on  td3.codevalue:: character varying = ch_am.unservice_state:: character varying and td3.domainid='UNSERVICEABLE_STATE' \r\n" + 
                                "                                        where am.id!=0 and am.status='1' \r\n" +SearchValue1+" " +SearchValue+
                                "group by  am.id,ma.assets_name,am.model_number,am.machine_number,am.mac_address,am.ip_address,pm.processor_type,rm.ram,hm.hdd,om.os,ofm.office,fm.os_firmware,dem.dply_env,\r\n" + 
                                "ch_am.warranty,ch_am.id,ch_am.service_state,ch_am.unsv_from_dt,td1.label,td3.label,td1.codevalue,td3.codevalue ,sec.section) app";
                             
                   	 
                  stmt=conn.prepareStatement(q);
        			
   				 
        			stmt = setQueryWhereClause_SQL(stmt,Search,assets_type,model_number,machine_number,ram_capi,hdd_capi,operating_system,unit_sus_no,from_date,to_date,status,section,sessionUserId);
        			stmt = setQueryWhereClause_SQL1(stmt,Search,status);
        		
                   	
                   }
			

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {  
				total = rs.getInt(1);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
		return (long) total;
	}

		
		
	public String GenerateQueryWhereClause_SQL(String Search,String assets_type,String model_number,String machine_number,String ram_capi,String hdd_capi,String operating_system,String unit_sus_no,String from_date,String to_date, String status,String section,HttpSession sessionUserId) {
		String SearchValue ="";
		
		if (!Search.equals("") || !Search.equals(null) || Search != null || Search != " ") { // for Input Filter
			SearchValue = " and ( ";
			SearchValue += "cast(ma.assets_name as character varying) like ? "
					+ "or upper(am.model_number)  like ? "
					+ "or upper(am.machine_number)  like ? "
					+ " or upper(am.mac_address)  like ? "
					+ " or upper(am.ip_address)  like ? "
					+ " or upper(pm.processor_type)  like ? "
					+ " or cast(rm.ram as character varying)  like ? "
					+ "or cast(hm.hdd  as character varying)  like ? "
					+ "or cast(om.os as character varying)  like ? "
					+ "or upper(ofm.office)  like ? "
					+ "or upper(dem.dply_env)  like ? " + 
					"or coalesce(ltrim(TO_CHAR( am.warranty  ,'DD-MON-YYYY'),'0'),'') like ? " + 
					"or upper(td.label) like ? " + 
					"or upper(td2.label) like ? " + 
					"or upper(td.codevalue) like ? " + 
					"or upper(sec.section) like ? " + 
					"or upper(td2.codevalue)  like ? ) ";
			
		}
      
        
     
        
        if(!assets_type.equals("0")) {
        	SearchValue+=" and am.asset_type  = ?";
		}
        if(!model_number.equals("")) {
        	SearchValue+=" and upper(am.model_number) like ?";      
		}
        if(!machine_number.equals("")) {
        	SearchValue+=" and upper(am.machine_number) like ?";      
		}
        if(!ram_capi.equals("0")) {
        	SearchValue+=" and cast(am.ram_capi as character varying) = ?";
		}        
        if(!hdd_capi.equals("0")) {
        	SearchValue+=" and cast(am.hdd_capi as character varying) = ?";
		} 
        if(!operating_system.equals("0")) {
        	SearchValue+=" and cast(am.operating_system as character varying) = ?";
		} 
        if(!from_date.equals("") && !to_date.equals(""))
		{
        	SearchValue +=" and am.created_date BETWEEN to_date(?,'DD/MM/YYYY') and  to_date(?,'DD/MM/YYYY')";
		}
        String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		if (roleAccess.equals("Unit") ) {
				SearchValue += "  and am.sus_no = ? ";
		}
		if(!section.equals("0") && !section.equals(""))
		{
			SearchValue +="AND am.section=? ";
		}
		
		return SearchValue;
	}
	
	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt,String Search,String assets_type,String model_number,String machine_number,String ram_capi,String hdd_capi,String operating_system,String unit_sus_no,String from_date,String to_date, String status,String section,HttpSession sessionUserId) {
		
		int flag = 0;
		if(status.equals("1"))
		{
			flag = 1;
		}
		try {
			if (!Search.equals("") || !Search.equals(null)) {
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");		
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");	
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");	
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");	
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");

			}

			

				
				  if(!assets_type.equals("0"))
		             {
					  flag += 1;
					  stmt.setInt(flag, Integer.parseInt(assets_type));
						
		             }
				  if(!model_number.equals(""))
		             {
					  flag += 1;
					  stmt.setString(flag, model_number.toUpperCase() +"%");
						
		             }
				  if(!machine_number.equals(""))
		             {
					  flag += 1;
					  stmt.setString(flag, machine_number.toUpperCase() +"%");
						
		             }
				  if(!ram_capi.equals("0"))
		             {
					  flag += 1;
					  stmt.setString(flag, ram_capi);
						
		             }
				  if(!hdd_capi.equals("0"))
		             {
					  flag += 1;
					  stmt.setString(flag, hdd_capi);
						
		             }
				  if(!operating_system.equals("0"))
		             {
					  flag += 1;
					  stmt.setString(flag, operating_system);
						
		             }
				  if (!from_date.equals("") && !to_date.equals("")) {
					  flag += 1;
						stmt.setString(flag, from_date);
						flag += 1;
						stmt.setString(flag, to_date);
						
					}
				  String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
					String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
					if (roleAccess.equals("Unit") ) {
						flag += 1;
						stmt.setString(flag, roleSusNo);   
					}
					if(!section.equals("0") && !section.equals(""))
					{
						flag += 1;
						stmt.setString(flag, section);   
					}
		
		}catch (Exception e) {}
		return stmt;
	}
	
	// ** END BISAG **//
	
	public String GenerateQueryWhereClause_SQL1(String Search, String status) {
		String SearchValue1 ="";
		

		
        if(status == "0" ||  status.equals("0"))
        {
        	
        	SearchValue1="and ch_am.status =?";
        }
        if(status == "1"  || status.equals("1") || status == "3"  || status.equals("3"))
        {
        	SearchValue1="and ch_am.status=?";
        }
        
		return SearchValue1;
	}
	
	public PreparedStatement setQueryWhereClause_SQL1(PreparedStatement stmt,String Search, String status) {
		
		int flag = 0;

		try {
			if (!Search.equals("") || !Search.equals(null)) {
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");		
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");	
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");	
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");	
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
			}

			 if(status == "0" ||  status.equals("0"))
             {
				 stmt.setInt(1, Integer.parseInt(status) );
             }
			 
			  if(status == "1"  || status.equals("1") || status == "3"  || status.equals("3"))
	             {
	            	 stmt.setInt(1, Integer.parseInt(status) );
	             }
				 
             
             
			
			 
			
		}catch (Exception e) {}
		return stmt;
	}


	
	
	public List<Map<String, Object>> getAppChildComputingassetList(String ch_id)
	{		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		try{	  
			conn = dataSource.getConnection();		
			String qry="";		
			
			qry="select * from tb_assets_child where p_id=? order by id desc limit 1" ;
			
			PreparedStatement stmt=conn.prepareStatement(qry);
			stmt.setInt(1,Integer.parseInt(ch_id));	
		    ResultSet rs = null ;
		    try {
				rs = stmt.executeQuery();      
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
		    }catch (Exception e) {
				// TODO: handle exception
			}
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
	
	// ** END BISAG AHM **//
	
	
	public List<Map<String, Object>> getComputingChildStatus (int id )
	{		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		try{	  
			conn = dataSource.getConnection();		
			String qry="";		
			
			qry="select status  from  tb_assets_child where p_id =? order by id desc limit 1"; 
			
			PreparedStatement stmt=conn.prepareStatement(qry);
			stmt.setInt(1, id);
		
		    ResultSet rs = null ;
		    try {
				rs = stmt.executeQuery();      
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
		    }catch (Exception e) {
				// TODO: handle exception
			}
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
		public List<Map<String, Object>> GetdataComputing(int id) throws SQLException {
			
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";
		
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;
				
				
				//** AHM BISAG **//
		
				
				q = "select warranty,unservice_state,service_state,unsv_from_dt,unsv_to_dt,ip_address,ram_capi,hdd_capi,operating_system,office,antiviruscheck,"
						+ "antivirus,os_patch,dply_envt,ram_slot_qty,cd_drive,b_head,b_code,b_cost from tb_assets_child where p_id=? and status=0";
		
				
				//**  END AHM BISAG **//
				
				stmt = conn.prepareStatement(q);
				stmt.setInt(1, id);
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
			} catch (SQLException e) {
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
	
//1234	
public ArrayList<ArrayList<String>> Search_DataTableList(HttpSession session){
		
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
				
			String roleSusNo = session.getAttribute("roleSusNo").toString();	
			q = "select th.type_of_hw,ap.proc_cost,ap.machine_no,ap.model_no,ap.ip_address,ap.type,ap.remarks,\r\n" + 
					"ap.monochrome_color,ap.is_networked,ap.print,ap.scan,ap.photography,ap.colour,ap.capacity,ap.resolution,ap.no_of_ports,ups.ups_capacity,pt.assets_name,\r\n" + 
					"ap.display_connector,ap.display_size,ap.paper_size,ap.b_head,ap.b_code,ap.b_cost,case when count(ch_am.*) >0 then td.label else td1.label end  as service_state,\r\n" + 
					"make.make_name,model.model_name,td4.label as status,case when count(ch_am.*) >0 then td2.label else td3.label end  as unservice_state,\r\n" + 
					"case when count(ch_am.*) >0 then ch_am.warranty else ap.warranty end as warranty,ap.connectivity_type,hwi.hardware_interface,epo.ethernet_port,\r\n" + 
					"mala.management_layer,nef.network_features,ap.v_display_size,ap.v_display_connector,(TO_CHAR(ap.proc_date,'DD-MM-YYYY')) as proc_date,\r\n" + 
					"ap.sus_no,ap.display_type,ap.year_of_proc,ap.year_of_manufacturing\r\n" + 
					"from it_asset_peripherals ap\r\n" + 
					"left join tb_mstr_type_of_hw th on th.id=ap.type_of_hw\r\n" + 
					"left join tb_mstr_ups_capacity ups on ups.id=cast(ap.ups_capacity as int)\r\n" + 
					"left join tb_peripheral_child ch_am on ap.id = ch_am.p_id and ch_am.status=0\r\n" + 
					"left join tb_mstr_assets pt on pt.id=ap.assets_type  and pt.category=2\r\n" + 
					"left join t_domain_value td on  td.codevalue:: character varying = ap.s_state:: character varying and td.domainid='SERVICE_CAT'\r\n" + 
					"left join t_domain_value td1 on  td1.codevalue:: character varying = ch_am.service_state:: character varying and td1.domainid='SERVICE_CAT'  \r\n" + 
					"left join t_domain_value td2 on  td2.codevalue:: character varying = ap.unserviceable_state:: character varying and td2.domainid='UNSERVICEABLE_STATE' \r\n" + 
					"left join t_domain_value td3 on  td3.codevalue:: character varying = ch_am.unservice_state:: character varying and td3.domainid='UNSERVICEABLE_STATE' \r\n" + 
					"left join tb_mstr_make make on make.id=ap.make_name\r\n" + 
					"left join tb_mstr_model model on model.id=ap.model_name\r\n" + 
					"left join t_domain_value td4 on  td4.codevalue = ap.status :: character varying and td4.domainid='STATUS'\r\n" + 
					"left join tb_mstr_hardware_interface hwi on hwi.id:: character varying   =ap.hw_interface \r\n" + 
					"left join tb_mstr_ethernet_port epo on epo.id=ap.ethernet_port\r\n" + 
					"left join tb_mstr_management_layer mala on mala.id=ap.management_layer\r\n" + 
					"left join tb_mstr_network_features nef on nef.id :: character varying   =ap.network_features\r\n" + 
					" WHERE ap.sus_no=? and ap.status=1 group by th.type_of_hw,ap.year_of_proc,ap.year_of_manufacturing,ap.proc_cost,ap.machine_no,ap.model_no,ap.ip_address,ap.type,ap.remarks,\r\n" + 
					"ap.monochrome_color,ap.is_networked,ap.print,ap.scan,ap.photography,ap.colour,ap.capacity,ap.resolution,ap.no_of_ports,ups.ups_capacity,pt.assets_name,\r\n" + 
					"ap.display_connector,ap.display_size,ap.paper_size,ap.b_head,ap.b_code,ap.b_cost,ch_am.warranty,ap.warranty,td.label,td1.label,td2.label,td3.label,td4.label,\r\n" + 
					"make.make_name,model.model_name,td4.label,ap.connectivity_type,hwi.hardware_interface,epo.ethernet_port,mala.management_layer,nef.network_features,ap.v_display_size,\r\n" + 
					"ap.v_display_connector,ap.proc_date,ap.sus_no,ap.display_type";
					
			stmt = conn.prepareStatement(q);
			stmt.setString(1, roleSusNo);
			
			ResultSet rs = stmt.executeQuery();   
			
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("type_of_hw"));
				alist.add(rs.getString("proc_cost"));
				alist.add(rs.getString("machine_no"));
				alist.add(rs.getString("model_no"));
				alist.add(rs.getString("ip_address"));
				alist.add(rs.getString("type"));
				alist.add(rs.getString("remarks"));
				alist.add(rs.getString("monochrome_color"));
				alist.add(rs.getString("is_networked"));
				alist.add(rs.getString("print"));
				alist.add(rs.getString("scan"));
				alist.add(rs.getString("photography"));
				alist.add(rs.getString("colour"));
				alist.add(rs.getString("capacity"));
				alist.add(rs.getString("resolution"));
				alist.add(rs.getString("no_of_ports"));
				alist.add(rs.getString("ups_capacity"));
				alist.add(rs.getString("assets_name"));
				
				alist.add(rs.getString("display_connector"));
				alist.add(rs.getString("display_size"));
				alist.add(rs.getString("paper_size"));
				alist.add(rs.getString("b_head"));
				alist.add(rs.getString("b_code"));
				alist.add(rs.getString("b_cost"));
				alist.add(rs.getString("service_state"));
				alist.add(rs.getString("make_name"));
				alist.add(rs.getString("model_name"));
				alist.add(rs.getString("status"));
				alist.add(rs.getString("unservice_state"));
				alist.add(rs.getString("warranty"));
				alist.add(rs.getString("connectivity_type"));
				alist.add(rs.getString("hardware_interface"));
				alist.add(rs.getString("ethernet_port"));
				alist.add(rs.getString("management_layer"));
				alist.add(rs.getString("network_features"));
				alist.add(rs.getString("v_display_size"));
				alist.add(rs.getString("v_display_connector"));
				alist.add(rs.getString("proc_date"));
				alist.add(rs.getString("sus_no"));
				
				alist.add(rs.getString("display_type"));
				alist.add(rs.getString("year_of_proc"));
				alist.add(rs.getString("year_of_manufacturing"));
				list.add(alist);
		        }

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
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

public ArrayList<ArrayList<String>> Search_DataTableList1(HttpSession session){
	
	ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q = "";
	try {
		//1111 KAJAL
		conn = dataSource.getConnection();
		PreparedStatement stmt = null;
			
		String roleSubAccess = session.getAttribute("roleSubAccess").toString();
		String roleFormationNo = session.getAttribute("roleFormationNo").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		//if()
		q = "select a.assets_name,ap.model_number,ap.machine_number,ap.mac_address,ap.ip_address,p.processor_type,r.ram,h.hdd,o.os,op.office,o1.os as os_patch,\r\n" + 
				"env.dply_env,ap.antiviruscheck,anti.antivirus,ap.b_head,ap.b_code,ap.b_cost,ap.cd_drive,\r\n" + 
				"case when count(ch_am.*) >0 then td1.label else td.label end  as service_state,\r\n" + 
				"model.model_name,make.make_name,case when count(ch_am.*) >0 then td3.label else td2.label end  as unservice_state,td4.label as status,\r\n" + 
				"case when count(ch_am.*) >0 then (TO_CHAR(ch_am.warranty,'DD-MM-YYYY')) else (TO_CHAR(ap.warranty,'DD-MM-YYYY')) end as warranty,ap.dimension,ap.part_no,epo.ethernet_port,ap.ram_slot_qty,\r\n" + 
				"ap.sus_no,(TO_CHAR(ap.proc_date,'DD-MM-YYYY')) as proc_date from\r\n" + 
				"tb_asset_main ap \r\n" + 
				"left join tb_mstr_assets a on ap.asset_type=a.id\r\n" + 
				"left join tb_mstr_processor_type p on ap.processor_type=p.id\r\n" + 
				"left join tb_mstr_ram r on ap.ram_capi=r.id\r\n" + 
				"left join tb_mstr_hdd h on ap.hdd_capi=h.id\r\n" + 
				"left join tb_mstr_os o on ap.operating_system=o.id\r\n" + 
				"left join tb_mstr_office op on ap.office=op.id\r\n" + 
				"left join tb_mstr_os o1 on ap.os_patch=o1.id\r\n" + 
				"left join tb_mstr_dply_env env on ap.dply_envt=env.id\r\n" + 
				"left join tb_mstr_antivirus anti on ap.antivirus=anti.id\r\n" + 
				"left join tb_peripheral_child ch_am on ap.id = ch_am.p_id and ch_am.status=0\r\n" + 
				"left join t_domain_value td on  td.codevalue:: character varying = ap.s_state:: character varying and td.domainid='SERVICE_CAT'\r\n" + 
				"left join t_domain_value td1 on  td1.codevalue:: character varying = ch_am.service_state:: character varying and td1.domainid='SERVICE_CAT'  \r\n" + 
				"left join t_domain_value td2 on  td2.codevalue:: character varying = ap.unserviceable_state:: character varying and td2.domainid='UNSERVICEABLE_STATE' \r\n" + 
				"left join t_domain_value td3 on  td3.codevalue:: character varying = ch_am.unservice_state:: character varying and td3.domainid='UNSERVICEABLE_STATE' \r\n" + 
				"left join t_domain_value td4 on  td4.codevalue = ap.status :: character varying and td4.domainid='STATUS'\r\n" + 
				"left join tb_mstr_make make on make.id=ap.make_name\r\n" + 
				"left join tb_mstr_model model on model.id=ap.model_name\r\n" + 
				"left join tb_mstr_ethernet_port epo on epo.id=ap.ethernet_port\r\n" + 
				"WHERE ap.sus_no=? and ap.status=1 group by a.assets_name,ap.model_number,ap.machine_number,ap.mac_address,ap.ip_address,p.processor_type,r.ram,h.hdd,o.os,op.office,o1.os,\r\n" + 
				"env.dply_env,ap.antiviruscheck,anti.antivirus,ap.b_head,ap.b_code,ap.b_cost,ap.cd_drive,td.label,td1.label,td2.label,td4.label,td3.label,\r\n" + 
				"ch_am.warranty,ap.warranty,ap.dimension,ap.part_no,epo.ethernet_port,ap.ram_slot_qty,model.model_name,make.make_name,\r\n" + 
				"ap.sus_no,ap.proc_date" ;
				
		stmt = conn.prepareStatement(q.toString());
		stmt.setString(1, roleSusNo);
		ResultSet rs = stmt.executeQuery();   
		
		while (rs.next()) {
			ArrayList<String> alist = new ArrayList<String>();
			alist.add(rs.getString("assets_name"));
			alist.add(rs.getString("model_number"));
			alist.add(rs.getString("machine_number"));
			alist.add(rs.getString("mac_address"));
			alist.add(rs.getString("ip_address"));
			alist.add(rs.getString("processor_type"));
			alist.add(rs.getString("ram"));
			alist.add(rs.getString("hdd"));
			alist.add(rs.getString("os"));
			alist.add(rs.getString("office"));
			alist.add(rs.getString("os_patch"));
			alist.add(rs.getString("dply_env"));
			alist.add(rs.getString("antiviruscheck"));
			alist.add(rs.getString("antivirus"));
			alist.add(rs.getString("b_head"));
			alist.add(rs.getString("b_code"));
			alist.add(rs.getString("b_cost"));
			alist.add(rs.getString("cd_drive"));
			alist.add(rs.getString("service_state"));
			alist.add(rs.getString("model_name"));
			alist.add(rs.getString("make_name"));
			alist.add(rs.getString("unservice_state"));
			alist.add(rs.getString("status"));
			alist.add(rs.getString("warranty"));
			alist.add(rs.getString("dimension"));
			alist.add(rs.getString("part_no"));
			alist.add(rs.getString("ethernet_port"));
			alist.add(rs.getString("ram_slot_qty"));
			alist.add(rs.getString("sus_no"));
			alist.add(rs.getString("proc_date"));
			
			list.add(alist);
	        }
		rs.close();
		stmt.close();
		conn.close();
	} catch (SQLException e) {
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


	// bisag v4 18-11-2022 (delete assets)
	public List<Map<String, Object>> Search_Computing_AssetsDelete(int startPage,int pageLength,String Search,String orderColunm,String orderType,String status,String assets_type,String model_number,String machine_number,String ram_capi,String hdd_capi,String operating_system,String from_date,String to_date,String s_state,String unit_sus_no, String unit_name, String section ,HttpSession session)
	{
	
  	String SearchValue = GenerateQueryWhereClause_SQL4(Search,assets_type,model_number,machine_number,ram_capi,hdd_capi,operating_system,from_date,to_date,status,s_state,unit_sus_no,  unit_name,section,session);
  	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();			
	Connection conn = null;
	String q="";
	
	try{	
		String pageL = "";
      
      if(pageLength == -1){
			pageL = "ALL";
		}else {
			pageL = String.valueOf(pageLength);
		}
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			q="select am.id,ma.assets_name,am.s_state,am.model_number,am.machine_number,am.mac_address,am.ip_address,pm.processor_type,rm.ram,hm.hdd,om.os,ofm.office,fm.os_firmware,dem.dply_env,sec.section,am.created_by  from tb_asset_main am\r\n" + 
					"inner join  tb_mstr_section sec  on sec.id:: text = am.section \r\n"+
					"left join  tb_mstr_assets ma on  ma.id=am.asset_type and ma.category=1\r\n" + 
					"left join  tb_mstr_processor_type pm on  pm.id=am.processor_type\r\n" + 
					"left join  tb_mstr_ram rm on  rm.id=am.ram_capi\r\n" + 
					"left join  tb_mstr_hdd hm on  hm.id=am.hdd_capi\r\n" + 
					"left join  tb_mstr_os om on  om.id=am.operating_system\r\n" + 
					"left join  tb_mstr_office ofm on  ofm.id=am.office\r\n" + 
					"left join  tb_mstr_os_firmware fm on  fm.id=am.os_patch\r\n"+
//					"inner join  tb_mstr_make mm on  mm.id=am.make_name\r\n"+ 
					"left join  tb_mstr_dply_env dem on  dem.id=am.dply_envt  "
					+ "left join tb_orbat_unt_dtl orb on orb.sus_no = am.sus_no and status_sus_no='Active'"
					+ " where am.id!=0 "+SearchValue+
					" ORDER BY "+orderColunm+" "+orderType +" limit " +pageL+" OFFSET "+startPage ;
			stmt=conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL4(stmt,Search,assets_type,model_number,machine_number,ram_capi,hdd_capi,operating_system,
					from_date,to_date,status,s_state,unit_sus_no,  unit_name,section,session);
	
	      ResultSet rs = stmt.executeQuery();   

	      ResultSetMetaData metaData = rs.getMetaData();
	      int columnCount = metaData.getColumnCount();
	      
	      while (rs.next()) {
	    	  Map<String, Object> columns = new LinkedHashMap<String, Object>();
	    	  for (int i = 1; i <= columnCount; i++) {
	    		  columns.put(metaData.getColumnLabel(i), rs.getObject(i));
	    		  
	    		 
	    	  }
	    	  String Checkbox="<input class='nrCheckBox' type='checkbox' id='" + rs.getObject(1)//13
				+ "' name='cbox' onchange='checkbox_count(this," + rs.getObject(1) + ");' />";
		  
		  String CheckboxId="<input  type='hidden' id='id" + rs.getObject(1) + "' name='id" + rs.getObject(1)//14
				+ "' value='" + rs.getObject(1) + "'   />";
		  
		  String chekboxaction="";
		  
		chekboxaction+=Checkbox;
	      chekboxaction+=CheckboxId;
	      
	  	columns.put("chekboxaction", chekboxaction);
		  
		  
		  
				
				String f1 = "";				
				String f2 = "";
				String f3 = "";
				
				
			
					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update Details ?') ){editData("
							+ rs.getInt("id") + ")}else{ return false;}\"";
					f1 = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";

					String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Information ?') ){deleteData("
							+ rs.getInt("id") + ")}else{ return false;}\"";
					f2 = "<i class='action_icons action_delete' " + Delete1 + " title='Delete Data'></i>";
					

					String View = "onclick=\"  if (confirm('Are You Sure You Want to View This Information ?') ){viewData("
												+ rs.getInt("id") + ")}else{ return false;}\"";
										
										f3 = "<i class='fa fa-eye' " + View + " title='View Data'></i>";
	
				
			String action="";
										
//			 if (!status.equals("1")) {
//				 columns.put("action",f1);//15
				 columns.put("action",f2);//16
				 action+=f2;
//				 action+=f1;
//				}
				

			 
//			 columns.put("action",f3);//17
//			 action+=f3;
			 columns.put("action", action);
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
	
	
	 public long getAppComputingassetCountList1Delete(String Search,String orderColunm,String orderType,String status,String assets_type,String model_number,
				String machine_number,String ram_capi,String hdd_capi,String operating_system,String from_date,String to_date,String s_state,String unit_sus_no,
				String unit_name,String section,HttpSession sessionUserId)throws SQLException {
	    	String SearchValue = GenerateQueryWhereClause_SQL4(Search,assets_type,model_number,machine_number,ram_capi,hdd_capi,operating_system,from_date,to_date,status,s_state, unit_sus_no,  unit_name,section,sessionUserId);
			int total = 0;
			String q = null;
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				q ="select \r\n" + 
						" count(app.*)\r\n" + 
						" from  ( select am.id,ma.assets_name,am.s_state,am.model_number,am.machine_number,am.mac_address,am.ip_address,pm.processor_type,rm.ram,hm.hdd,om.os,ofm.office,fm.os_firmware,dem.dply_env,sec.section,am.created_by from tb_asset_main am \r\n" + 
						" inner join  tb_mstr_section sec  on sec.id:: text = am.section \r\n"+
						"					left join  tb_mstr_assets ma on  ma.id=am.asset_type and ma.category=1 \r\n" + 
						"					left join  tb_mstr_processor_type pm on  pm.id=am.processor_type \r\n" + 
						"					left join  tb_mstr_ram rm on  rm.id=am.ram_capi \r\n" + 
						"					left join  tb_mstr_hdd hm on  hm.id=am.hdd_capi \r\n" + 
						"					left join  tb_mstr_os om on  om.id=am.operating_system \r\n" + 
						"					left join  tb_mstr_office ofm on  ofm.id=am.office\r\n" + 
						"					left join  tb_mstr_os_firmware fm on  fm.id=am.os_patch \r\n" + 
//						"					inner join  tb_mstr_make mm on  mm.id=am.make_name\r\n"+ 
						"					left join  tb_mstr_dply_env dem on  dem.id=am.dply_envt  "
						+ "left join tb_orbat_unt_dtl orb on orb.sus_no = am.sus_no and status_sus_no='Active' where am.id!=0  "  + SearchValue +" ) app " ;

				PreparedStatement stmt = conn.prepareStatement(q);
				
				stmt = setQueryWhereClause_SQL4(stmt,Search,assets_type,model_number,machine_number,ram_capi,hdd_capi,operating_system,from_date,to_date,status,s_state,unit_sus_no,  unit_name,section,sessionUserId);
				
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {  
					total = rs.getInt(1);
				}
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
					}
				}
			}
			return (long) total;
		}



	@Override
	public ArrayList<ArrayList<String>> excel_Computing_Assets_ReportDataList(HttpSession session, String model_number,
			String machine_number, String assets_type, String status, String s_state, String section) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		String SearchValue = GenerateQueryWhereClause_SQL4(assets_type,model_number,machine_number,status,
    			s_state,section,session);
  
	Connection conn = null;
	String q="";
	
	try{	
		String pageL = "";
        
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			
			q="select am.id,ma.assets_name,am.s_state,am.model_number,am.machine_number,am.mac_address,am.ip_address,pm.processor_type,rm.ram,hm.hdd,om.os,ofm.office,fm.os_firmware,dem.dply_env ,sec.section ,am.created_by from tb_asset_main am\r\n" + 
					"inner join  tb_mstr_section sec  on sec.id:: text = am.section \r\n"+
					"left join  tb_mstr_assets ma on  ma.id=am.asset_type and ma.category=1\r\n" + 
					"left join  tb_mstr_processor_type pm on  pm.id=am.processor_type\r\n" + 
					"left join  tb_mstr_ram rm on  rm.id=am.ram_capi\r\n" + 
					"left join  tb_mstr_hdd hm on  hm.id=am.hdd_capi\r\n" + 
					"left join  tb_mstr_os om on  om.id=am.operating_system\r\n" + 
					"left join  tb_mstr_office ofm on  ofm.id=am.office\r\n" + 
					"left join  tb_mstr_os_firmware fm on  fm.id=am.os_patch\r\n"+
					"left join  tb_mstr_dply_env dem on  dem.id=am.dply_envt  "
					+ "left join tb_orbat_unt_dtl orb on orb.sus_no = am.sus_no and status_sus_no='Active' where am.id!=0 "+SearchValue+ "";
				
			stmt=conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQL4(stmt,assets_type,model_number,machine_number,status,s_state,section,session);
	      ResultSet rs = stmt.executeQuery();   

			while (rs.next()) {
			
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("assets_name"));// 0
				list.add(rs.getString("model_number"));// 1
				list.add(rs.getString("machine_number"));// 2
				list.add(rs.getString("mac_address"));// 3
				list.add(rs.getString("ip_address"));// 4
				list.add(rs.getString("processor_type"));// 5
				list.add(rs.getString("office"));// 9
				list.add(rs.getString("dply_env"));// 11
			list.add(rs.getString("section"));// 12
			list.add(rs.getString("created_by"));// 12
			
				alist.add(list);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
				}
			}
		}
	     
	return alist;
	}


	
	
	 public String GenerateQueryWhereClause_SQL4
	 (String assets_type,String model_number,String machine_number, String status,String s_state,String section,HttpSession sessionUserId) {
			String SearchValue ="";
			
			
			if(!status.equals(""))
			{
				SearchValue += "  and cast(am.status as character varying) = ? ";
			}
			
			if(!assets_type.equals("0")) {
				SearchValue += "  and cast(am.asset_type as character varying) = ? ";
			}	
			if(!model_number.equals("")) {
				SearchValue += "  and upper(am.model_number) like ?";
			}
			if(!machine_number.equals("")) {
				SearchValue += "  and upper(am.machine_number) like ?";
			}
			
			if(!s_state.equals("0")) {
				SearchValue += "  and cast(am.s_state as character varying) = ? ";
			}
			
			if(!section.equals("") &&!section.equals("0"))
			{
				SearchValue += " and  am.section= ? ";
				
			}
			return SearchValue;
		}
	
	
		public PreparedStatement setQueryWhereClause_SQL4(PreparedStatement stmt, String assets_type,
				String model_number, String machine_number, String status, String s_state, String section,
				HttpSession sessionUserId) {

			int flag = 0;
			try {

				if (!status.equals("")) {
					flag += 1;
					stmt.setString(flag, status);
				}
				if (!assets_type.equals("0")) {
					flag += 1;
					stmt.setString(flag, assets_type);
				}
				if (!model_number.equals("")) {
					flag += 1;
					stmt.setString(flag, model_number.toUpperCase() + "%");

				}
				if (!machine_number.equals("")) {
					flag += 1;
					stmt.setString(flag, machine_number.toUpperCase() + "%");
				}

				if (!s_state.equals("0")) {
					flag += 1;
					stmt.setString(flag, s_state);

				}

				if (!section.equals("") && !section.equals("0")) {
					flag += 1;
					stmt.setString(flag, section);

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return stmt;
		}
	 
	 
	 
	 
	
	
}

