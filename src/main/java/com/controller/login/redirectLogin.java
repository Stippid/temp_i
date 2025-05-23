package com.controller.login;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.dao.login.UserLoginDAO;
import com.dao.login.UserLoginDAOImpl;
import com.models.Role;
import com.models.TB_LDAP_MODULE_MASTER;
import com.models.TB_LDAP_SCREEN_MASTER;
import com.models.TB_LDAP_SUBMODULE_MASTER;
import com.models.UserLogin;
import com.models.Helpdesk.HD_TB_BISAG_USER_LOGIN_COUNT_INFO;
import com.persistance.util.HibernateUtil;

public class redirectLogin extends SavedRequestAwareAuthenticationSuccessHandler{
	
	private UserLoginDAO userLoginDAO = new UserLoginDAOImpl();
	
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws ServletException, IOException {
		
		String txtInput = request.getParameter("txtInput").replaceAll("\\s", "").toString();
		String capcha =  request.getSession().getAttribute("capcha").toString();
		if(!txtInput.equals(capcha)){
			response.sendRedirect("/login");
		}else{
			
			Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
		
			String role1 = null;
			for(String role:roles)
			{
				request.getSession().setAttribute("role", role);
				role1 = role;
			}
			
			
			int userId = userLoginDAO.getUserId(authentication.getName());
			Role roleList = userLoginDAO.findRole_url(role1);
			
			request.getSession().setAttribute("userId_for_jnlp", userId);
			request.getSession().setAttribute("username", authentication.getName());
			
			String RoleUrl =  "";
			if(roleList.getRole_url() != null) {
				RoleUrl =  roleList.getRole_url();
			}
			String RoleType = "";		
			if(roleList.getRole_type() != null) {
				RoleType =  roleList.getRole_type();		
			}
			String Acces_lvl = "";
			if(roleList.getAccess_lvl() != null) {
				Acces_lvl = roleList.getAccess_lvl();
			}
			String subAcces_lvl = "";
			if(roleList.getSub_access_lvl() != null) {
				subAcces_lvl = roleList.getSub_access_lvl();
			}
			
			
			String staff_lvl = "";
			if(roleList.getStaff_lvl() != null) {
				staff_lvl = roleList.getStaff_lvl();
			}
			
			request.getSession().setAttribute("roleUrl", RoleUrl);
			request.getSession().setAttribute("roleType", RoleType);
			request.getSession().setAttribute("roleAccess", Acces_lvl);		
			request.getSession().setAttribute("roleSubAccess", subAcces_lvl);
			request.getSession().setAttribute("roleStaff_lvl", staff_lvl);
			
			int roleid =  roleList.getRoleId(); 
			
			UserLogin  addData = userLoginDAO.findByRoleId(userId);
			request.getSession().setAttribute("army_no", addData.getArmy_no());
			if(roleid !=0) {
				request.getSession().setAttribute("roleid", roleid);
			}
			request.getSession().setAttribute("successValue", "Fail");
			// New
			////////////////////
			//UserLogin  addData = userLoginDAO.findByRoleId(userId);
			String sus_no_role = "";
			if(addData.getUser_sus_no() != null) {
				sus_no_role = addData.getUser_sus_no();
			}
			
			String login_name = "";
			if(addData.getLogin_name() != null) {
				login_name = addData.getLogin_name();
			}
			
			request.getSession().setAttribute("userId", userId);
			request.getSession().setAttribute("username", addData.getUserName());
			request.getSession().setAttribute("army_no", addData.getArmy_no());
			if(roleid !=0) {
				request.getSession().setAttribute("roleid", roleid);
			}
			request.getSession().setAttribute("roleSusNo", sus_no_role);
			request.getSession().setAttribute("roleloginName", login_name);
			//////////////////
			
		
			String ip = getClientIp(request);
			request.getSession().setAttribute("ip", ip);
			String userAgent = request.getHeader("User-Agent");
		    request.getSession().setAttribute("user_agentWithIp", userAgent+"_"+ip);
			request.getSession().setAttribute("user_agent", userAgent);
			
			//request.getSession().setAttribute("otpKey", "commonPwdEncKeys");
			request.getSession().setAttribute("KeySpec", "dc0da04af8fee58593442bf834b30739");
			
			final long fileSizeLimit = 5 * 1024 * 1024; 
			request.getSession().setAttribute("fileSizeLimit", fileSizeLimit);
			
			String curDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
			request.getSession().setAttribute("curDate", curDate);
			
			request.getSession().setAttribute("regScript", "^[a-zA-Z0-9\\\\[\\\\] \\\\+ \\\\* \\\\-.,/ ~!@#$^&%_]+$");
			
			request.getSession().setAttribute("helpdeskFilePath", "/srv"+ File.separator + "HELP");
			request.getSession().setAttribute("handingTakingOverPath", "/srv"+ File.separator + "handingTakingOver");
			request.getSession().setAttribute("autismFilePath", "/srv"+ File.separator + "AUTISM");
			
			String[] medicalUnitPrifix = {"9609","9709","3742","6323","3755","3738","3735","3747","1234"};
			
			request.getSession().setAttribute("medicalUnitPrifix",medicalUnitPrifix);
			
			Boolean medical = false;
			if(Acces_lvl.equals("Unit")) {
				String sus_no = addData.getUser_sus_no().substring(0, 4);
				if(Arrays.asList(medicalUnitPrifix).contains(sus_no)) {
					medical = true;
				}
			}
			List<TB_LDAP_MODULE_MASTER> module = userLoginDAO.getModulelist(roleid,medical);
			String menu = "";
			 
			if(!RoleUrl.equals("")) {
			
					 menu="<li><a href='"+RoleUrl+"' class='btn btn-danger btn-sm'>Dashboard</a></li>";
				
			}
			
			
			for(int mod=0;mod<module.size() ;mod++) {
		
				 menu += "<li class='nav-item dropdown dropdown-item show' id='"+module.get(mod).getModule_name() +"_menu'>";	
					menu += "<a class='nav-link dropdown-toggle' href='#' id='Dropdown_"+module.get(mod).getModule_name()+"' role='button' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'>"+module.get(mod).getModule_name()+"</a>";
					menu += "<ul class='dropdown-menu show' aria-labelledby='Dropdown_"+module.get(mod).getModule_name()+"' >";
						List<TB_LDAP_SUBMODULE_MASTER> submodule = userLoginDAO.getSubModulelist(module.get(mod).getId(),roleid);
						for(int submod=0;submod<submodule.size();submod++){
							menu += "<li class='dropdown-item dropdown create_search' >";
							menu += "<a class='dropdown-toggle submodule-link'  id='Dropdown_" + submodule.get(submod).getId() + "' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false' >" + submodule.get(submod).getSubmodule_name() + "</a>"; 
							menu += "<ul class='dropdown-menu scrollbar h100' aria-labelledby='Dropdown_" + submodule.get(submod).getId() + "' id='Dropdown_" + submodule.get(submod).getId() + "'>";
							List<TB_LDAP_SCREEN_MASTER> screen = userLoginDAO.getScreenlist(module.get(mod).getId(), submodule.get(submod).getId(), roleid);
							for (int scr = 0; scr < screen.size(); scr++) {
							    menu += "<li class='dropdown-item'><a href='" + screen.get(scr).getScreen_url() + "' class='screen-link'> " + screen.get(scr).getScreen_name() + "</a></li>";
							}
							menu += "</ul>";
							
							menu += "</li>";
						}
					menu += "</ul>";
				menu += "</li>";
			}
			request.getSession().setAttribute("menu", menu);
			
			// User Login Status
			HD_TB_BISAG_USER_LOGIN_COUNT_INFO N = new HD_TB_BISAG_USER_LOGIN_COUNT_INFO();
			N.setDate(new Date());
			N.setLoginstatus("Active");
			N.setStatus("1");
			N.setUserid(userId);
			
			Session session1 = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session1.beginTransaction();
			try {
				session1.save(N);
				tx.commit();
			}catch (Exception e) {
				
				tx.rollback();
			}finally {
				session1.close();
			}
			response.sendRedirect("../admin/helpdesk");
		}
	}	
	
	private static String getClientIp(HttpServletRequest request) {
        String remoteAddr = "";
        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }
        return remoteAddr;
    }
}
