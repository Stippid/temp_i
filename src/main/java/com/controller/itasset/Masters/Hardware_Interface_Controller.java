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

import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.controller.itasset.ExportFile.ExcelUserListReportView;
import com.dao.itasset.masters.Hardware_interface_DAO;
import com.healthmarketscience.jackcess.expr.ParseException;
import com.models.itasset.master.TB_MSTR_HARDWARE_INTERFACE;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = {"admin","/" ,"user"})

public class Hardware_Interface_Controller {
	
	@Autowired 
	Hardware_interface_DAO hi_dao;
	
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	
	 @Autowired
	 RoleBaseMenuDAO roledao;
	
	@RequestMapping(value = "HARDWARE_INTERFACE_Url", method = RequestMethod.GET)
	public ModelAndView HARDWARE_INTERFACE_Url(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg)
			throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException {

			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("HARDWARE_INTERFACE_Url", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				//return new ModelAndView("redirect:bodyParameterNotAllow");
			}

		
		Mmap.put("msg", msg);
		return new ModelAndView("Hardware_Interface_Tile", "Hardware_interface_CMD", new TB_MSTR_HARDWARE_INTERFACE());
	}
	
	@RequestMapping(value = "/Hardware_interface_Action", method = RequestMethod.POST)
	public ModelAndView Hardware_interface_Action(@Valid @ModelAttribute("Connectivity_CMD") TB_MSTR_HARDWARE_INTERFACE hi,
			BindingResult result, HttpServletRequest request, ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("HARDWARE_INTERFACE_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		int id = hi.getId() > 0 ? hi.getId() : 0;
		Date date = new Date();
		String username = session.getAttribute("username").toString();
	    String hardware_interface =	request.getParameter("hardware_interface").trim();
		if (hardware_interface.equals("") || hardware_interface == null) {
			model.put("msg", "Please Enter Hardware Interface");
			return new ModelAndView("redirect:HARDWARE_INTERFACE_Url");
		}
		if (hardware_interface.length() > 50) {
			model.put("msg", "Hardware Interface Length Should Be Less Than 50 Characters");
			return new ModelAndView("redirect:HARDWARE_INTERFACE_Url");
		}

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		
		try {

			Query q0 = sessionHQL.createQuery("select count(id) from TB_MSTR_HARDWARE_INTERFACE where hardware_interface=:hardware_interface and  id !=:id");
			q0.setParameter("hardware_interface", hi.getHardware_interface().toUpperCase());

			q0.setParameter("id", id);
			Long c = (Long) q0.uniqueResult();

			if (id == 0) {
				hi.setHardware_interface(hardware_interface.toUpperCase());
				hi.setCreated_by(username);
				hi.setCreated_dt(date);
				if (c == 0) {
					sessionHQL.save(hi);
					sessionHQL.flush();
					sessionHQL.clear();
					model.put("msg", "Data Saved Successfully.");

				} else {
					model.put("msg", "Data already Exist.");
				}
			}

			tx.commit();
		} catch (RuntimeException e) {
			try {
				tx.rollback();
				model.put("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				model.put("msg", "Couldn�t roll back transaction " + rbe);
			}
			throw e;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return new ModelAndView("redirect:HARDWARE_INTERFACE_Url");
	}
	
	@RequestMapping(value = "/getHardware_Interface_mstrReportDataList", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getHardware_Interface_mstrReportDataList(int startPage, String pageLength,
			String Search, String orderColunm, String orderType, HttpSession sessionUserId,String hardware_interface)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		return hi_dao.getReportListHardwareInterface(startPage, pageLength, Search, orderColunm, orderType, sessionUserId,hardware_interface);
	}

	@RequestMapping(value = "/getHardware_Interface_mstrTotalCount", method = RequestMethod.POST)
	public @ResponseBody long getHardware_Interface_mstrTotalCount(HttpSession sessionUserId, String Search, String hardware_interface) {
		return hi_dao.getReportListHardwareInterfaceTotalCount(Search,hardware_interface);
	}
	
	@RequestMapping(value = "EditHardware_Interface_Url", method = RequestMethod.POST)
	public ModelAndView EditHardware_Interface_Url(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg, String updateid) {
		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("HARDWARE_INTERFACE_Url", roleid);
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
		q = s1.createQuery("from TB_MSTR_HARDWARE_INTERFACE where cast(id as string)=:PK");
		q.setString("PK", DcryptedPk);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		s1.close();
		Mmap.put("EditHardware_interface_CMD1", list.get(0));
		Mmap.put("msg", msg);
		return new ModelAndView("Edit_Hardware_Interface_Tile", "EditHardware_interface_CMD", new TB_MSTR_HARDWARE_INTERFACE());
	}
	
	@RequestMapping(value = "/EditHardware_interface_Action", method = RequestMethod.POST)
	public ModelAndView EditHardware_interface_Action(@ModelAttribute("EditHardware_interface_CMD") TB_MSTR_HARDWARE_INTERFACE hi,
			HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {

		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("HARDWARE_INTERFACE_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		int id = Integer.parseInt(request.getParameter("id"));
		String username = session.getAttribute("username").toString();
		String hardware_interface = request.getParameter("hardware_interface").trim();

		String enckey ="commonPwdEncKeys";
		try {
			Cipher c = hex_asciiDao.EncryptionSHA256Algo(session,enckey);

	    try {
			String EncryptedPk =new String(Base64.encodeBase64(c.doFinal(request.getParameter("id").toString().getBytes())));
			
			if(hardware_interface.equals("") || hardware_interface == null) 
		    { 
		      return EditHardware_Interface_Url(model,session,request, "Please Enter Hardware Interface",EncryptedPk);
		    } 
		    if (hardware_interface.length() > 50) {
		    	 return EditHardware_Interface_Url(model,session,request, "Hardware Interface Length Should Be Less Than 50 Characters",EncryptedPk);
		    }
			
		} catch (IllegalBlockSizeException | BadPaddingException e1) {
			e1.printStackTrace();
		}
		
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e1) {
				e1.printStackTrace();
		}


		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session1.beginTransaction();

		try {
			Query q0 = session1.createQuery(
					"select count(id) from TB_MSTR_HARDWARE_INTERFACE where hardware_interface=:hardware_interface and id !=:id");
			q0.setParameter("hardware_interface", hardware_interface.toUpperCase());
			q0.setParameter("id", id);
			Long c = (Long) q0.uniqueResult();

			if (c == 0) {
				String hql = "update TB_MSTR_HARDWARE_INTERFACE set hardware_interface=:hardware_interface,modifed_by=:modifed_by,modified_dt=:modified_dt"
						+ " where id=:id";

				Query query = session1.createQuery(hql).setString("hardware_interface", hardware_interface)
						.setString("modifed_by", username).setDate("modified_dt", new Date()).setInteger("id", id);
				msg = query.executeUpdate() > 0 ? "1" : "0";
				tx.commit();

				if (msg == "1") {
					model.put("msg", "Data Updated Successfully.");
				} else {
					model.put("msg", "Data Not Updated.");
				}
			} else {
				model.put("msg", "Data already Exist.");
			}

		} catch (RuntimeException e) {
			try {
				tx.rollback();
				model.put("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				model.put("msg", "Couldn�t roll back transaction " + rbe);
			}
			throw e;
		} finally {

		}
		return new ModelAndView("redirect:HARDWARE_INTERFACE_Url");
	}
	
	@RequestMapping(value = "/Delete_Hardware_Interface_Url", method = RequestMethod.POST)
	public ModelAndView Delete_Hardware_Interface_Url(String deleteid,
			@RequestParam(value = "msg", required = false) String msg,
			HttpServletRequest request,
			HttpSession session, ModelMap model) {
	
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("HARDWARE_INTERFACE_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		
		List<String> list = new ArrayList<String>();
		list.add(hi_dao.Delete_Hardware_Interface(deleteid, session));
		model.put("msg", list);
		return new ModelAndView("redirect:HARDWARE_INTERFACE_Url");
	}
	
	@RequestMapping(value = "/HWInterfacemastereport", method = RequestMethod.POST)
	public ModelAndView HWInterfacemastereport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1) {

	 String hardware_interface = request.getParameter("hardware_interface1");
	ArrayList<ArrayList<String>> CTlist = hi_dao.Report_DataTableMakeList(hardware_interface);
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("Hardware Interface");
	
		String Heading = "\nOH/WInterface Master";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}

}
