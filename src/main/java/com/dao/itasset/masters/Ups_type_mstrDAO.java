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


public interface Ups_type_mstrDAO {

public List<Map<String, Object>> getReportListUps_type_mstr(int startPage,String pageLength,String Search,String orderColunm,String orderType,String ups_type,HttpSession session) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException;
public long getReportListUps_type_mstrTotalCount(String Search,String ups_type);
public String Deleteups_type_mstr(String deleteid,HttpSession session);
public ArrayList<ArrayList<String>> Report_DataTableMakeList(String ups_type);
}
