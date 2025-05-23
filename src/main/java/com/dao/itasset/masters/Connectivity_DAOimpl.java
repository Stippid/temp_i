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
import com.models.itasset.assets.Assets_Main;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

public class Connectivity_DAOimpl implements Connectivity_DAO {
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();

	public boolean checkIsIntegerValue(String Search) {
		return Search.matches("[0-9]+");
	}
	
	public List<Map<String, Object>> getReportListConnectivity(int startPage, String pageLength, String Search,
			String orderColunm, String orderType, HttpSession session,String connectivity_type)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		if (pageLength.equals("-1")) {
			pageLength = "ALL";
		}
	
		String SearchValue = GenerateQueryWhereClause_SQL(Search,connectivity_type);

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			q = "select id, connectivity_type from tb_mstr_connectivity" + SearchValue + " ORDER BY " + orderColunm
					+ " " + orderType + " limit " + pageLength + " OFFSET " + startPage;
			
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search,connectivity_type);
		
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

	public long getReportListConnectivityTotalCount(String Search,String connectivity_type) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search,connectivity_type);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			q = "select count(*) from tb_mstr_connectivity" + SearchValue;
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search,connectivity_type);
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

	public String GenerateQueryWhereClause_SQL(String Search,String connectivity_type) {
		String SearchValue = "";
		
		if(!Search.equals("")) { // for Input Filter
			SearchValue =" where ( ";
			SearchValue +=" upper(connectivity_type) like ? )";
		}
 		if (!connectivity_type.equals("")) {
 			connectivity_type = connectivity_type.toUpperCase();
			if (SearchValue.contains("where")) {
				SearchValue += " and upper(connectivity_type) like ? ";
			} else {
				SearchValue += " where upper(connectivity_type) like ? ";
			}
		}
 		
		
		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search,String connectivity_type) {
		int flag = 0;
		try {
			
			if (!Search.equals("")) {
				flag += 1;
				stmt.setString(flag, "%"+Search.toUpperCase()+"%");
				
			}
			if(!connectivity_type.equals("")) {
				flag += 1;
				stmt.setString(flag, connectivity_type.toUpperCase()+"%");
    		}
		} catch (Exception e) {
		}
		
		return stmt;
	}
	
	public String Delete_Connectivity(String deleteid, HttpSession session1) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String enckey = "commonPwdEncKeys";
		String DcryptedPk = hex_asciiDao.decrypt((String) deleteid, enckey, session1);
		String hql = "Delete from TB_MSTR_CONNECTIVITY  where cast(id as string) = :deleteid";
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
	
	  public ArrayList<ArrayList<String>> Report_DataTableMakeList(String connectivity_type){
			
			String SearchValue = GenerateQueryWhereClause_SQL1(connectivity_type);
			
			ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
			Connection conn = null;
			String q = "";
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;
				
				        q= "select DISTINCT id, UPPER(connectivity_type) AS  connectivity_type from tb_mstr_connectivity"
				        		+SearchValue + " ORDER BY id";
						
				stmt = conn.prepareStatement(q);
				stmt = setQueryWhereClause_SQL1(stmt,connectivity_type);
				
				ResultSet rs = stmt.executeQuery();   
				
				while (rs.next()) {
					ArrayList<String> alist = new ArrayList<String>();
					alist.add(rs.getString("id"));
					alist.add(rs.getString("connectivity_type"));
					
				
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

	public String GenerateQueryWhereClause_SQL1(String connectivity_type) {
			String SearchValue ="";
			
			
			if(!connectivity_type.equals("")) {
				connectivity_type = connectivity_type.toUpperCase();
				if(SearchValue.contains("where")) {
					SearchValue +=" and upper(connectivity_type) like ? ";
				}else {
					SearchValue +=" where upper(connectivity_type) like ? ";
				}
			}
			
			
			return SearchValue;
		}
			
		public PreparedStatement setQueryWhereClause_SQL1(PreparedStatement stmt,String connectivity_type) {
			int flag = 0;
			
			try {
	   		
				if (!connectivity_type.equals("")) {
					flag += 1;
					stmt.setString(flag, connectivity_type.toUpperCase() + "%");
				}
				
			
		}catch (Exception e) {}
			
			return stmt;
		}

		
		
		
		
		
		 ////////////////Other/////////////
		 
		 
		 
		 public List<Map<String, Object>> DataTableConnectivityOtherList(int startPage,int pageLength,String Search,String orderColunm,String orderType,
					String connectivity_type,String status) {
					
				
					
					String SearchValue = GenerateQueryWhereClause_SQL(Search,connectivity_type,status);
					
					List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
					Connection conn = null;
					String q = "";
					String pageL="";
					try {
						conn = dataSource.getConnection();
						if (pageLength == -1) {
							pageL = "ALL";
						}else {
							pageL = String.valueOf(pageLength);
						}
						q="select id, connectivity_type from tb_mstr_connectivity" + SearchValue 
								+ " ORDER BY   "+orderColunm +" "+orderType +" limit " +pageL+" OFFSET "+startPage ;
						
						PreparedStatement stmt = conn.prepareStatement(q);
						stmt = setQueryWhereClause_SQL(stmt,Search,connectivity_type,status);
						
						ResultSet rs = stmt.executeQuery();
						ResultSetMetaData metaData = rs.getMetaData();
						int columnCount = metaData.getColumnCount();
						while (rs.next()) {
							Map<String, Object> columns = new LinkedHashMap<String, Object>();
							
							for (int i = 1; i <= columnCount; i++) {
								columns.put(metaData.getColumnLabel(i), rs.getObject(i));
							}
							
							String f = "";
							String f1 = "";
							String action = "";
							
			  		String checkboxIdValue = rs.getObject(1).toString(); 
			  	String checkbox = "<input class='nrCheckBox' type='checkbox' id='" + checkboxIdValue + 
			  	                  "' name='cbox' data-id='" + checkboxIdValue + "' />"; 

		
			  	String checkboxId = "<input type='hidden' id='id" + checkboxIdValue + 
			  	                    "' name='id" + checkboxIdValue + 
			  	                    "' value='" + checkboxIdValue + "' />";
			  		  
			  		String Delete1 = "onclick=\"  if (confirm('Are You Sure You Want to Delete This Information ?') ){deleteData("
							+ rs.getInt("id") + ")}else{ return false;}\"";
					f1 = "<i class='action_icons action_delete' " + Delete1 + " title='Delete Data'></i>";
			  		  
			  		  String chekboxaction="";
			  		  
			  		  
			  		 if (status.equals("1")) {
						 columns.put("action",f1);//15
						 action+=f1;
						}
			  		  
			  		  
			  		 else {
			  			chekboxaction+=checkbox;
					      chekboxaction+=checkboxId;
			  		 }
			  		  
			  		
				      
				  	columns.put("chekboxaction", chekboxaction);
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
			public String GenerateQueryWhereClause_SQL(String Search,String connectivity_type,String status) {
				String SearchValue ="";
				if(!Search.equals("")) { // for Input Filter
					SearchValue =" where ( ";
					SearchValue +="cast(connectivity_type as character varying) like ?)";
				}
				
				if(!status.equals(""))
				{
				if (SearchValue.contains("where")) {
					SearchValue += " and cast(status as character varying) = ? ";
					
				} else {
						SearchValue += " where cast(status as character varying) = ?  ";
					}
				}
				
				if (!connectivity_type.equals("")) {
					connectivity_type = connectivity_type.toUpperCase();
				if (SearchValue.contains("where")) {
					SearchValue += " and cast(connectivity_type as character varying) like ?  ";
					
				} else {
						SearchValue += " where cast(connectivity_type as character varying) like ?  ";
					}
				}
			
			
				
				return SearchValue;
			}
				
			public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt,String Search,String connectivity_type,String status) {
				int flag = 0;
				try {
					if(!Search.equals("")) {
						flag += 1;
						stmt.setString(flag, "%"+Search.toUpperCase()+"%");
						
						
					}
					if (!status.equals("")) {
						flag += 1;
						stmt.setString(flag, status);
					}
					if(!connectivity_type.equals("")) {
					flag += 1;
					stmt.setString(flag,"%"+connectivity_type+"%");
				}
				
				}catch (Exception e) {}
				return stmt;
			}	
				public long DataTableConnectivityOtherTotalCount(String Search,String connectivity_type,String status) {
					String SearchValue = GenerateQueryWhereClause_SQL(Search,connectivity_type,status);
					int total = 0;
					String q = null;
					Connection conn = null;
					try {
						conn = dataSource.getConnection();
						q ="select count(*) from tb_mstr_connectivity  "+SearchValue ;
						PreparedStatement stmt = conn.prepareStatement(q);
						stmt = setQueryWhereClause_SQL(stmt,Search,connectivity_type,status);
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

				
				
				
				
				
				public String approve_ConnectivityData(String a,String user_sus,String status,String username) {
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
								stmt = conn.prepareStatement("update tb_mstr_connectivity set status=? where id=?");
								
								
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
				public String reject_ConnectivityData(String a) {
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
//								Assets_Main assetupd =(Assets_Main)sessionHQL.get(Assets_Main.class, id);
								stmt = conn.prepareStatement("delete from tb_mstr_connectivity  where id=?");
								
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
