package com.dxc.reports.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HistoryConnection {
	@Autowired
	public ReadProperties rp;
	static Logger logger = LoggerFactory.getLogger(HistoryConnection.class.getName());

	public Connection getDatabaseConnection() {
		Connection connObj = null;
		try {

			String[] dbDetails = rp.dbHistoryDetails();
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String JDBC_URL = "jdbc:sqlserver://" + dbDetails[0] + ":" + dbDetails[1] + ";databaseName=" + dbDetails[2];
			connObj = DriverManager.getConnection(JDBC_URL, dbDetails[3], dbDetails[4]);
		} catch (SQLException sqlEx) {
			logger.error("SQLException in HistoryConnection", sqlEx);
		} catch (ClassNotFoundException cNFE) {
			logger.error("ClassNotFoundException in HistoryConnection", cNFE);
		}
		return connObj;
	}
}
