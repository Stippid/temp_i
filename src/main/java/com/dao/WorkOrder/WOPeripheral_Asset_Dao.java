package com.dao.WorkOrder;

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

import com.models.assets.It_Asset_Peripherals;



 

public interface WOPeripheral_Asset_Dao {
	public List<Map<String, Object>> Peripheral_Asset_ReportDataListDetails(int startPage,String pageLength,String Search,String orderColunm,String orderType,
			String asset_type, String make_name, String model_name,String status,HttpSession session) throws InvalidKeyException, NoSuchAlgorithmException,NoSuchPaddingException, InvalidKeySpecException, 
	                             InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException;

	public long Peripheral_Asset_TotalCountDtl(String Search,String asset_type, String make_name, String model_name,String status,HttpSession session);
	 public It_Asset_Peripherals getPeripheralAssesByid(int id);
	 public ArrayList<ArrayList<String>> GetPeripheral_Asset_PDF(String p_id ,String wo_id);
	 
	 public long Pop_UP_Peripheral_Asset_History_List_TotalCount(String Search,String assets_type,String wo_no,String wo_dt,String dt_eqpt_reqd_fwd_wksp,String wksp_unit_name,String defects_obs,HttpSession sessionUserId) ;

	 public List<Map<String, Object>> Pop_UP_Peripheral_Asset_History_ReportDataList(int startPage,String pageLength,String Search,String orderColunm,String orderType,String assets_type,String wo_no,String wo_dt,String dt_eqpt_reqd_fwd_wksp,String wksp_unit_name,String defects_obs,HttpSession session) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException ;
	  public List<Map<String, Object>> pop_up_detail_Peripheral_Asset_History_RecordDataList(HttpSession session,int p_id) throws InvalidKeyException, NoSuchAlgorithmException,NoSuchPaddingException, InvalidKeySpecException, 
			                             InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException;
		  
		  
		  public ArrayList<ArrayList<String>> getPeripheralDetails(String hid,String wksp_unit_name,String wo_dt,String dt_eqpt_reqd_fwd_wksp);
		  public ArrayList<ArrayList<String>> getPeripheralDetails1(String hid,String wksp_unit_name,String wo_dt,String dt_eqpt_reqd_fwd_wksp);

			public String Update_Wo_Peripheral();
}
