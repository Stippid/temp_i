package com.dao.itasset.masters;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.models.itasset.master.TB_MASTER_MAKE;
 

 

public interface Make_Dao {
	public List<Map<String, Object>> DataTableMakeList(int startPage,int pageLength,String Search,String orderColunm,String orderType,
			String category, String assets_name,String make_name);
	public long DataTableMakeTotalCount(String Search,String category,String assets_name,String make_name);
	public TB_MASTER_MAKE getMakeByid(int id);
	public ArrayList<ArrayList<String>> Report_DataTableMakeList(String category, String assets_name,String make_name);
	
	
	public List<Map<String, Object>> DataTableMakeOtherList(int startPage,int pageLength,String Search,String orderColunm,String orderType,
			String category, String assets_name,String make_name,String status);
	public long DataTableMakeOtherTotalCount(String Search,String category,String assets_name,String make_name,String status);
	public String approve_MakeData(String a,String user_sus,String status,String username);
	public String reject_MakeData(String a);

}
