package genericLibrary;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	/*
	 * this method will fetch data from excel sheet
	 */
	public String getDataFromExcel(String sheet,int row,int cell) throws EncryptedDocumentException, IOException {
		FileInputStream fis=new FileInputStream(IPathConstant.EXCEL_FILE_PATH);
		Workbook workbook = WorkbookFactory.create(fis);
		 return workbook.getSheet(sheet).getRow(row).getCell(cell).toString();
		}

}
