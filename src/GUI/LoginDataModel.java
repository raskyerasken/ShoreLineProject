/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author ander
 */
public class LoginDataModel 
{
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
