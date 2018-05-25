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
import DAL.ConnectionPool.DalException;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Labeled;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author jacob
 */
public class AddUserViewController implements Initializable 
{
    
    UserLogin userLogin = new UserLogin();
    LoginDataModel modelData ;
    BLLManagerUserLogin bllManagerul ;
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
    @FXML
    private TableView<UserLogin> createdUserTbl;
    @FXML
    private TableColumn<UserLogin, String> userTbl;
    @FXML
    private TableColumn<UserLogin, String> emailTbl;
    @FXML
    private TableColumn<UserLogin, Boolean> adminTbl;
    @FXML
    private JFXTextField search;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        try {
            bllManagerul = new BLLManagerUserLogin();
        } catch (DalException ex) {
            Logger.getLogger(AddUserViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        addColumsToTableView();
        Thread t = new Thread(()
                -> 
        {
            createdUserTbl.setItems(modelData.getUserInformationToList());
<<<<<<< HEAD
            modelData.logListUpdate();
            errorColor();
=======
            try {
                modelData.logListUpdate();
            } catch (DalException ex) {
                Logger.getLogger(AddUserViewController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(AddUserViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
>>>>>>> 5f8d45245c75b8c191c6e3be6a6a04f00eac85fc
            searchLogView();
        });
        t.start();
        validatorMessages();
        validators();
    }
    
    private void addColumsToTableView()
    {
        userTbl.setCellValueFactory(new PropertyValueFactory("userName"));
        emailTbl.setCellValueFactory(new PropertyValueFactory("email"));
        adminTbl.setCellValueFactory(new PropertyValueFactory("accessLevel"));
    }
    
    private void searchLogView() 
    {
        FilteredList<UserLogin> filteredData;
        filteredData = new FilteredList<>(modelData.getUserInformationToList(), p -> true);
        search.textProperty().addListener((observable, oldValue, newValue)
                -> 
        {
            filteredData.setPredicate(userLogin
                    -> 
            {
                if (newValue == null || newValue.isEmpty()) 
                {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (userLogin.getUserName().toLowerCase().contains(lowerCaseFilter)) 
                {
                    return true;
                } else if (userLogin.getEmail().toString().contains(lowerCaseFilter)) 
                {
                    return true; // Filter matches last name.
                }

                return false;

            });

        });
        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<UserLogin> sortedData = new SortedList<>(filteredData);
        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(createdUserTbl.comparatorProperty());
        // 5. Add sorted (and filtered) data to the table.
        createdUserTbl.setItems(sortedData);
    }
    
    @FXML
    private void goBack(ActionEvent event) throws SQLException 
    {
        FXMLLoader fxLoader = new FXMLLoader(getClass().getResource("/GUI/MainWindow.fxml"));
        Parent root;
        try 
        {
            root = fxLoader.load();
            MainWindowController controller = fxLoader.getController();
            controller.setmodel(fcModel,modelData);
            addUser.getChildren().setAll(root);
        } 
        catch (IOException ex) 
        {
            AlertWindow alert = new AlertWindow("MainWindow error", null, "It can show Exportview");
        }
    }

    @FXML
    private void CreateAccount(ActionEvent event) throws DalException, DalException 
    {
        
        userLogin.setEmail(txtEmail.getText());
        userLogin.setPassword(txtPassword.getText());
        String passwordAgain = txtPasswordAgain.getText();
        userLogin.setUserName(txtUsername.getText());
        if (userLogin.getEmail().isEmpty()
                || userLogin.getPassword().isEmpty()
                || passwordAgain.isEmpty()
                || userLogin.getUserName().isEmpty()) 
        {
            showErrorDialog("Empty fields", null, "Please insert something to each field.");
        } 
        else 
        {
            if (userLogin.getPassword().equals(passwordAgain)) 
            {
                try 
                {
                    if (bllManagerul.usernameAvaible(userLogin.getUserName())) 
                    {
                        userLogin.setAccessLevel(adminAccessLevelChckBox.isSelected());
                        bllManagerul.createNewUser(userLogin);
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
    
    private void errorColor()
    {
        createdUserTbl.setRowFactory(Callback -> new TableRow<UserLogin>()
        {   
            
            public void updateItem(UserLogin item, boolean empty)
            {
                super.updateItem(item, empty);

                if (item == null || empty) 
                {
                    setStyle("");
                } 
                else 
                {
                    //Now 'item' has all the info of the Person in this row
                    if (item.isAccessLevel() == true) 
                    {
                        //We apply now the changes in all the cells of the row
                        for(int i=0; i<getChildren().size();i++)
                        {
                            ((Labeled) getChildren().get(i)).setTextFill(Color.GREEN);
                        }                        
                    } 
                    else 
                    {
                        if(getTableView().getSelectionModel().getSelectedItems().contains(item))
                        {
                            for(int i=0; i<getChildren().size();i++)
                            {
                                ((Labeled) getChildren().get(i)).setTextFill(Color.ORANGE);;
                            }
                        }
                        else
                        {
                            for(int i=0; i<getChildren().size();i++)
                            {
                                ((Labeled) getChildren().get(i)).setTextFill(Color.BLACK);;
                            }
                        }
                    }
                }
            }
        });
    }
}
