package com.dxc.reports.scheduler;

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
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.dxc.reports.birt.History;
import com.dxc.reports.birt.ZipFile;
import com.dxc.reports.email.SmtpEmail;
import com.dxc.reports.util.CipherHelper;
import com.dxc.reports.util.FileCopier;

@Component
public class ScheduledFileAccess {
	@Value("${myProperties}")
	String location;

	@Autowired
	History history;

	@Autowired
	ZipFile zip;

	@Autowired
	FileCopier fileCopier;

	@Autowired
	CipherHelper cipherHelper;

	final Logger logger = LoggerFactory.getLogger(ScheduledFileAccess.class);

	@SuppressWarnings("unused")
	private final String USER_AGENT = "Mozilla/5.0";

	DateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss"); // OFAC
	// Extract
	DateFormat dateFormatter; // OFAC Extract

	String datetime;

	String clientName = "";
	String claimType = "";
	String repStatus = "";
	String polSite = "";
	String docType = "";
	String startDate = "";
	String endDate = "";
	String reportName = "";
	String url = "";
	String fileLocation = "";
	String from = "";
	String to = "";
	String subject = "";
	String body = "";
	String host = "";
	Integer delay;
	Integer port;
	String allReportParaFaceVal = "";
	String archiveFolder = "";
	String claimSatus = "";
	String region = "";
	String companyCode = "";

	Properties configProperties = new Properties();

	public void propertySetter(String clientName) {
		InputStream inStream = null;
		try {
			try {
				inStream = new FileInputStream(location + clientName + "_parameters.properties");
			} catch (IOException ex) {
				inStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("parameters.properties");
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

	void initMethod() {
		// context = new ClassPathXmlApplicationContext("job-config.xml");

		dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss"); // OFAC
		// Extract
		dateFormatter = new SimpleDateFormat("yyyy-MM-dd"); // OFAC Extract
	}

	// HTTP GET request
	public void sendClaimPolicyAggregateSPv9Job(String schedule, String clientName) throws Exception {
		initMethod();
		propertySetter(clientName);
		datetime = dateTimeFormatter.format(new Date());
		this.clientName = (String) configProperties.get(clientName + ".clientname");
		claimType = (String) configProperties
				.get(clientName + ".Claim_Policy_Aggregate_SP_V9." + schedule + ".claimtype");
		polSite = (String) configProperties.get(clientName + ".Claim_Policy_Aggregate_SP_V9." + schedule + ".polsite");
		repStatus = (String) configProperties
				.get(clientName + ".Claim_Policy_Aggregate_SP_V9." + schedule + ".repstatus");
		docType = (String) configProperties.get(clientName + ".Claim_Policy_Aggregate_SP_V9." + schedule + ".doctype");
		startDate = (String) configProperties
				.get(clientName + ".Claim_Policy_Aggregate_SP_V9." + schedule + ".startDate");
		endDate = (String) configProperties.get(clientName + ".Claim_Policy_Aggregate_SP_V9." + schedule + ".endDate");
		if (startDate.equals("Weekly") && endDate.equals("Weekly")) {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -1);
			endDate = dateFormatter.format(cal.getTime());
			cal.add(Calendar.DATE, -6);
			startDate = dateFormatter.format(cal.getTime());
		}
		reportName = (String) configProperties
				.get(clientName + ".Claim_Policy_Aggregate_SP_V9." + schedule + ".reportName");
		url = (String) configProperties.get(clientName + ".Claim_Policy_Aggregate_SP_V9." + schedule + ".url");
		fileLocation = (String) configProperties
				.get(clientName + ".Claim_Policy_Aggregate_SP_V9." + schedule + ".fileLocation");
		from = (String) configProperties.get(clientName + ".Claim_Policy_Aggregate_SP_V9." + schedule + ".from");
		to = (String) configProperties.get(clientName + ".Claim_Policy_Aggregate_SP_V9." + schedule + ".to");
		subject = (String) configProperties.get(clientName + ".Claim_Policy_Aggregate_SP_V9." + schedule + ".subject");
		body = (String) configProperties.get(clientName + ".Claim_Policy_Aggregate_SP_V9." + schedule + ".body");
		host = (String) configProperties.get(clientName + ".Claim_Policy_Aggregate_SP_V9." + schedule + ".host");
		port = Integer.parseInt(
				(String) configProperties.get(clientName + ".Claim_Policy_Aggregate_SP_V9." + schedule + ".port"));
		delay = Integer.parseInt(
				(String) configProperties.get(clientName + ".Claim_Policy_Aggregate_SP_V9." + schedule + ".delay"));
		allReportParaFaceVal = (String) configProperties
				.get(clientName + ".Claim_Policy_Aggregate_SP_V9." + schedule + ".facevalue");
		// archiveFolder = fileLocation + "Archive\\";
		//
		FileOutputStream fos = null;
		try {

			logger.debug("sendClaimPolicyAggregateSPv9Job Method");
			logger.debug("Input Parameters :-");
			logger.debug("Report Name" + reportName);
			logger.debug("Processing Site" + polSite);
			logger.debug("Client Name:-" + clientName);
			logger.debug("Claim Type:-" + claimType);
			logger.debug("Reporting Status:-" + repStatus);
			logger.debug("Document Type:-" + docType);
			logger.debug("Start Date:-" + startDate);
			logger.debug("End Date:-" + endDate);
			logger.debug("Send Email:- From:" + from + " To:" + to);
			logger.debug("Parameters Face Value Array :-" + allReportParaFaceVal);
			Date startTime = new Date();
			if (!(datetime == null || datetime.equals("")) && !(clientName == null || clientName.equals(""))
					&& !(claimType == null || claimType.equals("")) && !(repStatus == null || repStatus.equals(""))
					&& !(polSite == null || polSite.equals("")) && !(docType == null || docType.equals(""))
					&& !(startDate == null || startDate.equals("")) && !(endDate == null || endDate.equals(""))
					&& !(fileLocation == null || fileLocation.equals(""))
					&& !(reportName == null || reportName.equals("")) && !(url == null || url.equals(""))) {

				StringBuffer fileName = new StringBuffer(fileLocation + allReportParaFaceVal.split(":")[3] + "_"
						+ reportName + "_" + datetime + "." + docType);
				URL website = new URL(url + reportName + "_scheduled.rptdesign" + "&allReportParaFaceVal="
						+ allReportParaFaceVal.replaceAll("\\s+", "") + "&ClaimType=" + claimType + "&ReportingStatus="
						+ repStatus + "&PolicySite=" + polSite + "&StartDate=" + startDate + "&EndDate=" + endDate
						+ "&DBPARAM=" + clientName + "&__format=" + docType);
				ReadableByteChannel rbc = Channels.newChannel(website.openStream());

				fos = new FileOutputStream(fileName.toString());
				fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
				fos.close();

				Date endTime = new Date();
				history.setHistory("Success", cipherHelper.cipher("", schedule), allReportParaFaceVal,
						(endTime.getTime()) + "", (startTime.getTime()) + "", reportName, clientName,
						fileName.toString(), to, docType);
				if (subject.equals("") || subject == null) {
					subject = clientName + "-" + reportName + "-" + allReportParaFaceVal.split(":")[3];
				}
				if (!(from == null || from.equals("")) && !(to == null || to.equals(""))
						&& !(subject == null || subject.equals("")) && !(body == null || body.equals(""))
						&& !(host == null || host.equals("")) && !(port == null)) {

					String zipFile = (fileName).substring((fileName).lastIndexOf("/") + 1, (fileName).indexOf(".") + 1)
							+ "zip";
					zip.zipConverter(fileName.toString());
					SmtpEmail se = new SmtpEmail();
					Thread.sleep(delay);
					// se.sendEmail(reportName, "zip", zipFile, from, to,
					// subject, body, host, port);
					File file = new File(zipFile);
					file.delete();
					// fileCopier.copyFiles(new File(fileName.toString()), new
					// File(archiveFolder
					// + allReportParaFaceVal.split(":")[3] + "_" + reportName +
					// "_" + datetime + "." + docType));
				}

			}
		} catch (Exception e) {

			logger.error("Issue with sendClaimPolicyAggregateSPv9Job", e);
		} finally {
			if (fos != null)
				fos.close();

		}
		// se.sendEmail(fileName.toString());
	}

	public void sendClaimFinancialAtPolicyLevelJob(String schedule, String clientName) throws Exception {
		initMethod();
		propertySetter(clientName);
		datetime = dateTimeFormatter.format(new Date());
		this.clientName = (String) configProperties.get(clientName + ".clientname");
		claimType = (String) configProperties
				.get(clientName + ".claim_Financial_at_Policy_Level." + schedule + ".claimtype");
		polSite = (String) configProperties
				.get(clientName + ".claim_Financial_at_Policy_Level." + schedule + ".polsite");
		repStatus = (String) configProperties
				.get(clientName + ".claim_Financial_at_Policy_Level." + schedule + ".repstatus");
		docType = (String) configProperties
				.get(clientName + ".claim_Financial_at_Policy_Level." + schedule + ".doctype");
		startDate = (String) configProperties
				.get(clientName + ".claim_Financial_at_Policy_Level." + schedule + ".startDate");
		endDate = (String) configProperties
				.get(clientName + ".claim_Financial_at_Policy_Level." + schedule + ".endDate");
		// archiveFolder = fileLocation + "Archive";
		if (startDate.equals("Weekly") && endDate.equals("Weekly")) {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -1);
			endDate = dateFormatter.format(cal.getTime());
			cal.add(Calendar.DATE, -6);
			startDate = dateFormatter.format(cal.getTime());
		}
		reportName = (String) configProperties
				.get(clientName + ".claim_Financial_at_Policy_Level." + schedule + ".reportName");
		url = (String) configProperties.get(clientName + ".claim_Financial_at_Policy_Level." + schedule + ".url");
		fileLocation = (String) configProperties
				.get(clientName + ".claim_Financial_at_Policy_Level." + schedule + ".fileLocation");
		from = (String) configProperties.get(clientName + ".claim_Financial_at_Policy_Level." + schedule + ".from");
		to = (String) configProperties.get(clientName + ".claim_Financial_at_Policy_Level." + schedule + ".to");
		subject = (String) configProperties
				.get(clientName + ".claim_Financial_at_Policy_Level." + schedule + ".subject");
		body = (String) configProperties.get(clientName + ".claim_Financial_at_Policy_Level." + schedule + ".body");
		host = (String) configProperties.get(clientName + ".claim_Financial_at_Policy_Level." + schedule + ".host");
		port = Integer.parseInt(
				(String) configProperties.get(clientName + ".claim_Financial_at_Policy_Level." + schedule + ".port"));
		delay = Integer.parseInt(
				(String) configProperties.get(clientName + ".claim_Financial_at_Policy_Level." + schedule + ".delay"));
		allReportParaFaceVal = (String) configProperties
				.get(clientName + ".claim_Financial_at_Policy_Level." + schedule + ".facevalue");

		FileOutputStream fos = null;
		try {
			logger.debug("sendClaimFinancialAtPolicyLevelJob Method");
			logger.debug("Input Parameters :-");
			logger.debug("Report Name" + reportName);
			logger.debug("Processing Site" + polSite);
			logger.debug("Client Name:-" + clientName);
			logger.debug("Claim Type:-" + claimType);
			logger.debug("Reporting Status:-" + repStatus);
			logger.debug("Document Type:-" + docType);
			logger.debug("Start Date:-" + startDate);
			logger.debug("End Date:-" + endDate);
			logger.debug("Send Email:- From:" + from + " To:" + to);
			logger.debug("Parameters Face Value Array :-" + allReportParaFaceVal);
			Date startTime = new Date();
			if (!(datetime == null || datetime.equals("")) && !(clientName == null || clientName.equals(""))
					&& !(claimType == null || claimType.equals("")) && !(repStatus == null || repStatus.equals(""))
					&& !(polSite == null || polSite.equals("")) && !(docType == null || docType.equals(""))
					&& !(startDate == null || startDate.equals("")) && !(endDate == null || endDate.equals(""))
					&& !(fileLocation == null || fileLocation.equals(""))
					&& !(reportName == null || reportName.equals("")) && !(url == null || url.equals(""))) {

				StringBuffer fileName = new StringBuffer(fileLocation + allReportParaFaceVal.split(":")[3] + "_"
						+ reportName + "_" + datetime + "." + docType);
				URL website = new URL(url + reportName + "_scheduled.rptdesign" + "&allReportParaFaceVal="
						+ allReportParaFaceVal.replaceAll("\\s+", "") + "&ClaimType=" + claimType + "&ReportingStatus="
						+ repStatus + "&PolicySite=" + polSite + "&StartDate=" + startDate + "&EndDate=" + endDate
						+ "&DBPARAM=" + clientName + "&__format=" + docType);
				ReadableByteChannel rbc = Channels.newChannel(website.openStream());

				fos = new FileOutputStream(fileName.toString());
				fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
				fos.close();

				Date endTime = new Date();
				history.setHistory("Success", cipherHelper.cipher("", schedule), allReportParaFaceVal,
						(endTime.getTime()) + "", (startTime.getTime()) + "", reportName, clientName,
						fileName.toString(), to, docType);
				// createHistoryJson.createJsonforMongo((fileName).substring((fileName).lastIndexOf("\\")+1,
				// (fileName).indexOf(".")+1)+"zip", cipherHelper.cipher(null,
				// "Scheduled"), allReportParaFaceVal+": Start
				// Date"+startDate+": End Date"+endDate, clientName, reportName,
				// docType, to);
				if (subject.equals("") || subject == null) {
					subject = clientName + "-" + reportName + "-" + allReportParaFaceVal.split(":")[3];
				}
				if (!(from == null || from.equals("")) && !(to == null || to.equals(""))
						&& !(subject == null || subject.equals("")) && !(body == null || body.equals(""))
						&& !(host == null || host.equals("")) && !(port == null)) {
					zip.zipConverter(fileName.toString());

					String zipFile = (fileName).substring((fileName).lastIndexOf("/") + 1, (fileName).indexOf(".") + 1)
							+ "zip";
					SmtpEmail se = new SmtpEmail();
					Thread.sleep(delay);
					se.sendEmail(reportName, "zip", zipFile, from, to, subject, body, host, port);
					File file = new File(zipFile);
					file.delete();
					// fileCopier.copyFiles(new File(fileName.toString()), new
					// File(archiveFolder
					// + (fileName).substring((fileName).lastIndexOf("/") + 1,
					// (fileName).length())));
				}

			}
		} catch (Exception e) {

			logger.error("Issue with sendClaimFinancialAtPolicyLevel", e);
		} finally {
			if (fos != null)
				fos.close();

		}
		// se.sendEmail(fileName.toString());
	}

	public void sendClaimFinancialAtPayeeLevelSPv7Job(String schedule, String clientName) throws Exception {
		initMethod();
		propertySetter(clientName);
		datetime = dateTimeFormatter.format(new Date());
		this.clientName = (String) configProperties.get(clientName + ".clientname");
		claimType = (String) configProperties
				.get(clientName + ".Claim_Financial_at_Payee_Level_SP_v7." + schedule + ".claimtype");
		polSite = (String) configProperties
				.get(clientName + ".Claim_Financial_at_Payee_Level_SP_v7." + schedule + ".polsite");
		repStatus = (String) configProperties
				.get(clientName + ".Claim_Financial_at_Payee_Level_SP_v7." + schedule + ".repstatus");
		docType = (String) configProperties
				.get(clientName + ".Claim_Financial_at_Payee_Level_SP_v7." + schedule + ".doctype");
		startDate = (String) configProperties
				.get(clientName + ".Claim_Financial_at_Payee_Level_SP_v7." + schedule + ".startDate");
		endDate = (String) configProperties
				.get(clientName + ".Claim_Financial_at_Payee_Level_SP_v7." + schedule + ".endDate");
		// archiveFolder = fileLocation + "Archive";
		if (startDate.equals("Weekly") && endDate.equals("Weekly")) {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -1);
			endDate = dateFormatter.format(cal.getTime());
			cal.add(Calendar.DATE, -6);
			startDate = dateFormatter.format(cal.getTime());
		}
		reportName = (String) configProperties
				.get(clientName + ".Claim_Financial_at_Payee_Level_SP_v7." + schedule + ".reportName");
		url = (String) configProperties.get(clientName + ".Claim_Financial_at_Payee_Level_SP_v7." + schedule + ".url");
		fileLocation = (String) configProperties
				.get(clientName + ".Claim_Financial_at_Payee_Level_SP_v7." + schedule + ".fileLocation");
		from = (String) configProperties
				.get(clientName + ".Claim_Financial_at_Payee_Level_SP_v7." + schedule + ".from");
		to = (String) configProperties.get(clientName + ".Claim_Financial_at_Payee_Level_SP_v7." + schedule + ".to");
		subject = (String) configProperties
				.get(clientName + ".Claim_Financial_at_Payee_Level_SP_v7." + schedule + ".subject");
		body = (String) configProperties
				.get(clientName + ".Claim_Financial_at_Payee_Level_SP_v7." + schedule + ".body");
		host = (String) configProperties
				.get(clientName + ".Claim_Financial_at_Payee_Level_SP_v7." + schedule + ".host");
		port = Integer.parseInt((String) configProperties
				.get(clientName + ".Claim_Financial_at_Payee_Level_SP_v7." + schedule + ".port"));
		delay = Integer.parseInt((String) configProperties
				.get(clientName + ".Claim_Financial_at_Payee_Level_SP_v7." + schedule + ".delay"));
		allReportParaFaceVal = (String) configProperties
				.get(clientName + ".Claim_Financial_at_Payee_Level_SP_v7." + schedule + ".facevalue");

		//
		FileOutputStream fos = null;
		try {
			logger.debug("sendClaimFinancialAtPolicyLevelJob Method");
			logger.debug("Input Parameters :-");
			logger.debug("Report Name" + reportName);
			logger.debug("Processing Site" + polSite);
			logger.debug("Client Name:-" + clientName);
			logger.debug("Claim Type:-" + claimType);
			logger.debug("Reporting Status:-" + repStatus);
			logger.debug("Document Type:-" + docType);
			logger.debug("Start Date:-" + startDate);
			logger.debug("End Date:-" + endDate);
			logger.debug("Send Email:- From:" + from + " To:" + to);
			logger.debug("Parameters Face Value Array :-" + allReportParaFaceVal);
			Date startTime = new Date();
			if (!(datetime == null || datetime.equals("")) && !(clientName == null || clientName.equals(""))
					&& !(claimType == null || claimType.equals("")) && !(repStatus == null || repStatus.equals(""))
					&& !(polSite == null || polSite.equals("")) && !(docType == null || docType.equals(""))
					&& !(startDate == null || startDate.equals("")) && !(endDate == null || endDate.equals(""))
					&& !(fileLocation == null || fileLocation.equals(""))
					&& !(reportName == null || reportName.equals("")) && !(url == null || url.equals(""))) {

				StringBuffer fileName = new StringBuffer(fileLocation + allReportParaFaceVal.split(":")[3] + "_"
						+ reportName + "_" + datetime + "." + docType);
				URL website = new URL(url + reportName + "_scheduled.rptdesign" + "&allReportParaFaceVal="
						+ allReportParaFaceVal.replaceAll("\\s+", "") + "&ClaimType=" + claimType + "&ReportingStatus="
						+ repStatus + "&PolicySite=" + polSite + "&StartDate=" + startDate + "&EndDate=" + endDate
						+ "&DBPARAM=" + clientName + "&__format=" + docType);
				ReadableByteChannel rbc = Channels.newChannel(website.openStream());

				fos = new FileOutputStream(fileName.toString());
				fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
				fos.close();
				zip.zipConverter(fileName.toString());

				Date endTime = new Date();
				history.setHistory("Success", cipherHelper.cipher("", schedule), allReportParaFaceVal,
						(endTime.getTime()) + "", (startTime.getTime()) + "", reportName, clientName,
						fileName.toString(), to, docType);
				// createHistoryJson.createJsonforMongo((fileName).substring((fileName).lastIndexOf("\\")+1,
				// (fileName).indexOf(".")+1)+"zip", cipherHelper.cipher(null,
				// "Scheduled"), allReportParaFaceVal+": Start
				// Date"+startDate+": End Date"+endDate, clientName, reportName,
				// docType, to);
				if (subject.equals("") || subject == null) {
					subject = clientName + "-" + reportName + "-" + allReportParaFaceVal.split(":")[3];
				}
				if (!(from == null || from.equals("")) && !(to == null || to.equals(""))
						&& !(subject == null || subject.equals("")) && !(body == null || body.equals(""))
						&& !(host == null || host.equals("")) && !(port == null)) {

					String zipFile = (fileName).substring((fileName).lastIndexOf("/") + 1, (fileName).indexOf(".") + 1)
							+ "zip";
					SmtpEmail se = new SmtpEmail();
					Thread.sleep(delay);
					se.sendEmail(reportName, "zip", zipFile, from, to, subject, body, host, port);
					File file = new File(zipFile);
					file.delete();
					// fileCopier.copyFiles(new File(fileName.toString()), new
					// File(archiveFolder
					// + (fileName).substring((fileName).lastIndexOf("/") + 1,
					// (fileName).length())));
				}

			}
		} catch (Exception e) {

			logger.error("Issue with sendClaimFinancialAtPayeeLevelSPv7Job", e);
		} finally {
			if (fos != null)
				fos.close();

		}
		// se.sendEmail(fileName.toString());
	}

	public void sendClaimsDetailReportJob(String schedule, String clientName, String frequency) throws Exception {
		initMethod();
		propertySetter(clientName);
		datetime = dateTimeFormatter.format(new Date());
		clientName = (String) configProperties.get(clientName + ".clientname");
		claimType = (String) configProperties.get(clientName + ".ClaimsDetailReport." + schedule + ".claimtype");
		polSite = (String) configProperties.get(clientName + ".ClaimsDetailReport." + schedule + ".polsite");
		repStatus = (String) configProperties.get(clientName + ".ClaimsDetailReport." + schedule + ".repstatus");
		docType = (String) configProperties.get(clientName + ".ClaimsDetailReport." + schedule + ".doctype");
		startDate = (String) configProperties.get(clientName + ".ClaimsDetailReport." + schedule + ".startDate");
		endDate = (String) configProperties.get(clientName + ".ClaimsDetailReport." + schedule + ".endDate");
		claimSatus = (String) configProperties.get(clientName + ".ClaimsDetailReport." + schedule + ".claimSatus");
		region = (String) configProperties.get(clientName + ".ClaimsDetailReport." + schedule + ".region");
		companyCode = (String) configProperties.get(clientName + ".ClaimsDetailReport." + schedule + ".companyCode");
		// archiveFolder = fileLocation + "Archive";
		if (startDate.equals("Weekly") && endDate.equals("Weekly")) {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -1);
			endDate = dateFormatter.format(cal.getTime());
			cal.add(Calendar.DATE, -6);
			startDate = dateFormatter.format(cal.getTime());
		}
		reportName = (String) configProperties.get(clientName + ".ClaimsDetailReport." + schedule + ".reportName");
		url = (String) configProperties.get(clientName + ".ClaimsDetailReport." + schedule + ".url");
		fileLocation = (String) configProperties.get(clientName + ".ClaimsDetailReport." + schedule + ".fileLocation");
		from = (String) configProperties.get(clientName + ".ClaimsDetailReport." + schedule + ".from");
		to = (String) configProperties.get(clientName + ".ClaimsDetailReport." + schedule + ".to");
		subject = (String) configProperties.get(clientName + ".ClaimsDetailReport." + schedule + ".subject");
		body = (String) configProperties.get(clientName + ".ClaimsDetailReport." + schedule + ".body");
		host = (String) configProperties.get(clientName + ".ClaimsDetailReport." + schedule + ".host");
		port = Integer
				.parseInt((String) configProperties.get(clientName + ".ClaimsDetailReport." + schedule + ".port"));
		delay = Integer
				.parseInt((String) configProperties.get(clientName + ".ClaimsDetailReport." + schedule + ".delay"));
		allReportParaFaceVal = (String) configProperties
				.get(clientName + ".ClaimsDetailReport." + schedule + ".facevalue");

		//
		FileOutputStream fos = null;
		try {
			logger.debug("sendClaimFinancialAtPolicyLevelJob Method");
			logger.debug("Input Parameters :-");
			logger.debug("Report Name" + reportName);
			logger.debug("Processing Site" + polSite);
			logger.debug("Client Name:-" + clientName);
			logger.debug("Claim Type:-" + claimType);
			logger.debug("Reporting Status:-" + repStatus);
			logger.debug("Document Type:-" + docType);
			logger.debug("Start Date:-" + startDate);
			logger.debug("End Date:-" + endDate);
			logger.debug("Send Email:- From:" + from + " To:" + to);
			logger.debug("Parameters Face Value Array :-" + allReportParaFaceVal);

			Date startTime = new Date();
			if (!(datetime == null || datetime.equals("")) && !(clientName == null || clientName.equals(""))
					&& !(claimType == null || claimType.equals("")) && !(repStatus == null || repStatus.equals(""))
					&& !(polSite == null || polSite.equals("")) && !(docType == null || docType.equals(""))
					&& !(startDate == null || startDate.equals("")) && !(endDate == null || endDate.equals(""))
					&& !(fileLocation == null || fileLocation.equals(""))
					&& !(reportName == null || reportName.equals("")) && !(url == null || url.equals(""))) {

				StringBuffer fileName = new StringBuffer("");
				if (frequency == "") {
					fileName = new StringBuffer(fileLocation + "cmA_ESS_" + reportName + "_"
							+ allReportParaFaceVal.split(":")[3] + "_" + datetime + "." + docType);
				} else {
					fileName = new StringBuffer(
							fileLocation + "cmA_ESS_" + reportName + "_" + frequency + "_" + datetime + "." + docType);
				}
				URL website = new URL(url + reportName + "_scheduled.rptdesign" + "&allReportParaFaceVal="
						+ allReportParaFaceVal + "&ClaimType=" + claimType + "&ReportingStatus=" + repStatus
						+ "&PolicySite=" + polSite + "&StartDate=" + startDate + "&EndDate=" + endDate + "&DBPARAM="
						+ clientName + "&Region=" + region + "&CompanyCode=" + companyCode + "&ClaimStatus="
						+ claimSatus + "&__format=" + docType);
				if (docType.equals("txt")) {
					website = new URL(url + reportName + "_scheduled.rptdesign" + "&allReportParaFaceVal="
							+ allReportParaFaceVal + "&ClaimType=" + claimType + "&ReportingStatus=" + repStatus
							+ "&PolicySite=" + polSite + "&StartDate=" + startDate + "&EndDate=" + endDate + "&DBPARAM="
							+ clientName + "&Region=" + region + "&CompanyCode=" + companyCode + "&ClaimStatus="
							+ claimSatus + "&__format=" + "csv");
				}
				logger.debug("Acessing File at :" + website.toString());
				ReadableByteChannel rbc = Channels.newChannel(website.openStream());

				fos = new FileOutputStream(fileName.toString());
				fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
				fos.close();
				if (docType.equals("txt")) {
					File csvFile = new File(fileName.toString());
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
				zip.zipConverter(fileName.toString());

				Date endTime = new Date();
				history.setHistory("Success", cipherHelper.cipher("", schedule), allReportParaFaceVal,
						(endTime.getTime()) + "", (startTime.getTime()) + "", reportName, clientName,
						fileName.toString(), to, docType);
				// createHistoryJson.createJsonforMongo((fileName).substring((fileName).lastIndexOf("\\")+1,
				// (fileName).indexOf(".")+1)+"zip", cipherHelper.cipher(null,
				// "Scheduled"), allReportParaFaceVal+": Start
				// Date"+startDate+": End Date"+endDate, clientName, reportName,
				// docType, to);
				if (subject.equals("") || subject == null) {
					subject = clientName + "-" + reportName + "-" + allReportParaFaceVal.split(":")[3];
				}
				if (!(from == null || from.equals("")) && !(to == null || to.equals(""))
						&& !(subject == null || subject.equals("")) && !(body == null || body.equals(""))
						&& !(host == null || host.equals("")) && !(port == null)) {

					String zipFile = (fileName).substring((fileName).lastIndexOf("/") + 1, (fileName).indexOf(".") + 1)
							+ "zip";
					SmtpEmail se = new SmtpEmail();
					Thread.sleep(delay);
					se.sendEmail(reportName, "zip", zipFile, from, to, subject, body, host, port);
					File file = new File(zipFile);
					file.delete();
					// fileCopier.copyFiles(new File(fileName.toString()), new
					// File(archiveFolder
					// + (fileName).substring((fileName).lastIndexOf("/") + 1,
					// (fileName).length())));
				}

			}
		} catch (Exception e) {

			logger.error("Issue with sendClaimsDetailReportJob", e);
		} finally {
			if (fos != null)
				fos.close();

		}
		// se.sendEmail(fileName.toString());
	}

	public void sendCsaDeathClaimPendingJob(String schedule, String clientName) throws Exception {
		initMethod();
		propertySetter(clientName);
		datetime = dateTimeFormatter.format(new Date());
		this.clientName = (String) configProperties.get(clientName + ".clientname");
		docType = (String) configProperties
				.get(clientName + ".csA_Death_Claim_Pending_Report." + schedule + ".doctype");
		// archiveFolder = fileLocation + "Archive";
		if (startDate.equals("Weekly") && endDate.equals("Weekly")) {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -1);
			endDate = dateFormatter.format(cal.getTime());
			cal.add(Calendar.DATE, -6);
			startDate = dateFormatter.format(cal.getTime());
		}
		reportName = (String) configProperties
				.get(clientName + ".csA_Death_Claim_Pending_Report." + schedule + ".reportName");
		url = (String) configProperties.get(clientName + ".csA_Death_Claim_Pending_Report." + schedule + ".url");
		fileLocation = (String) configProperties
				.get(clientName + ".csA_Death_Claim_Pending_Report." + schedule + ".fileLocation");
		String fileLocation2 = (String) configProperties
				.get(clientName + ".csA_Death_Claim_Pending_Report." + schedule + ".fileLocation2");
		from = (String) configProperties
				.get(clientName + ".csA_Death_Claim_Pending_Report." + schedule + ".from");
		to = (String) configProperties.get(clientName + ".csA_Death_Claim_Pending_Report." + schedule + ".to");
		subject = (String) configProperties
				.get(clientName + ".csA_Death_Claim_Pending_Report." + schedule + ".subject");
		body = (String) configProperties
				.get(clientName + ".csA_Death_Claim_Pending_Report." + schedule + ".body");
		host = (String) configProperties
				.get(clientName + ".csA_Death_Claim_Pending_Report." + schedule + ".host");
		port = Integer.parseInt((String) configProperties
				.get(clientName + ".csA_Death_Claim_Pending_Report." + schedule + ".port"));
		delay = Integer.parseInt((String) configProperties
				.get(clientName + ".csA_Death_Claim_Pending_Report." + schedule + ".delay"));

		//
		FileOutputStream fos = null;
		try {
			logger.debug("sendCsaDeathClaimPendingJob Method");
			logger.debug("Input Parameters :-");

			logger.debug("Client Name:-" + clientName);

			logger.debug("Document Type:-" + docType);

			logger.debug("Send Email:- From:" + from + " To:" + to);
			logger.debug("Parameters Face Value Array :-" + allReportParaFaceVal);
			Date startTime = new Date();
			if (!(datetime == null || datetime.equals("")) && !(clientName == null || clientName.equals(""))
					&& !(docType == null || docType.equals("")) && !(fileLocation == null || fileLocation.equals(""))
					&& !(reportName == null || reportName.equals("")) && !(url == null || url.equals(""))) {

				StringBuffer fileName = new StringBuffer(
						fileLocation + clientName + "_" + reportName + "_" + datetime + "." + docType);
				URL website = new URL(
						url + reportName + "_scheduled.rptdesign" + "&DBPARAM=" + clientName + "&__format=" + docType);
				ReadableByteChannel rbc = Channels.newChannel(website.openStream());

				fos = new FileOutputStream(fileName.toString());
				fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
				fos.close();
				fileCopier.copyFiles(new File(fileName.toString()),
						new File(fileLocation2 + new File(fileName.toString()).getName()));
				

				zip.zipConverter(fileName.toString());

				Date endTime = new Date();
				history.setHistory("Success", cipherHelper.cipher("", schedule), allReportParaFaceVal,
						(endTime.getTime()) + "", (startTime.getTime()) + "", reportName, clientName,
						fileName.toString(), to, docType);
				// createHistoryJson.createJsonforMongo((fileName).substring((fileName).lastIndexOf("\\")+1,
				// (fileName).indexOf(".")+1)+"zip", cipherHelper.cipher(null,
				// "Scheduled"), allReportParaFaceVal+": Start
				// Date"+startDate+": End Date"+endDate, clientName, reportName,
				// docType, to);
				if (subject.equals("") || subject == null) {
					subject = clientName + "-" + reportName ;
				}
				if (!(from == null || from.equals("")) && !(to == null || to.equals(""))
						&& !(subject == null || subject.equals("")) && !(body == null || body.equals(""))
						&& !(host == null || host.equals("")) && !(port == null)) {

					String zipFile = (fileName).substring((fileName).lastIndexOf("/") + 1, (fileName).indexOf(".") + 1)
							+ "zip";
					SmtpEmail se = new SmtpEmail();
					Thread.sleep(delay);
					se.sendEmail(reportName, "zip", zipFile, from, to, subject, body, host, port);
					File file = new File(zipFile);
					file.delete();
					// fileCopier.copyFiles(new File(fileName.toString()), new
					// File(archiveFolder
					// + (fileName).substring((fileName).lastIndexOf("/") + 1,
					// (fileName).length())));
				}

			}
		} catch (Exception e) {

			logger.error("Issue with sendCsaDeathClaimPendingJob", e);
		} finally {
			if (fos != null)
				fos.close();

		}
		// se.sendEmail(fileName.toString());
	}

	// HTTP POST request
	/*
	 * private void sendPost() throws Exception {
	 * 
	 * String url = "https://selfsolve.apple.com/wcResults.do"; URL obj = new
	 * URL(url); HttpsURLConnection con = (HttpsURLConnection)
	 * obj.openConnection();
	 * 
	 * // add reuqest header con.setRequestMethod("POST");
	 * con.setRequestProperty("User-Agent", USER_AGENT);
	 * con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
	 * 
	 * String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";
	 * 
	 * // Send post request con.setDoOutput(true); DataOutputStream wr = new
	 * DataOutputStream(con.getOutputStream()); wr.writeBytes(urlParameters);
	 * wr.flush(); wr.close();
	 * 
	 * int responseCode = con.getResponseCode();
	 * 
	 * 
	 * 
	 * 
	 * BufferedReader in = new BufferedReader(new
	 * InputStreamReader(con.getInputStream())); String inputLine; StringBuffer
	 * response = new StringBuffer();
	 * 
	 * while ((inputLine = in.readLine()) != null) { response.append(inputLine);
	 * } in.close();
	 * 
	 * // print result
	 * 
	 * }
	 */
}
