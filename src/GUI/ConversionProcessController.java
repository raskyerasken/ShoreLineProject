/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BE.UpdateLog;
import BLL.BLLManagerUpdateLog;
import BLL.ReadingXLSX;
import GUI.Models.FilesConvertionModel;
import GUI.Models.LoginDataModel;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXProgressBar;
import java.io.File;
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author ander
 */
public class ConversionProcessController implements Initializable 
{

    @FXML
    private JFXListView<File> taskField;
    @FXML
    private JFXButton startTaskThread;
    @FXML
    private JFXButton pauseTaskThread;
    @FXML
    private JFXButton stopTaskThread;
    @FXML
    private Label taskXRun;
    @FXML
    private AnchorPane ConversionProcess;
    CompletableFuture com;
    double filesConvertedCount = 0;
    double allsize = 0;
    @FXML
    private JFXProgressBar progressBar;
    private FilesConvertionModel fcModel;
    private Thread threading = null;
    boolean conversionSuccess;
    UpdateLog updateLog = new UpdateLog();
    LoginDataModel modelData;
    BLL.BLLManagerUpdateLog up = new BLLManagerUpdateLog();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        setButtonsInvisible();
        conversionProgress();
        
    }    

    @FXML
    private void startTask(ActionEvent event) 
    {
        threading.resume();
    }

    @FXML
    private void pauseTask(ActionEvent event) 
    {
        threading.suspend();
    }

    @FXML
    private void stopTask(ActionEvent event) 
    {
        threading.stop();
        closeWindowWhenProcessisDone();
    }
    
    private void closeStage() throws SQLException, IOException
    {
        if (conversionSuccess == false) 
        {
            
            FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("/GUI/ConversionProcess.fxml"));
            Parent root = fxLoader.load();
            Scene scene = new Scene(root);
            System.out.println("wwwww");
            Stage stage = (Stage) pauseTaskThread.getScene().getWindow();
            stage.close();
        }
    }
    
    private void conversionProgress()
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
                    ReadingXLSX XLSX = new ReadingXLSX(file.getAbsolutePath(),fcModel);

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
                    updateLog.setAdjustment("Conversion done: " + file.getName());
                    //addToLog();
                    fileWriter.flush();
                    fileWriter.close();

                    filesConvertedCount++;
                                       try 
            {
                
                up.setUpdateLog(updateLog);
            } 
            catch (SQLException ex) 
            {
                Logger.getLogger(ExportWindowController.class.getName()).log(Level.SEVERE, null, ex);
            }
                } 
                catch (IOException ex) 
                {
                    updateLog.setError(true);
                    updateLog.setAdjustment("File not support yet: " + file.getName());

                    updateLog();
                } 
                catch (ParseException | IllegalArgumentException | IllegalAccessException | JSONException ex) 
                {
                    updateLog.setError(true);
                    updateLog.setAdjustment("Files Conversion wrong: " + file.getName());
                    updateLog();
                }

                Platform.runLater(() -> 
                {
                    startTaskThread.setDisable(fcModel.getFiles().isEmpty());
                    pauseTaskThread.setDisable(fcModel.getFiles().isEmpty());
                    stopTaskThread.setDisable(fcModel.getFiles().isEmpty());
                    fcModel.removeFile(file);
                    progressBar.setVisible(true);
                    progressBar.setProgress(filesConvertedCount / allsize);
                    if (fcModel.getFiles().isEmpty()) 
                    {
                        progressBar.setVisible(false);
                        closeWindowWhenProcessisDone();
                    }
                });
            }          
             
        });          
    }
    
    
    private void addDataToLog() 
    {
        Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
        java.sql.Timestamp sqlDate = new java.sql.Timestamp(currentTimestamp.getTime());

        updateLog.setUsername(modelData.getUserLogin());
        updateLog.setDatelog(sqlDate);
        updateLog.setError(conversionSuccess);
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
    
    private void setButtonsInvisible() 
    {
        progressBar.setVisible(false);
        startTaskThread.setDisable(true);
        stopTaskThread.setDisable(true);
        pauseTaskThread.setDisable(true);
    }

    void setmodel(FilesConvertionModel fcModel, LoginDataModel modelData) 
    {
        this.fcModel = fcModel;
        taskField.setItems(fcModel.getFiles());
        this.modelData = modelData;
    }
    private void closeWindowWhenProcessisDone()
    {
        Stage stage = (Stage) pauseTaskThread.getScene().getWindow();
        stage.close();
    }
}
