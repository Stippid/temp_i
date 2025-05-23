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

public class RANKDAOImpl implements RANKDAO {
	   private DataSource dataSource;
       public void setDataSource(DataSource dataSource) {
	       this.dataSource = dataSource;
       }
HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	public String GenerateQueryWhereClause_SQL(String Search,String rank, String status) {
		String SearchValue = "";
		
		if(!Search.equals("")) { // for Input Filter
			SearchValue =" where  (";
			SearchValue +=" upper(rank) like ? OR (status) like ?)";
		}
	  
			if (!rank.equals("")) {
				rank = rank.toUpperCase();
			if (SearchValue.contains("where")) {
				SearchValue += " and upper(rank) like ? ";
			} else {
				SearchValue += " where upper(rank) like ? ";
			}
		}
			if (!status.equals("0")) {
			if (SearchValue.contains("where")) {
				SearchValue += " and upper(status) like ? ";
			} else {
				SearchValue += " where upper(status) like ? ";
			}
		}
		
		return SearchValue;
	}
	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search,String rank, String status) {
		int flag = 0;
		try {
			
			if (!Search.equals("")) {
				flag += 1;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				
				flag += 1;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				
			}
			if(!rank.equals("")) {
				flag += 1;
				stmt.setString(flag, rank.toUpperCase()+"%");
			}
			if(!status.equals("0")) {
				flag += 1;
				stmt.setString(flag, status+"%");
			}
		} catch (Exception e) {
		}
		
		return stmt;
	}
	
	
	
	
	
	public List<Map<String, Object>> getReportListRANK(int startPage,String pageLength,String Search,String orderColunm,String orderType,String rank,String status,HttpSession session) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		if(pageLength.equals("-1")){
 			pageLength = "ALL";
		}
		String SearchValue = GenerateQueryWhereClause_SQL(Search,rank,status);

 		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			q = "select distinct id,rank,status from tb_mstr_rank "+SearchValue+" ORDER BY "+ orderColunm +" "+orderType +" limit " +pageLength+" OFFSET "+startPage ;
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt,Search,rank,status);
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
	                    String encryptedPk = EncryptedPk; // Use the appropriate variable name

	                    String deleteButton = "<i class='action_icons action_delete' " +
	                                          "data-encrypted-pk='" + encryptedPk + "' " +
	                                          "title='Delete Data'></i>";

	                    String updateButton = "<i class='action_icons action_update' " +
	                                          "data-encrypted-pk='" + encryptedPk + "' " +
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


@Override
public long getReportListRANKTotalCount(String Search, String rank, String status) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search,rank,status);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			q ="select count(*) from tb_mstr_rank  "+SearchValue ;
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt,Search,rank,status);
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

	
public String DeleteRANK(String deleteid,HttpSession session1) {

  	Session session = HibernateUtil.getSessionFactory().openSession();
  	Transaction tx = session.beginTransaction();
  	String enckey = "commonPwdEncKeys";
	    String DcryptedPk = hex_asciiDao.decrypt((String) deleteid,enckey,session1);
      	String hql = "Delete from TB_MASTER_RANK  where cast(id as string) = :deleteid";
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

public String GenerateQueryWhereClause_SQL1(String rank, String status) {
		String SearchValue ="";
		  if (rank != null && !rank.isEmpty()) {
			rank = rank.toUpperCase();
			if (SearchValue.contains("where")) {
				SearchValue += " and UPPER(rank) like ? ";
			} else {
				SearchValue += " where UPPER(rank) like ? ";
			}
		}
		  if (status != null && !status.equals("0")) {
			if (SearchValue.contains("where")) {
				SearchValue += " and UPPER(status) like ? ";
			} else {
				SearchValue += " where UPPER(status) like ? ";
			}
		}
return SearchValue;
}


public PreparedStatement setQueryWhereClause_SQL1(PreparedStatement stmt,String rank, String status) {
		int flag = 0;
		try {

			if (!rank.equals("")) {
				flag += 1;
				stmt.setString(flag, rank.toUpperCase() + "%");
			}
			if (!status.equals("0")) {
				flag += 1;
				stmt.setString(flag, status + "%");
			}
		}catch (Exception e) {}
		return stmt;
	}
public ArrayList<ArrayList<String>> Report_DataTableMakeRankList(String rank, String status){
	
		String SearchValue = GenerateQueryWhereClause_SQL1(rank,status);
		
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			        q="select DISTINCT id,UPPER(rank) AS rank, status AS status from tb_mstr_rank"
			        		+SearchValue + " ORDER BY id";
					
			stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL1(stmt,rank,status);
		
			ResultSet rs = stmt.executeQuery();   
			
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("id"));
				alist.add(rs.getString("rank"));
				alist.add(rs.getString("status"));
				
				
				
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


}
