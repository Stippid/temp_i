package com.controller.Computing;

import java.io.BufferedOutputStream;
import java.io.File;

//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.security.InvalidAlgorithmParameterException;
//import java.security.InvalidKeyException;
//import java.security.NoSuchAlgorithmException;
//import java.security.spec.InvalidKeySpecException;
//import java.sql.SQLException;
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Locale;
//import java.util.Map;
//
//import javax.crypto.BadPaddingException;
//import javax.crypto.Cipher;
//import javax.crypto.IllegalBlockSizeException;
//import javax.crypto.NoSuchPaddingException;
//import javax.servlet.ServletContext;
//import javax.servlet.ServletOutputStream;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.hibernate.Query;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.controller.DateWithTimestamp.DateWithTimeStampController;
//import com.controller.commonController.It_CommonController;
//import com.controller.itasset.ExportFile.ExcelComputingUserListReportView;
//import com.controller.itasset.ExportFile.ExcelUserListReportView;
//import com.controller.login.RoleController;
//import com.controller.orbat.AllMethodsControllerOrbat;
//import com.controller.tms.AllMethodsControllerTMS;
//import com.controller.validation.ValidationController;
//import com.dao.Assets.computing_assets_DAO;
//import com.dao.login.HexatoAsciiDAO;
//import com.dao.login.HexatoAsciiDAOImpl;
//import com.dao.login.RoleBaseMenuDAO;
//import com.models.assets.Assets_Child;
//import com.models.assets.Assets_Main;
//import com.models.itasset.master.TB_MASTER_MAKE;
//import com.models.itasset.master.TB_MASTER_MODEL;
//import com.models.itasset.master.TB_MASTER_SUPPLY_MASTER;
//import com.models.itasset.master.TB_MSTR_OFFICE_M;
//import com.models.itasset.master.TB_MSTR_OS_M;
//import com.models.itasset.master.TB_MSTR_PROCESSOR_TYPE_M;
//import com.persistance.util.HibernateUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FilenameUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.controller.DateWithTimestamp.DateWithTimeStampController;
import com.controller.commonController.It_CommonController;
import com.controller.itasset.ExportFile.ExcelComputingUserListReportView;
import com.controller.itasset.ExportFile.ExcelUserListReportView;
import com.controller.login.RoleController;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.tms.AllMethodsControllerTMS;
import com.controller.validation.CheckFileFormatValidation;
import com.controller.validation.ValidationController;
import com.dao.Assets.computing_assets_DAO;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.models.itasset.assets.Assets_Child;
import com.models.itasset.assets.Assets_Main;
import com.models.itasset.assets.Assign_Asset_to_user;
import com.models.itasset.master.TB_MASTER_MAKE;
import com.models.itasset.master.TB_MASTER_MODEL;
import com.models.itasset.master.TB_MASTER_SUPPLY_MASTER;
import com.models.itasset.master.TB_MSTR_OFFICE_M;
import com.models.itasset.master.TB_MSTR_OS_M;
import com.models.itasset.master.TB_MSTR_PROCESSOR_TYPE_M;
import com.persistance.util.HibernateUtil;
@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class ComputingController {
	RoleController roleController =new RoleController();	

	@Autowired
	private computing_assets_DAO cd;

	@Autowired
	private RoleBaseMenuDAO roledao;

	ValidationController validation = new ValidationController();
	@Autowired
	It_CommonController it_comm = new It_CommonController();
	@Autowired
	AllMethodsControllerOrbat com = new AllMethodsControllerOrbat();
	@Autowired
	AllMethodsControllerTMS allt = new AllMethodsControllerTMS();
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();

	@RequestMapping(value = "/admin/MainAssertsUrl", method = RequestMethod.GET)
	public ModelAndView MainAssertsUrl(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("MainAssertsUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		Mmap.put("username", session.getAttribute("username").toString());
		Mmap.put("AntivirusList", it_comm.getAntivirus());
		Mmap.put("ComputingAssetList", it_comm.getComputingAssetList());
		Mmap.put("processor_typeList", it_comm.getProcessorList());
		Mmap.put("ramList", it_comm.getRamList());
		Mmap.put("hddList", it_comm.getHDDList());
		Mmap.put("sddList", it_comm.getSDDList());
		Mmap.put("osList", it_comm.getOperatingSystem());
		Mmap.put("officeList", it_comm.getOffice());
		Mmap.put("osFirmwareList", it_comm.getOsFirmware());
		Mmap.put("dplyEnvList", it_comm.getdplyEnv());
		Mmap.put("UNServiceableList", it_comm.UNServiceableList());
		Mmap.put("getConnectingTechList", it_comm.getConnectingTechList());
		Mmap.put("getEthernetPortList", it_comm.getEthernetPortList());
		Mmap.put("getServiceable_StateList", it_comm.getServiceable_StateList());
		Mmap.put("getBudgetHeadList", it_comm.getBudgetHeadList());
		 Mmap.put("getSectionNameList", roleController.getSectionNameList());
		 Mmap.put("getMaintainceAgencyList", it_comm.getMaintainceAgencyList());
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		Mmap.put("roleSusNo", roleSusNo);
		
		Mmap.put("getTargetUnitNameList", allt.getTargetUnitNameList(roleSusNo, session));

		Mmap.put("msg", msg);
		return new ModelAndView("MainAssetsTiles", "MainAssetsCmd", new Assets_Main());
	}

	@RequestMapping(value = "/MainAssetsAction", method = RequestMethod.POST)
	public ModelAndView MainAssetsAction(@ModelAttribute("MainAssetsCmd") Assets_Main asset,
	@RequestParam(value = "msg", required = false) String msg1,
	@RequestParam(value = "u_file1", required = false) MultipartFile u_file,
	@RequestParam(value = "make_mstr_id", required = false) String make_mstr_id,
	@RequestParam(value = "model_mstr_id", required = false) String model_mstr_id,
	@RequestParam(value = "pro_type_id", required = false) String pro_type_id,
	@RequestParam(value = "office_mstr_id", required = false) String office_mstr_id,
	@RequestParam(value = "br_certificate1", required = false) MultipartFile br_certificate,
	 @RequestParam(value = "screenUrl", required = false) String screenurl,
	@RequestParam(value = "os_mstr_id", required = false) String os_mstr_id, HttpServletRequest request,
	ModelMap model, HttpSession session) throws ParseException {
	String roleid = session.getAttribute("roleid").toString();
	Boolean val = roledao.ScreenRedirect("MainAssertsUrl", roleid);
	if (val == false) {
	return new ModelAndView("AccessTiles");
	}
	if (request.getHeader("Referer") == null) {
	msg1 = "";

	}

	DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
	Date date = new Date();
	Date warrenty_dt = null;
	Date unsv_from_dt1 = null;
	Date proc_dt = null;
	Date Antivirus_expry = null;
	int id = asset.getId() > 0 ? asset.getId() : 0;
	String front_view_image1Ext = FilenameUtils.getExtension(u_file.getOriginalFilename()).toUpperCase();
	String rediurl = "";

	if (id == 0) {
	rediurl = "redirect:MainAssertsUrl";
	} else {
	rediurl = "redirect:Search_Computing_AssetsUrl";
	}

	String section = request.getParameter("section");
	String asset_type = request.getParameter("asset_type");
	String make_name = request.getParameter("make_name");
	String model_name = request.getParameter("model_name");
	String processor_type = request.getParameter("processor_type");
	String ram_capi = request.getParameter("ram_capi");
	String hdd_capi = request.getParameter("hdd_capi");
	String hsd_capi = request.getParameter("ssd_capi");
	String operating_system = request.getParameter("operating_system");
	String antiviruscheck = request.getParameter("antiviruscheck");
	String approveois = request.getParameter("approveois");
	String antivirus = request.getParameter("antivirus");
	String dply_envt = request.getParameter("dply_envt");
	String warranty = request.getParameter("warranty_dt");
	String warrantycheck = request.getParameter("warrantycheck");
	String cd_drive = request.getParameter("cd_drive");
	String s_state = request.getParameter("s_state");
	String unserviceable_state = request.getParameter("unserviceable_state");
	String b_cost = request.getParameter("b_cost");
	String proc_date = request.getParameter("proc_dt");
	String unsv_from_date = request.getParameter("unsv_from_dt1");
	String b_head = request.getParameter("b_head");

	int assetcount = Integer.parseInt(request.getParameter("assetcount"));
	String brand_other = request.getParameter("brand_other");
	String model_other = request.getParameter("model_other");
	String pro_type_other = request.getParameter("pro_type_other");
	String os_other = request.getParameter("os_other");
	String office_other = request.getParameter("office_other");
	String crv_no = request.getParameter("crv_no");
	String ssdcheck = request.getParameter("ssdcheck");
	String username = request.getParameter("user_name");
	String maintainAgency = request.getParameter("maintainAgency");
	String supplier = request.getParameter("supplier");
	String u_file1 = request.getParameter("u_file1");
	String br_certificate1 = request.getParameter("br_certificate1");
	String antivirus_expry =request.getParameter("antivirus_expry1");
	String supplierId =request.getParameter("supplierId");
	DateWithTimeStampController timestamp = new DateWithTimeStampController();

	Assets_Main T_asset = new Assets_Main();
	Assets_Child C_asset = new Assets_Child();

	String roleSusNo = session.getAttribute("roleSusNo").toString();
	String roleAccess = session.getAttribute("roleAccess").toString();


	if (section == null || section.equals("0") || section.equals("")) {
	model.put("msg", "Please Enter Section ");
	return new ModelAndView(rediurl);
	}


	if (asset_type == null || asset_type.equals("0") || asset_type.equals("")) {
	model.put("msg", "Please Select Computing Assets Type ");
	return new ModelAndView(rediurl);
	}

	if (make_name == null || make_name.equals("0") || make_name.equals("")) {
	model.put("msg", "Please Select Make/Brand Name ");
	return new ModelAndView(rediurl);
	}

	if (model_name == null || model_name.equals("0") || model_name.equals("")) {
	model.put("msg", "Please Select Model Name ");
	return new ModelAndView(rediurl);
	}

	if (processor_type == null || processor_type.equals("") || processor_type.equals("0")) {
	model.put("msg", "Please Select Processor Type");
	return new ModelAndView(rediurl);
	}

	if (ram_capi == null || ram_capi.equals("") || ram_capi.equals("0")) {
	model.put("msg", "Please Select RAM Capacity ");
	return new ModelAndView(rediurl);
	}

	if (hdd_capi == null || hdd_capi.equals("") || hdd_capi.equals("0")) {
	model.put("msg", "Please Select HDD Capacity");
	return new ModelAndView(rediurl);
	}

	if (ssdcheck != null && ssdcheck.toUpperCase().equals("YES")) {
	if (hsd_capi == null || hsd_capi.equals("") || hsd_capi.equals("0")) {
	model.put("msg", "Please Enter SSD");
	return new ModelAndView(rediurl);
	}
	}
	if (operating_system == null || operating_system.equals("") || operating_system.equals("0")) {
	model.put("msg", "Please Select Operating System ");
	return new ModelAndView(rediurl);
	}

	if (operating_system == null || operating_system.equals("") || operating_system.equals("0")) {
	model.put("msg", "Please Select Operating System ");
	return new ModelAndView(rediurl);
	}
	if (antiviruscheck != null && antiviruscheck.toUpperCase().equals("YES")) {
	if (antivirus == null || antivirus.equals("") || antivirus.equals("0")) {
	model.put("msg", "Please Select Antivirus.");
	return new ModelAndView(rediurl);
	}
	if (antivirus_expry == null || antivirus_expry.equals("") || antivirus_expry.equals("DD/MM/YYYY")) {
	model.put("msg", "Please Antivirus Expiry Date.");
	return new ModelAndView(rediurl);
	}
	}
	if (antivirus_expry != null && !antivirus_expry.equals("") && !antivirus_expry.equals("DD/MM/YYYY")) {
		Antivirus_expry = format.parse(antivirus_expry);
		}


	if (dply_envt == null || dply_envt.equals("") || dply_envt.equals("0")) {
	model.put("msg", "Please Select Dply Envt as Applicable ");
	return new ModelAndView(rediurl);
	}

	if (warrantycheck != null && warrantycheck.toUpperCase().equals("YES")) {
	if (warranty == null || warranty.equals("") || warranty.equals("DD/MM/YYYY")) {
	model.put("msg", "Please Select Warranty");
	return new ModelAndView(rediurl);
	} else {
	warrenty_dt = format.parse(warranty);
	}
	}

	if (s_state.equals("2")) {
	if (unserviceable_state == null || unserviceable_state.equals("") || unserviceable_state.equals("0")) {
	model.put("msg", "Please Select Un-Serviceable State");
	return new ModelAndView(rediurl);
	}


	if (unsv_from_date == null || unsv_from_date.equals("") || unsv_from_date.equals("0")) {
	model.put("msg", "Please Select Un-Serviceable State");
	return new ModelAndView(rediurl);
	}

	if (maintainAgency == null || maintainAgency.equals("") || maintainAgency.equals("0")) {
	model.put("msg", "Please Select Maintenance Agency");
	return new ModelAndView(rediurl);
	}

	}

	if (unsv_from_date != null && !unsv_from_date.equals("") && !unsv_from_date.equals("DD/MM/YYYY")) {
	unsv_from_dt1 = format.parse(unsv_from_date);
	}

	for (int j = 1; j <= assetcount; j++) {

	String machine_no = request.getParameter("machine_number" + j);

	if (machine_no == null || machine_no == "" || machine_no.trim().equals("")) {
	model.put("msg", "Please Enter Machine Number");
	return new ModelAndView(rediurl);
	}

	if (validation.MachineNumberLength(machine_no) == false) {
	model.put("msg", validation.MachineNumber);
	return new ModelAndView(rediurl);
	}

	for (int k = j + 1; k <= assetcount; k++) {
	String machine_n = request.getParameter("machine_number" + k);
	if (machine_no.equals(machine_n)) {
	model.put("msg", "You Have entered Duplicate machine number");
	return new ModelAndView(rediurl);
	}
	}

	}
	

	if (proc_date != null && !proc_date.equals("") && !proc_date.equals("DD/MM/YYYY")) {
	proc_dt = format.parse(proc_date);
	}
	//
	if (b_head == null || b_head.equals("0")) {
	model.put("msg", "Please Select Budget Head");
	return new ModelAndView(rediurl);
	}


	if (crv_no == null || crv_no.equals("") || crv_no.equals("0")) {
	model.put("msg", "Please Enter CRV No.");
	return new ModelAndView(rediurl);
	}
	if (approveois != null && approveois.toUpperCase().equals("NO")) {
		if (supplier == null || supplier.equals("") || supplier.equals("")) {
			model.put("msg", "Please Enter Supplier Name.");
	return new ModelAndView(rediurl);
	}
	}
	String username2 = session.getAttribute("username").toString();
	Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = sessionHQL.beginTransaction();
	TB_MASTER_MAKE make_mstr = new TB_MASTER_MAKE();
	String fname = "";
	int userid = Integer.parseInt(session.getAttribute("userId").toString());
	String doc_path1Ext = FilenameUtils.getExtension(u_file.getOriginalFilename());
	

	try {

	for (int i = 1; i <= assetcount; i++) {
	String machine_number = request.getParameter("machine_number" + i);
	String hqlUpdate = "select count(id) from Assets_Main where id!=:id and machine_number=:machine_number ";
	Query query2 = sessionHQL.createQuery(hqlUpdate).setInteger("id", id)
	.setString("machine_number", machine_number).setMaxResults(1);
	Long c22 = (Long) query2.uniqueResult();
	if (c22 != null && c22 > 0) {
	model.put("msg", "Computing Asset With Machine Number (" + machine_number + ") Already Exist");
	return new ModelAndView(rediurl);
	}
	}
	if (id == 0) {
	for (int i = 1; i <= assetcount; i++) {
		if (!u_file.isEmpty()) {
			// code modify by Paresh on 05/05/2020
			try {
				byte[] bytes = u_file.getBytes();
				CheckFileFormatValidation fileValidation = new CheckFileFormatValidation();
				if(fileValidation.check_PDF_File(bytes)) {
					String itAssetsFilePath = session.getAttribute("autismFilePath").toString();
		
					File dir = new File(itAssetsFilePath);
					if (!dir.exists()) {
						dir.mkdirs();
					}
					fname = dir.getAbsolutePath() + File.separator +timestamp.currentDateWithTimeStampString()+"_"+userid+"_ITASSETSDOC.PDF";
					File serverFile = new File(fname);	               
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);	                
					stream.close();
					T_asset.setU_file(fname);
				}else {
					model.put("msg", "Only *.pdf file extension allowed");
					return new ModelAndView(rediurl);
				}
			}
			catch (Exception e) {
	       }
		}
		if (!br_certificate.isEmpty()) {
			try {
				byte[] bytes = br_certificate.getBytes();
				CheckFileFormatValidation fileValidation = new CheckFileFormatValidation();
				if(fileValidation.check_PDF_File(bytes)) {
					String itAssetsFilePath = session.getAttribute("autismFilePath").toString();
					File dir = new File(itAssetsFilePath);
					if (!dir.exists()) {
						dir.mkdirs();
					}
					fname = dir.getAbsolutePath() + File.separator +timestamp.currentDateWithTimeStampString()+"_"+userid+"_ITASSETSDOC.PDF";
					File serverFile = new File(fname);	               
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);	                
					stream.close();
					T_asset.setBr_certificate(fname);
				}else {
					model.put("msg", "Only *.pdf file extension allowed");
					return new ModelAndView(rediurl);
				}
			}
			catch (Exception e) {
	       }
		}

	
	String machine_number = request.getParameter("machine_number" + i);
	String mac_address = request.getParameter("mac_address" + i);
	String ip_address = request.getParameter("ip_address" + i);
	String gem_no = request.getParameter("gem_no" + i);

	C_asset.setService_state(Integer.parseInt(s_state));
	C_asset.setWarranty(warrenty_dt);
	if (s_state.equals("2") || s_state == "2") {
	C_asset.setUnservice_state(asset.getUnserviceable_state());
	C_asset.setUnsv_from_dt(unsv_from_dt1);
	} else if (s_state.equals("1") || s_state == "1") {
	C_asset.setService_state(Integer.parseInt(s_state));
	}
	T_asset.setAsset_type(asset.getAsset_type());
	
	T_asset.setMachine_number(machine_number.trim());
	T_asset.setMac_address(mac_address);
	T_asset.setIp_address(ip_address);
	T_asset.setGem_no(gem_no);
	T_asset.setProcessor_type(asset.getProcessor_type());
	T_asset.setRam_capi(asset.getRam_capi());
	T_asset.setHdd_capi(asset.getHdd_capi());
	T_asset.setSsd_capi(asset.getSsd_capi());
	T_asset.setOperating_system(asset.getOperating_system());
	T_asset.setOffice(asset.getOffice());
	
	T_asset.setDply_envt(asset.getDply_envt());
	T_asset.setAntiviruscheck(asset.getAntiviruscheck());
	T_asset.setWarranty(warrenty_dt);
	T_asset.setWarrantycheck(asset.getWarrantycheck());
	T_asset.setAntivirus(asset.getAntivirus());
	T_asset.setProc_date(proc_dt);
	T_asset.setB_head(b_head);

	T_asset.setB_cost(b_cost);
	T_asset.setCd_drive(cd_drive);
	T_asset.setCrv_no(crv_no);
	
	T_asset.setSupplier_name(supplierId);

	T_asset.setMaintainagency(maintainAgency);
	T_asset.setModel_name(asset.getModel_name());
	T_asset.setMake_name(asset.getMake_name());
	T_asset.setAntivirus_expry(Antivirus_expry);
                                  
	TB_MASTER_MODEL model_mstr = new TB_MASTER_MODEL();
	if (!brand_other.equals("")) {
	List<String> brand_other1 = it_comm.getBrandOtherList(request);
	if (brand_other1.size() > 0) {
	model.put("msg", "This Make Name Already Exists.");
	return new ModelAndView(rediurl);
	}
	make_mstr.setMake_name(brand_other);
	make_mstr.setStatus(0);
	make_mstr.setCategory(1);
	make_mstr.setAssets_name(Integer.parseInt(asset_type));
	make_mstr.setCreated_by(username);
	make_mstr.setCreated_dt(date);
	sessionHQL.save(make_mstr);
	String make_id = String.valueOf(make_mstr.getId());
	T_asset.setBrand_other(make_id);
	}

	if (!model_other.equals("")) {

	int mak_id = make_mstr.getId();
	List<String> model_other1 = it_comm.getBrandOtherList(request);

	if (model_other1.size() > 0) {
	model.put("msg", "This Model Name Already Exists.");
	return new ModelAndView(rediurl);
	}

	model_mstr.setModel_name(model_other.toUpperCase());
	model_mstr.setAssets_name(Integer.parseInt(asset_type));
	model_mstr.setStatus(0);
	model_mstr.setCategory(1);
	model_mstr.setCreated_by(username);
	model_mstr.setCreated_dt(date);
	model_mstr.setMake_name(mak_id);
	sessionHQL.save(model_mstr);
	String model_id = String.valueOf(model_mstr.getId());
	T_asset.setModel_other(model_id);
	}

	TB_MSTR_PROCESSOR_TYPE_M pro_mstr = new TB_MSTR_PROCESSOR_TYPE_M();
	if (!pro_type_other.equals("")) {

	List<String> pro_type_other1 = it_comm.getBrandOtherList(request);

	if (pro_type_other1.size() > 0) {
	model.put("msg", "This ProcessorType Already Exists.");
	return new ModelAndView(rediurl);
	}

	pro_mstr.setProcessor_type(pro_type_other);
	pro_mstr.setStatus(0);
	sessionHQL.save(pro_mstr);
	String pro_id = String.valueOf(pro_mstr.getId());
	T_asset.setPro_type_other(pro_id);
	}

	TB_MSTR_OS_M os_mstr = new TB_MSTR_OS_M();
	if (!os_other.equals("")) {
	List<String> os_other1 = it_comm.getBrandOtherList(request);

	if (os_other1.size() > 0) {
	model.put("msg", "This OperatingSystem Already Exists.");
	return new ModelAndView(rediurl);
	}
	os_mstr.setOs(os_other);
	os_mstr.setStatus(0);
	sessionHQL.save(os_mstr);
	String os_id = String.valueOf(os_mstr.getId());
	T_asset.setOs_other(os_id);
	}

	TB_MSTR_OFFICE_M office_mstr = new TB_MSTR_OFFICE_M();
	if (!office_other.equals("")) {
	List<String> office_other1 = it_comm.getBrandOtherList(request);
	if (office_other1.size() > 0) {
	model.put("msg", "This Offices Already Exists.");
	return new ModelAndView(rediurl);
	}

	office_mstr.setOffice(office_other);
	office_mstr.setStatus(0);
	sessionHQL.save(office_mstr);
	String office_id = String.valueOf(office_mstr.getId());
	T_asset.setOffice_other(office_id);
	}

	T_asset.setS_state(s_state);

	if (s_state.equals("2") || s_state == "2") {
	T_asset.setUnserviceable_state(asset.getUnserviceable_state());
	T_asset.setUnsv_from_dt(unsv_from_dt1);
	}

	T_asset.setDimension(asset.getDimension());
	T_asset.setConnecting_tech(asset.getConnecting_tech());
	
	T_asset.setCreated_by(username);
	T_asset.setCreated_date(date);
	T_asset.setSection(section);
	T_asset.setCreated_by(username2);
	T_asset.setStatus(0);
	C_asset.setStatus(0);
	int c = (Integer) sessionHQL.save(T_asset);
	C_asset.setP_id(c);
	sessionHQL.save(C_asset);
	sessionHQL.flush();
	sessionHQL.clear();
	}

	model.put("msg", "Data Saved Successfully.");
	} else {

	//String model_number = request.getParameter("model_number1");
	String machine_number = request.getParameter("machine_number1");
	String mac_address = request.getParameter("mac_address1");
	String ip_address = request.getParameter("ip_address1");
	String gem_no = request.getParameter("gem_no1");

	String fname1 = "";

	String hql = "update Assets_Main set modified_by=:modified_by ,status=:status,modified_date=:modified_date "
	+ ",asset_type=:asset_type  ,machine_number=:machine_number ,mac_address=:mac_address "
	+ ",ip_address=:ip_address ,processor_type=:processor_type ,ram_capi=:ram_capi ,hdd_capi=:hdd_capi,ssd_capi=:hsd_capi ,operating_system=:operating_system "
	+ ",office=:office ,dply_envt=:dply_envt ,antiviruscheck=:antiviruscheck ,antivirus=:antivirus"
	+ ",warranty=:warranty ,warrantycheck=:warrantycheck,b_head=:b_head ,b_code=:b_code ,b_cost=:b_cost ,cd_drive=:cd_drive ,connecting_tech=:connecting_tech"
	+ ",s_state=:s_state ,model_name=:model_name ,make_name=:make_name ,unserviceable_state=:unserviceable_state,unsv_from_dt=:unsv_from_dt,ethernet_port=:ethernet_port,"
	+ "proc_date=:proc_date,section=:section,gem_no=:gem_no,crv_no=:crv_no ,supplier_name=:supplier_name,username=:username,maintainagency=:maintainAgency,antivirus_expry=:antivirus_expry " + " where   id=:id";
	Query query = sessionHQL.createQuery(hql).setInteger("status", 0).setInteger("id", id)
	.setString("modified_by", username).setTimestamp("modified_date", date)
	.setInteger("asset_type", asset.getAsset_type())
	.setString("machine_number", machine_number).setString("mac_address", mac_address)
	.setString("ip_address", ip_address).setInteger("processor_type", asset.getProcessor_type())
	.setInteger("ram_capi", asset.getRam_capi()).setInteger("hdd_capi", asset.getHdd_capi())
	.setInteger("operating_system", asset.getOperating_system()).setInteger("hsd_capi", asset.getSsd_capi())
	.setInteger("office", asset.getOffice())
	.setInteger("dply_envt", asset.getDply_envt()).setString("crv_no", crv_no)
	.setString("antiviruscheck", asset.getAntiviruscheck())
	.setInteger("antivirus", asset.getAntivirus()).setTimestamp("warranty", warrenty_dt)
	.setString("warrantycheck", asset.getWarrantycheck()).setString("b_head", asset.getB_head())
	.setString("b_code", asset.getB_code()).setString("b_cost", asset.getB_cost())
	.setString("cd_drive", asset.getCd_drive()).setString("s_state", asset.getS_state())
	.setInteger("model_name", asset.getModel_name()).setInteger("make_name", asset.getMake_name())
	.setInteger("connecting_tech", asset.getConnecting_tech())
	.setInteger("ethernet_port", asset.getEthernet_port())
	.setInteger("unserviceable_state", asset.getUnserviceable_state())
	.setTimestamp("unsv_from_dt", unsv_from_dt1).setTimestamp("proc_date", proc_dt)
	.setString("section", section).setString("gem_no", gem_no).setString("supplier_name",supplierId).setString("username", username).setString("maintainAgency",maintainAgency).setTimestamp("antivirus_expry", Antivirus_expry);

	String hql1 = "update   Assets_Child set modified_by=:modified_by,status=:status,modified_dt=:modified_dt,service_state=:service_state,"
	+ "unservice_state=:unservice_state,unsv_from_dt=:unsv_from_dt,warrantycheck=:warrantycheck,warranty=:warranty where p_id=:p_id\r\n";

	Query query1 = sessionHQL.createQuery(hql1).setInteger("status", 0).setInteger("p_id", id)
	.setString("modified_by", username).setTimestamp("modified_dt", date)
	.setInteger("service_state", Integer.parseInt(s_state))
	.setInteger("unservice_state", Integer.parseInt(unserviceable_state))
	.setTimestamp("unsv_from_dt", unsv_from_dt1).setTimestamp("warranty", warrenty_dt)
	.setString("warrantycheck", asset.getWarrantycheck());

	String msg = query.executeUpdate() > 0 ? "update" : "0";
	if (!make_mstr_id.equals("")) {
	String hql2 = "update   TB_MASTER_MAKE set modified_by=:modified_by,status=:status,modified_dt=:modified_dt,make_name=:make_name where id=:make_id";
	Query query2 = sessionHQL.createQuery(hql2).setInteger("status", 0)
	.setInteger("make_id", Integer.parseInt(make_mstr_id)).setString("modified_by", username)
	.setTimestamp("modified_dt", date).setString("make_name", brand_other.toUpperCase());
	msg = query2.executeUpdate() > 0 ? "update" : "0";
	}

	if (!model_mstr_id.equals("")) {
	String hql3 = "update   TB_MASTER_MODEL set modifed_by=:modified_by,status=:status,modified_dt=:modified_dt,model_name=:model_name where id=:model_id";
	Query query3 = sessionHQL.createQuery(hql3).setInteger("status", 0)
	.setInteger("model_id", Integer.parseInt(model_mstr_id)).setString("modified_by", username)
	.setTimestamp("modified_dt", date).setString("model_name", model_other.toUpperCase());
	msg = query3.executeUpdate() > 0 ? "update" : "0";
	}

	if (!pro_type_id.equals("")) {
	String hql4 = "update   TB_MSTR_PROCESSOR_TYPE_M set modified_by=:modified_by,status=:status,modified_date=:modified_dt,processor_type=:processor_type where id=:pro_id";
	Query query4 = sessionHQL.createQuery(hql4).setInteger("status", 0)
	.setInteger("pro_id", Integer.parseInt(pro_type_id)).setString("modified_by", username)
	.setTimestamp("modified_dt", date)
	.setString("processor_type", pro_type_other.toUpperCase());
	msg = query4.executeUpdate() > 0 ? "update" : "0";
	}

	if (!os_mstr_id.equals("")) {
	String hql4 = "update   TB_MSTR_OS_M set modified_by=:modified_by,status=:status,modified_date=:modified_dt,os=:os where id=:os_id";
	Query query4 = sessionHQL.createQuery(hql4).setInteger("status", 0)
	.setInteger("os_id", Integer.parseInt(os_mstr_id)).setString("modified_by", username)
	.setTimestamp("modified_dt", date).setString("os", os_other.toUpperCase());
	msg = query4.executeUpdate() > 0 ? "update" : "0";
	}

	if (!office_mstr_id.equals("")) {
	String hql4 = "update   TB_MSTR_OFFICE_M set modified_by=:modified_by,status=:status,modified_date=:modified_dt,office=:office where id=:office_id";
	Query query4 = sessionHQL.createQuery(hql4).setInteger("status", 0)
	.setInteger("office_id", Integer.parseInt(office_mstr_id))
	.setString("modified_by", username).setTimestamp("modified_dt", date)
	.setString("office", office_other.toUpperCase());
	msg = query4.executeUpdate() > 0 ? "update" : "0";
	}

	msg = query1.executeUpdate() > 0 ? "update" : "0";
	if (msg.equals("update")) {
	model.put("msg", "Data Updated Successfully.");
	
	return new ModelAndView("redirect:"+screenurl);

	 
	}
	 model.put("MainAssetsCmd", T_asset); // Add the asset to the model

	  
	tx.commit();
	}} catch (RuntimeException e) {
	try {
	tx.rollback();
	model.put("msg", "roll back transaction");
	} catch (RuntimeException rbe) {
	model.put("msg", "Couldn't roll back transaction " + rbe);
	}
	throw e;
	} finally {
	if (sessionHQL != null) {
	sessionHQL.close();
	}
	}
	return new ModelAndView(rediurl);
	}

	@RequestMapping(value = "/admin/ComputingAssertsEdit", method = RequestMethod.POST)
	public ModelAndView ComputingAssertsEdit(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "updateid", required = false) int id,
			 @RequestParam(value = "screenurl", required = false) String screenurl
	       
) throws ParseException 
	{

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_Computing_AssetsUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Assets_Main assetupd = (Assets_Main) sessionHQL.get(Assets_Main.class, id);

		if (((TB_MASTER_MAKE) sessionHQL.get(TB_MASTER_MAKE.class, assetupd.getMake_name())).getMake_name()
				.equals("OTHERS")) {
			TB_MASTER_MAKE make_mstr1 = (TB_MASTER_MAKE) sessionHQL.get(TB_MASTER_MAKE.class,
					Integer.parseInt(assetupd.getBrand_other()));
			Mmap.put("make_mstr_other", make_mstr1.getMake_name());
			Mmap.put("make_mstr_id", make_mstr1.getId());
		}

		if (((TB_MASTER_MODEL) sessionHQL.get(TB_MASTER_MODEL.class, assetupd.getModel_name())).getModel_name()
				.equals("OTHERS")) {
			TB_MASTER_MODEL model_mstr1 = (TB_MASTER_MODEL) sessionHQL.get(TB_MASTER_MODEL.class,
					Integer.parseInt(assetupd.getModel_other()));
			Mmap.put("model_mstr_other", model_mstr1.getModel_name());
			Mmap.put("model_mstr_id", model_mstr1.getId());
		}

		if (((TB_MSTR_PROCESSOR_TYPE_M) sessionHQL.get(TB_MSTR_PROCESSOR_TYPE_M.class, assetupd.getProcessor_type()))
				.getProcessor_type().equals("OTHERS")) {
			TB_MSTR_PROCESSOR_TYPE_M pro_mstr1 = (TB_MSTR_PROCESSOR_TYPE_M) sessionHQL
					.get(TB_MSTR_PROCESSOR_TYPE_M.class, Integer.parseInt(assetupd.getPro_type_other()));
			Mmap.put("pro_type_other", pro_mstr1.getProcessor_type());
			Mmap.put("pro_type_id", pro_mstr1.getId());
		}
		if(assetupd.getOffice()!=0)
		{

		if (((TB_MSTR_OFFICE_M) sessionHQL.get(TB_MSTR_OFFICE_M.class, assetupd.getOffice())).getOffice()
				.equals("OTHERS")) {
			TB_MSTR_OFFICE_M office_mstr1 = (TB_MSTR_OFFICE_M) sessionHQL.get(TB_MSTR_OFFICE_M.class,
					Integer.parseInt(assetupd.getOffice_other()));
			Mmap.put("office_mstr_other", office_mstr1.getOffice());
			Mmap.put("office_mstr_id", office_mstr1.getId());
		}
		}

		if (((TB_MSTR_OS_M) sessionHQL.get(TB_MSTR_OS_M.class, assetupd.getOperating_system())).getOs()
				.equals("OTHERS")) {
			TB_MSTR_OS_M os_mstr1 = (TB_MSTR_OS_M) sessionHQL.get(TB_MSTR_OS_M.class,
					Integer.parseInt(assetupd.getOs_other()));
			Mmap.put("os_mstr_other", os_mstr1.getOs());
			Mmap.put("os_mstr_id", os_mstr1.getId());
		}

		// Assets_Main
		// u_file_path=(Assets_Main)sessionHQL.get(Assets_Main.class,assetupd.getU_file());
		// Mmap.put("u_file_path",u_file_path.getU_file());

		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		Mmap.put("roleSusNo", roleSusNo);
		
		// String mnhFilePath = session.getAttribute("itAssetsFilePath").toString();
		Mmap.put("AntivirusList", it_comm.getAntivirus());
		Mmap.put("ComputingAssetList", it_comm.getComputingAssetList());
		Mmap.put("processor_typeList", it_comm.getProcessorList());
		Mmap.put("ramList", it_comm.getRamList());
		Mmap.put("hddList", it_comm.getHDDList());
		Mmap.put("sddList", it_comm.getSDDList());
		Mmap.put("osList", it_comm.getOperatingSystem());
		Mmap.put("officeList", it_comm.getOffice());
		Mmap.put("osFirmwareList", it_comm.getOsFirmware());
		Mmap.put("dplyEnvList", it_comm.getdplyEnv());
		Mmap.put("UNServiceableList", it_comm.UNServiceableList());
		Mmap.put("getConnectingTechList", it_comm.getConnectingTechList());
		Mmap.put("getEthernetPortList", it_comm.getEthernetPortList());
		Mmap.put("getServiceable_StateList", it_comm.getServiceable_StateList());
		Mmap.put("getBudgetHeadList", it_comm.getBudgetHeadList());
		Mmap.put("getSectionNameList", roleController.getSectionNameList());
		 Mmap.put("getMaintainceAgencyList", it_comm.getMaintainceAgencyList());
		 Mmap.put("screen_url", screenurl);
		 return new ModelAndView("MainAssetsTiles", "MainAssetsCmd", assetupd);
	}
	
	
	@RequestMapping(value = "/admin/Search_Computing_AssetsUrl", method = RequestMethod.GET)
	public ModelAndView Search_Computing_AssetsUrl(ModelMap Mmap, HttpSession sessionUserId, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = sessionUserId.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_Computing_AssetsUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		Mmap.put("roleSusNo", roleSusNo);

		
		Mmap.put("getTargetUnitNameList", allt.getTargetUnitNameList(roleSusNo, sessionUserId));
		Mmap.put("msg", msg);
		Mmap.put("asset_typeList", it_comm.getAssetList());
		Mmap.put("ramList", it_comm.getRamList());
		Mmap.put("hddList", it_comm.getHDDList());
		Mmap.put("sddList", it_comm.getSDDList());
		Mmap.put("osList", it_comm.getOperatingSystem());
		Mmap.put("getServiceable_StateList", it_comm.getServiceable_StateList());
		Mmap.put("status1", "0");
		Mmap.put("ComputingAssetList", it_comm.getComputingAssetList());
		Mmap.put("getSectionNameList", roleController.getSectionNameList());

		return new ModelAndView("SearchAssetsTiles");
	}


	@RequestMapping(value = "/getFilteredassetcomputing", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getFilteredassetcomputing(int startPage, int pageLength,
			String Search, String orderColunm, String orderType, String status, String assets_type, 
			String machine_number, String ram_capi, String hdd_capi, String operating_system, String from_date,
			String to_date, String s_state, String unit_sus_no, String unit_name, String section,HttpSession sessionUserId)
					throws SQLException {

		return cd.Search_Computing_Assets(startPage, pageLength, Search, orderColunm, orderType, status, assets_type,
			 machine_number, ram_capi, hdd_capi, operating_system, from_date, to_date, s_state,
				unit_sus_no, unit_name,section, sessionUserId);
	}

	@RequestMapping(value = "/getTotalAssetCount1", method = RequestMethod.POST)
	public @ResponseBody long getTotalAssetCount1(String Search, String orderColunm, String orderType, String status,
			String assets_type,  String machine_number, String ram_capi, String hdd_capi,
			String operating_system, String from_date, String to_date, String s_state, String unit_sus_no,
			String unit_name,String section, HttpSession sessionUserId) throws SQLException {

		return cd.getAppComputingassetCountList1(Search, orderColunm, orderType, status, assets_type, 
				machine_number, ram_capi, hdd_capi, operating_system, from_date, to_date, s_state, unit_sus_no,
				unit_name,section, sessionUserId);
	}
	
	
	@RequestMapping(value = "/getApprovedassetcomputing", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getApprovedassetcomputing(int startPage, int pageLength,
			String Search, String orderColunm, String orderType,  String computing_asset_type, 
			String section,String assets_uniq,HttpSession sessionUserId)
					throws SQLException {
		
		return cd.Search_Computing_Assets_Assign(startPage, pageLength, Search, orderColunm, orderType,  computing_asset_type,
			section,assets_uniq, sessionUserId);
	}
	
	@RequestMapping(value = "/getApprovedassetcount", method = RequestMethod.POST)
	public @ResponseBody long getApprovedassetcount(String Search, String orderColunm, String orderType, 
			String computing_asset_type, String section,String assets_uniq,HttpSession sessionUserId) throws SQLException {

		return cd.getAppComputingassetassignCountList1(Search, orderColunm, orderType,  computing_asset_type,
				section,assets_uniq,sessionUserId);
	}
	
	
	

	
	
	@RequestMapping(value = "/admin/Excel_Computing_Assets_Search", method = RequestMethod.POST)

	public ModelAndView Excel_Computing_Assets_Search(ModelMap Mmap, HttpSession session,

			@RequestParam(value = "msg", required = false) String msg,

		//	@RequestParam(value = "model_number2", required = false) String model_number,

			@RequestParam(value = "machine_number2", required = false) String machine_number,

			@RequestParam(value = "assets_type2", required = false) String assets_type,

			@RequestParam(value = "status2", required = false) String status,

			@RequestParam(value = "s_state2", required = false) String s_state,

			@RequestParam(value = "section2", required = false) String section) {



		

		ArrayList<ArrayList<String>> Excel = cd.excel_Computing_Assets_ReportDataList(session,machine_number,assets_type,status,s_state,section);

		

	String username =  session.getAttribute("username").toString();

		Mmap.put("msg", msg);

		String Heading ="SEARCH COMPUTING ASSESTS";

		Date date = new Date();

		String foot = " Report Generated On "+ new SimpleDateFormat("dd-MM-yyyy").format(date);

		

			List<String> TH = new ArrayList<String>();

			TH.add("Computing Assets Type");

		//	TH.add("Model Number");

			TH.add("Machine No");

			TH.add("MAC Address");

			TH.add("IP Address");

			TH.add("Processor Type");				

			TH.add("Office");

			TH.add("Deply Envt as Applicable"); 

			TH.add("Section");

			TH.add("Creadted By ");

		return new ModelAndView(new ExcelComputingUserListReportView("L", TH, Heading, username), "userList", Excel);

		

	}

	@RequestMapping(value = "/admin/Search_Computing_AssetsUrl_1", method = RequestMethod.POST)
	public ModelAndView Search_Computing_AssetsUrl_1(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			//@RequestParam(value = "model_number1", required = false) String model_number,
			@RequestParam(value = "machine_number1", required = false) String machine_number,
			@RequestParam(value = "ram_capi1", required = false) String ram_capi,
			@RequestParam(value = "hdd_capi1", required = false) String hdd_capi,
			@RequestParam(value = "operating_system1", required = false) String operating_system,
			@RequestParam(value = "unit_sus_no1", required = false) String unit_sus_no,
			@RequestParam(value = "unit_name1", required = false) String unit_name,
			@RequestParam(value = "from_date1", required = false) String from_date,
			@RequestParam(value = "to_date1", required = false) String to_date,
			@RequestParam(value = "assets_type1", required = false) String assets_type,
			@RequestParam(value = "status1", required = false) String status,
			@RequestParam(value = "s_state1", required = false) String s_state,
			@RequestParam(value = "section1", required = false) String section) {

		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		Mmap.put("roleSusNo", roleSusNo);

		
		Mmap.put("getTargetUnitNameList", allt.getTargetUnitNameList(roleSusNo, session));

		//Mmap.put("model_number1", model_number);
		Mmap.put("machine_number1", machine_number);
		Mmap.put("ram_capi1", ram_capi);
		Mmap.put("hdd_capi1", hdd_capi);
		Mmap.put("operating_system1", operating_system);
		Mmap.put("from_date1", from_date);
		Mmap.put("to_date1", to_date);
		Mmap.put("assets_type1", assets_type);
		Mmap.put("status1", status);
		Mmap.put("s_state1", s_state);
		Mmap.put("unit_name1", unit_name);
		Mmap.put("unit_sus_no1", unit_sus_no);
		Mmap.put("asset_typeList", it_comm.getAssetList());
		Mmap.put("ramList", it_comm.getRamList());
		Mmap.put("hddList", it_comm.getHDDList());
		Mmap.put("osList", it_comm.getOperatingSystem());
		Mmap.put("sddList", it_comm.getSDDList());
		Mmap.put("ComputingAssetList", it_comm.getComputingAssetList());
		Mmap.put("getServiceable_StateList", it_comm.getServiceable_StateList());
		Mmap.put("section1",section);
		Mmap.put("getSectionNameList", roleController.getSectionNameList());
		return new ModelAndView("SearchAssetsTiles");
	}

	@RequestMapping(value = "/Approve_ComputingAssetsData", method = RequestMethod.POST)
	public @ResponseBody List<String> Approve_ComputingAssetsData(String a, String status, HttpSession session)
			throws SQLException {
		String sus_no = session.getAttribute("roleSusNo").toString();
		String username = session.getAttribute("username").toString();
		List<String> list2 = new ArrayList<String>();
		// List<Map<String, Object>> ls = cd.approve_Master_Asset(a);

		list2.add(cd.approve_AssetsData(a, sus_no, status, username));
		return list2;
	}

	@RequestMapping(value = "/ComputingAssertsDelete", method = RequestMethod.POST)
	public ModelAndView ComputingAssertsDelete(@ModelAttribute("id1") String id,
			@RequestParam(value = "msg", required = false) String msg,
			 @RequestParam(value = "screenurl2", required = false) String screenurl,
			BindingResult result, HttpServletRequest request,
			ModelMap model, HttpSession session1) {

		String roleid = session1.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_Computing_AssetsUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		List<String> liststr = new ArrayList<String>();
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			String hqlUpdate = "";
			int app;
			hqlUpdate = "delete from Assets_Main where id=:id";
			app = session.createQuery(hqlUpdate).setInteger("id", Integer.parseInt(id)).executeUpdate();
			tx.commit();
			session.close();
			if (app > 0) {
				liststr.add("Data Deleted Successfully");
			} else {
				liststr.add("Data not Deleted");
			}
			model.put("msg", liststr.get(0));
		} catch (Exception e) {
			liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED");
			model.put("msg", liststr.get(0));
		}
		return new ModelAndView("redirect:"+screenurl);
	}
	
	
	@RequestMapping(value = "/admin/ComputingAssertsView", method = RequestMethod.POST)
	public ModelAndView ComputingAssertsView(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "viewid", required = false) int id,
	       @RequestParam(value = "screenurl4", required = false) String screenurl){

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_Computing_AssetsUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Assets_Main assetupd = (Assets_Main) sessionHQL.get(Assets_Main.class, id);
		if (((TB_MASTER_MAKE) sessionHQL.get(TB_MASTER_MAKE.class, assetupd.getMake_name())).getMake_name()
				.equals("OTHERS")) {
			TB_MASTER_MAKE make_mstr1 = (TB_MASTER_MAKE) sessionHQL.get(TB_MASTER_MAKE.class,
					Integer.parseInt(assetupd.getBrand_other()));
			Mmap.put("make_mstr_other", make_mstr1.getMake_name());
			Mmap.put("make_mstr_id", make_mstr1.getId());
		}

		if (((TB_MASTER_MODEL) sessionHQL.get(TB_MASTER_MODEL.class, assetupd.getModel_name())).getModel_name()
				.equals("OTHERS")) {
			TB_MASTER_MODEL model_mstr1 = (TB_MASTER_MODEL) sessionHQL.get(TB_MASTER_MODEL.class,
					Integer.parseInt(assetupd.getModel_other()));
			Mmap.put("model_mstr_other", model_mstr1.getModel_name());
			Mmap.put("model_mstr_id", model_mstr1.getId());
		}

		if (((TB_MSTR_PROCESSOR_TYPE_M) sessionHQL.get(TB_MSTR_PROCESSOR_TYPE_M.class, assetupd.getProcessor_type()))
				.getProcessor_type().equals("OTHERS")) {
			TB_MSTR_PROCESSOR_TYPE_M pro_mstr1 = (TB_MSTR_PROCESSOR_TYPE_M) sessionHQL
					.get(TB_MSTR_PROCESSOR_TYPE_M.class, Integer.parseInt(assetupd.getPro_type_other()));
			Mmap.put("pro_type_other", pro_mstr1.getProcessor_type());
			Mmap.put("pro_type_id", pro_mstr1.getId());
		}
		if(assetupd.getOffice()!=0)
		{

		if (((TB_MSTR_OFFICE_M) sessionHQL.get(TB_MSTR_OFFICE_M.class, assetupd.getOffice())).getOffice()
				.equals("OTHERS")) {
			TB_MSTR_OFFICE_M office_mstr1 = (TB_MSTR_OFFICE_M) sessionHQL.get(TB_MSTR_OFFICE_M.class,
					Integer.parseInt(assetupd.getOffice_other()));
			Mmap.put("office_mstr_other", office_mstr1.getOffice());
			Mmap.put("office_mstr_id", office_mstr1.getId());
		}
		}
		if (((TB_MSTR_OS_M) sessionHQL.get(TB_MSTR_OS_M.class, assetupd.getOperating_system())).getOs()
				.equals("OTHERS")) {
			TB_MSTR_OS_M os_mstr1 = (TB_MSTR_OS_M) sessionHQL.get(TB_MSTR_OS_M.class,
					Integer.parseInt(assetupd.getOs_other()));
			Mmap.put("os_mstr_other", os_mstr1.getOs());
			Mmap.put("os_mstr_id", os_mstr1.getId());
		}

		Mmap.put("MakeList", it_comm.getMakeName());
		Mmap.put("ModelList", it_comm.getModelNameList());
		Mmap.put("AntivirusList", it_comm.getAntivirus());
		Mmap.put("ComputingAssetList", it_comm.getComputingAssetList());
		Mmap.put("processor_typeList", it_comm.getProcessorList());
		Mmap.put("ramList", it_comm.getRamList());
		Mmap.put("hddList", it_comm.getHDDList());
		Mmap.put("sddList", it_comm.getSDDList());
		Mmap.put("osList", it_comm.getOperatingSystem());
		Mmap.put("officeList", it_comm.getOffice());
		Mmap.put("osFirmwareList", it_comm.getOsFirmware());
		Mmap.put("dplyEnvList", it_comm.getdplyEnv());
		Mmap.put("UNServiceableList", it_comm.UNServiceableList());
		Mmap.put("getConnectingTechList", it_comm.getConnectingTechList());
		Mmap.put("getEthernetPortList", it_comm.getEthernetPortList());
		Mmap.put("getServiceable_StateList", it_comm.getServiceable_StateList());
		Mmap.put("getBudgetHeadList", it_comm.getBudgetHeadList());
		Mmap.put("getBudgetCodeList", it_comm.getBudgetCodeList());
		Mmap.put("ServiceableList", it_comm.ServiceableList());
		 Mmap.put("getSectionNameList", roleController.getSectionNameList());
		 Mmap.put("getMaintainceAgencyList", it_comm.getMaintainceAgencyList());
		 Mmap.put("contactNumber",getContact_number( assetupd.getSupplier_name().toString()));
		 Mmap.put("screenurl",screenurl);
//		 Mmap.put("uploaddoc", assetupd.getU_file());
		return new ModelAndView("ViewAssetsTiles", "ViewAssetsCmd", assetupd);
	}
	
	
	
	
	
	
	
	@RequestMapping(value = "/admin/SearchApp_Computing_AssetsUrl", method = RequestMethod.GET)
	public ModelAndView SearchApp_Computing_AssetsUrl(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) throws SQLException {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("SearchApp_Computing_AssetsUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		Mmap.put("msg", msg);
		Mmap.put("asset_typeList", it_comm.getAssetList());
		Mmap.put("ramList", it_comm.getRamList());
		Mmap.put("hddList", it_comm.getHDDList());
		Mmap.put("sddList", it_comm.getSDDList());
		Mmap.put("osList", it_comm.getOperatingSystem());
		Mmap.put("ComputingAssetList", it_comm.getComputingAssetList());
		 Mmap.put("getSectionNameList", roleController.getSectionNameList());
		 Mmap.put("getMaintainceAgencyList", it_comm.getMaintainceAgencyList());
		return new ModelAndView("SearchAPPAssetsTiles");
	}

	@RequestMapping(value = "/getFilteredasset", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getFilteredasset(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String status, String assets_type, 
			String machine_number, String ram_capi, String hdd_capi, String operating_system, String unit_sus_no,
			String from_date, String to_date,String section, HttpSession sessionUserId) throws SQLException {

		return cd.getAppComputingassetList(startPage, pageLength, Search, orderColunm, orderType, status, assets_type,
			 machine_number, ram_capi, hdd_capi, operating_system, unit_sus_no, from_date, to_date,section,
				sessionUserId);
	}

	@RequestMapping(value = "/getTotalAssetCount", method = RequestMethod.POST)
	public @ResponseBody long getTotalAssetCount(String Search, String orderColunm, String orderType, String status,
			String assets_type, String machine_number, String ram_capi, String hdd_capi,
			String operating_system, String unit_sus_no, String from_date, String to_date,String section, HttpSession sessionUserId)
					throws SQLException {

		return cd.getAppComputingassetCountList(Search, orderColunm, orderType, status, assets_type,
				machine_number, ram_capi, hdd_capi, operating_system, unit_sus_no, from_date, to_date,section, sessionUserId);
	}
	@RequestMapping(value = "/admin/SearchApp_Computing_AssetsUrl_1", method = RequestMethod.POST)
	public ModelAndView SearchApp_Computing_AssetsUrl_1(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			//@RequestParam(value = "model_number1", required = false) String model_number,
			@RequestParam(value = "computing_assets1", required = false) String computing_asset,
			@RequestParam(value = "machine_number1", required = false) String machine_number,
			@RequestParam(value = "ram_capi1", required = false) String ram_capi,
			@RequestParam(value = "hdd_capi1", required = false) String hdd_capi,
			@RequestParam(value = "operating_system1", required = false) String operating_system,
			@RequestParam(value = "unit_sus_no1", required = false) String unit_sus_no,
			@RequestParam(value = "unit_name1", required = false) String unit_name,
			@RequestParam(value = "from_date1", required = false) String from_date,
			@RequestParam(value = "to_date1", required = false) String to_date,
			@RequestParam(value = "assets_type1", required = false) String assets_type,
			@RequestParam(value = "status1", required = false) String status,
			@RequestParam(value = "section1", required = false) String section) throws SQLException {

		//Mmap.put("model_number1", model_number);
		Mmap.put("computing_assets1", computing_asset);
		Mmap.put("machine_number1", machine_number);
		Mmap.put("ram_capi1", ram_capi);
		Mmap.put("hdd_capi1", hdd_capi);
		Mmap.put("operating_system1", operating_system);
		Mmap.put("from_date1", from_date);
		Mmap.put("to_date1", to_date);
		Mmap.put("assets_type1", assets_type);
		Mmap.put("status1", status);
		Mmap.put("section1", section);
		Mmap.put("asset_typeList", it_comm.getAssetList());
		Mmap.put("ramList", it_comm.getRamList());
		Mmap.put("hddList", it_comm.getHDDList());
		Mmap.put("sddList", it_comm.getSDDList());
		Mmap.put("osList", it_comm.getOperatingSystem());
		Mmap.put("ComputingAssetList", it_comm.getComputingAssetList());
		Mmap.put("getSectionNameList", roleController.getSectionNameList());
		return new ModelAndView("SearchAPPAssetsTiles");
	}


	@RequestMapping(value = "/admin/It_AppEditUrl", method = RequestMethod.POST)
	public ModelAndView It_AppEditUrl(ModelMap Mmap, HttpSession session, HttpServletRequest request, String p_id,
			@RequestParam(value = "id5", required = false) String id,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "ch_id1", required = false) String ch_id) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("SearchApp_Computing_AssetsUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Assets_Main assetupd = (Assets_Main) sessionHQL.get(Assets_Main.class, Integer.parseInt(id));

		List<Map<String, Object>> ch_list = cd.getAppChildComputingassetList(id);

		Mmap.put("ch_list_size", ch_list.size());
		Mmap.put("ch_list", ch_list);

		Mmap.put("AntivirusList", it_comm.getAntivirus());
		Mmap.put("ComputingAssetList", it_comm.getComputingAssetList());
		Mmap.put("processor_typeList", it_comm.getProcessorList());
		Mmap.put("ramList", it_comm.getRamList());
		Mmap.put("hddList", it_comm.getHDDList());
		Mmap.put("sddList", it_comm.getSDDList());
		Mmap.put("osList", it_comm.getOperatingSystem());
		Mmap.put("officeList", it_comm.getOffice());
		Mmap.put("osFirmwareList", it_comm.getOsFirmware());
		Mmap.put("dplyEnvList", it_comm.getdplyEnv());
		Mmap.put("UNServiceableList", it_comm.UNServiceableList());
		Mmap.put("getBudgetCodeList", it_comm.getBudgetCodeList());
		Mmap.put("getBudgetHeadList", it_comm.getBudgetHeadList());
		Mmap.put("e_id", id);
		Mmap.put("ch_id1", ch_id);
		Mmap.put("ComputingAssetList", it_comm.getComputingAssetList());
		Mmap.put("getSectionNameList", roleController.getSectionNameList());
		 Mmap.put("getMaintainceAgencyList", it_comm.getMaintainceAgencyList());
		 Mmap.put("getServiceable_StateList", it_comm.getServiceable_StateList());
		return new ModelAndView("AppMainAssetsTiles", "AppMainAssetsCmd", assetupd);
	}

	@RequestMapping(value = "/updateAssetsAction", method = RequestMethod.POST)
	public ModelAndView updateAssetsAction(@ModelAttribute("AppMainAssetsCmd") Assets_Child obj,
			HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("SearchApp_Computing_AssetsUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		Date dob_dt = null;

		Date unsv_from_dt1 = null;
		Date unsv_to_dt1 = null;

		String warranty = request.getParameter("warranty_dt").trim();
		String warrantycheck = request.getParameter("warrantycheck");
		if (warranty != null && !warranty.equals("") && !warranty.equals("DD/MM/YYYY")) {
			dob_dt = format.parse(warranty);

		}

		
		
		
		
		
		// dob_dt = format.parse(warranty);
		String service_state = request.getParameter("s_state");

		String unserviceable_state = request.getParameter("unserviceable_state");

		String unsv_from_dt = request.getParameter("unsv_from_dt1");
		String unsv_to_dt = request.getParameter("unsv_to_dt1");

		String p_id = request.getParameter("id");

		String ip_address = request.getParameter("ip_address1");

		String ram_capi = request.getParameter("ram_capi1");

		String hdd_capi = request.getParameter("hdd_capi1");
		String operating_system = request.getParameter("operating_system1");
		String office = request.getParameter("office1");
		String antiviruscheck = request.getParameter("antiviruscheck");
		String antivirus = request.getParameter("antivirus1");

//		String os_patch = request.getParameter("os_patch1");
		String dply_envt = request.getParameter("dply_envt1");
//		String ram_slot_qty = request.getParameter("ram_slot_qty");
		String cd_drive = request.getParameter("cd_drive1");

		String b_head = request.getParameter("b_head1");

		String b_code = request.getParameter("b_code1");

		String b_cost = request.getParameter("b_cost");
//		String crv_no = request.getParameter("crv_no");
		String ssdcheck = request.getParameter("ssdcheck");
//		String username2 = request.getParameter("user_name");
		String maintainAgency = request.getParameter("maintainAgency");
		
		
		String hsd_capi = request.getParameter("ssd_capi");
//		if (ssdcheck != null && ssdcheck.toUpperCase().equals("YES")) {
//			if (hsd_capi == null || hsd_capi.equals("") || hsd_capi.equals("0")) {
//				model.put("msg", "Please Enter SSD");
//				return new ModelAndView("redirect:SearchApp_Computing_AssetsUrl");
//			}
//		}
		if (service_state.equals("1") || service_state == "1") {
			unserviceable_state = "0";
			unsv_from_dt = null;

			model.put("getServicable", it_comm.getServicable(request, Integer.parseInt(p_id)));

		}

		if (service_state.equals("2")) {
			if (unserviceable_state == null || unserviceable_state.equals("") || unserviceable_state.equals("0")) {
				model.put("msg", "Please Select UN-Serviceable State");
				return new ModelAndView("redirect:SearchApp_Computing_AssetsUrl");
			}

			if (unsv_from_dt == null || unsv_from_dt.equals("") || unsv_from_dt.equals("0")) {
				model.put("msg", "Please Select UN-Serviceable From date");
				return new ModelAndView("redirect:SearchApp_Computing_AssetsUrl");
			}
			if (maintainAgency == null || maintainAgency.equals("") || maintainAgency.equals("0")) {
				model.put("msg", "Please Select Maintenance Agency");
				return new ModelAndView("redirect:SearchApp_Computing_AssetsUrl");
			}
		}
		if (unsv_from_dt != null && !unsv_from_dt.equals("") && !unsv_from_dt.equals("DD/MM/YYYY")) {
			unsv_from_dt1 = format.parse(unsv_from_dt);

		}
		if (unsv_to_dt != null && !unsv_to_dt.equals("") && !unsv_to_dt.equals("DD/MM/YYYY")) {
			unsv_to_dt1 = format.parse(unsv_to_dt);

		}

		String c_id = request.getParameter("c_id");

		int ID = 0;

		if (c_id != null && !c_id.equals("")) {
			ID = Integer.parseInt(c_id) > 0 ? Integer.parseInt(c_id) : 0;
		}

		String username = session.getAttribute("username").toString();

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {

			int unsv = 0;
			int sv = 0;
			if (service_state != null && !service_state.equals("")) {
				sv = Integer.parseInt(service_state);
			}
			if (unserviceable_state != null && !unserviceable_state.equals("")) {
				unsv = Integer.parseInt(unserviceable_state);
			}

			if (ID == 0) {

				obj.setWarranty(dob_dt);
				;
				obj.setWarrantycheck(warrantycheck);
				;
				obj.setService_state(sv);
				obj.setUnservice_state(unsv);
				obj.setUnsv_from_dt(unsv_from_dt1);
				obj.setUnsv_to_dt(unsv_to_dt1);
				obj.setIp_address(ip_address);
				obj.setAntivirus(Integer.parseInt(antivirus));
				obj.setAntiviruscheck(antiviruscheck);
				obj.setB_code(b_code);
				obj.setB_cost(b_cost);
				obj.setB_head(b_head);
				obj.setDply_envt(Integer.parseInt(dply_envt));
				obj.setCd_drive(cd_drive);
				obj.setHdd_capi(Integer.parseInt(hdd_capi));
				obj.setOffice(Integer.parseInt(office));
				obj.setOperating_system(Integer.parseInt(operating_system));
				obj.setOperating_system(Integer.parseInt(operating_system));
//				obj.setOs_patch(Integer.parseInt(os_patch));
				obj.setRam_capi(Integer.parseInt(ram_capi));
				obj.setMaintainagency(maintainAgency);
				obj.setSsd_capi(Integer.parseInt(hsd_capi));
				
				obj.setP_id(Integer.parseInt(p_id));
				sessionHQL.save(obj);
				sessionHQL.flush();
				sessionHQL.clear();
				model.put("msg", "Data Saved Successfully.");
			}

			if (ID > 0)

			{

				String hql = "update Assets_Child set warranty=:warranty,warrantycheck=:warrantycheck,service_state=:service_state,unservice_state=:unservice_state,unsv_from_dt=:unsv_from_dt,unsv_to_dt=:unsv_to_dt,"
						+ "ip_address=:ip_address,ram_capi=:ram_capi,hdd_capi=:hdd_capi,operating_system=:operating_system,office=:office,"
						+ "antiviruscheck=:antiviruscheck,antivirus=:antivirus,dply_envt=:dply_envt,"
						+ "cd_drive=:cd_drive,b_head=:b_head,b_code=:b_code,b_cost=:b_cost,"
						+ " modified_by=:modified_by,modified_dt=:modified_dt, maintainagency=:maintainAgency , ssd_capi=:hsd_capi    " + " where id=:id";

				Query query = sessionHQL.createQuery(hql).setTimestamp("warranty", dob_dt)
						.setString("warrantycheck", warrantycheck).setInteger("service_state", sv)
						.setInteger("unservice_state", unsv).setTimestamp("unsv_from_dt", unsv_from_dt1)
						.setTimestamp("unsv_to_dt", unsv_to_dt1).setString("ip_address", ip_address)
						.setInteger("ram_capi", Integer.parseInt(ram_capi))
						.setInteger("hdd_capi", Integer.parseInt(hdd_capi))
						.setInteger("operating_system", Integer.parseInt(operating_system))
						.setInteger("office", Integer.parseInt(office)).setString("maintainAgency", maintainAgency)
						.setInteger("hsd_capi", Integer.parseInt(hsd_capi))
						.setString("antiviruscheck", antiviruscheck)
						.setInteger("antivirus", Integer.parseInt(antivirus))
						

						.setInteger("dply_envt", Integer.parseInt(dply_envt))
						

						.setString("cd_drive", cd_drive).setString("b_head", b_head).setString("b_code", b_code)
						.setString("b_cost", b_cost).setString("modified_by", username)
						.setDate("modified_dt", new Date()).setInteger("id", ID);
				msg = query.executeUpdate() > 0 ? "1" : "0";

				if (msg == "1") {
					model.put("msg", "Data Updated Successfully.");
				} else {
					model.put("msg", "Data Not Updated.");
				}
			}
			tx.commit();

		}

		catch (RuntimeException e) {
			try {
				tx.rollback();
				model.put("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				model.put("msg", "Couldn't roll back transaction " + rbe);
			}
			throw e;

		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return new ModelAndView("redirect:SearchApp_Computing_AssetsUrl");
	}

	@RequestMapping(value = "/Approve_Update_Computing", method = RequestMethod.POST)
	public @ResponseBody ModelAndView Approve_Update_Computing(@ModelAttribute("id3") int id, BindingResult result,
			HttpServletRequest request, HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg,

			Authentication authentication) throws ParseException, SQLException {
		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("SearchApp_Computing_AssetsUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		List<String> liststr = new ArrayList<String>();
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {
			// **AHM BISAG **//

			Query query = sessionHQL.createQuery(" from Assets_Child where p_id=:p_id and status=0");
			query.setInteger("p_id", id);
			Assets_Child ac = (Assets_Child) query.uniqueResult();

			// ** END AHM BISAG **//

			String username = sessionA.getAttribute("username").toString();

			List<Map<String, Object>> ls = cd.GetdataComputing(id);

			DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

			String hqlUpdate1 = "update Assets_Child set status=:status,modified_by=:modified_by,modified_dt=:modified_dt,unsv_to_dt=:unsv_to_dt  where p_id=:id and status=1";
			int app1 = sessionHQL.createQuery(hqlUpdate1).setInteger("status", 2).setString("modified_by", username)
					.setTimestamp("unsv_to_dt", ac.getUnsv_to_dt()).setInteger("id", id)
					.setDate("modified_dt", new Date()).executeUpdate();

			if (ls.size() > 0) {
				Date wr = null;
				if (ls.get(0).get("warranty") != null && !ls.get(0).get("warranty").equals("")) {
					wr = format.parse(String.valueOf(ls.get(0).get("warranty")));
				}
				int m_status = 1;
				if (String.valueOf(ls.get(0).get("unservice_state")).equals("1")) {
					m_status = 4;
				}

				String hqlUpdate3 = "update Assets_Main set warranty=:warranty,unserviceable_state=:unservice_state,s_state=:service_state,unsv_from_dt=:unsv_from_dt,unsv_to_dt=:unsv_to_dt,"
						+ "ip_address=:ip_address,ram_capi=:ram_capi,hdd_capi=:hdd_capi,operating_system=:operating_system,office=:office,\r\n"
						+ "antiviruscheck=:antiviruscheck,antivirus=:antivirus,dply_envt=:dply_envt,\r\n"
						+ "cd_drive=:cd_drive,b_head=:b_head,b_code=:b_code,b_cost=:b_cost,modified_by=:modified_by,modified_date=:modified_date,status=:status  where id=:id ";
				int app2 = sessionHQL.createQuery(hqlUpdate3).setTimestamp("warranty", wr)
						.setInteger("service_state", Integer.parseInt(String.valueOf(ls.get(0).get("service_state"))))
						.setInteger("unservice_state",
								Integer.parseInt(String.valueOf(ls.get(0).get("unservice_state"))))
						.setString("ip_address", String.valueOf(ls.get(0).get("ip_address")))
						.setString("antiviruscheck", String.valueOf(ls.get(0).get("antiviruscheck")))
						.setString("b_head", String.valueOf(ls.get(0).get("b_head")))
						.setString("b_code", String.valueOf(ls.get(0).get("b_code")))
						.setString("b_cost", String.valueOf(ls.get(0).get("b_cost")))
						.setString("cd_drive", String.valueOf(ls.get(0).get("cd_drive")))

						.setInteger("antivirus", Integer.parseInt(String.valueOf(ls.get(0).get("antivirus"))))
						.setInteger("ram_capi", Integer.parseInt(String.valueOf(ls.get(0).get("ram_capi"))))
						.setInteger("hdd_capi", Integer.parseInt(String.valueOf(ls.get(0).get("hdd_capi"))))
						.setInteger("operating_system",
								Integer.parseInt(String.valueOf(ls.get(0).get("operating_system"))))
						.setInteger("office", Integer.parseInt(String.valueOf(ls.get(0).get("office"))))
					
						.setInteger("dply_envt", Integer.parseInt(String.valueOf(ls.get(0).get("dply_envt"))))
					

						.setString("modified_by", username)
						.setDate("unsv_from_dt", (Date) ls.get(0).get("unsv_from_dt"))
						.setDate("unsv_to_dt", (Date) ls.get(0).get("unsv_to_dt")).setDate("modified_date", new Date())
						.setInteger("status", m_status).setInteger("id", id).executeUpdate();
			}

			// ** AHM BISAG **//

			Date unsv_to_dt3 = null;
			String unsv_to_dt4 = request.getParameter("");
			if (unsv_to_dt4 != null && !unsv_to_dt4.equals("") && !unsv_to_dt4.equals("DD/MM/YYYY")) {
				unsv_to_dt3 = format.parse(unsv_to_dt4);

			}

			String hqlUpdate = "update Assets_Child set status=:status,modified_by=:modified_by,modified_dt=:modified_dt,unsv_to_dt=:unsv_to_dt  where p_id=:id and status =0";
			int app = sessionHQL.createQuery(hqlUpdate).setInteger("status", 1).setString("modified_by", username)
					.setTimestamp("unsv_to_dt", unsv_to_dt3).setDate("modified_dt", new Date()).setInteger("id", id)
					.executeUpdate();

			// ** END AHM BISAG **//
			tx.commit();

			if (app > 0) {
				liststr.add("Approved Successfully.");
			} else {
				liststr.add("Approved Not Successfully.");
			}
			model.put("msg", liststr.get(0));

		} catch (RuntimeException e) {
			try {
				tx.rollback();
				model.put("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				model.put("msg", "Couldn't roll back transaction " + rbe);
			}
			throw e;

		} finally {
			if (sessionHQL != null) {
				sessionHQL.close();
			}
		}
		return new ModelAndView("redirect:SearchApp_Computing_AssetsUrl");
	}

	@RequestMapping(value = "/Reject_Update_Computing", method = RequestMethod.POST)
	public @ResponseBody ModelAndView Reject_Update_Computing(@ModelAttribute("id4") int id, BindingResult result,
			HttpServletRequest request, HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {

		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("SearchApp_Computing_AssetsUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		List<String> liststr = new ArrayList<String>();
		// try {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		String username = sessionA.getAttribute("username").toString();

		String hqlUpdate = "update Assets_Child set status=:status,modified_by=:modified_by,modified_dt=:modified_date  where p_id=:id and  status=0";
		int app = sessionHQL.createQuery(hqlUpdate).setInteger("status", 3).setString("modified_by", username)
				.setDate("modified_date", new Date()).setInteger("id", id).executeUpdate();

		if (app > 0) {
			liststr.add("Reject Successfully.");
		} else {
			liststr.add("Reject UNSuccessfully.");
		}

		tx.commit();
		sessionHQL.close();
		return new ModelAndView("redirect:SearchApp_Computing_AssetsUrl");
	}

	@RequestMapping(value = "/Demo_Url", method = RequestMethod.GET)
	public ModelAndView Demo_Url(ModelMap model, @RequestParam(value = "msg", required = false) String msg,
			HttpSession session, HttpServletRequest request) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Demo_Url", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		model.put("msg", msg);
		return new ModelAndView("Demo_Tiles");
	}

	@RequestMapping(value = "/demo_report", method = RequestMethod.POST)
	public ModelAndView demo_report(HttpServletRequest request, ModelMap model, HttpSession session,
			String typeReport1) {

		ArrayList<ArrayList<String>> CTlist = cd.Search_DataTableList(session);

		List<String> TH = new ArrayList<String>();
		TH.add("TYPE OF HW");
		TH.add("PROC COST");
		TH.add("MACHINE NO");
		TH.add("MODEL NO");
		TH.add("SERIAL NO");
		TH.add("IP ADDRESS");
		TH.add("TYPE");
		TH.add("REMARKS");
		TH.add("MONOCHROME COLOR");
		TH.add("IS NETWORKED");
		TH.add("PRINT");
		TH.add("SCAN");
		TH.add("PHOTOGRAPHY");
		TH.add("COLOUR");
		TH.add("CAPACITY");
		TH.add("RESOLUTION");
		TH.add("NO OF PORTS");
		TH.add("UPS CAPACITY");
		TH.add("ASSETS NAME");

		TH.add("DISPLAY CONNECTOR");
		TH.add("DISPLAY SIZE");
		TH.add("PAPER SIZE");
		TH.add("B HEAD");
		TH.add("B CODE");
		TH.add("B COST");
		TH.add("SERVICE STATE");
		TH.add("MAKE NAME");
		TH.add("MODEL NAME");
		TH.add("STATUS");
		TH.add("UNSERVICE STATE");
		TH.add("WARRANTY");
		TH.add("CONECTIVITY TYPE");
		TH.add("HARDWARE INTERFACE");
		TH.add("ETHERNET PORT");
		TH.add("MANAGEMENT LAYER");
		TH.add("NETWORK FEATURE");
		TH.add("V DISPLAY SIZE");
		TH.add("V DISPLAY CONNECTOR");
		TH.add("PROC DATE");
		TH.add("SUS NO");

		TH.add("DISPLAY SIZE");
		TH.add("YEAR OF PROC");
		TH.add("YEAR OF MANUFACTURING");
		String Heading = "\nAccounting Unit";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}

	@RequestMapping(value = "/demo_report1", method = RequestMethod.POST)
	public ModelAndView demo_report1(HttpServletRequest request, ModelMap model, HttpSession session,
			String typeReport2) {

		ArrayList<ArrayList<String>> CTlist = cd.Search_DataTableList1(session);

		List<String> TH = new ArrayList<String>();
		TH.add("ASSETS NAME");
		TH.add("MODEL NUMBER");
		TH.add("MACHINE NUMBER");
		TH.add("MAC ADDRESS");
		TH.add("IP ADDRESS");
		TH.add("PROCESSOR TYPE");
		TH.add("RAM");
		TH.add("HDD");
		TH.add("OS");
		TH.add("OFFICE");
		TH.add("OS PATCH");
		TH.add("DPLY ENV");
		TH.add("ANTIVIRUS CHECK");
		TH.add("ANTIVIRUS");
		TH.add("B HEAD");
		TH.add("B CODE");
		TH.add("B COST");
		TH.add("CD DRIVE");
		TH.add("SERVICE STATE");
		TH.add("MODEL NAME");
		TH.add("MAKE NAME");
		TH.add("UNSERVICE STATE");
		TH.add("STATUS");
		TH.add("WARRANTY");
		TH.add("DIMENSION");
		TH.add("PART NO");
		TH.add("ETHERNET PORT");
		TH.add("RAM SLOT QTY");
		TH.add("SUS NO");
		TH.add("PROC DATE");
		String Heading = "\nAccounting Unit";
		String username = session.getAttribute("username").toString();
		return new ModelAndView(new ExcelUserListReportView("L", TH, Heading, username), "userList", CTlist);
	}

	@SuppressWarnings("null")
	@RequestMapping(value = "/BERFileDownloadDemo", method = RequestMethod.GET)
	public void kmlFileDownloadDemo(@ModelAttribute("id") int id, ModelMap model, HttpServletRequest request,
			HttpServletResponse response, HttpSession sessionA, Authentication authentication)
					throws IOException, SQLException {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		Assets_Main u_file_path = (Assets_Main) sessionHQL.get(Assets_Main.class, id);

		String filePath = u_file_path.getU_file();

		final int BUFFER_SIZE = 4096;

		ServletContext context = request.getSession().getServletContext();
		try {
			if (filePath == null && filePath.isEmpty() && filePath == "" && filePath == "null") {
				@SuppressWarnings("deprecation")
				String fullPath = request.getRealPath("/") + "admin/js/img/No_Image.jpg";
				File downloadFile = new File(fullPath);
				FileInputStream inputStream = new FileInputStream(downloadFile);
				String mimeType = context.getMimeType(fullPath);
				if (mimeType == null) {
					mimeType = "application/octet-stream";
				}
				response.setContentType(mimeType);
				response.setContentLength((int) downloadFile.length());
				String headerKey = "Content-Disposition";
				String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
				response.setHeader(headerKey, headerValue);
				ServletOutputStream outStream = response.getOutputStream();
				byte[] buffer = new byte[BUFFER_SIZE];
				int bytesRead = -1;
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outStream.write(buffer, 0, bytesRead);
				}
				inputStream.close();
				outStream.close();
			} else {

				String fullPath = filePath;
				File downloadFile = new File(fullPath);
				FileInputStream inputStream = new FileInputStream(downloadFile);
				String mimeType = context.getMimeType(fullPath);
				if (mimeType == null) {
					mimeType = "application/octet-stream";
				}
				response.setContentType(mimeType);
				response.setContentLength((int) downloadFile.length());
				String headerKey = "Content-Disposition";
				String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
				response.setHeader(headerKey, headerValue);
				ServletOutputStream outStream = response.getOutputStream();
				byte[] buffer = new byte[BUFFER_SIZE];
				int bytesRead = -1;
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outStream.write(buffer, 0, bytesRead);
				}
				inputStream.close();
				outStream.close();
			}
		} catch (Exception ex) {
			@SuppressWarnings("deprecation")
			String fullPath = request.getRealPath("/") + "admin/js/img/No_Image.jpg";
			File downloadFile = new File(fullPath);
			FileInputStream inputStream = new FileInputStream(downloadFile);
			String mimeType = context.getMimeType(fullPath);
			if (mimeType == null) {
				mimeType = "application/octet-stream";
			}
			response.setContentType(mimeType);
			response.setContentLength((int) downloadFile.length());
			String headerKey = "Content-Disposition";
			String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
			response.setHeader(headerKey, headerValue);
			ServletOutputStream outStream = response.getOutputStream();
			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = -1;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			}
			inputStream.close();
			outStream.close();
		}

	}

	@RequestMapping(value = "/admin/SearchDelete_Computing_AssetsUrl", method = RequestMethod.GET)
	public ModelAndView SearchDelete_Computing_AssetsUrl(ModelMap Mmap, HttpSession sessionUserId,
			HttpServletRequest request, @RequestParam(value = "msg", required = false) String msg) {

		String roleid = sessionUserId.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("SearchDelete_Computing_AssetsUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		Mmap.put("roleSusNo", roleSusNo);

	
		Mmap.put("getTargetUnitNameList", allt.getTargetUnitNameList(roleSusNo, sessionUserId));
		Mmap.put("msg", msg);
		Mmap.put("asset_typeList", it_comm.getAssetList());
		Mmap.put("ramList", it_comm.getRamList());
		Mmap.put("sddList", it_comm.getSDDList());
		Mmap.put("hddList", it_comm.getHDDList());
		Mmap.put("osList", it_comm.getOperatingSystem());
		Mmap.put("getServiceable_StateList", it_comm.getServiceable_StateList());
		Mmap.put("status1", "0");
		Mmap.put("ComputingAssetList", it_comm.getComputingAssetList());
		 Mmap.put("getSectionNameList", roleController.getSectionNameList());

		return new ModelAndView("SearchDeleteAssetsTiles");
	}

	@RequestMapping(value = "/admin/SearchDelete_Computing_AssetsUrl_1", method = RequestMethod.POST)
	public ModelAndView SearchDelete_Computing_AssetsUrl_1(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			//@RequestParam(value = "model_number1", required = false) String model_number,
			@RequestParam(value = "machine_number1", required = false) String machine_number,
			@RequestParam(value = "ram_capi1", required = false) String ram_capi,
			@RequestParam(value = "hdd_capi1", required = false) String hdd_capi,
			@RequestParam(value = "operating_system1", required = false) String operating_system,
			@RequestParam(value = "unit_sus_no1", required = false) String unit_sus_no,
			@RequestParam(value = "unit_name1", required = false) String unit_name,
			@RequestParam(value = "from_date1", required = false) String from_date,
			@RequestParam(value = "to_date1", required = false) String to_date,
			@RequestParam(value = "assets_type1", required = false) String assets_type,
			@RequestParam(value = "status1", required = false) String status,
			@RequestParam(value = "s_state1", required = false) String s_state) {

		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		Mmap.put("roleSusNo", roleSusNo);

	
		Mmap.put("getTargetUnitNameList", allt.getTargetUnitNameList(roleSusNo, session));

		//Mmap.put("model_number1", model_number);
		Mmap.put("machine_number1", machine_number);
		Mmap.put("ram_capi1", ram_capi);
		Mmap.put("hdd_capi1", hdd_capi);
		Mmap.put("operating_system1", operating_system);
		Mmap.put("from_date1", from_date);
		Mmap.put("to_date1", to_date);
		Mmap.put("assets_type1", assets_type);
		Mmap.put("status1", status);
		Mmap.put("s_state1", s_state);
		Mmap.put("unit_name1", unit_name);
		Mmap.put("unit_sus_no1", unit_sus_no);
		Mmap.put("asset_typeList", it_comm.getAssetList());
		Mmap.put("ramList", it_comm.getRamList());
		Mmap.put("hddList", it_comm.getHDDList());
		Mmap.put("sddList", it_comm.getSDDList());
		Mmap.put("osList", it_comm.getOperatingSystem());
		Mmap.put("ComputingAssetList", it_comm.getComputingAssetList());
		Mmap.put("getServiceable_StateList", it_comm.getServiceable_StateList());
		return new ModelAndView("SearchDeleteAssetsTiles");
	}

	@RequestMapping(value = "/getFilteredassetcomputingDelete", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getFilteredassetcomputingDelete(int startPage, int pageLength,
			String Search, String orderColunm, String orderType, String status, String assets_type, 
			String machine_number, String ram_capi, String hdd_capi, String operating_system, String from_date,
			String to_date, String s_state, String unit_sus_no, String unit_name,String section, HttpSession sessionUserId)
					throws SQLException {

		return cd.Search_Computing_AssetsDelete(startPage, pageLength, Search, orderColunm, orderType, status,
				assets_type,  machine_number, ram_capi, hdd_capi, operating_system, from_date, to_date,
				s_state, unit_sus_no, unit_name,section, sessionUserId);
	}

	@RequestMapping(value = "/getTotalAssetCount1Delete", method = RequestMethod.POST)
	public @ResponseBody long getTotalAssetCount1Delete(String Search, String orderColunm, String orderType,
			String status, String assets_type,  String machine_number, String ram_capi,
			String hdd_capi, String operating_system, String from_date, String to_date, String s_state,
			String unit_sus_no, String unit_name,String section, HttpSession sessionUserId) throws SQLException {

		return cd.getAppComputingassetCountList1Delete(Search, orderColunm, orderType, status, assets_type,
				 machine_number, ram_capi, hdd_capi, operating_system, from_date, to_date, s_state,
				unit_sus_no, unit_name,section, sessionUserId);
	}

	@RequestMapping(value = "/ComputingAssertsSearchDelete", method = RequestMethod.POST)
	public ModelAndView ComputingAssertsSearchDelete(@ModelAttribute("id1") String a,
			@RequestParam(value = "msg", required = false) String msg, BindingResult result, HttpServletRequest request,
			ModelMap model, HttpSession session1) {

		String[] id_list = a.split(":");
		String roleid = session1.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_Computing_AssetsUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		List<String> liststr = new ArrayList<String>();
		try {
			for (int i = 0; i < id_list.length; i++) {
				int id = Integer.parseInt(id_list[i]);

				Session session = HibernateUtil.getSessionFactory().openSession();
				Transaction tx = session.beginTransaction();
				String hqlUpdate = "";
				int app;
				hqlUpdate = "delete from Assets_Main where id=:id";
				app = session.createQuery(hqlUpdate).setInteger("id", id).executeUpdate();
				tx.commit();

				session.close();
				if (app > 0) {
					liststr.add("Data Deleted Successfully");
				} else {
					liststr.add("Data not Deleted");
				}
				model.put("msg", liststr.get(0));
			}
		} catch (Exception e) {
			liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED");
			model.put("msg", liststr.get(0));
		}
		return new ModelAndView("redirect:SearchDelete_Computing_AssetsUrl");
	}


	@RequestMapping(value = "/ComputingAssertsArchive", method = RequestMethod.POST)
public ModelAndView ComputingAssertsArchive(@ModelAttribute("id2") String id,
		@RequestParam(value = "msg", required = false) String msg, 
		 @RequestParam(value = "screenurl3", required = false) String screenurl,BindingResult result, HttpServletRequest request,
		ModelMap model, HttpSession session1) {

	String roleid = session1.getAttribute("roleid").toString();
	Boolean val = roledao.ScreenRedirect("Search_Computing_AssetsUrl", roleid);
	if (val == false) {
		return new ModelAndView("AccessTiles");
	}
	if (request.getHeader("Referer") == null) {
		msg = "";
		// return new ModelAndView("redirect:bodyParameterNotAllow");
	}

	List<String> liststr = new ArrayList<String>();
	try {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hqlUpdate = "";
		int app;
		hqlUpdate = "update Assets_Main set status=:status where id=:id";
		app = session.createQuery(hqlUpdate).setInteger("status", 7).setInteger("id", Integer.parseInt(id))
				.executeUpdate();
		tx.commit();
		session.close();
		if (app > 0) {
			liststr.add("Data Archived Successfully");
		} else {
			liststr.add("Data not Archived");
		}
		model.put("msg", liststr.get(0));
	} catch (Exception e) {
		liststr.add("CAN NOT ARCHIVE THIS DATA BECAUSE IT IS ALREADY IN USED");
		model.put("msg", liststr.get(0));
	}
	return new ModelAndView("redirect:"+screenurl);
}
	@RequestMapping(value = "/AssignAssertsArchive", method = RequestMethod.POST)
public ModelAndView AssignAssertsArchive(@ModelAttribute("id2") String id,
		@RequestParam(value = "msg", required = false) String msg, 
		 @RequestParam(value = "screenurl3", required = false) String screenurl,
		 @RequestParam(value = "status2", required = false) String status2,
		 BindingResult result, HttpServletRequest request,
		ModelMap model, HttpSession session1) {

	String roleid = session1.getAttribute("roleid").toString();
	Boolean val = roledao.ScreenRedirect("search_asset_assignurl", roleid);
	if (val == false) {
		return new ModelAndView("AccessTiles");
	}
	if (request.getHeader("Referer") == null) {
		msg = "";
		// return new ModelAndView("redirect:bodyParameterNotAllow");
	}

	List<String> liststr = new ArrayList<String>();
	try {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		String hqlUpdate = "";
		int app;
		hqlUpdate = "update Assets_Main set assign_user_status=:status where id=:id";
		app = session.createQuery(hqlUpdate).setInteger("status", status2!=null && status2.equals("7")?-1:7).setInteger("id", Integer.parseInt(id))
				.executeUpdate();
		tx.commit();
		session.close();
		if (app > 0) {
			
			String as=status2.equals("7")?"Data Unarchived Successfully":"Data Archived Successfully";
			 liststr.add(as);
			
		} else {
			String as=status2.equals("7")?"Data not Archived":"Data not Unarchived";
			liststr.add(as);
		}
		model.put("msg", liststr.get(0));
	} catch (Exception e) {
		liststr.add("CAN NOT ARCHIVE THIS DATA BECAUSE IT IS ALREADY IN USED");
		model.put("msg", liststr.get(0));
	}
	return new ModelAndView("redirect:"+screenurl);
}


	
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/getSupplierNameList", method = RequestMethod.POST)
	public @ResponseBody List<Object> getSupplierNameList(String loginname, HttpSession sessionUserId) {
	Session session = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = session.beginTransaction();
	Query q = session.createQuery("select id,supplier from TB_MASTER_SUPPLY_MASTER where supplier like :supplier order by supplier").setMaxResults(10);
	q.setParameter("supplier", "%"+loginname+"%");
	@SuppressWarnings("unchecked")
	List<Object[]> list = (List<Object[]>) q.list(); 
	tx.commit();
	session.close();
	String enckey = hex_asciiDao.getAlphaNumericString();
	Cipher c = null;
	
		    try {
		        c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId, enckey);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    
		    List<Object> FinalList = new ArrayList<Object>();
		    for (Object[] row : list) { // Iterate through the list of Object arrays
		        String supplier = (String) row[1]; // Get the supplier name
		        byte[] encCode = null;
		        try {
		            encCode = c.doFinal(supplier.getBytes()); // Encrypt the supplier name
		        } catch (IllegalBlockSizeException | BadPaddingException e) {
		            e.printStackTrace();
		        }
		        
		        // Base64 encode the encrypted supplier name
		        String base64EncodedEncryptedSupplier = Base64.encodeBase64String(encCode);
		        
		        // Create a JSON object to hold both id and supplier
		        Map<String, String> supplierData = new HashMap<>();
		        supplierData.put("id", row[0].toString()); // id
		        supplierData.put("supplier", base64EncodedEncryptedSupplier); // encrypted supplier
		        
		        FinalList.add(supplierData); // Add the map to the final list
		    }
		    
		    // Enc Key Append Last value of List
		    FinalList.add(enckey + "4bsjyg==");
		    return FinalList;
		}
	
	

	public String getContact_number(String loginname) {
		String result="";
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select contact_number from TB_MASTER_SUPPLY_MASTER where supplier =:supplier  ").setMaxResults(10);
		q.setParameter("supplier", loginname);
		@SuppressWarnings("unchecked")
		List<String> result2= (List<String>) q.list();
		if(result2.size()>0)
		{
			result= result2.get(0).toString();
		}
		
		tx.commit();
		session.close();
		return result;
	}
		
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getActiveSupplierDataList", method = RequestMethod.POST)
	public @ResponseBody List<TB_MASTER_SUPPLY_MASTER> getActiveSupplierDataList(@RequestParam(value = "id", required = false) Integer id, HttpSession sessionUserId) {
	      Session session = HibernateUtil.getSessionFactory().openSession();
	      Transaction tx = session.beginTransaction();
	      
	      Query<TB_MASTER_SUPPLY_MASTER> q = session.createQuery("FROM TB_MASTER_SUPPLY_MASTER WHERE id = :id", TB_MASTER_SUPPLY_MASTER.class);
	      q.setParameter("id", id);
	  
	      List<TB_MASTER_SUPPLY_MASTER> list = q.list();
	      
	      tx.commit();
	      session.close();
	      return list;
	}
	
	
	@RequestMapping(value = "/admin/assigntouserurl", method = RequestMethod.GET)
	public ModelAndView assigntouserUrl(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();

		if (request.getHeader("Referer") == null) {
			msg = "";
		}

		Mmap.put("AntivirusList", it_comm.getAntivirus());
		Mmap.put("ComputingAssetList", it_comm.getComputingAssetList());
		Mmap.put("processor_typeList", it_comm.getProcessorList());
		Mmap.put("ramList", it_comm.getRamList());
		Mmap.put("hddList", it_comm.getHDDList());
		Mmap.put("sddList", it_comm.getSDDList());
		Mmap.put("osList", it_comm.getOperatingSystem());
		Mmap.put("officeList", it_comm.getOffice());
		Mmap.put("osFirmwareList", it_comm.getOsFirmware());
		Mmap.put("dplyEnvList", it_comm.getdplyEnv());
		Mmap.put("UNServiceableList", it_comm.UNServiceableList());
		Mmap.put("getConnectingTechList", it_comm.getConnectingTechList());
		Mmap.put("getEthernetPortList", it_comm.getEthernetPortList());
		Mmap.put("getServiceable_StateList", it_comm.getServiceable_StateList());
		Mmap.put("getBudgetHeadList", it_comm.getBudgetHeadList());
		 Mmap.put("getSectionNameList", roleController.getSectionNameList());
		 Mmap.put("getMaintainceAgencyList", it_comm.getMaintainceAgencyList());
		 Mmap.put("getTypeOfSecuserList", it_comm.getTypeOfSecuserList());
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		Mmap.put("roleSusNo", roleSusNo);
		Mmap.put("getTargetUnitNameList", allt.getTargetUnitNameList(roleSusNo, session));
		Mmap.put("msg", msg);
		return new ModelAndView("AssignuserTiles", "MainAssetsassignCmd", new Assign_Asset_to_user());
	}
	
	
	@RequestMapping(value = "assignTouserChange", method = RequestMethod.POST)
	public ModelAndView assignTouserChange(ModelMap Mmap, HttpSession session, HttpServletRequest request,
	        @RequestParam(value = "msg", required = false) String msg,
	        @RequestParam(value = "assetId", required = false) String assetId,
	        @RequestParam(value = "Username", required = false) String Username) {

	    Mmap.put("msg", ""); // Initialize msg to an empty string

	    if (assetId != null && !assetId.isEmpty() && Username != null && !Username.isEmpty()) {
	        try {
	            int assetIdInt = Integer.parseInt(assetId);

	            Session session1 = HibernateUtil.getSessionFactory().openSession();
	            Transaction tx = session1.beginTransaction();

	            String hqlUpdate = "update Assets_Main set assign_user_status = :assign_user_status, assign_user = :assign_user where id = :id";
	            int app = session1.createQuery(hqlUpdate)
	                    .setString("assign_user", Username)
	                    .setInteger("assign_user_status", 0)
	                    .setInteger("id", assetIdInt)
	                    .executeUpdate();

	            tx.commit(); // Commit the transaction
	            session1.close(); // Close the session

	            Mmap.put("msg", app > 0 ? "Data Saved Successfully." : "Data Not Saved.");

	        } catch (NumberFormatException e) {
	            Mmap.put("msg", "Invalid Asset ID format.");
	            // Log the exception for debugging purposes
	            e.printStackTrace();
	        } catch (Exception e) {
	            Mmap.put("msg", "Error issuing asset: " + e.getMessage());
	            // Log the exception
	            e.printStackTrace();
	        }
	    } else {
	        Mmap.put("msg", "Asset ID and Username are required.");
	    }

	    return new ModelAndView("redirect:search_asset_assignurl");
	}

	@RequestMapping(value = "assignTouserApprove", method = RequestMethod.POST)
	public ModelAndView assignTouserApprove(ModelMap Mmap, HttpSession session, HttpServletRequest request,
	        @RequestParam(value = "msg", required = false) String msg,
	        @RequestParam(value = "assetId3", required = false) String assetId
	  ) {

	    Mmap.put("msg", ""); // Initialize msg to an empty string

	    if (assetId != null && !assetId.isEmpty()) {
	        try {
	            int assetIdInt = Integer.parseInt(assetId);

	            Session session1 = HibernateUtil.getSessionFactory().openSession();
	            Transaction tx = session1.beginTransaction();

	            String hqlUpdate = "update Assets_Main set assign_user_status = :assign_user_status where id = :id";
	            int app = session1.createQuery(hqlUpdate)
	               
	                    .setInteger("assign_user_status", 1)
	                    .setInteger("id", assetIdInt)
	                    .executeUpdate();

	            tx.commit(); // Commit the transaction
	            session1.close(); // Close the session

	            Mmap.put("msg", app > 0 ? "Asset Issued Successfully" : "Asset Not Issued.");

	        } catch (NumberFormatException e) {
	            Mmap.put("msg", "Invalid Asset ID format.");
	            // Log the exception for debugging purposes
	            e.printStackTrace();
	        } catch (Exception e) {
	            Mmap.put("msg", "Error issuing asset: " + e.getMessage());
	            // Log the exception
	            e.printStackTrace();
	        }
	    } else {
	        Mmap.put("msg", "Asset ID is  required.");
	    }

	    return new ModelAndView("redirect:search_asset_assignurl");
	}
	@RequestMapping(value = "assigntouserurla", method = RequestMethod.POST)
	public ModelAndView assigntouserUrlPOST(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "assetId", required = false) String assetId) {

		String roleid = session.getAttribute("roleid").toString();

		if (request.getHeader("Referer") == null) {
			msg = "";
		}

		Mmap.put("getSectionNameList", roleController.getSectionNameList());
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Assets_Main assetupd = (Assets_Main) sessionHQL.get(Assets_Main.class, Integer.parseInt(assetId));
		if (((TB_MASTER_MAKE) sessionHQL.get(TB_MASTER_MAKE.class, assetupd.getMake_name())).getMake_name()
				.equals("OTHERS")) {
			TB_MASTER_MAKE make_mstr1 = (TB_MASTER_MAKE) sessionHQL.get(TB_MASTER_MAKE.class,
					Integer.parseInt(assetupd.getBrand_other()));
			Mmap.put("make_mstr_other", make_mstr1.getMake_name());
			Mmap.put("make_mstr_id", make_mstr1.getId());
		}

		if (((TB_MASTER_MODEL) sessionHQL.get(TB_MASTER_MODEL.class, assetupd.getModel_name())).getModel_name()
				.equals("OTHERS")) {
			TB_MASTER_MODEL model_mstr1 = (TB_MASTER_MODEL) sessionHQL.get(TB_MASTER_MODEL.class,
					Integer.parseInt(assetupd.getModel_other()));
			Mmap.put("model_mstr_other", model_mstr1.getModel_name());
			Mmap.put("model_mstr_id", model_mstr1.getId());
		}

		if (((TB_MSTR_PROCESSOR_TYPE_M) sessionHQL.get(TB_MSTR_PROCESSOR_TYPE_M.class, assetupd.getProcessor_type()))
				.getProcessor_type().equals("OTHERS")) {
			TB_MSTR_PROCESSOR_TYPE_M pro_mstr1 = (TB_MSTR_PROCESSOR_TYPE_M) sessionHQL
					.get(TB_MSTR_PROCESSOR_TYPE_M.class, Integer.parseInt(assetupd.getPro_type_other()));
			Mmap.put("pro_type_other", pro_mstr1.getProcessor_type());
			Mmap.put("pro_type_id", pro_mstr1.getId());
		}
		if(assetupd.getOffice()!=0)
		{

		if (((TB_MSTR_OFFICE_M) sessionHQL.get(TB_MSTR_OFFICE_M.class, assetupd.getOffice())).getOffice()
				.equals("OTHERS")) {
			TB_MSTR_OFFICE_M office_mstr1 = (TB_MSTR_OFFICE_M) sessionHQL.get(TB_MSTR_OFFICE_M.class,
					Integer.parseInt(assetupd.getOffice_other()));
			Mmap.put("office_mstr_other", office_mstr1.getOffice());
			Mmap.put("office_mstr_id", office_mstr1.getId());
		}
		}
		if (((TB_MSTR_OS_M) sessionHQL.get(TB_MSTR_OS_M.class, assetupd.getOperating_system())).getOs()
				.equals("OTHERS")) {
			TB_MSTR_OS_M os_mstr1 = (TB_MSTR_OS_M) sessionHQL.get(TB_MSTR_OS_M.class,
					Integer.parseInt(assetupd.getOs_other()));
			Mmap.put("os_mstr_other", os_mstr1.getOs());
			Mmap.put("os_mstr_id", os_mstr1.getId());
		}

		Mmap.put("MakeList", it_comm.getMakeName());
		Mmap.put("ModelList", it_comm.getModelNameList());
		Mmap.put("AntivirusList", it_comm.getAntivirus());
		Mmap.put("ComputingAssetList", it_comm.getComputingAssetList());
		Mmap.put("processor_typeList", it_comm.getProcessorList());
		Mmap.put("ramList", it_comm.getRamList());
		Mmap.put("hddList", it_comm.getHDDList());
		Mmap.put("sddList", it_comm.getSDDList());
		Mmap.put("osList", it_comm.getOperatingSystem());
		Mmap.put("officeList", it_comm.getOffice());
		Mmap.put("osFirmwareList", it_comm.getOsFirmware());
		Mmap.put("dplyEnvList", it_comm.getdplyEnv());
		Mmap.put("UNServiceableList", it_comm.UNServiceableList());
		Mmap.put("getConnectingTechList", it_comm.getConnectingTechList());
		Mmap.put("getEthernetPortList", it_comm.getEthernetPortList());
		Mmap.put("getServiceable_StateList", it_comm.getServiceable_StateList());
		Mmap.put("getBudgetHeadList", it_comm.getBudgetHeadList());
		Mmap.put("getBudgetCodeList", it_comm.getBudgetCodeList());
		Mmap.put("ServiceableList", it_comm.ServiceableList());
		 Mmap.put("getSectionNameList", roleController.getSectionNameList());
		 Mmap.put("getMaintainceAgencyList", it_comm.getMaintainceAgencyList());
		 Mmap.put("contactNumber",getContact_number( assetupd.getSupplier_name().toString()));
//		 Mmap.put("screenurl",screenurl);
		
		
		
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		Mmap.put("roleSusNo", roleSusNo);
		Mmap.put("getTargetUnitNameList", allt.getTargetUnitNameList(roleSusNo, session));
		Mmap.put("msg", msg);

		 return new ModelAndView("AssignuserTiles", "ViewAssetsCmd", assetupd);
	}
	
	
	
	
	
	@RequestMapping(value = "/admin/search_asset_assignurl", method = RequestMethod.GET)
	public ModelAndView Search_Asset_AssignUrl(ModelMap Mmap, HttpSession sessionUserId, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = sessionUserId.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_Computing_AssetsUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		Mmap.put("roleSusNo", roleSusNo);

		

		Mmap.put("msg", msg);
		Mmap.put("asset_typeList", it_comm.getAssetList());
		Mmap.put("getServiceable_StateList", it_comm.getServiceable_StateList());
//		Mmap.put("status1", "0");
		Mmap.put("ComputingAssetList", it_comm.getComputingAssetList());
		Mmap.put("getSectionNameList", roleController.getSectionNameList());

		return new ModelAndView("SearchAssetsAssignTiles");
	}
	

	@RequestMapping(value = "/admin/Search_Computing_AssetsAssignUrl_1", method = RequestMethod.POST)
	public ModelAndView Search_Computing_AssetsAssignUrl_1(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "assets_type1", required = false) String assets_type,
			@RequestParam(value = "status1", required = false) String status,
			@RequestParam(value = "section1", required = false) String section) {

		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		Mmap.put("roleSusNo", roleSusNo);

		
		Mmap.put("asset_typeList", it_comm.getAssetList());
		Mmap.put("ramList", it_comm.getRamList());
		Mmap.put("hddList", it_comm.getHDDList());
		Mmap.put("sddList", it_comm.getSDDList());
		Mmap.put("osList", it_comm.getOperatingSystem());
		Mmap.put("getServiceable_StateList", it_comm.getServiceable_StateList());
//		Mmap.put("status1", "0");
		Mmap.put("ComputingAssetList", it_comm.getComputingAssetList());
		Mmap.put("getSectionNameList", roleController.getSectionNameList());
		return new ModelAndView("SearchAssetsAssignTiles");
	}
	@RequestMapping(value = "/assetassignaction", method = RequestMethod.POST)
	public ModelAndView assetassignaction(
	        @ModelAttribute("MainAssetsassignCmd") Assign_Asset_to_user BAN,
	        BindingResult result, HttpServletRequest request, ModelMap Mmap,
	        @RequestParam(value = "msg", required = false) String msg,
	        HttpSession session) throws ParseException {

	    Session sessionHQL = null;
	    Transaction tx = null;
	    Date completeDateAndTime = new Date();
        Date date = new Date();

	    try {
	        sessionHQL = HibernateUtil.getSessionFactory().openSession();
	        tx = sessionHQL.beginTransaction();
	        String section = request.getParameter("section");
	        String asset_type = request.getParameter("asset_type");
	        String make_name = request.getParameter("make_name");
	        String model_name = request.getParameter("model_name");
	        String processor_type = request.getParameter("processor_type");
	        String ram_capi = request.getParameter("ram_capi");
	        String hdd_capi = request.getParameter("hdd_capi");
	        String ssdcheck = request.getParameter("ssdcheck");
	        String ssdcapi = request.getParameter("ssd_capi");
	        String operating_system = request.getParameter("operating_system");
	        String assignuser = request.getParameter("assignuser");
	        String assetcountStr = request.getParameter("assetcount");

	        int assetcount;
	        try {
	            assetcount = Integer.parseInt(assetcountStr);
	        } catch (NumberFormatException e) {
	            Mmap.put("msg", "Invalid asset count. Please enter a valid number.");
	            return new ModelAndView("AssignuserTiles", "MainAssetsassignCmd", new Assign_Asset_to_user());
	        }

	        Integer availableAssetCount = cd.getassetcount(section, asset_type, make_name, model_name, processor_type, ram_capi, hdd_capi, ssdcapi, operating_system);

	        if (availableAssetCount == null) {
	            Mmap.put("msg", "Error retrieving available asset count. Please try again.");
	            return new ModelAndView("AssignuserTiles", "MainAssetsassignCmd", new Assign_Asset_to_user());
	        }
	        int totalAssetsAssignedSoFar = cd.getTotalAssetsAssigned(section, asset_type, make_name, model_name, processor_type, ram_capi, hdd_capi, ssdcapi, operating_system);  
	        int remainingAvailableCount = availableAssetCount - totalAssetsAssignedSoFar;

	        if (assetcount <= remainingAvailableCount) {
	            BAN.setSection(section);
	            BAN.setAsset_type(asset_type);
	            BAN.setMake_name(make_name);
	            BAN.setModel_name(model_name);
	            BAN.setProcessor_type(processor_type);
	            BAN.setRam_capi(ram_capi);
	            BAN.setHdd_capi(hdd_capi);
	            BAN.setSsd_capi(ssdcapi);
	            BAN.setOperating_system(operating_system);
	            BAN.setAssignuser(assignuser);
	            BAN.setAssetcount(assetcountStr);
	            BAN.setAssignstatus("0");
	            BAN.setCreated_date(date);

	            sessionHQL.save(BAN);
	            sessionHQL.flush();
	            sessionHQL.clear();

	            tx.commit();
	            Mmap.put("msg", "Assets Assigned Successfully.");

	        } else {
	            Mmap.put("msg", "Requested asset count (" + assetcount + ") exceeds remaining available count (" + remainingAvailableCount + ").");
	        }


	    } catch (RuntimeException e) {
	        try {
	            if (tx != null) {
	                tx.rollback();
	            }
	            Mmap.put("msg", "Transaction rolled back due to error: " + e.getMessage());
	        } catch (RuntimeException rbe) {
	            Mmap.put("msg", "Couldn’t roll back transaction: " + rbe.getMessage());
	        }
	        throw e;
	    } finally {
	        if (sessionHQL != null) {
	            sessionHQL.close();
	        }
	    }

	    return new ModelAndView("AssignuserTiles", "MainAssetsassignCmd", new Assign_Asset_to_user());
	}
		@RequestMapping(value = "/Approve_ComputingAssetsAssignData", method = RequestMethod.POST)
		public @ResponseBody List<String> Approve_ComputingAssetsAssignData(String a, String status, HttpSession session)
				throws SQLException {
			String sus_no = session.getAttribute("roleSusNo").toString();
			String username = session.getAttribute("username").toString();
			List<String> list2 = new ArrayList<String>();
			// List<Map<String, Object>> ls = cd.approve_Master_Asset(a);

			list2.add(cd.approve_AssetsAssignData(a,status));
			return list2;
		}
		
		 @RequestMapping(value = "/getassetcount")
		    @ResponseBody
		    public int getAssetCount(String section, String asset_type, String make_name, String model_name,
		                             String processor_type, String ram_capi, String hdd_capi, String ssd_capi,
		                             String operating_system) {
		        return cd.getcountqtyassignuser(section, asset_type, make_name, model_name, processor_type,
		                                     ram_capi, hdd_capi, ssd_capi, operating_system);
		    }
		 
			
			@RequestMapping(value = "/admin/Excel_Computing_Assets_AssignSearch", method = RequestMethod.POST)

			public ModelAndView Excel_Computing_Assets_AssignSearch(ModelMap Mmap, HttpSession session,

					@RequestParam(value = "msg", required = false) String msg,
					@RequestParam(value = "assets_type2", required = false) String assets_type,
					@RequestParam(value = "status2", required = false) String status,
					@RequestParam(value = "section2", required = false) String section) {
				ArrayList<ArrayList<String>> Excel = cd.excel_Computing_Assets_AssignReportDataList(session,assets_type,status,section);

				

			String username =  session.getAttribute("username").toString();
				Mmap.put("msg", msg);
				String Heading ="SEARCH COMPUTING ASSIGN ASSESTS";
				Date date = new Date();

				String foot = " Report Generated On "+ new SimpleDateFormat("dd-MM-yyyy").format(date);

					List<String> TH = new ArrayList<String>();
					
					TH.add("Section");

					TH.add("Assets Type");
				//	TH.add("Model Number");
					TH.add("Make Name");

					TH.add("Model Name");

					TH.add("Assign user");

					TH.add("Quantity");				

					TH.add("Date & Time");

				return new ModelAndView(new ExcelComputingUserListReportView("L", TH, Heading, username), "userList", Excel);

			}
			
			
			// edit methode
			
			@RequestMapping(value = "/admin/ComputingAssertsassignEdit", method = RequestMethod.POST)
			public ModelAndView ComputingAssertsassignEdit(ModelMap Mmap, HttpSession session, HttpServletRequest request,
					@RequestParam(value = "msg", required = false) String msg,
					@RequestParam(value = "updateid", required = false) int id,
					 @RequestParam(value = "screenurl", required = false) String screenurl
			       
		) throws ParseException 
			{

				String roleid = session.getAttribute("roleid").toString();
				Boolean val = roledao.ScreenRedirect("Search_Computing_AssetsUrl", roleid);
				if (val == false) {
					return new ModelAndView("AccessTiles");
				}
				if (request.getHeader("Referer") == null) {
					msg = "";
					// return new ModelAndView("redirect:bodyParameterNotAllow");
				}

				Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

				Assign_Asset_to_user assetupd = (Assign_Asset_to_user) sessionHQL.get(Assign_Asset_to_user.class, id);


				String roleSusNo = session.getAttribute("roleSusNo").toString();
				String roleAccess = session.getAttribute("roleAccess").toString();
				Mmap.put("roleSusNo", roleSusNo);
				
				
				Mmap.put("AntivirusList", it_comm.getAntivirus());
				Mmap.put("ComputingAssetList", it_comm.getComputingAssetList());
				Mmap.put("processor_typeList", it_comm.getProcessorList());
				Mmap.put("ramList", it_comm.getRamList());
				Mmap.put("hddList", it_comm.getHDDList());
				Mmap.put("sddList", it_comm.getSDDList());
				Mmap.put("osList", it_comm.getOperatingSystem());
				Mmap.put("officeList", it_comm.getOffice());
				Mmap.put("osFirmwareList", it_comm.getOsFirmware());
				Mmap.put("dplyEnvList", it_comm.getdplyEnv());
				Mmap.put("UNServiceableList", it_comm.UNServiceableList());
				Mmap.put("getConnectingTechList", it_comm.getConnectingTechList());
				Mmap.put("getEthernetPortList", it_comm.getEthernetPortList());
				Mmap.put("getServiceable_StateList", it_comm.getServiceable_StateList());
				Mmap.put("getBudgetHeadList", it_comm.getBudgetHeadList());
				Mmap.put("getSectionNameList", roleController.getSectionNameList());
				 Mmap.put("getMaintainceAgencyList", it_comm.getMaintainceAgencyList());
				 Mmap.put("getTypeOfSecuserList", it_comm.getTypeOfSecuserList());
				 Mmap.put("screen_url", screenurl);
				 return new ModelAndView("EditAssignuserTiles", "MainAssetsassigneditCmd", assetupd);
			}
			///////////////////////////assign assest computing 
			@SuppressWarnings("deprecation")
			@RequestMapping(value = "/getassets_uniqidList", method = RequestMethod.POST)
			public @ResponseBody List<Object> getassets_uniqidList(String loginname, HttpSession sessionUserId) {
				
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			Query q = session.createQuery("select id,assetid from Assets_Main where assetid like :assetid order by assetid").setMaxResults(10);
			q.setParameter("assetid", "%"+loginname+"%");
			@SuppressWarnings("unchecked")
			List<Object[]> list = (List<Object[]>) q.list(); 
			tx.commit();
			session.close();
			String enckey = hex_asciiDao.getAlphaNumericString();
			Cipher c = null;
			
				    try {
				        c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId, enckey);
				    } catch (Exception e) {
				        e.printStackTrace();
				    }
				    
				    List<Object> FinalList = new ArrayList<Object>();
				    for (Object[] row : list) { // Iterate through the list of Object arrays
				        String supplier = (String) row[1]; // Get the supplier name
				        byte[] encCode = null;
				        try {
				            encCode = c.doFinal(supplier.getBytes()); // Encrypt the supplier name
				        } catch (IllegalBlockSizeException | BadPaddingException e) {
				            e.printStackTrace();
				        }
				        
				        // Base64 encode the encrypted supplier name
				        String base64EncodedEncryptedSupplier = Base64.encodeBase64String(encCode);
				        
				        // Create a JSON object to hold both id and supplier
				        Map<String, String> supplierData = new HashMap<>();
				        
				        supplierData.put("assetid", base64EncodedEncryptedSupplier); // encrypted supplier
				        
				        FinalList.add(supplierData); // Add the map to the final list
				    }
				    
				    // Enc Key Append Last value of List
				    FinalList.add(enckey + "4bsjyg==");
				    return FinalList;
				}
		
}



   





		