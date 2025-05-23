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

import com.controller.commonController.It_CommonController;
import com.controller.itasset.ExportFile.ExcelUserListReportView;
import com.dao.itasset.masters.Type_of_hw_mstrDAO;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.models.itasset.master.TB_MSTR_TYPE_OF_HW_M;
import com.persistance.util.HibernateUtil;


@Controller
@RequestMapping(value = {"admin","/" ,"user"})
public class Type_of_hw__Other_mstrController {
	It_CommonController it_comm = new It_CommonController();


@Autowired
private Type_of_hw_mstrDAO objDAO;
HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();

@Autowired
RoleBaseMenuDAO roledao;


         @RequestMapping(value = "Type_of_hw_Other_mstr_Url", method = RequestMethod.GET)
         public ModelAndView Type_of_hw_Other_mstr_Url(ModelMap Mmap,HttpSession session,HttpServletRequest request,@RequestParam(value = "msg", required = false) String msg) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, 
         NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException{
        	 
			String roleid = session.getAttribute("roleid").toString();
			Boolean val = roledao.ScreenRedirect("Type_of_hw_Other_mstr_Url", roleid);
			if (val == false) {
				return new ModelAndView("AccessTiles");
			}
			if (request.getHeader("Referer") == null) {
				msg = "";
				//return new ModelAndView("redirect:bodyParameterNotAllow");
			}

        	 
        	 Mmap.put("CategoryList", it_comm.getCategoryList());
             Mmap.put("msg", msg);
         return new ModelAndView("Type_of_hw_other_mstr_tile","type_of_hw_mstrCMD",new TB_MSTR_TYPE_OF_HW_M());
}
 @RequestMapping(value = "/getType_of_hw_other_mstrReportDataList", method = RequestMethod.POST)
 public @ResponseBody List<Map<String, Object>> getType_of_hw_other_mstrReportDataList(int startPage,String pageLength,String Search,String orderColunm,String orderType, String type_of_hw,String peripheral_type,String status,HttpSession sessionUserId) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException{
	
	 return objDAO.getReportListType_of_hw_Other_mstr(startPage,pageLength,Search,orderColunm,orderType,type_of_hw,peripheral_type,status,sessionUserId);
}

 @RequestMapping(value = "/getType_of_hw_Other_mstrTotalCount", method = RequestMethod.POST)
public @ResponseBody long getType_of_hw_Other_mstrTotalCount(HttpSession sessionUserId,String Search,String type_of_hw,String peripheral_type,String status){
	
	return objDAO.getReportListType_of_hw_Other_mstrTotalCount(Search,type_of_hw,peripheral_type,status);
}
 
 
 @RequestMapping(value = "/Approve_type_of_hw_Data" , method = RequestMethod.POST)
	public @ResponseBody List<String> Approve_type_of_hw_Data(String a,String status,HttpSession session) {	
		String sus_no = session.getAttribute("roleSusNo").toString();
		String username = session.getAttribute("username").toString();
		List<String> list2 = new ArrayList<String>();
		list2.add(objDAO.approve_type_of_hw_Data(a,sus_no,status,username));
		return list2;
	}
 @RequestMapping(value = "/Delete_type_of_hw_Data" , method = RequestMethod.POST)
	public @ResponseBody List<String> Delete_MakeotherData(String a) {	
		List<String> list2 = new ArrayList<String>();
		
		list2.add(objDAO.reject_type_of_hw_Data(a));
		return list2;
	}
 
} 
