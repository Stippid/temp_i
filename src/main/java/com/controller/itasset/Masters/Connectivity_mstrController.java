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
import com.dao.itasset.masters.Connectivity_DAO;
import com.healthmarketscience.jackcess.expr.ParseException;
import com.models.itasset.master.TB_MSTR_CONNECTIVITY;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Connectivity_mstrController {

	@Autowired
	Connectivity_DAO cn_dao;

	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	
	@Autowired
	 RoleBaseMenuDAO roledao;

	@RequestMapping(value = "CONNECTIVITY_Url", method = RequestMethod.GET)
	public ModelAndView CONNECTIVITY_Url(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg)
			throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException {
		
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("CONNECTIVITY_Url", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				//return new ModelAndView("redirect:bodyParameterNotAllow");
			}

		Mmap.put("msg", msg);
		return new ModelAndView("Connectivity_Tile", "Connectivity_CMD", new TB_MSTR_CONNECTIVITY());
	}

	@RequestMapping(value = "/Connectivity_Action", method = RequestMethod.POST)
	public ModelAndView Connectivity_Action(@Valid @ModelAttribute("Connectivity_CMD") TB_MSTR_CONNECTIVITY cn,
			BindingResult result, HttpServletRequest request, ModelMap model,@RequestParam(value = "msg", required = false) String msg, HttpSession session) {

		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("CONNECTIVITY_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		int id = cn.getId() > 0 ? cn.getId() : 0;
		Date date = new Date();
		String username = session.getAttribute("username").toString();
       String connectivity_type = request.getParameter("connectivity_type").trim();
		if (connectivity_type.equals("") || connectivity_type == null) {
			model.put("msg", "Please Enter Connectivity Type");
			return new ModelAndView("redirect:CONNECTIVITY_Url");
		}
		if (connectivity_type.length() > 100) {
			model.put("msg", "Connectivity Type Length Should Be Less Than 100 Characters");
			return new ModelAndView("redirect:CONNECTIVITY_Url");
		}
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {

			Query q0 = sessionHQL.createQuery(
					"select count(id) from TB_MSTR_CONNECTIVITY where connectivity_type=:connectivity_type and  id !=:id");
			q0.setParameter("connectivity_type", cn.getConnectivity_type());

			q0.setParameter("id", id);
			Long c = (Long) q0.uniqueResult();

			if (id == 0) {
				cn.setConnectivity_type(connectivity_type.toUpperCase());
				cn.setCreated_by(username);
				cn.setCreated_dt(date);
				if (c == 0) {
					sessionHQL.save(cn);
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
		return new ModelAndView("redirect:CONNECTIVITY_Url");
	}

	@RequestMapping(value = "/getConnectivity_mstrReportDataList", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getConnectivity_mstrReportDataList(int startPage, String pageLength,
			String Search, String orderColunm, String orderType, HttpSession sessionUserId, String connectivity_type)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		
	
		return cn_dao.getReportListConnectivity(startPage, pageLength, Search, orderColunm, orderType, sessionUserId,
				connectivity_type);
	}

	@RequestMapping(value = "/getConnectivity_mstrTotalCount", method = RequestMethod.POST)
	public @ResponseBody long getConnectivity_mstrTotalCount(HttpSession sessionUserId, String Search,
			String connectivity_type) {
		return cn_dao.getReportListConnectivityTotalCount(Search, connectivity_type);
	}

	@RequestMapping(value = "EditConnectivity_Url", method = RequestMethod.POST)
	public ModelAndView EditConnectivity_Url(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg, String updateid) {
		
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("CONNECTIVITY_Url", roleid);
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
		q = s1.createQuery("from TB_MSTR_CONNECTIVITY where cast(id as string)=:PK");
		q.setString("PK", DcryptedPk);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		s1.close();
		Mmap.put("EditConnectivity_CMD1", list.get(0));
		Mmap.put("msg", msg);
		return new ModelAndView("Edit_Connectivity_Tile", "EditConnectivity_CMD", new TB_MSTR_CONNECTIVITY());
	}

	@RequestMapping(value = "/EditConnectivity_Action", method = RequestMethod.POST)
	public ModelAndView EditConnectivity_Action(@ModelAttribute("EditConnectivity_CMD") TB_MSTR_CONNECTIVITY cn,
			HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {

		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("CONNECTIVITY_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		int id = Integer.parseInt(request.getParameter("id"));
		String username = session.getAttribute("username").toString();
		String connectivity_type = request.getParameter("connectivity_type").trim();
		String enckey ="commonPwdEncKeys";
		try {
			Cipher c = hex_asciiDao.EncryptionSHA256Algo(session,enckey);

	    try {
			String EncryptedPk =new String(Base64.encodeBase64(c.doFinal(request.getParameter("id").toString().getBytes())));
			
			if(connectivity_type.equals("") || connectivity_type == null) 
		    { 
		      return EditConnectivity_Url(model,session,request, "Please Enter Connectivity Type",EncryptedPk);
		    } 
		    if (connectivity_type.length() > 100) {
		    	 return EditConnectivity_Url(model,session,request, "Connectivity Type Length Should Be Less Than 100 Characters",EncryptedPk);
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
					"select count(id) from TB_MSTR_CONNECTIVITY where connectivity_type=:connectivity_type and id !=:id");
			q0.setParameter("connectivity_type", connectivity_type);
			q0.setParameter("id", id);
			Long c = (Long) q0.uniqueResult();

			if (c == 0) {
				String hql = "update TB_MSTR_CONNECTIVITY set connectivity_type=:connectivity_type,modifed_by=:modifed_by,modified_dt=:modified_dt"
						+ " where id=:id";

				Query query = session1.createQuery(hql).setString("connectivity_type", connectivity_type.toUpperCase())
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
		return new ModelAndView("redirect:CONNECTIVITY_Url");
	}

	@RequestMapping(value = "/Delete_Connectivity_Url", method = RequestMethod.POST)
	public ModelAndView Delete_Connectivity_Url(String deleteid, HttpSession session,HttpServletRequest request, ModelMap model,@RequestParam(value = "msg", required = false) String msg) {
		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("CONNECTIVITY_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		List<String> list = new ArrayList<String>();
		list.add(cn_dao.Delete_Connectivity(deleteid, session));
		model.put("msg", list);
		return new ModelAndView("redirect:CONNECTIVITY_Url");
	}
	
	@RequestMapping(value = "/Connectivitymastereport", method = RequestMethod.POST)
	public ModelAndView Connectivitymastereport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1) {

	 String connectivity_type = request.getParameter("connectivity_type1");
	ArrayList<ArrayList<String>> CTlist = cn_dao.Report_DataTableMakeList(connectivity_type);
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("Connectivity Type");
	
		String Heading = "\nOConnectivity Master";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
}
