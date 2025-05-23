package com.dao.itasset.WorkOrder;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import org.apache.commons.codec.binary.Base64;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.models.itasset.assets.Assets_Main;
import com.persistance.util.HibernateUtil;

public class WOComputing_Asset_DaoImpl implements WOComputing_Asset_Dao{
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	
	 public boolean checkIsIntegerValue(String Search) {
			return Search.matches("[0-9]+");
		}		
	
	public List<Map<String, Object>> Computing_Asset_ReportDataListDetails(int startPage,String pageLength,String Search,String orderColunm,String orderType,
			String asset_type, String make_name, String model_name,String status,HttpSession session) throws InvalidKeyException, NoSuchAlgorithmException, 
	         NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		if(pageLength.equals("-1")){
 			pageLength = "ALL";
		}


 		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		String WK_Status = "";

		if(status != null && !status.equals("") )
		{
			WK_Status += "and cast(wo.wk_generated_status as character varying)  = ? and wo.category = 1 ";
		}
		try {
			conn = dataSource.getConnection();
		
			q = " select ass.assets_name,make.make_name,model.model_name,cp.model_number,cp.machine_number,cp.mac_address,cp.ip_address,cp.id,   \r\n" + 
					"cp.asset_type,cp.make_name as mk_name,cp.model_name as md_name,COALESCE(wo.id,'0') as wo_id,wo.wk_generated_status\r\n" + 
					"from tb_asset_main cp\r\n" + 
					"inner join tb_mstr_assets ass on ass.id = cp.asset_type and ass.category = 1 \r\n" + 
					"inner join tb_mstr_make make on make.id = cp.make_name and  make.assets_name = cp.asset_type\r\n" + 
					"inner join tb_mstr_model model on model.id = cp.model_name  and model.make_name = cp.make_name\r\n" + 
					"inner join t_domain_value t1 on t1.codevalue = cp.s_state and t1.domainid='SERVICE_CAT'  \r\n" + 
					"inner join t_domain_value t2 on t2.codevalue = cast(cp.unserviceable_state as char) and t2.domainid='UNSERVICEABLE_STATE'  \r\n" + 
					"left join tb_it_asset_work_order wo on cp.machine_number = wo.machine_number and wo.p_id = cp.id  \r\n" + WK_Status +
					" where cp.status = 1  and cp.unserviceable_state in ('2','3') and cp.s_state ='2' \r\n" +

				     " ORDER BY " + orderColunm +" "+orderType +" limit " +pageLength+" OFFSET "+startPage ;
			
			PreparedStatement stmt = conn.prepareStatement(q);

			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
 			int columnCount = metaData.getColumnCount();
 			int rc = 0;
 			while (rs.next()) {
 				rc++;
 				String print_val = "";
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
				    columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				    
				    
				}
				
				 String Checkbox="<input class='nrCheckBox' type='checkbox' id='" + rs.getString("id")//13
					+ "' name='cbox' onchange='checkbox_count(this," + rs.getString("id") + ");' />";
	  		  
	  		  String CheckboxId="<input  type='hidden' id='id" + rs.getString("id") + "' name='id" + rs.getString("id")//14
					+ "' value='" + rs.getString("id") + "'   />";
	  		  
	  		  String chekboxaction="";
	  		  
	  		chekboxaction+=Checkbox;
		      chekboxaction+=CheckboxId;
		      
		  	columns.put("chekboxaction", chekboxaction);
		  	
		  	
		  	String asset_type1 = "<input type='text' id='asset_type"+ rs.getString("id")+"' name='asset_type"+ rs.getString("id")+"' class='form-control autocomplete' autocomplete='off'></input>";
		  	
		  	columns.put("asset_type", asset_type1 );
		  	
		  
		  	String Defects = "<input type='text' id='Defects"+ rs.getString("id")+"' name='Defects"+ rs.getString("id")+"' class='form-control autocomplete' autocomplete='off'></input>";
		  	
		  	columns.put("Defects", Defects );
		  	
		  	String remarks = "<input type='text' id='remarks"+ rs.getString("id")+"' name='remarks"+ rs.getString("id")+"' class='form-control autocomplete' autocomplete='off'></input>";
		  	
		  	columns.put("remarks", remarks );
		  	

		  	
		  	
			String val = rs.getString("wk_generated_status");
			
			if (val == "null" || val == null || val.equals(null)) {
				print_val = "0";
			}else {
				print_val = rs.getString("wk_generated_status");
			}
				
				  String f = "";
				  String f1 = "";
				  
				String add = "onclick=\"  if (confirm('Are You Sure You Want to Generate Computing Work Order ?') ){EditData("+ rs.getString("id") + ")}else{ return false;}\"";
                f = "<i class='fa fa-external-link'" + add + " title='Open Work Order'></i>";
            	columns.put("action", f );
            	
                if(print_val == "1" || print_val.equals("1")) {
                	String print = "onclick=\"  if (confirm('Are You Sure You Want to Print Computing Work Order ?') ){Downloaddata("+ rs.getString("id") + ",'"+ rs.getString("wo_id")+"')}else{ return false;}\"";
    				f1 = "<i class='action_icons action_download'" + print + " title='Print Work Order'></i>";
    				columns.put("action", f +"  "+f1);
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
	  public long Computing_Asset_TotalCountDtl(String Search,String asset_type, String make_name, String model_name,String status) {
			int total = 0;
			String q = null;
			String WK_Status = "";

			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				q ="select count(app1.*) from ("+
						"select ass.assets_name,make.make_name,model.model_name,cp.model_number,cp.machine_number,cp.mac_address,cp.ip_address,cp.id,   \r\n" + 
						"cp.asset_type,cp.make_name as mk_name,cp.model_name as md_name,COALESCE(wo.id,'0') as wo_id,wo.wk_generated_status\r\n" + 
						"from tb_asset_main cp\r\n" + 
						"inner join tb_mstr_assets ass on ass.id = cp.asset_type and ass.category = 1 \r\n" + 
						"inner join tb_mstr_make make on make.id = cp.make_name and  make.assets_name = cp.asset_type\r\n" + 
						"inner join tb_mstr_model model on model.id = cp.model_name  and model.make_name = cp.make_name\r\n" + 
						"inner join t_domain_value t1 on t1.codevalue = cp.s_state and t1.domainid='SERVICE_CAT'  \r\n" + 
						"inner join t_domain_value t2 on t2.codevalue = cast(cp.unserviceable_state as char) and t2.domainid='UNSERVICEABLE_STATE'  \r\n" + 
						"left join tb_it_asset_work_order wo on cp.machine_number = wo.machine_number and wo.p_id = cp.id  \r\n" + WK_Status +
						" where cp.status = 1  and cp.unserviceable_state in ('2','3') and cp.s_state ='2'  \r\n" + ") app1";
				PreparedStatement stmt = conn.prepareStatement(q);

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
	  
	public String GenerateQueryWhereClause_SQL(String Search,String asset_type, String make_name, String model_name,String status) {
 		String SearchValue ="";
 	
  		if(!Search.equals("")) {
			Search = Search.toLowerCase();
  			SearchValue =" and ( ";
 			SearchValue += " cast(ass.assets_name as character varying) = ?  or  "
 					+ " cast(make.make_name as character varying) = ? or cast(model.model_name as character varying) = ? or "
 					+ " cast(cp.model_number as character varying) = ? or cast(cp.machine_number as character varying) = ? or "
 					+ " cast(cp.mac_address as character varying) = ? or cast(cp.ip_address as character varying) = ? ) " ;
 		
 		}
  		
  		
  		if(!asset_type.equals("0")){
    			SearchValue +=" and cast(cp.asset_type as character varying)  = ? ";
    	}
  		if(!make_name.equals("0")){
    			SearchValue +=" and cast(cp.make_name as character varying)  = ? ";
    	}
  		if(!model_name.equals("0")){
    			SearchValue +=" and cast(cp.model_name as character varying)  = ? ";
    	}
  		
   return SearchValue;
 }


  public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt,String Search,String asset_type, String make_name, String model_name,String status) {
 		int flag = 0;
 		try {
 			
    		if(!Search.equals("")) {
 				
 				flag += 1;
 				stmt.setString(flag,Search.toUpperCase());
 				flag += 1;
 				stmt.setString(flag, Search.toUpperCase());
 				flag += 1;
 				stmt.setString(flag, Search.toUpperCase());
 				flag += 1;
 				
 				stmt.setString(flag, Search);
 				flag += 1;
 				stmt.setString(flag, Search);
 				flag += 1;
 				stmt.setString(flag, Search);
 				flag += 1;
 				stmt.setString(flag, Search);
 				flag += 1;
 				
    		}	
    		if(!asset_type.equals("0")) {
				flag += 1;
				stmt.setString(flag, asset_type);
			}
    		if(!make_name.equals("0")) {
				flag += 1;
				stmt.setString(flag, make_name);
			}
    		if(!model_name.equals("0")) {
				flag += 1;
				stmt.setString(flag, model_name);
			}
    		if(status != null && !status.equals("") ){
    			flag += 1;
				stmt.setString(flag, status);
    		}
 		}catch (Exception e) {}
 		return stmt;
 	}
  
  public Assets_Main getComputingAssesByid(int id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {
			Assets_Main updateid = (Assets_Main) sessionHQL.get(Assets_Main.class, id);
			tx.commit();
			return updateid;
		} catch (RuntimeException e) {
			return null;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}	
	}
  
  public ArrayList<ArrayList<String>> GetLast_WO_no_dt(String id,String category_type)
  {
  	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
  	Connection conn = null;
  	String q="";
  	try{	  
  		conn = dataSource.getConnection();			 
  		PreparedStatement stmt=null;
  		q = "select \r\n" + 
  				"COALESCE(wo_no,'') as wo_no ,\r\n" + 
  				"COALESCE(ltrim(TO_CHAR(wo_dt,'dd-MON-yyyy'),'0'),'') as wo_dt \r\n" + 
  				"from tb_it_asset_work_order where p_id = ?  and category = ? order by id desc limit 1";
  			stmt=conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(id));
			stmt.setInt(2, Integer.parseInt(category_type));
			
  			ResultSet rs = stmt.executeQuery();   
  			while (rs.next()) {
  				
  				ArrayList<String> list = new ArrayList<String>();
  				list.add(rs.getString("wo_no"));//0
  				list.add(rs.getString("wo_dt"));//1
  				alist.add(list);
  					  				
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
  	return alist;
  }
 
  public ArrayList<ArrayList<String>> GetComputingAsset_PDF(String p_id,String wo_id)
  {
	  
  	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
  	Connection conn = null;
  	String q="";

  	try{	  
  		conn = dataSource.getConnection();			 
  		PreparedStatement stmt=null;
  			  		
  		q = "select \r\n" + 
  				"COALESCE(w.wksp_sus_no,'') as wksp_sus_no,\r\n" + 
  				"COALESCE(w.wksp_unit_name,'') as unit_name,\r\n" + 
  				"COALESCE(w.wo_no,'') as wo_no, \r\n" + 
  				"COALESCE(ltrim(TO_CHAR(w.wo_dt,'dd-MON-yyyy'),'0'),'') as wo_dt,\r\n" + 
  				"COALESCE(ass.assets_name,'') as assets_name,\r\n" + 
  				"COALESCE(make.make_name,'') as make_name,\r\n" + 
  				"COALESCE(model.model_name,'') as model_name,\r\n" + 
  				"COALESCE(w.machine_number,'') as machine_number,\r\n" + 
  				"COALESCE(ltrim(TO_CHAR(cp.proc_date,'dd-MON-yyyy'),'0'),'')  as proc_date,\r\n" + 
  				"COALESCE(ltrim(TO_CHAR(w.dt_eqpt_reqd_fwd_wksp,'dd-MON-yyyy'),'0'),'')  as dt_eqpt_reqd_fwd_wksp,\r\n" + 
  				"COALESCE(w.defect_obs,'') as defect_obs\r\n" + 
  				"from tb_it_asset_work_order w\r\n" + 
  				"inner join tb_asset_main cp on cp.machine_number = w.machine_number and cp.status = 1 and cp.unserviceable_state in ('2','3') and cp.s_state ='2' \r\n" + 
  				"----inner join tb_orbat_unt_dtl miso on  miso.sus_no = w.wksp_sus_no\r\n" + 
  				"left join tb_mstr_assets ass on ass.id = cp.asset_type and ass.category = 1 \r\n" + 
  				"left join tb_mstr_make make on make.id = cp.make_name and  make.assets_name = cp.asset_type\r\n" + 
  				"left join tb_mstr_model model on model.id = cp.model_name and  model.assets_name = cp.asset_type and model.make_name = cp.make_name\r\n" + 
  				"left join t_domain_value t1 on t1.codevalue = cp.s_state and t1.domainid='SERVICE_CAT'  \r\n" + 
  				"left join t_domain_value t2 on t2.codevalue = cast(cp.unserviceable_state as char) and t2.domainid='UNSERVICEABLE_STATE'   "+
  				"where w.p_id  = ? and w.id  = ? ";
  		
  			stmt=conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(p_id));
			stmt.setInt(2, Integer.parseInt(wo_id));
			
  			ResultSet rs = stmt.executeQuery();     
  			while (rs.next()) {
  				
  				ArrayList<String> list = new ArrayList<String>();
  				list.add(rs.getString("wksp_sus_no"));//0
  				list.add(rs.getString("unit_name"));//1
  				list.add(rs.getString("wo_no"));//2
  				list.add(rs.getString("wo_dt"));//3
  				list.add(rs.getString("assets_name"));//4
  				list.add(rs.getString("make_name"));//5
  				list.add(rs.getString("model_name"));//6
  				list.add(rs.getString("machine_number"));//7
  				list.add(rs.getString("proc_date"));//8
  				list.add(rs.getString("dt_eqpt_reqd_fwd_wksp"));//9
  				list.add(rs.getString("defect_obs"));//10
  				alist.add(list);
  					  				
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
  	return alist;
  }
  
/*  --------------------------------pop up --------------------------------------------        */
  
  public List<Map<String, Object>> Pop_UP_Computing_Asset_History_ReportDataList(int startPage,String pageLength,String Search,String orderColunm,String orderType,String assets_type,String wo_no,String wo_dt,String dt_eqpt_reqd_fwd_wksp,String wksp_unit_name,String defects_obs,HttpSession session) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		if(pageLength.equals("-1")){
			pageLength = "ALL";
		}
		String SearchValue = GenerateQueryWhereClause_SQL_History(Search,assets_type,wo_no,wo_dt,dt_eqpt_reqd_fwd_wksp,wksp_unit_name,defects_obs);

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			q = "select pa.id as pa_id,pa.wo_no,pa.wksp_unit_name,\r\n" + 
					"		 ltrim(TO_CHAR(pa.wo_dt,'DD-MON-YYYY'),'0') as wo_dt,\r\n" + 
					"		 ltrim(TO_CHAR(pa.dt_eqpt_reqd_fwd_wksp,'DD-MON-YYYY'),'0') as dt_eqpt_reqd_fwd_wksp,\r\n" + 
					"		 COALESCE(ass_type.assets_name,'') as assets_name, \r\n" + 
					"		 COUNT(ch.p_id) AS count_p_id ,\r\n" + 
					" 		 COALESCE(ch.defects_obs,'') as defects_obs\r\n" + 
					"from tb_it_computing_wo_ch ch\r\n" + 
					"inner join  tb_it_computing_wo_p pa on pa.id=ch.p_id\r\n" + 
					"inner join tb_asset_main ass_m on ass_m.id=ch.asset_type\r\n" + 
					"inner join tb_mstr_assets ass_type on ass_m.asset_type = ass_type.id " 
					+SearchValue+" GROUP BY pa.id,pa.wo_no,pa.wksp_unit_name,wo_dt,dt_eqpt_reqd_fwd_wksp,assets_name,defects_obs \r\n" + 
							" ORDER BY "+ orderColunm +" "+orderType +" limit " +pageLength+" OFFSET "+startPage + "" ;

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL_History(stmt,Search,assets_type,wo_no,wo_dt,dt_eqpt_reqd_fwd_wksp,wksp_unit_name,defects_obs);

			ResultSet rs = stmt.executeQuery();
			
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
				    columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
                  String enckey ="commonPwdEncKeys";
	                    Cipher c = hex_asciiDao.EncryptionSHA256Algo(session,enckey);
	                    String EncryptedPk = new String(Base64.encodeBase64( c.doFinal(rs.getString("pa_id").toString().getBytes())));
	                    String id = rs.getString("pa_id").toString();
	                    String count_p_id = rs.getString("count_p_id").toString();
	                    String history = "onclick=\"  if (confirm('Are you sure you want to see history?') ){historyData('"+EncryptedPk+"')}else{ return false;}\""; 
	                    String historyButton = "<i class='' " + history + " data-toggle='modal' data-target='#myHistoryModal'>"+ count_p_id +"</i>"; 
               
	                    String print = "onclick=\"  if (confirm('Are you sure you want to Print?') ){Downloaddata('"+id+"')}else{ return false;}\""; 
	                    String printButton = "<i class='action_icons action_download'" + print + " title='Print Work Order'></i>";
	                    String f1 = "";

	                    f1 += printButton;
	                    
	                    String f = "";
                  f += historyButton;
               
                 columns.put("history", f);
                 columns.put("action", f1 );
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

  public long Pop_UP_Computing_Asset_History_List_TotalCount(String Search,String assets_type,String wo_no,String wo_dt,String dt_eqpt_reqd_fwd_wksp,String wksp_unit_name,String defects_obs) {
		String SearchValue = GenerateQueryWhereClause_SQL_History(Search,assets_type,wo_no,wo_dt,dt_eqpt_reqd_fwd_wksp,wksp_unit_name,defects_obs);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			q ="SELECT COUNT(*)\r\n" + 
					" FROM tb_it_computing_wo_ch ch\r\n" + 
						"left join  tb_it_computing_wo_p pa on pa.id=ch.p_id\r\n" + 
						"left join it_asset_peripherals ass_m on ass_m.id=ch.asset_type\r\n" + 
						"left join tb_mstr_assets ass_type on ass_m.assets_type = ass_type.id"+ SearchValue ;
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL_History(stmt,Search,assets_type,wo_no,wo_dt,dt_eqpt_reqd_fwd_wksp,wksp_unit_name,defects_obs);
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

  public String GenerateQueryWhereClause_SQL_History(String Search,String assets_type,String wo_no,String wo_dt,String dt_eqpt_reqd_fwd_wksp,String wksp_unit_name,String defects_obs) {
		String SearchValue = "";
		
		if(!Search.equals("")) { // for Input Filter
			SearchValue =" where ( ";
			SearchValue +=" cast(pa.wo_no as character varying) like ? OR cast(pa.wksp_unit_name as character varying) like ? " + 
					"	OR cast(pa.wksp_unit_name as character varying) like ? OR cast(ch.defects_obs as character varying) like ? OR  upper((TO_CHAR(cast(pa.wo_dt as  date),'DD-MON-YYYY'))) like ?" + 
					"	   OR  upper((TO_CHAR(cast(pa.dt_eqpt_reqd_fwd_wkspas as  date),'DD-MON-YYYY'))) like ?" + 
					"	  cast(ass_type.assets_name as character varying) like ?  )";
		}
		

		
	if (!assets_type.equals("0")) {
		assets_type = assets_type.toUpperCase();
	if (SearchValue.contains("where")) {
			SearchValue += " and assets_name  like ?  ";
			} else {
			SearchValue += " where assets_name like ?  ";
			}
	}


		if (!wo_no.equals("")) {
			wo_no = wo_no.toUpperCase();
			if (SearchValue.contains("where")) {
				SearchValue += " and upper(pa.wo_no ) like ? ";
			} else {
				SearchValue += " where upper(pa.wo_no ) like ? ";
			}
		}
		
		if (!wo_dt.equals("")) {
			if (SearchValue.contains("where")) {
				SearchValue += " and cast(pa.wo_dt as date) = cast( ? as date) ";
			} else {
				SearchValue += " where cast(pa.wo_dt as date) = cast( ? as date) ";
			}
		}
		if (!dt_eqpt_reqd_fwd_wksp.equals("")) {
			if (SearchValue.contains("where")) {
				SearchValue += " and cast(pa.dt_eqpt_reqd_fwd_wksp as date) = cast( ? as date) ";
			} else {
				SearchValue += " where cast(pa.dt_eqpt_reqd_fwd_wksp as date) = cast( ? as date) ";
			}
		}
		if (!wksp_unit_name.equals("")) {
			wksp_unit_name = wksp_unit_name.toUpperCase();
			if (SearchValue.contains("where")) {
				SearchValue += " and upper(pa.wksp_unit_name ) like ? ";
			} else {
				SearchValue += " where upper(pa.wksp_unit_name ) like ? ";
			}
		}
		if (!defects_obs.equals("")) {
			defects_obs = defects_obs.toUpperCase();
			if (SearchValue.contains("where")) {
				SearchValue += " and upper(ch.defects_obs ) like ? ";
			} else {
				SearchValue += " where upper(ch.defects_obs ) like ? ";
			}
		}
		
		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL_History(PreparedStatement stmt, String Search,String assets_type,String wo_no,String wo_dt,String dt_eqpt_reqd_fwd_wksp,String wksp_unit_name,String defects_obs) {
		int flag = 0;
		try {
			
			if (!Search.equals("")) {
				flag += 1;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				flag += 1;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				flag += 1;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				flag += 1;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				flag += 1;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				flag += 1;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
			}
			if(!assets_type.equals("")) {
				flag += 1;
				stmt.setString(flag, assets_type);
			}
			if(!wo_no.equals("")) {
				flag += 1;
				stmt.setString(flag, wo_no.toUpperCase()+"%");
			}
			if(!wo_dt.equals("")) {
				flag += 1;
				stmt.setString(flag, wo_dt.toUpperCase()+"%");
			}
			if(!dt_eqpt_reqd_fwd_wksp.equals("")) {
				flag += 1;
				stmt.setString(flag, dt_eqpt_reqd_fwd_wksp.toUpperCase()+"%");
			}
			if(!wksp_unit_name.equals("")) {
				flag += 1;
				stmt.setString(flag, wksp_unit_name.toUpperCase()+"%");
			}
			if(!defects_obs.equals("")) {
				flag += 1;
				stmt.setString(flag, defects_obs.toUpperCase()+"%");
			}
		} catch (Exception e) {
		}
		
		return stmt;
	}


@Override
public List<Map<String, Object>> pop_up_detail_Computing_Asset_History_RecordDataList(HttpSession session, int p_id)
		throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException,
		InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
	
	
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	Connection conn = null;
	String q = "";
	try {
		conn = dataSource.getConnection();

		
		q = "SELECT pa.wo_no,defects_obs, ltrim(TO_CHAR(ch.proc_date,'DD-MON-YYYY'),'0') as proc_date FROM tb_it_computing_wo_ch ch\r\n" + 
				"LEFT JOIN tb_it_computing_wo_p pa ON ch.p_id = pa.id WHERE ch.p_id = ?  ORDER BY ch.id" ;
		PreparedStatement stmt = conn.prepareStatement(q);
		stmt.setInt(1,p_id);

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
 
public ArrayList<ArrayList<String>> getComputingDetails(String wksp_unit_name,String wo_dt,String dt_eqpt_reqd_fwd_wksp){

	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q = "";
	String qry = " where e.id='114'";
	try {
		conn = dataSource.getConnection();
		PreparedStatement stmt = null;


		q = "select  e.id,e.wksp_unit_name,"
				+ "ltrim(TO_CHAR(e.wo_dt ,'dd-mm-yyyy'),'0') as wo_dt,"
				+ "ltrim(TO_CHAR(e.dt_eqpt_reqd_fwd_wksp ,'dd-mm-yyyy'),'0') as dt_eqpt_reqd_fwd_wksp\r\n" + 
				"from tb_it_computing_wo_p e"+qry;

		stmt = conn.prepareStatement(q);

		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			
			rs.getString("wksp_unit_name");
			rs.getString("wo_dt");
			rs.getString("dt_eqpt_reqd_fwd_wksp");
			

			ArrayList<String> list = new ArrayList<String>();

			list.add(rs.getString("wksp_unit_name"));// 0
			list.add(rs.getString("wo_dt"));//1
			list.add(rs.getString("dt_eqpt_reqd_fwd_wksp"));//2
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
public ArrayList<ArrayList<String>> getComputingDetails(String hid,String wksp_unit_name,String wo_dt,String dt_eqpt_reqd_fwd_wksp){

	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q = "";
	String qry = " where ch.p_id=?";
	try {
		conn = dataSource.getConnection();
		PreparedStatement stmt = null;

		q = "select COALESCE(mk.make_name,'') as make_name, \r\n" + 
				"COALESCE(ass_type.assets_name,'') as assets_name, \r\n" + 
				"COALESCE(ass_m.machine_number,'') as machine_number, \r\n" + 
				"ltrim(TO_CHAR(ass_m.proc_date ,'dd-mm-yyyy'),'0') as proc_date,\r\n" + 
				"'1' as qty ,COALESCE(ch.defects_obs,'') as defects_obs, \r\n" + 
				" COALESCE( ch.remarks,'') as remarks \r\n" + 
				"  from tb_it_computing_wo_ch ch\r\n" + 
				"inner join  tb_it_computing_wo_p p on p.id=ch.p_id\r\n" + 
				"inner join tb_asset_main ass_m on ass_m.id=ch.asset_type\r\n" + 
				"inner join tb_mstr_make mk on ass_m.make_name=mk.id\r\n" + 
				"inner join tb_mstr_assets ass_type on ass_m.asset_type = ass_type.id\r\n" + 
				"inner join tb_it_computing_wo_p po on  po.id=ch.p_id \r\n" + qry;

		
		stmt = conn.prepareStatement(q);

	stmt.setInt(1, Integer.parseInt(hid));
	int i = 0;
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			i++;

			
			ArrayList<String> list = new ArrayList<String>();
			list.add(String.valueOf(i));// 0
			list.add(rs.getString("assets_name"));//1
			list.add(rs.getString("make_name"));//2
			list.add(rs.getString("machine_number"));//3
			list.add(rs.getString("qty"));//4
			list.add(rs.getString("defects_obs"));//5
			list.add(rs.getString("proc_date"));//6
            list.add(rs.getString("remarks"));//7
			
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



public ArrayList<ArrayList<String>> getComputingDetails1(String hid,String wksp_unit_name,String wo_dt,String dt_eqpt_reqd_fwd_wksp){

	ArrayList<ArrayList<String>> alist1 = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q = "";
	String qry = " where ch.p_id=?";
	try {
		conn = dataSource.getConnection();
		PreparedStatement stmt = null;

		
		q="select ch.p_id,\r\n"
				+ "COALESCE(po.wksp_unit_name,'') as wksp_unit_name,\r\n"
				+ "COALESCE(po.wo_no,'') as wo_no,\r\n"
				+ "COALESCE(ltrim(TO_CHAR(po.wo_dt ,'dd-mm-yyyy'),'0'),'') as wo_dt,\r\n"
				+ "COALESCE(ltrim(TO_CHAR(po.dt_eqpt_reqd_fwd_wksp ,'dd-mm-yyyy'),'0'),'') as dt_eqpt_reqd_fwd_wksp \r\n"
				+"from tb_it_computing_wo_ch ch \r\n" + 
				"inner join tb_it_computing_wo_p po on  po.id=ch.p_id"+qry;

		stmt = conn.prepareStatement(q);
		stmt.setInt(1, Integer.parseInt(hid));

		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {

			ArrayList<String> list = new ArrayList<String>();

			list.add(rs.getString("wksp_unit_name"));// 0
			list.add(rs.getString("wo_dt"));//1
			list.add(rs.getString("dt_eqpt_reqd_fwd_wksp"));//2
			list.add(rs.getString("wo_no"));//3
			alist1.add(list);


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
	return alist1;
}

}