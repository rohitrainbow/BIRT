package com.dxc.reports.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dxc.reports.model.HistoryModel;
import com.dxc.reports.util.Constant;
import com.dxc.reports.util.HistoryConnection;

@Repository
public class HistoryDaoImpl implements HistoryDao {

	@Autowired
	HistoryConnection historyConnection;

	private List<HistoryModel> historyList;
	final Logger logger = LoggerFactory.getLogger(HistoryDaoImpl.class);

	public List<HistoryModel> getHistoryDetails(String filter, Integer pageNumber, Integer recordsPerPage) {
		List<HistoryModel> pageHistoryList = new ArrayList<HistoryModel>();
		Integer offSet = 0;
		if (pageNumber != 1) {
			offSet = (pageNumber - 1) * recordsPerPage;
		}
		try {
			for (int i = offSet; (i <= offSet + recordsPerPage - 1) && (i<historyList.size()); i++) {
				pageHistoryList.add(historyList.get(i));
			}
		} catch (Exception e) {
			logger.error("issue in getHistoryDetails", e);
		}
		return pageHistoryList;
	}

	@Override
	public String fileRetrieval(String id) {
		String fileLocation = "";
		try {
			String query = Constant.filePath + "'" + id.trim() + "'";
			Connection conn = historyConnection.getDatabaseConnection();

			// create the java statement
			Statement st = conn.createStatement();

			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);

			// iterate through the java resultset
			while (rs.next()) {
				fileLocation = rs.getString("storedfilelocation");
			}
			st.close();
		} catch (Exception e) {
			logger.error("issue in fileRetrieval", e);
		}
		return fileLocation;
	}

	@Override
	public Integer getPageCount(String filter, Integer recordsPerPage) {
		// TODO Auto-generated method stub
		Integer pageCount = 0;
		Integer rowCount = 0;
		historyList = new ArrayList<HistoryModel>();
		try {
			String query = Constant.retrievalQuery + "'%" + filter + "%' ORDER BY startTime DESC";
			Connection conn = historyConnection.getDatabaseConnection();

			// create the java statement
			Statement st = conn.createStatement();

			// execute the query, and get a java resultset
			ResultSet rs = st.executeQuery(query);

			// iterate through the java resultset
			HistoryModel history = null;
			int i = 0;
			SimpleDateFormat viewFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat dbFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			while (rs.next()) {
				history = new HistoryModel();
				history.setNumber(++i);
				history.setId(rs.getString("id"));
				history.setStatus(rs.getString("status"));
				history.setCreatedBy(rs.getString("createdBy"));
				history.setParameters(rs.getString("parameters"));
				history.setEndTime(rs.getString("endTime"));
				history.setStartTime(viewFormat.format(dbFormat.parse(rs.getString("startTime"))));
				history.setTitle(rs.getString("title"));
				history.setDuration(rs.getString("duration"));
				history.setFileld(rs.getString("fileId"));
				history.setServerUsed(rs.getString("serverUsed"));
				history.setStoredFileLocation(rs.getString("storedFileLocation"));
				history.setExternalDestination(rs.getString("externalDestination"));
				history.setContentType(rs.getString("contentType"));
				history.setFileName(rs.getString("fileName"));

				historyList.add(history);
				rowCount++;
			}
			if (rowCount % recordsPerPage != 0)
				pageCount = new Double((Math.ceil(rowCount / recordsPerPage))).intValue() + 1;
			else
				pageCount = new Double((Math.ceil(rowCount / recordsPerPage))).intValue();
			st.close();
		} catch (Exception e) {
			logger.error("issue in getPageCount", e);
		}

		return pageCount;
	}

}
