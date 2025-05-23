package com.dao.Assets;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

public interface Peripheral_Hardware_DAO {
	
	public List<Map<String, Object>> Search_Peripheral(int startPage,int pageLength,String Search,String orderColunm,
			String orderType,String status,String assets_type,String year_of_manufacturing,String machine_make,String machine_no,String model_no,
			String from_date,String to_date,HttpSession sessionUserId);
	
	public long getPeripheralCountList(String Search,String orderColunm,String orderType,String status,String assets_type,String year_of_manufacturing,String machine_make,String machine_no,
			String from_date,String to_date,String model_no,HttpSession sessionUserId);

}
