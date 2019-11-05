package com.dxc.reports.email;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.MultiPartEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dxc.reports.birt.SetDatabaseDetails;

@Component
public class SmtpEmail {

	final static Logger logger = LoggerFactory.getLogger(SetDatabaseDetails.class);

	// private static Logger logger = Logger.getLogger(SmtpEmail.class);

	private static MultiPartEmail email = null;


	static {
		try {
			// esourceBundle = ResourceBundle.getBundle(EMAIL_CONFIG_FILE);
			// email.setHostName("20.137.2.87");
			// email.setHostName("smtp.gmail.com");
			// email.setSmtpPort(25);
			// email.setAuthentication("testbirtsmtp@gmail.com", "abcd@1234");
			/*
			 * List<InternetAddress> list = new ArrayList<>(); InternetAddress
			 * ia=new InternetAddress(); ia.setAddress("rlalwani3@csc.com");
			 * list.add(ia); email.setTo(list);
			 */
			// email.setFrom("testbirtsmtp@gmail.com");
			// email.setFrom("rlalwani3@csc.com");
			Properties props = new Properties();
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

		} catch (Exception mre) {
			logger.error("Static block SmtpEmail", mre);
		}
	}

	public SmtpEmail() {
	}

	public boolean setConfiguration() {
		boolean outcome = false;
		return outcome;
	}

	public boolean sendEmail(String reportName, String docType, String fileName, String from, String to, String subject,
			String body, String host, Integer port) {
		// ResourceBundle resourceBundle = resourceBundle =
		// ResourceBundle.getBundle(EMAIL_CONFIG_FILE);
		boolean outcome = false;
		try {

			try {
				email = new MultiPartEmail();
				email.setHostName(host);
				email.setFrom(from);
				email.setSubject(subject);
				email.setMsg(body);
				email.setSmtpPort(25);
				List<String> temp = Arrays.asList((to.split(",")));
				// claimTypeList.forEach(claim -> repository.save(new
				// ClaimType(claim)));
				for (int i = 0; i < temp.size(); i++) {
					email.addTo(temp.get(i));
				}
				
				// Attachement
				EmailAttachment attachment = new EmailAttachment();
				attachment.setPath(fileName);
				attachment.setDisposition(EmailAttachment.ATTACHMENT);
				attachment.setName(reportName + "." + docType);
				attachment.setDescription(reportName);
				email.attach(attachment);
			} catch (Exception e1) {
				logger.error("Error in sendEmail",e1);
			}

			String messageId = null;
			try {
				messageId = email.send();
			} catch (Exception e) {
				logger.error("Error in sendEmail",e);
			}

			if (messageId == null) {

			} else {
				outcome = true;
			}
		} catch (Exception exp) {
			logger.error("Error in sendEmail",exp);
		}
		return outcome;
	}

}
