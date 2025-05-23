package com.dao.WorkOrder;

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
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.models.assets.It_Asset_Peripherals;
import com.persistance.util.HibernateUtil;

public class WOPeripheral_Asset_DaoImpl implements WOPeripheral_Asset_Dao {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();

	public boolean checkIsIntegerValue(String Search) {
		return Search.matches("[0-9]+");
	}

	public List<Map<String, Object>> Peripheral_Asset_ReportDataListDetails(int startPage, String pageLength,
			String Search, String orderColunm, String orderType, String asset_type, String make_name, String model_name,
			String status, HttpSession session)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		if (pageLength.equals("-1")) {
			pageLength = "ALL";
		}

		String SearchValue = GenerateQueryWhereClause_SQL(Search, asset_type, make_name, model_name, status, session);
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		String WK_Status = "";
		String sus_no = "";

		if (status != null && !status.equals("")) {
			WK_Status += "and cast(wo.wk_generated_status as character varying)  = ? and wo.category = 2";
		}

		try {
			conn = dataSource.getConnection();

			q = "select distinct ass.assets_name,make.make_name,model.model_name,cp.assets_type ,cp.make_name as mk_name,cp.model_name as md_name,\r\n"
					+ "cp.model_no,cp.machine_no,hw.type_of_hw,cp.year_of_proc,cp.id,COALESCE(wo.id,'0') as wo_id,wo.wk_generated_status\r\n"
					+ "from it_asset_peripherals cp\r\n"
					+ "inner join tb_mstr_assets ass on ass.id = cp.assets_type and ass.category = 2 \r\n"
					+ "inner join tb_mstr_make make on make.id = cp.make_name and  make.assets_name = cp.assets_type\r\n"
					+ "inner join tb_mstr_model model on model.id = cp.model_name and model.make_name = cp.make_name\r\n"
					+ "inner join t_domain_value t1 on t1.codevalue = cp.s_state and t1.domainid='SERVICE_CAT'  \r\n"
					+ "inner join t_domain_value t2 on t2.codevalue = cast(cp.unserviceable_state as char) and t2.domainid='UNSERVICEABLE_STATE'  \r\n"
					+ "inner join tb_mstr_type_of_hw hw on cp.type_of_hw = hw.id and hw.peripheral_type = cp.assets_type\r\n"
					+ "left join tb_it_asset_work_order wo on cp.machine_no = wo.machine_number and wo.p_id = cp.id   \r\n"
					+ WK_Status
					+ "where cp.status = 1  and cp.unserviceable_state in ('2','3') and cp.s_state ='2'   \r\n" + sus_no + SearchValue 
					+ " ORDER BY " + orderColunm + " " + orderType + " limit " + pageLength + " OFFSET " + startPage;

			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, asset_type, make_name, model_name, status, session);
			
			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();

				String print_val = "0";

				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
				String Checkbox = "<input class='nrCheckBox_per' type='checkbox' id='" + rs.getString("id")// 13
						+ "' name='cbox_per' onchange='checkbox_count(this," + rs.getString("id") + ");' />";

				String CheckboxId = "<input  type='hidden' id='id" + rs.getString("id") + "' name='id"
						+ rs.getString("id")// 14
						+ "' value='" + rs.getString("id") + "'   />";

				String chekboxaction = "";

				chekboxaction += Checkbox;
				chekboxaction += CheckboxId;

				columns.put("chekboxaction", chekboxaction);

				String asset_type1 = "<input type='text' id='asset_type" + rs.getString("id") + "' name='asset_type"
						+ rs.getString("id") + "' class='form-control autocomplete' autocomplete='off'></input>";

				columns.put("asset_type", asset_type1);

				String Defects = "<input type='text' id='Defects" + rs.getString("id") + "' name='Defects"
						+ rs.getString("id") + "' class='form-control autocomplete' autocomplete='off'></input>";

				columns.put("Defects", Defects);

				String remarks = "<input type='text' id='remarks" + rs.getString("id") + "' name='remarks"
						+ rs.getString("id") + "' class='form-control autocomplete' autocomplete='off'></input>";

				columns.put("remarks", remarks);

				String val = rs.getString("wk_generated_status");
				if (val == "null" || val == null || val.equals(null)) {
					print_val = "0";
				} else {
					print_val = rs.getString("wk_generated_status");
				}
				String f = "";
				String f1 = "";

				String add = "onclick=\"  if (confirm('Are You Sure You Want to Generate Computing Work Order ?') ){EditData("
						+ rs.getString("id") + ")}else{ return false;}\"";
				f = "<i class='fa fa-external-link'" + add + " title='Open Work Order'></i>";

				columns.put("action", f);
				if (print_val == "1" || print_val.equals("1")) {

					String print = "onclick=\"  if (confirm('Are You Sure You Want to Print Computing Work Order ?') ){Downloaddata("
							+ rs.getString("id") + ",'" + rs.getString("wo_id") + "')}else{ return false;}\"";
					f1 = "<i class='action_icons action_download'" + print + " title='Print Work Order'></i>";

					columns.put("action", f + "  " + f1);
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

	public long Peripheral_Asset_TotalCountDtl(String Search, String asset_type, String make_name, String model_name,
			String status, HttpSession session) {
		String SearchValue = GenerateQueryWhereClause_SQL(Search, asset_type, make_name, model_name, status, session);
		int total = 0;
		String q = null;
		String WK_Status = "";
		String sus_no = "";
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		if (status != null && !status.equals("")) {
			WK_Status += "and cast(wo.wk_generated_status as character varying)  = ? and wo.category = 2";
		}

		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			q = "select count(app1.*) from ("
					+ "select distinct ass.assets_name,make.make_name,model.model_name,cp.assets_type ,cp.make_name as mk_name,cp.model_name as md_name,\r\n"
					+ "cp.model_no,cp.machine_no,hw.type_of_hw,cp.year_of_proc,cp.id,COALESCE(wo.id,'0') as wo_id,wo.wk_generated_status\r\n"
					+ "from it_asset_peripherals cp\r\n"
					+ "inner join tb_mstr_assets ass on ass.id = cp.assets_type and ass.category = 2 \r\n"
					+ "inner join tb_mstr_make make on make.id = cp.make_name and  make.assets_name = cp.assets_type\r\n"
					+ "inner join tb_mstr_model model on model.id = cp.model_name and model.make_name = cp.make_name\r\n"
					+ "inner join t_domain_value t1 on t1.codevalue = cp.s_state and t1.domainid='SERVICE_CAT'  \r\n"
					+ "inner join t_domain_value t2 on t2.codevalue = cast(cp.unserviceable_state as char) and t2.domainid='UNSERVICEABLE_STATE'  \r\n"
					+ "inner join tb_mstr_type_of_hw hw on cp.type_of_hw = hw.id and hw.peripheral_type = cp.assets_type\r\n"
					+ "left join tb_it_asset_work_order wo on cp.machine_no = wo.machine_number and wo.p_id = cp.id  \r\n"
					+ WK_Status
					+ "where cp.status = 1  and cp.unserviceable_state in ('2','3') and cp.s_state ='2'  \r\n" + sus_no
					+ SearchValue + ") app1";
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL(stmt, Search, asset_type, make_name, model_name, status, session);

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

	public String GenerateQueryWhereClause_SQL(String Search, String asset_type, String make_name, String model_name,
			String status, HttpSession sessionUserId) {
		String SearchValue = "";

		if (!Search.equals("")) {
			Search = Search.toLowerCase();
			SearchValue = " and ( ";
			SearchValue += " cast(ass.assets_name as character varying) like ?  or  "
					+ " cast(make.make_name as character varying) like ? or cast(model.model_name as character varying) like ? or "
					+ " cast(cp.model_no as character varying) like ? or cast(cp.machine_no as character varying) like ? or "
					+ " cast(hw.type_of_hw as character varying) like ? or cast(cp.year_of_proc as character varying) like ? )";
		}

		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt, String Search, String asset_type,
			String make_name, String model_name, String status, HttpSession sessionUserId) {
		int flag = 0;
		try {

			if (!Search.equals("")) {

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

			}

		} catch (Exception e) {
		}
		return stmt;
	}

	public It_Asset_Peripherals getPeripheralAssesByid(int id) {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {
			It_Asset_Peripherals updateid = (It_Asset_Peripherals) sessionHQL.get(It_Asset_Peripherals.class, id);
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

	public ArrayList<ArrayList<String>> GetPeripheral_Asset_PDF(String p_id, String wo_id) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select \r\n" + "COALESCE(w.wksp_sus_no,'') as wksp_sus_no,\r\n"
					+ "COALESCE(w.wksp_unit_name,'') as unit_name,\r\n" + "COALESCE(w.wo_no,'') as wo_no, \r\n"
					+ "COALESCE(ltrim(TO_CHAR(w.wo_dt,'dd-MON-yyyy'),'0'),'') as wo_dt,\r\n"
					+ "COALESCE(ass.assets_name,'') as assets_name,\r\n"
					+ "COALESCE(make.make_name,'') as make_name,\r\n"
					+ "COALESCE(model.model_name,'') as model_name,\r\n"
					+ "COALESCE(w.machine_number,'') as machine_number,\r\n"
					+ "COALESCE(ltrim(TO_CHAR(cp.proc_date,'dd-MON-yyyy'),'0'),'')  as proc_date,\r\n"
					+ "COALESCE(ltrim(TO_CHAR(w.dt_eqpt_reqd_fwd_wksp,'dd-MON-yyyy'),'0'),'')  as dt_eqpt_reqd_fwd_wksp,\r\n"
					+ "COALESCE(w.defect_obs,'') as defect_obs\r\n" + "from tb_it_asset_work_order w\r\n"
					+ "inner join it_asset_peripherals cp on cp.machine_no = w.machine_number and cp.status = 1  and cp.unserviceable_state in ('2','3') and cp.s_state ='2' \r\n"
					+ "---inner join tb_orbat_unt_dtl miso on  miso.sus_no = w.wksp_sus_no\r\n"
					+ "left join tb_mstr_assets ass on ass.id = cp.assets_type and ass.category = 2 \r\n"
					+ "left join tb_mstr_make make on make.id = cp.make_name and  make.assets_name = cp.assets_type\r\n"
					+ "left join tb_mstr_model model on model.id = cp.model_name and model.make_name = cp.make_name\r\n"
					+ "left join t_domain_value t1 on t1.codevalue = cp.s_state and t1.domainid='SERVICE_CAT'  \r\n"
					+ "left join t_domain_value t2 on t2.codevalue = cast(cp.unserviceable_state as char) and t2.domainid='UNSERVICEABLE_STATE' "
					+ "where w.p_id = ? and w.id = ? ";

			stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(p_id));
			stmt.setInt(2, Integer.parseInt(wo_id));
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("wksp_sus_no"));// 0
				list.add(rs.getString("unit_name"));// 1
				list.add(rs.getString("wo_no"));// 2
				list.add(rs.getString("wo_dt"));// 3
				list.add(rs.getString("assets_name"));// 4
				list.add(rs.getString("make_name"));// 5
				list.add(rs.getString("model_name"));// 6
				list.add(rs.getString("machine_number"));// 7
				list.add(rs.getString("proc_date"));// 8
				list.add(rs.getString("dt_eqpt_reqd_fwd_wksp"));// 9
				list.add(rs.getString("defect_obs"));// 10
				alist.add(list);

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
		return alist;
	}

	/*
	 * -------------------------------- Start pop up
	 * --------------------------------------------
	 */

	public List<Map<String, Object>> Pop_UP_Peripheral_Asset_History_ReportDataList(int startPage, String pageLength,
			String Search, String orderColunm, String orderType, String assets_type, String wo_no, String wo_dt,
			String dt_eqpt_reqd_fwd_wksp, String wksp_unit_name, String defects_obs, HttpSession session)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		if (pageLength.equals("-1")) {
			pageLength = "ALL";
		}

		String SearchValue = GenerateQueryWhereClause_SQL_History(Search, assets_type, wo_no, wo_dt,
				dt_eqpt_reqd_fwd_wksp, wksp_unit_name, defects_obs, session);

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			q = "select pa.id as pa_id,pa.wo_no,tmma.maintenance,\r\n"
					+ "		 ltrim(TO_CHAR(pa.wo_dt,'DD-MON-YYYY'),'0') as wo_dt,\r\n"
					+ "		 ltrim(TO_CHAR(pa.dt_eqpt_reqd_fwd_wksp,'DD-MON-YYYY'),'0') as dt_eqpt_reqd_fwd_wksp,\r\n"
					+ "		 COALESCE(ass_type.assets_name,'') as assets_name, \r\n"
					+ "		 COUNT(ch.p_id) AS count_p_id ,\r\n"
					+ " 		 COALESCE(ch.defects_obs,'') as defects_obs\r\n" + "from tb_peripheral_wo_ch ch\r\n"
					+ "inner join  tb_it_computing_wo_p pa on pa.id=ch.p_id\r\n"
					+ "inner join it_asset_peripherals ass_m on ass_m.id=ch.asset_type\r\n"
					+ "inner join tb_mstr_assets ass_type on ass_m.assets_type = ass_type.id " 
					+ "INNER JOIN tb_mstr_maintenance_agency tmma ON cast(tmma.id as VARCHAR) = PA.WKSP"
					+ SearchValue
					+ " GROUP BY pa.id,pa.wo_no,tmma.maintenance,wo_dt,dt_eqpt_reqd_fwd_wksp,assets_name,defects_obs "
					+ "" + " ORDER BY " + orderColunm + " " + orderType + " limit " + pageLength + " OFFSET "
					+ startPage + "";
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL_History(stmt, Search, assets_type, wo_no, wo_dt, dt_eqpt_reqd_fwd_wksp,
					wksp_unit_name, defects_obs, session);

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
                  String EncryptedPk = new String(Base64.encodeBase64( c.doFinal(rs.getString("pa_id").toString().getBytes())));
                  String id = rs.getString("pa_id").toString();
                  String count_p_id = rs.getString("count_p_id").toString();
				
				  String historyButtonId = "historyButton_" + count_p_id;
                 String printButtonId = "printButton_" + id;
                 String historyButton = "<i id='" + historyButtonId + "' class='fa fa-eye PERIPHERAL_history' data-toggle='modal' data-target='#myHistoryModal_per' data-encrypted-pk='" + EncryptedPk + "'></i>";
                 String printButton = "<i id='" + printButtonId + "' class='action_icons action_download PERIPHERAL' title='Download Work Order'"+"data-encrypted-pk='" + EncryptedPk + "'     ></i>";	          
                 String f1 = printButton;
                 String f = historyButton;
				columns.put("history", f);
				columns.put("action", f1);
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

	public long Pop_UP_Peripheral_Asset_History_List_TotalCount(String Search, String assets_type, String wo_no,
			String wo_dt, String dt_eqpt_reqd_fwd_wksp, String wksp_unit_name, String defects_obs,
			HttpSession sessionUserId) {
		String SearchValue = GenerateQueryWhereClause_SQL_History(Search, assets_type, wo_no, wo_dt,
				dt_eqpt_reqd_fwd_wksp, wksp_unit_name, defects_obs, sessionUserId);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			q = "select count(*) from(select pa.id as pa_id,pa.wo_no,tmma.maintenance,\r\n"
					+ "		 ltrim(TO_CHAR(pa.wo_dt,'DD-MON-YYYY'),'0') as wo_dt,\r\n"
					+ "		 ltrim(TO_CHAR(pa.dt_eqpt_reqd_fwd_wksp,'DD-MON-YYYY'),'0') as dt_eqpt_reqd_fwd_wksp,\r\n"
					+ "		 COALESCE(ass_type.assets_name,'') as assets_name, \r\n"
					+ "		 COUNT(ch.p_id) AS count_p_id ,\r\n"
					+ " 		 COALESCE(ch.defects_obs,'') as defects_obs\r\n" + "from tb_peripheral_wo_ch ch\r\n"
					+ "inner join  tb_it_computing_wo_p pa on pa.id=ch.p_id\r\n"
					+ "inner join it_asset_peripherals ass_m on ass_m.id=ch.asset_type\r\n"
					+ "inner join tb_mstr_assets ass_type on ass_m.assets_type = ass_type.id " 
					+ "INNER JOIN tb_mstr_maintenance_agency tmma ON cast(tmma.id as VARCHAR) = PA.WKSP"
					+ SearchValue
					+ " GROUP BY pa.id,pa.wo_no,tmma.maintenance,wo_dt,dt_eqpt_reqd_fwd_wksp,assets_name,defects_obs) apps ";
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL_History(stmt, Search, assets_type, wo_no, wo_dt, dt_eqpt_reqd_fwd_wksp,
					wksp_unit_name, defects_obs, sessionUserId);
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

	public String GenerateQueryWhereClause_SQL_History(String Search, String assets_type, String wo_no, String wo_dt,
			String dt_eqpt_reqd_fwd_wksp, String wksp_unit_name, String defects_obs, HttpSession session) {
		String SearchValue = "";

		if (!Search.equals("")) { // for Input Filter
			SearchValue = " where ( ";
			SearchValue += " cast(pa.wo_no as character varying) like ? OR cast(tmma.maintenance as character varying) like ? "
					+ "	OR upper(cast(pa.wksp as character varying)) like ? OR cast(ch.defects_obs as character varying) like ? OR  upper((TO_CHAR(cast(pa.wo_dt as  date),'DD-MON-YYYY'))) like ?"
					+ "	   OR  upper((TO_CHAR(cast(pa.dt_eqpt_reqd_fwd_wksp as  date),'DD-MON-YYYY'))) like ?"
					+ "	 OR cast(ass_type.assets_name as character varying) like ?  )";
		}

		if (!assets_type.equals("0")) {

			SearchValue += "  and cast(ass_m.assets_type as character varying) = ?  ";
		}

		if (!wo_no.equals("")) {

			SearchValue += " and upper(pa.wo_no ) like ? ";

		}
		if (!wo_dt.equals("")) {

			SearchValue += " and cast(pa.wo_dt as date) = cast( ? as date) ";

		}
		if (!dt_eqpt_reqd_fwd_wksp.equals("")) {

			SearchValue += " and cast(pa.dt_eqpt_reqd_fwd_wksp as date) = cast( ? as date) ";

		}
		if (!wksp_unit_name.equals("0")) {

			SearchValue += " and upper(pa.wksp ) like ? ";

		}
		if (!defects_obs.equals("")) {

			SearchValue += " and upper(ch.defects_obs ) like ? ";

		}
		

		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL_History(PreparedStatement stmt, String Search, String assets_type,
			String wo_no, String wo_dt, String dt_eqpt_reqd_fwd_wksp, String wksp_unit_name, String defects_obs,
			HttpSession session) {
		int flag = 0;
		try {

			if (!Search.equals("")) {
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
			if (!assets_type.equals("0")) {
				flag += 1;
				stmt.setString(flag, assets_type);
			}
			if (!wo_no.equals("")) {
				flag += 1;
				stmt.setString(flag, wo_no.toUpperCase() + "%");
			}
			if (!wo_dt.equals("")) {
				flag += 1;
				stmt.setString(flag, wo_dt.toUpperCase() + "%");
			}
			if (!dt_eqpt_reqd_fwd_wksp.equals("")) {
				flag += 1;
				stmt.setString(flag, dt_eqpt_reqd_fwd_wksp.toUpperCase() + "%");
			}
			if (!wksp_unit_name.equals("0")) {
				flag += 1;
				stmt.setString(flag, wksp_unit_name.toUpperCase() + "%");
			}
			if (!defects_obs.equals("")) {
				flag += 1;
				stmt.setString(flag, defects_obs.toUpperCase() + "%");
			}

		} catch (Exception e) {
		}

		return stmt;
	}

	@Override
	public List<Map<String, Object>> pop_up_detail_Peripheral_Asset_History_RecordDataList(HttpSession session,
			int p_id)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			conn = dataSource.getConnection();
			q = "SELECT ch.defects_obs, pa.wo_no FROM tb_peripheral_wo_ch ch\r\n"
					+ "INNER JOIN tb_it_computing_wo_p pa ON ch.p_id = pa.id WHERE ch.p_id  = ?  ORDER BY ch.id";
			PreparedStatement stmt = conn.prepareStatement(q);
			stmt.setInt(1, p_id);
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

	/*
	 * -------------------------------- End pop up
	 * --------------------------------------------
	 */

	public ArrayList<ArrayList<String>> getPeripheralDetails(String hid, String wksp_unit_name, String wo_dt,
			String dt_eqpt_reqd_fwd_wksp) {

		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String qry = " where ch.p_id=?";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select COALESCE(mk.make_name,'') as make_name, \r\n"
					+ "COALESCE(ass_type.assets_name,'') as assets_name, \r\n"
					+ "COALESCE(ass_m.machine_no,'') as machine_no, \r\n  " + "'1' as qty ,"
					+ "COALESCE(ch.defects_obs,'') as defects_obs, \r\n " + "COALESCE( ch.remarks,'') as remarks \r\n "
					+ " from tb_peripheral_wo_ch ch\r\n" + "inner join  tb_it_computing_wo_p p on p.id=ch.p_id\r\n"
					+ "inner join it_asset_peripherals ass_m on ass_m.id=ch.asset_type\r\n"
					+ "inner join tb_mstr_make mk on ass_m.make_name=mk.id\r\n"
					+ "inner join tb_mstr_assets ass_type on ass_m.assets_type = ass_type.id\r\n" + qry;

			stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(hid));
			int i = 0;
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				i++;
				ArrayList<String> list = new ArrayList<String>();
				list.add(String.valueOf(i));// 0
				list.add(rs.getString("make_name"));// 2
				list.add(rs.getString("machine_no"));// 3
				list.add(rs.getString("qty"));// 4
				list.add(rs.getString("defects_obs"));// 5
				list.add(rs.getString("assets_name"));// 1
				list.add(rs.getString("remarks"));// 6

				alist.add(list);

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
		return alist;
	}

	public ArrayList<ArrayList<String>> getPeripheralDetails1(String hid, String wksp_unit_name, String wo_dt,
			String dt_eqpt_reqd_fwd_wksp) {

		ArrayList<ArrayList<String>> alist1 = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";
		String qry = " where ch.p_id=?";
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select ch.p_id,\r\n" + "upper(COALESCE(tmma.maintenance,'')) as wksp_unit_name,\r\n"
					+ "COALESCE(po.wo_no,'') as wo_no,\r\n"
					+ "COALESCE(ltrim(TO_CHAR(po.wo_dt ,'dd-mm-yyyy'),'0'),'') as wo_dt,\r\n"
					+ "COALESCE(ltrim(TO_CHAR(po.dt_eqpt_reqd_fwd_wksp ,'dd-mm-yyyy'),'0'),'') as dt_eqpt_reqd_fwd_wksp \r\n"
					+ "from tb_peripheral_wo_ch ch \r\n" 
					+ "inner join tb_it_computing_wo_p po on  po.id=ch.p_id " 
					+ "INNER JOIN tb_mstr_maintenance_agency tmma ON cast(tmma.id as VARCHAR) = po.wksp"
					+ qry;

			stmt = conn.prepareStatement(q);
			stmt.setInt(1, Integer.parseInt(hid));

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				ArrayList<String> list = new ArrayList<String>();

				list.add(rs.getString("wksp_unit_name"));// 0
				list.add(rs.getString("wo_dt"));// 1
				list.add(rs.getString("dt_eqpt_reqd_fwd_wksp"));// 2
				list.add(rs.getString("wo_no"));// 3
				alist1.add(list);

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
		return alist1;
	}

	public String Update_Wo_Peripheral() {

		Connection conn = null;
		Integer out = 0;
		String q = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;
			stmt = conn
					.prepareStatement("UPDATE tb_it_computing_wo_p dummy\r\n" + "SET  sus_no=subquery.user_sus_no\r\n"
							+ "FROM (select distinct ln.user_sus_no,ln.username from tb_it_computing_wo_p wp\r\n"
							+ "inner join logininformation ln on upper(ln.username)=upper(wp.created_by)\r\n"
							+ "where sus_no is null )  AS subquery \r\n"
							+ "WHERE  upper(dummy.created_by) = upper(subquery.username)");

			out = stmt.executeUpdate();
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
		return q;

	}

}
