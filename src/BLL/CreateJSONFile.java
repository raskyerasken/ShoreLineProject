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
            fileWriter.write("{\n"
                    + json.getAssetSerialNumber() + "\n"
                    + json.getType() + "\n"
                    + json.getExternalWorkOrderId() + "\n"
                    + json.getSystemStatus() + "\n"
                    + json.getUserStatus() + "\n"
                    + formatter.format(json.getCreatedOn()) + "\n"
                    + json.getCreatedBy() + "\n"
                    + json.getName() + "\n"
                    + json.getPriority() + "\n"
                    + json.getStatus() + "\n{\n"
                    + formatter.format(json.getPlanning().getLatestFinishDate()) + "\n"
                    + formatter.format(json.getPlanning().getEarliestStartDate())+ "\n"
                    + formatter.format(json.getPlanning().getLatestStartDate())+ "\n"
                    + json.getPlanning().estimatedTimeProperty()+ "\n"
                    + "}\n}\n\n"
            );
        }
        

        fileWriter.flush();
        fileWriter.close();

    }
}
