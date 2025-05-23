package com.controller.itasset.Masters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
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
import com.models.itasset.master.TB_MSTR_OFFICE_M;
import com.controller.itasset.ExportFile.ExcelUserListReportView;
import com.dao.itasset.masters.Office_mstrDAO;
 import com.persistance.util.HibernateUtil;


@Controller
@RequestMapping(value = {"admin","/" ,"user"})
public class Office_mstr_OtherController {


@Autowired
private Office_mstrDAO objDAO;
HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();

@Autowired
RoleBaseMenuDAO roledao;

@RequestMapping(value = "Office_mstr_Other_Url", method = RequestMethod.GET)
public ModelAndView Office_mstr_Other_Url(ModelMap Mmap, HttpSession session, HttpServletRequest request,
		@RequestParam(value = "msg", required = false) String msg)
		throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException,
		NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException {

	String roleid = session.getAttribute("roleid").toString();
	Boolean val = roledao.ScreenRedirect("Office_mstr_Other_Url", roleid);
	if (val == false) {
		return new ModelAndView("AccessTiles");
	}
	if (request.getHeader("Referer") == null) {
		msg = "";
		// return new ModelAndView("redirect:bodyParameterNotAllow");
	}

	Mmap.put("msg", msg);
	return new ModelAndView("Office_mstr_other_tile", "office_mstrCMD", new TB_MSTR_OFFICE_M());
}

@RequestMapping(value = "/getOffice_mstr_Other_ReportDataList", method = RequestMethod.POST)
public @ResponseBody List<Map<String, Object>> getOffice_mstr_Other_ReportDataList(int startPage, String pageLength,
		String Search, String orderColunm, String orderType, String office, String status, HttpSession sessionUserId)
		throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException,
		InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
	return objDAO.getReportListOffice_Other_mstr(startPage, pageLength, Search, orderColunm, orderType, office, status,
			sessionUserId);
}

 @RequestMapping(value = "/getOffice_mstr_other_TotalCount", method = RequestMethod.POST)
public @ResponseBody long getOffice_mstr_other_TotalCount(HttpSession sessionUserId,String Search,String office,String status){
	return objDAO.getReportListOffice_Otrher_mstrTotalCount(Search,office,status);
}
 
 @RequestMapping(value = "/Approve_OfficeotherData" , method = RequestMethod.POST)
	public @ResponseBody List<String> Approve_OfficeotherData(String a,String status,HttpSession session) {	
		String sus_no = session.getAttribute("roleSusNo").toString();
		String username = session.getAttribute("username").toString();
		List<String> list2 = new ArrayList<String>();
		list2.add(objDAO.approve_Office_Data(a,sus_no,status,username));
		return list2;
	}
 @RequestMapping(value = "/Delete_OfficeotherData" , method = RequestMethod.POST)
	public @ResponseBody List<String> Delete_OfficeotherData(String a) {	
		List<String> list2 = new ArrayList<String>();
		
		list2.add(objDAO.reject_Office_Data(a));
		return list2;
	}
  
} 
