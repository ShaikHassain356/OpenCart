package utilities;

import java.io.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

public class ExcelUtility {
    public FileInputStream fi;
    public FileOutputStream fo;
    public XSSFWorkbook workbook;
    public XSSFSheet sheet;
    public XSSFRow row;
    public XSSFCell cell;
    public CellStyle style;
    String path;

    // Constructor to set file path
    public ExcelUtility(String path) {
        this.path = path;
    }

    // Get the number of rows in a sheet
    public int getRowCount(String sheetName) throws IOException {
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);
        int rowCount = sheet.getLastRowNum();
        workbook.close();
        fi.close();
        return rowCount;
    }

    // Get the number of cells in a row
    public int getCellCount(String sheetName, int rownum) throws IOException {
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rownum);
        int cellCount = row.getLastCellNum();
        workbook.close();
        fi.close();
        return cellCount;
    }

    // Get cell data as a string
    public String getCellData(String sheetName, int rownum, int column) throws IOException {
        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(rownum);
        cell = row.getCell(column);

        DataFormatter formatter = new DataFormatter();
        String data = formatter.formatCellValue(cell);

        workbook.close();
        fi.close();
        return data;
    }

    // Set cell data
    public void setCellData(String sheetName, int rownum, int column, String data) throws IOException {
        File xlfile = new File(path);
        if (!xlfile.exists()) {  // If file does not exist, create a new file
            workbook = new XSSFWorkbook();
            fo = new FileOutputStream(path);
            workbook.write(fo);
            fo.close();
        }

        fi = new FileInputStream(path);
        workbook = new XSSFWorkbook(fi);

        if (workbook.getSheetIndex(sheetName) == -1) {  // If sheet does not exist, create new sheet
            workbook.createSheet(sheetName);
        }
        sheet = workbook.getSheet(sheetName);

        if (sheet.getRow(rownum) == null) {  // If row does not exist, create new row
            sheet.createRow(rownum);
        }
        row = sheet.getRow(rownum);

        cell = row.createCell(column);
        cell.setCellValue(data);

        fo = new FileOutputStream(path);
        workbook.write(fo);
        workbook.close();
        fi.close();
        fo.close();
    }

    public void fillGreenColor(String sheetName, int rownum, int colnum) throws IOException {
        fi=new FileInputStream(path);
        workbook=new XSSFWorkbook(fi);
        sheet=workbook.getSheet(sheetName);

        row= sheet.getRow(rownum);
        cell=row.getCell(colnum);

        style=workbook.createCellStyle();

        style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(style);
        workbook.write(fo);
        workbook.close();
        fi.close();
        fo.close();

    }

    public void fillRedColor(String sheetName, int rownum, int colnum) throws IOException {
        fi=new FileInputStream(path);
        workbook=new XSSFWorkbook(fi);
        sheet=workbook.getSheet(sheetName);

        row= sheet.getRow(rownum);
        cell=row.getCell(colnum);

        style=workbook.createCellStyle();

        style.setFillForegroundColor(IndexedColors.RED.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(style);
        workbook.write(fo);
        workbook.close();
        fi.close();
        fo.close();
    }
}