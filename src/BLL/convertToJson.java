package BLL;

import BE.JSONCustommize;
import DAL.ReadDataCSV;
import DAL.ReadDataXLSX;
import GUI.Models.FilesConvertionModel;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.collections.ObservableList;
import org.json.JSONException;
import org.json.JSONObject;

public class convertToJson
{

    FilesConvertionModel fcmodel;
    List<List<String>> alldata = new ArrayList<>();
    DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
    Date today = new Date();
    String fileName;

    public convertToJson(String absolutePath, FilesConvertionModel fc) throws FileNotFoundException
    {
        fcmodel = fc;
        fileName = absolutePath;

    }

    public ObservableList<String> getTitleXLSX() throws FileNotFoundException, IOException, ParseException
    {
        ReadDataXLSX XLSX = new ReadDataXLSX(fileName, fcmodel);
        return XLSX.getColumsNames();
    }

    /*
     return a lost of Json object xlsx
     */
    public List<JSONObject> allJSONObjectInFileXLSX() throws ParseException, IllegalArgumentException, IllegalAccessException, JSONException, IOException
    {
        ReadDataXLSX XLSX = new ReadDataXLSX(fileName, fcmodel);
        List<JSONObject> JSONList = new ArrayList<>();
        alldata = XLSX.getAllData();
        for (int i = 1; i < alldata.size(); i++)
        {
            JSONObject newJSON = setJSONObject(i);

            JSONList.add(newJSON);
        }
        return JSONList;
    }

    public ObservableList<String> getTitleCSV() throws FileNotFoundException
    {
        ReadDataCSV csv = new ReadDataCSV(fileName, fcmodel);
        return csv.getTitle();
    }

    public List<JSONObject> allJSONObjectInFileCSV() throws ParseException, IllegalArgumentException, IllegalAccessException, JSONException, IOException
    {
        ReadDataCSV csv = new ReadDataCSV(fileName, fcmodel);
        alldata = csv.getAllData();
        List<JSONObject> JSONList = new ArrayList<>();

        for (int i = 1; i < alldata.size(); i++)
        {
            JSONObject newJSON = setJSONObject(i);

            JSONList.add(newJSON);
        }
        return JSONList;
    }

    /*
    Set each JsonObject 
     */
    private JSONObject setJSONObject(int i) throws JSONException, ParseException, IOException
    {
        JSONCustommize custom = fcmodel.getCustomClass();
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
