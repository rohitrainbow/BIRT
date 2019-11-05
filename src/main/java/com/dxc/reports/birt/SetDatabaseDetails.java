package com.dxc.reports.birt;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dxc.reports.util.ReadProperties;

public class SetDatabaseDetails {
	String location;
	Properties prop = new Properties();
	final Logger logger = LoggerFactory.getLogger(SetDatabaseDetails.class);

	// Reads the database connection details from the property files
	public String[] dbDetailsReader(String clientName) {
		String[] dbDetails = new String[3];
		InputStream inputStream = null;
		try {
			try {
				ReadProperties readProperties = new ReadProperties();
				location = readProperties.getPropFileLocation();
				inputStream = new FileInputStream(location + "dbdetails.properties");
			} catch (IOException ex) {
				inputStream = Thread.currentThread().getContextClassLoader()
						.getResourceAsStream("dbdetails.properties");
			}
			prop.load(inputStream);
			dbDetails[0] = prop.getProperty(clientName + ".url");
			dbDetails[1] = prop.getProperty(clientName + ".username");
			dbDetails[2] = prop.getProperty(clientName + ".password");
		} catch (IOException ex) {
			logger.error("dbdetails.properties not found", ex);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException ex) {
					logger.error("dbdetails.properties not found", ex);
				} catch (Exception ex) {
					logger.error("dbdetails.properties not found", ex);
				}
			}
		}
		return dbDetails;
	}
}
