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


public interface Ram_mstrDAO {

public List<Map<String, Object>> getReportListRam_mstr(int startPage,String pageLength,String Search,String orderColunm,String orderType,String ram,String ram_capacity,HttpSession session) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException;
public long getReportListRam_mstrTotalCount(String Search,String ram,String ram_capacity);
public String Deleteram_mstr(String deleteid,HttpSession session);
public ArrayList<ArrayList<String>> Report_DataTableMakeList(String ram);
}
