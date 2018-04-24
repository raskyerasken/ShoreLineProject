/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import BE.UserLogin;
import DAL.DataBaseUserLogin;
import java.sql.SQLException;

/**
 *
 * @author ander
 */
public class BLLManagerUserLogin 
{
    DataBaseUserLogin ul = new DataBaseUserLogin();
    
    public void addCourse(UserLogin userLogin) throws SQLException 
    {
        ul.setUserLogin(userLogin);
    }

    public boolean getAccess(UserLogin userLogin) throws SQLException 
    {
        return ul.getAccess(userLogin);
    }
}
