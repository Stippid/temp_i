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


public interface Type_mstrDAO {

public List<Map<String, Object>> getReportListType_mstr(int startPage,String pageLength,String Search,String orderColunm,String orderType,String peripheral_type,String type_of_hw,String type,HttpSession session) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException;
public long getReportListType_mstrTotalCount(String Search,String peripheral_type,String type_of_hw,String type);
public String Deletetype_mstr(String deleteid,HttpSession session);
public ArrayList<ArrayList<String>> Report_DataTableMakeList(String peripheral_type,String type_of_hw,String type);

public List<Map<String, Object>> getReportListType_other_mstr(int startPage,String pageLength,String Search,String orderColunm,String orderType,String peripheral_type,String type_of_hw,String type,String status,HttpSession session) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException;
public long getReportListType_other_mstrTotalCount(String Search,String peripheral_type,String type_of_hw,String type,String status);
public String approve_type_of_hw_model_type_Data(String a,String user_sus,String status,String username);
public String reject_type_of_hw_model_type_Data(String a);

}
