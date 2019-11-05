package com.dxc.reports.service;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.reports.dao.ApplicationDao;
import com.dxc.reports.model.Client;
import com.dxc.reports.model.Login;
import com.dxc.reports.model.Report;
import com.dxc.reports.util.CipherHelper;
import com.dxc.reports.util.ReadProperties;

@Service("clientService")
public class ApplicationServiceImpl implements ApplicationService {

	final Logger logger = LoggerFactory.getLogger(ApplicationServiceImpl.class);

	ApplicationDao applicationDao;

	private int repNumber;

	@Autowired
	private CipherHelper cipherHelper;

	@Autowired
	public void setClientDao(ApplicationDao applicationDao) {
		this.applicationDao = applicationDao;
	}

	@Autowired
	private ReadProperties readProperties;

	@Override
	public List<Client> findAllClients(String role) {
		List<Client> clients = null;
		try {
			clients = applicationDao.findAllClients(role);
		} catch (Exception e) {
			logger.error("issue with findAllClients service", e);
		}
		return clients;
	}

	@Override
	public String findAllReports(String clientName, Login login) {
		Double top = 0.0;
		Double left = 0.0;
		repNumber = 0;
		List<Report> reports = null;
		Map<String, String> historyGroups = new HashMap<String, String>();
		for (String group : readProperties.getLdapGroups("onlyHistoryGroups")) {
			historyGroups.put(group, group);
		}
		String response = "";

		try {
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			Double screenHeight = screenSize.getHeight();
			Double screenWidth = screenSize.getWidth();

			top = screenHeight - 600;
			if (top > 0)
				top = top / 2;
			else
				top = 0.0;
			left = screenWidth - 700;
			if (left > 0)
				left = left / 2;
			else
				left = 0.0;
			final Double leftValue = left;
			final Double topValue = top;
			reports = applicationDao.findAllReports(clientName);

			String user = cipherHelper.cipher(null, login.getUsername());
			StringBuffer temp = new StringBuffer(
					"<tr><th>S.No.</th><th>Report Names</th><th>Next Schedule</th>" + "<th>View History</th></tr>");
			String[] linkValue = { "" };

			if (reports != null) {
				if (historyGroups.get(login.getRole()) == null) {
					reports.forEach(name -> {
						if (applicationDao.fetchSchedules(clientName, name.getRepNames())
								.equalsIgnoreCase("Not Scheduled")) {
							linkValue[0] = "Not Scheduled";
						} else {
							linkValue[0] = "<a href='#' onclick='javascript:window.open(\"showSchedules?report_name="
									+ name.getRepNames() + "&client_name=" + clientName
									+ "\", \"_blank\", \"scrollbars=1,resizable=1,height=600,width=900,top="
									+ topValue.intValue() + ",left=" + leftValue.intValue()
									+ "\");' title='Show Schedules'" + ">View Schedules</a>";
						}

						temp.append("<tr><td>" + (++repNumber) + "</td><td><a href=\"frameset?__report="
								+ name.getRepNames() + ".rptdesign&DBPARAM=" + clientName + "&login=" + user + "\">"
								+ name.getRepNames() + "</a></td><td style=\"word-break:break-word;\">" + linkValue[0]
								+ "</td>" + "<td><a href=" + "/BPS_BIRT_Reports/showHistory?report_name="
								+ name.getRepNames() + "&client_name=" + clientName
								+ "&page_number=1&recordsPerPage=10>View History</a></td></tr>");
					});
				} else {
					reports.forEach(name -> {
						if (applicationDao.fetchSchedules(clientName, name.getRepNames())
								.equalsIgnoreCase("Not Scheduled")) {
							linkValue[0] = "Not Scheduled";
						} else {
							linkValue[0] = "<a href='#' onclick='javascript:window.open(\"showSchedules?report_name="
									+ name.getRepNames() + "&client_name=" + clientName
									+ "\", \"_blank\", \"scrollbars=1,resizable=1,height=600,width=900,top="
									+ topValue.intValue() + ",left=" + leftValue.intValue()
									+ "\");' title='Show Schedules'" + ">View Schedules</a>";
						}
						temp.append("<tr><td>" + (++repNumber) + "</td><td>" + name.getRepNames()
								+ "</td><td style=\"word-break:break-word;\">" + linkValue[0] + "</td>" + "<td><a href="
								+ "/BPS_BIRT_Reports/showHistory?report_name=" + name.getRepNames() + "&client_name="
								+ clientName + "&page_number=1&recordsPerPage=10>View History</a></td></tr>");
					});
				}
			} else
				temp.append("<tr><td colspan=\"4\">No Reports Available</td></tr>");
			response = temp.toString();
		} catch (Exception e) {
			logger.error("issue with findAllClients service", e);
		}
		return response;
	}

	@Override
	public String fetchSchedules(String client, String reportName) {
		// TODO Auto-generated method stub
		String schedule = "";
		try {
			schedule = applicationDao.fetchSchedules(client, reportName);
		} catch (Exception e) {
			logger.error("issue with fetchSchedules service", e);
		}
		return schedule;
	}
}
