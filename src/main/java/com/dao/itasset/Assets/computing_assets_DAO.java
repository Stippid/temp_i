package com.dao.itasset.Assets;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

public interface computing_assets_DAO {
	
	public List<Map<String, Object>> Search_Computing_Assets(int startPage,int pageLength,String Search,String orderColunm,String orderType,String status,String assets_type,String model_number,String machine_number,String ram_capi,String hdd_capi,String operating_system,String from_date,String to_date,String s_state,String unit_sus_no, String unit_name,String section, HttpSession session);
	public String approve_AssetsData(String a,String user_sus,String status,String username);
	public List<Map<String, Object>> getAppComputingassetList(int startPage,int pageLength,String Search,String orderColunm,String orderType,String status,String assets_type,
			String model_number,String machine_number,String ram_capi,String hdd_capi,String operating_system,String unit_sus_no,String from_date,String to_date,
			String section,HttpSession sessionUserId)throws SQLException;
	public List<Map<String, Object>> getAppChildComputingassetList(String p_id);
	public ArrayList<ArrayList<String>> Search_DataTableList(HttpSession session);
	public ArrayList<ArrayList<String>> Search_DataTableList1(HttpSession session);
	public List<Map<String, Object>> GetdataComputing(int id) throws SQLException;
	 public long getAppComputingassetCountList(String Search,String orderColunm,String orderType,String status,String assets_type,String model_number,
				String machine_number,String ram_capi,String hdd_capi,String operating_system,String unit_sus_no,String from_date,String to_date,String section,HttpSession sessionUserId)throws SQLException;

	 public long getAppComputingassetCountList1(String Search,String orderColunm,String orderType,String status,String assets_type,String model_number,
				String machine_number,String ram_capi,String hdd_capi,String operating_system,String from_date,String to_date,String s_state,String unit_sus_no, 
				String unit_name,String section,HttpSession sessionUserId)throws SQLException;
	 public List<Map<String, Object>> Search_Computing_AssetsDelete(int startPage,int pageLength,String Search,String orderColunm,String orderType,String status,String assets_type,String model_number,String machine_number,String ram_capi,String hdd_capi,String operating_system,String from_date,String to_date,
			 String s_state,String unit_sus_no, String unit_name, String section,HttpSession session);
	 public long getAppComputingassetCountList1Delete(String Search,String orderColunm,String orderType,String status,String assets_type,String model_number,
				String machine_number,String ram_capi,String hdd_capi,String operating_system,String from_date,String to_date,String s_state,String unit_sus_no, String section,String unit_name,HttpSession sessionUserId)throws SQLException;
	public ArrayList<ArrayList<String>> excel_Computing_Assets_ReportDataList(HttpSession session, String model_number,
			String machine_number, String assets_type, String status, String s_state, String section);

//	 public List<Map<String, Object>> approve_Master_Asset(String a) throws SQLException;
}