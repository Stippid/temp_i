package com.controller.itasset.Masters;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dao.itasset.masters.Processor_or_typeDAO;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.models.itasset.master.TB_MSTR_PROCESSOR_TYPE_M;


@Controller
@RequestMapping(value = {"admin","/" ,"user"})
public class Processor_or_type_other_Controller {
	
	
	
	@Autowired
	private Processor_or_typeDAO objDAO;
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();

	@Autowired
	RoleBaseMenuDAO roledao;
	
	
    @RequestMapping(value = "Processor_or_type_Other_Url", method = RequestMethod.GET)
    public ModelAndView Processor_or_type_Other_Url(ModelMap Mmap,HttpSession session,HttpServletRequest request,
 @RequestParam(value = "msg", required = false) String msg) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, 
NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException{
   	 
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Processor_or_type_Other_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}


        Mmap.put("msg", msg);
    return new ModelAndView("Processor_or_type_other_tile","Processor_or_typeCMD",new TB_MSTR_PROCESSOR_TYPE_M());
}
	
	
	 @RequestMapping(value = "/getProcessor_or_typeReportDataListOther", method = RequestMethod.POST)
	 public @ResponseBody List<Map<String, Object>> getFilteredDataList_SQL(int startPage,String pageLength,String Search,String orderColunm,String orderType,String processor_type,String status,HttpSession sessionUserId) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
		return objDAO.getReportListProcessor_or_type_other(startPage,pageLength,Search,orderColunm,orderType,processor_type,status,sessionUserId);
	}

	 @RequestMapping(value = "/getProcessor_or_typeTotalCountOther", method = RequestMethod.POST)
	public @ResponseBody long getProcessor_or_typeTotalCount(HttpSession sessionUserId,String Search,String processor_type,String status){
		return objDAO.getReportListProcessor_or_typeTotalCount_other(Search,processor_type,status);
	}
	 
	 
		@RequestMapping(value = "/Approve_ProceesaorotherData" , method = RequestMethod.POST)
		public @ResponseBody List<String> Approve_ProceesaorotherData(String a,String status,HttpSession session) {	
			String sus_no = session.getAttribute("roleSusNo").toString();
			String username = session.getAttribute("username").toString();
			List<String> list2 = new ArrayList<String>();
			list2.add(objDAO.approve_ProcessorData(a,sus_no,status,username));
			return list2;
		}
		
		@RequestMapping(value = "/Delete_ProceesaorotherData" , method = RequestMethod.POST)
		public @ResponseBody List<String> Delete_ProceesaorotherData(String a) {	
			List<String> list2 = new ArrayList<String>();
			
			list2.add(objDAO.reject_ProcessorData(a));
			return list2;
		}
}
