package com.dao.Peripheral;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

public interface PeripheralDao {

	public List<Map<String, Object>> Search_Peripheral(int startPage,int pageLength,String Search,String orderColunm,
			String orderType,String status,String assets_type,String year_of_manufacturing,String machine_make,String machine_no,
			String from_date,String to_date,String s_state,String section,HttpSession sessionUserId);

	public long getPeripheralCountList(String Search,String orderColunm,String orderType,String status,String assets_type,String year_of_manufacturing,String machine_make,String machine_no,
			String from_date,String to_date,String s_state,String section,HttpSession sessionUserId);
	public String approve_AssetsData(String a,String user_sus, String status,String username);

	public List<Map<String, Object>> getAppPeripheralList(int startPage,int pageLength,String Search,String orderColunm,String orderType,String status,
			String assets_type,String year_of_manufacturing,String machine_make,String machine_no,
			String section,String from_date,String to_date,HttpSession sessionUserId) throws SQLException;

	public long getAppPeripheralCountList(String Search,String orderColunm,String orderType,String status,String assets_type,String year_of_manufacturing,String machine_make,String machine_no,
			String section,String from_date,String to_date,HttpSession sessionUserId)throws SQLException;


	public List<Map<String, Object>> getAppPeripheralChildList(String p_id);
	public List<Map<String, Object>> getPeriChildStatus (int id );
	public List<Map<String, Object>> getAppChildPeriList(String p_id);
	public List<Map<String, Object>> GetdataPeri(int id) throws SQLException;

	public ArrayList<ArrayList<String>> getcomaseptext_network_feature(int id);
	public ArrayList<ArrayList<String>> getcomaseptext_hw_interface(int id);


	public List<Map<String, Object>> SearchDelete_Peripheral(int startPage,int pageLength,String Search,String orderColunm,
			String orderType,String status,String assets_type,String year_of_manufacturing,String machine_make,String machine_no,
			String from_date,String to_date,String s_state,String section,HttpSession sessionUserId);
	public long getPeripheralCountListDelete(String Search,String orderColunm,String orderType,String status,String assets_type,String year_of_manufacturing,String machine_make,String machine_no,
			String from_date,String to_date,String s_state,String section,HttpSession sessionUserId);

	public ArrayList<ArrayList<String>> Excel_Peripheral_Assets_Search(HttpSession session,
			String machine_number, String assets_type, String status, String s_state, String section,
			String year_of_manufacturing);
	public ArrayList<ArrayList<String>> Excel_Peripheral_Assets_Search_app(HttpSession session,
			String machine_number, String assets_type, String status, String s_state, String section,
			String year_of_manufacturing);



	public Integer getperipheralassetcount(String section, String assets_type,String type_of_hw, String make_name, String model_name);
	public int getTotalAssetsAssigned(String section, String assets_type,String type_of_hw, String make_name, String model_name);

	public List<Map<String, Object>> Search_peripheral_Assets_Assign(int startPage,int pageLength,String Search,
			String orderColunm,String orderType,String assets_type,String section,String status, HttpSession session);
	public long getAppperipheralassetassignCountList1(String Search,String orderColunm,String orderType,String assets_type,
			String section,String status,HttpSession sessionUserId)throws SQLException;

	public String approve_peripheralAssetsAssignData(String a, String status);


	public ArrayList<ArrayList<String>> excel_Peripheral_Assets_AssignReportDataList(HttpSession session,
			String assets_type, String status,  String section);




}
