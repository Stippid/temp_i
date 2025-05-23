package com.dao.itasset.masters;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;


import javax.servlet.http.HttpSession;


public interface Asset_mstrDAO {

public List<Map<String, Object>> getReportListAsset_mstr(int startPage,String pageLength,String Search,String orderColunm,String orderType, String assets_name,String category,HttpSession session) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException;
public long getReportListAsset_mstrTotalCount(String Search, String assets_name,String category);
public String Deleteasset_mstr(String deleteid,HttpSession session);

public ArrayList<ArrayList<String>> Report_DataTableMakeList(String assets_name,String category);


public List<Map<String, Object>> Search_consumable(int startPage,int pageLength,String Search,String orderColunm,
		String orderType,String status,String assets_type,String section,String year_of_manufacturing,HttpSession sessionUserId);

public long getconsumableCountList(String Search,String orderColunm,String orderType,String status,String assets_type,String section,String year_of_manufacturing,HttpSession sessionUserId);

public ArrayList<ArrayList<String>> getcomaseptext_network_feature(int id);
public ArrayList<ArrayList<String>> getcomaseptext_hw_interface(int id);

public ArrayList<ArrayList<String>> Excel_Consumables_Assets_Search(HttpSession session, String assets_type,
		String status, String section,
		String year_of_manufacturing);



public String approve_consumable_AssetsData(String a,String user_sus, String status,String username);



//public ArrayList<ArrayList<String>> Excel_issue_Consumables_Assets_Search(HttpSession session, String section,
//		String assets_type, String crv_no, String assetcount, String make_name, String model_name,  String status);
//		

public ArrayList<ArrayList<String>> Excel_issue_Consumables_Assets_Search(HttpSession session, String section,
		String assets_type, String crv_no, String status);
		
		




		public List<Map<String, Object>> Search_issue_consumable(int startPage,int pageLength,String Search,String orderColunm,
		String orderType,String section,String assets_type,
		 String crv_no,String status,HttpSession sessionUserId);


public long getissueconsumableCountList(String Search,String orderColunm,String orderType,String section,String assets_type,
		 String crv_no,String status,HttpSession sessionUserId);



}
