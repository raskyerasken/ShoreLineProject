/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BE.JSON;
import BE.UpdateLog;
import BLL.BLLManagerUpdateLog;
import BLL.CreateJSONFile;
import BLL.ReadingXLSX;
import GUI.Converter.XmlToJava;
import GUI.Converter.xmlToJSON;
import GUI.Threading.ShoreLineThreading;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXProgressBar;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import static jdk.nashorn.internal.objects.NativeRegExp.test;
import org.json.JSONException;
import org.xml.sax.SAXException;

/**
 *
 * @author Jason and Freddy Kruger
 */
public class MainWindowController implements Initializable {

    private ShoreLineThreading threading = null;
    Parent root;

    LoginDataModel modelData = new LoginDataModel();
    LoginViewController loginID;
    boolean acceptFile = false;
    String[] acceptedFiles = {".xlsx"};
    List<File> files;
    @FXML
    private Label taskXRun;
    @FXML
    private ListView<File> taskField;

    public String selectedDocument = "C:\\Users\\jacob\\Desktop\\Import_data.xlsx";
    @FXML
    private AnchorPane importWindow;
    @FXML
    private Button importbtn;
    private final ObservableList<File> filesAccepted
            = FXCollections.observableArrayList();
    private final ObservableList<File> filesNotAccepted
            = FXCollections.observableArrayList();
    private FilesConvertionModel fcModel;
    private final Thread t = null;

    CustomDataWindowController cdwc = new CustomDataWindowController();
    @FXML
    private JFXProgressBar progressBar;
    @FXML
    private JFXButton pauseTaskThread;
    @FXML
    private JFXButton startTaskThread;
    @FXML
    private JFXButton stopTaskThread;
    ReadingXLSX XLSX = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        importbtn.setStyle("-fx-background-color: #588fe8;");
//        startTaskThread.setDisable(true);
//        stopTaskThread.setDisable(true);
//        pauseTaskThread.setDisable(true);
        progressBar.setVisible(false);
//        progressBar.setVisible(false);
    }

    @FXML
    private void importData(ActionEvent event) throws SQLException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image File");
        // fileChooser.setInitialDirectory(new File("..."));
        files = fileChooser.showOpenMultipleDialog(new Stage());
        UpdateLog updateLog = new UpdateLog();
        BLL.BLLManagerUpdateLog up = new BLLManagerUpdateLog();
        CompletableFuture.runAsync(()
                -> {

            for (File file : files) {
                acceptFile = false;
                for (String acceptedFile : acceptedFiles) {
                    if (file.getAbsolutePath().endsWith(acceptedFile)) {

                        filesAccepted.add(file);
                        acceptFile = true;
                    }
                    if (!acceptFile) {
                        filesNotAccepted.add(file);
                    }
                }
            }

//                    updateLog.setUsername(modelData.getUserLogin());
//                    updateLog.setAdjustment("Exported files " + files);
//                    updateLog.setDatelog(sqlDate);
//                    
//                    try 
//                    {
//                        up.setUpdateLog(updateLog);
//                    } 
//                    catch (SQLException ex) 
//                    {
//                        Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                    up.setUpdateLog(updateLog);
//                    if (acceptetFile.getAbsolutePath().endsWith(acceptetFile)) 
//                    {
//                        filesAccepted.clear();
//                        filesAccepted.add(file);
//                        
//                         
//                        acceptFile = true;
//
//                        Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
//                        java.sql.Timestamp sqlDate = new java.sql.Timestamp(currentTimestamp.getTime());
//
//                        updateLog.setUsername(modelData.getUserLogin());
//                        updateLog.setAdjustment("Exported files " + files);
//                        updateLog.setDatelog(sqlDate);
//
//                        try 
//                        {
//                            up.setUpdateLog(updateLog);
//                        } 
//                        catch (SQLException ex) 
//                        {
//                            Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
//                        }
//                        try 
//                        {
//                            up.setUpdateLog(updateLog);
//                        } 
//                        catch (SQLException ex) 
//                        {
//                            Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
//                        }
//                        
//                    }
//                    if (!acceptFile) 
//                    {
//                        AlertWindow alert = new AlertWindow("File not support yet", null, "This file " + file.getAbsolutePath() + " can be added");
//                    
//                    acceptFile=false;
//                }
//            }
            Platform.runLater(()
                    -> {

                fcModel.AddAllFiles(filesAccepted);
                for (File acceptedFile : filesNotAccepted) {
                     AlertWindow alertWindow
                        = new AlertWindow("File not support yet", null, "This file " + acceptedFile.getName() + " can be added");

                }
               
                filesNotAccepted.clear();
            });
  Date date = new Date();
            TreeItem<String> newFilesAdded = new TreeItem<String>("("+filesAccepted.size()+")"+date.toGMTString());

            File acceptedFile =filesAccepted.get(0);
                TreeItem<String> newlyAdded
                        = new TreeItem<String>(acceptedFile.getName());
                try {
                    XLSX = new ReadingXLSX(acceptedFile.getAbsolutePath());
                } catch (IOException ex) {
                    Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
                }
                for (Object string : XLSX.getColumsNames()) 
                {CheckBox check = new CheckBox();
                check.setSelected(true);
                    TreeItem<String> colum = new TreeItem<String>(string.toString());
                    colum.setGraphic(check);
                    newlyAdded.getChildren().add(colum);
                     
                }
                newFilesAdded.getChildren().add(newlyAdded);

            
            fcModel.setTreeFiles(newFilesAdded);
        });

    }

    @FXML
    private void startTask(ActionEvent event) {
        threading.start();
    }

    @FXML
    private void pauseTask(ActionEvent event) {
        if (threading.isSuspended()) {
            threading.resume();
            pauseTaskThread.setText("Pause Slideshow");
        } else {
            threading.pause();
            pauseTaskThread.setText("Resume Slideshow");
        }
    }

    @FXML
    private void stopTask(ActionEvent event) {
        threading.stop();
    }

    void stageToFront() {
        Stage stage = (Stage) taskField.getScene().getWindow();
        stage.toFront();
    }

//
//    @FXML
//    private void exportMenuSelect(Event event) throws IOException {
//                AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/ExportWindow.fxml"));
//                importWindow.getChildren().setAll(pane);
//
//    }
//
//    @FXML
//    private void customDataMenuSelect(Event event) throws IOException {
//        
//        AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/CustomDataWindow.fxml"));
//                importWindow.getChildren().setAll(pane);
//    }
//
//    @FXML
//    private void logMenuSelect(Event event) throws IOException {
//        
//        AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/Logview.fxml"));
//                importWindow.getChildren().setAll(pane);
//    }
//    
    @FXML
    private void importMenuSelect(Event event) {

    }

    @FXML
    private void exportMenuSelect(Event event) {
        try {
            FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("/GUI/ExportWindow.fxml"));
            Parent root = fxLoader.load();
            ExportWindowController controller = fxLoader.getController();
            controller.setmodel(fcModel);
            importWindow.getChildren().setAll(root);
        } catch (IOException ex) {
            AlertWindow alert = new AlertWindow("IOException", null, "IOException");
        }

    }

    @FXML
    private void customDataMenuSelect(Event event) throws FileNotFoundException, ParseException {

        try {
            FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("/GUI/CustomDataWindow.fxml"));
            Parent root = fxLoader.load();
            CustomDataWindowController controller = fxLoader.getController();
            controller.setmodel(fcModel);
            importWindow.getChildren().setAll(root);
        } catch (IOException ex) {
            AlertWindow alert = new AlertWindow("IOException", null, "IOException");
        }

    }

    @FXML
    private void logMenuSelect(Event event) {
        try {
            Stage newStage = new Stage();
            FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("LogView.fxml"));
            Parent root = fxLoader.load();
            Scene scene = new Scene(root);
            newStage.setScene(scene);
            newStage.setResizable(false);
            newStage.showAndWait();
        } catch (IOException ex) {
            AlertWindow alert = new AlertWindow("IOException", null, "IOException");
        }
    }

    private void activateXmlReader() {

    }

    private void importDataClick(MouseEvent event) {

    }

    @FXML
    private void adminMenuSelect(ActionEvent event) {

    }

    void modelData(LoginDataModel modelData) {
        this.modelData = modelData;
    }

    void setmodel(FilesConvertionModel fcModel) {
        this.fcModel = fcModel;
        taskField.getItems().clear();
        taskField.setItems(fcModel.getFiles());
    }
}
