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


public interface Processor_or_typeDAO {

public List<Map<String, Object>> getReportListProcessor_or_type(int startPage,String pageLength,String Search,String orderColunm,String orderType,String processor_type,HttpSession session) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException;
public long getReportListProcessor_or_typeTotalCount(String Search,String processor_type);
public String DeleteProcessor_or_type(String deleteid,HttpSession session);
public ArrayList<ArrayList<String>> Report_DataTableMakeList(String processor_type);


public List<Map<String, Object>> getReportListProcessor_or_type_other(int startPage,String pageLength,String Search,String orderColunm,String orderType,String processor_type,String status,HttpSession session) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException;
public long getReportListProcessor_or_typeTotalCount_other(String Search,String processor_type,String status);
public String approve_ProcessorData(String a,String user_sus,String status,String username);
public String reject_ProcessorData(String a);
}
