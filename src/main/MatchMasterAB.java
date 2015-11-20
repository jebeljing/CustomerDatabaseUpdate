package main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import model.MasterABEntry;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class MatchMasterAB {

	private static final String masterAB = "C:\\Users\\lalala123\\Desktop\\CustomerDBUpdate\\Master AB updated\\Master AB.xlsx";
	
	public void writeXLSXFile(Map<String, MasterABEntry> updatingMap) throws IOException {
		InputStream ExcelFileToRead = new FileInputStream(masterAB);
		XSSFWorkbook  wb = new XSSFWorkbook(ExcelFileToRead);

		XSSFSheet sheet = wb.getSheetAt(0);

		int lastRowNum = sheet.getLastRowNum();
		for (int i = 0; i < lastRowNum; i++) {
			XSSFRow row = sheet.getRow(i);
			if (updatingMap.get(row.getCell(1).getRawValue()) != null) {
				MasterABEntry entry = updatingMap.get(row.getCell(1).getRawValue());
//				row.
			}
		}
//		XSSFRow row5 = sheet.getRow(4);
//		XSSFCell cell51 = row5.getCell(1);
//		cell51.setCellValue(model.getNumOfCore());
//		
//		XSSFRow row6 = sheet.getRow(5);
//		XSSFCell cell61 = row6.getCell(1);
//		cell61.setCellValue(model.getNumOfSecondary());
//		
//		XSSFRow row7 = sheet.getRow(6);
//		XSSFCell cell71 = row7.getCell(1);
//		cell71.setCellValue(model.getNumOfOccasional());
//		
//		XSSFRow row8 = sheet.getRow(7);
//		XSSFCell cell81 = row8.getCell(1);
//		cell81.setCellValue(model.getCustomers().size());
		
		FileOutputStream fileOut = new FileOutputStream(masterAB);

		//write this workbook to an Outputstream.
		wb.write(fileOut);
		fileOut.flush();
		fileOut.close();
	}
}
