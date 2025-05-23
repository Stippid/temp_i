package com.controller.itasset.Masters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
import com.models.itasset.master.TB_MSTR_PERIPHERAL_TYPE_M;
import com.dao.itasset.masters.PeripheralMasterDAO;
 
import com.persistance.util.HibernateUtil;


@Controller
@RequestMapping(value = {"admin","/" ,"user"})
public class PeripheralMasterController {


@Autowired
private PeripheralMasterDAO objDAO;
HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();

@Autowired
RoleBaseMenuDAO roledao;


         @RequestMapping(value = "PeripheralMaster_Url", method = RequestMethod.GET)
         public ModelAndView PeripheralMaster_Url(ModelMap Mmap,HttpSession session,HttpServletRequest request,@RequestParam(value = "msg", required = false) String msg) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, 
  NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException{
        	 
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("PeripheralMaster_Url", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				//return new ModelAndView("redirect:bodyParameterNotAllow");
			}

        	 
        	 

             Mmap.put("msg", msg);
         return new ModelAndView("PeripheralMaster_tile","PeripheralMasterCMD",new TB_MSTR_PERIPHERAL_TYPE_M());
}
 @RequestMapping(value = "/getPeripheralMasterReportDataList", method = RequestMethod.POST)
 public @ResponseBody List<Map<String, Object>> getFilteredDataList_SQL(int startPage,String pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
	return objDAO.getReportListPeripheralMaster(startPage,pageLength,Search,orderColunm,orderType,sessionUserId);
}

 @RequestMapping(value = "/getPeripheralMasterTotalCount", method = RequestMethod.POST)
public @ResponseBody long getPeripheralMasterTotalCount(HttpSession sessionUserId,String Search,String name){
	return objDAO.getReportListPeripheralMasterTotalCount(Search);
}
  @RequestMapping(value = "/PeripheralMasterAction" ,method = RequestMethod.POST) 
  public ModelAndView PeripheralMasterAction(@Valid @ModelAttribute("PeripheralMasterCMD") TB_MSTR_PERIPHERAL_TYPE_M ln,
		  @RequestParam(value = "msg", required = false) String msg,
		  BindingResult result, 
  HttpServletRequest request, ModelMap model, HttpSession session){ 
	  String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("PeripheralMaster_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}

  	 
 
    if(request.getParameter("peripheral_type").equals("") || request.getParameter("peripheral_type") == null) 
    { 
      model.put("msg", "Please Enter Peripheral Type"); 
      return new ModelAndView("redirect:PeripheralMaster_Url"); 
    } 

 
    Session sessionHQL = HibernateUtil.getSessionFactory().openSession(); 
    Transaction tx = sessionHQL.beginTransaction(); 
    sessionHQL.save(ln); 
    tx.commit(); 
    sessionHQL.close(); 

 
    model.put("msg","Data Saved Successfully"); 
    return new ModelAndView("redirect:PeripheralMaster_Url"); 
  } 
         @RequestMapping(value = "EditPeripheralMasterUrl", method = RequestMethod.POST)
         public ModelAndView EditPeripheralMasterUrl(ModelMap Mmap,HttpSession session,HttpServletRequest request,@RequestParam(value = "msg", required = false) String msg,String updateid) {

        	 String roleid = session.getAttribute("roleid").toString();
 			Boolean val = roledao.ScreenRedirect("PeripheralMaster_Url", roleid);
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
                q = s1.createQuery("from TB_MSTR_PERIPHERAL_TYPE_M where cast(id as string)=:PK");
                q.setString("PK", DcryptedPk);
                @SuppressWarnings("unchecked")
                List<String> list = (List<String>) q.list();
                tx.commit();
                s1.close();
                Mmap.put("EditPeripheralMasterCMD1", list.get(0));
                Mmap.put("msg", msg);
         return new ModelAndView("EditPeripheralMaster_tile","EditPeripheralMasterCMD",new TB_MSTR_PERIPHERAL_TYPE_M());
}
  @RequestMapping(value = "/EditPeripheralMasterAction" ,method = RequestMethod.POST) 
  public ModelAndView EditPeripheralMasterAction(@Valid @ModelAttribute("EditPeripheralMasterCMD") TB_MSTR_PERIPHERAL_TYPE_M ln, BindingResult result, 
  HttpServletRequest request, ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg){ 

	  String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("PeripheralMaster_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}

  	 
    if(request.getParameter("peripheral_type").equals("") || request.getParameter("peripheral_type") == null) 
    { 
      model.put("msg", "Please Enter Peripheral Type"); 
      return new ModelAndView("EditPeripheralMaster_tile"); 
    } 

 
    Session sessionHQL = HibernateUtil.getSessionFactory().openSession(); 
    Transaction tx = sessionHQL.beginTransaction(); 
    ln.setId(Integer.parseInt(request.getParameter("id")));
    sessionHQL.saveOrUpdate(ln); 
    tx.commit(); 
    sessionHQL.close(); 

 
    model.put("msg","Data Updated Successfully"); 
    return new ModelAndView("redirect:PeripheralMaster_Url"); 
  } 
  @RequestMapping(value = "/deletePeripheralMasterUrl", method = RequestMethod.POST) 
  public ModelAndView deletePeripheralMasterUrl(String deleteid,HttpSession session,ModelMap model,
		  @RequestParam(value = "msg", required = false) String msg, HttpServletRequest request ) { 
	  String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("PeripheralMaster_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}

  	 
	  
	  
	  List<String> list = new ArrayList<String>(); 
  	list.add(objDAO.DeletePeripheralMaster(deleteid,session)); 
  	model.put("msg",list);  
    return new ModelAndView("redirect:PeripheralMaster_Url"); 
  	}
} 
