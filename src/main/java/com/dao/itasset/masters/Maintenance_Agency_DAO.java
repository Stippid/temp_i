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

public interface Maintenance_Agency_DAO  {
	public List<Map<String, Object>> getReportListMAINTENANCE(int startPage,String pageLength,String Search,String orderColunm,String orderType,String maintenance,HttpSession session) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException;
	public long getReportListMAINTENANCETotalCount(String Search,String maintenance);
	public String DeleteMAINTENANCE(String deleteid,HttpSession session);
	public ArrayList<ArrayList<String>> Report_DataTableMakeListM(String maintenance);

}
