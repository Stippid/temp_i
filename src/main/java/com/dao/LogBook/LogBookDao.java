package com.dao.LogBook;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpSession;

public interface LogBookDao {

	List<Map<String, Object>> getLogBookReportDataList(int startPage, String pageLength, String search,
			String orderColunm, String orderType, String catgory, String make_name, String model_name, String status,
			HttpSession sessionUserId) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException;

	long getLogBookTotalCount(String search, String catgory, String make_name, String model_name, String status,
			HttpSession session);

	public ArrayList<ArrayList<String>> LogBookData(String logbookId,String category);

}
