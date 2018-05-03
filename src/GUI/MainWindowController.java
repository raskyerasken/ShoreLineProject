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

    String[] acceptetFiles= new String[1];
    List<File> files;
    @FXML
    private Label taskXRun;
    @FXML
    private ListView<?> taskField;

    public String selectedDocument = "C:\\Users\\jacob\\Desktop\\Import_data.xlsx";
    @FXML
    private AnchorPane importWindow;
    @FXML
    private Tab exportMenuSelect;
    @FXML
    private Tab customDataMenuSelect;
    @FXML
    private Tab logMenuSelect;
    @FXML
    private Tab importMenu;

    private void activateXmlReader() {

    }

    private void handleButtonAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        menuBar.setId("MenuBar");
       
    }

    private void importDataClick(MouseEvent event) {

    }

    @FXML
    private void importData(ActionEvent event) throws SAXException, IOException, ParseException, IllegalArgumentException, IllegalAccessException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image File");
        fileChooser.setInitialDirectory(new File("..."));
        files = fileChooser.showOpenMultipleDialog(new Stage());
      
        for (int i = 0; i < files.size(); i++) {
            ReadingXLSX XLSX = new ReadingXLSX(files.get(i).getAbsolutePath());
            XLSX.allRows();
            XLSX.getColumsNames();
            CreateJSONFile createJSON = new CreateJSONFile();
            createJSON.createJSON(XLSX.allJSONObjectInFile(), files.get(i).getName());
        }

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

    @FXML
    private void importMenuSelect(Event event) throws IOException {
           
        
    }

    @FXML
    private void exportMenuSelect(Event event) throws IOException {
                AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/ExportWindow.fxml"));
                importWindow.getChildren().setAll(pane);

    }

    @FXML
    private void customDataMenuSelect(Event event) throws IOException {
        
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/CustomDataWindow.fxml"));
                importWindow.getChildren().setAll(pane);
    }

    @FXML
    private void logMenuSelect(Event event) throws IOException {
        
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/Logview.fxml"));
                importWindow.getChildren().setAll(pane);
    }
    


}
