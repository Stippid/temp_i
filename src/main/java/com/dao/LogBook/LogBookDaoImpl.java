package com.dao.LogBook;

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

import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;

public class LogBookDaoImpl implements LogBookDao{

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	

	public List<Map<String, Object>> getLogBookReportDataList(int startPage, String pageLength, String search,
			String orderColunm, String orderType, String catgory, String assets_name, String make_name, String status,
			HttpSession session)
	
					
					
					throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		
		if(pageLength.equals("-1")){
			pageLength = "ALL";
		}
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			String searchValue="";
			
			if(search!="")
			{
				searchValue+=" and(  upper(a.category) like ? or  upper(a.assets_name) like ? or  upper(a.make_name) like ? or  upper(a.model_name) like ?    )";
			}
			if(assets_name!="0" && !assets_name.equals("0"))
			{
				if(catgory.equals("1"))
				{
				searchValue +=" and a.asset_type::text = ? ";
				
				}
				if(catgory.equals("2") || catgory.equals("3"))
				{
					searchValue +=" and a.assets_type::text = ? ";
				}
				
				
			}
			if(make_name!="0"&& !make_name.equals("0"))
			{
				
				searchValue +=" and a.cp_make::text = ? ";
			}
				
				
			if(catgory.equals("1"))
			{
				q = "select a.* from (select cp.id,cp.status,cp.asset_type,cp.make_name as cp_make,CASE\n"
						+ "					 WHEN ass.category=1 THEN 'COMPUTING'\n"
						+ "					 WHEN ass.category=2 THEN 'PERIPHERAL'\n"
						+ "					 WHEN ass.category=3 THEN 'CONSUMABLES'\n"
						+ "					 ELSE 'Other'\n"
						+ "					 END AS category ,ass.assets_name,make.make_name ,model.model_name from tb_asset_main cp \n"
						+ "left join tb_mstr_assets ass on ass.id = cp.asset_type and ass.category = 1 \n"
						+ "left join tb_mstr_make make on make.id = cp.make_name and  make.assets_name = cp.asset_type \n"
						+ "left join tb_mstr_model model on model.id = cp.model_name and  model.assets_name = cp.asset_type and model.make_name = cp.make_name\n"
						+ ") as a where a.status=1"+searchValue
						+ " ORDER BY "+ orderColunm +" "+orderType +" limit " +pageLength+" OFFSET "+startPage + "" ;

			}
			if(catgory.equals("2"))
			{
				q = " select a.* from (select cp.id, cp.status,cp.make_name as cp_make,CASE\n"
						+ "					 WHEN ass.category=1 THEN 'COMPUTING'\n"
						+ "					 WHEN ass.category=2 THEN 'PERIPHERAL'\n"
						+ "					 WHEN ass.category=3 THEN 'CONSUMABLES'\n"
						+ "					 ELSE 'Other'\n"
						+ "					 END AS category ,ass.assets_name,make.make_name ,model.model_name ,cp.assets_type  from it_asset_peripherals cp \n"
						+ " left join tb_mstr_assets ass on ass.id = cp.assets_type and ass.category = 2 \n"
						+ " left join tb_mstr_make make on make.id = cp.make_name and  make.assets_name = cp.assets_type \n"
						+ " left join tb_mstr_model model on model.id = cp.model_name and  model.assets_name = cp.assets_type and model.make_name = cp.make_name\n"
						+ "  ) as a where a.status=1 "+searchValue
						+ " ORDER BY "+ orderColunm +" "+orderType +" limit " +pageLength+" OFFSET "+startPage + "" ;

			}
			
			
			if(catgory.equals("3"))
			{
				q = " select a.* from (SELECT	CP.ID,CP.STATUS,CP.MAKE_NAME AS CP_MAKE,CASE\n"
						+ "				WHEN ASS.CATEGORY = 1 THEN 'COMPUTING'\n"
						+ "				WHEN ASS.CATEGORY = 2 THEN 'PERIPHERAL'\n"
						+ "				WHEN ASS.CATEGORY = 3 THEN 'CONSUMABLES'	ELSE 'Other'\n"
						+ "			END AS CATEGORY, ASS.ASSETS_NAME, MAKE.MAKE_NAME,\n"
						+ "			MODEL.MODEL_NAME,cp.Assets_type	FROM\n"
						+ "			tb_consumable_main CP\n"
						+ "			LEFT JOIN TB_MSTR_ASSETS ASS ON ASS.ID = CP.ASSETS_TYPE\n"
						+ "			AND ASS.CATEGORY = 3\n"
						+ "			LEFT JOIN TB_MSTR_MAKE MAKE ON MAKE.ID = CP.MAKE_NAME\n"
						+ "			AND MAKE.ASSETS_NAME = CP.ASSETS_TYPE\n"
						+ "			LEFT JOIN TB_MSTR_MODEL MODEL ON MODEL.ID = CP.MODEL_NAME\n"
						+ "			AND MODEL.ASSETS_NAME = CP.ASSETS_TYPE\n"
						+ "			AND MODEL.MAKE_NAME = CP.MAKE_NAME" + "  ) as a where a.status=1 " + searchValue
						+ " ORDER BY " + orderColunm + " " + orderType + " limit " + pageLength + " OFFSET " + startPage
						+ "";

			}
			
			
			
			
			
			
			
			PreparedStatement stmt = conn.prepareStatement(q);
			int flag=0;
		
			if(search!="")
			{
				flag += 1;
				stmt.setString(flag, "%"+search.toUpperCase()+"%");
				flag += 1;
				stmt.setString(flag, "%"+search.toUpperCase()+"%");
				flag += 1;
				stmt.setString(flag, "%"+search.toUpperCase()+"%");
				flag += 1;
				stmt.setString(flag, "%"+search.toUpperCase()+"%");
				}
			if(assets_name!="0" && !assets_name.equals("0"))
			{
				flag += 1;
				stmt.setString(flag,assets_name );
				
			}
			if(make_name!="0"&& !make_name.equals("0"))
			{
				flag += 1;
				stmt.setString(flag,make_name );
			}
			
			
			
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
	                    String id = rs.getString("id").toString();
	                    String count_p_id = rs.getString("id").toString();
//	                    String history = "onclick=\"  if (confirm('Are you sure you want to see history?') ){historyData('"+EncryptedPk+"')}else{ return false;}\""; 
//	                    String historyButton = "<i class='' " + history + " data-toggle='modal' data-target='#myHistoryModal'>"+ count_p_id +"</i>"; 
//               
//	                    String print = "onclick=\"  {Downloaddata('"+id+"')}\""; 
//	                    String printButton = "<i class='action_icons action_download'" + print + " title='Download Work Order'></i>";
//	                    String f1 = "";
//
//	                    f1 += printButton;
//	                    
//	                    String f = "";
//                  f += historyButton;
//               
//                 columns.put("history", f);
//                 columns.put("action", f1 );
	                    
	                    String historyButtonId = "historyButton_" + count_p_id;
	                    String printButtonId = "printButton_" + id;
	                    String historyButton = "<i id='" + historyButtonId + "' class='fa fa-eye' data-toggle='modal' data-target='#myHistoryModal' data-encrypted-pk='" + EncryptedPk + "'></i>";
	                    String printButton = "<i id='" + printButtonId + "' class='action_icons action_download' title='Download Work Order'"+"data-encrypted-pk='" + EncryptedPk + "'     ></i>";	          
	                    String f1 = printButton;
	                    String f = historyButton;

	                    columns.put("history", "");
	                    columns.put("action", f1);
	                    
			list.add(columns);
	}
rs.close();
stmt.close();
conn.close();
} catch (SQLException e) {
	//e.printStackTrace();
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

	public long getLogBookTotalCount(String search, 
			String catgory, String assets_name, String make_name, 
			String status, HttpSession session) {

		
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			q ="" ;
			String searchValue="";
			if(search!="")
			{
				searchValue+=" and(  upper(a.category) like ? or  upper(a.assets_name) like ? or  upper(a.make_name) like ? or  upper(a.model_name) like ?    )";
			}
			if(assets_name!="0" && !assets_name.equals("0"))
			{
				if(catgory.equals("1"))
				{
				searchValue +=" and a.asset_type::text = ? ";
				
				}
				if(catgory.equals("2")|| catgory.equals("3") )
				{
					searchValue +=" and a.assets_type::text = ? ";
				}
			}
			if(make_name!="0"&& !make_name.equals("0"))
			{
				
				searchValue +=" and a.cp_make :: text = ? ";
			}
			
			if(catgory.equals("1"))
			{
				q = "select count(a.*) from (select cp.id, cp.status,cp.asset_type,cp.make_name as cp_make,CASE "
						+ "										 WHEN ass.category=1 THEN 'COMPUTING' "
						+ "											 WHEN ass.category=2 THEN 'PERIPHERAL' "
						+ "											 WHEN ass.category=3 THEN 'CONSUMABLES' "
						+ "											 ELSE 'Other' "
						+ "										 END AS category ,ass.assets_name,make.make_name ,model.model_name  from tb_asset_main cp "
						+ "left join tb_mstr_assets ass on ass.id = cp.asset_type and ass.category = 1 \n"
						+ "left join tb_mstr_make make on make.id = cp.make_name and  make.assets_name = cp.asset_type \n"
						+ "left join tb_mstr_model model on model.id = cp.model_name and  model.assets_name = cp.asset_type and model.make_name = cp.make_name\n"
						+ " ) as a where a.status=1 "+searchValue
					 ;

			}
			if(catgory.equals("2"))
			{
				q = " select count(a.*) from (select cp.id, cp.status,cp.make_name as cp_make,CASE "
						+ "							 WHEN ass.category=1 THEN 'COMPUTING' "
						+ "										 WHEN ass.category=2 THEN 'PERIPHERAL' "
						+ "										 WHEN ass.category=3 THEN 'CONSUMABLES' "
						+ "								 ELSE 'Other' "
						+ "									 END AS category ,ass.assets_name,make.make_name ,model.model_name,cp.assets_type  from it_asset_peripherals cp "
						+ "left join tb_mstr_assets ass on ass.id = cp.assets_type and ass.category = 2 \n"
						+ "left join tb_mstr_make make on make.id = cp.make_name and  make.assets_name = cp.assets_type \n"
						+ "left join tb_mstr_model model on model.id = cp.model_name and  model.assets_name = cp.assets_type and model.make_name = cp.make_name\n"
						+ " ) as a  where a.status=1"+searchValue
						;

			}
			

			if(catgory.equals("3"))
			{
				q = " select count(a.*)  from (SELECT	CP.ID,CP.STATUS,CP.MAKE_NAME AS CP_MAKE,CASE\n"
						+ "				WHEN ASS.CATEGORY = 1 THEN 'COMPUTING'\n"
						+ "				WHEN ASS.CATEGORY = 2 THEN 'PERIPHERAL'\n"
						+ "				WHEN ASS.CATEGORY = 3 THEN 'CONSUMABLES'	ELSE 'Other'\n"
						+ "			END AS CATEGORY, ASS.ASSETS_NAME, MAKE.MAKE_NAME,\n"
						+ "			MODEL.MODEL_NAME,cp.Assets_type	FROM\n"
						+ "			tb_consumable_main CP\n"
						+ "			LEFT JOIN TB_MSTR_ASSETS ASS ON ASS.ID = CP.ASSETS_TYPE\n"
						+ "			AND ASS.CATEGORY = 3\n"
						+ "			LEFT JOIN TB_MSTR_MAKE MAKE ON MAKE.ID = CP.MAKE_NAME\n"
						+ "			AND MAKE.ASSETS_NAME = CP.ASSETS_TYPE\n"
						+ "			LEFT JOIN TB_MSTR_MODEL MODEL ON MODEL.ID = CP.MODEL_NAME\n"
						+ "			AND MODEL.ASSETS_NAME = CP.ASSETS_TYPE\n"
						+ "			AND MODEL.MAKE_NAME = CP.MAKE_NAME  ) as a where a.status=1 " + searchValue ;
				

			}
			
			
			
			PreparedStatement stmt = conn.prepareStatement(q);
			int flag=0;
			if(search!="")
			{
				flag += 1;
				stmt.setString(flag, "%"+search.toUpperCase()+"%");
				flag += 1;
				stmt.setString(flag, "%"+search.toUpperCase()+"%");
				flag += 1;
				stmt.setString(flag, "%"+search.toUpperCase()+"%");
				flag += 1;
				stmt.setString(flag, "%"+search.toUpperCase()+"%");
				}
			if(assets_name!="0" && !assets_name.equals("0"))
			{
				flag += 1;
				stmt.setString(flag,assets_name );
				
			}
			if(make_name!="0"&& !make_name.equals("0"))
			{
				flag += 1;
				stmt.setString(flag,make_name );
			}
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
	public ArrayList<ArrayList<String>> LogBookData(String logbookId,String category) {
	
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
	  	Connection conn = null;
	  	String q="";
	  	try{	  
	  		conn = dataSource.getConnection();			 
	  		PreparedStatement stmt=null;
	  		
	  		if(category.equals("1"))
	  		{
	  			q = "  select asset.assets_name,make.make_name,main.redgno as redg_no, main.crv_no,\n"
		  				+ "main.warranty,main.proc_date,  COALESCE(maintain.maintenance ,'') as maintenance \n"
		  				+ "from tb_asset_main  main\n"
		  				+ "inner join tb_mstr_assets asset on asset.id=main.asset_type  \n"
		  				+ "inner join tb_mstr_make make on make.id=main.make_name \n"
		  				+ "left join tb_mstr_maintenance_agency maintain on maintain.id :: text=main.maintainagency "
		  				+ " where main.status=1 and main.id=? ";
	  		}
	  		if(category.equals("2"))
	  		{
	  			q = "  select asset.assets_name,make.make_name,main.redgno as redg_no, main.crv_no,\n"
	  					+ "main.warranty,main.proc_date,  COALESCE(maintain.maintenance ,'') as maintenance \n"
	  					+ "from IT_ASSET_PERIPHERALS  main\n"
	  					+ "inner join tb_mstr_assets asset on asset.id=main.assets_type  \n"
	  					+ "inner join tb_mstr_make make on make.id=main.make_name \n"
	  					+ "left join tb_mstr_maintenance_agency maintain on maintain.id :: text=main.maintainagency  where main.status=1 and main.id=?  ";
	  		}
	  		
	  		if(category.equals("3"))
	  		{
	  			q = "  select asset.assets_name,make.make_name,main.redgno as redg_no, main.crv_no,\n"
	  					+ "main.warranty,main.proc_date,  COALESCE(maintain.maintenance ,'') as maintenance \n"
	  					+ "from tb_consumable_main  main\n"
	  					+ "inner join tb_mstr_assets asset on asset.id=main.assets_type  \n"
	  					+ "inner join tb_mstr_make make on make.id=main.make_name \n"
	  					+ "left join tb_mstr_maintenance_agency maintain on maintain.id :: text=main.maintainagency  where main.status=1 and main.id=?  ";
	
	  		}
	  		
	  			stmt=conn.prepareStatement(q);
				stmt.setInt(1, Integer.parseInt(logbookId));
				
	  			ResultSet rs = stmt.executeQuery(); 
	  			ResultSetMetaData metaData = rs.getMetaData();
	  			int columnCount = metaData.getColumnCount();

	  			 while (rs.next()) {
	  		        ArrayList<String> list = new ArrayList<>();
	  		        
	  		        for (int i = 1; i <= columnCount; i++) { // Start from 1 to columnCount
	  		            Object value = rs.getObject(i);
	  		            list.add(value != null ? value.toString() : null); // Handle null values
	  		        }
	  		        
	  		        alist.add(list);
	  		    }
	  	      rs.close();
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
	  	return alist;
		
	
	}

}
