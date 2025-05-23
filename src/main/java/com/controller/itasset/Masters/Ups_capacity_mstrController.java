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

import com.controller.validation.ValidationController;
import com.controller.itasset.ExportFile.ExcelUserListReportView;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.models.itasset.master.TB_MSTR_UPS_CAPACITY_M;
import com.dao.itasset.masters.Ups_capacity_mstrDAO;
 
import com.persistance.util.HibernateUtil;


@Controller
@RequestMapping(value = {"admin","/" ,"user"})
public class Ups_capacity_mstrController {


	ValidationController validation = new ValidationController();
	
@Autowired
private Ups_capacity_mstrDAO objDAO;
HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
@Autowired
private RoleBaseMenuDAO roledao;


         @RequestMapping(value = "Ups_capacity_mstr_Url", method = RequestMethod.GET)
         public ModelAndView Ups_capacity_mstr_Url(ModelMap Mmap,HttpSession session,HttpServletRequest request,@RequestParam(value = "msg", required = false) String msg) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, 
         NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException{
        		String roleid = session.getAttribute("roleid").toString();
    			Boolean val = roledao.ScreenRedirect("Ups_capacity_mstr_Url", roleid);
    			if (val == false) {
    				return new ModelAndView("AccessTiles");
    			}
    			if (request.getHeader("Referer") == null) {
    				msg = "";
    				//return new ModelAndView("redirect:bodyParameterNotAllow");
    			}
             Mmap.put("msg", msg);
         return new ModelAndView("Ups_capacity_mstr_tile","ups_capacity_mstrCMD",new TB_MSTR_UPS_CAPACITY_M());
}
 @RequestMapping(value = "/getUps_capacity_mstrReportDataList", method = RequestMethod.POST)
 public @ResponseBody List<Map<String, Object>> getFilteredDataList_SQL(int startPage,String pageLength,String Search,String orderColunm,String orderType,String ups_capacity,HttpSession sessionUserId) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
	return objDAO.getReportListUps_capacity_mstr(startPage,pageLength,Search,orderColunm,orderType,ups_capacity,sessionUserId);
}

 @RequestMapping(value = "/getUps_capacity_mstrTotalCount", method = RequestMethod.POST)
public @ResponseBody long getUps_capacity_mstrTotalCount(HttpSession sessionUserId,String Search,String ups_capacity){
	return objDAO.getReportListUps_capacity_mstrTotalCount(Search,ups_capacity);
}
  @RequestMapping(value = "/ups_capacity_mstrAction" ,method = RequestMethod.POST) 
  public ModelAndView ups_capacity_mstrAction(@Valid @ModelAttribute("ups_capacity_mstrCMD") TB_MSTR_UPS_CAPACITY_M ln, BindingResult result, 
  HttpServletRequest request, ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg){ 

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Ups_capacity_mstr_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}
	  String ups_capacity = request.getParameter("ups_capacity").trim();
	  String lVA = " KVA";
	
    if(ups_capacity.equals("") || ups_capacity == null) 
    { 
      model.put("msg", "Please Enter UPS Capacity"); 
      return new ModelAndView("redirect:Ups_capacity_mstr_Url"); 
    }
    if(ups_capacity.length() > 50) 
    { 
      model.put("msg", "UPS Capacity Length Should Be Less Than 50 Characters"); 
      return new ModelAndView("redirect:Ups_capacity_mstr_Url"); 
    } 
    
    if (validation.isOnlyNumer(ups_capacity) == true) {
        model.put("msg", "UPS Capacity " +validation.isOnlyNumerMSG );
           return new ModelAndView("redirect:Ups_capacity_mstr_Url");
    }

    double ans = (Double.parseDouble(ups_capacity)/1000);
  
    String ups_capacity1 = ans + lVA;
    
  
 
    Session sessionHQL = HibernateUtil.getSessionFactory().openSession(); 
    Transaction tx = sessionHQL.beginTransaction(); 
    try{			
		Query q0 = sessionHQL.createQuery(
				"select count(id) from TB_MSTR_UPS_CAPACITY_M where UPPER(ups_capacity)=:ups_capacity and id !=:id");
		
		q0.setParameter("ups_capacity", ln.getUps_capacity().toUpperCase());
		q0.setParameter("id", ln.getId());
		Long c = (Long) q0.uniqueResult();
		if (c == 0) {
			ln.setUps_capacity(ups_capacity1);
		    sessionHQL.save(ln); 
		    model.put("msg","Data Saved Successfully"); 
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
   
    return new ModelAndView("redirect:Ups_capacity_mstr_Url"); 
  } 
         @RequestMapping(value = "EditUps_capacity_mstrUrl", method = RequestMethod.POST)
         public ModelAndView EditUps_capacity_mstrUrl(ModelMap Mmap,HttpSession session,HttpServletRequest request,@RequestParam(value = "msg", required = false) String msg,String updateid) {
        		String roleid = session.getAttribute("roleid").toString();
    			Boolean val = roledao.ScreenRedirect("Ups_capacity_mstr_Url", roleid);
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
                q = s1.createQuery("from TB_MSTR_UPS_CAPACITY_M where cast(id as string)=:PK");
                q.setString("PK", DcryptedPk);
                @SuppressWarnings("unchecked")
                List<String> list = (List<String>) q.list();
                tx.commit();
                s1.close();
                Mmap.put("Editups_capacity_mstrCMD1", list.get(0));
                Mmap.put("msg", msg);
         return new ModelAndView("EditUps_capacity_mstr_tile","Editups_capacity_mstrCMD",new TB_MSTR_UPS_CAPACITY_M());
}
  @RequestMapping(value = "/Editups_capacity_mstrAction" ,method = RequestMethod.POST) 
  public ModelAndView Editups_capacity_mstrAction(@Valid @ModelAttribute("Editups_capacity_mstrCMD") TB_MSTR_UPS_CAPACITY_M ln, BindingResult result, 
  HttpServletRequest request, ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg){ 
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Ups_capacity_mstr_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}
	  String ups_capacity = request.getParameter("ups_capacity").trim();
	  String enckey ="commonPwdEncKeys";
		try {
			Cipher c = hex_asciiDao.EncryptionSHA256Algo(session,enckey);

	    try {
			String EncryptedPk =new String(Base64.encodeBase64(c.doFinal(request.getParameter("id").toString().getBytes())));
			if(ups_capacity.equals("") || ups_capacity == null) 
		    { 
		      return EditUps_capacity_mstrUrl(model,session,request, "Please Enter UPS Capacity",EncryptedPk);
		    } 
		    if (ups_capacity.length() > 50) {
		    	 return EditUps_capacity_mstrUrl(model,session,request, "UPS Capacity Length Should Be Less Than 50 Characters",EncryptedPk);
		    }
		    if (validation.isOnlyNumer(ups_capacity) == true) {
		          return EditUps_capacity_mstrUrl(model,session,request, "UPS Capacity " +validation.isOnlyNumerMSG,EncryptedPk);
		    }

		} catch (IllegalBlockSizeException | BadPaddingException e1) {
			e1.printStackTrace();
		}
		
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e1) {
				e1.printStackTrace();
		}
	  String lVA = " KVA";
    double ans = (Double.parseDouble(ups_capacity)/1000);
    String ups_capacity1 = ans + lVA;
    

    Session sessionHQL = HibernateUtil.getSessionFactory().openSession(); 
    Transaction tx = sessionHQL.beginTransaction(); 
    ln.setId(Integer.parseInt(request.getParameter("id")));
    try{			
		Query q0 = sessionHQL.createQuery(
				"select count(id) from TB_MSTR_UPS_CAPACITY_M where UPPER(ups_capacity)=:ups_capacity and id !=:id");
		
		q0.setParameter("ups_capacity", ln.getUps_capacity().toUpperCase());
		q0.setParameter("id", ln.getId());
		Long c = (Long) q0.uniqueResult();
		if (c == 0) {
			ln.setUps_capacity(ups_capacity1);	
			sessionHQL.saveOrUpdate(ln); 
    
    model.put("msg","Data Updated Successfully"); 
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
 
   
    return new ModelAndView("redirect:Ups_capacity_mstr_Url"); 
  } 
  @RequestMapping(value = "/deleteups_capacity_mstrUrl", method = RequestMethod.POST) 
  public ModelAndView deleteups_capacity_mstrUrl(String deleteid,HttpSession session,HttpServletRequest request,ModelMap model,@RequestParam(value = "msg", required = false) String msg) { 
  
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Ups_capacity_mstr_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}
	  List<String> list = new ArrayList<String>(); 
  	list.add(objDAO.Deleteups_capacity_mstr(deleteid,session)); 
  	model.put("msg",list);  
    return new ModelAndView("redirect:Ups_capacity_mstr_Url"); 
  	}
  
  @RequestMapping(value = "/UPSCapmastereport", method = RequestMethod.POST)
	public ModelAndView UPSCapmastereport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1) {

		String ups_capacity = request.getParameter("ups_capacity1");

		ArrayList<ArrayList<String>> CTlist = objDAO.Report_DataTableMakeList(ups_capacity);
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("UPS Capacity");
		
		
		String Heading = "\nOUPSCap Master";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
} 
