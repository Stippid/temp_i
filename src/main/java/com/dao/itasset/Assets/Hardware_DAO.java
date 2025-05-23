package com.dao.itasset.Assets;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpSession;

public interface Hardware_DAO {
	
	public List<Map<String, Object>> searchhardware(int startPage,String pageLength,String Search,String orderColunm,String orderType,String assets_type,String b_head,String b_cost,HttpSession session) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException;
	public long gethardwareTotalCount(String Search,String orderColunm,String orderType,String assets_type,String b_head,String b_cost,HttpSession sessionUserId);
	public List<Map<String, Object>> getassetdata(String id);
}
