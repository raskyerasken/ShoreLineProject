/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.Models.LoginDataModel;
import GUI.Models.FilesConvertionModel;
import java.awt.Checkbox;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

/**
 *
 * @author kasper
 */
public class CustomDataWindowController implements Initializable 
{
    LoginDataModel modelData;
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
    private Button adminButton;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) 
    {
        adminButton.setVisible(false);
        btnCustumData.setDisable(true);
//        CheckBoxTreeItem<String> rootItem
//                = new CheckBoxTreeItem<String>("view Source Files");
//        rootItem.setExpanded(true);
//        
//        CustomDataSelect.setCellFactory(CheckBoxTreeCell.<String>forTreeView());
//        
//        for (int i = 0; i < 8; i++) {
//            final CheckBoxTreeItem<String> checkBoxTreeItem
//                    = new CheckBoxTreeItem<String>("Sample" + (i + 1));
//            rootItem.getChildren().add(checkBoxTreeItem);
//        }
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
    
    @FXML
    private void importMenuSelect(Event event) throws SQLException 
    {
        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("/GUI/MainWindow.fxml"));
        Parent root;
        try {
            root = fxLoader.load();
            MainWindowController controller = fxLoader.getController();
            controller.setmodel(fcModel,modelData);
            customDataWindow.getChildren().setAll(root);
        } catch (IOException ex) {
            AlertWindow alert = new AlertWindow("ExportWindow error", null, "It can show ImportView");
        }
        
    }
    
    @FXML
    private void exportMenuSelect(Event event) throws SQLException {
        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("/GUI/ExportWindow.fxml"));
        Parent root;
        try {
            root = fxLoader.load();
            ExportWindowController controller = fxLoader.getController();
            controller.setmodel(fcModel,modelData);
            customDataWindow.getChildren().setAll(root);
        } catch (IOException ex) {
            AlertWindow alert = new AlertWindow("ExportWindow error", null, "It can show Exportview");
        }
    }
    
    @FXML
    private void logMenuSelect(Event event) throws SQLException {
        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("/GUI/LogView.fxml"));
        Parent root;
        try {
            root = fxLoader.load();
            LogViewController controller = fxLoader.getController();
            controller.setmodel(fcModel,modelData);
            customDataWindow.getChildren().setAll(root);
        } catch (IOException ex) {
            AlertWindow alert = new AlertWindow("ExportWindow error", null, "It can show LogView");
        }
    }
    
    @FXML
    private void adminMenuSelect(ActionEvent event) throws SQLException 
    {
        try 
        {
            FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("AddUserView.fxml"));
            Parent root = fxLoader.load();
            AddUserViewController controller = fxLoader.getController();
            controller.setmodel(fcModel,modelData);
            controller.setmodel(fcModel);
            controller.modelData(modelData);
            customDataWindow.getChildren().setAll(root);
        } 
        catch (IOException ex) 
        {
            AlertWindow alert = new AlertWindow("IOException", null, "IOException");
        }
    }
    
    void seePreview() {
        if (CustomDataSelect.getRoot() != null) {
            ObservableList<TreeItem<String>> JsonItems = CustomDataSelect.getRoot().getChildren().get(0).getChildren().get(0).getChildren();
            System.out.println(CustomDataSelect.getRoot().getChildren().get(0).getChildren());
            for (TreeItem<String> JsonItem : JsonItems) {
                //          Node check =  JsonItem.getGraphic();9
                //            System.out.println(check.isDisable());
                System.out.println(JsonItem.getValue());
                
            }
            
        }
    }
    
    void setmodel(FilesConvertionModel fcModel) throws IOException, FileNotFoundException, ParseException {
        this.modelData = modelData;
        this.fcModel = fcModel;
        TreeItem<String> allFiles = new TreeItem<String>("All files");
        for (TreeItem treeFile : fcModel.getTreeFiles()) {
            allFiles.getChildren().add(treeFile);
        }
        
        CustomDataSelect.setRoot(allFiles);
        CustomDataSelect.setShowRoot(false);
        CustomDataSelect.setEditable(true);
        CustomDataSelect.setCellFactory((TreeView<String> p)
                -> new TextFieldTreeCellImpl());
        CustomDataSelect.setEditable(true);
        CustomDataSelect.setCellFactory(new Callback<TreeView<String>, TreeCell<String>>() {
            @Override
            public TreeCell<String> call(TreeView<String> p) {
                return new TextFieldTreeCellImpl();
            }
        });
        
    }
    
    @FXML
    private void preview(ActionEvent event) 
    {
        seePreview();
    } 
    
    void setmodel(FilesConvertionModel fcModel, LoginDataModel modelData) throws SQLException 
    {
        this.modelData = modelData;
        this.fcModel=fcModel;
        isUserAdmin();
    }
    
    private void isUserAdmin() throws SQLException
    {
        System.out.println(modelData.getUserAccessLevel());
        if (modelData.getUserAccessLevel() == true) 
        {
            adminButton.setVisible(true);
        }
    }
}
