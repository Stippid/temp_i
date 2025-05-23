package com.dao.itasset.masters;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.models.itasset.master.TB_MASTER_MODEL;
 



public interface ModelDAO {
	
	public List<Map<String, Object>> DataTableModelList(int startPage,int pageLength,String Search,String orderColunm,String orderType, 
			String category, String assets_name, String make_name,String model_name);
	public long DataTableModelTotalCount(String Search,String category, String assets_name, String make_name,String model_name);
	public TB_MASTER_MODEL getModelByid(int id);
	public ArrayList<ArrayList<String>> Report_DataTableMakeList(String category, String assets_name,String make_name,String model_name);
	
	 public List<Map<String, Object>> DataTableModelOtherList(int startPage,int pageLength,String Search,String orderColunm,String orderType,
	    		String category, String assets_name, String make_name,String model_name,String status);
	 public long DataTableModelOtherTotalCount(String Search,String category, String assets_name, String make_name,String model_name,String status);
	public String approve_ModelData(String a,String user_sus,String status,String username);
	public String delete_ModelData(String a);
}
