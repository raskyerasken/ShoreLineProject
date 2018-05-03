/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author kasper
 */
public class CustomDataWindowController {

    @FXML
    private Label taskXRun;
    @FXML
    private ListView<?> CustomDataSelect;
    @FXML
    private AnchorPane customDataWindow;

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
    private void importMenuSelect(Event event) {
    }

    @FXML
    private void exportMenuSelect(Event event) {
    }

    @FXML
    private void customDataMenuSelect(Event event) {
    }

    @FXML
    private void logMenuSelect(Event event) {
    }
}
