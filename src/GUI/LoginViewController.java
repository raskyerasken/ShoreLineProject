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
    @FXML
    private JFXButton forgotPassword;

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
        Stage newStage = new Stage();
        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
        Parent root = fxLoader.load();
        MainWindowController controller = fxLoader.getController();
        Scene scene = new Scene(root);
        newStage.setScene(scene);
        newStage.show();
    }
    
}
