package com.dao.Assets;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

public interface interUnitTransf_DAO {
	
	public List<Map<String, Object>> GetMachine_noDataComp(String machine_no);
	public List<Map<String, Object>> GetMachine_noDataPerif(String machine_no);
	public List<Map<String, Object>> GetAssetTransfDataPerif(String roleType, String from_sus_no,String to_sus_no,String machine_no, String Status);
	public List<Map<String, Object>> GetAssetTransfDataComp(String roleType, String from_sus_no,String to_sus_no,String machine_no, String Status);
	public List<Map<String, Object>> getmachine_no_CompListToTransf(String sus_no);
	public List<Map<String, Object>> getmachine_no_perifListToTransf(String sus_no);
	
}
