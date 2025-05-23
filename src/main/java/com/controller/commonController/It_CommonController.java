package com.controller.commonController;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.models.Tbl_CodesForm;
import com.models.itasset.master.TB_MASTER_MAINTENANCE_AGENCY;
import com.persistance.util.HibernateUtil;


@Controller
@RequestMapping(value = {"admin","/" ,"user"})
public class It_CommonController {
	
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();
	
	public List<String> getAssetList() {
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		Query q1 = session1.createQuery("select id,asset_type from TB_MSTR_ASSET_M order by asset_type");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q1.list();
		tx1.commit();
		session1.close();
		return list;
	}	
	public List<String> getTypeOfSecuserList() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();
		Query q1 = session1.createQuery(
			    "select l.userName,l.userId from UserRole u , UserLogin l where u.userId = l.userId and u.roleId = 528");

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();
		
		tx1.commit();

		session1.close();

		return list;

	}
	public List<String> getTypeofRoleList() {

		Session session1 = HibernateUtil.getSessionFactory().openSession();

		Transaction tx1 = session1.beginTransaction();
		Query q1 = session1.createQuery(
			    "\r\n"
			    + "select l.userName,l.userId  from UserRole a , Role b , \r\n"
			    + " UserLogin l where a.roleId=b.roleId and l.userId = a.userId and b.role='IT_TECH' and l.status=1 ");

		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q1.list();
		
		tx1.commit();

		session1.close();

		return list;

	}
	
	public List<String> getProcessorList() {
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		
//		int status=1;
		Query q1 = session1.createQuery("select id,processor_type from TB_MSTR_PROCESSOR_TYPE_M where status='1' order by processor_type ");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q1.list();
		tx1.commit();
		session1.close();
		return list;
	}
	public List<String> getRamList() {
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		Query q1 = session1.createQuery("select id,ram from TB_MSTR_RAM_M ");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q1.list();
		tx1.commit();
		session1.close();
		return list;
	}
	public List<String> getHDDList() {
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		Query q1 = session1.createQuery("select id,hdd from TB_MSTR_HDD_M");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q1.list();
		tx1.commit();
		session1.close();
		return list;
	}
	public List<String> getSDDList() {
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		Query q1 = session1.createQuery("select id,ssd from TB_MSTR_SSD_M");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q1.list();
		tx1.commit();
		session1.close();
		return list;
	}
	public List<Object> getOperatingSystem() {
		List<Object> result=null;
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		Query q1 = session1.createQuery("select id,os from TB_MSTR_OS_M where status='1' order by os ");
		@SuppressWarnings("unchecked")
		List<Object> list = (List<Object>) q1.list();
		
		if(list.size()>0)
		{
			result=list;
		}
		tx1.commit();
		session1.close();
		return result;
	}
	public List<String> getOffice() {
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		Query q1 = session1.createQuery("select id,office from TB_MSTR_OFFICE_M where status='1' order by office");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q1.list();
		tx1.commit();
		session1.close();
		return list;
	}
	public List<String> getOsFirmware() {
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		Query q1 = session1.createQuery("select id,os_firmware from TB_MSTR_OS_FIRMWARE_M order by os_firmware ");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q1.list();
		tx1.commit();
		session1.close();
		return list;
	}

	public List<Object> getdplyEnv() {

		List<Object> result = null;
		Session session1 = null;
		Transaction tx1 = null;
		try {
			session1 = HibernateUtil.getSessionFactory().openSession();
			tx1 = session1.beginTransaction();

			Query q1 = session1.createQuery("select id, dply_env from TB_MSTR_DPLY_ENV_M order by dply_env");
			@SuppressWarnings("unchecked")
			List<Object> list = (List<Object>) q1.list();

			if (list != null && list.size() > 0) {
				result = list;
			}
			tx1.commit();
		} catch (Exception e) {
			if (tx1 != null) {
				tx1.rollback();
			}
			e.printStackTrace(); // Consider using a logging framework
		} finally {
			if (session1 != null) {
				session1.close();
			}
		}
		return result;
	}
	public List<String> gethardwareListDDL() {
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		Query q1 = session1.createQuery("select id,type_of_hw from TB_MSTR_TYPE_OF_HW_M where status='1' order by type_of_hw");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q1.list();
		tx1.commit();
		session1.close();
		return list;
	}
	public List<String> getYearOfProc() {
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		Query q1 = session1.createQuery("select id,year_of_proc from TB_MSTR_YEAR_OF_PROC_M");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q1.list();
		tx1.commit();
		session1.close();
		return list;
	}
	public List<String> getYearOfManufacturing() {
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		Query q1 = session1.createQuery("select id,year_of_manufacturing from TB_MSTR_YEAR_OF_MANUFACTURING_M");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q1.list();
		tx1.commit();
		session1.close();
		return list;
	}
	public List<String> getType1() {
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		Query q1 = session1.createQuery("select id,type from TB_MSTR_TYPE_M order by type");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q1.list();
		tx1.commit();
		session1.close();
		return list;
	}
	
	public List<String> getUpsCapacity() {
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		Query q1 = session1.createQuery("select id,ups_capacity from TB_MSTR_UPS_CAPACITY_M order by ups_capacity");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q1.list();
		tx1.commit();
		session1.close();
		return list;
	}

	public List<String> getPeripheralType() {
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		Query q1 = session1.createQuery("select id,peripheral_type from TB_MSTR_PERIPHERAL_TYPE_M order by peripheral_type");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q1.list();
		tx1.commit();
		session1.close();
		return list;
	}
	
	@RequestMapping(value = "/getCategoryList", method = RequestMethod.POST)

	public @ResponseBody List<Object> getCategoryList(HttpServletRequest request) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = session.beginTransaction();
		int categogry_id = Integer.parseInt(request.getParameter("categogry_id"));
		Query q = session
				.createQuery("select id,assets_name from TB_MSTR_ASSETS where category=:category order by assets_name");

		q.setParameter("category", categogry_id);
		@SuppressWarnings("unchecked")
		List<Object> list = (List<Object>) q.list();
		tx.commit();
		session.close();
		return list;
	}
	public List<String> getMakeName() {
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx1 = session1.beginTransaction();
		Query q1 = session1.createQuery("select id,make_name from TB_MASTER_MAKE order by make_name");
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q1.list();
		tx1.commit();
		session1.close();
		return list;
	}
	
	public List<String> getCategoryList() {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		try {
			Query q1 = sessionHQL.createQuery(
					"select id,assets_name from TB_MSTR_ASSETS where category='2' order by  assets_name");
			@SuppressWarnings("unchecked")
			List<String> list = (List<String>) q1.list();
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


@RequestMapping(value = "/getHWNameList", method = RequestMethod.POST)
	public @ResponseBody List<Object> getHWNameList(HttpServletRequest request) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		int peripheral_type = Integer.parseInt(request.getParameter("peripheral_type"));
		Query q = session.createQuery("select id,type_of_hw from TB_MSTR_TYPE_OF_HW_M where peripheral_type=:peripheral_type order by type_of_hw");
		q.setParameter("peripheral_type", peripheral_type);
		@SuppressWarnings("unchecked")
		List<Object> list = (List<Object>) q.list();
		tx.commit();
		session.close();
		return list;

	}
public List<String> getPeripheral() {
	Session session1 = HibernateUtil.getSessionFactory().openSession();
	Transaction tx1 = session1.beginTransaction();
	Query q1 = session1.createQuery("select id,assets_name from TB_MSTR_ASSETS where category=2 order by assets_name");
	@SuppressWarnings("unchecked")
	List<String> list = (List<String>) q1.list();
	tx1.commit();
	session1.close();
	return list;
}

public List<String> getComputingAssetList() {
	Session session1 = HibernateUtil.getSessionFactory().openSession();
	Transaction tx1 = session1.beginTransaction();
	Query q1 = session1.createQuery("select id,assets_name from TB_MSTR_ASSETS where category=1 order by assets_name");
	@SuppressWarnings("unchecked")
	List<String> list = (List<String>) q1.list();
	tx1.commit();
	session1.close();
	return list;
}
public List<String> getConsumableList() {
	Session session1 = HibernateUtil.getSessionFactory().openSession();
	Transaction tx1 = session1.beginTransaction();
	Query q1 = session1.createQuery("select id,assets_name from TB_MSTR_ASSETS where category=3 order by assets_name");
	@SuppressWarnings("unchecked")
	List<String> list = (List<String>) q1.list();
	tx1.commit();
	session1.close();
	return list;
}


@RequestMapping(value = "/getTypeList", method = RequestMethod.POST)

public @ResponseBody List<String> getTypeList(HttpServletRequest request) {
	Session session = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = session.beginTransaction();
	int status=1;
	int type_of_hw = Integer.parseInt(request.getParameter("type_of_hw"));
	Query q = session.createQuery("select id,type from TB_MSTR_TYPE_M where type_of_hw=:type_of_hw and status=:status order by type");
	q.setParameter("type_of_hw", type_of_hw);
	q.setParameter("status", status);
	@SuppressWarnings("unchecked")
	List<String> list = (List<String>) q.list();
	tx.commit();
	session.close();
	return list;
}

public List<String> getAntivirus() {
	Session session1 = HibernateUtil.getSessionFactory().openSession();
	Transaction tx1 = session1.beginTransaction();
	Query q1 = session1.createQuery("select id,antivirus from TB_MSTR_ANTIVIRUS_M order by antivirus");
	@SuppressWarnings("unchecked")
	List<String> list = (List<String>) q1.list();
	tx1.commit();
	session1.close();
	return list;
}

public List<String> getModelNameList() {
	Session session1 = HibernateUtil.getSessionFactory().openSession();
	Transaction tx1 = session1.beginTransaction();
	Query q1 = session1.createQuery("select id,model_name from TB_MASTER_MODEL order by model_name");
	@SuppressWarnings("unchecked")
	List<String> list = (List<String>) q1.list();
	tx1.commit();
	session1.close();
	return list;
}



@SuppressWarnings("unchecked")
@RequestMapping(value = "/getMakeNameList", method = RequestMethod.POST)
public @ResponseBody List<Object> getMakeNameList(HttpServletRequest request) {

	Session session = HibernateUtil.getSessionFactory().openSession();
	int status=1;
	Transaction tx = session.beginTransaction();
	int assets_name = Integer.parseInt(request.getParameter("assets_name"));
	Query q = session.createQuery("select id,make_name from TB_MASTER_MAKE where assets_name=:assets_name and status=:status order by make_name");
	q.setParameter("assets_name", assets_name);
	q.setParameter("status", status);
	List<Object> list = (List<Object>) q.list();
	tx.commit();
	session.close();
	return list;

}

@SuppressWarnings("deprecation")
@RequestMapping(value = "/getModelNameList", method = RequestMethod.POST)
public @ResponseBody List<Object> getModelNameList(HttpServletRequest request) {
	Session session = HibernateUtil.getSessionFactory().openSession();
	List<Object> list = new ArrayList<>(); // Declare the list here
	Transaction tx = null; // Declare the transaction outside the try block
	try {
		tx = session.beginTransaction();
		int status = 1;
		int make_name = Integer.parseInt(request.getParameter("make_name"));
		Query<Object> q = session.createQuery(
				"select id,model_name from TB_MASTER_MODEL where make_name=:make_name and status=:status order by model_name");
		q.setParameter("make_name", make_name);
		q.setParameter("status", status);

		list = q.list();

		tx.commit();
	} catch (Exception e) {
		if (tx != null) {
			tx.rollback(); // Rollback the transaction in case of an error
		}
		e.printStackTrace(); // Log the exception
	} finally {
		session.close(); // Ensure the session is closed
	}

	return list; // Return the list
}


public List<String> ServiceableList() {
	Session session1 = HibernateUtil.getSessionFactory().openSession();
	Transaction tx1 = session1.beginTransaction();
	Query q1 = session1.createQuery("select codevalue,label from T_Domain_Value where domainid='SERVICE_CAT' ");
	@SuppressWarnings("unchecked")
	List<String> list = (List<String>) q1.list();
	tx1.commit();
	session1.close();
	return list;
}

public List<String> UNServiceableList() {
	Session session1 = HibernateUtil.getSessionFactory().openSession();
	Transaction tx1 = session1.beginTransaction();
	Query q1 = session1.createQuery("select codevalue,label from T_Domain_Value where domainid='UNSERVICEABLE_STATE' order by label");
	@SuppressWarnings("unchecked")
	List<String> list = (List<String>) q1.list();
	tx1.commit();
	session1.close();
	return list;
}

public List<String> getStatusList() {
	Session session1 = HibernateUtil.getSessionFactory().openSession();
	Transaction tx1 = session1.beginTransaction();
	Query q1 = session1.createQuery("select codevalue,label from T_Domain_Value where domainid='STATUS'  order by codevalue");
	@SuppressWarnings("unchecked")
	List<String> list = (List<String>) q1.list();
	tx1.commit();
	session1.close();
	return list;

}	



@RequestMapping(value = "/ComputingAssetList2", method = RequestMethod.POST)
public @ResponseBody List<String> ComputingAssetList2(HttpServletRequest request) {
	Session session = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = session.beginTransaction();
	Query q = session.createQuery("select id,assets_name from TB_MSTR_ASSETS where category=1 ");
	@SuppressWarnings("unchecked")
	List<String> list = (List<String>) q.list();
	tx.commit();
	session.close();
	return list;
}

public List<String> getDisplay_ConnectorList() {
	Session session1 = HibernateUtil.getSessionFactory().openSession();
	Transaction tx1 = session1.beginTransaction();
	Query q1 = session1.createQuery("select codevalue,label from T_Domain_Value where domainid='DISPLAY_CONNECTOR' order by label");
	@SuppressWarnings("unchecked")
	List<String> list = (List<String>) q1.list();
	tx1.commit();
	session1.close();
	return list;
}

@RequestMapping(value = "/getType_of_hw", method = RequestMethod.POST)
public @ResponseBody List<String> getType_of_hw(HttpServletRequest request) {
	Session session = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = session.beginTransaction();
	int assets_name = Integer.parseInt(request.getParameter("assets_name"));
	Query q = session.createQuery(
			"select id,type_of_hw from TB_MSTR_TYPE_OF_HW_M where peripheral_type=:peripheral_type order by type_of_hw");

	q.setParameter("peripheral_type", assets_name);
	@SuppressWarnings("unchecked")
	List<String> list = (List<String>) q.list();
	tx.commit();
	session.close();
	return list;

}

@RequestMapping(value = "/getMakeNameListfromthw", method = RequestMethod.POST)

public @ResponseBody List<String> getMakeNameListfromthw(HttpServletRequest request) {

	Session session = HibernateUtil.getSessionFactory().openSession();

	Transaction tx = session.beginTransaction();
	int type_of_hw = Integer.parseInt(request.getParameter("type_of_hw"));
	Query q = session
			.createQuery("select id,make_name from TB_MASTER_MAKE where type_of_hw=:type_of_hw order by make_name");

	q.setParameter("type_of_hw", type_of_hw);

	@SuppressWarnings("unchecked")

	List<String> list = (List<String>) q.list();

	tx.commit();

	session.close();

	return list;

}

public List<String> getConnectivity_TypeList() {
	Session session1 = HibernateUtil.getSessionFactory().openSession();
	Transaction tx1 = session1.beginTransaction();
	Query q1 = session1.createQuery("select id,connectivity_type from TB_MSTR_CONNECTIVITY where status='1' order by connectivity_type");
	@SuppressWarnings("unchecked")
	List<String> list = (List<String>) q1.list();
	tx1.commit();
	session1.close();
	return list;
}

public List<String> gethwdata() {
	Session session1 = HibernateUtil.getSessionFactory().openSession();
	Transaction tx1 = session1.beginTransaction();
	Query q1 = session1.createQuery("select id,hardware_interface from TB_MSTR_HARDWARE_INTERFACE order by hardware_interface");
	@SuppressWarnings("unchecked")
	List<String> list = (List<String>) q1.list();
	tx1.commit();
	session1.close();
	return list;
}

public List<String> getServiceable_StateList() {
	Session session1 = HibernateUtil.getSessionFactory().openSession();
	Transaction tx1 = session1.beginTransaction();
	Query q1 = session1.createQuery("select codevalue,label from T_Domain_Value where domainid='SERVICE_CAT' ");
	@SuppressWarnings("unchecked")
	List<String> list = (List<String>) q1.list();
	tx1.commit();
	session1.close();
	return list;
}

public List<String> getPaper_SizeList() {
	Session session1 = HibernateUtil.getSessionFactory().openSession();
	Transaction tx1 = session1.beginTransaction();
	Query q1 = session1.createQuery("select codevalue,label from T_Domain_Value where domainid='PAPER_SIZE' ");
	@SuppressWarnings("unchecked")
	List<String> list = (List<String>) q1.list();
	tx1.commit();
	session1.close();
	return list;
}

@RequestMapping(value = "/getPeripheral2", method = RequestMethod.POST)
public  @ResponseBody List<String> getPeripheral2() {
	Session session1 = HibernateUtil.getSessionFactory().openSession();
	Transaction tx1 = session1.beginTransaction();
	Query q1 = session1.createQuery("select id,assets_name from TB_MSTR_ASSETS where category=2");
	@SuppressWarnings("unchecked")
	List<String> list = (List<String>) q1.list();
	tx1.commit();
	session1.close();
	return list;
}


public List<String> getConnectingTechList() {
	Session session1 = HibernateUtil.getSessionFactory().openSession();
	Transaction tx1 = session1.beginTransaction();
	Query q1 = session1.createQuery("select id,connectivity_type from TB_MSTR_CONNECTIVITY order by connectivity_type ");
	@SuppressWarnings("unchecked")
	List<String> list = (List<String>) q1.list();
	tx1.commit();
	session1.close();
	return list;
}

public List<String> getEthernetPortList() {
	Session session1 = HibernateUtil.getSessionFactory().openSession();
	Transaction tx1 = session1.beginTransaction();
	Query q1 = session1.createQuery("select id,ethernet_port from TB_MSTR_ETHERNET_PORT order by ethernet_port");
	@SuppressWarnings("unchecked")
	List<String> list = (List<String>) q1.list();
	tx1.commit();
	session1.close();
	return list;
}


public List<String> getBudgetHeadList() {
	Session session1 = HibernateUtil.getSessionFactory().openSession();
	Transaction tx1 = session1.beginTransaction();
	Query q1 = session1.createQuery("select id,budget_head from TB_MASTER_BUDGET order by budget_head");
	@SuppressWarnings("unchecked")
	List<String> list = (List<String>) q1.list();
	tx1.commit();
	session1.close();
	return list;
}

public List<String> getBudgetCodeList() {
	Session session1 = HibernateUtil.getSessionFactory().openSession();
	Transaction tx1 = session1.beginTransaction();
	Query q1 = session1.createQuery("select id,budget_code from TB_MASTER_BUDGET order by budget_code");
	@SuppressWarnings("unchecked")
	List<String> list = (List<String>) q1.list();
	tx1.commit();
	session1.close();
	return list;
}


@RequestMapping(value = "/getBudgetCodeList", method = RequestMethod.POST)
public @ResponseBody List<Object[]> getBudgetCodeList(HttpServletRequest request) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    List<Object[]> list = new ArrayList<>();

    try {
        Transaction tx = session.beginTransaction();
        String budget_head = request.getParameter("b_head");
        
        // Create the query
        Query<Object[]> q = session.createQuery("select id, budget_code from TB_MASTER_BUDGET where budget_head = :budget_head order by budget_code", Object[].class);
        q.setParameter("budget_head", budget_head);

        // Execute the query and get the results
        list = q.list();
        tx.commit();
    } catch (Exception e) {
        e.printStackTrace();
        // Optionally, you can handle the transaction rollback here
    } finally {
        session.close(); // Ensure the session is closed
    }

    return list; // Return the list of budget codes
}

public List<String> hw_interfaceList() {
	Session session1 = HibernateUtil.getSessionFactory().openSession();
	Transaction tx1 = session1.beginTransaction();
	Query q1 = session1.createQuery("select id,hardware_interface from TB_MSTR_HARDWARE_INTERFACE order by hardware_interface");
	@SuppressWarnings("unchecked")
	List<String> list = (List<String>) q1.list();
	tx1.commit();
	session1.close();
	return list;
}

public List<String> getEthernet_portList() {
	Session session1 = HibernateUtil.getSessionFactory().openSession();
	Transaction tx1 = session1.beginTransaction();
	Query q1 = session1.createQuery("select id,ethernet_port from TB_MSTR_ETHERNET_PORT");
	@SuppressWarnings("unchecked")
	List<String> list = (List<String>) q1.list();
	tx1.commit();
	session1.close();
	return list;
}

public List<String> getManagement_layerList() {
	Session session1 = HibernateUtil.getSessionFactory().openSession();
	Transaction tx1 = session1.beginTransaction();
	Query q1 = session1.createQuery("select id,management_layer from TB_MSTR_MANAGEMENT_LAYER");
	@SuppressWarnings("unchecked")
	List<String> list = (List<String>) q1.list();
	tx1.commit();
	session1.close();
	return list;
}

public List<String> getNetwork_featuresList() {
	Session session1 = HibernateUtil.getSessionFactory().openSession();
	Transaction tx1 = session1.beginTransaction();
	Query q1 = session1.createQuery("select id,network_features from TB_MSTR_NETWORK_FEATURES order by network_features");
	@SuppressWarnings("unchecked")
	List<String> list = (List<String>) q1.list();
	tx1.commit();
	session1.close();
	return list;
}
public List<String> getlinedteList() {
	Session session1 = HibernateUtil.getSessionFactory().openSession();
	Transaction tx1 = session1.beginTransaction();
	Query q1 = session1.createQuery("select distinct line_dte_sus,line_dte_name from TB_ORBAT_LINE_DTE where line_dte_sus is not null and line_dte_sus!='' ");
	@SuppressWarnings("unchecked")
	List<String> list = (List<String>) q1.list();
	tx1.commit();
	session1.close();
	return list;
}

//bisag v4 19112022
public List<String> getlinedteArmList() {
	Session session1 = HibernateUtil.getSessionFactory().openSession();
	Transaction tx1 = session1.beginTransaction();
	Query q1 = session1.createQuery("select distinct arm_code,arm_desc from TB_ORBAT_LINE_DTE where line_dte_sus is not null and line_dte_sus!='' ");
	@SuppressWarnings("unchecked")
	List<String> list = (List<String>) q1.list();
	tx1.commit();
	session1.close();
	return list;
}
//

//** BISAG  AHM** //

	@RequestMapping(value = "/getServicable", method = RequestMethod.POST)
	
	public @ResponseBody List<String> getServicable(HttpServletRequest request,int p_id) {
	
	Session session = HibernateUtil.getSessionFactory().openSession();
	
	
	Transaction tx = session.beginTransaction();

	Query q = session.createQuery("select service_state,unsv_from_dt,unservice_state from Assets_Child  where p_id=:p_id and service_state='2' and status='1'  order by id desc").setMaxResults(1);
	q.setParameter("p_id",p_id);

	
	@SuppressWarnings("unchecked")
	List<String> list = (List<String>) q.list();
	
	
	
	tx.commit();
	
	session.close();
	
	return list;

}
	
@RequestMapping(value = "/getServicableForPeripheral", method = RequestMethod.POST)
	
	public @ResponseBody List<String> getServicableForPeripheral(HttpServletRequest request,int p_id) {
	
	Session session = HibernateUtil.getSessionFactory().openSession();
	
	
	Transaction tx = session.beginTransaction();

	Query q = session.createQuery("select service_state,unsv_from_dt,unservice_state from TB_PERIPHERAL_CHILD  where p_id=:p_id and service_state='2' and status='1'  order by id desc").setMaxResults(1);
	q.setParameter("p_id",p_id);

	@SuppressWarnings("unchecked")
	List<String> list = (List<String>) q.list();
	
	
	tx.commit();
	
	session.close();
	
	return list;

}



	@RequestMapping(value = "/getDownloadUtility1", method = RequestMethod.POST)
	
	 public void  getDownloadUtility1(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
	    String folder = "IT ASSEST OFFLINE";
		try {
	    	File Dir = new File(System.getProperty("user.home")+File.separator+"Desktop"+File.separator+folder);
			Dir.mkdir();
			String fullPath = request.getRealPath("/") + "doc\\IT_Assets_Computing.html";
	    	String Desktop_foolder = System.getProperty("user.home")+File.separator+"Desktop"+File.separator+""+folder+""+File.separator+request.getParameter("screen_name")+".html";
	  
	      File myObj = new File(Desktop_foolder);
	      if (myObj.createNewFile()) {
	        FileWriter myWriter = new FileWriter(myObj);
	        myWriter.write(fullPath);
	        myWriter.close();
	      } else {
	        
	      }
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	}
	
	
	@RequestMapping(value = "/getBudgetCodeList1", method = RequestMethod.POST)
	public @ResponseBody List<String> getBudgetCodeList1(HttpServletRequest request) {
	    Session session = HibernateUtil.getSessionFactory().openSession();
	    List<String> list = new ArrayList<>(); // Declare the list here

	    try {
	        Transaction tx = session.beginTransaction();
	        String budget_head = request.getParameter("b_head");
	        Query<String> q = session.createQuery("select budget_code from TB_MASTER_BUDGET where budget_head=:budget_head", String.class);
	        q.setParameter("budget_head", budget_head);

	        // Fetch the list of budget codes
	        list = q.list(); // Assign the result to the list
	        tx.commit();
	    } catch (Exception e) {
	        e.printStackTrace();
	        // Optionally, you can handle the transaction rollback here
	    } finally {
	        session.close(); // Ensure the session is closed
	    }

	    return list; // Return the list
	}
	
	
	
	//** END BISAG AHM** //
	
	
	
	@RequestMapping(value = "/getAllMachineNumber", method = RequestMethod.POST)

	public @ResponseBody List<String> getAllMachineNumber(HttpServletRequest request) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = session.beginTransaction();
		String machine_no = request.getParameter("machine_no");
		Query q = session.createQuery("select id,machine_no from It_Asset_Peripherals where machine_no=:machine_no ");

		q.setParameter("machine_no",machine_no);



		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q.list();

		
		
		tx.commit();

		session.close();

		return list;

	}
	
	
	@RequestMapping(value = "/getAllMachineNumberComputing", method = RequestMethod.POST)

	public @ResponseBody List<String> getAllMachineNumberComputing(HttpServletRequest request) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = session.beginTransaction();
		String machine_number = request.getParameter("machine_number");
		Query q = session.createQuery("select id,machine_number from Assets_Main where machine_number=:machine_number ");

		q.setParameter("machine_number",machine_number);



		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q.list();

		
		
		tx.commit();

		session.close();

		return list;

	}
	
	
	
	
	
	
	
	
	
	@RequestMapping(value = "/getBrandOtherList", method = RequestMethod.POST)

	public @ResponseBody List<String> getBrandOtherList(HttpServletRequest request) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = session.beginTransaction();
		String asset_type = request.getParameter("asset_type");
		String brand_other = request.getParameter("brand_other");
		Query q = session.createQuery("select id,assets_name,make_name from TB_MASTER_MAKE where assets_name=:assets_name and upper(regexp_replace(make_name, '[\\s+]', '', 'g'))=:make_name");

		if(brand_other != null)
		
		brand_other=brand_other.replaceAll("\\s", "");
		
		q.setParameter("assets_name",Integer.parseInt(asset_type));
		q.setParameter("make_name",brand_other.toUpperCase());
		


		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q.list();

		
		
		tx.commit();

		session.close();

		return list;

	}
	
	
	
	@RequestMapping(value = "/getPeriBrandOtherList", method = RequestMethod.POST)

	public @ResponseBody List<String> getPeriBrandOtherList(HttpServletRequest request) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = session.beginTransaction();
		String assets_type = request.getParameter("assets_type");
		String brand_other = request.getParameter("brand_other");
		Query q = session.createQuery("select id,assets_name,make_name from TB_MASTER_MAKE where assets_name=:assets_name and upper(regexp_replace(make_name, '[\\s+]', '', 'g'))=:make_name");

		if(brand_other != null)
		
		brand_other=brand_other.replaceAll("\\s", "");
		
		q.setParameter("assets_name",Integer.parseInt(assets_type));
		q.setParameter("make_name",brand_other.toUpperCase());
		


		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q.list();

		
		
		tx.commit();

		session.close();

		return list;

	}
	
	
	
	
	/////////New Change//////
	
	@RequestMapping(value = "/getModelOtherList", method = RequestMethod.POST)

	public @ResponseBody List<String> getModelOtherList(HttpServletRequest request) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = session.beginTransaction();
		String asset_type = request.getParameter("asset_type");
//		String make_name = request.getParameter("make_name");
		String model_other = request.getParameter("model_other");
		Query q = session.createQuery("select id,assets_name,model_name from TB_MASTER_MODEL where assets_name=:assets_name and upper(regexp_replace(model_name, '[\\s+]', '', 'g'))=:model_name");

		model_other=model_other.replaceAll("\\s", "");
		q.setParameter("assets_name",Integer.parseInt(asset_type));
//		q.setParameter("make_name",Integer.parseInt(make_name));
		q.setParameter("model_name",model_other.toUpperCase());



		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q.list();

		
		
		tx.commit();

		session.close();

		return list;

	}
	
	
	
	@RequestMapping(value = "/getPeriModelOtherList", method = RequestMethod.POST)

	public @ResponseBody List<String> getPeriModelOtherList(HttpServletRequest request) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = session.beginTransaction();
		String assets_type = request.getParameter("assets_type");
//		String make_name = request.getParameter("make_name");
		String model_other = request.getParameter("model_other");
		Query q = session.createQuery("select id,assets_name,model_name from TB_MASTER_MODEL where assets_name=:assets_name and upper(regexp_replace(model_name, '[\\s+]', '', 'g'))=:model_name");

		model_other=model_other.replaceAll("\\s", "");
		q.setParameter("assets_name",Integer.parseInt(assets_type));
//		q.setParameter("make_name",Integer.parseInt(make_name));
		q.setParameter("model_name",model_other.toUpperCase());



		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q.list();

		
		
		tx.commit();

		session.close();

		return list;

	}
	
	
	@RequestMapping(value = "/getProcessorTypeOtherList", method = RequestMethod.POST)

	public @ResponseBody List<String> getProcessorTypeOtherList(HttpServletRequest request) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = session.beginTransaction();

		String pro_type_other = request.getParameter("pro_type_other");
		Query q = session.createQuery("select id,processor_type from TB_MSTR_PROCESSOR_TYPE_M where upper(regexp_replace(processor_type, '[\\s+]', '', 'g'))=:pro_type_other");

		pro_type_other=pro_type_other.replaceAll("\\s", "");
		q.setParameter("pro_type_other",pro_type_other.toUpperCase());



		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q.list();

		
		
		tx.commit();

		session.close();

		return list;

	}
	
	
	@RequestMapping(value = "/getOperatingSystemOtherList", method = RequestMethod.POST)

	public @ResponseBody List<String> getOperatingSystemOtherList(HttpServletRequest request) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = session.beginTransaction();

		String os_other = request.getParameter("os_other");
		Query q = session.createQuery("select id,os from TB_MSTR_OS_M where upper(regexp_replace(os, '[\\s+]', '', 'g'))=:os_other");

		os_other=os_other.replaceAll("\\s", "");
		q.setParameter("os_other",os_other.toUpperCase());



		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q.list();

		
		
		tx.commit();

		session.close();

		return list;

	}

	
	
	@RequestMapping(value = "/getOfficeOtherList", method = RequestMethod.POST)

	public @ResponseBody List<String> getOfficeOtherList(HttpServletRequest request) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = session.beginTransaction();

		String office_other = request.getParameter("office_other");
		Query q = session.createQuery("select id,office from TB_MSTR_OFFICE_M where upper(regexp_replace(office, '[\\s+]', '', 'g'))=:office_other");

		office_other=office_other.replaceAll("\\s", "");
		q.setParameter("office_other",office_other.toUpperCase());



		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q.list();

		
		
		tx.commit();

		session.close();

		return list;

	}

	
	@RequestMapping(value = "/getTypeOfPeripheralOtherList", method = RequestMethod.POST)

	public @ResponseBody List<String> getTypeOfPeripheralOtherList(HttpServletRequest request) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = session.beginTransaction();
//		String peripheral_type = request.getParameter("asset_type");
		String type_of_hw = request.getParameter("peri_hw_other");
		Query q = session.createQuery("select id,type_of_hw from TB_MSTR_TYPE_OF_HW_M where upper(regexp_replace(type_of_hw, '[\\s+]', '', 'g'))=:type_of_hw");

		type_of_hw=type_of_hw.replaceAll("\\s", "");
		q.setParameter("type_of_hw",type_of_hw.toUpperCase());



		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q.list();

		
		
		tx.commit();

		session.close();

		return list;

	}
	
	@RequestMapping(value = "/getTypeOfModelOtherList", method = RequestMethod.POST)

	public @ResponseBody List<String> getTypeOfModelOtherList(HttpServletRequest request) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = session.beginTransaction();
//		String peripheral_type = request.getParameter("asset_type");
		String type_of_hw = request.getParameter("type_of_hw");
		String model_type_other = request.getParameter("model_type_other");
		Query q = session.createQuery("select id,type_of_hw,type from TB_MSTR_TYPE_M where  type_of_hw=:type_of_hw and upper(regexp_replace(type, '[\\s+]', '', 'g'))=:type");

		model_type_other=model_type_other.replaceAll("\\s", "");
		
//		q.setParameter("peripheral_type",Integer.parseInt(peripheral_type));
		q.setParameter("type_of_hw",Integer.parseInt(type_of_hw));
		q.setParameter("type",model_type_other.toUpperCase());



		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q.list();

		
		
		tx.commit();

		session.close();

		return list;

	}
	
	
	@RequestMapping(value = "/getTypeConnectivityOtherList", method = RequestMethod.POST)

	public @ResponseBody List<String> getTypeConnectivityOtherList(HttpServletRequest request) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = session.beginTransaction();
//		String peripheral_type = request.getParameter("asset_type");
		String connectivity_other = request.getParameter("connectivity_other");
		Query q = session.createQuery("select id,connectivity_type from TB_MSTR_CONNECTIVITY where upper(regexp_replace(connectivity_type, '[\\s+]', '', 'g'))=:connectivity_type");

		connectivity_other=connectivity_other.replaceAll("\\s", "");
		
//		q.setParameter("peripheral_type",Integer.parseInt(peripheral_type));
//		q.setParameter("type_of_hw",Integer.parseInt(type_of_hw));
		q.setParameter("connectivity_type",connectivity_other.toUpperCase());



		@SuppressWarnings("unchecked")

		List<String> list = (List<String>) q.list();

		
		
		tx.commit();

		session.close();

		return list;

	}
	
	
	
	
	///	BISAG V1 220822 



//bisag v2 141122 start
@RequestMapping(value = "/getmachine_no_CompListApproved", method = RequestMethod.POST)
public @ResponseBody List<String> getmachine_no_CompListApproved(HttpSession sessionUserId,HttpServletRequest request,String machine_no) {

      Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
      Transaction tx = sessionHQL.beginTransaction();
//try{
      String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
      String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
      String roleType = sessionUserId.getAttribute("roleType").toString();
      Query q= null;
      String rsus=request.getParameter("roleSus");
      if( rsus!=null && !rsus.equals("")){
      	roleSusNo=rsus;
      }
      
      if(roleSusNo!=null && !roleSusNo.equals("")){
            
               q = sessionHQL.createQuery("select distinct p.machine_number from Assets_Main p where  "
               		+ ""
               		+ " p.sus_no=:roleSusNo and upper(p.machine_number) like :machine_number order by p.machine_number");
               
               q.setParameter("roleSusNo", roleSusNo);  

      }
      else
      {
               
               q = sessionHQL.createQuery("select distinct p.machine_number from Assets_Main p where upper(p.machine_number) like :machine_number order by p.machine_number");
                                      
      }
      q.setParameter("machine_number", "%"+machine_no.toUpperCase()+"%");  
      
      @SuppressWarnings("unchecked")        
      List<String> list = (List<String>) q.list();
      tx.commit();
      

              String enckey = hex_asciiDao.getAlphaNumericString();
              Cipher c = null;
              try {
                      c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId, enckey);
              } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
                              | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
                      e.printStackTrace();
              }
              List<String> FinalList = new ArrayList<String>();
              for (int i = 0; i < list.size(); i++) {
                      byte[] encCode = null;
                      try {
                              encCode = c.doFinal(String.valueOf(list.get(i)).getBytes());
                      } catch (IllegalBlockSizeException | BadPaddingException e) {
                              e.printStackTrace();
                      }
                      String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
                      FinalList.add(base64EncodedEncryptedCode);
              }
              FinalList.add(enckey + "4bsjyg==");
              return FinalList;
              
}


@RequestMapping(value = "/getmachine_no_perifListApproved", method = RequestMethod.POST)
public @ResponseBody List<String> getmachine_no_perifListApproved(HttpSession sessionUserId,HttpServletRequest request,String machine_no) {

      Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
      Transaction tx = sessionHQL.beginTransaction();
//try{
      String roleAccess = sessionUserId.getAttribute("roleAccess").toString();
      String roleSusNo = sessionUserId.getAttribute("roleSusNo").toString();
      String roleType = sessionUserId.getAttribute("roleType").toString();
      Query q= null;
      String rsus=request.getParameter("roleSus");
      if( rsus!=null && !rsus.equals("")){
      	roleSusNo=rsus;
      }
      
      if(roleSusNo!=null && !roleSusNo.equals("")){
             
               q = sessionHQL.createQuery("select distinct p.machine_no from It_Asset_Peripherals p where  p.sus_no=:roleSusNo and upper(p.machine_no) like :machine_no order by p.machine_no");
               
               q.setParameter("roleSusNo", roleSusNo);  

      }
      else
      {
               
               q = sessionHQL.createQuery("select distinct p.machine_no from It_Asset_Peripherals p where upper(p.machine_no) like :machine_no order by p.machine_no");
                                      
      }
      q.setParameter("machine_no", "%"+machine_no.toUpperCase()+"%");  
      
      
      @SuppressWarnings("unchecked")        
      List<String> list = (List<String>) q.list();
      tx.commit();
      

              String enckey = hex_asciiDao.getAlphaNumericString();
              Cipher c = null;
              try {
                      c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId, enckey);
              } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
                              | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
                      e.printStackTrace();
              }
              List<String> FinalList = new ArrayList<String>();
              for (int i = 0; i < list.size(); i++) {
                      byte[] encCode = null;
                      try {
                              encCode = c.doFinal(String.valueOf(list.get(i)).getBytes());
                      } catch (IllegalBlockSizeException | BadPaddingException e) {
                              e.printStackTrace();
                      }
                      String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
                      FinalList.add(base64EncodedEncryptedCode);
              }
              FinalList.add(enckey + "4bsjyg==");
              return FinalList;
              
}


//bisag v2 141122 end

public List<String> getTypeofRankList() {

	Session session1 = HibernateUtil.getSessionFactory().openSession();

	Transaction tx1 = session1.beginTransaction();
	Query q1 = session1.createQuery(
		    "select  distinct id, rank from TB_MASTER_RANK where status = 'ACTIVE'");

	@SuppressWarnings("unchecked")

	List<String> list = (List<String>) q1.list();
	
	tx1.commit();

	session1.close();

	return list;

}

@RequestMapping(value = "/getActiveUsernameList", method = RequestMethod.POST)
public @ResponseBody List<String> getActiveUsernameList(String loginname, HttpSession sessionUserId) {
	Session session = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = session.beginTransaction();
	Query q = session.createQuery("select distinct login_name from UserLogin where accountNonLocked='1'and enabled='1'  and login_name like :login_name order by login_name ").setMaxResults(10);
	q.setParameter("login_name", "%"+loginname+"%");
	@SuppressWarnings("unchecked")
	List<String> list = (List<String>) q.list();
	tx.commit();
	session.close();
	String enckey = hex_asciiDao.getAlphaNumericString();
	Cipher c = null;
	try {
		c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId, enckey);
	} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
			| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	List<String> FinalList = new ArrayList<String>();
	for (int i = 0; i < list.size(); i++) {
		byte[] encCode = null;
		try {
			encCode = c.doFinal(list.get(i).getBytes());
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
		FinalList.add(base64EncodedEncryptedCode);
	}
	// Enc Key Append Last value of List
	FinalList.add(enckey + "4bsjyg==");
	return FinalList;
}



public List<Object> getMaintainceAgencyList() {
	Session session1 = HibernateUtil.getSessionFactory().openSession();
	Transaction tx1 = session1.beginTransaction();
	Query q1 = session1.createQuery("select id,maintenance from TB_MASTER_MAINTENANCE_AGENCY order by maintenance");
	@SuppressWarnings("unchecked")
	List<Object> list = (List<Object>) q1.list();
	tx1.commit();
	session1.close();
	return list;
}	


//
//public String getQRnumber(String Type,int makeBrand ,int model,int assettype ,String Tablesname) {
//
//    Session session1 = HibernateUtil.getSessionFactory().openSession();
//	Transaction tx1 = session1.beginTransaction();
//	Query q1 = session1.createQuery("SELECT \r\n"
//			+ "    CASE \r\n"
//			+ "        WHEN MAX(redgno)  IS NULL THEN  CONCAT(EXTRACT(YEAR FROM CURRENT_DATE)::TEXT, \r\n"
//			+ "                         LPAD(EXTRACT(MONTH FROM CURRENT_DATE  )::TEXT, 2, '0'), \r\n"
//			+ "                         '0001')\r\n"
//			+ "        ELSE (MAX(CAST(redgno AS INTEGER)) + 1)::text \r\n"
//			+ "    END AS new_redgno\r\n"
//			+ "FROM  \r\n"
//			+ Tablesname 
//			+ "WHERE redgno LIKE CONCAT(EXTRACT(YEAR FROM CURRENT_DATE)::TEXT, \r\n"
//			+ "                         LPAD(EXTRACT(MONTH FROM CURRENT_DATE)::TEXT, 2, '0'), \r\n"
//			+ "                         '%')");
//	@SuppressWarnings("unchecked")
//	List<String> list = (List<String>) q1.list();
//    
//    String value = String.format("%s/%s/%s/%s/%s",Type,assettype, makeBrand, model, list.get(0));
//    return value;
//}	
@RequestMapping(value = "/getActiveUsernameListFromSection", method = RequestMethod.POST)
public @ResponseBody List<String> getActiveUsernameListFromSection(String Section,String loginname, HttpSession sessionUserId) {
	Session session = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = session.beginTransaction();
	Query q = session.createQuery("select distinct login_name from UserLogin where accountNonLocked='1'and enabled='1'  and user_sus_no=:section and login_name like :login_name  order by login_name ").setMaxResults(10);
	q.setParameter("section", Section);
	q.setParameter("login_name", "%"+loginname+"%");
	@SuppressWarnings("unchecked")
	List<String> list = (List<String>) q.list();
	tx.commit();
	session.close();
	String enckey = hex_asciiDao.getAlphaNumericString();
	Cipher c = null;
	try {
		c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId, enckey);
	} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
			| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	List<String> FinalList = new ArrayList<String>();
	for (int i = 0; i < list.size(); i++) {
		byte[] encCode = null;
		try {
			encCode = c.doFinal(list.get(i).getBytes());
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
		FinalList.add(base64EncodedEncryptedCode);
	}
	// Enc Key Append Last value of List
	FinalList.add(enckey + "4bsjyg==");
	return FinalList;
}




@RequestMapping(value = "/getRedgListFromSection", method = RequestMethod.POST)
public @ResponseBody List<String> getRedgListFromSection(String Section,String loginname,String Type, HttpSession sessionUserId) {
	String tablename="";
	if(Type.equals("1") ||Type=="1")
	{
		tablename="Assets_Main";
	}
	
	if(Type.equals("2") ||Type=="2")
	{
		tablename="It_Asset_Peripherals";
	}
	if(Type.equals("3") ||Type=="3")
	{
		tablename="CONSUMABLES_MAIN";
	}

	Session session = HibernateUtil.getSessionFactory().openSession();
	Transaction tx = session.beginTransaction();
	String qerry="select distinct redgno from "+tablename +" where status='1'  and section=:section and split_part(redgno, '/', 5) like :login_name  order by redgno ";
	Query q = session.createQuery(qerry).setMaxResults(16);
	q.setParameter("section", Section);
	q.setParameter("login_name", "%"+loginname+"%");
	@SuppressWarnings("unchecked")
	List<String> list = (List<String>) q.list();
	tx.commit();
	session.close();
	String enckey = hex_asciiDao.getAlphaNumericString();
	Cipher c = null;
	try {
		c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId, enckey);
	} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException
			| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	List<String> FinalList = new ArrayList<String>();
	for (int i = 0; i < list.size(); i++) {
		byte[] encCode = null;
		try {
			encCode = c.doFinal(list.get(i).getBytes());
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String base64EncodedEncryptedCode = new String(Base64.encodeBase64(encCode));
		FinalList.add(base64EncodedEncryptedCode);
	}
	// Enc Key Append Last value of List
	FinalList.add(enckey + "4bsjyg==");
	return FinalList;
}

}
