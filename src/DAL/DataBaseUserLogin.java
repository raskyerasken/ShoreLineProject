/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BE.UserLogin;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ander
 */
public class DataBaseUserLogin 
{
    private ConnectionManagerSLProject cm = new ConnectionManagerSLProject();
    
    public void setUserLogin(UserLogin userLogin) throws SQLException
    {
        try(Connection con = cm.getConnection())
        {
            String sql
                    = "INSERT * INTO UserLogin"
                    + "(Username, Password, Email)"
                    + "VALUES (?, ?, ?)";
            
            PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, userLogin.getPassword());
            pstmt.setString(2, userLogin.getUserName());
            pstmt.setString(3, userLogin.getEmail());
            pstmt.setBoolean(4, userLogin.isAccessLevel());
            
            //if it doesn't return anything do this
            int affected = pstmt.executeUpdate();
            if (affected < 1) 
            {
                throw new SQLException("Could not add Password / Username");
            }
        } 
        
        catch (SQLServerException ex) 
        {
            Logger.getLogger(DataBaseUserLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean getAccess(UserLogin userLogin) throws SQLException
    {
        UserLogin userAccess = new UserLogin();
        
        try(Connection con = cm.getConnection())
        {
            String query
                    =("SELECT * FROM UserLogin"
                    + "WHERE Username LIKE ?");
            
            PreparedStatement pstmt = con.prepareStatement(query);
            
            pstmt.setString(1, userLogin.getUserName());
            
            
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) 
            {
                userAccess.setUserName(rs.getString("Username"));
                userAccess.setPassword(rs.getString("Password"));
            }
        } 
        
        catch (SQLServerException ex) 
        {
            Logger.getLogger(DataBaseUserLogin.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
        return userLogin.getPassword().equals(userAccess.getPassword());
    }
    
}
