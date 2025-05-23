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


public class Budget_mstrDAOImpl implements Budget_mstrDAO {

         private DataSource dataSource;
         public void setDataSource(DataSource dataSource) {
	       this.dataSource = dataSource;
         }
 HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();



  public boolean checkIsIntegerValue(String Search) {
	return Search.matches("[0-9]+");
}


public List<Map<String, Object>> getReportListBudget_mstr(int startPage, String pageLength, String Search,
		String orderColunm, String orderType, String budget_head, String budget_code, HttpSession session)
		throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException,
		InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
	if (pageLength.equals("-1")) {
		pageLength = "ALL";
	}
	String SearchValue = GenerateQueryWhereClause_SQL(Search, budget_head, budget_code);

	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	Connection conn = null;
	String q = "";
	try {
		conn = dataSource.getConnection();
		q = "SELECT id, budget_head, budget_code\n" + "	FROM tb_mstr_budget " + SearchValue + " ORDER BY " + orderColunm
				+ " " + orderType + " limit " + pageLength + " OFFSET " + startPage;
		PreparedStatement stmt = conn.prepareStatement(q);
		stmt = setQueryWhereClause_SQL(stmt, Search, budget_head, budget_code);
		ResultSet rs = stmt.executeQuery();
		ResultSetMetaData metaData = rs.getMetaData();
		int columnCount = metaData.getColumnCount();
		while (rs.next()) {
			Map<String, Object> columns = new LinkedHashMap<String, Object>();
			for (int i = 1; i <= columnCount; i++) {
				columns.put(metaData.getColumnLabel(i), rs.getObject(i));
			}
			String enckey = "commonPwdEncKeys";
			Cipher c = hex_asciiDao.EncryptionSHA256Algo(session, enckey);
			String EncryptedPk = new String(Base64.encodeBase64(c.doFinal(rs.getString("id").toString().getBytes())));

			String deleteButton = "<i class='action_icons action_delete' " + "data-encrypted-pk='" + EncryptedPk + "' "
					+ "title='Delete Data'></i>";

			String updateButton = "<i class='action_icons action_update' " + "data-encrypted-pk='" + EncryptedPk + "' "
					+ "title='Edit Data'></i>";

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

public long getReportListBudget_mstrTotalCount(String Search,String budget_head, String budget_code) {
 		String SearchValue = GenerateQueryWhereClause_SQL(Search,budget_head,budget_code);
 		int total = 0;
 		String q = null;
 		Connection conn = null;
 		try {
 			conn = dataSource.getConnection();
 			q ="select count(*) from tb_mstr_budget  "+SearchValue ;
 			PreparedStatement stmt = conn.prepareStatement(q);
 			stmt = setQueryWhereClause_SQL(stmt,Search,budget_head,budget_code);
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


  	public String GenerateQueryWhereClause_SQL(String Search,String budget_head, String budget_code) {
 		String SearchValue ="";
 		if(!Search.equals("")) { // for Input Filter
			SearchValue =" where ( ";
			SearchValue +=" upper(budget_head) like ? or upper(budget_code) like ?)";
		}
 		if (!budget_head.equals("")) {
 			budget_head = budget_head.toUpperCase();
			if (SearchValue.contains("where")) {
				SearchValue += " and upper(budget_head) like ? ";
			} else {
				SearchValue += " where upper(budget_head) like ? ";
			}
		}
 		if (!budget_code.equals("")) {
 			budget_code = budget_code.toUpperCase();
			if (SearchValue.contains("where")) {
				SearchValue += " and upper(budget_code) like ? ";
			} else {
				SearchValue += " where upper(budget_code) like ? ";
			}
		}
 		
   return SearchValue;
 }


  public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt,String Search,String budget_head, String budget_code) {
 		int flag = 0;
 		try {
    		if(!Search.equals("")) {
    			flag += 1;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				
				flag += 1;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
 			}
    		if(!budget_head.equals("")) {
				flag += 1;
				stmt.setString(flag, budget_head.toUpperCase()+"%");
    		}
    		if(!budget_code.equals("")) {
				flag += 1;
				stmt.setString(flag, budget_code.toUpperCase()+"%");
    		}
 		}catch (Exception e) {}
 		return stmt;
 	}
   public String Deletebudget_mstr(String deleteid,HttpSession session1) {

      	Session session = HibernateUtil.getSessionFactory().openSession();
      	Transaction tx = session.beginTransaction();
      	String enckey = "commonPwdEncKeys";
		    String DcryptedPk = hex_asciiDao.decrypt((String) deleteid,enckey,session1);
	      	String hql = "Delete from TB_MASTER_BUDGET  where cast(id as string) = :deleteid";
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
   public ArrayList<ArrayList<String>> Report_DataTableMakeList(String budget_head,String budget_code){
		
		String SearchValue = GenerateQueryWhereClause_SQL1(budget_head,budget_code);
		
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			        q="SELECT id, UPPER(budget_head) AS budget_head, UPPER(budget_code) As budget_code \n" + 
			        		" FROM tb_mstr_budget"
			        		+SearchValue + " ORDER BY id";
					
			stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL1(stmt,budget_head,budget_code);
			
			ResultSet rs = stmt.executeQuery();   
			
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("id"));
				alist.add(rs.getString("budget_head"));
				alist.add(rs.getString("budget_code"));
			
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

public String GenerateQueryWhereClause_SQL1(String budget_head, String budget_code) {
		String SearchValue ="";
		
		
		if(!budget_head.equals("")) {
			budget_head = budget_head.toUpperCase();
			if(SearchValue.contains("where")) {
				SearchValue +=" and upper(budget_head) like ? ";
			}else {
				SearchValue +=" where upper(budget_head) like ? ";
			}
		}
		if (!budget_code.equals("")) {
 			budget_code = budget_code.toUpperCase();
			if (SearchValue.contains("where")) {
				SearchValue += " and upper(budget_code) like ? ";
			} else {
				SearchValue += " where upper(budget_code) like ? ";
			}
		}
		
		return SearchValue;
	}
		
	public PreparedStatement setQueryWhereClause_SQL1(PreparedStatement stmt,String budget_code, String budget_head) {
		int flag = 0;
		
		try {
   		
			if (!budget_code.equals("")) {
				flag += 1;
				stmt.setString(flag, budget_code.toUpperCase() + "%");
			}
			
			if(!budget_head.equals("")) {
				flag += 1;
				stmt.setString(flag, budget_head.toUpperCase()+"%");
			}
		
	}catch (Exception e) {}
		
		return stmt;
	}
}
