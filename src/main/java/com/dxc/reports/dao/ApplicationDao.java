package com.dxc.reports.dao;

import java.util.List;
import com.dxc.reports.model.Client;
import com.dxc.reports.model.Report;

public interface ApplicationDao {

	List<Client> findAllClients(String role);

	List<Report> findAllReports(String clientName);

	String fetchSchedules(String client,String reportName);

}
