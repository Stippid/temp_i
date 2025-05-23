package com.dao.itasset.masters;
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
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import org.apache.commons.codec.binary.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.models.itasset.assets.Assets_Main;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.servlet.http.HttpSession;


public class Type_mstrDAOImpl implements Type_mstrDAO {

         private DataSource dataSource;
         public void setDataSource(DataSource dataSource) {
	       this.dataSource = dataSource;
         }
 HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();



  public boolean checkIsIntegerValue(String Search) {
	return Search.matches("[0-9]+");
}


public List<Map<String, Object>> getReportListType_mstr(int startPage,String pageLength,String Search,String orderColunm,String orderType,String peripheral_type,String type_of_hw,String type,HttpSession session) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		if(pageLength.equals("-1")){
 			pageLength = "ALL";
		}
		String SearchValue = GenerateQueryWhereClause_SQL(Search,peripheral_type,type_of_hw,type);

 		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			q = "select distinct t.id,p.assets_name,h.type_of_hw,t.type from tb_mstr_type t\n" + 
					"				inner join tb_mstr_assets p on p.id=t.peripheral_type \n" + 
					"				inner join tb_mstr_type_of_hw h on t.type_of_hw=h.id "+SearchValue+" ORDER BY "+ orderColunm +" "+orderType +" limit " +pageLength+" OFFSET "+startPage ;
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt,Search,peripheral_type,type_of_hw,type);
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
						String encryptedPk = new String(
								Base64.encodeBase64(c.doFinal(rs.getString("id").toString().getBytes())));

						String deleteButton = "<i class='action_icons action_delete' " + "data-id='"
								+ encryptedPk + "' " + "title='Delete Data'></i>";

						String updateButton = "<i class='action_icons action_update' " + "data-id='"
								+ encryptedPk + "' " + "title='Edit Data'></i>";
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

public long getReportListType_mstrTotalCount(String Search,String peripheral_type,String type_of_hw,String type) {
 		String SearchValue = GenerateQueryWhereClause_SQL(Search,peripheral_type,type_of_hw,type);
 		int total = 0;
 		String q = null;
 		Connection conn = null;
 		try {
 			conn = dataSource.getConnection();
 			q ="select distinct t.id,p.assets_name,h.type_of_hw,t.type from tb_mstr_type t \n" + 
 					"inner join tb_mstr_assets p on p.id=t.peripheral_type \n" + 
 					"inner join tb_mstr_type_of_hw h on t.type_of_hw=h.id"+SearchValue ;
 			q = "select count(counts.*) from (select distinct t.id,p.assets_name,h.type_of_hw,t.type from tb_mstr_type t\n" + 
					"				inner join tb_mstr_assets p on p.id=t.peripheral_type \n" + 
					"				inner join tb_mstr_type_of_hw h on t.type_of_hw=h.id "+SearchValue +") counts" ;
		
 			PreparedStatement stmt = conn.prepareStatement(q);
 			stmt = setQueryWhereClause_SQL(stmt,Search,peripheral_type,type_of_hw,type);
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

public String GenerateQueryWhereClause_SQL(String Search,String peripheral_type,String type_of_hw,String type) {
	String SearchValue ="";
	if(!Search.trim().equals("")) { // for Input Filter
		SearchValue =" where ( ";
		SearchValue +=" cast(p.assets_name as character varying) like ? OR UPPER(h.type_of_hw) like ? OR UPPER(t.type) like ? ) ";
	}
	
	if (!peripheral_type.equals("0")) {
		if (SearchValue.contains("where")) {
			
			SearchValue += " and cast(t.peripheral_type as character varying) like ? ";
		} else {
			
			SearchValue += " where cast(t.peripheral_type as character varying) like ?";
		}
	}
	if (!type_of_hw.equals("0")) {
		if (SearchValue.contains("where")) {
			
			SearchValue += " and cast(t.type_of_hw as character varying) like ? ";
		} else {
			
			SearchValue += " where cast(t.type_of_hw as character varying) like ?";
		}
	}
	if (!type.equals("")) {
		
		if (SearchValue.contains("where")) {
			SearchValue += " and upper(t.type) like ? ";
		} else {
			SearchValue += " where upper(t.type) like ? ";
		}
	}
	
	
	return SearchValue;
}
	
public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt,String Search,String peripheral_type, String type_of_hw,String type) {
	int flag = 0;
	try {
		if(!Search.equals("")) {
			flag += 1;
			stmt.setString(flag, "%"+Search.toUpperCase()+"%");
		
			flag += 1;
			stmt.setString(flag, "%"+Search.toUpperCase()+"%");
			
			flag += 1;
			stmt.setString(flag, "%"+Search.toUpperCase()+"%");
		}
		
		
		if(!peripheral_type.equals("0")) {
			flag += 1;
			stmt.setString(flag, peripheral_type);
		}
		if(!type_of_hw.equals("0")) {
			flag += 1;
			stmt.setString(flag, type_of_hw);
		}
		if(!type.equals("")) {
			flag += 1;
			stmt.setString(flag, type.toUpperCase()+"%");
	}
	}catch (Exception e) {}
	return stmt;
}
  
   public String Deletetype_mstr(String deleteid,HttpSession session1) {

      	Session session = HibernateUtil.getSessionFactory().openSession();
      	Transaction tx = session.beginTransaction();
      	String enckey = "commonPwdEncKeys";
		    String DcryptedPk = hex_asciiDao.decrypt((String) deleteid,enckey,session1);
	      	String hql = "Delete from TB_MSTR_TYPE_M  where cast(id as string) = :deleteid";
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
   public ArrayList<ArrayList<String>> Report_DataTableMakeList(String peripheral_type,String type_of_hw,String type){
		
		String SearchValue = GenerateQueryWhereClause_SQL1(peripheral_type,type_of_hw,type);
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			 
			        q=" select distinct t.id,UPPER(p.assets_name) AS assets_name,UPPER(h.type_of_hw)AS type_of_hw,t.type from tb_mstr_type t "  + 
			        		"		inner join tb_mstr_assets p on p.id=t.peripheral_type "  + 
			        		"				inner join tb_mstr_type_of_hw h on t.type_of_hw=h.id "
			        		+SearchValue + " ORDER BY id";
					
			stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL1(stmt,peripheral_type,type_of_hw,type);
			
			ResultSet rs = stmt.executeQuery();   
			
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("id"));
				alist.add(rs.getString("assets_name"));
				alist.add(rs.getString("type_of_hw"));
				alist.add(rs.getString("type"));
				
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
   
	public String GenerateQueryWhereClause_SQL1(String peripheral_type,String type_of_hw,String type) {
 		String SearchValue ="";
 		if (!peripheral_type.equals("0")) {
 			if (SearchValue.contains("where")) {
 				
 				SearchValue += " and cast(t.peripheral_type as character varying) like ? ";
 			} else {
 				
 				SearchValue += " where cast(t.peripheral_type as character varying) like ?";
 			}
 		}
 		if (!type_of_hw.equals("0")) {
 			if (SearchValue.contains("where")) {
 				
 				SearchValue += " and cast(t.type_of_hw as character varying) like ? ";
 			} else {
 				
 				SearchValue += " where cast(t.type_of_hw as character varying) like ?";
 			}
 		}
 		if (!type.equals("")) {
 			
 			if (SearchValue.contains("where")) {
 				SearchValue += " and upper(t.type) like ? ";
 			} else {
 				SearchValue += " where upper(t.type) like ? ";
 			}
 		}
  		
   return SearchValue;
 }


  public PreparedStatement setQueryWhereClause_SQL1(PreparedStatement stmt,String peripheral_type,String type_of_hw,String type) {
 		int flag = 0;
 		try {
 			if(!peripheral_type.equals("0")) {
 				flag += 1;
 				stmt.setString(flag, peripheral_type);
 			}
 			if(!type_of_hw.equals("0")) {
 				flag += 1;
 				stmt.setString(flag, type_of_hw);
 			}
 			if(!type.equals("")) {
 				flag += 1;
 				stmt.setString(flag, type.toUpperCase()+"%");
 		}
 		}catch (Exception e) {}
 		return stmt;
 	}
  
  
  
  
  
  ///////other/////////////////
  
  
  
  public List<Map<String, Object>> getReportListType_other_mstr(int startPage,String pageLength,String Search,String orderColunm,String orderType,String peripheral_type,String type_of_hw,String type,String status,HttpSession session) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		if(pageLength.equals("-1")){
			pageLength = "ALL";
		}
		String SearchValue = GenerateQueryWhereClause_SQL(Search,peripheral_type,type_of_hw,type,status);

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			q = "select distinct t.id,p.assets_name,h.type_of_hw,t.type from tb_mstr_type t\n" + 
					"				inner join tb_mstr_assets p on p.id=t.peripheral_type \n" + 
					"				inner join tb_mstr_type_of_hw h on t.type_of_hw=h.id "+SearchValue+" ORDER BY "+ orderColunm +" "+orderType +" limit " +pageLength+" OFFSET "+startPage ;
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt,Search,peripheral_type,type_of_hw,type,status);
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
	                    String checkboxIdValue = rs.getObject(1).toString(); 

	                 String checkbox = "<input class='nrCheckBox' type='checkbox' id='" + checkboxIdValue + 
	                                   "' name='cbox' data-id='" + checkboxIdValue + "' />"; 

	                 String checkboxId = "<input type='hidden' id='id" + checkboxIdValue + 
	                                     "' name='id" + checkboxIdValue + 
	                                     "' value='" + checkboxIdValue + "' />";
		  		  
		  		String f = "";
				String f1 = "";
				String action = "";
		  		 String chekboxaction="";
		  		 
				String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Information ?') ){deleteData("
						+ rs.getInt("id") + ")}else{ return false;}\"";
				f1 = "<i class='action_icons action_delete' " + Delete1 + " title='Delete Data'></i>";

		  		 if (status.equals("1")) {
					 columns.put("action",f1);//15
					 action+=f1;
					}
		  		  
		  		else {
		  			chekboxaction+=checkbox;
				      chekboxaction+=checkboxId;
		  		 }		     
		  		 
		  		columns.put("chekboxaction", chekboxaction);
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

  

  
  
  public long getReportListType_other_mstrTotalCount(String Search,String peripheral_type,String type_of_hw,String type,String status) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search,peripheral_type,type_of_hw,type,status);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			q ="select count(a.*) from ( select distinct t.id,p.assets_name,h.type_of_hw,t.type from tb_mstr_type t \n" + 
					"inner join tb_mstr_assets p on p.id=t.peripheral_type \n" + 
					"inner join tb_mstr_type_of_hw h on t.type_of_hw=h.id "+SearchValue +" ) as a";
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt,Search,peripheral_type,type_of_hw,type,status);
		
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

public String GenerateQueryWhereClause_SQL(String Search,String peripheral_type,String type_of_hw,String type,String status) {
	String SearchValue ="";
	if(!Search.trim().equals("")) { // for Input Filter
		SearchValue =" where ( ";
		SearchValue +=" cast(p.assets_name as character varying) like ? OR UPPER(h.type_of_hw) like ? OR UPPER(t.type) like ? ) ";
	}
	
	if (!status.equals("")) {
		if (SearchValue.contains("where")) {
			
			SearchValue += " and cast(t.status as character varying) like ? ";
		} else {
			
			SearchValue += " where cast(t.status as character varying) like ?";
		}
	}
	
	
	if (!peripheral_type.equals("0")) {
		if (SearchValue.contains("where")) {
			
			SearchValue += " and cast(t.peripheral_type as character varying) like ? ";
		} else {
			
			SearchValue += " where cast(t.peripheral_type as character varying) like ?";
		}
	}
	if (!type_of_hw.equals("0")) {
		if (SearchValue.contains("where")) {
			
			SearchValue += " and cast(t.type_of_hw as character varying) like ? ";
		} else {
			
			SearchValue += " where cast(t.type_of_hw as character varying) like ?";
		}
	}
	if (!type.equals("")) {
		
		if (SearchValue.contains("where")) {
			SearchValue += " and upper(t.type) like ? ";
		} else {
			SearchValue += " where upper(t.type) like ? ";
		}
	}
	
	
	return SearchValue;
}
	
public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt,String Search,String peripheral_type, String type_of_hw,String type,String status) {
	int flag = 0;
	try {
		if(!Search.equals("")) {
			flag += 1;
			stmt.setString(flag, "%"+Search.toUpperCase()+"%");
		
			flag += 1;
			stmt.setString(flag, "%"+Search.toUpperCase()+"%");
			
			flag += 1;
			stmt.setString(flag, "%"+Search.toUpperCase()+"%");
		}
		
		
		if(!status.equals("")) {
			flag += 1;
			stmt.setString(flag, status);
		}
		
		if(!peripheral_type.equals("0")) {
			flag += 1;
			stmt.setString(flag, peripheral_type);
		}
		if(!type_of_hw.equals("0")) {
			flag += 1;
			stmt.setString(flag, type_of_hw);
		}
		if(!type.equals("")) {
			flag += 1;
			stmt.setString(flag, type.toUpperCase()+"%");
	}
	}catch (Exception e) {}
	return stmt;
}
  
  
  
public String approve_type_of_hw_model_type_Data(String a,String user_sus,String status,String username) {
	String[] id_list = a.split(":");

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
				stmt = conn.prepareStatement("update tb_mstr_type set status=? where id=?");
				
				
				stmt.setInt(1, Integer.parseInt(status));
				stmt.setInt(2, id);
				out = stmt.executeUpdate();
				
			
				
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
public String reject_type_of_hw_model_type_Data(String a) {
	String[] id_list = a.split(":");

	Connection conn = null;
	Integer out = 0;
	String q = "";


	try {
		conn = dataSource.getConnection();
		PreparedStatement stmt = null;

		
			for (int i = 0; i < id_list.length; i++) {
				int id = Integer.parseInt(id_list[i]);

				 Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
//				Assets_Main assetupd =(Assets_Main)sessionHQL.get(Assets_Main.class, id);
				stmt = conn.prepareStatement("delete from tb_mstr_type  where id=?");
				
				stmt.setInt(1, id);
				out = stmt.executeUpdate();
				
			
				
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
	return "Data Deleted Successfully.";
}
 
  
  
  
}
