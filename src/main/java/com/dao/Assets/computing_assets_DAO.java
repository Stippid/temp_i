package com.dao.Assets;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

public interface computing_assets_DAO {
	
	public List<Map<String, Object>> Search_Computing_Assets(int startPage,int pageLength,String Search,String orderColunm,String orderType,String status,String assets_type,String machine_number,String ram_capi,String hdd_capi,String operating_system,String from_date,String to_date,String s_state,String unit_sus_no, String unit_name,String section, HttpSession session);
	public String approve_AssetsData(String a,String user_sus,String status,String username);
	public List<Map<String, Object>> getAppComputingassetList(int startPage,int pageLength,String Search,String orderColunm,String orderType,String status,String assets_type,
		String machine_number,String ram_capi,String hdd_capi,String operating_system,String unit_sus_no,String from_date,String to_date,
			String section,HttpSession sessionUserId)throws SQLException;
	public List<Map<String, Object>> getAppChildComputingassetList(String p_id);
	public ArrayList<ArrayList<String>> Search_DataTableList(HttpSession session);
	public ArrayList<ArrayList<String>> Search_DataTableList1(HttpSession session);
	public List<Map<String, Object>> GetdataComputing(int id) throws SQLException;
	 public long getAppComputingassetCountList(String Search,String orderColunm,String orderType,String status,String assets_type,
				String machine_number,String ram_capi,String hdd_capi,String operating_system,String unit_sus_no,String from_date,String to_date,String section,HttpSession sessionUserId)throws SQLException;

	 public long getAppComputingassetCountList1(String Search,String orderColunm,String orderType,String status,String assets_type,
				String machine_number,String ram_capi,String hdd_capi,String operating_system,String from_date,String to_date,String s_state,String unit_sus_no, 
				String unit_name,String section,HttpSession sessionUserId)throws SQLException;
	 public List<Map<String, Object>> Search_Computing_AssetsDelete(int startPage,int pageLength,String Search,String orderColunm,String orderType,String status,String assets_type,String machine_number,String ram_capi,String hdd_capi,String operating_system,String from_date,String to_date,
			 String s_state,String unit_sus_no, String unit_name, String section,HttpSession session);
	 public long getAppComputingassetCountList1Delete(String Search,String orderColunm,String orderType,String status,String assets_type,
				String machine_number,String ram_capi,String hdd_capi,String operating_system,String from_date,String to_date,String s_state,String unit_sus_no, String section,String unit_name,HttpSession sessionUserId)throws SQLException;
	public ArrayList<ArrayList<String>> excel_Computing_Assets_ReportDataList(HttpSession session, 
			String machine_number, String assets_type, String status, String s_state, String section);
	
	public Integer getassetcount(String section,String asset_type,String make_name,String model_name,
			String processor_type,String ram_capi,String hdd_capi,
			String ssdcapi,String operating_system);
//	 public List<Map<String, Object>> approve_Master_Asset(String a) throws SQLException;
	
	public List<Map<String, Object>> Search_Computing_Assets_Assign(int startPage,int pageLength,String Search,
			String orderColunm,String orderType,String computing_asset_type,String section,String assets_uniq, HttpSession session);

	 public long getAppComputingassetassignCountList1(String Search,String orderColunm,String orderType,String assets_type,
				String section,String assets_uniq,HttpSession sessionUserId)throws SQLException;
	 

	 
	 public String approve_AssetsAssignData(String a,String status);
	 
	
	 public int getcountqtyassignuser(String section, String asset_type, String make_name, String model_name,
             String processor_type, String ram_capi, String hdd_capi, String ssd_capi,
             String operating_system);
	 
	 public int getTotalAssetsAssigned(String section, String asset_type, String make_name, String model_name, String processor_type, String ram_capi, String hdd_capi, String ssdcapi, String operating_system);
		public ArrayList<ArrayList<String>> excel_Computing_Assets_AssignReportDataList(HttpSession session, 
				 String assets_type, String status,  String section);
}
