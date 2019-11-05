package com.dxc.reports.service;

import java.util.List;

import com.dxc.reports.model.HistoryModel;

public interface HistoryService {
	public List<HistoryModel> getHistoryDetails(String filter,Integer pageNumber,Integer recordsPerPage);
	public String fileRetrieval(String id);
	public Integer getPageCount(String filter,Integer recordsPerPage);
}
