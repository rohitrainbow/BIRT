package com.dxc.reports.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dxc.reports.model.Client;
import com.dxc.reports.model.Report;
import com.dxc.reports.util.ReadProperties;

@Repository
public class ApplicationDaoImpl implements ApplicationDao {

	final Logger logger = LoggerFactory.getLogger(ApplicationDaoImpl.class);

	@Autowired
	private ReadProperties readProperties;

	@Override
	public List<Client> findAllClients(String role) {
		List<String> clientNameList = readProperties.getClientName(role);

		List<Client> clientList = new ArrayList<Client>();
		try {
			if (!(clientNameList == null) && !clientNameList.isEmpty()) {
				Collections.sort(clientNameList);

				clientNameList.forEach(names -> clientList.add(new Client(names)));
			}
		} catch (Exception e) {
			logger.error("issue in findAllClients", e);
		}
		return clientList;

	}

	@Override
	public List<Report> findAllReports(String clientName) {
		List<String> repNameList = readProperties.reportNameInitializer(clientName);
		if (!(repNameList == null) && !repNameList.isEmpty())
			Collections.sort(repNameList);
		List<Report> reportList = new ArrayList<Report>();
		try {
			repNameList.forEach(names -> reportList.add(new Report(names)));
		} catch (Exception e) {
			logger.error("issue in findAllReports", e);
		}
		return reportList;
	}

	@Override
	public String fetchSchedules(String client, String reportName) {
		// TODO Auto-generated method stub
		String schedule = "";
		try {
			schedule = readProperties.fetchSchedules(client, reportName);
		} catch (Exception e) {
			logger.error("issue in fetchSchedules", e);
		}
		return schedule;
	}

}
