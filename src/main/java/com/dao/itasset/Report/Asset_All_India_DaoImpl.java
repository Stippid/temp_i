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


public class Asset_All_India_DaoImpl implements Asset_All_India_Dao {
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	public List<Map<String, Object>> getallIndiaHoldingList(int startPage,int pageLength,String Search,String orderColunm,String orderType,String asset_type,
			String b_head,String b_code,String a_type,String line_dte,HttpSession sessionUserId,
			String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String s_state) throws SQLException
		{
			
		
		
	    	String  username = sessionUserId.getAttribute("username").toString();
			
	    	
	    	String SearchValue = GenerateQueryWhereClause_SQL4(Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,asset_type,b_head,b_code,a_type,line_dte,sessionUserId,s_state);
	    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();		
		Connection conn = null;
		String linesus = "";
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
				
//				if(username.equals("hq")){
//					if(!line_dte.equals("0") && !line_dte.equals("")) {
//					 linesus = " where line_dte_sus = ? ";
//					}
//				}
				if(!line_dte.equals("0") && !line_dte.equals("")) {
					 linesus = " and  sus_no = ? ";
					}
				
				
				
				String group_str=" group by fv.unit_name ,fvm.unit_name ,arm.arm_desc, \r\n" + 
						"	div.unit_name ,\r\n" + 
						"	bde.unit_name ,\r\n" + 
						"	orb.unit_name,sub.assest_id,sub.asset_cat,sub.id,sub.sus_no,sub.assets_name,sub.make_name,sub.model_name,sub.model_number,sub.machine_number,sub.b_head,sub.budget_code,sub.budget_id,sub.s_state ";
				String qry1="select distinct 'Computing' as asset_cat,am.id,am.sus_no,ma.assets_name,ma.id as assest_id,mm.make_name,m.model_name,am.model_number,am.machine_number,am.b_head,b.budget_code,b.id as budget_id,s_state from tb_asset_main am\r\n" + 
						"					inner join  tb_mstr_assets ma on  ma.id=am.asset_type  \r\n" + 
						"					inner join  tb_mstr_make mm on  mm.id=am.make_name  \r\n" + 
						"					inner join  tb_mstr_model m on  m.id=am.model_name  \r\n" + 
						" inner join  tb_mstr_budget b on  b.id::text=am.b_code  \r\n" +
						"					 where am.id!=0 and am.status=1 ";
				String qry2=	"					 select  distinct 'Peripheral'as asset_cat,ap.id,ap.sus_no,pt.assets_name,pt.id as assest_id,mm.make_name,m.model_name,ap.model_no as model_number,ap.machine_no as machine_number, ap.b_head,b.budget_code,b.id as budget_id \r\n" + 
						"					,s_state from it_asset_peripherals ap \r\n" + 
						"					inner join tb_mstr_assets pt on pt.id=ap.assets_type \r\n" + 
						"					inner join  tb_mstr_make mm on  mm.id=ap.make_name \r\n" + 
						"					inner join  tb_mstr_model m on  m.id=ap.model_name \r\n" +
						" inner join  tb_mstr_budget b on  b.id::text=ap.b_code \r\n " +
						"					where ap.id!=0 and ap.status=1 ";
				
				 if(asset_type.equals("1")) {
					q= "select fv.unit_name as command,fvm.unit_name as corps,\r\n" + 
							"	div.unit_name as div,\r\n" + 
							"	bde.unit_name as bde,\r\n" + 
							"	orb.unit_name,count(sub.asset_cat) as total,arm.arm_desc,sub.* "
							+ " from (" +qry1 +") sub "
								  + "inner join tb_orbat_unt_dtl orb on orb.sus_no = sub.sus_no and orb.status_sus_no='Active' \r\n"
								  + "inner join TB_ORBAT_LINE_DTE arm on arm.arm_code=orb.arm_code \r\n" + 
									"left join all_fmn_view fv  on orb.sus_no = sub.sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n" + 
									"left join all_fmn_view fvm  on orb.sus_no = sub.sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps' \r\n" + 
									"left join all_fmn_view div  on orb.sus_no = sub.sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \r\n" + 
									"left join all_fmn_view bde  on orb.sus_no = sub.sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade' 	"
									+ "where arm.line_dte_sus in ( \r\n" + 
									" 		select sus_no from tb_orbat_unt_dtl where status_sus_no='Active' "+linesus+" ) "+  SearchValue + group_str +
							" ORDER BY sub.id asc limit " +pageL+" OFFSET "+startPage ;
				}
				 else if(asset_type.equals("2")) {
					 q= "select fv.unit_name as command,fvm.unit_name as corps,\r\n" + 
					 		"	div.unit_name as div,\r\n" + 
					 		"	bde.unit_name as bde,\r\n" + 
					 		"	orb.unit_name,count(sub.asset_cat) as total,arm.arm_desc,sub.* "
					 		+ " from (" +qry2 +") sub "
					 			  + "inner join tb_orbat_unt_dtl orb on orb.sus_no = sub.sus_no and orb.status_sus_no='Active'\r\n" + 
					 			 "inner join TB_ORBAT_LINE_DTE arm on arm.arm_code=orb.arm_code  \r\n" + 
					 				"left join all_fmn_view fv  on orb.sus_no = sub.sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n" + 
					 				"left join all_fmn_view fvm  on orb.sus_no = sub.sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps' \r\n" + 
					 				"left join all_fmn_view div  on orb.sus_no = sub.sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \r\n" + 
					 				"left join all_fmn_view bde  on orb.sus_no = sub.sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade' 	"
					 				+ " where arm.line_dte_sus in ( \r\n" + 
					 				" 	select sus_no from tb_orbat_unt_dtl where status_sus_no='Active' "+linesus+" ) "+ SearchValue +group_str+
								" ORDER BY sub.id asc limit " +pageL+" OFFSET "+startPage ;
					}
				 else {
					 q= "select fv.unit_name as command,fvm.unit_name as corps,\r\n" + 
					 		"	div.unit_name as div,\r\n" + 
					 		"	bde.unit_name as bde,\r\n" + 
					 		"	orb.unit_name,count(sub.asset_cat) as total,arm.arm_desc,sub.* "
					 		+ " from (" +qry1+" union all "+ qry2 +") sub "
					 			  + "inner join tb_orbat_unt_dtl orb on orb.sus_no = sub.sus_no and orb.status_sus_no='Active'\r\n" + 
					 			 "inner join TB_ORBAT_LINE_DTE arm on arm.arm_code=orb.arm_code  \r\n" + 
					 				"left join all_fmn_view fv  on orb.sus_no = sub.sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n" + 
					 				"left join all_fmn_view fvm  on orb.sus_no = sub.sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps' \r\n" + 
					 				"left join all_fmn_view div  on orb.sus_no = sub.sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \r\n" + 
					 				"left join all_fmn_view bde  on orb.sus_no = sub.sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade' 	"
					 				+ "where arm.line_dte_sus in ( \r\n" + 
					 			" select sus_no from tb_orbat_unt_dtl where status_sus_no='Active' "+linesus+" ) "+ SearchValue +group_str+
								" ORDER BY sub.id asc limit " +pageL+" OFFSET "+startPage ;
					}
				
				stmt=conn.prepareStatement(q);
				if(username.equals("hq")){
					if(!line_dte.equals("0") && !line_dte.equals("")) {
					stmt.setString(1, line_dte);
					}
				}
				
				stmt = setQueryWhereClause_SQL4(stmt,Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,asset_type,b_head,b_code,a_type,line_dte,sessionUserId,s_state);
				

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
	
	 public long getallAssetIndiaHoldingCountList(String Search,String orderColunm,String orderType,String asset_type,String b_head,String b_code,String a_type,String line_dte,HttpSession sessionUserId,
			 String cont_comd,String cont_corps,
				String cont_div,String cont_bde,String unit_name,String sus_no,String s_state)throws SQLException {
		 String SearchValue = GenerateQueryWhereClause_SQL4(Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,asset_type,b_head,b_code,a_type,line_dte,sessionUserId,s_state);
			String username = sessionUserId.getAttribute("username").toString();
			int total = 0;
			String linesus = "";
			String q = null;
			Connection conn = null;
			try {
				
				
//				if(username.equals("hq")){
//					if(!line_dte.equals("0") && !line_dte.equals("")) {
//					 linesus = " where line_dte_sus = ? ";
//					}
//				}
				if(!line_dte.equals("0") && !line_dte.equals("")) {
					 linesus = " and  sus_no = ? ";
					}
				
				conn = dataSource.getConnection();
				String group_str=" group by fv.unit_name ,fvm.unit_name ,arm.arm_desc,\r\n" + 
						"	div.unit_name ,\r\n" + 
						"	bde.unit_name ,\r\n" + 
						"	orb.unit_name,sub.assest_id,sub.asset_cat,sub.id,sub.sus_no,sub.assets_name,sub.make_name,sub.model_name,sub.model_number,sub.machine_number,sub.b_head,sub.budget_code,sub.budget_id,sub.s_state ";
				String qry1="select distinct 'Computing' as asset_cat,am.id,am.sus_no,ma.assets_name,ma.id as assest_id,mm.make_name,m.model_name,am.model_number,am.machine_number,am.b_head,b.budget_code,b.id as budget_id, s_state from tb_asset_main am\r\n" + 
						"					inner join  tb_mstr_assets ma on  ma.id=am.asset_type  \r\n" + 
						"					inner join  tb_mstr_make mm on  mm.id=am.make_name  \r\n" + 
						"					inner join  tb_mstr_model m on  m.id=am.model_name  \r\n" + 
						" inner join  tb_mstr_budget b on  b.id::text=am.b_code  \r\n" +
						"					 where am.id!=0 and am.status=1 ";
				String qry2=	"					 select  distinct 'Peripheral'as asset_cat,ap.id,ap.sus_no,pt.assets_name,pt.id as assest_id,mm.make_name,m.model_name,ap.model_no as model_number,ap.machine_no as machine_number, ap.b_head,b.budget_code,b.id as budget_id \r\n" + 
						"					, s_state from it_asset_peripherals ap \r\n" + 
						"					inner join tb_mstr_assets pt on pt.id=ap.assets_type \r\n" + 
						"					inner join  tb_mstr_make mm on  mm.id=ap.make_name \r\n" + 
						"					inner join  tb_mstr_model m on  m.id=ap.model_name \r\n" +
						" inner join  tb_mstr_budget b on  b.id::text=ap.b_code \r\n " +
						"					where ap.id!=0 and ap.status=1 ";
				if(asset_type.equals("1")) {
					q= "select count(app.*) from (select fv.unit_name as command,fvm.unit_name as corps,\r\n" + 
							"	div.unit_name as div,\r\n" + 
							"	bde.unit_name as bde,\r\n" + 
							"	orb.unit_name,count(sub.asset_cat) as total,arm.arm_desc,sub.* "
							+ " from (" +qry1 +") sub "
									+ "inner join tb_orbat_unt_dtl orb on orb.sus_no = sub.sus_no and orb.status_sus_no='Active'\r\n" + 
									"inner join TB_ORBAT_LINE_DTE arm on arm.arm_code=orb.arm_code \r\n" + 
									"left join all_fmn_view fv  on orb.sus_no = sub.sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n" + 
									"left join all_fmn_view fvm  on orb.sus_no = sub.sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps' \r\n" + 
									"left join all_fmn_view div  on orb.sus_no = sub.sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \r\n" + 
									"left join all_fmn_view bde  on orb.sus_no = sub.sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade' 	"
									+ "where arm.line_dte_sus in ( \r\n" + 
									" 					select sus_no from tb_orbat_unt_dtl where status_sus_no='Active'"+linesus+" ) 	"+  SearchValue + group_str +
							" ORDER BY sub.id asc ) app" ;
				}
				 else if(asset_type.equals("2")) {
					 q= "select count(app.*) from (select fv.unit_name as command,fvm.unit_name as corps,\r\n" + 
					 		"	div.unit_name as div,\r\n" + 
					 		"	bde.unit_name as bde,\r\n" + 
					 		"	orb.unit_name,count(sub.asset_cat) as total,arm.arm_desc,sub.* "
					 		+ " from (" +qry2 +") sub "
					 				+ "inner join tb_orbat_unt_dtl orb on orb.sus_no = sub.sus_no and orb.status_sus_no='Active'\r\n" + 
					 				"inner join TB_ORBAT_LINE_DTE arm on arm.arm_code=orb.arm_code \r\n" + 
					 				"left join all_fmn_view fv  on orb.sus_no = sub.sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n" + 
					 				"left join all_fmn_view fvm  on orb.sus_no = sub.sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps' \r\n" + 
					 				"left join all_fmn_view div  on orb.sus_no = sub.sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \r\n" + 
					 				"left join all_fmn_view bde  on orb.sus_no = sub.sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade' 	"
					 				+ "where arm.line_dte_sus in ( \r\n" + 
					 				" 					select sus_no from tb_orbat_unt_dtl where status_sus_no='Active' "+linesus+" ) 	"+ SearchValue + group_str +
								" ORDER BY sub.id asc ) app";
					}
				 else {
					 q= "select count(app.*) from (select fv.unit_name as command,fvm.unit_name as corps,\r\n" + 
					 		"	div.unit_name as div,\r\n" + 
					 		"	bde.unit_name as bde,\r\n" + 
					 		"	orb.unit_name,count(sub.asset_cat) as total,arm.arm_desc,sub.* "
					 		+ " from (" +qry1+" union all "+ qry2 +") sub "
					 				+ "inner join tb_orbat_unt_dtl orb on orb.sus_no = sub.sus_no and orb.status_sus_no='Active'\r\n" + 
					 				"inner join TB_ORBAT_LINE_DTE arm on arm.arm_code=orb.arm_code \r\n" + 
					 				"left join all_fmn_view fv  on orb.sus_no = sub.sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n" + 
					 				"left join all_fmn_view fvm  on orb.sus_no = sub.sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps' \r\n" + 
					 				"left join all_fmn_view div  on orb.sus_no = sub.sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \r\n" + 
					 				"left join all_fmn_view bde  on orb.sus_no = sub.sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade' 	"
					 				+ " where arm.line_dte_sus in ( \r\n" + 
					 			" select sus_no from tb_orbat_unt_dtl where status_sus_no='Active'  "+linesus+" ) "+ SearchValue +group_str+
								" ORDER BY sub.id asc ) app";
					}
				
//				select mu.sus_no from tb_orbat_unt_dtl mu where mu.arm_code in (select arm_code from TB_ORBAT_LINE_DTE "+linesus+" )) 
				PreparedStatement stmt = conn.prepareStatement(q);
				if(username.equals("hq")){
					if(!line_dte.equals("0") && !line_dte.equals("")) {
					stmt.setString(1, line_dte);
					}
				}
				stmt = setQueryWhereClause_SQL4(stmt,Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,asset_type,b_head,b_code,a_type,line_dte,sessionUserId,s_state);
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
				String b_head,String b_code,String a_type,String line_dte,HttpSession sessionUserId,String s_state) {
		 String SearchValue ="";
			if (!Search.equals("") && !Search.equals(null) && Search != null && Search != "")  { // for Input Filter
				SearchValue += " and  ( ";
				SearchValue += " cast(sub.assets_name as character varying) like ? or "
								+ "upper(sub.asset_cat) like ? or "
								+ "cast(sub.model_name as character varying) like ? or "
								+ "cast(sub.make_name as character varying) like ? or "
								+ " upper(sub.model_number) like ? or upper(sub.machine_number) like ? or "
								+"sub.b_head like ? or upper( orb.unit_name) like ? or "
								+ "upper( fv.unit_name) like ? or upper( fvm.unit_name) like ? or "
								+ "upper( div.unit_name) like ? or upper( bde.unit_name) like ? or "
								+"cast(sub.budget_code as character varying) like ? or arm.arm_desc like ?  )";
				
			}
			if(!sus_no.equals("")){
	  				SearchValue +=" and lower(orb.sus_no) = ?";
	  			
	    	}
	    	if(!unit_name.equals("0") && !unit_name.equals("")){
	    			SearchValue +=" and lower(orb.unit_name) = ? ";
	    	} 
	    	
	    	if(!cont_bde.equals("0") && !cont_bde.equals("")){
	    			SearchValue +=" and orb.form_code_control = ? ";
	    	}else {
	    		if(!cont_div.equals("0") && !cont_div.equals("")){
	        			SearchValue +=" and orb.form_code_control like ? ";
		    	}else {
		    		if(!cont_corps.equals("0") && !cont_corps.equals("")){
		        			SearchValue +=" and orb.form_code_control like ? ";
			    	}else {
			    		if(!cont_comd.equals("-1") && !cont_comd.equals("")){
			    				SearchValue +=" and orb.form_code_control like ? ";
				    	}
			    	}
			    }
		    }
			
			if(!a_type.equals("0") && !a_type.equals("")) {
				
					SearchValue +=" and sub.assest_id = ? ";
				
			}	
			if(!b_head.equals("0") && !b_head.equals(""))  {
				
					SearchValue +=" and sub.b_head = ? ";
				
			}	
			if(!b_code.equals("0") && !b_code.equals("")) {
				
					SearchValue +=" and sub.budget_id = ? ";
				
			}
			if(!s_state.equals("0") && !s_state.equals("")) {
				
				SearchValue +=" and sub.s_state = ? ";
			
			}

			return SearchValue;
		}
	 
	 public PreparedStatement setQueryWhereClause_SQL4(PreparedStatement stmt,String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String asset_type,
				String b_head,String b_code,String a_type,String line_dte,HttpSession sessionUserId,String s_state) {
		 String  username = sessionUserId.getAttribute("username").toString();
		 	int flag = 0;
//		 	if(username.equals("hq")){
//		 		if(!line_dte.equals("0") && !line_dte.equals("")) {
//		 		 flag = 1;
//		 		}
//			}
//		 	else {
//		 		 flag = 0;
//		 	}
			
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
					flag += 1;
					stmt.setString(flag, "%" + Search.toUpperCase() + "%");
					flag += 1;
					stmt.setString(flag, "%" + Search.toUpperCase() + "%");
					flag += 1;
					stmt.setString(flag, "%" + Search.toUpperCase() + "%");
					

					
				}
				if(!line_dte.equals("0") && !line_dte.equals("")) {
			 		 flag += 1;
			 		 stmt.setString(flag, line_dte);
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
				if (!s_state.equals("0") && !s_state.equals("")) {
					flag += 1;
					stmt.setString(flag, (s_state));
				}
				
				
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return stmt;
		}
	

		public ArrayList<ArrayList<String>> pdf_all_india_asset_holding_ReportDataList(String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String asset_type,String a_type,String b_head,String b_code,String line_dte,HttpSession sessionUserId,String s_state) {
			ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q = "";
			String SearchValue = "";
			String linesus = "";
			String  username = sessionUserId.getAttribute("username").toString();
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;

				if(username.equals("hq")){
					if(!line_dte.equals("0") && !line_dte.equals("")) {
					 linesus = " where   sus_no = ? ";
					}
				}
				if(!sus_no.equals("")){
		  				SearchValue +=" and lower(orb.sus_no) = ?";
		  			
		    	}
		    	if(!unit_name.equals("0") && !unit_name.equals("")){
		    			SearchValue +=" and lower(orb.unit_name) = ? ";
		    	} 
		    	
		    	if(!cont_bde.equals("0") && !cont_bde.equals("")){
		    			SearchValue +=" and orb.form_code_control = ? ";
		    	}else {
		    		if(!cont_div.equals("0") && !cont_div.equals("")){
		        			SearchValue +=" and orb.form_code_control like ? ";
			    	}else {
			    		if(!cont_corps.equals("0") && !cont_corps.equals("")){
			        			SearchValue +=" and orb.form_code_control like ? ";
				    	}else {
				    		if(!cont_comd.equals("-1") && !cont_comd.equals("")){
				    				SearchValue +=" and orb.form_code_control like ? ";
					    	}
				    	}
				    }
			    }
				
				
				
				if(!a_type.equals("0") && !a_type.equals("")) {
					
						SearchValue +=" and sub.assest_id = ? ";
					}
					
				if(!b_head.equals("0") && !b_head.equals(""))  {
					
						SearchValue +=" and sub.b_head = ? ";
					}
					
				if(!b_code.equals("0") && !b_code.equals("")) {
					
						SearchValue +=" and sub.budget_id = ? ";
					
				}

				if(!s_state.equals("0") && !s_state.equals("")) {
					
					SearchValue +=" and sub.s_state = ? ";
				
				}
				
				
				String group_str=" group by fv.unit_name ,fvm.unit_name ,arm.arm_desc,\r\n" + 
						"	div.unit_name ,\r\n" + 
						"	bde.unit_name ,\r\n" + 
						"	orb.unit_name,sub.assest_id,sub.asset_cat,sub.id,sub.sus_no,sub.assets_name,sub.make_name,sub.model_name,sub.model_number,sub.machine_number,sub.b_head,sub.budget_code,sub.budget_id,sub.s_state ";
				String qry1="select distinct 'Computing' as asset_cat,am.id,am.sus_no,ma.assets_name,ma.id as assest_id,mm.make_name,m.model_name,am.model_number,am.machine_number,am.b_head,b.budget_code,b.id as budget_id,s_state from tb_asset_main am\r\n" + 
						"					inner join  tb_mstr_assets ma on  ma.id=am.asset_type  \r\n" + 
						"					inner join  tb_mstr_make mm on  mm.id=am.make_name  \r\n" + 
						"					inner join  tb_mstr_model m on  m.id=am.model_name  \r\n" + 
						" inner join  tb_mstr_budget b on  b.id::text=am.b_code  \r\n" +
						"					 where am.id!=0 and am.status=1 ";
				String qry2=	"					 select  distinct 'Peripheral'as asset_cat,ap.id,ap.sus_no,pt.assets_name,pt.id as assest_id,mm.make_name,m.model_name,ap.model_no as model_number,ap.machine_no as machine_number, ap.b_head,b.budget_code,b.id as budget_id \r\n" + 
						"					,s_state from it_asset_peripherals ap \r\n" + 
						"					inner join tb_mstr_assets pt on pt.id=ap.assets_type \r\n" + 
						"					inner join  tb_mstr_make mm on  mm.id=ap.make_name \r\n" + 
						"					inner join  tb_mstr_model m on  m.id=ap.model_name \r\n" +
						" inner join  tb_mstr_budget b on  b.id::text=ap.b_code \r\n " +
						"					where ap.id!=0 and ap.status=1 ";
				
				 if(asset_type.equals("1")) {
					q= "select fv.unit_name as command,fvm.unit_name as corps,\r\n" + 
							"	div.unit_name as div,\r\n" + 
							"	bde.unit_name as bde,\r\n" + 
							"	orb.unit_name,count(sub.asset_cat) as total,arm.arm_desc,sub.* "
							+ " from (" +qry1 +") sub "
									+ "inner join tb_orbat_unt_dtl orb on orb.sus_no = sub.sus_no and orb.status_sus_no='Active'\r\n" + 
									"inner join TB_ORBAT_LINE_DTE arm on arm.arm_code=orb.arm_code  \r\n" + 
									"left join all_fmn_view fv  on orb.sus_no = sub.sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n" + 
									"left join all_fmn_view fvm  on orb.sus_no = sub.sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps' \r\n" + 
									"left join all_fmn_view div  on orb.sus_no = sub.sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \r\n" + 
									"left join all_fmn_view bde  on orb.sus_no = sub.sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'"
									+ "where arm.line_dte_sus in ( \r\n" + 
									" 	 select sus_no from tb_orbat_unt_dtl where status_sus_no='Active'  "+linesus+" ) "+  SearchValue + group_str +
							" ORDER BY sub.id asc " ;
				}
				 else if(asset_type.equals("2")) {
					 q= "select fv.unit_name as command,fvm.unit_name as corps,\r\n" + 
					 		"	div.unit_name as div,\r\n" + 
					 		"	bde.unit_name as bde,\r\n" + 
					 		"	orb.unit_name,count(sub.asset_cat) as total,arm.arm_desc,sub.* "
					 		+ " from (" +qry2 +") sub "
					 				+ "inner join tb_orbat_unt_dtl orb on orb.sus_no = sub.sus_no and orb.status_sus_no='Active'\r\n" + 
					 				"inner join TB_ORBAT_LINE_DTE arm on arm.arm_code=orb.arm_code \r\n" + 
					 				"left join all_fmn_view fv  on orb.sus_no = sub.sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n" + 
					 				"left join all_fmn_view fvm  on orb.sus_no = sub.sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps' \r\n" + 
					 				"left join all_fmn_view div  on orb.sus_no = sub.sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \r\n" + 
					 				"left join all_fmn_view bde  on orb.sus_no = sub.sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'"
					 				+ " where arm.line_dte_sus in ( \r\n" + 
					 				" 	 select sus_no from tb_orbat_unt_dtl where status_sus_no='Active'  "+linesus+" ) "+ SearchValue +group_str+
								" ORDER BY sub.id asc " ;
					}
				 else {
					 q= "select fv.unit_name as command,fvm.unit_name as corps,\r\n" + 
					 		"	div.unit_name as div,\r\n" + 
					 		"	bde.unit_name as bde,\r\n" + 
					 		"	orb.unit_name,count(sub.asset_cat) as total,arm.arm_desc,sub.* "
					 		+ " from (" +qry1+" union all "+ qry2 +") sub "
					 				+ "inner join tb_orbat_unt_dtl orb on orb.sus_no = sub.sus_no and orb.status_sus_no='Active'\r\n" + 
					 				"inner join TB_ORBAT_LINE_DTE arm on arm.arm_code=orb.arm_code \r\n" + 
					 				"left join all_fmn_view fv  on orb.sus_no = sub.sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n" + 
					 				"left join all_fmn_view fvm  on orb.sus_no = sub.sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps' \r\n" + 
					 				"left join all_fmn_view div  on orb.sus_no = sub.sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \r\n" + 
					 				"left join all_fmn_view bde  on orb.sus_no = sub.sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'"
					 				+ " where arm.line_dte_sus in ( \r\n" + 
					 			"  select sus_no from tb_orbat_unt_dtl where status_sus_no='Active'  "+linesus+" ) "+ SearchValue +group_str+
								" ORDER BY sub.id asc "  ;
					}

				 
				stmt = conn.prepareStatement(q);
				
			 	int flag = 0;
			 	if(username.equals("hq")){
					if(!line_dte.equals("0") && !line_dte.equals("")) {
					flag += 1;
					stmt.setString(flag, line_dte);
					}
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
				if (!s_state.equals("0") && !s_state.equals("")) {
					flag += 1;
					stmt.setString(flag, (s_state));
				}
		
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {

					ArrayList<String> list = new ArrayList<String>();
					
					list.add(rs.getString("arm_desc"));// 8
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
					if (rs.getString("s_state").equals("1")) {
						list.add("Serviceable");// 5
					}
					else if (rs.getString("s_state").equals("2")) {
						list.add("Un-serviceable");// 5
					}
					else
						list.add(rs.getString("s_state"));// 5
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
