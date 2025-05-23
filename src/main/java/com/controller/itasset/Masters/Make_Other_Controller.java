package com.controller.itasset.Masters;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

import com.controller.commonController.It_CommonController;
import com.dao.itasset.masters.Make_Dao;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Make_Other_Controller {
	It_CommonController it_comm = new It_CommonController();
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();

	@Autowired
	private Make_Dao make;

	
	@Autowired
	 RoleBaseMenuDAO roledao;
	
	@RequestMapping(value = "/make_master_otherUrl", method = RequestMethod.GET)
	public ModelAndView make_master_otherUrl(ModelMap model, @RequestParam(value = "msg", required = false) String msg,
			HttpSession session, HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("make_master_otherUrl", roleid);
		
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		model.put("msg", msg);
		
		return new ModelAndView("make_master_otherTiles");
	}
	@RequestMapping(value = "/getFilteredMakeOther", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getFilteredMakeOther(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String category, String assets_name, String make_name,String status, HttpSession sessionUserId) {
		return make.DataTableMakeOtherList(startPage, pageLength, Search, orderColunm, orderType, category, assets_name,make_name,status);
	}

	@RequestMapping(value = "/getTotalMakeCountOther", method = RequestMethod.POST)
	public @ResponseBody long getTotalMakeCountOther(HttpSession sessionUserId, String Search, String category, String assets_name,
			String make_name,String status) {
		return make.DataTableMakeOtherTotalCount(Search, category, assets_name,make_name,status);
	}
	
	@RequestMapping(value = "/Approve_MakeotherData" , method = RequestMethod.POST)
	public @ResponseBody List<String> Approve_MakeotherData(String a,String status,HttpSession session) {	
		String sus_no = session.getAttribute("roleSusNo").toString();
		String username = session.getAttribute("username").toString();
		List<String> list2 = new ArrayList<String>();
		list2.add(make.approve_MakeData(a,sus_no,status,username));
		return list2;
	}
	@RequestMapping(value = "/Delete_MakeotherData" , method = RequestMethod.POST)
	public @ResponseBody List<String> Delete_MakeotherData(String a) {	
		List<String> list2 = new ArrayList<String>();
		
		list2.add(make.reject_MakeData(a));
		return list2;
	}
	
}
