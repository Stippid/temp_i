package com.controller.itasset.Masters;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
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

import com.controller.commonController.It_CommonController;
import com.controller.itasset.ExportFile.ExcelUserListReportView;
import com.dao.itasset.masters.Budget_mstrDAO;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.models.itasset.master.TB_MASTER_BUDGET;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Budget_mstrController {
	It_CommonController it_comm = new It_CommonController();

	@Autowired
	private Budget_mstrDAO objDAO;
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	
	@Autowired
	 RoleBaseMenuDAO roledao;

	@RequestMapping(value = "Budget_mstr_Url", method = RequestMethod.GET)
	public ModelAndView Asset_mstr_Url(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg)
			throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException {
		
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Budget_mstr_Url", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				//return new ModelAndView("redirect:bodyParameterNotAllow");
			}

		// Mmap.put("category", it_comm.getTypeOfAssets());
		Mmap.put("msg", msg);
		return new ModelAndView("budget_mstr_tile", "budget_mstrCMD", new TB_MASTER_BUDGET());
	}

	@RequestMapping(value = "/getBudget_mstrReportDataList", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getFilteredDataList_SQL(int startPage, String pageLength,
			String Search, String orderColunm, String orderType,String budget_head, String budget_code, HttpSession sessionUserId)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		return objDAO.getReportListBudget_mstr(startPage, pageLength, Search, orderColunm, orderType,budget_head, budget_code,sessionUserId);
	}

	@RequestMapping(value = "/getBudget_mstrTotalCount", method = RequestMethod.POST)
	public @ResponseBody long getBudget_mstrTotalCount(HttpSession sessionUserId, String Search,String budget_head, String budget_code) {
		return objDAO.getReportListBudget_mstrTotalCount(Search,budget_head,budget_code);
	}

	@RequestMapping(value = "/budget_mstrAction", method = RequestMethod.POST)
	public ModelAndView asset_mstrAction(@Valid @ModelAttribute("budget_mstrCMD") TB_MASTER_BUDGET ln,
			BindingResult result, HttpServletRequest request, ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg) {
		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Budget_mstr_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		int id = ln.getId() > 0 ? ln.getId() : 0;
		Date date = new Date();
		String username = session.getAttribute("username").toString();
		String budget_head = request.getParameter("budget_head").trim();
		String budget_code = request.getParameter("budget_code").trim();
		if (budget_head.equals("") || budget_head == null || budget_head == "null") {
			model.put("msg", "Please Enter Budget Head");
			return new ModelAndView("redirect:Budget_mstr_Url");
		}
		if(budget_head.length() > 50) {
			  model.put("msg", "Budget Head Length Should Be Less Than 50 Characters");
				 return new ModelAndView("redirect:Budget_mstr_Url");
		  }
		if (budget_code.equals("") || budget_code == null || budget_code == "null") {
			model.put("msg", "Please Enter Budget Code");
			return new ModelAndView("redirect:Budget_mstr_Url");
		}
		
		if(budget_code.length() > 50) {
			  model.put("msg", "Budget Code Length Should Be Less Than 50 Characters");
				 return new ModelAndView("redirect:Budget_mstr_Url");
		  }
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try{			
		Query q0 = sessionHQL.createQuery(
				"select count(id) from TB_MASTER_BUDGET where UPPER(budget_head)=:budget_head AND UPPER(budget_code)=:budget_code and id !=:id");
		q0.setParameter("budget_head", budget_head.toUpperCase());
		q0.setParameter("budget_code",budget_code.toUpperCase());
		q0.setParameter("id", id);
		
		Long c = (Long) q0.uniqueResult();
		if (c == 0) {
			ln.setCreated_by(username);
			ln.setCreated_date(date);
			ln.setBudget_code(budget_code.toUpperCase());
			ln.setBudget_head(budget_head.toUpperCase());
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
				model.put("msg", "Couldn�t roll back transaction " + rbe);
			}
			throw e;
		}finally{
			if(sessionHQL!=null){
			   sessionHQL.close();
			}
		}	
		
		return new ModelAndView("redirect:Budget_mstr_Url");
	}

	@RequestMapping(value = "EditBudget_mstrUrl", method = RequestMethod.POST)
	public ModelAndView EditBudget_mstrUrl(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg, String updateid) {
		
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Budget_mstr_Url", roleid);
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
		String DcryptedPk = hex_asciiDao.decrypt((String) updateid, enckey, session);
		
		Query q = null;
		q = s1.createQuery("from TB_MASTER_BUDGET where cast(id as string)=:PK");
		q.setString("PK", DcryptedPk);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		
		tx.commit();
		s1.close();
		Mmap.put("Editbudget_cntr_mstrCMD", list.get(0));
		Mmap.put("msg", msg);
		return new ModelAndView("Editbudget_mstr_tile", "Editbudget_mstrCMD", new TB_MASTER_BUDGET());
	}

	@RequestMapping(value = "/Editbudget_mstrAction", method = RequestMethod.POST)
	public ModelAndView Editasset_mstrAction(@Valid @ModelAttribute("Editbudget_mstrCMD") TB_MASTER_BUDGET ln,
			BindingResult result, HttpServletRequest request, ModelMap model, HttpSession session,	@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Budget_mstr_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		int id = Integer.parseInt(request.getParameter("id"));
		String budget_head = request.getParameter("budget_head").trim();
		String budget_code = request.getParameter("budget_code").trim();
		
		String enckey ="commonPwdEncKeys";
		try {
			Cipher c = hex_asciiDao.EncryptionSHA256Algo(session,enckey);

	    try {
			String EncryptedPk =new String(Base64.encodeBase64(c.doFinal(request.getParameter("id").toString().getBytes())));
			
			if(budget_head.equals("") || budget_head == null) 
		    { 
		      return EditBudget_mstrUrl(model,session,request, "Please Enter Budget Head",EncryptedPk);
		    } 
		    if (budget_head.length() > 50) {
		    	 return EditBudget_mstrUrl(model,session,request,"Budget Head Length Should Be Less Than 50 Characters",EncryptedPk);
		    }
		    if(budget_code.equals("") || budget_code == null) 
		    { 
		      return EditBudget_mstrUrl(model,session,request,"Please Enter Budget Code",EncryptedPk);
		    } 
		    if (budget_code.length() > 500) {
		    	 return EditBudget_mstrUrl(model,session,request, "Budget Code Length Should Be Less Than 50 Characters",EncryptedPk);
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
		try{			
		Query q0 = sessionHQL.createQuery(
				"select count(id) from TB_MASTER_BUDGET where UPPER(budget_head)=:budget_head AND UPPER(budget_code)=:budget_code and id !=:id");
		q0.setParameter("budget_head", budget_head.toUpperCase());
		q0.setParameter("budget_code",budget_code.toUpperCase());
		q0.setParameter("id", id);
		
		Long c = (Long) q0.uniqueResult();
		if (c == 0) {
		ln.setId(Integer.parseInt(request.getParameter("id")));
		ln.setBudget_code(budget_code.toUpperCase());
		ln.setBudget_head(budget_head.toUpperCase());
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

		//model.put("msg", "Data Updated Successfully");
		return new ModelAndView("redirect:Budget_mstr_Url");
	}

	@RequestMapping(value = "/deletebudget_mstrUrl", method = RequestMethod.POST)
	public ModelAndView deletebudget_mstrUrl(String deleteid, HttpSession session, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Budget_mstr_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		
		List<String> list = new ArrayList<String>();
		list.add(objDAO.Deletebudget_mstr(deleteid, session));
		model.put("msg", list);
		return new ModelAndView("redirect:Budget_mstr_Url");
	}
	
	@RequestMapping(value = "/Budgetmastereport", method = RequestMethod.POST)
	public ModelAndView Budgetmastereport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1) {

	 String budget_head = request.getParameter("budget_head1");
	 String budget_code = request.getParameter("budget_code1");
	

		ArrayList<ArrayList<String>> CTlist = objDAO.Report_DataTableMakeList(budget_head,budget_code);
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("Budget Head");
		TH.add("Budget Cost");
	
		String Heading = "\nBudget Master";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
}
