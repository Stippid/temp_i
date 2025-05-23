package com.dao.Dashboard;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;

//import com.models.psg.Transaction.TB_TRANS_PROPOSED_COMM_LETTER;

public interface CueDashboardDAO {
 
	 public List<Map<String, Object>> getAllReportListJdbc(String qry);
	 public DataSet<Map<String, Object>> DatatablesCriteriasCommonReportUnit(DatatablesCriterias criterias ,String pageTypeU);
	
	 ///////////////////////////////////////
	 public List<Map<String, Object>> getCueTypeYearWiseUE(int fromYear, int toYear, String arm);
	 public List<Map<String, Object>> getCueTypeMonthWiseUE(int year, String doc_type, String arm1);
	 
	 public List<Map<String, Object>> getCommandWiseUnitMov(Date fromDate,Date toDate);
	 public List<Map<String, Object>> getCommandWiseActionCluster(Date fromDate,Date toDate);
	
	 List<Map<String, Object>> DatatablesReporTpt(int startPage, int pageLength, String search,
				String orderColunm, String orderType, HttpSession sessionUserId, String std_nomclature, String arm,
				String cont_comd, String cont_corps,String cont_div, String cont_bde);
	 
	 long DatatablesReporTptcount(String search, String orderColunm, String orderType,
				HttpSession sessionUserId, String std_nomclature, String arm,String cont_comd, String cont_corps,String cont_div, String cont_bde);
	 List<Map<String, Object>> DatatablesReporWpn(int startPage, int pageLength, String search,
				String orderColunm, String orderType, HttpSession sessionUserId, String nomenClature,String cont_comd, String cont_corps, 
				String cont_div, String cont_bde,String arm);
	 
	 long DatatablesReporWpncount(String search, String orderColunm, String orderType,
				HttpSession sessionUserId, String nomenClature,String cont_comd, String cont_corps, 
				String cont_div, String cont_bde,String arm);
	
	 List<Map<String, Object>> DatatablesReporPers(int startPage, int pageLength, String search,
				String orderColunm, String orderType, HttpSession sessionUserId, String appointment, String off_rnk,String jco_rnk,String cont_comd, String cont_corps, 
				String cont_div, String cont_bde,String radio1);
	 
	 long DatatablesReporPerscount(String search, String orderColunm, String orderType,
				HttpSession sessionUserId,String appointment,String off_rnk,String jco_rnk,String cont_comd, String cont_corps,
				String cont_div, String cont_bde,String radio1);
	 

	 public long getDocTypeDetaisWEPEcount(String search, String orderColunm, String orderType,
             HttpSession sessionUserId, String cr_by, String crDate);
     public List<Map<String, Object>> getDocTypeDetaisWEPElist(int startPage, int pageLength, String search,
             String orderColunm, String orderType, HttpSession sessionUserId, String cr_by, String crDate);
     public long getDocTypeDetaisWETPETcount(String search, String orderColunm, String orderType,
             HttpSession sessionUserId, String cr_by, String crDate);
     public List<Map<String, Object>> getDocTypeDetaisWETPETlist(int startPage, int pageLength, String search,
             String orderColunm, String orderType, HttpSession sessionUserId, String cr_by, String crDate);
	public List<Map<String, Object>> unit_wise_dtls_table(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId, String fromDate, String toDate, String cmd, String ydata, String cont_comd, String cont_corps, String cont_div, String cont_bde, String arm, String unit_sus_no, String unit_name);
	public List<Map<String, Object>> getCueDocTypeWiseModule(Date fromDate, Date toDate);
	
	public List<Map<String, Object>> Rank_cat_wise_dtl_table(int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId, String fromDate, String cont_comd, String cont_corps, String cont_div, String cont_bde, String arm, String unit_sus_no, String unit_name, String rank);
	public long Rank_cat_wise_dtl_tablecount(String search, String orderColunm, String orderType,
			HttpSession sessionUserId, String cat_id, String cont_comd, String cont_corps, String cont_div, String cont_bde, String arm, String unit_sus_no, String unit_name, String rank);
	public long unit_wise_dtls_tablecount(String search, String orderColunm, String orderType,
			HttpSession sessionUserId, String fromDate, String toDate, String cmd, String ydata, String cont_comd, String cont_corps, String cont_div, String cont_bde, String arm, String unit_sus_no, String unit_name);
	public List<Map<String, Object>> get_DocTypeDetais_Report(String pageType, int startPage, int pageLength, String search,
			String orderColunm, String orderType, HttpSession sessionUserId, String cat_id, String cont_comd,
			String cont_corps, String cont_div, String cont_bde, String arm, String unit_sus_no, String radio1);
	public long get_DocTypeDetais_Report_tablecount(String pageType,String search, String orderColunm, String orderType,
			HttpSession sessionUserId, String cat_id, String cont_comd, String cont_corps, String cont_div,
			String cont_bde, String arm, String unit_sus_no, String radio1);
	public List<Map<String, Object>> getCueTypeYearWiseUEwet(int fromYear, int toYear, String arm);
	public List<Map<String, Object>> getCueTypeMonthWiseUEwet(int year, String doc_type, String armwet);
	public List<Map<String, Object>> get_Active_Unit_DocTypeDetais_Report(String pageType, int startPage,
			int pageLength, String search, String orderColunm, String orderType, HttpSession sessionUserId,
			String cat_id, String cont_comd, String cont_corps, String cont_div, String cont_bde, String arm,
			String unit_sus_no, String unit_name, String location, String radio1);
	public long get_Active_Unit_DocTypeDetais_Report_tablecount(String pageType, String search, String orderColunm,
			String orderType, HttpSession sessionUserId, String cat_id, String cont_comd, String cont_corps,
			String cont_div, String cont_bde, String arm, String unit_sus_no, String unit_name, String location, String radio1);
	public List<Map<String, Object>> get_Dtl_doc_type_month_wise(String pageType, int startPage, int pageLength,
			String search, String orderColunm, String orderType, HttpSession sessionUserId, 
			 String arm_a, String doc_type, String year);
	public List<Map<String, Object>> get_Dtl_doc_type_month_wise_WET_PET(String pageType, int startPage, int pageLength,
			String search, String orderColunm, String orderType, HttpSession sessionUserId, String arm_wet, String doc_wet,
			String year_wet);
	public long get_Dtl_doc_type_month_wiseCountsum(String search, String orderColunm, String orderType,
			HttpSession sessionUserId, String arm_a, String doc_type, String year);
	public long get_Dtl_doc_type_month_wise_WET_PETCountsum(String search, String orderColunm, String orderType,
			HttpSession sessionUserId, String arm_wet, String doc_wet, String year_wet);
}


