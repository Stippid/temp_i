package com.dao.itasset.Report;

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

public interface IT_Assets_Serviceable_Unserviceable_DAO {

	public ArrayList<List<String>> getCorpsList(String fcode);
	public List<Map<String, Object>> getIT_Assets_Serviceable_Unserviceable_ReportList(int startPage,String pageLength,String Search,String orderColunm,String orderType,
		String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String assets_type,String assets_name,HttpSession session) 
		throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException ;
	public long getIT_Assets_Serviceable_Unserviceable_TotalCountDtl(String Search,String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String assets_type,String assets_name);
	public ArrayList<ArrayList<String>> PDF_IT_Assets_Serviceable_Unserviceable_Report(String cont_comd,String cont_corps,String cont_div,
			String cont_bde,String unit_name,String sus_no,String assets_type);
}
