package com.controller.Dashboard;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.controller.login.RoleController;
import com.dao.helpDesk.HelpDAO;
import com.dao.login.RoleBaseMenuDAO;

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class HelpDashboardController {

	RoleController roleCnt = new RoleController();

	@Autowired
	HelpDAO helpDash;

	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	private SessionRegistry sessionRegistry;

	public List<String> getUsersFromSessionRegistry() {
		String userString = sessionRegistry.getAllPrincipals().stream()
				.filter(u -> !sessionRegistry.getAllSessions(u, false).isEmpty()).map(Object::toString)
				.collect(Collectors.toList()).toString();

		List<String> allMatches = new ArrayList<String>();
		Matcher m = Pattern.compile("Username\\:.*?\\;").matcher(userString);
		while (m.find()) {
			allMatches.add(m.group().replace("Username: ", "").replace(";", ""));
		}
		return allMatches;
	}

	@RequestMapping(value = "/admin/commonDashboard", method = RequestMethod.GET)
	public ModelAndView AllDashboard(ModelMap Mmap,HttpSession session,HttpServletRequest request) {
		return new ModelAndView("commanDashboardTiles");
	}
	
	@RequestMapping(value = "/TicketStatus", method = RequestMethod.GET)
	public ModelAndView TicketStatus(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("helpdesk", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
		return new ModelAndView("TicketStatusTiles");
	}

	@RequestMapping(value = "/admin/helpdesk", method = RequestMethod.GET)
	public ModelAndView helpdesk(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("helpdesk", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		Mmap.put("msg", msg);
	
		Mmap.put("getCompletedTicketCount", helpDash.getCompletedTicketCount());
		Mmap.put("getcreateTicketCount", helpDash.getcreateTicketCount());
		Mmap.put("getpendingTicketCount", helpDash.getpendingTicketCount());
		Mmap.put("getassignTicketCount", helpDash.getassignTicketCount());
		Mmap.put("getinprogressTicketCount", helpDash.getinprogressTicketCount());
		return new ModelAndView("HelpDashTiles");
	}

	

	

	@RequestMapping(value = "/RoleUrl", method = RequestMethod.GET)
	public ModelAndView RoleUrl(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg,HttpServletRequest request) {
		if(request.getHeader("Referer") == null ) {
			msg = "";
		}
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("helpdesk", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		} else {
			List<Map<String, Object>> list = roledao.RoleSearchReport();
			Mmap.put("list", list);
			Mmap.put("getRoleType", roleCnt.getRoleType());
			Mmap.put("msg", msg);
			return new ModelAndView("RoleUrlTiles");
		}
	}


	



	

	
}