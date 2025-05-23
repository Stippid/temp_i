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
import com.dao.itasset.masters.Network_Features_DAO;
import com.healthmarketscience.jackcess.expr.ParseException;
import com.models.itasset.master.TB_MSTR_NETWORK_FEATURES;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Network_Features_Controller {
	
	@Autowired
	Network_Features_DAO nf_dao;

	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	
	@Autowired
	 RoleBaseMenuDAO roledao;

	@RequestMapping(value = "NETWORK_FEATURES_Url", method = RequestMethod.GET)
	public ModelAndView NETWORK_FEATURES_Url(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg)
			throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException {

			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("NETWORK_FEATURES_Url", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				//return new ModelAndView("redirect:bodyParameterNotAllow");
			}

		
		Mmap.put("msg", msg);
		return new ModelAndView("Network_Features_Tile", "Network_Features_CMD", new TB_MSTR_NETWORK_FEATURES());
	}
	
	@RequestMapping(value = "/Network_Features_Action", method = RequestMethod.POST)
	public ModelAndView Network_Features_Action(@Valid @ModelAttribute("Network_Features_CMD") TB_MSTR_NETWORK_FEATURES nf,
			BindingResult result, HttpServletRequest request, ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("NETWORK_FEATURES_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		int id = nf.getId() > 0 ? nf.getId() : 0;
		Date date = new Date();
		String username = session.getAttribute("username").toString();
		String network_features = request.getParameter("network_features").trim();
		if (network_features.equals("") || network_features == null) {
			model.put("msg", "Please Enter Network Security Features");
			return new ModelAndView("redirect:NETWORK_FEATURES_Url");
		}
		if (network_features.length() > 100) {
			model.put("msg", "Network Security Features Length Should Be Less Than 100 Characters");
			return new ModelAndView("redirect:NETWORK_FEATURES_Url");
		}
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {

			Query q0 = sessionHQL.createQuery(
					"select count(id) from TB_MSTR_NETWORK_FEATURES where upper(network_features)=:network_features and  id !=:id");
			q0.setParameter("network_features", nf.getNetwork_features().toUpperCase());
			q0.setParameter("id", id);
			Long c = (Long) q0.uniqueResult();

			if (id == 0) {
				nf.setNetwork_features(network_features.toUpperCase());
				nf.setCreated_by(username);
				nf.setCreated_dt(date);
				if (c == 0) {
					sessionHQL.save(nf);
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
		return new ModelAndView("redirect:NETWORK_FEATURES_Url");
	}
	
	@RequestMapping(value = "/getNetwork_Features_mstrReportDataList", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getNetwork_Features_mstrReportDataList(int startPage, String pageLength,
			String Search, String orderColunm, String orderType, HttpSession sessionUserId, String network_feature)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		
		return nf_dao.getReportListNetwork_Features(startPage, pageLength, Search, orderColunm, orderType, sessionUserId,
				network_feature);
	}

	@RequestMapping(value = "/getNetwork_Features_mstrTotalCount", method = RequestMethod.POST)
	public @ResponseBody long getNetwork_Features_mstrTotalCount(HttpSession sessionUserId, String Search,
			String network_feature) {
		return nf_dao.getReportListNetwork_FeaturesTotalCount(Search, network_feature);
	}
	
	@RequestMapping(value = "EditNetwork_Features_Url", method = RequestMethod.POST)
	public ModelAndView EditNetwork_Features_Url(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg, String updateid) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("NETWORK_FEATURES_Url", roleid);
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
		q = s1.createQuery("from TB_MSTR_NETWORK_FEATURES where cast(id as string)=:PK");
		q.setString("PK", DcryptedPk);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		s1.close();
		Mmap.put("EditNetwork_Features_CMD1", list.get(0));
		Mmap.put("msg", msg);
		return new ModelAndView("Edit_Network_Features_Tile", "EditNetwork_Features_CMD", new TB_MSTR_NETWORK_FEATURES());
	}

	@RequestMapping(value = "/EditNetwork_Features_Action", method = RequestMethod.POST)
	public ModelAndView EditNetwork_Features_Action(@ModelAttribute("EditNetwork_Features_CMD") TB_MSTR_NETWORK_FEATURES nf,
			HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {
		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("NETWORK_FEATURES_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		int id = Integer.parseInt(request.getParameter("id"));
		String username = session.getAttribute("username").toString();
		String network_features = request.getParameter("network_features").trim();

		
		String enckey ="commonPwdEncKeys";
		try {
			Cipher c = hex_asciiDao.EncryptionSHA256Algo(session,enckey);

	    try {
			String EncryptedPk =new String(Base64.encodeBase64(c.doFinal(request.getParameter("id").toString().getBytes())));
			
			if(network_features.equals("") || network_features == null) 
		    { 
		      return EditNetwork_Features_Url(model,session,request, "Please Enter Network Security Features",EncryptedPk);
		    } 
		    if (network_features.length() > 100) {
		    	 return EditNetwork_Features_Url(model,session,request, "Network Security Featurese Length Should Be Less Than 100 Characters",EncryptedPk);
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
					"select count(id) from TB_MSTR_NETWORK_FEATURES where network_features=:network_features and id !=:id");
			q0.setParameter("network_features", network_features);
			q0.setParameter("id", id);
			Long c = (Long) q0.uniqueResult();

			if (c == 0) {
				String hql = "update TB_MSTR_NETWORK_FEATURES set network_features=:network_features,modified_by=:modified_by,modified_dt=:modified_dt"
						+ " where id=:id";

				Query query = session1.createQuery(hql).setString("network_features", network_features.toUpperCase())
						.setString("modified_by", username).setDate("modified_dt", new Date()).setInteger("id", id);
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
		return new ModelAndView("redirect:NETWORK_FEATURES_Url");
	}
	
	@RequestMapping(value = "/Delete_Network_Features_Url", method = RequestMethod.POST)
	public ModelAndView Delete_Network_Features_Url(String deleteid, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			ModelMap model,HttpServletRequest request) {
		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("NETWORK_FEATURES_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		List<String> list = new ArrayList<String>();
		list.add(nf_dao.Delete_Network_Features(deleteid, session));
		model.put("msg", list);
		return new ModelAndView("redirect:NETWORK_FEATURES_Url");
	}
	
	@RequestMapping(value = "/Networkmastereport", method = RequestMethod.POST)
	public ModelAndView Networkmastereport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1) {

	 String network_features = request.getParameter("network_features1");
	ArrayList<ArrayList<String>> CTlist = nf_dao.Report_DataTableMakeList(network_features);
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("Network Features");
	
		String Heading = "\nONetwork_features Master";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}

}
