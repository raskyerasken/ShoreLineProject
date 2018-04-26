/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.TEST.XmlToJava;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.xml.sax.SAXException;

/**
 *
 * @author Jason and Freddy Kruger
 */
public class MainWindowController implements Initializable 
{
    List<File> files ;
    @FXML
    private Label taskXRun;
    @FXML
    private ListView<?> taskField;
    @FXML
    private MenuBar menuBar;
    
    public String selectedDocument="C:\\Users\\jacob\\Desktop\\Import_data.xlsx";
    
   
    private void handleButtonAction(ActionEvent event) 
    {
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        menuBar.setId("MenuBar");
    }   

    private void importDataClick(MouseEvent event) {
    
    }
    @FXML
<<<<<<< HEAD
    private void importData(ActionEvent event) throws SAXException {
=======
    private void importData(ActionEvent event) 
    {
>>>>>>> ca3091afb4607d981c7a453615ca6317a817654a
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image File");
         files = fileChooser.showOpenMultipleDialog(new Stage());
//        FileNameExtensionFilter filter = new FileNameExtensionFilter();
//        FileNameExtensionFilter filter2 = new FileNameExtensionFilter();
//        chooser.setFileFilter(filter);
//        chooser.setFileFilter(filter2);
//        chooser.setAcceptAllFileFilterUsed(false);
XmlToJava xml =new XmlToJava();
xml.startDocument();
xml.startElement(selectedDocument, "hasd", selectedDocument, null);
     
    }

    @FXML
    private void convertData(ActionEvent event) 
    {
        
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
    {}
        
 
    
    void stageToFront()
    {
        Stage stage = (Stage) taskField.getScene().getWindow();
        stage.toFront();

    }

}
