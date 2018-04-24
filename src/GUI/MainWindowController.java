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
import javafx.scene.control.MenuBar;

/**
 *
 * @author Jason and Freddy Kruger
 */
public class MainWindowController implements Initializable 
{
    
    @FXML
    private Label label;
    @FXML
    private Label taskXRun;
    @FXML
    private ListView<?> taskField;
    @FXML
    private MenuBar menuBar;
    
   
    private void handleButtonAction(ActionEvent event) 
    {
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        menuBar.setId("MenuBar");
    }   

    @FXML
    private void importData(ActionEvent event) {
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
   
    
    
  
}
