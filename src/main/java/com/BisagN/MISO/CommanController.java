package com.BisagN.MISO;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dao.itasset.Report.IT_Assets_Serviceable_Unserviceable_DAO;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.models.Tbl_CodesForm;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class CommanController {

	@Autowired
	IT_Assets_Serviceable_Unserviceable_DAO SER_DAO;
	
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	

 		/************get Formation from sus no for Reports***************/
 		 public List<String> getformationfromSus_NOList(String sus_no) {
 	         Session sessionComnd = HibernateUtil.getSessionFactory().openSession();
 	         Transaction tx = sessionComnd.beginTransaction();
 	         Query q = sessionComnd.createQuery("select form_code_control from Orbat_Unt_Dtl "
 	                         + "where sus_no=:sus_no and status_sus_no='Active'");
 	     	q.setParameter("sus_no", sus_no);
 	         @SuppressWarnings("unchecked")
 	         List<String> list = (List<String>) q.list();
 	         tx.commit();
 	         sessionComnd.close();
 	         return list;
 	     	}
 		 
 		 

 		
}
