/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Models;

import BE.UpdateLog;
import BE.UserLogin;
import BLL.BLLManagerUpdateLog;
import BLL.BLLManagerUserLogin;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ander
 */
public class LoginDataModel 
{
    String userNameSavedToString;
    Boolean userAccessLevel;
    
    private final BLL.BLLManagerUserLogin bllManagerUsr 
            = new BLLManagerUserLogin();
    private final ObservableList<UserLogin> userLoginToList 
            = FXCollections.observableArrayList();
    
    public void logListUpdate() 
    { 
        userLoginToList.setAll(bllManagerUsr.getUserDataToTableView());
    }

    public ObservableList<UserLogin> getUserInformationToList() 
    {
        return userLoginToList;
    }
    
    public void addUserLoginData(String user)
    {
        userNameSavedToString = user;
    }

    public String getUserLogin() 
    {
        return userNameSavedToString;
    }
    
    public void addAccessLoginData(Boolean user)
    {
        userAccessLevel = user;
    }
    
    public Boolean getUserAccessLevel()
    {
        return userAccessLevel;
    }
}
