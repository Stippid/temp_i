package com.controller.Approve_Stock_Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.controller.commonController.It_CommonController;
import com.controller.login.RoleController;
import com.controller.tms.AllMethodsControllerTMS;
import com.dao.login.RoleBaseMenuDAO;

import com.models.assets.CONSUMABLES_MAIN;
import com.models.itasset.master.TB_MSTR_TYPE_M;
import com.persistance.util.HibernateUtil;
import org.hibernate.Query;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class Issue_Assest_Controller {

	@Autowired
	It_CommonController it_comm = new It_CommonController();

	RoleController roleController = new RoleController();

	@Autowired
	AllMethodsControllerTMS allt = new AllMethodsControllerTMS();

	@Autowired
	private RoleBaseMenuDAO roledao;

	@RequestMapping(value = "/Issue_Asset_Url", method = RequestMethod.GET)
	public ModelAndView Issue_Asset_Url(ModelMap Mmap, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "msg", required = false) String msg) {

		  Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

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

		Mmap.put("getSectionNameList", roleController.getSectionNameList());
		
		Mmap.put("hardwareListDDL", it_comm.gethardwareListDDL());
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
		
		Mmap.put("getMaintainceAgencyList", it_comm.getMaintainceAgencyList());
		Mmap.put("getModelNameList", it_comm.getModelNameList());
		
		Mmap.put("getConsumables", it_comm.getConsumableList());
		Mmap.put("hardwareListDDL", it_comm.gethardwareListDDL());
		Mmap.put("Type1", it_comm.getType1());
		Mmap.put("YearOfProc", it_comm.getYearOfProc());
		Mmap.put("YearOfManufacturing", it_comm.getYearOfManufacturing());
		Mmap.put("UpsCapacity", it_comm.getUpsCapacity());
		Mmap.put("UNServiceableList", it_comm.UNServiceableList());
		Mmap.put("getDisplay_ConnectorList", it_comm.getDisplay_ConnectorList());
	
		Mmap.put("getServiceable_StateList", it_comm.getServiceable_StateList());
		
		Mmap.put("getBudgetHeadList", it_comm.getBudgetHeadList());
		Mmap.put("getMakeName", it_comm.getMakeName());
		Mmap.put("getModelNameList", it_comm.getModelNameList());
		Mmap.put("getBudgetCodeList", it_comm.getBudgetCodeList());
		Mmap.put("ServiceableList", it_comm.ServiceableList());

		Mmap.put("getMaintainceAgencyList", it_comm.getMaintainceAgencyList());

		Mmap.put("msg", msg);
		return new ModelAndView("Issue_Asset_Tiles", "type_mstrCMD", new TB_MSTR_TYPE_M());
	}

	public String getContact_number(String loginname) {
		String result = "";
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select contact_number from TB_MASTER_SUPPLY_MASTER where supplier =:supplier")
				.setMaxResults(10);
		q.setParameter("supplier", loginname);
		@SuppressWarnings("unchecked")
		List<String> result2 = (List<String>) q.list();
		if (result2.size() > 0) {
			result = result2.get(0).toString();
		}
		tx.commit();
		session.close();
		return result;
	}

	@RequestMapping(value = "/Getregister_numberData", method = RequestMethod.POST)

	public @ResponseBody List<Object> Getregister_numberData(String redg_no) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();
		try {
			Query q1 = sessionHQL.createQuery(

					"select a.section,a.asset_type,a.make_name,a.model_name,a.processor_type,"
							+ "a.ram_capi,a.hdd_capi,a.ssd_capi,a.operating_system,a.office,a.dply_envt,a.mac_address,a.machine_number,"
							+ "a.ip_address,a.s_state,a.b_cost,a.b_head,a.crv_no,a.proc_date,a.gem_no,"
							+ "a.approveois,s.supplier,a.antiviruscheck,a.cd_drive,a.warrantycheck,a.warranty,a.u_file,a.unserviceable_state,"
							+ "a.br_certificate,a.unsv_from_dt,a.maintainagency,a.antivirus,a.antivirus_expry,s.contact_number FROM Assets_Main a, "
							+ " TB_MASTER_SUPPLY_MASTER s WHERE " + " a.supplier_name = CAST(s.id AS text)"
							+ "    AND a.redgno = :redgno  order by redgno ");
			q1.setParameter("redgno", redg_no);
			@SuppressWarnings("unchecked")

			List<Object> list = (List<Object>) q1.list();

			tx.commit();

			return list;

		} catch (RuntimeException e) {

			return null;

		} finally {

			if (sessionHQL != null) {

				sessionHQL.close();

			}

		}

	}


	@RequestMapping(value = "/Getregister_number_peripheral_Data", method = RequestMethod.POST)

	public @ResponseBody List<Object> Getregister_number_peripheral_Data(String redg_no) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();
		try {
//pdated on 23-06-2022 for getting non-effective data in record of View Approve Census form
			Query q1 = sessionHQL.createQuery(

					"select a.section,a.assets_type,a.type_of_hw,a.make_name,a.model_name,a.warrantycheck,a.warranty,a.year_of_proc,a.year_of_manufacturing,a.remarks,a.machine_no,a.is_networked,a.ip_address,a.print,a.scan,"
					+ "a.photography,a.colour,a.display_type,a.display_size,a.display_connector,a.no_of_ports,a.network_features,a.ethernet_port,a.management_layer,"
					+ "a.s_state,a.unserviceable_state,a.br_certificate,a.unsv_from_dt,a.maintainagency,a.b_cost,a.b_head,a.crv_no,a.proc_date,a.u_file,a.gem_no,a.approveois,s.contact_number,s.supplier,a.monochrome_color,"
					+ "a.paper_size,a. connectivity_type,a.capacity,a.hw_interface,a.resolution,a.v_display_size,a.v_display_connector from It_Asset_Peripherals a, "
					+ " TB_MASTER_SUPPLY_MASTER s WHERE " + " a.supplier_name = CAST(s.id AS text)"
					+ "    AND a.redgno = :redgno  order by redgno ");
			q1.setParameter("redgno", redg_no);

			@SuppressWarnings("unchecked")

			List<Object> list = (List<Object>) q1.list();

			tx.commit();

			return list;

		} catch (RuntimeException e) {

			return null;

		} finally {

			if (sessionHQL != null) {

				sessionHQL.close();

			}

		}

	}
	
	
	@RequestMapping(value = "/Getregister_number_consumable_Data", method = RequestMethod.POST)

	public @ResponseBody List<Object> Getregister_number_consumable_Data(String redg_no) {

		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = sessionHQL.beginTransaction();
		try {
//pdated on 23-06-2022 for getting non-effective data in record of View Approve Census form
			Query q1 = sessionHQL.createQuery(

					"select section,assets_type,make_name,model_name,warrantycheck,warranty,remarks,assetcount,proc_cost,proc_date,b_head,b_code,crv_no,gem_no,year_of_proc from CONSUMABLES_MAIN where redgno = :redgno  order by redgno ");
			q1.setParameter("redgno", redg_no);

			@SuppressWarnings("unchecked")

			List<Object> list = (List<Object>) q1.list();

			tx.commit();

			return list;

		} catch (RuntimeException e) {

			return null;

		} finally {

			if (sessionHQL != null) {

				sessionHQL.close();

			}

		}

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
