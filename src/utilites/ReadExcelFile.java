package utilites;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static utilites.Constants.excelFileName;
import static utilites.Constants.excelFilePath;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFCell;


public class ReadExcelFile {

    XSSFWorkbook workBook;
    XSSFSheet sheet;
    XSSFCell cell;

    public ReadExcelFile() throws IOException {

        File file = new File(excelFilePath+excelFileName);
        FileInputStream stream = new FileInputStream(file);
        workBook = new XSSFWorkbook(stream);
    }

    public String getCellData(int sheetIndex, int row, int col){

        sheet = workBook.getSheetAt(sheetIndex);

        DataFormatter formatter = new DataFormatter();
        cell = sheet.getRow(row).getCell(col);
        return formatter.formatCellValue(cell);
    }

    public int getRowCount(int sheetIndex){

        int rowCount = workBook.getSheetAt(sheetIndex).getLastRowNum();
        return rowCount;
    }
}
