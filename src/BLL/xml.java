package BLL;

import BE.JSONCustommize;
import GUI.Models.FilesConvertionModel;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Callback;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.json.JSONException;
import org.json.JSONObject;

public class xml
{

    FilesConvertionModel fcmodel;
    List<List<String>> alldata = new ArrayList<>();
    ObservableList<String> ColumNames;
    DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
    Date today = new Date();

    public xml(String absolutePath, FilesConvertionModel fc) throws FileNotFoundException
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

    public List<JSONObject> allJSONObjectInFile() throws ParseException, IllegalArgumentException, IllegalAccessException, JSONException, IOException
    {
        List<JSONObject> JSONList = new ArrayList<>();
        System.out.println(alldata.size());
        for (int i = 1; i < alldata.size(); i++)
        {
            JSONObject newJSON = setJSONObject(i);

            JSONList.add(newJSON);
        }
        return JSONList;
    }

    private JSONObject setJSONObject(int i) throws JSONException, ParseException, IOException
    {
        JSONCustommize custom = fcmodel.getCustomClass();
        System.out.println("hdsfsdey");
        JSONObject Json = new JSONObject();
        Json.put("siteName", "");
        Json.put("assetSerialNumber", "0");
        Json.put("type", alldata.get(i).get(alldata.get(0).indexOf(custom.getType())));
        Json.put("externalWorkOrderId", alldata.get(i).get(alldata.get(0).indexOf(custom.getExternalWorkOrderId())));
        Json.put("systemStatus", alldata.get(i).get(alldata.get(0).indexOf(custom.getSystemStatus())));
        Json.put("userStatus", alldata.get(i).get(alldata.get(0).indexOf(custom.getUserStatus())));
        Json.put("createdOn", today.getDate() + "-" + today.getMonth() + "-" + (today.getYear() + 1900));
        Json.put("createdBy", "SAP");

        Json.put("name", alldata.get(i).get(alldata.get(0).indexOf(custom.getName())));

        Json.put("priority", alldata.get(i).get(alldata.get(0).indexOf(custom.getPriority())));

        Json.put("status", "new");
        JSONObject planning = new JSONObject();
        planning.put("latestFinishDate", alldata.get(i).get(alldata.get(0).indexOf(custom.getLatestFinishDate())));
        planning.put("earliestStartDate", alldata.get(i).get(alldata.get(0).indexOf(custom.getEarlistStartDate())));
        planning.put("latestStartDate", alldata.get(i).get(alldata.get(0).indexOf(custom.getLatestStartDate())));
        planning.put("estimatedTime",
                alldata.get(i).get(alldata.get(0).indexOf(custom.getEstimatedTime())));;
        Json.put("planning", planning);

        return Json;

    }

}
