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


public interface Os_mstrDAO {

public List<Map<String, Object>> getReportListOs_mstr(int startPage,String pageLength,String Search,String orderColunm,String orderType,String os,HttpSession session) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException;
public long getReportListOs_mstrTotalCount(String Search,String os);
public String Deleteos_mstr(String deleteid,HttpSession session);
public ArrayList<ArrayList<String>> Report_DataTableMakeList(String os);

public List<Map<String, Object>> getReportListOs_Other_mstr(int startPage,String pageLength,String Search,String orderColunm,String orderType,String os,String status,HttpSession session) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException;
public long getReportListOs_Other_mstrTotalCount(String Search,String os,String status);
public String approve_OS_Data(String a,String user_sus,String status,String username);
public String reject_OS_Data(String a);
}
