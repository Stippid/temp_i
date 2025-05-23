package com.dao.itasset.masters;
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


public interface Year_of_proc_mstrDAO {

public List<Map<String, Object>> getReportListYear_of_proc_mstr(int startPage,String pageLength,String Search,String orderColunm,String orderType,HttpSession session) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException;
public long getReportListYear_of_proc_mstrTotalCount(String Search);
public String Deleteyear_of_proc_mstr(String deleteid,HttpSession session);
}
