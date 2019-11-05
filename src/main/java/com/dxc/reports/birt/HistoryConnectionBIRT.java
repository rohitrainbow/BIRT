package com.dxc.reports.birt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dxc.reports.util.ReadProperties;

@Component
public class HistoryConnectionBIRT {
	public ReadProperties rp = new ReadProperties();
	static Logger logger = LoggerFactory.getLogger(HistoryConnectionBIRT.class.getName());

	// Provides a connection object
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
