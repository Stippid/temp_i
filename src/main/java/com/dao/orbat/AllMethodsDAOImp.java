package com.dao.orbat;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.persistance.util.HibernateUtil;





public class AllMethodsDAOImp implements AllMethodsDAO{
	
	private DataSource dataSource;
	  
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    public ArrayList<List<String>> getCorpsList(String fcode) {
    	ArrayList<List<String>> alist = new ArrayList<List<String>>();
		Connection conn = null;
		try{	  
    		conn = dataSource.getConnection();
    		String sql= "select SUBSTR(form_code_control,1,3) as form_code_control,unit_name FROM tb_orbat_unt_dtl " + 
					" where sus_no in (select sus_no from tb_orbat_codesform where level_in_hierarchy ='Corps') and " + 
					" form_code_control like ? and status_sus_no = 'Active'";
			PreparedStatement stmt=conn.prepareStatement(sql);
			stmt.setString(1, fcode+"%");
			
			ResultSet rs1 = stmt.executeQuery();     
    		for(int i =0 ; rs1.next() ;i++) {
    			ArrayList<String> list = new ArrayList<String>();
    			list.add(rs1.getString("form_code_control"));
    			list.add(rs1.getString("unit_name"));
    			alist.add(list);
    		}
    		rs1.close();
    		stmt.close();
    	}catch (SQLException e) {
    		e.printStackTrace();
		}
		return alist;
    }
    
    public ArrayList<ArrayList<String>> getLOC_NRS_TypeLOC_TrnType(String locCode) {
    	ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try{	  
    		conn = dataSource.getConnection();
    		String sql= "select distinct a.code_value as location,"
					+ "b.code_value as nrs,"
					+ "a.code as loc_code,"
					+ "a.mod_desc as trn_type,"
					+ "c.label as type_of_loc,"
					+ "a.nrs_code "
					+ "from "
					+ "Tb_Orbat_Code a,Tb_Orbat_Code b,t_domain_value  c "
					+ "where a.code_type='Location' and b.code_type = 'Location' and a.nrs_code = b.code and "
					+ "a.status_record = '1' and b.status_record='1' and a.type_loc = c.codevalue and "
					+ "c.domainid='TYPEOFLOCATION' and a.code= ? order by a.code_value";
			PreparedStatement stmt=conn.prepareStatement(sql);
			stmt.setString(1, locCode);
			
			ResultSet rs1 = stmt.executeQuery();     
    		for(int i =0 ; rs1.next() ;i++) {
    			ArrayList<String> list = new ArrayList<String>();
    			list.add(rs1.getString("location"));
    			list.add(rs1.getString("nrs"));
    			list.add(rs1.getString("loc_code"));
    			list.add(rs1.getString("trn_type"));
    			list.add(rs1.getString("type_of_loc"));
    			list.add(rs1.getString("nrs_code"));
    			alist.add(list);
    		}
    		rs1.close();
    		stmt.close();
    	}catch (SQLException e) {
    		e.printStackTrace();
		}
		return alist;
    }
    
    //Generate Sus Version
    public int getSusVersion(String sus_no) {
    	Session sessionVersion = HibernateUtil.getSessionFactory().openSession();
		Transaction tx1 = sessionVersion.beginTransaction();
		Query q1 = sessionVersion.createQuery("select count(sus_no) from Orbat_Unt_Dtl where sus_no=:target_sus_no");
		q1.setParameter("target_sus_no", sus_no);
		Long list1 = (Long) q1.list().get(0);
		int ver =   (int) (list1 + 1);
		tx1.commit();
		sessionVersion.close();
		return ver;
    }
    @Override
	public List<String> check_sus_hierarchy(String sus_no)
	{
		List<String> list = new ArrayList<String>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			q = " select distinct view.level_in_hierarchy from tb_orbat_unt_dtl utl\r\n"
					+ "left join all_fmn_view view on view.form_code_control=utl.form_code_control\r\n"
					+ "where view.sus_no=?";
		
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setString(1, sus_no);
		
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
		
			while (rs.next()) {
//				Map<String, Object> columns = new LinkedHashMap<String, Object>();
//				for (int i = 1; i <= columnCount; i++) {
//					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
//				}
				list.add( rs.getString("level_in_hierarchy"));
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
    
    public ArrayList<List<String>> getCorpsListop(String fcode) {
    	ArrayList<List<String>> alist = new ArrayList<List<String>>();
		Connection conn = null;
		try{	  
    		conn = dataSource.getConnection();
    		String sql= "select SUBSTR(form_code_operation,1,3) as form_code_control,unit_name FROM tb_orbat_unt_dtl " + 
					" where sus_no in (select sus_no from tb_orbat_codesform where level_in_hierarchy ='Corps') and " + 
					" form_code_operation like ? and status_sus_no = 'Active'";
			PreparedStatement stmt=conn.prepareStatement(sql);
			stmt.setString(1, fcode+"%");
			
			ResultSet rs1 = stmt.executeQuery();     
    		for(int i =0 ; rs1.next() ;i++) {
    			ArrayList<String> list = new ArrayList<String>();
    			list.add(rs1.getString("form_code_control"));
    			list.add(rs1.getString("unit_name"));
    			alist.add(list);
    		}
    		rs1.close();
    		stmt.close();
    	}catch (SQLException e) {
    		e.printStackTrace();
		}
		return alist;
    }
 
 public ArrayList<List<String>> getCorpsListadm(String fcode) {
    	ArrayList<List<String>> alist = new ArrayList<List<String>>();
		Connection conn = null;
		try{	  
    		conn = dataSource.getConnection();
    		String sql= "select SUBSTR(form_code_admin,1,3) as form_code_control,unit_name FROM tb_orbat_unt_dtl " + 
					" where sus_no in (select sus_no from tb_orbat_codesform where level_in_hierarchy ='Corps') and " + 
					" form_code_admin like ? and status_sus_no = 'Active'";
			PreparedStatement stmt=conn.prepareStatement(sql);
			stmt.setString(1, fcode+"%");
			
			ResultSet rs1 = stmt.executeQuery();     
    		for(int i =0 ; rs1.next() ;i++) {
    			ArrayList<String> list = new ArrayList<String>();
    			list.add(rs1.getString("form_code_control"));
    			list.add(rs1.getString("unit_name"));
    			alist.add(list);
    		}
    		rs1.close();
    		stmt.close();
    	}catch (SQLException e) {
    		e.printStackTrace();
		}
		return alist;
    }
}