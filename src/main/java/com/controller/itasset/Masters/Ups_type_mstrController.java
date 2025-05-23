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
import com.models.itasset.master.TB_MSTR_UPS_TYPE_M;
import com.controller.itasset.ExportFile.ExcelUserListReportView;
import com.dao.itasset.masters.Ups_type_mstrDAO;
 
import com.persistance.util.HibernateUtil;


@Controller
@RequestMapping(value = {"admin","/" ,"user"})
public class Ups_type_mstrController {


@Autowired
private Ups_type_mstrDAO objDAO;
HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();

@Autowired
RoleBaseMenuDAO roledao;


         @RequestMapping(value = "Ups_type_mstr_Url", method = RequestMethod.GET)
         public ModelAndView Ups_type_mstr_Url(ModelMap Mmap,HttpSession session,HttpServletRequest request,@RequestParam(value = "msg", required = false) String msg) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, 
  NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException{

			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Ups_type_mstr_Url", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				//return new ModelAndView("redirect:bodyParameterNotAllow");
			}

        	 
             Mmap.put("msg", msg);
         return new ModelAndView("Ups_type_mstr_tile","ups_type_mstrCMD",new TB_MSTR_UPS_TYPE_M());
}
 @RequestMapping(value = "/getUps_type_mstrReportDataList", method = RequestMethod.POST)
 public @ResponseBody List<Map<String, Object>> getFilteredDataList_SQL(int startPage,String pageLength,String Search,String orderColunm,String orderType,String ups_type,HttpSession sessionUserId) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
	return objDAO.getReportListUps_type_mstr(startPage,pageLength,Search,orderColunm,orderType,ups_type,sessionUserId);
}

 @RequestMapping(value = "/getUps_type_mstrTotalCount", method = RequestMethod.POST)
public @ResponseBody long getUps_type_mstrTotalCount(HttpSession sessionUserId,String Search,String ups_type){
	return objDAO.getReportListUps_type_mstrTotalCount(Search,ups_type);
}
  @RequestMapping(value = "/ups_type_mstrAction" ,method = RequestMethod.POST) 
  public ModelAndView ups_type_mstrAction(@Valid @ModelAttribute("ups_type_mstrCMD") TB_MSTR_UPS_TYPE_M ln, BindingResult result, 
  HttpServletRequest request, ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg){ 

	  
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Ups_type_mstr_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}
	  String ups_type = request.getParameter("ups_type").trim();
 
    if(ups_type.equals("") || ups_type == null) 
    { 
      model.put("msg", "Please Enter Type Of UPS"); 
      return new ModelAndView("redirect:Ups_type_mstr_Url"); 
    } 

 
    Session sessionHQL = HibernateUtil.getSessionFactory().openSession(); 
    Transaction tx = sessionHQL.beginTransaction(); 
    try{			
		Query q0 = sessionHQL.createQuery(
				"select count(id) from TB_MSTR_UPS_TYPE_M where  UPPER(ups_type)=:ups_type and id !=:id");
		
		q0.setParameter("id", ln.getId());
		q0.setParameter("ups_type", ln.getUps_type().toUpperCase());
		Long c = (Long) q0.uniqueResult();
		if (c == 0) {
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
    return new ModelAndView("redirect:Ups_type_mstr_Url"); 
  } 
         @RequestMapping(value = "EditUps_type_mstrUrl", method = RequestMethod.POST)
         public ModelAndView EditUps_type_mstrUrl(ModelMap Mmap,HttpSession session,HttpServletRequest request,
        		 @RequestParam(value = "msg", required = false) String msg,String updateid) {

        	 String roleid = session.getAttribute("roleid").toString();
 			Boolean val = roledao.ScreenRedirect("Ups_type_mstr_Url", roleid);
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
                q = s1.createQuery("from TB_MSTR_UPS_TYPE_M where cast(id as string)=:PK");
                q.setString("PK", DcryptedPk);
                @SuppressWarnings("unchecked")
                List<String> list = (List<String>) q.list();
                tx.commit();
                s1.close();
                Mmap.put("Editups_type_mstrCMD1", list.get(0));
                Mmap.put("msg", msg);
         return new ModelAndView("EditUps_type_mstr_tile","Editups_type_mstrCMD",new TB_MSTR_UPS_TYPE_M());
}
  @RequestMapping(value = "/Editups_type_mstrAction" ,method = RequestMethod.POST) 
  public ModelAndView Editups_type_mstrAction(@Valid @ModelAttribute("Editups_type_mstrCMD") TB_MSTR_UPS_TYPE_M ln, BindingResult result, 
  HttpServletRequest request, ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg){ 
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Ups_type_mstr_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}
	  String ups_type = request.getParameter("ups_type").trim();
	  
	    if(ups_type.equals("") || ups_type == null) 
	    { 
	      model.put("msg", "Please Enter Type Of UPS"); 
	      return new ModelAndView("EditUps_type_mstr_tile"); 
	    } 
   
    Session sessionHQL = HibernateUtil.getSessionFactory().openSession(); 
    Transaction tx = sessionHQL.beginTransaction(); 
    ln.setId(Integer.parseInt(request.getParameter("id")));
    try{			
		Query q0 = sessionHQL.createQuery(
				"select count(id) from TB_MSTR_UPS_TYPE_M where  UPPER(ups_type)=:ups_type and id !=:id");
		
		q0.setParameter("id", ln.getId());
		q0.setParameter("ups_type", ln.getUps_type().toUpperCase());
		Long c = (Long) q0.uniqueResult();
		if (c == 0) {
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
    return new ModelAndView("redirect:Ups_type_mstr_Url"); 
  } 
  @RequestMapping(value = "/deleteups_type_mstrUrl", method = RequestMethod.POST) 
  public ModelAndView deleteups_type_mstrUrl(String deleteid,HttpSession session,ModelMap model,
		  @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) { 
  	
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Ups_type_mstr_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}
	  List<String> list = new ArrayList<String>(); 
  	list.add(objDAO.Deleteups_type_mstr(deleteid,session)); 
  	model.put("msg",list);  
    return new ModelAndView("redirect:Ups_type_mstr_Url"); 
  	}
  
  
  @RequestMapping(value = "/UPSmastereport", method = RequestMethod.POST)
	public ModelAndView UPSmastereport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1) {

		String ups_type = request.getParameter("ups_type1");

		ArrayList<ArrayList<String>> CTlist = objDAO.Report_DataTableMakeList(ups_type);
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("Type Of UPS");
		
		
		String Heading = "\nOUPS Master";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
} 
