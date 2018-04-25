/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import BE.UserLogin;
import DAL.DataBaseUserLogin;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.SQLException;
import javafx.collections.ObservableList;

/**
 *
 * @author ander
 */
public class BLLManagerUserLogin 
{
    DataBaseUserLogin ul = new DataBaseUserLogin();
    
    
    public void getPassword(UserLogin userLogin) throws SQLServerException, SQLException 
    {
        ul.setPassword(userLogin);
    }

    public boolean getAccess(UserLogin userLogin) throws SQLException 
    {
        return ul.getAccess(userLogin);
    }

    
    
    public boolean usernameAvaible(String Username) throws SQLException 
    {
        return ul.usernameAvaible(Username); 
    }


    public void createNewUser(UserLogin newUser) throws SQLException 
    {
        ul.createNewUser(newUser);
    }
}
