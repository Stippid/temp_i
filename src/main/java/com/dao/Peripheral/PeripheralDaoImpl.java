package com.dao.Peripheral;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import com.dao.Assets.computing_assets_DAOImpl;
import com.models.assets.It_Asset_Peripherals;
import com.persistance.util.HibernateUtil;

public class PeripheralDaoImpl  implements PeripheralDao{

	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	@Autowired
	computing_assets_DAOImpl n=new computing_assets_DAOImpl();



	@Override
	public List<Map<String, Object>> Search_Peripheral(int startPage, int pageLength, String Search, String orderColunm,
			String orderType, String status, String assets_type, String year_of_manufacturing, String machine_make,
			String machine_no, String from_date, String to_date, String s_state, String section,
			HttpSession sessionUserId) {

		String SearchValue = GenerateQueryWhereClause_SQL2(Search, assets_type, year_of_manufacturing, machine_make,
				machine_no, from_date, to_date, status, s_state, section, sessionUserId);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";

		try {
			String pageL = "";

			if (pageLength == -1) {
				pageL = "ALL";
			} else {
				pageL = String.valueOf(pageLength);
			}
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select ap.id,pt.assets_name,ap.s_state,th.type_of_hw,ap.year_of_proc,\r\n"
					+ "ap.year_of_manufacturing,ap.b_cost,ap.make_name,ap.machine_no,ap.remarks,sec.section,ap.created_by,ap.status as statusbtn  from it_asset_peripherals ap\r\n"
					+ "inner join  tb_mstr_section sec  on sec.id:: text = ap.section \r\n"
					+ "left join tb_mstr_assets pt on pt.id=ap.assets_type  and pt.category=2\r\n"
					+ "left join tb_mstr_type_of_hw th on th.id=ap.type_of_hw\r\n" +
					"where ap.id!=0 " + SearchValue + " ORDER BY " + orderColunm + " " + orderType + " limit " + pageL
					+ " OFFSET " + startPage;

			stmt = conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL2(stmt, Search, assets_type, year_of_manufacturing, machine_make, machine_no,
					from_date, to_date, status, s_state, section, sessionUserId);

			ResultSet rs = stmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();

			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));

				}


				String checkboxId = rs.getObject(1).toString(); // Assuming this is a valid ID
				String CheckBox = "<input class='nrCheckBox2' type='checkbox' id='" + checkboxId + "' name='cbox' data-id='" + checkboxId + "' />";
				String CheckBoxid = "<input type='hidden' id='id" + checkboxId + "' name='id" + checkboxId + "' value='" + checkboxId + "' />";

				String chekboxaction = CheckBox + CheckBoxid;

				columns.put("chekboxaction1", chekboxaction);
				//				String action = "";
				//				String editDataAttributes = "data-id='" + rs.getInt("id") + "' data-action='edit'";
				//				String deleteDataAttributes = "data-id='" + rs.getInt("id") + "' data-action='delete'";
				//				String viewDataAttributes = "data-id='" + rs.getInt("id") + "' data-action='view'";
				//				String archiveDataAttributes = "data-id='" + rs.getInt("id") + "' data-action='archive'";
				//
				//				if (!status.equals("1")) {
				//					action += "<i class='action_icons action_update' " + editDataAttributes + " title='Edit Data'></i>";
				//				}
				//				action += "<i class='action_icons action_delete' " + deleteDataAttributes + " title='Delete Data'></i>";
				//				action += "<i class='fa fa-eye ' " + viewDataAttributes + " title='View Data'></i>";
				//
				//				if (!status.equals("7")) {
				//					action += "<i class='action_icons action_archive' " + archiveDataAttributes
				//							+ " title='Archive Data'></i>";
				//				}
				String f1 = "<i class='action_icons action_update' data-id='" + rs.getInt("id") + "' title='Edit Data'></i>";
				String f2 = "<i class='action_icons action_delete' data-id='" + rs.getInt("id") + "' title='Delete Data'></i>";
				String f3 = "<i class='fa fa-eye action_view_1'  data-id='" + rs.getInt("id") + "' title='View Data'></i>";
				String f4 = "<i class='action_icons action_archive' data-id='" + rs.getInt("id") + "' title='Archive Data'></i>";

				String action = "";

				String status_all = rs.getString("statusbtn");
				String roleid = sessionUserId.getAttribute("roleid").toString();
				if (status.equals("4")) {
					if (status_all.equals("0") && roleid.equals("530")) {
						columns.put("action", f2);
						action += f2;
						action += f1;
						action += f4;
					}
					if (status_all.equals("1") && roleid.equals("530") ) {
						columns.put("action", f3);
						action += f3;
					}

					if (status_all.equals("3") && roleid.equals("530")) {
						columns.put("action", f1);
						action += f1;
					}
					if (status_all.equals("7") && roleid.equals("530")) {
						columns.put("action", f3);
						action += f3;
					}

					//for admin

					if (status_all.equals("0") && roleid.equals("1")) {
						columns.put("action", f2);
						action += f2;
						action += f1;
						action += f4;
					}
					if (status_all.equals("1") && roleid.equals("1") ) {
						columns.put("action", f3);
						action += f3;
					}

					if (status_all.equals("3") && roleid.equals("1")) {
						columns.put("action", f1);
						action += f1;
					}
					if (status_all.equals("7") && roleid.equals("1")) {
						columns.put("action", f3);
						action += f3;
					}
					// forsysadmin

					if (status_all.equals("0") && roleid.equals("14")) {
						columns.put("action", f2);
						action += f2;
						action += f1;
						action += f4;
					}
					if (status_all.equals("1") && roleid.equals("14") ) {
						columns.put("action", f3);
						action += f3;
					}

					if (status_all.equals("3") && roleid.equals("14")) {
						columns.put("action", f1);
						action += f1;
					}
					if (status_all.equals("7") && roleid.equals("14")) {
						columns.put("action", f3);
						action += f3;
					}

					// for highexec

					if (status_all.equals("0") && roleid.equals("525")) {
						columns.put("action", f3);
						action += f3;
					}
					if (status_all.equals("1") && roleid.equals("525") ) {
						columns.put("action", f3);
						action += f3;
					}

					if (status_all.equals("3") && roleid.equals("525")) {
						columns.put("action", f3);
						action += f3;
					}
					if (status_all.equals("7") && roleid.equals("525")) {
						columns.put("action", f3);
						action += f3;
					}

					//for sechead

					if (status_all.equals("0") && roleid.equals("526")) {
						columns.put("action", f3);
						action += f3;
					}
					if (status_all.equals("1") && roleid.equals("526") ) {
						columns.put("action", f3);
						action += f3;
					}

					if (status_all.equals("3") && roleid.equals("526")) {
						columns.put("action", f3);
						action += f3;
					}
					if (status_all.equals("7") && roleid.equals("526")) {
						columns.put("action", f3);
						action += f3;
					}


					//for secoff

					if (status_all.equals("0") && roleid.equals("529")) {
						columns.put("action", f3);
						action += f3;
					}
					if (status_all.equals("1") && roleid.equals("529") ) {
						columns.put("action", f3);
						action += f3;
					}

					if (status_all.equals("3") && roleid.equals("529")) {
						columns.put("action", f3);
						action += f3;
					}
					if (status_all.equals("7") && roleid.equals("529")) {
						columns.put("action", f3);
						action += f3;
					}

					//for itofficer

					if (status_all.equals("0") && roleid.equals("527")) {
						columns.put("action", f3);
						action += f3;
					}
					if (status_all.equals("1") && roleid.equals("527") ) {
						columns.put("action", f3);
						action += f3;
					}

					if (status_all.equals("3") && roleid.equals("527")) {
						columns.put("action", f3);
						action += f3;
					}
					if (status_all.equals("7") && roleid.equals("527")) {
						columns.put("action", f3);
						action += f3;
					}

					//forittech
					if (status_all.equals("0") && roleid.equals("531")) {
						columns.put("action", f3);
						action += f3;
					}
					if (status_all.equals("1") && roleid.equals("531") ) {
						columns.put("action", f3);
						action += f3;
					}

					if (status_all.equals("3") && roleid.equals("531")) {
						columns.put("action", f3);
						action += f3;
					}
					if (status_all.equals("7") && roleid.equals("531")) {
						columns.put("action", f3);
						action += f3;
					}



				}else {
					if (status.equals("0") && roleid.equals("530") ) {
						columns.put("action", f2);
						action += f2;
						action += f1;
						action += f4;
					}

					if (status.equals("1") && roleid.equals("530") ) {
						columns.put("action", f3);
						action += f3;
					}
					if (status.equals("3") && roleid.equals("530") ) {
						columns.put("action", f1);
						action += f1;
					}
					if (status.equals("7") && roleid.equals("530")) {
						columns.put("action", f3);
						action += f3;
					}
					//for admin
					if (status.equals("0") && roleid.equals("1") ) {
						columns.put("action", f2);
						action += f2;
						action += f1;
						action += f4;
					}

					if (status.equals("1") && roleid.equals("1") ) {
						columns.put("action", f3);
						action += f3;
					}
					if (status.equals("3") && roleid.equals("1") ) {
						columns.put("action", f1);
						action += f1;
					}
					if (status.equals("7") && roleid.equals("1")) {
						columns.put("action", f3);
						action += f3;
					}
					//for sysadmin
					if (status.equals("0") && roleid.equals("14") ) {
						columns.put("action", f2);
						action += f2;
						action += f1;
						action += f4;
					}

					if (status.equals("1") && roleid.equals("14") ) {
						columns.put("action", f3);
						action += f3;
					}
					if (status.equals("3") && roleid.equals("14") ) {
						columns.put("action", f1);
						action += f1;
					}
					if (status.equals("7") && roleid.equals("14")) {
						columns.put("action", f3);
						action += f3;
					}

					// for highexec

					if (status.equals("0") && roleid.equals("525") ) {
						columns.put("action", f2);
						columns.put("action", f3);
						action += f3;
					}

					if (status.equals("1") && roleid.equals("525") ) {
						columns.put("action", f3);
						action += f3;
					}
					if (status.equals("3") && roleid.equals("525") ) {
						columns.put("action", f3);
						action += f3;
					}
					if (status.equals("7") && roleid.equals("525")) {
						columns.put("action", f3);
						action += f3;
					}

					// for sechead

					if (status.equals("0") && roleid.equals("526") ) {
						columns.put("action", f2);
						columns.put("action", f3);
						action += f3;
					}

					if (status.equals("1") && roleid.equals("526") ) {
						columns.put("action", f3);
						action += f3;
					}
					if (status.equals("3") && roleid.equals("526") ) {
						columns.put("action", f3);
						action += f3;
					}
					if (status.equals("7") && roleid.equals("526")) {
						columns.put("action", f3);
						action += f3;
					}

					//for secoff

					if (status.equals("0") && roleid.equals("529") ) {
						columns.put("action", f2);
						columns.put("action", f3);
						action += f3;
					}

					if (status.equals("1") && roleid.equals("529") ) {
						columns.put("action", f3);
						action += f3;
					}
					if (status.equals("3") && roleid.equals("529") ) {
						columns.put("action", f3);
						action += f3;
					}
					if (status.equals("7") && roleid.equals("529")) {
						columns.put("action", f3);
						action += f3;
					}


					// for itofficer

					if (status.equals("0") && roleid.equals("527") ) {

						action += f3;
					}

					if (status_all.equals("1") && roleid.equals("527") ) {
						action += f3;
						//  action += f1;
					}
					if (status.equals("3") && roleid.equals("527") ) {
						columns.put("action", f3);
						action += f3;
					}
					if (status.equals("7") && roleid.equals("527")) {
						columns.put("action", f3);
						action += f3;
					}

					//for ittect

					if (status.equals("0") && roleid.equals("531") ) {

						action += f3;
					}

					if (status_all.equals("1") && roleid.equals("531") ) {
						action += f3;
					}
					if (status.equals("3") && roleid.equals("531") ) {
						columns.put("action", f3);
						action += f3;
					}
					if (status.equals("7") && roleid.equals("531")) {
						columns.put("action", f3);
						action += f3;
					}


				}

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
	@Override
	public long getPeripheralCountList(String Search,String orderColunm,String orderType,String status,String assets_type,String year_of_manufacturing,String machine_make,String machine_no,
			String from_date,String to_date,String s_state,String section,HttpSession sessionUserId){
		String SearchValue = GenerateQueryWhereClause_SQL2(Search,assets_type,year_of_manufacturing,machine_make,machine_no,from_date,to_date,status,s_state, section,sessionUserId);


		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			q ="select count(*) "
					+ "from it_asset_peripherals ap\r\n" +
					"inner join  tb_mstr_section sec  on sec.id:: text = ap.section \r\n"+
					"left join tb_mstr_assets pt on pt.id=ap.assets_type  and pt.category=2\r\n" +
					"left join tb_mstr_type_of_hw th on th.id=ap.type_of_hw\r\n" +

					 " where ap.id!=0 "+ SearchValue ;

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL2(stmt,Search,assets_type,year_of_manufacturing,machine_make,machine_no,from_date,to_date,status,s_state,section,sessionUserId);

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
		return total;
	}



	public String GenerateQueryWhereClause_SQL2(String Search,String assets_type,String year_of_manufacturing,String machine_make,String machine_no,
			String from_date,String to_date,String status,String s_state,String section,HttpSession sessionUserId) {
		String SearchValue ="";

		if (!Search.equals("") || !Search.equals(null) || Search != null || Search != " ") { // for Input Filter
			SearchValue = " and ( ";
			SearchValue += " pt.assets_name like ? or upper(th.type_of_hw)  like ? or upper(ap.year_of_proc)  like ? or "
					+ " ap.year_of_manufacturing like ? "
					+ " or upper(ap.b_cost) like ? or upper(ap.machine_no) like ?  or upper(ap.remarks) like ? "
					+ "or upper(sec.section) like ? or upper(ap.created_by) like ?   or upper(sec.section) like ? or Upper(ap.created_by) like ? "
					+ " )";

		}

		//				if(!status.equals(""))
		//				{
		//					SearchValue += "  and cast(ap.status as character varying) = ? ";
		//				}

		if (status.equals("0")) {
			SearchValue += " AND cast(ap.status as character varying) = '0' "; // Use quotes around 0
		}

		if (status.equals("1")) {
			SearchValue += " AND cast(ap.status as character varying) = '1' "; // Use quotes around 1
		}

		if (status.equals("3")) {
			SearchValue += " AND cast(ap.status as character varying) = '3' "; // Use quotes around 3
		}

		if (status.equals("7")) {
			SearchValue += " AND cast(ap.status as character varying) = '7' "; // Use quotes around 7
		}

		if (status.equals("4")) {
			SearchValue += " AND cast(ap.status as character varying) IN ('0', '1', '3', '7') "; // String literals
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

		if(!from_date.equals("") && !to_date.equals(""))
		{
			SearchValue +=" and ap.created_date BETWEEN to_date(?,'DD/MM/YYYY') and  to_date(?,'DD/MM/YYYY')";
		}
		if(!s_state.equals("0")) {
			SearchValue += "  and cast(ap.s_state as character varying) = ? ";
		}
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();

		if(!section.equals("") && !section.equals("0"))
		{
			SearchValue += "  and ap.section = ? ";
		}

		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL2(PreparedStatement stmt,String Search,String assets_type,String year_of_manufacturing,String machine_make,String machine_no,
			String from_date,String to_date,String status,String s_state,String section,HttpSession sessionUserId) {



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
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				flag += 1;
				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				//				flag += 1;
				//				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
				//

			}

			//			if (!status.equals("")) {
			//				flag += 1;
			//				stmt.setString(flag, status);
			//			}

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

			if (!from_date.equals("") && !to_date.equals("")) {
				flag += 1;
				stmt.setString(flag, from_date);
				flag += 1;
				stmt.setString(flag, to_date);

			}

			if (!s_state.equals("0")) {
				flag += 1;
				stmt.setString(flag, s_state);
			}
			String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
			String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();

			if(!section.equals("") && !section.equals("0"))
			{
				flag += 1;
				stmt.setString(flag, section);
			}


		}catch (Exception e) {
			e.printStackTrace();
		}
		return stmt;
	}




	@Override
	public String approve_AssetsData(String a,String user_sus,String status,String username) {
		String[] id_list = a.split(":");
		///BISAG V2 240822///
		String event_status= status;
		Connection conn = null;
		Integer out = 0;
		String q = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			// Get the current year
			int currentYear = Calendar.getInstance().get(Calendar.YEAR);

			for (int i = 0; i < id_list.length; i++) {



				int id = Integer.parseInt(id_list[i]);

				Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
				It_Asset_Peripherals assetupd =sessionHQL.get(It_Asset_Peripherals.class, id);
				String redgno=n.getQRnumber( "PERIPHERAL", assetupd.getMake_name() , assetupd.getModel_name(), assetupd.getAssets_type(), "it_asset_peripherals");


				stmt = conn.prepareStatement("update it_asset_peripherals set status=? ,redgno=?,assign_user_status=? where id=?");
				if(event_status == "1" || event_status.equals("1"))
				{

					if(assetupd.getS_state() == "1" || assetupd.getS_state().equals("1"))
					{
						status="1";
					}

					if(assetupd.getS_state() == "2" || assetupd.getS_state().equals("2"))
					{
						if(assetupd.getUnserviceable_state() == 1)
						{
							status="4";
						}
						if(assetupd.getUnserviceable_state() == 3 || assetupd.getUnserviceable_state() == 2)
						{
							status="1";
						}
					}

					// Generate new asset ID
					String newAssetId = generateNewAssetId(currentYear);

					///BISAG V2 240822 end///
					stmt.setInt(1, Integer.parseInt(status));
					stmt.setString(2, redgno);
					stmt.setInt(3, -1);
					stmt.setInt(4, id);
					out = stmt.executeUpdate();

					PreparedStatement stmt1=null;

					q="update tb_peripheral_child set status=? where p_id=?";
					stmt1 = conn.prepareStatement(q);
					stmt1.setInt(1, Integer.parseInt(status));
					stmt1.setInt(2, id);
					out  = stmt1.executeUpdate();

					// If asset is approved, set the new asset ID
					if (status.equals("1")) {
						stmt = conn.prepareStatement("update it_asset_peripherals set assetId=? where id=?");
						stmt.setString(1, newAssetId);
						stmt.setInt(2, id);
						stmt.executeUpdate();
					}

				}
				if(event_status == "3" || event_status.equals("3"))
				{
					status="3";
				}

				PreparedStatement stmt1 = null;

				q = "select id,warranty,unserviceable_state,s_state from it_asset_peripherals where id =? ";
				stmt1 = conn.prepareStatement(q);
				stmt1.setInt(1, id);
				ResultSet rs = stmt1.executeQuery();
				rs.next();

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
			} else {
				return "UnSuccessfully";
			}
		} else {
			if(status.equals("1")) {
				return "Approved not Successfully";
			}
			else if(status.equals("3")) {
				return "Rejected not Successfully";
			} else {
				return "UnSuccessfully";
			}
		}
	}

	private String generateNewAssetId(int currentYear) throws SQLException {
		String newAssetId;
		String query = "SELECT COUNT(*) FROM it_asset_peripherals WHERE assetId LIKE ?";
		try (Connection conn = dataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, currentYear + "%");
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				int count = rs.getInt(1);
				newAssetId = String.format("%d%04d", currentYear, count + 1); // Format: YYYY####
			} else {
				newAssetId = String.format("%d%04d", currentYear, 1); // Format: YYYY0001
			}
		}
		return newAssetId;
	}

	@Override
	public List<Map<String, Object>> getAppPeripheralList(int startPage,int pageLength,String Search,String orderColunm,String orderType,
			String assets_type,String year_of_manufacturing,String machine_make,String machine_no,
			String section,String from_date,String to_date,String status,HttpSession sessionUserId) throws SQLException
	{

		String roleType = sessionUserId.getAttribute("roleType").toString();

		String SearchValue = GenerateQueryWhereClause_SQL(Search,assets_type,year_of_manufacturing,machine_make,machine_no,section,from_date,to_date,status,sessionUserId);
		String SearchValue1 = GenerateQueryWhereClause_SQL1(Search,status);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		try{
			conn = dataSource.getConnection();
			PreparedStatement stmt =null;
			String qry="";

			String pageL = "";

			if(pageLength == -1){
				pageL = "ALL";
			}else {
				pageL = String.valueOf(pageLength);
			}

			if(status.equals("0")) {
				qry="select ap.id,pt.assets_name,th.type_of_hw,ap.year_of_proc,\r\n" +
						"ap.year_of_manufacturing,ap.b_cost,mm.make_name,ap.machine_no,ap.remarks,\r\n" +
						" coalesce(ltrim(TO_CHAR( ap.warranty  ,'DD-MON-YYYY'),'0'),'')   as warranty,\r\n" +
						" td.label   as service_state,\r\n" +
						" td2.label   as unservice_state  ,\r\n" +
						" td.codevalue  as ser, ch_am.unsv_from_dt,ch_am.unsv_to_dt,case when ch_am.status=0 then ch_am.id else 0 end as ch_id, \r\n" +
						" td2.codevalue   as unsv,sec.section ,ap.created_by from it_asset_peripherals ap\r\n" +
						"inner join tb_peripheral_child ch_am on ap.id = ch_am.p_id " +
						"inner join  tb_mstr_section sec  on sec.id:: text = ap.section \r\n"+
						"left join tb_mstr_assets pt on pt.id=ap.assets_type  and pt.category=2\r\n" +
						"left join tb_mstr_type_of_hw th on th.id=ap.type_of_hw\r\n" +
						"left join tb_mstr_make mm on mm.id=ap.make_name::int\r\n"+
						" left join t_domain_value td on  td.codevalue:: character varying = ch_am.service_state:: character varying and td.domainid='SERVICE_CAT'\r\n" +
						" left join t_domain_value td2 on  td2.codevalue:: character varying = ch_am.unservice_state:: character varying and td2.domainid='UNSERVICEABLE_STATE' \r\n" +
						" where ap.id!=0 and ap.status='1' and ch_am.status = (case when (select count(*) from tb_peripheral_child ch where ch.p_id=ap.id)=1 then 1 else 0 end) "
						+ SearchValue+
						" group by ap.id,pt.assets_name,th.type_of_hw,ap.year_of_proc,\r\n" +
						" ap.year_of_manufacturing,ap.b_cost,mm.make_name,ap.machine_no,ap.remarks,ap.warranty,\r\n" +
						" ch_am.warranty,ch_am.service_state,ch_am.unsv_from_dt,ch_am.unsv_to_dt,td.label,td2.label,td.codevalue,td2.codevalue,ch_am.id,sec.section,ap.created_by "
						+ " ORDER BY "+orderColunm+" "+orderType +" limit " +pageL+" OFFSET "+startPage;

				stmt=conn.prepareStatement(qry);
				stmt = setQueryWhereClause_SQL(stmt,Search,assets_type,year_of_manufacturing,machine_make,machine_no,section,from_date,to_date,status,sessionUserId);

			}
			else {
				qry="select ap.id,pt.assets_name,th.type_of_hw,ap.year_of_proc,\r\n" +
						"ap.year_of_manufacturing,ap.b_cost,mm.make_name,ap.machine_no,ap.remarks,\r\n" +
						" coalesce(ltrim(TO_CHAR( ap.warranty  ,'DD-MON-YYYY'),'0'),'')   as warranty,\r\n" +
						" td.label   as service_state,\r\n" +
						" td2.label   as unservice_state  ,ch_am.unsv_from_dt,ch_am.unsv_to_dt, \r\n" +
						" td.codevalue  as ser, \r\n" +
						" td2.codevalue   as unsv, ch_am.id as ch_id,sec.section,ap.created_by from it_asset_peripherals ap\r\n" +
						"inner join tb_peripheral_child ch_am on ap.id = ch_am.p_id " +
						"inner join  tb_mstr_section sec  on sec.id:: text = ap.section \r\n"+
						"left join tb_mstr_assets pt on pt.id=ap.assets_type  and pt.category=2\r\n" +
						"left join tb_mstr_type_of_hw th on th.id=ap.type_of_hw\r\n" +
						"left join tb_mstr_make mm on mm.id=ap.make_name::int\r\n"+
						" left join t_domain_value td on  td.codevalue:: character varying = ch_am.service_state:: character varying and td.domainid='SERVICE_CAT'\r\n" +
						" left join t_domain_value td2 on  td2.codevalue:: character varying =ch_am.unservice_state:: character varying and td2.domainid='UNSERVICEABLE_STATE' \r\n" +
						"where ap.id!=0 and ap.status='1'" +  SearchValue1+" " +SearchValue+
						"group by ap.id,pt.assets_name,th.type_of_hw,ap.year_of_proc,\r\n" +
						"ap.year_of_manufacturing,ap.b_cost,mm.make_name,ap.machine_no,ap.remarks,ap.warranty,\r\n" +
						"ch_am.warranty,ch_am.service_state,ch_am.unsv_from_dt,ch_am.unsv_to_dt,td.label,td2.label,td.codevalue,td2.codevalue,ch_am.id,sec.section,ap.created_by"
						+ " ORDER BY "+orderColunm+" "+orderType +" limit " +pageL+" OFFSET "+startPage;

				stmt=conn.prepareStatement(qry);

				stmt = setQueryWhereClause_SQL(stmt,Search,assets_type,year_of_manufacturing,machine_make,machine_no,section,from_date,to_date,status,sessionUserId);
				stmt = setQueryWhereClause_SQL1(stmt,Search,status);

			}



			ResultSet rs = null;
			try {
				rs = stmt.executeQuery();
				ResultSetMetaData metaData = rs.getMetaData();

				int columnCount = metaData.getColumnCount();
				while (rs.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();
					for (int i = 1; i <= columnCount; i++) {
						columns.put(metaData.getColumnLabel(i), rs.getObject(i));

					}
					//        					String updateButton = "";
					//
					//        					String Update = "onclick=\"  if (confirm('Are You Sure You Want to Update Details ?') ){editData("
					//        							+ rs.getInt("id") + "," + rs.getInt("ch_id") + ")}else{ return false;}\"";
					//        					updateButton = "<i class='action_icons action_update' " + Update + " title='Edit Data'></i>";
					//
					//        					String Approve = "onclick=\"  if (confirm('Are You Sure You Want to Approve This Information ?') ){Approve("
					//        							+ rs.getInt("id") + ")}else{ return false;}\"";
					//
					//        					String ApproveButton = "<i class='action_icons action_approve' " + Approve
					//        							+ " title='Approve Data'></i>";
					//
					//        					String Reject = "onclick=\"  if (confirm('Are You Sure You Want to Reject This Information ?') ){Reject("
					//        							+ rs.getInt("id") + ")}else{ return false;}\"";
					//        					String RejectButton = "<i class='action_icons action_reject' " + Reject
					//        							+ " title='Reject Data'></i>";

					String updateButton = "<i class='action_icons action_update' data-id='" + rs.getInt("id") + "' data-ch-id='" + rs.getInt("ch_id") + "' title='Edit Data'></i>";
					String ApproveButton = "<i class='action_icons action_approve' data-id='" + rs.getInt("id") + "' title='Approve Data'></i>";
					String RejectButton = "<i class='action_icons action_reject' data-id='" + rs.getInt("id") + "' title='Reject Data'></i>";

					String f = "";
					String ch_status = "1";
					List<Map<String, Object>> ls = getPeriChildStatus(rs.getInt("id"));
					if (ls.size() > 0) {
						ch_status = String.valueOf(ls.get(0).get("status"));
					}

					if (roleType.equals("DEO")
							&& (status == "0" || status.equals("0") || status == "3" || status.equals("3"))) {

						f += updateButton;
					}

					if (roleType.equals("DEO") && (status == "1" || status.equals("1"))) {

						f += updateButton;
					}

					if (roleType.equals("APP")
							&& ((status == "0" || status.equals("0")) && (ch_status == "0" || ch_status.equals("0")))) {

						f += updateButton;

						f += ApproveButton;
						f += RejectButton;
					}
					if (roleType.equals("APP")
							&& ((status == "0" || status.equals("0")) && (ch_status == "1" || ch_status.equals("1")))) {

						f += updateButton;

					}

					if (roleType.equals("APP") && (status == "1" || status.equals("1"))) {

						f += updateButton;

					}

					if (roleType.equals("APP") && (status == "3" || status.equals("3"))) {

						f += updateButton;

					}
					columns.put("action", f);

					list.add(columns);

				}
				rs.close();
				stmt.close();
				conn.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
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
	public long getAppPeripheralCountList(String Search,String orderColunm,String orderType,String status,String assets_type,String year_of_manufacturing,String machine_make,String machine_no,
			String section,String from_date,String to_date,HttpSession sessionUserId)throws SQLException {
		String SearchValue = GenerateQueryWhereClause_SQL(Search,assets_type,year_of_manufacturing,machine_make,machine_no,section,from_date,to_date,status,sessionUserId);
		String SearchValue1 = GenerateQueryWhereClause_SQL1(Search,status);

		PreparedStatement stmt =null;
		int total = 0;
		String q = null;
		Connection conn = null;

		try {
			conn = dataSource.getConnection();

			if(status.equals("0")) {
				q="select"+
						" count(app.*)\r\n"
						+ "from(select ap.id,pt.assets_name,th.type_of_hw,ap.year_of_proc,\r\n" +
						"ap.year_of_manufacturing,ap.b_cost,mm.make_name,ap.machine_no,ap.remarks,\r\n" +
						" coalesce(ltrim(TO_CHAR( ap.warranty  ,'DD-MON-YYYY'),'0'),'')   as warranty,\r\n" +
						" td.label   as service_state,\r\n" +
						" td2.label   as unservice_state  ,\r\n" +
						" td.codevalue  as ser, ch_am.unsv_from_dt,ch_am.unsv_to_dt,case when ch_am.status=0 then ch_am.id else 0 end as ch_id, \r\n" +
						" td2.codevalue   as unsv,sec.section, ap.created_by from it_asset_peripherals ap\r\n" +
						"inner join tb_peripheral_child ch_am on ap.id = ch_am.p_id " +
						"inner join  tb_mstr_section sec  on sec.id:: text = ap.section \r\n"+
						"left join tb_mstr_assets pt on pt.id=ap.assets_type  and pt.category=2\r\n" +
						"left join tb_mstr_type_of_hw th on th.id=ap.type_of_hw\r\n" +
						"left join tb_mstr_make mm on mm.id=ap.make_name::int\r\n"+
						" left join t_domain_value td on  td.codevalue:: character varying = ch_am.service_state:: character varying and td.domainid='SERVICE_CAT'\r\n" +
						" left join t_domain_value td2 on  td2.codevalue:: character varying = ch_am.unservice_state:: character varying and td2.domainid='UNSERVICEABLE_STATE' \r\n" +
						"where ap.id!=0 and ap.status='1' and ch_am.status = (case when (select count(*) from tb_peripheral_child ch where ch.p_id=ap.id)=1 then 1 else 0 end) " + SearchValue+
						"group by ap.id,pt.assets_name,th.type_of_hw,ap.year_of_proc,\r\n" +
						"ap.year_of_manufacturing,ap.b_cost,mm.make_name,ap.machine_no,ap.remarks,ap.warranty,\r\n" +
						"ch_am.warranty,ch_am.service_state,ch_am.unsv_from_dt,ch_am.unsv_to_dt,td.label,td2.label,td.codevalue,td2.codevalue,ch_am.id,sec.section,ap.created_by  ) app";

				stmt=conn.prepareStatement(q);
				stmt = setQueryWhereClause_SQL(stmt,Search,assets_type,year_of_manufacturing,machine_make,machine_no,section,from_date,to_date,status,sessionUserId);
			}

			else {
				q="select"
						+
						" count(app.*)\r\n" +
						"from(select ap.id,pt.assets_name,th.type_of_hw,ap.year_of_proc,\r\n" +
						"ap.year_of_manufacturing,ap.b_cost,mm.make_name,ap.machine_no,ap.remarks,\r\n" +
						" coalesce(ltrim(TO_CHAR( ap.warranty  ,'DD-MON-YYYY'),'0'),'')   as warranty,\r\n" +
						" td.label   as service_state,\r\n" +
						" td2.label   as unservice_state  ,ch_am.unsv_from_dt,ch_am.unsv_to_dt, \r\n" +
						" td.codevalue  as ser, \r\n" +
						" td2.codevalue   as unsv, ch_am.id as ch_id ,sec.section, ap.created_by from it_asset_peripherals ap\r\n" +
						"inner join tb_peripheral_child ch_am on ap.id = ch_am.p_id " +
						"inner join  tb_mstr_section sec  on sec.id:: text = ap.section \r\n"+
						"left join tb_mstr_assets pt on pt.id=ap.assets_type  and pt.category=2\r\n" +
						"left join tb_mstr_type_of_hw th on th.id=ap.type_of_hw\r\n" +
						"left join tb_mstr_make mm on mm.id=ap.make_name::int\r\n"+
						" left join t_domain_value td on  td.codevalue:: character varying = ch_am.service_state:: character varying and td.domainid='SERVICE_CAT'\r\n" +
						" left join t_domain_value td2 on  td2.codevalue:: character varying =ch_am.unservice_state:: character varying and td2.domainid='UNSERVICEABLE_STATE' \r\n" +
						"where ap.id!=0 and ap.status='1' " +  SearchValue1+" " +SearchValue+
						"group by ap.id,pt.assets_name,th.type_of_hw,ap.year_of_proc,\r\n" +
						"ap.year_of_manufacturing,ap.b_cost,mm.make_name,ap.machine_no,ap.remarks,ap.warranty,\r\n" +
						"ch_am.warranty,ch_am.service_state,ch_am.unsv_from_dt,ch_am.unsv_to_dt,td.label,td2.label,td.codevalue,td2.codevalue,ch_am.id,sec.section,ap.created_by) app";
				stmt=conn.prepareStatement(q);

				stmt = setQueryWhereClause_SQL(stmt,Search,assets_type,year_of_manufacturing,machine_make,machine_no,section,from_date,to_date,status,sessionUserId);
				stmt = setQueryWhereClause_SQL1(stmt,Search,status);
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
		return total;
	}



	public String GenerateQueryWhereClause_SQL(String Search,String assets_type,String year_of_manufacturing,String machine_make,String machine_no,
			String section ,String from_date,String to_date,String status,HttpSession sessionUserId) {
		String SearchValue ="";

		if (!Search.equals("") || !Search.equals(null) || Search != null || Search != " ") { // for Input Filter
			SearchValue = " and ( ";
			SearchValue += "cast(pt.assets_name as character varying) like ? or th.type_of_hw  like ? or ap.year_of_proc  like ? or ap.year_of_manufacturing like ? or ap.b_cost like ? or mm.make_name like ? or ap.machine_no like ?  or upper(ap.remarks) like ? " +
					" or coalesce(ltrim(TO_CHAR( ap.warranty  ,'DD-MON-YYYY'),'0'),'') like ? " +
					" or upper(td.label) like ? " +
					" or td2.label like ? " +
					" or upper(td.codevalue) like ? " +
					" or td2.codevalue like ? or upper(sec.section) like ?  or upper(ap.created_by) like ? )";

		}

		if(!assets_type.equals("0")) {
			SearchValue += "  and cast(ap.assets_type as character varying) = ? ";
		}
		if(!year_of_manufacturing.equals("")) {
			SearchValue += "  and upper(ap.year_of_manufacturing)  like ? ";
		}
		if(!machine_no.equals("")) {
			SearchValue += "  and upper(ap.machine_no) like ? ";
		}

		//                if (!model_no.equals("")) {
		//                	SearchValue += " and upper(ap.model_no) like ? ";
		//                }
		if(!from_date.equals("") && !to_date.equals(""))
		{
			SearchValue +=" and ap.created_date BETWEEN to_date(?,'DD/MM/YYYY') and  to_date(?,'DD/MM/YYYY')";
		}
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();

		if(!section.equals("") && !section.equals("0"))
		{
			SearchValue += "  and ap.section = ? ";
		}


		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL(PreparedStatement stmt,String Search,String assets_type,String year_of_manufacturing,String machine_make,String machine_no,
			String section,String from_date,String to_date,String status,HttpSession sessionUserId) {



		int flag = 0;
		if(status.equals("1"))
		{
			flag = 1;
		}

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



			if (!assets_type.equals("0")) {
				flag += 1;
				stmt.setString(flag, assets_type);


			}
			if (!year_of_manufacturing.equals("")) {
				flag += 1;
				stmt.setString(flag,"%"+  year_of_manufacturing + "%");

			}
			if (!machine_no.equals("")) {
				flag += 1;
				stmt.setString(flag, "%"+ machine_no.toUpperCase() + "%");

			}
			//				if (!model_no.equals("")) {
			//					flag += 1;
			//					stmt.setString(flag, "%"+ model_no.toUpperCase() + "%");
			//
			//				}
			if (!from_date.equals("") && !to_date.equals("")) {
				flag += 1;
				stmt.setString(flag, from_date);
				flag += 1;
				stmt.setString(flag, to_date);

			}
			String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
			String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();

			if(!section.equals("") && !section.equals("0"))
			{
				flag += 1;
				stmt.setString(flag, section);
			}

		}catch (Exception e) {}
		return stmt;
	}


	public String GenerateQueryWhereClause_SQL1(String Search, String status) {
		String SearchValue1 ="";


		if(status == "0" ||  status.equals("0"))
		{

			SearchValue1="and ch_am.status = ? ";
		}

		if(status == "1"  || status.equals("1") || status == "3"  || status.equals("3"))
		{
			SearchValue1=" and ch_am.status = ? ";
		}

		return SearchValue1;
	}

	public PreparedStatement setQueryWhereClause_SQL1(PreparedStatement stmt,String Search, String status) {

		int flag = 0;

		try {
			//			if (!Search.equals("") || !Search.equals(null)) {
			//				flag += 1;
			//				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
			//				flag += 1;
			//				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
			//				flag += 1;
			//				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
			//				flag += 1;
			//				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
			//				flag += 1;
			//				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
			//				flag += 1;
			//				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
			//				flag += 1;
			//				stmt.setString(flag, "%" + Search.toUpperCase() + "%");
			//			}

			if(status == "0" ||  status.equals("0"))
			{
				stmt.setInt(1, Integer.parseInt(status) );
			}

			if(status == "1"  || status.equals("1") || status == "3"  || status.equals("3"))
			{
				stmt.setInt(1, Integer.parseInt(status) );

			}


		}catch (Exception e) {}
		return stmt;
	}





	@Override
	public List<Map<String, Object>> getAppPeripheralChildList(String ch_id)
	{
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		try{
			conn = dataSource.getConnection();
			String qry="";


			qry="select * from tb_peripheral_child where p_id=? order by id desc limit 1" ;


			PreparedStatement stmt=conn.prepareStatement(qry);
			stmt.setInt(1,Integer.parseInt(ch_id));
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



	@Override
	public List<Map<String, Object>> getPeriChildStatus (int id )
	{
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		try{
			conn = dataSource.getConnection();
			String qry="";

			qry="select status  from  tb_peripheral_child where p_id =? order by id desc limit 1";

			PreparedStatement stmt=conn.prepareStatement(qry);
			stmt.setInt(1, id);

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






	@Override
	public List<Map<String, Object>> getAppChildPeriList(String p_id)
	{
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		try{
			conn = dataSource.getConnection();
			String qry="";

			qry="select * from it_asset_peripherals where status=0 and p_id=?" ;

			PreparedStatement stmt=conn.prepareStatement(qry);
			stmt.setInt(1,Integer.parseInt(p_id));
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




	@Override
	public List<Map<String, Object>> GetdataPeri(int id) throws SQLException {

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select warranty,unservice_state,service_state,unsv_from_dt,unsv_to_dt,is_networked,ip_address,"
					+ "connectivity_type,hw_interface,ethernet_port,management_layer,network_features from tb_peripheral_child where p_id=? and status=0";

			stmt = conn.prepareStatement(q);
			stmt.setInt(1, id);
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


	@Override
	public ArrayList<ArrayList<String>> getcomaseptext_hw_interface(int id){

		ArrayList<ArrayList<String>> list1 = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select 	 array_to_string(ARRAY(select sub.hardware_interface \n" +
					"from unnest(string_to_array((select hw_interface from it_asset_peripherals where id=nmk.id), ',')) qsub \n" +
					" inner join TB_MSTR_HARDWARE_INTERFACE sub on sub.id=cast(qsub as integer)),',') as hw_inf \n" +
					" from it_asset_peripherals  nmk where nmk.id=?";

			stmt = conn.prepareStatement(q);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();


			while (rs.next()) {

				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("hw_inf"));
				list1.add(list);
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
		return list1;
	}


	@Override
	public ArrayList<ArrayList<String>> getcomaseptext_network_feature(int id){

		ArrayList<ArrayList<String>> list1 = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q = "";

		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select 	 array_to_string(ARRAY(select sub.network_features \n" +
					"from unnest(string_to_array((select network_features from it_asset_peripherals where id=nmk.id), ',')) qsub\n" +
					"inner join tb_mstr_network_features sub on sub.id=cast(qsub as integer)),',') as subject\n" +
					"from it_asset_peripherals  nmk where nmk.id=?";

			stmt = conn.prepareStatement(q);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();


			while (rs.next()) {

				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("subject"));
				list1.add(list);
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
		return list1;
	}

	// bisag v4 18-11-2022 (delete assets)
	@Override
	public List<Map<String, Object>> SearchDelete_Peripheral(int startPage,int pageLength,String Search,String orderColunm,
			String orderType,String status,String assets_type,String year_of_manufacturing,String machine_make,String machine_no,
			String from_date,String to_date,String s_state,String section,HttpSession sessionUserId)
	{

		String SearchValue = GenerateQueryWhereClause_SQL2(Search,assets_type,year_of_manufacturing,machine_make,machine_no,from_date,to_date,status,s_state,
				section,sessionUserId);
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

			q="select ap.id,pt.assets_name,ap.s_state,th.type_of_hw,ap.year_of_proc,\r\n" +
					"ap.year_of_manufacturing,ap.b_cost,ap.make_name,ap.machine_no,ap.remarks,sec.section,ap.created_by from it_asset_peripherals ap\r\n" +
					"inner join  tb_mstr_section sec  on sec.id:: text = ap.section \r\n"+
					"left join tb_mstr_assets pt on pt.id=ap.assets_type  and pt.category=2\r\n" +
					"left join tb_mstr_type_of_hw th on th.id=ap.type_of_hw\r\n"
					+ "where ap.id!=0 "+ SearchValue +" ORDER BY "+orderColunm+" "+orderType +" limit " +pageL+" OFFSET "+startPage ;

			stmt=conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL2(stmt,Search,assets_type,year_of_manufacturing,machine_make,machine_no,from_date,to_date,status,s_state,section,sessionUserId);
			ResultSet rs = stmt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();

			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));

				}


				String CheckBox = "<input class='nrCheckBox' type='checkbox' id='" + rs.getObject(1)
				+ "' name='cbox' onchange='checkbox_count(this," + rs.getObject(1) + ");' />";

				String CheckBoxid = "<input type='hidden' id='id" + rs.getObject(1) + "' name='id" + rs.getObject(1)
				+ "' value='" + rs.getObject(1) + "' />";

				String chekboxaction = CheckBox + CheckBoxid;
				columns.put("chekboxaction", chekboxaction);

				String action = "";

				// Create action icons with data attributes
				String editIcon = "<i class='action_icons action_update' data-id='" + rs.getInt("id") + "' title='Edit Data'></i>";
				String deleteIcon = "<i class='action_icons action_delete' data-id='" + rs.getInt("id") + "' title='Delete Data'></i>";
				String viewIcon = "<i class='fa fa-eye' data-id='" + rs.getInt("id") + "' title='View Data'></i>";

				action += deleteIcon + editIcon + viewIcon;
				columns.put("action", action);

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
	@Override
	public long getPeripheralCountListDelete(String Search,String orderColunm,String orderType,String status,String assets_type,String year_of_manufacturing,String machine_make,String machine_no,
			String from_date,String to_date,String s_state,String section,HttpSession sessionUserId){
		String SearchValue = GenerateQueryWhereClause_SQL2(Search,assets_type,year_of_manufacturing,machine_make,machine_no,from_date,to_date,status,s_state, section,sessionUserId);


		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			q ="select count(*) "
					+ "from it_asset_peripherals ap\r\n" +
					"inner join  tb_mstr_section sec  on sec.id:: text = ap.section \r\n"+
					"left join tb_mstr_assets pt on pt.id=ap.assets_type  and pt.category=2\r\n" +
					"left join tb_mstr_type_of_hw th on th.id=ap.type_of_hw\r\n"

				+  "where ap.id!=0 "+ SearchValue ;

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL2(stmt,Search,assets_type,year_of_manufacturing,machine_make,machine_no,from_date,to_date,status,s_state,section,sessionUserId);

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
		return total;
	}

	@Override
	public ArrayList<ArrayList<String>> Excel_Peripheral_Assets_Search(HttpSession session,
			String machine_number, String assets_type, String status, String s_state, String section,
			String year_of_manufacturing) {

		String SearchValue = GenerateQueryWhereClause_SQL2(assets_type,year_of_manufacturing,machine_number,status,s_state,section,session);
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";

		try{
			String pageL = "";


			conn = dataSource.getConnection();
			PreparedStatement stmt=null;

			q="select ap.id,pt.assets_name,ap.s_state,th.type_of_hw,ap.year_of_proc,\r\n" +
					"ap.year_of_manufacturing,ap.b_cost,ap.make_name,ap.machine_no,ap.remarks,sec.section,ap.created_by  from it_asset_peripherals ap\r\n" +
					"inner join  tb_mstr_section sec  on sec.id:: text = ap.section \r\n"+
					"left join tb_mstr_assets pt on pt.id=ap.assets_type  and pt.category=2\r\n" +
					"left join tb_mstr_type_of_hw th on th.id=ap.type_of_hw\r\n" +
					"where ap.id!=0 "+ SearchValue;

			stmt=conn.prepareStatement(q);
			stmt = setQueryWhereClause_SQL2(stmt,assets_type,year_of_manufacturing,machine_number,status,s_state,section,session);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("assets_name"));// 0
				list.add(rs.getString("type_of_hw"));// 1
				list.add(rs.getString("year_of_proc"));// 2
				list.add(rs.getString("year_of_manufacturing"));// 3
				list.add(rs.getString("machine_no"));// 4
				list.add(rs.getString("section"));// 9
				list.add(rs.getString("created_by"));// 11
				list.add(rs.getString("remarks"));// 12


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

	public String GenerateQueryWhereClause_SQL2(String assets_type,String year_of_manufacturing,String machine_number,
			String status,String s_state,String section,HttpSession sessionUserId) {
		String SearchValue ="";



		if(!status.equals(""))
		{
			SearchValue += "  and cast(ap.status as character varying) = ? ";
		}

		if(!assets_type.equals("0")) {
			SearchValue += "  and upper( cast(ap.assets_type as character varying)) = ? ";
		}
		if(!year_of_manufacturing.equals("0") && !year_of_manufacturing.equals("")) {
			SearchValue += "  and cast(ap.year_of_manufacturing as character varying) = ? ";
		}
		if(!machine_number.equals("")) {
			SearchValue += "  and upper(ap.machine_no) like ? ";
		}

		if(!s_state.equals("0")) {
			SearchValue += "  and cast(ap.s_state as character varying) = ? ";
		}

		if(!section.equals("") && !section.equals("0"))
		{
			SearchValue += "  and ap.section = ? ";
		}

		return SearchValue;
	}


	public PreparedStatement setQueryWhereClause_SQL2(PreparedStatement stmt,String assets_type,String year_of_manufacturing,String machine_number,
			String status,String s_state,String section,HttpSession sessionUserId) {



		int flag = 0;
		try {


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
			if (!machine_number.equals("")) {
				flag += 1;
				stmt.setString(flag, machine_number.toUpperCase() + "%");

			}

			if (!s_state.equals("0")) {
				flag += 1;
				stmt.setString(flag, s_state);
			}

			if(!section.equals("") && !section.equals("0"))
			{
				flag += 1;
				stmt.setString(flag, section);
			}


		}catch (Exception e) {
			e.printStackTrace();
		}
		return stmt;
	}








	@Override
	public ArrayList<ArrayList<String>> Excel_Peripheral_Assets_Search_app(HttpSession session,
			String machine_number, String assets_type, String status, String s_state, String section,
			String year_of_manufacturing) {


		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		Connection conn = null;
		String q="";


		String SearchValue = GenerateQueryWhereClause_SQL("",assets_type,year_of_manufacturing,"",machine_number,section,"","",status,session);
		String SearchValue1 = GenerateQueryWhereClause_SQL1("",status);


		try{
			conn = dataSource.getConnection();
			PreparedStatement stmt =null;
			String qry="";

			String pageL = "";



			if(status.equals("0")) {
				qry="select ap.id,pt.assets_name,th.type_of_hw,ap.year_of_proc,\r\n" +
						"ap.year_of_manufacturing,ap.b_cost,mm.make_name,ap.machine_no,ap.remarks,\r\n" +
						" coalesce(ltrim(TO_CHAR( ap.warranty  ,'DD-MON-YYYY'),'0'),'')   as warranty,\r\n" +
						" td.label   as service_state,\r\n" +
						" td2.label   as unservice_state  ,\r\n" +
						" td.codevalue  as ser, ch_am.unsv_from_dt,ch_am.unsv_to_dt,case when ch_am.status=0 then ch_am.id else 0 end as ch_id, \r\n" +
						" td2.codevalue   as unsv,sec.section ,ap.created_by from it_asset_peripherals ap\r\n" +
						"inner join tb_peripheral_child ch_am on ap.id = ch_am.p_id " +
						"inner join  tb_mstr_section sec  on sec.id:: text = ap.section \r\n"+
						"left join tb_mstr_assets pt on pt.id=ap.assets_type  and pt.category=2\r\n" +
						"left join tb_mstr_type_of_hw th on th.id=ap.type_of_hw\r\n" +
						"left join tb_mstr_make mm on mm.id=ap.make_name::int\r\n"+
						" left join t_domain_value td on  td.codevalue:: character varying = ch_am.service_state:: character varying and td.domainid='SERVICE_CAT'\r\n" +
						" left join t_domain_value td2 on  td2.codevalue:: character varying = ch_am.unservice_state:: character varying and td2.domainid='UNSERVICEABLE_STATE' \r\n" +
						" where ap.id!=0 and ap.status='1' and ch_am.status = (case when (select count(*) from tb_peripheral_child ch where ch.p_id=ap.id)=1 then 1 else 0 end) "
						+ SearchValue+
						" group by ap.id,pt.assets_name,th.type_of_hw,ap.year_of_proc,\r\n" +
						" ap.year_of_manufacturing,ap.b_cost,mm.make_name,ap.machine_no,ap.remarks,ap.warranty,\r\n" +
						" ch_am.warranty,ch_am.service_state,ch_am.unsv_from_dt,ch_am.unsv_to_dt,td.label,td2.label,td.codevalue,td2.codevalue,ch_am.id,sec.section,ap.created_by ";


				stmt=conn.prepareStatement(qry);
				stmt = setQueryWhereClause_SQL(stmt,"",assets_type,year_of_manufacturing,"",machine_number,section,"","",status,session);

			}
			else {
				qry="select ap.id,pt.assets_name,th.type_of_hw,ap.year_of_proc,\r\n" +
						"ap.year_of_manufacturing,ap.b_cost,mm.make_name,ap.machine_no,ap.remarks,\r\n" +
						" coalesce(ltrim(TO_CHAR( ap.warranty  ,'DD-MON-YYYY'),'0'),'')   as warranty,\r\n" +
						" td.label   as service_state,\r\n" +
						" td2.label   as unservice_state  ,ch_am.unsv_from_dt,ch_am.unsv_to_dt, \r\n" +
						" td.codevalue  as ser, \r\n" +
						" td2.codevalue   as unsv, ch_am.id as ch_id,sec.section,ap.created_by from it_asset_peripherals ap\r\n" +
						"inner join tb_peripheral_child ch_am on ap.id = ch_am.p_id " +
						"inner join  tb_mstr_section sec  on sec.id:: text = ap.section \r\n"+
						"left join tb_mstr_assets pt on pt.id=ap.assets_type  and pt.category=2\r\n" +
						"left join tb_mstr_type_of_hw th on th.id=ap.type_of_hw\r\n" +
						"left join tb_mstr_make mm on mm.id=ap.make_name::int\r\n"+
						" left join t_domain_value td on  td.codevalue:: character varying = ch_am.service_state:: character varying and td.domainid='SERVICE_CAT'\r\n" +
						" left join t_domain_value td2 on  td2.codevalue:: character varying =ch_am.unservice_state:: character varying and td2.domainid='UNSERVICEABLE_STATE' \r\n" +
						"where ap.id!=0 and ap.status='1'" +  SearchValue1+" " +SearchValue+
						"group by ap.id,pt.assets_name,th.type_of_hw,ap.year_of_proc,\r\n" +
						"ap.year_of_manufacturing,ap.b_cost,mm.make_name,ap.machine_no,ap.remarks,ap.warranty,\r\n" +
						"ch_am.warranty,ch_am.service_state,ch_am.unsv_from_dt,ch_am.unsv_to_dt,td.label,td2.label,td.codevalue,td2.codevalue,ch_am.id,sec.section,ap.created_by";


				stmt=conn.prepareStatement(qry);

				stmt = setQueryWhereClause_SQL(stmt,"",assets_type,year_of_manufacturing,"",machine_number,section,"","",status,session);
				stmt = setQueryWhereClause_SQL1(stmt,"",status);

			}


			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("assets_name"));// 0
				list.add(rs.getString("type_of_hw"));// 1
				list.add(rs.getString("year_of_proc"));// 2
				list.add(rs.getString("year_of_manufacturing"));// 3
				list.add(rs.getString("b_cost"));// 3
				list.add(rs.getString("make_name"));// 3

				list.add(rs.getString("machine_no"));// 4
				//list.add(rs.getString("model_no"));// 5
				list.add(rs.getString("section"));// 9
				list.add(rs.getString("created_by"));// 11
				list.add(rs.getString("remarks"));// 12
				list.add(rs.getString("warranty"));// 12
				list.add(rs.getString("service_state"));// 12
				list.add(rs.getString("unservice_state"));// 12


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




	// for assetcount assign user

	@Override
	public Integer getperipheralassetcount(String section, String assets_type,String type_of_hw, String make_name, String model_name) {
		Connection conn = null;
		int count = 0;
		try {
			conn = dataSource.getConnection();
			String sql = "SELECT COUNT(*) as asset_count " + "FROM it_asset_peripherals " + "WHERE section = ? "
					+ "AND assets_type = ? " + "AND type_of_hw = ? " + "AND make_name = ? " + "AND model_name = ? "
					+ "AND status = 1 " + "AND assign_user IS NULL";

			PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);

			stmt.setString(1, section);
			stmt.setInt(2, Integer.parseInt(assets_type));
			stmt.setInt(3, Integer.parseInt(type_of_hw));
			stmt.setInt(4, Integer.parseInt(make_name));
			stmt.setInt(5, Integer.parseInt(model_name));

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt("asset_count");
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
					e.printStackTrace();
				}
			}
		}
		return count;
	}

	// for total count




	@Override
	public int getTotalAssetsAssigned(String section, String assets_type,String type_of_hw, String make_name, String model_name) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			String hql = "SELECT SUM(CAST(assetcount AS int)) FROM Peripheral_Asset_Assign_To_User " + // Assuming assetcount is
					// stored as string
					"WHERE section = :section " + "AND assets_type = :assets_type " + "AND type_of_hw = :type_of_hw "
					+ "AND make_name = :make_name " + "AND model_name = :model_name "
					+"AND assignstatus = '0'"; // Only Assigned Assets
			Query query = session.createQuery(hql);
			query.setParameter("section", section);
			query.setParameter("assets_type", assets_type);
			query.setParameter("type_of_hw", type_of_hw);
			query.setParameter("make_name", make_name);
			query.setParameter("model_name", model_name);

			Long sum = (Long) query.uniqueResult(); // Returns null if no records match


			return (sum == null) ? 0 : sum.intValue();

		} finally {
			session.close();
		}
	}

	// for search screen


	public String GenerateQueryWhereClause_SQL5(String Search, String assets_type, String section, String status,
			HttpSession sessionUserId) {
		String SearchValue = "";

		if (!Search.equals("") && Search != null && !Search.equals(null) && !Search.equals(" ")) {
			SearchValue = " and ( ";
			SearchValue += "cast(pt.assets_name as character varying) like ?  or upper(make.make_name)  like ? or upper(model.model_name)  like ? or upper(usr.username)  like ? or upper(am.assetcount)  like ? or upper(sec.section)  like ?)";
		}
		if (!assets_type.equals("0")) {
			SearchValue += "  and cast(am.assets_type as character varying) = ? ";
		}
		if (!section.equals("") && !section.equals("0")) {
			SearchValue += " and am.section = ? ";
		}
		if (!status.equals("")) {
			SearchValue += " AND cast(am.assignstatus as character varying) = ? "; // Use quotes around 0
		}
		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL5(PreparedStatement stmt, String Search, String assets_type,
			String section, 	String status,HttpSession sessionUserId) {
		int flag = 0;
		try {
			if (!Search.equals("") && Search != null && !Search.equals(" ")) {
				flag += 1;
				stmt.setString(flag,"%" + Search.toUpperCase() + "%");
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
			if (!section.equals("") && !section.equals("0")) {
				flag += 1;
				stmt.setString(flag, section);
			}
			if (!status.equals("")) {
				flag += 1;
				stmt.setString(flag, status);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return stmt;
	}





	@Override
	public List<Map<String, Object>> Search_peripheral_Assets_Assign(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,  String assets_type, String section,String status,
			HttpSession session) {
		String SearchValue = GenerateQueryWhereClause_SQL5(Search, assets_type, section,status,  session);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		String q = "";
		try {
			String pageL = "";
			if (pageLength == -1) {
				pageL = "ALL";
			} else {
				pageL = String.valueOf(pageLength);
			}
			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select am.id, sec.section, pt.assets_name, th.type_of_hw, make.make_name, model.model_name, usr.username, am.assetcount,am.assignstatus, TO_CHAR(am.created_date, 'DD-MM-YYYY HH24:MI:SS') AS created_date "
					+ "from tb_peripheral_assign_asset_user am "
					+ "inner join tb_mstr_section sec on sec.id::text = am.section "
					+ "left join tb_mstr_assets pt on pt.id::text = am.assets_type "
					+ "left join tb_mstr_type_of_hw th on th.id::text = am.type_of_hw "
					+ "left join tb_mstr_make make on make.id::text = am.make_name "
					+ "left join tb_mstr_model model on model.id::text = am.model_name "
					+ "left join logininformation usr on usr.userid::text = am.assignuser "
					+ "where am.id != 0 " // Notice space at the end
					+ SearchValue // <-- Still DANGEROUS if not handled carefully
					+ " ORDER BY " + orderColunm // <-- Still DANGEROUS if not validated
					+ " " + orderType // <-- Still DANGEROUS if not validated
					+ " limit " + pageL // <-- Should be parameterized
					+ " OFFSET " + startPage; // <-- Should be parameterized
			stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL5(stmt, Search, assets_type,  section, status,session);

			ResultSet rs = stmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();
			while (rs.next()) {
				Map<String, Object> columns = new LinkedHashMap<String, Object>();
				for (int i = 1; i <= columnCount; i++) {
					columns.put(metaData.getColumnLabel(i), rs.getObject(i));
				}
				String Checkbox = "<input class='nrCheckBox' type='checkbox' id='" + rs.getObject(1)// 13
				+ "' name='cbox' onchange='checkbox_count(this," + rs.getObject(1) + ");' />";

				String CheckboxId = "<input  type='hidden' id='id" + rs.getObject(1) + "' name='id" + rs.getObject(1)// 14
				+ "' value='" + rs.getObject(1) + "'   />";

				String chekboxaction = "";
				chekboxaction += Checkbox;
				chekboxaction += CheckboxId;

				columns.put("chekboxaction", chekboxaction);
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

	@Override
	public long getAppperipheralassetassignCountList1(String Search, String orderColunm, String orderType, String status,
			String assets_type, String section, HttpSession sessionUserId) throws SQLException {
		String SearchValue = GenerateQueryWhereClause_SQL5(Search, assets_type, status, section, sessionUserId);
		int total = 0;
		String q = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			q = "SELECT COUNT(*) "
					+ "FROM ( "
					+ "  SELECT am.id "
					+ "  , sec.section "
					+ "  , pt.assets_name "
					+ "  , th.type_of_hw "
					+ "  , make.make_name "
					+ "  , model.model_name "
					+ "  , usr.username "
					+ "  , am.assetcount "
					+ "  , TO_CHAR(am.created_date, 'DD-MM-YYYY HH24:MI:SS') AS created_date "
					+ "  FROM tb_peripheral_assign_asset_user am "
					+ "  INNER JOIN tb_mstr_section sec ON sec.id::text = am.section "
					+ "  LEFT JOIN tb_mstr_assets pt ON pt.id::text = am.assets_type "
					+ "  LEFT JOIN tb_mstr_type_of_hw th ON th.id::text = am.type_of_hw "
					+ "  LEFT JOIN tb_mstr_make make ON make.id::text = am.make_name "
					+ "  LEFT JOIN tb_mstr_model model ON model.id::text = am.model_name "
					+ "  LEFT JOIN logininformation usr ON usr.userid::text = am.assignuser "
					+ "  WHERE am.id != 0 "
					+ SearchValue
					+ ") app";

			PreparedStatement stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL5(stmt, Search, assets_type, status, section, sessionUserId);

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
		return total;
	}

	@Override
	public String approve_peripheralAssetsAssignData(String a, String status) {
		if (a == null || a.trim().isEmpty() || status == null || status.trim().isEmpty()) {
			return "Error: Input IDs or status cannot be empty.";
		}

		String[] id_list = a.split(":");
		int statusInt;
		try {
			statusInt = Integer.parseInt(status);
		} catch (NumberFormatException e) {
			System.err.println("Invalid status format: " + status);
			return "Error: Invalid status format. Status must be a number.";
		}

		String sql = "update tb_peripheral_assign_asset_user set assignstatus=? where id=?";
		int totalUpdates = 0;
		int failedUpdates = 0;
		boolean errorOccurred = false;
		try (Connection conn = dataSource.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {

			conn.setAutoCommit(false);

			for (String idStr : id_list) {
				try {
					int id = Integer.parseInt(idStr.trim());
					stmt.setInt(1, statusInt);
					stmt.setInt(2, id);

					int rowsAffected = stmt.executeUpdate();
					totalUpdates += rowsAffected;
					if (rowsAffected == 0) {
					}

				} catch (NumberFormatException nfe) {
					failedUpdates++;
				} catch (SQLException e) {
					e.printStackTrace();
					failedUpdates++;
					errorOccurred = true;

				}
			}
			if (errorOccurred || failedUpdates > 0) {

				try {
					conn.rollback();
				} catch(SQLException ex) {
				}
				errorOccurred = true;
			} else {
				conn.commit();
			}

		} catch (SQLException e) {

			e.printStackTrace();
			errorOccurred = true;

			return "Database error occurred during the update process.";
		} catch (Exception e) {

			e.printStackTrace();
			errorOccurred = true;
			return "An unexpected error occurred.";
		}

		if (!errorOccurred) {
			String action = "";
			if (status.equals("1")) {
				action = "Approved";
			} else if (status.equals("3")) {
				action = "Rejected";
			} else {
				action = "Updated (Status: " + status + ")";
			}
			return action + " Successfully";

		} else {
			String action = "";
			if (status.equals("1")) {
				action = "Approved";
			} else if (status.equals("3")) {
				action = "Rejected";
			} else {
				action = "Update (Status: " + status + ")";
			}
			return action + " not Successfully";
		}
	}

	// for excel

	//for excel assign

	public String GenerateQueryWhereClause_SQL6(String assets_type,  String status,
			String section, HttpSession sessionUserId) {
		String SearchValue = "";

		if (!status.equals("")) {
			SearchValue += "  and cast(am.assignstatus as character varying) = ? ";
		}

		if (!assets_type.equals("0")) {
			SearchValue += "  and cast(am.asset_type as character varying) = ? ";
		}


		if (!section.equals("") && !section.equals("0")) {
			SearchValue += " and  am.section= ? ";

		}
		return SearchValue;
	}

	public PreparedStatement setQueryWhereClause_SQL6(PreparedStatement stmt, String assets_type,
			String status,  String section, HttpSession sessionUserId) {

		int flag = 0;
		try {

			if (!status.equals("")) {
				flag += 1;
				stmt.setString(flag, status);
			}
			if (!assets_type.equals("0")) {
				flag += 1;
				stmt.setString(flag, assets_type);
			}


			if (!section.equals("") && !section.equals("0")) {
				flag += 1;
				stmt.setString(flag, section);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return stmt;
	}






	@Override
	public ArrayList<ArrayList<String>> excel_Peripheral_Assets_AssignReportDataList(HttpSession session,
			String assets_type, String status, String section) {
		ArrayList<ArrayList<String>> alist = new ArrayList<ArrayList<String>>();
		String SearchValue = GenerateQueryWhereClause_SQL6(assets_type, status, section,
				session);

		Connection conn = null;
		String q = "";

		try {
			String pageL = "";

			conn = dataSource.getConnection();
			PreparedStatement stmt = null;

			q = "select am.id, sec.section, pt.assets_name, th.type_of_hw, make.make_name, model.model_name, usr.username, am.assetcount, TO_CHAR(am.created_date, 'DD-MM-YYYY HH24:MI:SS') AS created_date "
					+ "from tb_peripheral_assign_asset_user am "
					+ "inner join tb_mstr_section sec on sec.id::text = am.section "
					+ "left join tb_mstr_assets pt on pt.id::text = am.assets_type "
					+ "left join tb_mstr_type_of_hw th on th.id::text = am.type_of_hw "
					+ "left join tb_mstr_make make on make.id::text = am.make_name "
					+ "left join tb_mstr_model model on model.id::text = am.model_name "
					+ "left join logininformation usr on usr.userid::text = am.assignuser "
					+ "where am.id != 0 " // Notice space at the end
					+ SearchValue  + "";

			stmt = conn.prepareStatement(q);

			stmt = setQueryWhereClause_SQL6(stmt, assets_type, status, section, session);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				ArrayList<String> list = new ArrayList<String>();
				list.add(rs.getString("section"));// 0
				// list.add(rs.getString("model_number"));// 1
				list.add(rs.getString("assets_name"));// 2
				list.add(rs.getString("type_of_hw"));// 2
				list.add(rs.getString("make_name"));// 3
				list.add(rs.getString("model_name"));// 4
				list.add(rs.getString("username"));// 5
				list.add(rs.getString("assetcount"));// 9
				list.add(rs.getString("created_date"));// 11

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










}
