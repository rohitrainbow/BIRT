package com.dxc.reports.util;

public class Constant {

	public static final String  retrievalQuery="select * from rpt_history where fileid like ";
	public static final String  filePath="select storedfilelocation from rpt_history where id=";
	public static final String createHisotry = "INSERT INTO rpt_history "
			+ "VALUES (NEWID(),?,?,?,?,?,?,?,?,?,?,?,?,?);";
}
