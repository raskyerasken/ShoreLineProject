/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BE.JSONCustommize;
import BLL.convertToJson;
import GUI.Models.LoginDataModel;
import GUI.Models.FilesConvertionModel;
import com.jfoenix.controls.JFXComboBox;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author kasper
 */
public class CustomDataWindowController implements Initializable
{

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
    public void initialize(URL location, ResourceBundle resources)
    {

        adminButton.setVisible(false);
        btnCustumData.setDisable(true);
//      
    }

    @FXML
    private void importMenuSelect(Event event) throws SQLException
    {
        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("/GUI/MainWindow.fxml"));
        Parent root;
        try
        {
            root = fxLoader.load();
            MainWindowController controller = fxLoader.getController();
            controller.setmodel(fcModel, modelData);
            customDataWindow.getChildren().setAll(root);
        }
        catch (IOException ex)
        {
            AlertWindow alert = new AlertWindow("ExportWindow error", null, "It can show ImportView");
        }

    }

    @FXML
    private void exportMenuSelect(Event event) throws SQLException
    {
        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("/GUI/ExportWindow.fxml"));
        Parent root;
        try
        {
            root = fxLoader.load();
            ExportWindowController controller = fxLoader.getController();
            controller.setmodel(fcModel, modelData);
            customDataWindow.getChildren().setAll(root);
        }
        catch (IOException ex)
        {
            AlertWindow alert = new AlertWindow("ExportWindow error", null, "It can show Exportview");
        }
    }

    @FXML
    private void logMenuSelect(Event event) throws SQLException
    {
        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("/GUI/LogView.fxml"));
        Parent root;
        try
        {
            root = fxLoader.load();
            LogViewController controller = fxLoader.getController();
            controller.setmodel(fcModel, modelData);
            customDataWindow.getChildren().setAll(root);
        }
        catch (IOException ex)
        {
            AlertWindow alert = new AlertWindow("ExportWindow error", null, "It can show LogView");
        }
    }

    @FXML
    private void adminMenuSelect(ActionEvent event) throws SQLException, IOException
    {
        Stage newStage = new Stage();
        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("AddUserView.fxml"));
        Parent root = fxLoader.load();
        AddUserViewController controller = fxLoader.getController();
        controller.setmodel(fcModel, modelData);
        Scene scene = new Scene(root);
        newStage.setResizable(false);
        newStage.setAlwaysOnTop(true);
        newStage.setTitle("Admin Window");
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.setScene(scene);
        newStage.show();
    }
 /*
    Set list view and models 
     */
    void setmodel(FilesConvertionModel fcModel, LoginDataModel modelData) throws SQLException, IOException, FileNotFoundException, ParseException
    {
        this.modelData = modelData;
        this.fcModel = fcModel;
        isUserAdmin();
        columNameExcel.getItems().clear();
        if (!fcModel.getFiles().isEmpty())
        {
            if (fcModel.getFiles().get(0).getAbsolutePath().endsWith(".xlsx"))
            {
                convertToJson xlsx = new convertToJson(fcModel.getFiles().get(0).getAbsolutePath(), fcModel);
                columNameExcel.setItems((ObservableList<String>) xlsx.getTitleXLSX());
            }
            else if (fcModel.getFiles().get(0).getAbsolutePath().endsWith(".csv"))
            {
                convertToJson csvData = new convertToJson(fcModel.getFiles().get(0).getAbsolutePath(), fcModel);
                columNameExcel.setItems((ObservableList<String>) csvData.getTitleCSV());
            }

        }
        comboboxCustom.setItems(fcModel.getCustom());
    }

    private void isUserAdmin() throws SQLException
    {
        
        if (modelData.getUserAccessLevel() == true)
        {
            adminButton.setVisible(true);
        }
    }
     /*
   Set the value form the specific buttom
     */

    @FXML
    private void custom(ActionEvent event)
    {
        Button node = (Button) event.getSource();
        GridPane grid = (GridPane) node.getParent();
        int number = grid.getChildren().indexOf(node) + 1;

        Label text = (Label) grid.getChildren().get(
                grid.getChildren().indexOf(node) + 1);
        if (!columNameExcel.getSelectionModel().isEmpty())
        {
            String select = columNameExcel.getSelectionModel().getSelectedItem();
            text.setText(select);
            switch (number)
            {
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
                case 13:
                    custom.setLatestFinishDate(select);
                    break;
                case 15:
                    custom.setEarlistStartDate(select);
                    break;
                case 17:
                    custom.setLatestStartDate(select);
                    break;
                case 19:
                    custom.setEstimatedTime(select);
                    break;

            }

        }
    }
 /*
    Save the customization
     */
    @FXML
    private void save(ActionEvent event)
    {
        if (!txtName.getText().isEmpty())
        {
            custom.setNameTable(txtName.getText());
            try
            {
                fcModel.saveCustom(custom);
            }
            catch (SQLException ex)
            {
                Logger.getLogger(CustomDataWindowController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
 /*
 Set the texxt for the customization they select in the drop down
     */
    @FXML
    private void changeText(ActionEvent event)
    {

        if (!comboboxCustom.getSelectionModel().isEmpty())
        {
            custom = comboboxCustom.getSelectionModel().getSelectedItem();

            for (int i = 0; i < 21; i++)
            {
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
                Label text6 = (Label) gridCustom.getChildren().get(13);
                text6.setText(custom.getLatestFinishDate());
                Label text7 = (Label) gridCustom.getChildren().get(15);
                text7.setText(custom.getEarlistStartDate());
                Label text8 = (Label) gridCustom.getChildren().get(17);
                text8.setText(custom.getLatestStartDate());
                Label text9 = (Label) gridCustom.getChildren().get(19);
                text9.setText(custom.getEstimatedTime());

            }

        }
    }

    @FXML
    private void logOut(ActionEvent event) throws IOException
    {
        Stage stage = (Stage) adminButton.getScene().getWindow();
        stage.close();

        Stage newStage = new Stage();
        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("LoginView.fxml"));
        Parent root = fxLoader.load();
        LoginViewController controller = fxLoader.getController();
        Scene scene = new Scene(root);
        newStage.setResizable(false);
        newStage.setAlwaysOnTop(true);
        newStage.setTitle("Login Window");
        newStage.setScene(scene);
        newStage.show();
    }
}
