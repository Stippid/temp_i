package com.controller.LogBook;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.commonController.It_CommonController;
import com.controller.login.RoleController;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.tms.AllMethodsControllerTMS;
import com.controller.validation.ValidationController;
import com.dao.LogBook.LogBookDao;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class LogBookController {

	@Autowired
	RoleBaseMenuDAO roledao;
	ValidationController validation = new ValidationController();
	It_CommonController it_comm = new It_CommonController();
	AllMethodsControllerOrbat com = new AllMethodsControllerOrbat();
	AllMethodsControllerTMS allt = new AllMethodsControllerTMS();
	@Autowired
	RoleController rolecontroller = new RoleController();
	@Autowired
	LogBookDao logbook;
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	@RequestMapping(value = "/Log_book_url", method = RequestMethod.GET)
	public ModelAndView Log_book_url(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg)
			throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException {

		String roleid = session.getAttribute("roleid").toString();
//		Boolean val = roledao.ScreenRedirect("Log_book_url", roleid);
//		if (val == false) {
//			return new ModelAndView("AccessTiles");
//		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		Mmap.put("msg", msg);
		
		return new ModelAndView("LogBooktiles");
	}

	
	@RequestMapping(value = "/getLogBookReportDataList", method = RequestMethod.POST)
	 public @ResponseBody List<Map<String, Object>> getLogBookReportDataList(int startPage,String pageLength,String Search,String orderColunm,String orderType,
			 
			String catgory, String assets_name, String make_name,String status, HttpSession sessionUserId) 
			 throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException,
			 InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
		 return logbook.getLogBookReportDataList(startPage,pageLength,Search,orderColunm,orderType,catgory,assets_name,make_name,status,sessionUserId);
	}
	 @RequestMapping(value = "/getLogBookTotalCount", method = RequestMethod.POST)
	 public @ResponseBody long getLogBookTotalCount(HttpSession sessionUserId,String Search	,String catgory, String assets_name, String make_name,String status,HttpSession session){
	 	return logbook.getLogBookTotalCount(Search,catgory,assets_name,make_name,status,session);
	 }
	 
//	 @RequestMapping(value = "/downloadLogBookPDF", method = RequestMethod.POST)
//	 public @ResponseBody long downloadLogBookPDF(HttpSession sessionUserId,String 	LogbookId,HttpSession session){
		 
	 @RequestMapping(value = "/downloadLogBookPDF", method = RequestMethod.POST)
		public ModelAndView downloadLogBookPDF(ModelMap Mmap,
				@RequestParam(value = "msg", required = false) String msg,
				Authentication authentication, HttpServletRequest request,
				HttpSession session,
				@RequestParam(value = "LogbookId", required = false) String LogbookId,
				@RequestParam(value = "categoryId", required = false) String categoryId
				) {


			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("PersonalAssertsUrl", roleid);
			String roleSusNo = session.getAttribute("roleSusNo").toString();
			if (val == false) {
				// return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				return new ModelAndView("redirect:bodyParameterNotAllow");
			}

			String enckey = "commonPwdEncKeys";
			String DcryptedPk = hex_asciiDao.decrypt((String) LogbookId, enckey, session);
			String username = session.getAttribute("username").toString();
			Mmap.put("msg", msg);

			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
			String formattedDate = formatter.format(date);
			String foot = " REPORT GENERATED ON " + new SimpleDateFormat("dd-MM-yyyy").format(date);
			ArrayList<ArrayList<String>> list_serving = logbook.LogBookData(DcryptedPk,categoryId);
			List<String> TH = new ArrayList<String>();
//			TH.add("Sr No");
//			TH.add("Pers No");
//			TH.add("Rk");
//			TH.add("Name");
//			TH.add("DoB");
//			TH.add("DoC");
//			TH.add("Dt of Retirement");
//			TH.add("Eligibility");
//			TH.add("Convening Order No");
//			TH.add("Convening Order Dt");
//			TH.add("Indl Sig\r\n"
//	                + "(I declare that I comply by provns of Appx B of DDG IT POLICY B/03568/GEN/DDGIT (PROJ) DT 29 NOV 24)\r\n"
//	                + "");


			return new ModelAndView(new PDF_Logbook(TH, foot, username), "userList",
					list_serving);
		}
	 


}
