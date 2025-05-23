package com.dao.itasset.Report;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

public interface UnserviceableDao {
	
	
	public List<Map<String, Object>> getallIndiaHoldingList(int startPage,int pageLength,String Search,String orderColunm,String orderType,String asset_type,String b_head,String b_code,String a_type,HttpSession sessionUserId,
			String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no) throws SQLException;
	public long getallIndiaHoldingCountList(String Search,String orderColunm,String orderType,String asset_type,String b_head,String b_code,String a_type,HttpSession sessionUserId,
			String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no)throws SQLException;
	public ArrayList<ArrayList<String>> pdf_all_india_holding_ReportDataList(String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String asset_type,String a_type,String b_head,String b_code);

}
