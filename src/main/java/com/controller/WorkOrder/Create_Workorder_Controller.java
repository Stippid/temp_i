package com.controller.WorkOrder;

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

import com.controller.commonController.It_CommonController;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.dao.WorkOrder.WOComputing_Asset_Dao;
import com.dao.WorkOrder.WOPeripheral_Asset_Dao;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Create_Workorder_Controller {

	@Autowired
	private WOComputing_Asset_Dao CPD;

	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();

	It_CommonController it_comm = new It_CommonController();

	AllMethodsControllerOrbat m = new AllMethodsControllerOrbat();

	WO_ComputingAsset_Controller work = new WO_ComputingAsset_Controller();

	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	private WOPeripheral_Asset_Dao PAD;

	@RequestMapping(value = "/admin/Create_Workorder", method = RequestMethod.GET)
	public ModelAndView Create_Workorder(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Create_Workorder", roleid);

		String susno = session.getAttribute("roleSusNo").toString();
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleType = session.getAttribute("roleType").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		Mmap.put("WO_NO", work.generateWO_No(susno));
		Mmap.put("getMaintainceAgencyList", it_comm.getMaintainceAgencyList());
		Mmap.put("ComputingAssetList", it_comm.getComputingAssetList());
		Mmap.put("getStatusList", it_comm.getStatusList());
		return new ModelAndView("Create_Workorder_Tiles");
	}

	@RequestMapping(value = "/Computing_Asset_List_ReportDataList", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> Computing_Asset_List_ReportDataList(int startPage, String pageLength,
			String Search, String orderColunm, String orderType, String asset_type, String make_name, String model_name,
			String status, HttpSession sessionUserId)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		return CPD.Computing_Asset_ReportDataListDetails(startPage, pageLength, Search, orderColunm, orderType,
				asset_type, make_name, model_name, status, sessionUserId);
	}

	@RequestMapping(value = "/Computing_Asset_List_TotalCount", method = RequestMethod.POST)
	public @ResponseBody long Computing_Asset_List_TotalCount(HttpSession sessionUserId, String Search,
			String asset_type, String make_name, String model_name, String status) {
		return CPD.Computing_Asset_TotalCountDtl(Search, asset_type, make_name, model_name, status, sessionUserId);
	}

	@RequestMapping(value = "/admin/Search_Workorder", method = RequestMethod.GET)
	public ModelAndView Search_Workorder(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Create_Workorder", roleid);

		String susno = session.getAttribute("roleSusNo").toString();
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleType = session.getAttribute("roleType").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		Mmap.put("getPeripheral", it_comm.getPeripheral());
		Mmap.put("WO_NO", work.generateWO_No(susno));
		Mmap.put("ComputingAssetList", it_comm.getComputingAssetList());
		Mmap.put("getMaintainceAgencyList", it_comm.getMaintainceAgencyList());
		Mmap.put("ComputingAssetList", it_comm.getComputingAssetList());
		Mmap.put("getStatusList", it_comm.getStatusList());
		return new ModelAndView("Search_Workorder_Tiles");
	}

	@RequestMapping(value = "/Pop_UP_Computing_Asset_History_ReportDataList", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> Pop_UP_Computing_Asset_History_ReportDataList(int startPage,
			String pageLength, String Search, String orderColunm, String orderType, String assets_type, String wo_no,
			String wo_dt, String dt_eqpt_reqd_fwd_wksp, String wksp_unit_name, String defects_obs,
			HttpSession sessionUserId)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		return CPD.Pop_UP_Computing_Asset_History_ReportDataList(startPage, pageLength, Search, orderColunm, orderType,
				assets_type, wo_no, wo_dt, dt_eqpt_reqd_fwd_wksp, wksp_unit_name, defects_obs, sessionUserId);
	}

	@RequestMapping(value = "/Pop_UP_Computing_Asset_History_List_TotalCount", method = RequestMethod.POST)
	public @ResponseBody long Pop_UP_Computing_Asset_History_List_TotalCount(HttpSession sessionUserId, String Search,
			String assets_type, String wo_no, String wo_dt, String dt_eqpt_reqd_fwd_wksp, String wksp_unit_name,
			String defects_obs) {
		return CPD.Pop_UP_Computing_Asset_History_List_TotalCount(Search, assets_type, wo_no, wo_dt,
				dt_eqpt_reqd_fwd_wksp, wksp_unit_name, defects_obs, sessionUserId);
	}

	@RequestMapping(value = "/Pop_UP_Peripheral_Asset_History_ReportDataList", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> Pop_UP_Computing_Peripheral_Asset_History_ReportDataList(
			int startPage, String pageLength, String Search, String orderColunm, String orderType, String assets_type,
			String wo_no, String wo_dt, String dt_eqpt_reqd_fwd_wksp, String wksp_unit_name, String defects_obs,
			HttpSession sessionUserId)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		return PAD.Pop_UP_Peripheral_Asset_History_ReportDataList(startPage, pageLength, Search, orderColunm, orderType,
				assets_type, wo_no, wo_dt, dt_eqpt_reqd_fwd_wksp, wksp_unit_name, defects_obs, sessionUserId);
	}

	@RequestMapping(value = "/Pop_UP_Peripheral_Asset_History_List_TotalCount", method = RequestMethod.POST)
	public @ResponseBody long Pop_UP_Computing_Peripheral_Asset_History_List_TotalCount(HttpSession sessionUserId,
			String Search, String assets_type, String wo_no, String wo_dt, String dt_eqpt_reqd_fwd_wksp,
			String wksp_unit_name, String defects_obs) {
		return PAD.Pop_UP_Peripheral_Asset_History_List_TotalCount(Search, assets_type, wo_no, wo_dt,
				dt_eqpt_reqd_fwd_wksp, wksp_unit_name, defects_obs, sessionUserId);
	}

	@RequestMapping(value = "/Download_Peripheral_Asset_WO", method = RequestMethod.POST)
	public ModelAndView Download_Peripheral_Asset_WO(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "Hid_id", required = false) String Hid_id,
			@RequestParam(value = "maintainAgency1", required = false) String wksp_unit_name,
			@RequestParam(value = "wo_dt_per", required = false) String wo_dt,
			@RequestParam(value = "dt_eqpt_reqd_fwd_wksp_per", required = false) String dt_eqpt_reqd_fwd_wksp,
			@RequestParam(value = "type", required = false) String type, String typeReport) {

		String enckey = "commonPwdEncKeys";
		String DcryptedPk = hex_asciiDao.decrypt((String) Hid_id, enckey, session);

		if (type.equals("COMPUTING")) {

			ArrayList<ArrayList<String>> list = CPD.getComputingDetails(DcryptedPk, wksp_unit_name, wo_dt,
					dt_eqpt_reqd_fwd_wksp);

			ArrayList<ArrayList<String>> list1 = CPD.getComputingDetails1(DcryptedPk, wksp_unit_name, wo_dt,
					dt_eqpt_reqd_fwd_wksp);

			List<String> TH = new ArrayList<String>();
			String Heading = session.getAttribute("roleloginName").toString();

			if (typeReport != null && typeReport.equals("pdfL")) {
				if (list.size() > 0) {

					TH.add("Ser No");
					TH.add("Nomenclature");
					TH.add("Reg No/Ser No");
					TH.add("Qty");
					TH.add("BAR CODE");
					TH.add("Remarks");

				}
			}

			return new ModelAndView(new PDF_Peripheral_Asset_Download("P", TH, Heading, list1), "userList", list);

		}

		ArrayList<ArrayList<String>> list = PAD.getPeripheralDetails(DcryptedPk, wksp_unit_name, wo_dt,
				dt_eqpt_reqd_fwd_wksp);

		ArrayList<ArrayList<String>> list1 = PAD.getPeripheralDetails1(DcryptedPk, wksp_unit_name, wo_dt,
				dt_eqpt_reqd_fwd_wksp);

		List<String> TH = new ArrayList<String>();
		/// BISAG V1 220822///
		String Heading = session.getAttribute("roleloginName").toString();
		if (typeReport != null && typeReport.equals("pdfL")) {
			if (list.size() > 0) {

				TH.add("Ser No");
				TH.add("Nomenclature");
				TH.add("Reg No/Ser No");
				TH.add("Qty");
				TH.add("Defects Observed");
				TH.add("BAR CODE");
				TH.add("Remarks");

			}
		}

		return new ModelAndView(new PDF_Peripheral_Asset_Download("P", TH, Heading, list1), "userList", list);

	}

	@RequestMapping(value = "/pop_up_detail_Computing_Asset_History_RecordDataList", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> pop_up_detail_Computing_Asset_History_RecordDataList(
			HttpSession sessionUserId, String id)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

		String enckey = "commonPwdEncKeys";
		String DcryptedPk = hex_asciiDao.decrypt((String) id, enckey, sessionUserId);
		return CPD.pop_up_detail_Computing_Asset_History_RecordDataList(sessionUserId, Integer.parseInt(DcryptedPk));
	}

	@RequestMapping(value = "/pop_up_detail_Peripheral_Asset_History_RecordDataList", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> pop_up_detail_Peripheral_Asset_History_RecordDataList_(
			HttpSession sessionUserId, String id)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

		String enckey = "commonPwdEncKeys";
		String DcryptedPk = hex_asciiDao.decrypt((String) id, enckey, sessionUserId);
		return PAD.pop_up_detail_Peripheral_Asset_History_RecordDataList(sessionUserId, Integer.parseInt(DcryptedPk));
	}

}
