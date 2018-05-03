/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BE.JSON;
import BLL.CreateJSONFile;
import BLL.ReadingXLSX;
import GUI.TEST.XmlToJava;
import GUI.TEST.xmlToJSON;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.text.ParseException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
public class MainWindowController implements Initializable {
boolean acceptfile=false;
    String[] acceptetFiles= {".xlsx"};
    List<File> files;
    @FXML
    private Label taskXRun;
    @FXML
    private ListView<?> taskField;

    public String selectedDocument = "C:\\Users\\jacob\\Desktop\\Import_data.xlsx";
    @FXML
    private AnchorPane importWindow;

    private void activateXmlReader() {

    }

    private void handleButtonAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }

    private void importDataClick(MouseEvent event) {

    }
List<File> filesAcceptet ;
    @FXML
    private void importData(ActionEvent event) throws SAXException, IOException, ParseException, IllegalArgumentException, IllegalAccessException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image File");
        fileChooser.setInitialDirectory(new File("..."));
        files = fileChooser.showOpenMultipleDialog(new Stage());
        
        for (File file : files) {
            for (String acceptetFile : acceptetFiles) {
                if(file.getAbsolutePath().endsWith(acceptetFile))
                {
                    filesAcceptet.add(file);
                    acceptfile=true;
                    
                }
                if (!acceptfile) {
                    
    AlertWindow alertWindow= 
            new AlertWindow("File not support yet", null, "This file "+file.getAbsolutePath()+" can be added");
                }
            }
            
        }
        if(!filesAcceptet.isEmpty()){
        for (int i = 0; i < filesAcceptet.size(); i++) {
            ReadingXLSX XLSX = new ReadingXLSX(filesAcceptet.get(i).getAbsolutePath());
            XLSX.allRows();
            XLSX.getColumsNames();
            CreateJSONFile createJSON = new CreateJSONFile();
            createJSON.createJSON(XLSX.allJSONObjectInFile(), filesAcceptet.get(i).getName());
        }}

    }

    @FXML
    private void convertData(ActionEvent event) {

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
    private void importMenuSelect(Event event) throws IOException {
         
    }

    @FXML
    private void exportMenuSelect(Event event) {
        System.out.println("hey");
    }

    @FXML
    private void customDataMenuSelect(Event event) {
    }
    
   @FXML
    private void logMenuSelect(Event event) throws IOException 
    {
        Stage newStage = new Stage();
        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("LogView.fxml"));
        Parent root = fxLoader.load();
        Scene scene = new Scene(root);
        newStage.setScene(scene);
        newStage.showAndWait();
    }

    @FXML
    private void adminMenuSelect(ActionEvent event) {
    }


}
