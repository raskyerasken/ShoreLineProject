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
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
    private void importMenuSelect(Event event) throws IOException {
    
            
             FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("/GUI/MainWindow.fxml"));
        Parent root = fxLoader.load();
        MainWindowController controller = fxLoader.getController();
        controller.setmodel(fcModel);
        exportWindow.getChildren().setAll(root);
    }

    @FXML
    private void customDataMenuSelect(Event event) throws IOException {
   
             FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("/GUI/CustomDataWindow.fxml"));
        Parent root = fxLoader.load();
        CustomDataWindowController controller = fxLoader.getController();
        controller.setmodel(fcModel);
        exportWindow.getChildren().setAll(root);
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
    private void convertData(ActionEvent event) {
        
//        for (int i = 0; i < filesAcceptet.size(); i++) {
//            ReadingXLSX XLSX = new ReadingXLSX(filesAcceptet.get(i).getAbsolutePath());
//            XLSX.allRows();
//            XLSX.getColumsNames();
//            CreateJSONFile createJSON = new CreateJSONFile();
//            createJSON.createJSON(XLSX.allJSONObjectInFile(), filesAcceptet.get(i).getName());
//        }
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
