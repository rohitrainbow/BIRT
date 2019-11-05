package com.dxc.reports.birt;

import java.net.InetAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dxc.reports.util.CipherHelper;
import com.dxc.reports.util.Constant;

@Component
public class History {
	public static Integer historyCreated = 0;
	final Logger logger = LoggerFactory.getLogger(SetDatabaseDetails.class);

	// This method is used to insert the data in the rpt_history table
	public String setHistory(String status, String createdBy, String parameters, String endTime, String startTime,
			String title, String client, String storedFileLocation, String externalDestination, String contentType) {
		HistoryConnectionBIRT historyConnection = new HistoryConnectionBIRT();
		String result = "failed";
		String query = Constant.createHisotry;
		Connection conn = historyConnection.getDatabaseConnection();
		try {
			logger.debug("setHistory Method Called");
			String fileName = storedFileLocation.substring(storedFileLocation.lastIndexOf("\\") + 1,
					storedFileLocation.length());
			PreparedStatement preparedStmt = conn.prepareStatement(query);
			// System.out.println(preparedStmt.toString());
			preparedStmt.setString(1, status);
			CipherHelper cipherHelper = new CipherHelper();
			preparedStmt.setString(2, cipherHelper.decipher(null, createdBy));
			preparedStmt.setString(3, parameters);

			Date endTim = new Date(Long.parseLong(endTime));
			DateFormat dfe = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			preparedStmt.setString(4, dfe.format(endTim));

			Date startTim = new Date(Long.parseLong(startTime));
			preparedStmt.setString(5, dfe.format(startTim));

			preparedStmt.setString(6, title);
			Long duration = Long.parseLong(endTime) - Long.parseLong(startTime);

			SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss.SSS");
			df.setTimeZone(TimeZone.getTimeZone("UTC"));

			preparedStmt.setString(7, df.format(duration));
			String fileId = client + "_" + title+(new SimpleDateFormat("yyyy-MM-dd HH-mm-ss")).format(startTim);
			preparedStmt.setString(8, fileId);
			InetAddress inetAddress = InetAddress.getLocalHost();
			String serverName = inetAddress.getHostName();
			preparedStmt.setString(9, serverName);
			preparedStmt.setString(10, storedFileLocation);
			preparedStmt.setString(11, externalDestination);
			preparedStmt.setString(12, contentType);
			preparedStmt.setString(13, fileName);
			preparedStmt.execute();
			conn.close();
			result = "success";
		} catch (Exception e) {
			logger.error("Issue with setHistory", e);
		}
		return result;
	}
}
