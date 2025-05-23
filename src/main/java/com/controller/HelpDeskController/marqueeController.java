package com.controller.HelpDeskController;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.controller.validation.ValidationController;
import com.dao.helpDesk.HelpDAO;
import com.dao.login.RoleBaseMenuDAO;
import com.models.Helpdesk.HD_MARQUEE;
import com.persistance.util.HibernateUtil;
import com.persistance.util.HibernateUtilNA;

@SuppressWarnings("unused")

@Controller
@RequestMapping(value = { "admin", "/", "user" })
public class marqueeController {

	@Autowired
	private RoleBaseMenuDAO roledao;

	@Autowired
	private HelpDAO helpDao;

	ValidationController validation = new ValidationController();

	@RequestMapping(value = "/marquee", method = RequestMethod.GET)
	public ModelAndView marquee(ModelMap Mmap, HttpSession session,
			@RequestParam(value = "msg", required = false) String msg, HttpServletRequest request) {
		String roleid = session.getAttribute("roleid").toString();
		Boolean val = roledao.ScreenRedirect("marquee", roleid);
		if (val == false) {
			return new ModelAndView("AccessTiles");
		}
		if (request.getHeader("Referer") == null) {
			msg = "";
		}
		Mmap.put("list", helpDao.getSearchMercuryList1("", ""));
		Mmap.put("msg", msg);
		return new ModelAndView("marqueeTiles");
	}

	@RequestMapping(value = "/marcuriesAction", method = RequestMethod.POST)
	public ModelAndView marcuriesAction(@ModelAttribute("marcuriescmd") HD_MARQUEE user,
			HttpServletRequest request, ModelMap model, HttpSession session) {

		String username = session.getAttribute("username").toString();
		String msg1 = request.getParameter("test_msg");
		String msg2 = request.getParameter("msg_old_name");
		user.setMsg(msg1);
	 	
		if (user.getValid_upto().equals(null) & user.getValid_upto() == null) {
			model.put("msg", "Please Enter Date.");
			return new ModelAndView("redirect:marcuriesurl");
		}
		Boolean val = true;
		if (msg2 == "") {
			if (user.getMsg().equals("")) {
				model.put("msg", "Please Insert marquee text");
				return new ModelAndView("redirect:marquee");
			} else if (validation.checkmsgMarqueeLength(user.getMsg()) == false) {
				model.put("msg", validation.msgMarqueeMSG);
				return new ModelAndView("redirect:marquee");
			}
			if (user.getValid_upto() == null) {
				model.put("msg", "Please select date");
				return new ModelAndView("redirect:marquee");
			}
			val = helpDao.getmsgExist(msg1, user.getValid_upto());
			user.setValid_upto(user.getValid_upto());
			user.setStatus("1");
			user.setCreated_by(username);
			user.setCreated_on(new Date());
			Session sessionHQL = HibernateUtil.getSessionFactory().openSession();
			sessionHQL.beginTransaction();
			int did = (Integer) sessionHQL.save(user);
			sessionHQL.getTransaction().commit();
			sessionHQL.close();
			if (did > 0) {
				model.put("msg", "Data saved Successfully");
			}
		} else {
			int id = Integer.parseInt(request.getParameter("id_hid"));
			Session session1 = HibernateUtil.getSessionFactory().openSession();
			Transaction tx = session1.beginTransaction();
			String hql = "UPDATE HD_MARQUEE SET msg=:msg1, valid_upto=:valid_upto WHERE id=:id";
			Query query = session1.createQuery(hql).setString("msg1", msg1).setDate("valid_upto", user.getValid_upto())
					.setInteger("id", id);
			int rowCount = query.executeUpdate();
			tx.commit();
			session1.close();
			if (rowCount > 0) {
				model.put("msg", "Updated Successfully");
			} else {
				model.put("msg", "Updated Not Successfully");
			}
		}
		List<Map<String, Object>> list = helpDao.getSearchMercuryList1("", "");
		model.put("list", list);
		model.put("list.size", list.size());
		return new ModelAndView("redirect:marquee");
	}
}