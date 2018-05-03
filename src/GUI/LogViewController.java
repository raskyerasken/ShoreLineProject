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
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
public class LogViewController implements Initializable 
{
    UpdateLogViewModel model = new UpdateLogViewModel();
    public static final ObservableList lines = 
    FXCollections.observableArrayList();
    int fileLinesNumber = 2;
    @FXML
    private JFXTextField searchTxt;
    boolean search = false;
    FilteredList<String> searchData 
            = new FilteredList<>(lines, p -> true);
    @FXML
    private TableView<UpdateLog> LogView;
    @FXML
    private TableColumn<UpdateLog, String> userNameTable;
    @FXML
    private TableColumn<UpdateLog, Date> timeTable;
    @FXML
    private TableColumn<UpdateLog, String> adjustment;
    @FXML
    private AnchorPane exportWindow;
    @FXML
    private Label taskXRun;
    
    public void initialize(URL url, ResourceBundle rb) 
    {
        addItemsToList();
        try 
        {
            LogView.setItems((ObservableList<UpdateLog>)model.getAllLogUpdates());
        } 
        
        catch (SQLException ex) 
        {
            Logger.getLogger(LogViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        searchLogView();
    }   
    
    private void readUserLoginTxt() throws FileNotFoundException, IOException
    {            
        //Should read the file
        BufferedReader br = new BufferedReader(new FileReader("UserLogin.txt"));
        String line = br.readLine();
        while (line != null)
        {
            lines.add(line);
            line = br.readLine();
            LogView.setItems(lines);
        }
    }
    
    private void addItemsToList()
    {
        userNameTable.setCellValueFactory(new PropertyValueFactory("Username"));
        timeTable.setCellValueFactory(new PropertyValueFactory("Datelog"));
        adjustment.setCellValueFactory(new PropertyValueFactory("Adjustment"));
    }
    
    private void filterTableView() throws SQLException
    {
        FilteredList<UpdateLog> filteredData 
                = new FilteredList<>(model.getAllLogUpdates(), p -> true);
        searchTxt.textProperty().addListener((observable, oldValue, newValue) ->
        {
            filteredData.setPredicate(updateLog ->
            {
                if (newValue == null || newValue.isEmpty()) 
                {
                    return true;
                }
                
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (updateLog.getUsername().toLowerCase().contains(lowerCaseFilter)) 
                {
                    return true;
                }
                else if (updateLog.getDatelog().toString().contains(lowerCaseFilter)) 
                {
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
    
    private void searchLogView()
    {
        try 
        {
            filterTableView();
        } 
        
        catch (SQLException ex) 
        {
            Logger.getLogger(LogViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    
    private void displayLoginText()
    {
        try 
        {
            readUserLoginTxt();
        } 
        
        catch (IOException ex) 
        {
            Logger.getLogger(LogViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void importData(ActionEvent event) {
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
    private void importMenuSelect(ActionEvent event) {
    }

    @FXML
    private void exportMenuSelect(ActionEvent event) {
    }

    @FXML
    private void customDataMenuSelect(ActionEvent event) {
    }

    @FXML
    private void logMenuSelect(ActionEvent event) {
    }

    @FXML
    private void adminMenuSelect(ActionEvent event) {
    }
    
}
