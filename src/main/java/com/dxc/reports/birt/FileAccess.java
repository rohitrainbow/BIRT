package com.dxc.reports.birt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dxc.reports.email.SmtpEmail;
import com.dxc.reports.util.ReadProperties;
import com.dxc.reports.validator.InputValidator;

@Component
public class FileAccess {

	private InputValidator inputValidator = new InputValidator();;
	final Logger logger = LoggerFactory.getLogger(FileAccess.class);

	public final String USER_AGENT = "Mozilla/5.0";

	public static Integer fileCreated = 0;

	DateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
	DateFormat dateFormatter;

	String datetime;

	String propLocation;

	String url = "";
	String fileLocation = "";
	String from = "";
	String subject = "";
	String body = "";
	String host = "";
	Integer delay;
	Integer port;

	Properties configProperties = new Properties();

	// Sets the location property file containing the required parameters
	public void propertySetter(String clientName) {
		InputStream inStream = null;
		ReadProperties readProperties = new ReadProperties();
		propLocation = readProperties.getPropFileLocation();
		try {

			try {
				inStream = new FileInputStream(propLocation + clientName + "_parameters.properties");
			} catch (IOException ex) {
				inStream = Thread.currentThread().getContextClassLoader()
						.getResourceAsStream(clientName + "_parameters.properties");
			}
			configProperties.load(inStream);
		} catch (IOException exception) {
			logger.error("parameters.properties not found", exception);
		} finally {
			if (inStream != null) {
				try {
					inStream.close();
				} catch (IOException ex) {
					logger.error("parameters.properties not found", ex);
				} catch (Exception ex) {
					logger.error("parameters.properties not found", ex);
				}
			}
		}
	}

	// Initializes the date formatters
	void initMethod() {
		dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
	}

	// Creates the file on the server based on the input parameters by the user
	public String sendFile(String allReportParaFaceVal, String clientName, String reportName, String claimType,
			String polSite, String repStatus, String docType, String startDate, String endDate, String to)
			throws Exception {
		initMethod();
		propertySetter(clientName);

		datetime = dateTimeFormatter.format(new Date());
		url = (String) configProperties.get(clientName + "." + reportName + ".url");
		fileLocation = (String) configProperties.get(clientName + "." + reportName + ".fileLocation");
		from = (String) configProperties.get(clientName + ".from");
		subject = (String) configProperties.get(clientName + ".subject");
		body = (String) configProperties.get(clientName + ".body");
		host = (String) configProperties.get(clientName + ".host");
		port = Integer.parseInt((String) configProperties.get(clientName + ".port"));
		delay = Integer.parseInt((String) configProperties.get(clientName + ".delay"));
		StringBuffer fileName2 = null;

		FileOutputStream fos = null;
		//
		try {
			logger.debug("sendFile Method");
			logger.debug("Input Parameters :-");
			logger.debug("Report Name" + reportName);
			logger.debug("Processing Site" + polSite);
			logger.debug("Client Name:-" + clientName);
			logger.debug("Claim Type:-" + claimType);
			logger.debug("Reporting Status:-" + repStatus);
			logger.debug("Document Type:-" + docType);
			logger.debug("Start Date:-" + startDate);
			logger.debug("End Date:-" + endDate);
			logger.debug("Send Email:-" + to);
			logger.debug("Parameters Face Value Array :-" + allReportParaFaceVal);
			if (!(datetime == null || datetime.equals("")) && !(clientName == null || clientName.equals(""))
					&& !(claimType == null || claimType.equals("")) && !(repStatus == null || repStatus.equals(""))
					&& !(polSite == null || polSite.equals("")) && !(docType == null || docType.equals(""))
					&& !(startDate == null || startDate.equals("")) && !(endDate == null || endDate.equals(""))
					&& !(fileLocation == null || fileLocation.equals(""))
					&& !(reportName == null || reportName.equals("")) && !(url == null || url.equals(""))) {
				fileName2 = new StringBuffer(fileLocation + allReportParaFaceVal.split(":")[3] + "_" + reportName
						+ datetime + "." + docType);

				String faceVal = allReportParaFaceVal.replaceAll("\\s+", "");
				URL website = new URL(url + reportName + "_scheduled.rptdesign" + "&allReportParaFaceVal=" + faceVal
						+ "&ClaimType=" + claimType + "&ReportingStatus=" + repStatus + "&PolicySite=" + polSite
						+ "&StartDate=" + startDate + "&EndDate=" + endDate + "&DBPARAM=" + clientName + "&__format="
						+ docType + "&DatePaid=" + startDate);
				logger.debug("Acessing File at :" + website.toString());
				ReadableByteChannel rbc = Channels.newChannel(website.openStream());

				fos = new FileOutputStream(fileName2.toString());
				fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
				fos.close();

				// File file = new File(fileName2.toString());
				// file.delete();
				if (subject.equals("") || subject == null) {
					subject = clientName + "-" + reportName + "-" + allReportParaFaceVal.split(":")[3];
				}
				if (!(from == null || from.equals("")) && !(to == null || to.equals(""))
						&& !(subject == null || subject.equals("")) && !(body == null || body.equals(""))
						&& !(host == null || host.equals("")) && !(port == null) && inputValidator.emailValidator(to)) {
					ZipFile zip = new ZipFile();
					zip.zipConverter(fileName2.toString());
					SmtpEmail se = new SmtpEmail();
					Thread.sleep(delay);
					se.sendEmail(reportName, "zip",
							(fileName2).substring((fileName2).lastIndexOf("/") + 1, (fileName2).indexOf(".") + 1)
									+ "zip",
							from, to, subject, body, host, port);
					File file = new File(
							(fileName2).substring((fileName2).lastIndexOf("/") + 1, (fileName2).indexOf(".") + 1)
									+ "zip");
					file.delete();
				}

			}
		} catch (Exception e) {

			logger.error("Issue with senFile", e);
		} finally {
			if (fos != null)
				fos.close();

		}
		return fileName2.toString();
	}

	public String sendClaimsDetailReport(String allReportParaFaceVal, String clientName, String reportName,
			String claimType, String polSite, String repStatus, String docType, String startDate, String endDate,
			String to, String claimSatus, String region, String companyCode) throws Exception {
		initMethod();
		propertySetter(clientName);
		datetime = dateTimeFormatter.format(new Date());
		url = (String) configProperties.get(clientName + "." + reportName + ".url");
		fileLocation = (String) configProperties.get(clientName + "." + reportName + ".fileLocation");
		from = (String) configProperties.get(clientName + ".from");
		subject = (String) configProperties.get(clientName + ".subject");
		body = (String) configProperties.get(clientName + ".body");
		host = (String) configProperties.get(clientName + ".host");
		port = Integer.parseInt((String) configProperties.get(clientName + ".port"));
		delay = Integer.parseInt((String) configProperties.get(clientName + ".delay"));
		StringBuffer fileName2 = null;
		FileOutputStream fos = null;
		//
		try {
			logger.debug("sendFile Method");
			logger.debug("Input Parameters :-");
			logger.debug("Report Name" + reportName);
			logger.debug("Processing Site" + polSite);
			logger.debug("Client Name:-" + clientName);
			logger.debug("Claim Type:-" + claimType);
			logger.debug("Reporting Status:-" + repStatus);
			logger.debug("Document Type:-" + docType);
			logger.debug("Start Date:-" + startDate);
			logger.debug("End Date:-" + endDate);
			logger.debug("Send Email:-" + to);
			logger.debug("Parameters Face Value Array :-" + allReportParaFaceVal);
			logger.debug("Claim Status :-" + claimSatus);
			logger.debug("Region :-" + region);
			logger.debug("Company Code :-" + companyCode);
			if (!(datetime == null || datetime.equals("")) && !(clientName == null || clientName.equals(""))
					&& !(claimType == null || claimType.equals("")) && !(repStatus == null || repStatus.equals(""))
					&& !(polSite == null || polSite.equals("")) && !(docType == null || docType.equals(""))
					&& !(startDate == null || startDate.equals("")) && !(endDate == null || endDate.equals(""))
					&& !(fileLocation == null || fileLocation.equals(""))
					&& !(claimSatus == null || claimSatus.equals("")) && !(region == null || region.equals(""))
					&& !(companyCode == null || companyCode.equals(""))
					&& !(reportName == null || reportName.equals("")) && !(url == null || url.equals(""))) {
				fileName2 = new StringBuffer(fileLocation + allReportParaFaceVal.split(":")[3] + "_" + reportName + "_"
						+ datetime + "." + docType);

				String faceVal = allReportParaFaceVal.replaceAll("\\s+", "");
				URL website = new URL(url + reportName + "_scheduled.rptdesign" + "&allReportParaFaceVal=" + faceVal
						+ "&ClaimType=" + claimType + "&ReportingStatus=" + repStatus + "&PolicySite=" + polSite
						+ "&StartDate=" + startDate + "&EndDate=" + endDate + "&DBPARAM=" + clientName + "&Region="
						+ region + "&CompanyCode=" + companyCode + "&ClaimStatus=" + claimSatus + "&__format="
						+ docType);
				if (docType.equals("txt")) {
					website = new URL(url + reportName + "_scheduled.rptdesign" + "&allReportParaFaceVal=" + faceVal
							+ "&ClaimType=" + claimType + "&ReportingStatus=" + repStatus + "&PolicySite=" + polSite
							+ "&StartDate=" + startDate + "&EndDate=" + endDate + "&DBPARAM=" + clientName + "&Region="
							+ region + "&CompanyCode=" + companyCode + "&ClaimStatus=" + claimSatus + "&__format="
							+ "csv");
				}
				logger.debug("Acessing File at :" + website.toString());
				ReadableByteChannel rbc = Channels.newChannel(website.openStream());

				fos = new FileOutputStream(fileName2.toString());
				fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
				fos.close();
				if (docType.equals("txt")) {
					File csvFile = new File(fileName2.toString());
					File fixWidthFile = new File("temp.txt");
					String search = ","; // <- changed to work with
											// String.replaceAll()
					String replacement = "";
					// file reading
					FileReader fr = new FileReader(csvFile);
					FileWriter fw = new FileWriter(fixWidthFile);
					String s;
					String newLine;
					try {
						BufferedReader br = new BufferedReader(fr);
						while ((s = br.readLine()) != null) {
							newLine = s.replaceAll(search, replacement);
							// do something with the resulting line
							fw.write(newLine + "\r\n");
						}
						fr.close();
						fw.close();
						csvFile.delete();
						fixWidthFile.renameTo(csvFile);

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				// File file = new File(fileName2.toString());
				// file.delete();
				if (subject.equals("") || subject == null) {
					subject = clientName + "-" + reportName + "-" + allReportParaFaceVal.split(":")[3];
				}
				if (!(from == null || from.equals("")) && !(to == null || to.equals(""))
						&& !(subject == null || subject.equals("")) && !(body == null || body.equals(""))
						&& !(host == null || host.equals("")) && !(port == null) && inputValidator.emailValidator(to)) {
					ZipFile zip = new ZipFile();
					zip.zipConverter(fileName2.toString());
					SmtpEmail se = new SmtpEmail();
					Thread.sleep(delay);
					se.sendEmail(reportName, "zip",
							(fileName2).substring((fileName2).lastIndexOf("/") + 1, (fileName2).indexOf(".") + 1)
									+ "zip",
							from, to, subject, body, host, port);
					File file = new File(
							(fileName2).substring((fileName2).lastIndexOf("/") + 1, (fileName2).indexOf(".") + 1)
									+ "zip");
					file.delete();
				}

			}
		} catch (Exception e) {

			logger.error("Issue with senFile", e);
		} finally {
			if (fos != null)
				fos.close();

		}
		return fileName2.toString();
	}

	// Gets the server location of the file
	public String[] getFileSaveLocation(String clientName, String reportName) {
		initMethod();
		propertySetter(clientName);
		String[] fileDetails = new String[2];
		try {
			fileDetails[0] = (String) configProperties.get(clientName + "." + reportName + ".fileLocation");
			fileDetails[1] = dateTimeFormatter.format(new Date());
		} catch (Exception e) {
			logger.error("Issue with getFileSaveLocation", e);
		}

		return fileDetails;
	}

	// Sends the email based on the input parameters
	public void sendEmail(String clientName, String reportName, String docType, String fileLocation, String to) {
		initMethod();
		propertySetter(clientName);
		from = (String) configProperties.get(clientName + ".from");
		subject = (String) configProperties.get(clientName + ".subject");
		body = (String) configProperties.get(clientName + ".body");
		host = (String) configProperties.get(clientName + ".host");
		port = Integer.parseInt((String) configProperties.get(clientName + ".port"));
		delay = Integer.parseInt((String) configProperties.get(clientName + ".delay"));
		if (subject.equals("") || subject == null) {
			subject = clientName + "-" + reportName;
		}
		try {

			if (!(from == null || from.equals("")) && !(to == null || to.equals(""))
					&& !(subject == null || subject.equals("")) && !(body == null || body.equals(""))
					&& !(host == null || host.equals("")) && !(port == null) && inputValidator.emailValidator(to)) {
				SmtpEmail se = new SmtpEmail();
				se.sendEmail(reportName, docType, fileLocation, from, to, subject, body, host, port);

			}
		} catch (Exception e) {
			logger.error("Issue with sendEmail", e);
		}
	}
}
