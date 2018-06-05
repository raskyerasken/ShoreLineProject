/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import GUI.Models.FilesConvertionModel;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author jacob
 */
public class ReadDataCSV
{

    FilesConvertionModel fcmodel;
    List<List<String>> alldata = new ArrayList<>();
    ObservableList<String> ColumNames;

    /*
    reading CSV file and it to a  List<List<String>>
     */
    public ReadDataCSV(String absolutePath, FilesConvertionModel fc) throws FileNotFoundException
    {
        fcmodel = fc;
        String fileName = absolutePath;
        File file = new File(fileName);

        Scanner inputStream;

        inputStream = new Scanner(file);

        while (inputStream.hasNext())
        {
            String line = inputStream.next();
            String[] values = line.split(",");

            alldata.add(Arrays.asList(values));
        }
        ColumNames = FXCollections.observableArrayList(alldata.get(0));;
        inputStream.close();
    }

    public ObservableList<String> getTitle()
    {
        return ColumNames;
    }

    public List<List<String>> getAllData()
    {
        return alldata;
    }
}
