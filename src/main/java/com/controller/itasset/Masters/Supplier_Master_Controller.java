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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.controller.itasset.ExportFile.ExcelUserListReportView;
import com.controller.validation.ValidationController;
import com.dao.itasset.masters.Supply_MasterDAO;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.models.itasset.master.TB_MASTER_MAINTENANCE_AGENCY;
import com.models.itasset.master.TB_MASTER_SUPPLY_MASTER;
import com.models.itasset.master.TB_MSTR_ANTIVIRUS_M;
import com.persistance.util.HibernateUtil;


@Controller
@RequestMapping(value = {"admin","/" ,"user"})
public class Supplier_Master_Controller {
	
	@Autowired
	RoleBaseMenuDAO roledao;
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl(); 
ValidationController validation = new ValidationController();

@Autowired
Supply_MasterDAO smdo;



    @RequestMapping(value = "Supply_Mstrurl", method = RequestMethod.GET)
    public ModelAndView Supply_Mstrurl(ModelMap Mmap,HttpSession session,HttpServletRequest request,@RequestParam(value = "msg", required = false) String msg) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, 
NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException{

//		String roleid = session.getAttribute("roleid").toString();
//		Boolean val = roledao.ScreenRedirect("Supply_Mstrurl", roleid);
//		if (val == false) {
//			return new ModelAndView("AccessTiles");
//		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}

        Mmap.put("msg", msg);
    return new ModelAndView("Supply_Master_tile","VENDORCMD",new TB_MASTER_SUPPLY_MASTER());
	}

	  @RequestMapping(value = "/VENDORAction" ,method = RequestMethod.POST) 
	  public ModelAndView VENDORAction(@Valid @ModelAttribute("VENDORCMD") TB_MASTER_SUPPLY_MASTER ln, BindingResult result, 
	  HttpServletRequest request, ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg){ 
//		  String roleid = session.getAttribute("roleid").toString();
//			Boolean val = roledao.ScreenRedirect("ANTIVIRUS_Url", roleid);
//			if (val == false) {
//				return new ModelAndView("AccessTiles");
//			}
//			if (request.getHeader("Referer") == null) {
//				msg = "";
//				//return new ModelAndView("redirect:bodyParameterNotAllow");
//			}
		  String supplier = request.getParameter("supplier");
		  String contact_number = request.getParameter("contact_number");
		  int id = ln.getId() > 0 ? ln.getId() : 0;
			String rediurl = "";

			if (id == 0) {
				rediurl = "redirect:Supply_Mstrurl";
			} else {
				rediurl = "redirect:Supply_Mstrurl";
			}
		  
	     if (ln.getSupplier() == null || ln.getSupplier().isEmpty()) {
	         model.put("msg", "Please Enter Supplier Name");
	         return new ModelAndView(rediurl);
	     }
	     
	     // Validate Contact Number
	     if (ln.getContact_number() == null || ln.getContact_number().isEmpty()) {
	         model.put("msg", "Please Enter Contact Number");
	         return new ModelAndView(rediurl);
	     }

	     // Validate Mobile Number
	     if (!validation.isValidMobileNo(ln.getContact_number())) {
	    	 model.put("msg", validation.isValidMobileNoMSG);
	         return new ModelAndView(rediurl);
	     }

	     Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	     Transaction tx = sessionHQL.beginTransaction();
	     try {
	         Query q0 = sessionHQL.createQuery(
	             "select count(id) from TB_MASTER_SUPPLY_MASTER where UPPER(supplier) = :supplier and contact_number = :contact_number and id != :id");
	         q0.setParameter("supplier", ln.getSupplier().toUpperCase());
	         q0.setParameter("contact_number", ln.getContact_number());
	         q0.setParameter("id", ln.getId());

	         Long c = (Long) q0.uniqueResult();
	         if (c == 0) {
	             ln.setSupplier(supplier);
	             ln.setContact_number(contact_number);
	             sessionHQL.save(ln);
	             model.put("msg", "Data Saved Successfully");
	         } else {
//	             redirectAttributes.addFlashAttribute("msg", "Data already Exist");
	         }
	         tx.commit();
	     } catch (RuntimeException e) {
	         try {
	             tx.rollback();
	             model.put("msg", "Roll back transaction");
	         } catch (RuntimeException rbe) {
	             model.put("msg", "Couldn't roll back transaction: " + rbe);
	         }
	         throw e;
	     } finally {
	         if (sessionHQL != null) {
	             sessionHQL.close();
	         }
	     }
	     return new ModelAndView(rediurl);
	 }
	 @RequestMapping(value = "/getVendorReportDataList", method = RequestMethod.POST)
	 public @ResponseBody List<Map<String, Object>> getVendorReportDataList(int startPage,String pageLength,String Search,String orderColunm,String orderType ,String supplier, String contact_number,HttpSession sessionUserId) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
		 return smdo.getReportListSUPPLY(startPage,pageLength,Search,orderColunm,orderType,supplier,contact_number,sessionUserId);
	}

	 @RequestMapping(value = "/getVendorTotalCount", method = RequestMethod.POST)
	public @ResponseBody long getVendorTotalCount(HttpSession sessionUserId,String Search,String supplier, String contact_number){
		return smdo.getReportListSUPPLYTotalCount(Search,supplier,contact_number);
	    
}
	  @RequestMapping(value = "/deleteSUPPLYUrl", method = RequestMethod.POST) 
	    public ModelAndView deleteSUPPLYUrl(String deleteid,HttpSession session,ModelMap model,
	  		  HttpServletRequest request,@RequestParam(value = "msg", required = false) String msg  
	  		  
	  		  ) { 
	  	  String roleid = session.getAttribute("roleid").toString();
	  	  Boolean val = roledao.ScreenRedirect("maintenance_agency_Url", roleid);
	  			if (val == false) {
	  				return new ModelAndView("AccessTiles");
	  			}
	  			if (request.getHeader("Referer") == null) {
	  				msg = "";
	  				//return new ModelAndView("redirect:bodyParameterNotAllow");
	  			}
	  	  
	  	  List<String> list = new ArrayList<String>(); 
	    	list.add(smdo.DeleteSUPPLY(deleteid,session)); 
	    	model.put("msg",list);  
	      return new ModelAndView("Supply_Master_tile", "VENDORCMD", new TB_MASTER_SUPPLY_MASTER()); 
	    	}
	  

	  @RequestMapping(value = "EditSUPPLYUrl", method = RequestMethod.POST)
	  public ModelAndView EditSUPPLYUrl(ModelMap Mmap, HttpSession session, HttpServletRequest request, @RequestParam(value = "msg", required = false) String msg, String updateid) {
	      Session s1 = HibernateUtil.getSessionFactory().openSession();
	      Transaction tx = s1.beginTransaction();
	      String enckey = "commonPwdEncKeys";  
	      String DcryptedPk = hex_asciiDao.decrypt((String) updateid, enckey, session); 
	      Query q = s1.createQuery("from TB_MASTER_SUPPLY_MASTER where cast(id as string)=:PK");
	      q.setString("PK", DcryptedPk);
	      
	      @SuppressWarnings("unchecked")
	      List<TB_MASTER_SUPPLY_MASTER> list = (List<TB_MASTER_SUPPLY_MASTER>) q.list();
	      tx.commit();
	      s1.close();
	      
	      if (!list.isEmpty()) {
	          Mmap.put("EditVENDORCMD", list.get(0)); // Ensure this is an instance of TB_MASTER_SUPPLY_MASTER
	      } else {
	          Mmap.put("EditVENDORCMD", new TB_MASTER_SUPPLY_MASTER()); // Handle case where no data is found
	      }
	      Mmap.put("msg", msg);
	      return new ModelAndView("Edit_Supply_Master_tile");
	  }
	  @RequestMapping(value = "/EditVENDORAction", method = RequestMethod.POST)
	  public ModelAndView EditVENDORAction(@Valid @ModelAttribute("EditVENDORCMD") TB_MASTER_SUPPLY_MASTER ln, 
	                                        BindingResult result, 
	                                        HttpServletRequest request, 
	                                        ModelMap model, 
	                                        HttpSession session, 
	                                        @RequestParam(value = "msg", required = false) String msg) {
	      String enckey = "commonPwdEncKeys";
	      String rediurl = "redirect:Supply_Mstrurl"; // Default redirect URL

	      try {
	          Cipher c1 = hex_asciiDao.EncryptionSHA256Algo(session, enckey);
	          int id = ln.getId() > 0 ? ln.getId() : 0;

	          // Validate Supplier Name
	          if (ln.getSupplier() == null || ln.getSupplier().isEmpty()) {
	              model.put("msg", "Please Enter Supplier Name");
	              return new ModelAndView(rediurl);
	          }

	          // Validate Contact Number
	          if (ln.getContact_number() == null || ln.getContact_number().isEmpty()) {
	              model.put("msg", "Please Enter Contact Number");
	              return new ModelAndView(rediurl);
	          }

	          // Validate Mobile Number
	          if (!validation.isValidMobileNo(ln.getContact_number())) {
	              model.put("msg", validation.isValidMobileNoMSG);
	              return new ModelAndView(rediurl);
	          }

	          // Encrypt ID
	          String EncryptedPk = new String(Base64.encodeBase64(c1.doFinal(request.getParameter("id").toString().getBytes())));

	          // Database operations
	          Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	          Transaction tx = sessionHQL.beginTransaction();
	          ln.setId(Integer.parseInt(request.getParameter("id")));
	          String supplier = request.getParameter("supplier");
	          String contact_number = request.getParameter("contact_number");

	          try {
	              Query q0 = sessionHQL.createQuery(
	                  "select count(id) from TB_MASTER_SUPPLY_MASTER where UPPER(supplier)=:supplier and contact_number = :contact_number and id !=:id");
	              q0.setParameter("supplier", ln.getSupplier().toUpperCase());
	              q0.setParameter("contact_number", ln.getContact_number().toUpperCase());
	              q0.setParameter("id", ln.getId());

	              Long c = (Long) q0.uniqueResult();
	              if (c == 0) {
	                  ln.setSupplier(supplier); // This line is redundant
	                  ln.setContact_number(contact_number); // This line is redundant
	                  sessionHQL.saveOrUpdate(ln);
	                  model.put("msg", "Data Updated Successfully");
	              } else {
	                  model.put("msg", "Data already Exist");
	              }
	              tx.commit();
	          } catch (RuntimeException e) {
	              if (tx != null) {
	                  tx.rollback();
	                  model.put("msg", "Transaction rolled back due to an error");
	              }
	              throw e; // Rethrow the exception after rollback
	          } finally {
	              if (sessionHQL != null) {
	                  sessionHQL.close();
	              }
	          }
	      } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
	              | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
	          e.printStackTrace();
	          model.put("msg", "Encryption error occurred: " + e.getMessage());
	      }

	      return new ModelAndView(rediurl);
	  }
	  
		@RequestMapping(value = "/SUPPLYmastereport", method = RequestMethod.POST)
		public ModelAndView SUPPLYmastereport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1) {

			String supplier = request.getParameter("supplier1");
			String contact_number = request.getParameter("contact_number1");
			ArrayList<ArrayList<String>> CTlist = smdo.Report_SupplyDataTableMakeList(supplier,contact_number);
			List<String> TH = new ArrayList<String>();
			TH.add("ID");
			TH.add("Supplier Name");
			TH.add("Contact number");
			String Heading = "\nOSupply Master";
			String username = session.getAttribute("username").toString();
			return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
		}
	  
	  
}
