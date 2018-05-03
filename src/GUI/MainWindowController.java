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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.input.MouseEvent;
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

    List<File> files;
    @FXML
    private Label taskXRun;
    @FXML
    private ListView<?> taskField;
    @FXML
    private MenuBar menuBar;

    public String selectedDocument = "C:\\Users\\jacob\\Desktop\\Import_data.xlsx";

    private void activateXmlReader() {

    }

    private void handleButtonAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        menuBar.setId("MenuBar");
        xmlToJSON hey = new xmlToJSON();
        try {
            hey.main();
        } catch (JSONException ex) {
            Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void importDataClick(MouseEvent event) {

    }

    @FXML
    private void importData(ActionEvent event) throws SAXException, IOException, ParseException {
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
    private void exportData(ActionEvent event) {
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

}
