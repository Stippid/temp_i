package com.dao.mms;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

public class Mms_Common_DAOImpl implements Mms_Common_DAO {

	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	// FINAL METHODS START
	public String getRegdTransName(String nPara) {
		Connection conn = null;
		String fileName = "";
		try {
			conn = dataSource.getConnection();
			// String sql="";
			PreparedStatement stmt = null;

			String sql1 = "";

			sql1 = "select versionno  as filecode from mms_domain_values where domainid='TYPEOFHOLDING' and codevalue= ?";

			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, nPara);
			ResultSet rs = stmt.executeQuery();

			String fn = "";

			if (rs.next()) {
				fn = rs.getString("filecode");
			}

			rs.close();
			stmt.close();
			conn.close();

			if (fn.equalsIgnoreCase("1")) {
				fileName = "mms_tb_regn_mstr_detl";
			}
			if (fn.equalsIgnoreCase("2")) {
				fileName = "mms_tb_regn_oth_mstr";
			}
			if (fn.equalsIgnoreCase("3")) {
				fileName = "mms_tb_depot_regn_mstr_detl";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fileName;
	}

	public List<String> getMMSPRFtListBySearch(String nParaValue) {
		nParaValue = nParaValue.toUpperCase();
		List<String> l = new ArrayList<String>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sq1 = "";
			sq1 = "select distinct prf_group_code, prf_group from cue_tb_prf_group_mst where status_active='Active'";
			sq1 = sq1 + " and ((UPPER(prf_group) like ? or UPPER(prf_group_code) like ?) and prf_group_code in (SELECT DISTINCT PRF_CODE AS PRF_GROUP_CODE FROM mms_tb_mlccs_mstr_detl WHERE UPPER(nomen) like ?)) order by prf_group";
			
			PreparedStatement stmt = conn.prepareStatement(sq1);
			stmt.setString(1, "%" + nParaValue + "%");
			stmt.setString(2, "%" + nParaValue + "%");
			stmt.setString(3, "%" + nParaValue + "%");
			ResultSet rs = stmt.executeQuery();

			String str1 = "";
			while (rs.next()) {
				str1 = str1 + rs.getString("prf_group_code");
				str1 = str1 + ":" + rs.getString("prf_group");
				str1 = str1 + ",";
			}
			l.add(str1);

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}

	/*
	 * public List<String> getPrfListBySUSNo(String nParaValue) { List<String> l =
	 * new ArrayList<String>(); Connection conn = null; try{ conn =
	 * dataSource.getConnection(); String sq1=""; sq1 =
	 * "select distinct prf_group_code, prf_group from cue_tb_prf_group_mst where status_active='Active' and prf_group_code in ("
	 * ; sq1 = sq1 +
	 * " select distinct prf_code from mms_tb_mlccs_mstr_detl where census_no in (";
	 * sq1 = sq1 +
	 * " select distinct census_no from mms_tv_regn_mstr where sus_no=?)) order by prf_group"
	 * ;
	 * 
	 * PreparedStatement stmt = conn.prepareStatement(sq1); stmt.setString(1,
	 * nParaValue); ResultSet rs = stmt.executeQuery();
	 * 
	 * String str1=""; int ii=0; while(rs.next()){
	 * str1=str1+rs.getString("prf_group_code");
	 * str1=str1+":"+rs.getString("prf_group"); str1=str1+","; } l.add(str1);
	 * 
	 * rs.close(); stmt.close(); conn.close(); }catch(SQLException e){
	 * e.printStackTrace(); } return l; }
	 */

	public List<String> getPrfListBySUSNo(String nParaValue) {
		List<String> l = new ArrayList<String>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			// String sq1="";
			// sq1 = "select distinct prf_group_code, prf_group from
			// cue_tb_prf_group_mst where status_active='Active' and prf_group_code in
			// (";
			// sq1 = sq1 + " select distinct prf_code from mms_tb_mlccs_mstr_detl where
			// census_no in (";
			// sq1 = sq1 + " select distinct census_no from mms_tv_regn_mstr where
			// sus_no=?)) order by prf_group";

			String sq1 = "";
			sq1 = "select \r\n" + "distinct p.prf_group_code, \r\n" + "p.prf_group \r\n"
					+ "from cue_tb_prf_group_mst  p\r\n" + "inner join (SELECT distinct m.census_no,m.sus_no\r\n"
					+ "FROM mms_tb_regn_mstr_detl m \r\n"
					+ "LEFT JOIN tb_orbat_unt_dtl u ON m.sus_no = u.sus_no AND u.status_sus_no = 'Active'\r\n"
					+ "where m.sus_no=?\r\n" + "UNION ALL\r\n" + "SELECT distinct d.census_no,d.sus_no\r\n"
					+ "FROM mms_tb_depot_regn_mstr_detl d\r\n"
					+ "LEFT JOIN tb_orbat_unt_dtl u ON d.sus_no = u.sus_no AND u.status_sus_no = 'Active'\r\n"
					+ "where d.sus_no=?\r\n" + "UNION ALL\r\n" + "SELECT distinct o.census_no,o.to_sus_no\r\n"
					+ "FROM mms_tb_regn_oth_mstr o\r\n"
					+ "LEFT JOIN tb_orbat_unt_dtl u ON o.to_sus_no = u.sus_no AND u.status_sus_no = 'Active'\r\n"
					+ "where o.to_sus_no=?) m on m.sus_no=?\r\n"
					+ "inner join mms_tb_mlccs_mstr_detl d on d.census_no = m.census_no and p.prf_group_code = d.prf_code\r\n"
					+ "where status_active='Active' order by prf_group";
			
			
			PreparedStatement stmt = conn.prepareStatement(sq1);
			stmt.setString(1, nParaValue);
			stmt.setString(2, nParaValue);
			stmt.setString(3, nParaValue);
			stmt.setString(4, nParaValue);
			ResultSet rs = stmt.executeQuery();

			String str1 = "";
			while (rs.next()) {
				str1 = str1 + rs.getString("prf_group_code");
				str1 = str1 + ":" + rs.getString("prf_group");
				str1 = str1 + ",";
			}
			l.add(str1);

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}

	public List<String> getCensusNoBySUSNo(String nSusNo, String nPrfCode) {

		List<String> l = new ArrayList<String>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sq1 = "";

			/*
			 * sq1 =
			 * "select distinct a.census_no,a.nomen,a.prf_code,a.item_code from mms_tb_mlccs_mstr_detl a "
			 * ; sq1 = sq1 + " inner join mms_tv_regn_mstr b on a.census_no=b.census_no ";
			 * sq1 = sq1 + " where b.sus_no=? and a.prf_code=? order by a.nomen";
			 * 
			 * PreparedStatement stmt = conn.prepareStatement(sq1); stmt.setString(1,
			 * nSusNo); stmt.setString(2, nPrfCode);
			 */

			sq1 = "select distinct a.census_no,a.nomen,a.prf_code,a.item_code from mms_tb_mlccs_mstr_detl a "
					+ " inner join (SELECT distinct m.census_no,m.sus_no FROM mms_tb_regn_mstr_detl m "
					+ " LEFT JOIN tb_orbat_unt_dtl u ON m.sus_no = u.sus_no AND u.status_sus_no = 'Active' where m.sus_no=?"
					+ " UNION ALL " + " SELECT distinct d.census_no,d.sus_no FROM mms_tb_depot_regn_mstr_detl d"
					+ " LEFT JOIN tb_orbat_unt_dtl u ON d.sus_no = u.sus_no AND u.status_sus_no = 'Active' where d.sus_no=?"
					+ " UNION ALL " + " SELECT distinct o.census_no, o.to_sus_no  FROM mms_tb_regn_oth_mstr o "
					+ " LEFT JOIN tb_orbat_unt_dtl u ON o.to_sus_no = u.sus_no AND u.status_sus_no = 'Active' where o.to_sus_no=?)  b on a.census_no=b.census_no"
					+ " where b.sus_no=? and a.prf_code=? order by a.nomen";

			PreparedStatement stmt = conn.prepareStatement(sq1);
			stmt.setString(1, nSusNo);
			stmt.setString(2, nSusNo);
			stmt.setString(3, nSusNo);
			stmt.setString(4, nSusNo);
			stmt.setString(5, nPrfCode);
			ResultSet rs = stmt.executeQuery();

			String str1 = "";
			while (rs.next()) {
				str1 = str1 + rs.getString("census_no");
				str1 = str1 + ":" + rs.getString("nomen");
				str1 = str1 + ":" + rs.getString("prf_code");
				str1 = str1 + ":" + rs.getString("item_code");
				str1 = str1 + ",";
			}
			l.add(str1);

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}

	public List<String> getMMSDistinctMlccsList(String nParaValue, String nPara) {
		List<String> l = new ArrayList<String>();
		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			String sq1 = "";

			if (nPara.equals("ALL")) {
				sq1 = "select distinct census_no, nomen, prf_code from mms_tb_mlccs_mstr_detl";
			} else if (nPara.equals("NOMEN")) {
				sq1 = "select distinct census_no, nomen, prf_code from mms_tb_mlccs_mstr_detl where nomen=?";
			} else if (nPara.equals("CENSUS")) {
				sq1 = "select distinct census_no, nomen, prf_code from mms_tb_mlccs_mstr_detl where census_no=?";
			} else if (nPara.equals("PRFCODE")) {
				//sq1 = "select distinct census_no, nomen, prf_code from mms_tb_mlccs_mstr_detl where prf_code=?";
				
				sq1 = "select distinct census_no,concat(census_no,'-',nomen) as nomen, prf_code from mms_tb_mlccs_mstr_detl where prf_code=?";
			} else if (nPara.equals("PRF")) {
				sq1 = "select distinct census_no, nomen, prf_code from mms_tb_mlccs_mstr_detl where prf_group=?";
			}

			PreparedStatement stmt = conn.prepareStatement(sq1);

			if (!nPara.equals("ALL")) {
				stmt.setString(1, nParaValue);
			}
			ResultSet rs = stmt.executeQuery();
          
			String str1 = "";
			while (rs.next()) {
				str1 = str1 + rs.getString("census_no");
				str1 = str1 + ":" + rs.getString("nomen");
				str1 = str1 + ":" + rs.getString("prf_code");
				str1 = str1 + ",";
			}
			l.add(str1);

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}



	public List<String> getMMSUnitListBySearchold(String nParaValue) {
		nParaValue = nParaValue.toUpperCase();
		List<String> l = new ArrayList<String>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sq1 = "";
			sq1 = "select distinct form_code_control,sus_no,unit_name from tb_orbat_unt_dtl where status_sus_no='Active'";
			sq1 = sq1 + " and (sus_no like ? or unit_name like ?) order by unit_name";

			PreparedStatement stmt = conn.prepareStatement(sq1);
			stmt.setString(1, nParaValue + "%");
			stmt.setString(2, "%" + nParaValue + "%");
			ResultSet rs = stmt.executeQuery();

			String str1 = "";
			while (rs.next()) {
				str1 = str1 + rs.getString("form_code_control");
				str1 = str1 + ":" + rs.getString("sus_no");
				str1 = str1 + ":" + rs.getString("unit_name");
				str1 = str1 + ",";
			}
			l.add(str1);

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}
	
	
	public List<String> getMMSUnitListBySearch(String nParaValue, HttpSession s1) {
		String usrid=s1.getAttribute("username").toString();
		nParaValue = nParaValue.toUpperCase();
		List<String> l = new ArrayList<String>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sq1 = "";
			sq1 = "select distinct form_code_control,sus_no,unit_name from tb_orbat_unt_dtl where status_sus_no='Active'";			
			sq1 = sq1 + " and (sus_no like ? or unit_name like ?) order by unit_name";			
			sq1 =  " select distinct form_code_control,sus_no,unit_name,status_sus_no from (";
			sq1 += " select distinct form_code_control,sus_no,unit_name,status_sus_no from tb_orbat_unt_dtl where status_sus_no='Active'";
			sq1 += " and (sus_no like ? or unit_name like ?)";
			
			if(usrid.toUpperCase().indexOf("MMS")>=0) {
				sq1 += " union all ";	
				sq1 += " select form_code_control as form_code_control,sus_no,unit_name as unit_name,'Inactive' as status_sus_no from tb_orbat_unt_dtl where (sus_no,comm_depart_date) in (";			
				sq1 += " select sus_no,max(comm_depart_date) as dp_dt from tb_orbat_unt_dtl where status_sus_no !='Active'";
				sq1 += " and (sus_no like ? or unit_name like ?) and form_code_control !='xxxxxxxxxx' group by sus_no)";			
				sq1 += " and (sus_no) not in (select distinct sus_no from tb_orbat_unt_dtl where status_sus_no='Active'";
				sq1 += " and (sus_no like ? or unit_name like ?))";
			}
			
			sq1 += " ) t order by unit_name";				
			PreparedStatement stmt = conn.prepareStatement(sq1);
			stmt.setString(1, nParaValue + "%");
			stmt.setString(2, "%" + nParaValue + "%");
			
			
			
			if(usrid.toUpperCase().indexOf("MMS")>=0) {
				stmt.setString(3, nParaValue + "%");
				stmt.setString(4, "%" + nParaValue + "%");
				stmt.setString(5, nParaValue + "%");
				stmt.setString(6, "%" + nParaValue + "%");
			}
			ResultSet rs = stmt.executeQuery();

			String str1 = "";
			while (rs.next()) {
				str1 = str1 + rs.getString("form_code_control");
				str1 = str1 + ":" + rs.getString("sus_no");
				str1 = str1 + ":" + rs.getString("unit_name");
				if(usrid.toUpperCase().indexOf("MMS")>=0) {
					if(!rs.getString("status_sus_no").toUpperCase().equalsIgnoreCase("ACTIVE")) {					
						str1 = str1 + " ( IN-ACTIVE ) ";
					}
				}
				str1 = str1 + ",";
			}
			l.add(str1);

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}
	

	public List<String> getMMSDepotListBySearch(String nParaValue) {
		nParaValue = nParaValue.toUpperCase();
		List<String> l = new ArrayList<String>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sq1 = "";
			sq1 = "select distinct form_code_control,sus_no,unit_name from tb_orbat_unt_dtl where status_sus_no='Active'";
			sq1 = sq1
					+ " and sus_no in (select distinct sus_no from Tb_Orbat_Unit_Other_Function where role='DEPOT' and function_mms='ORD DEP') and (sus_no like ? or upper(unit_name) like ?) order by unit_name";

			
			PreparedStatement stmt = conn.prepareStatement(sq1);
			stmt.setString(1, "%" + nParaValue + "%");
			stmt.setString(2, "%" + nParaValue + "%");
			ResultSet rs = stmt.executeQuery();

			String str1 = "";
			while (rs.next()) {
				str1 = str1 + rs.getString("form_code_control");
				str1 = str1 + ":" + rs.getString("sus_no");
				str1 = str1 + ":" + rs.getString("unit_name");
				str1 = str1 + ",";
			}
			l.add(str1);

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}

	public List<String> getMMSDistinctHirarName(String nHirar, String nCnd, String codelevel) {
		List<String> l = new ArrayList<String>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sq1 = "";
			String cond = "";
			int start = 0;
			int end = 0;

			if (codelevel.equalsIgnoreCase("COMMAND")) {
				start = 1;
				end = 1;
			} else if (codelevel.equalsIgnoreCase("CORPS")) {
				start = 1;
				end = 3;
			} else if (codelevel.equalsIgnoreCase("DIVISION")) {
				start = 1;
				end = 6;
			} else if (codelevel.equalsIgnoreCase("BRIGADE")) {
				start = 1;
				end = 10;
			} else if (codelevel.equalsIgnoreCase("UNIT")) {
				start = 1;
				end = 10;
			}

			sq1 = "select distinct form_code_control,sus_no,unit_name from tb_orbat_unt_dtl where status_sus_no='Active' ";

			if (!codelevel.equalsIgnoreCase("LINE")) {
				if (nCnd != null && !nCnd.equals("ALL")) {
					if (nHirar.equalsIgnoreCase("COMMAND")) {
						cond = " and substring(form_code_control,?,?)=substring(?,?,?) ";
					} else if (nHirar.equalsIgnoreCase("CORPS")) {
						cond = " and substring(form_code_control,?,?)=substring(?,?,?) ";
					} else if (nHirar.equalsIgnoreCase("DIVISION")) {
						cond = " and substring(form_code_control,?,?)=substring(?,?,?) ";
					} else if (nHirar.equalsIgnoreCase("BRIGADE")) {
						cond = " and substring(form_code_control,?,?)=substring(?,?,?) ";
					} else if (nHirar.equalsIgnoreCase("UNIT")) {
						cond = " and substring(form_code_control,?,?)=substring(?,?,?) ";
					}
				}
			}

			if (codelevel.equalsIgnoreCase("LINE")) {
				if (nCnd.equalsIgnoreCase("ALL")) {
					cond = cond + " and arm_code in (select distinct arm_code from tb_orbat_arm_code) ";
				} else {
					cond = cond + " and arm_code=? ";
				}
			}

			if (!nHirar.equalsIgnoreCase("ALL")) {
				if (nCnd.equalsIgnoreCase("ALL")) {
					cond = cond
							+ " and sus_no in (select DISTINCT sus_no from tb_orbat_codesform where upper(level_in_hierarchy) in (?)) ";
				} else {
					cond = cond
							+ " and sus_no in (select DISTINCT sus_no from tb_orbat_codesform where upper(level_in_hierarchy) in (?)) ";
				}
			}

			if (!cond.equals(null)) {
				sq1 = sq1 + cond;
			}

			PreparedStatement stmt = conn.prepareStatement(sq1);

			if (!codelevel.equalsIgnoreCase("LINE")) {
				if (nCnd != null && !nCnd.equals("ALL")) {
					stmt.setInt(1, start);
					stmt.setInt(2, end);
					stmt.setString(3, nCnd);
					stmt.setInt(4, start);
					stmt.setInt(5, end);
				}

				if (!nHirar.equalsIgnoreCase("ALL")) {
					if (nCnd.equalsIgnoreCase("ALL")) {
						stmt.setString(1, nHirar);
					} else {
						stmt.setString(6, nHirar);
					}
				}
			}

			if (codelevel.equalsIgnoreCase("LINE")) {
				if (nCnd.equalsIgnoreCase("ALL")) {
					stmt.setString(1, nHirar);
				} else {
					stmt.setString(1, nCnd);
					stmt.setString(2, nHirar);
				}
			}

			ResultSet rs = stmt.executeQuery();

			String str1 = "";
			while (rs.next()) {
				str1 = str1 + rs.getString("form_code_control");
				str1 = str1 + ":" + rs.getString("sus_no");
				str1 = str1 + ":" + rs.getString("unit_name");
				str1 = str1 + ",";
			}
			l.add(str1);

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}

	public List<String> getMMSHirarNameBySUS(String FindWhat, String nSUSNo) {

		Connection conn = null;
		List<String> l = new ArrayList<String>();
		try {
			conn = dataSource.getConnection();
			String sq1 = "";
			String cond = "";

			if (FindWhat.equalsIgnoreCase("COMMAND")) {
				cond = "concat(substring(a.form_code_control,?,?),?)";
			} else if (FindWhat.equalsIgnoreCase("CORPS")) {
				cond = "concat(substring(a.form_code_control,?,?),?)";
			} else if (FindWhat.equalsIgnoreCase("DIVISION")) {
				cond = "concat(substring(a.form_code_control,?,?),?)";
			} else if (FindWhat.equalsIgnoreCase("BRIGADE")) {
				cond = "a.form_code_control";
			} else if (FindWhat.equalsIgnoreCase("HQ")) {
				cond = "a.form_code_control";
			}

			sq1 = "select p.hq,q.sus_no as hq_sus_no,q.unit_name as hq_name,p.form_code_control,p.sus_no,p.unit_name from (";
			sq1 = sq1 + " select " + cond
					+ " as hq,a.form_code_control,a.sus_no,a.unit_name from tb_orbat_unt_dtl a";
			sq1 = sq1
					+ " where a.sus_no=? and a.status_sus_no='Active') p left join nrv_hq q on p.hq=q.form_code_control";

			PreparedStatement stmt = conn.prepareStatement(sq1);

			if (FindWhat.equalsIgnoreCase("COMMAND")) {
				stmt.setInt(1, 1);
				stmt.setInt(2, 1);
				stmt.setString(3, "000000000");
				stmt.setString(4, nSUSNo);
			} else if (FindWhat.equalsIgnoreCase("CORPS")) {
				stmt.setInt(1, 1);
				stmt.setInt(2, 3);
				stmt.setString(3, "0000000");
				stmt.setString(4, nSUSNo);
			} else if (FindWhat.equalsIgnoreCase("DIVISION")) {
				stmt.setInt(1, 1);
				stmt.setInt(2, 6);
				stmt.setString(3, "0000");
				stmt.setString(4, nSUSNo);
			} else if (FindWhat.equalsIgnoreCase("BRIGADE")) {
				stmt.setString(1, nSUSNo);
			} else if (FindWhat.equalsIgnoreCase("HQ")) {
				stmt.setString(1, nSUSNo);
			}

			ResultSet rs = stmt.executeQuery();

			String str1 = "";
			while (rs.next()) {
				str1 = str1 + rs.getString("hq");
				str1 = str1 + ":" + rs.getString("hq_name");
				str1 = str1 + ":" + rs.getString("form_code_control");
				str1 = str1 + ":" + rs.getString("sus_no");
				str1 = str1 + ":" + rs.getString("unit_name");
				str1 = str1 + ":" + rs.getString("hq_sus_no");
				str1 = str1 + ",";
			}
			l.add(str1);

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}

	public List<String> getUHValue(String nSUSNo, String nPRF, String nHldType, String nEqptType, String nPara) {
		List<String> l = new ArrayList<String>();
		Connection conn = null;
		try {
			if (nSUSNo.equalsIgnoreCase(null)) {
				nSUSNo = "ALL";
			}

			if (nPRF.equalsIgnoreCase(null)) {
				nPRF = "ALL";
			}

			if (nHldType.equalsIgnoreCase(null)) {
				nHldType = "ALL";
			}

			if (nEqptType.equalsIgnoreCase(null)) {
				nEqptType = "ALL";
			}

			conn = dataSource.getConnection();
			String sq1 = "";
			String nCnd = "";

			if (nPara.equalsIgnoreCase("SUMM")) {
				sq1 = "select p.prf_code,p.type_of_hldg,p.type_of_eqpt,sum(p.tothol) as tothol from (";
				sq1 = sq1
						+ " select a.sus_no,m.prf_code,a.type_of_hldg,a.type_of_eqpt,a.tothol from mms_tv_regn_mstr a";
				sq1 = sq1 + " left join mms_tb_mlccs_mstr_detl m on a.census_no=m.census_no ";

				if (!nSUSNo.equalsIgnoreCase("ALL")) {
					if (nCnd.length() > 0) {
						nCnd = nCnd + " and ";
					}
					nCnd = nCnd + " a.sus_no=?";
				}

				if (!nPRF.equalsIgnoreCase("ALL")) {
					if (nCnd.length() > 0) {
						nCnd = nCnd + " and ";
					}
					nCnd = nCnd + " m.prf_code=?";
				}

				if (!nHldType.equalsIgnoreCase("ALL")) {
					if (nCnd.length() > 0) {
						nCnd = nCnd + " and ";
					}
					nCnd = nCnd + " a.type_of_hldg=?";
				}

				if (!nEqptType.equalsIgnoreCase("ALL")) {
					if (nCnd.length() > 0) {
						nCnd = nCnd + " and ";
					}
					nCnd = nCnd + " a.type_of_eqpt=?";
				}

				if (nCnd.length() > 0) {
					sq1 = sq1 + " where " + nCnd;
				}

				sq1 = sq1 + " ) p group by p.prf_code,p.type_of_hldg,p.type_of_eqpt";
			} else {
				sq1 = "select b.form_code_control,c.unit_name as hq_name,a.sus_no,b.unit_name,m.prf_code,prf.prf_group,a.type_of_hldg,t1.label as type_of_hldg_n,";
				sq1 = sq1 + " a.type_of_eqpt,t2.label as type_of_eqpt_n,a.tothol from mms_tv_regn_mstr a";
				sq1 = sq1 + " left join mms_tb_mlccs_mstr_detl m on a.census_no=m.census_no";
				sq1 = sq1 + " left join cue_tb_prf_group_mst prf on m.prf_code=prf.prf_group_code";
				sq1 = sq1 + " left join tb_orbat_unt_dtl b on a.sus_no=b.sus_no";
				sq1 = sq1 + " left join nrv_hq c on b.form_code_control=c.form_code_control";
				sq1 = sq1
						+ " left join mms_domain_values t1 on a.type_of_hldg=t1.codevalue and t1.domainid='TYPEOFHOLDING'";
				sq1 = sq1
						+ " left join mms_domain_values t2 on a.type_of_eqpt=t2.codevalue and t2.domainid='TYPEOFEQPT'";
				nCnd = " b.status_sus_no='Active'";

				if (!nSUSNo.equalsIgnoreCase("ALL")) {
					if (nCnd.length() > 0) {
						nCnd = nCnd + " and ";
					}
					nCnd = nCnd + " a.sus_no=?";
				}
				if (!nPRF.equalsIgnoreCase("ALL")) {
					if (nCnd.length() > 0) {
						nCnd = nCnd + " and ";
					}
					nCnd = nCnd + " m.prf_code=?";
				}

				if (!nHldType.equalsIgnoreCase("ALL")) {
					if (nCnd.length() > 0) {
						nCnd = nCnd + " and ";
					}
					nCnd = nCnd + " a.type_of_hldg=?";
				}

				if (!nEqptType.equalsIgnoreCase("ALL")) {
					if (nCnd.length() > 0) {
						nCnd = nCnd + " and ";
					}
					nCnd = nCnd + " a.type_of_eqpt=?";
				}

				if (nCnd.length() > 0) {
					sq1 = sq1 + " where " + nCnd;
				}
			}

			if (nPara.equalsIgnoreCase("LIST")) {
				sq1 = sq1 + " order by b.form_code_control,a.sus_no,a.type_of_hldg,a.type_of_eqpt";
			}

			PreparedStatement stmt = conn.prepareStatement(sq1);

			int g1 = 1;
			if (!nSUSNo.equalsIgnoreCase("ALL")) {
				stmt.setString(g1, nSUSNo);
				g1++;
			}

			if (!nPRF.equalsIgnoreCase("ALL")) {
				stmt.setString(g1, nPRF);
				g1++;
			}

			if (!nHldType.equalsIgnoreCase("ALL")) {
				stmt.setString(g1, nHldType);
				g1++;
			}

			if (!nEqptType.equalsIgnoreCase("ALL")) {
				stmt.setString(g1, nEqptType);
				g1++;
			}

			ResultSet rs = stmt.executeQuery();
			String str1 = "";
			int nk = 0;

			while (rs.next()) {
				str1 = "";

				if (nPara.equalsIgnoreCase("LIST")) {
					str1 = str1 + rs.getString("form_code_control");
					str1 = str1 + ":" + rs.getString("hq_name");
					str1 = str1 + ":" + rs.getString("sus_no");
					str1 = str1 + ":" + rs.getString("unit_name");
					str1 = str1 + ":" + rs.getString("prf_code");
					str1 = str1 + ":" + rs.getString("prf_group");
					str1 = str1 + ":" + rs.getString("type_of_hldg");
					str1 = str1 + ":" + rs.getString("type_of_hldg_n");
					str1 = str1 + ":" + rs.getString("type_of_eqpt");
					str1 = str1 + ":" + rs.getString("type_of_eqpt_n") + ":";
				}

				str1 = str1 + rs.getString("tothol");
				if (nPara.equalsIgnoreCase("LIST")) {
					str1 = str1 + ",";
				}
				l.add(nk, str1);
				nk = nk + 1;
			}

			if (l.equals(null) || l.size() <= 0) {
				l.add(nk, "0");
			}

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}

	public List<String> getUEValue(String nSUSNo, String nPRF, String nWE, String nPara) {
		List<String> l = new ArrayList<String>();
		Connection conn = null;

		try {
			if (nSUSNo.equalsIgnoreCase(null)) {
				nSUSNo = "ALL";
			}
			if (nPRF.equalsIgnoreCase(null)) {
				nPRF = "ALL";
			}
			if (nWE.equalsIgnoreCase(null)) {
				nWE = "ALL";
			}

			conn = dataSource.getConnection();
			String sq1 = "";
			String nrCnd = "";

			if (!nSUSNo.equalsIgnoreCase("ALL")) {
				if (nrCnd.length() > 0) {
					nrCnd = nrCnd + " and ";
				}
				nrCnd = nrCnd + " sus_no=?";
			}

			if (!nPRF.equalsIgnoreCase("ALL")) {
				if (nrCnd.length() > 0) {
					nrCnd = nrCnd + " and ";
				}
				nrCnd = nrCnd + " prf_group_code=?";
			}

			if (!nWE.equalsIgnoreCase("ALL")) {
				if (nrCnd.length() > 0) {
					nrCnd = nrCnd + " and ";
				}
				nrCnd = nrCnd + " we_pe=?";
			}

			if (nPara.equalsIgnoreCase("LIST")) {
				if (nSUSNo.equalsIgnoreCase("ALL")) {
					sq1 = "select 'ALL' as sus_no,p.prf_code,p.we_pe,sum(p.totue) as totuh from (";
					sq1 = sq1
							+ " select 'ALL' as sus_no,prf_group_code as prf_code,(case when upper(type)='CES' then 'CES' else 'MAIN' end) as we_pe,total_ue_qty as totue from mms_ue_mview ";
				} else {
					sq1 = "select p.sus_no,p.prf_code,p.we_pe,sum(p.totue) as totuh from (";
					sq1 = sq1
							+ " select sus_no,prf_group_code as prf_code,(case when upper(type)='CES' then 'CES' else 'MAIN' end) as we_pe,total_ue_qty as totue from mms_ue_mview ";
				}
			} else {
				if (nSUSNo.equalsIgnoreCase("ALL")) {
					sq1 = "select 'ALL' as sus_no,p.prf_code,p.we_pe,sum(p.totue) as totuh from (";
					sq1 = sq1
							+ " select 'ALL' as sus_no,prf_group_code as prf_code,(case when upper(type)='CES' then 'CES' else 'MAIN' end) as we_pe,total_ue_qty as totue from mms_ue_mview ";
				} else {
					sq1 = "select p.sus_no,p.prf_code,p.we_pe,sum(p.totue) as totuh from (";
					sq1 = sq1
							+ " select sus_no,prf_group_code as prf_code,(case when upper(type)='CES' then 'CES' else 'MAIN' end) as we_pe,total_ue_qty as totue from mms_ue_mview ";
				}
			}

			if (nrCnd.length() > 0) {
				sq1 = sq1 + " where " + nrCnd;
			}
			sq1 = sq1 + ") p";

			if (nPara.equalsIgnoreCase("LIST")) {
				if (nSUSNo.equalsIgnoreCase("ALL")) {
					sq1 = sq1 + " group by p.prf_code,p.prf_group,p.we_pe";
				} else {
					sq1 = sq1 + " group by p.sus_no,p.prf_code,p.prf_group,p.we_pe";
				}
			} else {
				if (nSUSNo.equalsIgnoreCase("ALL")) {
					sq1 = sq1 + " group by p.prf_code,p.we_pe";
				} else {
					sq1 = sq1 + " group by p.sus_no,p.prf_code,p.we_pe";
				}
			}

			if (nPara.equalsIgnoreCase("LIST")) {
				if (nSUSNo.equalsIgnoreCase("ALL")) {
					sq1 = sq1 + " order by p.prf_group,p.we_pe";
				} else {
					sq1 = sq1 + " order by p.sus_no,p.prf_code,p.prf_group,p.we_pe";
				}
			}

			PreparedStatement stmt = conn.prepareStatement(sq1);

			int g1 = 1;
			if (!nSUSNo.equalsIgnoreCase("ALL")) {
				stmt.setString(g1, nSUSNo);
				g1++;
			}

			if (!nPRF.equalsIgnoreCase("ALL")) {
				stmt.setString(g1, nPRF);
				g1++;
			}

			if (!nWE.equalsIgnoreCase("ALL")) {
				stmt.setString(g1, nWE);
				g1++;
			}

			ResultSet rs = stmt.executeQuery();

			String str1 = "";
			int nk = 0;

			while (rs.next()) {
				str1 = "";
				if (nPara.equalsIgnoreCase("LIST")) {
					str1 = str1 + rs.getString("sus_no");
					str1 = str1 + ":" + rs.getString("prf_code");
					str1 = str1 + ":" + rs.getString("we_pe") + ":";
				}
				str1 = str1 + rs.getString("totuh");
				if (nPara.equalsIgnoreCase("LIST")) {
					str1 = str1 + ",";
				}
				l.add(nk, str1);
				nk = nk + 1;
			}

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}

	public List<String> getMMSMlccsList(String census, String nPara) {
		List<String> l = new ArrayList<String>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sq1 = "";
			String ncond = "";

			sq1 = "select cos_sec,auth_lett_no,auth_date,prf_code,item_code,cat_part_no,au,brief_desc,item_status,"
					+ "item_category,class_category,origin_country,manuf_agency,ahsp_agency,nato_stk_no,"
					+ "induc_year,spl_remarks,dte_category,dte_eqpt_category,eqpt_priority,nomen,cost,digest_category,def_cat_no_dcan,census_no"
					+ " from mms_tb_mlccs_mstr_detl";

			if (nPara.equals("NOMEN")) {
				ncond = " where nomen=?";
				sq1 = sq1 + ncond;
			} else if (nPara.equals("CENSUS")) {
				ncond = " where census_no=?";
				sq1 = sq1 + ncond;
			} else if (nPara.equals("PRF")) {
				ncond = " where prf_code=?";
				sq1 = sq1 + ncond;
			} else if (nPara.equals("PRF1")) {
				ncond = " where prf_group=?";
				sq1 = sq1 + ncond;
			} else if (nPara.equals("ID")) {
				ncond = " where id=?";
				sq1 = sq1 + ncond;
			}

			PreparedStatement stmt = conn.prepareStatement(sq1);

			if (nPara.equals("NOMEN") || nPara.equals("CENSUS") || nPara.equals("PRF") || nPara.equals("PRF1")) {
				stmt.setString(1, census);
			}

			if (nPara.equals("ID")) {
				stmt.setInt(1, Integer.parseInt(census));
			}

			ResultSet rs = stmt.executeQuery();

			String str1 = "";
			while (rs.next()) {
				str1 = str1 + rs.getString("cos_sec");
				str1 = str1 + ":" + rs.getString("auth_lett_no");
				str1 = str1 + ":" + rs.getString("auth_date");
				str1 = str1 + ":" + rs.getString("prf_code");
				str1 = str1 + ":" + rs.getString("item_code");
				str1 = str1 + ":" + rs.getString("cat_part_no");
				str1 = str1 + ":" + rs.getString("au");
				str1 = str1 + ":" + rs.getString("brief_desc");// 7
				str1 = str1 + ":" + rs.getString("item_status");// 8
				str1 = str1 + ":" + rs.getString("item_category");// 9
				str1 = str1 + ":" + rs.getString("class_category");// 10
				str1 = str1 + ":" + rs.getString("origin_country");// 11
				str1 = str1 + ":" + rs.getString("manuf_agency");// 12
				str1 = str1 + ":" + rs.getString("ahsp_agency");// 13
				str1 = str1 + ":" + rs.getString("nato_stk_no");// 14
				str1 = str1 + ":" + rs.getString("induc_year");// 15
				str1 = str1 + ":" + rs.getString("spl_remarks");// 16
				str1 = str1 + ":" + rs.getString("dte_category");// 17
				str1 = str1 + ":" + rs.getString("dte_eqpt_category");// 18
				str1 = str1 + ":" + rs.getString("eqpt_priority");// 19
				str1 = str1 + ":" + rs.getString("nomen");// 20
				str1 = str1 + ":" + rs.getString("cost");// 21
				str1 = str1 + ":" + rs.getString("digest_category");// 22
				str1 = str1 + ":" + rs.getString("def_cat_no_dcan");// 23
				str1 = str1 + ":" + rs.getString("census_no");// 24
				str1 = str1 + ",";
			}
			l.add(str1);

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}

	public List<String> getMMSEncList(List<String> l, HttpSession s1) {
		String enckey = hex_asciiDao.getAlphaNumericString();
		Cipher ci = null;
		try {
			ci = hex_asciiDao.EncryptionSHA256Algo(s1, enckey);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		List<String> FList = new ArrayList<String>();
		for (int i = 0; i < l.size(); i++) {
			byte[] encCode = null;
			try {
				encCode = ci.doFinal(l.get(i).getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
			String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			FList.add(base64EncodedEncryptedCode);
		}
		if (l.size() != 0) {
			FList.add(enckey + "0fsjyg==");
		}
		return FList;
	}

	public List<String> getMMSMaxDt(String sus) {
		List<String> l = new ArrayList<String>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sq1 = "";
			String qry = "";
			if (!sus.equals("") && sus != null) {
				qry = "where sus_no=?";
			}

			sq1 = "select max(upd_date) as max_dt from mms_tv_regn_mstr " + qry;

			PreparedStatement stmt = conn.prepareStatement(sq1);
			if (!sus.equals("") && sus != null) {
				stmt.setString(1, sus);
			}
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				l.add(rs.getString("max_dt"));
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}

	// FINAL METHODS END

	public ArrayList<ArrayList<String>> getMMSPRFtListBySearch2(String nParaValue) {
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String nrSql = "";
			nrSql = "select distinct prf_group_code, prf_group from cue_tb_prf_group_mst where status_active='Active'";
			nrSql = nrSql + " and (prf_group like ? or prf_group_code like ?) order by prf_group";

			PreparedStatement stmt = conn.prepareStatement(nrSql);
			stmt.setString(1, "%" + nParaValue + "%");
			stmt.setString(2, "%" + nParaValue + "%");
			ResultSet rs = stmt.executeQuery();

			if (!rs.isBeforeFirst()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add("NIL");
				li.add(list);
			} else {
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();
					list.add(rs.getString("prf_group_code"));
					list.add(rs.getString("prf_group"));
					li.add(list);
				}
			}

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}

	public List<String> getExtendDate() {
		Connection conn = null;
		String qry = "";
		String nrStr = "";
		List<String> nrList = new ArrayList<String>();
		try {
			conn = dataSource.getConnection();
			qry = "select DATE(NOW()+ INTERVAL '90 days') as ndate";
			PreparedStatement stmt = conn.prepareStatement(qry);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				nrStr = nrStr + rs.getString("ndate");
			}
			nrList.add(nrStr);

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nrList;
	}

	public List<String> getExtendDateOnChange(String d) {
		Connection conn = null;
		String qry = "";
		String nrStr = "";
		List<String> nrList = new ArrayList<String>();
		try {
			conn = dataSource.getConnection();
			qry = "select DATE(DATE(?) + INTERVAL '90 days') as ndate";
			PreparedStatement stmt = conn.prepareStatement(qry);
			stmt.setString(1, d);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				nrStr = nrStr + rs.getString("ndate");
			}
			nrList.add(nrStr);
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nrList;
	}

	public String RegdDataTransfer(String regnSeqno, String nFSUSNo, String nFHldType, String nTSUSNo, String nTHldType,
			String nPara, String nFileName, String TFileName) {
		Connection conn = null;
		String[] tmpRegn = regnSeqno.split(":");
		try {
			conn = dataSource.getConnection();
			String sus_no = "";
			String f_sus_no = "";
			if (TFileName == "mms_tb_regn_mstr_detl" || TFileName == "mms_tb_depot_regn_mstr_detl") {
				sus_no = "sus_no";
			} else if (TFileName == "mms_tb_regn_oth_mstr") {
				sus_no = "iv_sus_no";
			}

			if (nFileName == "mms_tb_regn_mstr_detl" || nFileName == "mms_tb_depot_regn_mstr_detl") {
				f_sus_no = "sus_no";
			} else if (nFileName == "mms_tb_regn_oth_mstr") {
				f_sus_no = "iv_sus_no";
			}

			String nrSql = "";
			String nrSql1 = "";
			nrSql = "	insert into " + TFileName + "(" + sus_no
					+ ",prf_code,census_no,type_of_hldg,type_of_eqpt,eqpt_regn_no,regn_seq_no,"
					+ "	from_sus_no,from_form_code,from_tr_date,to_sus_no,to_form_code,to_tr_date,service_status,spl_remarks,remarks,data_cr_by,"
					+ "	data_cr_date,data_upd_by,data_upd_date,data_app_by,data_app_date,op_status,tfr_status) "
					+ "	select " + f_sus_no
					+ ",prf_code,census_no,type_of_hldg,type_of_eqpt,eqpt_regn_no,regn_seq_no,from_sus_no,from_form_code,from_tr_date,to_sus_no,to_form_code,to_tr_date,"
					+ "	service_status,spl_remarks,remarks,data_cr_by,data_cr_date,data_upd_by,data_upd_date,data_app_by,data_app_date,op_status,"
					+ "	tfr_status from " + nFileName + "";

			nrSql = nrSql + " where regn_seq_no=?";

			nrSql1 = "delete from " + nFileName + "";
			nrSql1 = nrSql1 + " where regn_seq_no=?";

			for (int i = 0; i < tmpRegn.length; i++) {
				PreparedStatement stmt = conn.prepareStatement(nrSql);
				String nCnd = tmpRegn[i];
				stmt.setString(1, nCnd);
				stmt.executeUpdate();

				PreparedStatement stmt2 = conn.prepareStatement(nrSql1);
				stmt2.setString(1, nCnd);
				stmt2.executeUpdate();
			}

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getRegdFileName(String nPara) {
		Connection conn = null;
		String fileName = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			String sql1 = "";
			sql1 = "select versionno  as filecode from mms_domain_values where codevalue=?";
			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, nPara);
			ResultSet rs = stmt.executeQuery();
			String fn = "";
			if (rs.next()) {
				fn = rs.getString("filecode");
			}
			rs.close();
			stmt.close();
			conn.close();
			if (fn.equalsIgnoreCase("1")) {
				fileName = "mms_tb_regn_mstr_detl";
			}
			if (fn.equalsIgnoreCase("2")) {
				fileName = "mms_tb_regn_oth_mstr";
			}
			if (fn.equalsIgnoreCase("3")) {
				fileName = "mms_tb_depot_regn_mstr_detl";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fileName;
	}

	/*
	 * public ArrayList<ArrayList<String>> getMMSRData(int userid) {
	 * ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
	 * Connection conn = null; try{ conn = dataSource.getConnection(); String
	 * sql1=""; String sql2=""; String nSUS=""; String nRoleType=""; String
	 * nRoleAccess=""; String nRoleAccess1=""; String nRoleAccess2=""; String
	 * nCnd=""; String nfmncode=""; String narmcode="";
	 * 
	 * sql1 =
	 * "select a.userid,a.username,a.user_sus_no,a.user_formation_no,a.user_arm_code,d.role_id,c.role_type,c.access_lvl,c.sub_access_lvl,c.staff_lvl from"
	 * ; sql1 = sql1 +
	 * " (select distinct userid,username,user_sus_no,user_formation_no,user_arm_code from logininformation where userid=?) a"
	 * ; sql1 = sql1 +
	 * " left join (select distinct user_id,role_id from userroleinformation) d on a.userid=d.user_id"
	 * ; sql1 = sql1 +
	 * " left join (select role_id,role_type,access_lvl,sub_access_lvl,staff_lvl from roleinformation) c on d.role_id=c.role_id"
	 * ;
	 * 
	 * PreparedStatement stmt = conn.prepareStatement(sql1); stmt.setInt(1, userid);
	 * ResultSet rs = stmt.executeQuery();
	 * 
	 * while(rs.next()){ nSUS=rs.getString("user_sus_no");
	 * nRoleAccess=rs.getString("access_lvl"); nRoleType=rs.getString("role_type");
	 * if (nRoleType==null || nRoleType.equals(null)) { nRoleType="DEO"; }
	 * nRoleAccess1=rs.getString("sub_access_lvl");
	 * nRoleAccess2=rs.getString("staff_lvl");
	 * nfmncode=rs.getString("user_formation_no");
	 * narmcode=rs.getString("user_arm_code"); }
	 * 
	 * rs.close(); stmt.close();
	 * 
	 * if(nRoleAccess.equalsIgnoreCase("UNIT")){ nCnd=" and a.sus_no=?";//nSUS }
	 * if(nRoleAccess.equalsIgnoreCase("MISO")){ nCnd=""; }
	 * if(nRoleAccess.equalsIgnoreCase("FORMATION")){
	 * if(nRoleAccess1.equalsIgnoreCase("COMMAND")){
	 * nCnd=" and substring(a.form_code_control,1,1)=?"; }
	 * if(nRoleAccess1.equalsIgnoreCase("CORPS")){
	 * nCnd=" and substring(a.form_code_control,1,3)=?"; }
	 * if(nRoleAccess1.equalsIgnoreCase("DIVISION")){
	 * nCnd=" and substring(a.form_code_control,1,6)=?"; }
	 * if(nRoleAccess1.equalsIgnoreCase("BRIGADE")){
	 * nCnd=" and a.form_code_control=?"; } }
	 * if(nRoleAccess.equalsIgnoreCase("LINE_DTE")){
	 * nCnd=" and a.arm_code=?";//narmcode }
	 * 
	 * sql2 =
	 * "select distinct upper(COALESCE(b.level_in_hierarchy,'UNIT')) as lvl,a.sus_no,a.unit_name,a.form_code_control,a.arm_code from tb_orbat_unt_dtl a left join tb_orbat_codesform b on a.sus_no=b.sus_no where a.status_sus_no='Active' "
	 * ; sql2 = sql2 + nCnd; sql2 = sql2 +" order by a.form_code_control,a.sus_no";
	 * 
	 * PreparedStatement stmt2 = conn.prepareStatement(sql2);
	 * 
	 * if(nRoleAccess.equalsIgnoreCase("UNIT")){ stmt2.setString(1, nSUS); }
	 * if(nRoleAccess.equalsIgnoreCase("MISO")){ }
	 * if(nRoleAccess.equalsIgnoreCase("FORMATION")){
	 * if(nRoleAccess1.equalsIgnoreCase("COMMAND")){ stmt2.setString(1,
	 * nfmncode.substring(0, 1)); } if(nRoleAccess1.equalsIgnoreCase("CORPS")){
	 * stmt2.setString(1, nfmncode.substring(0, 3)); }
	 * if(nRoleAccess1.equalsIgnoreCase("DIVISION")){ stmt2.setString(1,
	 * nfmncode.substring(0, 6)); } if(nRoleAccess1.equalsIgnoreCase("BRIGADE")){
	 * stmt2.setString(1, nfmncode); } }
	 * if(nRoleAccess.equalsIgnoreCase("LINE_DTE")){ stmt2.setString(1, narmcode); }
	 * 
	 * ResultSet rs2 = stmt2.executeQuery();
	 * 
	 * if(!rs2.isBeforeFirst()) { ArrayList<String> list = new ArrayList<String>();
	 * list.add("NIL"); li.add(list); }else{ while(rs2.next()){ ArrayList<String>
	 * list = new ArrayList<String>(); if(nRoleAccess.equalsIgnoreCase("UNIT")){
	 * list.add("1"); list.add("UNIT"); } if(nRoleAccess.equalsIgnoreCase("MISO")){
	 * list.add("9"); list.add("MISO"); }
	 * if(nRoleAccess.equalsIgnoreCase("FORMATION")){
	 * if(nRoleAccess1.equalsIgnoreCase("COMMAND")){ list.add("5");
	 * list.add("COMMAND"); } if(nRoleAccess1.equalsIgnoreCase("CORPS")){
	 * list.add("4"); list.add("CORPS"); }
	 * if(nRoleAccess1.equalsIgnoreCase("DIVISION")){ list.add("3");
	 * list.add("DIVISION"); } if(nRoleAccess1.equalsIgnoreCase("BRIGADE")){
	 * list.add("2"); list.add("BRIGADE"); } }
	 * if(nRoleAccess.equalsIgnoreCase("LINE_DTE")){ list.add("6");
	 * list.add("LINE_DTE"); } list.add(rs2.getString("sus_no"));
	 * list.add(rs2.getString("unit_name"));
	 * list.add(rs2.getString("form_code_control"));
	 * list.add(rs2.getString("arm_code")); list.add(rs2.getString("lvl"));
	 * list.add(nRoleAccess.toUpperCase()); list.add(nRoleType.toUpperCase());
	 * li.add(list); } } rs2.close(); stmt2.close(); conn.close();
	 * 
	 * }catch(SQLException e){ e.printStackTrace(); }
	 * 
	 * return li; }
	 */

	public ArrayList<ArrayList<String>> getMMSRData(HttpSession sessionA) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();
		String roleFormationNo = sessionA.getAttribute("roleFormationNo").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		String roleArmCode = sessionA.getAttribute("roleArmCode").toString();
		String roleType = sessionA.getAttribute("roleType").toString();
		String unit_name = "";
		if (!roleSusNo.equals("")) {
			unit_name = getActiveUnitNameFromSusNo_Without_Enc(roleSusNo, sessionA).get(0);
		}

		ArrayList<ArrayList<String>> l1 = new ArrayList<ArrayList<String>>();
		ArrayList<String> list = new ArrayList<String>();
		if (roleAccess.equalsIgnoreCase("UNIT")) {
			list.add("1");
			list.add("UNIT");
		}
		if (roleAccess.equalsIgnoreCase("MISO")) {
			list.add("9");
			list.add("MISO");
		}
		if(roleAccess.equals("HeadQuarter")) {
			list.add("7");
			list.add("HeadQuarter");
		}
		if (roleAccess.equalsIgnoreCase("FORMATION")) {
			if (roleSubAccess.equalsIgnoreCase("COMMAND")) {
				list.add("5");
				list.add("COMMAND");
			}
			if (roleSubAccess.equalsIgnoreCase("CORPS")) {
				list.add("4");
				list.add("CORPS");
			}
			if (roleSubAccess.equalsIgnoreCase("DIVISION")) {
				list.add("3");
				list.add("DIVISION");
			}
			if (roleSubAccess.equalsIgnoreCase("BRIGADE")) {
				list.add("2");
				list.add("BRIGADE");
			}
		}
		if (roleAccess.equalsIgnoreCase("LINE_DTE")) {
			list.add("6");
			list.add("LINE_DTE");
		}
		list.add(roleSusNo);
		list.add(unit_name);
		list.add(roleFormationNo);
		list.add(roleArmCode);
		if (roleAccess.equals("FORMATION")) {
			list.add(roleSubAccess);
		} else {
			list.add(roleAccess);
		}
		list.add(roleAccess.toUpperCase());
		list.add(roleType.toUpperCase());
		l1.add(list);
		return l1;
	}

	public List<String> getActiveUnitNameFromSusNo_Without_Enc(String sus_no, HttpSession sessionA) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct unit_name from Orbat_Unt_Dtl where sus_no=:sus_no and status_sus_no='Active'");
		q.setParameter("sus_no", sus_no);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		if(list.size() == 0) {
			list.add("");
		}
		return list;
	}

	public ArrayList<ArrayList<String>> getMMSRAccess(int userid) {
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql1 = "";
			//String sql2 = "";
			String nSUS = "";
			//String nRoleType = "";
			String nRoleAccess = "";
			String nRoleAccess1 = "";
			//String nRoleAccess2 = "";
		//	String nCnd = "";
			String nfmncode = "";
			String narmcode = "";
		//	String nrUAccess = "";
			sql1 = "select a.userid,a.username,a.user_sus_no,a.user_formation_no,a.user_arm_code,d.role_id,c.role_type,c.access_lvl,c.sub_access_lvl,c.staff_lvl from";
			sql1 = sql1
					+ " (select distinct userid,username,user_sus_no,user_formation_no,user_arm_code from logininformation where userid=?) a";
			sql1 = sql1
					+ " left join (select distinct user_id,role_id from userroleinformation) d on a.userid=d.user_id";
			sql1 = sql1
					+ " left join (select role_id,role_type,access_lvl,sub_access_lvl,staff_lvl from roleinformation) c on d.role_id=c.role_id";

			PreparedStatement stmt = conn.prepareStatement(sql1);
			stmt.setInt(1, userid);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				nSUS = rs.getString("user_sus_no");
			//	nRoleType = rs.getString("role_type");
				nRoleAccess = rs.getString("access_lvl");
				nRoleAccess1 = rs.getString("sub_access_lvl");
			//	nRoleAccess2 = rs.getString("staff_lvl");
				nfmncode = rs.getString("user_formation_no");
				narmcode = rs.getString("user_arm_code");
			}
			rs.close();
			stmt.close();
			if (nRoleAccess.equalsIgnoreCase("UNIT")) {
				ArrayList<String> list = new ArrayList<String>();
				list.add("U");
				list.add("SUS_NO");
				list.add(nSUS);
				li.add(list);
			}
			if (nRoleAccess.equalsIgnoreCase("MISO")) {
				ArrayList<String> list = new ArrayList<String>();
				list.add("M");
				list.add("ALL");
				list.add("ALL");
				li.add(list);
			}
			if (nRoleAccess.equalsIgnoreCase("FORMATION")) {
				ArrayList<String> list = new ArrayList<String>();
				list.add("F");
				if (nRoleAccess1.equalsIgnoreCase("COMMAND")) {
					list.add("substring(form_code_control,1,1)");
					list.add(nfmncode.substring(0, 1));
				}
				if (nRoleAccess1.equalsIgnoreCase("CORPS")) {
					list.add("substring(form_code_control,1,3)");
					list.add(nfmncode.substring(0, 1));
				}
				if (nRoleAccess1.equalsIgnoreCase("DIVISION")) {
					list.add("substring(form_code_control,1,6)");
					list.add(nfmncode.substring(0, 1));
				}
				if (nRoleAccess1.equalsIgnoreCase("BRIGADE")) {
					list.add("form_code_control");
					list.add(nfmncode.substring(0, 1));
				}
				li.add(list);
			}
			if (nRoleAccess.equalsIgnoreCase("LINE_DTE")) {
				ArrayList<String> list = new ArrayList<String>();
				list.add("L");
				list.add("ARM_CODE");
				list.add(narmcode);
				li.add(list);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}

	//////////////////////////////////////
	public List<String> getTypeofHldgBySUSNo_frmtbl(String nParaValue) {
		List<String> l = new ArrayList<String>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String sql = "";
			PreparedStatement stmt = null;

			sql = "SELECT DISTINCT type_of_hldg,D.label as type_of_hldg_n  FROM (\r\n"
					+ "select type_of_hldg from mms_tb_regn_mstr_detl where sus_no= ? \r\n" + "UNION ALL\r\n"
					+ "select type_of_hldg from mms_tb_regn_oth_mstr where to_sus_no= ?\r\n" + "UNION ALL\r\n"
					+ "select type_of_hldg from mms_tb_depot_regn_mstr_detl where sus_no= ?) P LEFT JOIN \r\n"
					+ "mms_domain_values D on  P.type_of_hldg = D.codevalue and D.domainid='TYPEOFHOLDING'  ";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, nParaValue);
			stmt.setString(2, nParaValue);
			stmt.setString(3, nParaValue);
			ResultSet rs = stmt.executeQuery();

			String str1 = "";
			while (rs.next()) {
				str1 = str1 + rs.getString("type_of_hldg");
				str1 = str1 + ":" + rs.getString("type_of_hldg_n");
				str1 = str1 + ",";
			}
			l.add(str1);
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}
	
	
	public ArrayList<ArrayList<String>> getUnitDetailsList() {
		ArrayList<ArrayList<String>> li = new ArrayList<ArrayList<String>>();

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			String nrSql = "";
			nrSql = "select distinct sus_no, unit_name from tb_orbat_unt_dtl where sus_no in (select sus_no from tb_orbat_codesform where level_in_hierarchy ='Unit') and status_sus_no = 'Active'";		

			PreparedStatement stmt = conn.prepareStatement(nrSql);
			ResultSet rs = stmt.executeQuery();

			if (!rs.isBeforeFirst()) {
				ArrayList<String> list = new ArrayList<String>();
				list.add("NIL");
				li.add(list);
			} else {
				while (rs.next()) {
					ArrayList<String> list = new ArrayList<String>();				
					list.add(rs.getString("sus_no"));
					list.add(rs.getString("unit_name"));
					li.add(list);
				}
			}

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}
}