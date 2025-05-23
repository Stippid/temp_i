package com.dao.itasset.Assets;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

public class Peripheral_Hardware_DAOImpl implements Peripheral_Hardware_DAO {
	
	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
	
	public List<Map<String, Object>> Search_Peripheral(int startPage,int pageLength,String Search,String orderColunm,
			String orderType,String status,String assets_type,String year_of_manufacturing,String machine_make,String machine_no,String model_no,
			String from_date,String to_date,HttpSession sessionUserId)
	{

	
	
	String SearchValue = GenerateQueryWhereClause_SQL2(Search,assets_type,year_of_manufacturing,machine_make,machine_no,model_no,from_date,to_date,status);
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();		
		Connection conn = null;
		String q="";
	
		try{	
			String pageL = "";
	        
	        if(pageLength == -1){
				pageL = "ALL";
			}else {
				pageL = String.valueOf(pageLength);
			}
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
	
			
			
			q="select ap.id,pt.assets_name,th.type_of_hw,ap.year_of_proc,\r\n" + 
					"ap.year_of_manufacturing,ap.proc_cost,ap.make_name,ap.machine_no,ap.model_no,ap.remarks from it_asset_peripherals ap\r\n" + 
					"left join tb_mstr_assets pt on pt.id=ap.assets_type  and pt.category=2\r\n" + 
					"left join tb_mstr_type_of_hw th on th.id=ap.type_of_hw\r\n" + 
					"where ap.id!=0 "+ SearchValue +" ORDER BY "+orderColunm+" "+orderType +" limit " +pageL+" OFFSET "+startPage ;
			
			stmt=conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL2(stmt,Search,assets_type,year_of_manufacturing,machine_make,machine_no,model_no,from_date,to_date,status);


			
			 ResultSet rs = stmt.executeQuery();   
		      
		      ResultSetMetaData metaData = rs.getMetaData();
		      int columnCount = metaData.getColumnCount();

			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();			
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
					
				}

				
				String f1 = "";				

					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update Details ?') ){editData("
							+ rs.getInt("id") + ")}else{ return false;}\"";
					f1 = "<i class='action_icons action_update'  " + Update + " title='Edit Data'></i>";
			
				String action="";

			    action+=f1;
				columns.put("action", action);
				list.add(columns);
 	        }
	      
	      rs.close();
	      stmt.close();
	      conn.close();
	   }catch (SQLException e) {
			//throw new RuntimeException(e);
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
	public long getPeripheralCountList(String Search,String orderColunm,String orderType,String status,String assets_type,String year_of_manufacturing,String machine_make,String machine_no,
			String model_no,String from_date,String to_date,HttpSession sessionUserId){
    	String SearchValue = GenerateQueryWhereClause_SQL2(Search,assets_type,year_of_manufacturing,machine_make,machine_no,model_no,from_date,to_date,status);
	
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			q ="select count(*) "
					+ "from it_asset_peripherals ap\r\n" + 
					"left join tb_mstr_assets pt on pt.id=ap.assets_type  and pt.category=2\r\n" + 
					"left join tb_mstr_type_of_hw th on th.id=ap.type_of_hw\r\n" + 
					"where ap.id!=0"+ SearchValue ;

			PreparedStatement stmt = conn.prepareStatement(q);
			
			stmt = setQueryWhereClause_SQL2(stmt,Search,assets_type,year_of_manufacturing,machine_make,machine_no,model_no,from_date,to_date,status);
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
	
	public String GenerateQueryWhereClause_SQL2(String Search,String assets_type,String year_of_manufacturing,String machine_make,String machine_no,String model_no,
			String from_date,String to_date,String status) {
		String SearchValue ="";
		
		if (!Search.equals("") || !Search.equals(null) || Search != null || Search != " ") { // for Input Filter
			SearchValue = " and ( ";
			SearchValue += " upper(pt.assets_name) like ? or upper(th.type_of_hw)  like ? or upper(ap.year_of_proc)  like ? or "
					+ " upper(ap.year_of_manufacturing) like ? "
					+ " or upper(ap.proc_cost) like ? or upper(ap.machine_no) like ? or upper(ap.model_no) like ? or upper(ap.remarks) like ? )";
			
		}
		

				if(!status.equals(""))
				{
					SearchValue += "  and cast(status as character varying) = ? ";
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
					SearchValue +=" and cast(ap.created_date as character varying) BETWEEN ? and  ?";
				}

		
		return SearchValue;
	}
	
	public PreparedStatement setQueryWhereClause_SQL2(PreparedStatement stmt,String Search,String assets_type,String year_of_manufacturing,String machine_make,String machine_no,
			String model_no,String from_date,String to_date,String status) {

		
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
			 
		}catch (Exception e) {
			e.printStackTrace();
		}
		return stmt;
	}
}