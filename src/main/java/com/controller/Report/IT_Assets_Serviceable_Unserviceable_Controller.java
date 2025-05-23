package com.controller.Report;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
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
import com.controller.commonController.It_CommonController;
import com.controller.itasset.ExportFile.PDF_IT_Assets_Serviceable_Unserviceable;
import com.dao.itasset.Report.IT_Assets_Serviceable_Unserviceable_DAO;
import com.dao.login.RoleBaseMenuDAO;
import com.models.Tbl_CodesForm;
import com.persistance.util.HibernateUtil;


@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class IT_Assets_Serviceable_Unserviceable_Controller {


	@Autowired
	private IT_Assets_Serviceable_Unserviceable_DAO SER_DAO;
	CommanController cm = new CommanController();
	It_CommonController it_comm = new It_CommonController();
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	
	@RequestMapping(value = "/admin/IT_Assets_Serviceable_UnserviceableUrl", method = RequestMethod.GET)
	public ModelAndView IT_Assets_Serviceable_UnserviceableUrl(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();
		String roleFormationNo = sessionA.getAttribute("roleFormationNo").toString();
		
			String roleid = sessionA.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("IT_Assets_Serviceable_UnserviceableUrl", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				//return new ModelAndView("redirect:bodyParameterNotAllow");
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

		
		
		Mmap.put("msg", msg);
		Mmap.put("getPeripheral", it_comm.getPeripheral());
		Mmap.put("getComputing", it_comm.getComputingAssetList());
		return new ModelAndView("IT_Assets_Serviceable_Unserviceable_Tiles");
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
	
	@RequestMapping(value = "/getIT_Assets_Serviceable_Unserviceable_Report", method = RequestMethod.POST)
	 public @ResponseBody List<Map<String, Object>> getIT_Assets_Serviceable_Unserviceable_Report(int startPage,String pageLength,String Search,String orderColunm,String orderType,
			 String cont_comd,String cont_corps,String cont_div,String cont_bde,String unit_name,String sus_no,String assets_type,String assets_name,HttpSession sessionUserId) 
			 throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
		
		 
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
		 
		 return SER_DAO.getIT_Assets_Serviceable_Unserviceable_ReportList(startPage,pageLength,Search,orderColunm,orderType,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,
				 assets_type,assets_name,sessionUserId);
	}
	 
	 @RequestMapping(value = "/getIT_Assets_Serviceable_Unserviceable_TotalCount", method = RequestMethod.POST)
	 public @ResponseBody long getIT_Assets_Serviceable_Unserviceable_TotalCount(HttpSession sessionUserId,String Search,String cont_comd,String cont_corps,
			 String cont_div,String cont_bde,String unit_name,String sus_no,String assets_type,String assets_name){
	 	return SER_DAO.getIT_Assets_Serviceable_Unserviceable_TotalCountDtl(Search,cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,assets_type,assets_name);
	 }
	 

	 @RequestMapping(value = "/IT_Download_Marital_Discord_Cases_query",method = RequestMethod.POST)
		public ModelAndView IT_Download_Marital_Discord_Cases_query(
				@ModelAttribute("cont_comd_dn") String cont_comd,@ModelAttribute("cont_corps_dn") String cont_corps,@ModelAttribute("cont_div_dn") String cont_div,
				@ModelAttribute("cont_bde_dn") String cont_bde,
				@ModelAttribute("cont_comd_tx") String cont_comd_tx,@ModelAttribute("cont_corps_tx") String cont_corps_tx,@ModelAttribute("cont_div_tx") String cont_div_tx,
				@ModelAttribute("cont_bde_tx") String cont_bde_tx,@ModelAttribute("assets_type_tx") String assets_type_tx,
				@ModelAttribute("unit_name_dn") String unit_name,@ModelAttribute("sus_no_dn") String sus_no,
				@ModelAttribute("assets_type_dn") String assets_type,ModelMap Mmap,
				@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpServletRequest request,
				HttpSession sessionEdit) throws ParseException {	
		
			ArrayList<ArrayList<String>> pdfprint = SER_DAO.PDF_IT_Assets_Serviceable_Unserviceable_Report(cont_comd,cont_corps,cont_div,cont_bde,unit_name,sus_no,assets_type);
			
			String username =  sessionEdit.getAttribute("username").toString();
			Mmap.put("msg", msg);
			String Heading ="";
			Date date = new Date();
			String foot = " Report Generated On "+ new SimpleDateFormat("dd-MM-yyyy").format(date);
			
				List<String> TH = new ArrayList<String>();
				TH.add("Ser No");
				TH.add("Comd");
				TH.add("Corps/Area");
				TH.add("Div/Sub Area ");
				TH.add("Bde");
				TH.add("Unit");
				TH.add("SUS No");
				TH.add("Peripheral Type");
				TH.add("Serviceable");
				TH.add("UnServiceable");
			
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
			if(assets_type_tx.equals("--Select--")) {
				assets_type_tx = "";
			}
			
			return new ModelAndView(new PDF_IT_Assets_Serviceable_Unserviceable(Heading,TH,foot,username,pdfprint,cont_comd_tx,cont_corps_tx,
					cont_div_tx,cont_bde_tx,unit_name,sus_no,assets_type_tx),"userList",pdfprint);
		}
	 
}
