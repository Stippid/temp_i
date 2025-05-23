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
import com.models.itasset.master.TB_MSTR_YEAR_OF_MANUFACTURING_M;
import com.dao.itasset.masters.Year_of_manufacturing_mstrDAO;
 
import com.persistance.util.HibernateUtil;


@Controller
@RequestMapping(value = {"admin","/" ,"user"})
public class Year_of_manufacturing_mstrController {


@Autowired
private Year_of_manufacturing_mstrDAO objDAO;
HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();

@Autowired
RoleBaseMenuDAO roledao;

         @RequestMapping(value = "Year_of_manufacturing_mstr_Url", method = RequestMethod.GET)
         public ModelAndView Year_of_manufacturing_mstr_Url(ModelMap Mmap,HttpSession session,HttpServletRequest request,@RequestParam(value = "msg", required = false) String msg) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, 
  NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException{
        	 
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Year_of_manufacturing_mstr_Url", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				//return new ModelAndView("redirect:bodyParameterNotAllow");
			}


             Mmap.put("msg", msg);
         return new ModelAndView("Year_of_manufacturing_mstr_tile","year_of_manufacturing_mstrCMD",new TB_MSTR_YEAR_OF_MANUFACTURING_M());
}
 @RequestMapping(value = "/getYear_of_manufacturing_mstrReportDataList", method = RequestMethod.POST)
 public @ResponseBody List<Map<String, Object>> getFilteredDataList_SQL(int startPage,String pageLength,String Search,String orderColunm,String orderType,HttpSession sessionUserId) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
	return objDAO.getReportListYear_of_manufacturing_mstr(startPage,pageLength,Search,orderColunm,orderType,sessionUserId);
}

 @RequestMapping(value = "/getYear_of_manufacturing_mstrTotalCount", method = RequestMethod.POST)
public @ResponseBody long getYear_of_manufacturing_mstrTotalCount(HttpSession sessionUserId,String Search,String name){
	return objDAO.getReportListYear_of_manufacturing_mstrTotalCount(Search);
}
  @RequestMapping(value = "/year_of_manufacturing_mstrAction" ,method = RequestMethod.POST) 
  public ModelAndView year_of_manufacturing_mstrAction(@Valid @ModelAttribute("year_of_manufacturing_mstrCMD") TB_MSTR_YEAR_OF_MANUFACTURING_M ln, BindingResult result, 
  HttpServletRequest request, ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg){ 

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Year_of_manufacturing_mstr_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}
    if(request.getParameter("year_of_manufacturing").equals("") || request.getParameter("year_of_manufacturing") == null) 
    { 
      model.put("msg", "Please Enter Year of Manufacturing"); 
      return new ModelAndView("redirect:Year_of_manufacturing_mstr_Url"); 
    } 

 
    Session sessionHQL = HibernateUtil.getSessionFactory().openSession(); 
    Transaction tx = sessionHQL.beginTransaction(); 
    sessionHQL.save(ln); 
    tx.commit(); 
    sessionHQL.close(); 

 
    model.put("msg","Data Saved Successfully"); 
    return new ModelAndView("redirect:Year_of_manufacturing_mstr_Url"); 
  } 
         @RequestMapping(value = "EditYear_of_manufacturing_mstrUrl", method = RequestMethod.POST)
         public ModelAndView EditYear_of_manufacturing_mstrUrl(ModelMap Mmap,HttpSession session,HttpServletRequest request,@RequestParam(value = "msg", required = false) String msg,String updateid) {

        	 String roleid = session.getAttribute("roleid").toString();
 			Boolean val = roledao.ScreenRedirect("Year_of_manufacturing_mstr_Url", roleid);
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
                q = s1.createQuery("from TB_MSTR_YEAR_OF_MANUFACTURING_M where cast(id as string)=:PK");
                q.setString("PK", DcryptedPk);
                @SuppressWarnings("unchecked")
                List<String> list = (List<String>) q.list();
                tx.commit();
                s1.close();
                Mmap.put("Edityear_of_manufacturing_mstrCMD1", list.get(0));
                Mmap.put("msg", msg);
         return new ModelAndView("EditYear_of_manufacturing_mstr_tile","Edityear_of_manufacturing_mstrCMD",new TB_MSTR_YEAR_OF_MANUFACTURING_M());
}
  @RequestMapping(value = "/Edityear_of_manufacturing_mstrAction" ,method = RequestMethod.POST) 
  public ModelAndView Edityear_of_manufacturing_mstrAction(@Valid @ModelAttribute("Edityear_of_manufacturing_mstrCMD") TB_MSTR_YEAR_OF_MANUFACTURING_M ln, BindingResult result, 
  HttpServletRequest request, ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg){ 
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Year_of_manufacturing_mstr_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}
 
    if(request.getParameter("year_of_manufacturing").equals("") || request.getParameter("year_of_manufacturing") == null) 
    { 
      model.put("msg", "Please Enter Year of Manufacturing"); 
      return new ModelAndView("EditYear_of_manufacturing_mstr_tile"); 
    } 

 
    Session sessionHQL = HibernateUtil.getSessionFactory().openSession(); 
    Transaction tx = sessionHQL.beginTransaction(); 
    ln.setId(Integer.parseInt(request.getParameter("id")));
    sessionHQL.saveOrUpdate(ln); 
    tx.commit(); 
    sessionHQL.close(); 

 
    model.put("msg","Data Updated Successfully"); 
    return new ModelAndView("redirect:Year_of_manufacturing_mstr_Url"); 
  } 
  @RequestMapping(value = "/deleteyear_of_manufacturing_mstrUrl", method = RequestMethod.POST) 
  public ModelAndView deleteyear_of_manufacturing_mstrUrl(String deleteid,HttpSession session,ModelMap model,
		  @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) { 
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Year_of_manufacturing_mstr_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}
	  
	  List<String> list = new ArrayList<String>(); 
  	list.add(objDAO.Deleteyear_of_manufacturing_mstr(deleteid,session)); 
  	model.put("msg",list);  
    return new ModelAndView("redirect:Year_of_manufacturing_mstr_Url"); 
  	}
} 
