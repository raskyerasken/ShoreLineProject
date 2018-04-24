/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BE.UserLogin;
import BLL.BLLManagerUserLogin;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;

/**
 * FXML Controller class
 *
 * @author jacob
 */
public class AddUserViewController implements Initializable {

    BLLManagerUserLogin ul = new BLLManagerUserLogin();
    @FXML
    private CheckBox checkBoxAccesLevel;
    @FXML
    private JFXTextField txtUsername;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXPasswordField txtPassword;
    @FXML
    private JFXPasswordField txtPasswordAgain;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void goBack(ActionEvent event) {
    }

    @FXML
    private void CreateAccount(ActionEvent event) {
        UserLogin newUser = new UserLogin();
        newUser.setEmail(txtEmail.getText());
        newUser.setPassword(txtPassword.getText());
        String passwordAgain = txtPasswordAgain.getText();
        newUser.setUserName(txtUsername.getText());
        if (newUser.getEmail().isEmpty()
                || newUser.getPassword().isEmpty()
                || passwordAgain.isEmpty()
                || newUser.getUserName().isEmpty()) {
            showErrorDialog("Empty fields", null, "Please insert something to each field.");
        } else {
            if (newUser.getPassword().equals(passwordAgain)) {
                try {
                    if (ul.usernameAvaible(newUser.getUserName())) {
                        newUser.setAccessLevel(checkBoxAccesLevel.isSelected());
                        ul.createNewUser(newUser);
                    } else {
                        showErrorDialog("Username used", null, "Username is already used");
                    }
                } catch (SQLException ex) {
                    showErrorDialog("SQL erroe", null, "Chech your database connection");
                }

            } else {
                showErrorDialog("Password does not match", null, "It has to be the same password in password and password again.");
            }
        }
    }

    private void showErrorDialog(String title, String header, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
