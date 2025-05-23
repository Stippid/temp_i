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

import com.controller.itasset.ExportFile.ExcelUserListReportView;
import com.controller.validation.ValidationController;
import com.dao.itasset.masters.Ethernet_Port_DAO;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.healthmarketscience.jackcess.expr.ParseException;
import com.models.itasset.master.TB_MSTR_ETHERNET_PORT;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Ethernet_Port_Controller {

	@Autowired
	Ethernet_Port_DAO ep_dao;

	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	ValidationController validation=new ValidationController();
	@Autowired
	 RoleBaseMenuDAO roledao;

	@RequestMapping(value = "ETHERNET_PORT_Url", method = RequestMethod.GET)
	public ModelAndView ETHERNET_PORT_Url(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg)
			throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException {
		
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("ETHERNET_PORT_Url", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				//return new ModelAndView("redirect:bodyParameterNotAllow");
			}


		Mmap.put("msg", msg);
		return new ModelAndView("Ethernet_Port_Tile", "Ethernet_Port_CMD", new TB_MSTR_ETHERNET_PORT());
	}

	@RequestMapping(value = "/Ethernet_Port_Action", method = RequestMethod.POST)
	public ModelAndView Ethernet_Port_Action(@Valid @ModelAttribute("Ethernet_Port_CMD") TB_MSTR_ETHERNET_PORT ep,
			BindingResult result, HttpServletRequest request, ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("ETHERNET_PORT_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		int id = ep.getId() > 0 ? ep.getId() : 0;
		Date date = new Date();
		String username = session.getAttribute("username").toString();
		String ethernet_port = request.getParameter("ethernet_port").trim();
		if (ethernet_port.equals("") || ethernet_port == null) {
			model.put("msg", "Please Enter Ethernet Port");
			return new ModelAndView("redirect:ETHERNET_PORT_Url");
		}
		if (ethernet_port.length() > 50) {
			model.put("msg", "Ethernet Port Length Should Be Less Than 50 Characters");
			return new ModelAndView("redirect:ETHERNET_PORT_Url");
		}
		if(!validation.isOnlyAlphabetNumericSpaceNot(ethernet_port)) {
			model.put("msg", validation.isOnlyAlphabetNumericSpaceNotMSG +"in Ethernet port");
			return new ModelAndView("redirect:ETHERNET_PORT_Url");
		}
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {

			Query q0 = sessionHQL.createQuery(
					"select count(id) from TB_MSTR_ETHERNET_PORT where ethernet_port=:ethernet_port and  id !=:id");
			q0.setParameter("ethernet_port", ep.getEthernet_port());

			q0.setParameter("id", id);
			Long c = (Long) q0.uniqueResult();

			if (id == 0) {
				ep.setEthernet_port(ethernet_port.toUpperCase());
				ep.setCreated_by(username);
				ep.setCreated_dt(date);
				if (c == 0) {
					sessionHQL.save(ep);
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
		return new ModelAndView("redirect:ETHERNET_PORT_Url");
	}

	@RequestMapping(value = "/getEthernet_Port_mstrReportDataList", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getEthernet_Port_mstrReportDataList(int startPage, String pageLength,
			String Search, String orderColunm, String orderType, HttpSession sessionUserId, String ethernet_port)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		return ep_dao.getReportListEthernet_port(startPage, pageLength, Search, orderColunm, orderType, sessionUserId,
				ethernet_port);
	}

	@RequestMapping(value = "/getEthernet_Port_mstrTotalCount", method = RequestMethod.POST)
	public @ResponseBody long getEthernet_Port_mstrTotalCount(HttpSession sessionUserId, String Search,
			String ethernet_port) {
		return ep_dao.getReportListEthernet_portTotalCount(Search, ethernet_port);
	}

	@RequestMapping(value = "EditEthernet_Port_Url", method = RequestMethod.POST)
	public ModelAndView EditEthernet_Port_Url(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg, String updateid) {
		
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("ETHERNET_PORT_Url", roleid);
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
		q = s1.createQuery("from TB_MSTR_ETHERNET_PORT where cast(id as string)=:PK");
		q.setString("PK", DcryptedPk);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		s1.close();
		Mmap.put("Editethernet_port_CMD1", list.get(0));
		Mmap.put("msg", msg);
		return new ModelAndView("Edit_Ethernet_Port_Tile", "Editethernet_port_CMD", new TB_MSTR_ETHERNET_PORT());
	}

	@RequestMapping(value = "/Editethernet_port_Action", method = RequestMethod.POST)
	public ModelAndView Editethernet_port_Action(@ModelAttribute("Editethernet_port_CMD") TB_MSTR_ETHERNET_PORT ep,
			HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("ETHERNET_PORT_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		int id = Integer.parseInt(request.getParameter("id"));
		String username = session.getAttribute("username").toString();
		String ethernet_port = request.getParameter("ethernet_port").trim();
		String enckey ="commonPwdEncKeys";
		try {
			Cipher c = hex_asciiDao.EncryptionSHA256Algo(session,enckey);

	    try {
			String EncryptedPk =new String(Base64.encodeBase64(c.doFinal(request.getParameter("id").toString().getBytes())));
			
			if(ethernet_port.equals("") || ethernet_port == null) 
		    { 
		      return EditEthernet_Port_Url(model,session,request, "Please Enter Ethernet Port",EncryptedPk);
		    } 
		    if (ethernet_port.length() > 50) {
		    	 return EditEthernet_Port_Url(model,session,request, "Ethernet Port Length Should Be Less Than 50 Characters",EncryptedPk);
		    }
		    if(!validation.isOnlyAlphabetNumericSpaceNot(ethernet_port)) {
				model.put("msg", validation.isOnlyAlphabetNumericSpaceNotMSG +"in Ethernet port");
			      return EditEthernet_Port_Url(model,session,request, validation.isOnlyAlphabetNumericSpaceNotMSG +"in Ethernet port",EncryptedPk);
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
					"select count(id) from TB_MSTR_ETHERNET_PORT where ethernet_port=:ethernet_port and id !=:id");
			q0.setParameter("ethernet_port", ethernet_port);
			q0.setParameter("id", id);
			Long c = (Long) q0.uniqueResult();

			if (c == 0) {
				String hql = "update TB_MSTR_ETHERNET_PORT set ethernet_port=:ethernet_port,modifed_by=:modifed_by,modified_dt=:modified_dt"
						+ " where id=:id";

				Query query = session1.createQuery(hql).setString("ethernet_port", ethernet_port.toUpperCase())
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
		return new ModelAndView("redirect:ETHERNET_PORT_Url");
	}

	@RequestMapping(value = "/Delete_Ethernet_Port_Url", method = RequestMethod.POST)
	public ModelAndView Delete_Ethernet_Port_Url(String deleteid, HttpSession session, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("ETHERNET_PORT_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		List<String> list = new ArrayList<String>();
		list.add(ep_dao.Delete_Ethernet_port(deleteid, session));
		model.put("msg", list);
		return new ModelAndView("redirect:ETHERNET_PORT_Url");
	}
	
	@RequestMapping(value = "/Ethernetmastereport", method = RequestMethod.POST)
	public ModelAndView HWInterfacemastereport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1) {

	 String ethernet_port = request.getParameter("ethernet_port1");
	ArrayList<ArrayList<String>> CTlist = ep_dao.Report_DataTableMakeList(ethernet_port);
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("Ethernet Port");
	
		String Heading = "\nOEthernetPort Master";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
}
