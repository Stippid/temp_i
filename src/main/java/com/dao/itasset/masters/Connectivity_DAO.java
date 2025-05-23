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

public interface Connectivity_DAO {
	
	public List<Map<String, Object>> getReportListConnectivity(int startPage, String pageLength, String Search,
			String orderColunm, String orderType, HttpSession session,String connectivity_type)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException;

	public long getReportListConnectivityTotalCount(String Search,String connectivity_type);
	
	public String Delete_Connectivity(String deleteid, HttpSession session1);
	public ArrayList<ArrayList<String>> Report_DataTableMakeList(String connectivity_type);
	
	
	
	public List<Map<String, Object>> DataTableConnectivityOtherList(int startPage,int pageLength,String Search,String orderColunm,String orderType,
			String connectivity_type,String status);
	public long DataTableConnectivityOtherTotalCount(String Search,String connectivity_type,String status);
	public String approve_ConnectivityData(String a,String user_sus,String status,String username);
	public String reject_ConnectivityData(String a);

}

