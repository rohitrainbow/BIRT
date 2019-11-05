package com.dxc.reports.service;

import java.util.List;

import com.dxc.reports.model.Client;
import com.dxc.reports.model.Login;
import com.dxc.reports.model.Report;

public interface ApplicationService {

	List<Client> findAllClients(String role);

	String findAllReports(String clientName, Login login);

	String fetchSchedules(String client, String reportName);
}
