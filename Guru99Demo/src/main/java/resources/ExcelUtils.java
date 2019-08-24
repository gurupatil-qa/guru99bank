package resources;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	public static Sheet wSheet = null;
	public static DataFormatter dataFormatter = new DataFormatter();

	public static String[][] readExcel(String path, String fileName, String sheetName) throws IOException {

		String[][] userArray = null;
		FileInputStream fis = new FileInputStream(path + fileName);
		Workbook myworkbook = null;
		String fileExtension = fileName.substring(fileName.indexOf("."));

		if (fileExtension.equals(".xlsx")) {

			myworkbook = new XSSFWorkbook(fis);

		} else if (fileExtension.equals(".xls")) {

			myworkbook = new HSSFWorkbook(fis);

		}

		wSheet = myworkbook.getSheet(sheetName);

		int rowCount = wSheet.getLastRowNum();

		int ci = 0;
		
		
		
		Row row = wSheet.getRow(0);
		
		int colCount = row.getLastCellNum();
		
		userArray = new String[rowCount][colCount-1];
		
		for (int i = 1; i < rowCount+1; i++) {

			row = wSheet.getRow(i);
			
			int cj = 0;

			for (int j = 1; j < colCount; j++) {

				userArray[ci][cj] = getCellData(i, j);

				cj++;
			}

			ci++;
		}
		myworkbook.close();

		return userArray;

	}

	public static String getCellData(int rownum, int colnum) {

		Cell cellvalue = wSheet.getRow(rownum).getCell(colnum);

		String text = dataFormatter.formatCellValue(cellvalue);

		return text;

	}

}
