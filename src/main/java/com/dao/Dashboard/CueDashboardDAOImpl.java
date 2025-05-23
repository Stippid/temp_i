package com.dao.Dashboard;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.github.dandelion.core.utils.StringUtils;
import com.github.dandelion.datatables.core.ajax.ColumnDef;
import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;

//import com.models.psg.Transaction.TB_TRANS_PROPOSED_COMM_LETTER;
import com.persistance.util.HibernateUtil;

@Service
@Repository
public class CueDashboardDAOImpl implements CueDashboardDAO {
	
	private DataSource dataSource;
	  
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    public List<Map<String, Object>> getAllReportListJdbc(String qry)
  	{				
  		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
  		Connection conn = null;
  		try{	  
  			conn = dataSource.getConnection();					
  			PreparedStatement stmt=conn.prepareStatement(qry);
  			ResultSet rs = stmt.executeQuery();      
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
    
 

	private static StringBuilder getFilterQueryCommonReportWEPECond(DatatablesCriterias criterias,StringBuilder queryBuilder1) {
		StringBuilder queryBuilder = new StringBuilder();
		List<String> paramList = new ArrayList<String>();
		if (StringUtils.isNotBlank(criterias.getSearch()) && criterias.hasOneFilterableColumn()) {
			if(!queryBuilder1.toString().contains("where")){
				queryBuilder.append(" WHERE ");
			}
			else{
				queryBuilder.append(" AND (");
			}
			for (ColumnDef columnDef : criterias.getColumnDefs()) {
				if (columnDef.isFilterable() && StringUtils.isBlank(columnDef.getSearch())) {
					if(columnDef.getName().equalsIgnoreCase("id"))
					{
						if(criterias.getSearch().matches("[0-9]+"))
						{
							paramList.add(" d." + columnDef.getName()	+ " = '?'".replace("?", criterias.getSearch().toLowerCase()));
						}
					}
					else{
						paramList.add(" LOWER(d." + columnDef.getName()	+ ") LIKE '%?%'".replace("?", criterias.getSearch().toLowerCase()));
					}
				}
			}
			Iterator<String> itr = paramList.iterator();
			while (itr.hasNext()) {
				queryBuilder.append(itr.next());
				if (itr.hasNext()) {
					queryBuilder.append(" OR ");
				}
			}
			queryBuilder.append(")");
		}
		return queryBuilder;
	}
	private Long getTotalCountCommonReportWEPECond(String countQry) {
		Session session= HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		q = session.createQuery(countQry);
		Long count = (Long) q.list().get(0);
		tx.commit();
		session.close();
		return count;
	}
	
	
	public DataSet<Map<String, Object>> DatatablesCriteriasCommonReportUnit(DatatablesCriterias criterias ,String pageTypeU) {
		String mainQry = "";
		String TotalCountQry = "";
		String FilteredCountQry = "";
		if(pageTypeU.equals("activeunits")) {
			mainQry = "SELECT \r\n" + 
					"    a.sus_no,\r\n" + 
					"    a.unit_name,\r\n" + 
					"    ab.cmd_name,\r\n" + 
					"    ac.coprs_name,\r\n" + 
					"    ad.div_name,\r\n" + 
					"    ae.bde_name,\r\n" + 
					"    e.code_value AS location,\r\n" + 
					"    nr.code_value AS nrs,\r\n" + 
					"    x.label AS type_of_force,\r\n" + 
					"    a.ct_part_i_ii,\r\n" + 
					"    d.arm_desc\r\n" + 
					"   FROM tb_orbat_unt_dtl a\r\n" + 
					"     JOIN tb_orbat_shdul_detl b ON a.id = b.letter_id\r\n" + 
					"     LEFT JOIN tb_orbat_arm_code d ON a.arm_code::text = d.arm_code::text\r\n" + 
					"     LEFT JOIN Tb_Orbat_Code e ON a.code::text = e.code::text AND e.code_type::text = 'Location'::text\r\n" + 
					"     LEFT JOIN Tb_Orbat_Code nr ON a.nrs_code::text = nr.code::text AND nr.code_type::text = 'Location'::text\r\n" + 
					"     LEFT JOIN t_domain_value x ON x.domainid = 'TYPEOFFORCE'::text AND x.codevalue = a.type_force::text\r\n" + 
					"     LEFT JOIN orbat_view_cmd ab ON substr(a.form_code_control::text, 1, 1) = ab.cmd_code::text\r\n" + 
					"     LEFT JOIN orbat_view_corps ac ON substr(a.form_code_control::text, 2, 2) = ac.corps_code::text\r\n" + 
					"     LEFT JOIN orbat_view_div ad ON substr(a.form_code_control::text, 4, 3) = ad.div_code::text\r\n" + 
					"     LEFT JOIN orbat_view_bde ae ON substr(a.form_code_control::text, 7, 4) = ae.bde_code::text" + 
					"    where a.status_sus_no='Active'  order by a.form_code_control";
			TotalCountQry = "SELECT \r\n" + 
					"    count(*) as total \r\n" + 
					"   FROM tb_orbat_unt_dtl a\r\n" + 
					"     JOIN tb_orbat_shdul_detl b ON a.id = b.letter_id\r\n" + 
					"    where a.status_sus_no='Active'";
			FilteredCountQry =  "SELECT \r\n" + 
					"     count(*) as total \r\n" + 
					"   FROM tb_orbat_unt_dtl a\r\n" + 
					"     JOIN tb_orbat_shdul_detl b ON a.id = b.letter_id\r\n" + 
					"    where a.status_sus_no='Active'";
		}
		if(pageTypeU.equals("unitmove")) {
			mainQry = "SELECT \r\n" + 
					"     a.sus_no, \r\n" + 
					"    a.unit_name, \r\n" + 
					"    ab.cmd_name, \r\n" + 
					"    ac.coprs_name, \r\n" + 
					"    ad.div_name, \r\n" + 
					"    ae.bde_name, \r\n" + 
					"    e.code_value AS location, \r\n" + 
					"    nr.code_value AS nrs, \r\n" + 
					"    x.label AS type_of_force, \r\n" + 
					"    a.ct_part_i_ii, \r\n" + 
					"    d.arm_desc\r\n" + 
					"  FROM tb_orbat_unt_dtl a\r\n" + 
					"     JOIN tb_orbat_shdul_detl b ON a.id = b.letter_id\r\n" + 
					"     LEFT JOIN tb_orbat_arm_code d ON a.arm_code::text = d.arm_code::text\r\n" + 
					"     LEFT JOIN Tb_Orbat_Code e ON a.code::text = e.code::text AND e.code_type::text = 'Location'::text\r\n" + 
					"     LEFT JOIN Tb_Orbat_Code nr ON a.nrs_code::text = nr.code::text AND nr.code_type::text = 'Location'::text\r\n" + 
					"     LEFT JOIN t_domain_value x ON x.domainid = 'TYPEOFFORCE'::text AND x.codevalue = a.type_force::text\r\n" + 
					"     LEFT JOIN orbat_view_cmd ab ON substr(a.form_code_control::text, 1, 1) = ab.cmd_code::text\r\n" + 
					"     LEFT JOIN orbat_view_corps ac ON substr(a.form_code_control::text, 2, 2) = ac.corps_code::text\r\n" + 
					"     LEFT JOIN orbat_view_div ad ON substr(a.form_code_control::text, 4, 3) = ad.div_code::text\r\n" + 
					"     LEFT JOIN orbat_view_bde ae ON substr(a.form_code_control::text, 7, 4) = ae.bde_code::text\r\n" + 
					"where b.type_of_letter in ('8','9') and a.status_sus_no not in ('Pending') order by a.form_code_control";
			TotalCountQry = "SELECT \r\n" + 
					"    		count(*) as total \r\n" + 
					"		FROM tb_orbat_unt_dtl a\r\n" + 
					"		JOIN tb_orbat_shdul_detl b ON a.id = b.letter_id\r\n" + 
					" 		where b.type_of_letter in ('8','9') and a.status_sus_no not in ('Pending') ";
			FilteredCountQry = "SELECT \r\n" + 
					"     			count(*) as total \r\n" + 
					"	   	FROM tb_orbat_unt_dtl a\r\n" + 
					"   	JOIN tb_orbat_shdul_detl b ON a.id = b.letter_id\r\n" + 
					"	    where b.type_of_letter in ('8','9') and a.status_sus_no not in ('Pending') ";
		}
		List<Map<String, Object>> metadata = findAllActiveOrbatDetailsCriterias(criterias,mainQry);
		Long count = getTotalCountActiveDetails(TotalCountQry);
		Long countFiltered = getFilteredCountActiveDetails(FilteredCountQry);
		return new DataSet<Map<String, Object>>(metadata, count, countFiltered);
	}
	private List<Map<String, Object>> findAllActiveOrbatDetailsCriterias(DatatablesCriterias criterias , String qry) {
		StringBuilder queryBuilder = null;
		queryBuilder = new StringBuilder(qry);
		if (criterias.hasOneSortedColumn()) {
			List<String> orderParams = new ArrayList<String>();
			Iterator<String> itr2 = orderParams.iterator();
			while (itr2.hasNext()) {
				queryBuilder.append(itr2.next());
				if (itr2.hasNext()) {
					queryBuilder.append(" , ");
				}
			}
		}
		queryBuilder.append(" limit "+criterias.getDisplaySize()+" OFFSET "+ criterias.getDisplayStart()+"");
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		try{	  
    		conn = dataSource.getConnection();
    		PreparedStatement stmt=conn.prepareStatement(queryBuilder.toString());
    		ResultSet rs1 = stmt.executeQuery();     
    		for(int i =0 ; rs1.next() ;i++) {
    			Map<String, Object> columns = new LinkedHashMap<String, Object>();
    			columns.put("id", criterias.getDisplayStart()+i+1);
    			columns.put("sus_no", rs1.getString("sus_no"));
    			columns.put("unit_name", rs1.getString("unit_name"));
    			columns.put("cmd_name", rs1.getString("cmd_name"));
    			columns.put("coprs_name", rs1.getString("coprs_name"));
    			columns.put("div_name", rs1.getString("div_name"));
    			columns.put("bde_name", rs1.getString("bde_name"));
    			columns.put("location", rs1.getString("location"));
    			columns.put("nrs", rs1.getString("nrs"));
    			columns.put("arm_desc", rs1.getString("arm_desc"));
    			columns.put("ct_part_i_ii", rs1.getString("ct_part_i_ii"));
    			columns.put("type_of_force", rs1.getString("type_of_force"));
    			list.add(columns);
    		}
    		rs1.close();
    		stmt.close();
    	}catch (SQLException e) {
    		e.printStackTrace();
		}
		return list;
	}
	private Long getTotalCountActiveDetails(String qry) {
		Connection conn = null;
		Long count = (long) 0;
		try{	  
    		conn = dataSource.getConnection();
    		PreparedStatement stmt=conn.prepareStatement(qry);
			ResultSet rs = stmt.executeQuery();   
			if (rs.next()) {
				count = rs.getLong(1);
	    	}
    		rs.close();
    		stmt.close();
    	}catch (SQLException e) {
    		e.printStackTrace();
		}
		return count;
	}
	private Long getFilteredCountActiveDetails(String qry) {
		StringBuilder queryBuilder = null;
		queryBuilder = new StringBuilder(qry);
		Connection conn = null;
		Long FilteredCount = (long) 0;
		try{	  
    		conn = dataSource.getConnection();
    		PreparedStatement stmt=conn.prepareStatement(queryBuilder.toString());
    		ResultSet rs = stmt.executeQuery();     
			if (rs.next()) {
				FilteredCount = rs.getLong(1);
	    	}
			rs.close();
    		stmt.close();
    	}catch (SQLException e) {
    		
		}
		return FilteredCount;
	}
	
		//////////////////////////////////////
		public List<Map<String, Object>> getCueTypeYearWiseUE(int fromYear, int toYear, String arm)
	  	{				
	  		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	  		Connection conn = null;
	  		String q="";
	  	
	  		try{	  
	  			conn = dataSource.getConnection();	
	  			
	  			if(!arm.equals("0")) {
	  				q ="and  arm = ?";
	  			}
	  			String qry="select substring(created_on,7,6) as getyear,count(we_pe) FILTER (where we_pe='WE') as we,count(we_pe) FILTER (where we_pe='PE') as pe,count(we_pe) FILTER (where we_pe='FE') as fe,count(we_pe) FILTER (where we_pe='GSL') as gsl  from cue_tb_wepe_upload_condition where we_pe!='' and status='1' \r\n" + 
	  					" and substring(created_on,7,6)::text between ?::text and ?::text " + q +" group by 1 order by 1" ; 
	  			
	  			PreparedStatement stmt=conn.prepareStatement(qry);
	  			
	  			stmt.setInt(1, fromYear);
	  			stmt.setInt(2, toYear);	  		
	  			if(!arm.equals("0")){ 				
					stmt.setString(3, arm);
		  	  			}
	  			ResultSet rs = stmt.executeQuery();      
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
		
		public List<Map<String, Object>> getCueTypeMonthWiseUE(int year, String doc_type, String arm1)
	  	{		
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	  		Connection conn = null;
	  		String q="";
	  		String y="";
	  		try{	  
	  			conn = dataSource.getConnection();		
	  			if(doc_type.equals("we")) {
	  				q = "count(we_pe) FILTER (where we_pe='WE') as we";
	  			}
	  			if(doc_type.equals("pe")) {
	  				q = "count(we_pe) FILTER (where we_pe='PE') as pe";
	  			}
	  			if(doc_type.equals("fe")) {
	  				q = "count(we_pe) FILTER (where we_pe='FE') as fe";
	  			}
	  			if(doc_type.equals("gsl")) {
	  				q = "count(we_pe) FILTER (where we_pe='GSL') as gsl";
	  			}
	  			if(!arm1.equals("0")) {
	  			    y= " and arm =? " ;
	  			}
	  			
	  			String qry="select substring(created_on,4,2) as getmonth, "+ q +" from cue_tb_wepe_upload_condition where we_pe!='' and status='1' \r\n" + 
	  					"and substring(created_on,7,6)::text=?::text "+ y +" group by 1 order by 1" ;  		
	  			
	  			PreparedStatement stmt=conn.prepareStatement(qry);
	  			
	  			stmt.setInt(1, year);
	  			if(!arm1.equals("0")) {
	  				stmt.setString(2, arm1);
	  			}
	  			ResultSet rs = stmt.executeQuery();      
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
		
		
		
		
		public List<Map<String, Object>> getCommandWiseUnitMov(Date fromDate,Date toDate)
		  { 
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		  Connection conn = null;
		  try{   
		  conn = dataSource.getConnection(); 
		  String qry=" select ab.cmd_name AS cmd_name,\r\n" + 
		  "                 substr(cur.form_code_control::text, 1, 1) AS cmd_code,\r\n" + 
		  "               COALESCE(count(substr(a.imdt_fmn ,1,1)) FILTER (where substr(a.imdt_fmn ,1,1) = '1'), 0::numeric) as count1,     \r\n" + 
		  "               COALESCE(count(substr(a.imdt_fmn ,1,1)) FILTER (where substr(a.imdt_fmn ,1,1) = '2'), 0::numeric) as count2,     \r\n" + 
		  "               COALESCE(count(substr(a.imdt_fmn ,1,1)) FILTER (where substr(a.imdt_fmn ,1,1) = '3'), 0::numeric) as count3,     \r\n" + 
		  "               COALESCE(count(substr(a.imdt_fmn ,1,1)) FILTER (where substr(a.imdt_fmn ,1,1) = '4'), 0::numeric) as count4,     \r\n" + 
		  "               COALESCE(count(substr(a.imdt_fmn ,1,1)) FILTER (where substr(a.imdt_fmn ,1,1) = '5'), 0::numeric) as count5,     \r\n" + 
		  "               COALESCE(count(substr(a.imdt_fmn ,1,1)) FILTER (where substr(a.imdt_fmn ,1,1) = '6'), 0::numeric) as count6,     \r\n" + 
		  "               COALESCE(count(substr(a.imdt_fmn ,1,1)) FILTER (where substr(a.imdt_fmn ,1,1) = '7'), 0::numeric) as count7,     \r\n" + 
		  "               COALESCE(count(substr(a.imdt_fmn ,1,1)) FILTER (where substr(a.imdt_fmn ,1,1) = '8'), 0::numeric) as count8,     \r\n" + 
		  "               COALESCE(count(substr(a.imdt_fmn ,1,1)) FILTER (where substr(a.imdt_fmn ,1,1) = '9'), 0::numeric) as count9,     \r\n" + 
		  "               COALESCE(count(substr(a.imdt_fmn ,1,1)) FILTER (where substr(a.imdt_fmn ,1,1) = '0'), 0::numeric) as count10,     \r\n" + 
		  "               COALESCE(count(substr(a.imdt_fmn ,1,1)) FILTER (where substr(a.imdt_fmn ,1,1) = 'A'), 0::numeric) as count11     \r\n" + 
		  "               from tb_orbat_relief_prgm a\r\n" + 
		  "       JOIN tb_orbat_unt_dtl cur ON a.sus_no::text = cur.sus_no::text \r\n" + 
		  "       LEFT JOIN orbat_view_cmd cur_ab ON substr(cur.form_code_control::text, 1, 1) = cur_ab.cmd_code::text\r\n" + 
		  "         LEFT JOIN orbat_view_cmd ab ON substr(a.imdt_fmn::text, 1, 1) = ab.cmd_code::text\r\n" + 
		  "                   LEFT JOIN orbat_view_corps ac ON substr(a.imdt_fmn::text, 2, 2) = ac.corps_code::text\r\n" + 
		  "                   LEFT JOIN orbat_view_div ad ON substr(a.imdt_fmn::text, 4, 3) = ad.div_code::text\r\n" + 
		  "                   LEFT JOIN orbat_view_bde ae ON substr(a.imdt_fmn::text, 7, 4) = ae.bde_code::text\r\n" + 
		  "       \r\n" + 
		  "       WHERE a.sd_status='1' and a.nmb_date::text <> ''::text AND (a.miso_status::text = '0'::text OR a.miso_status::text = ''::text OR a.miso_status IS NULL)\r\n" + 
		  "       and cast(a.nmb_date as Date) between cast(? as Date) and cast(? as date) AND upper(cur.status_sus_no::text) = 'ACTIVE'::text \r\n" + 
		  "               GROUP BY 1,2 ";
		  
		  PreparedStatement stmt=conn.prepareStatement(qry);
		  
		  stmt.setDate(1, fromDate);
		  stmt.setDate(2, toDate);
		  
		  
		  ResultSet rs = stmt.executeQuery();           
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
		
		public List<Map<String, Object>> getCommandWiseActionCluster(Date fromDate,Date toDate)
	  	{		
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	  		Connection conn = null;
	  		try{	  
	  			conn = dataSource.getConnection();		
	  			String qry ="select distinct	c.cmd_name as cmd_name," + 
						"	c.cmd_code,\r\n" + 
						"	count(case when s.type_of_letter in ('0') then s.id end) as nrus,\r\n" + 
						"	count(case when s.type_of_letter in ('4') then s.id end) as disbanded,\r\n" + 
						"	count(case when s.type_of_letter in ('8','9') then s.id end) as unit_move_details,\r\n" + 
						"	count(case when s.type_of_letter in ('2','11','1') then s.id end) as converted\r\n" + 
						"from tb_orbat_unt_dtl a \r\n" + 
						"inner join tb_orbat_shdul_detl s on a.id = s.letter_id and cast(s.approved_rejected_on as Date) between cast(? as Date) and cast(? as date) \r\n" + 
						"Right join orbat_view_cmd c on substr(a.form_code_control,1,1)= c.cmd_code\r\n" + 
						"\r\n" + 
						"where  a.status_sus_no not in ('Pending') \r\n" + 
						"group by 1,2 order by 2";
	  			
	  			PreparedStatement stmt=conn.prepareStatement(qry);
	  			
	  			stmt.setDate(1, fromDate);
	  			stmt.setDate(2, toDate);
	  			
	  			ResultSet rs = stmt.executeQuery();      
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

		
// TRANSPORT
		
		public List<Map<String, Object>> DatatablesReporTpt(int startPage, int pageLength, String search,
				String orderColunm, String orderType, HttpSession sessionUserId, String std_nomclature, String arm,
				String cont_comd, String cont_corps,String cont_div, String cont_bde) 
		{
		 String SearchValue = GenerateQueryWhereClause_SQLTPT(search, std_nomclature,arm, cont_comd, cont_corps, cont_div, cont_bde);
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
			
					q= "select udtl.comd_name,\r\n"
							+ "	COALESCE(SUM(COALESCE(ue.total, 0)) FILTER (WHERE udtl.type_of_force = 'FF'), 0) AS ff,\r\n"
							+ "   COALESCE(SUM(COALESCE(ue.total, 0)) FILTER (WHERE udtl.type_of_force in ('NFF','TRG EST')), 0) AS Nff,\r\n"
							+ "   COALESCE(SUM(COALESCE(ue.total, 0))) AS TOTALUE										\r\n"
							+ "	from cue_transport_ue_final ue\r\n"
							+ "JOIN tb_tms_mct_main_master b ON ue.mct_no::text = b.mct_main_id\r\n"
							+ "	 LEFT JOIN ( SELECT DISTINCT tb_tms_mct_slot_master.group_name,\r\n"
							+ "                    tb_tms_mct_slot_master.prf_code,\r\n"
							+ "                    tb_tms_mct_slot_master.type_of_veh\r\n"
							+ "                   FROM tb_tms_mct_slot_master) prf ON prf.prf_code::text = b.prf_code::text\r\n"
							+ "LEFT JOIN ( SELECT a.id,\r\n"
							+ "            a.unit_sus,\r\n"
							+ "			substr(a.form_code_control,1,1) as cmd,\r\n"
							+ "            substr(a.form_code_control,1,3) as corps,\r\n"
							+ "            substr(a.form_code_control,1,6) as div,\r\n"
							+ "			a.form_code_control as bde,\r\n"
							+ "            a.comd_name,\r\n"
							+ "			a.arm_code,\r\n"
							+ "	 a.type_of_force\r\n"
							+ "           FROM orbat_all_units_view a\r\n"
							+ "             LEFT JOIN ( SELECT ls.sus_no,\r\n"
							+ "                    string_agg(ls.modification::text, ','::text) AS modif\r\n"
							+ "                   FROM cue_tb_wepe_link_sus_trans_mdfs ls\r\n"
							+ "                  WHERE ls.sus_no IS NOT NULL\r\n"
							+ "                  GROUP BY ls.sus_no) b ON a.unit_sus::text = b.sus_no::text\r\n"
							+ "          WHERE a.status_sus_no::text = 'Active'::text) udtl ON udtl.unit_sus::text = ue.sus_no::text\r\n"
							+ "LEFT JOIN tb_orbat_arm_code ac ON ac.arm_code::text = (\"left\"(udtl.arm_code::text, 2) || '00'::text)\r\n"
							+ "     LEFT JOIN cue_tb_wepeconditions wecon ON wecon.we_pe_no::text = ue.we_pe_no::text AND wecon.type::text = '2'::text\r\n"
							+SearchValue+ " GROUP BY udtl.comd_name";
				
				stmt=conn.prepareStatement(q);
				
				stmt = setQueryWhereClause_SQLTPT(stmt,search, std_nomclature,arm,
						 cont_comd, cont_corps, cont_div, cont_bde);
		      ResultSet rs = stmt.executeQuery();   
		  
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
		
		
		
		public String GenerateQueryWhereClause_SQLTPT(String search, String std_nomclature, String arm,
				String cont_comd, String cont_corps,String cont_div, String cont_bde) {
		 
			String SearchValue ="";		 
			if(!search.equals("")) { // for Input Filter
				SearchValue =" where  ";
				
				SearchValue +="( upper(udtl.comd_name) like ?)";
			}
			
			if (!std_nomclature.equals("")) {
				String temp = "";
				String[] datacmd = std_nomclature.split(Pattern.quote("|"));

				for (int i = 0; i < datacmd.length; i++) {

					if (i == 0) {
						temp = temp + "?";
					} else {
						temp = temp + ",?";
					}
				}
					SearchValue += " and  b.mct_nomen in (" + temp +")  ";	
			}
			
			if( !arm.equals("0")) {
				if (SearchValue.contains("where")) {
					SearchValue += " and  udtl.arm_code like ?  ";	
				}
				else {
					SearchValue += " where udtl.arm_code like ? ";
				}
			}
			
			if( !cont_comd.equals("")) {
				if (SearchValue.contains("where")) {
					SearchValue += " and  udtl.cmd like ?  ";	
				}
				else {
					SearchValue += " where udtl.cmd  like ? ";
				}
			}
			
			if( !cont_corps.equals("0")) {
				if (SearchValue.contains("where")) {
					SearchValue += " and  udtl.corps like ?  ";	
				}
				else {
					SearchValue += " where udtl.corps like ? ";
				}
			}
			if( !cont_div.equals("0")) {
				if (SearchValue.contains("where")) {
					SearchValue += " and  udtl.div like ?  ";	
				}
				else {
					SearchValue += " where udtl.div like ? ";
				}
			}
			if( !cont_bde.equals("0")) {
				if (SearchValue.contains("where")) {
					SearchValue += " and  udtl.bde like ?  ";	
				}
				else {
					SearchValue += " where  udtl.bde like ? ";
				}
			}
			return SearchValue;
		}	
		
		public PreparedStatement setQueryWhereClause_SQLTPT(PreparedStatement
				  stmt,String search,String std_nomclature, String arm,
					String cont_comd, String cont_corps,String cont_div, String cont_bde) {
			int flag = 0;
			int j=1;
			try {
				if(!search.equals("")) {			
					
					flag += 1;
					stmt.setString(flag, search.toUpperCase()+"%");
				}
				
				if (!std_nomclature.equals("")) {
					
					String[] datacmd = std_nomclature.split(Pattern.quote("|"));
					for (int i = 0; i < datacmd.length; i++) {
						stmt.setString(j, datacmd[i]);
						j += 1;
						flag += 1;
					}
				}
				if(!arm.equals("0")) {
					flag += 1;
					stmt.setString(flag, arm.toUpperCase()+"%");
					
				}
				if(!cont_comd.equals("")) {
					flag += 1;
					stmt.setString(flag, cont_comd.toUpperCase()+"%");
					
				}
				if(!cont_corps.equals("0")) {
					flag += 1;
					stmt.setString(flag, cont_corps.toUpperCase()+"%");
					
				}
				if(!cont_div.equals("0")) {
					flag += 1;
					stmt.setString(flag, cont_div.toUpperCase()+"%");
					
				}
				if(!cont_bde.equals("0")) {
					flag += 1;
					stmt.setString(flag, cont_bde.toUpperCase()+"%");
					
				}
				
				
			}catch (Exception e) {}
			
			return stmt;
			
		}
	
	public long DatatablesReporTptcount(String Search,String orderColunm,String orderType,HttpSession sessionUserId, String std_nomclature, String arm,
			String cont_comd, String cont_corps,String cont_div, String cont_bde) {
			String SearchValue = GenerateQueryWhereClause_SQLTPT(Search, std_nomclature,arm, cont_comd,  cont_corps, cont_div,  cont_bde);
			int total = 0;
				
				String q = null;
				Connection conn = null;
				try {
					conn = dataSource.getConnection();
				
						q= "select count (app.*) from(select udtl.comd_name,\r\n"
								+ "	COALESCE(SUM(COALESCE(ue.total, 0)) FILTER (WHERE udtl.type_of_force = 'FF'), 0) AS ff,\r\n"
								+ "   COALESCE(SUM(COALESCE(ue.total, 0)) FILTER (WHERE udtl.type_of_force in ('NFF','TRG EST')), 0) AS Nff,\r\n"
								+ "   COALESCE(SUM(COALESCE(ue.total, 0))) AS TOTALUE										\r\n"
								+ "	from cue_transport_ue_final ue\r\n"
								+ "JOIN tb_tms_mct_main_master b ON ue.mct_no::text = b.mct_main_id\r\n"
								+ "	 LEFT JOIN ( SELECT DISTINCT tb_tms_mct_slot_master.group_name,\r\n"
								+ "                    tb_tms_mct_slot_master.prf_code,\r\n"
								+ "                    tb_tms_mct_slot_master.type_of_veh\r\n"
								+ "                   FROM tb_tms_mct_slot_master) prf ON prf.prf_code::text = b.prf_code::text\r\n"
								+ "LEFT JOIN ( SELECT a.id,\r\n"
								+ "            a.unit_sus,\r\n"
								+ "			substr(a.form_code_control,1,1) as cmd,\r\n"
								+ "            substr(a.form_code_control,1,3) as corps,\r\n"
								+ "            substr(a.form_code_control,1,6) as div,\r\n"
								+ "			a.form_code_control as bde,\r\n"
								+ "            a.comd_name,\r\n"
								+ "			a.arm_code,\r\n"
								+ "	 a.type_of_force\r\n"
								+ "           FROM orbat_all_units_view a\r\n"
								+ "             LEFT JOIN ( SELECT ls.sus_no,\r\n"
								+ "                    string_agg(ls.modification::text, ','::text) AS modif\r\n"
								+ "                   FROM cue_tb_wepe_link_sus_trans_mdfs ls\r\n"
								+ "                  WHERE ls.sus_no IS NOT NULL\r\n"
								+ "                  GROUP BY ls.sus_no) b ON a.unit_sus::text = b.sus_no::text\r\n"
								+ "          WHERE a.status_sus_no::text = 'Active'::text) udtl ON udtl.unit_sus::text = ue.sus_no::text\r\n"
								+ "LEFT JOIN tb_orbat_arm_code ac ON ac.arm_code::text = (\"left\"(udtl.arm_code::text, 2) || '00'::text)\r\n"
								+ "     LEFT JOIN cue_tb_wepeconditions wecon ON wecon.we_pe_no::text = ue.we_pe_no::text AND wecon.type::text = '2'::text\r\n"
								+SearchValue+
							      " GROUP BY udtl.comd_name) app ";
					
					PreparedStatement stmt = conn.prepareStatement(q);
					stmt = setQueryWhereClause_SQLTPT(stmt,Search, std_nomclature,arm,
							 cont_comd, cont_corps, cont_div, cont_bde);
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
		
	// WEAPON
		
		public List<Map<String, Object>> DatatablesReporWpn(int startPage, int pageLength, String search,
				String orderColunm, String orderType, HttpSession sessionUserId, String nomenClature,String cont_comd, String cont_corps, 
				String cont_div, String cont_bde,String arm ) 
		{
		 String SearchValue = GenerateQueryWhereClause_SQLWPN(search,nomenClature, cont_comd,  cont_corps, 
					 cont_div,  cont_bde, arm);
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
			
					q= "SELECT\r\n"
							+ "    a.unit_name AS cmd_name,\r\n"
							+ "    COALESCE(SUM(COALESCE(mview.total_ue_qty, 0)) FILTER (WHERE u.type_force = '0'), 0) AS ff,\r\n"
							+ "    COALESCE(SUM(COALESCE(mview.total_ue_qty, 0)) FILTER (WHERE u.type_force = '1'), 0) AS nff,\r\n"
							+ "    COALESCE(SUM(COALESCE(mview.total_ue_qty, 0)), 0) AS totalue\r\n"
							+ "FROM\r\n"
							+ "    mms_ue_mview mview\r\n"
							+ "INNER JOIN cue_tb_prf_group_mst prf_m ON mview.prf_group_code = prf_m.prf_group_code\r\n"
							+ "INNER JOIN CUE_TB_MMS_ITEM_MSTR nc ON nc.item_code = mview.item_code\r\n"
							+ "JOIN tb_orbat_unt_dtl u ON u.status_sus_no = 'Active' AND mview.sus_no = u.sus_no\r\n"
							+ "JOIN tb_orbat_codesform c ON c.formation_code = substr(mview.form_code_control, 1, 1)\r\n"
							+ "JOIN tb_orbat_unt_dtl a ON a.sus_no = c.sus_no AND a.status_sus_no = 'Active' AND c.level_in_hierarchy = 'Command'  \r\n"
							+SearchValue+ " GROUP BY cmd_name"
							+ " limit " +pageL+" OFFSET "+startPage+"";
				
				stmt=conn.prepareStatement(q);
				
				stmt = setQueryWhereClause_SQLWPN(stmt,search,nomenClature, cont_comd,  cont_corps, 
						 cont_div,  cont_bde, arm);
				
		      ResultSet rs = stmt.executeQuery();   
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
		
		
		
		public String GenerateQueryWhereClause_SQLWPN(String search, String nomenClature,String cont_comd, String cont_corps, 
				String cont_div, String cont_bde,String arm) {
			 
			String SearchValue ="";		 
			if(!search.equals("")) { // for Input Filter
				SearchValue =" and  ";
				
				SearchValue +="( upper(a.unit_name) like ? )";
			}

			if (!nomenClature.equals("")) {
				String temp = "";
				String[] datacmd = nomenClature.split(Pattern.quote("|"));

				for (int i = 0; i < datacmd.length; i++) {

					if (i == 0) {
						temp = temp + "?";
					} else {
						temp = temp + ",?";
					}
				}
					SearchValue += " and  nc.item_type in (" + temp +")  ";	
			}
			
			if( !cont_comd.equals("")) {
				if (SearchValue.contains("and")) {
					SearchValue += " and  substr(mview.form_code_control,1,1)  like ?  ";	
				}
				else {
					SearchValue += " and substr(mview.form_code_control,1,1)  like ? ";
				}
			}
			
			if( !cont_corps.equals("0")) {
				if (SearchValue.contains("where")) {
					SearchValue += " and  substr(mview.form_code_control,1,3) like ?  ";	
				}
				else {
					SearchValue += " and substr(mview.form_code_control,1,3) like ? ";
				}
			}
			if( !cont_div.equals("0")) {
				if (SearchValue.contains("where")) {
					SearchValue += " and  substr(mview.form_code_control,1,6) like ?  ";	
				}
				else {
					SearchValue += " and substr(mview.form_code_control,1,6) like ? ";
				}
			}
			if( !cont_bde.equals("0")) {
				if (SearchValue.contains("where")) {
					SearchValue += " and   mview.form_code_control like ?  ";	
				}
				else {
					SearchValue += " and   mview.form_code_control like ? ";
				}
			}
			
			if( !arm.equals("0")) {
				if (SearchValue.contains("where")) {
					SearchValue += " and  u.arm_code like ?  ";	
				}
				else {
					SearchValue += " where u.arm_code like ? ";
				}
			}
			
			return SearchValue;
		}	
		
		public PreparedStatement setQueryWhereClause_SQLWPN(PreparedStatement
				  stmt,String search,String nomenClature,String cont_comd, String cont_corps, 
					String cont_div, String cont_bde,String arm) {
			int flag = 0;
			int j = 1;
			try {
				if(!search.equals("")) {			
					
					flag += 1;
					stmt.setString(flag, search.toUpperCase()+"%");
				}

				if (!nomenClature.equals("")) {
					
					String[] datacmd = nomenClature.split(Pattern.quote("|"));
					for (int i = 0; i < datacmd.length; i++) {
						stmt.setString(j, datacmd[i]);
						j += 1;
						flag += 1;

					}
				}
				
				if(!cont_comd.equals("")) {
					flag += 1;
					stmt.setString(flag, cont_comd.toUpperCase()+"%");
					
				}
				if(!cont_corps.equals("0")) {
					flag += 1;
					stmt.setString(flag, cont_corps.toUpperCase()+"%");
					
				}
				if(!cont_div.equals("0")) {
					flag += 1;
					stmt.setString(flag, cont_div.toUpperCase()+"%");
					
				}
				if(!cont_bde.equals("0")) {
					flag += 1;
					stmt.setString(flag, cont_bde.toUpperCase()+"%");
					
				}
				if(!arm.equals("0")) {
					flag += 1;
					stmt.setString(flag, arm.toUpperCase()+"%");
					
				}
			}catch (Exception e) {}
			
			return stmt;
			
		}
	
		
		
		public long DatatablesReporWpncount(String Search,String orderColunm,String orderType,HttpSession sessionUserId, String nomenClature,String cont_comd, String cont_corps, 
				String cont_div, String cont_bde,String arm) {
			String SearchValue = GenerateQueryWhereClause_SQLWPN(Search, nomenClature, cont_comd,  cont_corps, 
					 cont_div,  cont_bde, arm);
			int total = 0;
			
				String q = null;
				Connection conn = null;
				try {
					conn = dataSource.getConnection();
				
						q= "select count (app.*) from(SELECT\r\n"
								+ "    a.unit_name AS cmd_name,\r\n"
								+ "    COALESCE(SUM(COALESCE(mview.total_ue_qty, 0)) FILTER (WHERE u.type_force = '0'), 0) AS ff,\r\n"
								+ "    COALESCE(SUM(COALESCE(mview.total_ue_qty, 0)) FILTER (WHERE u.type_force = '1'), 0) AS nff,\r\n"
								+ "    COALESCE(SUM(COALESCE(mview.total_ue_qty, 0)), 0) AS totalue\r\n"
								+ "FROM\r\n"
								+ "    mms_ue_mview mview\r\n"
								+ "INNER JOIN cue_tb_prf_group_mst prf_m ON mview.prf_group_code = prf_m.prf_group_code\r\n"
								+ "INNER JOIN CUE_TB_MMS_ITEM_MSTR nc ON nc.item_code = mview.item_code\r\n"
								+ "JOIN tb_orbat_unt_dtl u ON u.status_sus_no = 'Active' AND mview.sus_no = u.sus_no\r\n"
								+ "JOIN tb_orbat_codesform c ON c.formation_code = substr(mview.form_code_control, 1, 1)\r\n"
								+ "JOIN tb_orbat_unt_dtl a ON a.sus_no = c.sus_no AND a.status_sus_no = 'Active' AND c.level_in_hierarchy = 'Command'  \r\n"
								+SearchValue+ " GROUP BY cmd_name) app ";
					
					PreparedStatement stmt = conn.prepareStatement(q);
					stmt = setQueryWhereClause_SQLWPN(stmt,Search,nomenClature, cont_comd,  cont_corps, 
							 cont_div,  cont_bde, arm);
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
		
		
		public List<Map<String, Object>> DatatablesReporPers(int startPage, int pageLength, String search,
				String orderColunm, String orderType, HttpSession sessionUserId,String  appt_trade,String rank, String rank_cat,String cont_comd, String cont_corps, String cont_div, String cont_bde,
				String radio1) 
		{
		 String SearchValue = GenerateQueryWhereClause_SQLPERS(search,  appt_trade, rank, rank_cat,cont_comd, cont_corps, cont_div,cont_bde, radio1);
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
			
					q=    "   SELECT \r\n"
							+ "    COALESCE(SUM(CASE WHEN ue.rank_cat = '0' THEN ue.base_auth + ue.mod_auth + ue.foot_auth ELSE 0 END), 0) AS officer,\r\n"
							+ "    COALESCE(SUM(CASE WHEN ue.rank_cat = '1' THEN ue.base_auth + ue.mod_auth + ue.foot_auth ELSE 0 END), 0) AS jco,\r\n"
							+ "    COALESCE(SUM(CASE WHEN ue.rank_cat IN ('2', '3') THEN ue.base_auth + ue.mod_auth + ue.foot_auth ELSE 0 END), 0) AS \"or\",\r\n"
							+ "    COALESCE(SUM(CASE WHEN ue.rank_cat IN ('4','5','6','7','8','9','10','11') THEN ue.base_auth + ue.mod_auth + ue.foot_auth ELSE 0 END), 0) AS civ,\r\n"
							+ "    SUM(ue.base_auth + ue.mod_auth + ue.foot_auth) AS total,\r\n"
							+ "    cmd.cmd_name\r\n"
							+ "FROM sus_pers_stdauth ue\r\n"
							+ "INNER JOIN tb_orbat_unt_dtl u ON ue.sus_no = u.sus_no AND u.status_sus_no = 'Active'\r\n"
//							+ "INNER JOIN cue_tb_wepe_pers_det cu ON cu.arm_code = u.arm_code\r\n"
							+ "INNER JOIN orbat_view_cmd cmd ON substr(u.form_code_control,1,1) = cmd.cmd_code \r\n"
							+ SearchValue+ " GROUP BY  cmd.cmd_name \r\n"
									+ "HAVING SUM(ue.base_auth + ue.mod_auth + ue.foot_auth) <> 0"
								+ " limit " +pageL+" OFFSET "+startPage+"";
				
				stmt=conn.prepareStatement(q);
				
				stmt = setQueryWhereClause_SQLPERS(stmt,search, appt_trade, rank, rank_cat,cont_comd, cont_corps, cont_div,cont_bde, radio1);
		  
				ResultSet rs = stmt.executeQuery();   
			
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
		
		
		public String GenerateQueryWhereClause_SQLPERS(String search, String  appt_trade,String rank, String rank_cat,String cont_comd, String cont_corps, String cont_div, String cont_bde,
				String radio1) {
			 
			String SearchValue ="";		 
			if(!search.equals("")) { // for Input Filter
				SearchValue =" and  ";
				
				SearchValue +="(  upper(cmd_name) like ?)";
			}
			if( !appt_trade.equals("")) {
				if (SearchValue.contains("and")) {
					SearchValue += " and  ue.app_trd_code in (select code from CUE_TB_PSG_RANK_APP_MASTER where description = ? and upper(level_in_hierarchy) =  'APPOINTMENT' )  ";	
				}
				else {
					SearchValue += " and  ue.app_trd_code in (select code from CUE_TB_PSG_RANK_APP_MASTER where description = ? and upper(level_in_hierarchy) =  'APPOINTMENT' ) ";
				}
			}
			
			if( !rank.equals("")) {
				if (SearchValue.contains("and")) {
					SearchValue += " and  ue.rank_code like ?  ";	
				}
				else {
					SearchValue += " and ue.rank_code like ? ";
				}
			}
			
			if( !rank_cat.equals("")) {
				if (SearchValue.contains("and")) {
					SearchValue += " and  ue.rank_cat = ?  ";	
				}
				else {
					SearchValue += " and ue.rank_cat = ? ";
				}
			}
			
			if( !cont_comd.equals("")) {
				if (SearchValue.contains("and")) {
					SearchValue += " and  substr(u.form_code_control,1,1)  like ?  ";	
				}
				else {
					SearchValue += " and substr(u.form_code_control,1,1)  like ? ";
				}
			}
			
			if( !cont_corps.equals("0")) {
				if (SearchValue.contains("where")) {
					SearchValue += " and  substr(u.form_code_control,1,3) like ?  ";	
				}
				else {
					SearchValue += " and substr(u.form_code_control,1,3) like ? ";
				}
			}
			if( !cont_div.equals("0")) {
				if (SearchValue.contains("where")) {
					SearchValue += " and  substr(u.form_code_control,1,6) like ?  ";	
				}
				else {
					SearchValue += " and substr(u.form_code_control,1,6) like ? ";
				}
			}
			if( !cont_bde.equals("0")) {
				if (SearchValue.contains("where")) {
					SearchValue += " and  u.form_code_control like ?  ";	
				}
				else {
					SearchValue += " and  u.form_code_control like ? ";
				}
			}
			if (!radio1.equals("")) {
			      if (SearchValue.contains("where")) {
			              SearchValue += " and upper(u.ct_part_i_ii) like ? ";
			      } else {
			              SearchValue += " and upper(u.ct_part_i_ii) like ? ";
			      }
			}
			return SearchValue;
		}	
		
			
			
		
		public PreparedStatement setQueryWhereClause_SQLPERS(PreparedStatement
				  stmt,String search,String  appt_trade,String rank, String rank_cat, String cont_comd, String cont_corps, String cont_div, String cont_bde,String radio1) {
			int flag = 0;
			try {
				if(!search.equals("")) {			
					
					flag += 1;
					stmt.setString(flag, search.toUpperCase()+"%");
				}

				if(!appt_trade.equals("")) {
					flag += 1;
					stmt.setString(flag, appt_trade.toUpperCase()+"%");
					
				}
				
				if(!rank.equals("")) {
					flag += 1;
					stmt.setString(flag, rank.toUpperCase()+"%");
					
				}
				if(!rank_cat.equals("")) {
					flag += 1;
					stmt.setString(flag, rank_cat.toUpperCase());
					
				}
				
				if(!cont_comd.equals("")) {
					flag += 1;
					stmt.setString(flag, cont_comd.toUpperCase()+"%");
					
				}
				if(!cont_corps.equals("0")) {
					flag += 1;
					stmt.setString(flag, cont_corps.toUpperCase()+"%");
					
				}
				if(!cont_div.equals("0")) {
					flag += 1;
					stmt.setString(flag, cont_div.toUpperCase()+"%");
					
				}
				if(!cont_bde.equals("0")) {
					flag += 1;
					stmt.setString(flag, cont_bde.toUpperCase()+"%");
					
				}
				if (!radio1.equals("")) {
					flag += 1;
					stmt.setString(flag, radio1.toUpperCase());
					
				}
			}catch (Exception e) {}
			
			return stmt;
			
		}
		
		
		
		
		public long DatatablesReporPerscount(String Search,String orderColunm,String orderType,HttpSession sessionUserId,String  appt_trade, String rank, String rank_cat,String cont_comd, String cont_corps, String cont_div, String cont_bde,
				String radio1) {
			String SearchValue = GenerateQueryWhereClause_SQLPERS(Search,  appt_trade, rank, rank_cat, cont_comd, cont_corps,  cont_div,  cont_bde, radio1);

			int total = 0;
				String q = null;
				Connection conn = null;
				try {
					conn = dataSource.getConnection();
				
						q= "select count (app.*) from(SELECT \r\n"
								+ "    COALESCE(SUM(CASE WHEN ue.rank_cat = '0' THEN ue.base_auth + ue.mod_auth + ue.foot_auth ELSE 0 END), 0) AS officer,\r\n"
								+ "    COALESCE(SUM(CASE WHEN ue.rank_cat = '1' THEN ue.base_auth + ue.mod_auth + ue.foot_auth ELSE 0 END), 0) AS jco,\r\n"
								+ "    COALESCE(SUM(CASE WHEN ue.rank_cat IN ('2', '3') THEN ue.base_auth + ue.mod_auth + ue.foot_auth ELSE 0 END), 0) AS \"or\",\r\n"
								+ "    COALESCE(SUM(CASE WHEN ue.rank_cat IN ('4','5','6','7','8','9','10','11') THEN ue.base_auth + ue.mod_auth + ue.foot_auth ELSE 0 END), 0) AS civ,\r\n"
								+ "    SUM(ue.base_auth + ue.mod_auth + ue.foot_auth) AS total,\r\n"
								+ "    cmd.cmd_name\r\n"
								+ "FROM sus_pers_stdauth ue\r\n"
								+ "INNER JOIN tb_orbat_unt_dtl u ON ue.sus_no = u.sus_no AND u.status_sus_no = 'Active'\r\n"
//								+ "INNER JOIN cue_tb_wepe_pers_det cu ON cu.arm_code = u.arm_code\r\n"
								+ "INNER JOIN orbat_view_cmd cmd ON substr(u.form_code_control,1,1) = cmd.cmd_code \r\n"
								+ SearchValue+ " GROUP BY  cmd.cmd_name \r\n"
										+ "HAVING SUM(ue.base_auth + ue.mod_auth + ue.foot_auth) <> 0) app ";
					
					PreparedStatement stmt = conn.prepareStatement(q);
					stmt = setQueryWhereClause_SQLPERS(stmt,Search, appt_trade, rank, rank_cat,cont_comd, cont_corps, cont_div,cont_bde, radio1);
				
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
		
		public List<Map<String, Object>> getDocTypeDetaisWEPElist(int startPage, int pageLength, String search,
				String orderColunm, String orderType, HttpSession sessionUserId, String cr_by, String crDate) 
		{
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();		
		Connection conn = null;
		String q="";
		String SearchValue="";

		try{	
			String pageL = "";
	        
	        if(pageLength == -1){
				pageL = "ALL";
			}else {
				pageL = String.valueOf(pageLength);
			}
				conn = dataSource.getConnection();			 
				PreparedStatement stmt=null;
				
				if(!search.equals("")) { // for Input Filter
					SearchValue =" and  ";
					
					SearchValue +="(  upper(c.we_pe_no) like ? or upper(c.created_by) like ? or upper(c.created_on) like ?"
							+ "or upper(c.modified_by) like ? or upper(c.modified_on) like ?)";
				}
				if( !cr_by.equals("")) {
					if (SearchValue.contains("and")) {
						SearchValue += " and   cast(o.userid as character varying) = ?  ";	
					}
					else {
						SearchValue += " and cast(o.userid as character varying) = ? ";
					}
				}				
				if(!crDate.equals("") && !crDate.equals("DD/MM/YYYY")){ 
					if (SearchValue.contains("where")) {
						  SearchValue +=  " and cast(c.created_on as date) >= cast(? as date)"; 
					} 
					else { 
						  SearchValue += " and cast(c.created_on as date) >= cast(? as date)"; 
					}
				}
					q= "select distinct c.id,c.we_pe,c.we_pe_no,o.login_name as cr_by,c.created_by,c.created_on,p.login_name as md_by,c.modified_by,c.modified_on\r\n"
							+ " from cue_tb_wepe_upload_condition c\r\n"
							+ " inner join logininformation o on c.created_by = o.username\r\n"
							+ " left join logininformation p on c.modified_by=p.username\r\n"
							+ " where c.status='1' " + SearchValue+" order by c.we_pe,c.we_pe_no";
				
				stmt=conn.prepareStatement(q);
				int flag = 0;
				if(!search.equals("")) {			
					
					flag += 1;
					stmt.setString(flag, search.toUpperCase()+"%");
					flag += 1;
					stmt.setString(flag, search.toUpperCase()+"%");
					flag += 1;
					stmt.setString(flag, search.toUpperCase()+"%");
					flag += 1;
					stmt.setString(flag, search.toUpperCase()+"%");
					flag += 1;
					stmt.setString(flag, search.toUpperCase()+"%");
				}
				if(!cr_by.equals("")) {
					flag += 1;
					stmt.setString(flag, cr_by.toUpperCase());
					
				}
				if(!crDate.equals("")) {
					flag += 1;
					stmt.setString(flag, crDate);
					
				}
		      ResultSet rs = stmt.executeQuery();   
		   
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
		
	
		
		
		
	public long getDocTypeDetaisWEPEcount(String Search,String orderColunm,String orderType,HttpSession sessionUserId, String cr_by, String crDate) {
			int total = 0;
				
				String q = null;
			String	SearchValue="";
				Connection conn = null;
				try {
					conn = dataSource.getConnection();
					if(!Search.equals("")) { // for Input Filter
						SearchValue =" and  ";
						
						SearchValue +="(  upper(c.we_pe_no) like ? or upper(c.created_by) like ? or upper(c.created_on) like ?"
								+ "or upper(c.modified_by) like ? or upper(c.modified_on) like ?)";
					}
					if( !cr_by.equals("")) {
						if (SearchValue.contains("and")) {
							SearchValue += " and    cast(o.userid as character varying) = ?  ";	
						}
						else {
							SearchValue += " and cast(o.userid as character varying) = ? ";
						}
					}
					
					if(!crDate.equals("") && !crDate.equals("DD/MM/YYYY")){ 
						if (SearchValue.contains("where")) {
							  SearchValue +=  " and cast(c.created_on as date) >= cast(? as date)"; 
						} 
						else { 
							  SearchValue += " and cast(c.created_on as date) >= cast(? as date)"; 
						}
					}
						q= "select count (app.*) from(select distinct c.id,c.we_pe,c.we_pe_no,o.login_name as cr_by,c.created_by,c.created_on,p.login_name as md_by,c.modified_by,c.modified_on\r\n"
								+ " from cue_tb_wepe_upload_condition c\r\n"
								+ " inner join logininformation o on c.created_by = o.username\r\n"
								+ " left join logininformation p on c.modified_by=p.username\r\n"
								+ " where c.status='1' " + SearchValue+" order by c.we_pe,c.we_pe_no) app ";
					
					PreparedStatement stmt = conn.prepareStatement(q);
					int flag = 0;
					if(!Search.equals("")) {			
						
						flag += 1;
						stmt.setString(flag, Search.toUpperCase()+"%");
						flag += 1;
						stmt.setString(flag, Search.toUpperCase()+"%");
						flag += 1;
						stmt.setString(flag, Search.toUpperCase()+"%");
						flag += 1;
						stmt.setString(flag, Search.toUpperCase()+"%");
						flag += 1;
						stmt.setString(flag, Search.toUpperCase()+"%");
					}
					if(!cr_by.equals("")) {
						flag += 1;
						stmt.setString(flag, cr_by.toUpperCase());
						
					}
					
					if(!crDate.equals("")) {
						flag += 1;
						stmt.setString(flag, crDate);
						
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
		
	public List<Map<String, Object>> getDocTypeDetaisWETPETlist(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId, String cr_by, String crDate) 
	{
	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();		
	Connection conn = null;
	String q="";
	String SearchValue ="";		 

	try{	
		String pageL = "";
        
        if(pageLength == -1){
			pageL = "ALL";
		}else {
			pageL = String.valueOf(pageLength);
		}
			conn = dataSource.getConnection();			 
			PreparedStatement stmt=null;
			if(!search.equals("")) { // for Input Filter
				SearchValue =" and  ";
				
				SearchValue +="(  upper(c.wet_pet_no) like ? or upper(c.created_by) like ? or upper(c.created_on) like ?"
						+ "or upper(c.modified_by) like ? or upper(c.modified_on) like ?)";
			}
			if( !cr_by.equals("")) {
				if (SearchValue.contains("and")) {
					SearchValue += " and  cast(o.userid as character varying) = ?  ";	
				}
				else {
					SearchValue += " and cast(o.userid as character varying) = ? ";
				}
			}
			
			if(!crDate.equals("") && !crDate.equals("DD/MM/YYYY")){ 
				if (SearchValue.contains("where")) {
					  SearchValue +=  " and cast(c.created_on as date) >= cast(? as date)"; 
				} 
				else { 
					  SearchValue += " and cast(c.created_on as date) >= cast(? as date)"; 
				}
			}
		
				q= "select distinct c.id,c.wet_pet,c.wet_pet_no,o.login_name as cr_by,c.created_by,c.created_on,p.login_name as md_by,c.modified_by,c.modified_on,c.status\r\n"
						+ "from cue_tb_mms_wet_mast_upload c\r\n"
						+ "inner join logininformation o on c.created_by = o.username\r\n"
						+ "left join logininformation p on c.modified_by=p.username\r\n"
						+ "where c.status='1' " + SearchValue+" order by c.wet_pet,c.wet_pet_no\r\n";
			
			stmt=conn.prepareStatement(q);
			int flag = 0;
			if(!search.equals("")) {			
				
				flag += 1;
				stmt.setString(flag, search.toUpperCase()+"%");
				flag += 1;
				stmt.setString(flag, search.toUpperCase()+"%");
				flag += 1;
				stmt.setString(flag, search.toUpperCase()+"%");
				flag += 1;
				stmt.setString(flag, search.toUpperCase()+"%");
				flag += 1;
				stmt.setString(flag, search.toUpperCase()+"%");
			}
			if(!cr_by.equals("")) {
				flag += 1;
				stmt.setString(flag, cr_by.toUpperCase());
				
			}
			
			if(!crDate.equals("")) {
				flag += 1;
				stmt.setString(flag, crDate);
				
			}
	      ResultSet rs = stmt.executeQuery();   
	    
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
	

	
	
	
	public long getDocTypeDetaisWETPETcount(String Search,String orderColunm,String orderType,HttpSession sessionUserId, String cr_by, String crDate) {
			int total = 0;
				
				String q = null;
				String SearchValue ="";
				Connection conn = null;
				try {
					conn = dataSource.getConnection();
					if(!Search.equals("")) { // for Input Filter
						SearchValue =" and  ";
						
						SearchValue +="(  upper(c.wet_pet_no) like ? or upper(c.created_by) like ? or upper(c.created_on) like ?"
								+ "or upper(c.modified_by) like ? or upper(c.modified_on) like ?)";
					}
					if( !cr_by.equals("")) {
						if (SearchValue.contains("and")) {
							SearchValue += " and cast(o.userid as character varying) = ?  ";	
						}
						else {
							SearchValue += " and cast(o.userid as character varying) = ? ";
						}
					}
					
					if(!crDate.equals("") && !crDate.equals("DD/MM/YYYY")){ 
						if (SearchValue.contains("where")) {
							  SearchValue +=  " and cast(c.created_on as date) >= cast(? as date)"; 
						} 
						else { 
							  SearchValue += " and cast(c.created_on as date) >= cast(? as date)"; 
						}
					}
						q= "select count (app.*) from(select distinct c.id,c.wet_pet,c.wet_pet_no,o.login_name as cr_by,c.created_by,c.created_on,p.login_name as md_by,c.modified_by,c.modified_on,c.status\r\n"
								+ "from cue_tb_mms_wet_mast_upload c\r\n"
								+ "inner join logininformation o on c.created_by = o.username\r\n"
								+ "left join logininformation p on c.modified_by=p.username\r\n"
								+ "where c.status='1' " + SearchValue+" order by c.wet_pet,c.wet_pet_no) app ";
					
					PreparedStatement stmt = conn.prepareStatement(q);
					int flag = 0;
						if(!Search.equals("")) {			
						
						flag += 1;
						stmt.setString(flag, Search.toUpperCase()+"%");
						flag += 1;
						stmt.setString(flag, Search.toUpperCase()+"%");
						flag += 1;
						stmt.setString(flag, Search.toUpperCase()+"%");
						flag += 1;
						stmt.setString(flag, Search.toUpperCase()+"%");
						flag += 1;
						stmt.setString(flag, Search.toUpperCase()+"%");
					}
					if(!cr_by.equals("")) {
						flag += 1;
						stmt.setString(flag, cr_by.toUpperCase());
						
					}
					
					if(!crDate.equals("")) {
						flag += 1;
						stmt.setString(flag, crDate);
						
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
	
	public List<Map<String, Object>> unit_wise_dtls_table(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId,  String fromDate, String toDate, String cmd, String Ydata, String cont_comd, String cont_corps, String cont_div, String cont_bde, String arm, String unit_sus_no, String unit_name)
  	{		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
  		Connection conn = null;
  		String SearchValue ="";
  		try{	  
  			
  			if(!search.equals("")) { // for Input Filter
				SearchValue =" and  ";
				
				SearchValue +="(  upper(a.unit_name) like ? or upper(a.sus_no) like ? or upper(c.cmd_name) like ?)";
			}
  			if(cmd.equals("SC")){ 
  	  		  SearchValue +=  "  c.cmd_code = '1' "; 
  	  		}
  			if(cmd.equals("EC")){ 
    	  		  SearchValue +=  "  c.cmd_code = '2' "; 
    	  		}
  			if(cmd.equals("WC")){ 
    	  		  SearchValue +=  "  c.cmd_code = '3' "; 
    	  		}
  			if(cmd.equals("CC")){ 
    	  		  SearchValue +=  "  c.cmd_code = '4' "; 
    	  		}
  			if(cmd.equals("NC")){ 
    	  		  SearchValue +=  "  c.cmd_code = '5' "; 
    	  		}
  			if(cmd.equals("ARTRAC")){ 
    	  		  SearchValue +=  "  c.cmd_code = '6' "; 
    	  		}
  			if(cmd.equals("ANC")){ 
    	  		  SearchValue +=  "  c.cmd_code = '7' "; 
    	  		}
  			if(cmd.equals("SWC")){ 
    	  		  SearchValue +=  "  c.cmd_code = '8' "; 
    	  		}
  			if(cmd.equals("UN")){ 
  	  		  	  SearchValue +=  "  c.cmd_code = '9' "; 
  	  			}
  			if(cmd.equals("MOD")){ 
  				SearchValue +=  "  c.cmd_code = '0' "; 
  	  			}
  			if(cmd.equals("SFC")){ 
  	  		  	SearchValue +=  "  c.cmd_code = 'A' "; 
  	  			}
  			if(Ydata.equals("nrus")){ 
    	  		  SearchValue +=  " and s.type_of_letter   in ('0') "; 
    	  		}
  			if(Ydata.equals("unit_move_details")){ 
    	  		  SearchValue +=  " and s.type_of_letter   in ('8','9')"; 
    	  		}
  			if(Ydata.equals("converted")){ 
    	  		  SearchValue +=  " and s.type_of_letter   in ('1','2','11') "; 
    	  		}
  			if(Ydata.equals("disbanded")){ 
    	  		  SearchValue +=  " and s.type_of_letter   in ('4') "; 
    	  		}
  			if(!fromDate.equals("") && !fromDate.equals("DD/MM/YYYY")){ 
  				SearchValue +=  " and cast(s.approved_rejected_on as date) >= cast(? as date)"; 
  				}
  		
  			if(!toDate.equals("") && !toDate.equals("DD/MM/YYYY")){ 
  				SearchValue += " and cast(s.approved_rejected_on as date) <= cast(? as date)"; 
  				}
  			if(!cont_corps.equals("0")){ 
  	  		  SearchValue +=  "and substring(a.form_code_control,1,3) = ?"; 
  	  		}
  			if(!cont_div.equals("0")){ 
	  	  		  SearchValue +=  "and substring(a.form_code_control,1,6) = ?"; 
	  	  			}
	  		if(!cont_bde.equals("0")){ 
	  	  		  SearchValue +=  "and a.form_code_control = ?"; 
	  	  			}
	  		if(!arm.equals("0")){ 
	  	  		  SearchValue +=  "and a.arm_code = ?"; 
	  	  			}
	  		if(!unit_sus_no.equals("")){ 
	  	  		  SearchValue +=  "and a.sus_no = ?"; 
	  	  			}
  			conn = dataSource.getConnection();		
  			String qry="select a.unit_name,a.sus_no,c.cmd_name as cmd_name,\r\n"
  					+ "	  CASE \r\n"
  					+ "        WHEN s.type_of_letter = '0' THEN 'NRUs'\r\n"
  					+ "        WHEN s.type_of_letter = '4' THEN 'Disbanded'\r\n"
  					+ "        WHEN s.type_of_letter IN ('8', '9') THEN 'Unit Move Details'\r\n"
  					+ "        WHEN s.type_of_letter IN ('2', '11', '1') THEN 'Converted'\r\n"
  					+ "        ELSE 'Unknown' -- Handle any other values not covered\r\n"
  					+ "    END AS scenario\r\n"
  					+ "	from tb_orbat_unt_dtl a\r\n"
  					+ "inner join tb_orbat_shdul_detl s on a.id = s.letter_id \r\n"
  					+ "Right join orbat_view_cmd c on substr(a.form_code_control,1,1)= c.cmd_code where "+SearchValue+"" ;  		
  			
  			PreparedStatement stmt=conn.prepareStatement(qry);
  			
  			int flag = 0;
  			
  			if(!search.equals("")) {			
				
				flag += 1;
				stmt.setString(flag, search.toUpperCase()+"%");
				flag += 1;
				stmt.setString(flag, search.toUpperCase()+"%");
				flag += 1;
				stmt.setString(flag, search.toUpperCase()+"%");

			}
  			if(!fromDate.equals("")) {
				flag += 1;
				stmt.setString(flag, fromDate);
				
			}
			if(!toDate.equals("")) {
				flag += 1;
				stmt.setString(flag, toDate);
				
			}
			if(!cont_corps.equals("0")){ 
				flag += 1;
				stmt.setString(flag, cont_corps);
	  	  	}
			if(!cont_div.equals("0")){ 
				flag += 1;
				stmt.setString(flag, cont_div);
	  	  	}
			if(!cont_bde.equals("0")){ 
				flag += 1;
				stmt.setString(flag, cont_bde);
	  	  	}
			if(!arm.equals("0")){ 
				flag += 1;
				stmt.setString(flag, arm); 
	  	  			}
			if(!unit_sus_no.equals("")){ 
				flag += 1;
				stmt.setString(flag, unit_sus_no); 
	  	  			}
//  			stmt.setInt(1, year);
  			ResultSet rs = stmt.executeQuery();      
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


	public long unit_wise_dtls_tablecount(String search, String orderColunm, String orderType,
			HttpSession sessionUserId, String fromDate, String toDate, String cmd, String Ydata, String cont_comd, String cont_corps, String cont_div, String cont_bde, String arm, String unit_sus_no, String unit_name) {
	 String SearchValue ="";
		int total = 0;
			String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
			String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
			String q = null;
			
			Connection conn = null;
			try {
				conn = dataSource.getConnection();
				if(!search.equals("")) { // for Input Filter
					SearchValue =" and  ";
					
					SearchValue +="(  upper(a.unit_name) like ? or upper(a.sus_no) like ? or upper(c.cmd_name) like ?)";
				}
				
				if(cmd.equals("SC")){ 
		  	  		  SearchValue +=  "  c.cmd_code = '1' "; 
		  	  		}
		  			if(cmd.equals("EC")){ 
		    	  		  SearchValue +=  "  c.cmd_code = '2' "; 
		    	  		}
		  			if(cmd.equals("WC")){ 
		    	  		  SearchValue +=  "  c.cmd_code = '3' "; 
		    	  		}
		  			if(cmd.equals("CC")){ 
		    	  		  SearchValue +=  "  c.cmd_code = '4' "; 
		    	  		}
		  			if(cmd.equals("NC")){ 
		    	  		  SearchValue +=  "  c.cmd_code = '5' "; 
		    	  		}
		  			if(cmd.equals("ARTRAC")){ 
		    	  		  SearchValue +=  "  c.cmd_code = '6' "; 
		    	  		}
		  			if(cmd.equals("ANC")){ 
		    	  		  SearchValue +=  "  c.cmd_code = '7' "; 
		    	  		}
		  			if(cmd.equals("SWC")){ 
		    	  		  SearchValue +=  "  c.cmd_code = '8' "; 
		    	  		}
		  			if(cmd.equals("UN")){ 
		  				SearchValue +=  "  c.cmd_code = '9' "; 
		  	  			}
		  			if(cmd.equals("MOD")){ 
		  				SearchValue +=  "  c.cmd_code = '0' "; 
		  	  			}
		  			if(cmd.equals("SFC")){ 
		  				SearchValue +=  "  c.cmd_code = 'A' "; 
		  	  			}
		  			if(Ydata.equals("nrus")){ 
		    	  		  SearchValue +=  " and s.type_of_letter   in ('0') "; 
		    	  		}
		  			if(Ydata.equals("unit_move_details")){ 
		    	  		  SearchValue +=  " and s.type_of_letter   in ('8','9')"; 
		    	  		}
		  			if(Ydata.equals("converted")){ 
		    	  		  SearchValue +=  " and s.type_of_letter   in ('1','2','11') "; 
		    	  		}
		  			if(Ydata.equals("disbanded")){ 
		    	  		  SearchValue +=  " and s.type_of_letter   in ('4') "; 
		    	  		}
		  			
		  			if(!fromDate.equals("") && !fromDate.equals("DD/MM/YYYY")){ 
		  				SearchValue +=  " and cast(s.approved_rejected_on as date) >= cast(? as date)"; 
		  				}
		  		
		  		if(!toDate.equals("") && !toDate.equals("DD/MM/YYYY")){ 
		  			 SearchValue += " and cast(s.approved_rejected_on as date) <= cast(? as date)"; 
		  				}
		  		if(!cont_corps.equals("0")){ 
		  	  		  SearchValue +=  "and substring(a.form_code_control,1,3) = ?"; 
		  	  			}
		  		if(!cont_div.equals("0")){ 
		  	  		  SearchValue +=  "and substring(a.form_code_control,1,6) = ?"; 
		  	  			}
		  		if(!cont_bde.equals("0")){ 
		  	  		  SearchValue +=  "and a.form_code_control = ?"; 
		  	  			}
		  		if(!arm.equals("0")){ 
		  	  		  SearchValue +=  "and a.arm_code = ?"; 
		  	  			}
		  		if(!unit_sus_no.equals("")){ 
		  	  		  SearchValue +=  "and a.sus_no = ?"; 
		  	  			}
			  q="select count(app.*) from(select a.unit_name,a.sus_no,c.cmd_name as cmd_name,\r\n"
			  		+ "  					CASE \r\n"
			  		+ "  					WHEN s.type_of_letter = '0' THEN 'NRUs'\r\n"
			  		+ "  				    WHEN s.type_of_letter = '4' THEN 'Disbanded'\r\n"
			  		+ "  				    WHEN s.type_of_letter IN ('8', '9') THEN 'Unit Move Details'\r\n"
			  		+ "  					WHEN s.type_of_letter IN ('2', '11', '1') THEN 'Converted'\r\n"
			  		+ "  				    ELSE 'Unknown' \r\n"
			  		+ "  				 	END AS scenario\r\n"
			  		+ "  					from tb_orbat_unt_dtl a\r\n"
			  		+ "  					inner join tb_orbat_shdul_detl s on a.id = s.letter_id \r\n"
			  		+ "  					Right join orbat_view_cmd c on substr(a.form_code_control,1,1)= c.cmd_code where  "
			  		+ SearchValue +") app";
				PreparedStatement stmt = conn.prepareStatement(q);
				int flag = 0;
				
				if(!search.equals("")) {			
					
					flag += 1;
					stmt.setString(flag, search.toUpperCase()+"%");
					flag += 1;
					stmt.setString(flag, search.toUpperCase()+"%");
					flag += 1;
					stmt.setString(flag, search.toUpperCase()+"%");
//					flag += 1;
//					stmt.setString(flag, search.toUpperCase()+"%");
//					flag += 1;
//					stmt.setString(flag, search.toUpperCase()+"%");
				}
				
	  			if(!fromDate.equals("")) {
					flag += 1;
					stmt.setString(flag, fromDate);
					
				}
				if(!toDate.equals("")) {
					flag += 1;
					stmt.setString(flag, toDate);
					
				}
				if(!cont_corps.equals("0")){ 
					flag += 1;
					stmt.setString(flag, cont_corps);
		  	  	}
				if(!cont_div.equals("0")){ 
					flag += 1;
					stmt.setString(flag, cont_div);
		  	  	}
				if(!cont_bde.equals("0")){ 
					flag += 1;
					stmt.setString(flag, cont_bde);
		  	  	}
				if(!arm.equals("0")){ 
					flag += 1;
					stmt.setString(flag, arm); 
		  	  			}
				if(!unit_sus_no.equals("")){ 
					flag += 1;
					stmt.setString(flag, unit_sus_no); 
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
	

	public List<Map<String, Object>> getCueDocTypeWiseModule(Date fromDate, Date toDate) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			String qry = "SELECT DISTINCT tbl.we_pe,COALESCE(tbl.noofwes, 0::int) AS noofwes,tblp.we_pe AS we_pe_pers,COALESCE(tblp.noofwes, 0::int) AS noofwes_pers,tblt.we_pe AS we_pe_trans,COALESCE(tblt.noofwes, 0::int) AS noofwes_trans,tblw.we_pe AS we_pe_weap,COALESCE(tblw.noofwes, 0::int) AS noofwes_weap FROM \r\n"
					+ "(SELECT DISTINCT\r\n" + "we_pe,count(we_pe) as noofwes\r\n"
					+ "FROM cue_tb_wepe_upload_condition\r\n"
					+ "WHERE we_pe_no not in (SELECT DISTINCT suprcdd_we_pe_no FROM cue_tb_wepe_upload_condition WHERE suprcdd_we_pe_no is not null) and we_pe_no is not null\r\n"
					+ "and	cast(created_on as Date) between cast(? as Date) and cast(? as date) \r\n"
					+ "GROUP BY 1 ORDER BY 1) tbl\r\n" + "LEFT JOIN \r\n" + "(SELECT DISTINCT \r\n"
					+ "we_pe,count(we_pe) as noofwes FROM cue_tb_wepeconditions\r\n"
					+ "WHERE we_pe_no not in (SELECT DISTINCT suprcdd_we_pe_no FROM cue_tb_wepeconditions WHERE suprcdd_we_pe_no is not null) and we_pe_no is not null and type='1'\r\n"
					+ "and	cast(created_on as Date) between cast(? as Date) and cast(? as date) \r\n"
					+ "GROUP BY 1 ORDER BY 1) tblp ON tbl.we_pe=tblp.we_pe\r\n" + "LEFT JOIN \r\n" + "(SELECT DISTINCT \r\n"
					+ "we_pe,count(we_pe) as noofwes FROM cue_tb_wepeconditions\r\n"
					+ "WHERE we_pe_no not in (SELECT DISTINCT suprcdd_we_pe_no FROM cue_tb_wepeconditions WHERE suprcdd_we_pe_no is not null) and we_pe_no is not null and type='2'\r\n"
					+ "and	cast(created_on as Date) between cast(? as Date) and cast(? as date) \r\n"
					+ "GROUP BY 1 ORDER BY 1) tblt ON tbl.we_pe=tblt.we_pe\r\n" + "LEFT JOIN \r\n" + "(SELECT DISTINCT \r\n"
					+ "we_pe,count(we_pe) as noofwes FROM cue_tb_wepeconditions\r\n"
					+ "WHERE we_pe_no not in (SELECT DISTINCT suprcdd_we_pe_no FROM cue_tb_wepeconditions WHERE suprcdd_we_pe_no is not null) and we_pe_no is not null and type='3'\r\n"
					+ "and	cast(created_on as Date) between cast(? as Date) and cast(? as date) \r\n"
					+ "GROUP BY 1 ORDER BY 1) tblw ON tbl.we_pe=tblw.we_pe\r\n" + "ORDER BY 1 DESC";

			PreparedStatement stmt = conn.prepareStatement(qry);

			stmt.setDate(1, fromDate);
			stmt.setDate(2, toDate);
			
			stmt.setDate(3, fromDate);
			stmt.setDate(4, toDate);
			
			stmt.setDate(5, fromDate);
			stmt.setDate(6, toDate);
			
			stmt.setDate(7, fromDate);
			stmt.setDate(8, toDate);

			ResultSet rs = stmt.executeQuery();
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
		
	
	 
		
		private Long getTotalCountCommonReportWETPETCond(String countQry) {
			Session session= HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = null;
			q = session.createQuery(countQry);
			Long count = (Long) q.list().get(0);
			tx.commit();
			session.close();
			return count;
		}
		
		public List<Map<String, Object>> Rank_cat_wise_dtl_table(int startPage, int pageLength, String search,
				String orderColunm, String orderType, HttpSession sessionUserId,  String cat_id,String cont_comd, String cont_corps,String cont_div,String cont_bde,String arm,String unit_sus_no,String unit_name,String rank)
	  	{		
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	  		Connection conn = null;
	  		String SearchValue ="";
	  		try{	  
	  			if(!search.equals("")) { // for Input Filter
					SearchValue =" and  ";
					
					SearchValue +="(  upper(a.sus_no) like ? or upper(utl.unit_name) like ? or upper(ab.cmd_name) like ?"
							+ "or upper(ac.coprs_name) like ? or upper(ad.div_name) like ? or upper(ae.bde_name) like ?or upper(d.arm_desc) like ?"
							+ " or upper(t.label) like ?)";
				}
	  			if(cat_id.equals("Civilian") ){ 
	  			 
	  		  SearchValue +=  " and a.rank_cat in ('5','4','6','7','8', '9','10','11')"; 
	  		  
	  		}
	  			if(cat_id.equals("JCO/OR") ){ 
		  			 
	  	  		  SearchValue +=  " and a.rank_cat in ('2')"; 
	  	  		  
	  	  		}
	  			if(cat_id.equals("OR") ){ 
		  			 
	  	  		  SearchValue +=  " and a.rank_cat in ('3')"; 
	  	  		  
	  	  		}
	  			if(cat_id.equals("OFFICER") ){ 
		  			 
		  	  		  SearchValue +=  " and a.rank_cat in ('0')"; 
		  	  		  
		  	  		}
	  			if(cat_id.equals("JCO") ){ 
		  			 
		  	  		  SearchValue +=  " and a.rank_cat in ('1')"; 
		  	  		}
	  			if(cat_id.equals("NCsUE") ){ 
		  			 
		  	  		  SearchValue +=  " and a.rank_cat in ('12')"; 
		  	  		}
	  			if(!cont_comd.equals("")){ 
		  	  		  SearchValue +=  "and ab.cmd_code = ?"; 
		  	  			}
	  			if(!cont_corps.equals("0")){ 
		  	  		  SearchValue +=  "and substring(utl.form_code_control,1,3) = ?"; 
		  	  			}
		  		if(!cont_div.equals("0")){ 
		  	  		  SearchValue +=  "and substring(utl.form_code_control,1,6) = ?"; 
		  	  			}
		  		if(!cont_bde.equals("0")){ 
		  	  		  SearchValue +=  "and utl.form_code_control = ?"; 
		  	  			}
		  		if(!arm.equals("0")){ 
		  	  		  SearchValue +=  "and d.arm_code = ?"; 
		  	  			}
		  		if(!unit_sus_no.equals("")){ 
		  	  		  SearchValue +=  "and utl.sus_no = ?"; 
		  	  			}
		  		if(!rank.equals("")){ 
		  	  		  SearchValue +=  "and a.rank_code  = ?"; 
		  	  			}
		  		
	  			conn = dataSource.getConnection();		
	  			String qry=" select distinct sum(a.base_auth + a.mod_auth + a.foot_auth)::int as total_ue,a.sus_no,utl.unit_name, ab.cmd_name, \r\n"
	  					+ "    ac.coprs_name, \r\n"
	  					+ "    ad.div_name, \r\n"
	  					+ "    ae.bde_name,  d.arm_desc,t.label,rank.description from sus_pers_stdauth a\r\n"
	  					+ "LEFT JOIN tb_orbat_arm_code d ON a.arm::text = d.arm_code::text\r\n"
	  					+ "join tb_orbat_unt_dtl utl on a.sus_no=utl.sus_no\r\n"
	  					+ "	LEFT JOIN orbat_view_cmd ab ON substr(utl.form_code_control::text, 1, 1) = ab.cmd_code::text\r\n"
	  					+ "     LEFT JOIN orbat_view_corps ac ON substr(utl.form_code_control::text, 2, 2) = ac.corps_code::text\r\n"
	  					+ "     LEFT JOIN orbat_view_div ad ON substr(utl.form_code_control::text, 4, 3) = ad.div_code::text\r\n"
	  					+ "     LEFT JOIN orbat_view_bde ae ON substr(utl.form_code_control::text, 7, 4) = ae.bde_code::text\r\n"
	  					+ "left join CUE_TB_PSG_RANK_APP_MASTER rank on rank.code=a.rank_code \r\n"
	  					+ "	left join t_domain_value t on t.domainid = 'RANKCATEGORY' and a.rank_cat = t.codevalue 	where utl.status_sus_no='Active'\r\n"
	  					+SearchValue
	  					+ " group by a.sus_no,d.arm_desc,utl.unit_name , ab.cmd_name, ac.coprs_name,ad.div_name,ae.bde_name ,t.label,rank.description\r\n"
	  					+ "order by utl.unit_name  " ;  		
	  			PreparedStatement stmt=conn.prepareStatement(qry);
	  			int flag = 0;
				if(!search.equals("")) {			
					
					flag += 1;
					stmt.setString(flag, search.toUpperCase()+"%");
					flag += 1;
					stmt.setString(flag, search.toUpperCase()+"%");
					flag += 1;
					stmt.setString(flag, search.toUpperCase()+"%");
					flag += 1;
					stmt.setString(flag, search.toUpperCase()+"%");
					flag += 1;
					stmt.setString(flag, search.toUpperCase()+"%");
					flag += 1;
					stmt.setString(flag, search.toUpperCase()+"%");
					flag += 1;
					stmt.setString(flag, search.toUpperCase()+"%");
					flag += 1;
					stmt.setString(flag, search.toUpperCase()+"%");
				}
				
				if(!cont_comd.equals("")){ 
					flag += 1;
					stmt.setString(flag, cont_comd);
		  	  			}
	  			if(!cont_corps.equals("0")){ 
	  				flag += 1;
					stmt.setString(flag, cont_corps);
		  	  			}
		  		if(!cont_div.equals("0")){ 
		  			flag += 1;
					stmt.setString(flag, cont_div); 
		  	  			}
		  		if(!cont_bde.equals("0")){ 
		  			flag += 1;
					stmt.setString(flag, cont_bde);
		  	  			}
		  		if(!arm.equals("0")){ 
		  			flag += 1;
					stmt.setString(flag, arm);
		  	  			}
		  		if(!unit_sus_no.equals("")){ 
		  			flag += 1;
					stmt.setString(flag, unit_sus_no); 
		  	  			}
		  		if(!rank.equals("")){ 
		  			flag += 1;
					stmt.setString(flag, rank); 
		  	  			}
		  		
	  			ResultSet rs = stmt.executeQuery();      
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
		
		public long Rank_cat_wise_dtl_tablecount(String search, String orderColunm, String orderType,
				HttpSession sessionUserId, String cat_id,String cont_comd, String cont_corps,String cont_div,String cont_bde,String arm,String unit_sus_no,String unit_name,String rank) {
		 String SearchValue ="";
			int total = 0;
				String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
				String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
				String q = null;
				Connection conn = null;
				try {
					conn = dataSource.getConnection();
					if(!search.equals("")) { // for Input Filter
						SearchValue =" and  ";
						
						SearchValue +="(  upper(a.sus_no) like ? or upper(utl.unit_name) like ? or upper(ab.cmd_name) like ?"
								+ "or upper(ac.coprs_name) like ? or upper(ad.div_name) like ? or upper(ae.bde_name) like ?or upper(d.arm_desc) like ?"
								+ " or upper(t.label) like ?)";
					}
					if(cat_id.equals("Civilian") ){ 
			  			 
				  		  SearchValue +=  " and a.rank_cat in ('5','4')"; 
				  		  
				  		}
				  			if(cat_id.equals("JCO/OR") ){ 
					  			 
				  	  		  SearchValue +=  " and a.rank_cat in ('2')"; 
				  	  		  
				  	  		}
				  			if(cat_id.equals("OR") ){ 
					  			 
				  	  		  SearchValue +=  " and a.rank_cat in ('3')"; 
				  	  		  
				  	  		}
				  			if(cat_id.equals("OFFICER") ){ 
					  			 
					  	  		  SearchValue +=  " and a.rank_cat in ('0')"; 
					  	  		  
					  	  		}
				  			if(cat_id.equals("JCO") ){ 
					  	  		  SearchValue +=  " and a.rank_cat in ('1')"; 
					  	  		}
				  			if(cat_id.equals("NCsUE") ){ 
					  			 
					  	  		  SearchValue +=  " and a.rank_cat in ('12')"; 
					  	  		}
				  			
				  			if(!cont_comd.equals("")){ 
					  	  		  SearchValue +=  "and ab.cmd_code = ?"; 
					  	  			}
				  			if(!cont_corps.equals("0")){ 
					  	  		  SearchValue +=  "and substring(utl.form_code_control,1,3) = ?"; 
					  	  			}
					  		if(!cont_div.equals("0")){ 
					  	  		  SearchValue +=  "and substring(utl.form_code_control,1,6) = ?"; 
					  	  			}
					  		if(!cont_bde.equals("0")){ 
					  	  		  SearchValue +=  "and utl.form_code_control = ?"; 
					  	  			}
					  		if(!arm.equals("0")){ 
					  	  		  SearchValue +=  "and d.arm_code = ?"; 
					  	  			}
					  		if(!unit_sus_no.equals("")){ 
					  	  		  SearchValue +=  "and utl.sus_no = ?"; 
					  	  			}
					  		if(!rank.equals("")){ 
					  	  		  SearchValue +=  "and a.rank_code  = ?"; 
					  	  			}
				
				  q="select count(app.*) from(\r\n"
				  		+ "select distinct sum(a.base_auth + a.mod_auth + a.foot_auth)::int as total_ue,a.sus_no,utl.unit_name, ab.cmd_name, \r\n"
				  		+ "    ac.coprs_name, \r\n"
				  		+ "    ad.div_name, \r\n"
				  		+ "    ae.bde_name,  d.arm_desc,a.rank_cat,rank.description  from sus_pers_stdauth a\r\n"
				  		+ "LEFT JOIN tb_orbat_arm_code d ON a.arm::text = d.arm_code::text\r\n"
				  		+ "join tb_orbat_unt_dtl utl on a.sus_no=utl.sus_no\r\n"
				  		+ "	LEFT JOIN orbat_view_cmd ab ON substr(utl.form_code_control::text, 1, 1) = ab.cmd_code::text\r\n"
				  		+ "     LEFT JOIN orbat_view_corps ac ON substr(utl.form_code_control::text, 2, 2) = ac.corps_code::text\r\n"
				  		+ "     LEFT JOIN orbat_view_div ad ON substr(utl.form_code_control::text, 4, 3) = ad.div_code::text\r\n"
				  		+ "     LEFT JOIN orbat_view_bde ae ON substr(utl.form_code_control::text, 7, 4) = ae.bde_code::text\r\n"
				  		+ "left join CUE_TB_PSG_RANK_APP_MASTER rank on rank.code=a.rank_code \r\n"
				  		+ "	left join t_domain_value t on t.domainid = 'RANKCATEGORY' and a.rank_cat = t.codevalue 	where utl.status_sus_no='Active'\r\n"
				  		+ SearchValue
				  		+ "group by a.sus_no,d.arm_desc,utl.unit_name , ab.cmd_name, ac.coprs_name,ad.div_name,ae.bde_name ,a.rank_cat,rank.description\r\n"
				  		+ "order by utl.unit_name ) app";
					PreparedStatement stmt = conn.prepareStatement(q);
					int flag = 0;
					if(!search.equals("")) {			
						
						flag += 1;
						stmt.setString(flag, search.toUpperCase()+"%");
						flag += 1;
						stmt.setString(flag, search.toUpperCase()+"%");
						flag += 1;
						stmt.setString(flag, search.toUpperCase()+"%");
						flag += 1;
						stmt.setString(flag, search.toUpperCase()+"%");
						flag += 1;
						stmt.setString(flag, search.toUpperCase()+"%");
						flag += 1;
						stmt.setString(flag, search.toUpperCase()+"%");
						flag += 1;
						stmt.setString(flag, search.toUpperCase()+"%");
						flag += 1;
						stmt.setString(flag, search.toUpperCase()+"%");
					}
					if(!cont_comd.equals("")){ 
						flag += 1;
						stmt.setString(flag, cont_comd);
			  	  			}
		  			if(!cont_corps.equals("0")){ 
		  				flag += 1;
						stmt.setString(flag, cont_corps);
			  	  			}
			  		if(!cont_div.equals("0")){ 
			  			flag += 1;
						stmt.setString(flag, cont_div); 
			  	  			}
			  		if(!cont_bde.equals("0")){ 
			  			flag += 1;
						stmt.setString(flag, cont_bde);
			  	  			}
			  		if(!arm.equals("0")){ 
			  			flag += 1;
						stmt.setString(flag, arm);
			  	  			}
			  		if(!unit_sus_no.equals("")){ 
			  			flag += 1;
						stmt.setString(flag, unit_sus_no); 
			  	  			}
			  		if(!rank.equals("")){ 
			  			flag += 1;
						stmt.setString(flag, rank); 
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
		
		
		public List<Map<String, Object>> get_DocTypeDetais_Report(String pageType, int startPage, int pageLength,
				String search, String orderColunm, String orderType, HttpSession sessionUserId ,
				String cont_comd, String cont_corps, String cont_div, String cont_bde, String arm, String unit_sus_no,
				String unit_name, String radio1)
	  	{		
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	  		Connection conn = null;
	  		String qry="";
	  		String SearchValue ="";
	  		try{	  
	  			if(!search.equals("")) { // for Input Filter
					SearchValue =" and  ";
					
					SearchValue +="(  upper(d.we_pe) like ? or upper(d.we_pe_no) like ? or upper(d.table_title) like ?"
							+ "or upper(d.sponsor_dire) like ? )";
				}
	  		
	  			if(!cont_comd.equals("")){ 
		  	  		  SearchValue +=  "and substring(utl.form_code_control,1,1) = ?"; 
		  	  			}
	  			if(!cont_corps.equals("0")){ 
		  	  		  SearchValue +=  "and substring(utl.form_code_control,1,3) = ?"; 
		  	  			}
		  		if(!cont_div.equals("0")){ 
		  	  		  SearchValue +=  "and substring(utl.form_code_control,1,6) = ?"; 
		  	  			}
		  		if(!cont_bde.equals("0")){ 
		  	  		  SearchValue +=  "and utl.form_code_control = ?"; 
		  	  			}
		  		if(!arm.equals("0")){ 
		  	  		  SearchValue +=  "and d.arm = ?"; 
		  	  			}
		  		if(!unit_sus_no.equals("")){ 
		  	  		  SearchValue +=  "and utl.sus_no = ?"; 
		  	  			}
		  		
		  		if(!pageType.equals("")){ 
//		  			if(pageType.equals("we") || pageType.equals("fe") || pageType.equals("pe") || pageType.equals("gsl")) {
//		  				 SearchValue +=  "AND d.we_pe = ?"; 
//		  			}
		  			if(pageType.equals("wet") || pageType.equals("fet") || pageType.equals("pet")) {
		  				 SearchValue +=  "AND wpf.wet_pet = ?"; 
		  			}	
		  	  			}
		  		if (!radio1.equals("")) {
				    SearchValue += " and upper(d.we_pe) like ? ";
				}
		  		
	  			conn = dataSource.getConnection();	
	  			if (pageType.equals("we") || pageType.equals("fe") || pageType.equals("pe") || pageType.equals("gsl")) {
	  			 qry=" select distinct d.we_pe,a.wepe_pers_no,a.sus_no,utl.unit_name,c.line_dte_name as sponsor_dire,\r\n"
	  			 		+ "a.wet_pet_no\r\n"
	  			 		+ "from (\r\n"
	  			 		+ "SELECT a.SUS_NO, a.wepe_pers_no,a.wepe_weapon_no,a.status_pers,b.wet_pet_no\r\n"
	  			 		+ "from  cue_tb_wepe_link_sus_perstransweapon a\r\n"
	  			 		+ "left join cue_tb_wepeconditions b on a.wepe_weapon_no=b.we_pe_no and b.type='3'\r\n"
	  			 		+ ") a\r\n"
	  			 		+ "join tb_orbat_unt_dtl utl on a.sus_no=utl.sus_no and utl.status_sus_no='Active'\r\n"
	  			 		+ "join cue_tb_wepeconditions d on a.wepe_pers_no=d.we_pe_no and d.type ='1'\r\n"
	  			 		+ "left join TB_ORBAT_LINE_DTE c on d.sponsor_dire=c.line_dte_sus \r\n"
	  			 		+ "where a.status_pers='1'"+SearchValue+" ORDER BY wepe_pers_no\r\n" ;  
	  			}
	  			
	  			if (pageType.equals("wet") || pageType.equals("fet") || pageType.equals("pet")) {
		  			 qry=" select distinct wpf.wet_pet as we_pe,w.sus_no,wpf.wet_pet_no as we_pe_no,utl.unit_name,c.line_dte_name as sponsor_dire \r\n"
		  			 		+ "	from cue_tb_mms_wet_pet_mast_det wpf\r\n"
		  			 		+ "	inner join  cue_tb_wepeconditions d  on d.wet_pet_no=wpf.wet_pet_no\r\n"
		  			 		+ "INNER JOIN  cue_tb_wepe_link_sus_perstransweapon w \r\n"
		  			 		+ "ON (d.we_pe_no = w.wepe_pers_no and status_pers='1')\r\n"
		  			 		+ "or (d.we_pe_no = w.wepe_trans_no and status_trans='1')\r\n"
		  			 		+ "or (d.we_pe_no = w.wepe_weapon_no and status_weap='1')\r\n"
		  			 		+ "left join TB_ORBAT_LINE_DTE c on d.sponsor_dire=c.line_dte_sus \r\n"
		  			 		+ "left join tb_orbat_unt_dtl utl on utl.sus_no=w.sus_no and status_sus_no='Active'\r\n"
		  			 		+ "WHERE d.we_pe_no NOT IN (SELECT DISTINCT suprcdd_we_pe_no FROM cue_tb_wepeconditions \r\n"
		  			 		+ "WHERE suprcdd_we_pe_no IS NOT NULL) \r\n"
		  			 		+ "AND d.we_pe_no IS NOT NULL   AND d.status = '1'   AND d.type IN ('1', '2', '3') \r\n"
		  					+ "\r\n"
		  					+SearchValue ;  
		  			}
	  			
	  			PreparedStatement stmt=conn.prepareStatement(qry);
	  			
	  			int flag = 0;
				if(!search.equals("")) {			
					
					flag += 1;
					stmt.setString(flag, search.toUpperCase()+"%");
					flag += 1;
					stmt.setString(flag, search.toUpperCase()+"%");
					flag += 1;
					stmt.setString(flag, search.toUpperCase()+"%");
					flag += 1;
					
				}
				
				if(!cont_comd.equals("")){ 
					flag += 1;
					stmt.setString(flag, cont_comd);
		  	  			}
	  			if(!cont_corps.equals("0")){ 
	  				flag += 1;
					stmt.setString(flag, cont_corps);
		  	  			}
		  		if(!cont_div.equals("0")){ 
		  			flag += 1;
					stmt.setString(flag, cont_div); 
		  	  			}
		  		if(!cont_bde.equals("0")){ 
		  			flag += 1;
					stmt.setString(flag, cont_bde);
		  	  			}
		  		if(!arm.equals("0")){ 
		  			flag += 1;
					stmt.setString(flag, arm);
		  	  			}
		  		if(!unit_sus_no.equals("")){ 
		  			flag += 1;
					stmt.setString(flag, unit_sus_no); 
		  	  			}
		  		
		  		if(!pageType.equals("")){ 
		  			if(pageType.equals("wet") || pageType.equals("fet") || pageType.equals("pet")) {
		  			flag += 1;
					stmt.setString(flag, pageType.toUpperCase()); 
		  			}
		  	  			}
		  		if(!radio1.equals("")){ 
		  			flag += 1;
					stmt.setString(flag, radio1); 
		  	  			}

	  			ResultSet rs = stmt.executeQuery();      
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
		
		public long get_DocTypeDetais_Report_tablecount(String pageType, String search, String orderColunm,
				String orderType, HttpSession sessionUserId,  String cont_comd, String cont_corps,
				String cont_div, String cont_bde, String arm, String unit_sus_no, String unit_name, String radio1) {
		 String SearchValue ="";
			int total = 0;
				String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
				String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
				String q = null;
				Connection conn = null;
				try {
					conn = dataSource.getConnection();
					if(!search.equals("")) { // for Input Filter
						SearchValue =" and  ";
						
						SearchValue +="( upper(d.we_pe) like ? or upper(d.we_pe_no) like ? or upper(d.table_title) like ?"
								+ "or upper(d.sponsor_dire) like ?)";
					}
					
				  			
				  			if(!cont_comd.equals("")){ 
					  	  		  SearchValue +=  "and substring(utl.form_code_control,1,1) = ?"; 
					  	  			}
				  			if(!cont_corps.equals("0")){ 
					  	  		  SearchValue +=  "and substring(utl.form_code_control,1,3) = ?"; 
					  	  			}
					  		if(!cont_div.equals("0")){ 
					  	  		  SearchValue +=  "and substring(utl.form_code_control,1,6) = ?"; 
					  	  			}
					  		if(!cont_bde.equals("0")){ 
					  	  		  SearchValue +=  "and utl.form_code_control = ?"; 
					  	  			}
					  		if(!arm.equals("0")){ 
					  	  		  SearchValue +=  "and d.arm = ?"; 
					  	  			}
					  		if(!unit_sus_no.equals("")){ 
					  	  		  SearchValue +=  "and utl.sus_no = ?"; 
					  	  			}
					  		if(!pageType.equals("")){ 
//					  			if(pageType.equals("we") || pageType.equals("fe") || pageType.equals("pe") || pageType.equals("gsl")) {
//					  				 SearchValue +=  "AND d.we_pe = ?"; 
//					  			}
					  			if(pageType.equals("wet") || pageType.equals("fet") || pageType.equals("pet")) {
					  				 SearchValue +=  "AND wpf.wet_pet = ?"; 
					  			}	
					  	  			}
					  		if (!radio1.equals("")) {
							    SearchValue += " and upper(d.we_pe) like ? ";
							}
					  		
					  		if (pageType.equals("we") || pageType.equals("fe") || pageType.equals("pe") || pageType.equals("gsl")) {
				  q="select count(app.*) from(select distinct d.we_pe,a.wepe_pers_no,a.sus_no,utl.unit_name,c.line_dte_name as sponsor_dire,\r\n"
				  		+ "a.wet_pet_no\r\n"
				  		+ "from (\r\n"
				  		+ "SELECT a.SUS_NO, a.wepe_pers_no,a.wepe_weapon_no,a.status_pers,b.wet_pet_no\r\n"
				  		+ "from  cue_tb_wepe_link_sus_perstransweapon a\r\n"
				  		+ "left join cue_tb_wepeconditions b on a.wepe_weapon_no=b.we_pe_no and b.type='3'\r\n"
				  		+ ") a\r\n"
				  		+ "join tb_orbat_unt_dtl utl on a.sus_no=utl.sus_no and utl.status_sus_no='Active'\r\n"
				  		+ "join cue_tb_wepeconditions d on a.wepe_pers_no=d.we_pe_no and d.type ='1'\r\n"
				  		+ "left join TB_ORBAT_LINE_DTE c on d.sponsor_dire=c.line_dte_sus \r\n"
				  		+ "where a.status_pers='1'"+SearchValue+" ORDER BY wepe_pers_no\r\n"
				  		+ ") app";
					  		}
					  		
							if (pageType.equals("wet") || pageType.equals("fet") || pageType.equals("pet") ) {
								  q="select count(app.*) from(select distinct w.sus_no, wpf.wet_pet as we_pe,wpf.wet_pet_no as we_pe_no,utl.unit_name,c.line_dte_name as sponsor_dire \r\n"
								  		+ "	from cue_tb_mms_wet_pet_mast_det wpf\r\n"
								  		+ "	inner join  cue_tb_wepeconditions d  on d.wet_pet_no=wpf.wet_pet_no\r\n"
								  		+ "INNER JOIN  cue_tb_wepe_link_sus_perstransweapon w \r\n"
								  		+ "ON (d.we_pe_no = w.wepe_pers_no and status_pers='1')\r\n"
								  		+ "or (d.we_pe_no = w.wepe_trans_no and status_trans='1')\r\n"
								  		+ "or (d.we_pe_no = w.wepe_weapon_no and status_weap='1')\r\n"
								  		+ "left join tb_orbat_unt_dtl utl on utl.sus_no=w.sus_no and status_sus_no='Active'\r\n"
								  		+ "left join TB_ORBAT_LINE_DTE c on d.sponsor_dire=c.line_dte_sus \r\n"
								  		+ "WHERE d.we_pe_no NOT IN (SELECT DISTINCT suprcdd_we_pe_no FROM cue_tb_wepeconditions \r\n"
								  		+ "WHERE suprcdd_we_pe_no IS NOT NULL) \r\n"
								  		+ "AND d.we_pe_no IS NOT NULL   AND d.status = '1'   AND d.type IN ('1', '2', '3')\r\n"
								  		+ "\r\n"
								  		+ SearchValue 
								  		+ ") app";
									  		}
					PreparedStatement stmt = conn.prepareStatement(q);
					int flag = 0;
					if(!search.equals("")) {			
						
						flag += 1;
						stmt.setString(flag, search.toUpperCase()+"%");
						flag += 1;
						stmt.setString(flag, search.toUpperCase()+"%");
						flag += 1;
						stmt.setString(flag, search.toUpperCase()+"%");
						flag += 1;
						
					}
					
					if(!cont_comd.equals("")){ 
						flag += 1;
						stmt.setString(flag, cont_comd);
			  	  			}
		  			if(!cont_corps.equals("0")){ 
		  				flag += 1;
						stmt.setString(flag, cont_corps);
			  	  			}
			  		if(!cont_div.equals("0")){ 
			  			flag += 1;
						stmt.setString(flag, cont_div); 
			  	  			}
			  		if(!cont_bde.equals("0")){ 
			  			flag += 1;
						stmt.setString(flag, cont_bde);
			  	  			}
			  		if(!arm.equals("0")){ 
			  			flag += 1;
						stmt.setString(flag, arm);
			  	  			}
			  		if(!unit_sus_no.equals("")){ 
			  			flag += 1;
						stmt.setString(flag, unit_sus_no); 
			  	  			}
			  		if(!pageType.equals("")){ 
			  			if(pageType.equals("wet") || pageType.equals("fet") || pageType.equals("pet")) {
			  			flag += 1;
						stmt.setString(flag, pageType.toUpperCase()); 
			  			}
			  	  			}
			  		if(!radio1.equals("")){ 
			  			flag += 1;
						stmt.setString(flag, radio1); 
			  	  			}
//			  		stmt.setString(1, pageType.toUpperCase());
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
	
		
		//////////////////////////////////////
		public List<Map<String, Object>> getCueTypeYearWiseUEwet(int fromYear, int toYear, String arm)
	  	{				
	  		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	  		Connection conn = null;
	  		String q="";
	  	
	  		try{	  
	  			conn = dataSource.getConnection();	
	  			
	  			if(!arm.equals("0")) {
	  				q ="and  arm = ?";
	  			}
	  			String qry="select substring(created_on,7,6) as getyear,count(wet_pet) FILTER (where wet_pet='WET') as wet,count(wet_pet) FILTER (where wet_pet='PET') as pet,count(wet_pet) FILTER (where wet_pet='FET') as fet  from cue_tb_mms_wet_mast_upload where wet_pet!='' and status='1' \r\n" + 
	  					" and substring(created_on,7,6)::text between ?::text and ?::text " + q +" group by 1 order by 1" ; 
	  			
	  			PreparedStatement stmt=conn.prepareStatement(qry);
	  			
	  			stmt.setInt(1, fromYear);
	  			stmt.setInt(2, toYear);	  		
	  			if(!arm.equals("0")){ 				
					stmt.setString(3, arm);
		  	  			}
	  		
	  			
	  			ResultSet rs = stmt.executeQuery();      
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


		public List<Map<String, Object>> getCueTypeMonthWiseUEwet(int year, String doc_type, String armwet)
	  	{		
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	  		Connection conn = null;
	  		String q="";
	  		String y="";
	  		try{	  
	  			conn = dataSource.getConnection();		
	  			if(doc_type.equals("wet")) {
	  				q = "count(wet_pet) FILTER (where wet_pet='WET') as wet";
	  			}
	  			if(doc_type.equals("pet")) {
	  				q = "count(wet_pet) FILTER (where wet_pet='PET') as pet";
	  			}
	  			if(doc_type.equals("fet")) {
	  				q = "count(wet_pet) FILTER (where wet_pet='FET') as fet";
	  			}
	  			
	  			if(!armwet.equals("0")) {
	  			    y= " and arm =? " ;
	  			}
	  			
	  			String qry="select substring(created_on,4,2) as getmonth, "+ q +" from cue_tb_mms_wet_mast_upload where wet_pet!='' and status='1' \r\n" + 
	  					"and substring(created_on,7,6)::text=?::text "+ y +" group by 1 order by 1" ;  		
	  			
	  			PreparedStatement stmt=conn.prepareStatement(qry);
	  			
	  			stmt.setInt(1, year);
	  			if(!armwet.equals("0")) {
	  				stmt.setString(2, armwet);
	  			}
	  		
	  			ResultSet rs = stmt.executeQuery();      
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
		
		public List<Map<String, Object>> get_Active_Unit_DocTypeDetais_Report(String pageType, int startPage, int pageLength,
				String search, String orderColunm, String orderType, HttpSession sessionUserId, String cat_id,
				String cont_comd, String cont_corps, String cont_div, String cont_bde, String arm, String unit_sus_no,
				String unit_name, String location,String radio1)
	  	{		
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	  		Connection conn = null;
	  		String qry="";
	  		String SearchValue ="";
	  		try{	  
	  			if(!search.equals("")) { // for Input Filter
					SearchValue =" and  ";
					
					SearchValue +="( upper( a.sus_no) like ? or upper(a.unit_name) like ? or upper(ab.cmd_name) like ?"
							+ "or upper(ac.coprs_name) like ? or upper(ad.div_name) like ? or upper(ae.bde_name) like ?"
							+ "or upper(e.code_value) like ? or upper(nr.code_value) like ? or upper(x.label) like ?"
							+ "or upper(a.ct_part_i_ii) like ? or upper(d.arm_desc) like ? )";
				}
	  			
	  			if(!cont_comd.equals("")){ 
		  	  		  SearchValue +=  "and substring(a.form_code_control,1,1) = ?"; 
		  	  			}
	  			if(!cont_corps.equals("0")){ 
		  	  		  SearchValue +=  "and substring(a.form_code_control,1,3) = ?"; 
		  	  			}
		  		if(!cont_div.equals("0")){ 
		  	  		  SearchValue +=  "and substring(a.form_code_control,1,6) = ?"; 
		  	  			}
		  		if(!cont_bde.equals("0")){ 
		  	  		  SearchValue +=  "and a.form_code_control = ?"; 
		  	  			}
		  		if(!arm.equals("0")){ 
		  	  		  SearchValue +=  "and d.arm_code = ?"; 
		  	  			}
		  		if(!unit_sus_no.equals("")){ 
		  	  		  SearchValue +=  "and a.sus_no = ?"; 
		  	  			}
		  		if(!location.equals("")){ 
		  	  		  SearchValue +=  "and e.code_value  = ?"; 
		  	  			}
		  		
		  		if (!radio1.equals("")) {
				      if (SearchValue.contains("where")) {
				              SearchValue += " and upper(a.ct_part_i_ii) like ? ";
				      } else {
				              SearchValue += " and upper(a.ct_part_i_ii) like ? ";
				      }
				}
		  		
		  		
	  			conn = dataSource.getConnection();	
	  			 qry="SELECT \r\n" + 
	 					"    a.sus_no,\r\n" + 
						"    a.unit_name,\r\n" + 
						"    ab.cmd_name,\r\n" + 
						"    ac.coprs_name,\r\n" + 
						"    ad.div_name,\r\n" + 
						"    ae.bde_name,\r\n" + 
						"    e.code_value AS location,\r\n" + 
						"    nr.code_value AS nrs,\r\n" + 
						"    x.label AS type_of_force,\r\n" + 
						"    a.ct_part_i_ii,\r\n" + 
						"    d.arm_desc\r\n" + 
						"   FROM tb_orbat_unt_dtl a\r\n" + 
						"     JOIN tb_orbat_shdul_detl b ON a.id = b.letter_id\r\n" + 
						"     LEFT JOIN tb_orbat_arm_code d ON a.arm_code::text = d.arm_code::text\r\n" + 
						"     LEFT JOIN Tb_Orbat_Code e ON a.code::text = e.code::text AND e.code_type::text = 'Location'::text\r\n" + 
						"     LEFT JOIN Tb_Orbat_Code nr ON a.nrs_code::text = nr.code::text AND nr.code_type::text = 'Location'::text\r\n" + 
						"     LEFT JOIN t_domain_value x ON x.domainid = 'TYPEOFFORCE'::text AND x.codevalue = a.type_force::text\r\n" + 
						"     LEFT JOIN orbat_view_cmd ab ON substr(a.form_code_control::text, 1, 1) = ab.cmd_code::text\r\n" + 
						"     LEFT JOIN orbat_view_corps ac ON substr(a.form_code_control::text, 2, 2) = ac.corps_code::text\r\n" + 
						"     LEFT JOIN orbat_view_div ad ON substr(a.form_code_control::text, 4, 3) = ad.div_code::text\r\n" + 
						"     LEFT JOIN orbat_view_bde ae ON substr(a.form_code_control::text, 7, 4) = ae.bde_code::text" + 
						"    where a.status_sus_no='Active' "+SearchValue +" order by a.form_code_control";  
	  			
	  			PreparedStatement stmt=conn.prepareStatement(qry);
	  			
	  			int flag = 0;
				if(!search.equals("")) {			
					
					flag += 1;
					stmt.setString(flag, search.toUpperCase()+"%");
					flag += 1;
					stmt.setString(flag, search.toUpperCase()+"%");
					flag += 1;
					stmt.setString(flag, search.toUpperCase()+"%");
					flag += 1;
					
				}
				
				if(!cont_comd.equals("")){ 
					flag += 1;
					stmt.setString(flag, cont_comd);
		  	  			}
	  			if(!cont_corps.equals("0")){ 
	  				flag += 1;
					stmt.setString(flag, cont_corps);
		  	  			}
		  		if(!cont_div.equals("0")){ 
		  			flag += 1;
					stmt.setString(flag, cont_div); 
		  	  			}
		  		if(!cont_bde.equals("0")){ 
		  			flag += 1;
					stmt.setString(flag, cont_bde);
		  	  			}
		  		if(!arm.equals("0")){ 
		  			flag += 1;
					stmt.setString(flag, arm);
		  	  			}
		  		if(!unit_sus_no.equals("")){ 
		  			flag += 1;
					stmt.setString(flag, unit_sus_no); 
		  	  			}
		  		if(!location.equals("")){ 
		  			flag += 1;
					stmt.setString(flag, location); 
		  	  			}
		  		
		  		if (!radio1.equals("")) {
					flag += 1;
					stmt.setString(flag, radio1.toUpperCase());
					
				}
		  		

	  			ResultSet rs = stmt.executeQuery();      
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
		
		public long get_Active_Unit_DocTypeDetais_Report_tablecount(String pageType, String search, String orderColunm,
				String orderType, HttpSession sessionUserId, String cat_id, String cont_comd, String cont_corps,
				String cont_div, String cont_bde, String arm, String unit_sus_no, String unit_name, String location, String radio1) {
		 String SearchValue ="";
			int total = 0;
				String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
				String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();	
				String q = null;
				Connection conn = null;
				try {
					conn = dataSource.getConnection();
					if(!search.equals("")) { // for Input Filter
						SearchValue =" and  ";
						
						SearchValue +="( upper( a.sus_no) like ? or upper(a.unit_name) like ? or upper(ab.cmd_name) like ?"
								+ "or upper(ac.coprs_name) like ? or upper(ad.div_name) like ? or upper(ae.bde_name) like ?"
								+ "or upper(e.code_value) like ? or upper(nr.code_value) like ? or upper(x.label) like ?"
								+ "or upper(a.ct_part_i_ii) like ? or upper(d.arm_desc) like ? )";
					}
					
				  			
				  			if(!cont_comd.equals("")){ 
					  	  		  SearchValue +=  "and substring(a.form_code_control,1,1) = ?"; 
					  	  			}
				  			if(!cont_corps.equals("0")){ 
					  	  		  SearchValue +=  "and substring(a.form_code_control,1,3) = ?"; 
					  	  			}
					  		if(!cont_div.equals("0")){ 
					  	  		  SearchValue +=  "and substring(a.form_code_control,1,6) = ?"; 
					  	  			}
					  		if(!cont_bde.equals("0")){ 
					  	  		  SearchValue +=  "and a.form_code_control = ?"; 
					  	  			}
					  		if(!arm.equals("0")){ 
					  	  		  SearchValue +=  "and d.arm_code = ?"; 
					  	  			}
					  		if(!unit_sus_no.equals("")){ 
					  	  		  SearchValue +=  "and a.sus_no = ?"; 
					  	  			}
					  		if (!radio1.equals("")) {
							      if (SearchValue.contains("where")) {
							              SearchValue += " and upper(a.ct_part_i_ii) like ? ";
							      } else {
							              SearchValue += " and upper(a.ct_part_i_ii) like ? ";
							      }
							}
					  		
					  		q= "select count(app.*) from(SELECT \r\n" + 
				 					"    a.sus_no,\r\n" + 
									"    a.unit_name,\r\n" + 
									"    ab.cmd_name,\r\n" + 
									"    ac.coprs_name,\r\n" + 
									"    ad.div_name,\r\n" + 
									"    ae.bde_name,\r\n" + 
									"    e.code_value AS location,\r\n" + 
									"    nr.code_value AS nrs,\r\n" + 
									"    x.label AS type_of_force,\r\n" + 
									"    a.ct_part_i_ii,\r\n" + 
									"    d.arm_desc\r\n" + 
									"   FROM tb_orbat_unt_dtl a\r\n" + 
									"     JOIN tb_orbat_shdul_detl b ON a.id = b.letter_id\r\n" + 
									"     LEFT JOIN tb_orbat_arm_code d ON a.arm_code::text = d.arm_code::text\r\n" + 
									"     LEFT JOIN Tb_Orbat_Code e ON a.code::text = e.code::text AND e.code_type::text = 'Location'::text\r\n" + 
									"     LEFT JOIN Tb_Orbat_Code nr ON a.nrs_code::text = nr.code::text AND nr.code_type::text = 'Location'::text\r\n" + 
									"     LEFT JOIN t_domain_value x ON x.domainid = 'TYPEOFFORCE'::text AND x.codevalue = a.type_force::text\r\n" + 
									"     LEFT JOIN orbat_view_cmd ab ON substr(a.form_code_control::text, 1, 1) = ab.cmd_code::text\r\n" + 
									"     LEFT JOIN orbat_view_corps ac ON substr(a.form_code_control::text, 2, 2) = ac.corps_code::text\r\n" + 
									"     LEFT JOIN orbat_view_div ad ON substr(a.form_code_control::text, 4, 3) = ad.div_code::text\r\n" + 
									"     LEFT JOIN orbat_view_bde ae ON substr(a.form_code_control::text, 7, 4) = ae.bde_code::text" + 
									"    where a.status_sus_no='Active' "+SearchValue +" order by a.form_code_control) app";
				
					  		
					
					PreparedStatement stmt = conn.prepareStatement(q);
					int flag = 0;
					if(!search.equals("")) {			
						
						flag += 1;
						stmt.setString(flag, search.toUpperCase()+"%");
						flag += 1;
						stmt.setString(flag, search.toUpperCase()+"%");
						flag += 1;
						stmt.setString(flag, search.toUpperCase()+"%");
						flag += 1;
						
					}
					
					if(!cont_comd.equals("")){ 
						flag += 1;
						stmt.setString(flag, cont_comd);
			  	  			}
		  			if(!cont_corps.equals("0")){ 
		  				flag += 1;
						stmt.setString(flag, cont_corps);
			  	  			}
			  		if(!cont_div.equals("0")){ 
			  			flag += 1;
						stmt.setString(flag, cont_div); 
			  	  			}
			  		if(!cont_bde.equals("0")){ 
			  			flag += 1;
						stmt.setString(flag, cont_bde);
			  	  			}
			  		if(!arm.equals("0")){ 
			  			flag += 1;
						stmt.setString(flag, arm);
			  	  			}
			  		if(!unit_sus_no.equals("")){ 
			  			flag += 1;
						stmt.setString(flag, unit_sus_no); 
			  	  			}
			  		
			  		if (!radio1.equals("")) {
						flag += 1;
						stmt.setString(flag, radio1.toUpperCase());
						
					}
//			  		stmt.setString(1, pageType.toUpperCase());
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
		public List<Map<String, Object>> get_Dtl_doc_type_month_wise(String pageType, int startPage, int pageLength,
				String search, String orderColunm, String orderType, HttpSession sessionUserId,
				 String arm_a, String doc_type, String year)
	  	{		
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	  		Connection conn = null;
	  		String qry="";
	  		String SearchValue ="";
	  		try{	  
	  			if(!arm_a.equals("0")){ 
		  	  		  SearchValue +=  "and a.arm = ?"; 
		  	  			}
	  			conn = dataSource.getConnection();	
	  			 qry="SELECT unt.unit_name, c.sus_no,a.we_pe_no AS uploaded_wepe, \r\n"
	  			 		+ "       b.we_pe_no AS we_pe_no, \r\n"
	  			 		+ "       a.doc_type, \r\n"
	  			 		+ "       a.created_by\r\n"
	  			 		+ "FROM cue_tb_wepe_upload_condition a\r\n"
	  			 		+ "LEFT JOIN cue_tb_wepeconditions b ON a.we_pe_no = b.uploaded_wepe and b.status='1' \r\n"
	  			 		+ "LEFT JOIN cue_tb_wepe_link_sus_perstransweapon c ON \r\n"
	  			 		+ "    ((c.wepe_pers_no = b.we_pe_no AND b.type = '1'  and c.status_trans='1')\r\n"
	  			 		+ "        OR (c.wepe_trans_no = b.we_pe_no AND b.type = '2'  and c.status_pers='1' )\r\n"
	  			 		+ "        OR (c.wepe_weapon_no = b.we_pe_no AND b.type = '3'and c.status_weap='1'))\r\n"
	  			 		+ "	left join tb_orbat_unt_dtl unt on unt.sus_no=c.sus_no and unt.status_sus_no='Active'\r\n"
	  			 		+ "	where a.status='1' and substring(a.created_on,7,6)::text= ?::text and a.we_pe = ?" +SearchValue;  
	  			
	  			PreparedStatement stmt=conn.prepareStatement(qry);
		  	
		  		stmt.setString(1, year.toUpperCase());
		  		stmt.setString(2, doc_type.toUpperCase());
		  		if(!arm_a.equals("0")){ 
		  			stmt.setString(3, arm_a.toUpperCase());
		  		}
		  	
	  			ResultSet rs = stmt.executeQuery();      
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
		
		public long get_Dtl_doc_type_month_wiseCountsum(String search, String orderColunm, String orderType,
				HttpSession sessionUserId, String arm_a, String doc_type, String year) {
		 String SearchValue ="";
			int total = 0;
				
				String q = null;
				Connection conn = null;
				try {
					if(!arm_a.equals("0")){ 
			  	  		  SearchValue +=  "and a.arm = ?"; 
			  	  			}
					conn = dataSource.getConnection();
					  		q= "select count(app.*) from(SELECT unt.unit_name, c.sus_no,a.we_pe_no AS uploaded_wepe, \r\n"
					  				+ "       b.we_pe_no AS we_pe_no, \r\n"
					  				+ "       a.doc_type, \r\n"
					  				+ "       a.created_by\r\n"
					  				+ "FROM cue_tb_wepe_upload_condition a\r\n"
					  				+ "LEFT JOIN cue_tb_wepeconditions b ON a.we_pe_no = b.uploaded_wepe and b.status='1' \r\n"
					  				+ "LEFT JOIN cue_tb_wepe_link_sus_perstransweapon c ON \r\n"
					  				+ "    ((c.wepe_pers_no = b.we_pe_no AND b.type = '1'  and c.status_trans='1')\r\n"
					  				+ "        OR (c.wepe_trans_no = b.we_pe_no AND b.type = '2'  and c.status_pers='1' )\r\n"
					  				+ "        OR (c.wepe_weapon_no = b.we_pe_no AND b.type = '3'and c.status_weap='1'))\r\n"
					  				+ "	left join tb_orbat_unt_dtl unt on unt.sus_no=c.sus_no and unt.status_sus_no='Active'\r\n"
					  				+ "	where a.status='1'  and substring(a.created_on,7,6)::text= ?::text and a.we_pe = ? " +SearchValue
					  				+ ") app";
					  		
							PreparedStatement stmt=conn.prepareStatement(q);
				
					stmt.setString(1, year.toUpperCase());
			  		stmt.setString(2, doc_type.toUpperCase());
			  		if(!arm_a.equals("0")){ 
			  			stmt.setString(3, arm_a.toUpperCase());
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
		
		public List<Map<String, Object>> get_Dtl_doc_type_month_wise_WET_PET(String pageType, int startPage,
				int pageLength, String search, String orderColunm, String orderType, HttpSession sessionUserId,
				String arm_wet, String doc_wet, String year_wet)
	  	{		
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	  		Connection conn = null;
	  		String qry="";
	  		String SearchValue ="";
	  		try{	  
	  			if(!arm_wet.equals("0")){ 
		  	  		  SearchValue +=  "and a.arm = ?"; 
		  	  			}
	  			conn = dataSource.getConnection();	
	  			 qry="SELECT distinct unt.unit_name, c.sus_no,d.wet_pet_no AS uploaded_wetpet,  \r\n"
	  			 		+ "       d.doc_type, \r\n"
	  			 		+ "       d.created_by\r\n"
	  			 		+ "	from cue_tb_mms_wet_mast_upload d\r\n"
	  			 		+ "left join  cue_tb_wepe_upload_condition a  on a.wet_pet_no=d.wet_pet_no\r\n"
	  			 		+ "LEFT JOIN cue_tb_wepeconditions b ON a.we_pe_no = b.uploaded_wepe and b.status='1' \r\n"
	  			 		+ "LEFT JOIN cue_tb_wepe_link_sus_perstransweapon c ON \r\n"
	  			 		+ "    ((c.wepe_pers_no = b.we_pe_no AND b.type = '1'  and c.status_trans='1')\r\n"
	  			 		+ "        OR (c.wepe_trans_no = b.we_pe_no AND b.type = '2'  and c.status_pers='1' )\r\n"
	  			 		+ "        OR (c.wepe_weapon_no = b.we_pe_no AND b.type = '3'and c.status_weap='1'))\r\n"
	  			 		+ "	left join tb_orbat_unt_dtl unt on unt.sus_no=c.sus_no and unt.status_sus_no='Active'\r\n"
	  			 		+ "	where d.status='1' \r\n"
	  			 		+ "	 and substring(d.created_on,7,6)::text=?::text  and d.wet_pet = ? "+SearchValue;  
	  			
	  			PreparedStatement stmt=conn.prepareStatement(qry);
		  	
		  		stmt.setString(1, year_wet.toUpperCase());
		  		stmt.setString(2, doc_wet.toUpperCase());
		  		if(!arm_wet.equals("0")){ 
		  			stmt.setString(3, arm_wet.toUpperCase());
		  		}
		  		
	  			ResultSet rs = stmt.executeQuery();      
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
		public long get_Dtl_doc_type_month_wise_WET_PETCountsum(String search, String orderColunm, String orderType,
				HttpSession sessionUserId, String arm_wet, String doc_wet, String year_wet) {
		 String SearchValue ="";
			int total = 0;
				
				String q = null;
				Connection conn = null;
				try {
					if(!arm_wet.equals("0")){ 
			  	  		  SearchValue +=  "and a.arm = ?"; 
			  	  			}
					conn = dataSource.getConnection();
					  		q= "select count(app.*) from(SELECT distinct unt.unit_name, c.sus_no,d.wet_pet_no AS uploaded_wetpet,  \r\n"
					  				+ "       d.doc_type, \r\n"
					  				+ "       d.created_by\r\n"
					  				+ "	from cue_tb_mms_wet_mast_upload d\r\n"
					  				+ "left join  cue_tb_wepe_upload_condition a  on a.wet_pet_no=d.wet_pet_no\r\n"
					  				+ "LEFT JOIN cue_tb_wepeconditions b ON a.we_pe_no = b.uploaded_wepe and b.status='1' \r\n"
					  				+ "LEFT JOIN cue_tb_wepe_link_sus_perstransweapon c ON \r\n"
					  				+ "    ((c.wepe_pers_no = b.we_pe_no AND b.type = '1'  and c.status_trans='1')\r\n"
					  				+ "        OR (c.wepe_trans_no = b.we_pe_no AND b.type = '2'  and c.status_pers='1' )\r\n"
					  				+ "        OR (c.wepe_weapon_no = b.we_pe_no AND b.type = '3'and c.status_weap='1'))\r\n"
					  				+ "	left join tb_orbat_unt_dtl unt on unt.sus_no=c.sus_no and unt.status_sus_no='Active'\r\n"
					  				+ "	where d.status='1' \r\n"
					  				+ "	and substring(d.created_on,7,6)::text=?::text  and d.wet_pet = ? " +SearchValue
					  				+ ") app";
					  		
							PreparedStatement stmt=conn.prepareStatement(q);
				
					stmt.setString(1, year_wet.toUpperCase());
			  		stmt.setString(2, doc_wet.toUpperCase());
			  		if(!arm_wet.equals("0")){ 
			  			stmt.setString(3, arm_wet.toUpperCase());
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
		
	
}
