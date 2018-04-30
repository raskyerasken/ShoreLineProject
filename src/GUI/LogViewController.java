/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXListView;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    public static final ObservableList lines = 
    FXCollections.observableArrayList();
    @FXML
    private JFXListView<String> LogView;
    
    
    public void initialize(URL url, ResourceBundle rb) 
    {
        displayLoginText();
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

    @FXML
    private void searchLogView(ActionEvent event) 
    {
        
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
        System.out.println(lines);
    }
    
}
