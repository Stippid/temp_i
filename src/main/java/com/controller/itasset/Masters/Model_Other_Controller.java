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
import com.dao.itasset.masters.ModelDAO;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Model_Other_Controller {
	It_CommonController it_comm = new It_CommonController();
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();

	@Autowired
	private ModelDAO model;

	
	@Autowired
	 RoleBaseMenuDAO roledao;
	
	@RequestMapping(value = "/model_master_otherUrl", method = RequestMethod.GET)
	public ModelAndView model_master_otherUrl(ModelMap model, @RequestParam(value = "msg", required = false) String msg,
			HttpSession session, HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("model_master_otherUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			//return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		model.put("msg", msg);
		return new ModelAndView("model_master_otherTiles");
	}
	@RequestMapping(value = "/getFilteredModelOther", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getFilteredModelOther(int startPage,int pageLength,String Search,String orderColunm,String orderType,
    		String category, String assets_name, String make_name,String model_name,String status,HttpSession sessionUserId) {
		return model.DataTableModelOtherList(startPage, pageLength, Search, orderColunm, orderType,category,assets_name,make_name,model_name,status);
	}

	@RequestMapping(value = "/getTotalModelCountOther", method = RequestMethod.POST)
	public @ResponseBody long getTotalModelCountOther(HttpSession sessionUserId, String Search,String category, String assets_name, String make_name,String model_name,String status) {
		return model.DataTableModelOtherTotalCount(Search, category, assets_name,make_name,model_name,status);

	}
	
	@RequestMapping(value = "/Approve_MpodelotherData" , method = RequestMethod.POST)
	public @ResponseBody List<String> Approve_MpodelotherData(String a,String status,HttpSession session) {	
		String sus_no = session.getAttribute("roleSusNo").toString();
		String username = session.getAttribute("username").toString();
		List<String> list2 = new ArrayList<String>();
		list2.add(model.approve_ModelData(a,sus_no,status,username));
		return list2;
	}
	
	@RequestMapping(value = "/Delete_MpodelotherData" , method = RequestMethod.POST)
	public @ResponseBody List<String> Delete_MpodelotherData(String a) {	
		List<String> list2 = new ArrayList<String>();
		
		list2.add(model.delete_ModelData(a));
		return list2;
	}
	
}
