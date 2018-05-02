/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BE.UpdateLog;
import static GUI.LogViewController.lines;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
    @FXML
    private JFXListView<UpdateLog> LogView;
    int fileLinesNumber = 2;
    @FXML
    private JFXTextField searchTxt;
    boolean search = false;
    @FXML
    private JFXButton searchButton;
    FilteredList<String> searchData 
            = new FilteredList<>(lines, p -> true);
    
    public void initialize(URL url, ResourceBundle rb) 
    {
        try {
            //        displayLoginText();
            LogView.setItems((ObservableList<UpdateLog>)model.getAllLogUpdates());
        } catch (SQLException ex) {
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
    
    private void searchLogView()
    {
//        searchTxt.textProperty().addListener((observable, oldValue, newValue) -> {
//        searchData.setPredicate(lines -> 
//        {
//            // If filter text is empty, display all logs.
//            if (newValue == null || newValue.isEmpty()) 
//            {
//                return true;
//            }
//
//            // Compare input text to all log.
//            String lowerCaseFilter = newValue.toLowerCase();
//
//            if (lines.toLowerCase().contains(lowerCaseFilter)) 
//            {
//                return true; 
//            } 
//
//            return false; // Does not match.
//        });
//        });
//
//        // 3. Wrap the FilteredList in a SortedList. 
//        SortedList<String> sortedData = new SortedList<>(searchData);
//
//        // 5. Add sorted (and filtered) data to the table.
//        LogView.setItems(sortedData);
    }


    @FXML
    private void closeMenu(ActionEvent event) 
    {
        
    }

    @FXML
    private void deleteItem(ActionEvent event) 
    {
        
    }

    @FXML
    private void edit(ActionEvent event) 
    {
        
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
    
}
