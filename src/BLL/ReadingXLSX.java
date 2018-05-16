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
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONException;
import org.json.JSONObject;

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
    Date today = new Date(); 
    
    public ReadingXLSX(String excelPath) throws FileNotFoundException, IOException, ParseException {
        excelFilePath = excelPath;
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

    public List<JSONObject> allJSONObjectInFile() throws ParseException, IllegalArgumentException, IllegalAccessException, JSONException, IOException {
        List<JSONObject> JSONList = new ArrayList<>();
        for (int i = 1; i < row; i++) {
         
           JSONObject newJSON = setJSONObject(i);
           
           JSONList.add(newJSON);
        }
        return JSONList; 
    }
    private JSON setJSON(int i) throws ParseException
    {
     
            JSON newJSON= new JSON();
            newJSON.setSiteName("");
            newJSON.setAssetSerialNumber(0);
            newJSON.setType(firstSheet.getRow(i).getCell( ColumNames.indexOf("Order Type")).toString());
            newJSON.setExternalWorkOrderId(Integer.parseInt(
                    firstSheet.getRow(i).getCell( ColumNames.indexOf("Order")).toString()));
            newJSON.setSystemStatus(firstSheet.getRow(i).getCell( ColumNames.indexOf("System status")).toString());
            newJSON.setUserStatus(firstSheet.getRow(i).getCell( ColumNames.indexOf("User status")).toString());
            newJSON.setCreatedOn(today);
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

           planning.setLatestFinishDate(
                   dateFormat.parse(firstSheet.getRow(i).getCell(ColumNames.indexOf("Lat.finish date")).toString()));
           planning.setEarliestStartDate(
                   dateFormat.parse(firstSheet.getRow(i).getCell(ColumNames.indexOf("Earl.start date")).toString()));
           planning.setLatestStartDate(
           dateFormat.parse(firstSheet.getRow(i).getCell(ColumNames.indexOf("Latest start")).toString()));
           planning.setEstimatedTime(Double.parseDouble(
                    firstSheet.getRow(i).getCell( ColumNames.indexOf("Normal duration")).toString()));
        newJSON.setPlanning(planning);
        return newJSON;
    }
    private JSONObject setJSONObject(int i) throws JSONException, ParseException, IOException
    {
         JSONObject Json = new JSONObject();
          Json.put("siteName", "");
          Json.put("assetSerialNumber", "0");
          Json.put("type", firstSheet.getRow(i).getCell( ColumNames.indexOf("Order Type")).toString());
          Json.put("externalWorkOrderId", firstSheet.getRow(i).getCell( ColumNames.indexOf("Order")).toString());
          Json.put("systemStatus", firstSheet.getRow(i).getCell( ColumNames.indexOf("System status")).toString());
          Json.put("userStatus", firstSheet.getRow(i).getCell( ColumNames.indexOf("User status")).toString());
          Json.put("createdOn", today.getDate()+"-"+today.getMonth()+"-"+(today.getYear()+1900));
          Json.put("createdBy", "SAP");
          if(firstSheet.getRow(i).getCell(ColumNames.indexOf("Opr. short text")).toString().isEmpty())
           {
               Json.put("name",firstSheet.getRow(i).getCell(ColumNames.indexOf("Description2")).toString());
           }
           else{
           Json.put("name",firstSheet.getRow(i).getCell(ColumNames.indexOf("Opr. short text")).toString());
           }
          if(firstSheet.getRow(i).getCell(ColumNames.indexOf("Priority")).toString().isEmpty())
           {
            Json.put("priority","Low");
           }
           else{
           Json.put("priority",firstSheet.getRow(i).getCell(ColumNames.indexOf("Priority")).toString());
           }
           Json.put("status", "new");
            JSONObject planning = new JSONObject();
            planning.put("latestFinishDate",firstSheet.getRow(i).getCell(ColumNames.indexOf("Lat.finish date")).toString());
            planning.put("earliestStartDate", firstSheet.getRow(i).getCell(ColumNames.indexOf("Earl.start date")).toString());
            planning.put("latestStartDate", firstSheet.getRow(i).getCell(ColumNames.indexOf("Latest start")).toString());
            planning.put("estimatedTime",
                   firstSheet.getRow(i).getCell( ColumNames.indexOf("Normal duration")).toString());
           Json.put("planning", planning);
          
        return Json;
    
    }
    
}
