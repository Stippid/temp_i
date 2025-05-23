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


public interface Dply_env_mstrDAO {

public List<Map<String, Object>> getReportListDply_env_mstr(int startPage,String pageLength,String Search,String orderColunm,String orderType,String dply_env,HttpSession session) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException;
public long getReportListDply_env_mstrTotalCount(String Search,String dply_env);
public String Deletedply_env_mstr(String deleteid,HttpSession session);
public ArrayList<ArrayList<String>> Report_DataTableMakeList(String dply_env);
}
