/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
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
    private TreeView<?> CustomDataSelect;
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
    private TreeView<?> CustomDataAdded;

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
    private void importMenuSelect(Event event)  {
        
             FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("/GUI/MainWindow.fxml"));
        Parent root;
        try {
            root = fxLoader.load();
                MainWindowController controller = fxLoader.getController();
        controller.setmodel(fcModel);
        customDataWindow.getChildren().setAll(root);
        } catch (IOException ex) {
            AlertWindow  alert = new AlertWindow("ExportWindow error", null, "It can show Exportview");
        }
    
    }

    @FXML
    private void exportMenuSelect(Event event)  {
     
                
             FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("/GUI/ExportWindow.fxml"));
        Parent root;
        try {
            root = fxLoader.load();
            ExportWindowController controller = fxLoader.getController();
        controller.setmodel(fcModel);
        customDataWindow.getChildren().setAll(root);
        } catch (IOException ex) {
            AlertWindow  alert = new AlertWindow("ExportWindow error", null, "It can show Exportview");
        }
        
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
    DefaultMutableTreeNode top =
        new DefaultMutableTreeNode("The Java Series");
    }

    void setmodel(FilesConvertionModel fcModel) {
    this.fcModel= fcModel;
    
    }

    @FXML
    private void addCustomData(ActionEvent event) {
    }

    @FXML
    private void removeCustomData(ActionEvent event) {
    }
    
    
    @FXML
    private void treeview() 
    {
        TreeItem<String> root = new TreeItem<String>("Root Node");
        root.setExpanded(true);
        root.getChildren().addAll(
        new TreeItem<String>("Item 1"),
        new TreeItem<String>("Item 2"),
        new TreeItem<String>("Item 3")
        );
    }
}
