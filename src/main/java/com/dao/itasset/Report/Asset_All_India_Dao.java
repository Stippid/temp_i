package com.dao.itasset.Report;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

public interface Asset_All_India_Dao {
	public List<Map<String, Object>> getallIndiaHoldingList(int startPage,int pageLength,String Search,String orderColunm,String orderType,String asset_type,
			String b_head,String b_code,String a_type,String line_dte,HttpSession sessionUserId,
			 String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String s_state) throws SQLException;
	 public long getallAssetIndiaHoldingCountList(String Search,String orderColunm,String orderType,String asset_type,String b_head,String b_code,String a_type,String line_dte,HttpSession sessionUserId,
			 String cont_comd,String cont_corps,
				String cont_div,String cont_bde,String unit_name,String sus_no,String s_state)throws SQLException;
	 public ArrayList<ArrayList<String>> pdf_all_india_asset_holding_ReportDataList(String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String asset_type,String a_type,String b_head,String b_code,String line_dte,HttpSession sessionUserId,String s_state) ;
}
