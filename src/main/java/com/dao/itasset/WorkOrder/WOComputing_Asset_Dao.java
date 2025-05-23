package com.dao.itasset.WorkOrder;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpSession;

import com.models.itasset.assets.Assets_Main;

 

public interface WOComputing_Asset_Dao {
	
	public List<Map<String, Object>> Computing_Asset_ReportDataListDetails(int startPage,String pageLength,String Search,String orderColunm,String orderType,
			String asset_type, String make_name, String model_name,String status,HttpSession session) throws InvalidKeyException, NoSuchAlgorithmException,NoSuchPaddingException, InvalidKeySpecException, 
	                             InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException;

	public long Computing_Asset_TotalCountDtl(String Search,String asset_type, String make_name, String model_name,String status);
	
	  public Assets_Main getComputingAssesByid(int id);
	  public ArrayList<ArrayList<String>> GetComputingAsset_PDF(String id,String wo_id);
	  public ArrayList<ArrayList<String>> GetLast_WO_no_dt(String id,String category_type);
	  
	  public List<Map<String, Object>> Pop_UP_Computing_Asset_History_ReportDataList(int startPage,String pageLength,String Search,String orderColunm,String orderType,
			  String assets_type,String wo_no,String wo_dt,String dt_eqpt_reqd_fwd_wksp,String wksp_unit_name,String defects_obs,HttpSession session) throws InvalidKeyException, NoSuchAlgorithmException,NoSuchPaddingException, InvalidKeySpecException, 
		                             InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException;

		public long Pop_UP_Computing_Asset_History_List_TotalCount(String Search,String assets_type,String wo_no,String wo_dt,String dt_eqpt_reqd_fwd_wksp,String wksp_unit_name,String defects_obs);
		  public List<Map<String, Object>> pop_up_detail_Computing_Asset_History_RecordDataList(HttpSession session,int p_id) throws InvalidKeyException, NoSuchAlgorithmException,NoSuchPaddingException, InvalidKeySpecException, 
			                             InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException;
		  
//		  public ArrayList<ArrayList<String>> getComputingDetails(String wksp_unit_name,String wo_dt,String dt_eqpt_reqd_fwd_wksp);
		  public ArrayList<ArrayList<String>> getComputingDetails(String hid,String wksp_unit_name,String wo_dt,String dt_eqpt_reqd_fwd_wksp);
		  public ArrayList<ArrayList<String>> getComputingDetails1(String hid,String wksp_unit_name,String wo_dt,String dt_eqpt_reqd_fwd_wksp);
}
