/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BLL.CreateJSONFile;
import BLL.ReadingXLSX;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author kasper
 */
public class ExportWindowController implements Initializable{

    @FXML
    private Label taskXRun;
    @FXML
    private AnchorPane exportWindow;
    @FXML
    private JFXListView<File> taskField;
    @FXML
    private Button btnExport;
    private FilesConvertionModel fcModel;

    @FXML
    private void importData(ActionEvent event) {
    }


    @FXML
    private void startTask(ActionEvent event) {
    }

    @FXML
    private void pauseTask(ActionEvent event) {
    }

    @FXML
    private void stopTask(ActionEvent event) {
    }


//    @FXML
//    private void importMenuSelect(Event event) throws IOException {
//            AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/ImportWindow.fxml"));
//            exportWindow.getChildren().setAll(pane);     
//    }
//
//    @FXML
//    private void customDataMenuSelect(Event event) throws IOException {
//           AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/CustomDataWindow.fxml"));
//                exportWindow.getChildren().setAll(pane);     
//    }
//
//    @FXML
//    private void logMenuSelect(Event event) throws IOException {
//        
//                AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/LogView.fxml"));
//                exportWindow.getChildren().setAll(pane);     
//    }

    @FXML
    private void importMenuSelect(Event event)  {
    
            
             FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("/GUI/MainWindow.fxml"));
        Parent root;
        try {
            root = fxLoader.load();
              MainWindowController controller = fxLoader.getController();
        controller.setmodel(fcModel);
        exportWindow.getChildren().setAll(root);
        } catch (IOException ex) {
              AlertWindow  alert = new AlertWindow("ExportWindow error", null, "It can show Exportview");
        }
      
    }

    @FXML
    private void customDataMenuSelect(Event event) throws FileNotFoundException, ParseException {
   
             FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("/GUI/CustomDataWindow.fxml"));
        Parent root;
        try {
            root = fxLoader.load();
            CustomDataWindowController controller = fxLoader.getController();
        controller.setmodel(fcModel);
        exportWindow.getChildren().setAll(root);
        } catch (IOException ex) {
            AlertWindow  alert = new AlertWindow("ExportWindow error", null, "It can show Exportview");
        }
        
    }

    @FXML
    private void logMenuSelect(Event event) {
    }

    @FXML
    private void exportMenuSelect(ActionEvent event) {
    }

    @FXML
    private void adminMenuSelect(ActionEvent event) {
    }

    @FXML
    private void convertData(ActionEvent event) throws JSONException {
        
        for (int i = 0; i < fcModel.getFiles().size(); i++) {
            try {
                ReadingXLSX XLSX = new ReadingXLSX(fcModel.getFiles().get(i).getAbsolutePath());
                XLSX.allRows();
                XLSX.getColumsNames();
                CreateJSONFile createJSON = new CreateJSONFile();
                 File file = new File(fcModel.getFiles().get(i).getCanonicalFile()+ ".json");
        FileWriter fileWriter = new FileWriter(file);
        
                for (JSONObject jSONObject : XLSX.allJSONObjectInFile()) {
                    fileWriter.write(jSONObject.toString(4));
                    
                    
                }
         fileWriter.flush();
        fileWriter.close();
                
            } catch (IOException ex) {
                AlertWindow  alert = new AlertWindow("IOException", null, "IOException");
            } catch (ParseException ex) {
                AlertWindow  alert = new AlertWindow("ParseException", null, "ParseException");
            } catch (IllegalArgumentException ex) {
                 AlertWindow  alert = new AlertWindow("IllegalArgumentException", null, "IllegalArgumentException");
            } catch (IllegalAccessException ex) {
                  AlertWindow  alert = new AlertWindow("IllegalAccessException", null, "IllegalAccessException");
            }
        }
        fcModel.clearFiles();
    }

    @FXML
    private void saveData(ActionEvent event) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    btnExport.setStyle("-fx-background-color: #588fe8;");
    
    }


    void setmodel(FilesConvertionModel fcModel) {
     this.fcModel=fcModel;
    taskField.setItems(fcModel.getFiles());}
    
}
