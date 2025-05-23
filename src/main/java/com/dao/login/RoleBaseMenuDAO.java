package com.dao.login;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpSession;

import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.models.Role;
import com.models.UserLogin;

public interface RoleBaseMenuDAO {
	public int VisitorCounter();
	public ArrayList<ArrayList<String>> getModulelist(String roleid);
	public ArrayList<ArrayList<String>> getSubModulelist(String  moduleid,String roleid);
	public ArrayList<ArrayList<String>> getScreenlist(String  moduleid,String  submoduleid,String roleid);
	public String getTodaysLogin();
	public Boolean getmoduleExist(String v);
	public Boolean getroleExist(String v);
	public Boolean getsubmoduleExist(String name,int m_id,int sm_id);
	public Boolean getscreenExist(String name,String url,int i,int j,String sc_id);
	public Boolean getscreenSubExist(String name,String url,String module_id,String screen_submodule_id);
	public Boolean getuserExist(String name);
	public Boolean ScreenRedirect(String name,String roleid);
	public Boolean CheckDashboard(String name,String roleid);
	public Boolean getlinkroleExist(int m_id,int sm_id,int s_id,int rid);
	//public  List<Map<String, Object>> getReportSubmoduleList();
	public void selectChild(String name,String url,String m_id,String sm_id);
	public void deleteChild(int sid);
	
	
	
	public  List<Map<String, Object>> RoleSearchReport() ;
	public  List<Map<String, Object>> ModuleSearchReport() ;
	public  List<Map<String, Object>> SubModuleSearchReport() ;
	public  List<Map<String, Object>> ScreenSearchReport(String m_id,String sm_id);
	
	public Role getRoleNameListbyid(int role_id);

	public  List<Map<String, Object>> SearchUserBbyRole1(String access_lvl,String subaccess_lvl,String user_name,String user_sus_no);
	

	public String getActiveData(String userid); // user Active 
	public String getDeactiveData(String userid); // user Deactive 

	 public  List<Map<String, Object>> getReportStatusOfInactiveUserList();
	 public String  userinsertdata(String type,int did,int roll);
	 
	 public List<Map<String, Object>> getUserReport();
		public UserLogin getUserLoginbyid(int id);
		public List<Map<String, Object>> getRole(int updateid);
		
		
		 public Boolean DashboardRedirect(String name,String roleid);
		 
		 public DataSet<Map<String, Object>> DatatablesCriteriasUserreport(DatatablesCriterias criterias , String qry,String roleSubAccess);
		 
		 public DataSet<Map<String, Object>> DatatablesCriteriasActiveUserreport(DatatablesCriterias criterias , String qry,String roleSubAccess,String Status);
		 
		 /*public String submodulenamebyscreen(String roleid, String url);*/
		 
		 public List<String> getLayoutlist();
		 
		 public List<Map<String, Object>> getLine_DteList(String line_dte_sus);
		 public List<String> nMgtSct(HttpSession n1);	
		 public List<Map<String, Object>> getLine_DteListsus();
			public List<Map<String, Object>> getLine_DteList11(String line_dte_sus);
		 public List<Map<String, Object>> getLine_DteListit_ass(String line_dte_sus);
		 public ArrayList<ArrayList<String>> GetUsernameandRank(String username1);
		 public String UpdateUserMst(UserLogin updateid, int roll,String user_arm_code,String user_sus_no,String formation_code,String access_lve1,String sub_access_lve1,String name,String rank,String username);

		 public List<Map<String, Object>> get_comnd_wise_count_list(HttpSession sessionUserId) throws SQLException;
		public long getgetUserTotalCount(String search, String login_name_srch, String sectionList, String access_lvl,String s_state,String name_search,String status_val);
		public List<Map<String, Object>> getUserReportList(int startPage, String pageLength, String search,
				String orderColunm, String orderType, String login_name_srch, String sectionList, String access_lvl,String s_state,String name_search,String status,
				HttpSession sessionUserId) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException;
}

