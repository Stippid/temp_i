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

import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.models.itasset.master.TB_MSTR_PROCESSOR_TYPE_M;
import com.controller.itasset.ExportFile.ExcelUserListReportView;
import com.controller.validation.ValidationController;
import com.dao.itasset.masters.Processor_or_typeDAO;
 
import com.persistance.util.HibernateUtil;


@Controller
@RequestMapping(value = {"admin","/" ,"user"})
public class Processor_or_typeController {


@Autowired
private Processor_or_typeDAO objDAO;
HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();

@Autowired
RoleBaseMenuDAO roledao;
ValidationController validation=new ValidationController();


         @RequestMapping(value = "Processor_or_type_Url", method = RequestMethod.GET)
         public ModelAndView Processor_or_type_Url(ModelMap Mmap,HttpSession session,HttpServletRequest request,
      @RequestParam(value = "msg", required = false) String msg) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, 
  NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException{
        	 
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Processor_or_type_Url", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				//return new ModelAndView("redirect:bodyParameterNotAllow");
			}


             Mmap.put("msg", msg);
         return new ModelAndView("Processor_or_type_tile","Processor_or_typeCMD",new TB_MSTR_PROCESSOR_TYPE_M());
}
 @RequestMapping(value = "/getProcessor_or_typeReportDataList", method = RequestMethod.POST)
 public @ResponseBody List<Map<String, Object>> getFilteredDataList_SQL(int startPage,String pageLength,String Search,String orderColunm,String orderType,String processor_type,HttpSession sessionUserId) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
	return objDAO.getReportListProcessor_or_type(startPage,pageLength,Search,orderColunm,orderType,processor_type,sessionUserId);
}

 @RequestMapping(value = "/getProcessor_or_typeTotalCount", method = RequestMethod.POST)
public @ResponseBody long getProcessor_or_typeTotalCount(HttpSession sessionUserId,String Search,String processor_type){
	return objDAO.getReportListProcessor_or_typeTotalCount(Search,processor_type);
}
  @RequestMapping(value = "/Processor_or_typeAction" ,method = RequestMethod.POST) 
  public ModelAndView Processor_or_typeAction(@Valid @ModelAttribute("Processor_or_typeCMD") TB_MSTR_PROCESSOR_TYPE_M ln,
		  @RequestParam(value = "msg", required = false) String msg,
		  BindingResult result, 
  HttpServletRequest request, ModelMap model, HttpSession session){ 
	  String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Processor_or_type_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}
	  String processor_type = request.getParameter("processor_type").trim();
    if(processor_type.equals("") || processor_type == null) 
    { 
      model.put("msg", "Please Enter Type Of Processor"); 
      return new ModelAndView("redirect:Processor_or_type_Url"); 
    } 
    if(processor_type.length() > 100) 
    { 
      model.put("msg", "Type Of Processor Length Should Be Less Than 100 Characters"); 
      return new ModelAndView("redirect:Processor_or_type_Url"); 
    } 
    if(!validation.isOnlyAlphabetNumericSpaceNot(processor_type)) {
		model.put("msg", validation.isOnlyAlphabetNumericSpaceNotMSG +"in processor_type");
	      return new ModelAndView("redirect:Processor_or_type_Url"); 
	}
 
    Session sessionHQL = HibernateUtil.getSessionFactory().openSession(); 
    Transaction tx = sessionHQL.beginTransaction(); 
    try{			
		Query q0 = sessionHQL.createQuery(
				"select count(id) from TB_MSTR_PROCESSOR_TYPE_M where UPPER(processor_type)=:processor_type and id !=:id");
		q0.setParameter("processor_type", ln.getProcessor_type().toUpperCase());
		q0.setParameter("id", ln.getId());
		
		Long c = (Long) q0.uniqueResult();
		if (c == 0) {
			ln.setProcessor_type(processor_type.toUpperCase());
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
    return new ModelAndView("redirect:Processor_or_type_Url"); 
  } 
         @RequestMapping(value = "EditProcessor_or_typeUrl", method = RequestMethod.POST)
         public ModelAndView EditProcessor_or_typeUrl(ModelMap Mmap,HttpSession session,HttpServletRequest request,@RequestParam(value = "msg", required = false) String msg,String updateid) {


        	 String roleid = session.getAttribute("roleid").toString();
 			Boolean val = roledao.ScreenRedirect("Processor_or_type_Url", roleid);
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
                q = s1.createQuery("from TB_MSTR_PROCESSOR_TYPE_M where cast(id as string)=:PK");
                q.setString("PK", DcryptedPk);
                @SuppressWarnings("unchecked")
                List<String> list = (List<String>) q.list();
                tx.commit();
                s1.close();
                Mmap.put("EditProcessor_or_typeCMD1", list.get(0));
                Mmap.put("msg", msg);
         return new ModelAndView("EditProcessor_or_type_tile","EditProcessor_or_typeCMD",new TB_MSTR_PROCESSOR_TYPE_M());
}
  @RequestMapping(value = "/EditProcessor_or_typeAction" ,method = RequestMethod.POST) 
  public ModelAndView EditProcessor_or_typeAction(@Valid @ModelAttribute("EditProcessor_or_typeCMD") TB_MSTR_PROCESSOR_TYPE_M ln, 
		  @RequestParam(value = "msg", required = false) String msg,
		  BindingResult result, 
  HttpServletRequest request, ModelMap model, HttpSession session){ 
	  String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Processor_or_type_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}
    String processor_type = request.getParameter("processor_type").trim();
	  String enckey ="commonPwdEncKeys";
			try {
				Cipher c = hex_asciiDao.EncryptionSHA256Algo(session,enckey);

		    try {
				String EncryptedPk =new String(Base64.encodeBase64(c.doFinal(request.getParameter("id").toString().getBytes())));
				
				if(processor_type.equals("") || processor_type == null) 
			    { 
			      return EditProcessor_or_typeUrl(model,session,request, "Please Enter Type Of Processor",EncryptedPk);
			    } 
			    if (processor_type.length() > 100) {
			    	 return EditProcessor_or_typeUrl(model,session,request,"Type Of Processor Length Should Be Less Than 100 Characters",EncryptedPk);
			    }
			    if(!validation.isOnlyAlphabetNumericSpaceNot(processor_type)) {
			    	 return EditProcessor_or_typeUrl(model,session,request,validation.isOnlyAlphabetNumericSpaceNotMSG +"in processor_type",EncryptedPk);
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
				"select count(id) from TB_MSTR_PROCESSOR_TYPE_M where UPPER(processor_type)=:processor_type and id !=:id");
		q0.setParameter("processor_type", ln.getProcessor_type().toUpperCase());
		q0.setParameter("id", ln.getId());
		
		Long c = (Long) q0.uniqueResult();
		if (c == 0) {
			ln.setProcessor_type(processor_type.toUpperCase());
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
    return new ModelAndView("redirect:Processor_or_type_Url"); 
  } 
  @RequestMapping(value = "/deleteProcessor_or_typeUrl", method = RequestMethod.POST) 
  public ModelAndView deleteProcessor_or_typeUrl(String deleteid,HttpSession session,
		  @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request,
		  ModelMap model) { 
  	
	  String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Processor_or_type_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}
	  List<String> list = new ArrayList<String>(); 
  	list.add(objDAO.DeleteProcessor_or_type(deleteid,session)); 
  	model.put("msg",list);  
    return new ModelAndView("redirect:Processor_or_type_Url"); 
  	}
  
////FOR Excell report
	@RequestMapping(value = "/Processormastereport", method = RequestMethod.POST)
	public ModelAndView Processormastereport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1) {

		String processor_type = request.getParameter("processor_type1");
	
		ArrayList<ArrayList<String>> CTlist = objDAO.Report_DataTableMakeList(processor_type);
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("Type Of Processor");
		
		
		String Heading = "\nProcessor Master";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
} 
