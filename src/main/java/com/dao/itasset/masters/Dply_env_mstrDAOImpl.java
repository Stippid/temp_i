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
import com.persistance.util.HibernateUtil;


public class Dply_env_mstrDAOImpl implements Dply_env_mstrDAO {

         private DataSource dataSource;
         public void setDataSource(DataSource dataSource) {
	       this.dataSource = dataSource;
         }
 HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();



  public boolean checkIsIntegerValue(String Search) {
	return Search.matches("[0-9]+");
}


public List<Map<String, Object>> getReportListDply_env_mstr(int startPage,String pageLength,String Search,String orderColunm,String orderType,String dply_env,HttpSession session) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		if(pageLength.equals("-1")){
 			pageLength = "ALL";
		}
		String SearchValue = GenerateQueryWhereClause_SQL(Search,dply_env);

 		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			q = "select distinct id,dply_env from tb_mstr_dply_env "+SearchValue+" ORDER BY "+ orderColunm +" "+orderType +" limit " +pageLength+" OFFSET "+startPage ;
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt,Search,dply_env);
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
						

						String deleteButton = "<i class='action_icons action_delete' " + "data-encrypted-pk='"
								+ EncryptedPk + "' " + "title='Delete Data'></i>";

						String updateButton = "<i class='action_icons action_update' " + "data-encrypted-pk='"
								+ EncryptedPk + "' " + "title='Edit Data'></i>";

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

public long getReportListDply_env_mstrTotalCount(String Search,String dply_env) {
 		String SearchValue = GenerateQueryWhereClause_SQL(Search,dply_env);
 		int total = 0;
 		String q = null;
 		Connection conn = null;
 		try {
 			conn = dataSource.getConnection();
 			q ="select count(*) from tb_mstr_dply_env  "+SearchValue ;
 			PreparedStatement stmt = conn.prepareStatement(q);
 			stmt = setQueryWhereClause_SQL(stmt,Search,dply_env);
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

public String GenerateQueryWhereClause_SQL(String Search,String dply_env) {
	String SearchValue = "";
	
	if(!Search.equals("")) { // for Input Filter
		SearchValue =" where ( ";
		SearchValue +=" upper(dply_env) like ? )";
	}
		if (!dply_env.equals("")) {
			dply_env = dply_env.toUpperCase();
		if (SearchValue.contains("where")) {
			SearchValue += " and upper(dply_env) like ? ";
		} else {
			SearchValue += " where upper(dply_env) like ? ";
		}
	}
		
	
	return SearchValue;
}

public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search,String dply_env) {
	int flag = 0;
	try {
		
		if (!Search.equals("")) {
			flag += 1;
			stmt.setString(flag, "%"+Search.toUpperCase()+"%");
			
		}
		if(!dply_env.equals("")) {
			flag += 1;
			stmt.setString(flag, dply_env.toUpperCase()+"%");
		}
	} catch (Exception e) {
	}
	
	return stmt;
}

  	
   public String Deletedply_env_mstr(String deleteid,HttpSession session1) {

      	Session session = HibernateUtil.getSessionFactory().openSession();
      	Transaction tx = session.beginTransaction();
      	String enckey = "commonPwdEncKeys";
		    String DcryptedPk = hex_asciiDao.decrypt((String) deleteid,enckey,session1);
	      	String hql = "Delete from TB_MSTR_DPLY_ENV_M  where cast(id as string) = :deleteid";
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
   public ArrayList<ArrayList<String>> Report_DataTableMakeList(String dply_env){
		
		String SearchValue = GenerateQueryWhereClause_SQL1(dply_env);
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			        q="select distinct id,dply_env from tb_mstr_dply_env"
			        		+SearchValue + " ORDER BY id";
					
			stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL1(stmt,dply_env);
		
			ResultSet rs = stmt.executeQuery();   
			
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("id"));
				alist.add(rs.getString("dply_env"));
				
				
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
   public String GenerateQueryWhereClause_SQL1(String dply_env) {
		String SearchValue ="";
		if (!dply_env.equals("")) {
			dply_env = dply_env.toUpperCase();
			if (SearchValue.contains("where")) {
				SearchValue += " and UPPER(dply_env) like ? ";
			} else {
				SearchValue += " where UPPER(dply_env) like ? ";
			}
		}
  return SearchValue;
}


 public PreparedStatement setQueryWhereClause_SQL1(PreparedStatement stmt,String dply_env) {
		int flag = 0;
		try {

				if (!dply_env.equals("")) {
					flag += 1;
					stmt.setString(flag, dply_env.toUpperCase() + "%");
				}
		}catch (Exception e) {}
		return stmt;
	}

}
