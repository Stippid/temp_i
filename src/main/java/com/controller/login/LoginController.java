package com.controller.login;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dao.login.RoleBaseMenuDAO;

@Controller
public class LoginController {

	@Autowired
	RoleBaseMenuDAO roleBaseDAO;

	@RequestMapping(value = "/admin/adminHome", method = RequestMethod.GET)
	public ModelAndView adminPage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("adminHomePage");
		return model;
	}

	@RequestMapping(value = "/user/userDashboard")
	public ModelAndView userPage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("userHomePage");
		return model;
	}

	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "msg", required = false) String msg,
			@RequestParam(value = "logout", required = false) String logout, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {

	
		ModelAndView model = new ModelAndView();
		
		if (error != null) {
			
			model.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
		}
		if (logout != null) {
	
			if (request.getHeader("Referer") != null) {
				model.addObject("msg", "You are logged out successfully.");
			}
		}
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String Role = "";
		if (!authentication.getName().equals("anonymousUser")) {
			
			Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
			String role1 = "";
			for (String role : roles) {
				role1 = role;
			}
			
			Role = role1;
		}
		if (!Role.equals("")) {
			return new ModelAndView("redirect:/admin/commonDashboard");
		} else {
			String layout = "";
			List<String> msgLayout = roleBaseDAO.getLayoutlist();
			layout += "<h3>";
			for (int m = 0; m < msgLayout.size(); m++) {
				if (m == 0) {
					layout += msgLayout.get(m);
				} else {
					layout += " | " + msgLayout.get(m);
				}
			}
			layout += "</h3>";
			model.addObject("layout", layout);
			model.addObject("visiter_count", roleBaseDAO.VisitorCounter());
			model.addObject("server",getServerIP());
			model.setViewName("login");
			model.addObject("capchaCode",capchaCode(request));
		}
		return model;
	}

	
	// customize the error message
	public static String getErrorMessage(HttpServletRequest request, String key) {
		Exception exception = (Exception) request.getSession().getAttribute(key);
		String error = "";
		if (exception instanceof BadCredentialsException) {
			error = "Invalid username and password!";
		} else if (exception instanceof LockedException) {
			error = exception.getMessage();
		} else if (exception instanceof SessionAuthenticationException) {
			error = "User Already logged in";// exception.getMessage();
		} else {
			error = "Invalid username and password!";
		}
		return error;
	}

	// for 403 access denied page
	@RequestMapping(value = "/user/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {
		ModelAndView model = new ModelAndView();
		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			model.addObject("username", userDetail.getUsername());
		}
		model.setViewName("403");
		return model;
	}

	// Create Capcha Code
	@RequestMapping(value = "/capchaCode", method = RequestMethod.POST)
	public @ResponseBody List<String> capchaCode(HttpServletRequest request) {
		List<String> capchaList = new ArrayList<String>();
		capchaList.add(getRandomInteger(10, 1));
		capchaList.add(getRandomInteger(10, 1));
		capchaList.add(getRandomInteger(10, 1));
		capchaList.add(getRandomInteger(10, 1));
		capchaList.add(getRandomCharacter());

		String capcha = "";
		for (int i = 0; i < capchaList.size(); i++) {
			capcha += capchaList.get(i);
		}
		HttpSession session = request.getSession();
		session.setAttribute("capcha", capcha);
		return capchaList;
	}

	public static String getRandomInteger(int maximum, int minimum) {
		return String.valueOf(((int) (Math.random() * (maximum - minimum))) + minimum);
	}

	public static String getRandomCharacter() {
		String AlphaNumericString = "ABCDEFGHIJKMNOPQRSTUVWXYZabcdefghjklmnopqrstuvxyz";
		int index = (int) (AlphaNumericString.length() * Math.random());
		return String.valueOf(AlphaNumericString.charAt(index));
	}
	
	@RequestMapping(value = "/JnlpDashboard", method = RequestMethod.GET)
	public ModelAndView dashboard(ModelMap Mmap,HttpSession session,HttpServletRequest request) {
		return new ModelAndView("JnlpDashboardTiles");
	}
	
	public String getServerIP() {
		try(final  DatagramSocket s = new DatagramSocket()){
			try {
				s.connect(InetAddress.getByName("8.8.8.8"),10002);
				String hadd = s.getLocalAddress().getHostAddress();
			
				if(hadd.equals("152.1.13.51")) {
					return "Server 1";
				}else if(hadd.equals("152.1.13.52")) {
					return "Server 2";
				}else if(hadd.equals("152.1.13.53")) {
					return "Server 3";
				}else {
					return "Unknown Server";
				}
			} catch (UnknownHostException e) {
				return "Unknown Server";
			}
		} catch (SocketException e1) {
			return "Unknown Server";
		}
	}
}