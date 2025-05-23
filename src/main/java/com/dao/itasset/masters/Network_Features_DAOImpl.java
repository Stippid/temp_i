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

public class Network_Features_DAOImpl implements Network_Features_DAO {
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();

	public boolean checkIsIntegerValue(String Search) {
		return Search.matches("[0-9]+");
	}
	
	public List<Map<String, Object>> getReportListNetwork_Features(int startPage, String pageLength, String Search,
			String orderColunm, String orderType, HttpSession session,String network_features)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		if (pageLength.equals("-1")) {
			pageLength = "ALL";
		}
		
	
		String SearchValue = GenerateQueryWhereClause_SQL(Search,network_features);

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			q = "select id,UPPER(network_features) AS network_features from tb_mstr_network_features" + SearchValue + " ORDER BY " + orderColunm
					+ " " + orderType + " limit " + pageLength + " OFFSET " + startPage;
			
			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQL(stmt, Search,network_features);
		
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
				String EncryptedPk = new String(
						Base64.encodeBase64(c.doFinal(rs.getString("id").toString().getBytes())));

				String deleteButton = "<i class='action_icons action_delete' " + "data-encrypted-pk='" + EncryptedPk
						+ "' " + "title='Delete Data'></i>";

				String updateButton = "<i class='action_icons action_update' " + "data-encrypted-pk='" + EncryptedPk
						+ "' " + "title='Edit Data'></i>";

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

	public long getReportListNetwork_FeaturesTotalCount(String Search,String network_features) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search,network_features);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			q = "select count(*) from tb_mstr_network_features" + SearchValue;
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search,network_features);
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

	public String GenerateQueryWhereClause_SQL(String Search,String network_features) {
		String SearchValue = "";
		
		if(!Search.equals("")) { // for Input Filter
			SearchValue =" where ( ";
			SearchValue +=" upper(network_features) like ? )";
		}
		if (!network_features.equals("")) {
			network_features = network_features.toUpperCase();
			if (SearchValue.contains("where")) {
				SearchValue += " and upper(network_features) like ? ";
			} else {
				SearchValue += " where upper(network_features) like ? ";
			}
		}
 		
		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String network_features) {
		int flag = 0;
		try {
			if (!Search.equals("")) {
				flag += 1;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				
			}
			if(!network_features.equals("")) {
				flag += 1;
				stmt.setString(flag, network_features.toUpperCase()+"%");
    		}
		} catch (Exception e) {
		}
		return stmt;
	}
	
	public String Delete_Network_Features(String deleteid, HttpSession session1) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String enckey = "commonPwdEncKeys";
		String DcryptedPk = hex_asciiDao.decrypt((String) deleteid, enckey, session1);
		String hql = "Delete from TB_MSTR_NETWORK_FEATURES  where cast(id as string) = :deleteid";
		Query q = session.createQuery(hql).setString("deleteid", DcryptedPk);
		int rowCount = q.executeUpdate();
		tx.commit();
		session.close();
		if (rowCount > 0) {
			return "Deleted Successfully";
		} else {
			return "Deleted not Successfully";
		}
	}
	
public ArrayList<ArrayList<String>> Report_DataTableMakeList(String network_features){
		
		String SearchValue = GenerateQueryWhereClause_SQL1(network_features);
		
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			
			        q= "select DISTINCT id, UPPER(network_features) AS network_features from tb_mstr_network_features"
			        		+SearchValue + " ORDER BY id";
					
			stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL1(stmt,network_features);
			
			ResultSet rs = stmt.executeQuery();   
			
			while (rs.next()) {
				ArrayList<String> alist = new ArrayList<String>();
				alist.add(rs.getString("id"));
				alist.add(rs.getString("network_features"));
				
			
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

public String GenerateQueryWhereClause_SQL1(String network_features) {
		String SearchValue ="";
		
		
		if(!network_features.equals("")) {
			network_features = network_features.toUpperCase();
			if(SearchValue.contains("where")) {
				SearchValue +=" and upper(network_features) like ? ";
			}else {
				SearchValue +=" where upper(network_features) like ? ";
			}
		}
		
		
		return SearchValue;
	}
		
	public PreparedStatement setQueryWhereClause_SQL1(PreparedStatement stmt,String network_features) {
		int flag = 0;
		
		try {
   		
			if (!network_features.equals("")) {
				flag += 1;
				stmt.setString(flag, network_features.toUpperCase() + "%");
			}
			
		
	}catch (Exception e) {}
		
		return stmt;
	}

}
