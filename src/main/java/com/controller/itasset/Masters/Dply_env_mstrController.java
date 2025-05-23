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
import com.models.itasset.master.TB_MSTR_DPLY_ENV_M;
import com.controller.itasset.ExportFile.ExcelUserListReportView;
import com.dao.itasset.masters.Dply_env_mstrDAO;
 
import com.persistance.util.HibernateUtil;


@Controller
@RequestMapping(value = {"admin","/" ,"user"})
public class Dply_env_mstrController {


@Autowired
private Dply_env_mstrDAO objDAO;
HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();

@Autowired
RoleBaseMenuDAO roledao;


         @RequestMapping(value = "Dply_env_mstr_Url", method = RequestMethod.GET)
         public ModelAndView Dply_env_mstr_Url(ModelMap Mmap,HttpSession session,HttpServletRequest request,
 @RequestParam(value = "msg", required = false) String msg) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, 
  NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException{

			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Dply_env_mstr_Url", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				//return new ModelAndView("redirect:bodyParameterNotAllow");
			}

        	 
             Mmap.put("msg", msg);
         return new ModelAndView("Dply_env_mstr_tile","dply_env_mstrCMD",new TB_MSTR_DPLY_ENV_M());
}
 @RequestMapping(value = "/getDply_env_mstrReportDataList", method = RequestMethod.POST)
 public @ResponseBody List<Map<String, Object>> getFilteredDataList_SQL(int startPage,String pageLength,String Search,String orderColunm,String orderType,String dply_env,HttpSession sessionUserId) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
	return objDAO.getReportListDply_env_mstr(startPage,pageLength,Search,orderColunm,orderType,dply_env,sessionUserId);
}

 @RequestMapping(value = "/getDply_env_mstrTotalCount", method = RequestMethod.POST)
public @ResponseBody long getDply_env_mstrTotalCount(HttpSession sessionUserId,String Search,String dply_env){
	return objDAO.getReportListDply_env_mstrTotalCount(Search,dply_env);
}
  @RequestMapping(value = "/dply_env_mstrAction" ,method = RequestMethod.POST) 
  public ModelAndView dply_env_mstrAction(@Valid @ModelAttribute("dply_env_mstrCMD") TB_MSTR_DPLY_ENV_M ln, BindingResult result, 
  HttpServletRequest request, ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg){ 
	  String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Dply_env_mstr_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}
	  String dply_env= request.getParameter("dply_env").trim();
    if(dply_env.equals("") || dply_env == null) 
    { 
      model.put("msg", "Please Enter Deployment Environment"); 
      return new ModelAndView("redirect:Dply_env_mstr_Url"); 
    } 
    if(dply_env.length() > 100) {
		 model.put("msg", "Deployment Environment Length Should Be Less Than 100 Characters");
		 return new ModelAndView("redirect:Dply_env_mstr_Url");
	 }
 
    Session sessionHQL = HibernateUtil.getSessionFactory().openSession(); 
    Transaction tx = sessionHQL.beginTransaction(); 
    try{			
		Query q0 = sessionHQL.createQuery(
				"select count(id) from TB_MSTR_DPLY_ENV_M where  UPPER(dply_env)=:dply_env and id !=:id");
		q0.setParameter("dply_env", ln.getDply_env().toUpperCase());
		q0.setParameter("id", ln.getId());
		
		Long c = (Long) q0.uniqueResult();
		if (c == 0) {
			ln.setDply_env(dply_env.toUpperCase());
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
    return new ModelAndView("redirect:Dply_env_mstr_Url"); 
  } 
         @RequestMapping(value = "EditDply_env_mstrUrl", method = RequestMethod.POST)
         public ModelAndView EditDply_env_mstrUrl(ModelMap Mmap,HttpSession session,HttpServletRequest request,@RequestParam(value = "msg", required = false) String msg,String updateid) {

        	 String roleid = session.getAttribute("roleid").toString();
 			Boolean val = roledao.ScreenRedirect("Dply_env_mstr_Url", roleid);
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
                q = s1.createQuery("from TB_MSTR_DPLY_ENV_M where cast(id as string)=:PK");
                q.setString("PK", DcryptedPk);
                @SuppressWarnings("unchecked")
                List<String> list = (List<String>) q.list();
                tx.commit();
                s1.close();
                Mmap.put("Editdply_env_mstrCMD1", list.get(0));
                Mmap.put("msg", msg);
         return new ModelAndView("EditDply_env_mstr_tile","Editdply_env_mstrCMD",new TB_MSTR_DPLY_ENV_M());
}
  @RequestMapping(value = "/Editdply_env_mstrAction" ,method = RequestMethod.POST) 
  public ModelAndView Editdply_env_mstrAction(@Valid @ModelAttribute("Editdply_env_mstrCMD") TB_MSTR_DPLY_ENV_M ln,
		  @RequestParam(value = "msg", required = false) String msg,
		  BindingResult result, 
         HttpServletRequest request, ModelMap model, HttpSession session){ 
	  String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Dply_env_mstr_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}
	  String dply_env= request.getParameter("dply_env").trim();
      String enckey ="commonPwdEncKeys";
	try {
		Cipher c = hex_asciiDao.EncryptionSHA256Algo(session,enckey);

    try {
		String EncryptedPk =new String(Base64.encodeBase64(c.doFinal(request.getParameter("id").toString().getBytes())));
		
		 if(dply_env.equals("") ||dply_env == null) 
		    { 
			return EditDply_env_mstrUrl(model,session,request, "Please Enter Deployment Environment",EncryptedPk);
		}
		if(dply_env.length() > 100) {
			return EditDply_env_mstrUrl(model,session,request, "Deployment Environment Length Should Be Less Than 100 Characters",EncryptedPk);
			
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
				"select count(id) from TB_MSTR_DPLY_ENV_M where  UPPER(dply_env)=:dply_env and id !=:id");
		q0.setParameter("dply_env", ln.getDply_env().toUpperCase());
		q0.setParameter("id", ln.getId());
		
		Long c = (Long) q0.uniqueResult();
		if (c == 0) {
			ln.setDply_env(dply_env.toUpperCase());
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
 
    
    return new ModelAndView("redirect:Dply_env_mstr_Url"); 
  } 
  @RequestMapping(value = "/deletedply_env_mstrUrl", method = RequestMethod.POST) 
  public ModelAndView deletedply_env_mstrUrl(String deleteid,HttpSession session,ModelMap model,
		  @RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) { 
	  String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Dply_env_mstr_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}
	  
	  
	  List<String> list = new ArrayList<String>(); 
  	list.add(objDAO.Deletedply_env_mstr(deleteid,session)); 
  	model.put("msg",list);  
    return new ModelAndView("redirect:Dply_env_mstr_Url"); 
  	}
  

	@RequestMapping(value = "/Dplymastereport", method = RequestMethod.POST)
	public ModelAndView Dplymastereport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1) {

		String dply_env = request.getParameter("dply_env1");

		ArrayList<ArrayList<String>> CTlist = objDAO.Report_DataTableMakeList(dply_env);
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("Deployment Environment");
		
		
		String Heading = "\nODeplyEnv Master";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
} 
