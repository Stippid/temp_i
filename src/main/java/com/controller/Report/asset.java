package com.controller.Report;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.BisagN.MISO.CommanController;
import com.controller.ExportFile.ExcelUserListReportView;
import com.controller.commonController.It_CommonController;
import com.controller.itasset.ExportFile.PDF_Asset_All_India_Holding;
import com.controller.mns.MMS_COMMON_Controller;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.dao.itasset.Report.Asset_All_India_Dao;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.models.Tbl_CodesForm;
import com.persistance.util.HibernateUtil;


@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class asset {
	It_CommonController it_comm = new It_CommonController();
	CommanController m = new CommanController();
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	
	@Autowired
	Asset_All_India_Dao cd;
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	AllMethodsControllerOrbat all =new AllMethodsControllerOrbat();
	
	MMS_COMMON_Controller m1 = new MMS_COMMON_Controller();
	
	@RequestMapping(value = "/admin/Asset_All_indiaurl", method = RequestMethod.GET)
	public ModelAndView Asset_All_indiaurl(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
			HttpSession sessionUserId)throws SQLException {
		String  roleSubAccess = sessionUserId.getAttribute("roleSubAccess").toString();
		String  username = sessionUserId.getAttribute("username").toString();
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleFormationNo = sessionA.getAttribute("roleFormationNo").toString();
		
		
		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Asset_All_indiaurl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		if(roleAccess.equals("Line_dte")) {
			 String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
			Mmap.put("getLine_DteList",roledao.getLine_DteListit_ass(roleSusNo));
		}else {
			String select="<option value='0'>--Select--</option>";
			Mmap.put("selectLine_dte", select);
			Mmap.put("getLine_DteList",roledao.getLine_DteListit_ass(""));
		}
		
		
		 if(roleAccess.equals("Formation")) {
				if(roleSubAccess.equals("Command")) {
					String formation_code = String.valueOf(roleFormationNo.charAt(0));
					List<Tbl_CodesForm> comd= getFormationList("Command",formation_code);	
					Mmap.put("getCommandList",comd);
					List<Tbl_CodesForm> corps=getFormationList("Corps",formation_code);
					Mmap.put("getCorpsList",corps);
					
					String select="<option value='0'>--Select--</option>";
					Mmap.put("selectcorps",select);
					Mmap.put("selectDiv",select);
					Mmap.put("selectBde",select);
				}
				if(roleSubAccess.equals("Corps")) {
					String command = String.valueOf(roleFormationNo.charAt(0));
					List<Tbl_CodesForm> comd=getFormationList("Command",command);
					Mmap.put("getCommandList",comd);
					
					String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
					List<Tbl_CodesForm> corps=getFormationList("Corps",cor);
					Mmap.put("getCorpsList",corps);
					
					List<Tbl_CodesForm> Bn=getFormationList("Division",cor);
					Mmap.put("getDivList",Bn);
					
					String select="<option value='0'>--Select--</option>";
					Mmap.put("selectDiv",select);
					Mmap.put("selectBde",select);
				}
				if(roleSubAccess.equals("Division")) {
					String command = String.valueOf(roleFormationNo.charAt(0));
					List<Tbl_CodesForm> comd=getFormationList("Command",command);
					Mmap.put("getCommandList",comd);
					
					String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
					List<Tbl_CodesForm> corps=getFormationList("Corps",cor);
					Mmap.put("getCorpsList",corps);
					
					String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
					List<Tbl_CodesForm> Bn=getFormationList("Division",div);
					Mmap.put("getDivList",Bn);
					
					List<Tbl_CodesForm> bde=getFormationList("Brigade",div);
					Mmap.put("getBdeList",bde);
					
					String select="<option value='0'>--Select--</option>";
					Mmap.put("selectBde",select);
				}
				if(roleSubAccess.equals("Brigade")) {
					String command = String.valueOf(roleFormationNo.charAt(0));
					List<Tbl_CodesForm> comd=getFormationList("Command",command);
					Mmap.put("getCommandList",comd);
					
					String cor = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2));
					List<Tbl_CodesForm> corps=getFormationList("Corps",cor);
					Mmap.put("getCorpsList",corps);
					
					String div = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) +  String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3)) +String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
					List<Tbl_CodesForm> Bn=getFormationList("Division",div);
					Mmap.put("getDivList",Bn);
					
					String bde_code = roleFormationNo;
					List<Tbl_CodesForm> bde = getFormationList("Brigade",bde_code);
					Mmap.put("getBdeList",bde);
				}
			}
			
		else {
				return new ModelAndView("AccessTiles");
			}
			Query q = null;
			String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	    	Transaction tx = sessionHQL.beginTransaction();
	    	 q = sessionHQL.createQuery("select arm_code from Orbat_Unt_Dtl "
						+ "where sus_no=:sus_no");
			q.setParameter("sus_no", roleSusNo);
			List<String> list = (List<String>) q.list();
			tx.commit();
			sessionHQL.close();
			
			if (list.size()>0) {
				Mmap.put("getArm_code", list.get(0));
			}
			
		 
		Date date = new Date();
		String date1 = new SimpleDateFormat("dd-MM-yyyy").format(date);
		String yyyy = new SimpleDateFormat("yyyy").format(date);
		String to_date = yyyy+"-01-01";
		Mmap.put("date", date1);
		Mmap.put("to_date",to_date );
		Mmap.put("getBudgetHeadList", it_comm.getBudgetHeadList());
		Mmap.put("getlinedteList", it_comm.getlinedteList());
		Mmap.put("msg", msg);
		Mmap.put("username", username);
		//Mmap.put("getlinedteList", it_comm.getlinedteList());
		//Mmap.put("getlinedteArmList", it_comm.getlinedteArmList());
		
	//	Mmap.put("armlist", m1.getMMSArmCodeList("ALL","ALL",sessionUserId));
		Mmap.put("getServiceable_StateList", it_comm.getServiceable_StateList());
		return new ModelAndView("Asset_all_india_tile");
		
	}
	
	
	public List<Tbl_CodesForm> getFormationList(String level_in_hierarchy,String fcode) {
    	Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
    	Transaction tx = sessionHQL.beginTransaction();
		Query q = null;
		
		if(level_in_hierarchy.equals("Command")) {
			 q = sessionHQL.createQuery("select SUBSTR(form_code_control,1,1),unit_name from Orbat_Unt_Dtl "
						+ "where sus_no in(select sus_no from "
						+ "Tbl_CodesForm where level_in_hierarchy='Command') and SUBSTR(form_code_control,1,1) =:formation_code and status_sus_no='Active'");
			q.setParameter("formation_code", fcode);
		}
		if(level_in_hierarchy.equals("Corps")) {
			q = sessionHQL.createQuery("select SUBSTR(form_code_control,1,3),unit_name from Orbat_Unt_Dtl "
					+ "where sus_no in(select sus_no from "
					+ "Tbl_CodesForm where level_in_hierarchy='Corps') and form_code_control like :formation_code and status_sus_no='Active'");
			q.setParameter("formation_code", fcode+"%");
		}
		if(level_in_hierarchy.equals("Division")) {
			q = sessionHQL.createQuery("select SUBSTR(form_code_control,1,6),unit_name from Orbat_Unt_Dtl "
					+ "where sus_no in(select sus_no from "
					+ "Tbl_CodesForm where level_in_hierarchy='Division' ) and form_code_control like :formation_code and status_sus_no='Active'");
			q.setParameter("formation_code", fcode+"%");
		}
		if(level_in_hierarchy.equals("Brigade")) {
			q = sessionHQL.createQuery("select form_code_control,unit_name from Orbat_Unt_Dtl "
					+ "where sus_no in(select sus_no from "
					+ "Tbl_CodesForm where level_in_hierarchy='Brigade' ) and form_code_control like :formation_code and status_sus_no='Active'");
			q.setParameter("formation_code", fcode+"%");
		}
		
		@SuppressWarnings("unchecked")
		List<Tbl_CodesForm> list = (List<Tbl_CodesForm>) q.list();
		tx.commit();
		sessionHQL.close();
		return list;
	}	
	
	@RequestMapping(value = "/getassetindiaholding", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getassetindiaholding(
			int startPage,int pageLength,String Search,String orderColunm,String orderType,String asset_type,String b_head,
			String b_code,String a_type,String line_dte,HttpSession sessionUserId,String cont_comd,String cont_corps,
			String cont_div,String cont_bde,String unit_name,String sus_no,String s_state) throws SQLException {
	

	 	String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSubAccess = sessionUserId.getAttribute("roleSubAccess").toString();
		String roleFormationNo = sessionUserId.getAttribute("roleFormationNo").toString();
		
		if(roleAccess.equals("Formation")) {
			if(roleSubAccess.equals("Command")) {
				String fcode_comd = String.valueOf(roleFormationNo.charAt(0));
				cont_comd = fcode_comd;
				
				if(!cont_bde.equals("0") && !cont_bde.equals("")){
					cont_bde = fcode_comd+cont_bde.substring(1, 10);
		    	}else {
		    		if(!cont_div.equals("0") && !cont_div.equals("")){
		    			cont_div = fcode_comd+cont_div.substring(1, 6);
			    	}else {
			    		if(!cont_corps.equals("0") && !cont_corps.equals("")){
			    			cont_corps = fcode_comd+cont_corps.substring(1, 3);
			    		}else {
				    		if(!cont_comd.equals("-1") && !cont_comd.equals("")){
				    			cont_comd = fcode_comd;
					    	}
				    	}
				    }
			    }
			}
			if(roleSubAccess.equals("Corps")) {
				String fcode_corps = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) + String.valueOf(roleFormationNo.charAt(2));
				cont_corps = fcode_corps;
				
				if(!cont_bde.equals("0") && !cont_bde.equals("")){
					cont_bde = fcode_corps+cont_bde.substring(3, 10);
		    	}else {
		    		if(!cont_div.equals("0") && !cont_div.equals("")){
		    			cont_div = fcode_corps+cont_div.substring(3, 6);
			    	}else {
			    		if(!cont_corps.equals("0") && !cont_corps.equals("")){
			    			cont_corps = fcode_corps;
				    	}else {
				    		if(!cont_comd.equals("-1") && !cont_comd.equals("")){
				    			cont_comd = fcode_corps;
					    	}
				    	}
				    }
			    }
			}
			if(roleSubAccess.equals("Division")) {
				String fcode_div =  String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1)) + String.valueOf(roleFormationNo.charAt(2))+ String.valueOf(roleFormationNo.charAt(3)) + String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
				cont_div = fcode_div;
				
				if(!cont_bde.equals("0") && !cont_bde.equals("")){
					cont_bde = fcode_div+cont_bde.substring(6, 10);
		    	}else {
		    		if(!cont_div.equals("0") && !cont_div.equals("")){
		    			cont_div = fcode_div;
			    	}else {
			    		if(!cont_corps.equals("0") && !cont_corps.equals("")){
			    			cont_corps = fcode_div;
				    	}else {
				    		if(!cont_comd.equals("-1") && !cont_comd.equals("")){
				    			cont_comd = fcode_div;
					    	}
				    	}
				    }
			    }
			}
			if(roleSubAccess.equals("Brigade")) {
				cont_bde = roleFormationNo;
			}
		}
		
		
		return cd.getallIndiaHoldingList(startPage, pageLength, Search, orderColunm, orderType, asset_type,b_head,b_code,a_type,line_dte,
				 sessionUserId,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,s_state);
	}
	
	@RequestMapping(value = "/getassetindiaholdingCount", method = RequestMethod.POST)
	public @ResponseBody long getassetindiaholdingCount(String Search,String orderColunm,String orderType,String asset_type,String b_head,String b_code,String a_type,String line_dte,HttpSession sessionUserId,
			String cont_comd,String cont_corps,
			String cont_div,String cont_bde,String unit_name,String sus_no,String s_state) throws SQLException {
		
	
		return cd.getallAssetIndiaHoldingCountList(Search, orderColunm, orderType,asset_type,b_head,b_code,a_type,line_dte, 
				 sessionUserId,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,s_state);
	}
	
	@RequestMapping(value = "/admin/SearchLine_directed_report", method = RequestMethod.POST)
	public ModelAndView SearchLine_directed_report(ModelMap Mmap,HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "asset_type1", required = false) String asset_type,
			@RequestParam(value = "a_type1", required = false) String a_type,
			@RequestParam(value = "b_head1", required = false) String b_head,
			@RequestParam(value = "b_code1", required = false) String b_code,
			@RequestParam(value = "line_dte1", required = false) String line_dte)
			{

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Asset_All_indiaurl", roleid);
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
		    Mmap.put("asset_type1", asset_type);
		    Mmap.put("a_type1", a_type);
		    Mmap.put("b_head1", b_head);
		    Mmap.put("b_code1", b_code);
		    Mmap.put("line_dte1", line_dte);
			Mmap.put("getBudgetHeadList", it_comm.getBudgetHeadList());
			Mmap.put("getlinedteList", it_comm.getlinedteList());
		    

		return new ModelAndView("Asset_all_india_tile");
	}
	
	 @RequestMapping(value = "/Download_All_India_Asset_Holding_Details",method = RequestMethod.POST)
		public ModelAndView Download_All_India_Asset_Holding_Details(ModelMap Mmap,
				@RequestParam(value = "msg", required = false) String msg,
				@RequestParam("cont_comd_dn") String cont_comd,@RequestParam("cont_corps_dn") String cont_corps,@RequestParam("cont_div_dn") String cont_div,
				@RequestParam("cont_bde_dn") String cont_bde,
				@RequestParam("cont_comd_tx") String cont_comd_tx,@RequestParam("cont_corps_tx") String cont_corps_tx,
				@RequestParam("cont_div_tx") String cont_div_tx,
				@RequestParam("cont_bde_tx") String cont_bde_tx,
				@RequestParam("unit_name_dn") String unit_name,@RequestParam("sus_no_dn") String sus_no,
				@RequestParam(value = "asset_type_dn", required = false) String asset_type, 
				@RequestParam(value = "a_type_dn", required = false) String a_type,
				@RequestParam(value = "b_head_dn", required = false) String b_head,
				@RequestParam(value = "b_code_dn", required = false) String b_code,
				@RequestParam(value = "line_dte_dn", required = false) String line_dte,
				@RequestParam(value = "asset_type_tx", required = false) String asset_type_tx, 
				@RequestParam(value = "a_type_tx", required = false) String a_type_tx,
				@RequestParam(value = "b_head_tx", required = false) String b_head_tx,
				@RequestParam(value = "b_code_tx", required = false) String b_code_tx,
				@RequestParam(value = "line_dte_tx", required = false) String line_dte_tx,
				@RequestParam(value = "s_state1", required = false) String s_state,
				Authentication authentication,HttpServletRequest request,HttpSession sessionEdit) throws ParseException {	
		
		 	ArrayList<ArrayList<String>> pdfprint = cd.pdf_all_india_asset_holding_ReportDataList( cont_comd, cont_corps, cont_div, cont_bde, unit_name, sus_no,asset_type,a_type,b_head,b_code,line_dte,sessionEdit,s_state);
			int total = pdfprint.size();
			String username =  sessionEdit.getAttribute("username").toString();
			Mmap.put("msg", msg);
			String Heading ="LINE DIRECTED REPORT";
			Date date = new Date();
			String foot = " Report Generated On "+ new SimpleDateFormat("dd-MM-yyyy").format(date);
			List<String> TH = new ArrayList<String>();
//			if(pdfprint.size() > 0) {
				
			TH.add("Arm");
			TH.add("Command");
			TH.add("Corps");
			TH.add("Div");
			TH.add("Bde");
			TH.add("Unit Name");
			TH.add("Sus No");
				TH.add("Asset Category");
				TH.add("Asset Name");
				TH.add("Make Name");
				TH.add("Model Name");
				TH.add("Model Number");
				TH.add("Machine Number");
				TH.add("Budget Head");
				TH.add("Budget Code");
				TH.add("Servicable State");
				TH.add("Total");
				
				if(cont_comd_tx.equals("--Select--")) {
					cont_comd_tx = "";
				}
				if(cont_corps_tx.equals("--Select--")) {
					cont_corps_tx = "";		
				}
				if(cont_div_tx.equals("--Select--")) {
					cont_div_tx = "";
				}
				if(cont_bde_tx.equals("--Select--")) {
					cont_bde_tx = "";
				}
				if(asset_type_tx.equals("--Select--")) {
					asset_type_tx = "";
				}
				if(a_type_tx.equals("--Select--")) {
					a_type_tx = "";
				}
	
				if(b_head_tx.equals("--Select--")) {
					b_head_tx = "";
				}
	
				if(b_code_tx.equals("--Select--")) {
					b_code_tx = "";
				}
				if(line_dte_tx.equals("--Select--")) {
					line_dte_tx = "";
				}
	
			return new ModelAndView(new PDF_Asset_All_India_Holding(TH,username,Heading,pdfprint,total
					,cont_comd_tx,cont_corps_tx,cont_div_tx,cont_bde_tx,unit_name,sus_no,
					asset_type_tx,a_type_tx,b_head_tx,b_code_tx,line_dte_tx),"userList",pdfprint);
		}
	 

	 @RequestMapping(value = "/Excel_All_India_Assets_Details_Export", method = RequestMethod.POST)
		public ModelAndView Excel_All_India_Assets_Details_Export(ModelMap model,HttpSession session,String typeReport1,
				@ModelAttribute("cont_comd_ex") String cont_comd,@ModelAttribute("cont_corps_ex") String cont_corps,@ModelAttribute("cont_div_ex") String cont_div,
				@ModelAttribute("cont_bde_ex") String cont_bde,
				@ModelAttribute("cont_comd_txt") String cont_comd_txt,@ModelAttribute("cont_corps_txt") String cont_corps_txt,@ModelAttribute("cont_div_txt") String cont_div_txt,
				@ModelAttribute("cont_bde_txt") String cont_bde_txt,
				@ModelAttribute("unit_name_ex") String unit_name,@ModelAttribute("sus_no_ex") String sus_no,
				@ModelAttribute("parent_arm_ex") String parent_arm,@ModelAttribute("cadre_ex") String cadre,
				@RequestParam(value = "asset_type_dn", required = false) String asset_type, 
				@RequestParam(value = "a_type_dn", required = false) String a_type,
				@RequestParam(value = "b_head_dn", required = false) String b_head,
				@RequestParam(value = "b_code_dn", required = false) String b_code,
				@RequestParam(value = "line_dte_dn", required = false) String line_dte_dn,
				@RequestParam(value = "s_state1", required = false) String s_state,
				Authentication authentication,HttpServletRequest request,HttpSession sessionEdit) throws ParseException {

		 
		 ArrayList<ArrayList<String>> Excel = cd.pdf_all_india_asset_holding_ReportDataList(cont_comd, cont_corps, cont_div, cont_bde, unit_name, sus_no,asset_type,a_type,b_head,b_code,line_dte_dn,sessionEdit,s_state);
		
			
		 List<String> TH = new ArrayList<String>();
		 	TH.add("Arm");
		 	TH.add("Command");
			TH.add("Corps");
			TH.add("Div");
			TH.add("Bde");
			TH.add("Unit Name");
			TH.add("Sus No");
			TH.add("Asset Category");
			TH.add("Asset Name");
			TH.add("Make Name");
			TH.add("Model Name");
			TH.add("Model Number");
			TH.add("Machine Number");
			TH.add("Budget Head");
			TH.add("Budget Code");
			TH.add("Servicable State");
			
	
			
			String Heading = "\nHELD STR OF CIVILIANS:ORGANISATION/ UNIT WISE AS ON";
			String username = session.getAttribute("username").toString();
			return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", Excel);
		}
	
	

}
