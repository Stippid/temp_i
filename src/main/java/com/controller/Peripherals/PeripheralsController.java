package com.controller.Peripherals;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.controller.login.RoleController;
import com.controller.orbat.AllMethodsControllerOrbat;
import com.controller.tms.AllMethodsControllerTMS;
import com.controller.validation.CheckFileFormatValidation;
import com.controller.validation.ValidationController;
import com.dao.Peripheral.PeripheralDao;
import com.dao.login.RoleBaseMenuDAO;
import com.models.assets.It_Asset_Peripherals;
import com.models.assets.TB_PERIPHERAL_CHILD;
import com.models.itasset.assets.Assign_Asset_to_user;
import com.models.itasset.assets.Peripheral_Asset_Assign_To_User;
import com.models.itasset.master.TB_MASTER_MAKE;
import com.models.itasset.master.TB_MASTER_MODEL;
import com.models.itasset.master.TB_MSTR_CONNECTIVITY;
import com.models.itasset.master.TB_MSTR_TYPE_M;
import com.models.itasset.master.TB_MSTR_TYPE_OF_HW_M;
import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class PeripheralsController {

	@Autowired
	RoleController rolecontroller = new RoleController();

	@Autowired
	private PeripheralDao cd;

	ValidationController validation = new ValidationController();

	AllMethodsControllerOrbat com = new AllMethodsControllerOrbat();
	AllMethodsControllerTMS allt = new AllMethodsControllerTMS();

	@Autowired
	RoleBaseMenuDAO roledao;

	It_CommonController it_comm = new It_CommonController();

	@RequestMapping(value = "/admin/PeripheralsUrl", method = RequestMethod.GET)
	public ModelAndView AllDashboard(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("PeripheralsUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		Mmap.put("roleSusNo", roleSusNo);

		Mmap.put("getPeripheral", it_comm.getPeripheral());
		Mmap.put("YearOfProc", it_comm.getYearOfProc());
		Mmap.put("YearOfManufacturing", it_comm.getYearOfManufacturing());
		Mmap.put("UpsCapacity", it_comm.getUpsCapacity());
		Mmap.put("UNServiceableList", it_comm.UNServiceableList());
		Mmap.put("getDisplay_ConnectorList", it_comm.getDisplay_ConnectorList());
		Mmap.put("getConnectivity_TypeList", it_comm.getConnectivity_TypeList());
		Mmap.put("getServiceable_StateList", it_comm.getServiceable_StateList());
		Mmap.put("getPaper_SizeList", it_comm.getPaper_SizeList());
		Mmap.put("getBudgetHeadList", it_comm.getBudgetHeadList());
		Mmap.put("hw_interfaceList", it_comm.hw_interfaceList());
		Mmap.put("getEthernet_portList", it_comm.getEthernet_portList());
		Mmap.put("getManagement_layerList", it_comm.getManagement_layerList());
		Mmap.put("getNetwork_featuresList", it_comm.getNetwork_featuresList());
		Mmap.put("getSectionNameList", rolecontroller.getSectionNameList());
		Mmap.put("getMaintainceAgencyList", it_comm.getMaintainceAgencyList());
		Mmap.put("msg", msg);
		return new ModelAndView("PeripheralsTiles", "PeripheralCmd", new It_Asset_Peripherals());
	}

	@RequestMapping(value = "/PeripheralAction", method = RequestMethod.POST)
	public ModelAndView PeripheralAction(@ModelAttribute("PeripheralCmd") It_Asset_Peripherals peripheral,
			@RequestParam(value = "msg", required = false) String msg1,
			@RequestParam(value = "u_file1", required = false) MultipartFile u_file,
			@RequestParam(value = "br_certificate1", required = false) MultipartFile br_certificate,
			@RequestParam(value = "make_mstr_id", required = false) String make_mstr_id,
			@RequestParam(value = "model_mstr_id", required = false) String model_mstr_id,
			@RequestParam(value = "type_hw_id", required = false) String type_hw_id,
			@RequestParam(value = "pro_type_id", required = false) String pro_type_id,
			@RequestParam(value = "pro_connect_id", required = false) String pro_connect_id,
			@RequestParam(value = "u_file_hid", required = false) String u_file_hid,
			@RequestParam(value = "screenUrl", required = false) String screenurl, HttpServletRequest request,
			ModelMap model, HttpSession session) throws ParseException {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_PeripheralUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg1 = "";
			// return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		int id = peripheral.getId() > 0 ? peripheral.getId() : 0;

		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		Date date = new Date();
		Date warrenty_dt = null;
		Date proc_dt = null;

		// ** BISG AHM **//

		Date unsv_from_dt1 = null;

		// **END BISG AHM **//

		String rediurl = "";
		if (id == 0) {
			rediurl = "redirect:PeripheralsUrl";

		} else {
			rediurl = "redirect:Search_PeripheralUrl";
		}
		String assets_type = request.getParameter("assets_type");
		String make_name = request.getParameter("make_name");
		String model_name = request.getParameter("model_name");
		String type_of_hw = request.getParameter("type_of_hw");
		String type = request.getParameter("type");
		String warranty = request.getParameter("warrantydt");
		String warrantycheck = request.getParameter("warrantycheck");
		String s_state = request.getParameter("s_state");
		String unserviceable_state = request.getParameter("unserviceable_state");
		// ** BISG AHM **//
		String unsv_from_date = request.getParameter("unsv_from_dt1");
		// **END BISG AHM **//
		String brand_other = request.getParameter("brand_other");
		String model_other = request.getParameter("model_other");

		String peri_hw_other = request.getParameter("peri_hw_other");
		String model_type_other = request.getParameter("model_type_other");
		String connectivity_other = request.getParameter("connectivity_other");
		String proc_date = request.getParameter("proc_dt");
		String b_head = request.getParameter("b_head");
		int assetcount = Integer.parseInt(request.getParameter("assetcount"));
		String crv_no = request.getParameter("crv_no");
		String section = request.getParameter("section");
		String username2 = request.getParameter("user_name");
		String maintainAgency = request.getParameter("maintainAgency");

		String supplier = request.getParameter("supplier");
		String u_file1 = request.getParameter("u_file1");
		String br_certificate1 = request.getParameter("br_certificate1");
		String approveois = request.getParameter("approveois");
		String supplierId = request.getParameter("supplierId");


		String monitor_v = request.getParameter("monitor");
		String visulizer_v = request.getParameter("visulizer");

		String resolution = request.getParameter("resolution");
		String v_display_size = request.getParameter("v_display_size");
		String v_display_connector = request.getParameter("v_display_connector");


		if (section == null || section.equals("0") || section.equals("")) {
			model.put("msg", "Please Enter Section ");
			return new ModelAndView(rediurl);
		}

		if (assets_type == null || assets_type.equals("0") || assets_type.equals("")) {
			model.put("msg", "Please Select Peripheral Type ");
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

		if (type_of_hw == null || type_of_hw.equals("0") || type_of_hw.equals("")) {
			model.put("msg", "Please Select Type of Peripheral HW ");
			return new ModelAndView(rediurl);
		}

		String sus_no1 = session.getAttribute("roleSusNo").toString();

		/// BISAG V1 220822///

		if (section == null || section.equals("0") || section.equals("")) {
			model.put("msg", "Please Select Section");
			return new ModelAndView(rediurl);
		}

		for (int j = 1; j <= assetcount; j++) {
			String model_number = request.getParameter("model_no" + j);
			String machine_number = request.getParameter("machine_no" + j);
			String is_networked = request.getParameter("is_networked" + j);
			String ip_address = request.getParameter("ip_address" + j);

			//
			//				if(validation.ModelNumberLength(model_number)  == false){
			//			 		model.put("msg",validation.ModelNumber);
			//					return new ModelAndView(rediurl);
			//				}
			if (machine_number == null || machine_number == "") {
				model.put("msg", "Please  Enter Machine Number");
				return new ModelAndView(rediurl);
			}

			if (validation.MachineNumberLength(machine_number) == false) {
				model.put("msg", validation.MachineNumber);
				return new ModelAndView(rediurl);
			}
			for (int k = j + 1; k <= assetcount; k++) {
				String machine_n = request.getParameter("machine_no" + k);
				if (machine_number.equals(machine_n)) {
					model.put("msg", "You Have entered Duplicate machine number");
					return new ModelAndView(rediurl);

				}
			}

			if (is_networked != null && is_networked.toUpperCase().equals("YES")) {
				if (ip_address == null || ip_address.equals("") || ip_address.equals("0")) {
					model.put("msg", "Please Enter IP Address(For Networked Device)");
					return new ModelAndView(rediurl);
				}
			}
		}

		if (warrantycheck != null && warrantycheck.toUpperCase().equals("YES")) {

			if (warranty == null || warranty.equals("") || warranty.equals("DD/MM/YYYY")) {
				model.put("msg", "Please Select Warranty");
				return new ModelAndView(rediurl);
			} else {
				warrenty_dt = format.parse(warranty);
			}

		}

		if (s_state == null || s_state.equals("") || s_state.equals("0")) {
			model.put("msg", "Please Select Serviceable State");
			return new ModelAndView(rediurl);
		}
		if (s_state.equals("2")) {
			if (unserviceable_state == null || unserviceable_state.equals("") || unserviceable_state.equals("0")) {
				model.put("msg", "Please Select UN-Serviceable State");
				return new ModelAndView(rediurl);
			}
			// ** BISG AHM **//
			if (unsv_from_date == null || unsv_from_date.equals("") || unsv_from_date.equals("0")) {
				model.put("msg", "Please Select UN-Serviceable State");
				return new ModelAndView(rediurl);
			}
			// **END BISG AHM **//

			if (maintainAgency == null || maintainAgency.equals("") || maintainAgency.equals("0")) {
				model.put("msg", "Please Select Maintenance Agency");
				return new ModelAndView(rediurl);
			}
		}

		if (proc_date != null && !proc_date.equals("") && !proc_date.equals("DD/MM/YYYY")) {
			proc_dt = format.parse(proc_date);

		}

		if (b_head == null || b_head.equals("0")) {
			model.put("msg", "Please Enter Budget Head");
			return new ModelAndView(rediurl);
		}

		if (approveois != null && approveois.toUpperCase().equals("NO")) {
			if (supplier == null || supplier.equals("") || supplier.equals("")) {
				model.put("msg", "Please Enter Supplier Name.");
				return new ModelAndView(rediurl);
			}
		}
		//			if (approveois != null && approveois.toUpperCase().equals("NO")) {
		//				if (supplier == null || supplier.equals("") || supplier.equals("")) {
		//					model.put("msg", "Please Enter Supplier Name.");
		//					return new ModelAndView(rediurl);
		//			}

		//			if(b_code==null  || b_code.equals("0") ) {
		//				model.put("msg", "Please Enter Budget Code");
		//				return new ModelAndView(rediurl);
		//			}

		// ** BISG AHM **//
		if (unsv_from_date != null && !unsv_from_date.equals("") && !unsv_from_date.equals("DD/MM/YYYY")) {
			unsv_from_dt1 = format.parse(unsv_from_date);

		}

		DateWithTimeStampController timestamp = new DateWithTimeStampController();
		String extension = "";
		String fname = "";
		It_Asset_Peripherals T_asset = new It_Asset_Peripherals();

		//
		//			if(u_file_hid.equals(""))
		//			{
		//			if (!u_file.isEmpty()) {
		//				try {
		//					byte[] bytes = u_file.getBytes();
		//					String mnhFilePath = session.getAttribute("itAssetsFilePath").toString();
		//					File dir = new File(mnhFilePath);
		//					if (!dir.exists())
		//						dir.mkdirs();
		//					String filename = u_file.getOriginalFilename();
		//					int i = filename.lastIndexOf('.');
		//					if (i >= 0) {
		//						extension = filename.substring(i + 1);
		//					}
		//					fname = dir.getAbsolutePath() + File.separator +timestamp.currentDateWithTimeStampString()+"_"+sus_no1+"_IT_Doc."+extension;
		//					File serverFile = new File(fname);
		//					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
		//					stream.write(bytes);
		//					stream.close();
		////					T_asset.setU_file(fname);
		//					T_asset.setU_file(fname.replace("\\","/"));
		//				} catch (Exception e) {
		//				}
		//			}
		//			}
		// **END BISG AHM **//

		String username = session.getAttribute("username").toString();
		int userid = Integer.parseInt(session.getAttribute("userId").toString());
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {

			for (int i = 1; i <= assetcount; i++) {
				String machine_no = request.getParameter("machine_no" + i);
				String hqlUpdate = "select count(id) from It_Asset_Peripherals where id!=:id and machine_no=:machine_no ";
				Query query2 = sessionHQL.createQuery(hqlUpdate).setInteger("id", id)
						.setString("machine_no", machine_no).setMaxResults(1);
				Long c22 = (Long) query2.uniqueResult();
				if (c22 != null && c22 > 0) {
					model.put("msg", "Peripheral Asset With This Machine Number Already Exist");

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
							if (fileValidation.check_PDF_File(bytes)) {
								String itAssetsFilePath = session.getAttribute("autismFilePath").toString();

								File dir = new File(itAssetsFilePath);
								if (!dir.exists()) {
									dir.mkdirs();
								}
								fname = dir.getAbsolutePath() + File.separator
										+ timestamp.currentDateWithTimeStampString() + "_" + userid + "_ITASSETSDOC.PDF";
								File serverFile = new File(fname);
								BufferedOutputStream stream = new BufferedOutputStream(
										new FileOutputStream(serverFile));
								stream.write(bytes);
								stream.close();
								T_asset.setU_file(fname);
							} else {
								model.put("msg", "Only *.pdf file extension allowed");
								return new ModelAndView(rediurl);
							}
						} catch (Exception e) {
						}
					}
					if (!br_certificate.isEmpty()) {
						try {
							byte[] bytes = br_certificate.getBytes();
							CheckFileFormatValidation fileValidation = new CheckFileFormatValidation();
							if (fileValidation.check_PDF_File(bytes)) {
								String itAssetsFilePath = session.getAttribute("autismFilePath").toString();
								File dir = new File(itAssetsFilePath);
								if (!dir.exists()) {
									dir.mkdirs();
								}
								fname = dir.getAbsolutePath() + File.separator
										+ timestamp.currentDateWithTimeStampString() + "_" + userid + "_ITASSETSDOC.PDF";
								File serverFile = new File(fname);
								BufferedOutputStream stream = new BufferedOutputStream(
										new FileOutputStream(serverFile));
								stream.write(bytes);
								stream.close();
								T_asset.setBr_certificate(fname);
							} else {
								model.put("msg", "Only *.pdf file extension allowed");
								return new ModelAndView(rediurl);
							}
						} catch (Exception e) {
						}
					}
					// String model_no=request.getParameter("model_no"+i);
					String machine_no = request.getParameter("machine_no" + i);
					String is_networked = request.getParameter("is_networked" + i);
					String ip_address = request.getParameter("ip_address" + i);
					String gem_no = request.getParameter("gem_no" + i);

					T_asset.setAssets_type(peripheral.getAssets_type());
					// T_asset.setModel_no(model_no);
					T_asset.setMachine_no(machine_no);
					T_asset.setType_of_hw(peripheral.getType_of_hw());
					T_asset.setYear_of_proc(peripheral.getYear_of_proc());
					T_asset.setYear_of_manufacturing(peripheral.getYear_of_manufacturing());
					T_asset.setIp_address(ip_address);
					T_asset.setType(peripheral.getType());
					T_asset.setRemarks(peripheral.getRemarks().toUpperCase());
					T_asset.setMonochrome_color(peripheral.getMonochrome_color());
					T_asset.setIs_networked(is_networked);
					T_asset.setPrint(peripheral.getPrint());
					T_asset.setScan(peripheral.getScan());
					T_asset.setPhotography(peripheral.getPhotography());
					T_asset.setColour(peripheral.getColour());
					T_asset.setCapacity(peripheral.getCapacity());

					//					if ("monitor".equals(monitor_v)) {
					//					T_asset.setDisplay_type(resolution);
					//					T_asset.setDisplay_size(v_display_size);
					//					T_asset.setDisplay_connector(v_display_connector);
					//					}else if("visulizer".equals(visulizer_v)) {
					//
					//						T_asset.setV_display_size(v_display_size);
					//						T_asset.setV_display_connector(v_display_connector);
					//						T_asset.setResolution(resolution);
					//
					//					}



					//					if ("monitor".equals(monitor_v)) {
					T_asset.setDisplay_type(resolution);
					T_asset.setDisplay_size(v_display_size);
					T_asset.setDisplay_connector(v_display_connector);
					T_asset.setV_display_size(v_display_size);
					T_asset.setV_display_connector(v_display_connector);
					T_asset.setResolution(resolution);


					T_asset.setNo_of_ports(peripheral.getNo_of_ports());
					T_asset.setUps_capacity(peripheral.getUps_capacity());
					T_asset.setPaper_size(peripheral.getPaper_size());




					T_asset.setWarranty(warrenty_dt);
					T_asset.setWarrantycheck(peripheral.getWarrantycheck());
					T_asset.setProc_date(proc_dt);
					T_asset.setB_head(peripheral.getB_head());
					// T_asset.setB_code(peripheral.getB_code());
					T_asset.setB_cost(peripheral.getB_cost());
					T_asset.setS_state(peripheral.getS_state());
					T_asset.setModel_name(peripheral.getModel_name());
					T_asset.setMake_name(peripheral.getMake_name());
					T_asset.setUnserviceable_state(peripheral.getUnserviceable_state());
					T_asset.setConnectivity_type(peripheral.getConnectivity_type());
					T_asset.setHw_interface(request.getParameter("hd_hw_interface"));
					T_asset.setEthernet_port(peripheral.getEthernet_port());
					T_asset.setManagement_layer(peripheral.getManagement_layer());
					T_asset.setNetwork_features(request.getParameter("hd_network_features"));

					T_asset.setUnsv_from_dt(unsv_from_dt1);
					T_asset.setSection(section);
					T_asset.setGem_no(gem_no);
					T_asset.setCrv_no(crv_no);
					T_asset.setUsername(username2);
					T_asset.setMaintainagency(maintainAgency);

					T_asset.setApproveois(peripheral.getApproveois());
					T_asset.setSupplier_name(supplierId);

					TB_MASTER_MAKE make_mstr = new TB_MASTER_MAKE();
					TB_MASTER_MODEL model_mstr = new TB_MASTER_MODEL();
					if (!brand_other.equals("")) {

						List<String> brand_other1 = it_comm.getPeriBrandOtherList(request);
						if (brand_other1.size() > 0) {
							model.put("msg", "This Make Name Already Exists.");
							return new ModelAndView(rediurl);
						}

						//							T_asset.setBrand_other(brand_other.toUpperCase());
						make_mstr.setMake_name(brand_other.toUpperCase());
						make_mstr.setStatus(0);
						make_mstr.setAssets_name(Integer.parseInt(assets_type));
						make_mstr.setCategory(2);
						make_mstr.setCreated_by(username);
						make_mstr.setCreated_dt(date);
						sessionHQL.save(make_mstr);
						String make_id = String.valueOf(make_mstr.getId());
						T_asset.setBrand_other(make_id);
					}

					if (!model_other.equals("")) {

						int mak_id = make_mstr.getId();

						List<String> model_other1 = it_comm.getPeriModelOtherList(request);

						if (model_other1.size() > 0) {
							model.put("msg", "This Model Name Already Exists.");
							return new ModelAndView(rediurl);
						}

						//							T_asset.setModel_other(model_other.toUpperCase());
						model_mstr.setModel_name(model_other.toUpperCase());
						model_mstr.setAssets_name(Integer.parseInt(assets_type));
						model_mstr.setStatus(0);
						model_mstr.setCategory(2);
						model_mstr.setCreated_by(username);
						model_mstr.setCreated_dt(date);
						model_mstr.setMake_name(mak_id);
						sessionHQL.save(model_mstr);
						String model_id = String.valueOf(model_mstr.getId());
						T_asset.setModel_other(model_id);
					}

					TB_MSTR_TYPE_OF_HW_M peri_hw_mstr = new TB_MSTR_TYPE_OF_HW_M();
					if (!peri_hw_other.equals("")) {

						List<String> peri_hw_other1 = it_comm.getTypeOfPeripheralOtherList(request);

						if (peri_hw_other1.size() > 0) {
							model.put("msg", "This Type Of Peripheral HW Already Exists.");
							return new ModelAndView(rediurl);
						}

						//							T_asset.setPeri_hw_other(peri_hw_other.toUpperCase());;
						peri_hw_mstr.setPeripheral_type(Integer.parseInt(assets_type));
						peri_hw_mstr.setType_of_hw(peri_hw_other.toUpperCase());
						peri_hw_mstr.setStatus(0);
						sessionHQL.save(peri_hw_mstr);
						String peri_hw_id = String.valueOf(peri_hw_mstr.getId());
						T_asset.setPeri_hw_other(peri_hw_id);

					}

					//						int peri_hw_id=peri_hw_mstr.getId();

					TB_MSTR_TYPE_M model_type_mstr = new TB_MSTR_TYPE_M();
					if (!model_type_other.equals("")) {

						List<String> model_type_other1 = it_comm.getTypeOfModelOtherList(request);

						if (model_type_other1.size() > 0) {
							model.put("msg", "This Model Type Already Exists.");
							return new ModelAndView(rediurl);
						}

						//							T_asset.setModel_type_other(model_type_other.toUpperCase());
						model_type_mstr.setPeripheral_type(Integer.parseInt(assets_type));
						model_type_mstr.setType(model_type_other.toUpperCase());
						model_type_mstr.setType_of_hw(Integer.parseInt(type_of_hw));
						model_type_mstr.setStatus(0);
						sessionHQL.save(model_type_mstr);
						String model_type_id = String.valueOf(model_type_mstr.getId());
						T_asset.setModel_type_other(model_type_id);
					}

					TB_MSTR_CONNECTIVITY connect_type_mstr = new TB_MSTR_CONNECTIVITY();

					if (!connectivity_other.equals("")) {

						//							List<String> connectivity_other1=it_comm.getTypeConnectivityOtherList(request);

						//							if(connectivity_other1.size()>0)
						//							{
						//								model.put("msg", "This Connectivity Type Already Exists.");
						//								return new ModelAndView(rediurl);
						//							}

						//							T_asset.setConnectivity_other(connectivity_other.toUpperCase());
						connect_type_mstr.setConnectivity_type(connectivity_other.toUpperCase());
						connect_type_mstr.setStatus(0);
						sessionHQL.save(connect_type_mstr);
						String connectivity_type_id = String.valueOf(connect_type_mstr.getId());
						T_asset.setConnectivity_other(connectivity_type_id);
					}

					T_asset.setCreated_by(username);
					T_asset.setCreated_date(date);
					//						T_asset.setSus_no(roleSusNo);

					TB_PERIPHERAL_CHILD obj = new TB_PERIPHERAL_CHILD();
					obj.setWarranty(warrenty_dt);
					obj.setService_state(Integer.parseInt(s_state));

					if (s_state.equals("2") || s_state == "2") {

						obj.setUnservice_state(Integer.parseInt(unserviceable_state));
						obj.setUnsv_from_dt(unsv_from_dt1);

					}

					obj.setCreated_by(username);
					obj.setCreated_dt(date);

					T_asset.setStatus(0);
					obj.setStatus(0);
					int c = (Integer) sessionHQL.save(T_asset);
					obj.setP_id(c);

					sessionHQL.save(obj);

					sessionHQL.flush();
					sessionHQL.clear();

				}
				model.put("msg", "Data Saved Successfully.");

			} else {

				// String model_no=request.getParameter("model_no1");
				String machine_no = request.getParameter("machine_no1");
				String is_networked = request.getParameter("is_networked1");
				String ip_address = request.getParameter("ip_address1");
				String gem_no = request.getParameter("gem_no1");

				String fname1 = "";
				//					if(!u_file_hid.equals(""))
				//					{
				//					if (!u_file.isEmpty()) {
				//						try {
				//							byte[] bytes = u_file.getBytes();
				//							String mnhFilePath = session.getAttribute("itAssetsFilePath").toString();
				//							File dir = new File(mnhFilePath);
				//							if (!dir.exists())
				//								dir.mkdirs();
				//							String filename = u_file.getOriginalFilename();
				//							int i = filename.lastIndexOf('.');
				//							if (i >= 0) {
				//								extension = filename.substring(i + 1);
				//							}
				//							fname1 = dir.getAbsolutePath() + File.separator +timestamp.currentDateWithTimeStampString()+"_"+sus_no1+"_IT_Doc."+extension;
				//							File serverFile = new File(fname1);
				//							BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				//							stream.write(bytes);
				//							stream.close();
				////							T_asset.setU_file(fname1);
				//						} catch (Exception e) {
				//						}
				//					}
				//
				//					}

				String hql = "update  It_Asset_Peripherals set modified_by=:modified_by,status=:status,modified_date=:modified_date,assets_type=:assets_type,\r\n"
						+ "type_of_hw=:type_of_hw,year_of_proc=:year_of_proc,year_of_manufacturing=:year_of_manufacturing,make_name=:make_name,\r\n"
						+ "model_name=:model_name,machine_no=:machine_number,proc_cost=:proc_cost,remarks=:remarks,type=:type,\r\n"
						+ "ups_capacity=:ups_capacity,monochrome_color=:monochrome_color,is_networked=:is_networked,\r\n"
						+ "ip_address=:ip_address,capacity=:capacity,print=:print,scan=:scan,photography=:photography,colour=:colour, \r\n"
						+ "resolution=:resolution,no_of_ports=:no_of_ports,paper_size=:paper_size,display_size=:display_size,display_type=:display_type, \r\n"
						+ "display_connector=:display_connector,warranty=:warranty,warrantycheck=:warrantycheck,s_state=:s_state,unserviceable_state=:unserviceable_state, \r\n"
						+ "b_head=:b_head,b_cost=:b_cost,connectivity_type=:connectivity_type, hw_interface=:hw_interface, \r\n"
						+ " ethernet_port=:ethernet_port, management_layer=:management_layer, network_features=:network_features, v_display_size=:v_display_size, \r\n"
						+ " v_display_connector=:v_display_connector, proc_date=:proc_date,unsv_from_dt=:unsv_from_dt,crv_no=:crv_no ,gem_no=:gem_no,supplier_name=:supplier_name,username=:username,maintainagency=:maintainAgency "
						+ " where id=:id ";

				Query query = sessionHQL.createQuery(hql).setInteger("status", 0).setInteger("id", id)
						.setString("modified_by", username).setTimestamp("modified_date", date)
						.setInteger("assets_type", peripheral.getAssets_type())
						.setInteger("type_of_hw", peripheral.getType_of_hw())
						.setString("year_of_proc", peripheral.getYear_of_proc())
						.setString("year_of_manufacturing", peripheral.getYear_of_manufacturing())
						.setInteger("make_name", peripheral.getMake_name())
						.setInteger("model_name", peripheral.getModel_name()).setString("machine_number", machine_no)
						.setString("proc_cost", peripheral.getProc_cost())
						.setString("remarks", peripheral.getRemarks().toUpperCase())
						.setString("type", peripheral.getType()).setString("ups_capacity", peripheral.getUps_capacity())
						.setString("monochrome_color", peripheral.getMonochrome_color())
						.setString("is_networked", is_networked).setString("ip_address", ip_address)
						.setString("capacity", peripheral.getCapacity()).setString("print", peripheral.getPrint())
						.setString("scan", peripheral.getScan()).setString("photography", peripheral.getPhotography())
						.setString("colour", peripheral.getColour()).setString("resolution", peripheral.getResolution())
						.setString("no_of_ports", peripheral.getNo_of_ports())
						.setString("paper_size", peripheral.getPaper_size())
						.setString("display_size", peripheral.getDisplay_size())
						.setString("display_connector", peripheral.getDisplay_connector())
						.setString("display_type", peripheral.getDisplay_type()).setTimestamp("warranty", warrenty_dt)
						.setString("warrantycheck", peripheral.getWarrantycheck()).setTimestamp("proc_date", proc_dt)
						.setString("s_state", peripheral.getS_state())
						.setInteger("unserviceable_state", peripheral.getUnserviceable_state())
						.setString("b_head", peripheral.getB_head()).setString("b_cost", peripheral.getB_cost())
						.setString("hw_interface", request.getParameter("hd_hw_interface"))
						.setString("connectivity_type", peripheral.getConnectivity_type())
						.setInteger("ethernet_port", peripheral.getEthernet_port())
						.setInteger("management_layer", peripheral.getManagement_layer())
						.setString("network_features", request.getParameter("hd_network_features"))
						.setString("v_display_size", peripheral.getV_display_size())
						.setString("v_display_connector", peripheral.getV_display_connector())
						.setTimestamp("unsv_from_dt", unsv_from_dt1)
						.setString("supplier_name",supplierId)
						.setString("crv_no", crv_no).setString("gem_no", gem_no).setString("username", username)
						.setString("maintainAgency", maintainAgency);
				;
				// **END BISG AHM **//

				String hql1 = "update  TB_PERIPHERAL_CHILD set modified_by=:modified_by,status=:status,modified_date=:modified_date,service_state=:service_state,"
						+ "unservice_state=:unservice_state,unsv_from_dt=:unsv_from_dt,warrantycheck=:warrantycheck,warranty=:warranty where p_id=:p_id\r\n";

				Query query1 = sessionHQL.createQuery(hql1).setInteger("status", 0).setInteger("p_id", id)
						.setString("modified_by", username).setTimestamp("modified_date", date)
						.setInteger("service_state", Integer.parseInt(s_state))
						.setInteger("unservice_state", Integer.parseInt(unserviceable_state))
						.setTimestamp("unsv_from_dt", unsv_from_dt1).setTimestamp("warranty", warrenty_dt)
						.setString("warrantycheck", peripheral.getWarrantycheck());

				String msg = query.executeUpdate() > 0 ? "update" : "0";

				if (!make_mstr_id.equals(""))

				{
					String hql2 = "update  TB_MASTER_MAKE set modified_by=:modified_by,status=:status,modified_dt=:modified_dt,make_name=:make_name where id=:make_id";

					Query query2 = sessionHQL.createQuery(hql2).setInteger("status", 0)
							.setInteger("make_id", Integer.parseInt(make_mstr_id)).setString("modified_by", username)
							.setTimestamp("modified_dt", date).setString("make_name", brand_other.toUpperCase());
					msg = query2.executeUpdate() > 0 ? "update" : "0";
				}

				if (!model_mstr_id.equals(""))

				{
					String hql3 = "update  TB_MASTER_MODEL set modifed_by=:modified_by,status=:status,modified_dt=:modified_dt,model_name=:model_name where id=:model_id";

					Query query3 = sessionHQL.createQuery(hql3).setInteger("status", 0)
							.setInteger("model_id", Integer.parseInt(model_mstr_id)).setString("modified_by", username)
							.setTimestamp("modified_dt", date).setString("model_name", model_other.toUpperCase());
					msg = query3.executeUpdate() > 0 ? "update" : "0";
				}

				if (!type_hw_id.equals(""))

				{
					String hql4 = "update  TB_MSTR_TYPE_OF_HW_M set modified_by=:modified_by,status=:status,modified_date=:modified_dt,type_of_hw=:type_of_hw where id=:type_hw_id";

					Query query4 = sessionHQL.createQuery(hql4).setInteger("status", 0)
							.setInteger("type_hw_id", Integer.parseInt(type_hw_id)).setString("modified_by", username)
							.setTimestamp("modified_dt", date).setString("type_of_hw", peri_hw_other.toUpperCase());
					msg = query4.executeUpdate() > 0 ? "update" : "0";
				}

				if (!pro_type_id.equals(""))

				{
					String hql5 = "update  TB_MSTR_TYPE_M set modified_by=:modified_by,status=:status,modified_date=:modified_dt,type=:type where id=:pro_typ_id";

					Query query5 = sessionHQL.createQuery(hql5).setInteger("status", 0)
							.setInteger("pro_typ_id", Integer.parseInt(pro_type_id)).setString("modified_by", username)
							.setTimestamp("modified_dt", date).setString("type", model_type_other.toUpperCase());
					msg = query5.executeUpdate() > 0 ? "update" : "0";
				}

				if (!pro_connect_id.equals(""))

				{
					String hql6 = "update  TB_MSTR_CONNECTIVITY set modifed_by=:modified_by,status=:status,modified_dt=:modified_dt,connectivity_type=:connectivity_type where id=:pro_connect_id";

					Query query6 = sessionHQL.createQuery(hql6).setInteger("status", 0)
							.setInteger("pro_connect_id", Integer.parseInt(pro_connect_id))
							.setString("modified_by", username).setTimestamp("modified_dt", date)
							.setString("connectivity_type", connectivity_other.toUpperCase());
					msg = query6.executeUpdate() > 0 ? "update" : "0";
				}

				msg = query1.executeUpdate() > 0 ? "update" : "0";
				if (msg.equals("update")) {
					model.put("msg", "Data Updated Successfully.");
					return new ModelAndView("redirect:" + screenurl);
				}
			}
			// **END BISG AHM **//
			tx.commit();
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
		return new ModelAndView(rediurl);
	}

	@RequestMapping(value = "/admin/Edit_Peripherals", method = RequestMethod.POST)
	public ModelAndView Edit_Peripherals(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "updateid", required = false) int id,
			@RequestParam(value = "screenurl5", required = false) String screenurl) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_PeripheralUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		Mmap.put("roleSusNo", roleSusNo);

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		It_Asset_Peripherals assetupd = sessionHQL.get(It_Asset_Peripherals.class, id);
		if (sessionHQL.get(TB_MASTER_MAKE.class, assetupd.getMake_name()).getMake_name()
				.equals("OTHERS")) {
			TB_MASTER_MAKE make_mstr1 = sessionHQL.get(TB_MASTER_MAKE.class,
					Integer.parseInt(assetupd.getBrand_other()));
			Mmap.put("make_mstr_other", make_mstr1.getMake_name());
			Mmap.put("make_mstr_id", make_mstr1.getId());
		}

		if (sessionHQL.get(TB_MASTER_MODEL.class, assetupd.getModel_name()).getModel_name()
				.equals("OTHERS")) {
			TB_MASTER_MODEL model_mstr1 = sessionHQL.get(TB_MASTER_MODEL.class,
					Integer.parseInt(assetupd.getModel_other()));
			Mmap.put("model_mstr_other", model_mstr1.getModel_name());
			Mmap.put("model_mstr_id", model_mstr1.getId());
		}

		if (sessionHQL.get(TB_MSTR_TYPE_OF_HW_M.class, assetupd.getType_of_hw())
				.getType_of_hw().equals("OTHERS")) {
			TB_MSTR_TYPE_OF_HW_M type_hw_mstr1 = sessionHQL.get(TB_MSTR_TYPE_OF_HW_M.class,
					Integer.parseInt((assetupd.getPeri_hw_other())));
			Mmap.put("type_hw_mstr", type_hw_mstr1.getType_of_hw());
			Mmap.put("type_hw_id", type_hw_mstr1.getId());
		}
		/// BISAG V1 220822///

		if (assetupd.getType() != null && (sessionHQL.get(TB_MSTR_TYPE_M.class,
				Integer.parseInt(assetupd.getType()))) != null) {
			if (sessionHQL.get(TB_MSTR_TYPE_M.class, Integer.parseInt(assetupd.getType())).getType()
					.equals("OTHERS")) {
				TB_MSTR_TYPE_M type_mstr1 = sessionHQL.get(TB_MSTR_TYPE_M.class,
						Integer.parseInt(assetupd.getModel_type_other()));
				Mmap.put("type_mstr", type_mstr1.getType());
				Mmap.put("pro_type_id", type_mstr1.getId());
			}
		}
		if (!assetupd.getConnectivity_type().equals("0")
				&& sessionHQL.get(TB_MSTR_CONNECTIVITY.class,
						Integer.parseInt(assetupd.getConnectivity_type())).getConnectivity_type().equals("OTHERS")) {
			TB_MSTR_CONNECTIVITY connect_mstr1 = sessionHQL.get(TB_MSTR_CONNECTIVITY.class,
					Integer.parseInt(assetupd.getConnectivity_other()));
			Mmap.put("connect_mstr", connect_mstr1.getConnectivity_type());
			Mmap.put("pro_connect_id", connect_mstr1.getId());
		}

		Mmap.put("getPeripheral", it_comm.getPeripheral());
		Mmap.put("getMakeName", it_comm.getMakeName());
		Mmap.put("getModelNameList", it_comm.getModelNameList());
		Mmap.put("YearOfProc", it_comm.getYearOfProc());
		Mmap.put("YearOfManufacturing", it_comm.getYearOfManufacturing());
		Mmap.put("UpsCapacity", it_comm.getUpsCapacity());
		Mmap.put("UNServiceableList", it_comm.UNServiceableList());
		Mmap.put("getDisplay_ConnectorList", it_comm.getDisplay_ConnectorList());
		Mmap.put("getConnectivity_TypeList", it_comm.getConnectivity_TypeList());
		Mmap.put("getServiceable_StateList", it_comm.getServiceable_StateList());
		Mmap.put("getPaper_SizeList", it_comm.getPaper_SizeList());
		Mmap.put("getBudgetHeadList", it_comm.getBudgetHeadList());
		Mmap.put("hw_interfaceList", it_comm.hw_interfaceList());
		Mmap.put("getEthernet_portList", it_comm.getEthernet_portList());
		Mmap.put("getManagement_layerList", it_comm.getManagement_layerList());
		Mmap.put("getNetwork_featuresList", it_comm.getNetwork_featuresList());
		Mmap.put("getSectionNameList", rolecontroller.getSectionNameList());
		Mmap.put("getMaintainceAgencyList", it_comm.getMaintainceAgencyList());
		Mmap.put("screen_url", screenurl);
		return new ModelAndView("PeripheralsTiles", "PeripheralCmd", assetupd);
	}

	@RequestMapping(value = "/admin/Search_PeripheralUrl", method = RequestMethod.GET)
	public ModelAndView Search_PeripheralUrl(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_PeripheralUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		Mmap.put("roleSusNo", roleSusNo);
		Mmap.put("msg", msg);
		Mmap.put("status1", "0");
		Mmap.put("YearOfManufacturing", it_comm.getYearOfManufacturing());
		Mmap.put("getPeripheral", it_comm.getPeripheral());
		Mmap.put("getServiceable_StateList", it_comm.getServiceable_StateList());
		Mmap.put("getSectionNameList", rolecontroller.getSectionNameList());
		return new ModelAndView("SearchPeripheralTiles");
	}

	@RequestMapping(value = "/getFilteredPeripheral1", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getFilteredPeripheral1(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String status, String assets_type, String year_of_manufacturing,
			String machine_make, String machine_no, String from_date, String to_date, String s_state, String section,
			HttpSession sessionUserId) throws SQLException {

		return cd.Search_Peripheral(startPage, pageLength, Search, orderColunm, orderType, status, assets_type,
				year_of_manufacturing, machine_make, machine_no, from_date, to_date, s_state, section, sessionUserId);
	}

	@RequestMapping(value = "/getTotalPeripheralCount1", method = RequestMethod.POST)
	public @ResponseBody long getTotalPeripheralCount1(String Search, String orderColunm, String orderType,
			String status, String assets_type, String year_of_manufacturing, String machine_make, String machine_no,
			String from_date, String to_date, String s_state, String section, HttpSession sessionUserId)
					throws SQLException {

		return cd.getPeripheralCountList(Search, orderColunm, orderType, status, assets_type, year_of_manufacturing,
				machine_make, machine_no, from_date, to_date, s_state, section, sessionUserId);
	}

	@RequestMapping(value = "/admin/Search_PeripheralUrl_1", method = RequestMethod.POST)
	public ModelAndView Search_PeripheralUrl_1(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "machine_make1", required = false) String machine_make,
			@RequestParam(value = "machine_no1", required = false) String machine_no,
			//			@RequestParam(value = "model_no1", required = false) String model_no,
			@RequestParam(value = "year_of_manufacturing1", required = false) String year_of_manufacturing,

			@RequestParam(value = "section1", required = false) String section,
			@RequestParam(value = "from_date1", required = false) String from_date,
			@RequestParam(value = "to_date1", required = false) String to_date,
			@RequestParam(value = "assets_type1", required = false) String assets_type,
			@RequestParam(value = "status1", required = false) String status,
			@RequestParam(value = "s_state1", required = false) String s_state) {
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		Mmap.put("roleSusNo", roleSusNo);

		Mmap.put("getTargetUnitNameList", allt.getTargetUnitNameList(roleSusNo, session));

		Mmap.put("section1", section);
		Mmap.put("machine_make1", machine_make);
		Mmap.put("machine_no1", machine_no);
		// Mmap.put("model_no1", model_no);
		Mmap.put("from_date1", from_date);
		Mmap.put("to_date1", to_date);
		Mmap.put("assets_type1", assets_type);
		Mmap.put("status1", status);
		Mmap.put("s_state1", s_state);
		Mmap.put("year_of_manufacturing1", year_of_manufacturing);
		Mmap.put("YearOfManufacturing", it_comm.getYearOfManufacturing());
		Mmap.put("getPeripheral", it_comm.getPeripheral());
		Mmap.put("getServiceable_StateList", it_comm.getServiceable_StateList());
		Mmap.put("getSectionNameList", rolecontroller.getSectionNameList());

		return new ModelAndView("SearchPeripheralTiles");
	}

	@RequestMapping(value = "/Approve_PeripheralAssetsData", method = RequestMethod.POST)
	public @ResponseBody List<String> Approve_PeripheralAssetsData(String a, String status, HttpSession session) {
		String sus_no = session.getAttribute("roleSusNo").toString();
		String username = session.getAttribute("username").toString();
		List<String> list2 = new ArrayList<String>();
		list2.add(cd.approve_AssetsData(a, sus_no, status, username));
		return list2;
	}

	@RequestMapping(value = "/Delete_Peripherals", method = RequestMethod.POST)
	public ModelAndView Delete_Peripherals(@ModelAttribute("id1") String id, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session1,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "screenurl6", required = false) String screenurl) {
		String roleid = session1.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_PeripheralUrl", roleid);
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
			hqlUpdate = "delete from It_Asset_Peripherals where id=:id";
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
		return new ModelAndView("redirect:" + screenurl);
	}

	@RequestMapping(value = "/PeripheralsArchive", method = RequestMethod.POST)
	public ModelAndView PeripheralsArchive(@ModelAttribute("arid2") String id, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session1,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "screenurl8", required = false) String screenurl) {
		String roleid = session1.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_PeripheralUrl", roleid);
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
			hqlUpdate = "update from It_Asset_Peripherals set status=:status where id=:id";
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
		return new ModelAndView("redirect:" + screenurl);
	}

	@RequestMapping(value = "/admin/View_Peripherals", method = RequestMethod.POST)
	public ModelAndView View_Peripherals(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "viewid", required = false) int id,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "screenurl7", required = false) String screenurl) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_PeripheralUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		It_Asset_Peripherals peripheralupd = sessionHQL.get(It_Asset_Peripherals.class, id);

		if (sessionHQL.get(TB_MASTER_MAKE.class, peripheralupd.getMake_name()).getMake_name()
				.equals("OTHERS")) {
			TB_MASTER_MAKE make_mstr1 = sessionHQL.get(TB_MASTER_MAKE.class,
					Integer.parseInt(peripheralupd.getBrand_other()));
			Mmap.put("make_mstr_other", make_mstr1.getMake_name());
			Mmap.put("make_mstr_id", make_mstr1.getId());
		}

		if (sessionHQL.get(TB_MASTER_MODEL.class, peripheralupd.getModel_name()).getModel_name()
				.equals("OTHERS")) {
			TB_MASTER_MODEL model_mstr1 = sessionHQL.get(TB_MASTER_MODEL.class,
					Integer.parseInt(peripheralupd.getModel_other()));
			Mmap.put("model_mstr_other", model_mstr1.getModel_name());
			Mmap.put("model_mstr_id", model_mstr1.getId());
		}

		if (sessionHQL.get(TB_MSTR_TYPE_OF_HW_M.class, peripheralupd.getType_of_hw())
				.getType_of_hw().equals("OTHERS")) {
			TB_MSTR_TYPE_OF_HW_M type_hw_mstr1 = sessionHQL.get(TB_MSTR_TYPE_OF_HW_M.class,
					Integer.parseInt((peripheralupd.getPeri_hw_other())));
			Mmap.put("type_hw_mstr", type_hw_mstr1.getType_of_hw());
			Mmap.put("type_hw_id", type_hw_mstr1.getId());
		}

		if ((peripheralupd.getType() != null) && !peripheralupd.getType().equals("0")
				&& !peripheralupd.getType().equals("")) {

			if (sessionHQL.get(TB_MSTR_TYPE_M.class, Integer.parseInt(peripheralupd.getType()))
					.getType().equals("OTHERS")) {
				TB_MSTR_TYPE_M type_mstr1 = sessionHQL.get(TB_MSTR_TYPE_M.class,
						Integer.parseInt(peripheralupd.getModel_type_other()));
				Mmap.put("type_mstr", type_mstr1.getType());
				Mmap.put("pro_type_id", type_mstr1.getId());
			}
		}

		//
		//		 if(!peripheralupd.getConnectivity_type().equals("0") && ((TB_MSTR_CONNECTIVITY)sessionHQL.get(TB_MSTR_CONNECTIVITY.class,Integer.parseInt(peripheralupd.getConnectivity_type()))).getConnectivity_type().equals("OTHERS")) {
		//			 TB_MSTR_CONNECTIVITY connect_mstr1=(TB_MSTR_CONNECTIVITY)sessionHQL.get(TB_MSTR_CONNECTIVITY.class,Integer.parseInt(peripheralupd.getConnectivity_other()));
		//				Mmap.put("connect_mstr", connect_mstr1.getConnectivity_type());
		//				Mmap.put("pro_connect_id", connect_mstr1.getId());
		//				 }

		Mmap.put("getPeripheral", it_comm.getPeripheral());
		Mmap.put("hardwareListDDL", it_comm.gethardwareListDDL());
		Mmap.put("Type1", it_comm.getType1());
		Mmap.put("YearOfProc", it_comm.getYearOfProc());
		Mmap.put("YearOfManufacturing", it_comm.getYearOfManufacturing());
		Mmap.put("UpsCapacity", it_comm.getUpsCapacity());
		Mmap.put("UNServiceableList", it_comm.UNServiceableList());
		Mmap.put("getDisplay_ConnectorList", it_comm.getDisplay_ConnectorList());
		Mmap.put("getConnectivity_TypeList", it_comm.getConnectivity_TypeList());
		Mmap.put("getServiceable_StateList", it_comm.getServiceable_StateList());
		Mmap.put("getPaper_SizeList", it_comm.getPaper_SizeList());
		Mmap.put("getBudgetHeadList", it_comm.getBudgetHeadList());
		Mmap.put("getMakeName", it_comm.getMakeName());
		Mmap.put("getModelNameList", it_comm.getModelNameList());
		Mmap.put("getBudgetCodeList", it_comm.getBudgetCodeList());
		Mmap.put("ServiceableList", it_comm.ServiceableList());
		Mmap.put("getSectionNameList", rolecontroller.getSectionNameList());
		Mmap.put("getMaintainceAgencyList", it_comm.getMaintainceAgencyList());
		Mmap.put("screenurl", screenurl);

		int at = peripheralupd.getAssets_type();
		if (at == 16) {
			ArrayList<ArrayList<String>> ntwrk_ftr_data = cd.getcomaseptext_network_feature(id);
			Mmap.put("ntwrk_ftr_data", ntwrk_ftr_data);
		}
		if (at == 14) {
			ArrayList<ArrayList<String>> hw_int_data = cd.getcomaseptext_hw_interface(id);
			Mmap.put("hw_int_data", hw_int_data);
		}

		return new ModelAndView("ViewPeripheralsTiles", "ViewPeripheralCmd", peripheralupd);
	}

	@RequestMapping(value = "/admin/Search_App_PeripheralUrl", method = RequestMethod.GET)
	public ModelAndView Search_App_PeripheralUrl(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) throws SQLException {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_App_PeripheralUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		Mmap.put("msg", msg);
		Mmap.put("YearOfManufacturing", it_comm.getYearOfManufacturing());
		Mmap.put("getPeripheral", it_comm.getPeripheral());
		Mmap.put("getSectionNameList", rolecontroller.getSectionNameList());
		return new ModelAndView("AppSearchPeripheralTiles");
	}

	@RequestMapping(value = "/getFilteredPeripheral", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getFilteredPeripheral(int startPage, int pageLength, String Search,
			String orderColunm, String orderType, String status, String assets_type, String year_of_manufacturing,
			String machine_make, String machine_no, String section, String from_date, String to_date,
			HttpSession sessionUserId) throws SQLException {

		return cd.getAppPeripheralList(startPage, pageLength, Search, orderColunm, orderType, assets_type,
				year_of_manufacturing, machine_make, machine_no, section, from_date, to_date, status, sessionUserId);
	}

	@RequestMapping(value = "/getTotalPeripheralCount", method = RequestMethod.POST)
	public @ResponseBody long getTotalPeripheralCount(String Search, String orderColunm, String orderType,
			String status, String assets_type, String year_of_manufacturing, String machine_make, String machine_no,
			String section, String from_date, String to_date, HttpSession sessionUserId) throws SQLException {

		return cd.getAppPeripheralCountList(Search, orderColunm, orderType, status, assets_type, year_of_manufacturing,
				machine_make, machine_no, section, from_date, to_date, sessionUserId);
	}

	@RequestMapping(value = "/admin/App_Search_PeripheralUrl", method = RequestMethod.POST)
	public ModelAndView App_Search_PeripheralUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "machine_make1", required = false) String machine_make,
			@RequestParam(value = "machine_no1", required = false) String machine_no,
			// @RequestParam(value = "model_no1", required = false) String model_no,
			@RequestParam(value = "year_of_manufacturing1", required = false) String year_of_manufacturing,

			@RequestParam(value = "section1", required = false) String section,
			@RequestParam(value = "from_date1", required = false) String from_date,
			@RequestParam(value = "to_date1", required = false) String to_date,
			@RequestParam(value = "assets_type1", required = false) String assets_type, HttpServletRequest request,
			@RequestParam(value = "status1", required = false) String status) throws SQLException {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_App_PeripheralUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		Mmap.put("machine_make1", machine_make);
		Mmap.put("machine_no1", machine_no);
		// Mmap.put("model_no1", model_no);
		Mmap.put("from_date1", from_date);
		Mmap.put("to_date1", to_date);
		Mmap.put("assets_type1", assets_type);
		Mmap.put("section1", section);
		Mmap.put("year_of_manufacturing1", year_of_manufacturing);
		Mmap.put("YearOfManufacturing", it_comm.getYearOfManufacturing());
		Mmap.put("getPeripheral", it_comm.getPeripheral());
		Mmap.put("status1", status);
		Mmap.put("getSectionNameList", rolecontroller.getSectionNameList());
		return new ModelAndView("AppSearchPeripheralTiles");
	}

	// ** AHM BISAG **//

	@RequestMapping(value = "/admin/AppEditPeripheralUrl", method = RequestMethod.POST)
	public ModelAndView AppEditPeripheralUrl(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "id5", required = false) int id,
			@RequestParam(value = "ch_id1", required = false) String ch_id) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_App_PeripheralUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		It_Asset_Peripherals assetupd = sessionHQL.get(It_Asset_Peripherals.class, id);

		List<Map<String, Object>> list = cd.getAppPeripheralChildList(String.valueOf(id));

		Mmap.put("ch_list_size", list.size());
		Mmap.put("ch_list", list);

		Mmap.put("getPeripheral", it_comm.getPeripheral());
		Mmap.put("getBudgetCodeList", it_comm.getBudgetCodeList());
		Mmap.put("hardwareListDDL", it_comm.gethardwareListDDL());
		Mmap.put("Type1", it_comm.getType1());
		Mmap.put("YearOfProc", it_comm.getYearOfProc());
		Mmap.put("YearOfManufacturing", it_comm.getYearOfManufacturing());
		Mmap.put("UpsCapacity", it_comm.getUpsCapacity());
		Mmap.put("UNServiceableList", it_comm.UNServiceableList());
		Mmap.put("getConnectivity_TypeList", it_comm.getConnectivity_TypeList());
		Mmap.put("getEthernet_portList", it_comm.getEthernet_portList());
		Mmap.put("getManagement_layerList", it_comm.getManagement_layerList());
		Mmap.put("hw_interfaceList", it_comm.hw_interfaceList());
		Mmap.put("getNetwork_featuresList", it_comm.getNetwork_featuresList());
		Mmap.put("gethwdata", it_comm.gethwdata());
		Mmap.put("app_id", id);
		Mmap.put("ch_id1", ch_id);
		Mmap.put("getPaper_SizeList", it_comm.getPaper_SizeList());
		Mmap.put("getSectionNameList", rolecontroller.getSectionNameList());
		Mmap.put("getMaintainceAgencyList", it_comm.getMaintainceAgencyList());
		return new ModelAndView("AppPeripheralTiles", "AppPeripheralCmd", assetupd);
	}

	// ** END AHM BISAG **//

	@RequestMapping(value = "/AppEditPeripheralAction", method = RequestMethod.POST)
	public ModelAndView AppEditPeripheralAction(@ModelAttribute("AppPeripheralCmd") TB_PERIPHERAL_CHILD obj,
			HttpServletRequest request, ModelMap model, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg) throws ParseException {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_App_PeripheralUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

		// ** AHM BISAG **//

		Date unsv_from_dt1 = null;
		Date unsv_to_dt1 = null;

		// ** END AHM BISAG **//

		Date dob_dt = null;

		String warranty = request.getParameter("warranty_dt");
		String warrantycheck = request.getParameter("warrantycheck");
		if (warranty != null && !warranty.equals("") && !warranty.equals("DD/MM/YYYY")) {
			dob_dt = format.parse(warranty);

		}

		String service_state = request.getParameter("s_state");

		String unserviceable_state = request.getParameter("unserviceable_state");

		// ** AHM BISAG **//

		String unsv_from_dt = request.getParameter("unsv_from_dt1");

		String unsv_to_dt = request.getParameter("unsv_to_dt1");

		String is_networked = request.getParameter("is_networked1");
		String ip_address = request.getParameter("ip_address1");
		String connectivity_type = request.getParameter("connectivity_type1");

		String hw_interface = request.getParameter("hd_hw_interface");
		String network_features = request.getParameter("hd_network_features");
		String ethernet_port = request.getParameter("ethernet_port");

		String management_layer = request.getParameter("management_layer");

		String maintainAgency = request.getParameter("maintainAgency");
		// ** END AHM BISAG **//

		String p_id = request.getParameter("id");

		String c_id = request.getParameter("c_id");

		if (service_state.equals("1") || service_state == "1") {
			unserviceable_state = "0";

			// ** AHM BISAG **//
			unsv_from_dt = null;

			model.put("getServicable", it_comm.getServicableForPeripheral(request, Integer.parseInt(p_id)));
			// ** END AHM BISAG **//
		}

		// ** AHM BISAG **//

		if (service_state.equals("2")) {
			if (unserviceable_state == null || unserviceable_state.equals("") || unserviceable_state.equals("0")) {
				model.put("msg", "Please Select UN-Serviceable State");
				return new ModelAndView("redirect:Search_App_PeripheralUrl");
			}

			if (unsv_from_dt == null || unsv_from_dt.equals("") || unsv_from_dt.equals("0")) {
				model.put("msg", "Please Select UN-Serviceable From date");
				return new ModelAndView("redirect:Search_App_PeripheralUrl");
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

		// ** END AHM BISAG **//

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
				obj.setService_state(sv);
				;
				obj.setUnservice_state(unsv);
				obj.setUnsv_from_dt(unsv_from_dt1);
				obj.setUnsv_to_dt(unsv_to_dt1);
				obj.setIp_address(ip_address);
				obj.setIs_networked(is_networked);
				obj.setConnectivity_type(connectivity_type);
				obj.setEthernet_port(Integer.parseInt(ethernet_port));
				obj.setHw_interface(hw_interface);
				obj.setManagement_layer(Integer.parseInt(management_layer));
				obj.setNetwork_features(network_features);
				obj.setP_id(Integer.parseInt(p_id));
				obj.setMaintainagency(maintainAgency);
				sessionHQL.save(obj);
				sessionHQL.flush();
				sessionHQL.clear();
				model.put("msg", "Data Saved Successfully.");
			}
			if (ID > 0)

			{

				String hql = "update TB_PERIPHERAL_CHILD set warranty=:warranty,warrantycheck=:warrantycheck,service_state=:service_state,unservice_state=:unservice_state,"
						+ "unsv_from_dt=:unsv_from_dt,unsv_to_dt=:unsv_to_dt,ip_address=:ip_address,is_networked=:is_networked,connectivity_type=:connectivity_type,"
						+ "ethernet_port=:ethernet_port,hw_interface=:hw_interface,management_layer=:management_layer,network_features=:network_features,modified_by=:modified_by,modified_date=:modified_date,maintainAgency=:maintainAgency"
						+ " where id=:id";

				Query query = sessionHQL.createQuery(hql).setTimestamp("warranty", new Date())
						.setString("warrantycheck", warrantycheck).setInteger("service_state", sv)
						.setInteger("unservice_state", unsv).setString("ip_address", ip_address)
						.setString("is_networked", is_networked).setString("connectivity_type", connectivity_type)

						.setInteger("ethernet_port", Integer.parseInt(ethernet_port))
						.setString("hw_interface", hw_interface)
						.setInteger("management_layer", Integer.parseInt(management_layer))
						.setString("network_features", network_features)

						.setString("modified_by", username).setTimestamp("unsv_from_dt", unsv_from_dt1)
						.setTimestamp("unsv_to_dt", unsv_to_dt1).setTimestamp("modified_date", new Date())
						.setInteger("id", ID).setString("maintainAgency", maintainAgency);
				msg = query.executeUpdate() > 0 ? "1" : "0";

				if (msg == "1") {
					model.put("msg", "Data Updated Successfully.");
				} else {
					model.put("msg", "Data Not Updated.");
				}

			}

			tx.commit();

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

		return new ModelAndView("redirect:Search_App_PeripheralUrl");
	}

	@RequestMapping(value = "/Approve_Update_Peripheral", method = RequestMethod.POST)
	public @ResponseBody ModelAndView Approve_Update_Peripheral(@ModelAttribute("id3") int id, BindingResult result,
			HttpServletRequest request, HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg,

			Authentication authentication) throws ParseException, SQLException {
		List<String> liststr = new ArrayList<String>();
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		try {

			// ** AHM BISAG ** //

			Query query = sessionHQL.createQuery(" from TB_PERIPHERAL_CHILD where p_id=:p_id and status=0");
			query.setInteger("p_id", id);
			TB_PERIPHERAL_CHILD ac = (TB_PERIPHERAL_CHILD) query.uniqueResult();

			// ** END AHM BISAG ** //

			String username = sessionA.getAttribute("username").toString();

			List<Map<String, Object>> ls = cd.GetdataPeri(id);
			Query q1 = sessionHQL.createQuery(
					"select warranty,unservice_state,service_state from TB_PERIPHERAL_CHILD where p_id=:id and status=0");
			q1.setParameter("id", id);
			@SuppressWarnings("unchecked")
			List<TB_PERIPHERAL_CHILD> list = q1.list();

			// ** AHM BISAG ** //

			// Assets_Child assetupd =(Assets_Child)sessionHQL.get(Assets_Child.class, id);
			String hqlUpdate1 = "update TB_PERIPHERAL_CHILD set status=:status,modified_by=:modified_by,modified_date=:modified_date,unsv_to_dt=:unsv_to_dt  where p_id=:id and status=1";
			int app1 = sessionHQL.createQuery(hqlUpdate1).setInteger("status", 2).setString("modified_by", username)
					.setTimestamp("unsv_to_dt", ac.getUnsv_to_dt()).setInteger("id", id)
					.setDate("modified_date", new Date()).executeUpdate();

			// ** END AHM BISAG ** //

			DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
			if (ls.size() > 0) {
				Date wr = null;
				if (ls.get(0).get("warranty") != null && !ls.get(0).get("warranty").equals("")) {
					wr = format.parse(String.valueOf(ls.get(0).get("warranty")));
				}
				int m_status = 1;
				if (String.valueOf(ls.get(0).get("unservice_state")).equals("1")) {
					m_status = 4;
				}

				String hqlUpdate3 = "update It_Asset_Peripherals set warranty=:warranty,unserviceable_state=:unservice_state,s_state=:service_state,"
						+ "unsv_from_dt=:unsv_from_dt,unsv_to_dt=:unsv_to_dt,is_networked=:is_networked,ip_address=:ip_address,connectivity_type=:connectivity_type,"
						+ "hw_interface=:hw_interface,ethernet_port=:ethernet_port,management_layer=:management_layer,network_features=:network_features,modified_by=:modified_by,modified_date=:modified_date,status=:status  where id=:id ";
				int app2 = sessionHQL.createQuery(hqlUpdate3).setTimestamp("warranty", wr)
						.setInteger("service_state", Integer.parseInt(String.valueOf(ls.get(0).get("service_state"))))
						.setInteger("unservice_state",
								Integer.parseInt(String.valueOf(ls.get(0).get("unservice_state"))))
						.setString("is_networked", String.valueOf(ls.get(0).get("is_networked")))
						.setString("ip_address", String.valueOf(ls.get(0).get("ip_address")))
						.setString("connectivity_type", String.valueOf(ls.get(0).get("connectivity_type")))
						.setString("hw_interface", String.valueOf(ls.get(0).get("hw_interface")))

						.setInteger("ethernet_port", Integer.parseInt(String.valueOf(ls.get(0).get("ethernet_port"))))
						.setInteger("management_layer",
								Integer.parseInt(String.valueOf(ls.get(0).get("management_layer"))))
						.setString("network_features", String.valueOf(ls.get(0).get("network_features")))

						.setString("modified_by", username)
						.setDate("unsv_from_dt", (Date) ls.get(0).get("unsv_from_dt"))
						.setDate("unsv_to_dt", (Date) ls.get(0).get("unsv_to_dt")).setDate("modified_date", new Date())
						.setInteger("status", m_status).setInteger("id", id).executeUpdate();
			}

			// ** AHM BISAG ** //

			Date unsv_to_dt3 = null;
			String unsv_to_dt4 = request.getParameter("");
			if (unsv_to_dt4 != null && !unsv_to_dt4.equals("") && !unsv_to_dt4.equals("DD/MM/YYYY")) {
				unsv_to_dt3 = format.parse(unsv_to_dt4);

			}

			String hqlUpdate = "update TB_PERIPHERAL_CHILD set status=:status,modified_by=:modified_by,modified_date=:modified_date,unsv_to_dt=:unsv_to_dt  where p_id=:id and status =0";
			int app = sessionHQL.createQuery(hqlUpdate).setInteger("status", 1).setString("modified_by", username)
					.setTimestamp("unsv_to_dt", unsv_to_dt3).setDate("modified_date", new Date()).setInteger("id", id)
					.executeUpdate();

			// ** END AHM BISAG ** //
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
		return new ModelAndView("redirect:Search_App_PeripheralUrl");
	}

	@RequestMapping(value = "/Reject_Update_Peripheral", method = RequestMethod.POST)
	public @ResponseBody ModelAndView Reject_Update_Peripheral(@ModelAttribute("id9") int id, BindingResult result,
			HttpServletRequest request, HttpSession sessionA, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_App_PeripheralUrl", roleid);
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

		String hqlUpdate = "update TB_PERIPHERAL_CHILD set status=:status,modified_by=:modified_by,modified_date=:modified_date  where p_id=:id and  status=0";
		int app = sessionHQL.createQuery(hqlUpdate).setInteger("status", 3).setString("modified_by", username)
				.setDate("modified_date", new Date()).setInteger("id", id).executeUpdate();

		if (app > 0) {

			liststr.add("Reject Successfully.");
		} else {
			liststr.add("Reject UNSuccessfully.");
		}

		tx.commit();
		sessionHQL.close();
		return new ModelAndView("redirect:Search_App_PeripheralUrl");
	}

	@SuppressWarnings("null")
	@RequestMapping(value = "/BERFileDownloadDemoPeripheral", method = RequestMethod.GET)
	public void kmlFileDownloadDemo(@ModelAttribute("id") int id, ModelMap model, HttpServletRequest request,
			HttpServletResponse response, HttpSession sessionA, Authentication authentication)
					throws IOException, SQLException {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();

		It_Asset_Peripherals u_file_path = sessionHQL.get(It_Asset_Peripherals.class, id);

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

	// bisag v4 18-11-2022 (delete assets)
	@RequestMapping(value = "/admin/SearchDelete_PeripheralUrl", method = RequestMethod.GET)
	public ModelAndView SearchDelete_PeripheralUrl(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("SearchDelete_PeripheralUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// return new ModelAndView("redirect:bodyParameterNotAllow");
		}

		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		Mmap.put("roleSusNo", roleSusNo);

		Mmap.put("getTargetUnitNameList", allt.getTargetUnitNameList(roleSusNo, session));

		Mmap.put("msg", msg);
		Mmap.put("status1", "0");
		Mmap.put("YearOfManufacturing", it_comm.getYearOfManufacturing());
		Mmap.put("getPeripheral", it_comm.getPeripheral());
		Mmap.put("getServiceable_StateList", it_comm.getServiceable_StateList());
		Mmap.put("getSectionNameList", rolecontroller.getSectionNameList());
		return new ModelAndView("SearchDeletePeripheralTiles");
	}

	@RequestMapping(value = "/getFilteredPeripheral1Delete", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getFilteredPeripheral1Delete(int startPage, int pageLength,
			String Search, String orderColunm, String orderType, String status, String assets_type,
			String year_of_manufacturing, String machine_make, String machine_no, String from_date, String to_date,
			String s_state, String section, HttpSession sessionUserId) throws SQLException {

		return cd.SearchDelete_Peripheral(startPage, pageLength, Search, orderColunm, orderType, status, assets_type,
				year_of_manufacturing, machine_make, machine_no, from_date, to_date, s_state, section, sessionUserId);
	}

	@RequestMapping(value = "/getTotalPeripheralCount1Delete", method = RequestMethod.POST)
	public @ResponseBody long getTotalPeripheralCount1Delete(String Search, String orderColunm, String orderType,
			String status, String assets_type, String year_of_manufacturing, String machine_make, String machine_no,
			String from_date, String to_date, String s_state, String section, HttpSession sessionUserId)
					throws SQLException {

		return cd.getPeripheralCountListDelete(Search, orderColunm, orderType, status, assets_type,
				year_of_manufacturing, machine_make, machine_no, from_date, to_date, s_state, section, sessionUserId);
	}

	@RequestMapping(value = "/admin/SearchDelete_PeripheralUrl_1", method = RequestMethod.POST)
	public ModelAndView SearchDelete_PeripheralUrl_1(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "machine_make1", required = false) String machine_make,
			@RequestParam(value = "machine_no1", required = false) String machine_no,
			// @RequestParam(value = "model_no1", required = false) String model_no,
			@RequestParam(value = "year_of_manufacturing1", required = false) String year_of_manufacturing,
			@RequestParam(value = "section1", required = false) String section,
			@RequestParam(value = "from_date1", required = false) String from_date,
			@RequestParam(value = "to_date1", required = false) String to_date,
			@RequestParam(value = "assets_type1", required = false) String assets_type,
			@RequestParam(value = "status1", required = false) String status,
			@RequestParam(value = "s_state1", required = false) String s_state) {
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		Mmap.put("roleSusNo", roleSusNo);
		Mmap.put("getTargetUnitNameList", allt.getTargetUnitNameList(roleSusNo, session));
		Mmap.put("section1", section);
		Mmap.put("machine_make1", machine_make);
		Mmap.put("machine_no1", machine_no);
		// Mmap.put("model_no1", model_no);
		Mmap.put("from_date1", from_date);
		Mmap.put("to_date1", to_date);
		Mmap.put("assets_type1", assets_type);
		Mmap.put("status1", status);
		Mmap.put("s_state1", s_state);
		Mmap.put("year_of_manufacturing1", year_of_manufacturing);
		Mmap.put("YearOfManufacturing", it_comm.getYearOfManufacturing());
		Mmap.put("getPeripheral", it_comm.getPeripheral());
		Mmap.put("getServiceable_StateList", it_comm.getServiceable_StateList());
		Mmap.put("getSectionNameList", rolecontroller.getSectionNameList());
		return new ModelAndView("SearchDeletePeripheralTiles");
	}

	@RequestMapping(value = "/Delete_PeripheralsAFMS", method = RequestMethod.POST)
	public ModelAndView Delete_PeripheralsAFMS(@ModelAttribute("id1") String a, BindingResult result,
			HttpServletRequest request, ModelMap model, HttpSession session1,
			@RequestParam(value = "msg", required = false) String msg) {
		String roleid = session1.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_PeripheralUrl", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		String[] id_list = a.split(":");
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
				hqlUpdate = "delete from It_Asset_Peripherals where id=:id";
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
		return new ModelAndView("redirect:SearchDelete_PeripheralUrl");
	}

	@RequestMapping(value = "/admin/Excel_Peripheral_Assets_Search", method = RequestMethod.POST)
	public ModelAndView Excel_Peripheral_Assets_Search(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "year_of_manufacturing2", required = false) String year_of_manufacturing,
			//				@RequestParam(value = "model_number2", required = false) String model_number,
			@RequestParam(value = "machine_number2", required = false) String machine_number,
			@RequestParam(value = "assets_type2", required = false) String assets_type,
			@RequestParam(value = "status2", required = false) String status,
			@RequestParam(value = "s_state2", required = false) String s_state,
			@RequestParam(value = "section2", required = false) String section) {

		ArrayList<ArrayList<String>> Excel = cd.Excel_Peripheral_Assets_Search(session, machine_number, assets_type,
				status, s_state, section, year_of_manufacturing);

		String username = session.getAttribute("username").toString();
		Mmap.put("msg", msg);
		String Heading = "SEARCH PERIPHERAL ASSESTS";
		Date date = new Date();
		String foot = " Report Generated On " + new SimpleDateFormat("dd-MM-yyyy").format(date);

		List<String> TH = new ArrayList<String>();
		TH.add("Peripheral Type");
		TH.add("Type of HW");
		TH.add("Year Of Proc");
		TH.add("Year Of Manufacturing");
		TH.add("Machine No");
		// TH.add("Make & Model");
		TH.add("Section");
		TH.add("Created By Username ");
		TH.add("Remarks");
		return new ModelAndView(new ExcelComputingUserListReportView("L", TH, Heading, username), "userList", Excel);

	}
	///

	@RequestMapping(value = "/admin/Excel_Peripheral_Assets_Search_app", method = RequestMethod.POST)
	public ModelAndView Excel_Peripheral_Assets_Search_app(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "year_of_manufacturing2", required = false) String year_of_manufacturing,
			// @RequestParam(value = "model_number2", required = false) String model_number,
			@RequestParam(value = "machine_number2", required = false) String machine_number,
			@RequestParam(value = "assets_type2", required = false) String assets_type,
			@RequestParam(value = "status2", required = false) String status,
			@RequestParam(value = "s_state2", required = false) String s_state,
			@RequestParam(value = "section2", required = false) String section) {

		ArrayList<ArrayList<String>> Excel = cd.Excel_Peripheral_Assets_Search_app(session, machine_number, assets_type,
				status, s_state, section, year_of_manufacturing);

		String username = session.getAttribute("username").toString();
		Mmap.put("msg", msg);
		String Heading = "EDIT PERIPHERAL CENSUS";
		Date date = new Date();
		String foot = " Report Generated On " + new SimpleDateFormat("dd-MM-yyyy").format(date);

		List<String> TH = new ArrayList<String>();
		TH.add("Peripheral Type");
		TH.add("Type of HW");
		TH.add("Year Of Proc");
		TH.add("Year Of Manufacturing");
		TH.add("Proc Cost");
		TH.add("Machine Make");
		TH.add("Machine No");
		// TH.add("Make & Model");
		TH.add("Section");
		TH.add("Created By Username ");
		TH.add("Remarks");
		TH.add("Warranty");
		TH.add("Serviceable State");
		TH.add("UN-Serviceable State");
		return new ModelAndView(new ExcelComputingUserListReportView("L", TH, Heading, username), "userList", Excel);

	}




	//for page

	@RequestMapping(value = "/admin/peripheralassigntouserurl", method = RequestMethod.GET)
	public ModelAndView assigntouserUrl(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = session.getAttribute("roleid").toString();

		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		Mmap.put("getPeripheral", it_comm.getPeripheral());
		Mmap.put("YearOfProc", it_comm.getYearOfProc());
		Mmap.put("YearOfManufacturing", it_comm.getYearOfManufacturing());
		Mmap.put("UpsCapacity", it_comm.getUpsCapacity());
		Mmap.put("UNServiceableList", it_comm.UNServiceableList());
		Mmap.put("getDisplay_ConnectorList", it_comm.getDisplay_ConnectorList());
		Mmap.put("getConnectivity_TypeList", it_comm.getConnectivity_TypeList());
		Mmap.put("getServiceable_StateList", it_comm.getServiceable_StateList());
		Mmap.put("getPaper_SizeList", it_comm.getPaper_SizeList());
		Mmap.put("getBudgetHeadList", it_comm.getBudgetHeadList());
		Mmap.put("hw_interfaceList", it_comm.hw_interfaceList());
		Mmap.put("getEthernet_portList", it_comm.getEthernet_portList());
		Mmap.put("getManagement_layerList", it_comm.getManagement_layerList());
		Mmap.put("getNetwork_featuresList", it_comm.getNetwork_featuresList());
		Mmap.put("getSectionNameList", rolecontroller.getSectionNameList());
		Mmap.put("getMaintainceAgencyList", it_comm.getMaintainceAgencyList());
		Mmap.put("getTypeOfSecuserList", it_comm.getTypeOfSecuserList());
		Mmap.put("msg", msg);

		return new ModelAndView("PeripheralAssignuserTiles","PeripheralMainAssetsassignCmd", new Peripheral_Asset_Assign_To_User());
	}

	// for save


	@RequestMapping(value = "/peripheralassetassignaction", method = RequestMethod.POST)
	public ModelAndView assetassignaction(
			@ModelAttribute("PeripheralMainAssetsassignCmd") Peripheral_Asset_Assign_To_User BAN,
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
			String assets_type = request.getParameter("assets_type");
			String type_of_hw = request.getParameter("type_of_hw");
			String make_name = request.getParameter("make_name");
			String model_name = request.getParameter("model_name");
			String assignuser = request.getParameter("assignuser");
			String assetcountStr = request.getParameter("assetcount");

			int assetcount;
			try {
				assetcount = Integer.parseInt(assetcountStr);
			} catch (NumberFormatException e) {
				Mmap.put("msg", "Invalid asset count. Please enter a valid number.");
				return new ModelAndView("AssignuserTiles", "MainAssetsassignCmd", new Assign_Asset_to_user());
			}
			Integer availableAssetCount = cd.getperipheralassetcount(section, assets_type,type_of_hw, make_name, model_name);
			if (availableAssetCount == null) {
				Mmap.put("msg", "Error retrieving available asset count. Please try again.");
				return new ModelAndView("AssignuserTiles", "MainAssetsassignCmd", new Assign_Asset_to_user());
			}

			int totalAssetsAssignedSoFar = cd.getTotalAssetsAssigned(section, assets_type,type_of_hw, make_name, model_name);
			int remainingAvailableCount = availableAssetCount - totalAssetsAssignedSoFar;

			if (assetcount <= remainingAvailableCount) {
				BAN.setSection(section);
				BAN.setAssets_type(assets_type);
				BAN.setType_of_hw(type_of_hw);
				BAN.setMake_name(make_name);
				BAN.setModel_name(model_name);
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
		return new ModelAndView("PeripheralAssignuserTiles","PeripheralMainAssetsassignCmd", new Peripheral_Asset_Assign_To_User());
	}



	//for search

	@RequestMapping(value = "/admin/search_peripheral_asset_assignurl", method = RequestMethod.GET)
	public ModelAndView search_peripheral_asset_assignurl(ModelMap Mmap, HttpSession sessionUserId, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		String roleid = sessionUserId.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("Search_Computing_AssetsUrl", roleid);
		if (val == false) {
			//			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			// return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
		String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
		Mmap.put("roleSusNo", roleSusNo);

		Mmap.put("getPeripheral", it_comm.getPeripheral());
		Mmap.put("YearOfProc", it_comm.getYearOfProc());
		Mmap.put("YearOfManufacturing", it_comm.getYearOfManufacturing());
		Mmap.put("UpsCapacity", it_comm.getUpsCapacity());
		Mmap.put("UNServiceableList", it_comm.UNServiceableList());
		Mmap.put("getDisplay_ConnectorList", it_comm.getDisplay_ConnectorList());
		Mmap.put("getConnectivity_TypeList", it_comm.getConnectivity_TypeList());
		Mmap.put("getServiceable_StateList", it_comm.getServiceable_StateList());
		Mmap.put("getPaper_SizeList", it_comm.getPaper_SizeList());
		Mmap.put("getBudgetHeadList", it_comm.getBudgetHeadList());
		Mmap.put("hw_interfaceList", it_comm.hw_interfaceList());
		Mmap.put("getEthernet_portList", it_comm.getEthernet_portList());
		Mmap.put("getManagement_layerList", it_comm.getManagement_layerList());
		Mmap.put("getNetwork_featuresList", it_comm.getNetwork_featuresList());
		Mmap.put("getSectionNameList", rolecontroller.getSectionNameList());
		Mmap.put("getMaintainceAgencyList", it_comm.getMaintainceAgencyList());
		Mmap.put("getTypeOfSecuserList", it_comm.getTypeOfSecuserList());
		return new ModelAndView("SearchperipheralAssetsAssignTiles");
	}

	//for data search screen

	@RequestMapping(value = "/getFilteredassetassignperipheral", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> getFilteredassetassignperipheral(int startPage, int pageLength,
			String Search, String orderColunm, String orderType,  String assets_type,
			String section,String status,HttpSession sessionUserId)
					throws SQLException {

		return cd.Search_peripheral_Assets_Assign(startPage, pageLength, Search, orderColunm, orderType,  assets_type,
				section,status, sessionUserId);
	}

	@RequestMapping(value = "/getTotalAssetAssignperipheralCount1", method = RequestMethod.POST)
	public @ResponseBody long getTotalAssetAssignperipheralCount1(String Search, String orderColunm, String orderType,
			String assets_type, String section, String status,HttpSession sessionUserId) throws SQLException {

		return cd.getAppperipheralassetassignCountList1(Search, orderColunm, orderType,  assets_type,
				section, status,sessionUserId);
	}



	@RequestMapping(value = "/Approve_PeripheralAssetsAssignData", method = RequestMethod.POST)
	public @ResponseBody List<String> Approve_PeripheralAssetsAssignData(String a, String status, HttpSession session)
			throws SQLException {
		String sus_no = session.getAttribute("roleSusNo").toString();
		String username = session.getAttribute("username").toString();
		List<String> list2 = new ArrayList<String>();
		// List<Map<String, Object>> ls = cd.approve_Master_Asset(a);

		list2.add(cd.approve_peripheralAssetsAssignData(a,status));
		return list2;
	}

	@RequestMapping(value = "/admin/Excel_Peripheral_Assets_AssignSearch", method = RequestMethod.POST)

	public ModelAndView Excel_Peripheral_Assets_AssignSearch(ModelMap Mmap, HttpSession session,

			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "assets_type2", required = false) String assets_type,
			@RequestParam(value = "status2", required = false) String status,
			@RequestParam(value = "section2", required = false) String section) {
		ArrayList<ArrayList<String>> Excel = cd.excel_Peripheral_Assets_AssignReportDataList(session,assets_type,status,section);



		String username =  session.getAttribute("username").toString();
		Mmap.put("msg", msg);
		String Heading ="SEARCH Peripheral ASSIGN ASSESTS";
		Date date = new Date();

		String foot = " Report Generated On "+ new SimpleDateFormat("dd-MM-yyyy").format(date);

		List<String> TH = new ArrayList<String>();

		TH.add("Section");

		TH.add("Peripheral Typ");

		TH.add("Type of Peripheral HW");

		TH.add("Make Name");

		TH.add("Model Name");

		TH.add("Assign user");

		TH.add("Quantity");

		TH.add("Date & Time");

		return new ModelAndView(new ExcelComputingUserListReportView("L", TH, Heading, username), "userList", Excel);



	}








}