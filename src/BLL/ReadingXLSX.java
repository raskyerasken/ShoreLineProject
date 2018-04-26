/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
String excelFilePath ;
FileInputStream inputStream;
    public ReadingXLSX(String exString) throws FileNotFoundException, IOException {
        excelFilePath=exString; 
       inputStream = new FileInputStream(new File(excelFilePath));

        workbook = new XSSFWorkbook(inputStream);
        firstSheet = workbook.getSheetAt(0);
    }
    
      Workbook workbook;
        org.apache.poi.ss.usermodel.Sheet firstSheet;
        List ColumNames = new ArrayList();
       public List getColumsNames()
       { 
           
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
       public String[][]  allRows()
       {
           
           int row=workbook.getNumberOfNames();
           int colum=ColumNames.size();
           String[][] allRows = new String[row][colum];
           for (int i = 0; i < row; i++) {
               for (int j = 0; j < colum; j++) {
                   allRows[i][j]=firstSheet.getRow(i).getCell(j).toString();
                   
               }
           }
           
           
      return allRows;
       
       }
}
