/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author kasper
 */
public class CustomDataWindowController  implements Initializable{

    @FXML
    private Label taskXRun;
    @FXML
    private ListView<?> CustomDataSelect;
    @FXML
    private AnchorPane customDataWindow;
    @FXML
    private Button btnCustumData;

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
//        
//        AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/ImportWindow.fxml"));
//                customDataWindow.getChildren().setAll(pane);
//    }
//
//    @FXML
//    private void exportMenuSelect(Event event) throws IOException {
//        
//        AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/ExportWindow.fxml"));
//                customDataWindow.getChildren().setAll(pane);
//    }
//
//    @FXML
//    private void customDataMenuSelect(Event event) throws IOException {
//        
//              
//    }
//
//    @FXML
//    private void logMenuSelect(Event event) throws IOException {
//        
//                AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/LogView.fxml"));
//                customDataWindow.getChildren().setAll(pane);
//    }
//    

    @FXML
    private void importMenuSelect(Event event) throws IOException {
              AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/MainWindow.fxml"));
                customDataWindow.getChildren().setAll(pane);
    }

    @FXML
    private void exportMenuSelect(Event event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/ExportWindow.fxml"));
                customDataWindow.getChildren().setAll(pane);
    }

    @FXML
    private void customDataMenuSelect(Event event) {
    }

    @FXML
    private void logMenuSelect(Event event) {
    }

    @FXML
    private void adminMenuSelect(ActionEvent event) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    btnCustumData.setStyle("-fx-background-color: #588fe8;");
    }
}
