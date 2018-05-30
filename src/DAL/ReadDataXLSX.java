/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import GUI.Models.FilesConvertionModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author jacob
 */
public class ReadDataXLSX {

    FilesConvertionModel fcmodel;
    String excelFilePath;
    FileInputStream inputStream;
    int row;
    int colum;
    List<List<String>> alldata = new ArrayList<>();
    Workbook workbook;
    org.apache.poi.ss.usermodel.Sheet firstSheet;
    ObservableList<String> ColumNames = FXCollections.observableArrayList();

    public ReadDataXLSX(String excelPath, FilesConvertionModel fcmodel) throws FileNotFoundException, IOException, ParseException {
        this.fcmodel = fcmodel;
        excelFilePath = excelPath;

        inputStream = new FileInputStream(new File(excelFilePath));

        workbook = new XSSFWorkbook(inputStream);;

        firstSheet = workbook.getSheetAt(0);
        row = workbook.getNumberOfNames();
    }

    public ObservableList<String> getColumsNames() {

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

    public List<List<String>> getAllData() {

        colum = ColumNames.size();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < colum; j++) {
                String[] values = null;
                values[j] = firstSheet.getRow(i).getCell(j).toString();
                alldata.add(Arrays.asList(values));

            }
        }

        return alldata;

    }

}
