package com.dao.itasset.Report;

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

public class UnserviceableDaoImpl implements UnserviceableDao{
	
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	
	public List<Map<String, Object>> getallIndiaHoldingList(int startPage,int pageLength,String Search,String orderColunm,String orderType,String asset_type,
			String b_head,String b_code,String a_type,HttpSession sessionUserId,
			String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no) throws SQLException
	{
		
		
    	String SearchValue = GenerateQueryWhereClause_SQL4(Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,asset_type,b_head,b_code,a_type);
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
			
			String group_str=" group by fv.unit_name ,fvm.unit_name ,\r\n" + 
					"	div.unit_name ,\r\n" + 
					"	bde.unit_name ,\r\n" + 
					"	orb.unit_name,sub.assest_id,sub.asset_cat,sub.id,sub.sus_no,sub.assets_name,sub.make_name,sub.model_name,sub.model_number,sub.machine_number,sub.b_head,sub.budget_code,sub.budget_id,sub.unservice_state,sub.label,sub.unsv_from_dt,sub.unsv_to_dt,sub.u_tdt,sub.u_fdt ";
			String qry1="select distinct 'Computing' as asset_cat,a.id,a.sus_no,ma.assets_name,ma.id as assest_id,mm.make_name,m.model_name,a.model_number,a.machine_number,a.b_head,bb.budget_code,bb.id as budget_id,b.unservice_state,\r\n" + 
					"t.label,ltrim(TO_CHAR(b.unsv_from_dt,'DD-MON-YYYY'),'0' ) as unsv_from_dt,b.unsv_from_dt as u_fdt,b.unsv_to_dt as u_tdt, ltrim(TO_CHAR(b.unsv_to_dt ,'DD-MON-YYYY'),'0' ) as unsv_to_dt   \r\n" + 
					"from tb_asset_main a\r\n" + 
					"inner join tb_assets_child b \r\n" + 
					"on a.id=b.p_id \r\n" + 
					"inner join  tb_mstr_assets ma on  ma.id= a.asset_type\r\n" + 
					"inner join  tb_mstr_make mm on  mm.id=a.make_name \r\n" + 
					"inner join  tb_mstr_model m on  m.id=a.model_name\r\n" + 
					"inner join  tb_mstr_budget bb on  bb.id::text=a.b_code\r\n" + 
					"inner join  t_domain_value t on cast(t.codevalue as character varying)=cast(b.unservice_state as character varying) and domainid='UNSERVICEABLE_STATE'\r\n" + 
					"where\r\n" + 
					"a.status not in ('3','0') and b.service_state=2 ";
			String qry2=	"					 select  distinct 'Peripheral'as asset_cat,ap.id,ap.sus_no,pt.assets_name,pt.id as assest_id,mm.make_name,m.model_name,ap.model_no as model_number,ap.machine_no as machine_number, ap.b_head,bb.budget_code,bb.id as budget_id,b.unservice_state,\r\n" + 
					"										 t.label,ltrim(TO_CHAR(b.unsv_from_dt,'DD-MON-YYYY'),'0' ) as unsv_from_dt,b.unsv_from_dt as u_fdt,b.unsv_to_dt as u_tdt, ltrim(TO_CHAR(b.unsv_to_dt ,'DD-MON-YYYY'),'0' ) as unsv_to_dt   \r\n" + 
					"										 from it_asset_peripherals ap \r\n" + 
					"										 inner join tb_peripheral_child b \r\n" + 
					"										on ap.id=b.p_id\r\n" + 
					"										inner join tb_mstr_assets pt on pt.id=ap.assets_type \r\n" + 
					"										inner join  tb_mstr_make mm on  mm.id=ap.make_name  \r\n" + 
					"										inner join  tb_mstr_model m on  m.id=ap.model_name\r\n" + 
					"										 inner join  tb_mstr_budget bb on  bb.id::text=ap.b_code\r\n" + 
					"					 					inner join  t_domain_value t on cast(t.codevalue as character varying)=cast(b.unservice_state as character varying) and domainid='UNSERVICEABLE_STATE'\r\n" + 
					"										where\r\n" + 
					"										ap.status not in ('3','0') and b.service_state=2";
			
			 if(asset_type.equals("1")) {
				q= "select fv.unit_name as command,fvm.unit_name as corps,\r\n" + 
						"	div.unit_name as div,\r\n" + 
						"	bde.unit_name as bde,\r\n" + 
						"	orb.unit_name,count(sub.asset_cat) as total,sub.*,case when sub.u_fdt is not null and sub.u_tdt is not null then  (EXTRACT(epoch from age(sub.u_tdt,sub.u_fdt )) / 86400)::int\r\n" + 
						"else 0\r\n" + 
						"end as days "
						+ " from (" +qry1 +") sub "
						+ "inner join tb_orbat_unt_dtl orb on orb.sus_no = sub.sus_no and orb.status_sus_no='Active'\r\n" + 
						"left join all_fmn_view fv  on orb.sus_no = sub.sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n" + 
						"left join all_fmn_view fvm  on orb.sus_no = sub.sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps' \r\n" + 
						"left join all_fmn_view div  on orb.sus_no = sub.sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \r\n" + 
						"left join all_fmn_view bde  on orb.sus_no = sub.sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade' 	"
						+  SearchValue + group_str +
						" ORDER BY sub.id asc limit " +pageL+" OFFSET "+startPage ;
			}
			 else if(asset_type.equals("2")) {
				 q= "select fv.unit_name as command,fvm.unit_name as corps,\r\n" + 
				 		"	div.unit_name as div,\r\n" + 
				 		"	bde.unit_name as bde,\r\n" + 
				 		"	orb.unit_name,count(sub.asset_cat) as total,sub.*,case when sub.u_fdt is not null and sub.u_tdt is not null then  (EXTRACT(epoch from age(sub.u_tdt,sub.u_fdt )) / 86400)::int\r\n" + 
				 		"else 0\r\n" + 
				 		"end as days "
				 		+ " from (" +qry2 +") sub "
				 		+ "inner join tb_orbat_unt_dtl orb on orb.sus_no = sub.sus_no and orb.status_sus_no='Active'\r\n" + 
						"left join all_fmn_view fv  on orb.sus_no = sub.sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n" + 
						"left join all_fmn_view fvm  on orb.sus_no = sub.sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps' \r\n" + 
						"left join all_fmn_view div  on orb.sus_no = sub.sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \r\n" + 
						"left join all_fmn_view bde  on orb.sus_no = sub.sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade' 	"
						 + SearchValue +group_str+
							" ORDER BY sub.id asc limit " +pageL+" OFFSET "+startPage ;
				}
			 else {
				 q= "select fv.unit_name as command,fvm.unit_name as corps,\r\n" + 
				 		"	div.unit_name as div,\r\n" + 
				 		"	bde.unit_name as bde,\r\n" + 
				 		"	orb.unit_name,count(sub.asset_cat) as total,sub.*,case when sub.u_fdt is not null and sub.u_tdt is not null then  (EXTRACT(epoch from age(sub.u_tdt,sub.u_fdt )) / 86400)::int\r\n" + 
				 		"else 0\r\n" + 
				 		"end as days "
				 		+ " from (" +qry1+" union all "+ qry2 +") sub "
				 		+ "inner join tb_orbat_unt_dtl orb on orb.sus_no = sub.sus_no and orb.status_sus_no='Active'\r\n" + 
						"left join all_fmn_view fv  on orb.sus_no = sub.sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n" + 
						"left join all_fmn_view fvm  on orb.sus_no = sub.sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps' \r\n" + 
						"left join all_fmn_view div  on orb.sus_no = sub.sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \r\n" + 
						"left join all_fmn_view bde  on orb.sus_no = sub.sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade' 	"
						 + SearchValue +group_str+
							" ORDER BY sub.id asc limit " +pageL+" OFFSET "+startPage ;
				}
			
			
			
			//					" ORDER BY "+orderColunm+" "+orderType +" limit " +pageL+" OFFSET "+startPage ;
			stmt=conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL4(stmt,Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,asset_type,b_head,b_code,a_type);
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
			//throw new RuntimeException(e);
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
	
	 public long getallIndiaHoldingCountList(String Search,String orderColunm,String orderType,String asset_type,String b_head,String b_code,String a_type,HttpSession sessionUserId,
			 String cont_comd,String cont_corps,
				String cont_div,String cont_bde,String unit_name,String sus_no)throws SQLException {
		 String SearchValue = GenerateQueryWhereClause_SQL4(Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,asset_type,b_head,b_code,a_type);
			int total = 0;
			String q = null;
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				
				
				String group_str=" group by fv.unit_name ,fvm.unit_name ,\r\n" + 
						"	div.unit_name ,\r\n" + 
						"	bde.unit_name ,\r\n" + 
						"	orb.unit_name,sub.assest_id,sub.asset_cat,sub.id,sub.sus_no,sub.assets_name,sub.make_name,sub.model_name,sub.model_number,sub.machine_number,sub.b_head,sub.budget_code,sub.budget_id,sub.unservice_state,sub.label,sub.unsv_from_dt,sub.unsv_to_dt ";
				String qry1="select distinct 'Computing' as asset_cat,a.id,a.sus_no,ma.assets_name,ma.id as assest_id,mm.make_name,m.model_name,a.model_number,a.machine_number,a.b_head,bb.budget_code,bb.id as budget_id,b.unservice_state,\r\n" + 
						"t.label,ltrim(TO_CHAR(b.unsv_from_dt,'DD-MON-YYYY'),'0' ) as unsv_from_dt, ltrim(TO_CHAR(b.unsv_to_dt ,'DD-MON-YYYY'),'0' ) as unsv_to_dt  \r\n" + 
						"from tb_asset_main a\r\n" + 
						"inner join tb_assets_child b \r\n" + 
						"on a.id=b.p_id \r\n" + 
						"inner join  tb_mstr_assets ma on  ma.id= a.asset_type\r\n" + 
						"inner join  tb_mstr_make mm on  mm.id=a.make_name \r\n" + 
						"inner join  tb_mstr_model m on  m.id=a.model_name\r\n" + 
						"inner join  tb_mstr_budget bb on  bb.id::text=a.b_code\r\n" + 
						"inner join  t_domain_value t on cast(t.codevalue as character varying)=cast(b.unservice_state as character varying) and domainid='UNSERVICEABLE_STATE'\r\n" + 
						"where\r\n" + 
						"a.status not in ('3','0') and b.service_state=2";
				String qry2=	"select  distinct 'Peripheral'as asset_cat,ap.id,ap.sus_no,pt.assets_name,pt.id as assest_id,mm.make_name,m.model_name,ap.model_no as model_number,ap.machine_no as machine_number, ap.b_head,bb.budget_code,bb.id as budget_id,b.unservice_state,\r\n" + 
						"										 t.label,ltrim(TO_CHAR(b.unsv_from_dt,'DD-MON-YYYY'),'0' ) as unsv_from_dt, ltrim(TO_CHAR(b.unsv_to_dt ,'DD-MON-YYYY'),'0' ) as unsv_to_dt  \r\n" + 
						"										 from it_asset_peripherals ap \r\n" + 
						"										 inner join tb_peripheral_child b \r\n" + 
						"										on ap.id=b.p_id\r\n" + 
						"										inner join tb_mstr_assets pt on pt.id=ap.assets_type \r\n" + 
						"										inner join  tb_mstr_make mm on  mm.id=ap.make_name  \r\n" + 
						"										inner join  tb_mstr_model m on  m.id=ap.model_name\r\n" + 
						"										 inner join  tb_mstr_budget bb on  bb.id::text=ap.b_code\r\n" + 
						"					 					inner join  t_domain_value t on cast(t.codevalue as character varying)=cast(b.unservice_state as character varying) and domainid='UNSERVICEABLE_STATE'\r\n" + 
						"										where\r\n" + 
						"										ap.status not in ('3','0') and b.service_state=2";
				
//				
				
				 if(asset_type.equals("1")) {
					q= "select fv.unit_name as command,fvm.unit_name as corps,\r\n" + 
							"	div.unit_name as div,\r\n" + 
							"	bde.unit_name as bde,\r\n" + 
							"	orb.unit_name,count(app.*) from (select count(sub.asset_cat) as total,sub.* "
							+ " from (" +qry1 +") sub "
							+ "inner join tb_orbat_unt_dtl orb on orb.sus_no = sub.sus_no and orb.status_sus_no='Active'\r\n" + 
							"left join all_fmn_view fv  on orb.sus_no = sub.sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n" + 
							"left join all_fmn_view fvm  on orb.sus_no = sub.sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps' \r\n" + 
							"left join all_fmn_view div  on orb.sus_no = sub.sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \r\n" + 
							"left join all_fmn_view bde  on orb.sus_no = sub.sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade' 	"
							+  SearchValue + group_str +
							" ORDER BY sub.id asc ) app " ;
				}
				 else if(asset_type.equals("2")) {
					 q= "select fv.unit_name as command,fvm.unit_name as corps,\r\n" + 
					 		"	div.unit_name as div,\r\n" + 
					 		"	bde.unit_name as bde,\r\n" + 
					 		"	orb.unit_name,count(app.*) from ( select count(sub.asset_cat) as total,sub.* "
					 		+ " from (" +qry2 +") sub "
					 		+ "inner join tb_orbat_unt_dtl orb on orb.sus_no = sub.sus_no and orb.status_sus_no='Active'\r\n" + 
							"left join all_fmn_view fv  on orb.sus_no = sub.sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n" + 
							"left join all_fmn_view fvm  on orb.sus_no = sub.sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps' \r\n" + 
							"left join all_fmn_view div  on orb.sus_no = sub.sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \r\n" + 
							"left join all_fmn_view bde  on orb.sus_no = sub.sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade' 	"
							 + SearchValue +group_str+
								" ORDER BY sub.id asc ) app";
					}
				 else {
					 q= "select fv.unit_name as command,fvm.unit_name as corps,\r\n" + 
					 		"	div.unit_name as div,\r\n" + 
					 		"	bde.unit_name as bde,\r\n" + 
					 		"	orb.unit_name,count(app.*) from ( select count(sub.asset_cat) as total,sub.* "
					 		+ " from (" +qry1+" union all "+ qry2 +") sub "
					 		+ "inner join tb_orbat_unt_dtl orb on orb.sus_no = sub.sus_no and orb.status_sus_no='Active'\r\n" + 
							"left join all_fmn_view fv  on orb.sus_no = sub.sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n" + 
							"left join all_fmn_view fvm  on orb.sus_no = sub.sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps' \r\n" + 
							"left join all_fmn_view div  on orb.sus_no = sub.sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \r\n" + 
							"left join all_fmn_view bde  on orb.sus_no = sub.sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade' 	"
							 + SearchValue +group_str+
								" ORDER BY sub.id asc ) app";
					}
				


				PreparedStatement stmt = conn.prepareStatement(q);
				
				stmt = setQueryWhereClause_SQL4(stmt,Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,asset_type,b_head,b_code,a_type);
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
	 public String GenerateQueryWhereClause_SQL4(String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String asset_type,
				String b_head,String b_code,String a_type) {
		 String SearchValue ="";
			if (!Search.equals("") && !Search.equals(null) && Search != null && Search != "")  { // for Input Filter
				SearchValue += " where  ( ";
				SearchValue += " cast(sub.assets_name as character varying) like ? or "
								+ "upper(sub.asset_cat) like ? or "
								+ "cast(sub.model_name as character varying) like ? or "
								+ "cast(sub.make_name as character varying) like ? or "
								+ " upper(sub.model_number) like ? or upper(sub.machine_number) like ? or "
								+"sub.b_head like ? or "
								+"cast(sub.budget_code as character varying) like ? or "
								+ "upper(sub.label) like ? or "
								 + "sub.unsv_from_dt  like ? or "
								 + " sub.unsv_to_dt  like ? )";
				
			}
			
			if(!sus_no.equals("")){
	  			if (SearchValue.contains("where")) {
	  				SearchValue +=" and lower(orb.sus_no) = ?";
				} else {
					SearchValue += " where lower(orb.sus_no) = ? ";
				}
	  			
	    	}
	    	if(!unit_name.equals("0") && !unit_name.equals("")){
	    		if (SearchValue.contains("where")) {
	    			SearchValue +=" and lower(orb.unit_name) = ? ";
				} else {
					SearchValue += " where lower(orb.unit_name) = ? ";
				}
	    	} 
	    	
	    	if(!cont_bde.equals("0") && !cont_bde.equals("")){
	    		if (SearchValue.contains("where")) {
	    			SearchValue +=" and orb.form_code_control = ? ";
				} else {
					SearchValue += " where orb.form_code_control = ? ";
				}
	    	}else {
	    		if(!cont_div.equals("0") && !cont_div.equals("")){
	    			if (SearchValue.contains("where")) {
	        			SearchValue +=" and orb.form_code_control like ? ";
	    			} else {
	    				SearchValue += " where orb.form_code_control like ? ";
	    			}
		    	}else {
		    		if(!cont_corps.equals("0") && !cont_corps.equals("")){
		    			if (SearchValue.contains("where")) {
		        			SearchValue +=" and orb.form_code_control like ? ";
		    			} else {
		    				SearchValue += " where orb.form_code_control like ? ";
		    			}
			    	}else {
			    		if(!cont_comd.equals("-1") && !cont_comd.equals("")){
			    			if (SearchValue.contains("where")) {
			    				SearchValue +=" and orb.form_code_control like ? ";
			    			} else {
			    				SearchValue += " where orb.form_code_control like ? ";
			    			}
				    	}
			    	}
			    }
		    }
			
			
			if(!a_type.equals("0") && !a_type.equals("")) {
				if(SearchValue.contains("where")) {
					SearchValue +=" and sub.assest_id = ? ";
				}else {
					SearchValue +=" where sub.assest_id = ? ";
				}
			}	
			if(!b_head.equals("0") && !b_head.equals(""))  {
				if(SearchValue.contains("where")) {
					SearchValue +=" and sub.b_head = ? ";
				}else {
					SearchValue +=" where sub.b_head = ? ";
				}
			}	
			if(!b_code.equals("0") && !b_code.equals("")) {
				if(SearchValue.contains("where")) {
					SearchValue +=" and sub.budget_id = ? ";
				}else {
					SearchValue +=" where sub.budget_id = ? ";
				}
			}	
			
	

			return SearchValue;
		}
	 
	 public PreparedStatement setQueryWhereClause_SQL4(PreparedStatement stmt,String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String asset_type,
				String b_head,String b_code,String a_type) {
			
			int flag = 0;
			
			try {
				if (!Search.equals("") && !Search.equals(null) && Search != null && Search != "")  {
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
				
				if(!sus_no.equals("")) {
					flag += 1;
					stmt.setString(flag, sus_no.toLowerCase());
				}
				if(!unit_name.equals("")) {
					flag += 1;
					stmt.setString(flag, unit_name.toLowerCase());
				}
				if(!cont_bde.equals("0") && !cont_bde.equals("")){
					flag += 1;
					stmt.setString(flag, cont_bde);
		    	}else {
		    		if(!cont_div.equals("0") && !cont_div.equals("")){
		    			flag += 1;
	 					stmt.setString(flag, cont_div+"%");
			    	}else {
			    		if(!cont_corps.equals("0") && !cont_corps.equals("")){
			    			flag += 1;
		 					stmt.setString(flag, cont_corps+"%");
				    	}else {
				    		if(!cont_comd.equals("-1") && !cont_comd.equals("")){
				    			flag += 1;
			 					stmt.setString(flag, cont_comd+"%");
					    	}
				    	}
				    }
			    }

				if(!a_type.equals("0") && !a_type.equals(""))
				{
					flag += 1;
					stmt.setInt(flag, Integer.parseInt(a_type));		
				}
				
				if (!b_head.equals("0") && !b_head.equals("")) {
					flag += 1;
					stmt.setString(flag, b_head);
				}
				
				if (!b_code.equals("0") && !b_code.equals("")) {
					flag += 1;
					stmt.setInt(flag, Integer.parseInt(b_code));
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return stmt;
		}
	
	
	

	
	public ArrayList<ArrayList<String>> pdf_all_india_holding_ReportDataList(String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String asset_type,String a_type,String b_head,String b_code) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String SearchValue = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			if(!sus_no.equals("")){
	  			if (SearchValue.contains("where")) {
	  				SearchValue +=" and lower(orb.sus_no) = ?";
				} else {
					SearchValue += " where lower(orb.sus_no) = ? ";
				}
	  			
	    	}
	    	if(!unit_name.equals("0") && !unit_name.equals("")){
	    		if (SearchValue.contains("where")) {
	    			SearchValue +=" and lower(orb.unit_name) = ? ";
				} else {
					SearchValue += " where lower(orb.unit_name) = ? ";
				}
	    	} 
	    	
	    	if(!cont_bde.equals("0") && !cont_bde.equals("")){
	    		if (SearchValue.contains("where")) {
	    			SearchValue +=" and orb.form_code_control = ? ";
				} else {
					SearchValue += " where orb.form_code_control = ? ";
				}
	    	}else {
	    		if(!cont_div.equals("0") && !cont_div.equals("")){
	    			if (SearchValue.contains("where")) {
	        			SearchValue +=" and orb.form_code_control like ? ";
	    			} else {
	    				SearchValue += " where orb.form_code_control like ? ";
	    			}
		    	}else {
		    		if(!cont_corps.equals("0") && !cont_corps.equals("")){
		    			if (SearchValue.contains("where")) {
		        			SearchValue +=" and orb.form_code_control like ? ";
		    			} else {
		    				SearchValue += " where orb.form_code_control like ? ";
		    			}
			    	}else {
			    		if(!cont_comd.equals("-1") && !cont_comd.equals("")){
			    			if (SearchValue.contains("where")) {
			    				SearchValue +=" and orb.form_code_control like ? ";
			    			} else {
			    				SearchValue += " where orb.form_code_control like ? ";
			    			}
				    	}
			    	}
			    }
		    }
			if(!a_type.equals("0") && !a_type.equals("")) {
				if(SearchValue.contains("where")) {
					SearchValue +=" and sub.assest_id = ? ";
				}else {
					SearchValue +=" where sub.assest_id = ? ";
				}
			}	
			if(!b_head.equals("0") && !b_head.equals(""))  {
				if(SearchValue.contains("where")) {
					SearchValue +=" and sub.b_head = ? ";
				}else {
					SearchValue +=" where sub.b_head = ? ";
				}
			}	
			if(!b_code.equals("0") && !b_code.equals("")) {
				if(SearchValue.contains("where")) {
					SearchValue +=" and sub.budget_id = ? ";
				}else {
					SearchValue +=" where sub.budget_id = ? ";
				}
			}	
			
			String group_str=" group by fv.unit_name ,fvm.unit_name ,\r\n" + 
					"	div.unit_name ,\r\n" + 
					"	bde.unit_name ,\r\n" + 
					"	orb.unit_name,sub.assest_id,sub.asset_cat,sub.id,sub.sus_no,sub.assets_name,sub.make_name,sub.model_name,sub.model_number,sub.machine_number,sub.b_head,sub.budget_code,sub.budget_id,sub.unservice_state,sub.label,sub.unsv_from_dt,sub.unsv_to_dt ";
			String qry1="select distinct 'Computing' as asset_cat,a.id,a.sus_no,ma.assets_name,ma.id as assest_id,mm.make_name,m.model_name,a.model_number,a.machine_number,a.b_head,bb.budget_code,bb.id as budget_id,b.unservice_state,\r\n" + 
					"t.label,ltrim(TO_CHAR(b.unsv_from_dt,'DD-MON-YYYY'),'0' ) as unsv_from_dt, ltrim(TO_CHAR(b.unsv_to_dt ,'DD-MON-YYYY'),'0' ) as unsv_to_dt  \r\n" + 
					"from tb_asset_main a\r\n" + 
					"inner join tb_assets_child b \r\n" + 
					"on a.id=b.p_id \r\n" + 
					"inner join  tb_mstr_assets ma on  ma.id= a.asset_type\r\n" + 
					"inner join  tb_mstr_make mm on  mm.id=a.make_name \r\n" + 
					"inner join  tb_mstr_model m on  m.id=a.model_name\r\n" + 
					"inner join  tb_mstr_budget bb on  bb.id::text=a.b_code\r\n" + 
					"inner join  t_domain_value t on cast(t.codevalue as character varying)=cast(b.unservice_state as character varying) and domainid='UNSERVICEABLE_STATE'\r\n" + 
					"where\r\n" + 
					"a.status not in ('3','0') and b.service_state=2 ";
			String qry2=	" select  distinct 'Peripheral'as asset_cat,ap.id,ap.sus_no,pt.assets_name,pt.id as assest_id,mm.make_name,m.model_name,ap.model_no as model_number,ap.machine_no as machine_number, ap.b_head,bb.budget_code,bb.id as budget_id,b.unservice_state,\r\n" + 
					"										 t.label,ltrim(TO_CHAR(b.unsv_from_dt,'DD-MON-YYYY'),'0' ) as unsv_from_dt, ltrim(TO_CHAR(b.unsv_to_dt ,'DD-MON-YYYY'),'0' ) as unsv_to_dt  \r\n" + 
					"										 from it_asset_peripherals ap \r\n" + 
					"										 inner join tb_peripheral_child b \r\n" + 
					"										on ap.id=b.p_id\r\n" + 
					"										inner join tb_mstr_assets pt on pt.id=ap.assets_type \r\n" + 
					"										inner join  tb_mstr_make mm on  mm.id=ap.make_name  \r\n" + 
					"										inner join  tb_mstr_model m on  m.id=ap.model_name\r\n" + 
					"										 inner join  tb_mstr_budget bb on  bb.id::text=ap.b_code\r\n" + 
					"					 					inner join  t_domain_value t on cast(t.codevalue as character varying)=cast(b.unservice_state as character varying) and domainid='UNSERVICEABLE_STATE'\r\n" + 
					"										where\r\n" + 
					"										ap.status not in ('3','0') and b.service_state=2";
			
			
			 if(asset_type.equals("1")) {
				q= "select fv.unit_name as command,fvm.unit_name as corps,\r\n" + 
						"	div.unit_name as div,\r\n" + 
						"	bde.unit_name as bde,\r\n" + 
						"	orb.unit_name,count(sub.asset_cat) as total,sub.* "
						+ " from (" +qry1 +") sub "
						+ "inner join tb_orbat_unt_dtl orb on orb.sus_no = sub.sus_no and orb.status_sus_no='Active'\r\n" + 
						"left join all_fmn_view fv  on orb.sus_no = sub.sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n" + 
						"left join all_fmn_view fvm  on orb.sus_no = sub.sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps' \r\n" + 
						"left join all_fmn_view div  on orb.sus_no = sub.sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \r\n" + 
						"left join all_fmn_view bde  on orb.sus_no = sub.sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'"
						+  SearchValue + group_str +
						" ORDER BY sub.id asc " ;
			}
			 else if(asset_type.equals("2")) {
				 q= "select fv.unit_name as command,fvm.unit_name as corps,\r\n" + 
				 		"	div.unit_name as div,\r\n" + 
				 		"	bde.unit_name as bde,\r\n" + 
				 		"	orb.unit_name,count(sub.asset_cat) as total,sub.* "
				 		+ " from (" +qry2 +") sub "
				 		+ "inner join tb_orbat_unt_dtl orb on orb.sus_no = sub.sus_no and orb.status_sus_no='Active'\r\n" + 
						"left join all_fmn_view fv  on orb.sus_no = sub.sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n" + 
						"left join all_fmn_view fvm  on orb.sus_no = sub.sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps' \r\n" + 
						"left join all_fmn_view div  on orb.sus_no = sub.sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \r\n" + 
						"left join all_fmn_view bde  on orb.sus_no = sub.sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'"
						 + SearchValue +group_str+
							" ORDER BY sub.id  asc " ;
				}
			 else {
				 q= "select fv.unit_name as command,fvm.unit_name as corps,\r\n" + 
				 		"	div.unit_name as div,\r\n" + 
				 		"	bde.unit_name as bde,\r\n" + 
				 		"	orb.unit_name,count(sub.asset_cat) as total,sub.* "
				 		+ " from (" +qry1+" union all "+ qry2 +") sub "
				 		+ "inner join tb_orbat_unt_dtl orb on orb.sus_no = sub.sus_no and orb.status_sus_no='Active'\r\n" + 
						"left join all_fmn_view fv  on orb.sus_no = sub.sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n" + 
						"left join all_fmn_view fvm  on orb.sus_no = sub.sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps' \r\n" + 
						"left join all_fmn_view div  on orb.sus_no = sub.sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \r\n" + 
						"left join all_fmn_view bde  on orb.sus_no = sub.sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'"
						 + SearchValue +group_str+
							" ORDER BY sub.id asc " ;
				}

			stmt = conn.prepareStatement(q);
			int flag =0;
			if(!sus_no.equals("")) {
				flag += 1;
				stmt.setString(flag, sus_no.toLowerCase());
			}
			if(!unit_name.equals("")) {
				flag += 1;
				stmt.setString(flag, unit_name.toLowerCase());
			}
			if(!cont_bde.equals("0") && !cont_bde.equals("")){
				flag += 1;
				stmt.setString(flag, cont_bde);
	    	}else {
	    		if(!cont_div.equals("0") && !cont_div.equals("")){
	    			flag += 1;
 					stmt.setString(flag, cont_div+"%");
		    	}else {
		    		if(!cont_corps.equals("0") && !cont_corps.equals("")){
		    			flag += 1;
	 					stmt.setString(flag, cont_corps+"%");
			    	}else {
			    		if(!cont_comd.equals("-1") && !cont_comd.equals("")){
			    			flag += 1;
		 					stmt.setString(flag, cont_comd+"%");
				    	}
			    	}
			    }
		    }
			
			
			if(!a_type.equals("0") && !a_type.equals(""))
			{
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(a_type));		
			}
			
			if (!b_head.equals("0") && !b_head.equals("")) {
				flag += 1;
				stmt.setString(flag, b_head);
			}
			
			if (!b_code.equals("0") && !b_code.equals("")) {
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(b_code));
			}
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("command"));// 8
				list.add(rs.getString("corps"));// 9
				list.add(rs.getString("div"));// 10
				list.add(rs.getString("bde"));// 11
				list.add(rs.getString("unit_name"));// 12
				list.add(rs.getString("sus_no"));// 12
				list.add(rs.getString("asset_cat"));// 0
				list.add(rs.getString("assets_name"));// 1
				list.add(rs.getString("make_name"));// 2
				list.add(rs.getString("model_name"));// 3
				list.add(rs.getString("model_number"));// 4
				list.add(rs.getString("machine_number"));// 5
				list.add(rs.getString("b_head"));// 5
				list.add(rs.getString("budget_code"));// 5
				list.add(rs.getString("label"));// 5
				list.add(rs.getString("unsv_from_dt"));// 5
				list.add(rs.getString("unsv_to_dt"));// 5
				
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

}
