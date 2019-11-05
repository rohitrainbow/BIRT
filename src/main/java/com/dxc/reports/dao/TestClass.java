package com.dxc.reports.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

import com.dxc.reports.util.CipherHelper;

/**
 * @author Crunchify.com
 * 
 */

public class TestClass {

	public static void main(String[] args) throws Exception {
		
		CipherHelper ch = new CipherHelper();
		System.out.println(ch.decipher(null, "DA4AAEF49C8822C27BCFE73A9FC76707"));
		
		System.out.println(new String("BPOALIC").substring(3));
		
		Integer[] ary={4,1,2,1,4,2,2,2,2};
		Integer tSum=0;
		Integer rSum=0;
		Integer lSum=0;
		for(int i=0;i<ary.length;i++)
		{
			tSum=tSum+ary[i];
		}
		
		for(int i=0;i<ary.length;i++)
		{
			for(int j=0;j<i;j++)
			{
				lSum=lSum+ary[j];
			}
			rSum=tSum-lSum-ary[i];
			if(rSum==lSum)
			{
				System.out.println(i);
				
			}
			lSum=0;
			rSum=0;
		}
		
		/*System.out.println("\nOutput: \n" + callURL("http://localhost:1010/BPS_BIRT_Reports/session"));
		
		StringBuffer fileName=new StringBuffer("C:\\Users\\rlalwani3.EAD\\Desktop\\BHF_Information_point.csv");
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
			System.out.println();
			fixWidthFile.renameTo(new File(csvFile.getAbsolutePath().substring(0,csvFile.getAbsolutePath().lastIndexOf('.'))+".txt"));

		} catch (Exception e) {
			e.printStackTrace();
		}*/
	}

	public static String callURL(String myURL) {
		System.out.println("Requeted URL:" + myURL);
		StringBuilder sb = new StringBuilder();
		URLConnection urlConn = null;
		InputStreamReader in = null;
		try {
			URL url = new URL(myURL);
			urlConn = url.openConnection();
			if (urlConn != null)
				urlConn.setReadTimeout(60 * 1000);
			if (urlConn != null && urlConn.getInputStream() != null) {
				in = new InputStreamReader(urlConn.getInputStream(), Charset.defaultCharset());
				BufferedReader bufferedReader = new BufferedReader(in);
				if (bufferedReader != null) {
					int cp;
					while ((cp = bufferedReader.read()) != -1) {
						sb.append((char) cp);
					}
					bufferedReader.close();
				}
			}
			in.close();
		} catch (Exception e) {
			throw new RuntimeException("Exception while calling URL:" + myURL, e);
		}

		return sb.toString();
	}
}
