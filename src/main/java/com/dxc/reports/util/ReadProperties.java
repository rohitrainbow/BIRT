package com.dxc.reports.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.support.CronSequenceGenerator;
import org.springframework.stereotype.Component;

@Component
public class ReadProperties {
	@Value("${myProperties}")
	String location;

	Properties prop = new Properties();
	static Logger logger = LoggerFactory.getLogger(ReadProperties.class.getName());

	public List<String> reportNameInitializer(String clientName) {
		List<String> reportNameList = null;

		InputStream inputStream = null;
		String clientPropertyName = clientName.concat(".reportList");

		try {

			try {
				inputStream = new FileInputStream(location + "dropdown.properties");
			} catch (IOException ex) {
				inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("dropdown.properties");
			}
			prop.load(inputStream);
			reportNameList = Arrays.asList((prop.getProperty(clientPropertyName).split(",")));
			// reportNameList.add("AIG");
		} catch (IOException ex) {
			logger.error("dropdown.properties not found", ex);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException ex) {
					logger.error("dropdown.properties not found", ex);
				} catch (Exception ex) {
					logger.error("dropdown.properties not found", ex);
				}
			}
		}
		return reportNameList;
	}

	public List<String> getClientName(String role) {
		List<String> clientNameList = null;
		InputStream inputStream = null;
		try {
			// inputStream =
			//
			// String
			// filelocation=getServletContext().getInitParameter("AdministratorEmail");

			// inputStream =
			// Thread.currentThread().getContextClassLoader().getResourceAsStream(location);
			// inputStream=ReadProperties.class.getResourceAsStream(location);
			try {
				inputStream = new FileInputStream(location + "dropdown.properties");
			} catch (IOException ex) {
				inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("dropdown.properties");
			}
			prop.load(inputStream);
			if (!(prop.getProperty(role + ".clientNames") == null)
					&& !prop.getProperty(role + ".clientNames").equalsIgnoreCase("NO_REPORTS"))
				clientNameList = Arrays.asList((prop.getProperty(role + ".clientNames").split(",")));
			else
				clientNameList = null;
		} catch (IOException ex) {
			logger.error("dropdown.properties not found", ex);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException ex) {
					logger.error("dropdown.properties not found", ex);
				} catch (Exception ex) {
					logger.error("dropdown.properties not found", ex);
				}
			}
		}
		return clientNameList;
	}

	public String[] dbDetailsReader(String clientName) {
		String[] dbDetails = new String[3];
		InputStream inputStream = null;
		try {
			// inputStream =
			//
			// String
			// filelocation=getServletContext().getInitParameter("AdministratorEmail");

			// inputStream =
			// Thread.currentThread().getContextClassLoader().getResourceAsStream(location);
			// inputStream=ReadProperties.class.getResourceAsStream(location);
			try {
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

	public String[] ldapDetailsReader() {
		String[] ldapDetails = new String[3];
		InputStream inputStream = null;
		try {
			// inputStream =
			//
			// String
			// filelocation=getServletContext().getInitParameter("AdministratorEmail");

			// inputStream =
			// Thread.currentThread().getContextClassLoader().getResourceAsStream(location);
			// inputStream=ReadProperties.class.getResourceAsStream(location);
			try {
				inputStream = new FileInputStream(location + "ldap.properties");
			} catch (IOException ex) {
				inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("ldap.properties");
			}
			prop.load(inputStream);
			ldapDetails[0] = prop.getProperty("url");
			ldapDetails[1] = prop.getProperty("baseprefix");
			ldapDetails[2] = prop.getProperty("factory");
		} catch (IOException ex) {
			logger.error("ldap.properties not found", ex);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException ex) {
					logger.error("ldap.properties not found", ex);
				} catch (Exception ex) {
					logger.error("ldap.properties not found", ex);
				}
			}
		}
		return ldapDetails;
	}

	public String getPropFileLocation() {
		String propLocation = null;
		InputStream inputStream = null;
		try {
			// inputStream =
			//
			// String
			// filelocation=getServletContext().getInitParameter("AdministratorEmail");

			// inputStream =
			// Thread.currentThread().getContextClassLoader().getResourceAsStream(location);
			// inputStream=ReadProperties.class.getResourceAsStream(location);
			inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("propLocation.properties");
			prop.load(inputStream);
			propLocation = prop.getProperty("prop.location");
			//
		} catch (IOException ex) {
			logger.error("propLocation.properties not found", ex);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException ex) {
					logger.error("propLocation.properties not found", ex);
				} catch (Exception ex) {
					logger.error("propLocation.properties not found", ex);
				}
			}
		}
		return propLocation;
	}

	public String getMessage(String type, String code) {
		String message = "";
		if (location == null || location == "") {
			location = getPropFileLocation();
		}
		InputStream inputStream = null;

		try {

			try {
				inputStream = new FileInputStream(location + "messages.properties");
			} catch (IOException ex) {
				inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("messages.properties");
			}
			prop.load(inputStream);
			message = prop.getProperty(type + "." + code);
		} catch (IOException ex) {
			logger.error("dropdown.properties not found", ex);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException ex) {
					logger.error("dropdown.properties not found", ex);
				} catch (Exception ex) {
					logger.error("dropdown.properties not found", ex);
				}
			}
		}
		return message;
	}

	public InputStream getMongoDBConfigFileLocation() {
		// String propLocation = null;
		InputStream inputStream = null;
		try {

			inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties");
			// prop.load(inputStream);
			// propLocation = prop.getProperty("prop.location");
			//
		} catch (Exception ex) {
			logger.error("propLocation.properties not found", ex);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException ex) {
					logger.error("propLocation.properties not found", ex);
				} catch (Exception ex) {
					logger.error("propLocation.properties not found", ex);
				}
			}
		}
		return inputStream;
	}

	public String[] dbHistoryDetails() {

		if (location == null || location == "") {
			location = getPropFileLocation();
		}
		String[] dbDetails = new String[5];
		InputStream inputStream = null;

		// inputStream =
		//
		// String
		// filelocation=getServletContext().getInitParameter("AdministratorEmail");

		// inputStream =
		// Thread.currentThread().getContextClassLoader().getResourceAsStream(location);
		// inputStream=ReadProperties.class.getResourceAsStream(location);
		try {

			try {
				inputStream = new FileInputStream(location + "historydbdetails.properties");
			} catch (IOException ex) {
				inputStream = Thread.currentThread().getContextClassLoader()
						.getResourceAsStream("historydbdetails.properties");
			}
			prop.load(inputStream);
			dbDetails[0] = prop.getProperty("server");
			dbDetails[1] = prop.getProperty("port");
			dbDetails[2] = prop.getProperty("dbName");
			dbDetails[3] = prop.getProperty("userName");
			dbDetails[4] = prop.getProperty("password");
		} catch (IOException ex) {
			logger.error("dropdown.properties not found", ex);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException ex) {
					logger.error("historydbdetails.properties not found", ex);
				} catch (Exception ex) {
					logger.error("historydbdetails.properties not found", ex);
				}
			}
		}

		return dbDetails;
	}

	public String fetchSchedules(String client, String reportName) {
		InputStream inputStream = null;

		String schedule = "";
		if (location == null || location == "") {
			location = getPropFileLocation();
		}
		try {

			try {
				inputStream = new FileInputStream(location + "cronexpression.properties");
			} catch (IOException ex) {
				inputStream = Thread.currentThread().getContextClassLoader()
						.getResourceAsStream("cronexpression.properties");
			}
			prop.load(inputStream);

			Boolean hasNextSchedule = true;
			Integer i = 1;
			while (hasNextSchedule) {
				String filter = client + "." + reportName + ".schedule" + i + ".cronexpression";
				if (prop.getProperty(filter) != null) {
					String cronExpression = prop.getProperty(filter);
					CronSequenceGenerator generator = new CronSequenceGenerator(cronExpression);
					Date nextExecutionDate = generator.next(new Date());
					SimpleDateFormat simpDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm aa");
					if (i % 3 == 0)
						schedule = schedule + simpDateFormat.format(nextExecutionDate) + "||" + "\n";
					else
						schedule = schedule + simpDateFormat.format(nextExecutionDate) + "||";
					i++;
				} else {
					hasNextSchedule = false;
				}
			}
			if (reportName.equalsIgnoreCase("csA_Death_Claim_Pending_Report") && client.equalsIgnoreCase("wre")) {
				SimpleDateFormat simpDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm aa");
				final Calendar c = Calendar.getInstance();
				c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.getActualMaximum(Calendar.DATE), 22, 00);
				schedule = simpDateFormat.format(c.getTime());
			}
			if (schedule.equals("")) {
				schedule = getMessage("schedule", "No_Schedule");
			}
			// reportNameList.add("AIG");
		} catch (IOException ex) {
			logger.error("cronexpression.properties not found", ex);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException ex) {
					logger.error("cronexpression.properties not found", ex);
				} catch (Exception ex) {
					logger.error("cronexpression.properties not found", ex);
				}
			}
		}
		return schedule;
	}

	public String[] getEmailConfiguration() {
		if (location == null || location == "") {
			location = getPropFileLocation();
		}
		String[] emailConfiguration = new String[3];
		InputStream inputStream = null;

		// inputStream =
		//
		// String
		// filelocation=getServletContext().getInitParameter("AdministratorEmail");

		// inputStream =
		// Thread.currentThread().getContextClassLoader().getResourceAsStream(location);
		// inputStream=ReadProperties.class.getResourceAsStream(location);
		try {

			try {
				inputStream = new FileInputStream(location + "email.properties");
			} catch (IOException ex) {
				inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("email.properties");
			}
			prop.load(inputStream);
			emailConfiguration[0] = prop.getProperty("from");
			emailConfiguration[1] = prop.getProperty("host");
			emailConfiguration[2] = prop.getProperty("port");
		} catch (IOException ex) {
			logger.error("dropdown.properties not found", ex);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException ex) {
					logger.error("email.properties not found", ex);
				} catch (Exception ex) {
					logger.error("email.properties not found", ex);
				}
			}
		}

		return emailConfiguration;
	}

	public List<String> getLdapGroups(String property) {
		if (location == null || location == "") {
			location = getPropFileLocation();
		}
		List<String> groupList = null;

		InputStream inputStream = null;

		try {

			try {
				inputStream = new FileInputStream(location + "ldap.properties");
			} catch (IOException ex) {
				inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("ldap.properties");
			}
			prop.load(inputStream);
			groupList = Arrays.asList((prop.getProperty(property).split(",")));
			// reportNameList.add("AIG");
		} catch (IOException ex) {
			logger.error("ldap.properties not found", ex);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException ex) {
					logger.error("ldap.properties not found", ex);
				} catch (Exception ex) {
					logger.error("ldap.properties not found", ex);
				}
			}
		}
		return groupList;
	}

}
