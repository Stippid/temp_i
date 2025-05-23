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
import javax.servlet.http.HttpServlet;
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
import com.dao.itasset.masters.Asset_mstrDAO;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.models.itasset.master.TB_MSTR_ASSETS;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Asset_mstrController {
	It_CommonController it_comm = new It_CommonController();

	@Autowired
	private Asset_mstrDAO objDAO;
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	
	@Autowired
	 RoleBaseMenuDAO roledao;

	@RequestMapping(value = "Asset_mstr_Url", method = RequestMethod.GET)
	public ModelAndView Asset_mstr_Url(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg)
			throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException {
		
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Asset_mstr_Url", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				//return new ModelAndView("redirect:bodyParameterNotAllow");
			}

		
		Mmap.put("msg", msg);
		return new ModelAndView("Asset_mstr_tile", "asset_mstrCMD", new TB_MSTR_ASSETS());
	}

	@RequestMapping(value = "/getAsset_mstrReportDataList", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getFilteredDataList_SQL(int startPage, String pageLength,
			String Search, String orderColunm, String orderType, String assets_name,String category,HttpSession sessionUserId)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
	
		return objDAO.getReportListAsset_mstr(startPage, pageLength, Search, orderColunm, orderType,assets_name,category, sessionUserId);
	}

	@RequestMapping(value = "/getAsset_mstrTotalCount", method = RequestMethod.POST)
	public @ResponseBody long getAsset_mstrTotalCount(HttpSession sessionUserId, String Search, String assets_name,String category) {
		return objDAO.getReportListAsset_mstrTotalCount(Search,assets_name,category);
	}

	@RequestMapping(value = "/asset_mstrAction", method = RequestMethod.POST)
	public ModelAndView asset_mstrAction(@Valid @ModelAttribute("asset_mstrCMD") TB_MSTR_ASSETS ln,
			BindingResult result, HttpServletRequest request, ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg) {
		
		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Asset_mstr_Url", roleid);
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
		String assets_name = request.getParameter("assets_name").trim();
		if(ln.getCategory() == 0) {
			 model.put("msg", "Please Select Category");
			 return new ModelAndView("redirect:Asset_mstr_Url");
		 }
		if (assets_name.equals("") || assets_name == null || assets_name == "null") {
			model.put("msg", "Please Enter Asset Name");
			return new ModelAndView("redirect:Asset_mstr_Url");
		}
		if(assets_name.length() > 50) {
			 model.put("msg", "Asset Name Length Should Be Less Than 50 Characters");
			 return new ModelAndView("redirect:Asset_mstr_Url");
		 }
		
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try{			
		Query q0 = sessionHQL.createQuery(
				"select count(id) from TB_MSTR_ASSETS where category=:category and UPPER(assets_name)=:assets_name and id !=:id");
		q0.setParameter("category", ln.getCategory());
		q0.setParameter("id", id);
		q0.setParameter("assets_name", assets_name.toUpperCase());
		Long c = (Long) q0.uniqueResult();
		if (c == 0) {
			ln.setCreated_by(username);
			ln.setCreated_dt(date);
			ln.setAssets_name(assets_name.toUpperCase());
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
		//model.put("msg", "Data Saved Successfully");
		return new ModelAndView("redirect:Asset_mstr_Url");
	}

	@RequestMapping(value = "EditAsset_mstrUrl", method = RequestMethod.POST)
	public ModelAndView EditAsset_mstrUrl(ModelMap Mmap, HttpSession session,HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg, String updateid) {
		
		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Asset_mstr_Url", roleid);
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
		q = s1.createQuery("from TB_MSTR_ASSETS where cast(id as string)=:PK");
		q.setString("PK", DcryptedPk);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		s1.close();
		Mmap.put("Editasset_mstrCMD1", list.get(0));
		Mmap.put("msg", msg);
		return new ModelAndView("EditAsset_mstr_tile", "Editasset_mstrCMD", new TB_MSTR_ASSETS());
	}

	@RequestMapping(value = "/Editasset_mstrAction", method = RequestMethod.POST)
	public ModelAndView Editasset_mstrAction(@Valid @ModelAttribute("Editasset_mstrCMD") TB_MSTR_ASSETS ln,
			BindingResult result, HttpServletRequest request, ModelMap model, HttpSession session,@RequestParam(value = "msg", required = false) String msg) {

		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Asset_mstr_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		int id = Integer.parseInt(request.getParameter("id"));
		String assets_name = request.getParameter("assets_name").trim();
		String enckey ="commonPwdEncKeys";
		try {
			Cipher c = hex_asciiDao.EncryptionSHA256Algo(session,enckey);

        try {
			String EncryptedPk =new String(Base64.encodeBase64(c.doFinal(request.getParameter("id").toString().getBytes())));
			if(ln.getCategory() == 0) {
				 return EditAsset_mstrUrl(model,session,request, "Please Select Category",EncryptedPk);
			 }
			if (assets_name.equals("") || assets_name == null) {
				return EditAsset_mstrUrl(model,session,request, "Please Select Category",EncryptedPk);
			}
			if(assets_name.length() > 50) {
				return EditAsset_mstrUrl(model,session,request, "Asset Name Length Should Be Less Than 50 Characters",EncryptedPk);
				
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
					"select count(id) from TB_MSTR_ASSETS where category=:category and UPPER(assets_name)=:assets_name and id !=:id");
			q0.setParameter("category", ln.getCategory());
			q0.setParameter("id", id);
			q0.setParameter("assets_name", assets_name.toUpperCase());
			Long du = (Long) q0.uniqueResult();
			if (du == 0) {
		ln.setId(Integer.parseInt(request.getParameter("id")));
		ln.setAssets_name(assets_name.toUpperCase());
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
					model.put("msg", "Couldn�t roll back transaction " + rbe);
				}
				throw e;
			}finally{
				if(sessionHQL!=null){
				   sessionHQL.close();
				}
			}	

		return new ModelAndView("redirect:Asset_mstr_Url");
	}

	@RequestMapping(value = "/deleteasset_mstrUrl", method = RequestMethod.POST)
	public ModelAndView deleteasset_mstrUrl(String deleteid, HttpSession session, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Asset_mstr_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		
		List<String> list = new ArrayList<String>();
		list.add(objDAO.Deleteasset_mstr(deleteid, session));
		model.put("msg", list);
		return new ModelAndView("redirect:Asset_mstr_Url");
	}
	@RequestMapping(value = "/Assetsmastereport", method = RequestMethod.POST)
	public ModelAndView Assetsmastereport(HttpServletRequest request,ModelMap model,HttpSession session,String typeReport1) {

		String category = request.getParameter("category1");
		String assets_name = request.getParameter("assets_name1");
		ArrayList<ArrayList<String>> CTlist = objDAO.Report_DataTableMakeList(assets_name,category);
		
		List<String> TH = new ArrayList<String>();
		TH.add("ID");
		TH.add("Category");
		TH.add("Assets Name");
		
		String Heading = "\nOassets_name Master";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}
}
