package com.dao.itasset.masters;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;


import javax.servlet.http.HttpSession;


public interface Office_mstrDAO {

public List<Map<String, Object>> getReportListOffice_mstr(int startPage,String pageLength,String Search,String orderColunm,String orderType,String office,HttpSession session) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException;
public long getReportListOffice_mstrTotalCount(String Search,String office);
public String Deleteoffice_mstr(String deleteid,HttpSession session);
public ArrayList<ArrayList<String>> Report_DataTableMakeList(String office);


public List<Map<String, Object>> getReportListOffice_Other_mstr(int startPage,String pageLength,String Search,String orderColunm,String orderType,String office,String status,HttpSession session) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException;
public long getReportListOffice_Otrher_mstrTotalCount(String Search,String office,String status);
public String approve_Office_Data(String a,String user_sus,String status,String username);
public String reject_Office_Data(String a);
}
