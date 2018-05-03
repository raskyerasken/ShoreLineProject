/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import BE.JSON;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *
 * @author jacob
 */
public class CreateJSONFile {
SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    /*
    Create json file with the name of the second argument "fileName"
    The JSON file contains the JSON files
     */
    public void createJSON(List<JSON> JSONsInThisDocument, String fileName) throws IOException {

        File file = new File(fileName + ".json");
        FileWriter fileWriter = new FileWriter(file);
        for (JSON json : JSONsInThisDocument) {
            fileWriter.write("{\n\t\"siteNamee\":\"\"\n\t\"assetSerialNumber\":\""
                    + json.getAssetSerialNumber() + "\"\n\t\"type\":\""
                    + json.getType() + "\"\n\t\"externalWorkOrderId\":\""
                    + json.getExternalWorkOrderId() + "\"\n\t\"systemStatus\":\""
                    + json.getSystemStatus() + "\"\n\t\"userStatus\":\""
                    + json.getUserStatus() + "\"\n\t\"createdOn\":\""
                    + formatter.format(json.getCreatedOn()) + "\"\n\t\"createdBy\":\""
                    + json.getCreatedBy() +  "\"\n\t\"name\":\""
                    + json.getName() +  "\"\n\t\"priority\":\""
                    + json.getPriority() +  "\"\n\t\"status\":\""
                    + json.getStatus() +  "\"\n\t\"planning\":{\n\t     \"latestFinishDate\":\""
                    + formatter.format(json.getPlanning().getLatestFinishDate()) +  "\"\n\t\"earliestStartDate\":\""
                    + formatter.format(json.getPlanning().getEarliestStartDate())+  "\"\n\t\"latestStartDate\":\""
                    + formatter.format(json.getPlanning().getLatestStartDate())+  "\"\n\t\"estimatedTime\":\""
                    + json.getPlanning().estimatedTimeProperty()+  "\"\n\t}\n}"
                    
            );
        }
        

        fileWriter.flush();
        fileWriter.close();

    }
}
