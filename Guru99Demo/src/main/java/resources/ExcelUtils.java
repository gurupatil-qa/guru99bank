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
import org.testng.Reporter;

public class ExcelUtils {

	public static Sheet wSheet = null;
	public static DataFormatter dataFormatter = new DataFormatter();

	public static void main(String[] args) {

		try {
			getData("login");

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public static Object[][] getData(String sheetName) throws IOException {

		Object[][] userArray = null; // Array Declaration
		Workbook myworkbook = null;
		String filepath = ".\\testdata\\demodata.xlsx";
		FileInputStream fis = new FileInputStream(filepath);
		String fileExtension = filepath.substring(filepath.indexOf(".", 2));// Get File extension

		if (fileExtension.equals(".xlsx")) {

			myworkbook = new XSSFWorkbook(fis);

		} else if (fileExtension.equals(".xls")) {

			myworkbook = new HSSFWorkbook(fis);

		}

		try {

			wSheet = myworkbook.getSheet(sheetName);

		} catch (NullPointerException e) {

			Reporter.log("Provide valid shet name...");
		}

		int rowCount = wSheet.getLastRowNum(); // Row count starts from 0th index, if 5 rows then return 4 rows

		System.out.println("Row :" + rowCount);

		int ci = 0;

		Row row = wSheet.getRow(0); // Get first row

		int colCount = row.getLastCellNum(); // Get first row cell/column count, count starts from 1st index, if 3 cells
												// then return 3 cells

		System.out.println("Column :" + colCount);

		userArray = new String[rowCount][colCount - 1]; // Instantiating/initializing array

		for (int i = 1; i <= rowCount; i++) {

			row = wSheet.getRow(i);

			int cj = 0;

			for (int j = 1; j < colCount; j++) {

				if (getCellData(i, j) != null && getCellData(i, j).length() != 0)// Check for NULL values
				{
					userArray[ci][cj] = getCellData(i, j); // Add excel data in array
					System.out.println(userArray[ci][cj]);
				}
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
