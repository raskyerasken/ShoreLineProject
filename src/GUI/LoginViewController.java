/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.Models.LoginDataModel;
import GUI.Models.FilesConvertionModel;
import BE.UpdateLog;
import BE.UserLogin;
import BLL.BLLManagerUpdateLog;
import BLL.BLLManagerUserLogin;
import DAL.ConnectionPool.DalException;
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

    LoginDataModel modelData;
    FilesConvertionModel fcModel;
    @FXML
    JFXTextField userNameID;
    @FXML
    public JFXPasswordField userPassword;
    @FXML
    private JFXCheckBox rememberUser;
    BLL.BLLManagerUserLogin ul;
    BLL.BLLManagerUpdateLog up = new BLLManagerUpdateLog();
    UpdateLog updateLog = new UpdateLog();
    UserLogin userLogin = new UserLogin();
    List<String> lines = new ArrayList<String>();
    int rememberMeLineNr = 2;
    private String filePathString = "UserLogin.txt";

    public void initialize(URL url, ResourceBundle rb)
    {
        try
        {
            modelData = LoginDataModel.getInstance();
        }
        catch (DalException ex)
        {
            Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        fcModel = FilesConvertionModel.getInstance();
        try
        {
            ul = new BLLManagerUserLogin();
        }
        catch (DalException ex)
        {
            Logger.getLogger(LoginViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        rememberMeFunction();
    }
 /*
    Check if password and username match in the database
     */
    @FXML
    private void login(ActionEvent event) throws DalException
    {
        try
        {
            userLogin.setPassword(userPassword.getText());
            userLogin.setUserName(userNameID.getText());

            if (ul.getAccess(userLogin))
            {
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
                modelData.addUserLoginData(userNameID.getText());
                modelData.addAccessLoginData(ul.getAdminAccess(userLogin));

                openMainWindow();
            }
            else
            {
                showErrorDialog("Wrong Password", null, "Please insert correct password");
            }
        }
        catch (SQLException ex)
        {
            AlertWindow alert = new AlertWindow("Database connection error", null,
                    "Database connection error, check your connection");
        }
    }

    private void openMainWindow() throws SQLException
    {
        try
        {
            Stage newStage = new Stage();
            FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
            Parent root = fxLoader.load();
            MainWindowController controller = fxLoader.getController();
            controller.setmodel(fcModel, modelData);

            Scene scene = new Scene(root);
            newStage.setScene(scene);
            newStage.show();
            Stage stage = (Stage) userNameID.getScene().getWindow();
            stage.close();
        }
        catch (IOException ex)
        {
            AlertWindow alert = new AlertWindow("Mainwindow can open", null, "Something wrong in Mainwindow with setmodel or Initializable");
        }

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
        try
        {
            //writes the user and pw to a txt file, but overwrites it everytime
            writer = new PrintWriter("UserLog.txt", "UTF-8");
            writer.println("The logged in: ");

            timeLog();
            writer.println(userNameID.getText());
            writer.println(userPassword.getText());
            writer.close();
        }
        catch (FileNotFoundException ex)
        {
            AlertWindow alert = new AlertWindow("File not found", null, "File not found");
        }
        catch (UnsupportedEncodingException ex)
        {
            AlertWindow alert = new AlertWindow("Unstuppoerted encoding", null, "UnsupportEncoding");
        }
        catch (IOException ex)
        {
            AlertWindow alert = new AlertWindow("IOException", null, "IO Exception");
        }
    }
 /*
    Save who login to a txt file
     */
    private void timeLog() throws FileNotFoundException, UnsupportedEncodingException, IOException
    {
        File f = new File(filePathString);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        if (f.exists() && !f.isDirectory())
        {
            try
            {
                String filename = filePathString;
                FileWriter writer = new FileWriter(filePathString, true);
                writer.write("The user " + userLogin.getUserName() + " logged in.    " + "Date: " + dateFormat.format(date));
                writer.write(System.getProperty("line.separator"));
                writer.close();
            }
            catch (IOException ioe)
            {
                AlertWindow alert = new AlertWindow("IOException", null, "IOException");
            }
        }
        else
        {
            PrintWriter writer = new PrintWriter("UserLogin.txt", "UTF-8");
            writer.println("The user " + userLogin.getUserName() + " logged in.   " + "Date: " + dateFormat.format(date));
            writer.close();
        }
    }
 /*
  Wrtite nothing to txt file if remember me is not on 
     */
    private void writeNothingTxt()
    {
        PrintWriter writer = null;
        try
        {
            //writes the user and pw to a txt file, but overwrites it everytime
            writer = new PrintWriter("UserLog.txt", "UTF-8");
            writer.println("Remember me is not selected");
            writer.close();
        }
        catch (FileNotFoundException ex)
        {
            AlertWindow alert = new AlertWindow("FileNotFoundException", null, "FileNotFoundException");
        }
        catch (UnsupportedEncodingException ex)
        {
            AlertWindow alert = new AlertWindow("UnsupportedEncodingException", null, "UnsupportedEncodingException");
        }
        finally
        {
            writer.close();
        }
    }

    //reads the userlogin text file
    private void readUserLoginTxt()
    {
        BufferedReader br = null;
        try
        {
            //Should read the file
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
            AlertWindow alert = new AlertWindow("FileNotFoundException", null, "FileNotFoundException");
        }
        catch (IOException ex)
        {
            AlertWindow alert = new AlertWindow("IOException", null, "IOException");
        }
        finally
        {
            try
            {
                br.close();
            }
            catch (IOException ex)
            {
                AlertWindow alert = new AlertWindow("IOException", null, "IOException");
            }
        }
    }
 /*
    save username and password to txt file if remember me are on.
     */
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

            if (lines.size() > rememberMeLineNr)
            {
                rememberUser.setSelected(true);

                userNameID.setText(lines.get(1));
                userPassword.setText(lines.get(2));
            }
        }
        catch (FileNotFoundException ex)
        {
            AlertWindow alert = new AlertWindow("FileNotFoundException", null, "FileNotFoundException");
        }
        catch (IOException ex)
        {
            AlertWindow alert = new AlertWindow("IOException", null, "IOException");
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

    }

}
