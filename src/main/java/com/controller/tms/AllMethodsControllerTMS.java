package com.controller.tms;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class AllMethodsControllerTMS {

	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();

	@RequestMapping(value = "/getTargetSUSNoList", method = RequestMethod.POST)
	public @ResponseBody List<String> getTargetSUSNoList(HttpSession sessionA, String sus_no) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();
		String roleFormationNo = sessionA.getAttribute("roleFormationNo").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		String roleArmCode = sessionA.getAttribute("roleArmCode").toString();

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		if (roleAccess.equals("MISO") || roleAccess.equals("Depot") || roleAccess.equals("DGMS"))   {
			
			if (roleSubAccess.equals("Medical")) {
				String[] medicalUnitPrifix = (String[]) sessionA.getAttribute("medicalUnitPrifix");
				q = session.createQuery("select sus_no from Orbat_Unt_Dtl where upper(sus_no) like :sus_no and status_sus_no  = 'Active' and substring(sus_no, 1,4) in (:med) order by SUBSTRING(sus_no,1,7) ").setMaxResults(10);
				q.setParameter("sus_no", sus_no.toUpperCase()+"%");
				q.setParameterList("med", medicalUnitPrifix);
			}else {
				q = session.createQuery("select sus_no from Orbat_Unt_Dtl where upper(sus_no) like :sus_no and status_sus_no  = 'Active' order by SUBSTRING(sus_no,1,7)").setMaxResults(10);
				q.setParameter("sus_no", sus_no.toUpperCase() + "%");
			}
		}
		
		
		if(roleAccess.equals("HeadQuarter")) {
			q = session.createQuery("select sus_no from Orbat_Unt_Dtl where upper(sus_no) like :sus_no and status_sus_no  = 'Active' order by SUBSTRING(sus_no,1,7)").setMaxResults(10);
			q.setParameter("sus_no", sus_no.toUpperCase() + "%");
		}
		if (roleAccess.equals("Formation")) {
			if (roleSubAccess.equals("Command")) {
				roleFormationNo = String.valueOf(roleFormationNo.charAt(0));
				q = session.createQuery("select sus_no from Orbat_Unt_Dtl where upper(sus_no) like :sus_no and status_sus_no  = 'Active' and  upper(form_code_control) like :roleFormationNo order by SUBSTRING(sus_no,1,7)").setMaxResults(10);
				q.setParameter("roleFormationNo", roleFormationNo + "%");
				q.setParameter("sus_no", sus_no.toUpperCase() + "%");
			}
			if (roleSubAccess.equals("Corps")) {
				roleFormationNo = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))+ String.valueOf(roleFormationNo.charAt(2));
				q = session.createQuery("select sus_no from Orbat_Unt_Dtl where upper(sus_no) like :sus_no and status_sus_no  = 'Active' and  upper(form_code_control) like :roleFormationNo order by SUBSTRING(sus_no,1,7)")
						.setMaxResults(10);
				q.setParameter("roleFormationNo", roleFormationNo + "%");
				q.setParameter("sus_no", sus_no.toUpperCase() + "%");
			}
			if (roleSubAccess.equals("Division")) {
				roleFormationNo = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))
						+ String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3))
						+ String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
				q = session.createQuery("select sus_no from Orbat_Unt_Dtl where upper(sus_no) like :sus_no and status_sus_no  = 'Active' and  upper(form_code_control) like :roleFormationNo order by SUBSTRING(sus_no,1,7)").setMaxResults(10);
				q.setParameter("roleFormationNo", roleFormationNo + "%");
				q.setParameter("sus_no", sus_no.toUpperCase() + "%");
			}
			if (roleSubAccess.equals("Brigade")) {
				q = session.createQuery("select sus_no from Orbat_Unt_Dtl where upper(sus_no) like :sus_no and status_sus_no  = 'Active' and  upper(form_code_control) like :roleFormationNo order by SUBSTRING(sus_no,1,7)").setMaxResults(10);
				q.setParameter("roleFormationNo", roleFormationNo + "%");
				q.setParameter("sus_no", sus_no.toUpperCase() + "%");
			}
		}
		if (roleAccess.equals("Unit") ) {
			sus_no = roleSusNo;
			q = session.createQuery("select sus_no from Orbat_Unt_Dtl where upper(sus_no) like :sus_no and status_sus_no  = 'Active' order by SUBSTRING(sus_no,1,7)").setMaxResults(10);
			q.setParameter("sus_no", sus_no.toUpperCase() + "%");
		}
		if (roleAccess.equals("Line_dte")) {
			q = session.createQuery("select sus_no from Orbat_Unt_Dtl where upper(sus_no) like :sus_no and arm_code in (select arm_code from TB_ORBAT_LINE_DTE where line_dte_sus=:roleSusNo ) and status_sus_no  = 'Active' order by SUBSTRING(sus_no,1,7)").setMaxResults(10);
			q.setParameter("roleSusNo", roleSusNo);
			q.setParameter("sus_no", sus_no.toUpperCase() + "%");
		}
		if(roleAccess.equals("DG")) {
			if(roleSubAccess.equals("Medical")) {
				
				String[] medicalUnitPrifix = (String[]) sessionA.getAttribute("medicalUnitPrifix");
				q = session.createQuery("select sus_no from Orbat_Unt_Dtl where upper(sus_no) like :sus_no and status_sus_no  = 'Active' and substring(sus_no, 1,4) in (:med) order by SUBSTRING(sus_no,1,7) ").setMaxResults(10);
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

	@RequestMapping(value = "/getTargetSUSNoList_it", method = RequestMethod.POST)
	public @ResponseBody List<String> getTargetSUSNoList_it(HttpSession sessionA, String sus_no) {
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
				q = session.createQuery("select sus_no from Orbat_Unt_Dtl where upper(sus_no) like :sus_no and status_sus_no  = 'Active' and substring(sus_no, 1,4) in (:med) order by SUBSTRING(sus_no,1,7) ").setMaxResults(10);
				q.setParameter("sus_no", sus_no.toUpperCase()+"%");
				q.setParameterList("med", medicalUnitPrifix);
			}else {
				q = session.createQuery("select sus_no from Orbat_Unt_Dtl where upper(sus_no) like :sus_no and status_sus_no  = 'Active' order by SUBSTRING(sus_no,1,7)").setMaxResults(10);
				q.setParameter("sus_no", sus_no.toUpperCase() + "%");
			}
		}
		else {
			q = session.createQuery("select sus_no from Orbat_Unt_Dtl where upper(sus_no) like :sus_no and status_sus_no  = 'Active' order by SUBSTRING(sus_no,1,7)").setMaxResults(10);
			q.setParameter("sus_no", sus_no.toUpperCase() + "%");
		}
		
		if(roleAccess.equals("HeadQuarter")) {
			q = session.createQuery("select sus_no from Orbat_Unt_Dtl where upper(sus_no) like :sus_no and status_sus_no  = 'Active' order by SUBSTRING(sus_no,1,7)").setMaxResults(10);
			q.setParameter("sus_no", sus_no.toUpperCase() + "%");
		}
		if (roleAccess.equals("Formation")) {
			if (roleSubAccess.equals("Command")) {
				roleFormationNo = String.valueOf(roleFormationNo.charAt(0));
				q = session.createQuery("select sus_no from Orbat_Unt_Dtl where upper(sus_no) like :sus_no and status_sus_no  = 'Active' and  upper(form_code_control) like :roleFormationNo order by SUBSTRING(sus_no,1,7)").setMaxResults(10);
				q.setParameter("roleFormationNo", roleFormationNo + "%");
				q.setParameter("sus_no", sus_no.toUpperCase() + "%");
			}
			if (roleSubAccess.equals("Corps")) {
				roleFormationNo = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))+ String.valueOf(roleFormationNo.charAt(2));
				q = session.createQuery("select sus_no from Orbat_Unt_Dtl where upper(sus_no) like :sus_no and status_sus_no  = 'Active' and  upper(form_code_control) like :roleFormationNo order by SUBSTRING(sus_no,1,7)")
						.setMaxResults(10);
				q.setParameter("roleFormationNo", roleFormationNo + "%");
				q.setParameter("sus_no", sus_no.toUpperCase() + "%");
			}
			if (roleSubAccess.equals("Division")) {
				roleFormationNo = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))
						+ String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3))
						+ String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
				q = session.createQuery("select sus_no from Orbat_Unt_Dtl where upper(sus_no) like :sus_no and status_sus_no  = 'Active' and  upper(form_code_control) like :roleFormationNo order by SUBSTRING(sus_no,1,7)").setMaxResults(10);
				q.setParameter("roleFormationNo", roleFormationNo + "%");
				q.setParameter("sus_no", sus_no.toUpperCase() + "%");
			}
			if (roleSubAccess.equals("Brigade")) {
				q = session.createQuery("select sus_no from Orbat_Unt_Dtl where upper(sus_no) like :sus_no and status_sus_no  = 'Active' and  upper(form_code_control) like :roleFormationNo order by SUBSTRING(sus_no,1,7)").setMaxResults(10);
				q.setParameter("roleFormationNo", roleFormationNo + "%");
				q.setParameter("sus_no", sus_no.toUpperCase() + "%");
			}
		}
		if (roleAccess.equals("Unit") ) {
			sus_no = roleSusNo;
			q = session.createQuery("select sus_no from Orbat_Unt_Dtl where upper(sus_no) like :sus_no and status_sus_no  = 'Active' order by SUBSTRING(sus_no,1,7)").setMaxResults(10);
			q.setParameter("sus_no", sus_no.toUpperCase() + "%");
		}
		if (roleAccess.equals("Line_dte")) {
			q = session.createQuery("select sus_no from Orbat_Unt_Dtl where upper(sus_no) like :sus_no and arm_code in (select arm_code from TB_ORBAT_LINE_DTE where line_dte_sus=:roleSusNo ) and status_sus_no  = 'Active' order by SUBSTRING(sus_no,1,7)").setMaxResults(10);
			q.setParameter("roleSusNo", roleSusNo);
			q.setParameter("sus_no", sus_no.toUpperCase() + "%");
		}
		if(roleAccess.equals("DG")) {
			if(roleSubAccess.equals("Medical")) {
				
				String[] medicalUnitPrifix = (String[]) sessionA.getAttribute("medicalUnitPrifix");
				q = session.createQuery("select sus_no from Orbat_Unt_Dtl where upper(sus_no) like :sus_no and status_sus_no  = 'Active' and substring(sus_no, 1,4) in (:med) order by SUBSTRING(sus_no,1,7) ").setMaxResults(10);
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

	
	
	
	
	
	
	
	@RequestMapping(value = "/getTargetUnitsNameActiveList", method = RequestMethod.POST)
	public @ResponseBody List<String> getTargetUnitsNameActiveList(HttpSession sessionA, String unit_name) {

		unit_name = unit_name.replace("&#40;", "(");
		unit_name = unit_name.replace("&#41;", ")");

		String sus_no;
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();
		String roleFormationNo = sessionA.getAttribute("roleFormationNo").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		String role = sessionA.getAttribute("role").toString();
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		if (roleAccess.equals("MISO") || roleAccess.equals("Depot") || roleAccess.equals("DGMS")) {      
			if (roleSubAccess.equals("Medical")) {
				String[] medicalUnitPrifix = (String[]) sessionA.getAttribute("medicalUnitPrifix");
				if (role.equals("mnh_deo")) {
					q = session.createQuery("select p.unit_name from Tb_Med_Hosp_Assign a,UserLogin l ,Orbat_Unt_Dtl p where a.user_id=l.userId and p.sus_no=a.sus_no and p.status_sus_no='Active' and substring(p.sus_no,1,4) in (:med)").setMaxResults(10);
					q.setParameterList("med", medicalUnitPrifix);
				}
				else {
					q = session.createQuery("select unit_name from Orbat_Unt_Dtl where upper(unit_name) like :unit_name and status_sus_no  = 'Active' and substring(sus_no, 1,4) in (:med) order by unit_name").setMaxResults(10);
					q.setParameter("unit_name", unit_name.toUpperCase() + "%");
					q.setParameterList("med", medicalUnitPrifix);
				}
			}else {
				q = session.createQuery("select unit_name from Orbat_Unt_Dtl where upper(unit_name) like :unit_name and status_sus_no  = 'Active' order by unit_name").setMaxResults(10);
				q.setParameter("unit_name", unit_name.toUpperCase() + "%");
			}
		}
		if(roleAccess.equals("HeadQuarter")) {
			q = session.createQuery("select unit_name from Orbat_Unt_Dtl where upper(unit_name) like :unit_name and status_sus_no  = 'Active' order by unit_name").setMaxResults(10);
			q.setParameter("unit_name", unit_name.toUpperCase() + "%");
		}
		if (roleAccess.equals("Formation")) {
			if (roleSubAccess.equals("Command")) {
				roleFormationNo = String.valueOf(roleFormationNo.charAt(0));
				q = session.createQuery("select unit_name from Orbat_Unt_Dtl where upper(unit_name) like :unit_name and status_sus_no  = 'Active' and  upper(form_code_control) like :roleFormationNo order by unit_name").setMaxResults(10);
				q.setParameter("roleFormationNo", roleFormationNo + "%");
				q.setParameter("unit_name", unit_name.toUpperCase() + "%");
			}
			if (roleSubAccess.equals("Corps")) {
				roleFormationNo = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))+ String.valueOf(roleFormationNo.charAt(2));
				q = session.createQuery("select unit_name from Orbat_Unt_Dtl where upper(unit_name) like :unit_name and status_sus_no  = 'Active' and  upper(form_code_control) like :roleFormationNo order by unit_name").setMaxResults(10);
				q.setParameter("roleFormationNo", roleFormationNo + "%");
				q.setParameter("unit_name", unit_name.toUpperCase() + "%");
			}
			if (roleSubAccess.equals("Division")) {
				roleFormationNo = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))+ String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3))+ String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
				q = session.createQuery("select unit_name from Orbat_Unt_Dtl where upper(unit_name) like :unit_name and status_sus_no  = 'Active' and  upper(form_code_control) like :roleFormationNo order by unit_name").setMaxResults(10);
				q.setParameter("roleFormationNo", roleFormationNo + "%");
				q.setParameter("unit_name", unit_name.toUpperCase() + "%");
			}
			if (roleSubAccess.equals("Brigade")) {
				q = session.createQuery("select unit_name from Orbat_Unt_Dtl where upper(unit_name) like :unit_name and status_sus_no  = 'Active' and  upper(form_code_control) like :roleFormationNo order by unit_name").setMaxResults(10);
				q.setParameter("roleFormationNo", roleFormationNo + "%");
				q.setParameter("unit_name",  unit_name.toUpperCase() + "%");
			}
		}
		if (roleAccess.equals("Unit") ) {
			sus_no = roleSusNo;
			q = session.createQuery("select unit_name from Orbat_Unt_Dtl where upper(sus_no) like :sus_no and upper(unit_name) like :unit_name and status_sus_no  = 'Active'  order by unit_name").setMaxResults(10);
			q.setParameter("sus_no", sus_no.toUpperCase() + "%");
			q.setParameter("unit_name", unit_name.toUpperCase() + "%");
		}
		if (roleAccess.equals("Line_dte")) {
			q = session.createQuery("select unit_name from Orbat_Unt_Dtl where upper(unit_name) like :unit_name and arm_code in (select arm_code from TB_ORBAT_LINE_DTE where line_dte_sus=:roleSusNo ) and status_sus_no  = 'Active' order by SUBSTRING(sus_no,1,7)").setMaxResults(10);
			q.setParameter("roleSusNo", roleSusNo);
			q.setParameter("unit_name", unit_name.toUpperCase() + "%");
		}
		if(roleAccess.equals("DG")) {
			if(roleSubAccess.equals("Medical")) {
				String[] medicalUnitPrifix = (String[]) sessionA.getAttribute("medicalUnitPrifix");
				q = session.createQuery("select unit_name from Orbat_Unt_Dtl where upper(unit_name) like :unit_name and status_sus_no  = 'Active' and substring(sus_no, 1,4) in (:med) order by unit_name").setMaxResults(10);
				q.setParameter("unit_name", unit_name.toUpperCase() + "%");
				q.setParameterList("med", medicalUnitPrifix);
			}
		}
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
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
	@RequestMapping(value = "/getTargetUnitsNameActiveList_It", method = RequestMethod.POST)
	public @ResponseBody List<String> getTargetUnitsNameActiveList_It(HttpSession sessionA, String unit_name) {

		unit_name = unit_name.replace("&#40;", "(");
		unit_name = unit_name.replace("&#41;", ")");

		String sus_no;
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();
		String roleFormationNo = sessionA.getAttribute("roleFormationNo").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		String role = sessionA.getAttribute("role").toString();
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		if (roleAccess.equals("MISO") || roleAccess.equals("Depot")) {
			if (roleSubAccess.equals("Medical")) {
				String[] medicalUnitPrifix = (String[]) sessionA.getAttribute("medicalUnitPrifix");
				if (role.equals("mnh_deo")) {
					q = session.createQuery("select p.unit_name from Tb_Med_Hosp_Assign a,UserLogin l ,Orbat_Unt_Dtl p where a.user_id=l.userId and p.sus_no=a.sus_no and p.status_sus_no='Active' and substring(p.sus_no,1,4) in (:med)").setMaxResults(10);
					q.setParameterList("med", medicalUnitPrifix);
				}
				else {
					q = session.createQuery("select unit_name from Orbat_Unt_Dtl where upper(unit_name) like :unit_name and status_sus_no  = 'Active' and substring(sus_no, 1,4) in (:med) order by unit_name").setMaxResults(10);
					q.setParameter("unit_name", unit_name.toUpperCase() + "%");
					q.setParameterList("med", medicalUnitPrifix);
				}
			}else {
				q = session.createQuery("select unit_name from Orbat_Unt_Dtl where upper(unit_name) like :unit_name and status_sus_no  = 'Active' order by unit_name").setMaxResults(10);
				q.setParameter("unit_name", unit_name.toUpperCase() + "%");
			}
	}else {
			q = session.createQuery("select unit_name from Orbat_Unt_Dtl where upper(unit_name) like :unit_name and status_sus_no  = 'Active' order by unit_name").setMaxResults(10);
			q.setParameter("unit_name", unit_name.toUpperCase() + "%");
		}
	
		if(roleAccess.equals("HeadQuarter")) {
			q = session.createQuery("select unit_name from Orbat_Unt_Dtl where upper(unit_name) like :unit_name and status_sus_no  = 'Active' order by unit_name").setMaxResults(10);
			q.setParameter("unit_name", unit_name.toUpperCase() + "%");
		}
		if (roleAccess.equals("Formation")) {
			if (roleSubAccess.equals("Command")) {
				roleFormationNo = String.valueOf(roleFormationNo.charAt(0));
				q = session.createQuery("select unit_name from Orbat_Unt_Dtl where upper(unit_name) like :unit_name and status_sus_no  = 'Active' and  upper(form_code_control) like :roleFormationNo order by unit_name").setMaxResults(10);
				q.setParameter("roleFormationNo", roleFormationNo + "%");
				q.setParameter("unit_name", unit_name.toUpperCase() + "%");
			}
			if (roleSubAccess.equals("Corps")) {
				roleFormationNo = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))+ String.valueOf(roleFormationNo.charAt(2));
				q = session.createQuery("select unit_name from Orbat_Unt_Dtl where upper(unit_name) like :unit_name and status_sus_no  = 'Active' and  upper(form_code_control) like :roleFormationNo order by unit_name").setMaxResults(10);
				q.setParameter("roleFormationNo", roleFormationNo + "%");
				q.setParameter("unit_name", unit_name.toUpperCase() + "%");
			}
			if (roleSubAccess.equals("Division")) {
				roleFormationNo = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))+ String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3))+ String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
				q = session.createQuery("select unit_name from Orbat_Unt_Dtl where upper(unit_name) like :unit_name and status_sus_no  = 'Active' and  upper(form_code_control) like :roleFormationNo order by unit_name").setMaxResults(10);
				q.setParameter("roleFormationNo", roleFormationNo + "%");
				q.setParameter("unit_name", unit_name.toUpperCase() + "%");
			}
			if (roleSubAccess.equals("Brigade")) {
				q = session.createQuery("select unit_name from Orbat_Unt_Dtl where upper(unit_name) like :unit_name and status_sus_no  = 'Active' and  upper(form_code_control) like :roleFormationNo order by unit_name").setMaxResults(10);
				q.setParameter("roleFormationNo", roleFormationNo + "%");
				q.setParameter("unit_name",  unit_name.toUpperCase() + "%");
			}
		}
		if (roleAccess.equals("Unit") ) {
			sus_no = roleSusNo;
			q = session.createQuery("select unit_name from Orbat_Unt_Dtl where upper(sus_no) like :sus_no and upper(unit_name) like :unit_name and status_sus_no  = 'Active'  order by unit_name").setMaxResults(10);
			q.setParameter("sus_no", sus_no.toUpperCase() + "%");
			q.setParameter("unit_name", unit_name.toUpperCase() + "%");
		}
		if (roleAccess.equals("Line_dte")) {
			q = session.createQuery("select unit_name from Orbat_Unt_Dtl where upper(unit_name) like :unit_name and arm_code in (select arm_code from TB_ORBAT_LINE_DTE where line_dte_sus=:roleSusNo ) and status_sus_no  = 'Active' order by SUBSTRING(sus_no,1,7)").setMaxResults(10);
			q.setParameter("roleSusNo", roleSusNo);
			q.setParameter("unit_name", unit_name.toUpperCase() + "%");
		}
		if(roleAccess.equals("DG")) {
			if(roleSubAccess.equals("Medical")) {
				String[] medicalUnitPrifix = (String[]) sessionA.getAttribute("medicalUnitPrifix");
				q = session.createQuery("select unit_name from Orbat_Unt_Dtl where upper(unit_name) like :unit_name and status_sus_no  = 'Active' and substring(sus_no, 1,4) in (:med) order by unit_name").setMaxResults(10);
				q.setParameter("unit_name", unit_name.toUpperCase() + "%");
				q.setParameterList("med", medicalUnitPrifix);
			}
		}
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
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
	
	@RequestMapping(value = "/getTargetUnitNameList", method = RequestMethod.POST)
	public @ResponseBody List<String> getTargetUnitNameList(String sus_no, HttpSession sessionUserId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct unit_name from Orbat_Unt_Dtl where sus_no=:target_sus_no and status_sus_no='Active'");
		//26-01-1994
		q.setParameter("target_sus_no", sus_no);
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
	// bisag v2 240323 (status_sus_no !='INVALID' as per discuss with poonam mam)
	
	@RequestMapping(value = "/getTargetUnitNameListpostinout", method = RequestMethod.POST)
	public @ResponseBody List<String> getTargetUnitNameListpostinout(String sus_no, HttpSession sessionUserId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct unit_name from Orbat_Unt_Dtl where sus_no=:target_sus_no and upper(status_sus_no) in ('INACTIVE','ACTIVE') ");
		//26-01-1994
		q.setParameter("target_sus_no", sus_no);
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
	

	@RequestMapping(value = "/getNodalDteList", method = RequestMethod.POST)
	public @ResponseBody List<String> getNodalDteList(String sponsoring_dte, HttpSession sessionUserId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		// Query q = session.createQuery("select nodal_dir from
		// TB_TMS_MCT_NODAL_DIR_MASTER where type_of_agncy='Nodal_dte' and
		// upper(nodal_dir) like :sponsoring_dte").setMaxResults(10);

		Query q = session.createQuery(
				"select unit_name from Orbat_Unt_Dtl where sus_no in (select sus_no from TB_TMS_MCT_NODAL_DIR_MASTER where type_of_agncy='Nodal_dte')  and upper(unit_name) like :sponsoring_dte and status_sus_no='Active' ")
				.setMaxResults(10);

		q.setParameter("sponsoring_dte", '%' + sponsoring_dte.toUpperCase() + '%');
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
		if (list.size() != 0) {
			FinalList.add(enckey + "4bsjyg==");
		}
		return FinalList;
	}

	@RequestMapping(value = "/getAllBaNoList", method = RequestMethod.POST)
	public @ResponseBody List<String> getAllBaNoList(String ba_no, HttpSession sessionUserId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select distinct ba_no from TB_TMS_BANUM_DIRCTRY where upper(ba_no) like :ba_no order by ba_no").setMaxResults(10);
		q.setParameter("ba_no", ba_no.toUpperCase() + "%");
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

	@RequestMapping(value = "/getActiveBaNoList", method = RequestMethod.POST)
	public @ResponseBody List<String> getActiveBaNoList(String ba_no, HttpSession sessionUserId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select distinct ba_no from TB_TMS_BANUM_DIRCTRY where (status = 'Active' or status='NonArmy') and upper(ba_no) like :ba_no order by ba_no")
				.setMaxResults(10);
		q.setParameter("ba_no", ba_no.toUpperCase() + "%");
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
		if (list.size() != 0) {
			FinalList.add(enckey + "4bsjyg==");
		}
		return FinalList;
	}

	/// Start Request Agency

	@RequestMapping(value = "/getReqAgencyList", method = RequestMethod.POST)
	public @ResponseBody List<String> getReqAgencyList(String unit_name, HttpSession sessionUserId) {
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String sus_no = "";
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		if (roleAccess.equals("Unit") || roleAccess.equals("Depot")) {
			sus_no = roleSusNo;
			q = session.createQuery("select unit_name from Orbat_Unt_Dtl "
					+ "where sus_no in (select distinct sus_no from TB_TMS_MCT_REG_AGENCY_MASTER where sus_no =:sus_no ) "
					+ "and  status_sus_no  = 'Active' and upper(unit_name) like :unit_name")
					.setMaxResults(10);
			q.setParameter("unit_name", unit_name.toUpperCase() + "%");
			q.setParameter("sus_no",sus_no);
		}else {
			q = session.createQuery("select unit_name from Orbat_Unt_Dtl "
					+ "where sus_no in (select distinct sus_no from TB_TMS_MCT_REG_AGENCY_MASTER) "
					+ "and  status_sus_no  = 'Active' and upper(unit_name) like :unit_name")
					.setMaxResults(10);
			q.setParameter("unit_name", unit_name.toUpperCase() + "%");
		}
		
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
		if (list.size() != 0) {
			FinalList.add(enckey + "4bsjyg==");
		}
		return FinalList;
	}

	@RequestMapping(value = "/getReqAgencySusNoList", method = RequestMethod.POST)
	public @ResponseBody List<String> getReqAgencySusNoList(String sus_no, HttpSession sessionA) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit") || roleAccess.equals("Depot")) {
			sus_no = roleSusNo;
		}

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select distinct sus_no from TB_TMS_MCT_REG_AGENCY_MASTER where sus_no in (select sus_no from Orbat_Unt_Dtl where status_sus_no  = 'Active')  and upper(sus_no) like :sus_no").setMaxResults(10);
		q.setParameter("sus_no", sus_no.toUpperCase() + "%");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
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
		if (list.size() != 0) {
			FinalList.add(enckey + "4bsjyg==");
		}
		return FinalList;
	}

	@RequestMapping(value = "/getSusNoFromReqAgency", method = RequestMethod.POST)
	public @ResponseBody List<String> getSusNoFromReqAgency(String req_agency, HttpSession sessionUserId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select sus_no from Orbat_Unt_Dtl where sus_no in (select distinct sus_no from TB_TMS_MCT_REG_AGENCY_MASTER) and status_sus_no  = 'Active' and unit_name=:req_agency");
		q.setParameter("req_agency", req_agency);
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
		if (list.size() != 0) {
			FinalList.add(enckey + "4bsjyg==");
		}
		return FinalList;
	}

	/*********************tarjana 26-8-20  //In model there is no req_agency column so we have to do join of miso orbat unit for unit_name*****************************/
	
	@RequestMapping(value = "/getReqAgencyFromSusNo", method = RequestMethod.POST)
	public @ResponseBody List<String> getReqAgencyFromSusNo(String sus_no, HttpSession sessionA) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			sus_no = roleSusNo;
		}

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
				
		Query q = session.createQuery(
				/*"select req_agency from TB_TMS_MCT_REG_AGENCY_MASTER where sus_no in (select sus_no from Orbat_Unt_Dtl where status_sus_no  = 'Active') and sus_no=:sus_no"*/
				"select distinct m.unit_name from TB_TMS_MCT_REG_AGENCY_MASTER r, Orbat_Unt_Dtl m where m.status_sus_no  = 'Active' and r.sus_no=:sus_no and r.sus_no=m.sus_no");
	
		
		q.setParameter("sus_no", sus_no);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
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
		if (list.size() != 0) {
			FinalList.add(enckey + "4bsjyg==");
		}
		return FinalList;
	}

	@RequestMapping(value = "/getBaNoFromMVCRprtl", method = RequestMethod.POST)
	public @ResponseBody List<String> getBaNoFromMVCRprtl(String ba_no, HttpSession sessionUserId) {

		Session sessionGet2 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx2 = sessionGet2.beginTransaction();
		Query q = sessionGet2.createQuery("select ba_no from TB_TMS_MVCR_PARTA_DTL where upper(ba_no) like :ba_no ")
				.setMaxResults(10);
		q.setParameter("ba_no", '%' + ba_no.toUpperCase() + '%');
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx2.commit();
		sessionGet2.close();
		String enckey = hex_asciiDao.getAlphaNumericString();
		Cipher c = null;
		try {
			c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId, enckey);
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
		if (list.size() != 0) {
			FinalList.add(enckey + "4bsjyg==");
		}
		return FinalList;
	}

	// VEH CATEGORY WISE BA NO AUTOCOMPLETE
	@RequestMapping(value = "/getAllBaNoByVehCatList", method = RequestMethod.POST)
	public @ResponseBody List<String> getAllBaNoByVehCatList(String ba_no, String veh_cat, HttpSession sessionUserId) {
		int userid = Integer.parseInt(sessionUserId.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct ba_no from TB_TMS_BANUM_DIRCTRY where upper(ba_no) like :ba_no and veh_cat=:veh_cat order by ba_no")
				.setMaxResults(10);
		q.setParameter("ba_no", ba_no.toUpperCase() + "%");
		q.setParameter("veh_cat", veh_cat);
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
		if (list.size() != 0) {
			FinalList.add(enckey + "4bsjyg==");
		}
		return FinalList;
	}

	public int CompareDate(String fdt, String tdt) {
		if (fdt.compareTo(tdt) > 0) {
			return 0;
		} else {
			return 1;
		}
	}
	/// END Request Agency
	
	

	///// for line directory
	
	@RequestMapping(value = "/getTargetSUSNoListforline", method = RequestMethod.POST)
	public @ResponseBody List<String> getTargetSUSNoListforline(HttpSession sessionA, String sus_no) {
	
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		
		q = session.createQuery("select sus_no from Orbat_Unt_Dtl where arm_code in (select distinct arm_code from TB_ORBAT_LINE_DTE) and upper(sus_no) like :sus_no  order by SUBSTRING(sus_no,1,7)").setMaxResults(10);
		q.setParameter("sus_no", sus_no.toUpperCase() + "%");
		
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
	
	
	@RequestMapping(value = "/getTargetUnitsNameActiveListline", method = RequestMethod.POST)
	public @ResponseBody List<String> getTargetUnitsNameActiveListline(HttpSession sessionA, String unit_name) {

		unit_name = unit_name.replace("&#40;", "(");
		unit_name = unit_name.replace("&#41;", ")");

		String sus_no;
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();
		String roleFormationNo = sessionA.getAttribute("roleFormationNo").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		String role = sessionA.getAttribute("role").toString();
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		if (roleAccess.equals("MISO") || roleAccess.equals("Depot")) {
			if (roleSubAccess.equals("Medical")) {
				String[] medicalUnitPrifix = (String[]) sessionA.getAttribute("medicalUnitPrifix");
				if (role.equals("mnh_deo")) {
					q = session.createQuery("select p.unit_name from Tb_Med_Hosp_Assign a,UserLogin l ,Orbat_Unt_Dtl p where a.user_id=l.userId and p.sus_no=a.sus_no and p.status_sus_no='Active' and substring(p.sus_no,1,4) in (:med)").setMaxResults(10);
					q.setParameterList("med", medicalUnitPrifix);
				}
				else {
					q = session.createQuery("select unit_name from Orbat_Unt_Dtl where upper(unit_name) like :unit_name and arm_code in (select arm_code from TB_ORBAT_LINE_DTE  ) and status_sus_no  = 'Active' order by SUBSTRING(sus_no,1,7)").setMaxResults(10);
					q.setParameter("unit_name", unit_name.toUpperCase() + "%");
					q.setParameterList("med", medicalUnitPrifix);
				}
			}else {
				q = session.createQuery("select unit_name from Orbat_Unt_Dtl where upper(unit_name) like :unit_name and status_sus_no  = 'Active' order by unit_name").setMaxResults(10);
				q.setParameter("unit_name", unit_name.toUpperCase() + "%");
			}
		}
		if(roleAccess.equals("HeadQuarter")) {
			q = session.createQuery("select unit_name from Orbat_Unt_Dtl where upper(unit_name) like :unit_name and arm_code in (select arm_code from TB_ORBAT_LINE_DTE  ) and status_sus_no  = 'Active' order by SUBSTRING(sus_no,1,7)").setMaxResults(10);
			q.setParameter("unit_name", unit_name.toUpperCase() + "%");
		}
		if (roleAccess.equals("Formation")) {
			if (roleSubAccess.equals("Command")) {
				roleFormationNo = String.valueOf(roleFormationNo.charAt(0));
				q = session.createQuery("select unit_name from Orbat_Unt_Dtl where upper(unit_name) like :unit_name and arm_code in (select arm_code from TB_ORBAT_LINE_DTE  ) and  upper(form_code_control) like :roleFormationNo order by unit_name").setMaxResults(10);
				q.setParameter("roleFormationNo", roleFormationNo + "%");
				q.setParameter("unit_name", unit_name.toUpperCase() + "%");
			}
			if (roleSubAccess.equals("Corps")) {
				roleFormationNo = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))+ String.valueOf(roleFormationNo.charAt(2));
				q = session.createQuery("select unit_name from Orbat_Unt_Dtl where upper(unit_name) like :unit_name and arm_code in (select arm_code from TB_ORBAT_LINE_DTE  ) and  upper(form_code_control) like :roleFormationNo order by unit_name").setMaxResults(10);
				q.setParameter("roleFormationNo", roleFormationNo + "%");
				q.setParameter("unit_name", unit_name.toUpperCase() + "%");
			}
			if (roleSubAccess.equals("Division")) {
				roleFormationNo = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))+ String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3))+ String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
				q = session.createQuery("select unit_name from Orbat_Unt_Dtl where upper(unit_name) like :unit_name and arm_code in (select arm_code from TB_ORBAT_LINE_DTE  )  and  upper(form_code_control) like :roleFormationNo order by unit_name").setMaxResults(10);
				q.setParameter("roleFormationNo", roleFormationNo + "%");
				q.setParameter("unit_name", unit_name.toUpperCase() + "%");
			}
			if (roleSubAccess.equals("Brigade")) {
				q = session.createQuery("select unit_name from Orbat_Unt_Dtl where upper(unit_name) like :unit_name and arm_code in (select arm_code from TB_ORBAT_LINE_DTE  )  and status_sus_no  = 'Active' and  upper(form_code_control) like :roleFormationNo order by unit_name").setMaxResults(10);
				q.setParameter("roleFormationNo", roleFormationNo + "%");
				q.setParameter("unit_name",  unit_name.toUpperCase() + "%");
			}
		}
		if (roleAccess.equals("Unit") ) {
			sus_no = roleSusNo;
			q = session.createQuery("select unit_name from Orbat_Unt_Dtl where upper(unit_name) like :unit_name and arm_code in (select arm_code from TB_ORBAT_LINE_DTE  ) and status_sus_no  = 'Active' order by SUBSTRING(sus_no,1,7)").setMaxResults(10);
			q.setParameter("sus_no", sus_no.toUpperCase() + "%");
			q.setParameter("unit_name", unit_name.toUpperCase() + "%");
		}
		if (roleAccess.equals("Line_dte")) {
			q = session.createQuery("select unit_name from Orbat_Unt_Dtl where upper(unit_name) like :unit_name and arm_code in (select arm_code from TB_ORBAT_LINE_DTE where line_dte_sus=:roleSusNo ) and status_sus_no  = 'Active' order by SUBSTRING(sus_no,1,7)").setMaxResults(10);
			q.setParameter("roleSusNo", roleSusNo);
			q.setParameter("unit_name", unit_name.toUpperCase() + "%");
		}
		if(roleAccess.equals("DG")) {
			if(roleSubAccess.equals("Medical")) {
				String[] medicalUnitPrifix = (String[]) sessionA.getAttribute("medicalUnitPrifix");
				q = session.createQuery("select unit_name from Orbat_Unt_Dtl where upper(unit_name) like :unit_name and status_sus_no  = 'Active' and substring(sus_no, 1,4) in (:med) order by unit_name").setMaxResults(10);
				q.setParameter("unit_name", unit_name.toUpperCase() + "%");
				q.setParameterList("med", medicalUnitPrifix);
			}
		}

		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
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
	@RequestMapping(value = "/getUnitNameList_Active_or_Inactive", method = RequestMethod.POST)
	public @ResponseBody List<String> getUnitNameList_Active_or_Inactive(String sus_no, HttpSession sessionUserId) {
		
		
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct unit_name from Orbat_Unt_Dtl where sus_no=:target_sus_no and upper(status_sus_no) in ('INACTIVE','ACTIVE') ");
		//26-01-1994
		q.setParameter("target_sus_no", sus_no);
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
	
	@RequestMapping(value = "/getUnitNameList_Inactiveonly", method = RequestMethod.POST)
	public @ResponseBody List<String> getUnitNameList_Inactiveonly(String sus_no, HttpSession sessionUserId) {
		
		
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct unit_name from Orbat_Unt_Dtl where sus_no=:target_sus_no and upper(status_sus_no) = upper('INACTIVE') ");
		//26-01-1994
		q.setParameter("target_sus_no", sus_no);
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