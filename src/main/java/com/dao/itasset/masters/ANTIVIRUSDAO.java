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


public interface ANTIVIRUSDAO {

public List<Map<String, Object>> getReportListANTIVIRUS(int startPage,String pageLength,String Search,String orderColunm,String orderType,String antivirus,HttpSession session) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException;
public long getReportListANTIVIRUSTotalCount(String Search,String antivirus);
public String DeleteANTIVIRUS(String deleteid,HttpSession session);
public ArrayList<ArrayList<String>> Report_DataTableMakeList(String antivirus);
}
