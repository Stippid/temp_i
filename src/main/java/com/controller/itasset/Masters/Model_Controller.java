package com.controller.itasset.Masters;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.dao.itasset.masters.ModelDAO;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.models.itasset.master.TB_MASTER_MODEL;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Model_Controller {

	It_CommonController it_comm = new It_CommonController();
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();

	@Autowired
	private ModelDAO modelDAO;

	@Autowired
	 RoleBaseMenuDAO roledao;

	@RequestMapping(value = "/model_masterUrl", method = RequestMethod.GET)
	public ModelAndView model_masterUrl(ModelMap model, @RequestParam(value = "msg", required = false) String msg,
			HttpSession session, HttpServletRequest request) {

			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("model_masterUrl", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				//return new ModelAndView("redirect:bodyParameterNotAllow");
			}

		

		model.put("ListMakeName", it_comm.getMakeName());
		model.put("msg", msg);
		return new ModelAndView("model_masterTiles","Model_master_CMD",new TB_MASTER_MODEL());
	}

	@RequestMapping(value = "/Model_master_Action", method = RequestMethod.POST)
	public ModelAndView Model_master_Action(@ModelAttribute("Model_master_CMD") TB_MASTER_MODEL Md,
			HttpServletRequest request, ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg) {

		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("model_masterUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		String Model_name = request.getParameter("model_name").trim();
	
		 if(Md.getCategory() == 0) 
		 { 
		   model.put("msg", "Please Select Category"); 
		   return new ModelAndView("redirect:model_masterUrl"); 
		 } 
		 if(Md.getAssets_name() == 0) 
		 { 
		   model.put("msg", "Please Select Assets Name"); 
		   return new ModelAndView("redirect:model_masterUrl"); 
		 } 
		 if (Md.getMake_name() == 0) {
				model.put("msg", "Please Select Make Name");
				return new ModelAndView("redirect:model_masterUrl");
			}
		
		 if (Model_name == null || Model_name.equals("")) {
				model.put("msg", "Please Enter Model Name");
				return new ModelAndView("redirect:model_masterUrl");
			}
		 if (Model_name.length() > 100) {
				model.put("msg", "Model Name Length Should Be Less Than 100 Characters");
				return new ModelAndView("redirect:model_masterUrl");
			}
		int id = Md.getId() > 0 ? Md.getId() : 0;
		Date date = new Date();
		String username = session.getAttribute("username").toString();

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {

			Query q0 = sessionHQL.createQuery(
					"select count(id) from TB_MASTER_MODEL where  model_name=:model_name and category=:category and assets_name=:assets_name and make_name=:make_name and  id !=:id");

			q0.setParameter("model_name", request.getParameter("model_name"));
			q0.setParameter("category", Integer.parseInt(request.getParameter("category")));
			q0.setParameter("assets_name", Integer.parseInt(request.getParameter("assets_name")));
			q0.setParameter("make_name", Integer.parseInt(request.getParameter("make_name")));
			q0.setParameter("id", id);
			Long c = (Long) q0.uniqueResult();

			if (id == 0) {
				Md.setModel_name(Model_name.toUpperCase());
				Md.setCreated_by(username);
				Md.setCreated_dt(date);
				if (c == 0) {

					sessionHQL.save(Md);
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
				model.put("msg", "Couldn't roll back transaction " + rbe);
			}
			throw e;
		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return new ModelAndView("redirect:model_masterUrl");
	}

	@RequestMapping(value = "/getMaxToModel_no", method = RequestMethod.POST)
	public @ResponseBody List<Integer> getMaxToModel_no(HttpSession sessionUserId) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select max(model_no) from TB_MASTER_MODEL ");
		@SuppressWarnings("unchecked")
		List<Integer> list = (List<Integer>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	@RequestMapping(value = "/getFilteredModel", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getFilteredModel(int startPage, int pageLength,
			String Search, String orderColunm, String orderType, String category, String assets_name, String make_name,String model_name,
			HttpSession sessionUserId) {
		return modelDAO.DataTableModelList(startPage, pageLength, Search, orderColunm, orderType, category, assets_name,
				make_name,model_name);
	}

	@RequestMapping(value = "/getTotalModelCount", method = RequestMethod.POST)
	public @ResponseBody long getTotalModelCount(HttpSession sessionUserId, String Search, String category, String assets_name, String make_name,String model_name) {
		return modelDAO.DataTableModelTotalCount(Search,category, assets_name,
				make_name,model_name);
	}

	@RequestMapping(value = "/Edit_Model_Url", method = RequestMethod.POST)
	public ModelAndView Edit_Model_Url(@ModelAttribute("id1") int id, ModelMap Mmap, String updateid,HttpServletRequest request,HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("model_masterUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}


		Mmap.put("Edit_Model_master_CMD", modelDAO.getModelByid(id));
		Mmap.put("ListMakeName", it_comm.getMakeName());
		Mmap.put("msg", msg);

		return new ModelAndView("Edit_Model_Tiles");
	}

	@RequestMapping(value = "/Edit_Model_master_Action", method = RequestMethod.POST)
	public ModelAndView Edit_Model_master_Action(@ModelAttribute("Edit_Model_master_CMD") TB_MASTER_MODEL obj,
			HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("model_masterUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		int id = Integer.parseInt(request.getParameter("id"));
		String model_name = request.getParameter("model_name").trim();
		String assets_name = request.getParameter("assets_name");
		String category = request.getParameter("category");
		String make_name = request.getParameter("make_name");
	
		String Model_name = request.getParameter("model_name").trim();
		
		 if(obj.getCategory() == 0) 
		 { 
			 TB_MASTER_MODEL authDetails = modelDAO.getModelByid(id);
	        	model.put("Edit_Model_Tiles", authDetails);
	        	model.put("ListMakeName", it_comm.getMakeName());
		   model.put("msg", "Please Select Category"); 
		   return new ModelAndView("Edit_Model_Tiles"); 
		 } 
		 if(obj.getAssets_name() == 0) 
		 { 
			 TB_MASTER_MODEL authDetails = modelDAO.getModelByid(id);
	        	model.put("Edit_Model_Tiles", authDetails);
	        	model.put("ListMakeName", it_comm.getMakeName());
		   model.put("msg", "Please Select Assets Name"); 
		   return new ModelAndView("Edit_Model_Tiles"); 
		 } 
		 if (obj.getMake_name() == 0) {
			 TB_MASTER_MODEL authDetails = modelDAO.getModelByid(id);
	        	model.put("Edit_Model_Tiles", authDetails);
	        	model.put("ListMakeName", it_comm.getMakeName());
				model.put("msg", "Please Select Make Name");
				return new ModelAndView("Edit_Model_Tiles");
			}
		
		 if (Model_name == null || Model_name.equals("")) {
			 TB_MASTER_MODEL authDetails = modelDAO.getModelByid(id);
	        	model.put("Edit_Model_Tiles", authDetails);
	        	model.put("ListMakeName", it_comm.getMakeName());
				model.put("msg", "Please Enter Model Name");
				return new ModelAndView("Edit_Model_Tiles");
			}
		 if (Model_name.length() > 100) {
			 TB_MASTER_MODEL authDetails = modelDAO.getModelByid(id);
	        	model.put("Edit_Model_Tiles", authDetails);
	        	model.put("ListMakeName", it_comm.getMakeName());
				model.put("msg", "Model Name Length Should Be Less Than 100 Characters");
				return new ModelAndView("Edit_Model_Tiles");
			}
		
		String username = session.getAttribute("username").toString();

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {
			Query q0 = sessionHQL.createQuery(
					"select count(id) from TB_MASTER_MODEL where  model_name=:model_name and id !=:id");
			q0.setParameter("model_name", request.getParameter("model_name"));
			q0.setParameter("id", id);
			Long c = (Long) q0.uniqueResult();

			if (c == 0) {

				String hql = "update TB_MASTER_MODEL set make_name=:make_name,model_name=:model_name,category=:category,assets_name=:assets_name,modifed_by=:modifed_by,modified_dt=:modified_dt"
						+ " where id=:id";

				Query query = sessionHQL.createQuery(hql).setString("model_name", model_name.toUpperCase())
						.setInteger("assets_name", Integer.parseInt(assets_name)).setInteger("category", Integer.parseInt(category))
						.setInteger("make_name", Integer.parseInt(make_name))
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
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return new ModelAndView("redirect:model_masterUrl");
	}

	@RequestMapping(value = "/Delete_Model", method = RequestMethod.POST)
	public ModelAndView Delete_Model(@ModelAttribute("id2") int id, BindingResult result, HttpServletRequest request,
			ModelMap model, HttpSession session1,@RequestParam(value = "msg", required = false) String msg) {
		String roleid = session1.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("model_masterUrl", roleid);
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
			hqlUpdate = "delete from TB_MASTER_MODEL where id=:id";
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
		return new ModelAndView("redirect:model_masterUrl");
	}

	@RequestMapping(value = "/Modelmastereport", method = RequestMethod.POST)
	public ModelAndView Modelmastereport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1) {

	 String category = request.getParameter("category1");
	 String assets_name = request.getParameter("assets_name1");
	 String make_name = request.getParameter("make_name1");
	 String model_name = request.getParameter("model_name1");

	 
		ArrayList<ArrayList<String>> CTlist = modelDAO.Report_DataTableMakeList(category,assets_name,make_name,model_name);
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("Category");
		TH.add("Assets Name");
		TH.add("Make/Brand Name");
		TH.add("Model Name");
		String Heading = "\nOModel Master";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}

}
