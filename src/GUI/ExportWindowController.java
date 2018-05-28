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
import BLL.ReadingXLSX;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXProgressBar;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
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
import javafx.stage.Stage;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author kasper
 */
public class ExportWindowController implements Initializable
{

    LoginDataModel modelData;
    BLL.BLLManagerUpdateLog up = new BLLManagerUpdateLog();
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
    private JFXButton startTaskThread;
    private JFXButton pauseTaskThread;
    private JFXButton stopTaskThread;
    private JFXProgressBar progressBar;
    CompletableFuture com;
    double filesConvertedCount = 0;
    double allsize = 0;
    private boolean suspended;
    private volatile boolean paused = false;
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
        newStage.setScene(scene);
        newStage.show();
    }

    private void updateLog()
    {
        try
        {
            up.setUpdateLog(updateLog);
        }
        catch (SQLException ex)
        {
            Logger.getLogger(ExportWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void addToLog() throws SQLException
    {

        java.util.Date utilDate = new java.util.Date();
        Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
        java.sql.Timestamp sqlDate = new java.sql.Timestamp(currentTimestamp.getTime());

        updateLog.setUsername(modelData.getUserLogin());
        updateLog.setDatelog(sqlDate);

        updateLog.setError(suspended);
        up.setUpdateLog(updateLog);

    }

    private void addDataToLog()
    {
        Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
        java.sql.Timestamp sqlDate = new java.sql.Timestamp(currentTimestamp.getTime());

        updateLog.setUsername(modelData.getUserLogin());
        updateLog.setDatelog(sqlDate);
        updateLog.setError(conversionSuccess);
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
        System.out.println(modelData.getUserAccessLevel());
        if (modelData.getUserAccessLevel() == true)
        {
            adminButton.setVisible(true);
        }
    }

    private void oldConverter()
    {
        com = CompletableFuture.runAsync(() ->
        {
            List<File> progressFileList = new ArrayList<File>(fcModel.getFiles());
            filesConvertedCount = 0;
            allsize = progressFileList.size();
            for (File file : progressFileList)
            {

                try
                {
                    threading = Thread.currentThread();
                    ReadingXLSX XLSX = new ReadingXLSX(file.getAbsolutePath(), fcModel);

                    XLSX.getColumsNames();
                    File JsonFile = new File(file.getCanonicalFile() + ".json");
                    FileWriter fileWriter = new FileWriter(JsonFile);

                    for (JSONObject jSONObject : XLSX.allJSONObjectInFile())
                    {
                        fileWriter.write(jSONObject.toString(4));
                    }
                    conversionSuccess = false;
                    addDataToLog();
                    updateLog.setError(false);
                    updateLog.setAdjustment("Conversion done: " + file);
                    //addToLog();
                    fileWriter.flush();
                    fileWriter.close();

                    filesConvertedCount++;
                }
                catch (IOException ex)
                {
                    updateLog.setError(true);
                    updateLog.setAdjustment("File is not support yet: " + file);

                    updateLog();
                }
                
                catch (IllegalArgumentException ex)
                {
                    updateLog.setError(true);
                    updateLog.setAdjustment("Illegal or inappropriate argument: ");

                    updateLog();
                }
                
                catch (ParseException ex)
                {
                    updateLog.setError(true);
                    updateLog.setAdjustment("Parse Exception: ");

                    updateLog();
                }
                
                catch (IllegalAccessException ex)
                {
                    updateLog.setError(true);
                    updateLog.setAdjustment("Illegal Access Exception: ");

                    updateLog();
                }
                
                catch (JSONException ex)
                {
                    updateLog.setError(true);
                    updateLog.setAdjustment("Wrong File type Converted: " + file);
                    updateLog();
                }

                Platform.runLater(() ->
                {
                    startTaskThread.setDisable(fcModel.getFiles().isEmpty());
                    pauseTaskThread.setDisable(fcModel.getFiles().isEmpty());
                    stopTaskThread.setDisable(fcModel.getFiles().isEmpty());
                    fcModel.removeFile(file);
                    progressBar.setVisible(true);
                    //progressBar.setProgress(ad / allsize);
                    if (fcModel.getFiles().isEmpty())
                    {
                        progressBar.setVisible(false);
                    }
                });
            }
            updateLog.setAdjustment("Converted: ");
            System.out.println(progressFileList);
            addDataToLog();
            try
            {
                System.out.println("hey");
                up.setUpdateLog(updateLog);
            }
            catch (SQLException ex)
            {
                Logger.getLogger(ExportWindowController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
}
