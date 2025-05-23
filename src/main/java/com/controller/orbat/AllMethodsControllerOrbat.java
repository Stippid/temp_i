package com.controller.orbat;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.orbat.AllMethodsDAO;
import com.models.Orbat_Unt_Dtl;
import com.models.T_Domain_Value;
import com.models.Tb_Orabt_Arm_Code;
import com.models.Tb_Orbat_Code;
import com.models.Tbl_CodesForm;
import com.persistance.util.HibernateUtil;

import ch.qos.logback.core.net.SyslogOutputStream;

@Controller
@RequestMapping(value = {"admin","/" ,"user"}) 
public class AllMethodsControllerOrbat {

		HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
		@Autowired
		AllMethodsDAO allMethodDao;
	
	
		// Operation Details
		
	
		//Parent Arm List Method 
		public List<Tb_Orbat_Code> getPrantArmList(HttpSession session){
			Session sessionCode = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionCode.beginTransaction();
			Query q = sessionCode.createQuery("from Tb_Orbat_Code where code_type=:code_type and status_record=:status_record order by code");
			q.setParameter("code_type", "Parent Arm");
			q.setParameter("status_record", "1");
			@SuppressWarnings("unchecked")
			List<Tb_Orbat_Code> list = (List<Tb_Orbat_Code>) q.list();
			tx.commit();
			sessionCode.close();
			return list;
		}
		
		//Type of Arm List Method from Selected Parent Arm
		
		@RequestMapping(value = "/getTypeOfArmArmList", method = RequestMethod.POST)
		public @ResponseBody  ArrayList<List<String>> getTypeOfArmArmList(String code,HttpSession sessionUserId) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
			Session sessionCode = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = sessionCode.beginTransaction();
			Query q = sessionCode.createQuery(" from Tb_Orbat_Code where code_type='Type of Arm' and status_record='1' and code like :code order by cast(code as int)");
			q.setParameter("code", code+"%");
			@SuppressWarnings("unchecked")
			List<Tb_Orbat_Code> list = (List<Tb_Orbat_Code>) q.list();
			tx.commit();
			sessionCode.close();
			
			String enckey = hex_asciiDao.getAlphaNumericString();
			Cipher c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId,enckey);
			ArrayList<List<String>> FinalList = new ArrayList<List<String>>();
		    for(int i=0; i<list.size();i++) {
		    	byte[] encCode = c.doFinal(list.get(i).getCode().getBytes());
			    String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
			    
			    byte[] encCodeVal = c.doFinal(list.get(i).getCode_value().getBytes());
			    String base64EncodedEncryptedCodeValue = new String(Base64.encodeBase64(encCodeVal));
			    
			    List<String> EncList = new ArrayList<String>();
			    EncList.add(base64EncodedEncryptedCode);
			    EncList.add(base64EncodedEncryptedCodeValue);
			    FinalList.add(EncList);
		    }
		    List<String> EncKeyList = new ArrayList<String>();
		    if(list.size() != 0) {
		    	 EncKeyList.add(enckey);
				 EncKeyList.add(enckey);
		    }
		    FinalList.add(EncKeyList);
		    return FinalList;
		}
		
		//Get Type of Arm Name from code  
		public String getTypeOfArmNameBYCode(String typeOfArm) {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("from Tb_Orbat_Code where code_type='Type of Arm' and status_record='1' and code=:code order by cast(code as int)");
			q.setParameter("code", typeOfArm);
			@SuppressWarnings("unchecked")
			List<Tb_Orbat_Code> list = (List<Tb_Orbat_Code>) q.list();
			String typeOfArmName = list.get(0).getCode_value();
			tx.commit();
			session.close();
			return typeOfArmName;
		}
		
		// ARM Name List
		public List<Tb_Orabt_Arm_Code> getArmNameList() {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("from Tb_Orabt_Arm_Code Where  status='1' and arm_desc is not null order by arm_code");
			@SuppressWarnings("unchecked")
			List<Tb_Orabt_Arm_Code> list = (List<Tb_Orabt_Arm_Code>) q.list();
			tx.commit();
			session.close();
			return list;
		}
		
		// LOC List
		public List<Tb_Orbat_Code> getLOCList() {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("select code,code_value from Tb_Orbat_Code where code_type='Location' and status_record='1' order by code_value");
			@SuppressWarnings("unchecked")
			List<Tb_Orbat_Code> list = (List<Tb_Orbat_Code>) q.list();
			tx.commit();
			session.close();
			return list;
		}
		
		@RequestMapping(value = "/getLOC_NRS_TypeLOC_TrnType",method = RequestMethod.POST)
		public @ResponseBody List<String> getLOC_NRS_TypeLOC_TrnType(String locCode,HttpSession sessionUserId) {
			Session session= HibernateUtil.getSessionFactory().openSession();
			session.setFlushMode(FlushMode.ALWAYS);
			Transaction tx = session.beginTransaction();
			Query q = null;
			q = session.createQuery("select distinct a.code_value as location,"
					+ "b.code_value as nrs,"
					+ "a.code as loc_code,"
					+ "a.mod_desc as trn_type,"
					+ "c.label as type_of_loc,"
					+ "a.nrs_code "
					+ "from "
					+ "Tb_Orbat_Code a,Tb_Orbat_Code b,T_Domain_Value  c "
					+ "where a.code_type='Location' and b.code_type = 'Location' and a.nrs_code = b.code and "
					+ "a.status_record = '1' and b.status_record='1' and a.type_loc = c.codevalue and "
					+ "c.domainid='TYPEOFLOCATION' and a.code=:locCode order by a.code_value");
			q.setParameter("locCode", locCode);
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q.list();
			tx.commit();
			session.close();
			return list;
		}
		
		public List<T_Domain_Value> getTypeOfUnitList() {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("select codevalue,label from T_Domain_Value where domainid=:domainid");
			q.setParameter("domainid", "TYPEOFFORCE");
			@SuppressWarnings("unchecked")
			List<T_Domain_Value> list = (List<T_Domain_Value>) q.list();
			tx.commit();
			session.close();
			return list;
		}
				
		// get ArmName From ArmCode 
		public List<Tb_Orabt_Arm_Code> getArmNameFromArmCodeList(String arm_code) {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("select arm_desc from Tb_Orabt_Arm_Code where arm_code=:arm_code ");
			q.setParameter("arm_code", arm_code);
			@SuppressWarnings("unchecked")
			List<Tb_Orabt_Arm_Code> list = (List<Tb_Orabt_Arm_Code>) q.list();
			tx.commit();
			session.close();
			return list;
		}
		
		public List<T_Domain_Value> getTypeOfUnitFromUnitNoList(String type_force) {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("from T_Domain_Value where domainid=:domainid and codevalue=:type_force");
			q.setParameter("type_force", type_force);
			q.setParameter("domainid", "TYPEOFFORCE");
			@SuppressWarnings("unchecked")
			List<T_Domain_Value> list = (List<T_Domain_Value>) q.list();
			tx.commit();
			session.close();
			return list;
		}
		
		public List<T_Domain_Value> getType_Of_LetterList() {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("from T_Domain_Value where domainid=:domainid order by codevalue");
			q.setParameter("domainid", "SCHEDULETYPE");
			@SuppressWarnings("unchecked")
			List<T_Domain_Value> list = (List<T_Domain_Value>) q.list();
			tx.commit();
			session.close();
			return list;
		}
		
	
		
		public String EncMSG(String msg,HttpSession sessionEnc) {
			String enckey = hex_asciiDao.getAlphaNumericString();
			Cipher c = null;
			try {
				c = hex_asciiDao.EncryptionSHA256Algo(sessionEnc,enckey);
			} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
					| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
				
			}
			
		    byte[] encMSG = null;
			try {
				encMSG = c.doFinal(msg.getBytes());
			} catch (IllegalBlockSizeException | BadPaddingException e) {
				
			}
		    String base64EncodedEncryptedMSG = new String(Base64.encodeBase64(encMSG));
			base64EncodedEncryptedMSG +=":"+enckey;
		    return base64EncodedEncryptedMSG;
		}
		
		
		
}