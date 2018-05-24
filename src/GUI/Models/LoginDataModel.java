/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Models;

/**
 *
 * @author ander
 */
public class LoginDataModel 
{  private static volatile LoginDataModel instance;

    public LoginDataModel() {}

    public static LoginDataModel getInstance(String value) {
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
