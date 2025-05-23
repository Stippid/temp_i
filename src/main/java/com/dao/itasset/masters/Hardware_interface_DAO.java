package com.dao.itasset.masters;

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

public interface Hardware_interface_DAO {

	public List<Map<String, Object>> getReportListHardwareInterface(int startPage, String pageLength, String Search,
			String orderColunm, String orderType, HttpSession session,String hardware_interface)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException;

	public long getReportListHardwareInterfaceTotalCount(String Search,String hardware_interface);
	
	public String Delete_Hardware_Interface(String deleteid, HttpSession session1);
	public ArrayList<ArrayList<String>> Report_DataTableMakeList(String hardware_interface);
}
