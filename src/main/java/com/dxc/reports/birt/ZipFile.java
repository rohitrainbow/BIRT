package com.dxc.reports.birt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ZipFile {
	final Logger logger = LoggerFactory.getLogger(SetDatabaseDetails.class);

	//Zips the large size excel files or any input files
	public void zipConverter(String input) {
		try {
			String sourceFile = input;
			FileOutputStream fos = new FileOutputStream(sourceFile.substring(sourceFile.lastIndexOf('/')+1, sourceFile.indexOf('.')+1)+"zip");
			ZipOutputStream zipOut = new ZipOutputStream(fos);
			File fileToZip = new File(sourceFile);
			FileInputStream fis = new FileInputStream(fileToZip);
			ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
			zipOut.putNextEntry(zipEntry);
			final byte[] bytes = new byte[1024];
			int length;
			while ((length = fis.read(bytes)) >= 0) {
				zipOut.write(bytes, 0, length);
			}
			zipOut.close();
			fis.close();
			fos.close();

		} catch (Exception e) {
			logger.error("zipConverter issues", e);
		}
	}
}