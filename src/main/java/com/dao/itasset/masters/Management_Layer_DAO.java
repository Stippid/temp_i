package com.dao.itasset.masters;

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

public interface Management_Layer_DAO {
	
	public List<Map<String, Object>> getReportListManagement_Layer(int startPage, String pageLength, String Search,
			String orderColunm, String orderType, HttpSession session,String management_layer)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException;

	public long getReportListManagement_LayerTotalCount(String Search,String management_layer);
	
	public String Delete_Management_Layer(String deleteid, HttpSession session1);
	
	public ArrayList<ArrayList<String>> Report_DataTableMakeList(String management_layer);


}
