package com.dxc.reports.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dxc.reports.birt.ZipFile;
import com.dxc.reports.email.SmtpEmail;
import com.dxc.reports.model.Login;
import com.dxc.reports.service.HistoryService;
import com.dxc.reports.util.CipherHelper;
import com.dxc.reports.util.ReadProperties;
import com.dxc.reports.validator.InputValidator;

@Controller
public class HistoryOptionController {

	private String id = null;
	private String docType = null;
	private String reportName = null;
	private String message = null;
	private String to = null;
	private String body = null;
	private String subject = null;

	@Autowired
	ReadProperties readProperties;

	@Autowired
	CipherHelper cipherHelper;

	@Autowired
	InputValidator inputValidator;

	private Model model;


	/*
	 * @Autowired private LDAPConnect ldapConnect;
	 */

	@Autowired
	private SmtpEmail se;

	@Autowired
	private HistoryService historyService;

	Integer pageCount = 0;

	final Logger logger = LoggerFactory.getLogger(HistoryOptionController.class);

	@RequestMapping(value = "/showHistory", method = RequestMethod.GET)
	public String historyOption(Model model, HttpServletRequest request) {
		this.model = model;
		HttpSession session = request.getSession();
		Login login = (Login) session.getAttribute("login");
		this.model.addAttribute("login", login);
		try {
			this.model.addAttribute("encryptedUserName", cipherHelper.cipher(null, login.getUsername()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Issue in historyOption", e);
		}
		logger.info("Logged in Username in the HistoryOption Controler " + login.getUsername());
		logger.info("in History controller--->");
		String reportName = request.getParameter("report_name");
		String clientName = request.getParameter("client_name");
		String pageNumber = request.getParameter("page_number");
		String recordsPerPage = request.getParameter("recordsPerPage");
		if (!recordsPerPage.matches("^[0-9]+$"))
			recordsPerPage = "10";
		if (!pageNumber.matches("^[0-9]+$"))
			pageNumber = "1";
		model.addAttribute("client", clientName);
		model.addAttribute("reportName", reportName);
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("recordsPerPage", recordsPerPage);
		if (pageNumber.equals("1"))
			pageCount = historyService.getPageCount(clientName + "_" + reportName, Integer.parseInt(recordsPerPage));
		model.addAttribute("histories", historyService.getHistoryDetails(clientName + "_" + reportName,
				Integer.parseInt(pageNumber), Integer.parseInt(recordsPerPage)));
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("noHistory", readProperties.getMessage("history", "NO_DATA"));
		/*
		 * try{ configProperties.load(inputStream); mymongo =
		 * MongoDBConnect.createInstance(); //DBCollection collection =
		 * mymongo.getCollection("HistoryOptionData"); DBCollection collection =
		 * mymongo.getCollection((String)
		 * configProperties.get("FS_FILESCollection"));
		 * CreateHistoryOptionDaoImpl createHistoryOption = new
		 * CreateHistoryOptionDaoImpl(); List<DBObject> cursorRecordByTitle =
		 * createHistoryOption.selectAllRecordByTitle(collection,reportName);
		 * 
		 * if(cursorRecordByTitle != null) { model.addAttribute("recordByTitle",
		 * cursorRecordByTitle); } DBCursor cursorAllRecordsFromACollection =
		 * createHistoryOption.selectAllRecordsFromACollection(collection);
		 * 
		 * if(cursorAllRecordsFromACollection != null) {
		 * model.addAttribute("recordRecordsFromACollection",
		 * cursorAllRecordsFromACollection); }
		 * 
		 * DBCursor cursorSingleRecordAndFieldByFileId =
		 * createHistoryOption.selectSingleRecordAndFieldByFileId(collection);
		 * 
		 * if(cursorSingleRecordAndFieldByFileId != null) {
		 * model.addAttribute("SingleRecordAndFieldByFileId",
		 * cursorSingleRecordAndFieldByFileId); }
		 * 
		 * } catch(Exception unknownHost) { unknownHost.printStackTrace();
		 * 
		 * }
		 */

		return "clients/showHistory";
	}

	@RequestMapping(value = "/sendEmail", method = RequestMethod.GET)
	public String sendEmail(Model model, HttpServletRequest request, HttpServletResponse response) {
		this.model = model;
		logger.debug("sendEmail()");
		id = request.getParameter("id");
		docType = request.getParameter("docType");
		reportName = request.getParameter("reportName");
		// se.sendEmail(reportName, docType, fileName, from, to, subject, body,
		// host, port);
		HttpSession session = request.getSession();
		Login login = (Login) session.getAttribute("login");
		this.model.addAttribute("login", login);
		this.model.addAttribute("message", message);
		this.model.addAttribute("subject", subject);
		this.model.addAttribute("to", to);
		this.model.addAttribute("body", body);
		message = "";
		return "clients/sendEmail";

	}

	@RequestMapping(value = "/mailSent", method = RequestMethod.GET)
	public String mailSent(Model model, HttpServletRequest request) {
		this.model = model;
		logger.debug("sendEmail()");
		HttpSession session = request.getSession();
		Login login = (Login) session.getAttribute("login");
		this.model.addAttribute("login", login);
		this.model.addAttribute("message", readProperties.getMessage("email", "EMAIL_SENT"));
		message = "";
		subject = "";
		to = "";
		body = "";
		return "clients/mailSent";

	}

	@RequestMapping(value = "/sendEmailProcess", method = RequestMethod.POST)
	public String sendEmailProcess(Model model, HttpServletRequest request, HttpServletResponse response) {
		this.model = model;
		logger.debug("sendEmailProcess()");
		to = request.getParameter("recipient");
		subject = request.getParameter("subject");
		body = request.getParameter("content");
		HttpSession session = request.getSession();
		Login login = (Login) session.getAttribute("login");
		if (!inputValidator.emailValidator(to) || to.equals("") || to == null) {
			this.model.addAttribute("login", login);
			message = readProperties.getMessage("email", "INVALID_EMAIL");
			return "redirect:sendEmail?id=" + id + "&docType=" + docType + "&reportName=" + reportName;
		}
		if ((subject.equals("") || subject == null)) {
			this.model.addAttribute("login", login);
			message = readProperties.getMessage("email", "INVALID_SUBJECT");
			return "redirect:sendEmail?id=" + id + "&docType=" + docType + "&reportName=" + reportName;
		}
		if ((body.equals("") || body == null)) {
			this.model.addAttribute("login", login);
			message = readProperties.getMessage("email", "INVALID_BODY");
			return "redirect:sendEmail?id=" + id + "&docType=" + docType + "&reportName=" + reportName;
		}
		File file = new File(historyService.fileRetrieval(id));
		// se.sendEmail(reportName, docType, fileName, from, to, subject, body,
		// host, port);
		if (!file.exists()) {
			this.model.addAttribute("login", login);
			message = readProperties.getMessage("file", "FILE_REMOVED");
			return "redirect:sendEmail?id=" + id + "&docType=" + docType + "&reportName=" + reportName;
		}
		try {
			ZipFile zip = new ZipFile();
			zip.zipConverter(file.getPath());
			String fileName2 = file.getPath();
			subject = login.getUsername() + ":" + subject;
			String[] emailConfiguration = new String[3];
			emailConfiguration = readProperties.getEmailConfiguration();
			se.sendEmail(reportName, docType,
					(fileName2).substring((fileName2).lastIndexOf("/") + 1, (fileName2).indexOf(".") + 1) + "zip",
					emailConfiguration[0], to, subject, body, emailConfiguration[1],
					Integer.parseInt(emailConfiguration[2]));
			File fileZip = new File(
					(fileName2).substring((fileName2).lastIndexOf("/") + 1, (fileName2).indexOf(".") + 1) + "zip");
			fileZip.delete();
		} catch (Exception e) {
			logger.error("issue with sendEmail in HistoryOptionController", e);
			return "redirect:sendEmail?id=" + id + "&docType=" + docType + "&reportName=" + reportName;
		}
		this.model.addAttribute("login", login);

		return "redirect:mailSent";

	}

	@RequestMapping(value = "/downloadFile", method = RequestMethod.GET)
	public String downloadFile(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		/*
		 * String dbName=null; MongoClient mongoClient = null; InputStream
		 * inputStream = null;
		 */
		// System.out.println("objectID recd from history page " +
		// request.getParameter("objectId"));
		String id = request.getParameter("id");
		/*
		 * String hostName=null; int port =0;
		 */

		/*
		 * ReadProperties loadconfigFile = new ReadProperties();
		 * 
		 * Properties configProperties = new Properties(); inputStream =
		 * loadconfigFile.getMongoDBConfigFileLocation();
		 */
		try {

			/*
			 * configProperties.load(inputStream); hostName = (String)
			 * configProperties.get("host"); port =
			 * Integer.parseInt((String)configProperties.get("port")); dbName =
			 * (String) configProperties.get("mongodatabaseName"); String
			 * tempFileStoredPath = (String)
			 * configProperties.get("tempFileStoredPath");
			 * 
			 * 
			 * 
			 * //dbName = "HistoryOptions"; mongoClient = new
			 * MongoClient(hostName, port); DB db = mongoClient.getDB(dbName);
			 * GridFS gfsPhoto = new GridFS(db); //GridFSDBFile imageForOutput =
			 * gfsPhoto.findOne(newSavedFileName); GridFSDBFile imageForOutput =
			 * gfsPhoto.find(new ObjectId(id)); String filename =
			 * imageForOutput.getFilename();
			 * //System.out.println(imageForOutput);
			 * logger.info("Retriving the file  saved image "); String
			 * newFileRetriveName = filename; GridFS gfsPhoto2 = new GridFS(db);
			 * GridFSDBFile imageForOutputRetrived =
			 * gfsPhoto2.findOne(newFileRetriveName);
			 */

			// imageForOutputRetrived.writeTo(filepathStored); //output to new
			// file

			File file = new File(historyService.fileRetrieval(id));
			Path filePath = Paths.get(file.toString());
			FileInputStream stream = null;
			if (file.exists()) { // response.setContentType("application/pdf");
				try {
					String extension = (file.getName()).substring(file.getName().lastIndexOf(".") + 1);
					if (extension.equals("xlsx"))
						response.setContentType(
								"APPLICATION/" + "vnd.openxmlformats-officedocument.spreadsheetml.sheet");
					else if (extension.equals("zip"))
						response.setContentType("APPLICATION/" + "Zip");
					else if (extension.equals("xls"))
						response.setContentType("application/vnd.ms-excel");
					response.setHeader("Content-Disposition", "attachment;" + "filename=" + file.getName());
					stream = new FileInputStream(file);
					response.setContentLength(stream.available());

					// OutputStream os = response.getOutputStream();
					Files.copy(filePath, response.getOutputStream());
					// os.close();
					response.flushBuffer();
				} catch (IOException e) {
					logger.error("issue with downloadFile in HistoryOptionController", e);
				} finally {
					if (stream != null) {
						try {
							stream.close();
						} catch (IOException e) {
							logger.error("issue with downloadFile in HistoryOptionController", e);
						}
					}
				}
			} else {
				return "redirect:fileremoved";
			}

			// mongoClient.close(); // closing the connection

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			logger.error("issue with downloadFile in HistoryOptionController", e1);
		}
		return null;
	}

	@RequestMapping(value = "/fileremoved", method = RequestMethod.GET)
	public String fileRemoved(Model model, HttpServletRequest request) {
		this.model = model;
		model.addAttribute("fileRemoved", readProperties.getMessage("file", "FILE_REMOVED"));
		model.addAttribute("frontpageRedirect", readProperties.getMessage("frontpage", "REDIRECT"));
		return "clients/fileremoved";
	}

}
