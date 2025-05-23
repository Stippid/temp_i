package com.controller.itasset.Masters;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
import com.dao.itasset.masters.RANKDAO;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.models.itasset.master.TB_MASTER_RANK;
import com.models.itasset.master.TB_MSTR_ANTIVIRUS_M;
import com.persistance.util.HibernateUtil;
@Controller
@RequestMapping(value = {"admin","/" ,"user"})
public class Rank_Controller {
	@Autowired
	private RANKDAO objDAO;
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	@Autowired
	RoleBaseMenuDAO roledao;
	
    @RequestMapping(value = "RANK_Url", method = RequestMethod.GET)
    public ModelAndView RANK_Url(ModelMap Mmap,HttpSession session,HttpServletRequest request,@RequestParam(value = "msg", required = false) String msg) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, 
NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException{
		String roleid = session.getAttribute("roleid").toString();
//		Boolean val = roledao.ScreenRedirect("RANK_Url", roleid);
//		if (val == false) {
//			return new ModelAndView("AccessTiles");
//		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}

        Mmap.put("msg", msg);
    return new ModelAndView("RANK_tile","RANKCMD",new TB_MASTER_RANK());
}
    @RequestMapping(value = "/RANKAction" ,method = RequestMethod.POST) 
    public ModelAndView RANKAction(@Valid @ModelAttribute("RANKCMD") TB_MASTER_RANK ln, BindingResult result, 
    HttpServletRequest request, ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg){ 
  	  String roleid = session.getAttribute("roleid").toString();
//  		Boolean val = roledao.ScreenRedirect("RANK_Url", roleid);
//  		if (val == false) {
//  			return new ModelAndView("AccessTiles");
//  		}
//  		if (request.getHeader("Referer") == null) {
//  			msg = "";
//  			//return new ModelAndView("redirect:bodyParameterNotAllow");
//  		}
  	Date currentDate = new Date();
   String rank =request.getParameter("rank").trim();
  	if(rank.equals("") || rank == null) 
      { 
        model.put("msg", "Please Enter Rank Name"); 
        return new ModelAndView("redirect:RANK_Url"); 
      } 
  	if(rank.length() > 100) {
  		  model.put("msg", "Rank Name Length Should Be Less Than 100 Characters");
  			 return new ModelAndView("redirect:RANK_Url");
  	  }
  	String status = request.getParameter("status").trim();
  	if(status.equals("0")) {
  		model.put("msg", "Please Select Status"); 
        return new ModelAndView("redirect:RANK_Url"); 
  		
  	}
   
      Session sessionHQL = HibernateUtil.getSessionFactory().openSession(); 
      Transaction tx = sessionHQL.beginTransaction(); 
      try{			
  		Query q0 = sessionHQL.createQuery(
  				"select count(id) from TB_MASTER_RANK where  UPPER(rank)=:rank and (status)=:status and id !=:id");
  		q0.setParameter("rank",ln.getRank().toUpperCase());
  		q0.setParameter("status",ln.getStatus());
  		q0.setParameter("id", ln.getId());
  		
  		Long c = (Long) q0.uniqueResult();
  		if (c == 0) {
  			ln.setRank(rank.toUpperCase());
  			ln.setStatus(status);
  			ln.setCreated_date(currentDate);
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
      return new ModelAndView("redirect:RANK_Url"); 
    } 
    @RequestMapping(value = "/getRANKReportDataList", method = RequestMethod.POST)
    public @ResponseBody List<Map<String, Object>> getRANKReportDataList(int startPage,String pageLength,String Search,String orderColunm,String orderType ,String rank,String status,HttpSession sessionUserId) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
   	return objDAO.getReportListRANK(startPage,pageLength,Search,orderColunm,orderType,rank,status,sessionUserId);
   }
    

    @RequestMapping(value = "/getRANKTotalCount", method = RequestMethod.POST)
   public @ResponseBody long getRANKTotalCount(HttpSession sessionUserId,String Search,String rank, String status){
   	return objDAO.getReportListRANKTotalCount(Search,rank,status);
   }
    @RequestMapping(value = "/deleteRANKUrl", method = RequestMethod.POST) 
    public ModelAndView deleteRANKUrl(String deleteid,HttpSession session,ModelMap model,
  		  HttpServletRequest request,@RequestParam(value = "msg", required = false) String msg  
  		  
  		  ) { 
  	  String roleid = session.getAttribute("roleid").toString();
  	  Boolean val = roledao.ScreenRedirect("ANTIVIRUS_Url", roleid);
  			if (val == false) {
  				return new ModelAndView("AccessTiles");
  			}
  			if (request.getHeader("Referer") == null) {
  				msg = "";
  				//return new ModelAndView("redirect:bodyParameterNotAllow");
  			}
  	  
  	  List<String> list = new ArrayList<String>(); 
    	list.add(objDAO.DeleteRANK(deleteid,session)); 
    	model.put("msg",list);  
      return new ModelAndView("redirect:RANK_Url"); 
    	}
    @RequestMapping(value = "EditRANKUrl", method = RequestMethod.POST)
    public ModelAndView EditRANKUrl(ModelMap Mmap,HttpSession session,HttpServletRequest request,@RequestParam(value = "msg", required = false) String msg,String updateid) {
   	 String roleid = session.getAttribute("roleid").toString();
//		Boolean val = roledao.ScreenRedirect("RANK_Url", roleid);
//		if (val == false) {
//			return new ModelAndView("AccessTiles");
//		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}


           Session s1 = HibernateUtil.getSessionFactory().openSession();
           Transaction tx = s1.beginTransaction();
           String enckey = "commonPwdEncKeys";  
           String DcryptedPk = hex_asciiDao.decrypt((String) updateid,enckey,session); 
           Query q = null;
           q = s1.createQuery("from TB_MASTER_RANK where cast(id as string)=:PK");
           q.setString("PK", DcryptedPk);
        
           @SuppressWarnings("unchecked")
           List<String> list = (List<String>) q.list();
           tx.commit();
           s1.close();
           Mmap.put("EditRANKCMD1", list.get(0));
           Mmap.put("msg", msg);
    return new ModelAndView("EditRANK_tile","EditRANKCMD",new TB_MASTER_RANK());
}
    @RequestMapping(value = "/EditRANKAction" ,method = RequestMethod.POST) 
    public ModelAndView EditRANKAction(@Valid @ModelAttribute("EditRANKCMD") TB_MASTER_RANK ln, BindingResult result, 
    HttpServletRequest request, ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg){ 
  		
  	  String roleid = session.getAttribute("roleid").toString();
//  	  Boolean val = roledao.ScreenRedirect("ANTIVIRUS_Url", roleid);
//  			if (val == false) {
//  				return new ModelAndView("AccessTiles");
//  			}
//  			if (request.getHeader("Referer") == null) {
//  				msg = "";
//  				//return new ModelAndView("redirect:bodyParameterNotAllow");
//  			}
   	Date currentDate = new Date();
  	  String rank =request.getParameter("rank").trim();
  	 String status =request.getParameter("status").trim();
  	  String enckey ="commonPwdEncKeys";
  		try {
  			Cipher c = hex_asciiDao.EncryptionSHA256Algo(session,enckey);

  	    try {
  			String EncryptedPk =new String(Base64.encodeBase64(c.doFinal(request.getParameter("id").toString().getBytes())));
  			
  			if(rank.equals("") || rank == null) 
  		    { 
  		      return EditRANKUrl(model,session,request, "Please Enter Antivirus Name",EncryptedPk);
  		    } 
  		    if (rank.length() > 100) {
  		    	 return EditRANKUrl(model,session,request, "Antivirus Name Length Should Be Less Than 100 Characters",EncryptedPk);
  		    }
  		   if (status.equals("0") ) {
		    	 return EditRANKUrl(model,session,request, "Please Select Status",EncryptedPk);
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
  				"select count(id) from TB_MASTER_RANK where  UPPER(rank)=:rank and (status)=:status and id !=:id");
  		q0.setParameter("rank", ln.getRank().toUpperCase());
  		q0.setParameter("status", ln.getStatus().toUpperCase());
  		q0.setParameter("id", ln.getId());
  		
  		Long c = (Long) q0.uniqueResult();
  		if (c == 0) {
  			ln.setRank(rank.toUpperCase());
  			ln.setStatus(status);
  			ln.setModified_date(currentDate);
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
      return new ModelAndView("redirect:RANK_Url"); 
    }
    
  	@RequestMapping(value = "/RANKmastereport", method = RequestMethod.POST)
  	public ModelAndView RANKmastereport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1) {

  		String rank = request.getParameter("antivirus1");
  		String status = request.getParameter("status1");
  		ArrayList<ArrayList<String>> CTlist = objDAO.Report_DataTableMakeRankList(rank,status);
  		List<String> TH = new ArrayList<String>();
  		TH.add("ID");
  		TH.add("Rank Name");
  		TH.add("Status");
  		String Heading = "\nORank Master";
  		String username = session.getAttribute("username").toString();
  		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
  	}
}
