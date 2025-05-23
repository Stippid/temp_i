package com.dao.itasset.Report;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

public interface Assests_Serviceablity_details_DAO {
	
	public ArrayList<ArrayList<String>> Search_Assets_Serviceablity_Details(String service_state, String asset_type,String unservice_state,HttpSession session);
	public ArrayList<ArrayList<String>> pdf_Computing_Assets_ReportDataList(HttpSession session,String service_state, String asset_type,String unservice_state);
public ArrayList<ArrayList<String>> Search_Assets_Peripherals_Details(String service_state,String asset_type, String unservice_state,HttpSession session);
	
	public ArrayList<ArrayList<String>> pdf_Peripherals_Assets_ReportDataList(HttpSession session,String service_state, String asset_type,String unservice_state);
	public ArrayList<ArrayList<String>> excel_Computing_Assets_ReportDataList(HttpSession session,String service_state, String asset_type,String unservice_state);
	public ArrayList<ArrayList<String>> excel_Peripheral_Assets_ReportDataList(HttpSession session,String service_state, String asset_type,String unservice_state);
}
