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

public interface Network_Features_DAO {
	
	public List<Map<String, Object>> getReportListNetwork_Features(int startPage, String pageLength, String Search,
			String orderColunm, String orderType, HttpSession session,String network_feature)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException;

	public long getReportListNetwork_FeaturesTotalCount(String Search,String network_feature);
	
	public String Delete_Network_Features(String deleteid, HttpSession session1);
	public ArrayList<ArrayList<String>> Report_DataTableMakeList(String network_feature);
}
