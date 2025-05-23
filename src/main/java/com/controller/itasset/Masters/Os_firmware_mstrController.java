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
import com.models.itasset.master.TB_MSTR_OS_FIRMWARE_M;
import com.controller.itasset.ExportFile.ExcelUserListReportView;
import com.dao.itasset.masters.Os_firmware_mstrDAO;
 
import com.persistance.util.HibernateUtil;


@Controller
@RequestMapping(value = {"admin","/" ,"user"})
public class Os_firmware_mstrController {


@Autowired
private Os_firmware_mstrDAO objDAO;
HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();

@Autowired
RoleBaseMenuDAO roledao;


         @RequestMapping(value = "Os_firmware_mstr_Url", method = RequestMethod.GET)
         public ModelAndView Os_firmware_mstr_Url(ModelMap Mmap,HttpSession session,HttpServletRequest request,@RequestParam(value = "msg", required = false) String msg) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, 
  NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException{

			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Os_firmware_mstr_Url", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				//return new ModelAndView("redirect:bodyParameterNotAllow");
			}

        	 
             Mmap.put("msg", msg);
         return new ModelAndView("Os_firmware_mstr_tile","os_firmware_mstrCMD",new TB_MSTR_OS_FIRMWARE_M());
}
 @RequestMapping(value = "/getOs_firmware_mstrReportDataList", method = RequestMethod.POST)
 public @ResponseBody List<Map<String, Object>> getFilteredDataList_SQL(int startPage,String pageLength,String Search,String orderColunm,String orderType,String os_firmware,HttpSession sessionUserId) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
	return objDAO.getReportListOs_firmware_mstr(startPage,pageLength,Search,orderColunm,orderType,os_firmware,sessionUserId);
}

 @RequestMapping(value = "/getOs_firmware_mstrTotalCount", method = RequestMethod.POST)
public @ResponseBody long getOs_firmware_mstrTotalCount(HttpSession sessionUserId,String Search,String os_firmware){
	return objDAO.getReportListOs_firmware_mstrTotalCount(Search,os_firmware);
}
  @RequestMapping(value = "/os_firmware_mstrAction" ,method = RequestMethod.POST) 
  public ModelAndView os_firmware_mstrAction(@Valid @ModelAttribute("os_firmware_mstrCMD") TB_MSTR_OS_FIRMWARE_M ln, BindingResult result, 
  HttpServletRequest request, ModelMap model,@RequestParam(value = "msg", required = false) String msg, HttpSession session){ 
	 
	  String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Os_firmware_mstr_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}
	  
	  String os_firmware = request.getParameter("os_firmware").trim();
 
    if(os_firmware.equals("") ||os_firmware == null) 
    { 
      model.put("msg", "Please Enter OS/Firmware/Patch Updates"); 
      return new ModelAndView("redirect:Os_firmware_mstr_Url"); 
    } 
    if(os_firmware.length() > 100) 
    { 
      model.put("msg", "OS/Firmware/Patch Updates Length Should Be Less Than 100 Characters"); 
      return new ModelAndView("redirect:Os_firmware_mstr_Url"); 
    } 

 
    Session sessionHQL = HibernateUtil.getSessionFactory().openSession(); 
    Transaction tx = sessionHQL.beginTransaction(); 
    try{			
		Query q0 = sessionHQL.createQuery(
				"select count(id) from TB_MSTR_OS_FIRMWARE_M where  UPPER(os_firmware)=:os_firmware and id !=:id");
		q0.setParameter("os_firmware", ln.getOs_firmware().toUpperCase());
		q0.setParameter("id", ln.getId());
		
		Long c = (Long) q0.uniqueResult();
		if (c == 0) {
			ln.setOs_firmware(os_firmware.toUpperCase());
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
    return new ModelAndView("redirect:Os_firmware_mstr_Url"); 
  } 
         @RequestMapping(value = "EditOs_firmware_mstrUrl", method = RequestMethod.POST)
         public ModelAndView EditOs_firmware_mstrUrl(ModelMap Mmap,HttpSession session,HttpServletRequest request,@RequestParam(value = "msg", required = false) String msg,String updateid) {


        	 String roleid = session.getAttribute("roleid").toString();
 			Boolean val = roledao.ScreenRedirect("Os_firmware_mstr_Url", roleid);
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
                q = s1.createQuery("from TB_MSTR_OS_FIRMWARE_M where cast(id as string)=:PK");
                q.setString("PK", DcryptedPk);
                @SuppressWarnings("unchecked")
                List<String> list = (List<String>) q.list();
                tx.commit();
                s1.close();
                Mmap.put("Editos_firmware_mstrCMD1", list.get(0));
                Mmap.put("msg", msg);
         return new ModelAndView("EditOs_firmware_mstr_tile","Editos_firmware_mstrCMD",new TB_MSTR_OS_FIRMWARE_M());
}
  @RequestMapping(value = "/Editos_firmware_mstrAction" ,method = RequestMethod.POST) 
  public ModelAndView Editos_firmware_mstrAction(@Valid @ModelAttribute("Editos_firmware_mstrCMD") TB_MSTR_OS_FIRMWARE_M ln,
		  @RequestParam(value = "msg", required = false) String msg,
		  BindingResult result, 
         HttpServletRequest request, ModelMap model, HttpSession session){ 
	  String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Os_firmware_mstr_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}
	  String os_firmware = request.getParameter("os_firmware").trim();
	  
	  
	    String enckey ="commonPwdEncKeys";
		try {
			Cipher c = hex_asciiDao.EncryptionSHA256Algo(session,enckey);

	    try {
			String EncryptedPk =new String(Base64.encodeBase64(c.doFinal(request.getParameter("id").toString().getBytes())));
			
			if(os_firmware.equals("") || os_firmware == null) 
		    { 
		      return EditOs_firmware_mstrUrl(model,session,request, "Please Enter OS/Firmware/Patch Updates",EncryptedPk);
		    } 
		    if (os_firmware.length() > 100) {
		    	 return EditOs_firmware_mstrUrl(model,session,request, "OS/Firmware/Patch Updates Length Should Be Less Than 100 Characters",EncryptedPk);
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
				"select count(id) from TB_MSTR_OS_FIRMWARE_M where  UPPER(os_firmware)=:os_firmware and id !=:id");
		q0.setParameter("os_firmware", ln.getOs_firmware().toUpperCase());
		q0.setParameter("id", ln.getId());
		
		Long c = (Long) q0.uniqueResult();
		if (c == 0) {
			ln.setOs_firmware(os_firmware.toUpperCase());
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
    return new ModelAndView("redirect:Os_firmware_mstr_Url"); 
  } 
  @RequestMapping(value = "/deleteos_firmware_mstrUrl", method = RequestMethod.POST) 
  public ModelAndView deleteos_firmware_mstrUrl(String deleteid,HttpSession session,ModelMap model,
		  @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) { 
	  
  
	  String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Os_firmware_mstr_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		} 
	  List<String> list = new ArrayList<String>(); 
  	list.add(objDAO.Deleteos_firmware_mstr(deleteid,session)); 
  	model.put("msg",list);  
    return new ModelAndView("redirect:Os_firmware_mstr_Url"); 
  	}
  
////FOR Excell report 
	@RequestMapping(value = "/OS_firmwaremastereport", method = RequestMethod.POST)
	public ModelAndView OS_firmwaremastereport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1) {

		String os_firmware = request.getParameter("os_firmware1");
	
		ArrayList<ArrayList<String>> CTlist = objDAO.Report_DataTableMakeList(os_firmware);
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("OS/Firmware/Patch Updates");
		
		
		String Heading = "\nos_firmware Master";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
} 
