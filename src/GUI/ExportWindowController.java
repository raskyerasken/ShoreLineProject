/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author kasper
 */
public class ExportWindowController {

    @FXML
    private Label taskXRun;
    @FXML
    private AnchorPane exportWindow;
    @FXML
    private TableView<?> LogView;
    @FXML
    private TableColumn<?, ?> userNameTable;
    @FXML
    private TableColumn<?, ?> timeTable;
    @FXML
    private TableColumn<?, ?> adjustment;
    @FXML
    private JFXTextField searchTxt;

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
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/MainWindow.fxml"));
            exportWindow.getChildren().setAll(pane);    
    }

    @FXML
    private void customDataMenuSelect(Event event) {
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
    
}
