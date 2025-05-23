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

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.itasset.assets.Assets_Main;
import com.models.itasset.master.TB_MASTER_MAKE;
import com.persistance.util.HibernateUtil;

public class Make_DaoImpl implements Make_Dao{

	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	public boolean checkIsIntegerValue(String Search) {
		return Search.matches("[0-9]+");
	}
	
	public List<Map<String, Object>> DataTableMakeList(int startPage,int pageLength,String Search,String orderColunm,String orderType,
			String category, String assets_name,String make_name) {
			
		
			
			String SearchValue = GenerateQueryWhereClause_SQL(Search,category,assets_name,make_name);
			
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
				q="select c.id,v.assets_name,c.make_name,thw.type_of_hw,\r\n" + 
						"						CASE\r\n" + 
						"						WHEN c.category=1 THEN 'COMPUTING'\r\n" + 
						"						WHEN c.category=2 THEN 'PERIPHERAL'\r\n" + 
						"						WHEN c.category=3 THEN 'CONSUMABLES'\r\n" + 
						"						ELSE 'Other'\r\n" + 
						"						END AS category\r\n" + 
						"						from tb_mstr_make c \r\n" + 
						"						inner join tb_mstr_assets v on v.id= c.assets_name\r\n" + 
						"						LEFT join tb_mstr_type_of_hw thw on thw.id=c.type_of_hw where c.status='1' \r\n" + SearchValue 
						+ " ORDER BY   "+orderColunm +" "+orderType +" limit " +pageL+" OFFSET "+startPage ;
				
				PreparedStatement stmt = conn.prepareStatement(q);
				stmt = setQueryWhereClause_SQL(stmt,Search,category,assets_name,make_name);
				ResultSet rs = stmt.executeQuery();
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				while (rs.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();
					
					for (int i = 1; i <= columnCount; i++) {
						columns.put(metaData.getColumnLabel(i), rs.getObject(i));
					}
					
				
					String action = "";
					
					String deleteButton = "<i class='action_icons action_delete' " + "data-id='" + rs.getString("id")  + "' "
							+ "title='Delete Data'></i>";

					// Create the update button with a data attribute for the ID
					String updateButton = "<i class='action_icons action_update' " + "data-id='" + rs.getString("id") + "' "
							+ "title='Edit Data'></i>";

					// Combine the buttons into a single action string
					 action = updateButton + deleteButton;
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
	public String GenerateQueryWhereClause_SQL(String Search,String category, String assets_name, String make_name) {
		String SearchValue ="";
		if(!Search.equals("")) { // for Input Filter
			SearchValue =" and ( ";
			SearchValue +=" CASE\n" + 
					"WHEN c.category=1 THEN 'COMPUTING' " + 
					"WHEN c.category=2 THEN 'PERIPHERAL' " + 
					"WHEN c.category=3 THEN 'CONSUMABLES' " + 
					"ELSE 'Other'\n" + 
					"END like ? or upper(cast(v.assets_name as character varying)) like ? or upper(cast(c.make_name as character varying)) like ?)";
		}
		
	
		if (!category.equals("0")) {
			category = category.toUpperCase();
		if (SearchValue.contains("where")) {
			SearchValue += " and cast(c.category as character varying) like ? ) ";
			
		} else {
				SearchValue += " and cast(c.category as character varying) like ?  ";
			}
		}
	
	if (!assets_name.equals("0")) {
			assets_name = assets_name.toUpperCase();
	if (SearchValue.contains("where")) {
			SearchValue += " and cast(c.assets_name as character varying) like ?  ";
			} else {
			SearchValue += " and  cast(c.assets_name as character varying) like ?  ";
			}
	}

	
		if (!make_name.equals("")) {
			make_name = make_name.toUpperCase();
			if (SearchValue.contains("where")) {
				SearchValue += " and upper(c.make_name ) like ? ";
			} else {
				SearchValue += " and upper(c.make_name ) like ? ";
			}
		}
		
		return SearchValue;
	}
		
	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt,String Search,String category, String assets_name,String make_name) {
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
			
			if(!category.equals("0")) {
			flag += 1;
			stmt.setString(flag, category);
		}
		if(!assets_name.equals("0")) {
			flag += 1;
			stmt.setString(flag, assets_name);
		}	
			if(!make_name.equals("")) {
				flag += 1;
				stmt.setString(flag, make_name.toUpperCase()+"%");
		}
		}catch (Exception e) {}
		return stmt;
	}	
		public long DataTableMakeTotalCount(String Search,String category,String assets_name,String make_name) {
			String SearchValue = GenerateQueryWhereClause_SQL(Search,category,assets_name,make_name);
			int total = 0;
			String q = null;
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				q ="select count(*) from tb_mstr_make c inner join tb_mstr_assets v on v.id= c.assets_name where status='1' \r\n "+SearchValue ;
				PreparedStatement stmt = conn.prepareStatement(q);
				stmt = setQueryWhereClause_SQL(stmt,Search,category,assets_name,make_name);
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
		
		public String GenerateQueryWhereClause_SQL1(String category, String assets_name,String make_name) {
			String SearchValue ="";
			
			if (!category.equals("0")) {
				category = category.toUpperCase();
			
					SearchValue += " and cast(c.category as character varying) like ? ";

			}
			if (!assets_name.equals("0")) {
	  			assets_name = assets_name.toUpperCase();
					SearchValue += " and  cast(c.assets_name as character varying) like ? ";
				
			}
			if(!make_name.equals("")) {
				make_name = make_name.toLowerCase();
			
					SearchValue +=" and upper(c.make_name) like ? ";
				
			}
			
			return SearchValue;
		}
			
		public PreparedStatement setQueryWhereClause_SQL1(PreparedStatement stmt,String category, String assets_name,String make_name) {
			int flag = 0;
			
			try {
	    		
				if (!category.equals("0")) {
 					flag += 1;
 					stmt.setString(flag, category + "%");
 				}
 				if (!assets_name.equals("")) {
 					flag += 1;
 					stmt.setString(flag, assets_name.toUpperCase() + "%");
 				}
 				
 				if(!make_name.equals("")) {
					flag += 1;
					stmt.setString(flag, make_name.toUpperCase()+"%");
				}
 			
 		}catch (Exception e) {}
			
			return stmt;
		}
		
		public TB_MASTER_MAKE getMakeByid(int id) {
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionHQL.beginTransaction();
			try {
				TB_MASTER_MAKE updateid = (TB_MASTER_MAKE) sessionHQL.get(TB_MASTER_MAKE.class, id);
				tx.commit();
				return updateid;
			} catch (RuntimeException e) {
				return null;
			} finally {
				if (sessionHQL != null) {
					sessionHQL.close();
				}
			}	
		}
			
		 public ArrayList<ArrayList<String>> Report_DataTableMakeList(String assets_name,String category,String make_name){
				
				String SearchValue = GenerateQueryWhereClause_SQL1(category,assets_name,make_name);
				ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
				Connection conn = null;
				String q = "";
				try {
					conn = dataSource.getConnection();
					PreparedStatement stmt = null;
					 
					        q="select c.id,UPPER(v.assets_name) AS assets_name,UPPER(c.make_name) AS make_name,thw.type_of_hw,\r\n" + 
					        		"		CASE \r\n" + 
					        		"		WHEN c.category=1 THEN 'COMPUTING' \r\n" + 
					        		"		WHEN c.category=2 THEN 'PERIPHERAL' \r\n" + 
					        		"		WHEN c.category=3 THEN 'CONSUMABLES' \r\n" + 
					        		"		ELSE 'Other' \r\n" + 
					        		"		END AS category \r\n" + 
					        		"from tb_mstr_make c \r\n" + 
					        		"inner join tb_mstr_assets v on v.id= c.assets_name \r\n" + 
					        		"LEFT join tb_mstr_type_of_hw thw on thw.id=c.type_of_hw where c.status='1' "
					        		+SearchValue + " ORDER BY id";
							
					stmt = conn.prepareStatement(q);
					stmt = setQueryWhereClause_SQL1(stmt,category,assets_name,make_name);
					ResultSet rs = stmt.executeQuery();   
					
					while (rs.next()) {
						ArrayList<String> alist = new ArrayList<String>();
						alist.add(rs.getString("id"));
						alist.add(rs.getString("category"));
						alist.add(rs.getString("assets_name"));
						alist.add(rs.getString("make_name"));
						
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

		
		 
		 
		 
		 ////////////////Other/////////////
		 
		 
		 
		 public List<Map<String, Object>> DataTableMakeOtherList(int startPage,int pageLength,String Search,String orderColunm,String orderType,
					String category, String assets_name,String make_name,String status) {
					
				
					
					String SearchValue = GenerateQueryWhereClause_SQL(Search,category,assets_name,make_name,status);
					
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
						q="select c.id,v.assets_name,c.make_name,thw.type_of_hw,c.status,\r\n" + 
								"						CASE\r\n" + 
								"						WHEN c.category=1 THEN 'COMPUTING'\r\n" + 
								"						WHEN c.category=2 THEN 'PERIPHERAL'\r\n" + 
								"						WHEN c.category=3 THEN 'CONSUMABLES'\r\n" + 
								"						ELSE 'Other'\r\n" + 
								"						END AS category\r\n" + 
								"						from tb_mstr_make c \r\n" + 
								"						inner join tb_mstr_assets v on v.id= c.assets_name\r\n" + 
								"						LEFT join tb_mstr_type_of_hw thw on thw.id=c.type_of_hw" + SearchValue 
								+ " ORDER BY   "+orderColunm +" "+orderType +" limit " +pageL+" OFFSET "+startPage ;
						
						PreparedStatement stmt = conn.prepareStatement(q);
						stmt = setQueryWhereClause_SQL(stmt,Search,category,assets_name,make_name,status);
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

							String checkboxAction = "";
							  
					  		 if (status.equals("1")) {
								 columns.put("action",f1);//15
								 action+=f1;
								}
					  		  
					  		  
					  		 else {
					  			checkboxAction+=checkbox;
					  			checkboxAction+=checkboxId;
					  		 }
					  		  
					  		
			  		  
			  	
				  	columns.put("chekboxaction", checkboxAction);
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
			public String GenerateQueryWhereClause_SQL(String Search,String category, String assets_name, String make_name,String status) {
				String SearchValue ="";
				if(!Search.equals("")) { // for Input Filter
					SearchValue =" where ( ";
					SearchValue +=" CASE\n" + 
							"WHEN c.category=1 THEN 'COMPUTING' " + 
							"WHEN c.category=2 THEN 'PERIPHERAL' " + 
							"WHEN c.category=3 THEN 'CONSUMABLES' " + 
							"ELSE 'Other'\n" + 
							"END like ? or cast(v.assets_name as character varying) like ? or cast(c.make_name as character varying) like ?)";
				}
				
				if(!status.equals(""))
				{
				if (SearchValue.contains("where")) {
					SearchValue += " and cast(c.status as character varying) = ? ";
					
				} else {
						SearchValue += " where cast(c.status as character varying) = ?  ";
					}
				}
				
				if (!category.equals("0")) {
					category = category.toUpperCase();
				if (SearchValue.contains("where")) {
					SearchValue += " and cast(c.category as character varying) like ?  ";
					
				} else {
						SearchValue += " where cast(c.category as character varying) like ?  ";
					}
				}
			
			if (!assets_name.equals("0")) {
					assets_name = assets_name.toUpperCase();
			if (SearchValue.contains("where")) {
					SearchValue += " and cast(c.assets_name as character varying) like ?  ";
					} else {
					SearchValue += " where  cast(c.assets_name as character varying) like ?  ";
					}
			}

			
				if (!make_name.equals("")) {
					make_name = make_name.toUpperCase();
					if (SearchValue.contains("where")) {
						SearchValue += " and upper(c.make_name ) like ? ";
					} else {
						SearchValue += " where upper(c.make_name ) like ? ";
					}
				}
				
				return SearchValue;
			}
				
			public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt,String Search,String category, String assets_name,String make_name,String status) {
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
					if (!status.equals("")) {
						flag += 1;
						stmt.setString(flag, status);
					}
					if(!category.equals("0")) {
					flag += 1;
					stmt.setString(flag, category);
				}
				if(!assets_name.equals("0")) {
					flag += 1;
					stmt.setString(flag, assets_name);
				}	
					if(!make_name.equals("")) {
						flag += 1;
						stmt.setString(flag, make_name.toUpperCase()+"%");
				}
				}catch (Exception e) {}
				return stmt;
			}	
				public long DataTableMakeOtherTotalCount(String Search,String category,String assets_name,String make_name,String status) {
					String SearchValue = GenerateQueryWhereClause_SQL(Search,category,assets_name,make_name,status);
					int total = 0;
					String q = null;
					Connection conn = null;
					try {
						conn = dataSource.getConnection();
						q ="select count(*) from tb_mstr_make c inner join tb_mstr_assets v on v.id= c.assets_name  LEFT join tb_mstr_type_of_hw thw on thw.id=c.type_of_hw "+SearchValue ;
						PreparedStatement stmt = conn.prepareStatement(q);
						stmt = setQueryWhereClause_SQL(stmt,Search,category,assets_name,make_name,status);
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
				
				@Override
				public String approve_MakeData(String a,String user_sus,String status,String username) {
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
								stmt = conn.prepareStatement("update tb_mstr_make set status=? where id=?");
								
								
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
				public String reject_MakeData(String a) {
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
								stmt = conn.prepareStatement("delete from tb_mstr_make  where id=?");
								
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
