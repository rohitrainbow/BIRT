package com.dxc.reports.dao;

import java.util.List;

import com.dxc.reports.model.HistoryModel;

public interface HistoryDao {
	public List<HistoryModel> getHistoryDetails(String filter,Integer offset,Integer recordsPerPage);
	public String fileRetrieval(String id);
	public Integer getPageCount(String filter,Integer recordsPerPage);
}
