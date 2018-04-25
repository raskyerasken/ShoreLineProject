/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BE.UserLogin;
import BLL.BLLManagerUserLogin;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ander
 */
public class LoginViewController implements Initializable 
{

    @FXML
    private JFXTextField userNameID;
    @FXML
    private JFXPasswordField userPassword;
    @FXML
    private JFXCheckBox rememberUser;
    BLL.BLLManagerUserLogin ul = new BLLManagerUserLogin();
    UserLogin userLogin = new UserLogin();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
    }    
    
    

    @FXML
    private void login(ActionEvent event) throws IOException, SQLException 
    {
        userLogin.setPassword(userPassword.getText());
        userLogin.setUserName(userNameID.getText());
        
        if (ul.getAccess(userLogin)) 
        {
            //System.out.println("User is logged in: " + userLogin.getUserName());
            Stage newStage = new Stage();
            FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
            Parent root = fxLoader.load();
            MainWindowController controller = fxLoader.getController();
            Scene scene = new Scene(root);
            newStage.setResizable(false);
            newStage.setScene(scene);
            newStage.show();
            Stage stage = (Stage) rememberUser.getScene().getWindow();
            stage.close();
            
            writeUserLoginTxt();
            readUserLoginTxt();
        }
        else
            showErrorDialog("Wrong Password", null, "Please insert correct password");
    }
    
    private void showErrorDialog(String title, String header, String message) 
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    //writes the login to a text file that we later can read
    private void writeUserLoginTxt() throws FileNotFoundException, UnsupportedEncodingException
    {
        //writes the user and pw to a txt file, but overwrites it everytime
        PrintWriter writer = new PrintWriter("UserLog.txt", "UTF-8");
        writer.println("The user logged in: ");
        writer.println(userLogin.getUserName());
        writer.println(userLogin.getPassword());
        writer.close(); 
    }
    
    //reads the userlogin text file
    private void readUserLoginTxt() throws FileNotFoundException, IOException
    {            
        //Should read the file
        List<String> lines = new ArrayList<String>();
        BufferedReader br = new BufferedReader(new FileReader("UserLog.txt"));
        String line = br.readLine();
        while (line != null)
        {
            lines.add(line);
            line = br.readLine();
        }

        System.out.println("from txt file: " + lines);
    }
        
    @FXML
    private void forgotPassword(ActionEvent event) 
    {
        showErrorDialog("You sure?", null, "Well, if yes then that's because you are an idiot.");
    }
    
}
