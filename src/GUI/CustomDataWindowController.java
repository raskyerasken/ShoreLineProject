/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BLL.ReadingXLSX;
import com.jfoenix.controls.JFXButton;
import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author kasper
 */
public class CustomDataWindowController  implements Initializable{

    @FXML
    private Label taskXRun;
    @FXML
    private TreeView<String> CustomDataSelect;
    @FXML
    private AnchorPane customDataWindow;
    @FXML
    private Button btnCustumData;
    private FilesConvertionModel fcModel;
    @FXML
    private JFXButton addCustomData;
    @FXML
    private JFXButton removeCustomData;
    @FXML
    private TreeView<String> CustomDataAdded;
    
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
    private void importMenuSelect(Event event)  
    {
              FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("/GUI/MainWindow.fxml"));
        Parent root;
        try 
        {
            root = fxLoader.load();
            MainWindowController controller = fxLoader.getController();
            controller.setmodel(fcModel);
            customDataWindow.getChildren().setAll(root);
        } 
        catch (IOException ex) 
        {
            AlertWindow  alert = new AlertWindow("ExportWindow error", null, "It can show Exportview");
        }
    
    }

    @FXML
    private void exportMenuSelect(Event event)  
    {
        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("/GUI/ExportWindow.fxml"));
        Parent root;
        try 
        {
            root = fxLoader.load();
            ExportWindowController controller = fxLoader.getController();
            controller.setmodel(fcModel);
            customDataWindow.getChildren().setAll(root);
        }
        catch (IOException ex) 
        {
            AlertWindow  alert = new AlertWindow("ExportWindow error", null, "It can show Exportview");
        }
    }

    @FXML
    private void customDataMenuSelect(Event event) 
    {
        
    }

    @FXML
    private void logMenuSelect(Event event) 
    {
        
    }

    @FXML
    private void adminMenuSelect(ActionEvent event) 
    {
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) 
    {
        btnCustumData.setStyle("-fx-background-color: #588fe8;");
        TreeItem<String> rootItem = new TreeItem<String> ("Inbox");

        TreeItem<String> hey = new TreeItem<String> ("hey");
        TreeItem<String> hey2 = new TreeItem<String> ("hey2");
        rootItem.setExpanded(true);
        for (int i = 1; i < 6; i++) 
        {
            TreeItem<String> item = new TreeItem<String> ("Message" + i);            
            hey.getChildren().add(item);
        }   
                
        for (int i = 1; i < 6; i++) 
        {
            TreeItem<String> item = new TreeItem<String> ("Message" + i);            
            hey2.getChildren().add(item);
        }   
         
        rootItem.getChildren().add(hey);
        rootItem.getChildren().add(hey2);

        CustomDataAdded.setRoot(rootItem);
        
        
        
   }
 
    

    void setmodel(FilesConvertionModel fcModel) throws IOException, FileNotFoundException, ParseException {
    this.fcModel= fcModel;
     TreeItem<String> allFiles = new TreeItem<String> ("All files");
     
            for (TreeItem object : fcModel.getTreeFiles()) 
            {
                allFiles.getChildren().add(object);
            }
       
    CustomDataSelect.setRoot(allFiles);
    CustomDataSelect.setShowRoot(false);

    }

    @FXML
    private void addCustomData(ActionEvent event) 
    {
        
    }

    @FXML
    private void removeCustomData(ActionEvent event) 
    {
        
    }
}
