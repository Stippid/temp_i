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
import com.dao.itasset.masters.Type_of_hw_mstrDAO;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.models.itasset.master.TB_MSTR_TYPE_OF_HW_M;
import com.persistance.util.HibernateUtil;


@Controller
@RequestMapping(value = {"admin","/" ,"user"})
public class Type_of_hw_mstrController {
	It_CommonController it_comm = new It_CommonController();


@Autowired
private Type_of_hw_mstrDAO objDAO;
HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();

@Autowired
RoleBaseMenuDAO roledao;


         @RequestMapping(value = "Type_of_hw_mstr_Url", method = RequestMethod.GET)
         public ModelAndView Type_of_hw_mstr_Url(ModelMap Mmap,HttpSession session,HttpServletRequest request,@RequestParam(value = "msg", required = false) String msg) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, 
         NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException{
        	 
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Type_of_hw_mstr_Url", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				//return new ModelAndView("redirect:bodyParameterNotAllow");
			}

        	 
        	 Mmap.put("CategoryList", it_comm.getCategoryList());
             Mmap.put("msg", msg);
         return new ModelAndView("Type_of_hw_mstr_tile","type_of_hw_mstrCMD",new TB_MSTR_TYPE_OF_HW_M());
}
 @RequestMapping(value = "/getType_of_hw_mstrReportDataList", method = RequestMethod.POST)
 public @ResponseBody List<Map<String, Object>> getFilteredDataList_SQL(int startPage,String pageLength,String Search,String orderColunm,String orderType, String type_of_hw,String peripheral_type,HttpSession sessionUserId) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
	
	 return objDAO.getReportListType_of_hw_mstr(startPage,pageLength,Search,orderColunm,orderType,type_of_hw,peripheral_type,sessionUserId);
}

 @RequestMapping(value = "/getType_of_hw_mstrTotalCount", method = RequestMethod.POST)
public @ResponseBody long getType_of_hw_mstrTotalCount(HttpSession sessionUserId,String Search,String type_of_hw,String peripheral_type){
	
	return objDAO.getReportListType_of_hw_mstrTotalCount(Search,type_of_hw,peripheral_type);
}
  @RequestMapping(value = "/type_of_hw_mstrAction" ,method = RequestMethod.POST) 
  public ModelAndView type_of_hw_mstrAction(@Valid @ModelAttribute("type_of_hw_mstrCMD") TB_MSTR_TYPE_OF_HW_M ln, BindingResult result, 
  HttpServletRequest request, ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg){ 
	  String type_of_hw = request.getParameter("type_of_hw").trim();
 
	  
	  String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Type_of_hw_mstr_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		
	  if(ln.getPeripheral_type() == 0) {
		 model.put("msg", "Please Select Type Of Peripheral");
		 return new ModelAndView("redirect:Type_of_hw_mstr_Url");
	 }
   
    if(type_of_hw.equals("") ||type_of_hw == null) 
    { 
      model.put("msg", "Please Enter Type Of Hardware"); 
      return new ModelAndView("redirect:Type_of_hw_mstr_Url"); 
    } 
    if(type_of_hw.length() > 50) {
		 model.put("msg", "Type Of Hardware Length Should Be Less Than 50 Characters");
		 return new ModelAndView("redirect:Type_of_hw_mstr_Url");
	 }
    Session sessionHQL = HibernateUtil.getSessionFactory().openSession(); 
    Transaction tx = sessionHQL.beginTransaction(); 
    try{			
		Query q0 = sessionHQL.createQuery(
				"select count(id) from TB_MSTR_TYPE_OF_HW_M where peripheral_type=:peripheral_type and UPPER(type_of_hw)=:type_of_hw and id !=:id");
		q0.setParameter("peripheral_type", ln.getPeripheral_type());
		q0.setParameter("type_of_hw", ln.getType_of_hw().toUpperCase());
		q0.setParameter("id", ln.getId());
		Long c = (Long) q0.uniqueResult();
		if (c == 0) {
			ln.setType_of_hw(type_of_hw.toUpperCase());
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
    return new ModelAndView("redirect:Type_of_hw_mstr_Url"); 
  } 
         @RequestMapping(value = "EditType_of_hw_mstrUrl", method = RequestMethod.POST)
         public ModelAndView EditType_of_hw_mstrUrl(ModelMap Mmap,HttpSession session,HttpServletRequest request,@RequestParam(value = "msg", required = false) String msg,String updateid) {

        	String roleid = session.getAttribute("roleid").toString();
 			Boolean val = roledao.ScreenRedirect("Type_of_hw_mstr_Url", roleid);
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
                q = s1.createQuery("from TB_MSTR_TYPE_OF_HW_M where cast(id as string)=:PK");
                q.setString("PK", DcryptedPk);
                @SuppressWarnings("unchecked")
                List<String> list = (List<String>) q.list();
                tx.commit();
                s1.close();
              
                Mmap.put("Edittype_of_hw_mstrCMD1", list.get(0));
                Mmap.put("CategoryList", it_comm.getCategoryList());
                Mmap.put("msg", msg);
         return new ModelAndView("EditType_of_hw_mstr_tile","Edittype_of_hw_mstrCMD",new TB_MSTR_TYPE_OF_HW_M());
}
  @RequestMapping(value = "/Edittype_of_hw_mstrAction" ,method = RequestMethod.POST) 
  public ModelAndView Edittype_of_hw_mstrAction(@Valid @ModelAttribute("Edittype_of_hw_mstrCMD") TB_MSTR_TYPE_OF_HW_M ln, BindingResult result, 
  HttpServletRequest request, ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg){ 

	  
	  String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Type_of_hw_mstr_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}
	  String type_of_hw = request.getParameter("type_of_hw").trim();
	  String enckey ="commonPwdEncKeys";
	try {
		Cipher c = hex_asciiDao.EncryptionSHA256Algo(session,enckey);

    try {
		String EncryptedPk =new String(Base64.encodeBase64(c.doFinal(request.getParameter("id").toString().getBytes())));
		  if(ln.getPeripheral_type() == 0) {
			 return EditType_of_hw_mstrUrl(model,session,request, "Please Select Type Of Peripheral",EncryptedPk);
		 }
		  if(type_of_hw.equals("") ||type_of_hw == null) 
		    { 
			return EditType_of_hw_mstrUrl(model,session,request,"Please Enter Type Of Hardware",EncryptedPk);
		}
		if(type_of_hw.length() > 50) {
			return EditType_of_hw_mstrUrl(model,session,request,"Type Of Hardware Length Should Be Less Than 50 Characters",EncryptedPk);
			
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
				"select count(id) from TB_MSTR_TYPE_OF_HW_M where peripheral_type=:peripheral_type and UPPER(type_of_hw)=:type_of_hw and id !=:id");
		q0.setParameter("peripheral_type", ln.getPeripheral_type());
		q0.setParameter("type_of_hw", ln.getType_of_hw().toUpperCase());
		q0.setParameter("id", ln.getId());
		Long c = (Long) q0.uniqueResult();
		if (c == 0) {
			ln.setType_of_hw(type_of_hw.toUpperCase());
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
    return new ModelAndView("redirect:Type_of_hw_mstr_Url"); 
  } 
  @RequestMapping(value = "/deletetype_of_hw_mstrUrl", method = RequestMethod.POST) 
  public ModelAndView deletetype_of_hw_mstrUrl(String deleteid,HttpSession session,ModelMap model,
		  @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request  
		  ) { 
  	
	  String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Type_of_hw_mstr_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}
	  
	  List<String> list = new ArrayList<String>(); 
  	list.add(objDAO.Deletetype_of_hw_mstr(deleteid,session)); 
  	model.put("msg",list);  
    return new ModelAndView("redirect:Type_of_hw_mstr_Url"); 
  	}
  
////FOR Excell report 
	@RequestMapping(value = "/HWmastereport", method = RequestMethod.POST)
	public ModelAndView HWmastereport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1) {

		String peripheral_type = request.getParameter("peripheral_type1");
		String type_of_hw = request.getParameter("type_of_hw1");


	 
		ArrayList<ArrayList<String>> CTlist = objDAO.Report_DataTableMakeList(type_of_hw,peripheral_type);
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("Type Of Peripheral");
		TH.add("Type Of Hardware");
		
		String Heading = "\nHW Master";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
} 
