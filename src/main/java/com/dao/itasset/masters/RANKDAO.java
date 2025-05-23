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

public interface RANKDAO {
	public List<Map<String, Object>> getReportListRANK(int startPage,String pageLength,String Search,String orderColunm,String orderType,String rank,String status, HttpSession session) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException;

	public long getReportListRANKTotalCount(String Search,String rank,String status);
	public String DeleteRANK(String deleteid,HttpSession session);
	public ArrayList<ArrayList<String>> Report_DataTableMakeRankList(String rank, String status);

}
