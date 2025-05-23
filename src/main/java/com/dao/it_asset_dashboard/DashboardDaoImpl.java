package com.dao.it_asset_dashboard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.sql.DataSource;

public class DashboardDaoImpl implements DashboardDao{
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	@Override
	public String getComputingData(String formation_code, String susno) {
		String list = "";
		Connection conn = null;
		String qry = "";
		
		String q = "";
		try {
			
				if(!formation_code.equals("")){
					
					q+=qAppend(formation_code, susno);
					
				}
				
			
			conn = dataSource.getConnection();
			qry = "		select (pt.assets_name) as name, count(am.id) filter (where am.id!=0  and cast(status as character varying) = '1'  )as holding ,\r\n" + 
					" count(am.id) filter (where am.id!=0  and  am.s_state='1' and cast(status as character varying) = '1'  )as servicable ,\r\n" + 
					"  count(am.id) filter (where am.id!=0  and  am.s_state='2' and cast(status as character varying) = '1'  )as un_servicable\r\n" + 
					"	\r\n" + 
					"from tb_asset_main am \r\n" + 
					"left join tb_mstr_assets pt on pt.id=am.asset_type  and pt.category=1 "
					+ "inner join logininformation l on l.user_sus_no =am.sus_no  \r\n" + 
					" where  l.username is not null  "+q+
					" group by pt.id,pt.assets_name  ";
			PreparedStatement stmt = conn.prepareStatement(qry);
			if(!formation_code.equals("")){
				stmt.setString(1,qStmtAppend(formation_code, susno) );
				}
			
		
			ResultSet rs = stmt.executeQuery();
			list += "[";
			int rowx=0;
			while (rs.next()) {
				
				if(rowx > 0)
				{
					list += ",";
				} else {
				rowx ++;
				}
				list += "{'data1': '"+rs.getString("name")+"', 'data2': " + rs.getString("holding") + ", 'data3':  " + rs.getString("servicable") + ", 'data4':  " + rs.getString("un_servicable") + " } ";
				
			}
			list += "]";
			
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
	public String getPeripheralData(String formation_code, String susno) {
		String list = "";
		Connection conn = null;
		String qry = "";
		
		String q = "";
		
		try {
			
			if(!formation_code.equals("")){
				
				q+=qAppend(formation_code, susno);
				
			}
			
			
			conn = dataSource.getConnection();
			qry = "		select (pt.assets_name) as name,  count(ap.id) filter (where ap.id!=0  and cast(status as character varying) = '1'  )as holding, \r\n" + 
					"	\r\n" + 
					"	 count(ap.id) filter (where ap.id!=0  and  ap.s_state='1' and cast(status as character varying) = '1'  )as servicable ,\r\n" + 
					"\r\n" + 
					"	 count(ap.id) filter (where ap.id!=0  and  ap.s_state='2' and cast(status as character varying) = '1'  )as un_servicable\r\n" + 
					"	\r\n" + 
					"from it_asset_peripherals ap   \r\n" + 
					"left join tb_mstr_assets pt on pt.id=ap.assets_type  and pt.category=2 "
					+ "inner join logininformation l on l.user_sus_no =ap.sus_no  \r\n" + 
					" where  l.user_sus_no is not null "+q+
					 " group by pt.id,pt.assets_name";
			PreparedStatement stmt = conn.prepareStatement(qry);
			if(!formation_code.equals("")){
				stmt.setString(1,qStmtAppend(formation_code, susno) );
				}
		
			ResultSet rs = stmt.executeQuery();
			list += "[";
			int rowx=0;
			while (rs.next()) {
				
				if(rowx > 0)
				{
					list += ",";
				} else {
				rowx ++;
				}
				list += "{'data1': '"+rs.getString("name")+"', 'data2': " + rs.getString("holding") + ", 'data3':  " + rs.getString("servicable") + ", 'data4':  " + rs.getString("un_servicable") + " } ";
				
			}
			list += "]";
			
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

	public String getComputingGraphData(String formation_code, String susno) {
		String list = "";
		Connection conn = null;
		String qry = "";
		String q = "";
		
		try {
			
			if(!formation_code.equals("")){
				
				q+=qAppend(formation_code, susno);
				
			}
			
			
			conn = dataSource.getConnection();
			qry = "select count(am.id) filter (where am.id!=0 and am.antiviruscheck='Yes'  and cast(status as character varying) = '1'  )as antivirus, \r\n" + 
					"	\r\n" + 
					"	 count(am.id) filter (where am.id!=0  and am.antiviruscheck='No'   and cast(status as character varying) = '1'  )as non_antivirus \r\n" + 
					"	\r\n" + 
					"from tb_asset_main am  " 
					+ "inner join logininformation l on l.user_sus_no =am.sus_no  \r\n"+
					" where  l.user_sus_no is not null "+q;
					
					
			PreparedStatement stmt = conn.prepareStatement(qry);
			if(!formation_code.equals("")){
				stmt.setString(1,qStmtAppend(formation_code, susno) );
				}
		
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				
				list += "["+ rs.getString("antivirus") + ", " + rs.getString("non_antivirus") + " ] ";
				
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

	
	
	public String getComputingWarrentyDueGraphData(int year,String formation_code, String susno) {
		String list = "";
		Connection conn = null;
		String qry = "";
		String q = "";
		try {
			
			
			if(!formation_code.equals("")){
							
							q+=qAppend(formation_code, susno);
				
			}
			
			conn = dataSource.getConnection();
			qry = 
					"select count(id) filter (where  extract(year from warranty)="+year+" and trim(to_char(warranty, 'month')) ='january' ) as january,\r\n" + 
					"count(id) filter (where  extract(year from warranty)="+year+" and trim(to_char(warranty, 'month')) ='febuary' ) as febuary,\r\n" + 
					"count(id) filter (where  extract(year from warranty)="+year+" and trim(to_char(warranty, 'month')) ='march' ) as march,\r\n" + 
					"count(id) filter (where  extract(year from warranty)="+year+" and trim(to_char(warranty, 'month')) ='april' ) as april,\r\n" + 
					"count(id) filter (where  extract(year from warranty)="+year+" and trim(to_char(warranty, 'month')) ='may' ) as may,\r\n" + 
					"count(id) filter (where  extract(year from warranty)="+year+" and trim(to_char(warranty, 'month')) ='june' ) as june,\r\n" + 
					"count(id) filter (where  extract(year from warranty)="+year+" and trim(to_char(warranty, 'month')) ='july' ) as july,\r\n" + 
					"count(id) filter (where  extract(year from warranty)="+year+" and trim(to_char(warranty, 'month')) ='august' ) as august,\r\n" + 
					"count(id) filter (where  extract(year from warranty)="+year+" and trim(to_char(warranty, 'month')) ='september' ) as september,\r\n" + 
					"count(id) filter (where  extract(year from warranty)="+year+" and trim(to_char(warranty, 'month')) ='october' ) as october,\r\n" + 
					"count(id) filter (where  extract(year from warranty)="+year+" and trim(to_char(warranty, 'month')) ='november' ) as november,\r\n" + 
					"count(id) filter (where  extract(year from warranty)="+year+" and trim(to_char(warranty, 'month')) ='december' ) as december\r\n" + 
					"\r\n" + 
					"from tb_asset_main am "+
					"inner join logininformation l on l.user_sus_no =am.sus_no  \r\n" + 
					" where  cast(status as character varying) = '1' and l.username is not null and id!=0 "+q;
			PreparedStatement stmt = conn.prepareStatement(qry);
			if(!formation_code.equals("")){
				stmt.setString(1,qStmtAppend(formation_code, susno) );
				}
			
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				
		
				list += "[{data1: 'January', data2: " + rs.getString("january") + " }, ";
				list += "{data1: 'Febuary', data2: " + rs.getString("febuary") + " }, ";
				list += "{data1: 'March', data2: " + rs.getString("march") + "}, ";
				list += "{data1: 'April', data2: " + rs.getString("april") + " }, ";
				list += "{data1: 'May', data2: " + rs.getString("may") + " }, ";
				list += "{data1: 'June', data2: " + rs.getString("june") + " }, ";
				list += "{data1: 'July', data2: " + rs.getString("july") + " }, ";
				list += "{data1: 'August', data2: " + rs.getString("august") + " }, ";
				list += "{data1: 'September', data2: " + rs.getString("september") + " }, ";
				list += "{data1: 'October', data2: " + rs.getString("october") + " }, ";
				list += "{data1: 'November', data2: " + rs.getString("november") + " }, ";
				list += "{data1: 'december', data2: " + rs.getString("december") + " } ]";
				
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
	
	
	
	public String getPeripheralWarrentyDueGraphData(int year,String formation_code, String susno) {
		String list = "";
		Connection conn = null;
		String qry = "";
		String q = "";
		try {
			
			
			if(!formation_code.equals("")){
				
				q+=qAppend(formation_code, susno);
	
				}
			
			conn = dataSource.getConnection();
			qry = 
					"select count(id) filter (where  extract(year from warranty)="+year+" and trim(to_char(warranty, 'month')) ='january' ) as january,\r\n" + 
					"count(id) filter (where  extract(year from warranty)="+year+" and trim(to_char(warranty, 'month')) ='febuary' ) as febuary,\r\n" + 
					"count(id) filter (where  extract(year from warranty)="+year+" and trim(to_char(warranty, 'month')) ='march' ) as march,\r\n" + 
					"count(id) filter (where  extract(year from warranty)="+year+" and trim(to_char(warranty, 'month')) ='april' ) as april,\r\n" + 
					"count(id) filter (where  extract(year from warranty)="+year+" and trim(to_char(warranty, 'month')) ='may' ) as may,\r\n" + 
					"count(id) filter (where  extract(year from warranty)="+year+" and trim(to_char(warranty, 'month')) ='june' ) as june,\r\n" + 
					"count(id) filter (where  extract(year from warranty)="+year+" and trim(to_char(warranty, 'month')) ='july' ) as july,\r\n" + 
					"count(id) filter (where  extract(year from warranty)="+year+" and trim(to_char(warranty, 'month')) ='august' ) as august,\r\n" + 
					"count(id) filter (where  extract(year from warranty)="+year+" and trim(to_char(warranty, 'month')) ='september' ) as september,\r\n" + 
					"count(id) filter (where  extract(year from warranty)="+year+" and trim(to_char(warranty, 'month')) ='october' ) as october,\r\n" + 
					"count(id) filter (where  extract(year from warranty)="+year+" and trim(to_char(warranty, 'month')) ='november' ) as november,\r\n" + 
					"count(id) filter (where  extract(year from warranty)="+year+" and trim(to_char(warranty, 'month')) ='december' ) as december\r\n" + 
					"\r\n" + 
					"from it_asset_peripherals am "+
					"inner join logininformation l on l.user_sus_no =am.sus_no  "
					+ "where id!=0  and cast(status as character varying) = '1' and  l.username is not null " +q; 
				
			PreparedStatement stmt = conn.prepareStatement(qry);
			if(!formation_code.equals("")){
				stmt.setString(1,qStmtAppend(formation_code, susno) );
				}
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				
		
				list += "[{data1: 'January', data2: " + rs.getString("january") + " }, ";
				list += "{data1: 'Febuary', data2: " + rs.getString("febuary") + " }, ";
				list += "{data1: 'March', data2: " + rs.getString("march") + "}, ";
				list += "{data1: 'April', data2: " + rs.getString("april") + " }, ";
				list += "{data1: 'May', data2: " + rs.getString("may") + " }, ";
				list += "{data1: 'June', data2: " + rs.getString("june") + " }, ";
				list += "{data1: 'July', data2: " + rs.getString("july") + " }, ";
				list += "{data1: 'August', data2: " + rs.getString("august") + " }, ";
				list += "{data1: 'September', data2: " + rs.getString("september") + " }, ";
				list += "{data1: 'October', data2: " + rs.getString("october") + " }, ";
				list += "{data1: 'November', data2: " + rs.getString("november") + " }, ";
				list += "{data1: 'december', data2: " + rs.getString("december") + " } ]";
				
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
	
	
	public String getComputingADNData(String formation_code, String susno) {
		String list = "";
		Connection conn = null;
		String qry = "";
		String q = "";
		
		try {
			
			
			if(!formation_code.equals("")){
							
							q+=qAppend(formation_code, susno);
				
			}
						
			conn = dataSource.getConnection();
			qry = "select ('Computing') as name, count(am.id) filter (where am.id!=0 and am.dply_envt in(7,9)   and cast(status as character varying) = '1'  )as ip_address, \r\n" + 
					"	count(am.id) filter (where am.id!=0  and am.dply_envt in(5,6,8,10)   and cast(status as character varying) = '1'  )as non_ip_address\r\n" + 
					"			from tb_asset_main am "
					+ "inner join logininformation l on l.user_sus_no =am.sus_no  "
					+ " where l.username is not null "+q;
					
					
			PreparedStatement stmt = conn.prepareStatement(qry);
	
			if(!formation_code.equals("")){
				stmt.setString(1,qStmtAppend(formation_code, susno) );
				}
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				
			
				list += "[{data1: '"+rs.getString("name")+"' , data2: " + rs.getString("ip_address") + ", data3: " + rs.getString("non_ip_address") + " }]";

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
	
	
	
	public String getaVehicleData(String formation_code, String susno) {
		String list = "";
		Connection conn = null;
		String qry = "";
		String q = "";
		try {
			if(!formation_code.equals("")){
				
				q+=qAppend(formation_code, susno);
				
			}
			
			conn = dataSource.getConnection();
			qry = "select \r\n" + 
					"	distinct m.mct_nomen,\r\n" + 
					"	\r\n" + 
					"	(select count(p.ba_no) from tb_tms_census_retrn p  where p.ba_no in (select ba_no from tb_tms_banum_dirctry d1 where \r\n" + 
					"	SUBSTRING(CAST(mct AS varchar),1,4) = SUBSTRING(CAST(d.mct  AS varchar),1,4)) and p.sus_no = part.sus_no and part.ser_status='0') as serviceable,\r\n" + 
					"	\r\n" + 
					"		(select count(p.ba_no) from tb_tms_census_retrn p  where p.ba_no in (select ba_no from tb_tms_banum_dirctry d1 where \r\n" + 
					"	SUBSTRING(CAST(mct AS varchar),1,4) = SUBSTRING(CAST(d.mct  AS varchar),1,4)) and p.sus_no = part.sus_no and part.ser_status='1') as un_serviceable\r\n" + 
					"from \r\n" + 
					"tb_tms_census_retrn part \r\n" + 
					"inner join tb_tms_banum_dirctry d on d.ba_no = part.ba_no \r\n" + 
					"left join tb_tms_mct_main_master m on  m.mct_main_id = SUBSTRING(cast(d.mct as character varying), 1, 4)\r\n" + 
					"inner join logininformation l on l.user_sus_no =part.sus_no   \r\n" + 
					"where  l.user_sus_no is not null "+q ;
			PreparedStatement stmt = conn.prepareStatement(qry);
			
			if(!formation_code.equals("")){
				stmt.setString(1,qStmtAppend(formation_code, susno) );
				}
			ResultSet rs = stmt.executeQuery();
			list += "[";
			int rowx=0;
			while (rs.next()) {
				
				if(rowx > 0)
				{
					list += ",";
				} else {
				rowx ++;
				}
				list += "{'data1': '"+rs.getString("mct_nomen")+"', 'data2': " + rs.getString("serviceable") + ", 'data3':  " + rs.getString("un_serviceable") + "  } ";
				
			}
			list += "]";
			
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
	
	public String getbVehicleData(String formation_code, String susno) {
		String list = "";
		Connection conn = null;
		String qry = "";
		String q = "";
		try {
			if(!formation_code.equals("")){
				
				q+=qAppend(formation_code, susno);
				
			}
			
			conn = dataSource.getConnection();
			qry ="\r\n" + 
					"select \r\n" + 
					"	distinct  d.mct ,m.mct_nomen,l.user_sus_no,\r\n" + 
					"	 SUBSTR(cast(d.mct as character varying),1,4) as mct_main_id,\r\n" + 
					"	(select count(p.ba_no) from tb_tms_census_retrn p  \r\n" + 
					"	 where p.ba_no in (select ba_no from tb_tms_banum_dirctry d1 where SUBSTRING(CAST(mct AS varchar),1,4) = SUBSTRING(CAST(d.mct  AS varchar),1,4)) \r\n" + 
					" 	) as total_uh, \r\n" + 
					"	\r\n" + 
					"	(select count(p.ba_no) from tb_tms_census_retrn p  \r\n" + 
					"	 where p.ba_no in (select ba_no from tb_tms_banum_dirctry d1 where SUBSTRING(CAST(mct AS varchar),1,4) = SUBSTRING(CAST(d.mct  AS varchar),1,4)) \r\n" + 
					" 	and p.ser_status='0') as servicable, \r\n" + 
					"	\r\n" + 
					"	(select count(p.ba_no) from tb_tms_census_retrn p  \r\n" + 
					"	 where p.ba_no in (select ba_no from tb_tms_banum_dirctry d1 where SUBSTRING(CAST(mct AS varchar),1,4) = SUBSTRING(CAST(d.mct  AS varchar),1,4)) \r\n" + 
					" 	and p.ser_status='1') as un_servicable \r\n" + 
					"from \r\n" + 
					"tb_tms_mvcr_parta_dtl part \r\n" + 
					"inner join tb_tms_banum_dirctry d on d.ba_no = part.ba_no \r\n" + 
					"left join tb_tms_mct_main_master m on  m.mct_main_id = SUBSTRING(cast(d.mct as character varying), 1, 4) \r\n" + 
					"inner join logininformation l on l.user_sus_no =d.sus_no   \r\n" + 
					"where  l.user_sus_no is not null "+q; 

			PreparedStatement stmt = conn.prepareStatement(qry);
		
			if(!formation_code.equals("")){
				stmt.setString(1,qStmtAppend(formation_code, susno) );
				}
			
			ResultSet rs = stmt.executeQuery();
			list += "[";
			int rowx=0;
			while (rs.next()) {
				
				if(rowx > 0)
				{
					list += ",";
				} else {
				rowx ++;
				}
				list += "{'data1': '"+rs.getString("mct_nomen")+"', 'data2': " + rs.getString("servicable") + ", 'data3':  " + rs.getString("un_servicable") + "  } ";
				
			}
			list += "]";
			
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
	
	
	public String getcVehicleData(String formation_code, String susno) {
		String list = "";
		Connection conn = null;
		String qry = "";
		String q = "";
		try {
			if(!formation_code.equals("")){
				
				q+=qAppend(formation_code, susno);
				
			}
			
			conn = dataSource.getConnection();
			qry =" select distinct mct.mct,mct.mct_nomen,\r\n" + 
					"	sum(we+opwks+ascfp+other) as total_uh ,\r\n" + 
					"	sum(ser_we+ser_opwks+ser_ascfp+ser_other) as servicable,\r\n" + 
					"	sum(ser_un_we+ser_un_opwks+ser_un_ascfp+ser_un_other) as un_servicable\r\n" + 
					"from (select \r\n" + 
					"		distinct substr(d.mct::varchar,1,4) as mct,\r\n" + 
					"	  (select count(p.proc_from) from tb_tms_emar_report p,tb_tms_banum_dirctry b  where upper(p.proc_from) = upper('we') and  p.em_no=b.ba_no and substr(b.mct::varchar,1,4) = m.mct_main_id and p.sus_no = e.sus_no ) as we,\r\n" + 
					"		(select count(p.proc_from) from tb_tms_emar_report p,tb_tms_banum_dirctry b  where upper(p.proc_from) = upper('we') and  p.em_no=b.ba_no and substr(b.mct::varchar,1,4) = m.mct_main_id and p.sus_no = e.sus_no and e.seviceable='Yes') as ser_we,\r\n" + 
					"	  (select count(p.proc_from) from tb_tms_emar_report p,tb_tms_banum_dirctry b  where upper(p.proc_from) = upper('we') and  p.em_no=b.ba_no and substr(b.mct::varchar,1,4) = m.mct_main_id and p.sus_no = e.sus_no and e.seviceable='No') as ser_un_we,\r\n" + 
					"	  \r\n" + 
					"	  (select count(p.proc_from) from tb_tms_emar_report p,tb_tms_banum_dirctry b  where upper(p.proc_from) =  upper('opwks') and p.em_no=b.ba_no and substr(b.mct::varchar,1,4) = m.mct_main_id and  p.sus_no = e.sus_no ) as opwks,\r\n" + 
					"		(select count(p.proc_from) from tb_tms_emar_report p,tb_tms_banum_dirctry b  where upper(p.proc_from) =  upper('opwks') and p.em_no=b.ba_no and substr(b.mct::varchar,1,4) = m.mct_main_id and  p.sus_no = e.sus_no and e.seviceable='Yes') as ser_opwks,\r\n" + 
					"	  (select count(p.proc_from) from tb_tms_emar_report p,tb_tms_banum_dirctry b  where upper(p.proc_from) =  upper('opwks') and p.em_no=b.ba_no and substr(b.mct::varchar,1,4) = m.mct_main_id and  p.sus_no = e.sus_no and e.seviceable='No') as ser_un_opwks,\r\n" + 
					"	  \r\n" + 
					"	  (select  count(p.proc_from) from tb_tms_banum_dirctry b ,tb_tms_emar_report p where  upper(p.proc_from) = upper('acsfp') and p.em_no=b.ba_no  and substr(b.mct::varchar,1,4) = m.mct_main_id and p.sus_no = e.sus_no  ) as ascfp,\r\n" + 
					"		(select  count(p.proc_from) from tb_tms_banum_dirctry b ,tb_tms_emar_report p where  upper(p.proc_from) = upper('acsfp') and p.em_no=b.ba_no  and substr(b.mct::varchar,1,4) = m.mct_main_id and p.sus_no = e.sus_no  and e.seviceable='Yes') as ser_ascfp,\r\n" + 
					"	  (select  count(p.proc_from) from tb_tms_banum_dirctry b ,tb_tms_emar_report p where  upper(p.proc_from) = upper('acsfp') and p.em_no=b.ba_no  and substr(b.mct::varchar,1,4) = m.mct_main_id and p.sus_no = e.sus_no  and e.seviceable='No') as ser_un_ascfp,\r\n" + 
					"	  \r\n" + 
					"	  (select count(p.proc_from) from tb_tms_emar_report p,tb_tms_banum_dirctry b  where upper(p.proc_from) = upper('others') and p.em_no=b.ba_no and substr(b.mct::varchar,1,4) = m.mct_main_id and  p.sus_no = e.sus_no) as other,\r\n" + 
					"		(select count(p.proc_from) from tb_tms_emar_report p,tb_tms_banum_dirctry b  where upper(p.proc_from) = upper('others') and p.em_no=b.ba_no and substr(b.mct::varchar,1,4) = m.mct_main_id and  p.sus_no = e.sus_no and e.seviceable='Yes') as ser_other,\r\n" + 
					"				(select count(p.proc_from) from tb_tms_emar_report p,tb_tms_banum_dirctry b  where upper(p.proc_from) = upper('others') and p.em_no=b.ba_no and substr(b.mct::varchar,1,4) = m.mct_main_id and  p.sus_no = e.sus_no and e.seviceable='No') as ser_un_other,\r\n" + 
					"	  \r\n" + 
					"	   (select count(p.proc_from) from tb_tms_emar_report p,tb_tms_banum_dirctry b  where p.em_no=e.em_no and substr(b.mct::varchar,1,4) = m.mct_main_id and p.sus_no = e.sus_no ) as Total_UH,\r\n" + 
					"	  (select count(p.proc_from) from tb_tms_emar_report p,tb_tms_banum_dirctry b  where p.em_no=e.em_no and substr(b.mct::varchar,1,4) = m.mct_main_id and p.sus_no = e.sus_no and e.seviceable='Yes') as ser_Total_UH,\r\n" + 
					"	  (select count(p.proc_from) from tb_tms_emar_report p,tb_tms_banum_dirctry b  where p.em_no=e.em_no and substr(b.mct::varchar,1,4) = m.mct_main_id and p.sus_no = e.sus_no and e.seviceable='No') as ser_Total_UH,\r\n" + 
					"	  \r\n" + 
					"		m.mct_nomen\r\n" + 
					"from tb_tms_emar_report e   \r\n" + 
					"inner join tb_tms_banum_dirctry d on  d.ba_no = e.em_no \r\n" + 
					"inner join tb_tms_mct_main_master m on  m.mct_main_id = SUBSTRING(cast(d.mct as character varying), 1, 4) \r\n" + 
					"	  left join tb_orbat_unt_dtl u on u.sus_no = e.sus_no\r\n" + 
					"	  inner join logininformation l on l.user_sus_no =d.sus_no   \r\n" + 
					"where  l.user_sus_no is not null \r\n" + 
					"	  and l.user_sus_no is not null "+q + 
					"	 ) as mct \r\n" + 
					"	 \r\n" + 
					"group by  mct.mct,mct.mct_nomen";
			PreparedStatement stmt = conn.prepareStatement(qry);
			
			if(!formation_code.equals("")){
				stmt.setString(1,qStmtAppend(formation_code, susno) );
				}
			
		
			
			ResultSet rs = stmt.executeQuery();
			list += "[";
			int rowx=0;
			while (rs.next()) {
				
				if(rowx > 0)
				{
					list += ",";
				} else {
				rowx ++;
				}
				list += "{'data1': '"+rs.getString("mct_nomen")+"', 'data2': " + rs.getString("servicable") + ", 'data3':  " + rs.getString("un_servicable") + "  } ";
				
			}
			list += "]";
			
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
	
	
public String  qAppend(String formation_code , String susno) {
		
		if(formation_code.equals("")) {
			
			return "and l.username  like ?";
		}
		else {
			
			return "and l.username=?";
		}
		
		
	}
	public String  qStmtAppend(String formation_code , String susno) {
		
		if(!formation_code.equals("")) {
			
			return formation_code;
		}
		else {
			
			return formation_code+"%";
		}
		
		
	}
}
