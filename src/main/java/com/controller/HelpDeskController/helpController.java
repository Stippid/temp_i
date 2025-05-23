
package com.controller.HelpDeskController;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.URLConnection;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

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
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.controller.DateWithTimestamp.DateWithTimeStampController;
import com.controller.commonController.It_CommonController;
import com.controller.login.RoleController;
import com.controller.validation.ValidationController;
import com.dao.helpDesk.HelpDAO;
import com.dao.login.HexatoAsciiDAO;
import com.dao.login.HexatoAsciiDAOImpl;
import com.dao.login.RoleBaseMenuDAO;
import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;
import com.github.dandelion.datatables.core.ajax.DatatablesResponse;
import com.github.dandelion.datatables.extras.spring3.ajax.DatatablesParams;
import com.models.TB_LDAP_MODULE_MASTER;
import com.models.TB_LDAP_SCREEN_MASTER;
import com.models.TB_LDAP_SUBMODULE_MASTER;
import com.models.Helpdesk.HD_TB_BISAG_FAQ;
import com.models.Helpdesk.HD_TB_BISAG_TICKET_GENERATE;
import com.models.Helpdesk.HD_TB_BISAG_USER_INFO;

import com.persistance.util.HibernateUtil;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class helpController {
	HexatoAsciiDAO hex_asciiDao = new HexatoAsciiDAOImpl();

	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	private HelpDAO help;

	RoleController role = new RoleController();

	ValidationController validation = new ValidationController();

	It_CommonController dd = new It_CommonController();

	@RequestMapping(value = "/myticket", method = RequestMethod.GET)
	public ModelAndView myticket(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();

		Boolean val = roledao.ScreenRedirect("myticket", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		String userId = session.getAttribute("userId").toString();
		Mmap.put("getModuleNameList", getModuleNameHelpDeskList(session));
		List<Map<String, Object>> list = help.getAllCodeListJdbc("", "", "", "", "0", userId, roleid, "0", "");

		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());

		Mmap.put("msg", msg);
		return new ModelAndView("myticketTiles");
	}

	@RequestMapping(value = "/viewuserticket", method = RequestMethod.GET)
	public ModelAndView viewuserticket(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();

//		Boolean val = roledao.ScreenRedirect("myticket", roleid);
//		if (val == false) {
//			return new ModelAndView("AccessTiles");
//		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		String userId = session.getAttribute("userId").toString();
		Mmap.put("getModuleNameList", getModuleNameHelpDeskList(session));


		Mmap.put("msg", msg);
		return new ModelAndView("myticketTiles");
	}

	@RequestMapping(value = "/manageticket", method = RequestMethod.GET)
	public ModelAndView manageticket(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		if (!roleid.equals("1")) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		Mmap.put("getModuleNameList", getModuleNameHelpDeskList(session));
		Mmap.put("label", "Manage User Tickets");
		Mmap.put("msg", msg);
		return new ModelAndView("myticketTiles");
	}

	@RequestMapping(value = "/admin/getAllCodeListJdbc1", method = RequestMethod.POST)
	public ModelAndView getAllCodeListJdbc1(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "ticket1", required = false) String ticket,
			@RequestParam(value = "ticket_status1", required = false) String ticket_status,
			@RequestParam(value = "from_date1", required = false) String from_date,
			@RequestParam(value = "to_date1", required = false) String to_date,
			@RequestParam(value = "help_topic1", required = false) String help_topic,
			@RequestParam(value = "type1", required = false) String type,
			@RequestParam(value = "module1", required = false) String module,
			@RequestParam(value = "label", required = false) String label1) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("myticket", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		try {
			Date to_date1 = new SimpleDateFormat("dd-MM-yyyy").parse(to_date);
			Date from_date1 = new SimpleDateFormat("dd-MM-yyyy").parse(from_date);
			if (to_date1.before(from_date1)) {
				Mmap.put("msg", "To date must be greater than from date");
				return new ModelAndView("myticketTiles");
			}
		} catch (ParseException e) {
			// e.printStackTrace();
		}
		Mmap.put("ticket1", ticket);
		Mmap.put("ticket_status1", ticket_status);
		Mmap.put("from_date1", from_date);
		Mmap.put("to_date1", to_date);
		Mmap.put("help_topic1", help_topic);
		Mmap.put("type1", type);
		Mmap.put("module1", module);
		Mmap.put("label", label1);
		Mmap.put("getModuleNameList", getModuleNameHelpDeskList(session));
		String userId = session.getAttribute("userId").toString();
		List<Map<String, Object>> list = help.getAllCodeListJdbc(ticket, ticket_status, from_date, to_date, help_topic,
				userId, roleid, module, label1);
		Mmap.put("list", list);
		Mmap.put("list.size()", list.size());
		return new ModelAndView("myticketTiles");
	}

	@RequestMapping(value = "/viewticketDetails")
	public ModelAndView viewticketDetails(HttpSession sessionA, @ModelAttribute("updateid") int updateid,
			@ModelAttribute("label3") String label3, HttpServletRequest request, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("myticket", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		model.put("msg1", msg);
		System.err.println(label3 + "  ===== label3");

		if (roleid.equals("1") && label3.equalsIgnoreCase("Manage User Tickets")) {
			HD_TB_BISAG_TICKET_GENERATE h = help.gethelpbyId(updateid);
			model.put("viewticketCMD", h);
			if (h.getModule() == -1) {
				model.put("moduleId", "others");
				model.put("moduleName", "others");
//				if(getUserNameList(0).size() > 0) {
//					model.put("agent_name", getUserNameList(0).get(0));
//				}
			} else {
				model.put("moduleId", h.getModule());
				model.put("moduleName", getModuleNameList(h.getModule()).get(0));
				// model.put("sub_module", getSubModuleNameList(h.getSub_module()).get(0));
				// model.put("screen_name", getScreenNamebyCodeList(h.getScreen_name()).get(0));
//				if(getUserNameList(h.getModule()).size() > 0) {
//					model.put("agent_name", getUserNameList(h.getModule()).get(0));
//				}
			}
			String help_topic = h.getHelp_topic();
			if (help_topic.equals("1")) {
				model.put("help_topic", "Hardware Issue");
			}
			if (help_topic.equals("2")) {
				model.put("help_topic", "Software Issue");
			}
			if (help_topic.equals("3")) {
				model.put("help_topic", "Feedback");
			}
			if (help_topic.equals("4")) {
				model.put("help_topic", "Others");
			}


			model.put("id", h.getId());
			model.put("ticket", h.getTicket());
			model.put("username", h.getUsername());
			model.put("created_on", h.getCreated_on());
			model.put("completed_dt", h.getCompleted_dt());
			model.put("assigned_dt", h.getAssigned_dt());
			model.put("screen_shot", h.getScreen_shot());///
			model.put("issue_summary", h.getIssue_summary());
			model.put("description", h.getDescription());
			model.put("ticket_status", h.getTicket_status());
			model.put("remarks", h.getRemarks());
			model.put("assigned_to", h.getAssigned_to());
			model.put("assigned_to", h.getAssigned_to());
			model.put("getSectionNameList", role.getSectionNameList());
			model.put("getSectionvalue", h.getSection());
			return new ModelAndView("viewticketTiles");
		} else if (roleid.equals("1") && label3.equalsIgnoreCase("My Tickets")) {
			HD_TB_BISAG_TICKET_GENERATE h = help.gethelpbyId(updateid);
			model.put("userticketCMD", h);
			if (h.getModule() == -1) {
				model.put("moduleId", "others");
				model.put("moduleName", "others");
//				if(getUserNameList(0).size() > 0) {
//					model.put("agent_name", getUserNameList(0).get(0));
//				}
			} else {
				model.put("moduleId", h.getModule());
				model.put("moduleName", getModuleNameList(h.getModule()).get(0));
				// model.put("sub_module", getSubModuleNameList(h.getSub_module()).get(0));
				// model.put("screen_name", getScreenNamebyCodeList(h.getScreen_name()).get(0));
//				if(getUserNameList(h.getModule()).size() > 0) {
//					model.put("agent_name", getUserNameList(h.getModule()).get(0));
//				}
			}
			String help_topic = h.getHelp_topic();
			if (help_topic.equals("1")) {
				model.put("help_topic", "Hardware Issue");
			}
			if (help_topic.equals("2")) {
				model.put("help_topic", "Software Issue");
			}
			if (help_topic.equals("3")) {
				model.put("help_topic", "Feedback");
			}
			if (help_topic.equals("4")) {
				model.put("help_topic", "Others");
			}

			model.put("remarks", h.getRemarks());
			model.put("id", h.getId());
			model.put("ticket", h.getTicket());
//			model.put("username", h.getUsername());
			model.put("created_on", h.getCreated_on());
			model.put("completed_dt", h.getCompleted_dt());
			model.put("assigned_dt", h.getAssigned_dt());
			model.put("screen_shot", h.getScreen_shot());
			model.put("issue_summary", h.getIssue_summary());
			model.put("description", h.getDescription());
			model.put("section", h.getSection());
			model.put("ticket_status", h.getTicket_status());
			model.put("getSectionNameList", role.getSectionNameList());
			model.put("getSectionvalue", h.getSection());
			return new ModelAndView("user_ticketTiles");
		} else {
			HD_TB_BISAG_TICKET_GENERATE h = help.gethelpbyId(updateid);
			model.put("viewticketCMD", h);
			if (h.getModule() == -1) {
				model.put("moduleId", "others");
				model.put("moduleName", "others");
//				if(getUserNameList(0).size() > 0) {
//					model.put("agent_name", getUserNameList(0).get(0));
//				}
			} else {
				model.put("moduleId", h.getModule());
				model.put("moduleName", getModuleNameList(h.getModule()).get(0));
				// model.put("sub_module", getSubModuleNameList(h.getSub_module()).get(0));
				// model.put("screen_name", getScreenNamebyCodeList(h.getScreen_name()).get(0));
//				if(getUserNameList(h.getModule()).size() > 0) {
//					model.put("agent_name", getUserNameList(h.getModule()).get(0));
//				}
			}
			String help_topic = h.getHelp_topic();
			if (help_topic.equals("1")) {
				model.put("help_topic", "Hardware Issue");
			}
			if (help_topic.equals("2")) {
				model.put("help_topic", "Software Issue");
			}
			if (help_topic.equals("3")) {
				model.put("help_topic", "Feedback");
			}
			if (help_topic.equals("4")) {
				model.put("help_topic", "Others");
			}


			model.put("id", h.getId());
			model.put("ticket", h.getTicket());
			model.put("username", h.getUsername());
			model.put("created_on", h.getCreated_on());
			model.put("completed_dt", h.getCompleted_dt());
			model.put("assigned_dt", h.getAssigned_dt());
			model.put("screen_shot", h.getScreen_shot());///
			model.put("issue_summary", h.getIssue_summary());
			model.put("description", h.getDescription());
			model.put("ticket_status", h.getTicket_status());
			model.put("remarks", h.getRemarks());
			model.put("assigned_to", h.getAssigned_to());
			model.put("assigned_to", h.getAssigned_to());
			model.put("getSectionNameList", role.getSectionNameList());
			model.put("getSectionvalue", h.getSection());
			return new ModelAndView("viewticketTiles");
		}
	}

	@RequestMapping(value = "/editticketdetails")
	public ModelAndView editticketdetails(HttpSession sessionA, @ModelAttribute("updateid") int updateid,
			HttpServletRequest request, ModelMap model, @RequestParam(value = "msg", required = false) String msg,
			Authentication authentication) {
		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("myticket", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		model.put("msg1", msg);

		HD_TB_BISAG_TICKET_GENERATE h = help.gethelpbyId(updateid);
		model.put("viewticketCMD", h);
		if (h.getModule() == -1) {
			model.put("moduleId", "others");
			model.put("moduleName", "others");

		} else {
			model.put("moduleId", h.getModule());
			model.put("moduleName", getModuleNameList(h.getModule()).get(0));

		}
		String help_topic = h.getHelp_topic();
//			if (help_topic.equals("1")) {
//				model.put("help_topic", "Hardware Issue");
//			}
//			if (help_topic.equals("2")) {
//				model.put("help_topic", "Software Issue");
//			}
//			if (help_topic.equals("3")) {
//				model.put("help_topic", "Feedback");
//			}
//			if (help_topic.equals("4")) {
//				model.put("help_topic", "Others");
//			}
		model.put("help_topic", help_topic);
		model.put("id", h.getId());
		model.put("ticket", h.getTicket());
		model.put("username", h.getUsername());
		model.put("created_on", h.getCreated_on());
		model.put("completed_dt", h.getCompleted_dt());
		model.put("assigned_dt", h.getAssigned_dt());
		model.put("screen_shot", h.getScreen_shot());///
		model.put("issue_summary", h.getIssue_summary());
		model.put("description", h.getDescription());
		model.put("ticket_status", h.getTicket_status());
		model.put("remarks", h.getRemarks());
		model.put("assigned_to", h.getAssigned_to());
		model.put("assigned_to", h.getAssigned_to());
		model.put("getSectionNameList", role.getSectionNameList());
		model.put("getSectionvalue", h.getSection());
		return new ModelAndView("viewticketTiles");
	}
	
	@RequestMapping(value = "/finalviewdetails")
	public ModelAndView finalviewdetails(HttpSession sessionA, @ModelAttribute("viewid") int viewid,
			HttpServletRequest request, ModelMap model, @RequestParam(value = "msg", required = false) String msg,
			Authentication authentication) {
		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("myticket", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		model.put("msg1", msg);

		HD_TB_BISAG_TICKET_GENERATE h = help.gethelpbyId(viewid);
		model.put("viewticketCMD", h);
		if (h.getModule() == -1) {
			model.put("moduleId", "others");
			model.put("moduleName", "others");

		} else {
			model.put("moduleId", h.getModule());
			model.put("moduleName", getModuleNameList(h.getModule()).get(0));

		}
		String help_topic = h.getHelp_topic();
//			if (help_topic.equals("1")) {
//				model.put("help_topic", "Hardware Issue");
//			}
//			if (help_topic.equals("2")) {
//				model.put("help_topic", "Software Issue");
//			}
//			if (help_topic.equals("3")) {
//				model.put("help_topic", "Feedback");
//			}
//			if (help_topic.equals("4")) {
//				model.put("help_topic", "Others");
//			}
		model.put("help_topic", help_topic);
		model.put("id", h.getId());
		model.put("ticket", h.getTicket());
		model.put("username", h.getUsername());
		model.put("created_on", h.getCreated_on());
		model.put("completed_dt", h.getCompleted_dt());
		model.put("assigned_dt", h.getAssigned_dt());
		model.put("screen_shot", h.getScreen_shot());///
		model.put("issue_summary", h.getIssue_summary());
		model.put("description", h.getDescription());
		model.put("ticket_status", h.getTicket_status());
		model.put("remarks", h.getRemarks());
		model.put("assigned_to", h.getAssigned_to());
		model.put("assigned_to", h.getAssigned_to());
		model.put("getSectionNameList", role.getSectionNameList());
		model.put("getSectionvalue", h.getSection());
		
		model.put("getStatusNameList", h.getIt_tech_approve_status());
		return new ModelAndView("finalviewticketTiles");
	}


	@RequestMapping(value = "/approvedetails")
	public ModelAndView approvedetails(HttpSession sessionA, @ModelAttribute("approveid") int approveid,
			HttpServletRequest request, ModelMap model, @RequestParam(value = "msg", required = false) String msg,
			Authentication authentication) {
		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("myticket", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		model.put("msg1", msg);

		HD_TB_BISAG_TICKET_GENERATE h = help.gethelpbyId(approveid);
		model.put("viewticketCMD", h);
		if (h.getModule() == -1) {
			model.put("moduleId", "others");
			model.put("moduleName", "others");

		} else {
			model.put("moduleId", h.getModule());
			model.put("moduleName", getModuleNameList(h.getModule()).get(0));

		}
		String help_topic = h.getHelp_topic();
//			if (help_topic.equals("1")) {
//				model.put("help_topic", "Hardware Issue");
//			}
//			if (help_topic.equals("2")) {
//				model.put("help_topic", "Software Issue");
//			}
//			if (help_topic.equals("3")) {
//				model.put("help_topic", "Feedback");
//			}
//			if (help_topic.equals("4")) {
//				model.put("help_topic", "Others");
//			}
		model.put("help_topic", help_topic);
		model.put("id", h.getId());
		model.put("ticket", h.getTicket());
		model.put("username", h.getUsername());
		model.put("created_on", h.getCreated_on());
		model.put("completed_dt", h.getCompleted_dt());
		model.put("assigned_dt", h.getAssigned_dt());
		model.put("screen_shot", h.getScreen_shot());///
		model.put("issue_summary", h.getIssue_summary());
		model.put("description", h.getDescription());
		model.put("ticket_status", h.getTicket_status());
		model.put("remarks", h.getRemarks());
		model.put("assigned_to", h.getAssigned_to());
		model.put("assigned_to", h.getAssigned_to());
		model.put("getSectionNameList", role.getSectionNameList());
		model.put("getSectionvalue", h.getSection());
		model.put("getTypeofRoleList", dd.getTypeofRoleList());
		return new ModelAndView("viewticketassignTiles");
	}

	@RequestMapping(value = "/viewticketAction", method = RequestMethod.POST)
	public ModelAndView viewticketAction(@ModelAttribute("viewticketCMD") HD_TB_BISAG_TICKET_GENERATE N,
			BindingResult result, HttpServletRequest request, ModelMap model,
			@RequestParam(value = "filedoc", required = false) MultipartFile filedoc, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg) {
		Boolean val = roledao.ScreenRedirect("myticket", session.getAttribute("roleid").toString());

		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
			return new ModelAndView("redirect:bodyParameterNotAllow");
		}
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session1.beginTransaction();
		String fname = "";
		String userId = session.getAttribute("userId").toString();
		String hidFileName = request.getParameter("hidFileName");

		try {

			if (N.getSection().equals("0")) {
				model.put("msg", "Please Select Section");
				return new ModelAndView("redirect:help");
			}
			if (N.getHelp_topic().equals("0")) {
				model.put("msg", "Please Select Ticket Type");
				return new ModelAndView("redirect:help");
			}

			if (N.getDescription().equals("")) {
				model.put("msg", "Please Enter Description");
				return new ModelAndView("redirect:help");
			} else if (validation.checkDescriptionLengthHelpdeskLength(N.getDescription()) == false) {
				model.put("msg", validation.DescriptionLengthHelpdeskMSG);
				return new ModelAndView("redirect:help");
			}
			if (filedoc.isEmpty()) {
				fname = hidFileName;
			}
			if (!filedoc.isEmpty()) {
				String doc_path1Ext = FilenameUtils.getExtension(filedoc.getOriginalFilename()).toUpperCase();
				final long fileSizeLimit = Long.parseLong(session.getAttribute("fileSizeLimit").toString());// 5 MB
				final long defalut = 5242880;
				if (filedoc.getSize() > defalut) {
					model.put("msg", "File size should be less then 5 MB");
					return new ModelAndView("redirect:help");
				}
				if (!doc_path1Ext.equals("PDF") && !doc_path1Ext.equals("JPG") && !doc_path1Ext.equals("JPEG")
						&& !doc_path1Ext.equals("PNG")) {
					model.put("msg", "Only *.pdf,jpg,png or *.jpeg  file extension allowed");
					return new ModelAndView("redirect:help");
				}

				// code modify by Paresh on 05/05/20202
				try {
					String extension = "";
					DateWithTimeStampController timestamp = new DateWithTimeStampController();
					byte[] bytes = filedoc.getBytes();
					String helpdeskFilePath = session.getAttribute("helpdeskFilePath").toString();
					File dir = new File(helpdeskFilePath);
					if (!dir.exists()) {
						dir.mkdirs();
					}
					String filename = filedoc.getOriginalFilename();
					int i = filename.lastIndexOf('.');
					if (i >= 0) {
						extension = filename.substring(i + 1);
					}
					fname = dir.getAbsolutePath() + File.separator + timestamp.currentDateWithTimeStampString() + "_"
							+ userId + "_HELPDESK." + extension;
					if (validation.checkImageAnmlLength(fname) == false) {
						model.put("msg", validation.image_MSG);
						return new ModelAndView("redirect:help");
					}
					File serverFile = new File(fname);
//						N.setScreen_shot(fname);
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();

				} catch (Exception e) {

				}
			}

		} catch (MaxUploadSizeExceededException e) {
			model.put("msg", "Uploaded file exceeds the maximum allowed size of 5 MB.");
			return new ModelAndView("redirect:help");
		}

		catch (Exception e) {
		}

		// int ticket = getMaxticket();
		// N.setTicket(ticket);

		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();

		try {
			String hql = "update HD_TB_BISAG_TICKET_GENERATE set section=:section,help_topic=:help_topic,"
					+ "screen_shot=:screen_shot,issue_summary=:issue_summary,"
					+ "description=:description where id=:id";

			Query query = session1.createQuery(hql).setString("section", N.getSection())
					.setString("help_topic", N.getHelp_topic()).setString("screen_shot", fname)
					.setString("issue_summary", N.getIssue_summary()).setString("description", N.getDescription())
					.setInteger("id", N.getId());

			msg = query.executeUpdate() > 0 ? "update" : "Data Not Updated.";

			tx.commit();

			if (msg == "update") {
				model.put("msg", "Data Updated Successfully.");
			} else {
				model.put("msg", "Data Not Updated.");
			}

		} catch (RuntimeException e) {
			try {
				tx.rollback();
				model.put("msg", "roll back transaction");
			} catch (RuntimeException rbe) {
				model.put("msg", "Couldn't roll back transaction " + rbe);
			}
			throw e;

		} finally {
			if (session1 != null) {
				session1.close();
			}
		}
		return new ModelAndView("redirect:myticket");
	}

	@RequestMapping(value = "/help", method = RequestMethod.GET)
	public ModelAndView help(ModelMap Mmap, HttpSession sessionA,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleAccess = sessionA.getAttribute("roleAccess").toString();
		String roleSusNo = sessionA.getAttribute("roleSusNo").toString();
		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("help", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}

		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		Mmap.put("msg", msg);
		Mmap.put("getModuleNameList", getModuleNameHelpDeskList(sessionA));
		Mmap.put("getSubModuleNameList", getSubModuleList(sessionA));
		Mmap.put("getScreenList", getScreenList(sessionA));
		Mmap.put("getSectionNameList", role.getSectionNameList());
		return new ModelAndView("helpTiles");
	}

	public @ResponseBody List<TB_LDAP_MODULE_MASTER> getModuleNameHelpDeskList(HttpSession sessionA) {
		int roleid = Integer.parseInt(sessionA.getAttribute("roleid").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"from TB_LDAP_MODULE_MASTER where id in (select distinct module.id from TB_LDAP_ROLEMASTER where roleid=:roleid) order by id DESC ");
		q.setParameter("roleid", roleid);
		@SuppressWarnings("unchecked")
		List<TB_LDAP_MODULE_MASTER> list = (List<TB_LDAP_MODULE_MASTER>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	public List<TB_LDAP_SUBMODULE_MASTER> getSubModuleList(HttpSession sessionA) {
		int roleid = Integer.parseInt(sessionA.getAttribute("roleid").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"from TB_LDAP_SUBMODULE_MASTER where id in (select distinct sub_module.id from TB_LDAP_ROLEMASTER where roleid=:roleid) order by id");
		q.setParameter("roleid", roleid);
		@SuppressWarnings("unchecked")
		List<TB_LDAP_SUBMODULE_MASTER> list = (List<TB_LDAP_SUBMODULE_MASTER>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	public List<TB_LDAP_SCREEN_MASTER> getScreenList(HttpSession sessionA) {
		int roleid = Integer.parseInt(sessionA.getAttribute("roleid").toString());
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"from TB_LDAP_SCREEN_MASTER where id in (select distinct screen.id from TB_LDAP_ROLEMASTER where roleid=:roleid) order by id");
		q.setParameter("roleid", roleid);
		@SuppressWarnings("unchecked")
		List<TB_LDAP_SCREEN_MASTER> list = (List<TB_LDAP_SCREEN_MASTER>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	@RequestMapping(value = "/helpAction", method = RequestMethod.POST)
	public ModelAndView helpAction(@ModelAttribute("helpAction") HD_TB_BISAG_TICKET_GENERATE N,
			@RequestParam(value = "filedoc", required = false) MultipartFile filedoc,
			@RequestParam(value = "section", required = false) String section, HttpServletRequest request,
			ModelMap model, HttpSession session) {
		String username = session.getAttribute("username").toString();
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("help", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		String userId = session.getAttribute("userId").toString();
		try {

			if (N.getSection().equals("0")) {
				model.put("msg", "Please Select Section");
				return new ModelAndView("redirect:help");
			}
			if (N.getHelp_topic().equals("0")) {
				model.put("msg", "Please Select Ticket Type");
				return new ModelAndView("redirect:help");
			}
//			if (N.getIssue_summary().equals("")) {
//				model.put("msg", "Please Enter Issue Summary");
//				return new ModelAndView("redirect:help");
//			} else if (validation.checkIssue_summaryHelpdeskLength(N.getIssue_summary()) == false) {
//				model.put("msg", validation.Issue_summaryHelpdeskMSG);
//				return new ModelAndView("redirect:help");
//			}
			if (N.getDescription().equals("")) {
				model.put("msg", "Please Enter Description");
				return new ModelAndView("redirect:help");
			} 
			else if (validation.checkDescriptionLengthHelpdeskLength(N.getDescription()) == false) {
				model.put("msg", validation.DescriptionLengthHelpdeskMSG);
				return new ModelAndView("redirect:help");
			}
			if (!filedoc.isEmpty()) {
				String doc_path1Ext = FilenameUtils.getExtension(filedoc.getOriginalFilename()).toUpperCase();
				final long fileSizeLimit = Long.parseLong(session.getAttribute("fileSizeLimit").toString());// 5 MB
				final long defalut = 5242880;
				if (filedoc.getSize() > defalut) {
					model.put("msg", "File size should be less then 5 MB");
					return new ModelAndView("redirect:help");
				}
				if (!doc_path1Ext.equals("PDF") && !doc_path1Ext.equals("JPG") && !doc_path1Ext.equals("JPEG")
						&& !doc_path1Ext.equals("PNG")) {
					model.put("msg", "Only *.pdf,jpg,png or *.jpeg  file extension allowed");
					return new ModelAndView("redirect:help");
				}
				String fname = "";
				// code modify by Paresh on 05/05/20202
				try {
					String extension = "";
					DateWithTimeStampController timestamp = new DateWithTimeStampController();
					byte[] bytes = filedoc.getBytes();
					String helpdeskFilePath = session.getAttribute("helpdeskFilePath").toString();
					File dir = new File(helpdeskFilePath);
					if (!dir.exists()) {
						dir.mkdirs();
					}
					String filename = filedoc.getOriginalFilename();
					int i = filename.lastIndexOf('.');
					if (i >= 0) {
						extension = filename.substring(i + 1);
					}
					fname = dir.getAbsolutePath() + File.separator + timestamp.currentDateWithTimeStampString() + "_"
							+ userId + "_HELPDESK." + extension;
					if (validation.checkImageAnmlLength(fname) == false) {
						model.put("msg", validation.image_MSG);
						return new ModelAndView("redirect:help");
					}
					File serverFile = new File(fname);
					N.setScreen_shot(fname);
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();

				} catch (Exception e) {

				}
			}

		} catch (MaxUploadSizeExceededException e) {
			model.put("msg", "Uploaded file exceeds the maximum allowed size of 5 MB.");
			return new ModelAndView("redirect:help");
		}

		catch (Exception e) {
		}

		int ticket = getMaxticket();
		N.setTicket(ticket);

		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		if (roleAccess.equals("Unit")) {
			N.setSection(roleSusNo);
		}
		N.setSection(section);
		N.setCreated_by(username);
		N.setCreated_on(new Date());
		N.setTicket_status("0");
		N.setUserid(Integer.parseInt(userId));

		Session session1 = HibernateUtil.getSessionFactory().openSession();
		session1.beginTransaction();
		session1.save(N);
		session1.getTransaction().commit();
		session1.close();

		model.put("msg", "Your Ticket Is Generated Please Note Down Your Ticket " + ticket);
		return new ModelAndView("redirect:help");
	}

	@RequestMapping(value = "/assignviewticketAction", method = RequestMethod.POST)
	public ModelAndView assignviewticketAction(@ModelAttribute("assignviewticketAction") HD_TB_BISAG_TICKET_GENERATE N,
			@RequestParam(value = "assignuser", required = false) String assignuser,
			@RequestParam(value = "id", required = false) Integer id, HttpServletRequest request, ModelMap model,
			HttpSession session) {
		String username = session.getAttribute("username").toString();
		String roleid = session.getAttribute("roleid").toString();
		String userId = session.getAttribute("userId").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		
		String remarks = request.getParameter("remarks");
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session1.beginTransaction();
		try {
			String hql = "update HD_TB_BISAG_TICKET_GENERATE set assignuser=:assignuser, remarks=:remarks,assigned_dt=:assigned_dt where id=:id";
			Query query = session1.createQuery(hql).setString("assignuser", assignuser).setString("remarks", remarks).setDate("assigned_dt", new Date())
					.setInteger("id", id);
			query.executeUpdate(); // Don't forget to execute the update
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
			if (session1 != null) {
				session1.close();
			}
		}
		model.put("msg", "This Ticket is Assign To It_Tech");
		return new ModelAndView("myticketTiles");
	}
	
	
	@RequestMapping(value = "/resovedticketbyittecAction", method = RequestMethod.POST)
	public ModelAndView resovedticketbyittecAction(@ModelAttribute("assignviewticketAction") HD_TB_BISAG_TICKET_GENERATE N,
			@RequestParam(value = "id", required = false) Integer id, HttpServletRequest request, ModelMap model,
			HttpSession session) {
		String username = session.getAttribute("username").toString();
		String roleid = session.getAttribute("roleid").toString();
		String userId = session.getAttribute("userId").toString();
		String roleAccess = session.getAttribute("roleAccess").toString();
		String roleSusNo = session.getAttribute("roleSusNo").toString();
		Date completed_dt =null;
		
		
		String ticket_status = request.getParameter("status");
		String comticket_status = request.getParameter("ticket_status");
		if (ticket_status.equals("2")) {
			completed_dt = new Date();
			comticket_status= "1";
		}else {
			completed_dt =null;
			comticket_status= "0";
		}
			
		
		String remarks = request.getParameter("remarks");
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session1.beginTransaction();
		try {
			String hql = "update HD_TB_BISAG_TICKET_GENERATE set it_tech_approve_status=:it_tech_approve_status,ticket_status=:ticket_status, it_tec_remarks=:it_tec_remarks,completed_dt=:completed_dt where id=:id";
			Query query = session1.createQuery(hql).setString("it_tech_approve_status", ticket_status).setString("ticket_status", comticket_status).setString("it_tec_remarks", remarks).setDate("completed_dt", completed_dt)
					.setInteger("id", id);
			query.executeUpdate(); // Don't forget to execute the update
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
			if (session1 != null) {
				session1.close();
			}
		}
		if (ticket_status.equals("2")) {
			model.put("msg", "Ticket Resolved");
		}else {
			model.put("msg", "Ticket In Progress");
		}
		
	
		return new ModelAndView("myticketTiles");
	}
	

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getUserNameList", method = RequestMethod.POST)
	public @ResponseBody List<HD_TB_BISAG_USER_INFO> getUserNameList(int module_id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = null;
		if (module_id == 0) {
			q = session.createQuery("select distinct moduleid,agent_name from HD_TB_BISAG_USER_INFO ");
		} else {
			q = session.createQuery("select moduleid,agent_name from HD_TB_BISAG_USER_INFO WHERE moduleid=:module_id");
			q.setParameter("module_id", module_id);
		}
		List<HD_TB_BISAG_USER_INFO> list = (List<HD_TB_BISAG_USER_INFO>) q.list();
		tx.commit();
		session.close();
		return list;
	}


	public List<TB_LDAP_MODULE_MASTER> getModuleNameHelpDeskListWithAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("from TB_LDAP_MODULE_MASTER order by id DESC ");

		@SuppressWarnings("unchecked")
		List<TB_LDAP_MODULE_MASTER> list = (List<TB_LDAP_MODULE_MASTER>) q.list();
		tx.commit();
		session.close();
		return list;
	}

//	

	public Long checkExitstingUserManagementUpdate(int module, String agent_name, String id) {
		String hql = "select count(*) from HD_TB_BISAG_USER_INFO where moduleid=:moduleid and LOWER(agent_name)=:agent_name and id!=:id";
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(hql);
		q.setParameter("moduleid", module);
		q.setParameter("agent_name", agent_name.toLowerCase());
		q.setParameter("id", Integer.parseInt(id));
		Long users = (Long) q.uniqueResult();
		tx.commit();
		session.close();
		return users;
	}

	@RequestMapping(value = "/deleteUsermngURL", method = RequestMethod.POST)
	@ResponseBody
	public List<String> deleteUsermngURL(int deleteid, HttpSession sessionA) {
		List<String> liststr = new ArrayList<String>();
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			String hqlUpdate = "delete from HD_TB_BISAG_USER_INFO where id = :id";
			int app = session.createQuery(hqlUpdate).setInteger("id", deleteid).executeUpdate();
			tx.commit();
			session.close();

			if (app > 0) {
				liststr.add("Delete Successfully.");
			} else {
				liststr.add("Delete UNSuccessfully.");
			}
			return liststr;

		} catch (Exception e) {

			liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
			return liststr;
		}
	}


	@RequestMapping(value = "/faqinsert", method = RequestMethod.GET)
	public ModelAndView faqinsert(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("faqinsert", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("faqinsertTiles");
	}

	@RequestMapping(value = "/faqAction", method = RequestMethod.POST)
	public ModelAndView faqAction(@ModelAttribute("faqCMD") HD_TB_BISAG_FAQ al, HttpServletRequest request,
			ModelMap model, HttpSession session) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("faqinsert", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (al.getSection().equals("")) {
			model.put("msg", "Please enter section");
			return new ModelAndView("redirect:faqinsert");
		} else if (validation.checksectionHelpdeskLength(al.getSection()) == false) {
			model.put("msg", validation.sectionHelpdeskMSG);
			return new ModelAndView("redirect:faqinsert");
		}
		if (al.getQuestion().equals("")) {
			model.put("msg", "Please enter question");
			return new ModelAndView("redirect:faqinsert");
		} else if (validation.checkquestionHelpdeskLength(al.getQuestion()) == false) {
			model.put("msg", validation.questionHelpdeskMSG);
			return new ModelAndView("redirect:faqinsert");
		}
		if (al.getAnswer().equals("")) {
			model.put("msg", "Please enter answer");
			return new ModelAndView("redirect:faqinsert");
		}
		String username = session.getAttribute("username").toString();
		Date created_on = new Date();
		String sec = request.getParameter("section").toUpperCase();
		al.setSection(sec.toUpperCase());
		al.setCreated_by(username);
		al.setCreated_on(created_on);
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		session1.beginTransaction();
		session1.save(al);
		session1.getTransaction().commit();
		session1.close();
		model.put("msg", "Data saved Successfully");
		return new ModelAndView("redirect:faqinsert");
	}

	@RequestMapping(value = "/getsectionlist", method = RequestMethod.POST)
	public @ResponseBody List<String> getsectionlist(String section, HttpSession sessionA) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("select distinct section from HD_TB_BISAG_FAQ where upper(section) like :section")
				.setMaxResults(10);
		q.setParameter("section", section.toUpperCase() + "%");
		System.err.println(q);
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		String enckey = hex_asciiDao.getAlphaNumericString();
		Cipher c = null;
		try {
			c = hex_asciiDao.EncryptionSHA256Algo(sessionA, enckey);
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

	@RequestMapping(value = "/searchfaq", method = RequestMethod.GET)
	public ModelAndView Searchfaq(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("searchfaq", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("searchfaqTiles");
	}





	@RequestMapping(value = "/deleteFAQdetailsUrlAdd", method = RequestMethod.POST)
	@ResponseBody
	public List<String> deleteFAQdetailsUrlAdd(int deleteid, HttpSession sessionA) {
		List<String> liststr = new ArrayList<String>();
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			String hqlUpdate = "delete from HD_TB_BISAG_FAQ where id =:id";
			int app = session.createQuery(hqlUpdate).setInteger("id", deleteid).executeUpdate();
			tx.commit();
			session.close();

			if (app > 0) {
				liststr.add("Delete Successfully.");
			} else {
				liststr.add("Delete UNSuccessfully.");
			}
			return liststr;

		} catch (Exception e) {

			liststr.add("CAN NOT DELETE THIS DATA BECAUSE IT IS ALREADY IN USED.");
			return liststr;
		}
	}



	public int getMaxticket() {
		// Current Year last two digits
		String currentyear = String.format("%ty", Year.now());

		// Current Month two digits
		GregorianCalendar date = new GregorianCalendar();
		String currentmonth = StringUtils.leftPad(String.valueOf(date.get(Calendar.MONTH) + 1), 2, "0");

		String currentyearmonth = currentyear + currentmonth;

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery(
				"select cast(ticket as string) from HD_TB_BISAG_TICKET_GENERATE where cast(ticket as string) like '"
						+ currentyearmonth + "%' order by id DESC")
				.setMaxResults(1);
		int Ticket = 0;
		@SuppressWarnings("unchecked")
		List<String> list = q.list();
		if (list.size() > 0) {
			String PreviousTicket = String.valueOf(list.get(0));
			Ticket = Integer.parseInt(PreviousTicket) + 1;
		} else {
			Ticket = Integer.parseInt(currentyear + currentmonth + "0001");
		}
		tx.commit();
		session.close();
		return Ticket;
	}

	@SuppressWarnings("unchecked")
	// @RequestMapping(value = "/getModuleNameList")
	public @ResponseBody List<String> getModuleNameList(int module_hid) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("SELECT module_name FROM TB_LDAP_MODULE_MASTER WHERE id=:id");
		q.setParameter("id", module_hid);
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	@SuppressWarnings("unchecked")
	// @RequestMapping(value = "/getSubModuleNameList")
	public @ResponseBody List<String> getSubModuleNameList(int sub_module_hid) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("SELECT submodule_name FROM TB_LDAP_SUBMODULE_MASTER WHERE id=:id");
		q.setParameter("id", sub_module_hid);
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	@SuppressWarnings("unchecked")
	// @RequestMapping(value = "/getScreenNamebyCodeList")
	public @ResponseBody List<String> getScreenNamebyCodeList(int screen_name_hid) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query q = session.createQuery("SELECT screen_name FROM TB_LDAP_SCREEN_MASTER WHERE id=:id");
		q.setParameter("id", screen_name_hid);
		List<String> list = (List<String>) q.list();
		tx.commit();
		session.close();
		return list;
	}

	

	public @ResponseBody List<Map<String, Object>> getDropDownHelp(HttpSession sessionUserId, List<Object[]> list)
			throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		String enckey = hex_asciiDao.getAlphaNumericString();
		Cipher c = hex_asciiDao.EncryptionSHA256Algo(sessionUserId, enckey);
		List<Map<String, Object>> FinalList = new ArrayList<Map<String, Object>>();

		for (Object[] listObject : list) {
			String columnName = (String) listObject[1];
			String columnCode = (String) listObject[0];

			byte[] enccolumnName = c.doFinal(columnName.getBytes());
			String base64EncodedEncryptedcolumnName = new String(Base64.encodeBase64(enccolumnName));

			byte[] enccolumnCode = c.doFinal(columnCode.getBytes());
			String base64EncodedEncryptedcolumnCode = new String(Base64.encodeBase64(enccolumnCode));

			Map<String, Object> EncList = new LinkedHashMap<String, Object>();
			EncList.put("columnName", base64EncodedEncryptedcolumnName);
			EncList.put("columnCode", base64EncodedEncryptedcolumnCode);
			FinalList.add(EncList);
		}
		Map<String, Object> EncKeyList = new LinkedHashMap<String, Object>();
		String a1 = enckey + "4bsjyg==";
		if (list.size() != 0) {
			EncKeyList.put("columnName1", a1);
			EncKeyList.put("columnCode1", "gDKfjjU+/PZ6k4WWTJB1IA==");
		}
		FinalList.add(EncKeyList);
		return FinalList;
	}

	/* getUserReportList_dashbored */
	@RequestMapping(value = "/getUserReportList_dashbored", method = RequestMethod.GET)
	public @ResponseBody DatatablesResponse<Map<String, Object>> getUserReportList1(
			@DatatablesParams DatatablesCriterias criterias, HttpSession session, HttpServletRequest request) {
		String qry = "";

		String roleSubAccess = session.getAttribute("roleSubAccess").toString();
		qry += "";
		DataSet<Map<String, Object>> dataSet = roledao.DatatablesCriteriasUserreport(criterias, qry, roleSubAccess);
		return DatatablesResponse.build(dataSet, criterias);
	}

//	@RequestMapping(value = "/admin/ImageAction", method = RequestMethod.POST)
//	public @ResponseBody String ImageAction(ModelMap Mmap, HttpSession session, HttpServletRequest request,
//			MultipartHttpServletRequest mul) throws IOException, ParseException {
//		String fname = "";
//		String extension = "";
//		
//		
//		
//		
//	}
	@SuppressWarnings("null")
	@RequestMapping(value = "/ticketFileDownloadDemo", method = RequestMethod.GET)
	public void ticketFileDownloadDemo(@ModelAttribute("id") int id, ModelMap model, HttpServletRequest request,
			HttpServletResponse response, HttpSession sessionA, Authentication authentication)
			throws IOException, SQLException {
		Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = sessionHQL.beginTransaction();
		HD_TB_BISAG_TICKET_GENERATE u_file_path = (HD_TB_BISAG_TICKET_GENERATE) sessionHQL
				.get(HD_TB_BISAG_TICKET_GENERATE.class, id);
		String filePath = u_file_path.getScreen_shot();
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

	@RequestMapping(value = "/acceptdetails")
	public ModelAndView acceptdetails(HttpSession sessionA, @ModelAttribute("acceptid") int acceptid,
			@ModelAttribute("label3") String label3, HttpServletRequest request, ModelMap model,
			@RequestParam(value = "msg", required = false) String msg, Authentication authentication) {
		String roleid = sessionA.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("myticket", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		model.put("msg1", msg);
		Session session1 = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session1.beginTransaction();
		String hql = "update HD_TB_BISAG_TICKET_GENERATE set it_tech_approve_status=:it_tech_approve_status where id=:id";
		Query query = session1.createQuery(hql).setString("it_tech_approve_status", "0").setInteger("id", acceptid);
		int rowCount = query.executeUpdate();
		tx.commit();

		HD_TB_BISAG_TICKET_GENERATE h = help.gethelpbyId(acceptid);
		model.put("viewticketCMD", h);

		model.put("help_topic", h.getHelp_topic());
		model.put("id", h.getId());
		model.put("ticket", h.getTicket());
		model.put("username", h.getUsername());
		model.put("created_on", h.getCreated_on());
		model.put("completed_dt", h.getCompleted_dt());
		model.put("assigned_dt", h.getAssigned_dt());
		model.put("screen_shot", h.getScreen_shot());///
		model.put("issue_summary", h.getIssue_summary());
		model.put("description", h.getDescription());
		model.put("ticket_status", h.getTicket_status());
		model.put("remarks", h.getRemarks());
		model.put("assigned_to", h.getAssigned_to());
		model.put("assigned_to", h.getAssigned_to());
		model.put("getSectionNameList", role.getSectionNameList());
		model.put("getSectionvalue", h.getSection());

		return new ModelAndView("techapproveTiles");
	}
}