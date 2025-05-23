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

public interface Ethernet_Port_DAO {
	
	public List<Map<String, Object>> getReportListEthernet_port(int startPage, String pageLength, String Search,
			String orderColunm, String orderType, HttpSession session,String ethernet_port)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException;

	public long getReportListEthernet_portTotalCount(String Search,String ethernet_port);
	
	public String Delete_Ethernet_port(String deleteid, HttpSession session1);
	public ArrayList<ArrayList<String>> Report_DataTableMakeList(String ethernet_port);

}
