/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.Models.LoginDataModel;
import GUI.Models.FilesConvertionModel;
import BE.UserLogin;
import BLL.BLLManagerUserLogin;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author jacob
 */
public class AddUserViewController implements Initializable 
{
    
    UserLogin userLogin = new UserLogin();
    LoginDataModel modelData = new LoginDataModel();
    BLLManagerUserLogin bllManagerul = new BLLManagerUserLogin();
    @FXML
    private CheckBox adminAccessLevelChckBox;
    @FXML
    private JFXTextField txtUsername;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXPasswordField txtPassword;
    @FXML
    private JFXPasswordField txtPasswordAgain;
    private FilesConvertionModel fcModel;
    @FXML
    private AnchorPane addUser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        validatorMessages();
        validators();
    }

    @FXML
    private void goBack(ActionEvent event) throws SQLException 
    {
        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("/GUI/MainWindow.fxml"));
        Parent root;
        try {
            root = fxLoader.load();
            MainWindowController controller = fxLoader.getController();
            controller.setmodel(fcModel,modelData);
            controller.setmodel(fcModel);
            controller.modelData(modelData);
            controller.setmodel(fcModel,modelData);
            addUser.getChildren().setAll(root);
        } 
        catch (IOException ex) 
        {
            AlertWindow alert = new AlertWindow("MainWindow error", null, "It can show Exportview");
        }
    }

    @FXML
    private void CreateAccount(ActionEvent event) 
    {
        UserLogin newUser = new UserLogin();
        newUser.setEmail(txtEmail.getText());
        newUser.setPassword(txtPassword.getText());
        String passwordAgain = txtPasswordAgain.getText();
        newUser.setUserName(txtUsername.getText());
        if (newUser.getEmail().isEmpty()
                || newUser.getPassword().isEmpty()
                || passwordAgain.isEmpty()
                || newUser.getUserName().isEmpty()) 
        {
            showErrorDialog("Empty fields", null, "Please insert something to each field.");
        } 
        else 
        {
            if (newUser.getPassword().equals(passwordAgain)) 
            {
                try 
                {
                    if (bllManagerul.usernameAvaible(newUser.getUserName())) 
                    {
                        newUser.setAccessLevel(adminAccessLevelChckBox.isSelected());
                        bllManagerul.createNewUser(newUser);
                        clearText();
                    } 
                    else 
                    {
                        showErrorDialog("Username used", null, "Username is already exist");
                    }
                } 
                catch (SQLException ex) 
                {
                    showErrorDialog("SQL erroe", null, "Chech your database connection");
                } 
            }
            else 
            {
                showErrorDialog("Password does not match", null, "It has to be the same password in password and password again.");
            }
        }
    }
    
    private void clearText()
    {
        txtEmail.clear();
        txtPassword.clear();
        txtPasswordAgain.clear();
        txtUsername.clear();
    }

    private void showErrorDialog(String title, String header, String message) 
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    void modelData(LoginDataModel modelData) throws SQLException 
    {
        this.modelData = modelData;
        userLogin.setAccessLevel(modelData.getUserAccessLevel());
        System.out.println(modelData.getUserAccessLevel());
    }
    
    void setmodel(FilesConvertionModel fcModel) throws SQLException 
    {
        this.fcModel = fcModel;
    }

    void setmodel(FilesConvertionModel fcModel, LoginDataModel modelData) 
    {
         this.fcModel = fcModel;
         this.modelData= modelData;
    }
    
    private void validators()
    {
        txtUsername.focusedProperty().addListener(new ChangeListener<Boolean>() 
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) 
            {
                if (oldValue) 
                {
                    txtUsername.validate();
                }
            }
        });
        
        txtEmail.focusedProperty().addListener(new ChangeListener<Boolean>() 
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) 
            {
                if (oldValue) 
                {
                    txtEmail.validate();
                }
            }
        });
        
        txtPassword.focusedProperty().addListener(new ChangeListener<Boolean>() 
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) 
            {
                if (oldValue) 
                {
                    txtPassword.validate();
                }
            }
        });
                
        txtPasswordAgain.focusedProperty().addListener(new ChangeListener<Boolean>() 
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) 
            {
                if (oldValue) 
                {
                    txtPasswordAgain.validate();
                }
            }
        });

    }
    
    private void validatorMessages()
    {
        RequiredFieldValidator validator = new RequiredFieldValidator();
        RequiredFieldValidator validator2 = new RequiredFieldValidator();
        RequiredFieldValidator validator3 = new RequiredFieldValidator();
        RequiredFieldValidator validator4 = new RequiredFieldValidator();
        
        validator.setMessage("Field input required");
        validator2.setMessage("Field input required");
        validator3.setMessage("Field input required");
        validator4.setMessage("Field input required");
        
        txtUsername.getValidators().add(validator);
        txtEmail.getValidators().add(validator2);
        txtPassword.getValidators().add(validator3);
        txtPasswordAgain.getValidators().add(validator4);
    }
}
