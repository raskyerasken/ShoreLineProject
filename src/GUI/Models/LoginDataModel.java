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
import DAL.ConnectionPool.DalException;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ander
 */
public class LoginDataModel 
{  private static volatile LoginDataModel instance;

    public LoginDataModel() throws DalException {
    bllManagerUsr= new BLLManagerUserLogin();
    }

    public static LoginDataModel getInstance(String value) throws DalException {
         
        if (instance == null) {
            synchronized (LoginDataModel.class) {
                if (instance == null) {
                    instance = new LoginDataModel();
                }
            }
        }
        return instance;
    }
    String userNameSavedToString;
    Boolean userAccessLevel;
    
    private final BLL.BLLManagerUserLogin bllManagerUsr;
           
    private final ObservableList<UserLogin> userLoginToList 
            = FXCollections.observableArrayList();
    
    public void logListUpdate() throws DalException, SQLException 
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
