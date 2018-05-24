/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BE.JSONCustommize;
import BLL.ReadingXLSX;
import GUI.Models.LoginDataModel;
import GUI.Models.FilesConvertionModel;
import com.jfoenix.controls.JFXComboBox;
import java.awt.Checkbox;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

/**
 *
 * @author kasper
 */
public class CustomDataWindowController implements Initializable {

    JSONCustommize custom = new JSONCustommize();
    LoginDataModel modelData;
    private TreeView<String> CustomDataSelect;
    @FXML
    private AnchorPane customDataWindow;
    @FXML
    private Button btnCustumData;
    private FilesConvertionModel fcModel;
    private TextField textField;
    @FXML
    private Button adminButton;
    @FXML
    private ListView<String> columNameExcel;
    @FXML
    private JFXComboBox<JSONCustommize> comboboxCustom;
    @FXML
    private TextField txtName;
    @FXML
    private GridPane gridCustom;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        adminButton.setVisible(false);
        btnCustumData.setDisable(true);
//      
    }

    @FXML
    private void importMenuSelect(Event event) throws SQLException {
        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("/GUI/MainWindow.fxml"));
        Parent root;
        try {
            root = fxLoader.load();
            MainWindowController controller = fxLoader.getController();
            controller.setmodel(fcModel, modelData);
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
            controller.setmodel(fcModel, modelData);
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
            controller.setmodel(fcModel, modelData);
            customDataWindow.getChildren().setAll(root);
        } catch (IOException ex) {
            AlertWindow alert = new AlertWindow("ExportWindow error", null, "It can show LogView");
        }
    }

    @FXML
    private void adminMenuSelect(ActionEvent event) throws SQLException {
        try {
            FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("AddUserView.fxml"));
            Parent root = fxLoader.load();
            AddUserViewController controller = fxLoader.getController();
            controller.setmodel(fcModel, modelData);
            controller.setmodel(fcModel);
            controller.modelData(modelData);
            customDataWindow.getChildren().setAll(root);
        } catch (IOException ex) {
            AlertWindow alert = new AlertWindow("IOException", null, "IOException");
        }
    }

    void setmodel(FilesConvertionModel fcModel, LoginDataModel modelData) throws SQLException, IOException, FileNotFoundException, ParseException {
        this.modelData = modelData;
        this.fcModel = fcModel;
        isUserAdmin();
        columNameExcel.getItems().clear();
        if (!fcModel.getFiles().isEmpty()) {
            ReadingXLSX xlsx = new ReadingXLSX(fcModel.getFiles().get(0).getAbsolutePath(), fcModel);
            columNameExcel.setItems((ObservableList<String>) xlsx.getColumsNames());
        }
        comboboxCustom.setItems(fcModel.getCustom());
    }

    private void isUserAdmin() throws SQLException {
        System.out.println(modelData.getUserAccessLevel());
        if (modelData.getUserAccessLevel() == true) {
            adminButton.setVisible(true);
        }
    }

    @FXML
    private void custom(ActionEvent event) {
        Button node = (Button) event.getSource();
        GridPane grid = (GridPane) node.getParent();
        int number = grid.getChildren().indexOf(node) + 1;

        Label text = (Label) grid.getChildren().get(
                grid.getChildren().indexOf(node) + 1);
        if (!columNameExcel.getSelectionModel().isEmpty()) {
            String select = columNameExcel.getSelectionModel().getSelectedItem();
            text.setText(select);
            switch (number) {
                case 1:
                    custom.setType(select);
                    break;
                case 3:
                    custom.setExternalWorkOrderId(select);
                    break;
                case 5:
                    custom.setSystemStatus(select);
                    break;
                case 7:
                    custom.setUserStatus(select);
                    break;
                case 9:
                    custom.setName(select);
                    break;
                case 11:
                    custom.setPriority(select);
                    break;
                case 14:
                    custom.setLatestFinishDate(select);
                    break;
                case 16:
                    custom.setEarlistStartDate(select);
                    break;
                case 18:
                    custom.setLatestStartDate(select);
                    break;
                case 20:
                    custom.setEstimatedTime(select);
                    break;

            }

        }
    }

    @FXML
    private void save(ActionEvent event) {
        if (!txtName.getText().isEmpty()) {
            custom.setNameTable(txtName.getText());
            try {
                fcModel.saveCustom(custom);
            } catch (SQLException ex) {
                Logger.getLogger(CustomDataWindowController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void changeText(ActionEvent event) {
        
        if (!comboboxCustom.getSelectionModel().isEmpty()) {
            custom = comboboxCustom.getSelectionModel().getSelectedItem();

            for (int i = 0; i < 21; i++) {
                Label text = (Label) gridCustom.getChildren().get(1);
                text.setText(custom.getType());
                Label text1 = (Label) gridCustom.getChildren().get(3);
                text1.setText(custom.getExternalWorkOrderId());
                Label text2 = (Label) gridCustom.getChildren().get(5);
                text2.setText(custom.getSystemStatus());
                Label text3 = (Label) gridCustom.getChildren().get(7);
                text3.setText(custom.getUserStatus());
                Label text4 = (Label) gridCustom.getChildren().get(9);
                text4.setText(custom.getName());
                Label text5 = (Label) gridCustom.getChildren().get(11);
                text5.setText(custom.getPriority());
                Label text6 = (Label) gridCustom.getChildren().get(14);
                text6.setText(custom.getLatestFinishDate());
                Label text7 = (Label) gridCustom.getChildren().get(16);
                text7.setText(custom.getEarlistStartDate());
                Label text8 = (Label) gridCustom.getChildren().get(18);
                text8.setText(custom.getLatestStartDate());
                Label text9 = (Label) gridCustom.getChildren().get(20);
                text9.setText(custom.getEstimatedTime());

            }

        }
    }
}
