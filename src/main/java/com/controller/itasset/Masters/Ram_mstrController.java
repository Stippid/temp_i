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

import com.controller.validation.ValidationController;
import com.controller.itasset.ExportFile.ExcelUserListReportView;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.models.itasset.master.TB_MSTR_RAM_M;
import com.dao.itasset.masters.Ram_mstrDAO;
 
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Ram_mstrController {

	@Autowired
	private Ram_mstrDAO objDAO;
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	
	ValidationController validation = new ValidationController();
	
	 @Autowired
	 RoleBaseMenuDAO roledao;
	
	@RequestMapping(value = "Ram_mstr_Url", method = RequestMethod.GET)
	public ModelAndView Ram_mstr_Url(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg)
			throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException {
		
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Ram_mstr_Url", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				//return new ModelAndView("redirect:bodyParameterNotAllow");
			}


		Mmap.put("msg", msg);
		return new ModelAndView("Ram_mstr_tile", "ram_mstrCMD", new TB_MSTR_RAM_M());
	}

	@RequestMapping(value = "/getRam_mstrReportDataList", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getFilteredDataList_SQL(int startPage, String pageLength,
			String Search, String orderColunm, String orderType,String ram,String ram_capacity, HttpSession sessionUserId)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
			return objDAO.getReportListRam_mstr(startPage, pageLength, Search, orderColunm, orderType,ram, ram_capacity,sessionUserId);
	}

	@RequestMapping(value = "/getRam_mstrTotalCount", method = RequestMethod.POST)
	public @ResponseBody long getRam_mstrTotalCount(HttpSession sessionUserId, String Search, String ram,String ram_capacity) {
		return objDAO.getReportListRam_mstrTotalCount(Search, ram,ram_capacity);
	}

	@RequestMapping(value = "/ram_mstrAction", method = RequestMethod.POST)
	public ModelAndView ram_mstrAction(@Valid @ModelAttribute("ram_mstrCMD") TB_MSTR_RAM_M ln, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Ram_mstr_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		 String ram = request.getParameter("ram").trim();
		  String size = request.getParameter("size");
		  
		if (ram.equals("") || ram == null) {
			model.put("msg", "Please Enter RAM Capacity");
			return new ModelAndView("redirect:Ram_mstr_Url");
		}
		if(ram.length() > 10) {
			 model.put("msg", "RAM Capacity Length Should Be Less Than 10 Characters");
			 return new ModelAndView("redirect:Ram_mstr_Url");
		 }
		

	   if(size.equals("0")) {
		   model.put("msg", "Please Select Size");
			 return new ModelAndView("redirect:Ram_mstr_Url");
	   }
		String ram_size = ram + size;
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {
			Query q0 = sessionHQL.createQuery("select count(id) from TB_MSTR_RAM_M where UPPER(ram)=:ram and id !=:id");
			q0.setParameter("ram", ln.getRam().toUpperCase());
			q0.setParameter("id", ln.getId());

			Long c = (Long) q0.uniqueResult();
			if (c == 0) {
				ln.setRam(ram_size);
				sessionHQL.save(ln);
				model.put("msg", "Data Saved Successfully");
			} else {
				model.put("msg", "Data already Exist");
			}
			tx.commit();
		} catch (RuntimeException e) {
			try {
				tx.rollback();
				model.put("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				model.put("msg", "Couldn't roll back transaction " + rbe);
			}
			throw e;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}

		return new ModelAndView("redirect:Ram_mstr_Url");
	}

	@RequestMapping(value = "EditRam_mstrUrl", method = RequestMethod.POST)
	public ModelAndView EditRam_mstrUrl(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg, String updateid) {
		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Ram_mstr_Url", roleid);
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
		q = s1.createQuery("from TB_MSTR_RAM_M where cast(id as string)=:PK");
		q.setString("PK", DcryptedPk);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		s1.close();
		Mmap.put("Editram_mstrCMD1", list.get(0));
		Mmap.put("msg", msg);
		return new ModelAndView("EditRam_mstr_tile", "Editram_mstrCMD", new TB_MSTR_RAM_M());
	}

	@RequestMapping(value = "/Editram_mstrAction", method = RequestMethod.POST)
	public ModelAndView Editram_mstrAction(@Valid @ModelAttribute("Editram_mstrCMD") TB_MSTR_RAM_M ln,
			BindingResult result, HttpServletRequest request, ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg) {
		 String ram = request.getParameter("ram").trim();
		  String size = request.getParameter("size");
		  
		  String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Ram_mstr_Url", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				//return new ModelAndView("redirect:bodyParameterNotAllow");
			}
		String enckey ="commonPwdEncKeys";
		try {
			Cipher c = hex_asciiDao.EncryptionSHA256Algo(session,enckey);

        try {
			String EncryptedPk =new String(Base64.encodeBase64(c.doFinal(request.getParameter("id").toString().getBytes())));
			if (ram.equals("") || ram == null) {
				return EditRam_mstrUrl(model,session,request, "Please Enter RAM Capacity",EncryptedPk);
			}
			if(ram.length() > 10) {
				 return EditRam_mstrUrl(model,session,request, "RAM Capacity Length Should Be Less Than 10 Characters",EncryptedPk);
			 }
			
			 if( !ram.matches("^[0-9]{1,2}([.][0-9]{1,2})?$")) {
				 return EditRam_mstrUrl(model,session,request, "RAM Capacity Must Be Contain Only Decimal Number" ,EncryptedPk);
			}
		   if(size.equals("0")) {
			   return EditRam_mstrUrl(model,session,request, "Please Select Size" ,EncryptedPk);

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
		
		String ram_size = ram +" " + size;
		try {

			Query q0 = sessionHQL.createQuery("select count(id) from TB_MSTR_RAM_M where UPPER(ram)=:ram and id !=:id");
			q0.setParameter("ram", ln.getRam().toUpperCase());
			q0.setParameter("id", ln.getId());

			Long c = (Long) q0.uniqueResult();
			if (c == 0) {
				ln.setId(Integer.parseInt(request.getParameter("id")));
				ln.setRam(ram_size);
				sessionHQL.saveOrUpdate(ln);
				model.put("msg", "Data Updated Successfully");
			} else {
				model.put("msg", "Data already Exist");
			}
			tx.commit();
		} catch (RuntimeException e) {
			try {
				tx.rollback();
				model.put("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				model.put("msg", "Couldn't roll back transaction " + rbe);
			}
			throw e;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}

		return new ModelAndView("redirect:Ram_mstr_Url");
	}

	@RequestMapping(value = "/deleteram_mstrUrl", method = RequestMethod.POST)
	public ModelAndView deleteram_mstrUrl(String deleteid, HttpSession session,HttpServletRequest request, ModelMap model,@RequestParam(value = "msg", required = false) String msg) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Ram_mstr_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		
		List<String> list = new ArrayList<String>();
		list.add(objDAO.Deleteram_mstr(deleteid, session));
		model.put("msg", list);
		return new ModelAndView("redirect:Ram_mstr_Url");
	}
	
////FOR Excell report 
	@RequestMapping(value = "/RAMmastereport", method = RequestMethod.POST)
	public ModelAndView RAMmastereport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1) {

		String ram = request.getParameter("ram1").trim();
		ArrayList<ArrayList<String>> CTlist = objDAO.Report_DataTableMakeList(ram);
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("RAM Capacity");
		
		String Heading = "\nORAM Master";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
}
