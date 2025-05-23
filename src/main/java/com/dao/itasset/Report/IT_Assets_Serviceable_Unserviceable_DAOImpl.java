package com.dao.itasset.Report;

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
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;

public class IT_Assets_Serviceable_Unserviceable_DAOImpl implements IT_Assets_Serviceable_Unserviceable_DAO{

HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	public ArrayList<List<String>> getCorpsList(String fcode) {
    	ArrayList<List<String>> alist = new ArrayList<List<String>>();
		Connection conn = null;
		try{	  
    		conn = dataSource.getConnection();
    		String sql= "select SUBSTR(form_code_control,1,3) as form_code_control,unit_name FROM tb_orbat_unt_dtl " + 
					" where sus_no in (select sus_no from tb_orbat_codesform where level_in_hierarchy ='Corps') and " + 
					" form_code_control like ? and status_sus_no = 'Active'";
			PreparedStatement stmt=conn.prepareStatement(sql);
			stmt.setString(1, fcode+"%");
			
			ResultSet rs1 = stmt.executeQuery();     
    		for(int i =0 ; rs1.next() ;i++) {
    			ArrayList<String> list = new ArrayList<String>();
    			list.add(rs1.getString("form_code_control"));
    			list.add(rs1.getString("unit_name"));
    			alist.add(list);
    		}
    		rs1.close();
    		stmt.close();
    	}catch (SQLException e) {
    		e.printStackTrace();
		}
		return alist;
    }
	
	public List<Map<String, Object>> getIT_Assets_Serviceable_Unserviceable_ReportList(int startPage,String pageLength,String Search,String orderColunm,String orderType,
			 String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String assets_type,String assets_name,HttpSession session) 
			 throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		if(pageLength.equals("-1")){
			pageLength = "ALL";
		}
		String SearchValue = GenerateQueryWhereClause_SQL(Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,assets_type,assets_name);

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();		
			String group_str="group by app.sus_no, app.asset_cat ,app.sus_no,app.assets_name,app.id ,app.make_name,app.model_name,app.model_number,app.machine_number,app.b_head,app.budget_code, app.unit_name,app.form_code_control";

			 if(assets_type.equals("1")) {
				 
				q = "		select app.cmd_unit as cont_comd,app.corp_unit as cont_corps,app.div_unit as cont_div,app.bde_unit as cont_bde,app.unit_name as unit_name,\r\n" + 
						"			app.sus_no, app.asset_cat as assets_type ,app.sus_no,app.assets_name, app.unit_name as unit_name,app.form_code_control,app.ser,app.unser,app.id \r\n" + 
						"					from(select distinct 'Computing' as asset_cat ,am.sus_no,ma.assets_name,fv.unit_name as cmd_unit,fvm.unit_name as corp_unit,div.unit_name as div_unit,bde.unit_name as bde_unit,\r\n" + 
						"						 orb.unit_name,ma.id,orb.form_code_control ,\r\n" + 
						"						 count(am.asset_type) FILTER (where td.codevalue='1') as ser,\r\n" + 
						"						 count(am.asset_type) FILTER (where td.codevalue='2') as unser\r\n" + 
						"						 from tb_asset_main am\r\n" + 
						"					inner join  tb_mstr_assets ma on  ma.id=am.asset_type  \r\n" + 
						"					inner join  tb_mstr_make mm on  mm.id=am.make_name  \r\n" + 
						"					inner join  tb_mstr_model m on  m.id=am.model_name  \r\n" + 
						"					inner join tb_orbat_unt_dtl orb on orb.sus_no = am.sus_no and orb.status_sus_no='Active'\r\n" + 
						"				    inner join t_domain_value td on  td.codevalue:: character varying = am.s_state:: character varying and td.domainid='SERVICE_CAT'\r\n" + 
						"\r\n" + 
						"left join all_fmn_view fv  on orb.sus_no = am.sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n" + 
						"left join all_fmn_view fvm  on orb.sus_no = am.sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps' \r\n" + 
						"left join all_fmn_view div  on orb.sus_no = am.sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \r\n" + 
						"left join all_fmn_view bde  on orb.sus_no = am.sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\r\n" + 
						" inner join  tb_mstr_budget b on  b.id::text=am.b_code  \r\n" + 
						"					 where am.id!=0\r\n" + 
						"							group by cmd_unit,corp_unit,div_unit,bde_unit,orb.unit_name,am.sus_no,orb.form_code_control,asset_cat,ma.assets_name,ma.id)app "+  SearchValue+" ORDER BY " + orderColunm +" "+orderType +" limit " +pageLength+" OFFSET "+startPage ; 
			}
			 else if(assets_type.equals("2")) {
				q = "		select app.cmd_unit as cont_comd,app.corp_unit as cont_corps,app.div_unit as cont_div,app.bde_unit as cont_bde,app.unit_name as unit_name,\r\n" + 
						"			app.sus_no, app.asset_cat as assets_type ,app.sus_no,app.assets_name, app.unit_name as unit_name,app.form_code_control,app.ser,app.unser,app.id \r\n" + 
						"					from(select distinct 'Peripherals' as asset_cat ,am.sus_no,ma.assets_name,fv.unit_name as cmd_unit,fvm.unit_name as corp_unit,div.unit_name as div_unit,bde.unit_name as bde_unit,\r\n" + 
						"					orb.unit_name,ma.id,orb.form_code_control ,\r\n" + 
						"					count(am.assets_type) FILTER (where td.codevalue='1') as ser,\r\n" + 
						"					count(am.assets_type) FILTER (where td.codevalue='2') as unser\r\n" + 
						"					from it_asset_peripherals am\r\n" + 
						"					inner join  tb_mstr_assets ma on  ma.id=am.assets_type  \r\n" + 
						"					inner join  tb_mstr_make mm on  mm.id=am.make_name  \r\n" + 
						"					inner join  tb_mstr_model m on  m.id=am.model_name  \r\n" + 
						"					inner join tb_orbat_unt_dtl orb on orb.sus_no = am.sus_no and orb.status_sus_no='Active'\r\n" + 
						"					inner join t_domain_value td on  td.codevalue:: character varying = am.s_state:: character varying and td.domainid='SERVICE_CAT'\r\n" + 
						"\r\n" + 
						"left join all_fmn_view fv  on orb.sus_no = am.sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n" + 
						"left join all_fmn_view fvm  on orb.sus_no = am.sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps' \r\n" + 
						"left join all_fmn_view div  on orb.sus_no = am.sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \r\n" + 
						"left join all_fmn_view bde  on orb.sus_no = am.sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\r\n" + 
						" inner join  tb_mstr_budget b on  b.id::text=am.b_code  \r\n" + 
						"					 where am.id!=0\r\n" + 
						"							group by cmd_unit,corp_unit,div_unit,bde_unit,orb.unit_name,am.sus_no,orb.form_code_control,asset_cat,ma.assets_name,ma.id)app "+ SearchValue+ " ORDER BY " + orderColunm +" "+orderType +" limit " +pageLength+" OFFSET "+startPage ;
				}
				 else {
				q = " select app.cmd_unit as cont_comd,app.corp_unit as cont_corps,app.div_unit as cont_div,app.bde_unit as cont_bde,app.unit_name as unit_name,   app.sus_no,app.form_code_control,app.assets_name,app.assets_type,\r\n" + 
						"app.ser,app.unser from\r\n" + 
						"	(select distinct \r\n" + 
						"	fv.unit_name as cmd_unit,\r\n" + 
						"	fvm.unit_name as corp_unit,\r\n" + 
						"	div.unit_name as div_unit,\r\n" + 
						"	bde.unit_name as bde_unit,\r\n" + 
						"	orb.unit_name,\r\n" + 
						"	hld.sus_no,\r\n" + 
						"	orb.form_code_control,\r\n" + 
						"	hld.assets_name,\r\n" + 
						"	hld.assets_type,\r\n" + 
						"	count(hld.assets_name) FILTER (where hld.codevalue='1') as ser,\r\n" + 
						"	count(hld.assets_name) FILTER (where hld.codevalue='2') as unser\r\n" + 
						"	from (\r\n" + 
						"		 select d.assets_name,d.id as m_id,c.sus_no,td.label as s_state,td.codevalue,'IT' AS typ,'Peripherals' AS assets_type\r\n" + 
						"		 from it_asset_peripherals c \r\n" + 
						"		 inner join tb_mstr_assets d on  d.id = c.assets_type and c.status = '1' \r\n" + 
						"		 inner join t_domain_value td on  td.codevalue:: character varying = c.s_state:: character varying and td.domainid='SERVICE_CAT'				\r\n" + 
						"		 union all\r\n" + 
						"		 select ma.assets_name,ma.id as m_id,j.sus_no,td1.label as s_state,td1.codevalue,'MAIN' AS typ ,'Computing' AS assets_type\r\n" + 
						"		 from tb_asset_main j \r\n" + 
						"		 inner join tb_mstr_assets ma on  ma.id = j.asset_type and j.status = '1' \r\n" + 
						"		 inner join t_domain_value td1 on  td1.codevalue:: character varying = j.s_state:: character varying and td1.domainid='SERVICE_CAT'				\r\n" + 
						"	) hld \r\n" + 
						"	inner join tb_orbat_unt_dtl orb on orb.sus_no = hld.sus_no and orb.status_sus_no='Active'\r\n" + 
						"	left join all_fmn_view fv  on orb.sus_no = hld.sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\r\n" + 
						"	left join all_fmn_view fvm  on orb.sus_no = hld.sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\r\n" + 
						"	left join all_fmn_view div  on orb.sus_no = hld.sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\r\n" + 
						"	left join all_fmn_view bde  on orb.sus_no = hld.sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade' \r\n" + 
						"	group by  cmd_unit,corp_unit,div_unit,bde_unit,orb.unit_name,hld.sus_no,orb.form_code_control,hld.assets_name,hld.assets_type) app " +SearchValue +" ORDER BY " + orderColunm +" "+orderType +" limit " +pageLength+" OFFSET "+startPage ;
				 }
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt,Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,assets_type,assets_name);

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
	 public long getIT_Assets_Serviceable_Unserviceable_TotalCountDtl(String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String assets_type,String assets_name) {
			String SearchValue = GenerateQueryWhereClause_SQL(Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,assets_type,assets_name);
			int total = 0;
			String q = null;
			Connection conn = null;

			try {
				conn = dataSource.getConnection();
				String group_str="group by app.sus_no, app.asset_cat ,app.sus_no,app.assets_name,app.id ,app.make_name,app.model_name,app.model_number,app.machine_number,app.b_head,app.budget_code, app.unit_name,app.form_code_control";
				
				
				 if(assets_type.equals("1")) {
					 
					q=	"select count(app.*),app.sus_no, app.asset_cat ,app.id,app.sus_no,app.assets_name,app.id  as assets_id,app.make_name,app.model_name,app.model_number,app.machine_number,app.b_head,app.budget_code,app.id as budget_id, app.unit_name as unit_name,app.form_code_control\r\n" + 
							"					from(select distinct 'Computing' as asset_cat ,am.id,am.sus_no,ma.assets_name,ma.id  as assets_id,mm.make_name,m.model_name,am.model_number,am.machine_number,am.b_head,b.budget_code,b.id as budget_id,orb.unit_name,orb.form_code_control from tb_asset_main am\r\n" + 
							"					inner join  tb_mstr_assets ma on  ma.id=am.asset_type  \r\n" + 
							"					inner join  tb_mstr_make mm on  mm.id=am.make_name  \r\n" + 
							"					inner join  tb_mstr_model m on  m.id=am.model_name  \r\n" + 
							"					inner join tb_orbat_unt_dtl orb on orb.sus_no = am.sus_no and orb.status_sus_no='Active'\r\n" + 
							"left join all_fmn_view fv  on orb.sus_no = am.sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n" + 
							"left join all_fmn_view fvm  on orb.sus_no = am.sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps' \r\n" + 
							"left join all_fmn_view div  on orb.sus_no = am.sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \r\n" + 
							"left join all_fmn_view bde  on orb.sus_no = am.sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\r\n" + 
							" inner join  tb_mstr_budget b on  b.id::text=am.b_code  \r\n" + 
							"					 where am.id!=0)app "+  SearchValue +group_str  ;
				}
				 else if(assets_type.equals("2")) {
					 q= 
							 "select  count(app.*),app.sus_no, app.asset_cat ,app.id,app.sus_no,app.assets_name,app.id  as assets_id,app.make_name,app.model_name,app.model_number,app.machine_number,app.b_head,app.budget_code,app.id as budget_id, app.unit_name as unit_name,app.form_code_control\r\n" + 
					 		"					from(select distinct 'Peripheral' as asset_cat ,am.id,am.sus_no,ma.assets_name,ma.id  as assets_id,mm.make_name,m.model_name,am.model_number,am.machine_number,am.b_head,b.budget_code,b.id as budget_id,orb.unit_name,orb.form_code_control from tb_asset_main am\r\n" + 
					 		"					inner join  tb_mstr_assets ma on  ma.id=am.asset_type  \r\n" + 
					 		"					inner join  tb_mstr_make mm on  mm.id=am.make_name  \r\n" + 
					 		"					inner join  tb_mstr_model m on  m.id=am.model_name  \r\n" + 
					 		"					inner join tb_orbat_unt_dtl orb on orb.sus_no = am.sus_no and orb.status_sus_no='Active'\r\n" + 
					 		"left join all_fmn_view fv  on orb.sus_no = am.sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command' \r\n" + 
					 		"left join all_fmn_view fvm  on orb.sus_no = am.sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps' \r\n" + 
					 		"left join all_fmn_view div  on orb.sus_no = am.sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division' \r\n" + 
					 		"left join all_fmn_view bde  on orb.sus_no = am.sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade'\r\n" + 
					 		" inner join  tb_mstr_budget b on  b.id::text=am.b_code  \r\n" + 
					 		"					 where am.id!=0)app  "+ SearchValue +group_str;
					}
				 else {
				q ="select count(app.*) from (\r\n" +
						" select app.cmd_unit,app.corp_unit,app.div_unit,app.bde_unit,app.unit_name,app.sus_no,app.form_code_control,app.assets_type,app.ser,app.unser from\r\n" + 
						"	(select distinct \r\n" + 
						"	fv.unit_name as cmd_unit,\r\n" + 
						"	fvm.unit_name as corp_unit,\r\n" + 
						"	div.unit_name as div_unit,\r\n" + 
						"	bde.unit_name as bde_unit,\r\n" + 
						"	orb.unit_name,\r\n" + 
						"	hld.sus_no,\r\n" + 
						"	orb.form_code_control,\r\n" + 
						"	hld.assets_type,\r\n" + 
						"	count(hld.assets_type) FILTER (where hld.codevalue='1') as ser,\r\n" + 
						"	count(hld.assets_type) FILTER (where hld.codevalue='2') as unser\r\n" + 
						"	from (\r\n" + 
						"		 select d.assets_name as assets_type,d.id as m_id,c.sus_no,td.label as s_state,td.codevalue,'IT' AS typ \r\n" + 
						"		 from it_asset_peripherals c \r\n" + 
						"		 inner join tb_mstr_assets d on  d.id = c.assets_type and c.status = '1' \r\n" + 
						"		 inner join t_domain_value td on  td.codevalue:: character varying = c.s_state:: character varying and td.domainid='SERVICE_CAT'				\r\n" + 
						"		 union all\r\n" + 
						"		 select ma.assets_name as assets_type,ma.id as m_id,j.sus_no,td1.label as s_state,td1.codevalue,'MAIN' AS typ \r\n" + 
						"		 from tb_asset_main j \r\n" + 
						"		 inner join tb_mstr_assets ma on  ma.id = j.asset_type and j.status = '1' \r\n" + 
						"		 inner join t_domain_value td1 on  td1.codevalue:: character varying = j.s_state:: character varying and td1.domainid='SERVICE_CAT'				\r\n" + 
						"	) hld \r\n" + 
						"	inner join tb_orbat_unt_dtl orb on orb.sus_no = hld.sus_no and orb.status_sus_no='Active'\r\n" + 
						"	left join all_fmn_view fv  on orb.sus_no = hld.sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\r\n" + 
						"	left join all_fmn_view fvm  on orb.sus_no = hld.sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\r\n" + 
						"	left join all_fmn_view div  on orb.sus_no = hld.sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\r\n" + 
						"	left join all_fmn_view bde  on orb.sus_no = hld.sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade' \r\n" + 
						"	group by  cmd_unit,corp_unit,div_unit,bde_unit,orb.unit_name,hld.sus_no,orb.form_code_control,hld.assets_type) app " +SearchValue +") app";
				 }
				PreparedStatement stmt = conn.prepareStatement(q);
				stmt = setQueryWhereClause_SQL(stmt,Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,assets_type,assets_name);
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
	  
	public String GenerateQueryWhereClause_SQL(String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String assets_type,String assets_name) {
		String SearchValue ="";
 		if(!Search.equals("")) {
			Search = Search.toLowerCase();
 			SearchValue =" where ( ";
			SearchValue +=" cast(app.assets_name as character varying) like ? or "
					+ "upper(app.asset_cat) like ? or "
					+"lower(app.cmd_unit) like ? or lower(app.corp_unit) like ? or lower(app.div_unit) like ? "
					+ " or lower(app.bde_unit) like ? or lower(app.sus_no) like ? or lower(app.unit_name) like ? "
					+ " or lower(app.assets_type) like ? or cast(app.ser as character varying) = ? or cast(app.unser as character varying) = ?)";
		}
 		
 		
 		if(!sus_no.equals("")){
 			if (SearchValue.contains("where")) {
 				SearchValue +=" and lower(app.sus_no) = ?";
			} else {
				SearchValue += " where lower(app.sus_no) = ? ";
			}
 			
   	}
   	if(!unit_name.equals("0") && !unit_name.equals("")){
   		if (SearchValue.contains("where")) {
   			SearchValue +=" and lower(app.unit_name) = ? ";
			} else {
				SearchValue += " where lower(app.unit_name) = ? ";
			}
   	} 
   	
   	if(!cont_bde.equals("0") && !cont_bde.equals("")){
   		if (SearchValue.contains("where")) {
   			SearchValue +=" and app.form_code_control = ? ";
			} else {
				SearchValue += " where app.form_code_control = ? ";
			}
   	}else {
   		if(!cont_div.equals("0") && !cont_div.equals("")){
   			if (SearchValue.contains("where")) {
       			SearchValue +=" and app.form_code_control like ? ";
   			} else {
   				SearchValue += " where app.form_code_control like ? ";
   			}
	    	}else {
	    		if(!cont_corps.equals("0") && !cont_corps.equals("")){
	    			if (SearchValue.contains("where")) {
	        			SearchValue +=" and app.form_code_control like ? ";
	    			} else {
	    				SearchValue += " where app.form_code_control like ? ";
	    			}
		    	}else {
		    		if(!cont_comd.equals("-1") && !cont_comd.equals("")){
		    			if (SearchValue.contains("where")) {
		    				SearchValue +=" and app.form_code_control like ? ";
		    			} else {
		    				SearchValue += " where app.form_code_control like ? ";
		    			}
			    	}
		    	}
		    }
	    }

	if(!assets_name.equals("0") && !assets_name.equals("")){
   		if (SearchValue.contains("where")) {
   			SearchValue +=" and app.id = ? ";
			} else {
				SearchValue += " where app.id = ? ";
			}
   	} 

  return SearchValue;
   	
}


 public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt,String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String assets_type,String assets_name) {
		int flag = 0;
		try {
   		if(!Search.equals("")) {
				flag += 1;
				stmt.setString(flag, "%"+Search.toLowerCase()+"%");
				flag += 1;
				stmt.setString(flag, "%"+Search.toLowerCase()+"%");
				flag += 1;
				stmt.setString(flag, "%"+Search.toLowerCase()+"%");
				flag += 1;
				stmt.setString(flag, "%"+Search.toLowerCase()+"%");
				flag += 1;
				stmt.setString(flag, "%"+Search.toLowerCase()+"%");
				flag += 1;
				stmt.setString(flag, "%"+Search.toLowerCase()+"%");
				flag += 1;
				stmt.setString(flag, "%"+Search.toLowerCase()+"%");	
				flag += 1;
 				stmt.setString(flag, Search);
 				flag += 1;
 				stmt.setString(flag, Search);
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
			if(!assets_name.equals("0") && !assets_name.equals(""))
			{
				flag += 1;
				stmt.setInt(flag, Integer.parseInt(assets_name));		
			}
	
		}catch (Exception e) {}
		return stmt;
	}
 

public ArrayList<ArrayList<String>> PDF_IT_Assets_Serviceable_Unserviceable_Report(String cont_comd,String cont_corps,String cont_div,
		String cont_bde,String unit_name,String sus_no,String assets_type)
{
	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	Connection conn = null;
	String q="";
	String SearchValue="";
	try{	  
		conn = dataSource.getConnection();			 
		PreparedStatement stmt=null;
		
		if(!sus_no.equals("")){
 			if (SearchValue.contains("where")) {
 				SearchValue +=" and lower(app.sus_no) = ?";
			} else {
				SearchValue += " where lower(app.sus_no) = ? ";
			}			
	   	}
	   	if(!unit_name.equals("0") && !unit_name.equals("")){
	   		if (SearchValue.contains("where")) {
	   			SearchValue +=" and lower(app.unit_name) = ? ";
				} else {
					SearchValue += " where lower(app.unit_name) = ? ";
				}
	   	} 	   	
	   	if(!cont_bde.equals("0") && !cont_bde.equals("")){
	   		if (SearchValue.contains("where")) {
	   			SearchValue +=" and app.form_code_control = ? ";
				} else {
					SearchValue += " where app.form_code_control = ? ";
				}
	   	}else {
	   		if(!cont_div.equals("0") && !cont_div.equals("")){
	   			if (SearchValue.contains("where")) {
	       			SearchValue +=" and app.form_code_control like ? ";
	   			} else {
	   				SearchValue += " where app.form_code_control like ? ";
	   			}
		    	}else {
		    		if(!cont_corps.equals("0") && !cont_corps.equals("")){
		    			if (SearchValue.contains("where")) {
		        			SearchValue +=" and app.form_code_control like ? ";
		    			} else {
		    				SearchValue += " where app.form_code_control like ? ";
		    			}
			    	}else {
			    		if(!cont_comd.equals("-1") && !cont_comd.equals("")){
			    			if (SearchValue.contains("where")) {
			    				SearchValue +=" and app.form_code_control like ? ";
			    			} else {
			    				SearchValue += " where app.form_code_control like ? ";
			    			}
				    	}
			    	}
			    }
		    }
		if(!assets_type.equals("")){
	   		if (SearchValue.contains("where")) {
	   			SearchValue +=" and app.assets_type like ? ";
				} else {
					SearchValue +=" where app.assets_type like ? ";
				}
	   	}

		
			q="select app.cmd_unit,app.corp_unit,app.div_unit,app.bde_unit,app.unit_name,app.sus_no,app.form_code_control,app.assets_type,app.ser,app.unser from\r\n" + 
					"	(select distinct \r\n" + 
					"	fv.unit_name as cmd_unit,\r\n" + 
					"	fvm.unit_name as corp_unit,\r\n" + 
					"	div.unit_name as div_unit,\r\n" + 
					"	bde.unit_name as bde_unit,\r\n" + 
					"	orb.unit_name,\r\n" + 
					"	hld.sus_no,\r\n" + 
					"	orb.form_code_control,\r\n" + 
					"	hld.assets_type,\r\n" + 
					"	count(hld.assets_type) FILTER (where hld.codevalue='1') as ser,\r\n" + 
					"	count(hld.assets_type) FILTER (where hld.codevalue='2') as unser\r\n" + 
					"	from (\r\n" + 
					"		 select d.assets_name as assets_type,d.id as m_id,c.sus_no,td.label as s_state,td.codevalue,'IT' AS typ \r\n" + 
					"		 from it_asset_peripherals c \r\n" + 
					"		 inner join tb_mstr_assets d on  d.id = c.assets_type and c.status = '1' \r\n" + 
					"		 inner join t_domain_value td on  td.codevalue:: character varying = c.s_state:: character varying and td.domainid='SERVICE_CAT'				\r\n" + 
					"		 union all\r\n" + 
					"		 select ma.assets_name as assets_type,ma.id as m_id,j.sus_no,td1.label as s_state,td1.codevalue,'MAIN' AS typ \r\n" + 
					"		 from tb_asset_main j \r\n" + 
					"		 inner join tb_mstr_assets ma on  ma.id = j.asset_type and j.status = '1' \r\n" + 
					"		 inner join t_domain_value td1 on  td1.codevalue:: character varying = j.s_state:: character varying and td1.domainid='SERVICE_CAT'				\r\n" + 
					"	) hld \r\n" + 
					"	inner join tb_orbat_unt_dtl orb on orb.sus_no = hld.sus_no and orb.status_sus_no='Active'\r\n" + 
					"	left join all_fmn_view fv  on orb.sus_no = hld.sus_no  and SUBSTRING(orb.form_code_control, 1 ,1) =SUBSTRING(fv.form_code_control, 1, 1) and fv.level_in_hierarchy = 'Command'\r\n" + 
					"	left join all_fmn_view fvm  on orb.sus_no = hld.sus_no  and SUBSTRING(orb.form_code_control, 1 ,3) =SUBSTRING(fvm.form_code_control, 1, 3) and fvm.level_in_hierarchy = 'Corps'\r\n" + 
					"	left join all_fmn_view div  on orb.sus_no = hld.sus_no  and SUBSTRING(orb.form_code_control, 1 ,6) =SUBSTRING(div.form_code_control, 1, 6) and div.level_in_hierarchy = 'Division'\r\n" + 
					"	left join all_fmn_view bde  on orb.sus_no = hld.sus_no  and orb.form_code_control =bde.form_code_control and bde.level_in_hierarchy = 'Brigade' \r\n" + 
					"	group by  cmd_unit,corp_unit,div_unit,bde_unit,orb.unit_name,hld.sus_no,orb.form_code_control,hld.assets_type) app" + SearchValue ;
		
			stmt=conn.prepareStatement(q);
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
			if(!assets_type.equals("")) {
				flag += 1;
				stmt.setString(flag, assets_type+"%");
			}	
			
			ResultSet rs = stmt.executeQuery();   
			int i =1;  
			while (rs.next()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(String.valueOf(i++)); //0
				list.add(rs.getString("cmd_unit"));//0
				list.add(rs.getString("corp_unit"));//1
				list.add(rs.getString("div_unit"));//2
				list.add(rs.getString("bde_unit"));//3
				list.add(rs.getString("unit_name"));//4
				list.add(rs.getString("sus_no"));//5
				list.add(rs.getString("assets_type"));//6
				list.add(rs.getString("ser"));//7	
				list.add(rs.getString("unser"));//8	
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
}
