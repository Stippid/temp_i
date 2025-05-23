package com.dao.helpDesk;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.models.Helpdesk.HD_TB_BISAG_FAQ;
import com.models.Helpdesk.HD_MARQUEE;
import com.models.Helpdesk.HD_TB_BISAG_TICKET_GENERATE;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

public class HelpDAOImpl implements HelpDAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	

	

	
////////////////new changes for it assest 
	public HD_TB_BISAG_TICKET_GENERATE getUploadScreenShotByid(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query q = session.createQuery("from HD_TB_BISAG_TICKET_GENERATE where id=:id");
		q.setParameter("id", id);
		HD_TB_BISAG_TICKET_GENERATE list = (HD_TB_BISAG_TICKET_GENERATE) q.list().get(0);
		session.getTransaction().commit();
		session.close();
		return list;
	}
	public List<Map<String, Object>> getCompletedTicketCount() {
		String qry = "select count(id)::int as total from ticket_generate where ticket_status ='1'"; // completed
		List<Map<String, Object>> list = getCompletedTicketCountJdbc(qry);
		return list;
	}
	public List<Map<String, Object>> getcreateTicketCount() {
		String qry = "select count(id)::int as total from ticket_generate where (ticket_status = '0' OR ticket_status = '1')"; // create
		List<Map<String, Object>> list = getCompletedTicketCountJdbc(qry);
		return list;
	}
	public List<Map<String, Object>> getpendingTicketCount() {
		String qry = "select count(id)::int as total from ticket_generate where ticket_status = '0' and assignuser is null "; // pending
		List<Map<String, Object>> list = getCompletedTicketCountJdbc(qry);
		return list;
	}
	public List<Map<String, Object>> getassignTicketCount() {
		String qry = "select count(id)::int as total from ticket_generate where ticket_status = '0' and assignuser is not null  and it_tech_approve_status is null "; // assign
		List<Map<String, Object>> list = getCompletedTicketCountJdbc(qry);
		return list;
	}
	
	public List<Map<String, Object>> getinprogressTicketCount() {
		String qry = "select count(id)::int as total from ticket_generate where ticket_status = '0' and assignuser is not null and it_tech_approve_status ='1' "; // assign
		List<Map<String, Object>> list = getCompletedTicketCountJdbc(qry);
		return list;
	}
	
	public List<Map<String, Object>> getAllCodeListJdbc(String ticket, String ticket_status, String from_date,
			String to_date, String help_topic, String userId, String roleid, String module, String label1) {
		{
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";
			String qry = "";
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;
				if (ticket_status != "" && ticket_status != null) {
					qry += " and ticket_status =?";
				}
				if (!from_date.equals("")) {
					qry += " and cast(created_on as Date) >= cast(? as Date)";
				}
				if (!to_date.equals("")) {
					qry += " and cast(created_on as Date) <= cast(? as Date)";
				}

				if (ticket != "" && ticket != null) {
					qry += " and cast(ticket as character varying) =?";
				}

				if (!help_topic.equals("0") && !help_topic.equals(null)) {
					qry += " and help_topic =?";
				}

				if (module != "0" && !module.equals("0")) {
					qry += " and cast(b.id as character varying) = ?";
				}
				if (!roleid.equals("1") &&  !roleid.equals("527") && !roleid.equals("531")) {
   				qry += " and cast(userid as character varying)=?";
				}
				
				if (roleid.equals("527") ) {
	   				qry += " and a.assignuser is null";
					}
				if (roleid.equals("531")) {
				    qry += " and a.assignuser = cast(? as character varying)";
				}
				

//				if (roleid.equals("1")) {
//
//					if (label1.equals("My Tickets")) {
//						qry += " and cast(userid as character varying)=?";
//					}
//				}
				if (qry != "") {
					qry = " where " + qry.substring(4, qry.length());
				}

				q = "select \r\n" + "	a.id,\r\n" + "	a.ticket,\r\n" + "	b.module_name,\r\n"
						+ "	a.issue_summary,\r\n"
						+ "	(case when a.ticket_status='0' then ltrim(TO_CHAR(a.created_on,'dd-mm-yyyy'),'0') \r\n"
						+ "		when a.ticket_status='1' then ltrim(TO_CHAR(a.assigned_dt,'dd-mm-yyyy'),'0') \r\n"
						+ "		when a.ticket_status='2' then ltrim(TO_CHAR(a.completed_dt,'dd-mm-yyyy'),'0') \r\n"
						+ "		else null\r\n" 
						+ "		end) as dt,\r\n" 
						+ "	t1.label as help_topic,\r\n"
						+ "	t.label as ticket_status,\r\n"
						+ "	(case when a.module='-1' then 'Others' else b.module_name end) as module_name,a.it_tech_approve_status,a.assignuser \r\n"
						+ "from ticket_generate a \r\n"
						+ "left join tb_ldap_module_master b on cast(a.module as varchar) = cast(b.id as varchar) \r\n"
						+ "left join t_domain_value t on t.codevalue=a.ticket_status and t.domainid='TICKETSTATUS' \r\n"
						+ "left join t_domain_value t1 on t1.codevalue=a.help_topic and t1.domainid='HELPTOPIC' \r\n"
						+ qry + " order by created_on DESC";
			
				int j = 1;
				stmt = conn.prepareStatement(q);
				
	

				if (ticket_status != "" && ticket_status != null) {
					stmt.setString(j, ticket_status);
					j += 1;
				}
				if (!from_date.equals("")) {
					stmt.setString(j, from_date);
					j += 1;
				}
				if (to_date != "") {
					stmt.setString(j, to_date);
					j += 1;
				}
				if (ticket != "" && ticket != null) {
					stmt.setString(j, ticket);
					j += 1;
				}
				if (!help_topic.equals("0") && !help_topic.equals(null)) {
					stmt.setString(j, help_topic);
					j += 1;
				}
				if (module != "0" && !module.equals("0")) {
					stmt.setString(j, module);
					j += 1;
				}
				if (!roleid.equals("1") &&  !roleid.equals("527") &&  !roleid.equals("531")) {
					stmt.setString(j, userId);
					j += 1;
				}
				if (roleid.equals("531")) {
					stmt.setString(j, userId);
					j += 1;
				   
				}

				ResultSet rs = stmt.executeQuery();
				
				ResultSetMetaData metaData = rs.getMetaData();
				String updateButton= "";
				String approveButton= "";
				String acceptButton= "";
				int columnCount = metaData.getColumnCount();
				while (rs.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();

					for (int i = 1; i <= columnCount; i++) {
						columns.put(metaData.getColumnLabel(i), rs.getObject(i));
					}
					 Object itTechApproveStatusObj = rs.getObject(9);
					Object assignuserObj = rs.getObject(10); 
					
//					if(assignuserObj==null) {
//
//						 updateButton = "<i class='fa fa-edit color_red' data-id='" + rs.getObject(1).toString() + "' data-label='" + label1 + "' " +
//			                      "title='Edit Data'></i>";
//					}
//					else {
//						updateButton = "<span class= 'color_red'>Assigned</span>";
//					}
//					 if(assignuserObj!=null && itTechApproveStatusObj.toString().equals("1")) {
//						 updateButton = "<span class= 'color_red'>In Progress</span>";
//					}
//					 if(assignuserObj!=null && itTechApproveStatusObj.toString().equals("2")) {
//						 updateButton = "<span class= 'color_red'>Completed</span>";
//					}
					if (assignuserObj == null) {
					    // If assignuserObj is null, show the edit button
					    updateButton = "<i class='fa fa-edit color_red' data-id='" + rs.getObject(1).toString() + "' data-label='" + label1 + "' " +
					                   "title='Edit Data'></i>";
					} else {
					    // If assignuserObj is not null, check the status
					    if (itTechApproveStatusObj != null) {
					        String status = itTechApproveStatusObj.toString();
					        
					        if (status.equals("1")) {
					            updateButton = "<span class='color_red'>In Progress</span>";
					        } else if (status.equals("2")) {
					            updateButton = "<span class='color_green'>Completed</span>";
					            updateButton +="<i class='fa fa-eye color_red' data-id='" + rs.getObject(1).toString() + "' data-label='" + label1 + "' " +
						                   "title='View Data'></i>";
					        } else {
					            // If status is neither 1 nor 2, show "Assigned" by default
					            updateButton = "<span class='color_red'>Assigned</span>";
					        }
					    } else {
					        // If itTechApproveStatusObj is null, show "Assigned" by default
					        updateButton = "<span class='color_red'>Assigned</span>";
					    }
					}
					
					
				
					 approveButton = "<i class='action_icons action_approve' data-id='" + rs.getObject(1).toString() + "' data-label='" + label1 + "' " +
			                      "title='Approve/View Data'></i>";
//					 acceptButton = "<i class='fa fa-thumbs-o-up color_red blinking' data-id=' " + rs.getObject(1).toString() + "' data-label='" + label1 + "' " +
//		                      "title='acceptButton'></i>";
					 
					 
					
				     
//				         acceptButton = "<span style='position: relative; display: inline-block; cursor: pointer;'>" +
//				                              "<span class='accept color_red blinking' style='visibility: hidden; width: 120px; background-color: white; "
//				                              + "color: red; text-align: center; border-radius: 5px; padding: 5px; position: absolute; z-index: 1; bottom: 125%; left: 50%; "
//				                              + "margin-left: -60px; font-weight: bold;'>" +
//				                              "Accept!" +
//				                              "</span>" + // Custom tooltip
//				                              "</span>";
			
//					 if(rs.getObject(9).toString().equals(0) && !rs.getObject(9).toString().equals("")&& !rs.getObject(9).toString().equals(null)) {
//					 acceptButton = "<i class='fa fa-thumbs-o-up color_green blinking' data-id='" + rs.getObject(1).toString() + "' data-label='" + label1 + "' " +
//		                      "title='Approve/View Data'>Accepted!</i>";
//					 }else {
//						 acceptButton = "<i class='fa fa-thumbs-o-up color_red blinking' data-id='" + rs.getObject(1).toString() + "' data-label='" + label1 + "' " +
//			                      "title='Approve/View Data'>Please Accept!</i>";
//					 }
					 // Assuming it_tech_approve_status is the 9th column

					 if (itTechApproveStatusObj != null && itTechApproveStatusObj.toString().equals("0")) {
					     acceptButton = "<i class='fa fa-thumbs-o-up color_green blinking approve' data-id='" + rs.getObject(1).toString() + "' data-label='" + label1 + "' " +
					                    "title='Approve/View Data'>Accepted!</i>";
					 } 
					 
					 else if (itTechApproveStatusObj != null && itTechApproveStatusObj.toString().equals("1")) {
					     acceptButton = "<i class='fa fa-thumbs-o-down color_blue blinking approve' data-id='" + rs.getObject(1).toString() + "' data-label='" + label1 + "' " +
					                    "title='Approve/View Data'>In Progress!</i>";
					 }
					 else {
					     acceptButton = "<i class='fa  color_red blinking accept' data-id='" + rs.getObject(1).toString() + "' data-label='" + label1 + "' " +
					                    "title='Approve/View Data'>Please Accept!</i>";
					 
					 
					 }
					String f = "";
				
					f+= updateButton;
					f+= approveButton;
					
					if (!roleid.equals("527") && !roleid.equals("531")) {
						columns.put(metaData.getColumnLabel(1), updateButton);
					}
				else if(!roleid.equals("531")){
					
					columns.put(metaData.getColumnLabel(1), approveButton);
				
				}
				else {
					columns.put(metaData.getColumnLabel(1), acceptButton);
					
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
	}
	public HD_TB_BISAG_TICKET_GENERATE gethelpbyId(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query q = session.createQuery("from HD_TB_BISAG_TICKET_GENERATE where id=:id");
		q.setParameter("id", id);
		HD_TB_BISAG_TICKET_GENERATE list = (HD_TB_BISAG_TICKET_GENERATE) q.list().get(0);
		session.getTransaction().commit();
		session.close();
		return list;
	}
	public List<Map<String, Object>> getSearchMercuryList1(String msg, String valid_upto) {
		{
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Connection conn = null;
			String q = "";
			String qry = "";
			try {
				conn = dataSource.getConnection();
				PreparedStatement stmt = null;
				if (msg != "" && msg != null) {
					qry += " and msg = ?";
				}
				if (valid_upto != "" && valid_upto != null) {
					qry += " and valid_upto = ?";
				}
				q = "select msg, ltrim(TO_CHAR(valid_upto,'dd-mm-yyyy'),'0') as valid_upto,id from tb_hd_mercuries WHERE valid_upto >= now() and valid_upto <= valid_upto and status='1' "
						+ qry;
				stmt = conn.prepareStatement(q);
				int j = 1;
				if (msg != "" && msg != null) {
					stmt.setString(j, msg);
					j += 1;
				}
				if (valid_upto != "" && valid_upto != null) {
					stmt.setString(j, valid_upto);
				}
				ResultSet rs = stmt.executeQuery();
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				while (rs.next()) {
					Map<String, Object> columns = new LinkedHashMap<String, Object>();

					for (int i = 1; i <= columnCount; i++) {
						columns.put(metaData.getColumnLabel(i), rs.getObject(i));
					}
					String editData = "onclick=\"  if (confirm('Are you sure you want to update?') ){editData("
							+ rs.getString("id") + ",'" + rs.getString("msg") + "','" + rs.getString("valid_upto")
							+ "')}else{ return false;}\"";
					String updateButton = "<i class='action_icons action_update' " + editData
							+ " title='Edit Data'></i>";
					String f = "";
					f += updateButton;
					columns.put(metaData.getColumnLabel(3), f);
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
	}
	@SuppressWarnings("unchecked")
	public Boolean getmsgExist(String msg, Date valid_upto) {
		String hql = "from HD_MARQUEE where msg=:msg and valid_upto=:valid_upto ";
		List<HD_MARQUEE> users = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		try {
			Query query = session.createQuery(hql);
			query.setParameter("msg", msg);
			query.setParameter("valid_upto", valid_upto);
			users = (List<HD_MARQUEE>) query.list();
			tx.commit();
			session.close();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return null;
		}
		if (users.size() > 0) {
			return true;
		}
		return false;
	}
	public List<Map<String, Object>> getCompletedTicketCountJdbc(String qry) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement stmt = conn.prepareStatement(qry);
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
}
