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

public interface Supply_MasterDAO {

public List<Map<String, Object>> getReportListSUPPLY(int startPage,String pageLength,String Search,String orderColunm,String orderType,String supplier, String contact_number,HttpSession session) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException;
public long getReportListSUPPLYTotalCount(String Search,String supplier,String contact_number);
public String DeleteSUPPLY(String deleteid,HttpSession session);
public ArrayList<ArrayList<String>> Report_SupplyDataTableMakeList(String supplier, String contact_number);

}
