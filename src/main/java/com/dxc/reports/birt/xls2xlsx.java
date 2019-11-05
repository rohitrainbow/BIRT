package com.dxc.reports.birt;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import nl.fountain.xelem.excel.Worksheet;

/**
 * Partial copy of one of style and data..
 * 
 * @author jcalfee
 */
public class xls2xlsx {

	/**
	 * @param args
	 * @throws Exception
	 */
	public void converter(String input,String output) throws Exception {

		String inpFn = input;
		String outFn = output;

		InputStream in = new BufferedInputStream(new FileInputStream(inpFn));
		// FileOutputStream fos=new FileOutputStream("test.xlsx");
		// fos.getChannel().transferFrom(in, 0, Long.MAX_VALUE);
		try {
			// Workbook wbIn = new HSSFWorkbook(in);
			nl.fountain.xelem.lex.ExcelReader reader = new nl.fountain.xelem.lex.ExcelReader();
			nl.fountain.xelem.excel.Workbook wbIn = reader
					.getWorkbook(inpFn);
			File outF = new File(outFn);
			if (outF.exists())
				outF.delete();

			Workbook wbOut = new XSSFWorkbook();
			CellStyle style = wbOut.createCellStyle();// Create style
			CellStyle style2 = wbOut.createCellStyle();
			Font font2 = wbOut.createFont();// Create font
			font2.setBoldweight(Font.BOLDWEIGHT_BOLD);
			// Create style

			Font font = wbOut.createFont();// Create font
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);// Make font bold
			font.setColor(HSSFColor.BLUE.index);

			// Make font bold
			// font2.setColor(HSSFColor.BLUE.index);

			int sheetCnt = wbIn.getWorksheets().size();
			// wbIn.getWorksheetAt(0).getRows().size();
			
			/* int sheetCnt = wbIn.getNumberOfSheets(); */
			for (int i = 0; i < sheetCnt; i++) {
				Worksheet sIn = wbIn.getWorksheetAt(i);
				// 
				
				Sheet sOut = wbOut.createSheet(sIn.getName());
				int rowCount = sIn.getRows().size();
				nl.fountain.xelem.excel.Row rowIn = sIn.getRowAt(1);
				// 
				Row rowOut = sOut.createRow(0);

				Iterator<nl.fountain.xelem.excel.Cell> cellIt = rowIn.cellIterator();
				int l = 0;
				while (l < rowIn.getCells().size()) {
					// 
					nl.fountain.xelem.excel.Cell cellIn = cellIt.next();
					Cell cellOut = rowOut.createCell(l, 0);
					cellOut.setCellValue(cellIn.getData$());
					style.setFont(font);
					cellOut.setCellStyle(style);
					l++;
				}

				rowIn = sIn.getRowAt(2);
				// style.setFont(font);
				rowOut = sOut.createRow(1);

				cellIt = rowIn.cellIterator();
				int m = 0;
				while (m < rowIn.getCells().size()) {
					// 
					nl.fountain.xelem.excel.Cell cellIn = cellIt.next();
					Cell cellOut = rowOut.createCell(m, 0);
					cellOut.setCellValue(cellIn.getData$());
					style2.setFont(font2);
					cellOut.setCellStyle(style2);
					m++;
				}
				OutputStream out = new BufferedOutputStream(new FileOutputStream(outF));
				wbOut.write(out);
				out.close();
				int start = 3;
				int end = 1000;
				int callFreq = sIn.getRows().size() / 1000;

				// 
				for (int call = 0; call < callFreq; call++) {
					xls2xlsx obj = new xls2xlsx();
					obj.miniConv(start, end, inpFn, outFn);
					obj = null;
					
					start = end + 1;
					end = end + 1000;
				}

				if (sIn.getRows().size() % 1000 != 0) {
					xls2xlsx obj = new xls2xlsx();
					obj.miniConv(start, rowCount - 3, inpFn, outFn);
					start=rowCount - 2;
					obj.endConv(start, rowCount, inpFn, outFn);
					obj = null;
				}

				/*
				 * while (j <= rowCount - 3) { rowIn = sIn.getRowAt(j); //
				 *  rowOut = sOut.createRow(j
				 * - 1);
				 * 
				 * cellIt = rowIn.cellIterator(); int k = 0; while (k < rowIn.getCells().size())
				 * { // 
				 * nl.fountain.xelem.excel.Cell cellIn = cellIt.next(); Cell cellOut =
				 * rowOut.createCell(k, 0); cellOut.setCellValue(cellIn.getData$()); k++;
				 * 
				 * } j++; }
				 * 
				 * while (j <= rowCount) { rowIn = sIn.getRowAt(j); // style.setFont(font);
				 * rowOut = sOut.createRow(j - 1);
				 * 
				 * cellIt = rowIn.cellIterator(); int n = 0; while (n < rowIn.getCells().size())
				 * { // 
				 * nl.fountain.xelem.excel.Cell cellIn = cellIt.next(); Cell cellOut =
				 * rowOut.createCell(n, 0); cellOut.setCellValue(cellIn.getData$());
				 * style2.setFont(font2); cellOut.setCellStyle(style2); n++; } j++; }
				 */

			}

			try {

			} finally {

			}
		} finally {
			in.close();
		}
	}

	public void miniConv(int start, int end, String inpFn, String outFn) throws Exception {
		nl.fountain.xelem.lex.ExcelReader reader = new nl.fountain.xelem.lex.ExcelReader();
		nl.fountain.xelem.excel.Workbook wbIn = reader.getWorkbook(inpFn);
		Worksheet sIn = wbIn.getWorksheetAt(0);
		nl.fountain.xelem.excel.Row rowIn = null;
		Iterator<nl.fountain.xelem.excel.Cell> cellIt = null;
		FileInputStream inputStream = new FileInputStream(new File(outFn));
		Workbook workbook =  new XSSFWorkbook(inputStream);
		Sheet sOut = workbook.getSheetAt(0);
		// 
		Row rowOut = null;
		while (start <= end) {
			rowIn = sIn.getRowAt(start);

			// 
			rowOut = sOut.createRow(start - 1);

			cellIt = rowIn.cellIterator();
			int k = 0;
			while (k < rowIn.getCells().size()) {
				// 
				nl.fountain.xelem.excel.Cell cellIn = cellIt.next();
				Cell cellOut = rowOut.createCell(k, 0);
				cellOut.setCellValue(cellIn.getData$());
				k++;

			}
			start++;
		}
		FileOutputStream outputStream = new FileOutputStream(outFn);
		workbook.write(outputStream);
		workbook = null;
		sOut = null;
		rowOut = null;
		//inputStream = null;

		outputStream.close();
	}

	public void endConv(int start, int end, String inpFn, String outFn) throws Exception {
		
		nl.fountain.xelem.lex.ExcelReader reader = new nl.fountain.xelem.lex.ExcelReader();
		nl.fountain.xelem.excel.Workbook wbIn = reader.getWorkbook(inpFn);
		Worksheet sIn = wbIn.getWorksheetAt(0);
		nl.fountain.xelem.excel.Row rowIn = null;
		Iterator<nl.fountain.xelem.excel.Cell> cellIt = null;
		FileInputStream inputStream = new FileInputStream(new File(outFn));
		Workbook workbook =  new XSSFWorkbook(inputStream);
		CellStyle style2 = workbook.createCellStyle();
		Font font2 = workbook.createFont();// Create font
		font2.setBoldweight(Font.BOLDWEIGHT_BOLD);
		style2.setFont(font2);
		Sheet sOut = workbook.getSheetAt(0);
		// 
		Row rowOut = null;
		while (start <= end) {
			rowIn = sIn.getRowAt(start);

			// 
			rowOut = sOut.createRow(start - 1);

			cellIt = rowIn.cellIterator();
			int k = 0;
			while (k < rowIn.getCells().size()) {
				// 
				nl.fountain.xelem.excel.Cell cellIn = cellIt.next();
				Cell cellOut = rowOut.createCell(k, 0);
				cellOut.setCellValue(cellIn.getData$());
				cellOut.setCellStyle(style2);
				k++;

			}
			start++;
		}
		FileOutputStream outputStream = new FileOutputStream(outFn);
		workbook.write(outputStream);
		workbook = null;
		sOut = null;
		rowOut = null;
		//inputStream = null;

		outputStream.close();
	}
}