/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

/**
 *
 * @author kasper
 */
public class CustomDataWindowController implements Initializable 
{

    @FXML
    private Label taskXRun;
    @FXML
    private TreeView<String> CustomDataSelect;
    @FXML
    private AnchorPane customDataWindow;
    @FXML
    private Button btnCustumData;
    private FilesConvertionModel fcModel;
    private TextField textField;

    @FXML
    private void startTask(ActionEvent event) 
    {

    }

    @FXML
    private void pauseTask(ActionEvent event) 
    {

    }

    @FXML
    private void stopTask(ActionEvent event) 
    {

    }

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
            AlertWindow alert = new AlertWindow("ExportWindow error", null, "It can show Exportview");
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
            AlertWindow alert = new AlertWindow("ExportWindow error", null, "It can show Exportview");
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
    public void initialize(URL location, ResourceBundle resources) {
        btnCustumData.setStyle("-fx-background-color: #588fe8;");
//        TreeItem<String> hey = new TreeItem<String> ("hey");
//        TreeItem<String> hey2 = new TreeItem<String> ("hey2");
//        rootItem.setExpanded(true);
//        for (int i = 1; i < 6; i++) 
//        {
//            TreeItem<String> item = new TreeItem<String> ("Message" + i);            
//            hey.getChildren().add(item);
//        }   
//                
//        for (int i = 1; i < 6; i++) 
//        {
//            TreeItem<String> item = new TreeItem<String> ("Message" + i);            
//            hey2.getChildren().add(item);
//        }   
//        rootItem.getChildren().add(hey);
//        rootItem.getChildren().add(hey2);

//        CustomDataAdded.setRoot(rootItem);
    }

    void setmodel(FilesConvertionModel fcModel) throws IOException, FileNotFoundException, ParseException 
    {
        this.fcModel = fcModel;
        TreeItem<String> allFiles = new TreeItem<String>("All files");
        for (TreeItem object : fcModel.getTreeFiles()) 
        {
            allFiles.getChildren().add(object);
            TreeItem<String> hey = new TreeItem<String>("hey");

            for (TreeItem treeFile : fcModel.getTreeFiles()) 
            {
                hey.getChildren().add(treeFile);
            }
            CustomDataSelect.setRoot(hey);

            CustomDataSelect.setEditable(true);
            CustomDataSelect.setCellFactory((TreeView<String> p)
                    -> new TextFieldTreeCellImpl());
            CustomDataSelect.setEditable(true);
            CustomDataSelect.setCellFactory(new Callback<TreeView<String>, TreeCell<String>>() {
                @Override
                public TreeCell<String> call(TreeView<String> p) 
                {
                    return new TextFieldTreeCellImpl();
                }
            });
        }
    }
}
