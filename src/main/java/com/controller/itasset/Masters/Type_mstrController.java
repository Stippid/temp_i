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

import com.controller.commonController.It_CommonController;
import com.controller.itasset.ExportFile.ExcelUserListReportView;
import com.dao.itasset.masters.Type_mstrDAO;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.models.itasset.master.TB_MSTR_TYPE_M;
import com.persistance.util.HibernateUtil;


@Controller
@RequestMapping(value = {"admin","/" ,"user"})
public class Type_mstrController {
	It_CommonController it_comm = new It_CommonController();


@Autowired
private Type_mstrDAO objDAO;
HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();

@Autowired
RoleBaseMenuDAO roledao;

         @RequestMapping(value = "Type_mstr_Url", method = RequestMethod.GET)
         public ModelAndView Type_mstr_Url(ModelMap Mmap,HttpSession session,HttpServletRequest request,@RequestParam(value = "msg", required = false) String msg) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, 
  NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException{
        	 
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Type_mstr_Url", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				//return new ModelAndView("redirect:bodyParameterNotAllow");
			}

        	 
        	 
        	 Mmap.put("CategoryList", it_comm.getCategoryList());
             Mmap.put("msg", msg);
         return new ModelAndView("Type_mstr_tile","type_mstrCMD",new TB_MSTR_TYPE_M());
}
 public List<TB_MSTR_TYPE_M> gettypeofhwListDDL(HttpSession sessionU) {
   	Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from TB_MSTR_TYPE_M order by id");
		@SuppressWarnings("unchecked")
		List<TB_MSTR_TYPE_M>  list = (List<TB_MSTR_TYPE_M>) q.list();
		tx.commit();
		session.close();
		return list;
	}
 @RequestMapping(value = "/getType_mstrReportDataList", method = RequestMethod.POST)
 public @ResponseBody List<Map<String, Object>> getFilteredDataList_SQL(int startPage,String pageLength,String Search,String orderColunm,String orderType,String peripheral_type,String type_of_hw,String type,HttpSession sessionUserId) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
	return objDAO.getReportListType_mstr(startPage,pageLength,Search,orderColunm, orderType,peripheral_type, type_of_hw, type,sessionUserId);
}

 @RequestMapping(value = "/getType_mstrTotalCount", method = RequestMethod.POST)
public @ResponseBody long getType_mstrTotalCount(HttpSession sessionUserId,String Search,String peripheral_type,String type_of_hw,String type){
	return objDAO.getReportListType_mstrTotalCount(Search,peripheral_type, type_of_hw, type);
}
  @RequestMapping(value = "/type_mstrAction" ,method = RequestMethod.POST) 
  public ModelAndView type_mstrAction(@Valid @ModelAttribute("type_mstrCMD") TB_MSTR_TYPE_M ln, BindingResult result, 
  HttpServletRequest request, ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg){ 
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Type_mstr_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}

 String type = request.getParameter("type").trim();
 
	 if(ln.getPeripheral_type() == 0) 
	 { 
	   model.put("msg", "Please Select Type of Peripheral"); 
	   return new ModelAndView("redirect:Type_mstr_Url"); 
	 } 
    if(ln.getType_of_hw() == 0) 
    { 
      model.put("msg", "Please Select Type of Hardware"); 
      return new ModelAndView("redirect:Type_mstr_Url"); 
    } 
    if(type.equals("") || type == null) 
    { 
      model.put("msg", "Please Enter Model Type"); 
      return new ModelAndView("redirect:Type_mstr_Url"); 
    } 
  if(type.length() > 50) {
	  model.put("msg", "Model Type Length Should Be Less Than 50 Characters");
		 return new ModelAndView("redirect:Type_mstr_Url");
  }
 
    Session sessionHQL = HibernateUtil.getSessionFactory().openSession(); 
    Transaction tx = sessionHQL.beginTransaction(); 
    try{			
		Query q0 = sessionHQL.createQuery(
				"select count(id) from TB_MSTR_TYPE_M where peripheral_type=:peripheral_type and type_of_hw=:type_of_hw AND UPPER(type)=:type and id !=:id");
		q0.setParameter("peripheral_type", ln.getPeripheral_type());
		q0.setParameter("type_of_hw", ln.getType_of_hw());
		q0.setParameter("type", type.toUpperCase());
		q0.setParameter("id", ln.getId());
		
		
		Long c = (Long) q0.uniqueResult();
		if (c == 0) {
			ln.setType(type.toUpperCase());
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
    return new ModelAndView("redirect:Type_mstr_Url"); 
  } 
         @RequestMapping(value = "EditType_mstrUrl", method = RequestMethod.POST)
         public ModelAndView EditType_mstrUrl(ModelMap Mmap,HttpSession session,HttpServletRequest request,@RequestParam(value = "msg", required = false) String msg,String updateid) {


        	 String roleid = session.getAttribute("roleid").toString();
 			Boolean val = roledao.ScreenRedirect("Type_mstr_Url", roleid);
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
                q = s1.createQuery("from TB_MSTR_TYPE_M where cast(id as string)=:PK");
                q.setString("PK", DcryptedPk);
                @SuppressWarnings("unchecked")
                List<String> list = (List<String>) q.list();
                tx.commit();
                s1.close();
                Mmap.put("CategoryList", it_comm.getCategoryList());
                Mmap.put("Edittype_mstrCMD1", list.get(0));
             Mmap.put("gettypeofhwListDDL", gettypeofhwListDDL(session));
                Mmap.put("msg", msg);
         return new ModelAndView("EditType_mstr_tile","Edittype_mstrCMD",new TB_MSTR_TYPE_M());
}
  @RequestMapping(value = "/Edittype_mstrAction" ,method = RequestMethod.POST) 
  public ModelAndView Edittype_mstrAction(@Valid @ModelAttribute("Edittype_mstrCMD") TB_MSTR_TYPE_M ln, BindingResult result, 
  HttpServletRequest request, ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg){ 

	  
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Type_mstr_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}

	  String type = request.getParameter("type").trim();
	  
		
	    String enckey ="commonPwdEncKeys";
		try {
			Cipher c = hex_asciiDao.EncryptionSHA256Algo(session,enckey);

	    try {
			String EncryptedPk =new String(Base64.encodeBase64(c.doFinal(request.getParameter("id").toString().getBytes())));
			 if(ln.getPeripheral_type() == 0) 
			 { 
				 return EditType_mstrUrl(model,session,request, "Please Select Type of Peripheral",EncryptedPk);
			
			 } 
		    if(ln.getType_of_hw() == 0) 
		    { 
		    	 return EditType_mstrUrl(model,session,request,"Please Select Type of Hardware",EncryptedPk);
		     
		    } 
		     if(type.equals("") ||type == null) 
			    { 
				return EditType_mstrUrl(model,session,request,"Please Enter Model Type",EncryptedPk);
			}
			if(type.length() > 50) {
				return EditType_mstrUrl(model,session,request,"Model Type Length Should Be Less Than 50 Characters",EncryptedPk);
				
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
  				"select count(id) from TB_MSTR_TYPE_M where peripheral_type=:peripheral_type and type_of_hw=:type_of_hw AND UPPER(type)=:type and id !=:id");
  		q0.setParameter("peripheral_type", ln.getPeripheral_type());
  		q0.setParameter("type_of_hw", ln.getType_of_hw());
  		q0.setParameter("type", type.toUpperCase());
  		q0.setParameter("id", ln.getId());
  		
  		
  		Long c = (Long) q0.uniqueResult();
  		if (c == 0) {
  			ln.setType(type.toUpperCase());
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
				model.put("msg", "Couldn't roll back transaction " + rbe);
			}
			throw e;
		}finally{
			if(sessionHQL!=null){
			   sessionHQL.close();
			}
		}	
    return new ModelAndView("redirect:Type_mstr_Url"); 
  } 
  @RequestMapping(value = "/deletetype_mstrUrl", method = RequestMethod.POST) 
  public ModelAndView deletetype_mstrUrl(String deleteid,HttpSession session,HttpServletRequest request, ModelMap model,@RequestParam(value = "msg", required = false) String msg) { 
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Type_mstr_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}

	  
	  List<String> list = new ArrayList<String>(); 
  	list.add(objDAO.Deletetype_mstr(deleteid,session)); 
  	model.put("msg",list);  
    return new ModelAndView("redirect:Type_mstr_Url"); 
  	}
  
////FOR Excell report
	@RequestMapping(value = "/TypeHwmastereport", method = RequestMethod.POST)
	public ModelAndView TypeHwmastereport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1) {

		String peripheral_type = request.getParameter("peripheral_type1");
		String type_of_hw = request.getParameter("type_of_hw1");
		String type = request.getParameter("type1");


	 
		ArrayList<ArrayList<String>> CTlist = objDAO.Report_DataTableMakeList(peripheral_type,type_of_hw,type);
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("Type Of Peripheral");
		TH.add("Type Of Hardware");
		TH.add("MODEL TYPE");
		
		String Heading = "\nOtype Master";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
} 
