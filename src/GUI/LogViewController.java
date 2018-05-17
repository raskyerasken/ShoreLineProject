/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BE.UpdateLog;
import static GUI.LogViewController.lines;
import com.jfoenix.controls.JFXTextField;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author ander
 */
public class LogViewController implements Initializable {

    LoginDataModel modelData;
    UpdateLogViewModel model = new UpdateLogViewModel();
    public static final ObservableList lines
            = FXCollections.observableArrayList();
    int fileLinesNumber = 2;
    @FXML
    private JFXTextField searchTxt;
    boolean search = false;
    FilteredList<String> searchData
            = new FilteredList<>(lines, p -> true);
    private FilesConvertionModel fcModel;
    @FXML
    private TableView<UpdateLog> LogView;
    @FXML
    private TableColumn<UpdateLog, String> userNameTable;
    @FXML
    private TableColumn<UpdateLog, Date> timeTable;
    @FXML
    private TableColumn<UpdateLog, String> adjustment;
    @FXML
    private TableColumn<UpdateLog, Boolean> error;
    CompletableFuture com;
    private Thread thread = null;
    long timeWaitThread = 500;
    @FXML
    private AnchorPane exportWindow;
    @FXML
    private Button logMenuSelect;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        logMenuSelect.setDisable(true);
        addColumsToTableView();   //get all the colums
        LogView.setItems(model.getUpdateLogToList());
        Thread t = new Thread(()
                -> {
            model.logListUpdate();
            searchLogView();
        });
        t.start();
    }

    private void readUserLoginTxt() {
        //Should read the file
//        BufferedReader br;
//        try 
//        {
//            br = new BufferedReader(new FileReader("UserLogin.txt"));
//            String line = br.readLine();
//            while (line != null)
//            {
//                lines.add(line);
//                line = br.readLine();
//                LogView.setItems(lines);
//            }
//        } 
//        catch (FileNotFoundException ex) 
//        {
//            AlertWindow  alert = new AlertWindow("File not found", null, "Can find file Userlogin");
//        } 
//        catch (IOException ex)
//        {
//            AlertWindow  alert = new AlertWindow("Can not read the file", null, "It can not read the file");
//        }
    }

    private void addColumsToTableView() {
        userNameTable.setCellValueFactory(new PropertyValueFactory("Username"));
        timeTable.setCellValueFactory(new PropertyValueFactory("Datelog"));
        adjustment.setCellValueFactory(new PropertyValueFactory("Adjustment"));
        error.setCellValueFactory(new PropertyValueFactory("Error"));
    }

    private void filterTableView() {
        FilteredList<UpdateLog> filteredData;
        filteredData = new FilteredList<>(model.getUpdateLogToList(), p -> true);
        searchTxt.textProperty().addListener((observable, oldValue, newValue)
                -> {
            filteredData.setPredicate(updateLog
                    -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (updateLog.getUsername().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (updateLog.getDatelog().toString().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }

                return false;

            });

        });
        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<UpdateLog> sortedData = new SortedList<>(filteredData);
        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(LogView.comparatorProperty());
        // 5. Add sorted (and filtered) data to the table.
        LogView.setItems(sortedData);
    }

    private void searchLogView() {
        filterTableView();
    }

    private void displayLoginText() {
        readUserLoginTxt();
    }

    @FXML
    private void importMenuSelect(ActionEvent event) throws IOException {
        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("/GUI/MainWindow.fxml"));
        Parent root;
        try {
            root = fxLoader.load();
            MainWindowController controller = fxLoader.getController();
            controller.setmodel(fcModel);
            controller.setmodel(modelData);
            exportWindow.getChildren().setAll(root);
        } catch (IOException ex) {
            AlertWindow alert = new AlertWindow("ExportWindow error", null, "It can show Exportview");
        }
    }

    @FXML
    private void exportMenuSelect(ActionEvent event) {
        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("/GUI/ExportWindow.fxml"));
        Parent root;
        try {
            root = fxLoader.load();
            ExportWindowController controller = fxLoader.getController();
            controller.setmodel(fcModel);
            controller.setmodel(modelData);
            exportWindow.getChildren().setAll(root);
        } catch (IOException ex) {
            AlertWindow alert = new AlertWindow("ExportWindow error", null, "It can show Exportview");
        }
    }

    @FXML
    private void customDataMenuSelect(ActionEvent event) throws FileNotFoundException, ParseException {
        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("/GUI/CustomDataWindow.fxml"));
        Parent root;
        try {
            root = fxLoader.load();
            CustomDataWindowController controller = fxLoader.getController();
            controller.setmodel(fcModel);
            controller.setmodel(modelData);
            exportWindow.getChildren().setAll(root);
        } catch (IOException ex) {
            AlertWindow alert = new AlertWindow("ExportWindow error", null, "It can show Exportview");
        }
    }

    @FXML
    private void adminMenuSelect(ActionEvent event) {
    }

    void setmodel(FilesConvertionModel fcModel) {
        this.fcModel = fcModel;
    }

    void setmodel(LoginDataModel modelData) {
        this.modelData = modelData;
    }

}
