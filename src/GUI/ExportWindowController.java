/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BE.JSONCustommize;
import GUI.Models.LoginDataModel;
import GUI.Models.FilesConvertionModel;
import BE.UpdateLog;
import BLL.BLLManagerUpdateLog;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.json.JSONException;

/**
 *
 * @author kasper
 */
public class ExportWindowController implements Initializable
{

    LoginDataModel modelData;
    private Thread threading = null;
    @FXML
    private Label taskXRun;
    @FXML
    private AnchorPane exportWindow;
    @FXML
    private JFXListView<File> taskField;
    @FXML
    private Button btnExport;
    private FilesConvertionModel fcModel;
    CompletableFuture com;
    double filesConvertedCount = 0;
    double allsize = 0;
    UpdateLog updateLog = new UpdateLog();
    boolean conversionSuccess;
    @FXML
    private Button adminButton;
    @FXML
    private JFXComboBox<JSONCustommize> combos;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        btnExport.setDisable(true);
        adminButton.setVisible(false);
    }

    @FXML
    private void convertData(ActionEvent event) throws JSONException, SQLException, IOException
    {
        fcModel.setCustomClass(combos.getSelectionModel().getSelectedItem());
        Stage newStage = new Stage();
        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("ConversionProcess.fxml"));
        Parent root = fxLoader.load();
        ConversionProcessController controller = fxLoader.getController();
        controller.setmodel(fcModel, modelData);
        Scene scene = new Scene(root);
        newStage.setResizable(false);
        newStage.setTitle("Conversion Process");
        newStage.setScene(scene);
        newStage.show();
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
            exportWindow.getChildren().setAll(root);
        }
        catch (IOException ex)
        {
            AlertWindow alert = new AlertWindow("ExportWindow error", null, "It can't show ImportView");
        }
    }

    @FXML
    private void customDataMenuSelect(Event event) throws FileNotFoundException, ParseException, SQLException
    {

        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("/GUI/CustomDataWindow.fxml"));
        Parent root;
        try
        {
            root = fxLoader.load();
            CustomDataWindowController controller = fxLoader.getController();
            controller.setmodel(fcModel, modelData);
            exportWindow.getChildren().setAll(root);
        }
        catch (IOException ex)
        {
            AlertWindow alert = new AlertWindow("ExportWindow error", null, "It can't show CustumData");
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
            exportWindow.getChildren().setAll(root);
        }
        catch (IOException ex)
        {
            AlertWindow alert = new AlertWindow("ExportWindow error", null, "It can't show Logmenu");
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

    @FXML
    private void saveData(ActionEvent event)
    {

    }

    void setmodel(FilesConvertionModel fcModel, LoginDataModel modelData) throws SQLException
    {
        this.fcModel = fcModel;
        taskField.setItems(fcModel.getFiles());
        this.modelData = modelData;
        isUserAdmin();
        combos.setItems(fcModel.getCustom());

    }

    private void isUserAdmin() throws SQLException
    {
        if (modelData.getUserAccessLevel() == true)
        {
            adminButton.setVisible(true);
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
