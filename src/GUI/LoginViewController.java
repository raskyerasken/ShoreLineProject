/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
    }    

    @FXML
    private void login(ActionEvent event) throws IOException 
    {
        if (userPassword.getLength() == 4) 
        {
//            Stage newStage = new Stage();
//            FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
//            Parent root = fxLoader.load();
//            MainWindowController controller = fxLoader.getController();
//            Scene scene = new Scene(root);
//            newStage.setScene(scene);
//            newStage.show();
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

    @FXML
    private void forgotPassword(ActionEvent event) 
    {
        showErrorDialog("You sure?", null, "Well, if yes then that's because you are an idiot.");
    }
    
}
