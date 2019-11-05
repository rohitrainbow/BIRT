package com.dxc.reports.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dxc.reports.model.Login;
import com.dxc.reports.service.ApplicationService;
import com.dxc.reports.service.LoginService;
import com.dxc.reports.util.CipherHelper;
import com.dxc.reports.util.ReadProperties;

@Controller
public class ApplicationController {

	@Autowired
	public LoginService userService;

	@Autowired
	private CipherHelper cipherHelper;

	@Autowired
	private ReadProperties readProperties;

	private Model model;

	private final Logger logger = LoggerFactory.getLogger(ApplicationController.class);

	private ApplicationService applicationService;

	@Autowired
	public void setClientService(ApplicationService applicationService) {
		this.applicationService = applicationService;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
		logger.debug("index()");
		return "redirect:frontpage";
	}

	/*
	 * @RequestMapping(value = "/login", method = RequestMethod.GET) public
	 * ModelAndView showLogin(HttpServletRequest request, HttpServletResponse
	 * response) { ModelAndView mav = new ModelAndView("login");
	 * mav.addObject("login", new Login()); return mav; }
	 * 
	 * @RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
	 * public ModelAndView loginProcess(HttpServletRequest request,
	 * HttpServletResponse response,
	 * 
	 * @ModelAttribute("login") Login login) { ModelAndView mav = null; String
	 * status = userService.validateUser(login); if (null != status) { mav = new
	 * ModelAndView("frontpage"); // mav.addObject("firstname",
	 * user.getFirstname()); } else { mav = new ModelAndView("login");
	 * mav.addObject("message", "Username or Password is wrong!!"); } return
	 * mav; }
	 */

	// list page
	@RequestMapping(value = "/frontpage", method = RequestMethod.GET)
	public String showAllClients(Model model, HttpServletRequest request) {
		this.model = model;
		logger.debug("showAllClients()");
		HttpSession session = request.getSession();
		Login login=(Login) session.getAttribute("login");
		if(login==null)
			return "redirect:login";
		this.model.addAttribute("clients",
				applicationService.findAllClients((login.getRole())));
		this.model.addAttribute("login", session.getAttribute("login"));

		return "clients/frontpage";

	}

	@RequestMapping(value = "/webcontent/frontpage", method = RequestMethod.GET)
	public String redirectFrontpage(Model model) {
		return "redirect:../frontpage";

	}

	@RequestMapping(value = "/roleSet", method = RequestMethod.POST)
	public @ResponseBody String roleSet(@RequestParam("name") String roleSelected, HttpServletRequest request)
			throws Exception {
		HttpSession session = request.getSession();
		((Login) session.getAttribute("login")).setRole(roleSelected);
		return "redirect:frontpage";
	}

	@RequestMapping(value = "/repList", method = RequestMethod.POST)
	public @ResponseBody String generateReportList(@RequestParam("name") String clientName, HttpServletRequest request)
			throws Exception {

		String response = "<tr><td>Session Expired</td></tr><tr><td><a href=\"/BPS_BIRT_Reports/login\">Login Again</a></td></tr>";

		logger.debug("generateReportList()");
		HttpSession session = request.getSession();
		Login login = (Login) session.getAttribute("login");
		if (login.getUsername() != null) {

			response = applicationService.findAllReports(clientName, login);
		}

		return response;
	}

	@RequestMapping(value = "/showSchedules", method = RequestMethod.GET)
	public String showSchedules(Model model, HttpServletRequest request) {
		this.model = model;
		HttpSession session = request.getSession();
		Login login = (Login) session.getAttribute("login");
		this.model.addAttribute("login", login);
		try {
			this.model.addAttribute("encryptedUserName", cipherHelper.cipher(null, login.getUsername()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Issue in showSchedules", e);
		}

		logger.info("in showSchedules--->");
		String reportName = request.getParameter("report_name");
		String clientName = request.getParameter("client_name");
		model.addAttribute("client", clientName);
		model.addAttribute("reportName", reportName);
		model.addAttribute("schedules", applicationService.fetchSchedules(clientName, reportName));

		return "clients/showSchedules";
	}

	@RequestMapping(value = "/session", method = RequestMethod.GET)
	public @ResponseBody String checkSession(Model model, HttpServletRequest request) {
		String response = "inactive";
		Map<String, String> historyGroups = new HashMap<String, String>();
		for (String group : readProperties.getLdapGroups("onlyHistoryGroups")) {
			historyGroups.put(group, group);
		}
		HttpSession session = request.getSession();
		Login login = (Login) session.getAttribute("login");
		if (login != null) {
			if (historyGroups.get(login.getRole()) == null
					&& !(applicationService.findAllClients(login.getRole()).isEmpty())) {
				response = "active";
			}
		}
		return response;
	}

	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ModelAndView handleEmptyData(HttpServletRequest req, Exception ex) {

		logger.debug("handleEmptyData()");
		logger.error("Request: {}, error ", req.getRequestURL(), ex);

		ModelAndView model = new ModelAndView();
		return model;

	}
}
