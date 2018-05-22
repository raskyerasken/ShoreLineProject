/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import BE.JSONAttributes;
import BE.JSONCustommize;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
        row = workbook.getNumberOfNames();
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
   
    
    private JSONObject setJSONObject(int i) throws JSONException, ParseException, IOException {
        JSONCustommize custom = new JSONCustommize();
        
        custom.setType("Order Type");
        custom.setExternalWorkOrderId("Order");
        custom.setStatus("System status");
        custom.setUserStatus("User status");
        custom.setName("Opr. short text");
        custom.setPriority("Priority");
      custom.setLatestFinishDate("Lat.finish date");
      custom.setEarlistStartDate("Earl.start date");
      custom.setLatestStartDate("Latest start");
      custom.setEstimatedTime("Normal duration");
        JSONObject Json = new JSONObject();
        Json.put("siteName", "");
        Json.put("assetSerialNumber", "0");
        Json.put("type", firstSheet.getRow(i).getCell(ColumNames.indexOf(custom.getType())).toString());
        Json.put("externalWorkOrderId", firstSheet.getRow(i).getCell(ColumNames.indexOf(custom.getExternalWorkOrderId())).toString());
        Json.put("systemStatus", firstSheet.getRow(i).getCell(ColumNames.indexOf(custom.getStatus())).toString());
        Json.put("userStatus", firstSheet.getRow(i).getCell(ColumNames.indexOf(custom.getUserStatus())).toString());
        Json.put("createdOn", today.getDate() + "-" + today.getMonth() + "-" + (today.getYear() + 1900));
        Json.put("createdBy", "SAP");
        if (firstSheet.getRow(i).getCell(ColumNames.indexOf(custom.getName())).toString().isEmpty()) {
            Json.put("name", firstSheet.getRow(i).getCell(ColumNames.indexOf("Description2")).toString());
        } else {
            Json.put("name", firstSheet.getRow(i).getCell(ColumNames.indexOf(custom.getName())).toString());
        }
        if (firstSheet.getRow(i).getCell(ColumNames.indexOf(custom.getPriority())).toString().isEmpty()) {
            Json.put("priority", "Low");
        } else {
            Json.put("priority", firstSheet.getRow(i).getCell(ColumNames.indexOf(custom.getPriority())).toString());
        }
        Json.put("status", "new");
        JSONObject planning = new JSONObject();
        planning.put("latestFinishDate", firstSheet.getRow(i).getCell(ColumNames.indexOf(custom.getLatestFinishDate())).toString());
        planning.put("earliestStartDate", firstSheet.getRow(i).getCell(ColumNames.indexOf(custom.getEarlistStartDate())).toString());
        planning.put("latestStartDate", firstSheet.getRow(i).getCell(ColumNames.indexOf(custom.getLatestStartDate())).toString());
        planning.put("estimatedTime",
                firstSheet.getRow(i).getCell(ColumNames.indexOf(custom.getEstimatedTime())).toString());
        Json.put("planning", planning);

        return Json;

    }

}
