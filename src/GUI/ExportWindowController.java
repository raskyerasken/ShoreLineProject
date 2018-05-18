/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BE.UpdateLog;
import BLL.BLLManagerUpdateLog;
import BLL.ReadingXLSX;
import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author kasper
 */
public class ExportWindowController implements Initializable {

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
    @FXML
    private JFXButton startTaskThread;
    @FXML
    private JFXButton pauseTaskThread;
    @FXML
    private JFXButton stopTaskThread;
    @FXML
    private JFXProgressBar progressBar;
    CompletableFuture com;
    double filesConvertedCount = 0;
    double allsize = 0;
    private boolean suspended;
    private volatile boolean paused = false;
    UpdateLog updateLog = new UpdateLog();
    boolean conversionSuccess;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnExport.setDisable(true);
        setButtonsInvisible();
    }

    @FXML
    private void startTask(ActionEvent event) {
        threading.getThreadGroup().resume();
    }

    @FXML
    private void pauseTask(ActionEvent event) {
        threading.suspend();
    }

    private void setButtonsInvisible() {
        progressBar.setVisible(false);
        startTaskThread.setDisable(true);
        stopTaskThread.setDisable(true);
        pauseTaskThread.setDisable(true);
    }

    @FXML
    private void stopTask(ActionEvent event) {
        threading.stop();
        setButtonsInvisible();
    }

    @FXML
    private void importMenuSelect(Event event) {
        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("/GUI/MainWindow.fxml"));
        Parent root;
        try {
            root = fxLoader.load();
            MainWindowController controller = fxLoader.getController();
            controller.setmodel(fcModel);
            controller.setmodel(modelData);
            exportWindow.getChildren().setAll(root);
        } catch (IOException ex) {
            AlertWindow alert = new AlertWindow("ExportWindow error", null, "It can show ImportView");
        }
    }

    @FXML
    private void customDataMenuSelect(Event event) throws FileNotFoundException, ParseException {

        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("/GUI/CustomDataWindow.fxml"));
        Parent root;
        try {
            root = fxLoader.load();
            CustomDataWindowController controller = fxLoader.getController();
            controller.setmodel(fcModel);
            controller.setmodel(modelData);
            exportWindow.getChildren().setAll(root);
        } catch (IOException ex) {
            AlertWindow alert = new AlertWindow("ExportWindow error", null, "It can show CustumData");
        }
    }

    @FXML
    private void logMenuSelect(Event event) {
        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("/GUI/LogView.fxml"));
        Parent root;
        try {
            root = fxLoader.load();
            LogViewController controller = fxLoader.getController();
            controller.setmodel(fcModel);
            controller.setmodel(modelData);
            exportWindow.getChildren().setAll(root);
        } catch (IOException ex) {
            AlertWindow alert = new AlertWindow("ExportWindow error", null, "It can show Logmenu");
        }
    }

    @FXML
    private void adminMenuSelect(ActionEvent event) {

    }

    private void updateLog() {
        try {
            up.setUpdateLog(updateLog);
        } catch (SQLException ex) {
            Logger.getLogger(ExportWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void convertData(ActionEvent event) throws JSONException {
        com = CompletableFuture.runAsync(() -> {
            List<File> progressFileList = new ArrayList<File>(fcModel.getFiles());
            filesConvertedCount = 0;
            allsize = progressFileList.size();
            for (File file : progressFileList) {

                try {
                    threading = Thread.currentThread();
                    ReadingXLSX XLSX = new ReadingXLSX(file.getAbsolutePath());

                    XLSX.getColumsNames();
                    File JsonFile = new File(file.getCanonicalFile() + ".json");
                    FileWriter fileWriter = new FileWriter(JsonFile);

                    for (JSONObject jSONObject : XLSX.allJSONObjectInFile()) {
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
                } catch (IOException ex) {
                    updateLog.setError(true);
                    updateLog.setAdjustment("File not support yet: " + file);

                    updateLog();
                } catch (ParseException | IllegalArgumentException | IllegalAccessException | JSONException ex) {
                    updateLog.setError(true);
                    updateLog.setAdjustment("Files Conversion wrong: " + file);
                    updateLog();
                }

                Platform.runLater(() -> {

                    startTaskThread.setDisable(fcModel.getFiles().isEmpty());
                    pauseTaskThread.setDisable(fcModel.getFiles().isEmpty());
                    stopTaskThread.setDisable(fcModel.getFiles().isEmpty());
                    fcModel.removeFile(file);
                    progressBar.setVisible(true);
                    progressBar.setProgress(filesConvertedCount / allsize);
                    if (fcModel.getFiles().isEmpty()) {
                        progressBar.setVisible(false);
                    }
                });
            }
        });
    }

    private void addToLog() throws SQLException {

        java.util.Date utilDate = new java.util.Date();
        Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
        java.sql.Timestamp sqlDate = new java.sql.Timestamp(currentTimestamp.getTime());

        updateLog.setUsername(modelData.getUserLogin());
        updateLog.setDatelog(sqlDate);

        updateLog.setError(suspended);
        up.setUpdateLog(updateLog);

    }

    private void addDataToLog() {
        Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
        java.sql.Timestamp sqlDate = new java.sql.Timestamp(currentTimestamp.getTime());

        updateLog.setUsername(modelData.getUserLogin());
        updateLog.setDatelog(sqlDate);
        updateLog.setError(conversionSuccess);
    }

    @FXML
    private void saveData(ActionEvent event) {

    }

    void setmodel(FilesConvertionModel fcModel) {
        this.fcModel = fcModel;
        taskField.setItems(fcModel.getFiles());
    }

    void setmodel(LoginDataModel modelData) {
        this.modelData = modelData;
    }

}
