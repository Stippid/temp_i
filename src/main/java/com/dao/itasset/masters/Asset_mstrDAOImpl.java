package com.dao.itasset.masters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.models.assets.CONSUMABLES_MAIN;
import com.models.assets.It_Asset_Peripherals;
import com.persistance.util.HibernateUtil;


public class Asset_mstrDAOImpl implements Asset_mstrDAO {

         private DataSource dataSource;
         public void setDataSource(DataSource dataSource) {
	       this.dataSource = dataSource;
         }
 HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();



  public boolean checkIsIntegerValue(String Search) {
	return Search.matches("[0-9]+");
}


public List<Map<String, Object>> getReportListAsset_mstr(int startPage,String pageLength,String Search,String orderColunm,String orderType, String assets_name,String category,HttpSession session) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		if(pageLength.equals("-1")){
 			pageLength = "ALL";
		}
		String SearchValue = GenerateQueryWhereClause_SQL( Search, category,assets_name);

 		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			q = "select distinct id,assets_name,\n"
					+"CASE\n"
					+ " WHEN category=1 THEN 'COMPUTING'\n"
					+ " WHEN category=2 THEN 'PERIPHERAL'\n"
					+ " WHEN category=3 THEN 'CONSUMABLES'\n"
					+ " ELSE 'Other'\n"
					+ " END AS category\n"
					+ " from tb_mstr_assets "+SearchValue+" ORDER BY "+ orderColunm +" "+orderType +" limit " +pageLength+" OFFSET "+startPage ;
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt,  Search, category,assets_name);
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
	                    String EncryptedPk = new String(Base64.encodeBase64( c.doFinal(rs.getString("id").toString().getBytes())));
	                    

	                    String deleteButton = "<i class='action_icons action_delete' " +
	                                          "data-encrypted-pk='" + EncryptedPk + "' " +
	                                          "title='Delete Data'></i>";

	                    String updateButton = "<i class='action_icons action_update' " +
	                                          "data-encrypted-pk='" + EncryptedPk + "' " +
	                                          "title='Edit Data'></i>";

	                    String f = "";
	                    f += updateButton;
	                    f += deleteButton;
                     columns.put(metaData.getColumnLabel(1), f);
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

public long getReportListAsset_mstrTotalCount(String Search, String assets_name,String category) {
 		String SearchValue = GenerateQueryWhereClause_SQL( Search, category,assets_name);
 		int total = 0;
 		String q = null;
 		Connection conn = null;
 		try {
 			conn = dataSource.getConnection();
 			q ="select count(*) from tb_mstr_assets  "+SearchValue ;
 			PreparedStatement stmt = conn.prepareStatement(q);
 			stmt = setQueryWhereClause_SQL(stmt,  Search, category,assets_name);
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


  	
   public String Deleteasset_mstr(String deleteid,HttpSession session1) {

      	Session session = HibernateUtil.getSessionFactory().openSession();
      	Transaction tx = session.beginTransaction();
      	String enckey = "commonPwdEncKeys";
		    String DcryptedPk = hex_asciiDao.decrypt((String) deleteid,enckey,session1);
	      	String hql = "Delete from TB_MSTR_ASSETS  where cast(id as string) = :deleteid";
          Query q = session.createQuery(hql).setString("deleteid",DcryptedPk);
      	int rowCount = q.executeUpdate();
      	tx.commit();
          session.close();
	    if(rowCount > 0) {
 			return "Deleted Successfully";
  		}else {
  		return "Deleted not Successfully";
   	}
  	}
   public String GenerateQueryWhereClause_SQL(String Search,String category, String assets_name) {
		String SearchValue ="";
		if(!Search.equals("")) { // for Input Filter
			SearchValue =" where ( ";
			SearchValue +=" CASE\n" + 
					"WHEN category=1 THEN 'COMPUTING' " + 
					"WHEN category=2 THEN 'PERIPHERAL' " + 
					"ELSE 'Other'\n" + 
					"END like ? or upper(assets_name) like ? )";
		}
		
	
		if (!category.equals("0")) {
			category = category.toUpperCase();
		if (SearchValue.contains("where")) {
			SearchValue += " and cast(category as character varying) like ? ) ";
			
		} else {
				SearchValue += " where cast(category as character varying) like ?  ";
			}
		}
	

	
		if (!assets_name.equals("")) {
			assets_name = assets_name.toUpperCase();
			if (SearchValue.contains("where")) {
				SearchValue += " and upper(assets_name ) like ? ";
			} else {
				SearchValue += " where upper(assets_name ) like ? ";
			}
		}
		
		return SearchValue;
	}
		
	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt,String Search,String category, String assets_name) {
		int flag = 0;
		try {
			if(!Search.equals("")) {
				flag += 1;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				
				flag += 1;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				
			}
			
			if(!category.equals("0")) {
			flag += 1;
			stmt.setString(flag, category);
		}
			
		if(!assets_name.equals("")) {
				flag += 1;
				stmt.setString(flag, assets_name.toUpperCase()+"%");
		}
		}catch (Exception e) {}
		return stmt;
	}
	
   /* report excel */
   public ArrayList<ArrayList<String>> Report_DataTableMakeList(String assets_name,String category){
		
		String SearchValue = GenerateQueryWhereClause_SQL1(assets_name,category);
	
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			 
			        q="select distinct id,assets_name, CASE WHEN category=1 THEN 'COMPUTING'  WHEN category=2 THEN 'PERIPHERAL'  WHEN category=3 THEN 'CONSUMABLES' ELSE 'Other' END AS category from tb_mstr_assets"
			        		+SearchValue + " ORDER BY id";
					
			stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL1(stmt,assets_name,category);
		
			ResultSet rs = stmt.executeQuery();   
			
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("id"));
				alist.add(rs.getString("category"));
				alist.add(rs.getString("assets_name"));
				
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
   
   public String GenerateQueryWhereClause_SQL1( String assets_name,String category) {
		String SearchValue ="";
 		
			//SearchValue +=" UPPER(assets_name) like ? OR (CASE WHEN category=1 THEN 'COMPUTING' WHEN category=2 THEN 'PERIPHERAL' ELSE 'Other' END) LIKE ?  )";
		
 		
 		if (!assets_name.equals("")) {
 			assets_name = assets_name.toUpperCase();
			if (SearchValue.contains("where")) {
				SearchValue += " and UPPER(assets_name) like ? ";
			} else {
				SearchValue += " where UPPER(assets_name) like ? ";
			}
		}
		if (!category.equals("0")) {
			category = category.toUpperCase();
			if (SearchValue.contains("where")) {
				SearchValue += " and cast(category as character varying) like ? ";
			} else {
				SearchValue += " where cast(category as character varying) like ? ";
			}
		}
  return SearchValue;
}


 public PreparedStatement setQueryWhereClause_SQL1(PreparedStatement stmt,String assets_name,String category) {
		int flag = 0;
		try {
   		
				if (!assets_name.equals("")) {
					flag += 1;
					stmt.setString(flag, assets_name.toUpperCase() + "%");
				}
				if (!category.equals("0")) {
					flag += 1;
					stmt.setString(flag, category + "%");
				}
			
		}catch (Exception e) {}
		return stmt;
	}
	public String GenerateQueryWhereClause_SQL2(String Search,String assets_type,String year_of_manufacturing,String machine_make,String machine_no,String model_no,
			String from_date,String to_date,String status,String s_state,String section,HttpSession sessionUserId) {
		String SearchValue ="";
		
		if (!Search.equals("") || !Search.equals(null) || Search != null || Search != " ") { // for Input Filter
			SearchValue = " and ( ";
			SearchValue += " pt.assets_name like ? or upper(th.type_of_hw)  like ? or upper(ap.year_of_proc)  like ? or "
					+ " ap.year_of_manufacturing like ? "
					+ " or upper(ap.b_cost) like ? or upper(ap.machine_no) like ? or upper(ap.model_no) like ? or upper(ap.remarks) like ? "
					+ "or upper(sec.section) like ? or upper(ap.created_by) like ?   or upper(sec.section) like ? or Upper(ap.created_by) like ? "
					+ " )";
			
		}
		
				if(!status.equals(""))
				{
					SearchValue += "  and cast(ap.status as character varying) = ? ";
				}
			
				if(!assets_type.equals("0")) {
					SearchValue += "  and upper( cast(ap.assets_type as character varying)) = ? ";
				}	
				if(!year_of_manufacturing.equals("0") && !year_of_manufacturing.equals("")) {
					SearchValue += "  and cast(ap.year_of_manufacturing as character varying) = ? ";
				}		
				if(!machine_no.equals("")) { 
					SearchValue += "  and upper(ap.machine_no) like ? ";
				}			
				if (model_no !=null && !model_no.equals("")) {
					SearchValue += " and upper(ap.model_no) like ? ";
				}
				if(!from_date.equals("") && !to_date.equals(""))
				{
					SearchValue +=" and ap.created_date BETWEEN to_date(?,'DD/MM/YYYY') and  to_date(?,'DD/MM/YYYY')";
				}
				if(!s_state.equals("0")) {
					SearchValue += "  and cast(ap.s_state as character varying) = ? ";
				}
				 String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
					if (roleAccess.equals("Unit") ) {
							SearchValue += "  and ap.sus_no = ? ";
					}
					if(!section.equals("") && !section.equals("0"))
					{
						SearchValue += "  and ap.section = ? ";
					}
		
		return SearchValue;
	}
	
	public PreparedStatement setQueryWhereClause_SQL2(PreparedStatement stmt,String Search,String assets_type,String year_of_manufacturing,String machine_make,String machine_no,
			String model_no,String from_date,String to_date,String status,String s_state,String section,HttpSession sessionUserId) {
		

		
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
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");	
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");	
				
				
			}
			
			if (!status.equals("")) {
				flag += 1;
				stmt.setString(flag, status);
			}

			if (!assets_type.equals("0")) {
				flag += 1;
				stmt.setString(flag, assets_type);
			
			}
				if (!year_of_manufacturing.equals("0") && !year_of_manufacturing.equals("")) {
					flag +=1;
					stmt.setString(flag, year_of_manufacturing);
				}
			if (!machine_no.equals("")) {
				flag += 1;
				stmt.setString(flag, machine_no.toUpperCase() + "%");
				
			}
			if (model_no!=null && !model_no.equals("")) {
				flag += 1;
				stmt.setString(flag, model_no.toUpperCase() + "%");
				
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
				if(!section.equals("") && !section.equals("0"))
				{
					flag += 1;
					stmt.setString(flag, section);
				}
			 
			 
		}catch (Exception e) {
			e.printStackTrace();
		}
		return stmt;
	}
 
 public List<Map<String, Object>> Search_consumable(int startPage, int pageLength, String Search, String orderColunm,
			String orderType, String status, String assets_type, String section,String year_of_manufacturing,
			HttpSession sessionUserId) {

		String SearchValue = GenerateQueryWhereClause_SQL2(Search, assets_type,  status, section,year_of_manufacturing, sessionUserId);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";

		try {
			String pageL = "";

			if (pageLength == -1) {
				pageL = "ALL";
			} else {
				pageL = String.valueOf(pageLength);
			}
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select ap.id,pt.assets_name,ap.year_of_proc,\r\n"
					+ "ap.b_cost,ap.make_name,ap.remarks,sec.section,ap.created_by  from tb_consumable_main ap\r\n"
					+ "inner join  tb_mstr_section sec  on sec.id:: text = ap.section \r\n"
					+ "left join tb_mstr_assets pt on pt.id=ap.assets_type  and pt.category=3\r\n"
					+ "left join tb_mstr_type_of_hw th on th.id=ap.type_of_hw\r\n" +
					"where ap.id!=0 " + SearchValue + " ORDER BY " + orderColunm + " " + orderType + " limit " + pageL
					+ " OFFSET " + startPage;

			stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL2(stmt, Search, assets_type, 
					 status,  section,year_of_manufacturing, sessionUserId);
			ResultSet rs = stmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();

			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));

				}


				String checkboxId = rs.getObject(1).toString(); // Assuming this is a valid ID
				String CheckBox = "<input class='nrCheckBox3' type='checkbox' id='" + checkboxId + "' name='cbox' data-id='" + checkboxId + "' />";
				String CheckBoxid = "<input type='hidden' id='id" + checkboxId + "' name='id" + checkboxId + "' value='" + checkboxId + "' />";

				String chekboxaction = CheckBox + CheckBoxid;

				columns.put("chekboxaction", chekboxaction);

				String action = "";

				// Use data attributes instead of inline JavaScript
				String editDataAttributes = "data-id='" + rs.getInt("id") + "' data-action='edit'";
				String deleteDataAttributes = "data-id='" + rs.getInt("id") + "' data-action='delete'";
				String viewDataAttributes = "data-id='" + rs.getInt("id") + "' data-action='view'";
				String archiveDataAttributes = "data-id='" + rs.getInt("id") + "' data-action='archive'";

				if (!status.equals("1")) {
					action += "<i class='action_icons action_update' " + editDataAttributes + " title='Edit Data'></i>";
				}
				action += "<i class='action_icons action_delete' " + deleteDataAttributes + " title='Delete Data'></i>";
				action += "<i class='fa fa-eye ' " + viewDataAttributes + " title='View Data'></i>";
		
				if (!status.equals("7")) {
					action += "<i class='action_icons action_archive' " + archiveDataAttributes
							+ " title='Archive Data'></i>";
				}

				columns.put("action", action);
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
	public long getconsumableCountList(String Search,String orderColunm,String orderType,String status,String assets_type,String section,String year_of_manufacturing,HttpSession sessionUserId){
 	String SearchValue = GenerateQueryWhereClause_SQL2(Search,assets_type,status,section,year_of_manufacturing,sessionUserId);
	
		int total = 0;
		String q = "";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			q ="select count(*) "
					+ "from tb_consumable_main ap\r\n" + 
					"inner join  tb_mstr_section sec  on sec.id:: text = ap.section \r\n"+
					"left join tb_mstr_assets pt on pt.id=ap.assets_type  and pt.category=2\r\n" + 
					"left join tb_mstr_type_of_hw th on th.id=ap.type_of_hw\r\n" + 
					 " where ap.id!=0 "+ SearchValue ;

			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQL2(stmt,Search,assets_type,status,section,year_of_manufacturing,sessionUserId);
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
	public ArrayList<ArrayList<String>> getcomaseptext_network_feature(int id){
		
		ArrayList<ArrayList<String>> list1 = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select 	 array_to_string(ARRAY(select sub.network_features \n" + 
					"from unnest(string_to_array((select network_features from tb_consumable_main where id=nmk.id), ',')) qsub\n" + 
					"inner join tb_mstr_network_features sub on sub.id=cast(qsub as integer)),',') as subject\n" + 
					"from tb_consumable_main  nmk where nmk.id=?";

			stmt = conn.prepareStatement(q);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			

			while (rs.next()) {
				
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("subject"));
				list1.add(list);	
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
		return list1;
	}
	public ArrayList<ArrayList<String>> getcomaseptext_hw_interface(int id){
		
		ArrayList<ArrayList<String>> list1 = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select 	 array_to_string(ARRAY(select sub.hardware_interface \n" + 
					"from unnest(string_to_array((select hw_interface from tb_consumable_main where id=nmk.id), ',')) qsub \n" + 
					" inner join TB_MSTR_HARDWARE_INTERFACE sub on sub.id=cast(qsub as integer)),',') as hw_inf \n" + 
					" from tb_consumable_main  nmk where nmk.id=?";

			stmt = conn.prepareStatement(q);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			

			while (rs.next()) {
				
				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("hw_inf"));
				list1.add(list);		
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
		return list1;
	}
	public String GenerateQueryWhereClause_SQL2(String Search,String assets_type,
			String status,String section,String year_of_pro,HttpSession sessionUserId) {
		String SearchValue ="";
		

		if (!Search.equals("") || Search != "") { 
			SearchValue = " and ( " ;
			SearchValue += "upper(pt.assets_name)  like ?  "				
					+ " or upper(ap.year_of_proc) like ? or upper(sec.section) like ? or upper(ap.created_by) like ? or upper(ap.remarks) like ? "		
					+ " )";
			
		}
				if(!status.equals(""))
				{
					SearchValue += "  and cast(ap.status as character varying) = ? ";
				}
			
				if(!assets_type.equals("0")) {
					SearchValue += "  and upper( cast(ap.assets_type as character varying)) = ? ";
				}	
				
					if(!section.equals("") && !section.equals("0"))
					{
						SearchValue += "  and ap.section = ? ";
					}
		
					if(!year_of_pro.equals("") && !year_of_pro.equals(""))
					{
						SearchValue += "  and ap.year_of_proc = ? ";
					}
		return SearchValue;
	}
	
	
	public PreparedStatement setQueryWhereClause_SQL2(PreparedStatement stmt,String search,String assets_type,
			String status,String section,String year_of_pro,HttpSession sessionUserId) {
		

		
		int flag = 0;
		try {
			
			if (!search.equals("") || search !="") {
				
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + search.toUpperCase() + "%");
				
			}
			
			
			if (!status.equals("")) {
				flag += 1;
				stmt.setString(flag, status);
			}

			if (!assets_type.equals("0")) {
				flag += 1;
				stmt.setString(flag, assets_type);
			
			}
			
				if(!section.equals("") && !section.equals("0"))
				{
					flag += 1;
					stmt.setString(flag, section);
				}
				
				if(!year_of_pro.equals("") && !year_of_pro.equals("0"))
				{
					flag += 1;
					stmt.setString(flag, year_of_pro);
				}
				
				
				
				
			 
			 
		}catch (Exception e) {
			e.printStackTrace();
		}
		return stmt;
	}
	

	public PreparedStatement setQueryWhereClause_SQL2(PreparedStatement stmt,String assets_type,
			String status,String section,String year_of_manufacturing,HttpSession sessionUserId) {
		

		
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
			
				if(!section.equals("") && !section.equals("0"))
				{
					flag += 1;
					stmt.setString(flag, section);
				}
				if(!year_of_manufacturing.equals("") && !year_of_manufacturing.equals("0"))
				{
					flag += 1;
					stmt.setString(flag, year_of_manufacturing);
				}
				
			 
		}catch (Exception e) {
			e.printStackTrace();
		}
		return stmt;
	}
	
	
	
	@Override
	public ArrayList<ArrayList<String>> Excel_Consumables_Assets_Search(HttpSession session, String assets_type,
			String status, String section, String year_of_manufacturing
			) {

//			
			
		String SearchValue = GenerateQueryWhereClause_SQL2("",assets_type,status,section,year_of_manufacturing,session);
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q="";
		
			try{	
				String pageL = "";
		        
		      
					conn = dataSource.getConnection();			 
					PreparedStatement stmt=null;
			
				q="select ap.id,pt.assets_name,ap.s_state,th.type_of_hw,ap.year_of_proc,ap.crv_no,\r\n" + 
						"ap.year_of_manufacturing,ap.b_cost,make.make_name,ap.machine_no,ap.model_no,ap.remarks,sec.section,ap.created_by  from tb_consumable_main ap\r\n" + 
						"inner join  tb_mstr_section sec  on sec.id:: text = ap.section \r\n"+
						"left join tb_mstr_assets pt on pt.id=ap.assets_type  and pt.category=3\r\n" + 
						"left join tb_mstr_type_of_hw th on th.id=ap.type_of_hw\r\n" + 
						"left join tb_mstr_make make on make.id=ap.make_name and make.category=3 "+
						 "where ap.id!=0 "+ SearchValue;
				
				stmt=conn.prepareStatement(q);
				stmt = setQueryWhereClause_SQL2(stmt,assets_type,status,section,year_of_manufacturing,session);
				ResultSet rs = stmt.executeQuery();   
				while (rs.next()) {
					
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("assets_name"));// 0
					list.add(rs.getString("crv_no"));// 0
					list.add(rs.getString("year_of_proc"));// 2
					list.add(rs.getString("make_name"));// 9
					list.add(rs.getString("section"));// 9
					list.add(rs.getString("created_by"));// 11
				list.add(rs.getString("remarks"));// 12
				
				
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
	public String getQRnumber(String type, int makeBrand, int model, int assetType, String tableName) {
	    String q = "";
	    String value = "";
	    String newRedgno = "";

	    // Use try-with-resources for automatic resource management
	    try (Connection conn = dataSource.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(
	            "SELECT \r\n"
	            + "    CASE \r\n"
	            + "        WHEN MAX(redgno) IS NULL THEN CONCAT(EXTRACT(YEAR FROM CURRENT_DATE)::TEXT, \r\n"
	            + "                         LPAD(EXTRACT(MONTH FROM CURRENT_DATE)::TEXT, 2, '0'), \r\n"
	            + "                         '0001')\r\n"
	            + "        ELSE (MAX(CAST(SUBSTRING(redgno FROM LENGTH(redgno) - 9 FOR 10) AS INTEGER)) + 1)::text \r\n"
	            + "    END AS new_redgno\r\n"
	            + "FROM  \r\n"+  tableName
	            + "  WHERE SUBSTRING(redgno FROM LENGTH(redgno) - 9 FOR 10) LIKE CONCAT(EXTRACT(YEAR FROM CURRENT_DATE)::TEXT, \r\n"
	            + "                         LPAD(EXTRACT(MONTH FROM CURRENT_DATE)::TEXT, 2, '0'), \r\n"
	            + "                         '%')")) {

	        ResultSet rs = stmt.executeQuery();

	        if (rs.next()) {
	            // Use getObject(1) instead o					 select * from tb_asset_mainf getObject(0)
	            if (rs.getObject(1) != null && !rs.getObject(1).toString().isEmpty()) {
	                newRedgno = rs.getObject(1).toString();
	            } else {
	                LocalDate currentDate = LocalDate.now();
	                String year = String.valueOf(currentDate.getYear());
	                String month = String.format("%02d", currentDate.getMonthValue());
	                newRedgno = year + month + "0001";
	            }
	        }

	        // Uncomment this line if you want to format the value
	        value = String.format("%s/%s/%s/%s/%s", type, assetType, makeBrand, model, newRedgno);
	        //value = newRedgno;

	    } catch (SQLException e) {
	  
	        e.printStackTrace(); 
	    } catch (Exception e) {
	        
	        e.printStackTrace(); 
	    }

	    return value;
	}
	public String approve_consumable_AssetsData(String a,String user_sus,String status,String username) {
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
					 CONSUMABLES_MAIN assetupd =(CONSUMABLES_MAIN)sessionHQL.get(CONSUMABLES_MAIN.class, id);
					 
					 String redgno=getQRnumber( "CONSUMABLE", assetupd.getMake_name() , assetupd.getModel_name(), assetupd.getAssets_type() , "tb_consumable_main");
					
					stmt = conn.prepareStatement("update tb_consumable_main set status=?,redgno=? where id=?");
					stmt.setInt(1, Integer.parseInt(status));
					stmt.setString(2,redgno );
					stmt.setInt(3, id);
					out = stmt.executeUpdate();
					
					
					
					
						PreparedStatement stmt1=null;
						
						q="update tb_consumable_child set status=? where p_id=?";
						stmt1 = conn.prepareStatement(q);
						stmt1.setInt(1, Integer.parseInt(status));
						stmt1.setInt(2, id);
						out  = stmt1.executeUpdate();   
						
 
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
	
	
	@Override
	public ArrayList<ArrayList<String>> Excel_issue_Consumables_Assets_Search(HttpSession session, String section,
	        String assets_type, String crv_no, String status) {

	    ArrayList<ArrayList<String>> alist = new ArrayList<>();
	    Connection conn = null;
	    String q = "";

	    try {
	        conn = dataSource.getConnection();
	        PreparedStatement stmt = null;

	        q = "SELECT\n"
	                + "sec.section as section,\n"
	                + "pt.assets_name as aname,\n"
	                + "make.make_name as mname,\n"
	                + "model.model_name as model_name,\n"
	                + "MAIN.assetcount as assetcount,\n"
	                + "MAIN.crv_no as crv_no,\n"
	                + "CHILD.*, MAIN.crv_no, MAIN.assetcount,\n"
	                + "CHILD.ID AS CH_ID\n"
	                + "FROM\n"
	                + "TB_CONSUMABLE_MAIN MAIN\n"
	                + "INNER JOIN TB_CONSUMABLE_CHILD CHILD ON MAIN.ID = CHILD.P_ID\n"
	                + "INNER JOIN tb_mstr_section sec ON sec.id::text = MAIN.section\n"
	                + "LEFT JOIN tb_mstr_assets pt ON pt.id = MAIN.assets_type AND pt.category = 3\n"
	                + "LEFT JOIN tb_mstr_make make ON make.id = MAIN.make_name AND make.category = 3\n"
	                + "LEFT JOIN tb_mstr_model model ON model.id = MAIN.model_name AND model.category = 3\n"
	                + "WHERE\n"
	                + "MAIN.STATUS::TEXT = '1'";

	        String SearchValue = GenerateQueryWhereClause_SQL3("", section, assets_type, crv_no, status, session);
	        q += SearchValue; // Append the WHERE clause

	        stmt = conn.prepareStatement(q);
	        stmt = setQueryWhereClause_SQL3(stmt,"", section, assets_type, crv_no, status, session);

	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	            ArrayList<String> list = new ArrayList<>();
	            list.add(rs.getString("section")); // 0
	            list.add(rs.getString("aname")); // 1
	            list.add(rs.getString("mname")); // 2
	            list.add(rs.getString("model_name")); // 3
	            list.add(rs.getString("assetcount")); // 4
	            list.add(rs.getString("crv_no")); // 5

	            alist.add(list);
	        }
	        rs.close();
	        stmt.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        if (conn != null) {
	            try {
	                conn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }

	    return alist;
	}

	private String GenerateQueryWhereClause_SQL3(String Search, String assets_type, String section, String crv_no, String status, HttpSession sessionUserId) {
	    StringBuilder searchValue = new StringBuilder();

	    if (Search != null && !Search.trim().isEmpty()) { // for Input Filter
	        searchValue.append(" AND ( ");
	        searchValue.append(" pt.assets_name ILIKE ? OR ")
	                   .append(" make.make_name ILIKE ? OR ")
	                   .append(" cast(MAIN.assetcount as character varying) ILIKE ? OR ")
	                   .append(" MAIN.crv_no ILIKE ? "); // Assuming crv_no is a field in MAIN
	        searchValue.append(" ) ");
	    }

	    if (status != null && !status.isEmpty()) {
	        searchValue.append(" AND cast(MAIN.status as character varying) = ? ");
	    }

	    if (assets_type != null && !assets_type.equals("0")) {
	        searchValue.append(" AND upper(cast(MAIN.assets_type as character varying)) = ? ");
	    }

	    if (section != null && !section.isEmpty() && !section.equals("0")) {
	        searchValue.append(" AND MAIN.section = ? ");
	    }
		if(!crv_no.equals("0") && !crv_no.equals("")) {
			searchValue .append(" AND MAIN.crv_no = ? ");
		}

	    return searchValue.toString();
	}
	
	private PreparedStatement setQueryWhereClause_SQL3(PreparedStatement stmt, String Search, String assets_type, String section, String crv_no, String status, HttpSession sessionUserId) throws SQLException {
	    int index = 1;

	    if (Search != null && !Search.trim().isEmpty()) {
	        String upperSearch = "%" + Search.toUpperCase() + "%";
	        stmt.setString(index++, upperSearch); // For assets_name
	        stmt.setString(index++, upperSearch); // For make_name
	        stmt.setString(index++, upperSearch); // For crv_no
	        stmt.setString(index++, upperSearch);
	    }

	    if (status != null && !status.isEmpty()) {
	        stmt.setString(index++, status);
	    }

	    if (assets_type != null && !assets_type.equals("0")) {
	        stmt.setString(index++, assets_type);
	    }

	    if (section != null && !section.isEmpty() && !section.equals("0")) {
	        stmt.setString(index++, section);
	    }
	    if(!crv_no.equals("0") && !crv_no.equals("")) {
	    	  stmt.setString(index++, crv_no);
		}


	    return stmt;
	}
	
	
	
	
	
			public List<Map<String, Object>> Search_issue_consumable(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String section, String assets_type, String crv_no, String status,
			HttpSession sessionUserId) {

			String SearchValue = GenerateQueryWhereClause_SQL3(Search, section, assets_type, crv_no, status, sessionUserId);
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";

			try {
			String pageL = "";

			if (pageLength == -1) {
			pageL = "ALL";
			} else {
			pageL = String.valueOf(pageLength);
			}
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "SELECT\n"
			+"pt.assets_name as aname,\n"
			+"make.make_name as mname,\n"
			+ " *,\n"
			+ " CHILD.ID AS CH_ID\n"
			+ "FROM\n"
			+ " TB_CONSUMABLE_MAIN MAIN\n"
			+ " INNER JOIN TB_CONSUMABLE_CHILD CHILD ON MAIN.ID = CHILD.P_ID\n"
			+ " inner join     tb_mstr_section sec     on sec.id:: text = MAIN.section \n"
			+ " left join tb_mstr_assets pt on pt.id=MAIN.assets_type     and pt.category=3\n"
			+ " left join tb_mstr_make make on make.id=MAIN.make_name and make.category=3\n"
			+ " left join tb_mstr_model model on model.id=MAIN.model_name and model.category=3\n"
			+ "WHERE\n"
			+ " MAIN.STATUS::TEXT = '1'\n"
			+   SearchValue
			+ " ORDER BY " + orderColunm + " " + orderType + " limit " + pageL + " OFFSET " + startPage;


			stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL3(stmt, Search, section, assets_type, crv_no, status, sessionUserId);
			ResultSet rs = stmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
			      Map<String, Object> columns = new LinkedHashMap<String, Object>();
			      for (int i = 1; i <= columnCount; i++) {
			              columns.put(metaData.getColumnLabel(i), rs.getObject(i));
			      }

			      String action = "";

			      if (status.equals("0")) {
			              // Status 0: Show all buttons
			              action += "<i class='action_icons action_update' data-id='" + rs.getInt("ch_id") + "' data-chid='"
			                                      + rs.getInt("ch_id") + "' title='Edit Data'></i>";
			              action += "<i class='action_icons action_delete' data-id='" + rs.getInt("ch_id")
			                              + "' title='Delete Data'></i>";
			              action += "<i class='action_icons action_approve' data-id='" + rs.getInt("ch_id")
			                              + "' title='Approve Data'></i>";
			              action += "<i class='action_icons action_reject' data-id='" + rs.getInt("ch_id")
			                              + "' title='Reject Data'></i>";
			      } else if (status.equals("1")) {
			              action += "<i class='action_icons action_view' data-id='" + rs.getInt("ch_id") + "' title='View Data'></i>";
			            
			      } else if (status.equals("3")) {
			              action += "<i class='action_icons action_update' data-id='" + rs.getInt("ch_id") + "' data-chid='"
			                              + rs.getInt("ch_id") + "' title='Edit Data'></i>";
			              action += "<i class='action_icons action_view' data-id='" + rs.getInt("ch_id") + "' title='View Data'></i>";
			      }

			      columns.put("action", action);
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

			public long getissueconsumableCountList(String Search, String orderColunm, String orderType, String section,
			String assets_type, String crv_no, String status, HttpSession sessionUserId) {
			String SearchValue = GenerateQueryWhereClause_SQL3(Search, section, assets_type, crv_no, status, sessionUserId);

			int total = 0;
			String q = "";
			Connection conn = null;
			try {
			conn = dataSource.getConnection();
			q = "SELECT COUNT(*)\n"
			      + "FROM\n"
			      + "       TB_CONSUMABLE_MAIN MAIN\n"
			      + "       INNER JOIN TB_CONSUMABLE_CHILD CHILD ON MAIN.ID = CHILD.P_ID\n"
			      + "       INNER JOIN TB_MSTR_SECTION SEC ON SEC.ID::TEXT = MAIN.SECTION\n"
			      + "       LEFT JOIN TB_MSTR_ASSETS PT ON PT.ID = MAIN.ASSETS_TYPE AND PT.CATEGORY = 3\n"
			      + "       LEFT JOIN TB_MSTR_MAKE MAKE ON MAKE.ID = MAIN.MAKE_NAME AND MAKE.CATEGORY = 3\n"
			      +"         LEFT JOIN TB_MSTR_MODEL MODEL ON MODEL.ID = MAIN.MODEL_NAME AND MODEL.CATEGORY = 3\n"
			      + "WHERE\n"
			      + "       MAIN.STATUS::TEXT = '1'\n"
			      + "       \n"
			      +       SearchValue;
			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL3(stmt, Search, section, assets_type, crv_no, status, sessionUserId);
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

}
