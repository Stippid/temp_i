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

import com.controller.ExportFile.ExcelUserListReportView;
import com.controller.commonController.It_CommonController;
import com.controller.itasset.ExportFile.ExcelComputingUserListReportView;
import com.controller.itasset.ExportFile.PDF_Computing_Assets_Report;
import com.controller.login.RoleController;
import com.dao.itasset.Report.Assests_Serviceablity_details_DAO;
import com.dao.login.RoleBaseMenuDAO;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Assets_Serviceablity_Controller {
	
	It_CommonController common = new It_CommonController();
	
	@Autowired
	Assests_Serviceablity_details_DAO asd;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	RoleController roleController =new RoleController();	
	
	
	@RequestMapping(value = "/admin/Assests_Serviceablity_details_url", method = RequestMethod.GET)
	public ModelAndView Assests_Serviceablity_details_url(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
			HttpSession sessionUserId) {
		
			String roleid = sessionUserId.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Assests_Serviceablity_details_url", roleid);
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
		Mmap.put("ComputingAssetList", common.getComputingAssetList());
		 Mmap.put("getSectionNameList", roleController.getSectionNameList());
		ArrayList<ArrayList<String>>list =asd.Search_Assets_Serviceablity_Details("0","0","0",sessionA); 
		 if(list.size() == 0){ 
			  Mmap.put("msg", "Data Not Available."); 
		  }	  
		  else
		  { 
			  Mmap.put("list", list); 
			  Mmap.put("listsize", list.size());
		  }
		
		return new ModelAndView("Assets_Serviceablity_details_tile");
	}
	
	 @RequestMapping(value = "/GetSearch_Assests_Serviceable_details", method = RequestMethod.POST) public
	  ModelAndView GetSearch_Assests_Serviceable_details(ModelMap Mmap,HttpSession session,HttpServletRequest request,
	  @RequestParam(value = "msg", required = false) String msg,
	  @RequestParam(value = "s_state1", required = false) String s_state,
	  @RequestParam(value = "asset_type1", required = false) String asset_type,
	  @RequestParam(value = "unserviceable_state1", required = false) String unserviceable_state){
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Assests_Serviceablity_details_url", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				//return new ModelAndView("redirect:bodyParameterNotAllow");
			}
	  ArrayList<ArrayList<String>>list =asd.Search_Assets_Serviceablity_Details(s_state,asset_type,unserviceable_state,session); 
	  
	  if(list.size() == 0){ 
		  Mmap.put("msg", "Data Not Available."); 
	  }	  
	  else
	  { 
		  Mmap.put("list", list); 
		  Mmap.put("listsize", list.size());
	  }
	  Mmap.put("s_state1", s_state); 
	  Mmap.put("asset_type1", asset_type); 
	  Mmap.put("unserviceable_state1", unserviceable_state); 
	  Mmap.put("getServiceable_StateList", common.getServiceable_StateList());
	  Mmap.put("ComputingAssetList", common.getComputingAssetList());
		Mmap.put("UNServiceableList", common.UNServiceableList());
		 Mmap.put("getSectionNameList", roleController.getSectionNameList());
	  return new ModelAndView("Assets_Serviceablity_details_tile");
	  }
	 
	 @RequestMapping(value = "/Download_Computing_Assets_Details",method = RequestMethod.POST)
		public ModelAndView Download_Computing_Assets_Details(ModelMap Mmap,Authentication authentication,HttpServletRequest request,HttpSession sessionEdit,
				@RequestParam(value = "msg", required = false) String msg,
				  @RequestParam(value = "s_state2", required = false) String s_state,
				  @RequestParam(value = "asset_type2", required = false) String asset_type,
				  @RequestParam(value = "unserviceable_state2", required = false) String unserviceable_state) throws ParseException {	
		
		 
		
		 	ArrayList<ArrayList<String>> pdfprint = asd.pdf_Computing_Assets_ReportDataList(sessionEdit,s_state,asset_type,unserviceable_state);
			int total = pdfprint.size();
			String username =  sessionEdit.getAttribute("roleloginName").toString();
			
			
			Mmap.put("msg", msg);
			String Heading ="COMPUTING ASSESTS SERVICEABLITY DETAILS";
			Date date = new Date();
			String foot = " Report Generated On "+ new SimpleDateFormat("dd-MM-yyyy").format(date);
			
				List<String> TH = new ArrayList<String>();
				TH.add("Computing Assets Type");
				TH.add("Model Number");
				TH.add("Machine No");
				TH.add("MAC Address");
				TH.add("IP Address");
				TH.add("Processor Type");				
				TH.add("RAM Capacity");
				TH.add("HDD Capacity");
				TH.add("Operating System");
				TH.add("Office");
				TH.add("Os/Frameware Activation and subsequent patch updation Mechanism");
				TH.add("Deply Envt as Applicable");
				TH.add("Total");
					
			return new ModelAndView(new PDF_Computing_Assets_Report(TH,username,Heading,pdfprint,total),"userList",pdfprint);
		}
	 
	 
	 @RequestMapping(value = "/Excel_Computing_Assets_Details",method = RequestMethod.POST)
		public ModelAndView Excel_Computing_Assets_Details(ModelMap Mmap,Authentication authentication,HttpServletRequest request,HttpSession sessionEdit,
				@RequestParam(value = "msg", required = false) String msg,
				  @RequestParam(value = "s_state3", required = false) String s_state,
				  @RequestParam(value = "asset_type3", required = false) String asset_type,
				  @RequestParam(value = "unserviceable_state3", required = false) String unserviceable_state) throws ParseException {	
		
		 
		
		 	ArrayList<ArrayList<String>> Excel = asd.excel_Computing_Assets_ReportDataList(sessionEdit,s_state,asset_type,unserviceable_state);
//			int total = Excel.size();
			String username =  sessionEdit.getAttribute("username").toString();
			Mmap.put("msg", msg);
			String Heading ="COMPUTING ASSESTS SERVICEABLITY DETAILS";
			Date date = new Date();
			String foot = " Report Generated On "+ new SimpleDateFormat("dd-MM-yyyy").format(date);
			
				List<String> TH = new ArrayList<String>();
				TH.add("Computing Assets Type");
				TH.add("Model Number");
				TH.add("Machine No");
				TH.add("MAC Address");
				TH.add("IP Address");
				TH.add("Processor Type");				
				TH.add("RAM Capacity");
				TH.add("HDD Capacity");
				TH.add("Operating System");
				TH.add("Office");
				TH.add("Os/Frameware Activation and subsequent patch updation Mechanism");
				TH.add("Deply Envt as Applicable");
				
					
				return new ModelAndView(new ExcelComputingUserListReportView("L", TH, Heading, username), "userList", Excel);
		}
	 

}
