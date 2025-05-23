package com.controller.itasset.Masters;


import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;
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

import org.apache.commons.codec.binary.Base64;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
import com.dao.itasset.masters.Make_Dao;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.models.itasset.master.TB_MASTER_MAKE;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Make_Controller {

	It_CommonController it_comm = new It_CommonController();
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();

	@Autowired
	private Make_Dao make;

	
	@Autowired
	 RoleBaseMenuDAO roledao;

	@RequestMapping(value = "/make_masterUrl", method = RequestMethod.GET)
	public ModelAndView make_masterUrl(ModelMap model, @RequestParam(value = "msg", required = false) String msg,
			HttpSession session, HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("make_masterUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		model.put("msg", msg);
		return new ModelAndView("make_masterTiles");
	}

	@RequestMapping(value = "/make_master_Action", method = RequestMethod.POST)
	public ModelAndView make_master_Action(@ModelAttribute("make_master_CMD") TB_MASTER_MAKE Mk,
			HttpServletRequest request, ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg) {

		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("make_masterUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		String make_name = request.getParameter("make_name").trim();
	
		
		 if(Mk.getCategory() == 0) 
		 { 
		   model.put("msg", "Please Select Category"); 
		   return new ModelAndView("redirect:make_masterUrl"); 
		 } 
		 if(Mk.getAssets_name() == 0) 
		 { 
		   model.put("msg", "Please Select Assets Name"); 
		   return new ModelAndView("redirect:make_masterUrl"); 
		 } 
		 if (make_name == null || make_name.equals("")) {
				model.put("msg", "Please Enter Make Name");
				return new ModelAndView("redirect:make_masterUrl");
			}
		 if (make_name.length() > 100) {
				model.put("msg", "Make Name Length Should Be Less Than 100 Characters");
				return new ModelAndView("redirect:make_masterUrl");
			}
		int id = Mk.getId() > 0 ? Mk.getId() : 0;
		Date date = new Date();
		String username = session.getAttribute("username").toString();

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {

			Query q0 = sessionHQL.createQuery("select count(id) from TB_MASTER_MAKE where make_name=:make_name and category=:category and assets_name=:assets_name and id !=:id");
			q0.setParameter("make_name", make_name.toUpperCase());
			q0.setParameter("category", Integer.parseInt(request.getParameter("category")));
			q0.setParameter("assets_name", Integer.parseInt(request.getParameter("assets_name")));
			q0.setParameter("id", id);
			Long c = (Long) q0.uniqueResult();

			if (id == 0) {
				
				Mk.setMake_name(make_name.toUpperCase());
				Mk.setCreated_by(username);
				Mk.setCreated_dt(date);
				
				if (c == 0) {
					sessionHQL.save(Mk);
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
		return new ModelAndView("redirect:make_masterUrl");
	}

	@RequestMapping(value = "/getMaxToMake_no", method = RequestMethod.POST)
	public @ResponseBody List<Integer> getMaxToMake_no(HttpSession sessionUserId) {


		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select max(make_no) from TB_MASTER_MAKE ");
		@SuppressWarnings("unchecked")
		List<Integer> list = (List<Integer>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	@RequestMapping(value = "/dataTablePagination_Make", method = RequestMethod.GET)
	public ModelAndView dataTablePagination_Make(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {
		return new ModelAndView("dataTablePaginationSQLTile");
	}

	@RequestMapping(value = "/getFilteredMake", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getFilteredMake(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String category, String assets_name, String make_name, HttpSession sessionUserId) {
		return make.DataTableMakeList(startPage, pageLength, Search, orderColunm, orderType, category, assets_name,make_name);
	}

	@RequestMapping(value = "/getTotalMakeCount", method = RequestMethod.POST)
	public @ResponseBody long getTotalMakeCount(HttpSession sessionUserId, String Search, String category, String assets_name,
			String make_name) {
		return make.DataTableMakeTotalCount(Search, category, assets_name,make_name);
	}

	@RequestMapping(value = "/Edit_Make_Url", method = RequestMethod.POST)
	public ModelAndView Edit_Make_Url(@ModelAttribute("id1") int id, ModelMap Mmap, String updateid,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication,HttpSession session) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("make_masterUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		Mmap.put("Edit_make_master_CMD", make.getMakeByid(id));
		Mmap.put("msg", msg);

		return new ModelAndView("Edit_Make_Tiles");
	}

	@RequestMapping(value = "/Edit_make_master_Action", method = RequestMethod.POST)
	public ModelAndView Edit_make_master_Action(@ModelAttribute("Edit_make_master_CMD") TB_MASTER_MAKE obj,
			HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) throws ParseException {
		
		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("make_masterUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		String assets_name = request.getParameter("assets_name");
		String category = request.getParameter("category");
	
		String make_name = request.getParameter("make_name").trim();
		
		 String enckey ="commonPwdEncKeys";
			try {
				Cipher c = hex_asciiDao.EncryptionSHA256Algo(session,enckey);

		    try {
				String EncryptedPk =new String(Base64.encodeBase64(c.doFinal(request.getParameter("id").toString().getBytes())));
				 if(obj.getCategory() == 0) 
				 { 
					 return Edit_Make_Url(Integer.parseInt(request.getParameter("id")),model,EncryptedPk,request, "Please Select Category",authentication, session);
				
				 } 
			    if(obj.getAssets_name() == 0) 
			    { 
			    	 return Edit_Make_Url(Integer.parseInt(request.getParameter("id")),model,EncryptedPk,request,"Please Select Assets Name",authentication, session);
			     
			    } 
			     if(make_name.equals("") ||make_name == null) 
			    { 
			    	 return Edit_Make_Url(Integer.parseInt(request.getParameter("id")),model,EncryptedPk,request,"Please Enter Model Type",authentication, session);
				}
				if(make_name.length() > 100) {
					return Edit_Make_Url(Integer.parseInt(request.getParameter("id")),model,EncryptedPk,request,"Model Type Length Should Be Less Than 100 Characters",authentication, session);
		
				}
			} catch (IllegalBlockSizeException | BadPaddingException e1) {
				e1.printStackTrace();
			}
			
			} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
					| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e1) {
					e1.printStackTrace();
			}
		
		int id = obj.getId() > 0 ? obj.getId() : 0;
		String username = session.getAttribute("username").toString();

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {

			Query q0 = sessionHQL.createQuery("select count(id) from TB_MASTER_MAKE where make_name=:make_name and assets_name=:assets_name and category=:category and id !=:id");
			q0.setParameter("make_name", make_name.toUpperCase());
			q0.setParameter("assets_name", Integer.parseInt(assets_name));
			q0.setParameter("category", Integer.parseInt(category));
			q0.setParameter("id", id);
			Long c = (Long) q0.uniqueResult();

			if (c == 0) {
				String hql = "update TB_MASTER_MAKE set make_name=:make_name,modifed_by=:modifed_by,modified_dt=:modified_dt,assets_name=:assets_name,category=:category,status=:status"
						+ " where id=:id";

				Query query = sessionHQL.createQuery(hql).setString("make_name", make_name.toUpperCase())
						.setInteger("assets_name", Integer.parseInt(assets_name)).setInteger("category", Integer.parseInt(category))
						.setString("modifed_by", username).setDate("modified_dt", new Date()).setInteger("id", id).setInteger("status", 1);
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
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return new ModelAndView("redirect:make_masterUrl");
	}

	@RequestMapping(value = "/Delete_Make", method = RequestMethod.POST)
	public ModelAndView Delete_Make(@ModelAttribute("id2") int id, BindingResult result, HttpServletRequest request,
			ModelMap model, HttpSession session1,@RequestParam(value = "msg", required = false) String msg) {
		
		String roleid = session1.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("make_masterUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		
		List<String> liststr = new ArrayList<String>();
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			String hqlUpdate = "";
			int app;
			hqlUpdate = "delete from TB_MASTER_MAKE where id=:id";
			app = session.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();
			tx.commit();
			session.close();
			if (app > 0) {
				liststr.add("Data Deleted Successfully");
			} else {
				liststr.add("Data not Deleted");
			}
			model.put("msg", liststr.get(0));
		} catch (Exception e) {
			liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED");
			model.put("msg", liststr.get(0));
		}
		return new ModelAndView("redirect:make_masterUrl");
	}

	@RequestMapping(value = "/Makemastereport", method = RequestMethod.POST)
	public ModelAndView Makemastereport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1) {

		String category = request.getParameter("category1");
		String assets_name = request.getParameter("assets_name1");
		String make_name = request.getParameter("make_name1");

	 
		ArrayList<ArrayList<String>> CTlist = make.Report_DataTableMakeList(assets_name,category,make_name);
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("Category");
		TH.add("Assets Name");
		TH.add("Make/Brand Name");
		
		String Heading = "\nOModel Master";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
}
