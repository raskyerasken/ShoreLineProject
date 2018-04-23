/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

/**
 *
 * @author kasper
 */
public class MainWindowController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private Button importData;
    @FXML
    private Button convertData;
    @FXML
    private Button exportData;
    @FXML
    private Button startTask;
    @FXML
    private Button pauseTask;
    @FXML
    private Button stopTask;
    @FXML
    private Label taskXRun;
    @FXML
    private ListView<?> taskField;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
