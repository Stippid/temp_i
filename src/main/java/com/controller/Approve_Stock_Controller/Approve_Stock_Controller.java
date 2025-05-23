package com.controller.Approve_Stock_Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.controller.commonController.It_CommonController;
import com.controller.login.RoleController;
import com.models.itasset.assets.Assets_Main;


@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Approve_Stock_Controller {
	RoleController roleController =new RoleController();
	
	@Autowired
	It_CommonController it_comm = new It_CommonController();
	
	@RequestMapping(value = "/admin/approve_stock", method = RequestMethod.GET)
	public ModelAndView approve_stock(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {
          Mmap.put("status1", "0");
		  Mmap.put("getSectionNameList", roleController.getSectionNameList());
		  Mmap.put("ComputingAssetList", it_comm.getComputingAssetList());
		  Mmap.put("getServiceable_StateList", it_comm.getServiceable_StateList());
		  Mmap.put("getPeripheral", it_comm.getPeripheral());
		  Mmap.put("getConsumables", it_comm.getConsumableList());
		 
		return new ModelAndView("Create_Approve_Stock_Tiles", "user_mstCMD", new Assets_Main());
	}

}
