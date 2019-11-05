package com.dxc.reports.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.reports.dao.HistoryDao;
import com.dxc.reports.model.HistoryModel;

@Service
public class HistoryServiceImpl implements HistoryService {
	final Logger logger = LoggerFactory.getLogger(HistoryServiceImpl.class);

	@Autowired
	HistoryDao historyDao;
	
	@Override
	public List<HistoryModel> getHistoryDetails(String filter,Integer pageNumber,Integer recordsPerPage) {
		List<HistoryModel> historyList=null;
		// TODO Auto-generated method stub
		try {
			historyList=historyDao.getHistoryDetails(filter,pageNumber,recordsPerPage);
		} catch (Exception e) {
			logger.error("issue with getHistoryDetails service", e);
		}
		return historyList;
	}

	@Override
	public String fileRetrieval(String id) {
		// TODO Auto-generated method stub
		String filePath="";
		// TODO Auto-generated method stub
		try {
			filePath=historyDao.fileRetrieval(id);
		} catch (Exception e) {
			logger.error("issue with fileRetrieval service", e);
		}
		return filePath;
	}

	@Override
	public Integer getPageCount(String filter,Integer recordsPerPage) {
		// TODO Auto-generated method stub
		Integer pageCount=0;
		// TODO Auto-generated method stub
		try {
			pageCount=historyDao.getPageCount(filter,recordsPerPage);
		} catch (Exception e) {
			logger.error("issue with fileRetrieval service", e);
		}
		return pageCount;
	}

}
