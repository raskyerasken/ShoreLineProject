/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BE.UpdateLog;
import BE.UserLogin;
import BLL.BLLManagerUpdateLog;
import BLL.BLLManagerUserLogin;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
<<<<<<< HEAD
    LoginDataModel modelData = new LoginDataModel();
=======
    
    FilesConvertionModel fcModel = new FilesConvertionModel();
>>>>>>> 177c120ba5303a54d7d6c496806c04a993492939
    @FXML
    JFXTextField userNameID;
    @FXML
    public JFXPasswordField userPassword;
    @FXML
    private JFXCheckBox rememberUser;
    BLL.BLLManagerUserLogin ul = new BLLManagerUserLogin();
    BLL.BLLManagerUpdateLog up = new BLLManagerUpdateLog();
    UpdateLog updateLog = new UpdateLog();
    UserLogin userLogin = new UserLogin();
    List<String> lines = new ArrayList<String>();
    int rememberMeLineNr = 2;
    private String filePathString = "UserLogin.txt";
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        rememberMeFunction();
    }    
    
    @FXML
    private void login(ActionEvent event) 
    {
<<<<<<< HEAD
        
        userLogin.setPassword(userPassword.getText());
        userLogin.setUserName(userNameID.getText());
        
        //java.util.Date utilDate = new java.util.Date();
=======
        try {
            userLogin.setPassword(userPassword.getText());
            userLogin.setUserName(userNameID.getText());
            
            //java.util.Date utilDate = new java.util.Date();
>>>>>>> 177c120ba5303a54d7d6c496806c04a993492939
//        Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
//        java.sql.Timestamp sqlDate = new java.sql.Timestamp(currentTimestamp.getTime());
        
        modelData.addLoginData(userNameID.getText());
        
//        updateLog.setAdjustment(userNameID.getText());
//        updateLog.setDatelog(sqlDate);
//        up.setUpdateLog(updateLog);

if (ul.getAccess(userLogin))
{
    
    /**
     * Writes in to a file if the remember me box is checked,
     * if not it writes nothing
     */
    if (rememberUser.isSelected())
    {
        writeUserLoginTxt();
        readUserLoginTxt();
        System.out.println("from txt file: " + lines);
    }
    else
    {
        writeNothingTxt();
    }
    
    //System.out.println("User is logged in: " + userLogin.getUserName());
    openMainWindow();
}
else
    showErrorDialog("Wrong Password", null, "Please insert correct password");
        } catch (SQLException ex) {
         AlertWindow  alert = new AlertWindow("Database connection error", null, "Database connection error, check your connection");
        }
    }
    
    private void openMainWindow() 
    {
<<<<<<< HEAD
        Stage newStage = new Stage();
        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
        Parent root = fxLoader.load();
        MainWindowController controller = fxLoader.getController();
        controller.modelData(modelData);
        Scene scene = new Scene(root);
        newStage.setResizable(false);
        newStage.setScene(scene);
        newStage.show();
        Stage stage = (Stage) userNameID.getScene().getWindow();
        stage.close();
=======
        try {
            Stage newStage = new Stage();
            FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
            Parent root = fxLoader.load();
            MainWindowController controller = fxLoader.getController();
            controller.setmodel(fcModel);
            Scene scene = new Scene(root);
            newStage.setResizable(false);
            newStage.setScene(scene);
            newStage.show();
            Stage stage = (Stage) userNameID.getScene().getWindow();
            stage.close();
        } catch (IOException ex) {
           AlertWindow  alert = new AlertWindow("MainWindow error", null, "It can not show MainWindow view");
        }
>>>>>>> 177c120ba5303a54d7d6c496806c04a993492939
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
    private void writeUserLoginTxt()
    {
        PrintWriter writer = null;
        try {
            //writes the user and pw to a txt file, but overwrites it everytime
            writer = new PrintWriter("UserLog.txt", "UTF-8");
            writer.println("The logged in: ");
            timeLog();
            writer.println(userLogin.getUserName());
            writer.println(userLogin.getPassword());
            writer.close();
        } catch (FileNotFoundException ex) { 
              AlertWindow  alert = new AlertWindow("File not found", null, "File not found");
        } catch (UnsupportedEncodingException ex) {
             AlertWindow  alert = new AlertWindow("Unstuppoerted encoding", null, "UnsupportEncoding");
        } catch (IOException ex) {
              AlertWindow  alert = new AlertWindow("IOException", null, "IO Exception");
        } finally {
            writer.close();
        }
    }
    
    private void timeLog() throws FileNotFoundException, UnsupportedEncodingException, IOException
    {
        File f = new File(filePathString);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        if (f.exists() && !f.isDirectory())
        {
            try
            {
                String filename= filePathString;
                FileWriter writer = new FileWriter(filePathString,true);
                writer.write("The user " + userLogin.getUserName() + " logged in.    " + "Date: " + dateFormat.format(date));
                writer.write(System.getProperty( "line.separator" ));
                writer.close();
            }
            
            catch (IOException ioe)
            {
                  AlertWindow  alert = new AlertWindow("IOException", null, "IOException");
            }
        }
        
        else  
        {
            PrintWriter writer = new PrintWriter("UserLogin.txt", "UTF-8");
            writer.println("The user "+ userLogin.getUserName() +" logged in.   " + "Date: " + dateFormat.format(date));

            //writer.println("Date: " + dateFormat.format(date));
            writer.close(); 
        }
    }
    
    private void writeNothingTxt() 
    {
        PrintWriter writer = null;
        try {
            //writes the user and pw to a txt file, but overwrites it everytime
            writer = new PrintWriter("UserLog.txt", "UTF-8"); 
            writer.println("Remember me is not selected");
            writer.close();
        } catch (FileNotFoundException ex) {
            AlertWindow  alert = new AlertWindow("FileNotFoundException", null, "FileNotFoundException");
        } catch (UnsupportedEncodingException ex) {
           AlertWindow  alert = new AlertWindow("UnsupportedEncodingException", null, "UnsupportedEncodingException");
        } finally {
            writer.close();
        }
    }
    
    //reads the userlogin text file
    private void readUserLoginTxt() 
    {            
        BufferedReader br = null;
        try {
            //Should read the file
            br = new BufferedReader(new FileReader("UserLog.txt"));
            String line = br.readLine();
            while (line != null)
            {
                lines.add(line);
                line = br.readLine();
            }
        } catch (FileNotFoundException ex) {
           AlertWindow  alert = new AlertWindow("FileNotFoundException", null, "FileNotFoundException");
        } catch (IOException ex) {
          AlertWindow  alert = new AlertWindow("IOException", null, "IOException");
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                AlertWindow  alert = new AlertWindow("IOException", null, "IOException");
            }
        }
    }
    
    private void rememberMeFunction()
    {
        BufferedReader br = null;
        try 
        {
            br = new BufferedReader(new FileReader("UserLog.txt"));
            String line = br.readLine();
            while (line != null)
            {
                lines.add(line);
                line = br.readLine();
            }
        } 
        
        catch (FileNotFoundException ex) 
        {
            AlertWindow  alert = new AlertWindow("FileNotFoundException", null, "FileNotFoundException");
        } 
        
        catch (IOException ex) 
        {
            AlertWindow  alert = new AlertWindow("IOException", null, "IOException");
        } 
        
        finally 
        {
            try 
            {
                br.close();
            } 
            catch (IOException ex) 
            {
                Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if (lines.size() > rememberMeLineNr) 
        {
            rememberUser.setSelected(true);
            userNameID.setText(lines.get(1));
            userPassword.setText(lines.get(2));
        }
    }
        
    private void forgotPassword(ActionEvent event) 
    {
        showErrorDialog("You sure?", null, "Well, if yes then that's because you are an idiot.");
    }

}
