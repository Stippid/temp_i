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
import com.dao.itasset.masters.Management_Layer_DAO;
import com.healthmarketscience.jackcess.expr.ParseException;
import com.models.itasset.master.TB_MSTR_MANAGEMENT_LAYER;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Management_Layer_Controller {

	@Autowired
	Management_Layer_DAO ml_dao;

	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	
	@Autowired
	 RoleBaseMenuDAO roledao;

	@RequestMapping(value = "MANAGEMENT_LAYER_Url", method = RequestMethod.GET)
	public ModelAndView MANAGEMENT_LAYER_Url(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg)
			throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException {

			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("MANAGEMENT_LAYER_Url", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				//return new ModelAndView("redirect:bodyParameterNotAllow");
			}

		
		Mmap.put("msg", msg);
		return new ModelAndView("Management_Layer_Tile", "Management_Layer_CMD", new TB_MSTR_MANAGEMENT_LAYER());
	}

	@RequestMapping(value = "/Management_Layer_Action", method = RequestMethod.POST)
	public ModelAndView Management_Layer_Action(
			@Valid @ModelAttribute("Management_Layer_CMD") TB_MSTR_MANAGEMENT_LAYER ml, BindingResult result,@RequestParam(value = "msg", required = false) String msg,
			HttpServletRequest request, ModelMap model, HttpSession session) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("MANAGEMENT_LAYER_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}

	
		int id = ml.getId() > 0 ? ml.getId() : 0;
		Date date = new Date();
		String username = session.getAttribute("username").toString();
		String management_layer = request.getParameter("management_layer").trim();
		if (management_layer.equals("") || management_layer == null) {
			model.put("msg", "Please Enter Management Layer");
			return new ModelAndView("redirect:MANAGEMENT_LAYER_Url");
		}
		if (management_layer.length() > 50) {
			model.put("msg", "Management Layer Length Should Be Less Than 50 Characters");
			return new ModelAndView("redirect:MANAGEMENT_LAYER_Url");
		}
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {

			Query q0 = sessionHQL.createQuery(
					"select count(id) from TB_MSTR_MANAGEMENT_LAYER where management_layer=:management_layer and  id !=:id");
			q0.setParameter("management_layer", ml.getManagement_layer());

			q0.setParameter("id", id);
			Long c = (Long) q0.uniqueResult();

			if (id == 0) {
				ml.setManagement_layer(management_layer.toUpperCase());
				ml.setCreated_by(username);
				ml.setCreated_dt(date);
				if (c == 0) {
					sessionHQL.save(ml);
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
		return new ModelAndView("redirect:MANAGEMENT_LAYER_Url");
	}

	@RequestMapping(value = "/getManagement_Layer_mstrReportDataList", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getManagement_Layer_mstrReportDataList(int startPage,
			String pageLength, String Search, String orderColunm, String orderType, HttpSession sessionUserId,
			String management_layer)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		return ml_dao.getReportListManagement_Layer(startPage, pageLength, Search, orderColunm, orderType,
				sessionUserId, management_layer);
	}

	@RequestMapping(value = "/getManagement_Layer_mstrTotalCount", method = RequestMethod.POST)
	public @ResponseBody long getManagement_Layer_mstrTotalCount(HttpSession sessionUserId, String Search,
			String management_layer) {
		return ml_dao.getReportListManagement_LayerTotalCount(Search, management_layer);
	}

	@RequestMapping(value = "EditManagement_Layer_Url", method = RequestMethod.POST)
	public ModelAndView EditManagement_Layer_Url(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg, String updateid) {
		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("MANAGEMENT_LAYER_Url", roleid);
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
		q = s1.createQuery("from TB_MSTR_MANAGEMENT_LAYER where cast(id as string)=:PK");
		q.setString("PK", DcryptedPk);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		s1.close();
		Mmap.put("EditManagement_Layer_CMD1", list.get(0));
		Mmap.put("msg", msg);
		return new ModelAndView("Edit_Management_Layer_Tile", "EditManagement_Layer_CMD",
				new TB_MSTR_MANAGEMENT_LAYER());
	}

	@RequestMapping(value = "/EditManagement_Layer_Action", method = RequestMethod.POST)
	public ModelAndView EditManagement_Layer_Action(
			@ModelAttribute("EditManagement_Layer_CMD") TB_MSTR_MANAGEMENT_LAYER ml, HttpServletRequest request,
			ModelMap model, HttpSession session, @RequestParam(value = "msg", required = false) String msg)
			throws ParseException {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("MANAGEMENT_LAYER_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}

	
		int id = Integer.parseInt(request.getParameter("id"));
		String username = session.getAttribute("username").toString();
		String management_layer = request.getParameter("management_layer").trim();

		String enckey ="commonPwdEncKeys";
		try {
			Cipher c = hex_asciiDao.EncryptionSHA256Algo(session,enckey);

	    try {
			String EncryptedPk =new String(Base64.encodeBase64(c.doFinal(request.getParameter("id").toString().getBytes())));
			
			if(management_layer.equals("") || management_layer == null) 
		    { 
		      return EditManagement_Layer_Url(model,session,request, "Please Enter Management Layer",EncryptedPk);
		    } 
		    if (management_layer.length() > 50) {
		    	 return EditManagement_Layer_Url(model,session,request, "Management Layer Length Should Be Less Than 50 Characters",EncryptedPk);
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
					"select count(id) from TB_MSTR_MANAGEMENT_LAYER where management_layer=:management_layer and id !=:id");
			q0.setParameter("management_layer", management_layer.toUpperCase());
			q0.setParameter("id", id);
			Long c = (Long) q0.uniqueResult();

			if (c == 0) {
				String hql = "update TB_MSTR_MANAGEMENT_LAYER set management_layer=:management_layer,modifed_by=:modifed_by,modified_dt=:modified_dt"
						+ " where id=:id";

				Query query = session1.createQuery(hql).setString("management_layer", management_layer.toUpperCase())
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
		return new ModelAndView("redirect:MANAGEMENT_LAYER_Url");
	}

	@RequestMapping(value = "/Delete_Management_Layer_Url", method = RequestMethod.POST)
	public ModelAndView Delete_Management_Layer_Url(String deleteid,
			HttpServletRequest request,
			HttpSession session, ModelMap model,@RequestParam(value = "msg", required = false) String msg) {
		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("MANAGEMENT_LAYER_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}

	
		
		List<String> list = new ArrayList<String>();
		list.add(ml_dao.Delete_Management_Layer(deleteid, session));
		model.put("msg", list);
		return new ModelAndView("redirect:MANAGEMENT_LAYER_Url");
	}
	
	@RequestMapping(value = "/MGMTmastereport", method = RequestMethod.POST)
	public ModelAndView MGMTmastereport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1) {

	 String management_layer = request.getParameter("management_layer1");
	ArrayList<ArrayList<String>> CTlist = ml_dao.Report_DataTableMakeList(management_layer);
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("Management Layer");
	
		String Heading = "\nOManagementLayer Master";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}

}
