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
import com.dao.itasset.masters.SECTIONDAO;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.models.itasset.master.TB_MSTR_SECTION;
import com.persistance.util.HibernateUtil;

@Controller 
@RequestMapping(value = {"admin","/" ,"user"})
public class Section_Mstr_Controller {

	@Autowired
	SECTIONDAO objDAO;
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();

	@Autowired
	RoleBaseMenuDAO roledao;
	  @RequestMapping(value = "Section_Mstr_Url", method = RequestMethod.GET)
      public ModelAndView Section_Mstr_Url(ModelMap Mmap,HttpSession session,HttpServletRequest request,@RequestParam(value = "msg", required = false) String msg) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, 
NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException{

			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Section_Mstr_Url", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				//return new ModelAndView("redirect:bodyParameterNotAllow");
			}

          Mmap.put("msg", msg);
      return new ModelAndView("Section_Mstr_tile","SectionMasterCmd",new TB_MSTR_SECTION());
}
	
	  
      
 @RequestMapping(value = "/getSECTIONReportDataList", method = RequestMethod.POST)
 public @ResponseBody List<Map<String, Object>> getFilteredDataList_SQL(int startPage,String pageLength,String Search,String orderColunm,String orderType ,String section,HttpSession sessionUserId) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
	return objDAO.getReportListSECTION(startPage,pageLength,Search,orderColunm,orderType,section,sessionUserId);
}

 @RequestMapping(value = "/getSECTIONTotalCount", method = RequestMethod.POST)
public @ResponseBody long getSECTIONTotalCount(HttpSession sessionUserId,String Search,String section){
	return objDAO.getReportListSECTIONTotalCount(Search,section);
}
  @RequestMapping(value = "/SECTIONAction" ,method = RequestMethod.POST) 
  public ModelAndView SECTIONAction(@Valid @ModelAttribute("SECTIONCMD") TB_MSTR_SECTION ln, BindingResult result, 
  HttpServletRequest request, ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg){ 
	  String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Section_Mstr_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}

 String section =request.getParameter("section").trim();
	if(section.equals("") || section == null) 
    { 
      model.put("msg", "Please Enter Antivirus Name"); 
      return new ModelAndView("redirect:Section_Mstr_Url"); 
    } 
	if(section.length() > 100) {
		  model.put("msg", "Antivirus Name Length Should Be Less Than 100 Characters");
			 return new ModelAndView("redirect:Section_Mstr_Url");
	  }
 
    Session sessionHQL = HibernateUtil.getSessionFactory().openSession(); 
    Transaction tx = sessionHQL.beginTransaction(); 
    try{			
		Query q0 = sessionHQL.createQuery(
				"select count(id) from TB_MSTR_SECTION where  UPPER(section)=:section and id !=:id");
		q0.setParameter("section", ln.getSection().toUpperCase());
		q0.setParameter("id", ln.getId());
		
		Long c = (Long) q0.uniqueResult();
		if (c == 0) {
			ln.setSection(section.toUpperCase());
			ln.setCreated_by(session.getAttribute("username").toString());
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
    return new ModelAndView("redirect:Section_Mstr_Url"); 
  } 
         @RequestMapping(value = "EditSECTIONUrl", method = RequestMethod.POST)
         public ModelAndView EditSECTIONUrl(ModelMap Mmap,HttpSession session,HttpServletRequest request,@RequestParam(value = "msg", required = false) String msg,String updateid) {

        	 String roleid = session.getAttribute("roleid").toString();
 			Boolean val = roledao.ScreenRedirect("Section_Mstr_Url", roleid);
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
                q = s1.createQuery("from TB_MSTR_SECTION where cast(id as string)=:PK");
                q.setString("PK", DcryptedPk);
             
                @SuppressWarnings("unchecked")
                List<String> list = (List<String>) q.list();
                tx.commit();
                s1.close();
                Mmap.put("EditSECTIONCMD1", list.get(0));
                Mmap.put("msg", msg);
         return new ModelAndView("EditSECTION_tile","EditSECTIONCMD",new TB_MSTR_SECTION());
}
  @RequestMapping(value = "/EditSECTIONAction" ,method = RequestMethod.POST) 
  public ModelAndView EditSECTIONAction(@Valid @ModelAttribute("EditSECTIONCMD") TB_MSTR_SECTION ln, BindingResult result, 
  HttpServletRequest request, ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg){ 
		
	  String roleid = session.getAttribute("roleid").toString();
	  Boolean val = roledao.ScreenRedirect("Section_Mstr_Url", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				//return new ModelAndView("redirect:bodyParameterNotAllow");
			}
	  String section =request.getParameter("section").trim();
	  String enckey ="commonPwdEncKeys";
		try {
			Cipher c = hex_asciiDao.EncryptionSHA256Algo(session,enckey);

	    try {
			String EncryptedPk =new String(Base64.encodeBase64(c.doFinal(request.getParameter("id").toString().getBytes())));
			
			if(section.equals("") || section == null) 
		    { 
		      return EditSECTIONUrl(model,session,request, "Please Enter Antivirus Name",EncryptedPk);
		    } 
		    if (section.length() > 100) {
		    	 return EditSECTIONUrl(model,session,request, "Antivirus Name Length Should Be Less Than 100 Characters",EncryptedPk);
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
				"select count(id) from TB_MSTR_SECTION where  UPPER(section)=:section and id !=:id");
		q0.setParameter("section", ln.getSection().toUpperCase());
		q0.setParameter("id", ln.getId());
		
		Long c = (Long) q0.uniqueResult();
		if (c == 0) {
			ln.setSection(section.toUpperCase());
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
    return new ModelAndView("redirect:Section_Mstr_Url"); 
  } 
  @RequestMapping(value = "/deleteSECTIONUrl", method = RequestMethod.POST) 
  public ModelAndView deleteSECTIONUrl(String deleteid,HttpSession session,ModelMap model,
		  HttpServletRequest request,@RequestParam(value = "msg", required = false) String msg  
		  ) { 
	  String roleid = session.getAttribute("roleid").toString();
	  Boolean val = roledao.ScreenRedirect("Section_Mstr_Url", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				//return new ModelAndView("redirect:bodyParameterNotAllow");
			}
	  
	  List<String> list = new ArrayList<String>(); 
  	list.add(objDAO.DeleteSECTION(deleteid,session)); 
  	model.put("msg",list);  
    return new ModelAndView("redirect:Section_Mstr_Url"); 
  	}
  
  
	@RequestMapping(value = "/SECTIONmastereport", method = RequestMethod.POST)
	public ModelAndView SECTIONmastereport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1) {

		String section = request.getParameter("section1");
		ArrayList<ArrayList<String>> CTlist = objDAO.Report_DataTableMakeList(section);
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("Section ");
		String Heading = "\nOSection Master";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
	  
	  
	  
	  
	
	
}
