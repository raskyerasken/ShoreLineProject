/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BE.JSON;
import BE.UpdateLog;
import BLL.BLLManagerUpdateLog;
import BLL.CreateJSONFile;
import BLL.ReadingXLSX;
import GUI.TEST.XmlToJava;
import GUI.TEST.xmlToJSON;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import static jdk.nashorn.internal.objects.NativeRegExp.test;
import org.json.JSONException;
import org.xml.sax.SAXException;

/**
 *
 * @author Jason and Freddy Kruger
 */
public class MainWindowController implements Initializable 
{
    
    LoginDataModel modelData = new LoginDataModel();
    LoginViewController loginID;
    boolean acceptfile = false;
    String[] acceptetFiles = {".xlsx"};
    List<File> files;
    @FXML
    private Label taskXRun;
    @FXML
    private ListView<File> taskField;

    public String selectedDocument = "C:\\Users\\jacob\\Desktop\\Import_data.xlsx";
    @FXML
    private AnchorPane importWindow;
    @FXML
    private Button importbtn;
    private final ObservableList<File> filesAcceptet
            = FXCollections.observableArrayList();
    private FilesConvertionModel fcModel;

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        importbtn.setStyle("-fx-background-color: #588fe8;");
    }
    
    private void activateXmlReader() {

    }

    private void importDataClick(MouseEvent event)
    {

    }



    @FXML
    private void importData(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image File");
        fileChooser.setInitialDirectory(new File("..."));
        files = fileChooser.showOpenMultipleDialog(new Stage());
        UpdateLog updateLog = new UpdateLog();
        BLL.BLLManagerUpdateLog up = new BLLManagerUpdateLog();
        
        for (File file : files) {
            for (String acceptetFile : acceptetFiles) {
                if (file.getAbsolutePath().endsWith(acceptetFile)) {
                    filesAcceptet.clear();
                    filesAcceptet.add(file);
                    acceptfile = true;
<<<<<<< HEAD
                    
                    Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
                    java.sql.Timestamp sqlDate = new java.sql.Timestamp(currentTimestamp.getTime());
                    
                    
                    updateLog.setUsername(modelData.getUserLogin());
                    updateLog.setAdjustment("Exported files " + files);
                    updateLog.setDatelog(sqlDate);
                    up.setUpdateLog(updateLog);
                    
                    System.out.println("what i am trying to do: "+modelData.getUserLogin().toString());
=======
//                    
//                    Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
//                    java.sql.Timestamp sqlDate = new java.sql.Timestamp(currentTimestamp.getTime());
//        
//                    updateLog.setUsername(loginID.userNameID.getText());
//                    updateLog.setAdjustment("Exported files " + files);
//                    updateLog.setDatelog(sqlDate);
//                    up.setUpdateLog(updateLog);
//                    
//                    System.out.println("writes");
>>>>>>> 177c120ba5303a54d7d6c496806c04a993492939

                }
                if (!acceptfile) {

                    AlertWindow alertWindow
                            = new AlertWindow("File not support yet", null, "This file " + file.getAbsolutePath() + " can be added");
                }
                acceptfile=false;
            }
            fcModel.setFiles(filesAcceptet);
     
        }
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

    void stageToFront() {
        Stage stage = (Stage) taskField.getScene().getWindow();
        stage.toFront();

    }

//
//    @FXML
//    private void exportMenuSelect(Event event) throws IOException {
//                AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/ExportWindow.fxml"));
//                importWindow.getChildren().setAll(pane);
//
//    }
//
//    @FXML
//    private void customDataMenuSelect(Event event) throws IOException {
//        
//        AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/CustomDataWindow.fxml"));
//                importWindow.getChildren().setAll(pane);
//    }
//
//    @FXML
//    private void logMenuSelect(Event event) throws IOException {
//        
//        AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/Logview.fxml"));
//                importWindow.getChildren().setAll(pane);
//    }
//    
    @FXML
    private void importMenuSelect(Event event) {

    }

    @FXML
    private void exportMenuSelect(Event event)  {
        try {
            FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("/GUI/ExportWindow.fxml"));
            Parent root = fxLoader.load();
            ExportWindowController controller = fxLoader.getController();
            controller.setmodel(fcModel);
            importWindow.getChildren().setAll(root);
        } catch (IOException ex) {
            AlertWindow  alert = new AlertWindow("IOException", null, "IOException");
        }


    }

    @FXML
    private void customDataMenuSelect(Event event) {
     
        try {
            FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("/GUI/CustomDataWindow.fxml"));
            Parent root = fxLoader.load();
            CustomDataWindowController controller = fxLoader.getController();
            controller.setmodel(fcModel);
            importWindow.getChildren().setAll(root);
        } catch (IOException ex) {
            AlertWindow  alert = new AlertWindow("IOException", null, "IOException");
        }
        
    }

    @FXML
    private void logMenuSelect(Event event)  {
        try {
            Stage newStage = new Stage();
            FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("LogView.fxml"));
            Parent root = fxLoader.load();
            Scene scene = new Scene(root);
            newStage.setScene(scene);
            newStage.setResizable(false);
            newStage.showAndWait();
        } catch (IOException ex) {
            AlertWindow  alert = new AlertWindow("IOException", null, "IOException");
        }
    }

    @FXML
    private void adminMenuSelect(ActionEvent event) {
    }

<<<<<<< HEAD
    void modelData(LoginDataModel modelData) 
    {
        this.modelData = modelData;
    }
=======
   

    void setmodel(FilesConvertionModel fcModel) {
      this.fcModel=fcModel;
    taskField.getItems().clear();
       taskField.setItems(fcModel.getFiles());}
>>>>>>> 177c120ba5303a54d7d6c496806c04a993492939

}
