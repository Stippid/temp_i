package com.controller.mns;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.dao.mms.Mms_Common_DAO;
import com.models.MMS_Domain_Values;
import com.models.MMS_TB_MLCCS_NEW_REQ;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class MMS_COMMON_Controller {

	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	private Mms_Common_DAO mmsCommonDAO;

	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();

	@RequestMapping(value = "/admin/dashboard", method = RequestMethod.GET)
	public ModelAndView dashboard(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("dashboardTiles");
	}

	// Common Controller Methods Start
	// START=================================================================================================================================================

	@RequestMapping(value = "/getMMSPRFtListBySearch", method = RequestMethod.POST)
	@ResponseBody
	public List<String> getMMSPRFtListBySearch(String nParaValue, HttpSession s1) {

		List<String> list = mmsCommonDAO.getMMSPRFtListBySearch(nParaValue);

		if (list.size() != 0) {
			List<String> FList = mmsCommonDAO.getMMSEncList(list, s1);
			return FList;
		} else {
			return list;
		}
	}

	@RequestMapping(value = "/getmms_distinct_prf_group_by_sus", method = RequestMethod.POST)
	@ResponseBody
	public List<String> getmms_distinct_prf_group_by_sus(String nParaValue, String nPara, HttpSession s1) {

		Boolean val = roledao.ScreenRedirect("mms_unit_mcr", s1.getAttribute("roleid").toString());
		if (val == false) {
			// return "AccessTiles";
		}
		String roleAccess = s1.getAttribute("roleAccess").toString();
		String roleSusNo = s1.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			nParaValue = roleSusNo;
		}

		List<String> list = null;
		if (nPara.equals("SUS")) {
			list = mmsCommonDAO.getPrfListBySUSNo(nParaValue);
		}

		if (list.size() != 0) {
			List<String> FList = mmsCommonDAO.getMMSEncList(list, s1);
			return FList;
		} else {
			return list;
		}
	}

	@RequestMapping(value = "/getmms_prf_groupList1", method = RequestMethod.POST) // For PRF Group Listing By COS SEC
	public @ResponseBody List<String> getmms_prf_groupList1(String cos_sec, String nPara, HttpSession s1) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		if (nPara.equals("ALL")) {
			q = session.createQuery("select concat(prf_group_code,':',prf_group) as prf from CUE_TB_MISO_PRF_Mst");
		} else if (nPara.equals("COS")) {
			q = session.createQuery(
					"select concat(prf_group_code,':',prf_group) as prf from CUE_TB_MISO_PRF_Mst where coss_section=:cos_sec");
			q.setParameter("cos_sec", cos_sec);
		} else if (nPara.equals("PRF")) {
			q = session.createQuery(
					"select concat(prf_group_code,':',prf_group) as prf from CUE_TB_MISO_PRF_Mst where prf_group_code=:prf_group");
			q.setParameter("prf_group", cos_sec);
		}
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();

		if (list.size() != 0) {
			List<String> FList = mmsCommonDAO.getMMSEncList(list, s1);
			return FList;
		} else {
			return list;
		}
	}

	@RequestMapping(value = "/getmms_census_noList", method = RequestMethod.POST)
	public @ResponseBody List<String> getmms_census_noList(String census, String nPara, HttpSession s1) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		if (nPara.equals("ALL")) {
			q = session.createQuery("select DISTINCT census_no from MMS_TB_MLCCS_MSTR_DETL");
		} else if (nPara.equals("CENSUS")) {
			q = session
					.createQuery("select DISTINCT census_no from MMS_TB_MLCCS_MSTR_DETL where census_no like :census");
			q.setParameter("census", census + "%");
			q.setMaxResults(10);
		} else if (nPara.equals("NOMEN")) {
			q = session.createQuery("select DISTINCT census_no from MMS_TB_MLCCS_MSTR_DETL where nomen=:census");
			q.setParameter("census", census);
		}
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();

		if (list.size() != 0) {
			List<String> FList = mmsCommonDAO.getMMSEncList(list, s1);
			return FList;
		} else {
			return list;
		}
	}

	@RequestMapping(value = "/getmms_distinct_census_by_sus", method = RequestMethod.POST)
	@ResponseBody
	public List<String> getmms_distinct_census_by_sus(String nSusNo, String nPrfCode, HttpSession s1) {

		Boolean val = roledao.ScreenRedirect("mms_unit_mcr", s1.getAttribute("roleid").toString());
		if (val == false) {

		}
		String roleAccess = s1.getAttribute("roleAccess").toString();
		String roleSusNo = s1.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			nSusNo = roleSusNo;
		}

		List<String> list = mmsCommonDAO.getCensusNoBySUSNo(nSusNo, nPrfCode);
		if (list.size() != 0) {
			List<String> FList = mmsCommonDAO.getMMSEncList(list, s1);
			return FList;
		} else {
			return list;
		}
	}

	@RequestMapping(value = "/checkDetailsOfCensus") // Used In to check exist census No
	public @ResponseBody List<String> checkDetailsOfCensus(String census1, HttpSession s1) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session
				.createQuery("select distinct census_no from MMS_TB_MLCCS_MSTR_DETL where census_no=:census1 ");
		q.setParameter("census1", census1);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();

		if (list.size() != 0) {
			List<String> FList = mmsCommonDAO.getMMSEncList(list, s1);
			return FList;
		} else {
			return list;
		}
	}

	@RequestMapping(value = "/getNewCensusNo", method = RequestMethod.POST) // Generate New Census No
	public @ResponseBody List<String> getNewCensusNo(String cos, HttpSession s1) {
		String census_no = "";
		String prfcode = cos;

		char ch = prfcode.charAt(0);
		int castAscii = (int) ch;

		// Prf First Two Degits
		String first = String.format("%02d", castAscii - 64);

		// Prf second Two Degits
		String second = String.valueOf(prfcode.charAt(2)) + String.valueOf(prfcode.charAt(3));

		census_no = first + second;

		// Serial No
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("SELECT max(census_no) FROM MMS_TB_MLCCS_MSTR_DETL where cos_sec like :prfcode ");
		q.setParameter("prfcode", prfcode + "%");
		String list = (String) q.list().get(0);
		tx.commit();
		session.close();

		String serial = "";
		if (list == null) {
			serial = "0000";
		} else {
			serial = String.valueOf(list.charAt(4)) + String.valueOf(list.charAt(5)) + String.valueOf(list.charAt(6))
					+ String.valueOf(list.charAt(7));
		}

		int serialNo = Integer.parseInt(serial) + 1;
		serial = String.format("%04d", serialNo);
		census_no += serial;

		int sum = 0;
		int length = census_no.length() + 1;
		for (int i = 0; i < census_no.length(); i++) {
			String multi = String.valueOf(census_no.charAt(i));
			int ans = Integer.parseInt(multi) * length;
			sum += ans;
			length--;
		}

		int mod = sum % 11;

		Session sessionT = HibernateUtil.getSessionFactory().openSession();
		Transaction txT = sessionT.beginTransaction();
		Query qT = sessionT
				.createQuery("select label FROM MMS_Domain_Values where domainid='MMSCHECKDIGIT' and codevalue=:mod");
		qT.setParameter("mod", String.valueOf(mod));
		@SuppressWarnings("unchecked")
		List<String> list1 = (List<String>) qT.list();
		txT.commit();
		sessionT.close();

		census_no += list1.get(0);

		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<String> listcensus_no = new ArrayList();
		listcensus_no.add(census_no);

		List<String> FList = mmsCommonDAO.getMMSEncList(listcensus_no, s1);

		return FList;
	}

	@RequestMapping(value = "/getmmsDistinctNomenList", method = RequestMethod.POST) // For fetch nomen listing
	public @ResponseBody List<String> getmmsDistinctNomenList(String y, HttpSession s1) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct nomen from MMS_TB_MLCCS_MSTR_DETL where lower(nomen) like :nomen or Upper(nomen) like :nomen");
		q.setParameter("nomen", y.toLowerCase() + "%");
		q.setParameter("nomen", y.toUpperCase() + "%");
		q.setMaxResults(10);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();

		if (list.size() != 0) {
			List<String> FList = mmsCommonDAO.getMMSEncList(list, s1);
			return FList;
		} else {
			return list;
		}
	}

	@RequestMapping(value = "/checkDetailsOfNomen", method = RequestMethod.POST) // Used In to check exist nomen
	public @ResponseBody List<String> checkDetailsOfNomen(String nomen1, HttpSession s1) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session
				.createQuery("select distinct nomen from MMS_TB_MLCCS_MSTR_DETL where  upper(nomen) like :nomen1");

		q.setParameter("nomen1", nomen1.toUpperCase() + "%");

		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();

		if (list.size() != 0) {
			List<String> FList = mmsCommonDAO.getMMSEncList(list, s1);
			return FList;
		} else {
			return list;
		}
	}

	@RequestMapping(value = "/getMMSCensusToNomen", method = RequestMethod.POST) // Return nomenclature based on census
																					// no
	public @ResponseBody List<String> getMMSCensusToNomen(String y, HttpSession s1) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select distinct nomen from MMS_TB_MLCCS_MSTR_DETL where census_no=:census");
		q.setParameter("census", y);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();

		if (list.size() != 0) {
			List<String> FList = mmsCommonDAO.getMMSEncList(list, s1);
			return FList;
		} else {
			return list;
		}

	}

	@RequestMapping(value = "/getMMSNomenToCensus", method = RequestMethod.POST) // Return census no based on
	public @ResponseBody List<String> getMMSNomenToCensus(String y, HttpSession s1) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select distinct census_no from MMS_TB_MLCCS_MSTR_DETL where nomen=:nomen");
		q.setParameter("nomen", y);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();

		if (list.size() != 0) {
			List<String> FList = mmsCommonDAO.getMMSEncList(list, s1);
			return FList;
		} else {
			return list;
		}
	}

	@RequestMapping(value = "/getmmsDistinctMlccs", method = RequestMethod.POST)
	@ResponseBody
	public List<String> getmmsDistinctMlccs(String nParaValue, String nPara, HttpSession s1) {
		List<String> list = mmsCommonDAO.getMMSDistinctMlccsList(nParaValue, nPara);
		if (list.size() != 0) {
			List<String> FList = mmsCommonDAO.getMMSEncList(list, s1);
			return FList;
		} else {
			return list;
		}
	}

	@RequestMapping(value = "/getmmsMlccsList", method = RequestMethod.POST) // For census,nomen,prf_code,prf_group // based mlccs listing
	public @ResponseBody List<String> getmmsMlccsList(String census, String nPara, HttpSession s1) {
		List<String> list = mmsCommonDAO.getMMSMlccsList(census, nPara);
		if (list.size() != 0) {
			List<String> FList = mmsCommonDAO.getMMSEncList(list, s1);
			return FList;
		} else {
			return list;
		}
	}

	@RequestMapping(value = "/getMMSUnitListBySearch", method = RequestMethod.POST)
	@ResponseBody
	public List<String> getMMSUnitListBySearch(String nValue, HttpSession s1) {
		String roleAccess = s1.getAttribute("roleAccess").toString();
		String roleSusNo = s1.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			nValue = roleSusNo;
		}
		List<String> list = mmsCommonDAO.getMMSUnitListBySearch(nValue,s1);
		if (list.size() != 0) {
			List<String> FList = mmsCommonDAO.getMMSEncList(list, s1);
			return FList;
		} else {
			return list;
		}
	}

	@RequestMapping(value = "/getMMSDepotListBySearch", method = RequestMethod.POST)
	@ResponseBody
	public List<String> getMMSDepotListBySearch(String nValue, HttpSession s1) {
		List<String> list = mmsCommonDAO.getMMSDepotListBySearch(nValue);
		if (list.size() != 0) {
			List<String> FList = mmsCommonDAO.getMMSEncList(list, s1);
			return FList;
		} else {
			return list;
		}
	}

	@RequestMapping(value = "/getMMSRList", method = RequestMethod.POST) // For Autocomplete - // a(type),b(url),c(textbox),d(paravalue)
	public @ResponseBody List<String> getMMSRList(String a, String b, String c, String d, HttpSession sessionA) {
		String qw = "";
		String qr = "";
		String qcnd = "";
		if (c.equalsIgnoreCase("SUS")) {
			qw = "select distinct sus_no from Orbat_Unt_Dtl where sus_no like :sus_no and status_sus_no='Active'";
		}
		if (c.equalsIgnoreCase("NAME")) {
			qw = "select distinct unit_name from Orbat_Unt_Dtl where (Upper(unit_name) like :sus_no) and status_sus_no='Active' ";
		}
		if (c.equalsIgnoreCase("DSUS")) {
			qw = "select distinct sus_no from Orbat_Unt_Dtl where status_sus_no='Active' and sus_no in "
					+ "(select distinct sus_no from Tb_Orbat_Unit_Other_Function where role='DEPOT' and function_mms='ORD DEP') "
					+ "and (sus_no like :sus_no) order by sus_no";
		}
		if (c.equalsIgnoreCase("DNAME")) {
			qw = "select distinct unit_name from Orbat_Unt_Dtl where status_sus_no='Active' and sus_no in "
					+ "(select distinct sus_no from Tb_Orbat_Unit_Other_Function where role='DEPOT' and function_mms='ORD DEP') "
					+ "and (lower(unit_name) like :sus_no or Upper(unit_name) like :sus_no) order by unit_name";
		}

		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();
		String roleFormationNo = sessionA.getAttribute("roleFormationNo").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		String roleArmCode = sessionA.getAttribute("roleArmCode").toString();

		if (roleAccess.equalsIgnoreCase("MISO") || roleAccess.equalsIgnoreCase("HeadQuarter")) {
			if (!d.equals("")) {
				qcnd = " and form_code_control like :susval";
			}
			qr = qw + qcnd;
		}
		if (roleAccess.equalsIgnoreCase("Unit")) {
			qcnd = " and sus_no=:susval";
			qr = qw + qcnd;
		}
		if (roleAccess.equalsIgnoreCase("Formation")) {
			if (roleSubAccess.equalsIgnoreCase("BRIGADE")) { // ok
				qcnd = " and form_code_control=:susval";
				qr = qw + qcnd;
			}
			if (roleSubAccess.equalsIgnoreCase("DIVISION")) {
				qcnd = " and substring(form_code_control,1,6)=:susval";
				qr = qw + qcnd;
			}
			if (roleSubAccess.equalsIgnoreCase("CORPS")) {
				qcnd = " and substring(form_code_control,1,3)=:susval";
				qr = qw + qcnd;
			}
			if (roleSubAccess.equalsIgnoreCase("COMMAND")) {
				qcnd = " and substring(form_code_control,1,1)=:susval";
				qr = qw + qcnd;
			}
		}
		if (roleAccess.equalsIgnoreCase("Line_dte")) {
			qcnd = " and arm_code=:susval";
			qr = qw + qcnd;
		}

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(qr).setMaxResults(10);
		if (c.equalsIgnoreCase("SUS") || c.equalsIgnoreCase("DSUS")) {
			q.setParameter("sus_no", a + "%");
			if (!d.equals("")) {
				q.setParameter("susval", d + "%");
			}
		}
		if (c.equalsIgnoreCase("NAME") || c.equalsIgnoreCase("DNAME")) {
			q.setParameter("sus_no", '%' + a.toUpperCase() + '%');
			if (!d.equals("")) {
				q.setParameter("susval", d + "%");
			}
		}
		if (roleAccess.equalsIgnoreCase("Unit")) {
			q.setParameter("susval", roleSusNo);
		}

		if (roleAccess.equalsIgnoreCase("Formation")) {
			if (roleSubAccess.equalsIgnoreCase("BRIGADE")) { // ok
				q.setParameter("susval", roleFormationNo);
			}
			if (roleSubAccess.equalsIgnoreCase("DIVISION")) {
				q.setParameter("susval", roleFormationNo.substring(0, 6));
			}
			if (roleSubAccess.equalsIgnoreCase("CORPS")) {
				q.setParameter("susval", roleFormationNo.substring(0, 3));
			}
			if (roleSubAccess.equalsIgnoreCase("COMMAND")) {
				q.setParameter("susval", roleFormationNo.substring(0, 1));
			}
		}

		if (roleAccess.equalsIgnoreCase("Line_dte")) {
			q.setParameter("susval", roleArmCode);
		}

		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();

		List<String> FList = mmsCommonDAO.getMMSEncList(list, sessionA);
		return FList;
	}

	@RequestMapping(value = "/getMMSUnitNameBySUSNo", method = RequestMethod.POST)
	public @ResponseBody List<String> getMMSUnitNameBySUSNo(String y, HttpSession s1) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct unit_name from Orbat_Unt_Dtl where sus_no=:sus_no and status_sus_no = 'Active'");
		q.setParameter("sus_no", y);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();

		if (list.size() != 0) {
			List<String> FList = mmsCommonDAO.getMMSEncList(list, s1);
			return FList;
		} else {
			return list;
		}
	}

	@RequestMapping(value = "/getMMSSUSNoByUnitName", method = RequestMethod.POST)
	public @ResponseBody List<String> getMMSSUSNoByUnitName(String y, HttpSession s1) {
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct sus_no from Orbat_Unt_Dtl where unit_name=:unit_name and status_sus_no = 'Active'");
		q.setParameter("unit_name", y);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();

		if (list.size() != 0) {
			List<String> FList = mmsCommonDAO.getMMSEncList(list, s1);
			return FList;
		} else {
			return list;
		}
	}
	
	
	@RequestMapping(value = "/getMMSUnitNameWITHSUSNo", method = RequestMethod.POST)
	public @ResponseBody List<String> getMMSUnitNameWITHSUSNo(HttpSession sessionA,String line_dte_sus) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSubAccess = sessionA.getAttribute("roleSubAccess").toString();
		String roleFormationNo = sessionA.getAttribute("roleFormationNo").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;//session.createQuery("select unit_name,sus_no from Orbat_Unt_Dtl where status_sus_no = 'Active' order by unit_name");
		
		if (roleAccess.equals("MISO")) {
			if (roleSubAccess.equals("Medical")) {
				String[] medicalUnitPrifix = (String[]) sessionA.getAttribute("medicalUnitPrifix");
				q = session.createQuery("select unit_name,sus_no from Orbat_Unt_Dtl where arm_code in (select arm_code from TB_ORBAT_LINE_DTE where line_dte_sus=:line_dte_sus ) and status_sus_no  = 'Active' and substring(sus_no, 1,4) in (:med) order by unit_name ");
				q.setParameterList("med", medicalUnitPrifix);
				q.setParameter("line_dte_sus",line_dte_sus);
			}else {
				q = session.createQuery("select unit_name,sus_no from Orbat_Unt_Dtl where arm_code in (select arm_code from TB_ORBAT_LINE_DTE where line_dte_sus=:line_dte_sus ) and status_sus_no  = 'Active' order by unit_name");
				q.setParameter("line_dte_sus",line_dte_sus);
			}
		}
		if(roleAccess.equals("HeadQuarter")) {
			q = session.createQuery("select unit_name,sus_no from Orbat_Unt_Dtl where arm_code in (select arm_code from TB_ORBAT_LINE_DTE where line_dte_sus=:line_dte_sus ) and status_sus_no  = 'Active' order by unit_name");
			q.setParameter("line_dte_sus",line_dte_sus);
		}
		
		if (roleAccess.equals("Formation")) {
			if (roleSubAccess.equals("Command")) {
				roleFormationNo = String.valueOf(roleFormationNo.charAt(0));
				q = session.createQuery("select unit_name,sus_no from Orbat_Unt_Dtl where arm_code in (select arm_code from TB_ORBAT_LINE_DTE where line_dte_sus=:line_dte_sus ) and status_sus_no = 'Active' and  upper(form_code_control) like :roleFormationNo order by unit_name");
				q.setParameter("roleFormationNo", roleFormationNo + "%");
				q.setParameter("line_dte_sus",line_dte_sus);
			}
			if (roleSubAccess.equals("Corps")) {
				roleFormationNo = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))+ String.valueOf(roleFormationNo.charAt(2));
				q = session.createQuery("select unit_name,sus_no from Orbat_Unt_Dtl where arm_code in (select arm_code from TB_ORBAT_LINE_DTE where line_dte_sus=:line_dte_sus ) and status_sus_no  = 'Active' and  upper(form_code_control) like :roleFormationNo order by unit_name");
				q.setParameter("roleFormationNo", roleFormationNo + "%");
				q.setParameter("line_dte_sus",line_dte_sus);
			}
			if (roleSubAccess.equals("Division")) {
				roleFormationNo = String.valueOf(roleFormationNo.charAt(0)) + String.valueOf(roleFormationNo.charAt(1))
						+ String.valueOf(roleFormationNo.charAt(2)) + String.valueOf(roleFormationNo.charAt(3))
						+ String.valueOf(roleFormationNo.charAt(4)) + String.valueOf(roleFormationNo.charAt(5));
				q = session.createQuery("select unit_name,sus_no from Orbat_Unt_Dtl where arm_code in (select arm_code from TB_ORBAT_LINE_DTE where line_dte_sus=:line_dte_sus ) and status_sus_no  = 'Active' and  upper(form_code_control) like :roleFormationNo order by unit_name");
				q.setParameter("roleFormationNo", roleFormationNo + "%");
				q.setParameter("line_dte_sus",line_dte_sus);
			}
			if (roleSubAccess.equals("Brigade")) {
				q = session.createQuery("select unit_name,sus_no from Orbat_Unt_Dtl where arm_code in (select arm_code from TB_ORBAT_LINE_DTE where line_dte_sus=:line_dte_sus ) and status_sus_no  = 'Active' and  upper(form_code_control) like :roleFormationNo order by unit_name");
				q.setParameter("roleFormationNo", roleFormationNo + "%");
				q.setParameter("line_dte_sus",line_dte_sus);
			}
		}
		if (roleAccess.equals("Unit") || roleAccess.equals("Depot")) {
			q = session.createQuery("select unit_name,sus_no from Orbat_Unt_Dtl where arm_code in (select arm_code from TB_ORBAT_LINE_DTE where line_dte_sus=:line_dte_sus ) and upper(sus_no) like :sus_no and status_sus_no = 'Active' order by unit_name");
			q.setParameter("sus_no", roleSusNo.toUpperCase() + "%");
			q.setParameter("line_dte_sus",line_dte_sus);
		}
		if (roleAccess.equals("Line_dte")) {
			q = session.createQuery("select unit_name,sus_no from Orbat_Unt_Dtl where arm_code in (select arm_code from TB_ORBAT_LINE_DTE where line_dte_sus=:roleSusNo ) and status_sus_no  = 'Active' order by unit_name");
			q.setParameter("roleSusNo", roleSusNo);
		}
		if(roleAccess.equals("DG")) {
			if(roleSubAccess.equals("Medical")) {
				String[] medicalUnitPrifix = (String[]) sessionA.getAttribute("medicalUnitPrifix");
				q = session.createQuery("select unit_name,sus_no from Orbat_Unt_Dtl where  arm_code in (select arm_code from TB_ORBAT_LINE_DTE where line_dte_sus=:line_dte_sus ) and status_sus_no  = 'Active' and substring(sus_no, 1,4) in (:med) order by unit_name ");
				q.setParameterList("med", medicalUnitPrifix);
				q.setParameter("line_dte_sus",line_dte_sus);
			}
		}
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
		/*if (list.size() != 0) {
			List<String> FList = mmsCommonDAO.getMMSEncList(list, s1);
			return FList;
		} else {
			return list;
		}*/
	}
	

	@RequestMapping(value = "/getMMSDistinctHirarName", method = RequestMethod.POST) // For Fetching Distinct Hirar.
																						// Name List
	public @ResponseBody List<String> getMMSDistinctHirarName(String nHirar, String nCnd, String codelevel,
			HttpSession s1) {
		List<String> list = mmsCommonDAO.getMMSDistinctHirarName(nHirar, nCnd, codelevel);

		if (list.size() != 0) {
			List<String> FList = mmsCommonDAO.getMMSEncList(list, s1);
			return FList;
		} else {
			return list;
		}
	}

	@RequestMapping(value = "/getMMSDistHirarSingleName", method = RequestMethod.POST) // For Fetching Single Hirar.
																						// Name
	public @ResponseBody List<String> getMMSDistHirarSingleName(String nValue, HttpSession s1) {

		int userid = Integer.parseInt(s1.getAttribute("userId").toString());

		String nHirar = "COMMAND";
		String nCnd = "ALL";
		String codelevel = "COMMAND";

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String nrQry = "";
		String hir = nHirar;
		String cond = "";

		int start = 0;
		int end = 0;

		if (codelevel.equalsIgnoreCase("COMMAND")) {
			start = 1;
			end = 1;
		} else if (codelevel.equalsIgnoreCase("CORPS")) {
			start = 1;
			end = 3;
		} else if (codelevel.equalsIgnoreCase("DIVISION")) {
			start = 1;
			end = 6;
		} else if (codelevel.equalsIgnoreCase("BRIGADE")) {
			start = 1;
			end = 10;
		} else if (codelevel.equalsIgnoreCase("UNIT")) {
			start = 1;
			end = 10;
		}

		if (nCnd != null && !nCnd.equals("ALL")) {
			if (hir.equalsIgnoreCase("COMMAND")) {

			} else if (hir.equalsIgnoreCase("CORPS")) {
				cond = " and substring(form_code_control," + start + "," + end + ")=substring('" + nCnd + "'," + start
						+ "," + end + ")";
			} else if (hir.equalsIgnoreCase("DIVISION")) {
				cond = " and substring(form_code_control," + start + "," + end + ")=substring('" + nCnd + "'," + start
						+ "," + end + ")";
			} else if (hir.equalsIgnoreCase("BRIGADE")) {
				cond = " and substring(form_code_control," + start + "," + end + ")=substring('" + nCnd + "'," + start
						+ "," + end + ")";
			} else if (hir.equalsIgnoreCase("UNIT")) {
				cond = " and substring(form_code_control," + start + "," + end + ")=substring('" + nCnd + "'," + start
						+ "," + end + ")";
			}
		}

		if (nCnd == "ALL") {
			nrQry = "select distinct concat(form_code_control,':',sus_no,':',unit_name) as unit_name from Orbat_Unt_Dtl where status_sus_no='Active' and sus_no in (select DISTINCT sus_no from Tbl_CodesForm where upper(level_in_hierarchy) in ('"
					+ hir + "')) and (lower(unit_name) like :unit_name or Upper(unit_name) like :unit_name)";
		} else {
			nrQry = "select distinct concat(form_code_control,':',sus_no,':',unit_name) as unit_name from Orbat_Unt_Dtl where status_sus_no='Active' and sus_no in (select DISTINCT sus_no from Tbl_CodesForm where upper(level_in_hierarchy) in ('"
					+ hir + "')) and (lower(unit_name) like :unit_name or Upper(unit_name) like :unit_name) " + cond;
		}

		Query q = session.createQuery(nrQry);
		q.setParameter("unit_name", "%" + nValue.toLowerCase() + "%");
		q.setParameter("unit_name", "%" + nValue.toUpperCase() + "%");
		q.setMaxResults(10);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();

		tx.commit();
		session.close();

		List<String> FList = mmsCommonDAO.getMMSEncList(list, s1);

		return FList;
	}

	@RequestMapping(value = "/getMMSHirarNameBySUS", method = RequestMethod.POST) // For Fetching Single Hirar. Name By
																					// SUS No
	public @ResponseBody List<String> getMMSHirarNameBySUS(String FindWhat, String a, HttpSession s1) {

		List<String> list = mmsCommonDAO.getMMSHirarNameBySUS(FindWhat, a);

		if (list.size() != 0) {
			List<String> FList = mmsCommonDAO.getMMSEncList(list, s1);
			return FList;
		} else {
			return list;
		}
	}

	@RequestMapping(value = "/getMMSArmCodeList") // PARA-ALL/OTHER,PARAVALUE<ARM_CODE>
	public @ResponseBody List<String> getMMSArmCodeList(String nPara, String nParaValue, HttpSession s1) {
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String rq = "";
		if (nPara.equalsIgnoreCase("ALL")) {
			rq = "select distinct arm_code,arm_desc from Tb_Orabt_Arm_Code order by arm_desc";
		} else {
			rq = "select distinct arm_code,arm_desc from Tb_Orabt_Arm_Code where arm_code=:armcode order by arm_desc";
		}
		Query q = session.createQuery(rq);
		if (!nPara.equalsIgnoreCase("ALL")) {
			q.setParameter("armcode", nParaValue);
		}
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	@RequestMapping(value = "/getnuhval", method = RequestMethod.POST) // Fetch UH Value
	public @ResponseBody List<String> getnuhval(String nSUSNo, String nPRF, String nHldType, String nEqptType,
			String nPara, HttpSession s1) {
		String roleAccess = s1.getAttribute("roleAccess").toString();
		String roleSusNo = s1.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit") || roleAccess.equals("Depot")) {
			nSUSNo = roleSusNo;
		}
		List<String> list = mmsCommonDAO.getUHValue(nSUSNo, nPRF, nHldType, nEqptType, nPara);

		if (list.size() != 0) {
			List<String> FList = mmsCommonDAO.getMMSEncList(list, s1);
			return FList;
		} else {
			return list;
		}
	}

	@RequestMapping(value = "/getnueval", method = RequestMethod.POST) // Fetch UE Value
	public @ResponseBody List<String> getnueval(String nSUSNo, String nPRF, String nItemCd, String nWE, String nPara,
			HttpSession sessionA) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit") || roleAccess.equals("Depot")) {
			nSUSNo = roleSusNo;
		}

		List<String> list = mmsCommonDAO.getUEValue(nSUSNo, nPRF, nWE, nPara);
		if (list.size() != 0) {
			List<String> FList = mmsCommonDAO.getMMSEncList(list, sessionA);
			return FList;
		} else {
			return list;
		}
	}

	@RequestMapping(value = "/getmms_cos_sectionList", method = RequestMethod.POST) // Fetch cos section List (Used in
																					// Autocomplete)
	public @ResponseBody List<String> getmms_cos_sectionList(String y, HttpSession s1) {
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select DISTINCT coss_section from CUE_TB_MISO_PRF_Mst where lower(coss_section) like :coss_section or Upper(coss_section) like :coss_section");
		q.setParameter("coss_section", y.toLowerCase() + "%");
		q.setParameter("coss_section", y.toUpperCase() + "%");
		q.setMaxResults(10);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();

		if (list.size() != 0) {
			List<String> FList = mmsCommonDAO.getMMSEncList(list, s1);
			return FList;
		} else {
			return list;
		}
	}

	@RequestMapping(value = "/getCountryName", method = RequestMethod.POST) // Fetch Country List (Used in Autocomplete)
	public @ResponseBody List<String> getCountryName(String y, HttpSession s1) {
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select DISTINCT country from COUNTRY where lower(country) like :country or Upper(country) like :country");
		q.setParameter("country", y.toLowerCase() + "%");
		q.setParameter("country", y.toUpperCase() + "%");
		q.setMaxResults(10);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();

		if (list.size() != 0) {
			List<String> FList = mmsCommonDAO.getMMSEncList(list, s1);
			return FList;
		} else {
			return list;
		}
	}

	@RequestMapping(value = "/getmms_item_codeList", method = RequestMethod.POST) // Item Code Listing
	public @ResponseBody List<String> getmms_item_codeList(String val, HttpSession s1) {
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select concat(item_code,':',item_type) as item_code from CUE_TB_MMS_ITEM_MSTR where prf_group_code=:prf");
		q.setParameter("prf", val);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();

		if (list.size() != 0) {
			List<String> FList = mmsCommonDAO.getMMSEncList(list, s1);
			return FList;
		} else {
			return list;
		}
	}

	@RequestMapping(value = "/getmmsNewReqList", method = RequestMethod.POST) // (check for opti.)
	public @ResponseBody List<MMS_TB_MLCCS_NEW_REQ> getmmsNewReqList(int id, HttpSession s1) {
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		q = session.createQuery("from MMS_TB_MLCCS_NEW_REQ where id=:id");
		q.setParameter("id", id);
		@SuppressWarnings("unchecked")
		List<MMS_TB_MLCCS_NEW_REQ> list = (List<MMS_TB_MLCCS_NEW_REQ>) q.list();
		tx.commit();
		session.close();
		return list;
	}
	// END=================================================================================================================================================

	@RequestMapping(value = "/getMMSDepotList", method = RequestMethod.POST) // For Depot Listing
	public @ResponseBody List<String> getMMSDepotList(HttpSession s1) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String nrQry = "select distinct form_code_control,sus_no,unit_name from Orbat_Unt_Dtl where status_sus_no='Active' "
				+ "and sus_no in (select distinct sus_no from Tb_Orbat_Unit_Other_Function where role='DEPOT' and function_mms='ORD DEP') order by unit_name";
		// q = session.createQuery(nrQry);
		Query q = session.createQuery(nrQry);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	@RequestMapping(value = "/getExtendDate", method = RequestMethod.POST) // Fetch cur date + 90 days date
	public @ResponseBody List<String> getExtendDate(HttpSession s1) {
		List<String> list = mmsCommonDAO.getExtendDate();
		return list;
	}

	@RequestMapping(value = "/getExtendDateOnChange", method = RequestMethod.POST) // On Chnage Fetch cur date + 90 days
	public @ResponseBody List<String> getExtendDateOnChange(String d, HttpSession s1) {
		List<String> list = mmsCommonDAO.getExtendDateOnChange(d);
		if (list.size() != 0) {
			List<String> FList = mmsCommonDAO.getMMSEncList(list, s1);
			return FList;
		} else {
			return list;
		}
	}

	@RequestMapping(value = "/getPRFNameByPRFCode", method = RequestMethod.POST)
	public @ResponseBody List<String> getPRFNameByPRFCode(String y, HttpSession s1) {
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session
				.createQuery("select distinct prf_group from CUE_TB_MISO_PRF_Mst where prf_group_code=:prf_group_code");
		q.setParameter("prf_group_code", y);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();

		if (list.size() != 0) {
			List<String> FList = mmsCommonDAO.getMMSEncList(list, s1);
			return FList;
		} else {
			return list;
		}
	}

	// @RequestMapping(value = "/getMMSUnitCodeList") //For form code fetch
	public List<String> getMMSUnitCodeList(String a) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select distinct form_code_control from Orbat_Unt_Dtl where status_sus_no='Active' and sus_no=:sus_no");
		q.setParameter("sus_no", a);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	public List<MMS_Domain_Values> getDomainListingData(String domainid) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select codevalue,label from MMS_Domain_Values where domainid=:domainid order by disp_order");
		q.setParameter("domainid", domainid);
		@SuppressWarnings("unchecked")
		List<MMS_Domain_Values> list = (List<MMS_Domain_Values>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	public List<String> getDData(String domainid) { // Remove When not needed
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select codevalue,label from MMS_Domain_Values where domainid=:domainid order by disp_order");
		q.setParameter("domainid", domainid);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	@RequestMapping(value = "/getMMSDistDomain")
	public @ResponseBody List<String> getMMSDistDomain(HttpSession s1) {
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select distinct domainid from MMS_Domain_Values");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	@RequestMapping(value = "/getDomainValues") // For dropdown based data Fetch (old method)
	public @ResponseBody List<MMS_Domain_Values> getDomainValues(String domainid, HttpSession s1) {
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from MMS_Domain_Values where domainid=:domainid order by disp_order");
		q.setParameter("domainid", domainid);
		@SuppressWarnings("unchecked")
		List<MMS_Domain_Values> list = (List<MMS_Domain_Values>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	// Remove when Not Needed
	// @RequestMapping(value = "/getDomainDetailsValues") //For dropdown based data
	// Fetch
	public @ResponseBody List<MMS_Domain_Values> getDomainDetailsValues(String domainid, String codevalue,
			HttpSession s1) {
		int userid = Integer.parseInt(s1.getAttribute("userId").toString());

		String[] cdval = codevalue.split(":");
		String cnd = "";
		if (cdval.length == 1) {
			cnd = " codevalue LIKE :codevalue ";
		} else {
			cnd = " ( codevalue LIKE :codevalue1 OR codevalue LIKE :codevalue2 )";
		}
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(" from MMS_Domain_Values where domainid=:domainid and " + cnd);
		q.setParameter("domainid", domainid);

		if (cdval.length == 1) {
			q.setParameter("codevalue", cdval[0] + "%");
		} else {
			q.setParameter("codevalue1", cdval[0] + "%");
			q.setParameter("codevalue2", cdval[1] + "%");
		}

		@SuppressWarnings("unchecked")
		List<MMS_Domain_Values> list = (List<MMS_Domain_Values>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	public List<MMS_Domain_Values> getDomainListingValues(String domainid, String codevalue) {
		String[] cdval = codevalue.split(":");
		String cnd = "";
		if (cdval.length == 1) {
			cnd = "codevalue LIKE :codevalue ";
		} else {
			cnd = " ( codevalue LIKE :codevalue1 OR codevalue LIKE :codevalue2 )";
		}
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(" from MMS_Domain_Values where domainid=:domainid and " + cnd);
		q.setParameter("domainid", domainid);
		if (cdval.length == 1) {
			q.setParameter("codevalue", cdval[0] + "%");
		} else {
			q.setParameter("codevalue1", cdval[0] + "%");
			q.setParameter("codevalue2", cdval[1] + "%");
		}
		@SuppressWarnings("unchecked")
		List<MMS_Domain_Values> list = (List<MMS_Domain_Values>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	// NEW METHOD FOR TYPE OF HOLDING //

	public List<MMS_Domain_Values> getTypeOfHold(String versionno) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from MMS_Domain_Values where domainid=:domainid and versionno=:versionno");
		q.setParameter("domainid", "TYPEOFHOLDING");
		q.setParameter("versionno", versionno);
		@SuppressWarnings("unchecked")
		List<MMS_Domain_Values> list = (List<MMS_Domain_Values>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	// END METHOD FOR TYPE OF HOLDING //

	@RequestMapping(value = "/getmms_distinct_type_of_hldg_by_sus_frmtbl", method = RequestMethod.POST)
	@ResponseBody
	public List<String> getmms_distinct_type_of_hldg_by_sus_frmtbl(String nParaValue, String nPara,HttpSession sessionA) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit") || roleAccess.equals("Depot")) {
			nParaValue = roleSusNo;
		}

		List<String> list = null;
		if (nPara.equals("SUS")) {
			list = mmsCommonDAO.getTypeofHldgBySUSNo_frmtbl(nParaValue);
		}
		if (list.size() != 0) {
			List<String> FList = mmsCommonDAO.getMMSEncList(list, sessionA);
			return FList;
		} else {
			return list;
		}
	}
	

	@RequestMapping(value = "/getMMSUnitListBySearchToUnit", method = RequestMethod.POST)
		@ResponseBody
		public List<String> getMMSUnitListBySearchToUnit(String nValue, HttpSession s1) {
			String roleAccess = s1.getAttribute("roleAccess").toString();
			String roleSusNo = s1.getAttribute("roleSusNo").toString();
			List<String> list = mmsCommonDAO.getMMSUnitListBySearch(nValue,s1);
			if (list.size() != 0) {
				List<String> FList = mmsCommonDAO.getMMSEncList(list, s1);
				return FList;
			} else {
				return list;
			}
		}
}
