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
import com.models.itasset.master.TB_MSTR_HDD_M;
import com.dao.itasset.masters.Hdd_mstrDAO;
 
import com.persistance.util.HibernateUtil;


@Controller
@RequestMapping(value = {"admin","/" ,"user"})
public class Hdd_mstrController {


@Autowired
private Hdd_mstrDAO objDAO;
HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();

ValidationController validation = new ValidationController();

@Autowired
RoleBaseMenuDAO roledao;

         @RequestMapping(value = "Hdd_mstr_Url", method = RequestMethod.GET)
         public ModelAndView Hdd_mstr_Url(ModelMap Mmap,HttpSession session,HttpServletRequest request,@RequestParam(value = "msg", required = false) String msg) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, 
  NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException{

			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Hdd_mstr_Url", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				//return new ModelAndView("redirect:bodyParameterNotAllow");
			}

        	 
             Mmap.put("msg", msg);
         return new ModelAndView("Hdd_mstr_tile","hdd_mstrCMD",new TB_MSTR_HDD_M());
}
 @RequestMapping(value = "/getHdd_mstrReportDataList", method = RequestMethod.POST)
 public @ResponseBody List<Map<String, Object>> getFilteredDataList_SQL(int startPage,String pageLength,String Search,String orderColunm,String orderType,String hdd,String hdd_capacity,HttpSession sessionUserId) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
	
	 return objDAO.getReportListHdd_mstr(startPage,pageLength,Search,orderColunm,orderType,hdd,hdd_capacity,sessionUserId);
}

 @RequestMapping(value = "/getHdd_mstrTotalCount", method = RequestMethod.POST)
public @ResponseBody long getHdd_mstrTotalCount(HttpSession sessionUserId,String Search,String hdd,String hdd_capacity){
	return objDAO.getReportListHdd_mstrTotalCount(Search,hdd,hdd_capacity);
}
  @RequestMapping(value = "/hdd_mstrAction" ,method = RequestMethod.POST) 
  public ModelAndView hdd_mstrAction(@Valid @ModelAttribute("hdd_mstrCMD") TB_MSTR_HDD_M ln, BindingResult result, 
  HttpServletRequest request, ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg){ 

	  
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Hdd_mstr_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}

    	 
	  String hdd = request.getParameter("hdd").trim();
	  String size = request.getParameter("size");
	  
    if(hdd.equals("") ||hdd == null) 
    { 
      model.put("msg", "Please Enter HDD Capacity"); 
      return new ModelAndView("redirect:Hdd_mstr_Url"); 
    } 
    if(hdd.length() > 10) {
		 model.put("msg", "HDD Capacity Length Should Be Less Than 10 Characters");
		 return new ModelAndView("redirect:Hdd_mstr_Url");
	 }

  if(size.equals("0")) {
	   model.put("hdd", "Please Select Size");
		 return new ModelAndView("redirect:Hdd_mstr_Url");
  }

 String hdd_size = hdd +" " + size;
    Session sessionHQL = HibernateUtil.getSessionFactory().openSession(); 
    Transaction tx = sessionHQL.beginTransaction(); 
    try{			
		Query q0 = sessionHQL.createQuery(
				"select count(id) from TB_MSTR_HDD_M where  UPPER(hdd)=:hdd and id !=:id");
		q0.setParameter("hdd", ln.getHdd().toUpperCase());
		q0.setParameter("id", ln.getId());
		
		Long c = (Long) q0.uniqueResult();
		if (c == 0) {
			ln.setHdd(hdd_size);
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
    return new ModelAndView("redirect:Hdd_mstr_Url"); 
  } 
         @RequestMapping(value = "EditHdd_mstrUrl", method = RequestMethod.POST)
         public ModelAndView EditHdd_mstrUrl(ModelMap Mmap,HttpSession session,HttpServletRequest request,@RequestParam(value = "msg", required = false) String msg,String updateid) {

			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Hdd_mstr_Url", roleid);
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
                q = s1.createQuery("from TB_MSTR_HDD_M where cast(id as string)=:PK");
                q.setString("PK", DcryptedPk);
                @SuppressWarnings("unchecked")
                List<String> list = (List<String>) q.list();
                tx.commit();
                s1.close();
                Mmap.put("Edithdd_mstrCMD1", list.get(0));
                Mmap.put("msg", msg);
         return new ModelAndView("EditHdd_mstr_tile","Edithdd_mstrCMD",new TB_MSTR_HDD_M());
}
  @RequestMapping(value = "/Edithdd_mstrAction" ,method = RequestMethod.POST) 
  public ModelAndView Edithdd_mstrAction(@Valid @ModelAttribute("Edithdd_mstrCMD") TB_MSTR_HDD_M ln, BindingResult result, 
  HttpServletRequest request, ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg){ 

	  
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Hdd_mstr_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}

    	 
	  String hdd = request.getParameter("hdd").trim();
	  String size = request.getParameter("size");
   
    String enckey ="commonPwdEncKeys";
	try {
		Cipher c = hex_asciiDao.EncryptionSHA256Algo(session,enckey);

    try {
		String EncryptedPk =new String(Base64.encodeBase64(c.doFinal(request.getParameter("id").toString().getBytes())));
		if (hdd.equals("") || hdd == null) {
			return EditHdd_mstrUrl(model,session,request,"Please Enter HDD Capacity",EncryptedPk);
		}
		if(hdd.length() > 10) {
			 return EditHdd_mstrUrl(model,session,request, "HDD Capacity Length Should Be Less Than 10 Characters",EncryptedPk);
		 }
		
	   if(size.equals("0")) {
		   return EditHdd_mstrUrl(model,session,request,"Please Select Size" ,EncryptedPk);

	   }
	} catch (IllegalBlockSizeException | BadPaddingException e1) {
		e1.printStackTrace();
	}
	
	} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
			| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e1) {
			e1.printStackTrace();
	}
    String hdd_size = hdd +" "+ size;
    Session sessionHQL = HibernateUtil.getSessionFactory().openSession(); 
    Transaction tx = sessionHQL.beginTransaction(); 
    ln.setId(Integer.parseInt(request.getParameter("id")));
    try{			
		Query q0 = sessionHQL.createQuery(
				"select count(id) from TB_MSTR_HDD_M where  UPPER(hdd)=:hdd and id !=:id");
		q0.setParameter("hdd", ln.getHdd().toUpperCase());
		q0.setParameter("id", ln.getId());
		
		Long c = (Long) q0.uniqueResult();
		if (c == 0) {
			ln.setHdd(hdd_size);
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
    return new ModelAndView("redirect:Hdd_mstr_Url"); 
  } 
  @RequestMapping(value = "/deletehdd_mstrUrl", method = RequestMethod.POST) 
  public ModelAndView deletehdd_mstrUrl(String deleteid,HttpSession session,ModelMap model,
		  HttpServletRequest request,
		  @RequestParam(value = "msg", required = false) String msg) { 
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Hdd_mstr_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}

	  List<String> list = new ArrayList<String>(); 
  	list.add(objDAO.Deletehdd_mstr(deleteid,session)); 
  	model.put("msg",list);  
    return new ModelAndView("redirect:Hdd_mstr_Url"); 
  	}
  
////FOR Excell report 
	@RequestMapping(value = "/HDDmastereport", method = RequestMethod.POST)
	public ModelAndView HDDmastereport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1) {

		String hdd = request.getParameter("hdd1").trim();
		
		ArrayList<ArrayList<String>> CTlist = objDAO.Report_DataTableMakeList(hdd);
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("HDD Capacity");
		
		String Heading = "\nOHDD Master";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
} 
