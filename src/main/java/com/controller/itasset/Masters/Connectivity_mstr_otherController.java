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
import com.dao.itasset.masters.Connectivity_DAO;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;

@Controller
@RequestMapping(value = { "admin", "/", "user" })

public class Connectivity_mstr_otherController {
	
	It_CommonController it_comm = new It_CommonController();
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();

	@Autowired
	private Connectivity_DAO conn;

	
	@Autowired
	 RoleBaseMenuDAO roledao;
	
	@RequestMapping(value = "/connecticity_master_otherUrl", method = RequestMethod.GET)
	public ModelAndView connecticity_master_otherUrl(ModelMap model, @RequestParam(value = "msg", required = false) String msg,
			HttpSession session, HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("connecticity_master_otherUrl", roleid);
		
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		model.put("msg", msg);
		
		return new ModelAndView("conncectivity_master_otherTiles");
	}
	@RequestMapping(value = "/getConnectivity_mstr_OtherReportDataList", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getConnectivity_mstr_OtherReportDataList(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String connectivity_type,String status, HttpSession sessionUserId) {
		return conn.DataTableConnectivityOtherList(startPage, pageLength, Search, orderColunm, orderType, connectivity_type,status);
	}

	@RequestMapping(value = "/getConnectivity_mstr_OtherTotalCount", method = RequestMethod.POST)
	public @ResponseBody long getConnectivity_mstr_OtherTotalCount(HttpSession sessionUserId, String Search,String connectivity_type,String status) {
		return conn.DataTableConnectivityOtherTotalCount(Search,connectivity_type,status);
	}
	
	@RequestMapping(value = "/Approve_ConecticityOtherData" , method = RequestMethod.POST)
	public @ResponseBody List<String> Approve_ConecticityOtherData(String a,String status,HttpSession session) {	
		String sus_no = session.getAttribute("roleSusNo").toString();
		String username = session.getAttribute("username").toString();
		List<String> list2 = new ArrayList<String>();
		list2.add(conn.approve_ConnectivityData(a,sus_no,status,username));
		return list2;
	}
	@RequestMapping(value = "/Delete_ConecticityOtherData" , method = RequestMethod.POST)
	public @ResponseBody List<String> Delete_ConecticityOtherData(String a) {	
		List<String> list2 = new ArrayList<String>();
		
		list2.add(conn.reject_ConnectivityData(a));
		return list2;
	}
	
}
