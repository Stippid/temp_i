package com.dao.itasset.Assets;

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

public class Hardware_DAOImpl implements Hardware_DAO{
	 private DataSource dataSource;
     public void setDataSource(DataSource dataSource) {
       this.dataSource = dataSource;
     }
	

public List<Map<String, Object>> searchhardware(int startPage,String pageLength,String Search,String orderColunm,String orderType,String assets_type,String b_head,String b_cost,HttpSession session) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		if(pageLength.equals("-1")){
 			pageLength = "ALL";
		}
		String SearchValue = GenerateQueryWhereClause_SQL(Search,assets_type,b_head,b_cost);

 		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			
			q = "select distinct tbm.id,ta.assets_name,tbm.b_head,tbm.b_cost \r\n" + 
					"from tb_asset_main tbm \r\n" + 
					"inner join  tb_mstr_assets ta on ta.id=tbm.asset_type"+SearchValue+" ORDER BY "+ orderColunm +" "+orderType +" limit " +pageLength+" OFFSET "+startPage ;
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt,Search,assets_type,b_head,b_cost);
			ResultSet rs = stmt.executeQuery();
			

			ResultSetMetaData metaData = rs.getMetaData();
 			int columnCount = metaData.getColumnCount();
 			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
				    columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
                      String Update = "onclick=\"  if (confirm('Are you sure you want to Update?') ){editData('"+rs.getInt("id")+"')}else{ return false;}\""; 
                      String updateButton = "<i class='action_icons action_update' " + Update + " title='Edit Data'></i>"; 
                      String f = "";

                     columns.put(metaData.getColumnLabel(1), f);
                 	String action="";
        			 
        			 columns.put("action",updateButton);//17
        			 action+=updateButton;
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

public long gethardwareTotalCount(String Search,String orderColunm,String orderType,String assets_type,String b_head,String b_cost,HttpSession sessionUserId) {
 		String SearchValue = GenerateQueryWhereClause_SQL(Search,assets_type,b_head,b_cost);
 		int total = 0;
 		String q = null;
 		Connection conn = null;
 		try {
 			conn = dataSource.getConnection();
 			q ="select count(*) \r\n" + 
 					"	from tb_asset_main tbm \r\n" + 
 					"	inner join  tb_mstr_assets ta on ta.id=tbm.asset_type "+SearchValue ;
 			PreparedStatement stmt = conn.prepareStatement(q);

 			stmt = setQueryWhereClause_SQL(stmt,Search,assets_type,b_head,b_cost);
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


public String GenerateQueryWhereClause_SQL(String Search,String assets_type,String b_head,String b_cost) {
	String SearchValue ="";
	
	if (!Search.equals("") || !Search.equals(null) || Search != null || Search != " ") { // for Input Filter
		SearchValue = " where ( ";
		SearchValue += "cast(ta.assets_name as character varying) like ? or upper(tbm.b_head)  like ? or "
				+ "upper(tbm.b_cost)  like ?)";
				
		
	}
	
	
	if(!assets_type.equals("0")) {
		SearchValue += "  and cast(tbm.asset_type as character varying) = ? ";
	}
	if(!b_head.equals("0")) {
		SearchValue += "  and upper(tbm.b_head) =  ? ";
	}	
	
	if(!b_cost.equals("")) {
		SearchValue += "  and upper(tbm.b_cost) like ? ";
	}
	return SearchValue;
}


public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt,String Search,String assets_type,String b_head,String b_cost) {
	
	int flag = 0;
	try {
		if (!Search.equals("") || !Search.equals(null)) {
			flag += 1;
			stmt.setString(flag, "%" + Search.toUpperCase() + "%");
			flag += 1;
			stmt.setString(flag, "%" + Search.toUpperCase() + "%");
			flag += 1;
			stmt.setString(flag, "%" + Search.toUpperCase() + "%");
		}
				
		
			if (!assets_type.equals("0")) {
				flag += 1;
				stmt.setString(flag, assets_type);
			}
			if (!b_head.equals("0")) {
				flag += 1;
				stmt.setString(flag, b_head);
				
			}
			
			if (!b_cost.equals("")) {
				flag += 1;
				stmt.setString(flag, b_cost);
			}  
			
	}catch (Exception e) {
		e.printStackTrace();
	}
	return stmt;
}

public List<Map<String, Object>> getassetdata(String id)
{		
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	Connection conn = null;
	try{	  
		conn = dataSource.getConnection();		
		String qry="";		
		
			qry="select * from tb_asset_main where id=?" ;
		
		PreparedStatement stmt=conn.prepareStatement(qry);
		stmt.setInt(1,Integer.parseInt(id));	
	    ResultSet rs = null ;
	    try {
			rs = stmt.executeQuery();      
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
	    }catch (Exception e) {
			// TODO: handle exception
		}
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
	return list;
}
}