package com.dxc.reports.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dxc.reports.model.Login;
import com.dxc.reports.service.LoginService;
import com.dxc.reports.util.ReadProperties;

@Controller
public class LoginController {

	private static String message;

	@Autowired
	private LoginService loginservice;

	@Autowired
	private ReadProperties readProperties;

	// private Model model;
	private final Logger logger = LoggerFactory.getLogger(LoginController.class);

	private List<String> userRoles = new ArrayList<String>();

	@RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
	public String loginProcess(@ModelAttribute("loginInput") Login login, HttpServletRequest request) {
		// this.model = model;
		//
		//
		logger.debug("showAllClients()");
		// this.login=login;

		// this.model.addAttribute("login", new Login());

		try {
			// request.getSession().setAttribute("cart",value);
			userRoles.clear();
			userRoles = loginservice.validateUser(login);
			HttpSession session = request.getSession();
			login.setRole("No Role");
			session.setAttribute("login", login);
			/*
			 * this.login.setUsername(login.getUsername());
			 * this.login.setPassword(login.getPassword());
			 */
			// this.login.setSessionStatus("active");
			message = "";
			/*
			 * if(userRoles.size()==1) { this.login.setRole(userRoles.get(0));
			 * return "redirect:frontpage"; }
			 */
			return "redirect:roles";
		} catch (Exception e) {
			message = readProperties.getMessage("login", "UNAUTHORIZED_ACCESS");
			return "redirect:login";
		}

	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, HttpServletRequest request) {
		// this.model = model;
		logger.debug("showAllClients()");
		HttpSession session = request.getSession();
		Login login = (Login) session.getAttribute("login");
		model.addAttribute("login", new Login());

		/*
		 * if(login.getRole()!=null && login.getUsername() != null) { return
		 * "redirect:frontpage"; }
		 */

		if (login != null) {

			if (login.getRole() != null)
				return "redirect:roles";
			else
				return "clients/login";
		}
		model.addAttribute("errorMessage", message);
		return "clients/login";

	}

	@RequestMapping(value = "/roles", method = RequestMethod.GET)

	public String selectRoles(Model model, HttpServletRequest request) {
		// this.model = model;
		//
		//
		logger.debug("selectRoles()");
		// this.login=login;

		// this.model.addAttribute("login", new Login());

		try {
			// request.getSession().setAttribute("cart",value);

			// this.login.setSessionStatus("active");
			message = "";
			model.addAttribute("roles", userRoles);
			HttpSession session = request.getSession();

			model.addAttribute("login", session.getAttribute("login"));
			return "clients/roles";
		} catch (Exception e) {
			message = readProperties.getMessage("login", "UNAUTHORIZED_ACCESS");
			return "redirect:login";
		}

	}

	@RequestMapping(value = { "/sessionexpired", "/webcontent/sessionexpired" }, method = RequestMethod.GET)
	public String sessionExpired(Model model) {
		// this.model = model;
		logger.debug("sessionExpired()");
		model.addAttribute("session", readProperties.getMessage("login", "SESSION_EXPIRED"));
		model.addAttribute("loginRedirect", readProperties.getMessage("login", "LOGIN_AGAIN"));
		// this.model.addAttribute("user", new User());
		return "components/sessionexpired";

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Model model, HttpServletRequest request) {
		// this.model = model;
		logger.debug("logout()");
		HttpSession session = request.getSession();

		session.removeAttribute("login");
		String message = readProperties.getMessage("login", "LOGOUT");
		model.addAttribute("message", message);

		// this.login.setSessionStatus(null);
		// this.model.addAttribute("login", login);
		return "clients/logout";
	}

}
