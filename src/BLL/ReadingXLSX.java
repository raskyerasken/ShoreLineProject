/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import BE.JSON;
import BE.Planning;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author jacob
 */
public class ReadingXLSX {

    String excelFilePath;
    FileInputStream inputStream;
    int row;
    int colum;
    String[][] allRows;  
    Workbook workbook;
    org.apache.poi.ss.usermodel.Sheet firstSheet;
    List ColumNames = new ArrayList();
    DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
    public ReadingXLSX(String exString) throws FileNotFoundException, IOException {
        excelFilePath = exString;
        inputStream = new FileInputStream(new File(excelFilePath));

        workbook = new XSSFWorkbook(inputStream);
        firstSheet = workbook.getSheetAt(0);
    }

  

    public List getColumsNames() {

        Iterator<Row> iterator = firstSheet.iterator();

        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();

                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_STRING:
                        ColumNames.add(cell.getStringCellValue());
                        break;
                    case Cell.CELL_TYPE_BOOLEAN:

                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        break;
                }

            }
            break;

        }

        return ColumNames;
    }
   

    public String[][] allRows() {

        row = workbook.getNumberOfNames();
        colum = ColumNames.size();
        allRows = new String[row][colum];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < colum; j++) {
                allRows[i][j] = firstSheet.getRow(i).getCell(j).toString();

            }
        }

        return allRows;

    }

    public List<JSON> allJSONObjectInFile() throws ParseException {
        for (int i = 1; i < row; i++) {
            JSON newJSON= new JSON();
            newJSON.setSiteName("");
            newJSON.setAssetSerialNumber(0);
            newJSON.setType(firstSheet.getRow(i).getCell( ColumNames.indexOf("Order Type")).toString());
            newJSON.setExternalWorkOrderId(Integer.parseInt(
                    firstSheet.getRow(i).getCell( ColumNames.indexOf("Order")).toString()));
            newJSON.setSystemStatus(firstSheet.getRow(i).getCell( ColumNames.indexOf("System status")).toString());
            newJSON.setUserStatus(firstSheet.getRow(i).getCell( ColumNames.indexOf("User status")).toString());
           newJSON.setCreatedBy("sap");
           if(firstSheet.getRow(i).getCell(ColumNames.indexOf("Opr. short text")).toString().isEmpty())
           {
               newJSON.setName(firstSheet.getRow(i).getCell(ColumNames.indexOf("Description2")).toString());
           }
           else{
           newJSON.setName(firstSheet.getRow(i).getCell(ColumNames.indexOf("Opr. short text")).toString());
           }
           if(firstSheet.getRow(i).getCell(ColumNames.indexOf("Priority")).toString().isEmpty())
           {
           newJSON.setPriority("Low");
           }
           else{
           newJSON.setPriority(firstSheet.getRow(i).getCell(ColumNames.indexOf("Priority")).toString());
           }
           newJSON.setStatus("New");
           Planning planning =new Planning();
            System.out.println(""+dateFormat.parse(firstSheet.getRow(i).getCell(ColumNames.indexOf("Lat.finish date")).toString()));
           planning.setLatestFinishDate(
                   dateFormat.parse(firstSheet.getRow(i).getCell(ColumNames.indexOf("Lat.finish date")).toString()));
           planning.setEarliestStartDate(
                   dateFormat.parse(firstSheet.getRow(i).getCell(ColumNames.indexOf("Earl.start date")).toString()));
           planning.setLatestStartDate(
           dateFormat.parse(firstSheet.getRow(i).getCell(ColumNames.indexOf("Latest start")).toString()));
           planning.setEstimatedTime(Double.parseDouble(
                    firstSheet.getRow(i).getCell( ColumNames.indexOf("Normal duration")).toString()));
        }
        return null; 
    }
}
