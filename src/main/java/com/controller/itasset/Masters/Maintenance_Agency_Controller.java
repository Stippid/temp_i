package com.controller.itasset.Masters;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.itasset.ExportFile.ExcelUserListReportView;
import com.dao.itasset.masters.Maintenance_Agency_DAO;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.models.itasset.master.TB_MASTER_MAINTENANCE_AGENCY;
import com.persistance.util.HibernateUtil;



@Controller
@RequestMapping(value = {"admin","/" ,"user"})
public class Maintenance_Agency_Controller {
	@Autowired
	RoleBaseMenuDAO roledao;
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	

@Autowired
private Maintenance_Agency_DAO objDAOM;

    @RequestMapping(value = "/maintenance_agency_Url", method = RequestMethod.GET)
    public ModelAndView maintenance_agency_Url(ModelMap Mmap, HttpSession session, HttpServletRequest request, @RequestParam(value = "msg", required = false) String msg) {
    
        return new ModelAndView("MAINTENANCE_AGENCY_tile", "MAINTENANCECMD", new TB_MASTER_MAINTENANCE_AGENCY());
    }
    
    @RequestMapping(value = "/MAINTENANCEAction" ,method = RequestMethod.POST) 
    public ModelAndView MAINTENANCEAction(@Valid @ModelAttribute("MAINTENANCECMD") TB_MASTER_MAINTENANCE_AGENCY ln, BindingResult result, 
    HttpServletRequest request, ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg){ 
  	  String roleid = session.getAttribute("roleid").toString();
  	Boolean val = roledao.ScreenRedirect("maintenance_agency_Url", roleid);
	if (val == false) {
		return new ModelAndView("AccessTiles");
	}
	if (request.getHeader("Referer") == null) {
		msg = "";
	}

   String maintenance =request.getParameter("maintenance").trim();
  	if(maintenance.equals("") || maintenance == null) 
      { 
        model.put("msg", "Please Enter Maintenance Name"); 
        return new ModelAndView("redirect:maintenance_agency_Url"); 
      } 
  	if(maintenance.length() > 100) {
  		  model.put("msg", "Maintenance Name Length Should Be Less Than 100 Characters");
  			 return new ModelAndView("redirect:maintenance_agency_Url");
  	  }
   
      Session sessionHQL = HibernateUtil.getSessionFactory().openSession(); 
      Transaction tx = sessionHQL.beginTransaction(); 
      try{			
  		Query q0 = sessionHQL.createQuery(
  				"select count(id) from TB_MASTER_MAINTENANCE_AGENCY where  UPPER(maintenance)=:maintenance and id !=:id");
  		q0.setParameter("maintenance", ln.getMaintenance().toUpperCase());
  		q0.setParameter("id", ln.getId());
  		
  		Long c = (Long) q0.uniqueResult();
  		if (c == 0) {
  			ln.setMaintenance(maintenance.toUpperCase());
      sessionHQL.save(ln); 
      model.put("msg", "Data Saved Successfully");
  		} else {
  			model.put("msg", "Data already Exist");
  		}
  		tx.commit();
  		
  		}catch(RuntimeException e){
  			try{
  				tx.rollback();
  				model.put("msg", "roll back transaction");
  			}catch(RuntimeException rbe){
  				model.put("msg", "Couldn't roll back transaction " + rbe);
  			}
  			throw e;
  		}finally{
  			if(sessionHQL!=null){
  			   sessionHQL.close();
  			}
  		}	
      return new ModelAndView("MAINTENANCE_AGENCY_tile", "MAINTENANCECMD", new TB_MASTER_MAINTENANCE_AGENCY()); 
    } 
    
    @RequestMapping(value = "/getMaintenanceReportDataList", method = RequestMethod.POST)
    public @ResponseBody List<Map<String, Object>> getMaintenanceReportDataList(int startPage,String pageLength,String Search,String orderColunm,String orderType ,String maintenance,HttpSession sessionUserId) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
   	return objDAOM.getReportListMAINTENANCE(startPage,pageLength,Search,orderColunm,orderType,maintenance,sessionUserId);
   }

    @RequestMapping(value = "/getMaintenanceTotalCount", method = RequestMethod.POST)
   public @ResponseBody long getMaintenanceTotalCount(HttpSession sessionUserId,String Search,String maintenance){
   	return objDAOM.getReportListMAINTENANCETotalCount(Search,maintenance);
}
    
    @RequestMapping(value = "/deleteMAINTENANCEUrl", method = RequestMethod.POST) 
    public ModelAndView deleteMAINTENANCEUrl(String deleteid,HttpSession session,ModelMap model,
  		  HttpServletRequest request,@RequestParam(value = "msg", required = false) String msg  
  		  
  		  ) { 
  	  String roleid = session.getAttribute("roleid").toString();
  	  Boolean val = roledao.ScreenRedirect("maintenance_agency_Url", roleid);
  			if (val == false) {
  				return new ModelAndView("AccessTiles");
  			}
  			if (request.getHeader("Referer") == null) {
  				msg = "";
  				//return new ModelAndView("redirect:bodyParameterNotAllow");
  			}
  	  
  	  List<String> list = new ArrayList<String>(); 
    	list.add(objDAOM.DeleteMAINTENANCE(deleteid,session)); 
    	model.put("msg",list);  
      return new ModelAndView("MAINTENANCE_AGENCY_tile", "MAINTENANCECMD", new TB_MASTER_MAINTENANCE_AGENCY()); 
    	}
    
    @RequestMapping(value = "EditMAINTENANCEUrl", method = RequestMethod.POST)
    public ModelAndView EditMAINTENANCEUrl(ModelMap Mmap,HttpSession session,HttpServletRequest request,@RequestParam(value = "msg", required = false) String msg,String updateid) {

   	 String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("ANTIVIRUS_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}


           Session s1 = HibernateUtil.getSessionFactory().openSession();
           Transaction tx = s1.beginTransaction();
           String enckey = "commonPwdEncKeys";  
           String DcryptedPk = hex_asciiDao.decrypt((String) updateid,enckey,session); 
           Query q = null;
           q = s1.createQuery("from TB_MASTER_MAINTENANCE_AGENCY where cast(id as string)=:PK");
           q.setString("PK", DcryptedPk);
        
           @SuppressWarnings("unchecked")
           List<String> list = (List<String>) q.list();
           tx.commit();
           s1.close();
           Mmap.put("EditMAINTENANCECMD1", list.get(0));
           Mmap.put("msg", msg);
    return new ModelAndView("EditMAINTENANCE_AGENCY_tile","EditMAINTENANCECMD",new TB_MASTER_MAINTENANCE_AGENCY());
}
    
    @RequestMapping(value = "/EditMAINTENANCEAction" ,method = RequestMethod.POST) 
    public ModelAndView EditMAINTENANCEAction(@Valid @ModelAttribute("EditMAINTENANCECMD") TB_MASTER_MAINTENANCE_AGENCY ln, BindingResult result, 
    HttpServletRequest request, ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg){ 
  		
  	  String roleid = session.getAttribute("roleid").toString();
  	  Boolean val = roledao.ScreenRedirect("ANTIVIRUS_Url", roleid);
  			if (val == false) {
  				return new ModelAndView("AccessTiles");
  			}
  			if (request.getHeader("Referer") == null) {
  				msg = "";
  				//return new ModelAndView("redirect:bodyParameterNotAllow");
  			}
  	  String maintenance =request.getParameter("maintenance").trim();
  	  String enckey ="commonPwdEncKeys";
  		try {
  			Cipher c = hex_asciiDao.EncryptionSHA256Algo(session,enckey);

  	    try {
  			String EncryptedPk =new String(Base64.encodeBase64(c.doFinal(request.getParameter("id").toString().getBytes())));
  			
  			if(maintenance.equals("") || maintenance == null) 
  		    { 
  		      return EditMAINTENANCEUrl(model,session,request, "Please Enter Maintenance Name",EncryptedPk);
  		    } 
  		    if (maintenance.length() > 100) {
  		    	 return EditMAINTENANCEUrl(model,session,request, "Maintenance Name Length Should Be Less Than 100 Characters",EncryptedPk);
  		    }
  			
  		} catch (IllegalBlockSizeException | BadPaddingException e1) {
  			e1.printStackTrace();
  		}
  		
  		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
  				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e1) {
  				e1.printStackTrace();
  		}
   
  		Session sessionHQL = HibernateUtil.getSessionFactory().openSession(); 
      Transaction tx = sessionHQL.beginTransaction(); 
      ln.setId(Integer.parseInt(request.getParameter("id")));
      try{			
  		Query q0 = sessionHQL.createQuery(
  				"select count(id) from TB_MASTER_MAINTENANCE_AGENCY where  UPPER(maintenance)=:maintenance and id !=:id");
  		q0.setParameter("maintenance", ln.getMaintenance().toUpperCase());
  		q0.setParameter("id", ln.getId());
  		
  		Long c = (Long) q0.uniqueResult();
  		if (c == 0) {
  			ln.setMaintenance(maintenance.toUpperCase());
      sessionHQL.saveOrUpdate(ln); 
  	model.put("msg", "Data Updated Successfully");
  	
  		} else {
  			model.put("msg", "Data already Exist");
  		}
  		tx.commit();
  		
  		}catch(RuntimeException e){
  			try{
  				tx.rollback();
  				model.put("msg", "roll back transaction");
  			}catch(RuntimeException rbe){
  				model.put("msg", "Couldn�t roll back transaction " + rbe);
  			}
  			throw e;
  		}finally{
  			if(sessionHQL!=null){
  			   sessionHQL.close();
  			}
  		}	
      return new ModelAndView("MAINTENANCE_AGENCY_tile", "MAINTENANCECMD", new TB_MASTER_MAINTENANCE_AGENCY()); 
    } 
	@RequestMapping(value = "/MAINTENANCEmastereport", method = RequestMethod.POST)
	public ModelAndView MAINTENANCEmastereport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1) {
		String maintenance = request.getParameter("maintenance1");
		ArrayList<ArrayList<String>> CTlist = objDAOM.Report_DataTableMakeListM(maintenance);
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("Maintenance Name");
		String Heading = "\nOMaintenance Master";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
    
}
