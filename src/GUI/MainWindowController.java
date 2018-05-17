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
import com.jfoenix.controls.JFXProgressBar;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Jason and Freddy Kruger
 */
public class MainWindowController implements Initializable {

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

    public ObservableList<File> fileNames
            = FXCollections.observableArrayList();

    CustomDataWindowController cdwc = new CustomDataWindowController();
    @FXML
    private JFXProgressBar progressBar;
    @FXML
    private JFXButton pauseTaskThread;
    @FXML
    private JFXButton stopTaskThread;
    ReadingXLSX XLSX = null;
    @FXML
    private JFXButton startTaskThead;

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        importbtn.setDisable(true);
        progressBar.setVisible(false);
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

            filesAccepted.clear();
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
            TreeItem<String> newFilesAdded = new TreeItem<String>("(" + filesAccepted.size() + ")" + date.toGMTString());

            File acceptedFile = filesAccepted.get(0);
            TreeItem<String> newlyAdded
                    = new TreeItem<String>(acceptedFile.getName());
            try {
                XLSX = new ReadingXLSX(acceptedFile.getAbsolutePath());
            } catch (IOException ex) {
                Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(MainWindowController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            for (Object string : XLSX.getColumsNames()) {
                CheckBox check = new CheckBox();
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

    }

    @FXML
    private void pauseTask(ActionEvent event) {

    }

    @FXML
    private void stopTask(ActionEvent event) {

    }

    void stageToFront() {
        Stage stage = (Stage) taskField.getScene().getWindow();
        stage.toFront();
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
    private void logMenuSelect(Event event) 
    {
        try 
        {
            FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("LogView.fxml"));
            Parent root = fxLoader.load();
            LogViewController controller = fxLoader.getController();
            controller.setmodel(fcModel);
            importWindow.getChildren().setAll(root);
        } 
        catch (IOException ex) 
        {
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

    public String getTextNames() {
        fileNames = (ObservableList<File>) fcModel;

        return fileNames.toString();
    }
}
