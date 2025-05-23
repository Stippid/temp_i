package com.controller.Consumables;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.controller.DateWithTimestamp.DateWithTimeStampController;
import com.controller.commonController.It_CommonController;
import com.controller.itasset.ExportFile.ExcelComputingUserListReportView;
import com.controller.login.RoleController;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.tms.AllMethodsControllerTMS;
import com.controller.validation.ValidationController;
import com.dao.itasset.masters.Asset_mstrDAO;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.models.assets.CONSUMABLES_CHILD;
import com.models.assets.CONSUMABLES_MAIN;
import com.models.itasset.master.TB_MASTER_MAKE;
import com.models.itasset.master.TB_MASTER_MODEL;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Consumables {

	RoleController roleController = new RoleController();

	@Autowired
	private Asset_mstrDAO objDAO;
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();

	@Autowired
	RoleBaseMenuDAO roledao;

	ValidationController validation = new ValidationController();
	It_CommonController it_comm = new It_CommonController();
	AllMethodsControllerOrbat com = new AllMethodsControllerOrbat();
	AllMethodsControllerTMS allt = new AllMethodsControllerTMS();
	@Autowired
	RoleController rolecontroller = new RoleController();

	@RequestMapping(value = "/add_consumables", method = RequestMethod.GET)
	public ModelAndView add_consumables(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg)
			throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException {

		// String roleid = session.getAttribute("roleid").toString();
		//
//		Boolean val = roledao.ScreenRedirect("add_consumables", roleid);
//		if (val == false) {
//			return new ModelAndView("AccessTiles");
//		}
//		if (request.getHeader("Referer") == null) {
//			msg = "";
//			// return new ModelAndView("redirect:bodyParameterNotAllow");
//		}

		Mmap.put("msg", msg);
		Mmap.put("getServiceable_StateList", it_comm.getServiceable_StateList());
		Mmap.put("UNServiceableList", it_comm.UNServiceableList());
		Mmap.put("getBudgetHeadList", it_comm.getBudgetHeadList());
		Mmap.put("getConsumables", it_comm.getConsumableList());
		Mmap.put("getSectionNameList", rolecontroller.getSectionNameList());
		Mmap.put("getMaintainceAgencyList", it_comm.getMaintainceAgencyList());
		return new ModelAndView("ConsumablesTiles", "ConsumablesCMD", new CONSUMABLES_MAIN());
	}
	
	@RequestMapping(value = "/request_consumables", method = RequestMethod.GET)
	public ModelAndView request_consumablesurl(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg)
			throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException {

		// String roleid = session.getAttribute("roleid").toString();
		//
//		Boolean val = roledao.ScreenRedirect("add_consumables", roleid);
//		if (val == false) {
//			return new ModelAndView("AccessTiles");
//		}
//		if (request.getHeader("Referer") == null) {
//			msg = "";
//			// return new ModelAndView("redirect:bodyParameterNotAllow");
//		}

		Mmap.put("msg", msg);
		Mmap.put("getConsumables", it_comm.getConsumableList());
		Mmap.put("getSectionNameList", rolecontroller.getSectionNameList());
		 Mmap.put("getBudgetHeadList", it_comm.getBudgetHeadList());
		return new ModelAndView("RequestConsumableTiles", "RequestConsumableCmd", new CONSUMABLES_CHILD());
	}
	
//	@RequestMapping(value = "/RequestConsumableAction", method = RequestMethod.POST)
//	public ModelAndView RequestConsumableAction(@ModelAttribute("RequestConsumableCmd") CONSUMABLES_CHILD consumable,
//	        @RequestParam(value = "msg", required = false) String msg1,
//	        @RequestParam(value = "issue_quantity2", required = false) String issue_quantityStr, // Change to String
//	        @RequestParam(value = "p_id", required = false) String p_idStr, // Change to String
//	        @RequestParam(value = "warrantycheck", required = false) String warrantycheck,
//	        @RequestParam(value = "assetcount2", required = false) int assetcount2, // Keep as int
//	        ModelMap model, HttpSession session) throws ParseException {
//
//	    int id = consumable.getId() > 0 ? consumable.getId() : 0;
//	    Date date = new Date();
//	    String rediurl = "redirect:request_consumables";
//	    Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
//	    Transaction tx = null;
//	    String username = session.getAttribute("username").toString();
//
//	    try {
//	        tx = sessionHQL.beginTransaction();
//	        CONSUMABLES_CHILD T_asset = new CONSUMABLES_CHILD();
//
//	        // Check if issue_quantityStr is empty or null
//	        if (issue_quantityStr == null ||issue_quantityStr.equals("") || issue_quantityStr.trim().isEmpty()) {
//	            model.put("msg", "Please Enter Issue Quantity");
//	            return new ModelAndView(rediurl);
//	        }
//
//	        int issue_quantity;
//	        try {
//	            issue_quantity = Integer.parseInt(issue_quantityStr);
//	        } catch (NumberFormatException e) {
//	            model.put("msg", "Invalid Issue Quantity. Please enter a valid number.");
//	            return new ModelAndView(rediurl);
//	        }
//
//	        if (issue_quantity > assetcount2) {
//	            model.put("msg", "Issue Quantity cannot be greater than Total Quantity.");
//	            return new ModelAndView(rediurl);
//	        }
//
//	        if (p_idStr == null || p_idStr.trim().isEmpty()) {
//	            model.put("msg", "Please Enter Product ID (p_id)");
//	            return new ModelAndView(rediurl);
//	        }
//
//	        int p_id;
//	        try {
//	            p_id = Integer.parseInt(p_idStr);
//	        } catch (NumberFormatException e) {
//	            model.put("msg", "Invalid Product ID. Please enter a valid number.");
//	            return new ModelAndView(rediurl);
//	        }
//
//	        if (id == 0) {
//	            T_asset.setIssue_quantity(issue_quantity);
//	            T_asset.setWarrantycheck(warrantycheck);
//	            T_asset.setCreated_by(username);
//	            T_asset.setCreated_dt(date);
//	            T_asset.setP_id(p_id); // Set the p_id
//	            sessionHQL.save(T_asset);
//	            tx.commit(); // Commit the transaction
//	            model.put("msg", "Data Saved Successfully.");
//	        }
//	    } catch (Exception e) {
//	        if (tx != null) {
//	            tx.rollback(); // Rollback on error
//	        }
//	        model.put("msg", "Error saving data: " + e.getMessage());
//	    } finally {
//	        sessionHQL.close(); // Close the session
//	    }
//
//	    return new ModelAndView(rediurl);
//	}
//	@RequestMapping(value = "/RequestConsumableAction", method = RequestMethod.POST)
//	public ModelAndView RequestConsumableAction(@ModelAttribute("RequestConsumableCmd") CONSUMABLES_CHILD consumable,
//	        @RequestParam(value = "msg", required = false) String msg1,
//	        @RequestParam(value = "issue_quantity2", required = false) String issue_quantityStr,
//	        @RequestParam(value = "p_id", required = false) String p_idStr,
//	        @RequestParam(value = "warrantycheck", required = false) String warrantycheck,
//	        @RequestParam(value = "assetcount2", required = false) int assetcount2,
//	        ModelMap model, HttpSession session) throws ParseException {
//
//		int id = consumable.getId() > 0 ? consumable.getId() : 0;
//		Date date = new Date();
//		String rediurl; // Declare the variable here
//
//		if (id == 0) {
//		    rediurl = "redirect:request_consumables"; // Assign value based on condition
//		} else {
//		    rediurl = "redirect:issue_consumables"; // Assign value based on condition
//		}
//
//		// Now you can use rediurl as needed
//	    Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
//	    Transaction tx = null;
//	    String username = session.getAttribute("username").toString();
//
//	    try {
//	        tx = sessionHQL.beginTransaction();
//	        CONSUMABLES_CHILD T_asset = new CONSUMABLES_CHILD();
//
//	        // Check if issue_quantityStr is empty or null
//	        if (issue_quantityStr == null || issue_quantityStr.trim().isEmpty()) {
//	            model.put("msg", "Please Enter Issue Quantity");
//	            return new ModelAndView(rediurl);
//	        }
//
//	        int issue_quantity;
//	        try {
//	            issue_quantity = Integer.parseInt(issue_quantityStr);
//	        } catch (NumberFormatException e) {
//	            model.put("msg", "Invalid Issue Quantity. Please enter a valid number.");
//	            return new ModelAndView(rediurl);
//	        }
//
//	        if (issue_quantity > assetcount2) {
//	            model.put("msg", "Issue Quantity cannot be greater than Total Quantity.");
//	            return new ModelAndView(rediurl);
//	        }
//
//	        // Handle p_id
//	        int p_id = 0; // Default value
//	        if (p_idStr != null && !p_idStr.trim().isEmpty()) {
//	            try {
//	                p_id = Integer.parseInt(p_idStr);
//	            } catch (NumberFormatException e) {
//	                model.put("msg", "Invalid Product ID. Please enter a valid number.");
//	                return new ModelAndView(rediurl);
//	            }
//	        }
//
//	        if (id == 0) {
//	            // Create new entry
//	            T_asset.setIssue_quantity(issue_quantity);
//	            T_asset.setWarrantycheck(warrantycheck);
//	            T_asset.setCreated_by(username);
//	            T_asset.setCreated_dt(date);
//	            T_asset.setP_id(p_id); // Set the p_id
//	            sessionHQL.save(T_asset);
//	            tx.commit(); // Commit the transaction
//	            model.put("msg", "Data Saved Successfully.");
//	        } else if (id != 0) { // Use else if here
//	            // Update existing entry
//	            T_asset = sessionHQL.get(CONSUMABLES_CHILD.class, id);
//	            if (T_asset != null) {
//	                T_asset.setIssue_quantity(issue_quantity);
//	                T_asset.setWarrantycheck(warrantycheck);
//	                T_asset.setModified_by(username); // Assuming you have an updated_by field
//	                T_asset.setModified_date(date); // Assuming you have an updated_dt field
//	                sessionHQL.update(T_asset);
//	                tx.commit(); // Commit the transaction
//	                model.put("msg", "Data Updated Successfully.");
//	            } else {
//	                model.put("msg", "Consumable not found for the given ID.");
//	            }
//	        }
//	    } catch (Exception e) {
//	        if (tx != null) {
//	            tx.rollback(); // Rollback on error
//	        }
//	        model.put("msg", "Error saving data: " + e.getMessage());
//	    } finally {
//	        sessionHQL.close(); // Close the session
//	    }
//
//	    return new ModelAndView(rediurl);
//	}
	
	
	
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/getActiveCrvNoList", method = RequestMethod.POST)
	public @ResponseBody List<String> getActiveCrvNoList(String loginname, HttpSession sessionUserId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select crv_no from CONSUMABLES_MAIN where status = '1' and crv_no like :crv_no order by crv_no ").setMaxResults(10);
		q.setParameter("crv_no", loginname+"%");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		String enckey = hex_asciiDao.getAlphaNumericString();
		Cipher c = null;
		try {
			c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId, enckey);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String> FinalList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			byte[] encCode = null;
			try {
				encCode = c.doFinal(list.get(i).getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			FinalList.add(base64EncodedEncryptedCode);
		}
		// Enc Key Append Last value of List
		FinalList.add(enckey + "4bsjyg==");
		return FinalList;
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getActiveCrvDataList", method = RequestMethod.POST)
	public @ResponseBody List<CONSUMABLES_MAIN> getActiveCrvDataList(@RequestParam(value = "id", required = false) String id, HttpSession sessionUserId) {
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    Transaction tx = session.beginTransaction();
	    
	    Query<CONSUMABLES_MAIN> q = session.createQuery("FROM CONSUMABLES_MAIN WHERE status='1' and crv_no = :crv_no", CONSUMABLES_MAIN.class);
	    q.setParameter("crv_no", id);
	    
	    List<CONSUMABLES_MAIN> list = q.list();
	    
	    tx.commit();
	    session.close();
	    return list;
	}
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/ConsumableAction", method = RequestMethod.POST)
	public ModelAndView ConsumableAction(@ModelAttribute("ConsumablesCMD") CONSUMABLES_MAIN consumable,
			@RequestParam(value = "msg", required = false) String msg1,
			@RequestParam(value = "u_file1", required = false) MultipartFile u_file,
			@RequestParam(value = "make_mstr_id", required = false) String make_mstr_id,
			@RequestParam(value = "model_mstr_id", required = false) String model_mstr_id,
			@RequestParam(value = "type_hw_id", required = false) String type_hw_id,
			@RequestParam(value = "pro_type_id", required = false) String pro_type_id,
			@RequestParam(value = "pro_connect_id", required = false) String pro_connect_id,
			@RequestParam(value = "screenurl", required = false) String screenurl,
			@RequestParam(value = "u_file_hid", required = false) String u_file_hid, HttpServletRequest request,
			ModelMap model, HttpSession session) throws ParseException {
		String roleid = session.getAttribute("roleid").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int id = consumable.getId() > 0 ? consumable.getId() : 0;
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		Date date = new Date();
		Date warrenty_dt = null;
		Date proc_dt = null;

		Date unsv_from_dt1 = null;
		String rediurl = "";
		if (id == 0) {
			rediurl = "redirect:add_consumables";
		} else {
			rediurl = "redirect:Search_ConsumableUrl";
		}
		String section = request.getParameter("section");
		String assets_type = request.getParameter("assets_type");
		String make_name = request.getParameter("make_name");
		String brand_other = request.getParameter("brand_other");
		String model_name = request.getParameter("model_name");
		String model_other = request.getParameter("model_other");
		String warrantycheck = request.getParameter("warrantycheck");
		String warranty = request.getParameter("warrantydt");
		String proc_date = request.getParameter("proc_dt");
		String b_head = request.getParameter("b_head");
		String b_code = request.getParameter("b_code");
		String crv_no = request.getParameter("crv_no");
		String gem_no1 = request.getParameter("gem_no1");

		String assetcount2 = request.getParameter("assetcount2");

		String asscount = "0";

		if (section == null || section.equals("0") || section.equals("")) {
			model.put("msg", "Please Enter Section  ");
			return new ModelAndView(rediurl);
		}
		if (assets_type == null || assets_type.equals("0") || assets_type.equals("")) {
			model.put("msg", "Please Select Consumable Type ");
			return new ModelAndView(rediurl);
		}
		if (make_name == null || make_name.equals("0") || make_name.equals("")) {
			model.put("msg", "Please Select Make/Brand Name  ");
			return new ModelAndView(rediurl);
		}
		if (model_name == null || model_name.equals("0") || model_name.equals("")) {
			model.put("msg", "Please Select Model Name ");
			return new ModelAndView(rediurl);
		}
//		if (warrantycheck == null || warrantycheck.trim().isEmpty()) {
//		    model.put("msg", "Please select a warranty option.");
//		    return new ModelAndView(rediurl);
//		}
//
//		if(warranty==null  || warranty.equals("") || warranty.equals("DD/MM/YYYY")) {
//			model.put("msg", "Please Select Warranty");
//			return new ModelAndView(rediurl);
//		}
		
		
		
		
		if (warrantycheck != null && warrantycheck.toUpperCase().equals("YES")) {

			if (warranty == null || warranty.equals("") || warranty.equals("DD/MM/YYYY")) {
				model.put("msg", "Please Select Warranty");
				return new ModelAndView(rediurl);
			} else {
				warrenty_dt = format.parse(warranty);
			}

		}
		if (assetcount2 == null || assetcount2.equals("")) {
			model.put("msg", "Please Enter Total Count.");
			return new ModelAndView(rediurl);
		}

		if (proc_date == null || proc_date.equals("") || proc_date.equals("DD/MM/YYYY")) {
			model.put("msg", "Please Select Proc date ");
			return new ModelAndView(rediurl);
		}

		if (proc_date != null && !proc_date.equals("") && !proc_date.equals("DD/MM/YYYY")) {
			proc_dt = format.parse(proc_date);

		}
		if (b_head == null || b_head.equals("0")) {
			model.put("msg", "Please Enter Budget Head ");
			return new ModelAndView(rediurl);
		}
		if (b_code == null || b_code.equals("0")) {
			model.put("msg", "Please Enter Budget Code ");
			return new ModelAndView(rediurl);
		}
		if (crv_no == null || crv_no.equals("") || crv_no.equals("")) {
			model.put("msg", "Please Enter CRV NO.");
			return new ModelAndView(rediurl);
		}
		
//		if(assetcount2

//		
		if(assetcount2 ==null || assetcount2 == "" || assetcount2.equals("")|| assetcount2.equals(null)){
			 assetcount2 = asscount;
		}else {
			 assetcount2 = request.getParameter("assetcount2");
		}
		int assetcount;
		try {
			assetcount = Integer.parseInt(assetcount2);
			if (assetcount < 0) {
				model.put("msg", "Please Enter Valid Asset count.");
				return new ModelAndView(rediurl);
			}
		} catch (NumberFormatException e) {
			model.put("msg", "Please Enter Asset count.");
			return new ModelAndView(rediurl);
		}

		String sus_no1 = session.getAttribute("roleSusNo").toString();
		DateWithTimeStampController timestamp = new DateWithTimeStampController();
		String extension = "";
		String fname = "";
		CONSUMABLES_MAIN T_asset = new CONSUMABLES_MAIN();


		String username = session.getAttribute("username").toString();

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {
			for (int i = 1; i <= assetcount; i++) {
				String machine_no = request.getParameter("machine_no" + i);
				String hqlUpdate = "select count(id) from CONSUMABLES_MAIN where id!=:id and machine_no=:machine_no ";
				@SuppressWarnings("deprecation")
				Query query2 = sessionHQL.createQuery(hqlUpdate).setInteger("id", id)
						.setString("machine_no", machine_no).setMaxResults(1);
				Long c22 = (Long) query2.uniqueResult();
				if (c22 != null && c22 > 0) {
					model.put("msg", "Consumables Asset With This Machine Number Already Exist");

					return new ModelAndView(rediurl);
				}
			}
			if (id == 0) {

				String model_no = request.getParameter("model_no");

				T_asset.setSection(section);
				T_asset.setAssets_type(consumable.getAssets_type());
				T_asset.setMake_name(Integer.parseInt(make_name));
				T_asset.setModel_no(model_no);
				T_asset.setModel_name(consumable.getModel_name());
				T_asset.setWarrantycheck(consumable.getWarrantycheck());
				T_asset.setWarranty(warrenty_dt);
				T_asset.setYear_of_proc(consumable.getYear_of_proc());
				T_asset.setRemarks(consumable.getRemarks().toUpperCase());
				T_asset.setProc_cost(consumable.getProc_cost());
				T_asset.setProc_date(proc_dt);
				T_asset.setB_head(consumable.getB_head());
				T_asset.setB_code(consumable.getB_code());
				T_asset.setCrv_no(crv_no);
				T_asset.setGem_no(gem_no1);
				T_asset.setAssetcount(Integer.parseInt(assetcount2));
				T_asset.setWarrantycheck(warrantycheck);
				TB_MASTER_MAKE make_mstr = new TB_MASTER_MAKE();
				TB_MASTER_MODEL model_mstr = new TB_MASTER_MODEL();
				if (!brand_other.equals("")) {

					List<String> brand_other1 = it_comm.getPeriBrandOtherList(request);
					if (brand_other1.size() > 0) {
						model.put("msg", "This Make Name Already Exists.");
						return new ModelAndView(rediurl);
					}
					make_mstr.setMake_name(brand_other.toUpperCase());
					make_mstr.setStatus(0);
					make_mstr.setAssets_name(Integer.parseInt(assets_type));
					make_mstr.setCategory(3);
					make_mstr.setCreated_by(username);
					make_mstr.setCreated_dt(date);
					sessionHQL.save(make_mstr);
					String make_id = String.valueOf(make_mstr.getId());
					T_asset.setBrand_other(make_id);
				}

				if (!model_other.equals("")) {

					int mak_id = make_mstr.getId();

					List<String> model_other1 = it_comm.getPeriModelOtherList(request);

					if (model_other1.size() > 0) {
						model.put("msg", "This Model Name Already Exists.");
						return new ModelAndView(rediurl);
					}

					model_mstr.setModel_name(model_other.toUpperCase());
					model_mstr.setAssets_name(Integer.parseInt(assets_type));
					model_mstr.setStatus(0);
					model_mstr.setCategory(3);
					model_mstr.setCreated_by(username);
					model_mstr.setCreated_dt(date);
					model_mstr.setMake_name(mak_id);
					sessionHQL.save(model_mstr);
					String model_id = String.valueOf(model_mstr.getId());
					T_asset.setModel_other(model_id);
				}

				T_asset.setCreated_by(username);
				T_asset.setCreated_date(date);

				CONSUMABLES_CHILD obj = new CONSUMABLES_CHILD();
				obj.setWarranty(warrenty_dt);

				obj.setCreated_by(username);
				obj.setIssue_quantity(assetcount);
				obj.setCreated_dt(date);
				T_asset.setStatus(0);
				obj.setStatus(0);
				int c = (Integer) sessionHQL.save(T_asset);
				obj.setP_id(c);
				sessionHQL.save(obj);
				sessionHQL.flush();
				sessionHQL.clear();
				model.put("msg", "Data Saved Successfully.");

			} else {

				String gem_no = request.getParameter("gem_no1");

				String fname1 = "";
				if (!u_file_hid.equals("")) {
					if (!u_file.isEmpty()) {
						try {
							byte[] bytes = u_file.getBytes();
							String mnhFilePath = session.getAttribute("itAssetsFilePath").toString();
							File dir = new File(mnhFilePath);
							if (!dir.exists())
								dir.mkdirs();
							String filename = u_file.getOriginalFilename();
							int i = filename.lastIndexOf('.');
							if (i >= 0) {
								extension = filename.substring(i + 1);
							}
							fname1 = dir.getAbsolutePath() + File.separator + timestamp.currentDateWithTimeStampString()
									+ "_" + sus_no1 + "_IT_Doc." + extension;
							File serverFile = new File(fname1);
							BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
							stream.write(bytes);
							stream.close();

						} catch (Exception e) {
						}
					}

				}

				String hql = "update  CONSUMABLES_MAIN set modified_by=:modified_by,status=:status,modified_date=:modified_date,assets_type=:assets_type,\r\n"
						+ "type_of_hw=:type_of_hw,year_of_proc=:year_of_proc,make_name=:make_name,\r\n"
						+ "model_name=:model_name,proc_cost=:proc_cost,remarks=:remarks,type=:type,\r\n"
						+ "warranty=:warranty,warrantycheck=:warrantycheck," + " \r\n"
						+ "b_head=:b_head,b_code=:b_code,b_cost=:b_cost,\r\n"
						+ " proc_date=:proc_date,u_file=:fname1 , crv_no=:crv_no ,gem_no=:gem_no,username=:username ,assetcount=:assetcount  " 
						+ " where id=:id ";
			
				Query query = sessionHQL.createQuery(hql).setInteger("status", 0).setInteger("id", id)
						.setString("modified_by", username).setTimestamp("modified_date", date)
						.setInteger("assets_type", consumable.getAssets_type())
						.setInteger("type_of_hw", consumable.getType_of_hw())
						.setString("year_of_proc", consumable.getYear_of_proc())
						.setInteger("make_name", consumable.getMake_name())
						.setInteger("model_name", consumable.getModel_name())
						.setString("proc_cost", consumable.getProc_cost())
						.setString("remarks", consumable.getRemarks().toUpperCase())
						.setString("type", consumable.getType())
						.setTimestamp("warranty", warrenty_dt).setTimestamp("proc_date", proc_dt)
						.setString("b_head", consumable.getB_head()).setString("b_code", consumable.getB_code())
						.setString("b_cost", consumable.getB_cost())
						.setString("fname1", fname1).setString("crv_no", crv_no).setString("gem_no", gem_no).setInteger("assetcount", assetcount)
						.setString("username", username).setString("warrantycheck", warrantycheck) ;

				String hql1 = "update  CONSUMABLES_CHILD set modified_by=:modified_by,status=:status,modified_date=:modified_date,"
						+ "unsv_from_dt=:unsv_from_dt,warranty=:warranty ,issue_quantity=:issue_quantity where p_id=:p_id and status = '0' \r\n";

				Query query1 = sessionHQL.createQuery(hql1).setInteger("status", 0).setInteger("p_id", id)
						.setString("modified_by", username).setTimestamp("modified_date", date)
						.setTimestamp("unsv_from_dt", unsv_from_dt1).setTimestamp("warranty", warrenty_dt).setInteger("issue_quantity", assetcount);

				String msg = query.executeUpdate() > 0 ? "update" : "0";

				if (!make_mstr_id.equals(""))

				{
					String hql2 = "update  TB_MASTER_MAKE set modified_by=:modified_by,status=:status,modified_dt=:modified_dt,make_name=:make_name where id=:make_id";

					Query query2 = sessionHQL.createQuery(hql2).setInteger("status", 0)
							.setInteger("make_id", Integer.parseInt(make_mstr_id)).setString("modified_by", username)
							.setTimestamp("modified_dt", date).setString("make_name", brand_other.toUpperCase());
					msg = query2.executeUpdate() > 0 ? "update" : "0";
				}

				if (!model_mstr_id.equals(""))

				{
					String hql3 = "update  TB_MASTER_MODEL set modifed_by=:modified_by,status=:status,modified_dt=:modified_dt,model_name=:model_name where id=:model_id";

					Query query3 = sessionHQL.createQuery(hql3).setInteger("status", 0)
							.setInteger("model_id", Integer.parseInt(model_mstr_id)).setString("modified_by", username)
							.setTimestamp("modified_dt", date).setString("model_name", model_other.toUpperCase());
					msg = query3.executeUpdate() > 0 ? "update" : "0";
				}

				if (!type_hw_id.equals(""))

				{
					String hql4 = "update  TB_MSTR_TYPE_OF_HW_M set modified_by=:modified_by,status=:status,modified_date=:modified_dt,type_of_hw=:type_of_hw where id=:type_hw_id";

					Query query4 = sessionHQL.createQuery(hql4).setInteger("status", 0)
							.setInteger("type_hw_id", Integer.parseInt(type_hw_id)).setString("modified_by", username)
							.setTimestamp("modified_dt", date);

					msg = query4.executeUpdate() > 0 ? "update" : "0";
				}

				if (!pro_type_id.equals(""))

				{
					String hql5 = "update  TB_MSTR_TYPE_M set modified_by=:modified_by,status=:status,modified_date=:modified_dt,type=:type where id=:pro_typ_id";

					Query query5 = sessionHQL.createQuery(hql5).setInteger("status", 0)
							.setInteger("pro_typ_id", Integer.parseInt(pro_type_id)).setString("modified_by", username)
							.setTimestamp("modified_dt", date);

					msg = query5.executeUpdate() > 0 ? "update" : "0";
				}

				if (!pro_connect_id.equals(""))

				{
					String hql6 = "update  TB_MSTR_CONNECTIVITY set modifed_by=:modified_by,status=:status,modified_dt=:modified_dt where id=:pro_connect_id";

					Query query6 = sessionHQL.createQuery(hql6).setInteger("status", 0)
							.setInteger("pro_connect_id", Integer.parseInt(pro_connect_id))
							.setString("modified_by", username).setTimestamp("modified_dt", date);
					msg = query6.executeUpdate() > 0 ? "update" : "0";
				}

				msg = query1.executeUpdate() > 0 ? "update" : "0";
				if (msg.equals("update")) {
					model.put("msg", "Data Updated Successfully.");
					return new ModelAndView("redirect:"+screenurl);
				}
			}
			// **END BISG AHM **//
			tx.commit();
		} catch (RuntimeException e) {
			e.printStackTrace();
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
		return new ModelAndView(rediurl);
	}



	@RequestMapping(value = "/admin/Search_ConsumableUrl", method = RequestMethod.GET)
	public ModelAndView Search_ConsumableUrl(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_ConsumableUrl", roleid);
//		if (val == false) {
//			return new ModelAndView("AccessTiles");
//		}
//		if (request.getHeader("Referer") == null) {
//			msg = "";
//			//return new ModelAndView("redirect:bodyParameterNotAllow");
//		}

		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		Mmap.put("roleSusNo", roleSusNo);
		Mmap.put("msg", msg);
		Mmap.put("status1", "0");
		Mmap.put("YearOfManufacturing", it_comm.getYearOfManufacturing());
		Mmap.put("get", it_comm.getPeripheral());
		Mmap.put("getServiceable_StateList", it_comm.getServiceable_StateList());
		Mmap.put("getConsumables", it_comm.getConsumableList());
		Mmap.put("getSectionNameList", rolecontroller.getSectionNameList());
		return new ModelAndView("SearchConsumableTiles");
	}

	@RequestMapping(value = "/admin/Search_consumable_1", method = RequestMethod.POST)
	public ModelAndView Search_consumable_1(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "machine_make1", required = false) String machine_make,
			@RequestParam(value = "machine_no1", required = false) String machine_no,
			@RequestParam(value = "model_no1", required = false) String model_no,
			@RequestParam(value = "year_of_manufacturing1", required = false) String year_of_manufacturing,

			@RequestParam(value = "section1", required = false) String section,
			@RequestParam(value = "from_date1", required = false) String from_date,
			@RequestParam(value = "to_date1", required = false) String to_date,
			@RequestParam(value = "assets_type1", required = false) String assets_type,
			@RequestParam(value = "status1", required = false) String status,
			@RequestParam(value = "s_state1", required = false) String s_state) {
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		Mmap.put("roleSusNo", roleSusNo);
		Mmap.put("getTargetUnitNameList", allt.getTargetUnitNameList(roleSusNo, session));

		Mmap.put("section1", section);
		Mmap.put("machine_make1", machine_make);
		Mmap.put("machine_no1", machine_no);
		Mmap.put("model_no1", model_no);
		Mmap.put("from_date1", from_date);
		Mmap.put("to_date1", to_date);
		Mmap.put("assets_type1", assets_type);
		Mmap.put("status1", status);
		Mmap.put("s_state1", s_state);
		Mmap.put("year_of_manufacturing1", year_of_manufacturing);
		Mmap.put("YearOfManufacturing", it_comm.getYearOfManufacturing());
		// Mmap.put("getPeripheral", it_comm.getPeripheral());
		Mmap.put("getConsumables", it_comm.getConsumableList());
		Mmap.put("getServiceable_StateList", it_comm.getServiceable_StateList());
		Mmap.put("getSectionNameList", rolecontroller.getSectionNameList());

		return new ModelAndView("SearchConsumableTiles");
	}

	@RequestMapping(value = "/getFilteredconsumable1", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getFilteredconsumable1(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String status, String assets_type,
			String section,String year_of_manufacturing, HttpSession sessionUserId) throws SQLException {

		return objDAO.Search_consumable(startPage, pageLength, Search, orderColunm, orderType, status, assets_type, section,year_of_manufacturing,sessionUserId);
	}

	@RequestMapping(value = "/getTotalconsumableCount1", method = RequestMethod.POST)
	public @ResponseBody long getTotalconsumableCount1(String Search, String orderColunm, String orderType,
			String status, String assets_type, String section,String year_of_manufacturing,
			HttpSession sessionUserId) throws SQLException {

		return objDAO.getconsumableCountList(Search, orderColunm, orderType, status, assets_type, section,year_of_manufacturing, sessionUserId);
	}

	@RequestMapping(value = "/Delete_consumables", method = RequestMethod.POST)
	public ModelAndView Delete_consumables(@ModelAttribute("id1") String id, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session1,
			@RequestParam(value = "msg", required = false) String msg,
			 @RequestParam(value = "screenurl10", required = false) String screenurl) {
//		String roleid = session1.getAttribute("roleid").toString();
//		Boolean val = roledao.ScreenRedirect("Search_ConsumableUrl", roleid);
//		if (val == false) {
//			return new ModelAndView("AccessTiles");
//		}
//		if (request.getHeader("Referer") == null) {
//			msg = "";
//			//return new ModelAndView("redirect:bodyParameterNotAllow");
//		}
		List<String> liststr = new ArrayList<String>();
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			String hqlUpdate = "";
			int app;
			hqlUpdate = "delete from CONSUMABLES_MAIN where id=:id";
			app = session.createQuery(hqlUpdate).setInteger("id", Integer.parseInt(id)).executeUpdate();
		
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
		return new ModelAndView("redirect:"+screenurl);
	}



	@RequestMapping(value = "/ConsumablesArchive", method = RequestMethod.POST)
	public ModelAndView ConsumablesArchive(@ModelAttribute("arid2") String id, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session1,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "screenurl12", required = false) String screenurl) {
		String roleid = session1.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_ConsumableUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		List<String> liststr = new ArrayList<String>();
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			String hqlUpdate = "";
			int app;
			hqlUpdate = "update from CONSUMABLES_MAIN set status=:status where id=:id";
			app = session.createQuery(hqlUpdate).setInteger("status", 7).setInteger("id", Integer.parseInt(id))
					.executeUpdate();
			tx.commit();
			session.close();
			if (app > 0) {
				liststr.add("Data Archived Successfully");
			} else {
				liststr.add("Data not Archived");
			}
			model.put("msg", liststr.get(0));
		} catch (Exception e) {
			liststr.add("CAN NOT ARCHIVE THIS DATA BECAUSE IT IS ALREADY IN USED");
			model.put("msg", liststr.get(0));
		}
		return new ModelAndView("redirect:"+screenurl);
	}


	
	
	
	
	

	@RequestMapping(value = "/admin/View_consumables", method = RequestMethod.POST)
	public ModelAndView View_consumables(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "viewid", required = false) int id,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "screenurl11", required = false) String screenurl) {
//		String roleid = session.getAttribute("roleid").toString();
//		Boolean val = roledao.ScreenRedirect("Search_ConsumableUrl", roleid);
//		if (val == false) {
//			return new ModelAndView("AccessTiles");
//		}
//		if (request.getHeader("Referer") == null) {
//			msg = "";
//			//return new ModelAndView("redirect:bodyParameterNotAllow");
//		}
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		CONSUMABLES_MAIN peripheralupd = (CONSUMABLES_MAIN) sessionHQL.get(CONSUMABLES_MAIN.class, id);

		Mmap.put("getConsumables", it_comm.getConsumableList());
		Mmap.put("hardwareListDDL", it_comm.gethardwareListDDL());
		Mmap.put("Type1", it_comm.getType1());
		Mmap.put("YearOfProc", it_comm.getYearOfProc());
		Mmap.put("YearOfManufacturing", it_comm.getYearOfManufacturing());
		Mmap.put("UpsCapacity", it_comm.getUpsCapacity());
		Mmap.put("UNServiceableList", it_comm.UNServiceableList());
		Mmap.put("getDisplay_ConnectorList", it_comm.getDisplay_ConnectorList());
		// Mmap.put("getConnectivity_TypeList", it_comm.getConnectivity_TypeList());
		Mmap.put("getServiceable_StateList", it_comm.getServiceable_StateList());
		// Mmap.put("getPaper_SizeList", it_comm.getPaper_SizeList());
		Mmap.put("getBudgetHeadList", it_comm.getBudgetHeadList());
		Mmap.put("getMakeName", it_comm.getMakeName());
		Mmap.put("getModelNameList", it_comm.getModelNameList());
		Mmap.put("getBudgetCodeList", it_comm.getBudgetCodeList());
		Mmap.put("ServiceableList", it_comm.ServiceableList());
		Mmap.put("getSectionNameList", rolecontroller.getSectionNameList());
		Mmap.put("getMaintainceAgencyList", it_comm.getMaintainceAgencyList());
		Mmap.put("screenurl", screenurl);
		
		int at = peripheralupd.getAssets_type();
		if (at == 16) {
			ArrayList<ArrayList<String>> ntwrk_ftr_data = objDAO.getcomaseptext_network_feature(id);
			Mmap.put("ntwrk_ftr_data", ntwrk_ftr_data);
		}
		if (at == 14) {
			ArrayList<ArrayList<String>> hw_int_data = objDAO.getcomaseptext_hw_interface(id);
			Mmap.put("hw_int_data", hw_int_data);
		}

		return new ModelAndView("ViewConsumablesTiles", "ViewConsumablesCmd", peripheralupd);
	}




	@RequestMapping(value = "/admin/Edit_Consumables", method = RequestMethod.POST)
	public ModelAndView Edit_Consumables(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "updateid", required = false) int id,
			 @RequestParam(value = "screenurl9", required = false) String screenurl) {


		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		Mmap.put("roleSusNo", roleSusNo);
	

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		CONSUMABLES_MAIN assetupd = (CONSUMABLES_MAIN) sessionHQL.get(CONSUMABLES_MAIN.class, id);
		Mmap.put("getConsumables", it_comm.getConsumableList());
		Mmap.put("getMakeName", it_comm.getMakeName());
		Mmap.put("getModelNameList", it_comm.getModelNameList());
		Mmap.put("YearOfProc", it_comm.getYearOfProc());
		Mmap.put("YearOfManufacturing", it_comm.getYearOfManufacturing());
		Mmap.put("UpsCapacity", it_comm.getUpsCapacity());
		Mmap.put("UNServiceableList", it_comm.UNServiceableList());
		Mmap.put("getDisplay_ConnectorList", it_comm.getDisplay_ConnectorList());
		Mmap.put("getServiceable_StateList", it_comm.getServiceable_StateList());
		Mmap.put("getBudgetHeadList", it_comm.getBudgetHeadList());
		Mmap.put("hw_interfaceList", it_comm.hw_interfaceList());
		Mmap.put("getEthernet_portList", it_comm.getEthernet_portList());
		Mmap.put("getManagement_layerList", it_comm.getManagement_layerList());
		Mmap.put("getNetwork_featuresList", it_comm.getNetwork_featuresList());
		Mmap.put("getSectionNameList", rolecontroller.getSectionNameList());
		Mmap.put("getSectionNameList", rolecontroller.getSectionNameList());
		Mmap.put("getMaintainceAgencyList", it_comm.getMaintainceAgencyList());
		Mmap.put("getConsumables", it_comm.getConsumableList());
		Mmap.put("screenurl", screenurl);
		return new ModelAndView("ConsumablesTiles", "ConsumablesCMD", assetupd);
	}

	@RequestMapping(value = "/admin/Excel_Consumables_Assets_Search", method = RequestMethod.POST)
	public ModelAndView Excel_Consumables_Assets_Search(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
	
			@RequestParam(value = "year_of_manufacturing2", required = false) String year_of_manufacturing,
			@RequestParam(value = "assets_type2", required = false) String assets_type,
			@RequestParam(value = "status2", required = false) String status,
			@RequestParam(value = "section2", required = false) String section) {

		ArrayList<ArrayList<String>> Excel = objDAO.Excel_Consumables_Assets_Search(session,
				 assets_type, status, section, year_of_manufacturing);

		String username = session.getAttribute("username").toString();
		Mmap.put("msg", msg);
		String Heading = "SEARCH CONSUMABLES ASSESTS";
		Date date = new Date();
		String foot = " Report Generated On " + new SimpleDateFormat("dd-MM-yyyy").format(date);

		List<String> TH = new ArrayList<String>();
		TH.add("Consumable Type");
		TH.add("CRV No");
		TH.add("Year Of Proc");
		TH.add("Make & Model");
		TH.add("Section");
		TH.add("Created By Username ");
		TH.add("Remarks");
		return new ModelAndView(new ExcelComputingUserListReportView("L", TH, Heading, username), "userList", Excel);
	}

	@RequestMapping(value = "/Approve_ConsumableAssetsData2", method = RequestMethod.POST)
	public @ResponseBody List<String> Approve_ConsumableAssetsData2(String a, String status, HttpSession session) {
		String sus_no = session.getAttribute("roleSusNo").toString();
		String username = session.getAttribute("username").toString();
		List<String> list2 = new ArrayList<String>();
		list2.add(objDAO.approve_consumable_AssetsData(a, sus_no, status, username));
		return list2;
	}
	
	

	// issue consumables//
	@RequestMapping(value = "/issue_consumables", method = RequestMethod.GET)
	public ModelAndView issue_consumables(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg)
			throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException {

		Mmap.put("msg", msg);
		Mmap.put("getConsumables", it_comm.getConsumableList());
		Mmap.put("getSectionNameList", rolecontroller.getSectionNameList());
		 Mmap.put("getBudgetHeadList", it_comm.getBudgetHeadList());
		return new ModelAndView("IssueConsumableTiles", "IssueConsumablesCMD", new CONSUMABLES_CHILD());
	}
	@RequestMapping(value = "/Search_issue_consumable_1", method = RequestMethod.POST)
public ModelAndView Search_issue_consumable_1(ModelMap Mmap, HttpSession session,
		@RequestParam(value = "section1", required = false) String section,
		@RequestParam(value = "assets_type1", required = false) String assets_type,
		@RequestParam(value = "status1", required = false) String status,
		@RequestParam(value = "crv_no1", required = false) String crv_no) {
	String roleSusNo = session.getAttribute("roleSusNo").toString();
	String roleAccess = session.getAttribute("roleAccess").toString();
	Mmap.put("roleSusNo", roleSusNo);
	
	Mmap.put("section1", section);
	Mmap.put("assets_type1", assets_type);
	Mmap.put("status1", status);
	Mmap.put("crv_no1", crv_no);
	Mmap.put("getConsumables", it_comm.getConsumableList());
	Mmap.put("getServiceable_StateList", it_comm.getServiceable_StateList());
	Mmap.put("getSectionNameList", rolecontroller.getSectionNameList());
	return new ModelAndView("IssueConsumableTiles");
	
}
	@RequestMapping(value = "/getFilteredissueconsumable1", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getFilteredissueconsumable1(int startPage, int pageLength, String Search,
			String orderColunm, String orderType,String section,String assets_type,
			 String crv_no,String status, String assetcount, HttpSession sessionUserId) throws SQLException {

		return objDAO.Search_issue_consumable(startPage, pageLength, Search, orderColunm, orderType, section, assets_type,crv_no, status, sessionUserId);
	}

	@RequestMapping(value = "/getTotalissueconsumableCount1", method = RequestMethod.POST)
	public @ResponseBody long getTotalissueconsumableCount1(String Search, String orderColunm, String orderType,
			String section,String assets_type,
			 String crv_no,String status,
			HttpSession sessionUserId) throws SQLException {

		return objDAO.getissueconsumableCountList(Search, orderColunm, orderType, section, assets_type,crv_no, status, sessionUserId);
	}
	@RequestMapping(value = "/admin/Edit_issue_Consumables", method = RequestMethod.POST)
	public ModelAndView Edit_issue_Consumables(ModelMap Mmap, HttpSession session, HttpServletRequest request,
	        @RequestParam(value = "msg", required = false) String msg,
	        @RequestParam(value = "updateid", required = false) int id) {
	    
	    String view = "viewpage";
	    Mmap.addAttribute("screenname", view);
	    String roleSusNo = session.getAttribute("roleSusNo").toString();
	    String roleAccess = session.getAttribute("roleAccess").toString();
	    Mmap.put("roleSusNo", roleSusNo);

	    Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	    Transaction transaction = null;
	    CONSUMABLES_CHILD assetupd = null;
	    CONSUMABLES_MAIN assetupd2 = null;

	    try {
	        transaction = sessionHQL.beginTransaction();

	        // Fetch the CONSUMABLES_CHILD record
	        assetupd = (CONSUMABLES_CHILD) sessionHQL.get(CONSUMABLES_CHILD.class, id);
	        
	        if (assetupd != null) {
	            // Fetch the p_id from assetupd
	            int pId = assetupd.getP_id(); // Assuming getP_id() method exists

	            // Fetch the corresponding CONSUMABLES_MAIN record using p_id
	            assetupd2 = (CONSUMABLES_MAIN) sessionHQL.get(CONSUMABLES_MAIN.class, pId);
	        }

	        // Put values into the ModelMap
	        Mmap.put("issue_quantity", assetupd.getIssue_quantity());
	        if (assetupd2 != null) {
	            Mmap.put("crv_no", assetupd2.getCrv_no());
	        } else {
	            Mmap.put("crv_no", "Not Found"); // Handle case where CONSUMABLES_MAIN is not found
	        }

	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace(); // Log the exception
	    } finally {
	        sessionHQL.close();
	    }
		Mmap.put("getMakeName", it_comm.getMakeName());
		Mmap.put("getModelNameList", it_comm.getModelNameList());
		Mmap.put("YearOfProc", it_comm.getYearOfProc());
		Mmap.put("YearOfManufacturing", it_comm.getYearOfManufacturing());
		Mmap.put("getBudgetHeadList", it_comm.getBudgetHeadList());
		Mmap.put("hw_interfaceList", it_comm.hw_interfaceList());
		Mmap.put("getEthernet_portList", it_comm.getEthernet_portList());
		Mmap.put("getManagement_layerList", it_comm.getManagement_layerList());
		Mmap.put("getNetwork_featuresList", it_comm.getNetwork_featuresList());
		Mmap.put("getSectionNameList", rolecontroller.getSectionNameList());
		Mmap.put("getMaintainceAgencyList", it_comm.getMaintainceAgencyList());
		Mmap.put("getConsumables", it_comm.getConsumableList());
	    
	    return new ModelAndView("RequestConsumableTiles", "RequestConsumableCmd", assetupd);
	}
	@RequestMapping(value = "/Approve_Issue_Consumables", method = RequestMethod.POST)
	public @ResponseBody ModelAndView Approve_Issue_Consumables(@ModelAttribute("id3") int id, BindingResult result,
	        HttpServletRequest request, HttpSession sessionA, ModelMap model,
	        @RequestParam(value = "msg", required = false) String msg, Authentication authentication) {

	    String roleid = sessionA.getAttribute("roleid").toString();
	    Boolean val = roledao.ScreenRedirect("issue_consumables", roleid);
	    if (!val) {
	        return new ModelAndView("AccessTiles");
	    }
	    if (request.getHeader("Referer") == null) {
	        msg = "";
	    }

	    List<String> liststr = new ArrayList<String>();
	    Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	    Transaction tx = null;

	    try {
	        tx = sessionHQL.beginTransaction();
	        String username = sessionA.getAttribute("username").toString();

	        // Update query without the status condition for testing
	        String hqlUpdate = "update CONSUMABLES_CHILD set status=:status, modified_by=:modified_by, modified_date=:modified_date where id=:id";
	        int app = sessionHQL.createQuery(hqlUpdate)
	                .setInteger("status", 1)
	                .setString("modified_by", username)
	                .setDate("modified_date", new Date())
	                .setInteger("id", id)
	                .executeUpdate();

	        if (app > 0) {
	            liststr.add("Approve Successfully.");
	        } else {
	            liststr.add("Approve UNSuccessfully.");
	        }

	        tx.commit();
	    } catch (Exception e) {
	        if (tx != null) {
	            tx.rollback(); // Rollback in case of an error
	        }
	        e.printStackTrace(); // Log the exception
	        liststr.add("Error occurred: " + e.getMessage());
	    } finally {
	        sessionHQL.close();
	    }
	    model.put("msg", liststr.get(0));
	    return new ModelAndView("redirect:issue_consumables");
	}
	
	
	
	@RequestMapping(value = "/Reject_Issue_Consumables", method = RequestMethod.POST)
	public @ResponseBody ModelAndView Reject_Issue_Consumables(@ModelAttribute("id4") int id, BindingResult result,
	        HttpServletRequest request, HttpSession sessionA, ModelMap model,
	        @RequestParam(value = "msg", required = false) String msg, Authentication authentication) {

//	   // String roleid = sessionA.getAttribute("roleid").toString();
//	    Boolean val = roledao.ScreenRedirect("issue_consumables", roleid);
//	    if (!val) {
//	        return new ModelAndView("AccessTiles");
//	    }
//	    if (request.getHeader("Referer") == null) {
//	        msg = "";
//	    }

	    List<String> liststr = new ArrayList<String>();
	    Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	    Transaction tx = null;

	    try {
	        tx = sessionHQL.beginTransaction();
	        String username = sessionA.getAttribute("username").toString();

	        // Update query without the status condition for testing
	        String hqlUpdate = "update CONSUMABLES_CHILD set status=:status, modified_by=:modified_by, modified_date=:modified_date where id=:id";
	        int app = sessionHQL.createQuery(hqlUpdate)
	                .setInteger("status", 3)
	                .setString("modified_by", username)
	                .setDate("modified_date", new Date())
	                .setInteger("id", id)
	                .executeUpdate();

	        if (app > 0) {
	            liststr.add("Reject Successfully.");
	        } else {
	            liststr.add("Reject UNSuccessfully.");
	        }

	        tx.commit();
	    } catch (Exception e) {
	        if (tx != null) {
	            tx.rollback(); // Rollback in case of an error
	        }
	        e.printStackTrace(); // Log the exception
	        liststr.add("Error occurred: " + e.getMessage());
	    } finally {
	        sessionHQL.close();
	    }
	    model.put("msg", liststr.get(0));
	    return new ModelAndView("redirect:issue_consumables");
	}


	
	@RequestMapping(value = "/Delete_issue_consumables", method = RequestMethod.POST)
	public ModelAndView Delete_issue_consumables(@ModelAttribute("id1") String id, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session1,
			@RequestParam(value = "msg", required = false) String msg) {
//		String roleid = session1.getAttribute("roleid").toString();
//		Boolean val = roledao.ScreenRedirect("Search_ConsumableUrl", roleid);
//		if (val == false) {
//			return new ModelAndView("AccessTiles");
//		}
//		if (request.getHeader("Referer") == null) {
//			msg = "";
//			//return new ModelAndView("redirect:bodyParameterNotAllow");
//		}
		List<String> liststr = new ArrayList<String>();
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			String hqlUpdate = "";
			int app;
			hqlUpdate = "delete from CONSUMABLES_CHILD where id=:id";
			app = session.createQuery(hqlUpdate).setInteger("id", Integer.parseInt(id)).executeUpdate();
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
		return new ModelAndView("redirect:issue_consumables");
	}
	
	///for integratio n 07-02-25
	
	
	
//	@RequestMapping(value = "/admin/Excel_Issue_Consumables_Assets_Search", method = RequestMethod.POST)
//	public ModelAndView Excel_Issue_Consumables_Assets_Search(ModelMap Mmap, HttpSession session,
//			@RequestParam(value = "msg", required = false) String msg,
//			@RequestParam(value = "section1", required = false) String section,
//			@RequestParam(value = "assets_type1", required = false) String assets_type,
//			@RequestParam(value = "crv_no1", required = false) String crv_no,
//			@RequestParam(value = "assetcount1", required = false) String assetcount,
//			@RequestParam(value = "make_name1", required = false) String make_name,
//			@RequestParam(value = "model_name1", required = false) String model_name,
//			@RequestParam(value = "status1", required = false) String status
//			) {
//
//		ArrayList<ArrayList<String>> Excel = objDAO.Excel_issue_Consumables_Assets_Search(session, section,
//				assets_type, crv_no, assetcount, make_name, model_name,status);
//
//		String username = session.getAttribute("username").toString();
//		Mmap.put("msg", msg);
//		String Heading = "SEARCH CONSUMABLES ASSESTS";
//		Date date = new Date();
//		String foot = " Report Generated On " + new SimpleDateFormat("dd-MM-yyyy").format(date);
//
//		List<String> TH = new ArrayList<String>();
//		TH.add("Section");
//		TH.add("Consumable Type");
//		TH.add("Make name");
//		TH.add("Model name");
//		TH.add("Total Quantity");
//		TH.add("CRV No");
//		return new ModelAndView(new ExcelComputingUserListReportView("L", TH, Heading, username), "userList", Excel);
//	}
//	
	
	
	
	
	@RequestMapping(value = "/RequestConsumableAction", method = RequestMethod.POST)
	public ModelAndView RequestConsumableAction(@ModelAttribute("RequestConsumableCmd") CONSUMABLES_CHILD consumable,
	              @RequestParam(value = "msg", required = false) String msg1,
	              @RequestParam(value = "issue_quantity2", required = false) String issue_quantityStr,
	              @RequestParam(value = "p_id", required = false) String p_idStr,
	              @RequestParam(value = "warrantycheck", required = false) String warrantycheck,
	              @RequestParam(value = "assetcount2", required = false) int assetcount2,
	              ModelMap model, HttpSession session) throws ParseException {

	      int id = consumable.getId() > 0 ? consumable.getId() : 0;
	      Date date = new Date();
	      String rediurl;

	      if (id == 0) {
	              rediurl = "redirect:request_consumables";
	      } else {
	              rediurl = "redirect:issue_consumables";
	      }

	      Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	      Transaction tx = null;
	      String username = session.getAttribute("username").toString();

	      try {
	              tx = sessionHQL.beginTransaction();
	              CONSUMABLES_CHILD T_asset = new CONSUMABLES_CHILD();

	              // Check if issue_quantityStr is empty or null
	              if (issue_quantityStr == null || issue_quantityStr.trim().isEmpty()) {
	                      model.put("msg", "Please Enter Issue Quantity");
	                      return new ModelAndView(rediurl);
	              }

	              int issue_quantity;
	              try {
	                      issue_quantity = Integer.parseInt(issue_quantityStr);
	              } catch (NumberFormatException e) {
	                      model.put("msg", "Invalid Issue Quantity. Please enter a valid number.");
	                      return new ModelAndView(rediurl);
	              }

	              if (issue_quantity > assetcount2) {
	                      model.put("msg", "Issue Quantity cannot be greater than Total Quantity.");
	                      return new ModelAndView(rediurl);
	              }

	              // Handle p_id
	              int p_id = 0; // Default value
	              if (p_idStr != null && !p_idStr.trim().isEmpty()) {
	                      try {
	                              p_id = Integer.parseInt(p_idStr);
	                      } catch (NumberFormatException e) {
	                              model.put("msg", "Invalid Product ID. Please enter a valid number.");
	                              return new ModelAndView(rediurl);
	                      }
	              }

	              // Check the total issued quantity for the given p_id
	              Long totalIssuedQuantity = (Long) sessionHQL.createQuery("SELECT SUM(c.issue_quantity) FROM CONSUMABLES_CHILD c WHERE c.p_id =:p_id AND c.status = '0'")
	                              .setParameter("p_id", p_id)
	                              .uniqueResult();

	              // If totalIssuedQuantity is null, treat it as 0
	              totalIssuedQuantity = (totalIssuedQuantity != null) ? totalIssuedQuantity : 0;

	              // Validate if the new issue quantity exceeds the available asset count
	              if (totalIssuedQuantity + issue_quantity > assetcount2) {
	                      int excessAmount = (int) ((totalIssuedQuantity + issue_quantity) - assetcount2);
	                      model.put("msg", "Total issued quantity exceeds available asset count by " + excessAmount + ".");
	                      return new ModelAndView(rediurl);
	              }

	              if (id == 0) {
	                      // Create new entry
	                      T_asset.setIssue_quantity(issue_quantity);
	                      T_asset.setWarrantycheck(warrantycheck);
	                      T_asset.setCreated_by(username);
	                      T_asset.setCreated_dt(date);
	                      T_asset.setP_id(p_id);
	                      T_asset.setRequest_status("0");
	                      sessionHQL.save(T_asset);
	                      tx.commit();
	                      model.put("msg", "Data Saved Successfully.");
	              } else {
	                      // Update existing entry
	                      T_asset = sessionHQL.get(CONSUMABLES_CHILD.class, id);
	                      if (T_asset != null) {
	                              T_asset.setIssue_quantity(issue_quantity);
	                              T_asset.setWarrantycheck(warrantycheck);
	                              T_asset.setModified_by(username);
	                              T_asset.setModified_date(date);
	                              sessionHQL.update(T_asset);
	                              tx.commit();
	                              model.put("msg", "Data Updated Successfully.");
	                      } else {
	                              model.put("msg", "Consumable not found for the given ID.");
	                      }
	              }
	      } catch (Exception e) {
	              if (tx != null) {
	                      tx.rollback();
	              }
	              model.put("msg", "Error saving data: " + e.getMessage());
	      } finally {
	              sessionHQL.close();
	      }

	      return new ModelAndView(rediurl);
	}

	
	//////////////////////////////////Integrated for issue Consumables 07/02/2025/////////////////////////////////////
	
	
	@RequestMapping(value = "/admin/Excel_Issue_Consumables_Assets_Search", method = RequestMethod.POST)
	public ModelAndView Excel_Issue_Consumables_Assets_Search(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "section3", required = false) String section,
			@RequestParam(value = "assets_type3", required = false) String assets_type,
			@RequestParam(value = "crv_no3", required = false) String crv_no,
			@RequestParam(value = "status3", required = false) String status
			) {

		ArrayList<ArrayList<String>> Excel = objDAO.Excel_issue_Consumables_Assets_Search(session,section,
				assets_type, crv_no,status);

		

		
		String username = session.getAttribute("username").toString();
		Mmap.put("msg", msg);
		String Heading = "SEARCH CONSUMABLES ASSESTS";
		Date date = new Date();
		String foot = " Report Generated On " + new SimpleDateFormat("dd-MM-yyyy").format(date);

		List<String> TH = new ArrayList<String>();
		TH.add("Section");
		TH.add("Consumable Type");
		TH.add("Make name");
		TH.add("Model name");
		TH.add("Total Quantity");
		TH.add("CRV No");
		return new ModelAndView(new ExcelComputingUserListReportView("L", TH, Heading, username), "userList", Excel);
	}
	
	
	
	
	
}
