package com.controller.itasset.InterUnitTrnsf;

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

import com.controller.validation.ValidationController;
import com.dao.Assets.interUnitTransf_DAO;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.models.assets.It_Asset_Inter_Unit_Trnsf_Comp;
import com.models.assets.It_Asset_Inter_Unit_Trnsf_Perif;
import com.persistance.util.HibernateUtil;



import java.security.InvalidAlgorithmParameterException;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;


import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;


import org.apache.commons.codec.binary.Base64;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class InterUnitTransferController {
	
	@Autowired
	private RoleBaseMenuDAO roledao;
	@Autowired
	private interUnitTransf_DAO iut;
	
	ValidationController valid = new ValidationController();
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	@RequestMapping(value = "/admin/AssetsInterUnitTransfer", method = RequestMethod.GET)
	public ModelAndView AssetsInterUnitTransfer(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
			HttpSession sessionUserId) {
		Boolean val = roledao.ScreenRedirect("AssetsInterUnitTransfer", sessionUserId.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String roleType = sessionUserId.getAttribute("roleType").toString();
		Mmap.put("msg", msg);
		Mmap.put("roleSusNo", roleSusNo);
		Mmap.put("roleType", roleType);
		return new ModelAndView("InterUnitTrnsfTiles");
	}
	
	@RequestMapping(value = "/computingTransfer_action", method = RequestMethod.POST)

	public @ResponseBody String computingTransfer_action(HttpSession session,String from_sus,String to_sus,String machine_no,
			String machine_id) {
		
		if (machine_no == null || machine_no.equals("")) {
			return "Please Select Machine No.";
		}			
//		if (!valid.isOnlyAlphabetNumericSpaceNot(machine_no)) {
//			return valid.isOnlyAlphabetNumericSpaceNotMSG + " Machine No ";
//		}
		if (from_sus.equals("") || from_sus == "" || from_sus == null) {
			return "Please Enter From Unit SUS No";
		}	
		if (!valid.isOnlyAlphabetNumeric(from_sus)) {
		 	return valid.isOnlyAlphabetNumericMSG + "From Unit Sus No";	
		}
		if (from_sus != "") {
			if (!valid.SusNoLength(from_sus)) {
				return valid.SusNoMSG;
			}
		}
		if (from_sus.equals("") || to_sus == "" || to_sus == null) {
			return "Please Enter To Unit SUS No";
		}	
		if (!valid.isOnlyAlphabetNumeric(to_sus)) {
		 	return valid.isOnlyAlphabetNumericMSG + "To Unit Sus No";	
		}
		if (to_sus != "") {
			if (!valid.SusNoLength(to_sus)) {
				return valid.SusNoMSG;
			}
		}

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String username = session.getAttribute("username").toString();
		
		String[] machine_noList = machine_no.split(",");
		String[] machine_idList = machine_id.split(",");
		String msg ="";
		ArrayList<String> msgex = new ArrayList<String>();
		for (int i = 0; i < machine_idList.length; i++) {
			Query q0 = sessionHQL.createQuery("select count(id) from It_Asset_Inter_Unit_Trnsf_Comp where status=:status AND machine_id=:machine_id");
			q0.setParameter("status", 0);  
			 
			q0.setParameter("machine_id", Integer.parseInt(machine_idList[i]));  
			Long c = (Long) q0.uniqueResult();
			
			
			if (c==0) {
				It_Asset_Inter_Unit_Trnsf_Comp tcomp = new It_Asset_Inter_Unit_Trnsf_Comp();
				tcomp.setFrom_sus(from_sus);
				tcomp.setTo_sus(to_sus);
				tcomp.setMachine_no(machine_noList[i]);
				tcomp.setMachine_id(Integer.parseInt(machine_idList[i]));
				tcomp.setStatus(0);
				tcomp.setCreated_by(username);
				tcomp.setCreated_dt(new Date());
				
				int id = (int) sessionHQL.save(tcomp);
				msg = String.valueOf(id);
			}
			else {
				msgex.add(String.valueOf(machine_noList[i])) ;
			}
		}
		tx.commit();
		if (msgex.size()>0) {
			msg = "Machine No: "+msgex+" Already In Pending State Transfered To The Unit";
		}
		return msg;
	}
	
	@RequestMapping(value = "/periferalTransfer_action", method = RequestMethod.POST)

	public @ResponseBody String periferalTransfer_action(HttpSession session,String from_sus,String to_sus,String machine_no,
			String machine_id) {

		if (machine_no == null || machine_no.equals("")) {
			return "Please Select Machine No.";
		}			
//		if (!valid.isOnlyAlphabetNumericSpaceNot(machine_no)) {
//			return valid.isOnlyAlphabetNumericSpaceNotMSG + " Machine No ";
//		}
		if (from_sus.equals("") || from_sus == "" || from_sus == null) {
			return "Please Enter From Unit SUS No";
		}	
		if (!valid.isOnlyAlphabetNumeric(from_sus)) {
		 	return valid.isOnlyAlphabetNumericMSG + "From Unit Sus No";	
		}
		if (from_sus != "") {
			if (!valid.SusNoLength(from_sus)) {
				return valid.SusNoMSG;
			}
		}
		if (from_sus.equals("") || to_sus == "" || to_sus == null) {
			return "Please Enter To Unit SUS No";
		}	
		if (!valid.isOnlyAlphabetNumeric(to_sus)) {
		 	return valid.isOnlyAlphabetNumericMSG + "To Unit Sus No";	
		}
		if (to_sus != "") {
			if (!valid.SusNoLength(to_sus)) {
				return valid.SusNoMSG;
			}
		}
		
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String username = session.getAttribute("username").toString();
		
		String[] machine_noList = machine_no.split(",");
		String[] machine_idList = machine_id.split(",");
		String msg ="";
		ArrayList<String> msgex = new ArrayList<String>();
		for (int i = 0; i < machine_idList.length; i++) {
			Query q0 = sessionHQL.createQuery("select count(id) from It_Asset_Inter_Unit_Trnsf_Perif where status=:status AND machine_id=:machine_id");
			q0.setParameter("status", 0);  
			 
			q0.setParameter("machine_id", Integer.parseInt(machine_idList[i]));  
			Long c = (Long) q0.uniqueResult();
			
			if (c==0) {
				It_Asset_Inter_Unit_Trnsf_Perif tperif = new It_Asset_Inter_Unit_Trnsf_Perif();
				tperif.setFrom_sus(from_sus);
				tperif.setTo_sus(to_sus);
				tperif.setMachine_no(machine_noList[i]);
				tperif.setMachine_id(Integer.parseInt(machine_idList[i]));
				tperif.setStatus(0);
				tperif.setCreated_by(username);
				tperif.setCreated_dt(new Date());
				
				int id = (int) sessionHQL.save(tperif);
				msg = String.valueOf(id);
				
			}
			else {
				msgex.add(String.valueOf(machine_noList[i])) ;
			}
		}
		
		tx.commit();
		if (msgex.size()>0) {
			msg = "Machine No: "+msgex+" Already In Pending State Transfered To The Unit";
		}
		
		return msg;
	}
	
	@RequestMapping(value = "/admin/AssetsInterUnitTransferSearch", method = RequestMethod.GET)
	public ModelAndView AssetsInterUnitTransferSearch(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request,
			HttpSession sessionUserId) {
		Boolean val = roledao.ScreenRedirect("AssetsInterUnitTransferSearch", sessionUserId.getAttribute("roleid").toString());
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		Mmap.put("msg", msg);
		Mmap.put("roleSusNo", roleSusNo);
		return new ModelAndView("InterUnitTrnsfSearchTiles");
	}
	
	@RequestMapping(value = "/Search_Computing_Transfer", method = RequestMethod.POST)

	public @ResponseBody List<Map<String, Object>> Search_Computing_Transfer(HttpSession sessionA, String from_sus_no, String to_sus_no,String machine_no, String status) {

		String roleType = sessionA.getAttribute("roleType").toString();
		return iut.GetAssetTransfDataComp(roleType, from_sus_no, to_sus_no, machine_no, status);
	}
	
	@RequestMapping(value = "/Search_Peripheral_Transfer", method = RequestMethod.POST)

	public @ResponseBody List<Map<String, Object>> Search_Peripheral_Transfer(HttpSession sessionA, String from_sus_no, String to_sus_no,String machine_no, String status) {

		String roleType = sessionA.getAttribute("roleType").toString();
		return iut.GetAssetTransfDataPerif(roleType, from_sus_no, to_sus_no, machine_no, status);
	}
	
	@RequestMapping(value = "/Approve_AssetTransfer", method = RequestMethod.POST)
	public  ModelAndView Approve_AssetTransfer(@ModelAttribute("idApp") int id, BindingResult result,
			HttpServletRequest request,
			HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "machine_idApp", required = false) String machine_idApp,
			@RequestParam(value = "catApp", required = false) String catApp,
			@RequestParam(value = "to_susApp", required = false) String to_susApp,
			
			Authentication authentication) {
//		String roleid = sessionA.getAttribute("roleid").toString();
//		Boolean val = roledao.ScreenRedirect("Approve_AssetTransfer", roleid);
//		if (val == false) {
//			return new ModelAndView("AccessTiles");
//		}
//		if (request.getHeader("Referer") == null) {
//			msg = "";
//			return new ModelAndView("redirect:bodyParameterNotAllow");
//		}
		List<String> liststr = new ArrayList<String>();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		String roleType = sessionA.getAttribute("roleType").toString();
		String username = sessionA.getAttribute("username").toString();
		String reciver_sus_no="";
		
		
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate1 = "";
		String hqlUpdate2 = "";
		if (catApp.equals("computing")) {
			hqlUpdate1 += "update It_Asset_Inter_Unit_Trnsf_Comp ";
			hqlUpdate2 += "update Assets_Main ";
		}
		else if (catApp.equals("peripherals")) {
			hqlUpdate1 += "update It_Asset_Inter_Unit_Trnsf_Perif ";
			hqlUpdate2 += "update It_Asset_Peripherals ";
		}

		hqlUpdate1 += " set status=:status,modified_by=:modified_by,modified_dt=:modified_dt  where id=:id";
	
		int app = sessionHQL.createQuery(hqlUpdate1).setInteger("status", 1).setString("modified_by", username)
				.setDate("modified_dt", new Date())
				.setInteger("id", id).executeUpdate();
		
			hqlUpdate2 += " set sus_no=:sus_no where id=:id";
			int app2 = sessionHQL.createQuery(hqlUpdate2)
					.setString("sus_no", to_susApp)
					.setInteger("id", Integer.parseInt(machine_idApp)).executeUpdate();
		tx.commit();
		sessionHQL.close();
		if (app > 0) {
			liststr.add("Approved Successfully.");
			
		} else {
			liststr.add("Approved Not Successfully.");
		}
		model.put("msg", liststr.get(0));
		model.put("to_susApp",to_susApp);
		model.put("catApp", catApp);
		return new ModelAndView("redirect:AssetsInterUnitTransferSearch");
	}
	
	@RequestMapping(value = "/Reject_AssetTransfer", method = RequestMethod.POST)
	public  ModelAndView Reject_AssetTransfer(@ModelAttribute("idRej") int id, BindingResult result,
			HttpServletRequest request,
			HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "catRej", required = false) String catRej,
			
			Authentication authentication) {
//		String roleid = sessionA.getAttribute("roleid").toString();
//		Boolean val = roledao.ScreenRedirect("Reject_AssetTransfer", roleid);
//		if (val == false) {
//			return new ModelAndView("AccessTiles");
//		}
//		if (request.getHeader("Referer") == null) {
//			msg = "";
//			return new ModelAndView("redirect:bodyParameterNotAllow");
//		}
		List<String> liststr = new ArrayList<String>();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		String roleType = sessionA.getAttribute("roleType").toString();
		String username = sessionA.getAttribute("username").toString();
		
		
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		String hqlUpdate1 = "";
		if (catRej.equals("computing")) {
			hqlUpdate1 += "delete It_Asset_Inter_Unit_Trnsf_Comp ";
		}
		else if (catRej.equals("peripherals")) {
			hqlUpdate1 += "delete It_Asset_Inter_Unit_Trnsf_Perif ";
		}

		hqlUpdate1 += "   where id=:id";
	
		int app = sessionHQL.createQuery(hqlUpdate1)
				.setInteger("id", id).executeUpdate();
		
		tx.commit();
		sessionHQL.close();
		if (app > 0) {
			liststr.add("Rejected Successfully.");
			
		} else {
			liststr.add("Reject Not Successfully.");
		}
		model.put("msg", liststr.get(0));
		model.put("catRej", catRej);
		return new ModelAndView("redirect:AssetsInterUnitTransferSearch");
	}
	
	@RequestMapping(value = "/GetMachine_noDataComp", method = RequestMethod.POST)

	public @ResponseBody List<Map<String, Object>> GetMachine_noDataComp(String machine_no) {

		return iut.GetMachine_noDataComp(machine_no);
	}
	
	@RequestMapping(value = "/GetMachine_noDataPerif", method = RequestMethod.POST)

	public @ResponseBody List<Map<String, Object>> GetMachine_noDataPerif(String machine_no) {

		return iut.GetMachine_noDataPerif(machine_no);
	}
	
	@RequestMapping(value = "/getmachine_no_CompListToTransf", method = RequestMethod.POST)

	public @ResponseBody List<Map<String, Object>> getmachine_no_CompListToTransf(String sus_no) {

		return iut.getmachine_no_CompListToTransf(sus_no);
	}
	
	@RequestMapping(value = "/getmachine_no_perifListToTransf", method = RequestMethod.POST)

	public @ResponseBody List<Map<String, Object>> getmachine_no_perifListToTransf(String sus_no) {

		return iut.getmachine_no_perifListToTransf(sus_no);
	}
	@RequestMapping(value = "/getSUSNoList_fromonlyforitassiut", method = RequestMethod.POST)
	public @ResponseBody List<String> getSUSNoList_fromonlyforitassiut(HttpSession sessionA, String sus_no) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();
		String roleFormationNo = sessionA.getAttribute("roleFormationNo").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		String roleArmCode = sessionA.getAttribute("roleArmCode").toString();

		///bisag 181022 v2(remove status sus No active for coverted sus no)
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
	
	

				q = session.createQuery(
						"select distinct sus_no from Orbat_Unt_Dtl  "
						+ "where  sus_no in (select sus_no from Assets_Main where status='1') ")
						.setMaxResults(10);

			
	

		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();

		String enckey = hex_asciiDao.getAlphaNumericString();
		Cipher c = null;
		try {
			c = hex_asciiDao.EncryptionSHA256Algo(sessionA, enckey);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		List<String> FinalList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			byte[] encCode = null;
			try {
				encCode = c.doFinal(list.get(i).getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
			String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			FinalList.add(base64EncodedEncryptedCode);
		}
		FinalList.add(enckey + "4bsjyg==");
		return FinalList;
	}
	
	@RequestMapping(value = "/getUnitnameList_fromonlyforitassiut", method = RequestMethod.POST)
	public @ResponseBody List<String> getUnitnameList_fromonlyforitassiut(HttpSession sessionA, String sus_no) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();
		String roleFormationNo = sessionA.getAttribute("roleFormationNo").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		String roleArmCode = sessionA.getAttribute("roleArmCode").toString();

		///bisag 181022 v2(remove status sus No active for coverted sus no)
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
	
	

				q = session.createQuery(
						"select distinct unit_name from Orbat_Unt_Dtl  "
						+ "where  sus_no in (select sus_no from Assets_Main where status='1') ")
						.setMaxResults(10);

			
	

		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();

		String enckey = hex_asciiDao.getAlphaNumericString();
		Cipher c = null;
		try {
			c = hex_asciiDao.EncryptionSHA256Algo(sessionA, enckey);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		List<String> FinalList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			byte[] encCode = null;
			try {
				encCode = c.doFinal(list.get(i).getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
			String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			FinalList.add(base64EncodedEncryptedCode);
		}
		FinalList.add(enckey + "4bsjyg==");
		return FinalList;
	}
	@RequestMapping(value = "/getSUSNoList_fromonlyforPeripheralsiut", method = RequestMethod.POST)
	public @ResponseBody List<String> getSUSNoList_fromonlyforPeripheralsiut(HttpSession sessionA, String sus_no) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();
		String roleFormationNo = sessionA.getAttribute("roleFormationNo").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		String roleArmCode = sessionA.getAttribute("roleArmCode").toString();

		///bisag 181022 v2(remove status sus No active for coverted sus no)
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
	
	

				q = session.createQuery(
						"select distinct sus_no from Orbat_Unt_Dtl  "
						+ "where  sus_no in (select sus_no from It_Asset_Peripherals where status='1') ")
						.setMaxResults(10);

			
	

		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();

		String enckey = hex_asciiDao.getAlphaNumericString();
		Cipher c = null;
		try {
			c = hex_asciiDao.EncryptionSHA256Algo(sessionA, enckey);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		List<String> FinalList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			byte[] encCode = null;
			try {
				encCode = c.doFinal(list.get(i).getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
			String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			FinalList.add(base64EncodedEncryptedCode);
		}
		FinalList.add(enckey + "4bsjyg==");
		return FinalList;
	}
	
	@RequestMapping(value = "/getUnitnameList_fromonlyforPeripheralsiut", method = RequestMethod.POST)
	public @ResponseBody List<String> getUnitnameList_fromonlyforPeripheralsiut(HttpSession sessionA, String sus_no) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();
		String roleFormationNo = sessionA.getAttribute("roleFormationNo").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		String roleArmCode = sessionA.getAttribute("roleArmCode").toString();

		///bisag 181022 v2(remove status sus No active for coverted sus no)
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
	
	

				q = session.createQuery(
						"select distinct unit_name from Orbat_Unt_Dtl  "
						+ "where  sus_no in (select sus_no from It_Asset_Peripherals where status='1') ")
						.setMaxResults(10);

			
	

		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();

		String enckey = hex_asciiDao.getAlphaNumericString();
		Cipher c = null;
		try {
			c = hex_asciiDao.EncryptionSHA256Algo(sessionA, enckey);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		List<String> FinalList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			byte[] encCode = null;
			try {
				encCode = c.doFinal(list.get(i).getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
			String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			FinalList.add(base64EncodedEncryptedCode);
		}
		FinalList.add(enckey + "4bsjyg==");
		return FinalList;
	}
	

@RequestMapping(value = "/getSUSNoList_Active_or_Inactive_it", method = RequestMethod.POST)
	public @ResponseBody List<String> getSUSNoList_Active_or_Inactive_it(HttpSession sessionA, String sus_no) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();
		String roleFormationNo = sessionA.getAttribute("roleFormationNo").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		String roleArmCode = sessionA.getAttribute("roleArmCode").toString();

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		if (roleAccess.equals("MISO") || roleAccess.equals("Depot")) {
			
			if (roleSubAccess.equals("Medical")) {
				String[] medicalUnitPrifix = (String[]) sessionA.getAttribute("medicalUnitPrifix");
				q = session.createQuery("select sus_no from Orbat_Unt_Dtl where upper(sus_no) like :sus_no and upper(status_sus_no) in ('INACTIVE','ACTIVE') and substring(sus_no, 1,4) in (:med) order by SUBSTRING(sus_no,1,7) ").setMaxResults(10);
				q.setParameter("sus_no", sus_no.toUpperCase()+"%");
				q.setParameterList("med", medicalUnitPrifix);
			}else {
				q = session.createQuery("select sus_no from Orbat_Unt_Dtl where upper(sus_no) like :sus_no and upper(status_sus_no) in ('INACTIVE','ACTIVE') order by SUBSTRING(sus_no,1,7)").setMaxResults(10);
				q.setParameter("sus_no", sus_no.toUpperCase() + "%");
			}
		}
		else {
			q = session.createQuery("select sus_no from Orbat_Unt_Dtl where upper(sus_no) like :sus_no and upper(status_sus_no) in ('INACTIVE','ACTIVE') order by SUBSTRING(sus_no,1,7)").setMaxResults(10);
			q.setParameter("sus_no", sus_no.toUpperCase() + "%");
		}
		
		if(roleAccess.equals("HeadQuarter")) {
			q = session.createQuery("select sus_no from Orbat_Unt_Dtl where upper(sus_no) like :sus_no and upper(status_sus_no) in ('INACTIVE','ACTIVE') order by SUBSTRING(sus_no,1,7)").setMaxResults(10);
			q.setParameter("sus_no", sus_no.toUpperCase() + "%");
		}
		if (roleAccess.equals("Formation")) {
			if (roleSubAccess.equals("Command")) {
				roleFormationNo = String.valueOf(roleFormationNo.charAt(0));
				q = session.createQuery("select sus_no from Orbat_Unt_Dtl where upper(sus_no) like :sus_no and upper(status_sus_no) in ('INACTIVE','ACTIVE') and  upper(form_code_control) like :roleFormationNo order by SUBSTRING(sus_no,1,7)").setMaxResults(10);
				q.setParameter("roleFormationNo", roleFormationNo + "%");
				q.setParameter("sus_no", sus_no.toUpperCase() + "%");
			}
			if (roleSubAccess.equals("Corps")) {
				roleFormationNo = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))+ String.valueOf(roleFormationNo.charAt(2));
				q = session.createQuery("select sus_no from Orbat_Unt_Dtl where upper(sus_no) like :sus_no and upper(status_sus_no) in ('INACTIVE','ACTIVE')and  upper(form_code_control) like :roleFormationNo order by SUBSTRING(sus_no,1,7)")
						.setMaxResults(10);
				q.setParameter("roleFormationNo", roleFormationNo + "%");
				q.setParameter("sus_no", sus_no.toUpperCase() + "%");
			}
			if (roleSubAccess.equals("Division")) {
				roleFormationNo = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))
						+ String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3))
						+ String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
				q = session.createQuery("select sus_no from Orbat_Unt_Dtl where upper(sus_no) like :sus_no and upper(status_sus_no) in ('INACTIVE','ACTIVE') and  upper(form_code_control) like :roleFormationNo order by SUBSTRING(sus_no,1,7)").setMaxResults(10);
				q.setParameter("roleFormationNo", roleFormationNo + "%");
				q.setParameter("sus_no", sus_no.toUpperCase() + "%");
			}
			if (roleSubAccess.equals("Brigade")) {
				q = session.createQuery("select sus_no from Orbat_Unt_Dtl where upper(sus_no) like :sus_no and upper(status_sus_no) in ('INACTIVE','ACTIVE') and  upper(form_code_control) like :roleFormationNo order by SUBSTRING(sus_no,1,7)").setMaxResults(10);
				q.setParameter("roleFormationNo", roleFormationNo + "%");
				q.setParameter("sus_no", sus_no.toUpperCase() + "%");
			}
		}
		if (roleAccess.equals("Unit") ) {
			sus_no = roleSusNo;
			q = session.createQuery("select sus_no from Orbat_Unt_Dtl where upper(sus_no) like :sus_no and upper(status_sus_no) in ('INACTIVE','ACTIVE') order by SUBSTRING(sus_no,1,7)").setMaxResults(10);
			q.setParameter("sus_no", sus_no.toUpperCase() + "%");
		}
		if (roleAccess.equals("Line_dte")) {
			q = session.createQuery("select sus_no from Orbat_Unt_Dtl where upper(sus_no) like :sus_no and arm_code in (select arm_code from TB_ORBAT_LINE_DTE where line_dte_sus=:roleSusNo ) and upper(status_sus_no) in ('INACTIVE','ACTIVE') order by SUBSTRING(sus_no,1,7)").setMaxResults(10);
			q.setParameter("roleSusNo", roleSusNo);
			q.setParameter("sus_no", sus_no.toUpperCase() + "%");
		}
		if(roleAccess.equals("DG")) {
			if(roleSubAccess.equals("Medical")) {
				
				String[] medicalUnitPrifix = (String[]) sessionA.getAttribute("medicalUnitPrifix");
				q = session.createQuery("select sus_no from Orbat_Unt_Dtl where upper(sus_no) like :sus_no and upper(status_sus_no) in ('INACTIVE','ACTIVE') and substring(sus_no, 1,4) in (:med) order by SUBSTRING(sus_no,1,7) ").setMaxResults(10);
				q.setParameter("sus_no", sus_no.toUpperCase()+"%");
				q.setParameterList("med", medicalUnitPrifix);
			}
		}
		
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();

		String enckey = hex_asciiDao.getAlphaNumericString();
		Cipher c = null;
		try {
			c = hex_asciiDao.EncryptionSHA256Algo(sessionA, enckey);
		} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		List<String> FinalList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			byte[] encCode = null;
			try {
				encCode = c.doFinal(list.get(i).getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				e.printStackTrace();
			}
			String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			FinalList.add(base64EncodedEncryptedCode);
		}
		FinalList.add(enckey + "4bsjyg==");
		return FinalList;
	}
	

}