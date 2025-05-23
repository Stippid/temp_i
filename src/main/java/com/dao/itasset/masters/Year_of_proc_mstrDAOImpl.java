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


public class Year_of_proc_mstrDAOImpl implements Year_of_proc_mstrDAO {

         private DataSource dataSource;
         public void setDataSource(DataSource dataSource) {
	       this.dataSource = dataSource;
         }
 HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();



  public boolean checkIsIntegerValue(String Search) {
	return Search.matches("[0-9]+");
}


public List<Map<String, Object>> getReportListYear_of_proc_mstr(int startPage,String pageLength,String Search,String orderColunm,String orderType,HttpSession session) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		if(pageLength.equals("-1")){
 			pageLength = "ALL";
		}
		String SearchValue = GenerateQueryWhereClause_SQL(Search);

 		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			q = "select distinct id,year_of_proc from tb_mstr_year_of_proc "+SearchValue+" ORDER BY "+ orderColunm +" "+orderType +" limit " +pageLength+" OFFSET "+startPage ;
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt,Search);
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
                      String Delete = "onclick=\"  if (confirm('Are you sure you want to Delete?') ){deleteData('"+EncryptedPk+"')}else{ return false;}\""; 
                      String deleteButton = "<i class='action_icons action_delete' " + Delete + " title='Delete Data'></i>"; 
                      String Update = "onclick=\"  if (confirm('Are you sure you want to Update?') ){editData('"+EncryptedPk+"')}else{ return false;}\""; 
                      String updateButton = "<i class='action_icons action_update' " + Update + " title='Edit Data'></i>"; 
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

public long getReportListYear_of_proc_mstrTotalCount(String Search) {
 		String SearchValue = GenerateQueryWhereClause_SQL(Search);
 		int total = 0;
 		String q = null;
 		Connection conn = null;
 		try {
 			conn = dataSource.getConnection();
 			q ="select count(*) from tb_mstr_year_of_proc  "+SearchValue ;
 			PreparedStatement stmt = conn.prepareStatement(q);
 			stmt = setQueryWhereClause_SQL(stmt,Search);
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


  	public String GenerateQueryWhereClause_SQL(String Search) {
 		String SearchValue ="";
  		if(!Search.equals("")) {
			Search = Search.toLowerCase();
  			SearchValue =" where ( ";
  			if(checkIsIntegerValue(Search)) {
  				SearchValue +=" id=? or ";
  			}
 			SearchValue +=" lower(year_of_proc) like ?  )";
 		}
   return SearchValue;
 }


  public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt,String Search) {
 		int flag = 0;
 		try {
    		if(!Search.equals("")) {
 				if(checkIsIntegerValue(Search)) {
 					flag += 1;
 					stmt.setInt(flag, Integer.parseInt(Search));
 				}
 				flag += 1;
 				stmt.setString(flag, "%"+Search.toLowerCase()+"%");
 			}
 		}catch (Exception e) {}
 		return stmt;
 	}
   public String Deleteyear_of_proc_mstr(String deleteid,HttpSession session1) {

      	Session session = HibernateUtil.getSessionFactory().openSession();
      	Transaction tx = session.beginTransaction();
      	String enckey = "commonPwdEncKeys";
		    String DcryptedPk = hex_asciiDao.decrypt((String) deleteid,enckey,session1);
	      	String hql = "Delete from TB_MSTR_YEAR_OF_PROC_M  where cast(id as string) = :deleteid";
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

}
