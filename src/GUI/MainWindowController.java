/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.TEST.XmlToJava;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

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
    
    public String selectedDocument;
    
    XmlToJava xtj = new XmlToJava();
   
    private void handleButtonAction(ActionEvent event) 
    {
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        menuBar.setId("MenuBar");
    }   

<<<<<<< HEAD
    private void importDataClick(MouseEvent event) {
=======
<<<<<<< HEAD
=======
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
    private void stopTask(ActionEvent event) 
    {
>>>>>>> d628d8bc7e2674ed864cdde23f1ba8091dc8990e
        
    }
    private void importDataClick(MouseEvent event) 
    {
       
    }

<<<<<<< HEAD
    private void importDataClick(ActionEvent event) 
    {
=======
    private void importDataClick(ActionEvent event) {
>>>>>>> 2f2c7e63414b033cfef782eeff7ddfa7f835d917
        
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
//        FileNameExtensionFilter filter = new FileNameExtensionFilter();
//        FileNameExtensionFilter filter2 = new FileNameExtensionFilter();
//        chooser.setFileFilter(filter);
//        chooser.setFileFilter(filter2);
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.setDialogTitle("choosertitle");
        
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) 
        { 
            stageToFront();
        } 
        selectedDocument=""+chooser.getSelectedFile();
        stageToFront();
        
    }
    
    void stageToFront()
    {
<<<<<<< HEAD
        Stage stage = (Stage) taskField.getScene().getWindow();
        stage.toFront();
=======

    }
<<<<<<< HEAD
=======

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
>>>>>>> d628d8bc7e2674ed864cdde23f1ba8091dc8990e

    
<<<<<<< HEAD
    }
=======
    
>>>>>>> be63f12d2b6c5d0113320364ad773d493c331e61
   
    
    
  
>>>>>>> 2f2c7e63414b033cfef782eeff7ddfa7f835d917
>>>>>>> d628d8bc7e2674ed864cdde23f1ba8091dc8990e
}
