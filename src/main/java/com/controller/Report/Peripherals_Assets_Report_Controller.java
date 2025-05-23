package com.controller.Report;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.controller.commonController.It_CommonController;
import com.controller.itasset.ExportFile.ExcelPeripheralUserListReportView;
import com.controller.itasset.ExportFile.PDF_Peripheral_Assets_Report;
import com.controller.login.RoleController;
import com.dao.itasset.Report.Assests_Serviceablity_details_DAO;
import com.dao.login.RoleBaseMenuDAO;




@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Peripherals_Assets_Report_Controller {

	
	It_CommonController common = new It_CommonController();
	@Autowired
	Assests_Serviceablity_details_DAO asd;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	RoleController roleController =new RoleController();	
	@RequestMapping(value = "/admin/Peripherals_Assests_details_url", method = RequestMethod.GET)
	public ModelAndView Assests_Serviceablity_details_url(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
			HttpSession sessionUserId) {
		
			String roleid = sessionUserId.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Peripherals_Assests_details_url", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				//return new ModelAndView("redirect:bodyParameterNotAllow");
			}

		
				
		Date date = new Date();
		String date1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
		String yyyy = new SimpleDateFormat("yyyy").format(date);
		String to_date = yyyy+"-01-01";
		Mmap.put("date", date1);
		Mmap.put("to_date",to_date );
		Mmap.put("msg", msg);
		Mmap.put("getServiceable_StateList", common.getServiceable_StateList());
		Mmap.put("UNServiceableList", common.UNServiceableList());
		 Mmap.put("getPeripheral", common.getPeripheral());
		 Mmap.put("getSectionNameList", roleController.getSectionNameList());
		Mmap.put("list", asd.Search_Assets_Peripherals_Details("0","0","0",sessionA));
		
		 ArrayList<ArrayList<String>>list =asd.Search_Assets_Peripherals_Details("0","0","0",sessionA); 
		  
		  if(list.size() == 0){ 
			  Mmap.put("msg", "Data Not Available."); 
		  }	  
		  else
		  { 
			  Mmap.put("list", list); 
			  Mmap.put("listsize", list.size()); 
			  
		  }
		
		return new ModelAndView("Assets_Peripherals_details_tiles");
	}
	
	 @RequestMapping(value = "/GetSearch_Assests_Peripherals_details", method = RequestMethod.POST) public
	  ModelAndView GetSearch_Assests_Peripherals_details(ModelMap Mmap,HttpSession session,HttpServletRequest request,
	  @RequestParam(value = "msg", required = false) String msg,
	  @RequestParam(value = "s_state1", required = false) String s_state,
	  @RequestParam(value = "assets_type1", required = false) String assets_type,
	  @RequestParam(value = "unserviceable_state1", required = false) String unserviceable_state){
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Peripherals_Assests_details_url", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				//return new ModelAndView("redirect:bodyParameterNotAllow");
			}

		  
	  ArrayList<ArrayList<String>>list =asd.Search_Assets_Peripherals_Details(s_state,assets_type,unserviceable_state,session); 
	  
	  if(list.size() == 0){ 
		  Mmap.put("msg", "Data Not Available."); 
	  }	  
	  else
	  { 
		  Mmap.put("list", list); 
		  Mmap.put("listsize", list.size()); 
		  
	  }

	  Mmap.put("s_state1", s_state); 
	  Mmap.put("assets_type1", assets_type); 
      Mmap.put("unserviceable_state1", unserviceable_state); 
	  Mmap.put("getServiceable_StateList", common.getServiceable_StateList());
	  Mmap.put("UNServiceableList", common.UNServiceableList());
	  Mmap.put("getPeripheral", common.getPeripheral());
	  Mmap.put("getSectionNameList", roleController.getSectionNameList());
	  return new ModelAndView("Assets_Peripherals_details_tiles");
	  }
	 
	 @RequestMapping(value = "/Download_Peripherals_Assets_Details",method = RequestMethod.POST)
		public ModelAndView Download_Peripherals_Assets_Details(ModelMap Mmap,
				@RequestParam(value = "msg", required = false) String msg,
				  @RequestParam(value = "s_state2", required = false) String s_state,
				  @RequestParam(value = "asset_type2", required = false) String asset_type,
				  @RequestParam(value = "unserviceable_state2", required = false) String unserviceable_state,
				Authentication authentication,HttpServletRequest request,HttpSession sessionEdit) throws ParseException {	
		
		 	ArrayList<ArrayList<String>> pdfprint = asd.pdf_Peripherals_Assets_ReportDataList(sessionEdit,s_state,asset_type,unserviceable_state);
		 	int total = pdfprint.size();
			String username =  sessionEdit.getAttribute("roleloginName").toString();
			Mmap.put("msg", msg);
			String Heading ="PERIPHERAL ASSETS SERVICEABLITY DETAILS REPORT";
			Date date = new Date();
			String foot = " Report Generated On "+ new SimpleDateFormat("dd-MM-yyyy").format(date);
			
				List<String> TH = new ArrayList<String>();
				TH.add("Peripheral Type");
				TH.add("Type of HW");
				TH.add("Year Of Proc");
				TH.add("Year Of Manufacturing");
				TH.add("Proc Cost");
				TH.add("Machine Make");				
				TH.add("Machine No");
				TH.add("Model Number");
				TH.add("Remarks");
				TH.add("Total");
				
			return new ModelAndView(new PDF_Peripheral_Assets_Report(TH,username,Heading,pdfprint,total),"userList",pdfprint);
		}
	 
	 
	 @RequestMapping(value = "/Excel_Peripheral_Assets_Details",method = RequestMethod.POST)
		public ModelAndView Excel_Peripheral_Assets_Details(ModelMap Mmap,Authentication authentication,HttpServletRequest request,HttpSession sessionEdit,
				@RequestParam(value = "msg", required = false) String msg,
				  @RequestParam(value = "s_state3", required = false) String s_state,
				  @RequestParam(value = "asset_type3", required = false) String asset_type,
				  @RequestParam(value = "unserviceable_state3", required = false) String unserviceable_state) throws ParseException {	
		
		 
		
		 	ArrayList<ArrayList<String>> Excel = asd.excel_Peripheral_Assets_ReportDataList(sessionEdit,s_state,asset_type,unserviceable_state);
//			int total = Excel.size();
			String username =  sessionEdit.getAttribute("username").toString();
			Mmap.put("msg", msg);
			String Heading ="COMPUTING ASSESTS SERVICEABLITY DETAILS";
			Date date = new Date();
			String foot = " Report Generated On "+ new SimpleDateFormat("dd-MM-yyyy").format(date);
			
				List<String> TH = new ArrayList<String>();
				TH.add("Peripheral Type");
				TH.add("Type of HW");
				TH.add("Year Of Proc");
				TH.add("Year Of Manufacturing");
				TH.add("Proc Cost");
				TH.add("Machine Make");				
				TH.add("Machine No");
				TH.add("Model Number");
				TH.add("Remarks");
//				TH.add("Total");
				
					
				return new ModelAndView(new ExcelPeripheralUserListReportView("L", TH, Heading, username), "userList", Excel);
		}
}
